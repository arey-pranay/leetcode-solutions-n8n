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
        int ans = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i=0;i<nums.length;i++){
            currSum += nums[i];
            ans = Math.max(ans,currSum);
            if(currSum < 0) currSum = 0;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given an integer array, find the contiguous subarray with the largest sum.
This problem can be solved efficiently using Kadane's Algorithm, which iterates through the array maintaining a current sum and the maximum sum found so far.

## Intuition
The core idea is that if at any point our `currSum` becomes negative, it's better to discard it and start a new subarray from the next element. This is because a negative `currSum` will only decrease the sum of any future subarray it's part of. We also need to keep track of the overall maximum sum encountered (`ans`) because the maximum subarray might end before the array itself ends.

## Algorithm
1. Initialize `ans` (maximum sum found so far) to the smallest possible integer value (`Integer.MIN_VALUE`).
2. Initialize `currSum` (current sum of the subarray ending at the current position) to 0.
3. Iterate through the input array `nums` from the first element to the last.
4. In each iteration, add the current element `nums[i]` to `currSum`.
5. Update `ans` to be the maximum of `ans` and `currSum`. This ensures we capture the largest sum seen up to this point.
6. If `currSum` becomes negative, reset it to 0. This signifies that starting a new subarray from the next element would be more beneficial than extending the current one.
7. After the loop finishes, `ans` will hold the maximum sum of any contiguous subarray. Return `ans`.

## Concept to Remember
*   **Greedy Approach:** At each step, we make the locally optimal choice (discarding negative `currSum`) hoping it leads to a globally optimal solution.
*   **Dynamic Programming (Implicit):** The `currSum` at any point depends on the `currSum` of the previous point, exhibiting optimal substructure.
*   **Handling Negative Numbers:** The algorithm correctly handles arrays with all negative numbers by initializing `ans` to `Integer.MIN_VALUE` and updating it with `currSum` even if `currSum` is negative.

## Common Mistakes
*   Not initializing `ans` correctly (e.g., to 0), which fails for arrays with all negative numbers.
*   Forgetting to reset `currSum` to 0 when it becomes negative.
*   Not updating `ans` with `currSum` *before* checking if `currSum` is negative.
*   Misunderstanding the condition for resetting `currSum` (e.g., resetting if `currSum` is less than or equal to 0).

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the input array `nums` exactly once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `ans` and `currSum`, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize 'ans' to the smallest possible integer value to correctly handle cases with all negative numbers.
        int ans = Integer.MIN_VALUE;
        // Initialize 'currSum' to 0, representing the sum of the current contiguous subarray being considered.
        int currSum = 0;
        // Iterate through each element of the input array 'nums'.
        for(int i=0;i<nums.length;i++){
            // Add the current element to the 'currSum'. This extends the current subarray.
            currSum += nums[i];
            // Update 'ans' if the 'currSum' is greater than the current maximum sum found so far.
            ans = Math.max(ans,currSum);
            // If 'currSum' becomes negative, it means this subarray is detrimental to future sums.
            // Reset 'currSum' to 0 to start a new potential subarray from the next element.
            if(currSum < 0) currSum = 0;
        }
        // Return the maximum sum found across all contiguous subarrays.
        return ans;
    }
}
```

## Interview Tips
*   Explain Kadane's Algorithm clearly, focusing on the greedy choice of resetting `currSum`.
*   Walk through an example with both positive and negative numbers to demonstrate the logic.
*   Be prepared to discuss edge cases like an empty array (though LeetCode constraints usually prevent this) or an array with a single element.
*   Mention the connection to dynamic programming, even though the space is optimized to O(1).

## Revision Checklist
- [ ] Understand the problem statement: find max sum of a contiguous subarray.
- [ ] Recall Kadane's Algorithm: `currSum` and `ans`.
- [ ] Implement the initialization of `ans` to `Integer.MIN_VALUE`.
- [ ] Implement the logic for updating `currSum` and `ans`.
- [ ] Implement the condition for resetting `currSum` to 0.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm.

## Similar Problems
*   Maximum Product Subarray
*   Best Time to Buy and Sell Stock (various versions)
*   Container With Most Water

## Tags
`Array` `Dynamic Programming` `Greedy`
