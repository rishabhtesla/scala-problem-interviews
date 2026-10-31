# Module 5: Binary trees

Trees are recursive data structures: each node contains a value and two smaller trees. That recursive definition makes tree traversal a natural place to practice recursion, pattern-like case distinctions, and bottom-up reasoning.

## Learning goals

- Read a recursive algebraic data type.
- Distinguish an empty tree from a node and understand leaf nodes.
- Trace depth-first and breadth-first traversals.
- Combine information from child subtrees to solve a problem.

## Source map

- [`Tree.scala`](Tree.scala) defines the generic immutable `Tree[T]`, the `End` empty case, the `Node` case, and example operations such as `size`, `mirror`, `collectNodes`, and `toList`.
- [`PathSum.scala`](PathSum.scala) checks for root-to-leaf sums and collects paths with a target sum.
- [`CameraSurveillance.scala`](CameraSurveillance.scala) uses a small state returned from each subtree to place the minimum cameras.

Run `runMain com.rockthejvm.trees.BinaryTreeProblems` for core tree operations.

## The tree type

`Tree[+T]` is a sealed abstract class. Its concrete cases are:

```scala
case object End extends Tree[Nothing]
case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]
```

`End` is the empty tree. A `Node` stores one value and references to its left and right subtrees. `Tree.apply` provides convenient constructors, so `Tree(5)` creates a leaf and `Tree()` creates an empty tree. `case class` gives `Node` value-based construction and matching behavior. The type parameter `T` lets the same tree structure store integers, strings, or other types.

## Two ways to traverse

The tree implementation includes recursive logic and iterative-style traversals built with immutable collections. Depth-first recursion is often the clearest way to ask a question about a node and its children. Breadth-first traversal uses a queue: process the current level, then enqueue children for the next level. `collectNodes(level)` is a useful trace for seeing this.

When a helper processes a node, ask:

- What is the base case for `End`?
- What should this node return if its children already know their answers?
- Does the caller need a result from the left child, the right child, or both?

## Trace: minimum cameras

`CameraSurveillance` returns a pair: number of cameras in a subtree and that subtree's coverage state. The state is one of `COVERED`, `NOT_COVERED`, or `CAMERA`.

1. Empty children are already covered.
2. If either child is not covered, put a camera at the current node.
3. If a child has a camera, the current node is covered.
4. Otherwise, the current node is not covered and may need its parent to place a camera.
5. After processing the root, add a camera if the root is still not covered.

This is a classic post-order dynamic-programming idea: solve children first, then combine their results at the parent.

## Exercises

1. Draw a tree with one node and with three nodes; write down `size`, `leafCount`, and `toList`.
2. Trace `collectNodes(2)` level by level.
3. Explain why `sameShapeAs` compares empty/non-empty structure and does not need to compare node values.
4. For `PathSum`, track the remaining target as you descend from root to leaf.
5. Add a method that returns the height of the tree and define what height means for `End`.

Interview practice: name the traversal order, define the base case, state what each recursive call returns, and derive complexity in terms of node count. A skewed tree can be much deeper than a balanced tree, so discuss recursion depth too.
