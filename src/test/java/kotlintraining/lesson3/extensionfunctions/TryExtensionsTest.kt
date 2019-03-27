package kotlintraining.lesson3.extensionfunctions

import org.junit.Assert.assertEquals
import org.junit.Test

class TryExtensionsTest {

    @Test
    fun defaultIfEmptyTest_Empty() {

        val default = "default"
        assertEquals(default, "".defaultIfEmpty(default))
    }

    @Test
    fun defaultIfEmptyTest_null() {

        val someNullString : String? = null
        val default = "default"
        assertEquals(default, someNullString.defaultIfEmpty(default))
    }

    @Test
    fun defaultIfEmptyTest_notEmpty() {

        val setString = "I like Turtles"
        val default = "default"
        assertEquals(setString, setString.defaultIfEmpty(default))
    }

    @Test
    fun countAllOddNumbers() {
        print(NUMBER_LIST.countOdd())

        assertEquals(7, NUMBER_LIST.countOdd())
    }
}

fun List<Int>?.countOdd() = this?.filter { it % 2 != 0 }?.count() ?: 0

