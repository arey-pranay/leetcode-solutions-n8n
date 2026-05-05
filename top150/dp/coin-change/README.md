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
    int[] memo;
    int[] arr;
    public int coinChange(int[] coins, int amount){
        memo = new int[amount+1];
        Arrays.fill(memo,-1);
        arr = coins;
        
        int ans = func(amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
        
    }
    public int func(int amount){
        if(amount==0) return 0;
        if(amount<0) return Integer.MAX_VALUE;
        if(memo[amount] != -1) return memo[amount];
        
        int min = Integer.MAX_VALUE;
        for(int coin : arr){
            int res = func(amount-coin);
            if(res == Integer.MAX_VALUE) continue;
            min = Math.min(min,res+1);
        }
        return memo[amount] = min;
    }
}
```

---

---
## Quick Revision
Given an array of coin denominations and a target amount, find the minimum number of coins to make up that amount.
This is solved using dynamic programming (top-down with memoization or bottom-up).

## Intuition
The core idea is to break down the problem into smaller subproblems. To find the minimum coins for amount `A`, we can consider using each coin `c` from the given denominations. If we use coin `c`, the remaining amount is `A - c`. The minimum coins for `A` would then be 1 (for coin `c`) plus the minimum coins needed for `A - c`. We try this for all possible coins and take the minimum. This recursive structure with overlapping subproblems naturally leads to dynamic programming. The "aha moment" is realizing that the solution for a larger amount depends on the solutions for smaller amounts.

## Algorithm
1. Initialize a memoization array (e.g., `memo`) of size `amount + 1` with a sentinel value (e.g., -1) to indicate uncomputed states.
2. Initialize a variable `minCoins` to `Integer.MAX_VALUE` to store the minimum number of coins found so far for the current amount.
3. Define a recursive helper function `solve(currentAmount)`:
    a. Base Case 1: If `currentAmount` is 0, return 0 (no coins needed).
    b. Base Case 2: If `currentAmount` is less than 0, return `Integer.MAX_VALUE` (invalid state, cannot form this amount).
    c. Memoization Check: If `memo[currentAmount]` is not the sentinel value, return `memo[currentAmount]` (already computed).
    d. Iterate through each `coin` in the `coins` array:
        i. Recursively call `solve(currentAmount - coin)`.
        ii. If the result of the recursive call is not `Integer.MAX_VALUE` (meaning it's possible to form `currentAmount - coin`), update `minCoins = Math.min(minCoins, result + 1)`.
    e. Store the computed `minCoins` in `memo[currentAmount]` and return it.
4. Call the helper function `solve(amount)` to get the result.
5. If the result is `Integer.MAX_VALUE`, return -1 (impossible to form the amount); otherwise, return the result.

## Concept to Remember
*   **Dynamic Programming:** Breaking down a complex problem into simpler overlapping subproblems and storing their solutions to avoid recomputation.
*   **Recursion with Memoization (Top-Down DP):** A recursive approach where results of function calls are cached to speed up computation.
*   **Base Cases:** Crucial for terminating recursion and defining the simplest scenarios.
*   **State Representation:** How to define the subproblem (e.g., `dp[i]` represents the minimum coins for amount `i`).

## Common Mistakes
*   **Incorrect Base Cases:** Not handling `amount == 0` or `amount < 0` properly can lead to infinite recursion or incorrect results.
*   **Not Handling Impossible Amounts:** Failing to return a specific value (like `Integer.MAX_VALUE` or -1) when an amount cannot be formed.
*   **Integer Overflow:** Using `Integer.MAX_VALUE` for intermediate calculations and not checking for it before adding 1, which could wrap around.
*   **Forgetting Memoization:** Implementing a purely recursive solution without storing results, leading to exponential time complexity.
*   **Off-by-One Errors:** Incorrect array indexing or loop bounds.

## Complexity Analysis
*   Time: O(amount * n) - reason: Each state (amount from 0 to `amount`) is computed once. For each state, we iterate through `n` coins.
*   Space: O(amount) - reason: For the memoization array to store results for each amount from 0 to `amount`.

## Commented Code
```java
class Solution {
    // memo array to store the minimum coins needed for each amount from 0 to amount.
    // Initialized with -1 to indicate that the value has not been computed yet.
    int[] memo;
    // arr will store the input coins array for easy access within the helper function.
    int[] arr;

    // Main function to initiate the coin change calculation.
    public int coinChange(int[] coins, int amount){
        // Initialize the memoization array with size amount + 1.
        memo = new int[amount+1];
        // Fill the memo array with -1, signifying that no subproblem has been solved yet.
        Arrays.fill(memo,-1);
        // Store the coins array in a class-level variable for accessibility in the helper function.
        arr = coins;
        
        // Call the recursive helper function to find the minimum coins for the target amount.
        int ans = func(amount);
        // If the result is Integer.MAX_VALUE, it means the amount cannot be formed by any combination of coins.
        // In this case, return -1 as per problem requirements. Otherwise, return the computed minimum number of coins.
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // Recursive helper function to compute the minimum coins for a given amount.
    public int func(int amount){
        // Base case 1: If the amount is 0, no coins are needed. Return 0.
        if(amount==0) return 0;
        // Base case 2: If the amount is negative, it's an invalid state. Return a very large value to signify impossibility.
        if(amount<0) return Integer.MAX_VALUE;
        // Memoization check: If the result for this amount has already been computed and stored in memo, return it directly.
        if(memo[amount] != -1) return memo[amount];
        
        // Initialize min to a very large value. This will store the minimum coins found for the current 'amount'.
        int min = Integer.MAX_VALUE;
        // Iterate through each coin denomination available.
        for(int coin : arr){
            // Recursively call func for the remaining amount after using the current coin.
            int res = func(amount-coin);
            // If the recursive call returned Integer.MAX_VALUE, it means the subproblem (amount-coin) is impossible to solve.
            // So, we skip this coin and continue to the next one.
            if(res == Integer.MAX_VALUE) continue;
            // If the subproblem was solvable, update 'min' with the minimum between the current 'min' and (result of subproblem + 1 for the current coin).
            min = Math.min(min,res+1);
        }
        // Store the computed minimum coins for the current 'amount' in the memoization array before returning.
        return memo[amount] = min;
    }
}
```

## Interview Tips
*   **Explain the DP approach:** Clearly articulate why DP is suitable and how you're breaking down the problem.
*   **Walk through an example:** Use a small `coins` array and `amount` to trace the execution of your algorithm, especially the memoization.
*   **Discuss base cases and edge cases:** Emphasize how you handle `amount = 0`, `amount < 0`, and cases where the amount is impossible to form.
*   **Consider both top-down and bottom-up:** Be prepared to discuss or implement the bottom-up iterative DP solution as well.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify overlapping subproblems and optimal substructure.
- [ ] Implement the recursive solution with memoization.
- [ ] Define correct base cases for recursion.
- [ ] Handle impossible amounts correctly.
- [ ] Analyze time and space complexity.
- [ ] Consider the iterative (bottom-up) DP approach.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
*   Coin Change 2
*   Minimum Path Sum
*   Unbounded Knapsack
*   Rod Cutting

## Tags
`Array` `Dynamic Programming` `Breadth-First Search`
