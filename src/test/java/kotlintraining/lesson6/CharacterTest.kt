package kotlintraining.lesson6

import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterTest {


    @Test
    fun testDelegation() {

        val characterDto = CharacterDto(CharacterImpl())
        characterDto.jump()

        // Uses getName() from CharacterImpl automatically!
        assertEquals("CharacterImpl", characterDto.getName())
    }

}