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
        return func(nums,0);
    }
    public int func(int[] nums, int i){
        if(i>=nums.length) return 0;
        if(memo[i] != -1) return memo[i];
        
        int a = func(nums,i+1);
        int b = nums[i] + func(nums,i+2);
        memo[i] = Math.max(a,b);
        
        return memo[i];
    }
}
```

---

---
## Quick Revision
You cannot rob adjacent houses. Find the maximum amount of money you can rob.
This is solved using dynamic programming with memoization or tabulation.

## Intuition
The core idea is that for any given house `i`, we have two choices:
1. **Rob house `i`**: If we rob house `i`, we cannot rob house `i+1`. So, the maximum amount we can get is `nums[i]` plus the maximum amount we can get from house `i+2` onwards.
2. **Don't rob house `i`**: If we don't rob house `i`, we can potentially rob house `i+1`. So, the maximum amount we can get is the maximum amount we can get from house `i+1` onwards.

We want to take the maximum of these two choices at each step. This naturally leads to a recursive structure that can be optimized with dynamic programming.

## Algorithm
1. **Base Case**: If the current house index `i` is out of bounds (i.e., `i >= nums.length`), it means there are no more houses to rob, so return 0.
2. **Memoization Check**: If the result for the current house index `i` has already been computed and stored in `memo[i]`, return the stored value.
3. **Recursive Step**:
    * Calculate the maximum amount if we *skip* the current house `i`: `skip_current = func(nums, i + 1)`.
    * Calculate the maximum amount if we *rob* the current house `i`: `rob_current = nums[i] + func(nums, i + 2)`.
4. **Store and Return**: Store the maximum of `skip_current` and `rob_current` in `memo[i]` and return it.
5. **Initialization**: Initialize a memoization array `memo` of the same size as `nums` with a sentinel value (e.g., -1) to indicate that results haven't been computed yet. Call the recursive function starting from index 0.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a problem into overlapping subproblems and solving each subproblem only once, storing their solutions.
*   **Recursion with Memoization**: Using a recursive function to define subproblems and a data structure (like an array or hash map) to store results of subproblems to avoid recomputation.
*   **Decision Making**: At each step, making an optimal choice based on future possibilities.

## Common Mistakes
*   **Incorrect Base Cases**: Not handling the `i >= nums.length` condition properly, leading to infinite recursion or incorrect results.
*   **Off-by-One Errors**: Incorrectly calculating the next indices for recursion (e.g., `i+1` vs `i+2`).
*   **Forgetting Memoization**: Implementing the recursive solution without storing results, leading to exponential time complexity.
*   **Incorrectly Handling the "Cannot Rob Adjacent" Rule**: Not ensuring that if house `i` is robbed, house `i+1` is skipped.

## Complexity Analysis
- Time: O(n) - Each subproblem `func(nums, i)` is computed only once due to memoization. There are `n` such subproblems, and each takes constant time (excluding recursive calls).
- Space: O(n) - For the memoization array `memo` of size `n`, and O(n) for the recursion call stack in the worst case.

## Commented Code
```java
class Solution {
    // Declare a memoization array to store computed results.
    int[] memo;

    // The main function to initiate the robbing process.
    public int rob(int[] nums) {
        // Initialize the memo array with a sentinel value (-1) indicating uncomputed states.
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        // Start the recursive calculation from the first house (index 0).
        return func(nums, 0);
    }

    // Recursive helper function to calculate the maximum amount that can be robbed from house 'i' onwards.
    public int func(int[] nums, int i) {
        // Base case: If the current index is out of bounds, no more houses to rob, return 0.
        if (i >= nums.length) return 0;
        // Memoization check: If the result for this index 'i' is already computed, return it.
        if (memo[i] != -1) return memo[i];

        // Option 1: Skip the current house 'i'. The maximum amount is whatever we can get from the next house (i+1).
        int skip_current = func(nums, i + 1);
        // Option 2: Rob the current house 'i'. We get nums[i] money, but we cannot rob the next house (i+1), so we jump to (i+2).
        int rob_current = nums[i] + func(nums, i + 2);

        // Store the maximum of the two options (skipping or robbing the current house) in the memo array for future use.
        memo[i] = Math.max(skip_current, rob_current);

        // Return the computed maximum amount for the current state.
        return memo[i];
    }
}
```

## Interview Tips
*   **Explain the DP State**: Clearly articulate what `dp[i]` or `memo[i]` represents (e.g., "the maximum amount that can be robbed from house `i` to the end").
*   **Walk Through an Example**: Use a small array like `[1, 2, 3, 1]` to trace the recursive calls and memoization updates.
*   **Discuss Trade-offs**: Mention the difference between memoization (top-down) and tabulation (bottom-up) approaches.
*   **Consider Edge Cases**: Discuss what happens with an empty array or an array with a single element.

## Revision Checklist
- [ ] Understand the problem constraints (no adjacent houses).
- [ ] Identify the overlapping subproblems.
- [ ] Define the DP state or recursive relation.
- [ ] Implement the base cases correctly.
- [ ] Implement the recursive/iterative step.
- [ ] Apply memoization or tabulation.
- [ ] Analyze time and space complexity.

## Similar Problems
*   House Robber II
*   House Robber III
*   Coin Change
*   Climbing Stairs

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization`
