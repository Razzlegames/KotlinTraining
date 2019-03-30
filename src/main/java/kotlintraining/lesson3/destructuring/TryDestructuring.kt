package kotlintraining.lesson3.destructuring


val petMap = mapOf("Bob" to "Frog", "Billy" to "Dog", "Mort" to "Rock")
data class TimeOff(val name: String, val daysOff: Int)

fun destructuringExample() {

    val timeOff = TimeOff(name = "Guy", daysOff = 4)

    val (name, daysOff) = timeOff

    print("$name has $daysOff days Off")
}

