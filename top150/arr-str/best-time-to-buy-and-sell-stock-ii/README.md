# Best Time To Buy And Sell Stock Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int boughtAt = prices[0];
        int ans = 0;
        for(int price : prices){
            boughtAt = Math.min(boughtAt,price);
            int profit = price-boughtAt;
            if(profit> 0){
                ans += profit;
                boughtAt = price;
            }
        }
        return ans;
    }
}
```

---

---
## Quick Revision
You can buy and sell stock multiple times to maximize profit.
The solution involves accumulating profit from every upward price trend.

## Intuition
The key insight is that we can buy and sell on the same day. If the price goes up tomorrow, we can effectively "sell" today at the current price and "buy" again at the same price to capture that day's profit. This means we can simply sum up all the positive differences between consecutive days. If `prices[i] > prices[i-1]`, we can make a profit of `prices[i] - prices[i-1]`. The provided solution cleverly achieves this by always trying to buy at the lowest point seen so far and adding any positive profit to the total, then resetting the "buy" point to the current price if a profit was made. This effectively captures all upward trends.

## Algorithm
1. Initialize `maxProfit` to 0.
2. Iterate through the `prices` array starting from the second element (index 1).
3. For each `price` at index `i`, compare it with the `price` at index `i-1`.
4. If `prices[i]` is greater than `prices[i-1]`, it means there's a potential profit. Add the difference (`prices[i] - prices[i-1]`) to `maxProfit`.
5. Return `maxProfit`.

*Note: The provided solution uses a slightly different but equivalent approach. It tracks the `boughtAt` price and accumulates profit whenever the current price is higher than the `boughtAt` price, resetting `boughtAt` to the current price if a profit is realized. This implicitly captures the same daily gains.*

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice at each step leads to a globally optimal solution.
*   **Dynamic Programming (Implicit):** While not explicitly using a DP table, the problem can be viewed as a simplified DP where the state depends only on the previous day's price.
*   **Iterative Improvement:** The solution iteratively builds up the maximum profit by considering each possible transaction.

## Common Mistakes
*   **Overcomplicating the logic:** Trying to track multiple buy/sell points simultaneously when a simpler greedy approach suffices.
*   **Not handling edge cases:** Forgetting to consider arrays with only one element or arrays with no profitable transactions.
*   **Incorrectly resetting buy/sell points:** If using a buy/sell tracking mechanism, ensuring it's reset correctly after a profitable transaction.
*   **Missing the "buy and sell on the same day" implication:** This is crucial for understanding why summing consecutive positive differences works.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the `prices` array once.
- Space: O(1) - reason: We only use a few constant extra variables.

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize the lowest price seen so far to the first day's price.
        int boughtAt = prices[0];
        // Initialize the total accumulated profit to 0.
        int ans = 0;
        // Iterate through each price in the prices array.
        for(int price : prices){
            // Update boughtAt to be the minimum of its current value and the current price.
            // This ensures we always consider buying at the lowest possible point encountered so far.
            boughtAt = Math.min(boughtAt,price);
            // Calculate the potential profit if we were to sell at the current price, having bought at boughtAt.
            int profit = price-boughtAt;
            // If the potential profit is positive, it means we can make money.
            if(profit> 0){
                // Add this profit to our total accumulated profit.
                ans += profit;
                // Crucially, reset boughtAt to the current price. This is because we've "realized" the profit,
                // and for future calculations, we can consider buying again at this current price.
                // This effectively allows for multiple transactions and captures every upward trend.
                boughtAt = price;
            }
        }
        // Return the total maximum profit that can be achieved.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the greedy strategy: "I can buy and sell on the same day, so I'll just take every opportunity to profit from an increase."
*   Walk through an example: Use `[7,1,5,3,6,4]` and show how the profit accumulates.
*   Discuss the alternative approach of summing `prices[i] - prices[i-1]` for all `prices[i] > prices[i-1]` and explain why it's equivalent.
*   Be prepared to discuss the time and space complexity.

## Revision Checklist
- [ ] Understand the problem statement: multiple transactions allowed.
- [ ] Grasp the greedy intuition: sum of all positive price differences.
- [ ] Implement the O(n) time, O(1) space solution.
- [ ] Trace the algorithm with an example.
- [ ] Be ready to explain the "buy and sell on the same day" concept.

## Similar Problems
Best Time to Buy and Sell Stock
Best Time to Buy and Sell Stock III
Best Time to Buy and Sell Stock IV

## Tags
`Array` `Greedy`
