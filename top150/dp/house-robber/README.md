# House Robber

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return use(nums,0);
    }
    public int use(int[] nums, int i){
        if(i>=nums.length) return 0;
        if(memo[i] == -1){
            int a= nums[i] + use(nums,i+2);
            int b= use(nums,i+1);
            memo[i] = Math.max(a,b);
        }
        return memo[i];
    }
    
}
```

---

---
## Quick Revision
You cannot rob adjacent houses. The goal is to maximize the total amount of money robbed.
This is solved using dynamic programming with memoization to avoid redundant calculations.

## Intuition
The core idea is that for any given house, we have two choices: either rob it or don't rob it.
If we rob the current house, we cannot rob the next one, so we add the current house's value to the maximum we can get from the house two steps ahead.
If we don't rob the current house, we can potentially rob the next one, so we consider the maximum we can get from the house one step ahead. We take the maximum of these two choices.

## Algorithm
1. Initialize a memoization array `memo` of the same size as `nums`, filled with a sentinel value (e.g., -1) to indicate uncomputed states.
2. Define a recursive helper function `use(nums, i)` that calculates the maximum amount that can be robbed starting from house `i`.
3. Base Case: If `i` is out of bounds (i.e., `i >= nums.length`), return 0 (no houses left to rob).
4. Memoization Check: If `memo[i]` is not the sentinel value, it means the result for this subproblem has already been computed, so return `memo[i]`.
5. Recursive Step:
    a. Calculate `rob_current`: the amount if we rob the current house `nums[i]`. This is `nums[i]` plus the maximum amount we can get from house `i+2` (since we can't rob `i+1`).
    b. Calculate `skip_current`: the amount if we skip the current house `nums[i]`. This is the maximum amount we can get from house `i+1`.
    c. The maximum amount for house `i` is `Math.max(rob_current, skip_current)`.
6. Store the result in `memo[i]` before returning it.
7. The initial call to the helper function will be `use(nums, 0)`.

## Concept to Remember
*   **Dynamic Programming (DP)**: Breaking down a problem into overlapping subproblems and solving each subproblem only once.
*   **Recursion with Memoization**: A top-down approach to DP where results of function calls are cached to avoid recomputation.
*   **Decision Making**: At each step, we make a choice (rob or skip) that affects future possibilities.

## Common Mistakes
*   Forgetting the base case in the recursion, leading to infinite recursion or incorrect results.
*   Incorrectly handling the indices when considering robbing the current house (e.g., `i+1` instead of `i+2`).
*   Not initializing the memoization array correctly or not checking it before computation.
*   Assuming a greedy approach will work (e.g., always picking the largest available amount).

## Complexity Analysis
- Time: O(n) - Each house's maximum robbable amount is computed only once due to memoization. The recursive calls explore each house at most once.
- Space: O(n) - For the memoization array `memo` and the recursion call stack (which can go up to `n` in depth in the worst case).

## Commented Code
```java
class Solution {
    // Declare a memoization array to store results of subproblems.
    int[] memo;

    // The main function that initiates the robbing process.
    public int rob(int[] nums) {
        // Initialize the memo array with the same size as the input array.
        memo = new int[nums.length];
        // Fill the memo array with -1, indicating that no results have been computed yet.
        Arrays.fill(memo,-1);
        // Start the recursive calculation from the first house (index 0).
        return use(nums,0);
    }

    // Recursive helper function to calculate the maximum amount robbable from house 'i' onwards.
    public int use(int[] nums, int i){
        // Base case: If the index 'i' is out of bounds, it means there are no more houses to rob, so return 0.
        if(i>=nums.length) return 0;
        // Memoization check: If the result for index 'i' has already been computed and stored in memo[i], return it directly.
        if(memo[i] == -1){
            // Option 1: Rob the current house (nums[i]).
            // If we rob house 'i', we cannot rob house 'i+1', so we add nums[i] to the max amount from house 'i+2'.
            int rob_current = nums[i] + use(nums,i+2);
            // Option 2: Skip the current house (nums[i]).
            // If we skip house 'i', we can potentially rob house 'i+1', so we take the max amount from house 'i+1'.
            int skip_current = use(nums,i+1);
            // Store the maximum of the two options in memo[i] for future use.
            memo[i] = Math.max(rob_current, skip_current);
        }
        // Return the computed or retrieved maximum amount for house 'i'.
        return memo[i];
    }
}
```

## Interview Tips
1.  **Explain the DP State**: Clearly articulate what `dp[i]` (or `memo[i]` in this case) represents – the maximum amount that can be robbed up to house `i` (or starting from house `i`).
2.  **Walk Through an Example**: Use a small array like `[1, 2, 3, 1]` and trace the recursive calls and memoization updates to demonstrate your understanding.
3.  **Discuss Trade-offs**: Mention the iterative DP approach as an alternative and discuss its space complexity (can be optimized to O(1)).
4.  **Clarify Constraints**: Ask about potential constraints on the size of `nums` or the values within it, which might influence the choice of data types or algorithms.

## Revision Checklist
- [ ] Understand the problem statement: cannot rob adjacent houses.
- [ ] Identify the overlapping subproblems and optimal substructure.
- [ ] Formulate the recurrence relation: `dp[i] = max(nums[i] + dp[i-2], dp[i-1])`.
- [ ] Implement using recursion with memoization.
- [ ] Implement using iterative DP (bottom-up).
- [ ] Analyze time and space complexity for both approaches.
- [ ] Test with edge cases: empty array, single house, two houses.

## Similar Problems
*   House Robber II
*   House Robber III
*   Coin Change
*   Maximum Subarray

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization`
