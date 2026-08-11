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
        //total o=m*n numbers hai, aur ye sorted order me merge hone pe m*n
        // sbse chhote o/2 numbers ka highest, aur bache hue t/2 numbers ka lowest
        //m=3 n=4 => 7 => o/2 = 3. 
        if(m>n) return findMedianSortedArrays(nums2,nums1); // because we assumed m is smaller. (1st array is smaller)
        int o = m+n;
  
        //cut2 lgaya n/2 pe
        //cut1 lgega o - cut2;
        int s=0, e=m;
  
        //cut mid pe lga lo, aur fir check krte rho cut ko kahan move krna hai
        while(s<=e){
            int cut1 = (s+e)/2, cut2 = o/2-cut1;
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1]; //cut ke left wala element in nums1. //cut1 agr 0 tk aa gya, means l1 ignore krna hia, and since we use max of l1,l2. we can put l1 as int minvalue
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1];
            int r1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];
            //agr ye cuts correct hai, nums1[cut1] > nums2[cut2-1] => it means cut1 further right nhi ja skta. 
            // agr cuts valid hai, mtlb left ke elements right se chhote hoge, we know that l1<r1 & l2<r2 kyuki nums sorted hhai.
          // we need to just check
            if(l1<=r2 && l2<=r1){
                if(o%2==1) return Math.min(r1,r2);   //large part ka highest  //5 => 2,3 not 3,2
                else return (Math.max(l1,l2)+Math.min(r1,r2))/2.0;//(smalls ka h + bigs ka l)/2
            }
            if(l1 > r2) e=cut1-1; //nums1 ka element bda hai, usse aur chhota element chahiye
            else s = cut1+1;
          //cut 2 will be recalculated
        }
        return -1;
    }
}

```

---

---
## Quick Revision
This problem asks for the median of two sorted arrays combined. The solution uses a binary search approach to efficiently find the partition points.

## Intuition
The core idea is to find a "partition" in both arrays such that all elements to the left of the partitions are smaller than all elements to the right. If we can achieve this, the median will be determined by the maximum of the left elements and the minimum of the right elements. Binary search helps us find these partitions efficiently.

## Algorithm
1.  Ensure `nums1` is the shorter array. If not, swap them. This optimizes the binary search range.
2.  Initialize `low = 0` and `high = m` (length of `nums1`) for the binary search on `nums1`.
3.  Calculate the total length `totalLength = m + n`.
4.  Enter a `while` loop that continues as long as `low <= high`.
5.  Inside the loop, calculate `partition1 = (low + high) / 2` (the cut point in `nums1`).
6.  Calculate `partition2 = totalLength / 2 - partition1` (the corresponding cut point in `nums2`).
7.  Determine the four boundary elements:
    *   `maxLeft1`: The element just before `partition1` in `nums1`. If `partition1` is 0, use `Integer.MIN_VALUE`.
    *   `minRight1`: The element at `partition1` in `nums1`. If `partition1` is `m`, use `Integer.MAX_VALUE`.
    *   `maxLeft2`: The element just before `partition2` in `nums2`. If `partition2` is 0, use `Integer.MIN_VALUE`.
    *   `minRight2`: The element at `partition2` in `nums2`. If `partition2` is `n`, use `Integer.MAX_VALUE`.
8.  Check if the partitions are correct: `maxLeft1 <= minRight2` AND `maxLeft2 <= minRight1`.
    *   If true:
        *   If `totalLength` is odd, the median is `Math.min(minRight1, minRight2)`.
        *   If `totalLength` is even, the median is `(Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0`.
        *   Return the calculated median.
    *   If `maxLeft1 > minRight2`: The cut in `nums1` is too far to the right. Move the `high` pointer: `high = partition1 - 1`.
    *   Else (`maxLeft2 > minRight1`): The cut in `nums1` is too far to the left. Move the `low` pointer: `low = partition1 + 1`.
9.  If the loop finishes without returning (should not happen with valid inputs), return -1.

## Concept to Remember
*   Binary Search: Efficiently searching a sorted data structure by repeatedly dividing the search interval in half.
*   Partitioning: Dividing a set of elements into two subsets based on a condition.
*   Median Definition: The middle element in a sorted list, or the average of the two middle elements if the list has an even number of elements.
*   Edge Cases: Handling boundary conditions like empty arrays or partitions at the beginning/end of arrays.

## Common Mistakes
*   Incorrectly handling edge cases where partitions are at the beginning or end of arrays (e.g., `partition1 = 0` or `partition1 = m`).
*   Off-by-one errors when calculating partition indices or accessing array elements.
*   Not ensuring the binary search is performed on the shorter array, leading to a less optimal time complexity.
*   Incorrectly calculating the median for even vs. odd total lengths.
*   Integer division issues when calculating the average for the even case.

## Complexity Analysis
*   Time: O(log(min(m, n))) - The binary search is performed on the shorter of the two arrays.
*   Space: O(1) - No extra space is used beyond a few variables.

## Commented Code
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length; // Get the length of the first array
        int n = nums2.length; // Get the length of the second array

        // Ensure nums1 is the shorter array to optimize binary search
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1); // Recursively call with swapped arrays
        }

        int totalLength = m + n; // Calculate the total number of elements in both arrays
        int halfLength = totalLength / 2; // The target number of elements in the left partition

        int low = 0; // Initialize the lower bound for binary search on nums1
        int high = m; // Initialize the upper bound for binary search on nums1

        // Perform binary search on the partition point of the shorter array (nums1)
        while (low <= high) {
            int partition1 = (low + high) / 2; // Calculate the partition point for nums1
            int partition2 = halfLength - partition1; // Calculate the corresponding partition point for nums2

            // Determine the four boundary elements around the partitions
            // If partition1 is 0, there are no elements to its left in nums1, so use MIN_VALUE
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            // If partition1 is m, there are no elements to its right in nums1, so use MAX_VALUE
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            // If partition2 is 0, there are no elements to its left in nums2, so use MIN_VALUE
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            // If partition2 is n, there are no elements to its right in nums2, so use MAX_VALUE
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // Check if the partitions are correct:
            // All elements in the left partition must be less than or equal to all elements in the right partition.
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // If the total length is odd, the median is the smallest element in the right partition
                if (totalLength % 2 == 1) {
                    return Math.min(minRight1, minRight2); // The median is the minimum of the two rightmost elements
                } else {
                    // If the total length is even, the median is the average of the two middle elements
                    // The two middle elements are the maximum of the left partitions and the minimum of the right partitions
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0; // Average of the two middle elements
                }
            }
            // If maxLeft1 is greater than minRight2, it means partition1 is too far to the right.
            // We need to move the partition in nums1 to the left.
            else if (maxLeft1 > minRight2) {
                high = partition1 - 1; // Adjust the upper bound of the binary search
            }
            // If maxLeft2 is greater than minRight1, it means partition1 is too far to the left.
            // We need to move the partition in nums1 to the right.
            else { // maxLeft2 > minRight1
                low = partition1 + 1; // Adjust the lower bound of the binary search
            }
        }

        // This part should ideally not be reached if the input arrays are valid sorted arrays.
        // It's a fallback return, though an exception might be more appropriate in some contexts.
        return -1.0; // Should not happen with valid inputs
    }
}
```

## Interview Tips
*   Clearly explain the partitioning logic and the conditions for a correct partition (`maxLeft <= minRight`).
*   Walk through an example with small arrays to demonstrate how the binary search and partition adjustments work.
*   Pay close attention to edge cases like empty arrays or when partitions fall at the array boundaries.
*   Discuss the time and space complexity and justify why the binary search on the shorter array is crucial for the optimal time complexity.

## Revision Checklist
- [ ] Understand the definition of median for odd/even length lists.
- [ ] Grasp the concept of partitioning two sorted arrays.
- [ ] Implement binary search on the partition point of the shorter array.
- [ ] Correctly handle boundary conditions for `maxLeft` and `minRight` elements.
- [ ] Differentiate median calculation for odd vs. even total lengths.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find K-th Smallest Element in Two Sorted Arrays
*   Merge Sorted Array
*   Median of Two Sorted Arrays (different approach, e.g., merging)

## Tags
`Binary Search` `Array` `Divide and Conquer`
