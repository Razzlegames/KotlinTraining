package kotlintraining.lesson6

import org.junit.Test

class PropertyDelagatesTest {

    @Test
    fun testSystem() {
        val system = System()

        // Event handler kicks off here, on set()
        //  Notice the print
        system.value = 5

        // No event kicks off on get()
        val value = system.value

    }
}
