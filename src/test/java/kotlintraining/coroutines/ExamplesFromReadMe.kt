package kotlintraining.coroutines

import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis
import kotlin.test.Test

class ExamplesFromReadMe {

    @Test
    fun asyncExample() {
        runBlocking {

            val first = async { firstNumber() }
            val second = async { secondNumber() }
            val third = async { thirdNumber() }

            val result = first.await() + second.await() + third.await()
            println("result $result")

        } //  Blocks until all tasks complete

        println("All Tasks Complete")
    }

    @Test
    fun defaultContextTest() {
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
}