# Module 2: Lists and functional programming

This module moves from using Scala's built-in `List` to implementing a small immutable singly linked list yourself. It is the best place to practice how data structures, recursion, and higher-order functions fit together.

## Learning goals

- Understand a linked list as a head element and a tail list.
- Read generic types such as `RList[T]` and variance such as `+T`.
- Implement operations with recursion and accumulators.
- Explain `map`, `flatMap`, `filter`, sorting, and collection conversion.

## Source and entry point

- [`ListProblems.scala`](ListProblems.scala) contains `RList`, its empty and non-empty cases, sorting and utility operations, plus example runs.
- The main object is `ListProblems`; run it with `runMain com.rockthejvm.lists.ListProblems`.

## The data structure

The sealed abstract class `RList[+T]` describes the operations every list supports. `RNil` is the empty list. `::` is a case class holding one `head` element and the remaining `tail`. The `sealed` keyword means Scala can see all direct implementations in this source file, which makes the set of possible cases controlled.

The `+T` means the list is covariant: a list of a more specific type can be used where a list of a more general type is expected. `Nothing` is Scala's bottom type, so the empty `RNil` can serve as an empty list for any element type.

The method `::` prepends an element. It is written with a colon at the end, so Scala treats it as right-associative:

```scala
val list = 1 :: 2 :: 3 :: RNil
// equivalent grouping: 1 :: (2 :: (3 :: RNil))
```

The method `RList.from(iterable)` converts a standard iterable into this custom structure.

## Start with the small operations

Read `length`, `apply`, and `reverse` first. Each walks through the list until it reaches `RNil`. A recursive helper often carries:

- `remaining`: the part not processed yet;
- an index or count: progress through the input;
- an accumulator: the result built so far.

In `reverse`, the accumulator starts empty. Each head is prepended to it. When no elements remain, the accumulator is the reversed list. This pattern avoids repeatedly appending to the end of an immutable list.

## The functional “big three”

`map`, `flatMap`, and `filter` each accept a function as an argument:

```scala
val values = RList.from(List(1, 2, 3))
values.map(_ * 2) // a custom list containing 2, 4, 6
values.filter(_ % 2 == 1) // a custom list containing 1, 3
values.flatMap(n => n :: (n * 10) :: RNil) // a custom list containing 1, 10, 2, 20, 3, 30
```

The function types in the class are `T => S` for `map` and `filter`, and `T => RList[S]` for `flatMap`. `map` changes one element into one result. `filter` decides whether to keep each element. `flatMap` can produce zero, one, or several results per element and flattens them into one list. The implementations reverse their accumulators at the end because prepending is efficient.

## More interview patterns in this file

| Operation | Idea to trace | Complexity shown by the implementation |
|---|---|---|
| `rle` | Keep the current value and its run count; emit when the value changes | O(N) |
| `duplicateEach` | Track which input element is being repeated and how many copies were added | O(N × K) |
| `rotate` | Save the first `k` items, then attach them after the remainder | O(max(N, K)) |
| `sample` | Generate random indexes and look up those positions | O(N × K) in this linked-list representation |
| `insertionSort` | Insert each next item into an already sorted prefix | O(N²) |
| `mergeSort` | Split into runs of length one, then repeatedly merge sorted runs | O(N log N) |
| `quickSort` | Partition values around a pivot, then sort the partitions | Depends on partition balance; worst case O(N²) |

The sort methods accept an `Ordering[S]`. An `Ordering` tells the algorithm how to compare values without assuming that every type has a natural numeric order.

## How to study and extend it

1. Trace `reverse` on `[1, 2, 3]`, writing `remaining` and `accumulator` after each call.
2. Reimplement `map` on paper, then compare it to `mapTailrec`.
3. For each sort, identify the input split/partition, the base case, and the merge/combination step.
4. Try empty lists, a one-element list, repeated values, and an already sorted list.
5. Implement a new operation such as `take(k)` or `last`, first choosing its behavior for an empty list.

Interview topics to practice: linked-list traversal, immutable updates, generics, variance, higher-order functions, accumulator invariants, and time/space trade-offs.
