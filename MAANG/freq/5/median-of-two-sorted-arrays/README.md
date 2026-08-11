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
This problem asks for the median of two sorted arrays combined. The solution uses binary search on the smaller array to find the correct partition.

## Intuition
The core idea is to partition both arrays such that all elements to the left of the partitions are smaller than all elements to the right. If we can achieve this, the median will be determined by the maximum element on the left side and the minimum element on the right side. Binary search helps us efficiently find this perfect partition.

## Algorithm
1. Ensure `nums1` is the shorter array. If not, swap them. This optimizes the binary search space.
2. Calculate the total length `totalLength = m + n`.
3. Initialize binary search boundaries for `nums1`: `low = 0`, `high = m`.
4. While `low <= high`:
    a. Calculate `partition1 = (low + high) / 2`. This is the cut point in `nums1`.
    b. Calculate `partition2 = totalLength / 2 - partition1`. This is the corresponding cut point in `nums2`.
    c. Determine the four boundary elements:
        - `maxLeft1`: The element just before `partition1` in `nums1` (or `Integer.MIN_VALUE` if `partition1` is 0).
        - `minRight1`: The element at `partition1` in `nums1` (or `Integer.MAX_VALUE` if `partition1` is `m`).
        - `maxLeft2`: The element just before `partition2` in `nums2` (or `Integer.MIN_VALUE` if `partition2` is 0).
        - `minRight2`: The element at `partition2` in `nums2` (or `Integer.MAX_VALUE` if `partition2` is `n`).
    d. Check if the partitions are correct: `maxLeft1 <= minRight2` AND `maxLeft2 <= minRight1`.
        - If correct:
            - If `totalLength` is odd, the median is `Math.min(minRight1, minRight2)`.
            - If `totalLength` is even, the median is `(Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0`.
            - Return the median.
        - If `maxLeft1 > minRight2`: The cut in `nums1` is too far to the right. Move `high = partition1 - 1`.
        - Else (`maxLeft2 > minRight1`): The cut in `nums1` is too far to the left. Move `low = partition1 + 1`.
5. If the loop finishes without returning (should not happen with valid inputs), return -1.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted space by repeatedly dividing the search interval in half.
*   **Partitioning:** Dividing a set of elements into two groups based on a condition.
*   **Median Definition:** The middle element in a sorted list, or the average of the two middle elements if the list has an even number of elements.
*   **Edge Cases with Partitions:** Handling partitions at the beginning or end of arrays using `Integer.MIN_VALUE` and `Integer.MAX_VALUE`.

## Common Mistakes
*   **Incorrect Partition Calculation:** Miscalculating `partition2` based on `partition1` and `totalLength`.
*   **Off-by-One Errors:** Incorrectly handling array indices when accessing elements around the partition points (`cut-1` vs. `cut`).
*   **Handling Edge Cases:** Not properly using `Integer.MIN_VALUE` and `Integer.MAX_VALUE` for partitions at the array boundaries, leading to incorrect comparisons.
*   **Integer Division:** Forgetting to cast to `double` when calculating the average for an even number of elements.
*   **Not Swapping Arrays:** Failing to ensure the binary search is performed on the smaller array, leading to suboptimal time complexity.

## Complexity Analysis
- Time: O(log(min(m, n))) - The binary search is performed on the smaller of the two arrays.
- Space: O(1) - No extra space is used beyond a few variables.

## Commented Code
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length; // Get the length of the first array.
        int n = nums2.length; // Get the length of the second array.

        // Ensure nums1 is the shorter array to optimize binary search.
        // If nums1 is longer than nums2, recursively call the function with swapped arrays.
        if(m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int totalLength = m + n; // Calculate the total number of elements in both arrays.
        int low = 0; // Initialize the lower bound for binary search on nums1.
        int high = m; // Initialize the upper bound for binary search on nums1.

        // Perform binary search to find the correct partition.
        while(low <= high) {
            // Calculate the partition point for nums1. This is the number of elements taken from nums1 for the left half.
            int partition1 = (low + high) / 2;
            // Calculate the corresponding partition point for nums2.
            // The total number of elements in the left half should be totalLength / 2.
            int partition2 = totalLength / 2 - partition1;

            // Determine the four boundary elements around the partitions.
            // l1: The largest element in the left part of nums1. If partition1 is 0, there's no left part, so use MIN_VALUE.
            int l1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            // l2: The largest element in the left part of nums2. If partition2 is 0, use MIN_VALUE.
            int l2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];

            // r1: The smallest element in the right part of nums1. If partition1 is m, there's no right part, so use MAX_VALUE.
            int r1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            // r2: The smallest element in the right part of nums2. If partition2 is n, use MAX_VALUE.
            int r2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // Check if the partitions are correct.
            // The condition is that the largest element on the left of one array must be less than or equal to the smallest element on the right of the other array.
            if(l1 <= r2 && l2 <= r1) {
                // If the partitions are correct, we've found the median.
                // If the total number of elements is odd, the median is the smallest element in the right half.
                if(totalLength % 2 == 1) {
                    return Math.min(r1, r2);
                }
                // If the total number of elements is even, the median is the average of the largest element in the left half and the smallest element in the right half.
                else {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
            }
            // If l1 is greater than r2, it means the partition in nums1 is too far to the right.
            // We need to move the partition in nums1 to the left.
            else if(l1 > r2) {
                high = partition1 - 1; // Adjust the upper bound of binary search.
            }
            // If l2 is greater than r1, it means the partition in nums1 is too far to the left.
            // We need to move the partition in nums1 to the right.
            else { // l2 > r1
                low = partition1 + 1; // Adjust the lower bound of binary search.
            }
            // partition2 will be recalculated in the next iteration based on the new partition1.
        }
        // This line should theoretically not be reached if the input arrays are valid sorted arrays.
        return -1;
    }
}
```

## Interview Tips
*   **Explain the Partitioning Logic:** Clearly articulate why `l1 <= r2` and `l2 <= r1` are the conditions for a correct partition.
*   **Handle Edge Cases Explicitly:** Walk through how `Integer.MIN_VALUE` and `Integer.MAX_VALUE` are used to handle partitions at the array boundaries.
*   **Discuss Time Complexity Optimization:** Emphasize why binary searching on the smaller array is crucial for achieving `O(log(min(m, n)))`.
*   **Clarify Median Calculation:** Explain how the median is derived differently for odd and even total lengths.

## Revision Checklist
- [ ] Understand the problem statement: find the median of two sorted arrays.
- [ ] Grasp the partitioning concept: dividing arrays into left and right halves.
- [ ] Implement binary search on the smaller array.
- [ ] Correctly calculate `partition1` and `partition2`.
- [ ] Handle edge cases for `l1`, `l2`, `r1`, `r2` using `MIN_VALUE`/`MAX_VALUE`.
- [ ] Implement the correct partition check: `l1 <= r2 && l2 <= r1`.
- [ ] Calculate the median for odd and even total lengths.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find K-th Smallest Element in Two Sorted Arrays
*   Median of Two Sorted Arrays (different constraints or variations)

## Tags
`Array` `Binary Search` `Divide and Conquer` `Two Pointers`
