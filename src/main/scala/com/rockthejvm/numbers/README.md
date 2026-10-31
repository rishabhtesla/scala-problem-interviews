# Module 3: Number problems

These exercises turn number puzzles into explicit state changes. They are useful for practicing loops as recursion, integer arithmetic, boundary conditions, and choosing a representation that avoids unnecessary work.

## Learning goals

- Process digits with `%` (remainder) and `/` (integer division).
- Use an accumulator for repeated calculations.
- Guard against integer overflow and special boundary values.
- Compare straightforward and optimized approaches.

## Source map

| File | Problem and concepts |
|---|---|
| [`NumberProblems.scala`](NumberProblems.scala) | Prime checking and prime factorization; `NumberOps` adds extension-style methods through an implicit class |
| [`ReverseInteger.scala`](ReverseInteger.scala) | Reverse digits while handling signed `Int` overflow |
| [`ParseInteger.scala`](ParseInteger.scala) | Parse a signed decimal prefix, including whitespace and overflow handling |
| [`RecurringDecimals.scala`](RecurringDecimals.scala) | Long division and detecting repeating remainders |
| [`UglyNumber.scala`](UglyNumber.scala) | Repeated factor removal and generating a sequence with queues |
| [`Duplicates.scala`](Duplicates.scala) | Find the single unpaired number; compare counting, sets, and XOR |
| [`LargestNumber.scala`](LargestNumber.scala) | Define a custom ordering to arrange numbers as the largest concatenation |
| [`ApproximatePi.scala`](ApproximatePi.scala) | Monte Carlo estimation using random points and collection operations |

Run `runMain com.rockthejvm.numbers.NumberProblems` for prime factorization examples. Files with `main` methods can be run by their object name; `ApproximatePi` and `RecurringDecimals` extend `App`.

## Scala techniques to notice

- **Extension-style method:** `NumberOps.RRichInt` is an `implicit class`; after `import NumberOps._`, an `Int` can be written as `15.isPrime`. The method is defined on a wrapper around the integer.
- **Tail recursion:** `@tailrec` marks helper methods whose recursive call is the final operation. For factorization, the helper carries the remaining number, current divisor, and factors found so far.
- **Collection folds:** `foldLeft(initial)(step)` processes a collection from left to right while carrying a result. In `Duplicates`, XOR folds every number into one `Int`.
- **Custom ordering:** `Ordering.fromLessThan` lets `LargestNumber` compare `a` and `b` by comparing the concatenated strings `ab` and `ba`.
- **Tuples and pattern matching:** `case (a, b, t) => ...` unpacks tuple fields inside a collection transformation.

## Trace: reverse an integer

For `reverseInteger(504)`, the helper repeatedly computes the last digit with `% 10` and removes it with `/ 10`:

| Remaining | Digit | Accumulator after step |
|---:|---:|---:|
| 504 | 4 | 4 |
| 50 | 0 | 40 |
| 5 | 5 | 405 |
| 0 | — | result is 405 |

The key interview question is not only how to reverse digits, but how to detect overflow before returning a wrapped `Int`. Trace `Int.MaxValue`, `Int.MinValue`, zero, and negative inputs.

## Exercises

1. Explain why checking divisors only up to the square root is enough for primality.
2. Hand-trace the XOR solution when each paired number cancels itself.
3. Write down the remainder after each long-division step for `1 / 6`; explain how seeing a remainder again identifies the start of a cycle.
4. Modify the prime check to avoid recomputing the square root each recursive step.
5. State the valid-input assumptions for each method and add tests for the boundary cases before changing behavior.

Interview practice: explain the invariant carried by each recursive helper, compare memory costs, and be precise about numeric overflow and input assumptions.
