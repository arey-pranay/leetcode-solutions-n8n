# Best Time To Buy And Sell Stock Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
        public int maxProfit(int[] prices)  {
        // 2 transactions => buy1 sell1, buy2 sell2. 
        // buy ke liye udhaar, sell ke liye money increment, we need to maximise all. because -1 > -20. 
        // for buy1, we do not have money currently, so that we count as negative. Math.max(buy1, -prices[i]) buy1 is neg
        // for sell1, we would get a revenue of prices[i], but we need to pay back buy1, so net is Math.max(sell1, prices[i]+buy1)
        // for buy2, we currently have the sell1 money, we need only sell1 - prices[i] amount, so Math.max(buy2,sell1-prices[i])
        // for sell2, we would get prices[i] in wallet, but we need to pay back the buy2 amount. Math.max(sell2, prices[i]+buy2)
        // so finally we would end up with sell2 amount of net net profit, considering best cases as we go forward.
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE, sell1 = 0, sell2 = 0;
        for(int price : prices){
         buy1 = Math.max(buy1, -price);
         sell1 = Math.max(sell1, price+buy1);
         buy2 = Math.max(buy2,sell1-price);
         sell2 = Math.max(sell2, price+buy2);
        }
        return sell2;   
    }
}

// [3,3,5,0,0,3,1,4]
// b1 = 3 , 3
// s1 = 0,  2
// b2 = 3
// s2 = 0

// class Solution {
//     int[] memo;
//     public int maxProfit(int[] prices) {
//         memo = new int[prices.length];
//         Arrays.fill(memo,-1);
//         return func(prices,0,2);
//     }
//     public int func(int[] arr, int i, int left){
//         if(left == 0) return 0;
//         int boughtAt = arr[i];
//         int max = 0;
//         if(memo[i] != -1) return memo[i];
//         for(int j=i+1;j<arr.length;j++){
//             if(arr[j] > boughtAt){
//                 int profit = arr[j]-boughtAt;
//                 int sold= profit + func(arr, j, left-1);
//                 max = Math.max(max,sold);
//             } else {
//                 boughtAt = arr[j];
//             }
//         }
//         return memo[i] = max;
//     }
// }
```

---

---
## Quick Revision
Find the maximum profit achievable with at most two stock transactions.
Track the states of buying and selling for up to two transactions iteratively in a single pass.

## Intuition
The problem restricts us to at most two transactions. We can model this state machine using four variables representing the best financial outcome at each distinct phase: `buy1` (cost after first purchase), `sell1` (profit after first sale), `buy2` (remaining cash after second purchase), and `sell2` (total profit after second sale). As we iterate through prices, we greedily update these states. The "aha moment" is realizing we can simulate holding and releasing capital for two overlapping or separate transactions simultaneously without building a heavy DP table.

## Algorithm
1. Initialize four variables: `buy1` and `buy2` to negative infinity (`Integer.MIN_VALUE`), and `sell1` and `sell2` to `0`.
2. Iterate through each `price` in the `prices` array.
3. Update `buy1` to be the maximum of its previous value or `-price` (representing spending money to buy the first stock).
4. Update `sell1` to be the maximum of its previous value or `price + buy1` (representing selling the first stock and keeping the profit).
5. Update `buy2` to be the maximum of its previous value or `sell1 - price` (representing using earnings from the first sale to buy the second stock).
6. Update `sell2` to be the maximum of its previous value or `price + buy2` (representing selling the second stock and securing total profit).
7. Return `sell2` after the loop finishes, as it holds the maximum profit from up to two transactions.

## Concept to Remember
- **State Machine DP:** Breaking down a complex sequence of events into fixed states and transitioning between them.
- **Greedy Choice Property:** Maximizing intermediate profits at each step guarantees the global maximum for limited transactions.
- **Space Optimization:** Converting an $O(N \times K)$ DP table down to $O(1)$ space by only keeping track of state variables from the previous step.

## Common Mistakes
- Initializing `buy1` and `buy2` to `0` instead of negative infinity, which causes incorrect calculations when prices are positive.
- Updating the variables in the wrong order within the loop (e.g., updating `sell1` using a prematurely updated `buy1` in a way that violates sequence, though here sequential dependency is required).
- Confusing the number of allowed transactions with unlimited transactions.
- Forgetting that holding zero stock initially means a profit of 0, whereas buying puts us in debt.

## Complexity Analysis
- Time: $O(N)$ - We iterate through the `prices` array of size $N$ exactly once.
- Space: $O(1)$ - We only use four scalar variables (`buy1`, `sell1`, `buy2`, `sell2`) regardless of the array size.

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize buy1 with minimum possible value to represent infinite debt initially
        int buy1 = Integer.MIN_VALUE;
        // Initialize buy2 with minimum possible value to represent second transaction's initial state
        int buy2 = Integer.MIN_VALUE;
        // Initialize sell1 profit to 0 since no transactions have been made yet
        int sell1 = 0;
        // Initialize sell2 profit to 0 since no transactions have been made yet
        int sell2 = 0;
        
        // Loop through every stock price given in the array
        for (int price : prices) {
            // Update the maximum money left after the first buy (minimizing purchase cost)
            buy1 = Math.max(buy1, -price);
            // Update the maximum profit after the first sell (adding current price to buy1 debt)
            sell1 = Math.max(sell1, price + buy1);
            // Update the maximum money left after the second buy (reusing profit from sell1)
            buy2 = Math.max(buy2, sell1 - price);
            // Update the maximum profit after the second sell (adding current price to buy2 debt)
            sell2 = Math.max(sell2, price + buy2);
        }
        
        // After checking all prices, sell2 holds the maximum profit from at most two transactions
        return sell2;   
    }
}
```

## Interview Tips
- Start by explaining the brute force recursive approach with memoization, then transition to the optimized state machine approach.
- Clearly define what each variable (`buy1`, `sell1`, `buy2`, `sell2`) represents to the interviewer to show deep comprehension.
- Mention how this solution naturally scales to $K$ transactions (LeetCode 188: Best Time to Buy and Sell Stock IV).
- Walk through a small test case (like `[3, 3, 5, 0, 0, 3, 1, 4]`) to prove state updates.

## Revision Checklist
- [ ] Understand why `buy1` and `buy2` are initialized to `Integer.MIN_VALUE`.
- [ ] Be able to trace state transitions for each price element.
- [ ] Explain why space complexity is $O(1)$.
- [ ] Extend the logic mentally to $K$ transactions.

## Similar Problems
- Best Time to Buy and Sell Stock I
- Best Time to Buy and Sell Stock II
- Best Time to Buy and Sell Stock IV
- Best Time to Buy and Sell Stock with Cooldown
- Best Time to Buy and Sell Stock with Transaction Fee

## Tags
`Array` `Dynamic Programming`
