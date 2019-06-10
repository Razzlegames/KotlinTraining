# Coroutines

## What are Coroutines?

- A threading abstraction, aka "light weight threads". 
 - Much like `ThreadPoolExecutors`, you 
do not have direct control as to what thread picks up each task. 
   - For the default use case, 
 they are all executed (`async`) on the same `"main"` thread (not concurrent).
     - Tasks are actually queued up and ran in a non-deterministic sequence from a "work" queue for running on the same `JVM` `Thread`
   - You can easily change this to be truly multi threaded.  
  
 
 - These "light weight threads" are scoped within a `CoroutineScope`
which will manage the lifetime of the coroutine.

## How to use Coroutines

```kotlin

runBlocking {

    val first = async { firstNumber() }
    val second = async { secondNumber() }
    val third = async { thirdNumber() }

    val result = first.await() + second.await() + third.await()
    println("result $result")
    
} //  Blocks until all tasks complete 

println("All Tasks Complete")

```

## Flavors

### `launch` : returns `Job`

- "Fire and Forget", you don't need to know the result
- You can wait for coroutine to finish with `Job.join()`

### `async` : returns `Deferred<T>`

- Use when you want to know the results of the coroutine
- You can wait for result with `Deferred<T>.await(): T`
  - This returns the result of the coroutine! :)

## Cool cool, but we have these ThreadPools...

We can easily convert a `ThreadPoolExecutor` into a `CoroutineContext` for use in a coroutine
with  `ExecutorService.asCoroutineDispatcher()`

```kotlin
var javaThreadPool = ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, LinkedBlockingQueue())
val threadPoolContext = javaThreadPool.asCoroutineDispatcher()

val list = Collections.synchronizedList(ArrayList<Int>())
runBlocking(threadPoolContext) {

    println("-RunBlocking Thread--- start : ${Thread.currentThread().name}")
    for (i in 0..10000) {
        async {
            println("-Thread--- start $i : ${Thread.currentThread().name}")
            list.add(i)
        }
    }
}
```

### Question:
 
- Without using an explicit `CoroutineContext` from Kotlin or created from a `ThreadpoolExecutor`, what would you expect to happen?  

- What thread would the coroutines execute on?

```kotlin
runBlocking {

    launch {
      delay(300)
      println("Task from first launch ${Thread.currentThread().name}")
    }
    
    launch {
      delay(200)
      println("Task from second launch ${Thread.currentThread().name}")
    }

    println("Task from coroutine scope ${Thread.currentThread().name}") 

}

println("Coroutine scope is over ${Thread.currentThread().name}") 
```

- What would be printed above? 
  - Note: Delays above are larger than context switching time needed between coroutines

## What about Ol' `Java` `Future`s?

- Coroutines use the analogous `Deferred<T>`.  
  - Similar to `Future.get()`, 
you can use `Deferred<T>.await()` or `List<Deferred<T>>.awaitAll()`, to block until all tasks are complete
  - `Deferred` is returned by `CoroutineScope.async`

Previous example shown used `Deferred` :)

```kotlin
val first = async { firstNumber() }
val second = async { secondNumber() }
val third = async { thirdNumber() }

val result = first.await() + second.await() + third.await()
```

- `result` isn't assigned until `first`, `second` and `third` `Deferred` objects have completed their tasks.

Note: there's a cool extension function called `List<Deferred>.awaitAll()` :) You can use this on a list of tasks to wait till all are complete


# Easy to measure time!

Want to know the time it took to execute a coroutine?  No problem with `measureTimeMillis`

```kotlin
 @Test
    fun lifeCycleTest() {
        val mainTime = measureTimeMillis {
            runBlocking {
                // Run things
            }
        }

        val defaultThreadsTime = measureTimeMillis {
            runBlocking(Dispatchers.Default) {
               // Run other things
            }
        }

        println("------------------------------------------")
        println("time in only Main Thread: $mainTime ms")
        println("time in Default threads: $defaultThreadsTime ms")
        println(">> Savings of: ${mainTime - defaultThreadsTime} ms")
        println("------------------------------------------")
    }
```

This is really handy for measuring Service call metrics at a granular level!

- Know how long each client or Util took in the call etc.


# What about Errors? How do we stop child Coroutines?

*[From the docs](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html#children-of-a-coroutine)* 

>*"When a coroutine is launched in the `CoroutineScope` of another coroutine, it inherits its context via >`CoroutineScope.coroutineContext` and the Job of the new coroutine becomes a child of the parent coroutine's job. When the parent >coroutine is cancelled, all its children are recursively cancelled, too.*

>*However, when `GlobalScope` is used to launch a coroutine, it is not tied to the scope it was launched from and operates independently.*"


This means, basically:
- Any time an exception is thrown and not handled by any subcoroutine, all other coroutines executing in this context will `.cancel()`
- No Orphaned coroutines will continue executing, or leak from the context.
- The exception to this is using `GlobalScope`.
  - If you do this the coroutine is bounded to the **entire** application lifetime.  
  - This is a huge potential for leakage here.  Avoid!
  
  ## Example
  
```kotlin
val request = launch {

    // it spawns two other jobs, one with GlobalScope
    GlobalScope.launch {
        println("job1: I run in GlobalScope and execute independently!")
        doSomeWork1()
        println("job1: I am not affected by cancellation of the request")
    }
    // and the other inherits the parent context
    launch {
        delay(100)
        println("job2: I am a child of the request coroutine")
        doSomeWork2()
        println("job2: I will not execute this line if my parent request is cancelled")
    }
    
    launch {
       println("job3: I am a child of the request coroutine")
       doSomeWork3()
       println("job3: I will not execute this line if my parent request is cancelled")
    }
}
```

- If `doSomeWork2()` throws an exception
  - Anything within the request's `CoroutineContext` is canceled`ed
  - This includes `job3` (`doSomeWork3()`)
  - `job1` is **NOT** canceled ! 
    - The coroutine leaks 💧!
  
