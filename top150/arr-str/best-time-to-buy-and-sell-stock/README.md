# Best Time To Buy And Sell Stock

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int boughtAt = prices[0];
        for(int price : prices){
            boughtAt = Math.min(boughtAt,price);
            profit = Math.max(profit,price - boughtAt);
        }
        return profit;
    }
}
```

---

---
## Quick Revision
Find the maximum profit achievable by buying and selling a stock once.
Iterate through prices, keeping track of the minimum buy price seen so far and the maximum profit.

## Intuition
The core idea is that to maximize profit, we want to buy at the lowest possible price *before* selling at the highest possible price. As we iterate through the prices, if we encounter a new minimum price, that's a potential new "buy" point. For every price we encounter, we can calculate the potential profit if we had bought at the *current minimum buy price seen so far*. We then update our overall maximum profit if this potential profit is greater.

## Algorithm
1. Initialize `maxProfit` to 0. This will store the maximum profit found.
2. Initialize `minBuyPrice` to the first price in the `prices` array. This represents the lowest price we've seen so far to buy the stock.
3. Iterate through the `prices` array starting from the second element (or even the first, as the logic handles it).
4. For each `currentPrice`:
    a. Update `minBuyPrice`: `minBuyPrice = Math.min(minBuyPrice, currentPrice)`. This ensures we always have the lowest possible buying price encountered up to this point.
    b. Calculate potential profit: `potentialProfit = currentPrice - minBuyPrice`.
    c. Update `maxProfit`: `maxProfit = Math.max(maxProfit, potentialProfit)`. This keeps track of the highest profit achievable.
5. Return `maxProfit`.

## Concept to Remember
*   **Greedy Approach**: At each step, we make the locally optimal choice (finding the minimum buy price and calculating profit against it) which leads to the globally optimal solution.
*   **Single Pass Iteration**: The problem can be solved efficiently by iterating through the data only once.
*   **State Management**: Keeping track of the minimum buy price is crucial state information that needs to be maintained throughout the iteration.

## Common Mistakes
*   **Sorting the array**: This problem requires maintaining the order of prices to ensure we buy *before* we sell. Sorting destroys this order.
*   **Calculating profit based on the first price only**: Forgetting to update the `minBuyPrice` as we iterate can lead to suboptimal profit calculations.
*   **Handling edge cases**: Not considering an empty or single-element `prices` array (though the provided solution handles this implicitly).
*   **Incorrectly updating `maxProfit`**: Forgetting to use `Math.max` to ensure we are always storing the *highest* profit.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the `prices` array once.
- Space: O(1) - reason: We only use a few constant extra variables (`profit`, `boughtAt`).

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize the maximum profit found so far to 0.
        int profit = 0;
        // Initialize the minimum price at which we could have bought the stock.
        // We start by assuming we bought at the very first price.
        int boughtAt = prices[0];
        // Iterate through each price in the given array of prices.
        for(int price : prices){
            // Update the minimum buying price seen so far.
            // If the current price is lower than our current 'boughtAt' price,
            // it becomes our new potential best buying point.
            boughtAt = Math.min(boughtAt,price);
            // Calculate the potential profit if we sell at the current 'price'
            // having bought at the 'boughtAt' price (the minimum seen so far).
            // Then, update the overall maximum profit if this potential profit is greater.
            profit = Math.max(profit,price - boughtAt);
        }
        // Return the maximum profit that could have been achieved.
        return profit;
    }
}
```

## Interview Tips
*   **Explain your thought process**: Clearly articulate the greedy strategy and why it works.
*   **Walk through an example**: Use a small `prices` array (e.g., `[7,1,5,3,6,4]`) to demonstrate how your algorithm tracks `minBuyPrice` and `maxProfit`.
*   **Discuss edge cases**: Mention what happens with an empty array or an array with only one element.
*   **Ask clarifying questions**: If unsure about constraints (e.g., can prices be negative?), ask the interviewer.

## Revision Checklist
- [ ] Understand the problem: buy low, sell high, only once.
- [ ] Identify the greedy strategy: track min buy price.
- [ ] Implement the single-pass iteration.
- [ ] Handle initialization correctly.
- [ ] Verify complexity.
- [ ] Practice explaining the solution.

## Similar Problems
*   Best Time to Buy and Sell Stock II
*   Best Time to Buy and Sell Stock III
*   Best Time to Buy and Sell Stock IV

## Tags
`Array` `Greedy`
