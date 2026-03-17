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
        int ans = 0;
        int bp = prices[0];
        for(int i : prices){
           if(i > bp) ans = Math.max(ans,i-bp);
           else bp = i;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Find the maximum profit achievable by buying and selling a stock once.
Iterate through prices, keeping track of the minimum buy price seen so far and the maximum profit.

## Intuition
The core idea is that to maximize profit, we want to buy at the lowest possible price and sell at the highest possible price *after* that lowest buy price. As we iterate through the prices, we can maintain a running "minimum buy price" encountered so far. For each new price, we check if selling at this price (given the current minimum buy price) would yield a greater profit than our current maximum profit. If the current price is lower than our minimum buy price, it becomes our new potential best buy point.

## Algorithm
1. Initialize `maxProfit` to 0. This will store our maximum profit found.
2. Initialize `minBuyPrice` to the first element of the `prices` array. This represents the lowest price we've seen so far to buy the stock.
3. Iterate through the `prices` array starting from the second element (or even the first, as the logic handles it).
4. For each `currentPrice`:
    a. Calculate the potential profit if we sell at `currentPrice` and bought at `minBuyPrice`: `potentialProfit = currentPrice - minBuyPrice`.
    b. Update `maxProfit` to be the maximum of `maxProfit` and `potentialProfit`. This ensures we always keep track of the highest profit found.
    c. If `currentPrice` is less than `minBuyPrice`, update `minBuyPrice` to `currentPrice`. This is because a new lower buy price might lead to a greater profit later.
5. After iterating through all prices, return `maxProfit`.

## Concept to Remember
*   **Greedy Approach:** At each step, we make the locally optimal choice (updating min buy price or max profit) which leads to the globally optimal solution.
*   **Single Pass Iteration:** The problem can be solved efficiently by iterating through the data only once.
*   **State Management:** Keeping track of the minimum buy price encountered so far is crucial state information.

## Common Mistakes
*   **Sorting the array:** This is incorrect because the order of prices matters (you can only sell after you buy).
*   **Calculating profit for every pair:** This leads to an O(n^2) solution, which is too slow for larger inputs.
*   **Not handling the case where prices are strictly decreasing:** The algorithm should correctly return 0 profit in such scenarios.
*   **Initializing `minBuyPrice` incorrectly:** If not initialized to the first element or a very large number, it can lead to incorrect profit calculations.

## Complexity Analysis
*   **Time:** O(n) - reason: We iterate through the `prices` array exactly once.
*   **Space:** O(1) - reason: We only use a few constant extra variables (`maxProfit`, `minBuyPrice`).

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize maxProfit to 0, as no transactions mean no profit.
        int ans = 0;
        // Initialize bp (buy price) to the first day's price. This is our initial minimum buy price.
        int bp = prices[0];
        // Iterate through each price in the prices array.
        for(int i : prices){
           // If the current price 'i' is greater than our current minimum buy price 'bp',
           // it means we can potentially make a profit by selling at 'i'.
           if(i > bp)
               // Update 'ans' (maxProfit) to be the maximum of its current value and the profit from selling at 'i' (i - bp).
               ans = Math.max(ans,i-bp);
           // If the current price 'i' is less than or equal to our current minimum buy price 'bp',
           // it means this 'i' is a new potential lowest buy price.
           else
               // Update 'bp' to the current price 'i', as it's a better (lower) price to buy.
               bp = i;
        }
        // Return the maximum profit found after iterating through all prices.
        return ans;
    }
}
```

## Interview Tips
*   Explain your greedy approach clearly: "I'm trying to find the lowest buy point seen so far and then calculate the maximum possible profit by selling at any subsequent higher price."
*   Walk through an example: "Let's say prices are [7, 1, 5, 3, 6, 4].
    *   Start: `ans = 0`, `bp = 7`.
    *   `i = 1`: `1 < 7`, so `bp = 1`. `ans` remains 0.
    *   `i = 5`: `5 > 1`, `ans = max(0, 5-1) = 4`. `bp` remains 1.
    *   `i = 3`: `3 > 1`, `ans = max(4, 3-1) = 4`. `bp` remains 1.
    *   `i = 6`: `6 > 1`, `ans = max(4, 6-1) = 5`. `bp` remains 1.
    *   `i = 4`: `4 > 1`, `ans = max(5, 4-1) = 5`. `bp` remains 1.
    *   Final `ans = 5`."
*   Be prepared to discuss why sorting is not a valid approach.
*   Mention the time and space complexity and justify them.

## Revision Checklist
- [ ] Understand the problem: find max profit from one buy/sell.
- [ ] Identify the greedy strategy: track min buy price.
- [ ] Implement the single pass iteration.
- [ ] Handle edge cases (e.g., decreasing prices).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its trade-offs.

## Similar Problems
*   Best Time to Buy and Sell Stock II (multiple transactions)
*   Best Time to Buy and Sell Stock III (at most two transactions)
*   Best Time to Buy and Sell Stock IV (at most k transactions)

## Tags
`Array` `Greedy`

## My Notes
classic question
