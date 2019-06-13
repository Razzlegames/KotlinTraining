package kotlintraining.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals

class SequenceYieldTest {

    @Test
    fun testPowerOf2() {

        val powerOf2 = 5
        getPowersOf2.take(powerOf2+1)
            .last()
            .also { log("Value is: $it")
                assertEquals(2.0.pow(powerOf2).toInt(), it)
            }

        log(getPowersOf2.take(powerOf2+1).toList().toString())

    }

    @Test
    fun manyLogsYieldExampleTest() {

        runBlocking {
            launch { manyLogsYieldExample() }
            launch { manyLogsYieldExample() }
        }
    }

    // TODO Uncomment and make this pass
//    @Test
//    fun testFibonacci() {
//        fibonacci.take(6)
//            .toList()
//            .also {
//                log(it.toString())
//                assertEquals(listOf(0, 1, 1, 2, 3, 5), it)
//            }
//    }
}