package kotlintraining.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.Collections
import kotlin.test.assertTrue

class GeneralKotlinCoroutineExamples {

    @Test
    fun coroutinesAsync() {

        runBlocking {

            val list = Collections.synchronizedList(ArrayList<Int>())
            val result = GlobalScope.async {

                    for (i in 0..10000) {
                        async {
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

    /**
     *  This is unexpected but this will run all async{} blocks on the same main thread
     */
    @Test
    fun coroutinesWithDefaultThreadPoolContext() {

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking {

            println("-RunBlocking Thread--- start : ${Thread.currentThread().name}")
            for (i in 0..10000) {
                async {
                    println("-Thread--- start $i : ${Thread.currentThread().name}")
                    list.add(i)
                }
            }
        }

        println("-Main Thread--- running : ${Thread.currentThread().name}")

        verifyListIsSequential(list)
    }

    /**
     *  Back to being async.  We just need to use `GlobalScope.async`, if not specifying a ThreadPoolContext to `runBlocking`
     */
    @Test
    fun coroutinesWithGlobalScopeContext() {

        val list = Collections.synchronizedList(ArrayList<Int>())
        runBlocking {

            println("-RunBlocking Thread--- start : ${Thread.currentThread().name}")
            for (i in 0..10000) {
                GlobalScope.async {
                    println("-Thread--- start $i : ${Thread.currentThread().name}")
                    list.add(i)
                }
            }
        }

        println("-Main Thread--- running : ${Thread.currentThread().name}")

        verifyListIsNotSequential(list)
    }

    @Test
    fun coroutinesAsyncWithThreadPoolAndWithoutAwait() {

        println("-Main Thread--- Start : ${Thread.currentThread().name}")

        val threadPoolContext = newFixedThreadPoolContext(nThreads = 10, name = "Threads for testing")

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

            println("-Main Thread--- running : ${Thread.currentThread().name}")

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

    private fun verifyListIsSequential(list: List<Int>) {
        val listChunked = list.chunked(2)
            .filter { it.size > 1 }
            .filter { it.first() > it.last() - 1}
            .also { assertTrue(it.isEmpty()) }

        print("List found: $listChunked")
    }

    /**
     *  List not being sequential in index, proves it was processed asynchronously
     */
    private fun verifyListIsNotSequential(list: List<Int>) {
        val listChunked = list.chunked(2)
            .filter { it.size > 1 }
            .filter { it.first() > it.last() - 1}
            .also { assertTrue(it.isNotEmpty()) }

        print("List found: $listChunked")
    }
}