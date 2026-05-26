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
Find the smallest contiguous subarray whose sum is at least the target.
This is solved efficiently using the sliding window technique.

## Intuition
The core idea is to maintain a "window" of elements in the array. We expand this window by moving its right boundary forward, adding elements to the current sum. Once the sum within the window meets or exceeds the target, we have a potential candidate subarray. To find the *minimum* size, we then try to shrink the window from the left, removing elements and updating the minimum length if the sum still meets the target. This process continues until the right boundary reaches the end of the array.

## Algorithm
1. Initialize two pointers, `left` (or `i`) and `right` (or `j`), to the start of the array (index 0).
2. Initialize `current_sum` to 0.
3. Initialize `min_length` to infinity (or `Integer.MAX_VALUE` in Java).
4. Iterate with the `right` pointer through the array:
    a. Add `nums[right]` to `current_sum`.
    b. While `current_sum` is greater than or equal to `target`:
        i. Update `min_length` with the minimum of its current value and the current window size (`right - left + 1`).
        ii. Subtract `nums[left]` from `current_sum`.
        iii. Increment `left`.
    c. Increment `right`.
5. If `min_length` is still infinity, it means no subarray met the target, so return 0. Otherwise, return `min_length`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing contiguous subarrays by dynamically adjusting window boundaries.
*   **Two Pointers:** Using multiple pointers to traverse and manage parts of a data structure (here, the array).
*   **Greedy Approach:** At each step, making the locally optimal choice (shrinking the window when possible) to achieve a globally optimal solution (minimum length).

## Common Mistakes
*   **Incorrect Window Boundary Updates:** Forgetting to increment `left` or `right` pointer correctly, or off-by-one errors in calculating window size.
*   **Not Handling the Case Where No Subarray Meets the Target:** Failing to return 0 when `min_length` remains at its initial infinite value.
*   **Inefficient Sum Calculation:** Re-calculating the sum of the window from scratch in each iteration instead of incrementally updating it.
*   **Integer Overflow:** While less likely with typical LeetCode constraints for `target` and `nums` values, it's a general consideration for sum-based problems.

## Complexity Analysis
*   **Time:** O(n) - The `right` pointer iterates through the array once. The `left` pointer also moves at most `n` times in total across all iterations. Therefore, each element is visited by both pointers at most twice.
*   **Space:** O(1) - We only use a few extra variables to store pointers, current sum, and minimum length, which do not depend on the input size.

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
            // Add the current element at 'j' to the window sum.
            curr += nums[j];

            // While the current sum is greater than or equal to the target,
            // we have a valid subarray. Try to shrink it from the left.
            while (curr >= target) {
                // Update the minimum length found so far.
                // The length of the current window is (j - i + 1).
                min = Math.min(min, j - i + 1);

                // Subtract the element at the left pointer 'i' from the sum.
                curr -= nums[i];
                // Move the left pointer 'i' one step to the right to shrink the window.
                i++;
            }
            // Move the right pointer 'j' one step to the right to expand the window.
            j++;
        }

        // If 'min' is still Integer.MAX_VALUE, it means no subarray met the target.
        // In this case, return 0. Otherwise, return the minimum length found.
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```

## Interview Tips
*   **Explain the Sliding Window:** Clearly articulate the concept of a sliding window and why it's suitable for this problem.
*   **Trace with an Example:** Walk through a small example array and target to demonstrate how your pointers and sum update.
*   **Edge Cases:** Discuss how you handle cases like an empty array, an array where no subarray sums to the target, or when the target itself is very large.
*   **Optimization:** Mention why this O(n) approach is better than a naive O(n^2) solution (checking all possible subarrays).

## Revision Checklist
- [ ] Understand the problem statement: find minimum length contiguous subarray with sum >= target.
- [ ] Recognize the sliding window pattern.
- [ ] Implement two pointers (`left`, `right`).
- [ ] Maintain `current_sum`.
- [ ] Update `min_length` when `current_sum >= target`.
- [ ] Shrink window by moving `left` pointer when `current_sum >= target`.
- [ ] Expand window by moving `right` pointer.
- [ ] Handle the case where no solution exists (return 0).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters (LeetCode 3)
*   Permutation in String (LeetCode 567)
*   Find All Anagrams in a String (LeetCode 438)
*   Subarray Product Less Than K (LeetCode 713)

## Tags
`Array` `Two Pointers` `Sliding Window`
