package kotlintraining.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 *  TODO
 *    - Create your own coroutine async wrapper that prints at start of thread, at end of thread execution
 *    - Print the time it took to execute the thread in the last print statement
 */
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
 *  TODO
 *   - Create your own coroutine wrapper that fires off many List<String>.adds(),
 *     Wait for all to finish, then add a final String to the end in the Main thread: List<String>.add("Finished")
 */



/** Used to simplify printing out thread we are in */
fun log(msg: String) = println("[${Thread.currentThread().name}]: $msg")