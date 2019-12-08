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

    @Test
    fun testCharacterInterfaceExample() {

        val monster = Monster(flying = FlyingBehavior(), character = CharacterBehavior(),
            swimming = SwimmingBehavior())

        val charList = listOf(monster, Hero())

        charList.forEach { it.jump() }
    }

}

class A: Flying {
    override fun fly() {
        print("fly A")
    }
}

class B: Flying {
    override fun fly() {
        print("fly B")
    }
}

fun callFly(b: Flying) {
    b.fly()
}