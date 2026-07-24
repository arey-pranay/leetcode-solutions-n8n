# Letter Combinations Of A Phone Number

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Backtracking`  
**Time:** O(4^N * N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    List<String> ans = new ArrayList<>();
    Map<Character, List<Character>> hm = Map.of(
        '2',List.of('a','b','c'),
        '3',List.of('d','e','f'),
        '4',List.of('g','h','i'),
        '5',List.of('j','k','l'),
        '6',List.of('m','n','o'),
        '7',List.of('p','q','r','s'),
        '8',List.of('t','u','v'),
        '9',List.of('w','x','y','z')
    );
    public List<String> letterCombinations(String digits) {
        func(digits,0,new StringBuilder(""));
        return ans;
    }
    public void func(String digits, int i, StringBuilder sb){
        if(i==digits.length()) { ans.add(new String(sb.toString())); return;} 
        List<Character> chars = hm.get(digits.charAt(i));
        for(int x=0;x<chars.size();x++){
            sb.append(chars.get(x));
            func(digits,i+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
```

---

---
## Quick Revision
Given a string of digits, return all possible letter combinations that the number could represent based on a phone keypad mapping.
This problem is solved using a recursive backtracking approach.

## Intuition
The core idea is that for each digit, we have a set of possible letters. We need to explore all combinations by picking one letter for the first digit, then one for the second, and so on. This naturally leads to a tree-like exploration where each level represents a digit and each branch represents a letter choice. Backtracking is essential to explore all paths in this tree.

## Algorithm
1.  **Initialization**:
    *   Create a mapping (e.g., a HashMap) from digit characters ('2'-'9') to their corresponding letter lists.
    *   Initialize an empty list to store the resulting combinations.
    *   Handle the edge case: if the input `digits` string is empty, return the empty list.
2.  **Recursive Helper Function (`func`)**:
    *   **Parameters**: `digits` (the input string), `index` (current digit being processed), `currentCombination` (a StringBuilder to build the current combination).
    *   **Base Case**: If `index` equals the length of `digits`, it means we have processed all digits. Add the `currentCombination` to the result list and return.
    *   **Recursive Step**:
        *   Get the current digit character at `digits[index]`.
        *   Retrieve the list of letters corresponding to this digit from the mapping.
        *   Iterate through each `letter` in the retrieved list:
            *   Append the `letter` to `currentCombination`.
            *   Recursively call `func` with `index + 1` (move to the next digit) and the updated `currentCombination`.
            *   **Backtrack**: Remove the last appended `letter` from `currentCombination` to explore other possibilities for the current digit.

## Concept to Remember
*   **Recursion**: Breaking down a problem into smaller, self-similar subproblems.
*   **Backtracking**: A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **String Manipulation**: Efficiently building and modifying strings using `StringBuilder`.

## Common Mistakes
*   **Forgetting to backtrack**: Not removing the last appended character from the `StringBuilder` after a recursive call, leading to incorrect combinations.
*   **Incorrect base case**: Not properly identifying when a complete combination has been formed.
*   **Handling empty input**: Not considering the case where the input `digits` string is empty.
*   **Inefficient string concatenation**: Using `+` for string concatenation repeatedly in a loop or recursion, which can be slow. `StringBuilder` is preferred.

## Complexity Analysis
*   **Time**: O(4^N * N), where N is the length of the `digits` string. In the worst case (digits '7' or '9'), each digit can map to 4 letters. We explore a tree of depth N, and at each leaf node (depth N), we create a new string of length N. The `* N` comes from the string creation at the base case.
*   **Space**: O(N) for the recursion call stack depth and O(N) for the `StringBuilder`. The output list can store up to O(4^N * N) strings, but this is usually not counted in auxiliary space complexity.

## Commented Code
```java
class Solution {
    // List to store all the generated letter combinations.
    List<String> ans = new ArrayList<>();
    // Map to store the digit-to-letter mappings for a phone keypad.
    Map<Character, List<Character>> hm = Map.of(
        '2',List.of('a','b','c'), // '2' maps to 'a', 'b', 'c'
        '3',List.of('d','e','f'), // '3' maps to 'd', 'e', 'f'
        '4',List.of('g','h','i'), // '4' maps to 'g', 'h', 'i'
        '5',List.of('j','k','l'), // '5' maps to 'j', 'k', 'l'
        '6',List.of('m','n','o'), // '6' maps to 'm', 'n', 'o'
        '7',List.of('p','q','r','s'), // '7' maps to 'p', 'q', 'r', 's'
        '8',List.of('t','u','v'), // '8' maps to 't', 'u', 'v'
        '9',List.of('w','x','y','z') // '9' maps to 'w', 'x', 'y', 'z'
    );
    // Main function to initiate the letter combinations generation.
    public List<String> letterCombinations(String digits) {
        // Start the recursive process from the first digit (index 0) with an empty string builder.
        func(digits,0,new StringBuilder(""));
        // Return the list of all generated combinations.
        return ans;
    }
    // Recursive helper function to generate combinations.
    // digits: the input string of digits.
    // i: the current index of the digit being processed.
    // sb: the StringBuilder holding the current combination being built.
    public void func(String digits, int i, StringBuilder sb){
        // Base case: if the current index 'i' has reached the end of the digits string.
        if(i==digits.length()) { 
            // A complete combination has been formed, add its string representation to the answer list.
            ans.add(new String(sb.toString())); 
            // Stop this recursive path.
            return;
        } 
        // Get the list of characters corresponding to the current digit at index 'i'.
        List<Character> chars = hm.get(digits.charAt(i));
        // Iterate through each possible character for the current digit.
        for(int x=0;x<chars.size();x++){
            // Append the current character to the StringBuilder.
            sb.append(chars.get(x));
            // Recursively call func for the next digit (i+1) with the updated StringBuilder.
            func(digits,i+1,sb);
            // Backtrack: remove the last appended character to explore other possibilities for the current digit.
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
```

## Interview Tips
*   Clearly explain the backtracking process and why it's necessary.
*   Walk through an example (e.g., "23") on a whiteboard or paper to illustrate the recursion tree.
*   Discuss the time and space complexity, justifying your reasoning.
*   Be prepared to discuss alternative approaches, like iterative solutions using a queue, though recursion is often more intuitive here.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement the digit-to-letter mapping correctly.
- [ ] Implement the recursive backtracking logic with a proper base case.
- [ ] Ensure correct backtracking by removing characters from `StringBuilder`.
- [ ] Handle the edge case of an empty input string.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Generate Parentheses

## Tags
`Backtracking` `Recursion` `String` `Hash Map`
