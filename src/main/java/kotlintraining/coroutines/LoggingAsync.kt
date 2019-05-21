package kotlintraining.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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
