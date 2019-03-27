package kotlintraining.lesson3.extensionfunctions


/**
 * This is an example of an extension function
 *  String? is the type it's "extending", it takes a string, if the string it's extending
 *    (called a "dispatch receiver") is null or empty, it returns @p default
 */
fun String?.defaultIfEmpty(default: String) = if (this.isNullOrEmpty()) default else this

/**
 *  If you prefer you can write out the long way, notice you have to declare the return type if you do
 */
fun String?.defaultIfEmpty2(default: String) : String {

    return if (this.isNullOrEmpty()) default else this
}

/**
 *  TODO create an extension function that counts returns odd integers in a List
 *    If the list is null return 0
 */
val NUMBER_LIST = listOf(5, 3, 1, 9, 6, 2, 12324, 333, 5, 99)

/**
 *  TODO create an extension function that creates a map from NUMBER_LIST where the first value is the key,
 *   second value is the value, for example {5 maps to 3}, {1 maps to 9}, {6 maps to 2}
 *   You can assume that if there's odd elements that the last element maps to null
 */


