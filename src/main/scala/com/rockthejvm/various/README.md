# Module 7: Other problem-solving patterns

These problems combine the Scala and algorithm skills from earlier modules. They are intentionally different from one another: expression parsing, backtracking search, and recursive graphics each need a different way to represent progress.

## Source map

- [`Eval.scala`](Eval.scala) evaluates expressions with `+`, `-`, `*`, and `/` using operand and operator stacks. Operator precedence is represented by a `priority` function.
- [`NQueens.scala`](NQueens.scala) searches for placements of queens that do not attack one another. The state records queen positions; conflicts prune impossible choices.
- [`SierpinskiTriangles.scala`](SierpinskiTriangles.scala) builds a text-based fractal by transforming and joining rows recursively.

Run `runMain com.rockthejvm.various.NQueens` for the N-Queens output. `Eval` and `SierpinskiTriangles` extend `App` and can be run by their object names.

## Patterns to recognize

### Expression evaluation

`Eval` splits tokens into numbers and operators, then compares operator priorities. A higher-priority pending operator must be evaluated before a lower-priority one. The two immutable lists act as stacks: `head` is the top, and prepending pushes a value. Pattern matching (`case "+" => ...`) chooses the operation.

The implementation is a focused exercise rather than a complete production parser: it expects space-separated, well-formed tokens and does not handle parentheses or unary operators. State such assumptions before extending it.

### Backtracking

N-Queens tries a position, checks it against existing queens, and advances when there is a conflict. A valid partial placement leads to a deeper search; a complete placement is saved as a solution. Backtracking means returning to the earlier decision point and trying the next choice when a path cannot produce a solution.

The recursive helper's parameters describe its search state: current column/position, already placed queens, and solutions collected so far. The `zipWithIndex` operation pairs list values with their indexes, and `exists` checks whether any existing queen conflicts with a candidate.

### Recursive construction

The Sierpinski function has a smallest shape (`List("*")`) and a rule for building a larger shape from a smaller one. `map` transforms each row, and `++` concatenates the upper and lower portions. This is a useful example of defining a recursive object by a base case and a construction rule.

## Exercises

1. Evaluate `1 + 2 * 3` by hand. Show what each stack contains and why multiplication happens before addition.
2. Extend `Eval` only after deciding how to represent parentheses; explain why precedence alone is not enough.
3. Draw an N-Queens board for `n = 4` and explain which candidate placements are pruned.
4. Run `NQueens` for smaller board sizes and count the solutions.
5. Trace the first two construction levels of the Sierpinski triangle without running the code.

Interview practice: identify the state space, stopping condition, and pruning rule. For parsing, also state the grammar your evaluator supports.
