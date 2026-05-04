# Climbing Stairs

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Dynamic Programming` `Memoization`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int[] memo;
    public int climbStairs(int n) {
        memo = new int[n+1];
        Arrays.fill(memo,-1);
        return func(n);
    }
    public int func(int curr){
        if(curr==0) return 1;
        if(curr<0) return 0;
        if(memo[curr] == -1) memo[curr] = func(curr-1) + func(curr-2);
        return memo[curr];
    }
}
// 2 = 11 , 2
// 3 = 111 , 12, 21, 
// 4 = 1111 , 22 , 121 , 1112, 2111, 1211, 
// 0 1 2 3 5 8 
// 0 1 1 2 3 5 8 13
```

---

---
## Quick Revision
You need to find the number of distinct ways to climb to the top of a staircase with `n` steps, taking either 1 or 2 steps at a time. This problem can be solved using dynamic programming or recursion with memoization.

## Intuition
The core idea is that to reach step `n`, you must have come from either step `n-1` (by taking one step) or step `n-2` (by taking two steps). Therefore, the total number of ways to reach step `n` is the sum of the ways to reach step `n-1` and the ways to reach step `n-2`. This recursive relationship is the hallmark of the Fibonacci sequence.

## Algorithm
1. **Base Cases:**
   - If `n` is 0, there's 1 way to reach it (do nothing).
   - If `n` is negative, there are 0 ways to reach it (invalid state).
2. **Recursive Step:**
   - For any `n > 0`, the number of ways to reach `n` is `ways(n-1) + ways(n-2)`.
3. **Memoization:**
   - To avoid redundant calculations in the recursive calls, use an array (e.g., `memo`) to store the results for each `n`.
   - Initialize `memo` with a sentinel value (e.g., -1) to indicate that a result hasn't been computed yet.
   - Before computing `ways(n)`, check if `memo[n]` already has a valid result. If so, return it.
   - After computing `ways(n)`, store it in `memo[n]`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Dynamic Programming (Top-Down/Memoization):** Storing results of expensive function calls and returning the cached result when the same inputs occur again.
*   **Fibonacci Sequence:** A sequence where each number is the sum of the two preceding ones, usually starting with 0 and 1. This problem maps directly to it.

## Common Mistakes
*   **Incorrect Base Cases:** Forgetting to handle `n=0` or `n<0` correctly can lead to infinite recursion or wrong results.
*   **Not Using Memoization:** A purely recursive solution without memoization will have exponential time complexity due to repeated computations of the same subproblems.
*   **Off-by-One Errors:** Miscalculating array indices or loop bounds when implementing the DP approach.
*   **Integer Overflow:** For very large `n`, the number of ways can exceed the capacity of standard integer types. (Though less likely for typical LeetCode constraints on this problem).

## Complexity Analysis
- Time: O(n) - Each subproblem `func(i)` for `i` from 0 to `n` is computed only once due to memoization.
- Space: O(n) - For the `memo` array used to store results of subproblems, and O(n) for the recursion call stack in the worst case.

## Commented Code
```java
class Solution {
    // Declare an array to store computed results (memoization).
    int[] memo;

    // Main function to initiate the climbing stairs calculation.
    public int climbStairs(int n) {
        // Initialize the memoization array with size n+1.
        // We need indices from 0 to n.
        memo = new int[n+1];
        // Fill the memo array with -1, indicating that no results have been computed yet.
        Arrays.fill(memo,-1);
        // Call the recursive helper function to compute the number of ways for n steps.
        return func(n);
    }

    // Recursive helper function with memoization.
    public int func(int curr){
        // Base case 1: If the current step is 0, we've reached the top successfully. There's 1 way.
        if(curr==0) return 1;
        // Base case 2: If the current step is negative, it means we overshot. This path is invalid. There are 0 ways.
        if(curr<0) return 0;
        // Check if the result for the current step has already been computed and stored in memo.
        if(memo[curr] == -1) {
            // If not computed, calculate it:
            // The number of ways to reach 'curr' is the sum of ways to reach 'curr-1' (by taking 1 step)
            // and ways to reach 'curr-2' (by taking 2 steps).
            memo[curr] = func(curr-1) + func(curr-2);
        }
        // Return the computed (or previously stored) result for the current step.
        return memo[curr];
    }
}
```

## Interview Tips
*   **Explain the Fibonacci Connection:** Clearly articulate how the problem maps to the Fibonacci sequence and why.
*   **Discuss Trade-offs:** Mention the difference between a pure recursive solution (exponential time) and a memoized/DP solution (linear time and space).
*   **Walk Through an Example:** Use a small `n` (like 3 or 4) to trace the recursive calls and how memoization prevents re-computation.
*   **Consider Iterative DP:** Briefly mention that an iterative bottom-up DP approach is also possible and can sometimes be preferred for space optimization (though not significantly here).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the recursive relationship: `ways(n) = ways(n-1) + ways(n-2)`.
- [ ] Define correct base cases: `ways(0) = 1`, `ways(<0) = 0`.
- [ ] Implement memoization to store and retrieve results.
- [ ] Analyze time and space complexity.
- [ ] Practice writing the code from scratch.

## Similar Problems
*   70. Climbing Stairs (This problem)
*   509. Fibonacci Number
*   198. House Robber
*   303. Range Sum Query - Immutable (uses prefix sums, related DP concept)
*   746. Min Cost Climbing Stairs

## Tags
`Dynamic Programming` `Recursion` `Memoization` `Math`
