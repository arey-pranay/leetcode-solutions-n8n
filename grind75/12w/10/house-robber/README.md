# House Robber

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    int[] memo;
    public int rob(int[] nums){
        arr = nums;
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return func(0);
    }
    public int func(int i){
      if(i >= arr.length) return 0;
      if(memo[i]!=-1) return memo[i];
      return memo[i] = Math.max(arr[i]+func(i+2),func(i+1));
    }
}
```

---

---
## Quick Revision
You cannot rob adjacent houses. Find the maximum amount of money you can rob.
This is solved using dynamic programming with memoization or tabulation.

## Intuition
The core idea is that for any given house, we have two choices: either rob it or don't.
If we rob house `i`, we cannot rob house `i+1`, so our maximum loot would be `nums[i]` plus the maximum loot from house `i+2` onwards.
If we don't rob house `i`, our maximum loot would be the same as the maximum loot from house `i+1` onwards.
We want to take the maximum of these two choices at each step. This naturally leads to a recursive structure that can be optimized with dynamic programming.

## Algorithm
1. **Define a recursive function `func(i)`**: This function will return the maximum amount of money that can be robbed starting from house `i` up to the end of the houses.
2. **Base Case**: If `i` is out of bounds (i.e., `i >= nums.length`), it means there are no more houses to rob, so return `0`.
3. **Memoization**: If the result for `func(i)` has already been computed and stored in a `memo` array, return the stored value.
4. **Recursive Step**: Calculate two possibilities:
    * **Rob house `i`**: The loot is `nums[i]` plus the maximum loot from `func(i+2)` (since we skip `i+1`).
    * **Don't rob house `i`**: The loot is the maximum loot from `func(i+1)`.
5. **Store and Return**: Take the maximum of these two possibilities, store it in `memo[i]`, and return it.
6. **Initial Call**: Call `func(0)` to start the process from the first house.

## Concept to Remember
*   **Dynamic Programming (DP)**: Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Recursion with Memoization**: A top-down DP approach where recursive calls are augmented with a cache (memoization table) to store results of subproblems.
*   **Decision Making**: At each step, a choice (rob or not rob) leads to different future states.

## Common Mistakes
*   **Incorrect Base Case**: Not handling the `i >= nums.length` condition properly, leading to infinite recursion or index out of bounds.
*   **Skipping Logic Error**: Incorrectly calculating the next recursive calls (e.g., `i+1` instead of `i+2` when robbing).
*   **Forgetting Memoization**: Implementing the recursive solution without memoization, resulting in exponential time complexity due to redundant calculations.
*   **Array Indexing Errors**: Off-by-one errors when accessing `nums` or `memo` arrays.

## Complexity Analysis
*   **Time**: O(N) - Each house's maximum loot is computed exactly once due to memoization. The `func` is called for each index from 0 to N-1.
*   **Space**: O(N) - For the `memo` array used to store the results of subproblems. The recursion depth can also go up to N in the worst case, contributing to the call stack space.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for utility functions like fill.

class Solution {
    int[] arr; // Declare an instance variable to hold the input array of house values.
    int[] memo; // Declare an instance variable for the memoization table.

    public int rob(int[] nums){ // The main function that initiates the robbing process.
        arr = nums; // Assign the input array to the instance variable.
        memo = new int[nums.length]; // Initialize the memoization array with the same size as nums.
        Arrays.fill(memo,-1); // Fill the memoization array with -1 to indicate that no results have been computed yet.
        return func(0); // Start the recursive calculation from the first house (index 0).
    }

    public int func(int i){ // Recursive helper function to calculate max loot starting from house i.
      if(i >= arr.length) return 0; // Base case: If the current index is out of bounds, no more money can be robbed, return 0.
      if(memo[i]!=-1) return memo[i]; // Memoization check: If the result for this index is already computed, return it.
      // Recursive step: Calculate the maximum of two options:
      // 1. Rob house i: Add arr[i] to the max loot from house i+2 (skipping i+1).
      // 2. Don't rob house i: Take the max loot from house i+1.
      return memo[i] = Math.max(arr[i]+func(i+2),func(i+1)); // Store the computed maximum in memo[i] and return it.
    }
}
```

## Interview Tips
*   **Explain the DP State**: Clearly articulate what `dp[i]` or `func(i)` represents (e.g., "maximum loot from house `i` onwards").
*   **Walk Through an Example**: Use a small `nums` array (e.g., `[1, 2, 3, 1]`) to trace the recursive calls and memoization updates.
*   **Discuss Trade-offs**: Mention the difference between top-down (memoization) and bottom-up (tabulation) DP approaches and their space/time complexities.
*   **Consider Edge Cases**: Discuss what happens with an empty array or an array with a single element.

## Revision Checklist
- [ ] Understand the problem constraints (no adjacent houses).
- [ ] Identify the overlapping subproblems.
- [ ] Define the DP state correctly.
- [ ] Implement the base cases for the recursion/iteration.
- [ ] Implement the recurrence relation.
- [ ] Apply memoization or tabulation.
- [ ] Analyze time and space complexity.
- [ ] Test with edge cases (empty array, single element array).

## Similar Problems
*   House Robber II
*   House Robber III
*   Coin Change
*   Climbing Stairs

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization`
