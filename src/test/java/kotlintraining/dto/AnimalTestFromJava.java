package kotlintraining.dto;

import org.junit.Test;

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
}
