package kotlintraining.coroutines

import kotlinx.coroutines.yield

/**
 * Will compute infinitely but can be suspended on each calculation,
 *   as to only run as many times as you specify
 *
 *   This uses SequenceScope.yield(T)
 */
val getPowersOf2 = sequence {

    var value = 1

    while (true) {

        yield(value)
        value *= 2
    }
}

/**
 *  TODO Create a simple `sequence`, like above, that computes the Fibonacci sequence (infinite)
 */
//val fibonacci = sequence {
//
//}
/**
 *  This uses  public suspend fun yield()
 *    It will yield() control to another executing coroutine in the same context
 */
suspend fun manyLogsYieldExample() {

    var i = 1
    log("called  $i")
    yield()
    i++
    log("called  $i")
    yield()
    log("We're done now! :) $i")
}