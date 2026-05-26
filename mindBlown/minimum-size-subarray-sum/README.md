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
The core idea is to maintain a "window" of elements in the array and expand it until its sum meets or exceeds the target. Once the sum is sufficient, we try to shrink the window from the left to find the smallest possible subarray that still satisfies the condition. This process is repeated by sliding the window across the entire array.

## Algorithm
1. Initialize two pointers, `left` (or `i`) and `right` (or `j`), to the start of the array (index 0).
2. Initialize `currentSum` to 0 and `minLength` to infinity (or `Integer.MAX_VALUE`).
3. Iterate with the `right` pointer from the beginning to the end of the array:
    a. Add the element at `nums[right]` to `currentSum`.
    b. While `currentSum` is greater than or equal to `target`:
        i. Update `minLength` with the minimum of its current value and the current window size (`right - left + 1`).
        ii. Subtract `nums[left]` from `currentSum`.
        iii. Increment the `left` pointer.
    c. Increment the `right` pointer.
4. After the loop, if `minLength` is still infinity, it means no such subarray was found, so return 0. Otherwise, return `minLength`.

## Concept to Remember
*   **Sliding Window Technique:** An efficient approach for problems involving contiguous subarrays or substrings where you maintain a "window" and move its boundaries.
*   **Two Pointers:** Used to define the start and end of the sliding window.
*   **Greedy Approach:** At each step, we make the locally optimal choice (shrinking the window when possible) to achieve the globally optimal solution (minimum length).

## Common Mistakes
*   **Incorrectly initializing `minLength`:** Using 0 instead of a very large value can lead to incorrect results if the smallest valid subarray has a length greater than 0.
*   **Off-by-one errors in window size calculation:** Ensure `right - left + 1` is used for the window length.
*   **Not handling the case where no subarray meets the target:** Forgetting to return 0 when `minLength` remains at its initial large value.
*   **Infinite loops:** Incorrectly managing the `left` and `right` pointer movements, especially within the inner `while` loop.

## Complexity Analysis
*   Time: O(n) - The `right` pointer iterates through the array once, and the `left` pointer also moves at most `n` times in total. Each element is added and subtracted from `currentSum` at most once.
*   Space: O(1) - We only use a few extra variables to store pointers, current sum, and minimum length, which do not depend on the input size.

## Commented Code
```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length; // Get the length of the input array.
        int i = 0; // Initialize the left pointer of the sliding window.
        int j = 0; // Initialize the right pointer of the sliding window.
        int min = Integer.MAX_VALUE; // Initialize minimum length to a very large value.
        int curr = 0; // Initialize the current sum of the window.

        // Iterate through the array with the right pointer (j).
        while (j < n) {
            // Add the current element to the window sum.
            curr += nums[j];

            // While the current sum is greater than or equal to the target,
            // we have a potential candidate subarray.
            while (curr >= target) {
                // Update the minimum length found so far.
                // The length of the current window is (j - i + 1).
                min = Math.min(min, j - i + 1);

                // Shrink the window from the left by removing the leftmost element.
                curr -= nums[i];
                // Move the left pointer to the right.
                i++;
            }
            // Expand the window by moving the right pointer to the right.
            j++;
        }

        // If min is still Integer.MAX_VALUE, it means no subarray met the target.
        // In this case, return 0. Otherwise, return the minimum length found.
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```

## Interview Tips
*   Clearly explain the sliding window approach and why it's suitable for this problem.
*   Walk through an example with the interviewer, showing how the pointers and sum change.
*   Be mindful of edge cases: empty array, target being 0, or no subarray summing up to the target.
*   Discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement: find the smallest contiguous subarray with sum >= target.
- [ ] Identify the sliding window pattern.
- [ ] Implement the two pointers (`left`, `right`).
- [ ] Maintain `currentSum` and `minLength`.
- [ ] Correctly update `minLength` when `currentSum >= target`.
- [ ] Correctly shrink the window by moving `left` pointer.
- [ ] Handle the case where no valid subarray exists (return 0).
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 209: Minimum Size Subarray Sum (This problem)
*   LeetCode 3: Longest Substring Without Repeating Characters
*   LeetCode 76: Minimum Window Substring
*   LeetCode 1876: Substrings of Size Three with Distinct Characters

## Tags
`Array` `Sliding Window` `Two Pointers` `Greedy`
