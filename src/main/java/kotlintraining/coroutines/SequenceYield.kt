package kotlintraining.coroutines

val getPowersOf2 = sequence {

    var value = 1

    while (true) {
        yield(value)
        value *= 2
    }
}