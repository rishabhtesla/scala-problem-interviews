# Scala from zero: problem solving and interview practice

This repository is a hands-on course for learning Scala by reading, running, tracing, and extending the code in `src/main/scala/com/rockthejvm`. It starts with the language basics and builds toward interview-style problems in lists, numbers, strings, trees, and graphs.

The project uses **Scala 2.13.5** and **sbt 1.3.3**. It is a plain Scala/JVM project: there is no web framework or application framework to learn first. The important tools here are the Scala language, its standard library, and sbt (the build tool).

## If you are completely new

You do not need to know Scala before starting. Basic comfort with using a terminal and editing files is helpful, but programming experience is not required.

1. Install **JDK 17** and [sbt](https://www.scala-sbt.org/), or install IntelliJ IDEA with its Scala plugin. JDK 17 is a known-compatible choice for this repository's older sbt version.
2. Clone this repository and open its root directory as an sbt project.
3. Read [`recap/README.md`](src/main/scala/com/rockthejvm/recap/README.md) first. It introduces the syntax used throughout the exercises.
4. Follow the module order below. For each problem, understand the input and output, trace a small example by hand, explain the state the algorithm carries, then run and modify the code.
5. Keep an interview notebook: write the solution idea, edge cases, time complexity, space complexity, and one alternative approach in your own words.

## Course roadmap

| Order | Module | What you will learn |
|---|---|---|
| 1 | [Scala recap](src/main/scala/com/rockthejvm/recap/README.md) | Values, types, functions, classes, objects, recursion, collections, and pattern matching |
| 2 | [Lists](src/main/scala/com/rockthejvm/lists/README.md) | Build an immutable list; learn `map`, `flatMap`, `filter`, accumulators, and sorting |
| 3 | [Numbers](src/main/scala/com/rockthejvm/numbers/README.md) | Loops as recursion, digit processing, overflow, counting, and numeric reasoning |
| 4 | [Strings](src/main/scala/com/rockthejvm/strings/README.md) | String traversal, frequency maps, stacks, greedy choices, and simulation |
| 5 | [Trees](src/main/scala/com/rockthejvm/trees/README.md) | Algebraic data types, recursive structures, tree traversal, and dynamic programming |
| 6 | [Graphs](src/main/scala/com/rockthejvm/graphs/README.md) | Adjacency maps, reachability, cycle detection, topological order, and shortest paths |
| 7 | [Various problems](src/main/scala/com/rockthejvm/various/README.md) | Expression evaluation, backtracking, and recursive drawing |
| 8 | [Playground](src/main/scala/com/rockthejvm/playground/README.md) | A safe place to try your own small Scala experiments |

Each module README explains the relevant source files, Scala syntax, problem-solving approach, complexity, and exercises. Read the recap before the algorithm modules: it explains the Scala idioms that the later code relies on.

## Run the code

From the repository root, start sbt:

```text
sbt
```

At the sbt prompt, compile the project:

```text
compile
```

Run an example application with `runMain` and its fully qualified class name:

```text
runMain com.rockthejvm.recap.ScalaRecap
runMain com.rockthejvm.lists.ListProblems
runMain com.rockthejvm.numbers.NumberProblems
runMain com.rockthejvm.strings.ParenthesisProblems
runMain com.rockthejvm.trees.BinaryTreeProblems
runMain com.rockthejvm.graphs.GraphColoring
runMain com.rockthejvm.various.NQueens
runMain com.rockthejvm.playground.Playground
```

You can also run a one-off command without entering the sbt prompt:

```bash
sbt "runMain com.rockthejvm.lists.ListProblems"
```

Most exercises include a `main` method or extend `App`; the module guides identify which class to run. The project currently demonstrates examples by printing results rather than using a separate test framework.

## A repeatable interview-practice method

For each exercise:

1. **Restate the contract.** Identify input, expected output, assumptions, and invalid or boundary inputs.
2. **Work a tiny example.** Show the input, each state change, and the final answer.
3. **Choose a representation.** For example, a set for visited graph nodes or a map for character counts.
4. **Explain the invariant.** Say what remains true after each iteration or recursive call.
5. **Implement and test.** Check empty, smallest, duplicate, negative, and large inputs where they apply.
6. **Analyze complexity.** Count how many times each element is processed and what extra data is stored.
7. **Compare approaches.** Explain why the chosen representation is suitable and when another approach would be better.

## Scala course source and history

This repository began as the code companion for Rock the JVM's [Scala & Functional Programming Practice course](https://rockthejvm.com/course/scala-functional-programming-practice). The READMEs added here are a self-study guide for the code currently in this checkout; they do not replace the video course.

The original repository also includes lecture tags such as `start`, `lists-reverse`, and `graphs-intro`. Those tags identify historical lesson snapshots. The current checked-out source is the one described by the module guides.
