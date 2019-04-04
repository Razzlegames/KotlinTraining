package kotlintraining.lesson3.destructuring

import kotlintraining.dto.Animal
import org.junit.Test
import kotlin.test.assertEquals

class TryDestructuringTest {

    /**
     * Exercise 3:
     *    Todo Use destructuring to print out "petMap"
     */
    @Test
    fun printOutPetMap() {

        // TODO iterate over the map and get map as a (key, value)
        // Print out key and value

    }

    /**
     *  Exercise 4:
     *     Use Destructuring on Animal class into separate sound and weight.
     *       In practice,
     *       - I don't recommend doing this for all but simple use cases (e.g. iterating over map etc)
     *       - It's very easy to write confusing code and make naming/type/assigment mistakes
     *         -> Due to not being strict in destructured var naming
     *         -> This isn't an issue the other way around since we can use "named parameters" on construction
     */
    @Test
    fun destructureAnimalClass() {

        val animal = Animal(sound = "chirp", weight = 1)

//        assertEquals(animal.sound, sound)
//        assertEquals(animal.weight, weight)
    }

}