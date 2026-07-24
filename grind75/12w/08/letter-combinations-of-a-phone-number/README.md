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
1. **Initialization**: Create a mapping (e.g., a HashMap) from digit characters ('2'-'9') to their corresponding letter lists. Initialize an empty list to store the final combinations and an empty StringBuilder to build current combinations.
2. **Base Case**: If the input `digits` string is empty, return an empty list of combinations.
3. **Recursive Function `func(digits, index, current_combination)`**:
    a. **Termination Condition**: If `index` reaches the length of `digits`, it means a complete combination has been formed. Add the `current_combination` (converted to a String) to the result list and return.
    b. **Get Letters**: Retrieve the list of characters corresponding to the digit at the current `index` from the mapping.
    c. **Iterate and Recurse**: For each character in the retrieved list:
        i. **Append**: Append the current character to `current_combination`.
        ii. **Recurse**: Call `func` with `index + 1` to process the next digit.
        iii. **Backtrack**: Remove the last appended character from `current_combination` to explore other possibilities for the current digit.
4. **Start Recursion**: Call the recursive function `func` with the initial `digits` string, starting `index` 0, and an empty `StringBuilder`.
5. **Return Result**: Return the list containing all generated combinations.

## Concept to Remember
*   **Recursion**: Breaking down a problem into smaller, self-similar subproblems.
*   **Backtracking**: A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **String Manipulation**: Efficiently building and modifying strings using `StringBuilder`.

## Common Mistakes
*   **Forgetting to backtrack**: Not removing the last appended character from the `StringBuilder` after a recursive call, leading to incorrect combinations.
*   **Incorrect base case**: Not handling the empty input `digits` string or not correctly identifying when a full combination is formed.
*   **Off-by-one errors**: Incorrectly managing the `index` in the recursive calls.
*   **Mutable state issues**: If not using `StringBuilder` correctly or passing immutable strings, it can lead to unexpected behavior.

## Complexity Analysis
*   **Time**: O(4^N * N) - where N is the number of digits. In the worst case (digits like '7' or '9' which have 4 letters), each digit can branch into 4 possibilities. The `* N` comes from converting the `StringBuilder` to a `String` at the end of each valid path, which takes O(N) time.
*   **Space**: O(N) - for the recursion depth (call stack) and the `StringBuilder`. The space for the output list is not typically counted in space complexity unless explicitly asked.

## Commented Code
```java
class Solution {
    // List to store all the generated letter combinations.
    List<String> ans = new ArrayList<>();
    // Map to store the digit-to-letter mappings for a standard phone keypad.
    Map<Character, List<Character>> hm = Map.of(
        '2',List.of('a','b','c'), // Mapping for digit '2'
        '3',List.of('d','e','f'), // Mapping for digit '3'
        '4',List.of('g','h','i'), // Mapping for digit '4'
        '5',List.of('j','k','l'), // Mapping for digit '5'
        '6',List.of('m','n','o'), // Mapping for digit '6'
        '7',List.of('p','q','r','s'), // Mapping for digit '7'
        '8',List.of('t','u','v'), // Mapping for digit '8'
        '9',List.of('w','x','y','z')  // Mapping for digit '9'
    );
    // Main function to initiate the letter combination generation.
    public List<String> letterCombinations(String digits) {
        // Start the recursive helper function.
        // digits: the input string of digits.
        // 0: the starting index for processing digits.
        // new StringBuilder(""): an empty StringBuilder to build the current combination.
        func(digits,0,new StringBuilder(""));
        // Return the list containing all generated combinations.
        return ans;
    }
    // Recursive helper function to generate combinations.
    public void func(String digits, int i, StringBuilder sb){
        // Base case: If the current index 'i' has reached the end of the digits string,
        // it means a complete combination has been formed.
        if(i==digits.length()) {
            // Add the current combination (as a String) to the answer list.
            ans.add(new String(sb.toString()));
            // Return to the previous call stack.
            return;
        }
        // Get the list of characters corresponding to the digit at the current index 'i'.
        List<Character> chars = hm.get(digits.charAt(i));
        // Iterate through each character available for the current digit.
        for(int x=0;x<chars.size();x++){
            // Append the current character to the StringBuilder to form part of the combination.
            sb.append(chars.get(x));
            // Recursively call func for the next digit (i+1).
            func(digits,i+1,sb);
            // Backtrack: Remove the last appended character from the StringBuilder.
            // This is crucial to explore other possibilities for the current digit.
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
```

## Interview Tips
*   Clearly explain the recursive structure and the role of backtracking.
*   Walk through an example (e.g., "23") on a whiteboard to illustrate the recursion tree.
*   Discuss the time and space complexity, justifying each part.
*   Be prepared to discuss alternative approaches, like an iterative BFS solution, though recursion is more common for this problem.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement the digit-to-letter mapping correctly.
- [ ] Implement the recursive function with a clear base case.
- [ ] Ensure correct appending and backtracking in the recursive function.
- [ ] Handle the edge case of an empty input string.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Generate Parentheses

## Tags
`Backtracking` `Recursion` `String` `Map` `Depth-First Search`
