package kotlintraining.dto

/**
 *   This is an example of a Nullable type String?.  In this example, sound is optional
 *     in the constructor and it can be set to null,
 *
 *     - Any Type with a ? at the end is Nullable,
 *     - Any Type without cannot be null
 *     - WARNING: All types that are passed from Java are potentially nullable. Keep this in mind
 */
data class Animal(val sound: String? = null, val weight: Number)
