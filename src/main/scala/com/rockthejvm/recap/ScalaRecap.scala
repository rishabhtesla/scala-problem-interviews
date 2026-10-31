package com.rockthejvm.recap

/**
  * This simple application serves as a playground for the Scala features we are going to use in this course.
  * I wrote it already so you don't waste your time typing what you already know.
  * For any concept that we use in this course which you're rusty/uncomfortable with, feel free to refer back to this app.
  *
  * For more in-depth exploration and exercises on these topics, check out the beginners course (Scala essentials).
  *
  * Daniel @ Rock the JVM
  */
object ScalaRecap extends App {

  /**
    * BASICS
    *
    * Scala is an expression-oriented language: most constructs produce a value.
    * This file is a reference. Try changing one example at a time and compile.
    */

  // `val` creates an immutable name. The compiler infers Int from 42.
  val purposeOfLife = 42

  // A val cannot be reassigned; uncommenting the next line is a compile error.
//   purposeOfLife = 43 // nope!

  // `var` allows reassignment. Prefer `val` unless changing state is needed.
  var mood = "sad"
  mood = "happy"

  // `if` is an expression, so its selected branch becomes the value of aCondition.
  val aCondition = if (purposeOfLife > 0) "happy" else "sad"

  // A block can define local values; its last expression is its result.
  val aCodeBlock = {
    val x = 2 // can define values inside
    x + 99 // the value of the block is the value of its last expression
  }

  // A method's parameter and result types make the contract explicit.
  def aFunction(param: Int) = param + 1

  // Recursion solves a smaller version of the same problem.
  // The base case stops; each other call moves n closer to that base case.
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  // In tail recursion, the recursive call is the final operation.
  // The accumulator carries completed work, allowing the compiler to optimize it.
  def factorialTailrec(n: Int, accumulator: Int): Int =
    if (n <= 1) accumulator
    else factorialTailrec(n - 1, n * accumulator)

  /**
    * OBJECT-ORIENTED PROGRAMMING
    */
  class Car // A class is a blueprint; use `new Car` to make an instance.
  class Supercar extends Car // `extends` makes Supercar a subtype of Car.

  // Parameters in the primary constructor become public values because of `val`.
  // A normal class is useful when data has behavior or identity beyond its fields.
  class Student(val name: String, val score: Int) {
    def hasPassed: Boolean = score >= 50
  }
  val student = new Student("Sam", 82)

  // A Supercar can be used anywhere a Car is expected (subtype polymorphism).
  val c: Car = new Supercar

  // A trait describes behavior a class agrees to provide.
  trait SelfDriving {
    def drive: Unit
  }

  // A class extends one parent class and can mix in multiple traits with `with`.
  class CarOfTheFuture extends Car with SelfDriving {
    override def drive: Unit = println("I'm driving by myself! Wooohooo! I hope I don't hit any pedestrians... Who's Asimov?!")
  }

  // A case class is convenient for data: Scala supplies construction, equality,
  // hashCode, and pattern-matching support based on its fields.
  case class Person(name: String, age: Int) {
    // The `s` prefix inserts the values of expressions written as $name, etc.
    def drives(car: Car) = println(s"$name is driving $car")
  }

  // Case-class constructor: no `new` is needed.
  val alice = Person("Alice", 23)
  val lamborghini = new Supercar
  // This infix method call is equivalent to alice.drives(lamborghini).
  alice drives lamborghini

  // `[T]` declares a type parameter: one class can work with many element types.
  class MySet[+T]

  // An object with the same name as a class is its companion object.
  // Objects are singletons and commonly hold constructors or utility methods.
  object MySet

  /**
    * FUNCTIONAL PROGRAMMING
    */

  // A function from Int to Int is an instance of Function1[Int, Int].
  val incrementer = new Function1[Int, Int] {
    override def apply(x: Int): Int = x + 1
  }
  val two = incrementer(1) // Calling the value uses its `apply` method.

  // Function literal syntax is shorter: `(x: Int) => ...`.
  val anotherIncrementer = (x: Int) => x + 1

  // `map` is higher-order: it receives a function and returns a NEW list.
  val incrementedList = List(1,2,3).map(incrementer)

  /**
    * COLLECTIONS
    */

  // List is immutable: operations produce a new collection rather than changing it.
  val aList = List(1,2,3,4)

  // Seq is an ordered collection interface; this factory creates an immutable sequence.
  val aSeq = Seq(1,2,3,4)

  // Arrays are mutable; this creates 2 rows and 3 columns of Int values.
  val anArray = Array.ofDim[Int](2, 3)

  // A Set contains each distinct value once.
  val aSet = Set(1,1,2,3)

  // Vector is an immutable indexed sequence.
  val aVector = Vector(1,2,3,4)

  // A tuple groups a fixed number of values, which may have different types.
  val aTuple: (Int, String) = (1, "I love tuples! Yay. Please bring more tuples.")

  // A Map associates keys with values; `->` creates each key/value pair.
  val aMap = Map(
    "Daniel" -> 789,
    "Jess" -> 555
  )

  /**
    * PATTERN MATCHING
    */

  // `match` checks cases from top to bottom; `_` is the fallback for any value.
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    // Match the case-class shape, bind the name to n, and ignore age with `_`.
    case Person(n, _) => s"Hi, my name is $n"
  }

  // all the patterns

  /**
    * IMPLICITS
    * Implicits are a beast. We are only going to use implicits for the below use cases.
    * Implicits are hard and OPTIONAL in this course, just for the sake of Scala's expressiveness.
    *
    * The advanced Scala course explains everything about implicits.
    */

  // An implicit value can be supplied by the compiler when a method needs Ordering[Int].
  implicit val descendingIntOrdering: Ordering[Int] = Ordering.fromLessThan((a, b) => b > a) // comparison object
  val theObviousTruth = descendingIntOrdering.lteq(99, 100)

  // `sorted` searches for a compatible Ordering[Int] and uses the one above.
  val descendingList = List(1,2,3,4).sorted

  // An implicit class can add a method to an existing type via a wrapper.
  implicit class MyRichInt(number: Int) {
    def isPositive = number > 0
  }

  val oneSign = 1.isPositive
  // Equivalent in idea to MyRichInt(1).isPositive; the compiler inserts the wrapper.
}
