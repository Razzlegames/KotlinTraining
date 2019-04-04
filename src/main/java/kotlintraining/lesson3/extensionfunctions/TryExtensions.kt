package kotlintraining.lesson3.extensionfunctions


/**
 * This is an example of an extension function
 *  String? is the type it's "extending", called a receiver.
 *
 *  It takes a string, if the string it's extending ("receiver")
 *     is null or empty, it returns @p default
 */
fun String?.defaultIfEmpty(default: String) = if (this.isNullOrEmpty()) default else this

/**
 *  If you prefer you can write out the long way, notice you have to declare the return type if you do
 */
fun String?.defaultIfEmpty2(default: String) : String {

    return if (this.isNullOrEmpty()) default else this
}

/**
 *  TODO Exercise 1
 *
 *  Create an extension function that counts returns odd integers in a List
 *    If the list is null return 0.
 *
 *    Bonus, Use List<T>.filter and other extension functions to build your solution!
 *
 *    WARNING Don't add any duplicate elements to this, so next exercise will pass
 */
val NUMBER_LIST = listOf(530, 3, 1, 9, 6, 2, 12324, 333, 5, 99, 1002)

/**
 *  TODO Exercise 2
 *
 *  Create an extension function called "toChunkedMap()"
 *
 *  that creates a map from NUMBER_LIST where the first value is the key,
 *   second value is the value, for example {5 maps to 3}, {1 maps to 9}, {6 maps to 2}
 *   You can assume that if there's odd elements that the last element maps to null
 *
 * Hints:
 *   Receiver Type to extend: List<T>
 *   Returns: Map<T, T?>
 *
 *   Extension function List<T>.chunked(int) can be used
 *
 */
//Another way to do using associateBy()
fun List<Int>?.toChunkedMap() = this.orEmpty().chunked(2).associateBy ( {it.get(0)}, {if (it.size>1) it.get(1) else null} ).toMap()


