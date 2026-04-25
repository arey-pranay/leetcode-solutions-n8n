# Median Of Two Sorted Arrays

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Divide and Conquer`  
**Time:** O(log(min(m, n)  
**Space:** O(1)

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
The core idea is to find a "cut" or partition point in both arrays such that if we merge the left parts of these partitions, we get the first half of the combined sorted array, and if we merge the right parts, we get the second half. The median will then be determined by the boundary elements of these partitions. We can use binary search to efficiently find this optimal partition.

## Algorithm
1.  Ensure `nums1` is the shorter array. If not, swap them. This optimizes the binary search range.
2.  Initialize `m` and `n` as the lengths of `nums1` and `nums2` respectively.
3.  Calculate `totalLength = m + n` and `halfLength = totalLength / 2`. This `halfLength` represents the number of elements that should be in the left partition of the merged array.
4.  Perform binary search on `nums1`'s partition point. Let `low = 0` and `high = m`.
5.  In each iteration of the binary search:
    *   Calculate `partition1 = (low + high) / 2`. This is the number of elements taken from `nums1` for the left partition.
    *   Calculate `partition2 = halfLength - partition1`. This is the number of elements taken from `nums2` for the left partition.
    *   Determine the boundary elements:
        *   `maxLeft1`: The rightmost element of the left partition of `nums1`. If `partition1` is 0, use `Integer.MIN_VALUE`. Otherwise, it's `nums1[partition1 - 1]`.
        *   `minRight1`: The leftmost element of the right partition of `nums1`. If `partition1` is `m`, use `Integer.MAX_VALUE`. Otherwise, it's `nums1[partition1]`.
        *   `maxLeft2`: The rightmost element of the left partition of `nums2`. If `partition2` is 0, use `Integer.MIN_VALUE`. Otherwise, it's `nums2[partition2 - 1]`.
        *   `minRight2`: The leftmost element of the right partition of `nums2`. If `partition2` is `n`, use `Integer.MAX_VALUE`. Otherwise, it's `nums2[partition2]`.
    *   Check if the partitions are valid: `maxLeft1 <= minRight2` AND `maxLeft2 <= minRight1`.
        *   If valid:
            *   If `totalLength` is even, the median is `(max(maxLeft1, maxLeft2) + min(minRight1, minRight2)) / 2.0`.
            *   If `totalLength` is odd, the median is `min(minRight1, minRight2)`.
            *   Return the median.
        *   If `maxLeft1 > minRight2`: The partition in `nums1` is too far to the right. Move the `high` pointer: `high = partition1 - 1`.
        *   Else (`maxLeft2 > minRight1`): The partition in `nums1` is too far to the left. Move the `low` pointer: `low = partition1 + 1`.
6.  The loop will eventually find the correct partition. The `return -1` is a fallback, though it should not be reached with valid inputs.

## Concept to Remember
*   Binary Search: Efficiently searching a sorted space by repeatedly dividing the search interval in half.
*   Partitioning: Dividing a data structure into two parts based on a condition.
*   Median Definition: The middle element (or average of two middle elements) in a sorted dataset.
*   Handling Edge Cases: Using `Integer.MIN_VALUE` and `Integer.MAX_VALUE` to simplify boundary comparisons when partitions are at the array's start or end.

## Common Mistakes
*   Incorrectly handling edge cases where a partition is at the beginning or end of an array (e.g., `partition1 = 0` or `partition1 = m`).
*   Off-by-one errors in calculating partition indices or accessing array elements.
*   Not ensuring the binary search is performed on the smaller array, leading to a less optimal time complexity.
*   Incorrectly calculating the median for even vs. odd total lengths.
*   Forgetting to swap arrays to ensure binary search is on the smaller one.

## Complexity Analysis
- Time: O(log(min(m, n))) - The binary search is performed on the smaller of the two arrays.
- Space: O(1) - No extra space is used beyond a few variables.

## Commented Code
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Get the lengths of the two arrays.
        int m = nums1.length;
        int n = nums2.length;

        // Ensure nums1 is the shorter array to optimize binary search.
        // If m > n, recursively call with swapped arrays.
        if(m > n) return findMedianSortedArrays(nums2, nums1);

        // Calculate the total length of the merged array.
        int totalLength = m + n;
        // Calculate the desired number of elements in the left partition of the merged array.
        int halfLength = totalLength / 2;

        // Initialize binary search pointers for the partition in nums1.
        int low = 0; // Minimum possible elements from nums1 in the left partition.
        int high = m; // Maximum possible elements from nums1 in the left partition.

        // Perform binary search to find the correct partition point in nums1.
        while(low <= high){
            // Calculate the partition point for nums1. This is the number of elements from nums1 in the left half.
            int partition1 = (low + high) / 2;
            // Calculate the corresponding partition point for nums2.
            // The total elements in the left half must be halfLength.
            int partition2 = halfLength - partition1;

            // Determine the boundary elements for the partitions.
            // l1: The largest element in the left partition of nums1.
            // If partition1 is 0, it means no elements are taken from nums1 for the left half, so use MIN_VALUE.
            int l1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            // l2: The largest element in the left partition of nums2.
            // If partition2 is 0, use MIN_VALUE.
            int l2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];

            // r1: The smallest element in the right partition of nums1.
            // If partition1 is m, it means all elements of nums1 are in the left half, so use MAX_VALUE for the right partition's start.
            int r1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            // r2: The smallest element in the right partition of nums2.
            // If partition2 is n, use MAX_VALUE.
            int r2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // Check if the partitions are valid.
            // A valid partition means all elements in the left halves are less than or equal to all elements in the right halves.
            // This condition is met if the largest element of the left partition of nums1 (l1) is <= the smallest element of the right partition of nums2 (r2),
            // AND the largest element of the left partition of nums2 (l2) is <= the smallest element of the right partition of nums1 (r1).
            if(l1 <= r2 && l2 <= r1){
                // If the total number of elements is even, the median is the average of the two middle elements.
                // These are the maximum of the left partitions and the minimum of the right partitions.
                if(totalLength % 2 == 0) return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                // If the total number of elements is odd, the median is the single middle element.
                // This is the smallest element in the right partition.
                else return Math.min(r1, r2);
            }

            // If the partitions are not valid, adjust the binary search range.
            // If l1 > r2, it means the partition in nums1 is too far to the right.
            // We need to move the partition point in nums1 to the left.
            if(l1 > r2) high = partition1 - 1;
            // Otherwise (l2 > r1), it means the partition in nums1 is too far to the left.
            // We need to move the partition point in nums1 to the right.
            else low = partition1 + 1;
        }

        // This return statement should ideally not be reached if the input arrays are valid sorted arrays.
        // It serves as a fallback.
        return -1;
    }
}
```

## Interview Tips
1.  **Explain the Binary Search Strategy First:** Before diving into code, clearly articulate the idea of partitioning both arrays and using binary search on the smaller one to find the optimal cut.
2.  **Handle Edge Cases Explicitly:** Walk through how you'd handle `partition1 = 0`, `partition1 = m`, `partition2 = 0`, and `partition2 = n` using `Integer.MIN_VALUE` and `Integer.MAX_VALUE`. This shows attention to detail.
3.  **Trace with an Example:** Use a small, concrete example (e.g., `nums1 = [1, 3]`, `nums2 = [2]`) to trace the binary search steps and demonstrate how the partitions are adjusted.
4.  **Discuss Complexity:** Be ready to explain why the time complexity is logarithmic with respect to the *smaller* array and why the space complexity is constant.

## Revision Checklist
- [ ] Understand the problem: finding the median of two sorted arrays.
- [ ] Grasp the core intuition: partitioning arrays to simulate a merged sorted array.
- [ ] Implement binary search on the smaller array.
- [ ] Correctly define and calculate partition points (`partition1`, `partition2`).
- [ ] Accurately identify boundary elements (`l1`, `l2`, `r1`, `r2`).
- [ ] Handle edge cases for boundary elements using `MIN_VALUE`/`MAX_VALUE`.
- [ ] Implement the condition for a valid partition (`l1 <= r2 && l2 <= r1`).
- [ ] Correctly calculate the median for even and odd total lengths.
- [ ] Implement the logic to adjust binary search bounds (`low`, `high`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find K-th Smallest Element in Two Sorted Arrays
*   Merge Sorted Array
*   Median of Two Sorted Arrays (different approaches, e.g., merging)

## Tags
`Binary Search` `Array` `Divide and Conquer`
