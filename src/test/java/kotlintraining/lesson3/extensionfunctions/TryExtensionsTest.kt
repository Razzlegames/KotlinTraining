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

    // UNCOMMENT below to test your exercise 3 results
//    /**
//     *  Exercise 1 test
//     */
//    @Test
//    fun countAllOddNumbers() {
//        val NUMBER_OF_ODDS = calculateOddsOldStyle(NUMBER_LIST)
//        print(NUMBER_LIST.countOdd())
//
//        assertEquals(NUMBER_OF_ODDS, NUMBER_LIST.countOdd())
//
//        val nullList: List<Int>? = null
//        assertEquals(0, nullList.countOdd())
//    }
//
//    /**
//     *  Exercise 2 test
//     *    Note that you can change NUMBER_LIST (adding or removing numbers)
//     *      and this should still pass
//     */
//    @Test
//    fun listToChunkedMapTest() {
//
//        val map = NUMBER_LIST.toChunkedMap()
//
//        for (i in 0 until NUMBER_LIST.size step 2) {
//                assertEquals(map.get(NUMBER_LIST[i]),
//                    if(i < NUMBER_LIST.size-1) NUMBER_LIST[i+1] else null)
//        }
//    }

    /**
     * This is old style just and not an extension function, just to demonstrate what is
     *   required for Exercise 1
     */
    private fun calculateOddsOldStyle(list: List<Int>): Int {

        var count = 0
        for(element in list) {
            if (element % 2 != 0) {
                count++
            }
        }

        return count
    }
}


