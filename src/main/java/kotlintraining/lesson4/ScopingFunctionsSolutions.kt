package kotlintraining.lesson4

import kotlintraining.dto.Animal

class Response(
    var convertedItemsCodes: List<Int> = emptyList(),
    var animalWeight: Int? = null,
    var processed: Boolean = false) {

    fun processSomethingWhenDone() {
        processed = true
    }
}

data class Request(val items: List<String>, val animal: Animal?)

class FakeService {

    fun processRequest(request: Request) : Response {

        // TODO Use a scoping function on Response() that (passes this and returns the same)
        // - map all request items to convertedItemsCodes in response
        // - map animalWeight to request.animal weight using a scoping function
        return Response()
            .apply {

                // You can now use convertedItemsCodes as if you're in a Response object
                convertedItemsCodes = request.items.map{ convertToItemCode(it)}

                // Map animal to weight as an Int.
                // Use a function that passes "it" returns "Last Line in Block"
                animalWeight = request.animal?.let { getAnimalWeightAsInt(it) }
            }

            // TODO
            // Now use a different scoping function to call doSomethingWithResponse
            .also {
                doSomethingWithResponse(it)
            }

            // We can also give the lambda argument "it", a better name, if needed
            //  (especially useful if there's many nested Scoped functions: "let{  also{ apply{} } }" etc)
            // TODO use a scoping function here with a named lambda parameter called "response" and
            //    call doSomethingWithResponse(response)
            //  NOTE: Not all scoping functions let you do this naming?
            //    - Which ones do?
            //    - What function choice is appropriate here?
            .also { response ->   doSomethingWithResponse(response)}

            // TODO
            // Call a scoping function here that will call Response.processSomethingWhenDone
            // Use a function that passes "this"
            .apply { processSomethingWhenDone() }
    }

    // Obviously theses helper functions are trivial and not needed they serve only to
    // Help demonstrate.  Imagine more complex business logic was fired off with each transform
    fun getAnimalWeightAsInt(animal: Animal):  Int? {

        return animal.weight.toInt()
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
        }// A this point any dot (.) chaining on the withBlock will be the return value of the last line of the block
            .plus(1)

        // What do you think the value of this print will be here?
        print(value)
    }
}