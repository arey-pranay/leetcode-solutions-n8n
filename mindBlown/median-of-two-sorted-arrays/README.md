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
        int m = nums1.length;
        int n = nums2.length;
        if(m > n) return findMedianSortedArrays(nums2, nums1);
        int o = m+n;
        int ls = o/2;
        int lo = 0;
        int hi = m;
        while(lo<=hi){
            int cut1 = (lo+hi)/2;
            int cut2 = ls - cut1;
            
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE :nums2[cut2-1];
            int r1 = cut1 == m ? Integer.MAX_VALUE :nums1[cut1];
            int r2 = cut2 == n ? Integer.MAX_VALUE :nums2[cut2];
            
            if(l1 <=r2 && l2 <= r1){
                if(o%2 == 0) return (Math.max(l1,l2) + Math.min(r1,r2))/2.0;
                else return Math.min(r1,r2);
            }
            
            if(l1 > r2) hi = cut1-1;
            else lo = cut1+1;
            
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
Find the median of two sorted arrays by partitioning them into two halves such that elements in the left halves are less than or equal to elements in the right halves.
This is achieved using binary search on the smaller array to find the optimal partition point.

## Intuition
The core idea is to find a "partition" in both arrays such that if we merge the left parts of these partitions, we get the left half of the combined sorted array, and similarly for the right parts. If we can achieve this, the median will be determined by the boundary elements of these partitions. We can use binary search to efficiently find this optimal partition. The "aha moment" is realizing that we don't need to actually merge the arrays; we only need to find the correct split points.

## Algorithm
1.  Ensure `nums1` is the shorter array. If not, swap them. This optimizes the binary search range.
2.  Initialize `m` and `n` as the lengths of `nums1` and `nums2` respectively.
3.  Calculate the total length `o = m + n`.
4.  Determine the desired size of the left partition of the merged array: `ls = o / 2`.
5.  Set up binary search boundaries for `nums1`: `lo = 0` and `hi = m`.
6.  While `lo <= hi`:
    a.  Calculate the partition point for `nums1`: `cut1 = (lo + hi) / 2`.
    b.  Calculate the corresponding partition point for `nums2`: `cut2 = ls - cut1`.
    c.  Determine the four boundary elements:
        *   `l1`: The element just before `cut1` in `nums1` (or `Integer.MIN_VALUE` if `cut1` is 0).
        *   `l2`: The element just before `cut2` in `nums2` (or `Integer.MIN_VALUE` if `cut2` is 0).
        *   `r1`: The element at `cut1` in `nums1` (or `Integer.MAX_VALUE` if `cut1` is `m`).
        *   `r2`: The element at `cut2` in `nums2` (or `Integer.MAX_VALUE` if `cut2` is `n`).
    d.  Check if the partitions are valid: `l1 <= r2` and `l2 <= r1`.
        *   If valid:
            *   If `o` is even, the median is the average of `max(l1, l2)` and `min(r1, r2)`.
            *   If `o` is odd, the median is `min(r1, r2)`.
            *   Return the calculated median.
        *   If `l1 > r2`: The partition in `nums1` is too far to the right. Move the `hi` pointer: `hi = cut1 - 1`.
        *   If `l2 > r1`: The partition in `nums1` is too far to the left. Move the `lo` pointer: `lo = cut1 + 1`.
7.  If the loop finishes without returning (should not happen with valid inputs), return -1.

## Concept to Remember
*   Binary Search: Efficiently searching a sorted space by repeatedly dividing the search interval in half.
*   Partitioning: Dividing a set into subsets based on a condition. Here, we partition the merged array conceptually.
*   Edge Cases: Handling boundary conditions like empty arrays or partitions at the beginning/end of arrays (using `Integer.MIN_VALUE` and `Integer.MAX_VALUE`).
*   Median Definition: Understanding how to calculate the median for both even and odd total number of elements.

## Common Mistakes
*   Not handling the case where one array is significantly larger than the other, leading to inefficient binary search. Always binary search on the smaller array.
*   Incorrectly defining the boundary elements (`l1`, `l2`, `r1`, `r2`), especially when `cut1` or `cut2` are 0 or equal to the array length.
*   Off-by-one errors in calculating `cut1`, `cut2`, or adjusting binary search pointers (`lo`, `hi`).
*   Forgetting to handle the even/odd total length cases for median calculation.
*   Not using sentinel values (`Integer.MIN_VALUE`, `Integer.MAX_VALUE`) to simplify boundary checks.

## Complexity Analysis
*   Time: O(log(min(m, n))) - The binary search is performed on the smaller of the two arrays. In each step, we reduce the search space by half.
*   Space: O(1) - We are only using a few variables to store lengths, pointers, and boundary elements. No additional data structures are created that grow with input size.

## Commented Code
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Get the lengths of the two input arrays.
        int m = nums1.length;
        int n = nums2.length;

        // Ensure nums1 is the shorter array to optimize binary search.
        // If m > n, recursively call with swapped arrays.
        if(m > n) return findMedianSortedArrays(nums2, nums1);

        // Calculate the total length of the merged array.
        int o = m + n;
        // Calculate the desired size of the left partition of the merged array.
        // This is the number of elements that should be in the left half.
        int ls = o / 2;

        // Initialize binary search pointers for the partition in nums1.
        // lo represents the minimum possible cut point in nums1.
        int lo = 0;
        // hi represents the maximum possible cut point in nums1.
        int hi = m;

        // Perform binary search to find the optimal partition.
        while(lo <= hi){
            // Calculate the partition point for nums1. This is the number of elements
            // taken from nums1 for the left half of the merged array.
            int cut1 = (lo + hi) / 2;
            // Calculate the corresponding partition point for nums2.
            // The total number of elements in the left half must be ls.
            int cut2 = ls - cut1;

            // Determine the four boundary elements around the cuts.
            // l1: The element just before the cut in nums1. If cut1 is 0, it means
            //     no elements are taken from nums1 for the left partition, so use MIN_VALUE.
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            // l2: The element just before the cut in nums2. If cut2 is 0, use MIN_VALUE.
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            // r1: The element at the cut in nums1. If cut1 is m, it means all elements
            //     of nums1 are in the left partition, so use MAX_VALUE for the right boundary.
            int r1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
            // r2: The element at the cut in nums2. If cut2 is n, use MAX_VALUE.
            int r2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];

            // Check if the partitions are valid.
            // A valid partition means all elements in the left halves are less than or equal to
            // all elements in the right halves. This is satisfied if:
            // l1 <= r2 (largest element from nums1's left partition <= smallest from nums2's right partition)
            // AND
            // l2 <= r1 (largest element from nums2's left partition <= smallest from nums1's right partition)
            if(l1 <= r2 && l2 <= r1){
                // If partitions are valid, we've found the correct split.
                // Now, calculate the median based on whether the total number of elements is even or odd.
                if(o % 2 == 0){
                    // If total elements (o) is even, the median is the average of the two middle elements.
                    // These are the largest element in the left partition (max(l1, l2))
                    // and the smallest element in the right partition (min(r1, r2)).
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    // If total elements (o) is odd, the median is the single middle element.
                    // This is the smallest element in the right partition (min(r1, r2)).
                    return Math.min(r1, r2);
                }
            }

            // If partitions are not valid, adjust the binary search range.
            // If l1 > r2, it means the cut in nums1 is too far to the right.
            // We need to move the cut to the left, so we reduce the upper bound of the search.
            if(l1 > r2) {
                hi = cut1 - 1;
            }
            // If l2 > r1, it means the cut in nums1 is too far to the left.
            // We need to move the cut to the right, so we increase the lower bound of the search.
            else { // This implies l2 > r1
                lo = cut1 + 1;
            }
        }
        // This return statement should ideally not be reached if inputs are valid sorted arrays.
        // It's a fallback for unexpected scenarios.
        return -1;
    }
}
```

## Interview Tips
1.  **Clarify Constraints:** Ask about empty arrays, negative numbers, and the range of values. This helps in choosing appropriate sentinel values.
2.  **Explain the Partitioning Logic:** Clearly articulate how you're dividing the conceptual merged array into two halves and the conditions for a valid partition (`l1 <= r2` and `l2 <= r1`).
3.  **Handle Edge Cases Explicitly:** Walk through examples where one array is empty, or the partition falls at the beginning or end of an array. Explain why `Integer.MIN_VALUE` and `Integer.MAX_VALUE` are crucial.
4.  **Discuss Complexity:** Be ready to explain why the time complexity is logarithmic and the space complexity is constant. Emphasize that the binary search is on the *smaller* array.
5.  **Walk Through an Example:** Use a small, concrete example (e.g., `nums1 = [1, 3]`, `nums2 = [2]`) to trace the binary search steps and show how the `cut1`, `cut2`, `l1`, `l2`, `r1`, `r2` values change.

## Revision Checklist
- [ ] Understand the problem: Find the median of two sorted arrays.
- [ ] Recognize the need for a non-merging approach.
- [ ] Grasp the partitioning concept for the merged array.
- [ ] Implement binary search on the smaller array.
- [ ] Correctly define `cut1` and `cut2`.
- [ ] Handle boundary elements (`l1`, `l2`, `r1`, `r2`) with sentinel values.
- [ ] Implement the partition validity check (`l1 <= r2 && l2 <= r1`).
- [ ] Correctly adjust binary search pointers (`lo`, `hi`).
- [ ] Calculate median for even and odd total lengths.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its edge cases.

## Similar Problems
*   Find K-th Smallest Element in Two Sorted Arrays
*   Median of Two Sorted Arrays (different approach, e.g., merging)
*   Merge Sorted Array

## Tags
`Array` `Binary Search` `Divide and Conquer` `Two Pointers`
