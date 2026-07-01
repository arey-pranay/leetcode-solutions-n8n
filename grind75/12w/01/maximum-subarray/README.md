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
This is solved using Kadane's Algorithm, which iterates through the array, maintaining a current sum and the maximum sum found so far.

## Intuition
The core idea is that if at any point our current running sum becomes negative, it's better to discard it and start a new subarray from the next element. This is because a negative sum will only decrease any future positive sum. We also need to keep track of the overall maximum sum encountered, as the maximum subarray might end before the array itself does.

## Algorithm
1. Initialize `maxSum` to the smallest possible integer value (to handle cases with all negative numbers).
2. Initialize `currentSum` to 0.
3. Iterate through each number `num` in the input array `nums`:
    a. Add `num` to `currentSum`.
    b. Update `maxSum` to be the maximum of `maxSum` and `currentSum`.
    c. If `currentSum` becomes negative, reset `currentSum` to 0. This signifies starting a new potential subarray from the next element.
4. Return `maxSum`.

## Concept to Remember
*   **Dynamic Programming (Implicit):** Kadane's algorithm can be viewed as a simple form of DP where the optimal solution for a subarray ending at index `i` depends on the optimal solution ending at `i-1`.
*   **Greedy Approach:** At each step, we make the locally optimal choice (either extend the current subarray or start a new one) which leads to the globally optimal solution.
*   **Handling Negative Numbers:** The logic to reset `currentSum` to 0 when it becomes negative is crucial for correctly handling arrays with negative numbers.

## Common Mistakes
*   **Not handling all negative numbers:** If the array contains only negative numbers, the algorithm must return the largest (least negative) single number, not 0. The provided solution correctly handles this by initializing `max` to `Integer.MIN_VALUE`.
*   **Incorrectly resetting `currentSum`:** Resetting `currentSum` only when it's negative, not when it's zero or positive but less than the current element, can lead to errors. The logic `if(sum<0)sum = 0;` is correct.
*   **Forgetting to update `maxSum` before resetting `currentSum`:** The maximum sum must be recorded *before* `currentSum` is reset, as the maximum subarray might end at the current element.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the input array `nums` exactly once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `max` and `sum`, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize max to the smallest possible integer value.
        // This ensures that even if all numbers are negative, we correctly capture the largest negative number.
        int max = Integer.MIN_VALUE;
        // Initialize sum to 0. This variable will store the sum of the current contiguous subarray.
        int sum = 0;
        // Iterate through each number in the input array.
        for(int i : nums){
            // Add the current number to the running sum.
            sum+=i;
            // Update the maximum sum found so far if the current sum is greater.
            max = Math.max(sum,max);
            // If the current sum becomes negative, it means this subarray is detrimental to future sums.
            // So, we reset the sum to 0, effectively starting a new potential subarray from the next element.
            if(sum<0)sum = 0;
        }
        // Return the overall maximum sum found.
        return max;
    }
}
```

## Interview Tips
*   **Explain Kadane's Algorithm:** Clearly articulate the logic behind resetting the `currentSum` when it becomes negative.
*   **Edge Cases:** Discuss how you handle arrays with all negative numbers, single-element arrays, and empty arrays (though LeetCode constraints usually prevent empty arrays).
*   **Walkthrough:** Be prepared to walk through an example array (e.g., `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`) to demonstrate how your variables change.
*   **Complexity:** Be ready to justify your O(n) time and O(1) space complexity.

## Revision Checklist
- [ ] Understand the problem statement: find the contiguous subarray with the largest sum.
- [ ] Recall Kadane's Algorithm: `currentSum` and `maxSum`.
- [ ] Implement the logic for updating `currentSum` and `maxSum`.
- [ ] Implement the condition to reset `currentSum` when it's negative.
- [ ] Consider edge cases: all negative numbers, single element.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Product Subarray
*   Best Time to Buy and Sell Stock (variants)
*   Container With Most Water

## Tags
`Array` `Dynamic Programming` `Greedy`
