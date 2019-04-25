package kotlintraining.lesson6

import kotlin.test.Test


class MapPropertyDelegateTest {

    @Test
    fun mapTest() {

        val user = User(HashMap())
        user.name = "Bob"
        user.age = 10
    }
}