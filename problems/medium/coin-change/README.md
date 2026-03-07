# Coin Change

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Breadth-First Search`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    public int coinChange(int[] a, int k) {
        int[] f = new int[k + 1];
        java.util.Arrays.fill(f, (int)1e9);
        f[0] = 0;
        
        for (int x : a) {
            for (int i = x; i <= k; i++) {
                if (f[i - x] + 1 < f[i]) {
                    f[i] = f[i - x] + 1;
                }
            }
        }
        
        int ans = f[k];
        if (ans == (int)1e9) {
            ans = -1;
        }
        return ans;
    }
}
```

---

---
## Problem Summary
Given an array of coin denominations `coins` and a target amount `amount`, find the minimum number of coins required to make up that amount. If the amount cannot be made up by any combination of the coins, return -1. Assume an infinite supply of each coin denomination.

## Approach and Intuition
This problem can be solved using dynamic programming. The core idea is to build up a solution for smaller amounts and use those solutions to solve for larger amounts.

Let `dp[i]` represent the minimum number of coins required to make up the amount `i`.

1.  **Initialization**:
    *   `dp[0]` should be 0, as 0 coins are needed to make up an amount of 0.
    *   For all other amounts `i` from 1 to `amount`, initialize `dp[i]` to a very large value (e.g., `infinity` or `k+1` if `k` is the target amount, as we can't use more than `k` coins of denomination 1 to make `k`). This large value signifies that the amount is currently unreachable.

2.  **Iteration**:
    *   We iterate through each coin denomination `coin` in the `coins` array.
    *   For each `coin`, we iterate through all possible amounts `i` from `coin` up to the target `amount`.
    *   For each amount `i`, we consider if we can form it by using the current `coin`. If we use the current `coin`, the remaining amount we need to make up is `i - coin`.
    *   The number of coins required to make up `i` using the current `coin` would be `dp[i - coin] + 1` (1 for the current coin, plus the minimum coins needed for the remaining amount).
    *   We update `dp[i]` with the minimum of its current value and `dp[i - coin] + 1`. This ensures we always store the minimum number of coins.
    *   The recurrence relation is: `dp[i] = min(dp[i], dp[i - coin] + 1)` for `i >= coin`.

3.  **Result**:
    *   After iterating through all coins and all amounts, `dp[amount]` will hold the minimum number of coins required to make up the target `amount`.
    *   If `dp[amount]` is still the initial large value, it means the `amount` cannot be made up, so we return -1. Otherwise, we return `dp[amount]`.

The provided code uses `(int)1e9` as the large value, which is a common practice for representing infinity in competitive programming. It also uses `k` (the target amount) as the upper bound for the DP array size, which is correct.

## Complexity Analysis
*   **Time**: O(amount * n)
    *   We have a nested loop. The outer loop iterates through each coin denomination (n coins). The inner loop iterates from the coin's value up to the target amount. In the worst case, the inner loop runs `amount` times for each coin.
*   **Space**: O(amount)
    *   We use a DP array of size `amount + 1` to store the minimum coins needed for each amount from 0 to `amount`.

## Code Walkthrough
```java
class Solution {
    public int coinChange(int[] a, int k) { // a: coins array, k: target amount
        // Initialize DP array. f[i] will store the minimum coins to make amount i.
        // Size k+1 to cover amounts from 0 to k.
        // Initialize with a large value (1e9) representing infinity, meaning unreachable.
        int[] f = new int[k + 1];
        java.util.Arrays.fill(f, (int)1e9);

        // Base case: 0 coins are needed to make an amount of 0.
        f[0] = 0;

        // Iterate through each coin denomination.
        for (int x : a) { // x is the current coin denomination
            // For each coin, iterate through amounts from the coin's value up to the target amount.
            // We start from 'x' because we can only use coin 'x' to form amounts >= x.
            for (int i = x; i <= k; i++) {
                // If the amount (i - x) is reachable (f[i - x] is not infinity),
                // then we can potentially form amount 'i' by adding coin 'x'.
                // The number of coins would be f[i - x] + 1.
                // We update f[i] if this new way of forming 'i' uses fewer coins.
                if (f[i - x] + 1 < f[i]) {
                    f[i] = f[i - x] + 1;
                }
            }
        }

        // After processing all coins and amounts, f[k] holds the minimum coins for the target amount.
        int ans = f[k];

        // If f[k] is still the initial large value, it means the amount 'k' is unreachable.
        // In this case, return -1 as per problem statement.
        if (ans == (int)1e9) {
            ans = -1;
        }
        // Otherwise, return the calculated minimum number of coins.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the DP State**: Clearly define what `dp[i]` represents.
*   **Base Case**: Emphasize the importance of `dp[0] = 0`.
*   **Recurrence Relation**: Explain how `dp[i]` is derived from previous states (`dp[i - coin]`).
*   **Initialization**: Discuss why initializing with a large value is crucial for the `min` operation.
*   **Order of Loops**: Explain why the outer loop iterates through coins and the inner loop through amounts. This order ensures that we consider using each coin multiple times for a given amount (unbounded knapsack-like behavior). If the loops were swapped, it would imply using each coin at most once.
*   **Edge Case**: Clearly state how the unreachable case (returning -1) is handled.
*   **Ask Clarifying Questions**: "Can the coin denominations be zero or negative?" (Usually no, but good to confirm). "Is the target amount always non-negative?"

## Optimization and Alternatives
*   **Space Optimization**: For this specific problem, space optimization is not straightforward because the calculation of `dp[i]` depends on `dp[i - coin]`, which could be many steps back. Standard DP is generally used.
*   **BFS Approach**: This problem can also be viewed as a shortest path problem on a graph. Each amount is a node, and an edge exists from amount `u` to `v` if `v = u + coin` for some coin denomination. The weight of each edge is 1. We want to find the shortest path from 0 to `amount`. Breadth-First Search (BFS) can be used.
    *   **BFS Intuition**: Start with amount 0 (0 coins). In the first step, explore all amounts reachable with 1 coin (i.e., `0 + coin` for all coins). In the second step, explore all amounts reachable with 2 coins, and so on. Use a queue and a `visited` array (or a distance array similar to DP) to keep track of visited amounts and the minimum coins to reach them.
    *   **BFS Complexity**:
        *   Time: O(amount * n) in the worst case, as each amount up to `amount` might be enqueued and dequeued once, and for each, we iterate through `n` coins.
        *   Space: O(amount) for the queue and visited/distance array.

## Revision Checklist
*   [ ] Understand the problem statement thoroughly.
*   [ ] Identify the subproblems: minimum coins for smaller amounts.
*   [ ] Define the DP state: `dp[i]` = min coins for amount `i`.
*   [ ] Determine the base case: `dp[0] = 0`.
*   [ ] Formulate the recurrence relation: `dp[i] = min(dp[i], dp[i - coin] + 1)`.
*   [ ] Handle initialization with a large value for unreachable states.
*   [ ] Implement the nested loops correctly (coins outer, amounts inner).
*   [ ] Handle the final result and the -1 case.
*   [ ] Analyze time and space complexity.
*   [ ] Consider alternative approaches like BFS.

## Similar Problems
*   Coin Change 2 (LeetCode 518) - Count the number of combinations to make up the amount.
*   Unbounded Knapsack (similar DP structure).
*   Minimum Path Sum (LeetCode 64) - Another DP problem with a grid.
*   Shortest Path in Binary Matrix (LeetCode 1091) - Can be solved with BFS.

## Tags
`Dynamic Programming` `Array` `Breadth-First Search`

## My Notes
extra commmentpddp
