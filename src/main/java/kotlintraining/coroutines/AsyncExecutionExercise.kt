package kotlintraining.coroutines

import kotlinx.coroutines.delay

class AsyncExecutionExercise {

    /**
     *  TODO run calculation1, calculationDontCare and importantCalculation in coroutines
     *
     *   - Fire and forget about calculationDontCare, consider this a low priority best effort call
     *   - Call and print the value of calculation1 and importantCalculation, by getting back the results from the Coroutine
     */
    fun callAll() {

    }

    suspend fun calculation1(): Int {
        log("-firstNumber Thread--- start")

        delay(3_000) // 3 seconds delay
        log("first finished")
        return 5
    }

    suspend fun calculationDontCare(): Int {
        log("-secondNumber Thread--- start")

        delay(5_00) // 5 seconds delay
        log("second finished")
        return 8
    }

    suspend fun importantCalculation(): Int {
        log("-thirdNumber Thread--- start")

        delay(7_000) // 7 seconds delay
        log("third finished")
        return 10
    }
}
