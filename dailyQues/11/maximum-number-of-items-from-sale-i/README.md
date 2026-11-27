# Maximum Number Of Items From Sale I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(n^2 + n * budget)  
**Space:** O(n * budget)

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
Finds the maximum number of items you can buy given a budget and a list of sale items with bundle/quantity bonuses.
Solved using Dynamic Programming (memoization) similar to the 0/1 Knapsack problem, augmented with a base fallback using the minimum price item.

## Intuition
The problem allows us to either take an item (which costs a certain price and yields a certain quantity based on divisibility) or skip it. At the end of our item choices, any remaining budget should be spent entirely on the cheapest available item to maximize the total quantity. This structure maps directly to the 0/1 Knapsack problem where "weight" is the price, "value" is the quantity obtained, and the base case handles the leftover budget.

## Algorithm
1. Track the minimum price across all items to use when spending leftover budget.
2. Build an auxiliary `info` array of size `n x 3` storing each item's price, factor/identifier, and quantity (calculated by counting how many other items have quantities divisible by this item's factor).
3. Initialize a 2D memoization table with `-1`.
4. Define a recursive helper function `func(currIndex, budget)` that evaluates choices for the current item:
   - Base case: If `currIndex == info.length`, return `budget / minPrice` (spend all remaining budget on the cheapest item).
   - Check if the subproblem is already memoized; if so, return it.
   - Compute `skip` by recursively calling the function for `currIndex + 1` with the same budget.
   - Compute `take` if the item's price is less than or equal to the current budget: add its quantity and recursively call for `currIndex + 1` with `budget - itemPrice`.
   - Store and return `Math.max(skip, take)` in the memoization table.

## Concept to Remember
- 0/1 Knapsack dynamic programming pattern.
- Memoization to avoid overlapping subproblems.
- Greedy fallback strategy for remainder budget.
- Pre-processing input arrays to extract problem-specific weights and values.

## Common Mistakes
- Forgetting to handle the base case where remaining budget is spent on the absolute cheapest item.
- Incorrectly calculating the quantity bonus (nested loops causing unintended time complexity bottlenecks).
- Index out of bounds errors when sizing the memoization table relative to the number of items and budget.
- Mutating the budget incorrectly or failing to check if `itemPrice <= budget` before taking.

## Complexity Analysis
- Time: O(n^2 + n * budget) - O(n^2) to compute the quantities and find the minimum price, and O(n * budget) states for the DP memoization.
- Space: O(n * budget) - Required to store the memoization table alongside the auxiliary `info` array of size O(n).

## Commented Code
```java
class Solution {
    int[][] memo; // Stores computed subproblems to avoid redundant calculations
    int minPrice = Integer.MAX_VALUE; // Tracks the lowest price item for remainder spending
    public int maximumSaleItems(int[][] items, int budget) {
        int count = 0; // Unused counter variable retained for structure
        int n = items.length; // Total number of items available in the sale
        int minIndex = -1; // Tracks the index of the minimum price item
        int[][] info = new int[n][3]; // Auxiliary array storing [factor/id, price, quantity]
        for(int i = 0; i < n; i++){
            minPrice = Math.min(minPrice, items[i][1]); // Update the minimum price found so far
            if(items[i][1] == minPrice) minIndex = i; // Save the index of the minimum price item
            info[i][0] = items[i][0]; // Store the item factor
            info[i][1] = items[i][1]; // Store the item price
            // Calculate quantity by checking how many item factors divide evenly into the current item's factor
            for(int j = 0; j < n; j++) if(items[j][0] % items[i][0] == 0) info[i][2]++;
        }
        memo = new int[n + 1][budget + 1]; // Initialize memoization table for items and budget
        for(int[] temp : memo) Arrays.fill(temp, -1); // Fill memo table with -1 to indicate uncalculated states
        return func(0, budget, info); // Start the recursive DP function from the first item
    }
    private int func(int currIndex, int budget, int[][] info){
        if(currIndex == info.length) return budget / minPrice; // Base case: spend leftover budget on the cheapest item
        if(memo[currIndex][budget] != -1) return memo[currIndex][budget]; // Return cached result if already computed
        int skip = func(currIndex + 1, budget, info); // Option 1: Skip the current item
        int take = 0; // Initialize take value
        // Option 2: Take the item only if we have enough budget remaining
        if(info[currIndex][1] <= budget) take = info[currIndex][2] + func(currIndex + 1, budget - info[currIndex][1], info);
        return memo[currIndex][budget] = Math.max(skip, take); // Cache and return the maximum items between skipping and taking
    }
}
```

## Interview Tips
- Clarify how item quantities and bundle offers are calculated before coding the pre-processing loop.
- Explain the choice of 0/1 Knapsack DP clearly to the interviewer.
- Discuss space optimization possibilities (e.g., reducing 2D DP to 1D DP if budget allows).
- Walk through the base case where leftover budget is spent on the minimum price item.

## Revision Checklist
- [ ] Understand 0/1 knapsack state transitions.
- [ ] Ensure correct memoization bounds (`n + 1` by `budget + 1`).
- [ ] Verify handling of the remainder budget using the minimum price.
- [ ] Check time and space complexity limits.

## Similar Problems
- Coin Change II
- Combination Sum IV
- Partition Equal Subset Sum
- Profitable Schemes

## Tags
`Array` `Dynamic Programming` `Memoization` `Knapsack`
