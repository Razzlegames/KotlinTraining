package kotlintraining.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTestFromJava {

    /**
     *  Java tried to pass null for weight which in Kotlin is a non nullable type
     *   - Throws Exception!
     */
    @Test(expected = IllegalArgumentException.class)
    public void failNonNullableParameterSetToNull() {

        // Will at least have compile warning since Kotlin compiles with @NotNull in JVM Byte code
        new Animal("Quack", null);
    }

    @Test
    public void testGetSet() {

        Animal animal = new Animal("Bark", 20);

        // All getters generated!
        animal.getWeight();
        animal.getSound();

        ///  No Setters generated since all variables are "val"
        // These are the same an final in Java! :)
        //animal.set

        Animal animal2 = new Animal("Bark", 20);

        // Yay it works!
        assertEquals(animal, animal2);
        // Same as above only for demo purposes
        assertTrue(animal.equals(animal2));

        assertEquals(animal.hashCode(), animal2.hashCode());

        /// It all works!!!!!!!
    }
}
