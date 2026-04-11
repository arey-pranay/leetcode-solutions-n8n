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
    char[][] phone = new char[][]{
        {'-'},
        {'-'},
        {'a','b','c'},
        {'d','e','f'},
        {'g','h','i'},
        {'j','k','l'},
        {'m','n','o'},
        {'p','q','r','s'},
        {'t','u','v'},
        {'w','x','y','z'}
    };
    List<String> ans;
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>(); 
        func(digits,0, new StringBuilder(""));
        return ans;
    }
    public void func(String digits, int index, StringBuilder curr){
        if(index == digits.length()){
            ans.add(curr.toString());
            return;
        }
        char[] carr = phone[(int)(digits.charAt(index) - '0')];
        for(char c : carr){
            curr.append(c);
            func(digits, index+1, curr);
            curr.deleteCharAt(curr.length()-1);
        }
        return;
    }
}
```

---

---
## Quick Revision
Given a string of digits, return all possible letter combinations that the number could represent on a phone keypad.
This problem is solved using a recursive backtracking approach to explore all possible combinations.

## Intuition
The core idea is to build combinations character by character. For each digit, we iterate through its corresponding letters and append them to the current combination. When we've processed all digits, we've found a complete combination. This naturally leads to a recursive structure where each recursive call handles one digit and explores its letter options.

## Algorithm
1.  **Initialization**: Create a mapping (e.g., a 2D char array or HashMap) from digits '2'-'9' to their corresponding letters. Initialize an empty list to store the resulting combinations.
2.  **Base Case**: If the input `digits` string is empty, return an empty list.
3.  **Recursive Function (`func`)**:
    *   **Parameters**: `digits` (the input string), `index` (current digit being processed), `currentCombination` (a StringBuilder to build the combination).
    *   **Termination Condition**: If `index` equals the length of `digits`, it means a complete combination has been formed. Add `currentCombination.toString()` to the result list and return.
    *   **Recursive Step**:
        *   Get the current digit: `digits.charAt(index)`.
        *   Convert the digit character to an integer to use as an index into the phone mapping.
        *   Iterate through each character (`c`) corresponding to the current digit.
        *   **Append**: Append `c` to `currentCombination`.
        *   **Recurse**: Call `func` with `index + 1` and the updated `currentCombination`.
        *   **Backtrack**: Remove the last appended character (`c`) from `currentCombination` to explore other possibilities for the current digit.

## Concept to Remember
*   **Backtracking**: A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Recursion**: A method of solving a problem where the solution depends on smaller instances of the same problem.
*   **String Manipulation (StringBuilder)**: Efficiently building strings by appending and deleting characters.

## Common Mistakes
*   **Forgetting to backtrack**: Not removing the last appended character from the `StringBuilder` after a recursive call, leading to incorrect combinations.
*   **Incorrect digit-to-letter mapping**: Errors in the `phone` array or HashMap, mapping digits to the wrong letters.
*   **Off-by-one errors in indexing**: Incorrectly handling the `index` in the recursive function, especially with the base case or when accessing `digits`.
*   **Not handling empty input**: Failing to return an empty list when the input `digits` string is empty.

## Complexity Analysis
*   **Time**: O(4^N * N), where N is the number of digits. In the worst case, each digit maps to 4 letters (e.g., '7' or '9'). For each digit, we explore 4 branches. The `* N` comes from the string concatenation/building operation at the leaf nodes of the recursion tree.
*   **Space**: O(N) for the recursion stack depth and the `StringBuilder`. The output list can store up to O(4^N * N) strings, but this is usually considered part of the output space, not auxiliary space.

## Commented Code
```java
class Solution {
    // Define the mapping from digits to letters, similar to a phone keypad.
    // Index 0 and 1 are unused, hence the '-' placeholder.
    char[][] phone = new char[][]{
        {'-'}, // 0
        {'-'}, // 1
        {'a','b','c'}, // 2
        {'d','e','f'}, // 3
        {'g','h','i'}, // 4
        {'j','k','l'}, // 5
        {'m','n','o'}, // 6
        {'p','q','r','s'}, // 7
        {'t','u','v'}, // 8
        {'w','x','y','z'}  // 9
    };
    // List to store all the generated letter combinations.
    List<String> ans;

    // Main function to initiate the letter combination generation.
    public List<String> letterCombinations(String digits) {
        // Initialize the answer list.
        ans = new ArrayList<>();
        // If the input digits string is empty, return the empty list immediately.
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        // Start the recursive helper function.
        // digits: the input string of digits.
        // 0: the starting index of the digits string to process.
        // new StringBuilder(""): an empty StringBuilder to build the current combination.
        func(digits, 0, new StringBuilder(""));
        // Return the list of all combinations.
        return ans;
    }

    // Recursive helper function to generate combinations.
    public void func(String digits, int index, StringBuilder curr){
        // Base case: If the current index has reached the end of the digits string,
        // it means a complete combination has been formed.
        if(index == digits.length()){
            // Add the current combination to the answer list.
            ans.add(curr.toString());
            // Stop this recursive path.
            return;
        }

        // Get the current digit character.
        char digitChar = digits.charAt(index);
        // Convert the digit character to its integer representation.
        // Subtract '0' to get the numerical value (e.g., '2' - '0' = 2).
        int digitInt = (int)(digitChar - '0');
        // Get the array of characters corresponding to the current digit from the phone mapping.
        char[] carr = phone[digitInt];

        // Iterate through each character (letter) that the current digit can represent.
        for(char c : carr){
            // Append the current character to the StringBuilder to build the combination.
            curr.append(c);
            // Recursively call func for the next digit (index + 1).
            // Pass the updated StringBuilder with the appended character.
            func(digits, index+1, curr);
            // Backtrack: Remove the last appended character from the StringBuilder.
            // This is crucial to explore other letter possibilities for the current digit
            // and to correctly build subsequent combinations.
            curr.deleteCharAt(curr.length()-1);
        }
        // The function implicitly returns after the loop finishes exploring all characters for the current digit.
        return;
    }
}
```

## Interview Tips
*   Clearly explain the backtracking approach and why it's suitable for this problem.
*   Walk through an example (e.g., "23") step-by-step, showing how the `StringBuilder` changes and how recursion unfolds.
*   Be prepared to discuss the time and space complexity, especially the `4^N` factor.
*   Emphasize the importance of the `curr.deleteCharAt(curr.length()-1)` line for backtracking.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement the digit-to-letter mapping correctly.
- [ ] Implement the recursive function with a clear base case.
- [ ] Ensure proper backtracking (removing characters from StringBuilder).
- [ ] Handle edge cases like empty input.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Generate Parentheses

## Tags
`Backtracking` `Recursion` `String` `Depth-First Search`

## My Notes
Beats100%, best way
