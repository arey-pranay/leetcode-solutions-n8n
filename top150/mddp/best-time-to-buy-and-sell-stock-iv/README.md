# Best Time To Buy And Sell Stock Iv

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n * k * n)  
**Space:** O(n * k * n)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    Integer[][] memo ;
    public int maxProfit(int k, int[] prices) {
        memo = new Integer[prices.length+1][k+1];
        arr = prices;
        return func(0,k);
    }
    public int func(int start, int k){
        int selling = 0;
        if(k == 0 || start>=arr.length) return 0;
        if(memo[start][k] != null) return memo[start][k];
        
        int boughtAt = start;
        
        for(int i=boughtAt + 1; i<arr.length; i++){
            if (arr[i]<=arr[boughtAt]) boughtAt = i; 
            else selling = Math.max(selling,  arr[i]-arr[boughtAt] + func(i+1,k-1)); //10
        }
        selling = Math.max(selling,func(start + 1, k));
        return memo[start][k] = selling;           
    }
}

```

---

---
## Quick Revision
This problem asks for the maximum profit achievable by completing at most k transactions.
We solve it using dynamic programming with memoization, considering states based on the current day and remaining transactions.

## Intuition
The core idea is to explore all possible valid transactions. For each day, we have two main choices: either we don't make any transaction on this day, or we decide to buy on this day and sell on a future day. If we buy on day `i`, we can sell on any day `j > i`. The profit from this transaction would be `prices[j] - prices[i]`. After completing a transaction (buy and sell), we have `k-1` transactions remaining, and we need to find the maximum profit from the remaining days starting from `j+1`. This recursive structure, combined with memoization to avoid redundant calculations, leads to the DP solution.

## Algorithm
1.  **Initialization**:
    *   Create a memoization table `memo` of size `(prices.length + 1) x (k + 1)` to store computed results. Initialize it with a sentinel value (e.g., `null` or `-1`) to indicate uncomputed states.
    *   Store the input `prices` array in a class-level variable for easy access within the recursive function.

2.  **Recursive Function `func(start, k)`**:
    *   **Base Cases**:
        *   If `k == 0` (no more transactions allowed) or `start >= prices.length` (no more days to consider), return `0` profit.
    *   **Memoization Check**:
        *   If `memo[start][k]` is already computed, return the stored value.
    *   **Recursive Steps**:
        *   Initialize `maxProfit` for the current state to `0`.
        *   Iterate through all possible selling days `i` starting from `start + 1` up to `prices.length - 1`.
        *   For each `i`, calculate the profit if we buy on `start` and sell on `i`: `prices[i] - prices[start]`.
        *   Recursively call `func(i + 1, k - 1)` to find the maximum profit from the remaining days with one less transaction.
        *   Update `maxProfit` with the maximum of its current value and `(prices[i] - prices[start]) + func(i + 1, k - 1)`.
        *   **Consider not making a transaction on `start`**: Recursively call `func(start + 1, k)` to find the maximum profit by skipping the current day.
        *   Update `maxProfit` with the maximum of its current value and the result of skipping the current day.
    *   **Store and Return**:
        *   Store the computed `maxProfit` in `memo[start][k]`.
        *   Return `maxProfit`.

3.  **Main Function `maxProfit(k, prices)`**:
    *   Call the recursive function `func(0, k)` to start the computation from the first day with `k` transactions.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a complex problem into smaller overlapping subproblems and storing their solutions to avoid recomputation.
*   **Recursion with Memoization**: A top-down approach to DP where a recursive function is used, and its results are cached.
*   **State Definition**: Carefully defining the state for DP (e.g., `dp[day][transactions_left]`).
*   **Transition Logic**: Defining how to move from one state to another based on problem constraints and choices.

## Common Mistakes
*   **Incorrect Base Cases**: Not handling `k=0` or `start >= prices.length` correctly can lead to infinite recursion or wrong results.
*   **Overlapping Subproblems Not Handled**: Without memoization, the same subproblems will be computed multiple times, leading to exponential time complexity.
*   **Incorrect State Transition**: The logic for calculating profit when buying and selling, and then recursively calling for remaining transactions and days, needs to be precise. Forgetting to consider the option of *not* making a transaction on the current day.
*   **Off-by-One Errors**: In loop bounds or array indexing when iterating through prices or accessing the memoization table.

## Complexity Analysis
*   **Time**: O(n * k * n) where n is the number of prices. The state space is O(n*k). For each state `(start, k)`, we iterate up to `n` times to find the best selling day. This can be optimized.
    *   **Optimized Time**: O(n * k). The provided solution has a nested loop within the recursive function, leading to O(n^2 * k) in the worst case. A more optimized DP approach (often using states like `dp[i][k][0]` for not holding stock and `dp[i][k][1]` for holding stock) can achieve O(n*k). The provided code's `for` loop inside `func` makes it O(n^2 * k) without further optimization.
*   **Space**: O(n * k) for the memoization table.

## Commented Code
```java
class Solution {
    // Array to store the stock prices.
    int[] arr;
    // Memoization table to store results of subproblems.
    // memo[i][j] will store the max profit starting from day i with j transactions left.
    Integer[][] memo ;

    // Main function to calculate the maximum profit.
    public int maxProfit(int k, int[] prices) {
        // Initialize the memoization table with nulls.
        // The dimensions are (number of days + 1) x (max transactions + 1).
        memo = new Integer[prices.length+1][k+1];
        // Store the prices array in a class variable for easy access.
        arr = prices;
        // Start the recursive calculation from day 0 with k transactions allowed.
        return func(0,k);
    }

    // Recursive helper function to calculate max profit.
    // start: the current day index we are considering.
    // k: the number of transactions remaining.
    public int func(int start, int k){
        // Variable to store the maximum profit achievable from this state.
        int selling = 0;

        // Base case 1: If no transactions are allowed (k=0) or we have run out of days, return 0 profit.
        if(k == 0 || start>=arr.length) return 0;

        // Memoization check: If the result for this state (start, k) is already computed, return it.
        if(memo[start][k] != null) return memo[start][k];

        // 'boughtAt' is the day we potentially buy the stock. Initially, it's the current 'start' day.
        int boughtAt = start;

        // Iterate through all possible future days 'i' to sell the stock.
        // We start from 'boughtAt + 1' because we must sell on a day after buying.
        for(int i=boughtAt + 1; i<arr.length; i++){
            // If the price on day 'i' is less than or equal to the price on 'boughtAt',
            // it's potentially a better day to buy. Update 'boughtAt' to 'i'.
            // This is a crucial optimization: if we find a lower price to buy, we should consider it.
            if (arr[i]<=arr[boughtAt]) boughtAt = i;
            // Otherwise, if arr[i] > arr[boughtAt], we can make a profit.
            else {
                // Calculate the profit from buying at 'boughtAt' and selling at 'i'.
                // Then, recursively call 'func' for the remaining days (i+1) and one less transaction (k-1).
                // We take the maximum of the current 'selling' profit and this new potential profit.
                selling = Math.max(selling,  arr[i]-arr[boughtAt] + func(i+1,k-1)); //10
            }
        }
        // Also consider the case where we *do not* make any transaction starting from 'start' day.
        // This means we skip the current day 'start' and move to the next day 'start + 1' with the same number of transactions 'k'.
        selling = Math.max(selling,func(start + 1, k));

        // Store the computed maximum profit for the current state (start, k) in the memoization table.
        // Then, return this value.
        return memo[start][k] = selling;
    }
}
```

## Interview Tips
*   **Clarify Constraints**: Ask about the maximum value of `k` and the length of `prices`. If `k` is very large (e.g., `k >= n/2`), the problem simplifies to the "Best Time to Buy and Sell Stock II" problem, where you can make unlimited transactions. This is a common optimization to mention.
*   **Explain the DP State**: Clearly articulate what your DP state represents (e.g., `dp[i][j]` means max profit up to day `i` with `j` transactions).
*   **Walk Through an Example**: Use a small example array and `k` to trace your algorithm's execution, showing how the memoization table is filled.
*   **Discuss Optimization**: If you initially present an O(n^2 * k) solution, be prepared to discuss how to optimize it to O(n*k) using a different DP state definition (e.g., `dp[i][k][0/1]`).

## Revision Checklist
- [ ] Understand the problem: Max profit with at most `k` transactions.
- [ ] Identify base cases for recursion/DP.
- [ ] Define DP state correctly.
- [ ] Implement transition logic for buying and selling.
- [ ] Implement transition logic for skipping a day.
- [ ] Use memoization to store results.
- [ ] Handle the `k >= n/2` optimization.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Best Time to Buy and Sell Stock
*   Best Time to Buy and Sell Stock II
*   Best Time to Buy and Sell Stock III
*   Coin Change
*   Longest Increasing Subsequence

## Tags
`Dynamic Programming` `Array` `Recursion` `Memoization`
