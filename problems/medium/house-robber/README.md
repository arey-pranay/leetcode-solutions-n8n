# House Robber

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
   int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robRecursion(nums, 0);
    }
    public int robRecursion(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }
        if(memo[i] != -1) return memo[i];
        int take = nums[i] + robRecursion(nums, i + 2);
        int skip = robRecursion(nums, i + 1);
        return memo[i] = Math.max(take, skip);
    }

//  public int rob(int[] nums) {
//         int[] dp = new int[nums.length];
//         dp[0] = nums[0];

//         for(int i=1; i<nums.length; i++){
//             int take = nums[i];
//             if(i>1) take+= dp[i-2];

//             int notTake = dp[i-1];

//             dp[i] = Math.max(take, notTake);
//         }

//         return dp[nums.length-1];
//     }
}

```

---

---
## Problem Summary
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array `nums` representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

## Approach and Intuition
The problem asks us to find the maximum amount of money we can rob from a circular arrangement of houses, with the constraint that we cannot rob adjacent houses. This constraint, combined with the circular arrangement, means we cannot rob both the first and the last house simultaneously.

This problem can be broken down into subproblems. For any given house `i`, we have two choices:
1. **Rob house `i`**: If we rob house `i`, we cannot rob house `i-1` or house `i+1`. The total money would be `nums[i]` plus the maximum money we can get from houses *before* `i-1` (or *after* `i+1` in a linear sense).
2. **Skip house `i`**: If we skip house `i`, we can then consider robbing house `i-1` or `i+1`. The total money would be the maximum money we can get from houses *before* `i`.

This suggests a dynamic programming or recursive approach with memoization.

The provided solution uses a recursive approach with memoization. The `robRecursion(nums, i)` function calculates the maximum amount of money that can be robbed starting from house `i` up to the end of the street.

- **Base Case**: If `i` is out of bounds (`i >= nums.length`), it means there are no more houses to rob, so we return `0`.
- **Memoization**: If `memo[i]` is not `-1`, it means we have already computed the result for this subproblem, so we return the stored value.
- **Recursive Steps**:
    - `take`: Represents the maximum money if we *take* the current house `nums[i]`. In this case, we cannot take the next house (`i+1`), so we recursively call `robRecursion` for `i+2`.
    - `skip`: Represents the maximum money if we *skip* the current house `nums[i]`. In this case, we can consider the next house (`i+1`), so we recursively call `robRecursion` for `i+1`.
- **Result**: We take the maximum of `take` and `skip` and store it in `memo[i]` before returning.

The initial call is `robRecursion(nums, 0)`, which starts the process from the first house.

**Important Note on Circularity**: The provided code snippet *does not* handle the circular nature of the houses. It solves the linear version of the House Robber problem. To solve the circular version, one would typically solve the linear problem twice:
1. Robbing houses from index 0 to `n-2` (excluding the last house).
2. Robbing houses from index 1 to `n-1` (excluding the first house).
The final answer would be the maximum of these two results.

The commented-out code shows an iterative DP approach for the linear version.

## Complexity Analysis
- **Time**: O(N) - reason: Each subproblem `robRecursion(nums, i)` is computed only once due to memoization. There are `N` possible values for `i` (from 0 to N-1). Each computation involves constant time operations (addition, comparison, array access).
- **Space**: O(N) - reason: The `memo` array of size `N` is used to store the results of subproblems. The recursion depth can also go up to `N` in the worst case, contributing to the call stack space.

## Code Walkthrough
```java
class Solution {
   int[] memo; // Array to store computed results for memoization. Initialized with -1.

    public int rob(int[] nums) {
        // Initialize memoization array with -1, indicating no results computed yet.
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        // Start the recursive process from the first house (index 0).
        return robRecursion(nums, 0);
    }

    // Recursive helper function with memoization.
    // i: the current house index we are considering.
    public int robRecursion(int[] nums, int i) {
        // Base case: If we've gone past the last house, no more money can be robbed.
        if (i >= nums.length) {
            return 0;
        }
        // Memoization check: If the result for this house index is already computed, return it.
        if(memo[i] != -1) return memo[i];

        // Option 1: Take the current house nums[i].
        // If we take nums[i], we cannot take nums[i+1], so we jump to i+2.
        int take = nums[i] + robRecursion(nums, i + 2);

        // Option 2: Skip the current house nums[i].
        // If we skip nums[i], we can consider the next house i+1.
        int skip = robRecursion(nums, i + 1);

        // Store the maximum of the two options in memo[i] and return it.
        return memo[i] = Math.max(take, skip);
    }

//  // Commented out: Iterative DP solution for the LINEAR House Robber problem.
//  public int rob(int[] nums) {
//         // dp[i] will store the maximum money that can be robbed up to house i.
//         int[] dp = new int[nums.length];
//         // Base case: For the first house, the max money is just the money in that house.
//         dp[0] = nums[0];

//         // Iterate through the houses starting from the second one.
//         for(int i=1; i<nums.length; i++){
//             // Option 1: Take the current house nums[i].
//             // If we take nums[i], we must have skipped nums[i-1].
//             // So, the total is nums[i] + max money up to house i-2.
//             int take = nums[i];
//             if(i>1) take+= dp[i-2]; // Add dp[i-2] only if i-2 is a valid index.

//             // Option 2: Skip the current house nums[i].
//             // If we skip nums[i], the max money is the same as the max money up to house i-1.
//             int notTake = dp[i-1];

//             // The max money up to house i is the maximum of taking or not taking house i.
//             dp[i] = Math.max(take, notTake);
//         }

//         // The final answer is the max money that can be robbed up to the last house.
//         return dp[nums.length-1];
//     }
}
```

## Interview Tips
1.  **Clarify the Problem**: The most crucial first step for this problem is to clarify if it's the *linear* or *circular* version. The provided code solves the linear version. If the interviewer says "circular," you *must* address the constraint of not robbing both the first and last houses.
2.  **Start with Brute Force/Recursion**: Explain the decision at each house: either rob it (and skip the next) or skip it (and consider the next). This naturally leads to a recursive solution.
3.  **Identify Overlapping Subproblems**: Point out that the recursive solution will recompute the same subproblems multiple times (e.g., `robRecursion(nums, 3)` might be called from `robRecursion(nums, 1)` and `robRecursion(nums, 2)`).
4.  **Introduce Memoization**: Explain how memoization (top-down DP) can store the results of these subproblems to avoid recomputation, leading to an O(N) time complexity. Walk through the `memo` array and the base cases.
5.  **Transition to Iterative DP (Optional but Recommended)**: After explaining memoization, show how to convert it into an iterative DP solution (bottom-up DP). This often involves a `dp` array where `dp[i]` represents the maximum amount robbed up to house `i`. Explain the transitions: `dp[i] = max(nums[i] + dp[i-2], dp[i-1])`.
6.  **Space Optimization**: For the iterative DP, discuss if space can be optimized. In the linear version, notice that `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`. This means we only need to keep track of the previous two values, reducing space complexity from O(N) to O(1).
7.  **Handle Circularity (if applicable)**: If the problem is circular, explain the strategy: solve the linear problem for `nums[0...n-2]` and `nums[1...n-1]` and take the maximum. This is because the circular constraint only affects the first and last houses.

## Optimization and Alternatives
*   **Iterative DP (Bottom-Up)**: As shown in the commented code, an iterative DP approach can be used.
    *   `dp[i]` = maximum money robbed up to house `i`.
    *   `dp[i] = max(nums[i] + dp[i-2], dp[i-1])`
    *   Base cases: `dp[0] = nums[0]`, `dp[1] = max(nums[0], nums[1])`.
    *   Time: O(N), Space: O(N)

*   **Space-Optimized Iterative DP**: For the linear version, we can optimize the space complexity of the iterative DP. Since `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`, we only need to store the last two computed values.
    *   Let `prev2` be the max money up to `i-2`, and `prev1` be the max money up to `i-1`.
    *   The current max `current` will be `max(nums[i] + prev2, prev1)`.
    *   Then update `prev2 = prev1` and `prev1 = current`.
    *   Time: O(N), Space: O(1)

*   **Handling Circularity**: To solve the *circular* House Robber problem, you would typically call the linear solution twice:
    1.  Rob houses from index 0 to `n-2` (i.e., `rob(nums[0...n-2])`).
    2.  Rob houses from index 1 to `n-1` (i.e., `rob(nums[1...n-1])`).
    The final answer is `max(result1, result2)`. This is because the constraint of not robbing adjacent houses in a circle only impacts the choice between robbing house 0 and house `n-1`. By excluding one of them in each of the two linear subproblems, we cover all valid scenarios.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Differentiate between linear and circular House Robber.
- [ ] Formulate a recursive relation.
- [ ] Implement memoization (top-down DP).
- [ ] Analyze time and space complexity of the recursive solution.
- [ ] Implement iterative DP (bottom-up).
- [ ] Analyze time and space complexity of the iterative solution.
- [ ] Consider space optimization for iterative DP.
- [ ] If circular, devise a strategy to handle the first/last house constraint.
- [ ] Test with edge cases (empty array, single house, two houses).

## Similar Problems
*   House Robber II (Circular version)
*   Climbing Stairs
*   Coin Change
*   Maximum Subarray

## Tags
`Dynamic Programming` `Array` `Recursion` `Memoization`
