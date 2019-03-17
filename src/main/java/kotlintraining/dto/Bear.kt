package kotlintraining.dto

class Bear(val back: Back = Back()) {

    fun scratchBack() {
    }

    class Back() {
        fun scratch() {
            print("Back scratched")
        }
    }
}