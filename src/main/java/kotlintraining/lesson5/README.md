# Lesson 5 - Spring and Unit Tests

## Goal

* Observe common patterns for Spring DI in Kotlin
* Identify common issues when migrating to Kotlin
* Learn how to deal with common mocking issues in Kotlin

## Exercise

Convert the existing service and tests to Kotlin (converting the `kotlintraining.lesson5.init` package is optional but extra credit :) ).

Given the Spring Boot service, add a POST endpoint that stores a value for a given ID:

```kotlin
@POST
@Path("{id}")
fun getValue(@PathParam("id") id: Int, value: Value): Value
```

Extend `IntegerService` to use a data access layer object.  This should be a Spring managed class.
The internal storage mechanism is up to you.

There should be unit tests for all of this.  Experiment with writing tests with MockK and Mockito.