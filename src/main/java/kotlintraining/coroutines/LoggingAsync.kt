package kotlintraining.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 *  TODO
 *    - Create your own coroutine async wrapper that prints at start of thread, at end of thread execution
 *    - Print the time it took to execute the thread in the last print statement
 */
fun <T> asyncAndLog( block: suspend Any.() -> T) : Deferred<T> {

    return GlobalScope.async {
        block
            .also {
                println("-Thread--- start : ${Thread.currentThread().name}")
            }
            .let {
                it()
            }
            .also {
                println("-Thread--- end : ${Thread.currentThread().name}")
            }
    }
}


/**
 *  TODO
 *   - Create your own coroutine wrapper that fires off many List<String>.adds(),
 *     Wait for all to finish, then add a final String to the end in the Main thread: List<String>.add("Finished")
 */
