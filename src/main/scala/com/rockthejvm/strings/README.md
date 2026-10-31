# Module 4: String problems

Strings are sequences of characters, but interview solutions often become simpler after converting them into another representation: a frequency map, a list of tokens, a stack-like counter, or digits.

## Learning goals

- Traverse strings and transform characters into collections.
- Use maps to count and compare frequencies.
- Use a running balance to validate parentheses.
- Build algorithms by simulating familiar manual processes.

## Source map

| File | Problem and concepts |
|---|---|
| [`CountCharacters.scala`](CountCharacters.scala) | Count occurrences into `Map[Char, Int]` |
| [`Anagrams.scala`](Anagrams.scala) | Compare character-count maps or sorted strings |
| [`ParenthesisProblems.scala`](ParenthesisProblems.scala) | Validate and generate balanced parentheses |
| [`ReverseWords.scala`](ReverseWords.scala) | Split, filter empty tokens, reverse, and join |
| [`RansomNote.scala`](RansomNote.scala) | Check whether one string has enough copies of each character |
| [`ReorganizeString.scala`](ReorganizeString.scala) | Greedily choose a frequent character that differs from the previous character |
| [`MultiplyStrings.scala`](MultiplyStrings.scala) | Multiply digit strings using carries without converting to a machine integer |
| [`CompareVersionNumbers.scala`](CompareVersionNumbers.scala) | Compare dot-separated integer components |
| [`Justify.scala`](Justify.scala) | Pack words into rows and distribute spaces |

Run `runMain com.rockthejvm.strings.ParenthesisProblems` for the parentheses examples.

## Important Scala syntax

- `string.split(" ").toList` converts the string into an array of tokens, then into a Scala `List`.
- `map`, `filter`, and `flatMap` transform or select collection elements. `mkString(separator)` joins elements into one string.
- `Map[Char, Int]` associates each character with its count. `getOrElse(key, default)` handles a missing key.
- `forall(predicate)` is true only when every element satisfies the predicate; `exists(predicate)` is true when at least one does.
- A partial function such as `.map { case (digit, index) => ... }` pattern-matches and unpacks each tuple.
- `@tailrec` is used where a helper makes a recursive call after updating its state.

## Trace: valid parentheses

`ParenthesisProblems.hasValidParentheses` keeps a count of open parentheses:

1. Start with `openParens = 0`.
2. On `(`, increment the count.
3. On `)`, decrement the count.
4. If a closing parenthesis arrives while the count is zero, the string is invalid immediately.
5. At the end, the count must be zero.

This is O(N) time and O(1) extra space. The important insight is that a stack is not needed when the only opening and closing symbol is one matching pair.

## Exercises

1. Explain why comparing frequency maps detects anagrams, including repeated characters.
2. Trace the greedy choices in `reorganizeString("aaabc")`; find the condition that makes an answer impossible.
3. Multiply `123 × 45` by hand, then relate each partial product and carry to `MultiplyStrings`.
4. Explain why trailing zero revisions do not change a version number.
5. Try empty input, repeated spaces, one-character input, and input with a single character appearing many times. Document any assumptions you discover.

Interview practice: state your chosen representation and invariant before writing code. Discuss whether normalization (case-folding, punctuation removal, or whitespace handling) is part of the problem contract instead of silently assuming it.
