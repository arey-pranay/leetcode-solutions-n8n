# Best Time To Buy And Sell Stock Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n)  
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
Solve this using state machine/dynamic programming tracking the best states of buying and selling after each transaction sequentially.

## Intuition
The "aha moment" is realizing you don't need a 3D DP table `(day, transaction_count, holding_status)`. Since we can make at most two transactions, we can simulate the exact cash flow in our pockets at each step using four variables: `buy1`, `sell1`, `buy2`, and `sell2`. As we iterate through each price, we update these states to represent the maximum money we would have if we were currently in that specific financial state.

## Algorithm
1. Initialize four variables to track the state: `buy1 = Integer.MIN_VALUE`, `sell1 = 0`, `buy2 = Integer.MIN_VALUE`, `sell2 = 0`.
2. Iterate through each `price` in the `prices` array.
3. Update `buy1` to be the maximum of its current value or buying the stock using initial 0 cash (hence `-price`).
4. Update `sell1` to be the maximum of its current value or selling the stock bought in transaction 1 (`price + buy1`).
5. Update `buy2` to be the maximum of its current value or buying the second stock using the profit from `sell1` (`sell1 - price`).
6. Update `sell2` to be the maximum of its current value or selling the stock bought in transaction 2 (`price + buy2`).
7. Return `sell2` as the maximum profit after completing up to two transactions.

## Concept to Remember
- State Machine DP: Modeling complex multi-step rules by tracking distinct states and their transitions.
- Space Optimization: Reducing multi-dimensional DP storage down to a few scalar variables by relying on optimal sub-structure.

## Common Mistakes
- Initializing `buy1` and `buy2` to `0` instead of `Integer.MIN_VALUE`, which fails if all stock prices are negative (though prices are usually positive, it is a bad habit for state initialization).
- Incorrectly updating `buy2` by subtracting the price from the *current* stock price instead of using accumulated profit from `sell1`.
- Forgetting that transactions can be of length zero (profit remains `0`).

## Complexity Analysis
- Time: O(n) - We iterate through the array of prices of length $n$ exactly once.
- Space: O(1) - We only use four scalar variables regardless of the input size.

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize buy1 with minimum possible value to represent state before first purchase
        int buy1 = Integer.MIN_VALUE;
        // Initialize buy2 with minimum possible value to represent state before second purchase
        int buy2 = Integer.MIN_VALUE;
        // Initialize sell1 to 0 since no profit has been made yet after 1st transaction
        int sell1 = 0;
        // Initialize sell2 to 0 since no profit has been made yet after 2nd transaction
        int sell2 = 0;
        
        // Iterate through every price in the market array
        for(int price : prices) {
            // Update the max money held after the first buy (maximize remaining cash)
            buy1 = Math.max(buy1, -price);
            // Update the max profit after the first sell (add current price to money left from buy1)
            sell1 = Math.max(sell1, price + buy1);
            // Update the max money held after the second buy (use profit accumulated from sell1)
            buy2 = Math.max(buy2, sell1 - price);
            // Update the max profit after the second sell (add current price to money left from buy2)
            sell2 = Math.max(sell2, price + buy2);
        }
        
        // Return the final profit after at most two complete transactions
        return sell2;   
    }
}
```

## Interview Tips
- Start by explaining the brute-force recursive approach with memoization, then transition to the optimized O(1) space state machine approach.
- Clearly articulate what each of the four variables (`buy1`, `sell1`, `buy2`, `sell2`) represents in terms of cash flow.
- Be prepared to generalize the solution to $k$ transactions (LeetCode 188), which transforms the approach into an array-based DP or size-$k$ state loop.

## Revision Checklist
- [ ] Understand why `buy1` and `buy2` are initialized to `Integer.MIN_VALUE`.
- [ ] Trace the code mentally with a sample array like `[3, 3, 5, 0, 0, 3, 1, 4]`.
- [ ] Explain why the order of updates inside the loop matters.
- [ ] Know how to extend this logic to $k$ transactions.

## Similar Problems
- `Best Time to Buy and Sell Stock`
- `Best Time to Buy and Sell Stock II`
- `Best Time to Buy and Sell Stock IV`
- `Best Time to Buy and Sell Stock with Cooldown`
- `Best Time to Buy and Sell Stock with Transaction Fee`

## Tags
`Array` `Dynamic Programming`
