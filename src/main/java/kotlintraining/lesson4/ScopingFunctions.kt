package kotlintraining.lesson4

import kotlintraining.dto.Animal

class Response(
    var convertedItemsCodes: List<Int> = emptyList(),
    var animalWeight: Int? = null) {

    fun processSomethingWhenDone() {

    }
}

data class RequestPojo(val items: List<String>, val animal: Animal?)

class FakeService {

    fun processRequest(request: RequestPojo) : Response {

        // Apply has puts Response in the "this" scope
        return Response()
            .apply {

                // You can now use convertedItemsCodes as if you're in a Response object
                convertedItemsCodes = request.items.map{ convertToItemCode(it)}

                // Let
                animalWeight = request.animal?.let { it.weight.toInt() }
            }
            // Now it == response, we could also
            .also {
                doSomethingWithResponse(it)
            }
            // We can also give the lambda argument it, a better name, if needed
            //  (especially useful if there's many nested Scoped functions: "let{  also{ apply{} } }" etc)
            .also { response ->   doSomethingWithResponse(response)}
    }

    fun convertToItemCode(item: String) : Int {
        // Could call some mapper or downstream service for a more realistic scenario
        return item.toInt()
    }

    fun doSomethingWithResponse(response: Response) {

        // With puts "response" in "this" scope of "with()",
        //   - so you can access all the memebers without specifying the response object
        val value = with(response) {
            print("codes: "+ convertedItemsCodes.joinToString { it.toString() })
                1
        }
            /// A this point any dot (.) chaining on the withBlock will be the return value of the last line of the block
            .plus(1)

        // What do you think the value of this print will be here?
        print(value)
    }
}