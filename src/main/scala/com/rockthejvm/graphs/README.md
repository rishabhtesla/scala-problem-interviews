# Module 6: Graphs

Graphs model connections: roads, dependencies, friendships, or network links. This module uses immutable maps and sets to represent graph structure and then builds common interview operations on top.

## Learning goals

- Represent a graph with an adjacency map.
- Understand in-degree, out-degree, paths, cycles, and visited nodes.
- Recognize depth-first search (DFS), breadth-first search (BFS), and topological sorting.
- Trace Dijkstra's shortest-path algorithm and its assumptions.

## Source map

- [`GraphProblems.scala`](GraphProblems.scala) defines `Graph[T] = Map[T, Set[T]]`, degrees, paths, cycles, and undirected conversion.
- [`GraphColoring.scala`](GraphColoring.scala) colors an undirected view of a graph using a greedy strategy.
- [`TownJudge.scala`](TownJudge.scala) derives the judge from in-degree and out-degree counts.
- [`UniCourses.scala`](UniCourses.scala) detects prerequisite cycles and computes a possible course order.
- [`NetworkDelays.scala`](NetworkDelays.scala) computes shortest signal delays using Dijkstra's algorithm.

Run `runMain com.rockthejvm.graphs.GraphColoring` for a graph-coloring example.

## Representation and Scala collections

`type Graph[T] = Map[T, Set[T]]` is a type alias: each node maps to the set of nodes it points to. The generic `T` means a graph can use any suitable node type. `Set` avoids duplicate neighbors; `Map` provides efficient lookup by node.

Common operations in these files:

- `graph.getOrElse(node, Set())` supplies an empty neighbor set when no key exists.
- `visited + node` returns a new set with the node added.
- `foldLeft(initial) { (state, item) => ... }` builds a result by processing items.
- `forall` and `exists` express “all” and “at least one” conditions.
- `@tailrec` helpers carry pending nodes and visited state without making non-tail recursive calls.

## Recognize the algorithms

**Reachability:** Start at a node, inspect its neighbors, and remember visited nodes so cycles do not cause repeated work. A path exists if the destination is reached.

**Course scheduling:** A directed cycle means some prerequisites depend on themselves indirectly. Depth-first traversal tracks the current expansion path separately from nodes fully processed. A node is added to the output order after its prerequisites.

**Dijkstra:** Keep the best known distance for each node. Repeatedly expand the unvisited node with the smallest known cost and relax its outgoing edges. This implementation is intended for non-negative edge weights; Dijkstra is not correct for negative weights. The straightforward minimum selection makes this version O(V²) in the usual dense/array-style analysis.

**Town judge:** The candidate must have in-degree `n - 1` and out-degree `0`. The implementation first counts both degrees, then searches the people from `1` to `n`.

## Exercises

1. Draw a directed graph and write its adjacency map. Calculate in-degree and out-degree for every node by hand.
2. Trace the queue-like `remaining` worklist and `consideredNodes` set in `isPath`.
3. Add a disconnected node to a course graph; explain why it still needs to appear in the final order.
4. Trace Dijkstra's distance map after every expansion in one `NetworkDelays` example.
5. Compare a queue-based BFS with a stack-based DFS for unweighted shortest paths.

Interview practice: state whether edges are directed, whether weights may be negative, how you avoid revisiting nodes, and what the graph representation costs. Test disconnected graphs, isolated nodes, cycles, and duplicate edges.
