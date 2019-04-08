package kotlintraining.lesson4

import kotlintraining.dto.Animal
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ScopingFunctionsTest {

    lateinit var fakeService: FakeService

    @Before
    fun init() {
        fakeService = FakeService()
    }

    /**
     *  TODO get this to pass by adding scoping functions to ScopingFunctions.kt
     */
    @Test
    fun processRequestTest() {

        fakeService.processRequest(
            Request(items =  listOf("0", "1", "2"),animal = Animal(weight = 10)))
            .apply {

                assertEquals(listOf(0, 1, 2), convertedItemsCodes)
                assertEquals(10, animalWeight)
                assertTrue {processed  }
            }
    }
}