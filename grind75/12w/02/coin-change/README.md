# Coin Change

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Breadth-First Search`  
**Time:** O(amount * n)  
**Space:** O(amount)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    int[] memo;
    public int coinChange(int[] coins, int amount) {
        arr = coins;
        memo = new int[amount+1];
        Arrays.fill(memo,-1);
        int ans = func(amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // 7, 416, 419
    // 10
    // 848
    public int func(int amount){   
        if(amount == 0) return 0;
        if(amount < 0) return Integer.MAX_VALUE;
        if(memo[amount] != -1) return memo[amount];
        int ans = Integer.MAX_VALUE;
        for(int coin : arr){
            int temp = func(amount-coin);
            if(temp != Integer.MAX_VALUE) ans = Math.min(ans, temp+1);
        }
        return memo[amount] =ans;
    }
}
```

---

---
## Quick Revision
Given a set of coin denominations and a target amount, find the minimum number of coins to make up that amount.
This is solved using dynamic programming with memoization (top-down) or tabulation (bottom-up).

## Intuition
The core idea is to break down the problem into smaller subproblems. To find the minimum coins for an `amount`, we can consider using each `coin` denomination. If we use a `coin`, the problem reduces to finding the minimum coins for `amount - coin`. We want to find the minimum among all possible `coin` choices. This recursive structure with overlapping subproblems strongly suggests dynamic programming. The "aha moment" is realizing that the optimal solution for `amount` depends on the optimal solutions for smaller amounts.

## Algorithm
1. **Initialization**: Create a memoization array `memo` of size `amount + 1`. Initialize all entries to a sentinel value (e.g., -1) to indicate that the subproblem has not been solved yet.
2. **Base Cases**:
   - If `amount` is 0, return 0 (no coins needed).
   - If `amount` is negative, return `Integer.MAX_VALUE` (an impossible state).
3. **Memoization Check**: If `memo[amount]` is not the sentinel value, it means the result for this `amount` has already been computed, so return `memo[amount]`.
4. **Recursive Step**:
   - Initialize `minCoins` to `Integer.MAX_VALUE`.
   - Iterate through each `coin` in the `coins` array.
   - For each `coin`, recursively call the function for `amount - coin`. Let the result be `subProblemResult`.
   - If `subProblemResult` is not `Integer.MAX_VALUE` (meaning it's a valid solution), update `minCoins` with the minimum of its current value and `subProblemResult + 1` (adding 1 for the current coin used).
5. **Store and Return**: Store the computed `minCoins` in `memo[amount]` and return it.
6. **Final Result**: In the main function, call the recursive helper for the target `amount`. If the returned value is `Integer.MAX_VALUE`, it means the amount cannot be made up, so return -1. Otherwise, return the computed minimum number of coins.

## Concept to Remember
*   **Dynamic Programming (DP)**: Problems with overlapping subproblems and optimal substructure are good candidates for DP.
*   **Recursion with Memoization (Top-Down DP)**: Storing results of expensive function calls and returning the cached result when the same inputs occur again.
*   **Base Cases**: Crucial for terminating recursion and defining the simplest scenarios.
*   **State Representation**: Defining what each DP state (e.g., `memo[i]`) represents (minimum coins for amount `i`).

## Common Mistakes
*   **Not handling `Integer.MAX_VALUE` correctly**: Forgetting to check if a subproblem returned `Integer.MAX_VALUE` before adding 1, which can lead to overflow or incorrect minimums.
*   **Incorrect base cases**: Missing the `amount == 0` or `amount < 0` base cases, or not initializing them properly.
*   **Forgetting to memoize**: Not storing the result of a subproblem, leading to recomputation and exponential time complexity.
*   **Off-by-one errors in array indexing**: Using `amount` directly as an index when the array size is `amount + 1`.
*   **Returning -1 prematurely**: Returning -1 when a subproblem is impossible, instead of propagating `Integer.MAX_VALUE`.

## Complexity Analysis
- Time: O(amount * n) - `amount` is the target amount, and `n` is the number of coin denominations. Each state `memo[i]` is computed once, and for each state, we iterate through `n` coins.
- Space: O(amount) - For the memoization array `memo` which stores results for amounts from 0 to `amount`.

## Commented Code
```java
class Solution {
    int[] arr; // Stores the coin denominations for easy access.
    int[] memo; // Memoization table to store results of subproblems. memo[i] will store the minimum coins for amount i.

    public int coinChange(int[] coins, int amount) {
        arr = coins; // Initialize the coin array.
        memo = new int[amount + 1]; // Create memoization array of size amount + 1 to store results for amounts 0 to 'amount'.
        Arrays.fill(memo, -1); // Initialize all entries in memo to -1, indicating they haven't been computed yet.
        int ans = func(amount); // Call the recursive helper function to find the minimum coins for the target amount.
        // If the result 'ans' is Integer.MAX_VALUE, it means the amount cannot be formed by any combination of coins.
        // In this case, return -1 as per problem requirements. Otherwise, return the computed minimum number of coins.
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // Recursive helper function with memoization.
    public int func(int amount) {
        // Base case 1: If the current amount is 0, we need 0 coins.
        if (amount == 0) return 0;
        // Base case 2: If the current amount is negative, it's an invalid state, so return a very large value.
        if (amount < 0) return Integer.MAX_VALUE;
        // Memoization check: If the result for this 'amount' has already been computed, return it directly.
        if (memo[amount] != -1) return memo[amount];

        int ans = Integer.MAX_VALUE; // Initialize 'ans' to a very large value to find the minimum.
        // Iterate through each coin denomination.
        for (int coin : arr) {
            // Recursively call func for the remaining amount after using the current 'coin'.
            int temp = func(amount - coin);
            // If the subproblem (amount - coin) has a valid solution (not Integer.MAX_VALUE).
            if (temp != Integer.MAX_VALUE) {
                // Update 'ans' with the minimum between its current value and the result of the subproblem plus 1 (for the current coin).
                ans = Math.min(ans, temp + 1);
            }
        }
        // Store the computed minimum coins for the current 'amount' in the memoization table before returning.
        return memo[amount] = ans;
    }
}
```

## Interview Tips
*   **Explain the DP approach**: Clearly articulate why DP is suitable (overlapping subproblems, optimal substructure).
*   **Walk through an example**: Use a small example (e.g., `coins = [1, 2, 5]`, `amount = 11`) to trace the recursive calls and memoization.
*   **Discuss base cases and edge cases**: Emphasize the importance of `amount == 0`, `amount < 0`, and the final check for `Integer.MAX_VALUE`.
*   **Consider the bottom-up approach**: Be prepared to discuss or implement the iterative (tabulation) DP solution as well.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify overlapping subproblems and optimal substructure.
- [ ] Implement the recursive solution with memoization.
- [ ] Correctly handle base cases (amount = 0, amount < 0).
- [ ] Properly initialize and use the memoization table.
- [ ] Handle the case where the amount cannot be formed.
- [ ] Analyze time and space complexity.
- [ ] Be able to explain the intuition and algorithm clearly.
- [ ] Consider the iterative (bottom-up) DP approach.

## Similar Problems
*   Coin Change 2 (LeetCode 518) - Number of combinations to make amount.
*   Minimum Path Sum (LeetCode 64) - Similar DP structure.
*   Climbing Stairs (LeetCode 70) - A simpler DP problem.
*   Unbounded Knapsack (GeeksforGeeks) - Conceptually related.

## Tags
`Dynamic Programming` `Breadth-First Search` `Array`
