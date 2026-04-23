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
    public int findPeakElement(int[] nums){
        int left = 0;
        int right = nums.length-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid] < nums[mid+1]) left = mid+1;
            else right = mid;
        }
        return left;
        //agar array bdhti hi rahi to last element hai peak. agr bdhti nhi rahi to peak beech me aayega mid aur right ke
        //agar array kam hoti hi rahi to first element hai peak. agr kamti nhi rahi to peak beech me aayega mid aur left ke
    }
}
//  [1,2,1,3,5,6,4,2] 
```

---

---
## Quick Revision
Given an array `nums` where `nums[i] != nums[i+1]`, find any peak element.
A peak element is an element that is strictly greater than its neighbors. We can use binary search to efficiently find a peak.

## Intuition
The problem guarantees that `nums[i] != nums[i+1]`. This means that if we are at an element `nums[mid]` and `nums[mid] < nums[mid+1]`, then there *must* be a peak to the right of `mid` (including `mid+1`). Why? Because the array is increasing at `mid`. If it keeps increasing all the way to the end, the last element is a peak. If it starts decreasing at some point, that point where it starts decreasing will be a peak. Similarly, if `nums[mid] > nums[mid+1]`, there must be a peak to the left of or at `mid`. This observation is the core of the binary search approach.

## Algorithm
1. Initialize two pointers, `left` to the start of the array (index 0) and `right` to the end of the array (index `nums.length - 1`).
2. While `left` is less than `right`:
   a. Calculate the middle index: `mid = left + (right - left) / 2`. This prevents potential integer overflow.
   b. Compare `nums[mid]` with its right neighbor `nums[mid+1]`:
      i. If `nums[mid] < nums[mid+1]`: This means the peak must be in the right half (including `mid+1`), so update `left = mid + 1`.
      ii. If `nums[mid] > nums[mid+1]`: This means `nums[mid]` could be a peak, or the peak is in the left half (including `mid`), so update `right = mid`.
3. When the loop terminates, `left` will be equal to `right`, and this index will point to a peak element. Return `left`.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted or partially ordered data structure by repeatedly dividing the search interval in half.
*   **Monotonicity:** The property of an array where elements consistently increase or decrease. While the entire array isn't monotonic, segments can exhibit this behavior, which is exploited by the binary search.
*   **Edge Cases:** Handling arrays with one element, or scenarios where the peak is at the beginning or end.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly updating `left` or `right` pointers, leading to infinite loops or missing the peak. For example, using `right = mid - 1` when `nums[mid] > nums[mid+1]` would incorrectly discard `mid` as a potential peak.
*   **Integer Overflow:** Using `mid = (left + right) / 2` without considering the possibility of `left + right` exceeding the maximum integer value. The safer way is `mid = left + (right - left) / 2`.
*   **Not handling `nums[i] != nums[i+1]` constraint:** Assuming elements can be equal, which simplifies the logic but is not allowed by the problem statement.
*   **Returning the wrong index:** Returning `mid` directly instead of `left` (or `right`) after the loop, as the loop condition `left < right` ensures `left` converges to the peak index.

## Complexity Analysis
*   **Time:** O(log n) - The algorithm uses binary search, which halves the search space in each iteration.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `left`, `right`, and `mid`.

## Commented Code
```java
class Solution {
    public int findPeakElement(int[] nums){
        // Initialize the left pointer to the start of the array.
        int left = 0;
        // Initialize the right pointer to the end of the array.
        int right = nums.length - 1;

        // Continue the binary search as long as the left pointer is less than the right pointer.
        // This ensures we are always searching in a valid range and haven't converged to a single element.
        while(left < right){
            // Calculate the middle index. Using (right - left) / 2 prevents potential integer overflow.
            int mid = left + (right - left) / 2;

            // Compare the middle element with its right neighbor.
            // If nums[mid] is less than nums[mid+1], it means the array is increasing at mid.
            // Therefore, a peak must exist to the right of mid (including mid+1).
            // So, we discard the left half and move the left pointer to mid + 1.
            if(nums[mid] < nums[mid+1]) {
                left = mid + 1;
            }
            // If nums[mid] is greater than nums[mid+1], it means the array is decreasing at mid.
            // This implies that nums[mid] itself could be a peak, or a peak exists to its left.
            // So, we discard the right half (excluding mid) and move the right pointer to mid.
            // We keep mid because it might be the peak.
            else {
                right = mid;
            }
        }
        // When the loop terminates, left == right. This index points to a peak element.
        // This is because the loop invariant ensures that a peak always exists within the [left, right] range.
        // When left == right, the range contains only one element, which must be a peak.
        return left;
    }
}
```

## Interview Tips
*   **Explain the binary search logic clearly:** Emphasize why `nums[mid] < nums[mid+1]` implies a peak to the right and `nums[mid] > nums[mid+1]` implies a peak to the left or at `mid`.
*   **Discuss edge cases:** Mention how the algorithm handles arrays of size 1, or when the peak is at the boundaries. The problem statement implies `nums.length >= 1`.
*   **Clarify the `nums[i] != nums[i+1]` constraint:** Explain how this constraint simplifies the problem and guarantees a peak exists and the binary search logic works.
*   **Walk through an example:** Use `[1,2,1,3,5,6,4,2]` to demonstrate how `left`, `right`, and `mid` change in each iteration.

## Revision Checklist
- [ ] Understand the definition of a peak element.
- [ ] Recognize that binary search is applicable due to the problem's structure.
- [ ] Implement the binary search correctly with proper pointer updates.
- [ ] Handle potential integer overflow when calculating `mid`.
- [ ] Verify the time and space complexity.
- [ ] Practice explaining the intuition and algorithm.

## Similar Problems
*   [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
*   [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
*   [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

## Tags
`Array` `Binary Search`
