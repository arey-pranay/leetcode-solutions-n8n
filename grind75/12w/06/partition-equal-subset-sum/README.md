# Partition Equal Subset Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n * S)  
**Space:** O(n * S)

---

## Solution (java)

```java
class Solution {
    Boolean [][] memo;
    public boolean canPartition(int[] nums) {
        int totSum = 0 ;
        for(int i : nums) totSum +=i;    
        if(totSum%2!=0) return false;
        memo = new Boolean[nums.length][totSum+1];
        return func(0,nums,totSum,totSum/2);
    }
    public boolean func(int i , int[] nums , int t ,int h){
        if(t == h) return true;
        if(i==nums.length) return false;
        if(memo[i][t] != null) return memo[i][t];
        return memo[i][t] =func(i+1,nums,t-nums[i],h) || func(i+1,nums,t,h);
    }
}

```

---

---
## Quick Revision
Given an array of integers, determine if it can be partitioned into two subsets with equal sums.
This is solved using dynamic programming or recursion with memoization, by checking if a subset summing to half of the total sum exists.

## Intuition
The core idea is that if we can partition the array into two subsets with equal sums, then the sum of each subset must be exactly half of the total sum of the array. So, the problem reduces to finding if there exists a subset within the given array that sums up to `totalSum / 2`. If such a subset exists, the remaining elements will automatically form the other subset with the same sum.

## Algorithm
1. Calculate the total sum of all elements in the input array `nums`.
2. If the `totalSum` is odd, it's impossible to partition it into two equal halves, so return `false`.
3. Define a target sum, which is `totalSum / 2`.
4. Initialize a 2D memoization table `memo` of size `nums.length x (totalSum + 1)` to store results of subproblems. This table will store `Boolean` values, where `null` indicates the subproblem hasn't been computed yet.
5. Implement a recursive helper function `func(index, nums, currentSum, targetSum)`:
    a. **Base Case 1:** If `currentSum` equals `targetSum`, we have found a valid subset, return `true`.
    b. **Base Case 2:** If `index` reaches the end of the `nums` array, and `currentSum` is not equal to `targetSum`, we cannot form the target sum with the remaining elements, return `false`.
    c. **Memoization Check:** If `memo[index][currentSum]` is not `null`, return the stored result.
    d. **Recursive Step:** For the current element `nums[index]`, we have two choices:
        i. **Include `nums[index]`:** Recursively call `func(index + 1, nums, currentSum - nums[index], targetSum)`. This is only possible if `currentSum >= nums[index]`.
        ii. **Exclude `nums[index]`:** Recursively call `func(index + 1, nums, currentSum, targetSum)`.
    e. The result for the current state `(index, currentSum)` is the logical OR of the results from including and excluding the current element. Store this result in `memo[index][currentSum]` before returning.
6. Call the recursive helper function starting from index 0, with the initial `currentSum` as `totalSum`, and the `targetSum` as `totalSum / 2`.

*Note: The provided solution uses `t` for `currentSum` and `h` for `targetSum`, and the initial call is `func(0, nums, totSum, totSum/2)`. The logic is equivalent to checking if a subset summing to `totSum/2` can be formed from `nums` starting from index 0, where `t` is the remaining sum to be achieved and `h` is the target sum.*

## Concept to Remember
*   **Subset Sum Problem:** This problem is a variation of the classic Subset Sum problem.
*   **Dynamic Programming (Top-Down with Memoization):** Using recursion with a memoization table to store and reuse results of overlapping subproblems.
*   **Recursion:** Breaking down the problem into smaller, self-similar subproblems.
*   **Bit Manipulation (Alternative DP):** While not used in this solution, a bottom-up DP approach can also be implemented using bitsets for efficiency.

## Common Mistakes
*   **Forgetting the Odd Sum Check:** Not realizing that an odd total sum immediately makes partitioning impossible.
*   **Incorrect Base Cases:** Mishandling the conditions for reaching the end of the array or achieving the target sum.
*   **Off-by-One Errors in DP Table Size:** Allocating a DP table that is too small or too large, leading to index out of bounds errors.
*   **Not Handling Memoization Correctly:** Failing to check the memo table before computation or not storing the result after computation.
*   **Confusing `currentSum` and `targetSum`:** Mismanaging the state variables in the recursive calls, especially when subtracting elements.

## Complexity Analysis
- Time: O(n * S) - reason: The recursive function `func` explores each state `(index, remaining_sum)`. There are `n` possible indices and `S` possible remaining sums (where `S` is `totalSum / 2`). Each state is computed only once due to memoization.
- Space: O(n * S) - reason: The space complexity is dominated by the memoization table `memo`, which has dimensions `n x (totalSum / 2 + 1)`. The recursion depth can also go up to `n` in the worst case, contributing to the call stack space.

## Commented Code
```java
class Solution {
    // Declare a 2D array to store memoized results.
    // memo[i][j] will store whether a subset summing to 'j' can be formed using elements from index 'i' onwards.
    Boolean [][] memo;

    // Main function to check if the array can be partitioned into two equal sum subsets.
    public boolean canPartition(int[] nums) {
        // Initialize total sum to 0.
        int totSum = 0 ;
        // Iterate through the array to calculate the total sum.
        for(int i : nums) totSum +=i;

        // If the total sum is odd, it's impossible to partition into two equal halves.
        if(totSum%2!=0) return false;

        // Initialize the memoization table.
        // The dimensions are nums.length (for index) and totSum + 1 (for possible sums).
        // We use totSum + 1 because the target sum can be up to totSum/2, and intermediate sums can be up to totSum.
        // The provided solution uses totSum as the upper bound for the sum dimension, which is slightly more than needed but still correct.
        memo = new Boolean[nums.length][totSum+1];

        // Call the recursive helper function.
        // Start from index 0.
        // The initial 't' (remaining sum to achieve) is the total sum.
        // The 'h' (target sum) is half of the total sum.
        // The function will try to find if a subset summing to totSum/2 can be formed.
        return func(0,nums,totSum,totSum/2);
    }

    // Recursive helper function with memoization.
    // i: current index in the nums array.
    // nums: the input array.
    // t: the remaining sum that needs to be achieved to reach the target.
    // h: the target sum (half of the total sum).
    public boolean func(int i , int[] nums , int t ,int h){
        // Base Case 1: If the remaining sum 't' equals the target sum 'h', we have found a valid subset.
        if(t == h) return true;

        // Base Case 2: If we have reached the end of the array (i == nums.length)
        // and the remaining sum 't' is not equal to the target 'h', then we cannot form the target sum.
        if(i==nums.length) return false;

        // Memoization Check: If the result for the current state (i, t) has already been computed, return it.
        if(memo[i][t] != null) return memo[i][t];

        // Recursive Step: Explore two possibilities for the current element nums[i].
        // 1. Include nums[i]: Subtract nums[i] from the remaining sum 't' and move to the next element (i+1).
        //    This is only valid if the current remaining sum 't' is greater than or equal to nums[i].
        //    The provided solution implicitly handles this by passing `t - nums[i]` and relying on base cases.
        //    A more explicit check `if (t >= nums[i])` could be added before the first recursive call.
        // 2. Exclude nums[i]: Keep the remaining sum 't' as is and move to the next element (i+1).

        // The result for the current state is true if either including or excluding nums[i] leads to a solution.
        // Store the computed result in the memoization table before returning.
        return memo[i][t] = func(i+1,nums,t-nums[i],h) || func(i+1,nums,t,h);
    }
}
```

## Interview Tips
1.  **Clarify the Goal:** Ensure you understand that the problem is about finding *if* a partition exists, not *how* to partition.
2.  **Explain the "Half Sum" Logic:** Clearly articulate why the problem reduces to finding a subset that sums to `totalSum / 2`.
3.  **Discuss DP vs. Recursion:** Be prepared to discuss both the recursive (top-down with memoization) and iterative (bottom-up DP) approaches. The provided solution uses memoization.
4.  **Edge Cases:** Mention handling the odd total sum case and empty input arrays (though the constraints might specify non-empty).
5.  **State Definition:** For DP, clearly define the state (e.g., `dp[i][s]` means can sum `s` be formed using first `i` elements).

## Revision Checklist
- [ ] Understand the problem: Partitioning an array into two equal sum subsets.
- [ ] Identify the core subproblem: Finding a subset that sums to `totalSum / 2`.
- [ ] Handle the odd `totalSum` edge case.
- [ ] Implement recursion with memoization (or bottom-up DP).
- [ ] Define base cases correctly for recursion.
- [ ] Define state transitions for DP.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative DP approaches (e.g., using a 1D array if space optimization is discussed).

## Similar Problems
*   Subset Sum
*   Partition Equal Subset Sum II (if duplicates are allowed and need specific handling)
*   Target Sum
*   Knapsack Problem (0/1 Knapsack)

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization`
