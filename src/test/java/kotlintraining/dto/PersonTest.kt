package kotlintraining.dto

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 *  These are just learning tests.  Data classes don't need unit tests typically, since all code is generated in most cases.
 */
class PersonTest {

    @Test
    fun testConstructor() {

        val person = Person(name = "Guy", title = "Mr")
        val person2 = Person(name = "Guy", title = "Mr")

        // Uncomment to see how this fails if we don't specify "title" for Person()
        //val personCompileFail = Person(name = "Guy")

        assertEquals(person.name, "Guy")
        assertEquals(person.title, "Mr")

        assertEquals(person, person2)

        // == calls .equals() in Kotlin!
        assertTrue(person == person2)
        // === is typical object id compare, like Java "==
        assertFalse(person === person2)
    }
}