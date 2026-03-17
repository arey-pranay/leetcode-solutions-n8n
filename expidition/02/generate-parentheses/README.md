# Generate Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Backtracking`  
**Time:** O(4^n / (n * sqrt(n)  
**Space:** O(4^n / (n * sqrt(n)

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
Given an integer `n`, generate all combinations of well-formed parentheses.
This problem is solved using backtracking to explore valid parenthesis placements.

## Intuition
The core idea is to build valid parenthesis strings incrementally. At any point, we can add an opening parenthesis if we haven't used all `n` available opening parentheses. We can add a closing parenthesis only if it maintains the well-formed property, meaning the number of closing parentheses does not exceed the number of opening parentheses. This naturally leads to a recursive, backtracking approach.

## Algorithm
1. Initialize an empty list `res` to store the valid parenthesis combinations.
2. Initialize a `StringBuilder` `s` to build the current parenthesis string.
3. Define a recursive helper function `func(StringBuilder s, int open, int close)`:
    a. **Base Case:** If the length of `s` is equal to `2 * n` (meaning we have `n` pairs of parentheses), add the current string `s` to `res` and return.
    b. **Recursive Step 1 (Add Open Parenthesis):** If the number of `open` parentheses used is less than `n`, append an opening parenthesis `(` to `s`, increment `open`, and recursively call `func(s, open + 1, close)`. After the recursive call returns, backtrack by removing the last character from `s` (the appended `(`).
    c. **Recursive Step 2 (Add Close Parenthesis):** If the number of `close` parentheses used is less than the number of `open` parentheses used, append a closing parenthesis `)` to `s`, increment `close`, and recursively call `func(s, open, close + 1)`. After the recursive call returns, backtrack by removing the last character from `s` (the appended `)`).
4. Call the helper function `func` initially with an empty `StringBuilder`, `open = 0`, and `close = 0`.
5. Return the `res` list.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion:** A method where the solution to a problem depends on solutions to smaller instances of the same problem.
*   **State Management in Recursion:** Carefully managing the state (e.g., `open` and `close` counts, the `StringBuilder`) and backtracking (undoing changes) is crucial for correctness.

## Common Mistakes
*   Not handling the base case correctly, leading to infinite recursion or incomplete results.
*   Incorrectly applying the constraints for adding opening and closing parentheses (e.g., allowing `close > open` at any point).
*   Forgetting to backtrack (remove the last character from the `StringBuilder`) after a recursive call, which corrupts the state for subsequent branches.
*   Modifying the `StringBuilder` directly without proper backtracking, leading to incorrect string constructions.

## Complexity Analysis
*   **Time:** O(4^n / (n * sqrt(n))). This is because the number of valid parenthesis combinations is given by the Catalan numbers, C_n. The generation process explores a tree where each node can have up to two children, but the pruning based on constraints significantly reduces the search space. The exact complexity is related to Catalan numbers.
*   **Space:** O(n) for the recursion depth (call stack) and O(n) for the `StringBuilder`. The total space complexity is dominated by the recursion depth, which is proportional to `n` (the maximum length of a valid parenthesis string is `2n`).

## Commented Code
```java
class Solution {
    // List to store all valid combinations of parentheses.
    List<String> res = new ArrayList<>();
    // Stores the input integer N, representing the number of pairs of parentheses.
    int n;

    // Main function to generate parentheses.
    public List<String> generateParenthesis(int N) {
        // Initialize the StringBuilder to build the current parenthesis string.
        StringBuilder s = new StringBuilder("");
        // Initialize the count of open parentheses used.
        int open = 0;
        // Initialize the count of close parentheses used.
        int close = 0;
        // Store the input N in the class member variable.
        n = N;
        // Start the recursive backtracking process.
        func(s,0,0);
        // Return the list of all generated valid parenthesis combinations.
        return res;
    }

    // Recursive helper function to generate parenthesis combinations.
    // s: The current string being built.
    // open: The number of open parentheses used so far.
    // close: The number of close parentheses used so far.
    public void func(StringBuilder s, int open, int close){
        // Base case: If the length of the string is 2*n, it means we have formed a complete combination.
        if(s.length()==n*2){
            // Add the current valid string to the result list.
            res.add(s.toString());
            // Stop this recursive path.
            return ;
        }

        // Recursive step 1: Try adding an opening parenthesis.
        // Condition: We can add an opening parenthesis if we haven't used all 'n' available open parentheses.
        if(open < n){
            // Append '(' to the current string.
            // Increment the count of open parentheses.
            // Recursively call func with the updated state.
            func(s.append('('),open+1,close);
            // Backtrack: Remove the last character (which was '(') to explore other possibilities.
            s.deleteCharAt(s.length()-1);
        }

        // Recursive step 2: Try adding a closing parenthesis.
        // Condition: We can add a closing parenthesis only if the number of close parentheses is less than the number of open parentheses. This ensures well-formedness.
        if(close < open){
            // Append ')' to the current string.
            // Increment the count of close parentheses.
            // Recursively call func with the updated state.
            func(s.append(')'),open,close+1);
            // Backtrack: Remove the last character (which was ')') to explore other possibilities.
            s.deleteCharAt(s.length()-1);
        }
        // The function implicitly returns after exploring all valid paths from this state.
        return;
    }
}
```

## Interview Tips
*   Clearly explain the backtracking approach and the conditions for adding `(` and `)`.
*   Walk through an example (e.g., `n=2`) to demonstrate how the recursion and backtracking work.
*   Pay close attention to the state management (counts of `open` and `close`) and the backtracking step (`s.deleteCharAt`).
*   Be prepared to discuss the time and space complexity, relating it to Catalan numbers if possible.

## Revision Checklist
- [ ] Understand the problem: generate all valid parenthesis combinations.
- [ ] Identify the core constraint: well-formedness (close <= open at any point, and total open == total close == n).
- [ ] Recognize backtracking as a suitable approach.
- [ ] Implement the recursive function with correct base case and recursive steps.
- [ ] Ensure proper state updates (open/close counts) and backtracking.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combinations
*   Permutations
*   Subsets
*   Letter Combinations of a Phone Number

## Tags
`Backtracking` `Recursion` `String`

## My Notes
Most intuitive way of generating the paranthesis I think
