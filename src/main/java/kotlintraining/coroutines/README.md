# Coroutines

## What are Coroutines?

- A threading abstraction, aka "light weight threads". 
 - Much like `ThreadPoolExecutors`, you 
do not have direct control as to what thread picks up each task. 
   - For the default use case, 
 they are all executed (`async`) on the same `"main"` thread (not concurrent).
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
you can use `Deferred<T>.await()` or `List<Deferred<T>>.awaitAll()` block until all tasks are complete
  - `Deferred` is returned by `CoroutineScope.async`

Previous example shown used `Deferred` :)

```kotlin
val first = async { firstNumber() }
val second = async { secondNumber() }
val third = async { thirdNumber() }

val result = first.await() + second.await() + third.await()
```

- `result` isn't assigned until `first`, `second` and `third` `Deferred` objects have completed their tasks.