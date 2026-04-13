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
Find the median of two sorted arrays by partitioning them such that elements on the left of partitions are less than or equal to elements on the right.
This is achieved using binary search on the smaller array to find the optimal partition point.

## Intuition
The core idea is to find a "cut" or partition point in both arrays such that if we merge the left halves of these partitions, we get the left half of the conceptually merged sorted array, and similarly for the right halves. If we can achieve this, the median will be determined by the boundary elements of these partitions. We can use binary search to efficiently find this optimal partition.

## Algorithm
1. Ensure `nums1` is the shorter array. If not, swap `nums1` and `nums2`. This optimizes the binary search range.
2. Calculate the total length `o = m + n` and the desired size of the left partition `ls = o / 2`.
3. Initialize binary search boundaries for `nums1`: `lo = 0` and `hi = m`.
4. While `lo <= hi`:
    a. Calculate `cut1 = (lo + hi) / 2` (the partition point in `nums1`).
    b. Calculate `cut2 = ls - cut1` (the corresponding partition point in `nums2`).
    c. Determine the four boundary elements:
        - `l1`: The element just before `cut1` in `nums1` (or `Integer.MIN_VALUE` if `cut1` is 0).
        - `l2`: The element just before `cut2` in `nums2` (or `Integer.MIN_VALUE` if `cut2` is 0).
        - `r1`: The element at `cut1` in `nums1` (or `Integer.MAX_VALUE` if `cut1` is `m`).
        - `r2`: The element at `cut2` in `nums2` (or `Integer.MAX_VALUE` if `cut2` is `n`).
    d. Check if the partitions are valid: `l1 <= r2` and `l2 <= r1`.
        - If valid:
            - If `o` is even, the median is the average of `max(l1, l2)` and `min(r1, r2)`.
            - If `o` is odd, the median is `min(r1, r2)`.
            - Return the calculated median.
        - If `l1 > r2`: The partition in `nums1` is too far to the right. Move the `hi` pointer: `hi = cut1 - 1`.
        - If `l2 > r1`: The partition in `nums1` is too far to the left. Move the `lo` pointer: `lo = cut1 + 1`.
5. If the loop finishes without finding a median (should not happen with valid inputs), return -1.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted space by repeatedly dividing the search interval in half.
*   **Partitioning:** Dividing a set of elements into two subsets based on a condition.
*   **Median Definition:** The middle element of a sorted dataset (or the average of the two middle elements for an even count).
*   **Handling Edge Cases:** Using `Integer.MIN_VALUE` and `Integer.MAX_VALUE` to simplify boundary checks when partitions are at the beginning or end of arrays.

## Common Mistakes
*   **Incorrect Partition Logic:** Not correctly calculating `cut2` based on `cut1` and the total desired left partition size.
*   **Off-by-One Errors:** Mismanaging indices when accessing elements around the partition points (`cut1-1`, `cut1`, `cut2-1`, `cut2`).
*   **Ignoring Edge Cases:** Not properly handling situations where a partition is at the very beginning or end of an array (e.g., `cut1 == 0` or `cut1 == m`).
*   **Incorrect Median Calculation:** Forgetting to handle both even and odd total lengths (`o`) when calculating the final median.
*   **Not Swapping Arrays:** Failing to ensure the binary search is performed on the smaller array, which can lead to a less efficient solution.

## Complexity Analysis
- Time: O(log(min(m, n))) - The binary search is performed on the smaller of the two arrays.
- Space: O(1) - No extra space is used beyond a few variables.

## Commented Code
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Get the lengths of the two input arrays.
        int m = nums1.length;
        int n = nums2.length;

        // Ensure nums1 is the shorter array to optimize binary search.
        // If nums1 is longer than nums2, recursively call the function with swapped arrays.
        if(m > n) return findMedianSortedArrays(nums2, nums1);

        // Calculate the total number of elements in the merged array.
        int o = m+n;
        // Calculate the desired size of the left partition of the merged array.
        // This is half the total elements, rounded down.
        int ls = o/2;

        // Initialize binary search pointers for the partition in nums1.
        // lo represents the minimum possible cut in nums1 (0 elements from nums1 in left partition).
        // hi represents the maximum possible cut in nums1 (m elements from nums1 in left partition).
        int lo = 0;
        int hi = m;

        // Perform binary search to find the optimal partition.
        while(lo<=hi){
            // Calculate the partition point in nums1. This is the number of elements from nums1
            // that will be in the left partition of the merged array.
            int cut1 = (lo+hi)/2;
            // Calculate the corresponding partition point in nums2.
            // The total elements in the left partition must be 'ls'.
            // So, if 'cut1' elements come from nums1, 'ls - cut1' must come from nums2.
            int cut2 = ls - cut1;

            // Determine the four boundary elements around the cuts.
            // l1: The rightmost element in the left partition of nums1.
            // If cut1 is 0, it means no elements from nums1 are in the left partition, so use MIN_VALUE.
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1];
            // l2: The rightmost element in the left partition of nums2.
            // If cut2 is 0, use MIN_VALUE.
            int l2 = cut2 == 0 ? Integer.MIN_VALUE :nums2[cut2-1];

            // r1: The leftmost element in the right partition of nums1.
            // If cut1 is m, it means all elements of nums1 are in the left partition, so use MAX_VALUE.
            int r1 = cut1 == m ? Integer.MAX_VALUE :nums1[cut1];
            // r2: The leftmost element in the right partition of nums2.
            // If cut2 is n, use MAX_VALUE.
            int r2 = cut2 == n ? Integer.MAX_VALUE :nums2[cut2];

            // Check if the partitions are valid.
            // A valid partition means all elements in the left partitions are less than or equal to
            // all elements in the right partitions. This is satisfied if:
            // 1. The largest element in nums1's left partition (l1) is <= the smallest in nums2's right partition (r2).
            // 2. The largest element in nums2's left partition (l2) is <= the smallest in nums1's right partition (r1).
            if(l1 <=r2 && l2 <= r1){
                // If partitions are valid, we've found the correct cuts.
                // Now, calculate the median based on whether the total number of elements is even or odd.
                if(o%2 == 0){
                    // If total elements are even, the median is the average of the two middle elements.
                    // The two middle elements are the largest of the left partitions (max(l1, l2))
                    // and the smallest of the right partitions (min(r1, r2)).
                    return (Math.max(l1,l2) + Math.min(r1,r2))/2.0;
                } else {
                    // If total elements are odd, the median is the single middle element.
                    // This middle element is the smallest element in the right partitions (min(r1, r2)).
                    return Math.min(r1,r2);
                }
            }

            // If partitions are not valid, adjust the binary search range.
            // If l1 > r2, it means the partition in nums1 is too far to the right.
            // We need to move the cut in nums1 to the left.
            if(l1 > r2) hi = cut1-1;
            // If l2 > r1, it means the partition in nums1 is too far to the left.
            // We need to move the cut in nums1 to the right.
            else lo = cut1+1;
        }
        // This return statement should ideally not be reached if inputs are valid sorted arrays.
        // It's a fallback for unexpected scenarios.
        return -1;
    }
}
```

## Interview Tips
*   **Explain the Partitioning Strategy:** Clearly articulate how you're dividing the arrays and the conditions for a valid partition.
*   **Handle Edge Cases Explicitly:** Walk through how `Integer.MIN_VALUE` and `Integer.MAX_VALUE` help manage partitions at the array boundaries.
*   **Trace with Examples:** Use small, concrete examples (e.g., `[1,3]` and `[2]`, or `[1,2]` and `[3,4]`) to demonstrate the algorithm's steps and how the binary search converges.
*   **Discuss Complexity:** Be ready to explain why the time complexity is logarithmic and the space complexity is constant.

## Revision Checklist
- [ ] Understand the problem statement and median definition.
- [ ] Grasp the partitioning concept for two sorted arrays.
- [ ] Implement binary search on the smaller array.
- [ ] Correctly define and calculate `cut1` and `cut2`.
- [ ] Handle boundary elements (`l1`, `l2`, `r1`, `r2`) with `MIN_VALUE`/`MAX_VALUE`.
- [ ] Implement the partition validity check (`l1 <= r2 && l2 <= r1`).
- [ ] Correctly calculate the median for even and odd total lengths.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find K-th Smallest Element in Two Sorted Arrays
*   Merge Sorted Array
*   Median of Two Sorted Arrays (different approaches, e.g., merging)

## Tags
`Binary Search` `Divide and Conquer` `Array`
