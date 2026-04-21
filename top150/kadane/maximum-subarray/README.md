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
1. Initialize `max_so_far` to the smallest possible integer value (to handle all negative numbers).
2. Initialize `current_max` to 0.
3. Iterate through each number `num` in the input array `nums`:
    a. Add `num` to `current_max`.
    b. Update `max_so_far` to be the maximum of `max_so_far` and `current_max`.
    c. If `current_max` becomes negative, reset `current_max` to 0.
4. Return `max_so_far`.

## Concept to Remember
*   Dynamic Programming: The problem can be viewed as finding the maximum subarray ending at each position.
*   Greedy Approach: At each step, we make the locally optimal choice (discarding negative sums) that leads to a globally optimal solution.
*   Handling Negative Numbers: The algorithm must correctly handle arrays containing only negative numbers.

## Common Mistakes
*   Not initializing `max_so_far` correctly: If initialized to 0, it won't work for arrays with all negative numbers.
*   Forgetting to reset `current_max` to 0 when it becomes negative: This would incorrectly carry over negative sums.
*   Not updating `max_so_far` at every step: The maximum subarray might end at any point, not necessarily at the end of the array.
*   Confusing `current_max` with `max_so_far`: `current_max` tracks the sum of the subarray ending at the current position, while `max_so_far` tracks the overall maximum.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the input array once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `max_so_far` and `current_max`.

## Commented Code
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize max to the smallest possible integer value.
        // This is crucial to correctly handle cases where all numbers are negative.
        int max = Integer.MIN_VALUE;
        // Initialize sum to 0. This variable will store the current sum of the subarray being considered.
        int sum = 0;
        // Iterate through each number in the input array nums.
        for(int i : nums){
            // Add the current number to the running sum.
            sum+=i;
            // Update the maximum sum found so far if the current sum is greater.
            max = Math.max(sum,max);
            // If the current sum becomes negative, it means starting a new subarray from the next element
            // will yield a larger or equal sum than continuing with the current negative sum.
            // So, reset the current sum to 0.
            if(sum<0)sum = 0;
        }
        // Return the overall maximum sum found.
        return max;
    }
}
```

## Interview Tips
*   Explain Kadane's Algorithm clearly, emphasizing the greedy choice of resetting the sum when it becomes negative.
*   Walk through an example with both positive and negative numbers to demonstrate the logic.
*   Be prepared to discuss edge cases like an empty array (though LeetCode constraints usually prevent this) or an array with all negative numbers.
*   Mention the dynamic programming perspective if asked for alternative approaches or deeper understanding.

## Revision Checklist
- [ ] Understand the problem statement: find the contiguous subarray with the largest sum.
- [ ] Recall Kadane's Algorithm: `current_max` and `max_so_far`.
- [ ] Implement the logic for updating `current_max` and `max_so_far`.
- [ ] Implement the condition for resetting `current_max` to 0.
- [ ] Ensure correct initialization of `max_so_far` for negative numbers.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Product Subarray
*   Best Time to Buy and Sell Stock (various versions)
*   Container With Most Water

## Tags
`Array` `Dynamic Programming` `Greedy`
