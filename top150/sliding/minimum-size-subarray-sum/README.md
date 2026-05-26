# Minimum Size Subarray Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Sliding Window` `Prefix Sum`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i=0;
        int j = 1;
        int min = Integer.MAX_VALUE;
        int curr = nums[i];
        while(i<n){
            while(curr >= target){
                min = Math.min(min,j-i);
                curr -= nums[i++];
            }
            if(j>=n) break;
            curr += nums[j];
            j++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```

---

---
## Quick Revision
Find the smallest contiguous subarray whose sum is greater than or equal to a given target.
This is solved efficiently using the sliding window technique.

## Intuition
The core idea is to maintain a "window" of elements in the array. We expand this window by moving its right boundary until the sum of elements within the window meets or exceeds the target. Once the target is met, we try to shrink the window from the left to find the smallest possible subarray that still satisfies the condition. This process continues until the right boundary reaches the end of the array.

## Algorithm
1. Initialize two pointers, `left` (or `i`) and `right` (or `j`), to the start of the array (index 0).
2. Initialize `current_sum` to 0.
3. Initialize `min_length` to infinity (or `Integer.MAX_VALUE` in Java) to store the minimum length found so far.
4. Iterate with the `right` pointer from the beginning to the end of the array:
    a. Add the element at `nums[right]` to `current_sum`.
    b. While `current_sum` is greater than or equal to `target`:
        i. Update `min_length` with the minimum of its current value and the current window size (`right - left + 1`).
        ii. Subtract `nums[left]` from `current_sum`.
        iii. Increment the `left` pointer.
    c. Increment the `right` pointer.
5. If `min_length` remains infinity, it means no subarray was found, so return 0. Otherwise, return `min_length`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processes contiguous subarrays by dynamically adjusting window boundaries.
*   **Two Pointers:** Used to manage the start and end of the sliding window.
*   **Greedy Approach:** At each step, we make the locally optimal choice (shrinking the window) hoping to find the globally optimal solution (minimum length).

## Common Mistakes
*   **Incorrect Window Boundary Updates:** Forgetting to increment `left` or `right` pointers at the correct times.
*   **Not Handling the Case Where No Subarray Meets the Target:** Failing to return 0 when `min_length` remains at its initial infinite value.
*   **Off-by-One Errors in Length Calculation:** Calculating window size as `right - left` instead of `right - left + 1`.
*   **Integer Overflow:** If sums can become very large, consider using `long` for `current_sum`.

## Complexity Analysis
*   **Time:** O(n) - The `right` pointer iterates through the array once, and the `left` pointer also traverses at most `n` elements in total across all iterations.
*   **Space:** O(1) - We only use a few extra variables to store pointers, sum, and minimum length.

## Commented Code
```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length; // Get the length of the input array.
        int i = 0; // Initialize the left pointer of the sliding window.
        int j = 0; // Initialize the right pointer of the sliding window.
        int min = Integer.MAX_VALUE; // Initialize minimum length to a very large value.
        int curr = 0; // Initialize the current sum of the window.

        // Iterate through the array with the right pointer 'j'.
        while (j < n) {
            // Add the current element to the window sum.
            curr += nums[j];

            // While the current sum is greater than or equal to the target,
            // try to shrink the window from the left to find the minimum length.
            while (curr >= target) {
                // Update the minimum length found so far.
                // The length of the current window is (j - i + 1).
                min = Math.min(min, j - i + 1);
                // Subtract the element at the left pointer from the current sum.
                curr -= nums[i];
                // Move the left pointer to the right to shrink the window.
                i++;
            }
            // Move the right pointer to the right to expand the window.
            j++;
        }

        // If min is still Integer.MAX_VALUE, it means no subarray was found.
        // Otherwise, return the minimum length found.
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```

## Interview Tips
*   Clearly explain the sliding window approach and why it's suitable for this problem.
*   Walk through an example with the interviewer, showing how the `left` and `right` pointers move and how `current_sum` and `min_length` are updated.
*   Discuss edge cases: empty array, target being larger than the sum of all elements, target being 0 or negative (though problem constraints usually prevent this).
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement: find the minimum length contiguous subarray with sum >= target.
- [ ] Identify the sliding window pattern.
- [ ] Implement the two pointers (`left`, `right`).
- [ ] Maintain `current_sum`.
- [ ] Update `min_length` when `current_sum >= target`.
- [ ] Shrink the window by moving `left` pointer.
- [ ] Handle the case where no such subarray exists (return 0).
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 187: Repeated DNA Sequences (uses sliding window with hash map)
*   LeetCode 3: Longest Substring Without Repeating Characters (classic sliding window)
*   LeetCode 209: Minimum Size Subarray Sum (this problem)
*   LeetCode 713: Subarray Product Less Than K

## Tags
`Array` `Two Pointers` `Sliding Window`
