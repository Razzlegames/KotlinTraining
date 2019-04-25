package kotlintraining.lesson6

import kotlin.properties.Delegates

class Event(val name: String) {
    fun process() {
        println("Event processed")
    }
}

/**
 *  Example of observer pattern, simplified
 *
 *  Every time value changes, ProcessEvent() will be called!
 */
class System {
    var value: Int by Delegates.observable(0) {
        prop, old, new ->
        processEvent(Event(name = "value changed to: $new"))
    }

    private fun processEvent(event: Event) {

        event.process()
        println(event.name)
    }
}