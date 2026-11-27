# Maximum Number Of Items From Sale I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(N^2 + N \times \text{budget})  
**Space:** O(N \times \text{budget})

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    int minPrice = Integer.MAX_VALUE;
    public int maximumSaleItems(int[][] items, int budget) {
        int count = 0;
        int n = items.length;
        int minIndex= -1;
        int[][] info = new int[n][3];   //factor, price, quantity
        for(int i=0;i<n;i++){
            minPrice = Math.min(minPrice, items[i][1]);
            if(items[i][1] == minPrice) minIndex = i;
            info[i][0] = items[i][0];
            info[i][1] = items[i][1];
            for(int j = 0;j<n;j++) if(items[j][0]%items[i][0] == 0) info[i][2]++;
        }
        memo = new int[n+1][budget+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(0,budget, info);
    }
    private int func(int currIndex, int budget, int[][] info){
        if(currIndex == info.length) return budget/minPrice;
        if(memo[currIndex][budget] != -1) return memo[currIndex][budget];
        int skip =  func(currIndex+1, budget, info);
        int take = 0;
        if(info[currIndex][1] <= budget) take = info[currIndex][2] + func(currIndex+1, budget-info[currIndex][1], info);
        return memo[currIndex][budget] = Math.max(skip,take);
    }
}
// f p
// 9 100
// 6 2 x2
// 2 6
// 3 4 x1 (+2 free copy of 0th item)


  
        // given budget = 3
        // 2,3,4,6,8,9,10,12,14..
        // 2 -> 2 element    (ind price 4, but divides 1 element)   (3,9)
        // 3 -> 1000 element (ind price = 3000, but divides 999 elements) (2,4,6,8,4000)
        
        // 4 -> 1 (+3) => 1
        // 2 -> 2
        //  (1 free)  (isi ko kharidenge)
        // 7 -> 3 (+2 free) item (2.1>2)
        
        
           // after creating a effective group price we will check it with the minumum value item if it is less we will buy those 
        
        //once all the free item offer will be exausted for the rest amount we will buy the smallest item
        
        //
        // 0 -> []
        // 1 -> [0]
        // 2 -> [0]
        
        // group 0 = 6
        // group 1 = 6+2 = 8/2 = 4
        //group 2 = 4+2 = 6/2 = 3.
```

---

---
## Quick Revision
Finds the maximum number of sale items you can buy under a given budget, including conditional bonuses.
Solved using dynamic programming with memoization, modeling choices as a variation of the knapsack problem.

## Intuition
The "aha moment" comes from realizing that each item in the sale might carry unique conditions or quantities (such as divisor relationships and bonuses) and a specific price. By precomputing these derived attributes for each item, we can transform the problem into a choice-based optimization problem. Since we are trying to maximize the count of items purchased under a strict budget constraint, this maps directly to the 0/1 Knapsack pattern where we either take an item (along with its calculated benefit and cost) or skip it, plus using leftover budget on the absolute cheapest item available.

## Algorithm
1. Initialize variables to track the minimum price and the array information structure.
2. Iterate through all given items to find the minimum price and calculate secondary factors (like divisor relationships to determine bonus quantities).
3. Populate an auxiliary info array storing item factors, prices, and quantities.
4. Initialize a memoization table with `-1` for all states of index and budget.
5. Define a recursive helper function `func(currIndex, budget)` to evaluate choices:
   - Base case: If `currIndex` reaches the end, return the remaining budget divided by the minimum price (`minPrice`).
   - Memoization check: Return the cached result if already computed.
   - Transition: Calculate the result of skipping the current item and taking the current item (if the budget permits).
6. Store and return the maximum value between skipping and taking the current item.

## Concept to Remember
- 0/1 Knapsack Dynamic Programming: Deciding whether to include an item or skip it based on remaining capacity (budget).
- Memoization: Caching subproblem states to avoid exponential time complexity.
- Greedy fallback: Using the minimum price item to spend leftover budget efficiently at the end.

## Common Mistakes
- Failing to properly reset or initialize the memoization table dimensions according to item count and budget size.
- Forgetting to handle the base case where remaining budget is spent entirely on the minimum price item.
- Incorrectly calculating item relationships or bonus quantities during the precomputation phase.
- Missing constraints on array indices leading to `ArrayIndexOutOfBoundsException`.

## Complexity Analysis
- Time: $O(N^2 + N \times \text{budget})$ - $O(N^2)$ to precompute divisor relationships for all items and $O(N \times \text{budget})$ for the dynamic programming states.
- Space: $O(N \times \text{budget})$ - Required to store the memoization table and the auxiliary item information array of size $O(N)$.

## Commented Code
```java
class Solution {
    int[][] memo; // Stores results of subproblems to avoid redundant calculations
    int minPrice = Integer.MAX_VALUE; // Tracks the lowest item price for spending leftover budget
    public int maximumSaleItems(int[][] items, int budget) {
        int count = 0; // Local counter variable (unused but part of initial setup)
        int n = items.length; // Number of items available in the sale
        int minIndex = -1; // Tracks the index of the item with the minimum price
        int[][] info = new int[n][3]; // Auxiliary array to store factor, price, and calculated quantity
        for(int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]); // Update global minimum price
            if(items[i][1] == minPrice) minIndex = i; // Record index if minimum price is matched
            info[i][0] = items[i][0]; // Copy factor to info array
            info[i][1] = items[i][1]; // Copy price to info array
            for(int j = 0; j < n; j++) {
                if(items[j][0] % items[i][0] == 0) info[i][2]++; // Count items whose factors are multiples
            }
        }
        memo = new int[n+1][budget+1]; // Initialize memoization table for DP
        for(int[] temp : memo) Arrays.fill(temp, -1); // Fill memo table with -1 indicating unvisited states
        return func(0, budget, info); // Start the recursive DP function from index 0
    }
    private int func(int currIndex, int budget, int[][] info) {
        if(currIndex == info.length) return budget / minPrice; // Base case: spend leftover budget on the cheapest item
        if(memo[currIndex][budget] != -1) return memo[currIndex][budget]; // Return cached result if already computed
        int skip = func(currIndex + 1, budget, info); // Option 1: Skip the current item
        int take = 0; // Initialize take option
        if(info[currIndex][1] <= budget) {
            // Option 2: Take the current item if budget allows, adding its quantity and recurring with reduced budget
            take = info[currIndex][2] + func(currIndex + 1, budget - info[currIndex][1], info);
        }
        return memo[currIndex][budget] = Math.max(skip, take); // Store and return the maximum of skip and take choices
    }
}
```

## Interview Tips
- Clarify the problem constraints early, especially the maximum size of the budget and the number of items, to justify using dynamic programming.
- Discuss alternative approaches like greedy choices first, then explain why DP is required due to overlapping subproblems and conditional values.
- Walk through the state transitions clearly during the explanation to demonstrate a strong grasp of knapsack variations.

## Revision Checklist
- [ ] Understand the 0/1 Knapsack pattern application.
- [ ] Review how memoization eliminates overlapping subproblems.
- [ ] Practice writing the recursive transition function cleanly.
- [ ] Trace through base cases involving leftover budget.

## Similar Problems
- Coin Change
- Partition Equal Subset Sum
- Combination Sum IV
- House Robber

## Tags
`Array` `Dynamic Programming` `Memoization` `Knapsack`
