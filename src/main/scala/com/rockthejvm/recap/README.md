# Module 1: Scala recap

Start here, even if you have programmed in another language. The exercises use Scala 2.13 and assume you can read the ideas in this guide. A Scala program is compiled to run on the Java Virtual Machine (JVM), but this repository does not use a framework such as Spring or Play.

## Learning goals

- Read Scala source files, packages, objects, classes, and methods.
- Recognize immutable values, types, expressions, and function types.
- Understand recursion, tail recursion, collection transformations, and pattern matching.
- Know what `extends App` does in these examples.

## Source

- [`ScalaRecap.scala`](ScalaRecap.scala) is an executable collection of small language examples.

Run it from the sbt prompt with `runMain com.rockthejvm.recap.ScalaRecap`. It is a reference example, not a lesson that prints a long result.

## Read the syntax

### Package, object, and values

```scala
package com.rockthejvm.recap

object ScalaRecap extends App {
  val answer: Int = 42
  var mood = "curious"
  mood = "ready"
}
```

`package` gives the file a namespace and determines its fully qualified name. An `object` is a single shared instance (a singleton), commonly used for utility functions and program entry points. `extends App` makes the object's body run as a small application. Use `val` for a name that cannot be reassigned; use `var` only when reassignment is genuinely needed. Scala can infer `Int` and `String` from the right-hand values, but writing types in public method signatures makes intent clearer.

### Expressions and functions

An `if` expression returns a value; it is not just a statement:

```scala
val label = if (answer > 0) "positive" else "not positive"
```

A block also evaluates to its final expression:

```scala
val result = {
  val base = 2
  base * 21
}
```

Define a method with `def`, parameters, and a result type:

```scala
def increment(number: Int): Int = number + 1
val incrementFunction: Int => Int = number => number + 1
```

`Int => Int` means “a function that accepts an `Int` and returns an `Int`.” The body after `=` is the return value, so there is no `return` keyword here. Functions are values and can be passed to other functions:

```scala
List(1, 2, 3).map(increment) // List(2, 3, 4)
```

`map` transforms each element and returns a new list; the original list is unchanged.

### Recursion and tail recursion

Recursion solves a problem by handling a smaller version of the same problem. Every recursive function needs a stopping case:

```scala
def factorial(n: Int): Int =
  if (n <= 1) 1
  else n * factorial(n - 1)
```

The stopping case is `n <= 1`. For `factorial(4)`, the calls are `4 * factorial(3)`, `4 * 3 * factorial(2)`, and so on. The pending multiplication means this version must return through the call stack.

Tail recursion moves pending work into an accumulator:

```scala
import scala.annotation.tailrec

@tailrec
def factorialTailrec(n: Int, accumulator: Int = 1): Int =
  if (n <= 1) accumulator
  else factorialTailrec(n - 1, n * accumulator)
```

The recursive call is the final operation. `@tailrec` asks the compiler to verify that property and optimize the call; a compilation error means the call is not in tail position. The accumulator records work already completed.

### Classes, case classes, and traits

```scala
class Vehicle
class ElectricCar extends Vehicle

case class Person(name: String, age: Int)

trait CanDrive {
  def drive: Unit
}
```

A `class` describes objects that can be created with `new`; `extends` establishes inheritance. A `case class` is designed for data and provides useful features such as construction without `new`, value-based equality, and pattern matching. A `trait` describes shared behavior or a type contract. Methods returning `Unit` perform an action and do not provide a useful result value.

### Collections and transformations

```scala
val values = List(1, 2, 3, 4)
val doubled = values.map(_ * 2)
val evens = values.filter(_ % 2 == 0)
val pairs = values.flatMap(n => List(n, n * 10))
```

The underscore is shorthand for a parameter used once: `_ * 2` means `number => number * 2`. `filter` keeps elements whose predicate returns `true`. `flatMap` maps each element to a collection and joins the resulting collections into one. `Set` keeps unique values; `Map` stores key/value pairs; `Queue` is useful when items are processed in arrival order.

### Pattern matching

```scala
val position = 2 match {
  case 1 => "first"
  case 2 => "second"
  case _ => "another position"
}
```

The first matching `case` is chosen. `_` is a wildcard for any remaining value. Pattern matching is also useful for unpacking case classes and tuples, for example `case (name, age) => ...`.

## How to study this module

Read `ScalaRecap.scala` top to bottom and predict what each expression means before reading its comment. Then add a method of your own for a concept already shown: a function that doubles an integer, a case class with one method, or a tail-recursive sum from `1` through `n`. Compile after each small change.

Before moving on, explain aloud: why `val` is the default, what a function type means, how an accumulator changes a recursive algorithm, and what `map` returns.
