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
![Extension functions](https://cdn.programiz.com/sites/tutorial2program/files/kotlin-extension-function.jpg)
- Learn about extension functions
- Learn about basic destructuring

### Exercises

1. Create a basic extension function for counting odd numbers in a list, `countOdd()`
2. Create a more challenging extension function for creating a map from a `List`, `toChunkedMap()` 
3. Print out `petMap` using destructuring
4. Use destructing on `Animal` class

- Get all existing unit tests to pass

### Goals

- See how easy it is to extend existing Java library classes, even without needing the source code
- Utilize the `stream` like conversion skills from Lesson 2.
- Exposure to the different `for` loop and range syntax in Kotlin (seen in Unit test)
- Exposure to using `to` infix operator for generating `Pair`s while constructing a `Map`

## Lesson 4
- Learn about Scoping functions

Scoping functions are the bread and butter of `Kotlin`.  You'll see these everywhere peppered in code bases and for 
GOOD reason! They can really simplify and add flexibility to your code base.

- They provide an easy way to chain together calls and avoid having to repeat variable names. 
- Many times they avoid having to declare extra variables  
- Reduce lines of code to maintain
- Clarify intent
- Easier to understand with less code clutter
- Provide all the conveniences of chaining calls together
  - Formally called [`Monads`](https://en.wikipedia.org/wiki/Monad_(functional_programming)) 


### Functions
These functions are
- `run`
- `with`
- `T.run`
- `T.let`
- `T.also`
- `T.apply`

Note `T.` just means you call the extension function on an object, e.g., `T.also` used as:  `object.also{ process(it)}`

### Similar patterns

Scoping functions are handy ways to reduce code when processing objects. They are very similar and there is quite
a bit of overlap on uses.  As such there are many solutions to the same problem.

For example you could construct an object and pass to a processing function as:

```kotlin
class Level {

   ///... in  some method. Level contains Level.checkCollisions(Mario)
   // Mario class has a Int member called "coints" and public accessors for "var coins: Int"
   Mario(powerUp = PowerUp.MUSHROOM)
      .also { 
          it.coins = 10
          checkCollisions(it)
      }
```

Here are some other ways:
```kotlin
  Mario(powerUp = PowerUp.MUSHROOM)
      .apply{
          coins = 10
          checkCollisions(this)}
```

```kotlin
  Mario(powerUp = PowerUp.MUSHROOM)
      .let { 
          it.coins = 10
          checkCollisions(it)
      }
```

```kotlin
  Mario(powerUp = PowerUp.MUSHROOM)
      .run { 
          coins = 10
          checkCollisions(this)
      }
```

```kotlin
  with(Mario(powerUp = PowerUp.MUSHROOM)) { 
      coins = 10
      checkCollisions(this)
  }
```

#### Chart of behavior
As a reference this is what each is doing:

| Function  | Type        | Passed as | Returns   |
| :---      | :---        | :---      | :---       |
| also      | Extension   | it        | Same      |
| apply     | Extension   | this      | Same      |
| let       | Extension   | it        | Last Line in Block |
| run       | Extension   | this      | Last Line in Block |
| with      | Stand alone | this      | Last Line in Block |

### So what is the best practice in the last example?

Well that depends on your intent of course but let's just say:
 - We aren't returning `Mario` and 
 - just running that operation `checkCollisions`
 
Given these conditions I'd avoid `T.let`, `run`, `T.run` and `with`, 
- They all return the last line of the block
- We should probably only use these functions for `mapping` similar to `Java`'s `Optional.map`, `Stream.map` etc.
- This can avoid any confusion/mistakes while chaining calls together
  - Immutable lambda chaining, so to speak  


### When do we use `let`, `run` and `with`?

- `let` is great for mapping types
  - Especially in nullable map chaining (like `Java`'s `Optional.map`)
 
```kotlin
// Param is a nullable type Type?
someObject.param
  ?.let{ 
      mutate(it)
  }
  // Now output from `mutate(it)` is used
  ?.let {
      mutateAgain(it)
  }
  // Maybe you change some mutable state from `mutateAgain(it)` here
  ?.apply {
      state = "OK"
  }
```

Note the `state = "OK"` only executes if all the other operations succeed without returning `null` and 
if `param` isn't null.

- `run` seems to be a rare case where you want to scope the object as `this` and possibly map to a
 different value after processing.  
   ```kotlin
   someObject.run {
      someListInObject.forEach {print(it)}
      mapToSomething(someObject)
   } 
   ```
   
   - I find it clearer to do these in 2 atomic steps for readability e.g. 
     ```kotlin
     someObject.apply{ /*...*/}
         .let { /* map stuff */} 
     ```
- `with` is the same as `run` only the syntax is different
    ```kotlin
    with(someObject) {
        // set some internal state
        // Return something else
    }
    ```
    - I'd favor `apply` and not mutate state, or do as suggested with run and use `.apply{}.let{}` otherwise
    
## Lesson 5

See [lesson 5 instructions](src/main/java/kotlintraining/lesson5/README.md)

