# Maximum Subarray

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i : nums){
            sum+=i;
            max = Math.max(sum,max);
            if(sum<0)sum = 0;
        }
        return max;
    }
}
```

---

---
## Quick Revision
Find the contiguous subarray within an array that has the largest sum.
This is solved using Kadane's Algorithm, which iterates through the array maintaining a current sum and the maximum sum found so far.

## Intuition
The core idea is that if at any point our current sum becomes negative, it's better to "reset" and start a new subarray from the next element. A negative prefix will only decrease the sum of any subsequent subarray. We also need to keep track of the overall maximum sum encountered across all possible subarrays.

## Algorithm
1. Initialize `max_so_far` to the smallest possible integer value (to handle cases with all negative numbers).
2. Initialize `current_max` to 0.
3. Iterate through each number `num` in the input array `nums`:
    a. Add `num` to `current_max`.
    b. Update `max_so_far` to be the maximum of `max_so_far` and `current_max`.
    c. If `current_max` becomes negative, reset `current_max` to 0. This signifies that starting a new subarray from the next element would be more beneficial than extending the current one.
4. Return `max_so_far`.

## Concept to Remember
*   **Dynamic Programming (Implicit):** Kadane's algorithm can be seen as a simple form of DP where the "state" is the maximum sum ending at the current position.
*   **Greedy Approach:** At each step, we make the locally optimal choice (either extend the current subarray or start a new one) which leads to the globally optimal solution.
*   **Handling Negative Numbers:** The algorithm correctly handles arrays with negative numbers by resetting the `current_max` when it drops below zero.

## Common Mistakes
*   **Not handling all negative numbers:** If the array contains only negative numbers, the algorithm must return the largest (least negative) number, not 0. The provided solution correctly handles this by initializing `max` to `Integer.MIN_VALUE`.
*   **Incorrectly resetting `current_max`:** Resetting `current_max` to 0 *after* updating `max_so_far` is crucial. If reset happens before, the maximum sum might be missed.
*   **Forgetting to update `max_so_far` in every iteration:** The maximum sum could end at any point in the array, so `max_so_far` must be updated in each step.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the input array `nums` exactly once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `max` and `sum`, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize max to the smallest possible integer value. This is crucial for cases where all numbers are negative.
        int max = Integer.MIN_VALUE;
        // Initialize sum to 0. This variable will keep track of the current subarray sum.
        int sum = 0;
        // Iterate through each number in the input array nums.
        for(int i : nums){
            // Add the current number to the running sum.
            sum+=i;
            // Update max if the current sum is greater than the maximum sum found so far.
            max = Math.max(sum,max);
            // If the current sum becomes negative, reset it to 0. This is the core of Kadane's algorithm: a negative prefix won't help maximize future sums.
            if(sum<0)sum = 0;
        }
        // Return the maximum subarray sum found.
        return max;
    }
}
```

## Interview Tips
*   **Explain Kadane's Algorithm:** Clearly articulate the logic behind resetting the `current_max` when it becomes negative.
*   **Walk Through Examples:** Use a small example array (e.g., `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`) to demonstrate how the `max` and `sum` variables change.
*   **Discuss Edge Cases:** Specifically mention how the algorithm handles arrays with all negative numbers and arrays with a single element.
*   **Ask Clarifying Questions:** If unsure about constraints (e.g., array size, range of numbers), ask the interviewer.

## Revision Checklist
- [ ] Understand the problem statement: find the contiguous subarray with the largest sum.
- [ ] Recall Kadane's Algorithm: `current_max` and `max_so_far`.
- [ ] Implement the logic for updating `current_max` and `max_so_far`.
- [ ] Ensure correct initialization of `max_so_far` for negative numbers.
- [ ] Verify the reset condition for `current_max`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Product Subarray
*   Best Time to Buy and Sell Stock (various versions)
*   Container With Most Water

## Tags
`Array` `Dynamic Programming` `Greedy`
