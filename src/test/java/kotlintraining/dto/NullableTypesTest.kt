package kotlintraining.dto

import org.junit.Test

class NullableTypesTest {

    @Test
    fun nullableTypeSetToNull() {

        var s: String? = "Stuff"
        // This is OK
        s = null
    }

    @Test
    fun nonNullableTypeSetToNull() {

        var s = "Stuff"
        // s = null // This will be a compiler error
    }

    @Test
    fun onlyDoIfNotNull() {

        var bear: Bear? = null

        // You cannot do this unless null is checked
        // Won't compile
        // bear.scratchBack()

        // This won't be called since bear == null
        // Null is checked with "?" operator
        bear?.scratchBack()

        bear = Bear()

        // This will be called now!
        bear?.scratchBack()

        // bear is smart cast to NonNullable type, so you can use without "?" here
        // and anywhere after "bear = Bear()"
        bear.scratchBack()
    }


}