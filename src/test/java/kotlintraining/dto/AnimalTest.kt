package kotlintraining.dto

import org.junit.Assert.assertNull
import kotlin.test.Test

import kotlin.test.assertEquals

class AnimalTest {

    @Test
    fun testConstructionWithNullDefaultSound() {

        val animal = Animal(weight = 4)
        assertNull(animal.sound)
    }

    @Test
    fun testConstructionWithSoundSet() {

        val animal = Animal(weight = 4, sound = "Bark")

        assertEquals("Bark", animal.sound)
        assertEquals(4, animal.weight)

        // Uncomment below, cannot set val after construction, is effectively "final"
        //animal.sound = "Yell"
    }
}