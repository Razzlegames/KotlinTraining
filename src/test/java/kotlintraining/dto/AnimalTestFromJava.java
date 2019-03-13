package kotlintraining.dto;

import org.junit.Test;

public class AnimalTestFromJava {

    /**
     *  Java tried to pass null for weight which in Kotlin is a non nullable type
     *   - Throws Exception!
     */
    @Test(expected = IllegalArgumentException.class)
    public void failNonNullableParameterSetToNull() {

        new Animal("Quack", null);
    }
}
