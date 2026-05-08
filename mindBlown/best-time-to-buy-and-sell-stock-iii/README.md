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
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int prof1 = 0, prof2 = 0;

        for (int price :  prices) {
            buy1 = Math.min(buy1, price);
            prof1 = Math.max(prof1, price - buy1);
            buy2 = Math.min(buy2, price - prof1);// prof1 ka discount milega 2nd buy pe, kyuki already utna gain hai humaare paas
            prof2 = Math.max(prof2, price - buy2); 
            System.out.println(buy1 + " " + prof1 + " " + buy2 + " " + prof2);
        }
        
        return prof2;
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
This problem asks for the maximum profit from at most two stock transactions.
We solve it by iterating through prices and tracking maximum profits after one and two transactions.

## Intuition
The core idea is to realize that we can break down the problem into two independent "Best Time to Buy and Sell Stock I" problems. If we can find the best single transaction profit up to a certain day, we can then use that information to find the best second transaction profit after that day. This leads to a dynamic programming approach where we keep track of the state of our transactions.

The provided solution uses a clever optimization. Instead of explicitly defining states for "buy1", "sell1", "buy2", "sell2", it implicitly tracks the minimum cost to buy for the first transaction (`buy1`) and the maximum profit after the first transaction (`prof1`). Then, it uses `prof1` to adjust the cost for the second buy (`buy2 = Math.min(buy2, price - prof1)`). This means `buy2` represents the minimum effective cost to *start* the second transaction, considering the profit already made from the first. Finally, `prof2` tracks the maximum profit after the second transaction.

## Algorithm
1. Initialize `buy1` to a very large value (e.g., `Integer.MAX_VALUE`) to represent the minimum cost to buy the first stock.
2. Initialize `prof1` to 0, representing the maximum profit after the first transaction.
3. Initialize `buy2` to a very large value (e.g., `Integer.MAX_VALUE`) to represent the minimum effective cost to buy the second stock.
4. Initialize `prof2` to 0, representing the maximum profit after the second transaction.
5. Iterate through each `price` in the `prices` array:
    a. Update `buy1`: `buy1 = Math.min(buy1, price)`. This finds the lowest price encountered so far for the first purchase.
    b. Update `prof1`: `prof1 = Math.max(prof1, price - buy1)`. This calculates the maximum profit achievable by selling the first stock at the current `price` after buying at `buy1`.
    c. Update `buy2`: `buy2 = Math.min(buy2, price - prof1)`. This is the crucial step. It calculates the minimum effective cost for the second buy. `price - prof1` represents the net cost if we were to buy at the current `price` and had already secured `prof1` profit. We want to minimize this net cost for the second transaction.
    d. Update `prof2`: `prof2 = Math.max(prof2, price - buy2)`. This calculates the maximum profit achievable by selling the second stock at the current `price` after buying at the effective cost `buy2`.
6. Return `prof2`, which will hold the maximum profit from at most two transactions.

## Concept to Remember
*   Dynamic Programming: Breaking down a problem into overlapping subproblems and storing their solutions.
*   State Management: Carefully defining and updating variables that represent the state of transactions (buy/sell, number of transactions).
*   Optimization: Recognizing how to reduce the number of states or computations needed.
*   Greedy Approach (within DP): Making locally optimal choices (e.g., minimizing buy price, maximizing profit) that lead to a globally optimal solution.

## Common Mistakes
*   Incorrectly defining DP states: Not capturing all necessary information about transactions.
*   Overcomplicating the state: Trying to track too many variables when a simpler set suffices.
*   Off-by-one errors in loops or state transitions.
*   Not handling the "at most two transactions" constraint correctly, potentially allowing more than two.
*   Forgetting to initialize variables to appropriate base values (e.g., `Integer.MAX_VALUE` for minimums, 0 for maximums).

## Complexity Analysis
- Time: O(N) - reason: We iterate through the `prices` array once.
- Space: O(1) - reason: We only use a few constant extra variables to store `buy1`, `prof1`, `buy2`, and `prof2`.

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize buy1 to a very large value. This will store the minimum price to buy the first stock.
        int buy1 = Integer.MAX_VALUE;
        // Initialize prof1 to 0. This will store the maximum profit after the first transaction.
        int prof1 = 0;
        // Initialize buy2 to a very large value. This will store the minimum effective cost to buy the second stock.
        // The effective cost considers the profit from the first transaction.
        int buy2 = Integer.MAX_VALUE;
        // Initialize prof2 to 0. This will store the maximum profit after the second transaction.
        int prof2 = 0;

        // Iterate through each price in the prices array.
        for (int price : prices) {
            // Update buy1: Find the minimum price encountered so far for the first purchase.
            buy1 = Math.min(buy1, price);
            // Update prof1: Calculate the maximum profit if we sell the first stock at the current price.
            // This is the current price minus the minimum buy price for the first transaction.
            prof1 = Math.max(prof1, price - buy1);
            
            // Update buy2: This is the key optimization.
            // We want to find the minimum effective cost for the second buy.
            // 'price - prof1' represents the net cost if we buy at the current 'price'
            // and have already secured 'prof1' profit from the first transaction.
            // We minimize this net cost for the second transaction.
            buy2 = Math.min(buy2, price - prof1);
            
            // Update prof2: Calculate the maximum profit if we sell the second stock at the current price.
            // This is the current price minus the minimum effective buy price for the second transaction.
            prof2 = Math.max(prof2, price - buy2);
            
            // Optional: Print intermediate values for debugging/understanding.
            // System.out.println("buy1: " + buy1 + ", prof1: " + prof1 + ", buy2: " + buy2 + ", prof2: " + prof2);
        }
        
        // Return prof2, which holds the maximum profit achievable with at most two transactions.
        return prof2;
    }
}
```

## Interview Tips
*   Explain the intuition behind the `buy2 = Math.min(buy2, price - prof1)` step clearly. This is the most non-obvious part.
*   Start by explaining a brute-force or a simpler DP approach (like allowing infinite transactions) and then optimize to the O(N) time, O(1) space solution.
*   Walk through an example array step-by-step to demonstrate how the variables change.
*   Be prepared to discuss the states you are implicitly tracking with `buy1`, `prof1`, `buy2`, and `prof2`.

## Revision Checklist
- [ ] Understand the problem: at most two transactions.
- [ ] Recognize the connection to "Best Time to Buy and Sell Stock I".
- [ ] Grasp the state transitions for `buy1`, `prof1`, `buy2`, `prof2`.
- [ ] Understand the optimization `buy2 = Math.min(buy2, price - prof1)`.
- [ ] Implement the O(N) time, O(1) space solution.
- [ ] Trace an example manually.

## Similar Problems
*   Best Time to Buy and Sell Stock (LeetCode 121)
*   Best Time to Buy and Sell Stock II (LeetCode 122)
*   Best Time to Buy and Sell Stock IV (LeetCode 188) - Generalization to K transactions.

## Tags
`Array` `Dynamic Programming` `Greedy`
