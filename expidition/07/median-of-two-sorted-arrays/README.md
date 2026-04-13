# Median Of Two Sorted Arrays

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Divide and Conquer`  
**Time:** O(log(min(m, n)  
**Space:** O(log(min(m, n)

---

## Solution (java)

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array to keep cut1 in valid range
        if (nums1.length > nums2.length) {
            ret urn findMedianSortedArrays(nums2, nums1);
        }

        // the merged element array has n+m = o size. So the left side has ls=o/2 size.
        int o = nums1.length + nums2.length;
        int ls = o / 2;

        // binary search cut2 on the smaller array (nums1)
        int lo = 0, hi = nums1.length;

        while (lo <= hi) {
            int cut1 = (lo + hi) / 2;
            int cut2 = ls - cut1;

            // use INT boundaries to handle edge cases where cut is at 0 or end of array
            int l1 = (cut1 == 0)            ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = (cut2 == 0)            ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            // valid cut: largest on left side of both arrays <= smallest on right side
            if (l1 <= r2 && l2 <= r1) {
                if (o % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.min(r1, r2);
                }
            }
            // l1 too large: shift cut1 left
            if (l1 > r2) {
                hi = cut1 - 1;
            }
            // l2 too large: shift cut1 right
            else {
                lo = cut1 + 1;
            }
        }

        return -1;
    }
}

// the goal is to simulate what would be the middle element or middle 2 elements
// Suppose you stand at the midpoint of the merged array
// the left side of the merged array has some elements from nums1 and some from nums2
// the merged element array has n+m = o size. So the left side has ls=o/2 size.
// we need to find which subarray will be contributed by which array.
// let's assume nums2 contributes cut2= nums2/2 elements and nums1 contributes the remaining cut1=(o- nums2/2)
// the largest element from nums1 subarray nums1[cut1] needs to be less than nums2[cuts2+1]
// the largest element from nums2 subarray nums2[cut2] needs to be less than nums1[cuts1+1]
// if that is the case, then cuts are valid and contributions are reasonable
// otherwise if nums1[cut1] > nums2[cut2+1] then cut1 needs to shift left or cut2 needs to shift right
// if nums2[cut2] > nums1[cut1] cut2 needs to shift left or cut1 needs to shift right ✅
// the bottomline being contribution by nums1 contributoin by nums2 must be equal to o
// once you find a valid cut for both arrays, consider elements from left of cut1 and left of cut2
// they will contribute to left part of merged array. Take max(nums1[cut1],nums[cut2]) to find left subarray contribution to median
// then get min(nums1[cut1+1],nums2[cut2+1]) is the contribution of right subarray of the merged array, in median calculation/
// adjust according to even or odd value of o.

```

---

---
## Quick Revision
Find the median of two sorted arrays by partitioning them such that elements on the left of partitions are less than or equal to elements on the right.
This is achieved using binary search on the smaller array to efficiently find the correct partition points.

## Intuition
The core idea is to find a "cut" in both arrays such that all elements to the left of the cuts are smaller than or equal to all elements to the right of the cuts. If we can achieve this, the median will be determined by the elements immediately surrounding these cuts. We can use binary search to efficiently find these optimal cut points. The "aha moment" is realizing that we don't need to merge the arrays; we only need to find the correct partition.

## Algorithm
1.  Ensure `nums1` is the shorter array. If not, swap `nums1` and `nums2`. This optimizes the binary search range.
2.  Calculate the total length `o` of both arrays and the desired size of the left partition `ls = o / 2`.
3.  Initialize binary search pointers `lo = 0` and `hi = nums1.length` for `nums1`.
4.  Enter a `while` loop that continues as long as `lo <= hi`.
5.  Inside the loop, calculate `cut1` (partition point for `nums1`) as the midpoint of `lo` and `hi`.
6.  Calculate `cut2` (partition point for `nums2`) as `ls - cut1`. This ensures the total number of elements to the left of `cut1` and `cut2` is `ls`.
7.  Determine the four boundary elements:
    *   `l1`: The element just before `cut1` in `nums1`. Use `Integer.MIN_VALUE` if `cut1` is 0.
    *   `l2`: The element just before `cut2` in `nums2`. Use `Integer.MIN_VALUE` if `cut2` is 0.
    *   `r1`: The element at `cut1` in `nums1`. Use `Integer.MAX_VALUE` if `cut1` is `nums1.length`.
    *   `r2`: The element at `cut2` in `nums2`. Use `Integer.MAX_VALUE` if `cut2` is `nums2.length`.
8.  Check if the partition is valid: `l1 <= r2` AND `l2 <= r1`.
    *   If valid:
        *   If `o` is even, the median is the average of `max(l1, l2)` and `min(r1, r2)`.
        *   If `o` is odd, the median is `min(r1, r2)`.
        *   Return the calculated median.
    *   If `l1 > r2`: The partition in `nums1` is too far to the right. Adjust `hi = cut1 - 1`.
    *   If `l2 > r1`: The partition in `nums1` is too far to the left. Adjust `lo = cut1 + 1`.
9.  If the loop finishes without finding a valid partition (should not happen with correct logic), return -1 or throw an exception.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted space by repeatedly dividing the search interval in half.
*   **Partitioning:** Dividing a data structure into two parts based on a condition.
*   **Edge Case Handling:** Using sentinel values (like `Integer.MIN_VALUE`, `Integer.MAX_VALUE`) to simplify logic for boundary conditions.
*   **Median Definition:** Understanding how to calculate the median for both even and odd total numbers of elements.

## Common Mistakes
*   **Not handling edge cases:** Forgetting to use `Integer.MIN_VALUE` and `Integer.MAX_VALUE` when a partition is at the beginning or end of an array, leading to index out of bounds or incorrect comparisons.
*   **Incorrectly calculating `cut2`:** If `cut1` is determined, `cut2` is fixed to maintain the total left partition size. Errors here break the logic.
*   **Swapping arrays:** Not ensuring the binary search is performed on the smaller array, which can lead to a larger search space and potentially incorrect `cut2` calculations if not handled carefully.
*   **Off-by-one errors in partition indices:** Confusing `cut` with `cut - 1` or `cut + 1` when accessing array elements.
*   **Incorrect median calculation for even/odd lengths:** Misapplying the `max(left)` and `min(right)` logic.

## Complexity Analysis
*   Time: O(log(min(m, n))) - The binary search is performed on the smaller of the two arrays. In each step, the search space is halved.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables, regardless of the input array sizes.

## Commented Code
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Ensure nums1 is the shorter array to optimize binary search range.
        // If nums1 is longer, recursively call with swapped arrays.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // 'o' is the total number of elements in the merged array.
        int o = nums1.length + nums2.length;
        // 'ls' is the desired number of elements in the left partition of the merged array.
        // For an even total, this is exactly half. For an odd total, this is the floor of half,
        // meaning the median element will be the smallest in the right partition.
        int ls = o / 2;

        // Binary search range for the partition point in nums1.
        // 'lo' is the minimum possible cut in nums1 (0 elements from nums1 in left partition).
        // 'hi' is the maximum possible cut in nums1 (all elements from nums1 in left partition).
        int lo = 0, hi = nums1.length;

        // Perform binary search to find the correct partition.
        while (lo <= hi) {
            // 'cut1' is the proposed partition point in nums1. It represents the number of elements
            // from nums1 that will be in the left partition of the merged array.
            int cut1 = (lo + hi) / 2;
            // 'cut2' is the corresponding partition point in nums2. It's calculated to ensure
            // that the total number of elements in the left partition (cut1 + cut2) equals 'ls'.
            int cut2 = ls - cut1;

            // Determine the four boundary elements around the cuts.
            // 'l1' is the rightmost element of the left partition from nums1.
            // If cut1 is 0, it means no elements from nums1 are in the left partition, so use MIN_VALUE.
            int l1 = (cut1 == 0)            ? Integer.MIN_VALUE : nums1[cut1 - 1];
            // 'l2' is the rightmost element of the left partition from nums2.
            // If cut2 is 0, use MIN_VALUE.
            int l2 = (cut2 == 0)            ? Integer.MIN_VALUE : nums2[cut2 - 1];
            // 'r1' is the leftmost element of the right partition from nums1.
            // If cut1 is nums1.length, it means all elements of nums1 are in the left partition,
            // so use MAX_VALUE for the right partition's start.
            int r1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];
            // 'r2' is the leftmost element of the right partition from nums2.
            // If cut2 is nums2.length, use MAX_VALUE.
            int r2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];

            // Check if the current partitions are valid.
            // A valid partition means:
            // 1. The largest element in the left partition of nums1 (l1) is less than or equal to
            //    the smallest element in the right partition of nums2 (r2).
            // 2. The largest element in the left partition of nums2 (l2) is less than or equal to
            //    the smallest element in the right partition of nums1 (r1).
            if (l1 <= r2 && l2 <= r1) {
                // If the total number of elements is even, the median is the average of the
                // two middle elements: the largest of the left partitions and the smallest of the right partitions.
                if (o % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
                // If the total number of elements is odd, the median is the single middle element,
                // which is the smallest element in the right partition.
                else {
                    return Math.min(r1, r2);
                }
            }
            // If l1 is greater than r2, it means the partition in nums1 is too far to the right.
            // We need to move the cut in nums1 to the left.
            else if (l1 > r2) {
                // Adjust the binary search range for nums1: search in the left half.
                hi = cut1 - 1;
            }
            // If l2 is greater than r1, it means the partition in nums1 is too far to the left.
            // We need to move the cut in nums1 to the right.
            else { // l2 > r1
                // Adjust the binary search range for nums1: search in the right half.
                lo = cut1 + 1;
            }
        }

        // This return statement should ideally not be reached if the input arrays are sorted.
        // It's a fallback for unexpected scenarios.
        return -1;
    }
}
```

## Interview Tips
1.  **Explain the Partitioning Logic First:** Before diving into code, clearly explain the concept of partitioning both arrays and the condition for a valid partition (`l1 <= r2 && l2 <= r1`). This shows you understand the core idea.
2.  **Handle Edge Cases Explicitly:** Emphasize how you're using `Integer.MIN_VALUE` and `Integer.MAX_VALUE` to manage cases where a partition is at the array's beginning or end. This is a common pitfall.
3.  **Justify Binary Search on Smaller Array:** Explain why performing binary search on the smaller array is crucial for optimizing the time complexity to `O(log(min(m, n)))`.
4.  **Trace with an Example:** Be prepared to walk through a small example (e.g., `nums1 = [1, 3]`, `nums2 = [2]`) to demonstrate how the `cut1`, `cut2`, and boundary elements are calculated and how the binary search converges.
5.  **Discuss Complexity:** Clearly state and justify the time and space complexity.

## Revision Checklist
- [ ] Understand the problem: Find the median of two sorted arrays.
- [ ] Recall the binary search approach on partitions.
- [ ] Ensure `nums1` is the smaller array.
- [ ] Correctly calculate `ls` (left partition size).
- [ ] Implement binary search for `cut1`.
- [ ] Correctly derive `cut2` from `cut1` and `ls`.
- [ ] Handle boundary elements (`l1`, `l2`, `r1`, `r2`) with sentinel values.
- [ ] Verify the partition condition: `l1 <= r2 && l2 <= r1`.
- [ ] Implement median calculation for even and odd total lengths.
- [ ] Adjust binary search bounds (`lo`, `hi`) based on partition validity.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find K-th Smallest Element in Two Sorted Arrays
*   Median of Two Sorted Arrays (this problem)
*   Merge Sorted Array

## Tags
`Binary Search` `Array` `Divide and Conquer`
