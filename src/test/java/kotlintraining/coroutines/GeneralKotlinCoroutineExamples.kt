package kotlintraining.coroutines

import kotlinx.coroutines.*
import java.util.Collections
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GeneralKotlinCoroutineExamples {

    @Test
    fun coroutinesAsync() {

        runBlocking {

            val list = Collections.synchronizedList(ArrayList<Int>())
            val result = GlobalScope.async {
                println("-Thread--- async scope: ${Thread.currentThread().name}")

                for (i in 0..10000) {
                    launch {
                        println("-Thread--- start $i : ${Thread.currentThread().name}")
                        list.add(i)
                    }
                }
            }

            result.await()
            println("-Main Thread--- running : ${Thread.currentThread().name}")

            verifyListIsNotSequential(list)
        }
    }

    @Test
    fun coroutinesAsyncNoWaiting() {

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking {

            for (i in 0..10000) {
                launch {
                    println("-Thread--- start $i : ${Thread.currentThread().name}")
                    list.add(i)
                }
            }
        }

        println("-Main Thread--- running : ${Thread.currentThread().name}")

        verifyListIsSequential(list)
    }

    @Test
    fun coroutinesAsyncNoWaitingGlobalScope() {

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking {

            for (i in 0..10000) {
                GlobalScope.launch {
                    println("-Thread--- start $i : ${Thread.currentThread().name}")
                    list.add(i)
                }
            }

        }

        println("-Main Thread--- running : ${Thread.currentThread().name}")

        verifyListIsNotSequential(list)
    }

    /**
     *  This is unexpected but this will run all async{} blocks on the same main thread
     */
    @Test
    fun coroutinesWithDefaultThreadPoolContext() {

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking {

            GlobalScope.async {
                println("async  Thread--- start : ${Thread.currentThread().name}")
            }

            println("-RunBlocking Thread--- start : ${Thread.currentThread().name}")
            val asyncList = ArrayList<Deferred<Any>>()
            for (i in 0..10000) {

                asyncList += async {
                    //assertTrue(Thread.currentThread().name.contains("main"))
                    println("-Thread--- start $i : ${Thread.currentThread().name}")
                    list.add(i)
                    assertTrue(list.isNotEmpty())
                }
            }
            asyncList.awaitAll()
        }

        println("-Main Thread--- running : ${Thread.currentThread().name}")

        verifyListIsSequential(list)
    }

    /**
     * This sometimes is async sometimes is not (for loops in 10K range)!
     *   GlobalScope Builder
     */
    @Test
    fun coroutinesWithGlobalScopeContext() {

        val list = Collections.synchronizedList(ArrayList<Int>())

        runBlocking {
            coroutineScope {
                println("-RunBlocking Thread--- start : ${Thread.currentThread().name}")
                for (i in 0..1000000) {
                    async {
                        println("-Thread--- start $i : ${Thread.currentThread().name}")
                        list.add(i)
                    }
                }
            }
        }

        println("-Main Thread--- running : ${Thread.currentThread().name}")

        verifyListIsNotSequential(list)
    }

    /**
     *  Back to being async.
     *    - If we don't specify a CoroutineContext for any coroutine, AND it isn't in enclosed in a CoroutineContext
     *     -> It will use EmptyCoroutineContext
     *     -> This may all execute on the main thread, or use separate threads depending
     */
    @Test
    fun coroutinesWithcDefaultScopeContextAwaitAll() {

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking(Dispatchers.Default) {

            println("-RunBlocking Thread--- start : ${Thread.currentThread().name}")
            (0..2000000)
                .map {i ->
                    async {
                        assertFalse( Thread.currentThread().name.contains("main"))
                        println("-Thread--- start $i : ${Thread.currentThread().name}")
                        if (i % 2 == 0) delay(10)
                        list.add(i)
                    }
                }
                .awaitAll()
        }

        log("-Main Thread--- running")

        verifyListIsNotSequential(list)
    }

    @Test
    fun lifeCycleTest() {
        val mainTime = measureTimeMillis {
            runBlocking {
                (0..100)
                    .forEach { runLifeCycle() }
            }
        }

        val defaultThreadsTime = measureTimeMillis {
            runBlocking(Dispatchers.Default) {
                (0..100)
                    .forEach { runLifeCycle() }
            }
        }

        println("------------------------------------------")
        println("time in only Main Thread: $mainTime ms")
        println("time in Default threads: $defaultThreadsTime ms")
        println(">> Savings of: ${mainTime - defaultThreadsTime} ms")
        println("------------------------------------------")
    }

    @Test
    fun lifeCycleTestSimple() {
        runBlocking {
            runLifeCycle()
        }
    }

    private suspend fun runLifeCycle() {

        coroutineScope {
            launch {
                delay(3)
                log("Task from first launch")
            }

            launch {
                delay(2)
                log("Task from second launch")
            }

            log("Task from coroutine scope")
        }

        log("Coroutine scope is over")
    }

    @Test
    fun coroutinesAsyncWithThreadPoolAndWithoutAwait() {

        println("-Main Thread--- Start : ${Thread.currentThread().name}")

        val threadPoolContext = newFixedThreadPoolContext(nThreads = 10, name = "Threads for testing")

            val list = Collections.synchronizedList(ArrayList<Int>())
            runBlocking(threadPoolContext) {

               log("-RunBlocking Thread--- start")
                for (i in 0..10000) {
                    async {
                        log("-Thread--- start $i")
                        list.add(i)
                    }
                }
            }

            log("-Main Thread--- running")

            verifyListIsNotSequential(list)
    }

    /**
     *  Demo converting a typical Java ThreadPoolExecutor into
     *  a Kotlin CoroutineDispatcher (a type of CoroutineContext)
     */
    @Test
    fun coroutinesAsyncWithJavaThreadPool() {

        println("-Main Thread--- Start : ${Thread.currentThread().name}")

        var javaThreadPool = ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, LinkedBlockingQueue())
        val threadPoolContext = javaThreadPool.asCoroutineDispatcher()

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking(threadPoolContext) {

            log("-RunBlocking Thread--- start")
            for (i in 0..10000) {
                async {
                    log("-Thread--- start $i")
                    list.add(i)
                }
            }
        }

        log("-Main Thread--- running")

        verifyListIsNotSequential(list)
    }

    @Test
    fun coroutineBlocking() {

            val list = Collections.synchronizedList(ArrayList<Int>())
            runBlocking {

                for (i in 0..10000) {
                    async {
                        println("-Thread--- start $i : ${Thread.currentThread().name}")
                        list.add(i)
                    }
                }

                println("-Main Thread--- running : ${Thread.currentThread().name}")
        }

        verifyListIsSequential(list)
    }

    @Test
    fun testAsyncAndLogExample() {

        val list = Collections.synchronizedList(ArrayList<Int>())

        runBlocking(Dispatchers.Default) {
            for (i in 0..2000) {
                asyncAndLog {

                    list.add(i)
                }
            }
        }
        verifyListIsNotSequential(list)
    }

    /**
     *  Note:  All of these "async" tasks actually occur on the same thread, "main"
     */
    @Test
    fun onlineTest() {
        val time = measureTimeMillis {
            runBlocking {

                val first = async { firstNumber() }
                val second = async { secondNumber() }
                val third = async { thirdNumber() }

                val result = first.await() + second.await() + third.await()
                println("result $result")
            }

        }
        println("time taken: $time") //prints 7 seconds
    }

    @Test
    fun onlineTestNoAsync() {
        val time = measureTimeMillis {
            runBlocking {

                launch {

                    suspendNoAsync()
                }

                log("Outside of launch")
            }

        }
        println("time taken: $time") //prints 7 seconds
    }

    suspend fun suspendNoAsync() {

        val first =  firstNumber()
        val second = secondNumber()
        val third =  thirdNumber()

        val result = first + second + third
        println("result $result")
    }

    suspend fun firstNumber(): Int {
        log("-firstNumber Thread--- start")

        delay(3_000) // 3 seconds delay
        log("first finished")
        return 5
    }
    suspend fun secondNumber(): Int {
        log("-secondNumber Thread--- start")

        delay(5_00) // 5 seconds delay
        log("second finished")
        return 8
    }
    suspend fun thirdNumber(): Int {
        log("-thirdNumber Thread--- start")

        delay(7_000) // 7 seconds delay
        log("third finished")
        return 10
    }

    private fun verifyListIsSequential(list: List<Int>) {

        assertTrue(list.isNotEmpty())

        val listChunked = list.chunked(2)
            .filter { it.size > 1 }
            .filter { it.first() > it.last() - 1}
            .also { assertTrue(it.isEmpty()) }

        log("List found where not sequential (Should be empty): $listChunked")
    }

    /**
     *  List not being sequential in index, proves it was processed asynchronously
     */
    private fun verifyListIsNotSequential(list: List<Int>) {

        assertTrue(list.isNotEmpty())

        val listChunked = list.chunked(2)
            .filter { it.size > 1 }
            .filter { it.first() > it.last() - 1}
            .also { assertTrue(it.isNotEmpty()) }

        log("List found, not sequential (should be not empty): $listChunked")
    }
}