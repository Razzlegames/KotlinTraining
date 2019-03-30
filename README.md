# KotlinTraining
A bunch of Kotlin Examples for learning

Well well, fancy seeing you here.  You ready to learn what `Kotlin` is all about?

Here's a basic study guide subject to change:

## Lesson 1

- Learn about Kotlin `data class`es
- What makes these unique compared to Java?
- What can we use when calling Kotlin from Java?

### Exercise

- Run the existing unit tests for `PersonTest.kt`
- Implement your own `data class`
  - Call `toString` on your `data class` and print results
- Implement your own Kotlin Unit test
- Implement your own Java Unit test that calls Kotlin
- Construct your class using Named constructor arguments
- Create a function with Nullable type as a parameter in a test
  - Call a method on this object with a nullable type
  - What did you have to do?
  - Try calling the function with null
  - Try calling with an actual object
  - What happens? (Set break points or `print()` to see)
 

### Goals

- Understand what Kotlin data classes do automatically for us
- Understand how to call Kotlin data classes from Java (don’t worry, just works)
- Understand named constructor parameters and what that means for Good Ol’ Builder patterns
- Understand what Non-Nullable and Nullable types are

## Lesson 2

- Learn additional basic Kotlin control structures and how they differ from Java
- Examples of converting Optionals to type-safe accessors and null coalescing operators
- Examples of converting streams to Kotlin types

### Exercises

- Convert IfElseConversion class to Kotlin.  Run tests.
- Convert WhenConversion class to Kotlin.  Run tests and implement missing methods.
- Convert OptionalConversion class to Kotlin.  Run tests and implement missing methods.
- Convert StreamConversion class to Kotlin.  Run tests and implement missing methods.

### Goals

- Beginning understanding of "when"
- Understand how if/else differs from Java
- Understand how to convert Optionals to nullable types in Kotlin
- Understand how to convert Streams to Kotlin collections and when to use `asSequence()`

## Lesson 3

- Learn about extension functions

### Exercises

- Create a basic extension function for counting odd numbers in a list, `countOdd()`
- Create a more challenging extension function for creating a map from a `List`, `toChunkedMap()` 
- Get all existing unit tests to pass

### Goals

- See how easy it is to extend existing Java library classes, even without needing the source code
- Utilize the `stream` like conversion skills from Lesson 2.
- Exposure to the different `for` loop and range syntax in Kotlin (seen in Unit test)
