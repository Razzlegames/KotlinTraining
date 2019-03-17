package kotlintraining.dto

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class SpaceShip(val speed: Int) {

    // Members with a Non-Null type (no "?") must be initialized in the constructor.
    // If we use for example Spring injection, or some init method, we will need to use
    //  "lateinit" to still keep as Non-Null
    lateinit var toBeSetLater: String

    var noLateInitNeeded: String

    // Do things we'd normally do in a Java constructor here
    // This happens at construction time
    init {
        noLateInitNeeded = "Not Set Later!"
    }

    fun setup() {
        toBeSetLater = "Set Later!"
    }
}

class LateInitLearningTest {

    @Test
    fun testInit() {

        val spaceShip = SpaceShip(speed = 100)
        // Runtime error if you do this before calling setup!
        //         assertEquals("Set Later!", spaceShip.toBeSetLater)
        spaceShip.setup()

        assertEquals("Set Later!", spaceShip.toBeSetLater)
        assertEquals("Not Set Later!", spaceShip.noLateInitNeeded)
    }
}