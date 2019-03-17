package kotlintraining.dto

class Bear(val back: Back = Back()) {

    // Example of using a custom setter
    //   Ignore the bad type choice, of weight as a String ;)
    var weight: String = "300 lbs"
        // Custom setter
        set(value){
            field = "$value lbs"
        }

    fun scratchBack() {
        back.scratch()
    }

    class Back {
        fun scratch() {
            print("Back scratched")
        }
    }
}