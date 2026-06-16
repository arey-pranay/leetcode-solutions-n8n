# Generate Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Backtracking`  
**Time:** O(4^n / sqrt(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    List<String> res = new ArrayList<>();
    int n;
    public List<String> generateParenthesis(int N) {
        StringBuilder s = new StringBuilder("");
        int open = 0;
        int close = 0;
        n = N;
        func(s,0,0);
        return res;
    }
    public void func(StringBuilder s, int open, int close){
        if(s.length()==n*2){
            res.add(s.toString());
            return ;
        }
        if(open < n){
            func(s.append('('),open+1,close);
            s.deleteCharAt(s.length()-1);
        }
        if(close < open){
            func(s.append(')'),open,close+1);
            s.deleteCharAt(s.length()-1);    
        }
        return;    
    }
}

```

---

---
## Quick Revision
Given an integer n, generate all combinations of well-formed parentheses.
This problem is solved using backtracking to explore all valid combinations.

## Intuition
The core idea is to build valid parenthesis strings incrementally. At any point, we can add an opening parenthesis if we haven't used all `n` available opening parentheses. We can add a closing parenthesis only if the number of closing parentheses is strictly less than the number of opening parentheses already placed. This ensures that we never have an invalid prefix (e.g., `))(`). When the string reaches the desired length (`2*n`), it's a valid combination.

## Algorithm
1. Initialize an empty list `res` to store the valid parenthesis combinations.
2. Initialize a `StringBuilder` `s` to build the current parenthesis string.
3. Define a recursive helper function `func(StringBuilder s, int open, int close)`:
    a. Base Case: If the length of `s` is equal to `2*n`, add `s.toString()` to `res` and return.
    b. Recursive Step 1 (Add Opening Parenthesis): If `open < n` (we still have opening parentheses to use), append '(' to `s`, increment `open`, and recursively call `func(s, open + 1, close)`. After the recursive call returns, backtrack by removing the last character from `s`.
    c. Recursive Step 2 (Add Closing Parenthesis): If `close < open` (we can add a closing parenthesis without violating the well-formed condition), append ')' to `s`, increment `close`, and recursively call `func(s, open, close + 1)`. After the recursive call returns, backtrack by removing the last character from `s`.
4. Call the helper function `func` initially with an empty `StringBuilder`, `open = 0`, and `close = 0`.
5. Return the `res` list.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion:** A method of solving a problem where the solution depends on smaller instances of the same problem.
*   **State Management in Recursion:** Carefully managing the state (e.g., `open` and `close` counts, the `StringBuilder`) and backtracking (undoing changes) is crucial for correctness.

## Common Mistakes
*   Not handling the base case correctly, leading to infinite recursion or incomplete results.
*   Incorrectly applying the conditions for adding opening and closing parentheses (e.g., `close < open` vs. `close <= open`).
*   Forgetting to backtrack (remove the character added in the recursive call), which corrupts the `StringBuilder` for subsequent branches.
*   Not initializing `n` correctly or passing it around unnecessarily.

## Complexity Analysis
*   Time: O(4^n / sqrt(n)) - This is related to the Catalan numbers. For each valid combination, we do a constant amount of work. The number of valid parenthesis combinations grows exponentially.
*   Space: O(n) - Primarily due to the recursion depth, which can go up to `2*n` (the length of the string), and the `StringBuilder` which also stores up to `2*n` characters. The `res` list can store many strings, but its size is accounted for in the time complexity.

## Commented Code
```java
class Solution {
    // List to store all valid combinations of parentheses.
    List<String> res = new ArrayList<>();
    // Stores the input number 'n' for easy access.
    int n;

    // Main function to generate parentheses.
    public List<String> generateParenthesis(int N) {
        // Initialize the StringBuilder to build the current parenthesis string.
        StringBuilder s = new StringBuilder("");
        // Initialize the count of open parentheses used.
        int open = 0;
        // Initialize the count of close parentheses used.
        int close = 0;
        // Store the input 'N' in the class member 'n'.
        n = N;
        // Start the recursive backtracking process.
        func(s, 0, 0);
        // Return the list of all generated valid parenthesis combinations.
        return res;
    }

    // Recursive helper function to generate parentheses using backtracking.
    // s: The current string being built.
    // open: The number of open parentheses used so far.
    // close: The number of close parentheses used so far.
    public void func(StringBuilder s, int open, int close) {
        // Base case: If the length of the string is twice the input 'n',
        // it means we have a complete and valid combination.
        if (s.length() == n * 2) {
            // Add the current string to the result list.
            res.add(s.toString());
            // Stop this recursive branch.
            return;
        }

        // Recursive step 1: Try adding an opening parenthesis.
        // Condition: We can add an opening parenthesis if we haven't used all 'n' available open parentheses.
        if (open < n) {
            // Append '(' to the current string.
            func(s.append('('), open + 1, close);
            // Backtrack: Remove the last character added to explore other possibilities.
            s.deleteCharAt(s.length() - 1);
        }

        // Recursive step 2: Try adding a closing parenthesis.
        // Condition: We can add a closing parenthesis only if the number of closing parentheses
        // is strictly less than the number of opening parentheses. This ensures well-formedness.
        if (close < open) {
            // Append ')' to the current string.
            func(s.append(')'), open, close + 1);
            // Backtrack: Remove the last character added to explore other possibilities.
            s.deleteCharAt(s.length() - 1);
        }
        // The function implicitly returns after exploring all valid branches from this state.
        return;
    }
}
```

## Interview Tips
*   Clearly explain the backtracking approach and the conditions for adding '(' and ')'.
*   Walk through an example (e.g., n=2) to demonstrate how the recursion and backtracking work.
*   Pay close attention to the state variables (`open`, `close`) and the backtracking step (`s.deleteCharAt`).
*   Be prepared to discuss the time and space complexity, relating it to Catalan numbers if possible.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core idea: building valid strings incrementally.
- [ ] Recognize backtracking as the appropriate technique.
- [ ] Define the state for the recursive function (current string, open count, close count).
- [ ] Implement the base case correctly.
- [ ] Implement the recursive steps with correct conditions for adding '(' and ')'.
- [ ] Ensure proper backtracking by removing characters.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Letter Combinations of a Phone Number

## Tags
`Backtracking` `Recursion` `String` `StringBuilder`
