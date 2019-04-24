package kotlintraining.lesson6

import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterTest {


    @Test
    fun testDelegation() {


        val monster = Monster(flying = FlyingBehavior(), character = CharacterBehavior(),
            swimming = SwimmingBehavior())

        // Uses getName() from CharacterBehavior automatically!
        assertEquals("CharacterBehavior", monster.getName())

        // Cool! Monster gets all these behaviors delegated for free!
        monster.swim()
        monster.moveLeft()
        monster.moveRight()
        monster.jump()
        monster.fly()
    }

}