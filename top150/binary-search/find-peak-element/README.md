# Find Peak Element

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left<right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[mid+1]) left = mid + 1;
            else right = mid;
        }
        return left; 
    }
}
```

---

---
## Quick Revision
Given an array where adjacent elements are different, find any peak element.
A peak element is an element that is strictly greater than its neighbors. We can solve this efficiently using binary search.

## Intuition
The problem guarantees that `nums[-1] = nums[n] = -∞`. This means there must be at least one peak. If we are at an element `nums[mid]` and `nums[mid] < nums[mid+1]`, it implies that the peak must lie to the right of `mid` because the array is increasing at `mid`. Conversely, if `nums[mid] > nums[mid+1]`, then `mid` itself could be a peak, or a peak exists to its left because the array is decreasing at `mid`. This observation is the core of a binary search approach.

## Algorithm
1. Initialize two pointers, `left` to the start of the array (index 0) and `right` to the end of the array (index `nums.length - 1`).
2. While `left` is less than `right`:
   a. Calculate the middle index: `mid = left + (right - left) / 2`. This prevents potential integer overflow.
   b. Compare `nums[mid]` with its right neighbor `nums[mid+1]`.
   c. If `nums[mid] < nums[mid+1]`: This means the peak must be to the right of `mid` (inclusive of `mid+1`), so update `left = mid + 1`.
   d. If `nums[mid] >= nums[mid+1]`: This means `mid` could be a peak, or a peak exists to its left. So, we narrow down the search space to the left half, including `mid`: update `right = mid`.
3. When the loop terminates, `left` will be equal to `right`, and this index will point to a peak element. Return `left`.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted or partially ordered data structure by repeatedly dividing the search interval in half.
*   **Monotonicity:** The problem leverages the fact that if an element is smaller than its right neighbor, a peak must exist to its right. If it's larger, a peak must exist to its left or at the current position.
*   **Edge Cases:** Understanding the implicit `nums[-1] = nums[n] = -∞` is crucial for guaranteeing a peak exists and for the binary search logic.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly updating `left` or `right` pointers, especially when `mid` is involved. For example, setting `right = mid - 1` when `nums[mid] > nums[mid+1]` might exclude a valid peak at `mid`.
*   **Not handling `mid+1` boundary:** Forgetting to check if `mid+1` is within the array bounds, though the problem constraints and binary search logic usually prevent this from being an issue in this specific implementation.
*   **Incorrect loop condition:** Using `left <= right` instead of `left < right` can lead to an infinite loop or incorrect results when `left` and `right` converge.
*   **Returning the wrong index:** Returning `mid` directly instead of `left` (or `right`) after the loop terminates.

## Complexity Analysis
*   **Time:** O(log n) - The algorithm uses binary search, which halves the search space in each iteration.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `left`, `right`, and `mid`.

## Commented Code
```java
class Solution {
    public int findPeakElement(int[] nums) {
        // Initialize the left pointer to the start of the array.
        int left = 0;
        // Initialize the right pointer to the end of the array.
        int right = nums.length - 1;

        // Continue the loop as long as the left pointer is less than the right pointer.
        // This ensures we are always searching in a valid range and haven't converged.
        while (left < right) {
            // Calculate the middle index. Using (right - left) / 2 prevents potential integer overflow.
            int mid = left + (right - left) / 2;

            // Compare the middle element with its right neighbor.
            if (nums[mid] < nums[mid + 1]) {
                // If the middle element is smaller than its right neighbor,
                // it means a peak must exist to the right of mid (inclusive of mid+1).
                // So, we discard the left half and move the left pointer to mid + 1.
                left = mid + 1;
            } else {
                // If the middle element is greater than or equal to its right neighbor,
                // it means mid itself could be a peak, or a peak exists to its left.
                // We discard the right half (elements after mid) and keep mid in the search space.
                // So, we move the right pointer to mid.
                right = mid;
            }
        }
        // When the loop terminates, left == right. This index points to a peak element.
        // The problem guarantees that a peak always exists.
        return left;
    }
}
```

## Interview Tips
*   **Explain the binary search logic clearly:** Emphasize why `nums[mid] < nums[mid+1]` implies searching right, and `nums[mid] >= nums[mid+1]` implies searching left (or staying at `mid`).
*   **Discuss the implicit boundary conditions:** Mention `nums[-1] = nums[n] = -∞` and how it guarantees a peak exists and simplifies the binary search.
*   **Walk through an example:** Use a small array like `[1, 2, 3, 1]` or `[1, 2, 1, 3, 5, 6, 4]` to trace the `left`, `right`, and `mid` pointers.
*   **Be prepared to discuss alternative approaches:** While binary search is optimal, briefly mentioning a linear scan (O(n)) and why it's less efficient can show a broader understanding.

## Revision Checklist
- [ ] Understand the definition of a peak element.
- [ ] Recognize that binary search is applicable due to the problem's properties.
- [ ] Implement the binary search correctly with `left < right` condition.
- [ ] Correctly update `left` and `right` pointers based on `nums[mid]` vs `nums[mid+1]`.
- [ ] Understand why the final `left` (or `right`) index is the peak.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
*   [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
*   [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

## Tags
`Array` `Binary Search`
