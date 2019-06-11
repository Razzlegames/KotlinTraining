package kotlintraining.coroutines

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

    }
}