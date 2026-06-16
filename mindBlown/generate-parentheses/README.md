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
This problem is solved using backtracking (depth-first search) to explore valid combinations.

## Intuition
The core idea is to build valid parentheses strings incrementally. At each step, we have two choices: add an opening parenthesis '(' or a closing parenthesis ')'. However, we must adhere to two rules to ensure well-formedness:
1. We can only add an opening parenthesis if we haven't used all `n` available opening parentheses.
2. We can only add a closing parenthesis if the number of closing parentheses is strictly less than the number of opening parentheses already placed. This ensures that we never have more closing parentheses than opening ones at any point, preventing invalid sequences like `))((`.
When the string reaches the desired length (`2*n`), we've found a valid combination.

## Algorithm
1. Initialize an empty list `res` to store the valid parentheses combinations.
2. Initialize a `StringBuilder` `s` to build the current parentheses string.
3. Define a recursive helper function `func(StringBuilder s, int open, int close)`:
    a. **Base Case:** If the length of `s` is equal to `2 * n`, it means we have formed a complete, valid parentheses string. Add `s.toString()` to `res` and return.
    b. **Recursive Step 1 (Add Opening Parenthesis):** If the number of `open` parentheses used is less than `n`, append '(' to `s`, increment `open`, and recursively call `func(s, open + 1, close)`. After the recursive call returns, backtrack by removing the last character from `s` (i.e., `s.deleteCharAt(s.length() - 1)`).
    c. **Recursive Step 2 (Add Closing Parenthesis):** If the number of `close` parentheses used is less than the number of `open` parentheses used, append ')' to `s`, increment `close`, and recursively call `func(s, open, close + 1)`. After the recursive call returns, backtrack by removing the last character from `s`.
4. Call the `func` with an empty `StringBuilder`, `open = 0`, and `close = 0`.
5. Return the `res` list.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion:** A method where the solution to a problem depends on solutions to smaller instances of the same problem.
*   **State Management in Recursion:** Carefully managing the state (e.g., `open` and `close` counts, the `StringBuilder`) and ensuring proper backtracking (undoing changes) is crucial for correctness.

## Common Mistakes
*   **Incorrect Base Case:** Not stopping the recursion when the string length reaches `2*n` or adding incomplete strings.
*   **Violating Constraints:** Adding a closing parenthesis when `close >= open`, or adding more than `n` opening parentheses.
*   **Forgetting Backtracking:** Failing to remove the last added character from the `StringBuilder` after a recursive call returns, leading to incorrect strings being built.
*   **Mutable State Issues:** If not using `StringBuilder` correctly with backtracking, or if passing immutable strings and creating new ones in each call, it can lead to performance issues or incorrect results.

## Complexity Analysis
*   **Time:** O(4^n / sqrt(n)) - This is related to the Catalan numbers. For each valid combination, we perform constant time operations (append, delete, check conditions). The number of valid combinations grows exponentially.
*   **Space:** O(n) - This is due to the recursion depth, which can go up to `2*n` (the length of the string), and the `StringBuilder` which also stores up to `2*n` characters. The `res` list can store up to O(4^n / sqrt(n)) strings, but this is typically considered output space and not auxiliary space.

## Commented Code
```java
class Solution {
    // List to store all valid combinations of parentheses.
    List<String> res = new ArrayList<>();
    // Stores the target number of pairs of parentheses.
    int n;

    // Main function to initiate the generation process.
    public List<String> generateParenthesis(int N) {
        // Initialize the StringBuilder to build the current parentheses string.
        StringBuilder s = new StringBuilder("");
        // Initialize the count of open parentheses used.
        int open = 0;
        // Initialize the count of close parentheses used.
        int close = 0;
        // Set the instance variable 'n' to the input value.
        n = N;
        // Start the recursive backtracking function.
        func(s, 0, 0);
        // Return the list of all generated valid parentheses combinations.
        return res;
    }

    // Recursive helper function to generate parentheses combinations.
    // s: The current string being built.
    // open: The number of open parentheses used so far.
    // close: The number of close parentheses used so far.
    public void func(StringBuilder s, int open, int close) {
        // Base case: If the length of the string is twice the target number of pairs,
        // it means we have formed a complete and valid parentheses string.
        if (s.length() == n * 2) {
            // Add the current string to the result list.
            res.add(s.toString());
            // Stop this recursive path.
            return;
        }

        // Recursive step 1: Try adding an opening parenthesis.
        // Condition: We can add an opening parenthesis if we haven't used all 'n' available open parentheses.
        if (open < n) {
            // Append '(' to the current string.
            // Increment the count of open parentheses.
            func(s.append('('), open + 1, close);
            // Backtrack: Remove the last added character to explore other possibilities.
            s.deleteCharAt(s.length() - 1);
        }

        // Recursive step 2: Try adding a closing parenthesis.
        // Condition: We can add a closing parenthesis only if the number of closing parentheses
        // is strictly less than the number of opening parentheses. This ensures well-formedness.
        if (close < open) {
            // Append ')' to the current string.
            // Increment the count of close parentheses.
            func(s.append(')'), open, close + 1);
            // Backtrack: Remove the last added character to explore other possibilities.
            s.deleteCharAt(s.length() - 1);
        }
        // Return from the current recursive call.
        return;
    }
}
```

## Interview Tips
*   **Explain Backtracking Clearly:** Articulate the process of building the string step-by-step and how you prune invalid paths. Use the `open` and `close` counts as your primary logic.
*   **Trace an Example:** Walk through `n=2` or `n=3` manually to demonstrate your understanding of the algorithm and how the `StringBuilder` and backtracking work.
*   **Discuss Constraints:** Emphasize why `open < n` and `close < open` are the critical conditions for generating *well-formed* parentheses.
*   **Mention Catalan Numbers (Optional but good):** If you're comfortable, briefly mentioning that the number of valid combinations relates to Catalan numbers shows deeper mathematical understanding.

## Revision Checklist
- [ ] Understand the problem statement: generate all valid combinations of `n` pairs of parentheses.
- [ ] Identify the core constraints for well-formed parentheses: `open < n` and `close < open`.
- [ ] Implement a recursive backtracking solution.
- [ ] Correctly manage the `StringBuilder` (append and backtrack).
- [ ] Handle the base case: string length equals `2*n`.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with small `n` values.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Letter Combinations of a Phone Number
*   N-Queens

## Tags
`Backtracking` `Recursion` `String` `Dynamic Programming`
