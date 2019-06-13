package kotlintraining.coroutines

import kotlinx.coroutines.*


fun <T> CoroutineScope.asyncAndLog( block: suspend Any.() -> T) : Deferred<T> {

    return async {
        block
            .also {
                log("-Thread--- start :")
            }
            .let {
                it()
            }
            .also {
                log("-Thread--- end")
            }
    }
}

/**
 *  TODO (similar to above)
 *    - Create your own coroutine async wrapper that prints at start of thread, at end of thread execution
 *    - Print the time it took to execute the thread in the last print statement
 *    - Hint look at README for easy solution to printing time
 */


/** Used to simplify printing out thread we are in */
fun log(msg: String) = println("[${Thread.currentThread().name}]: $msg")