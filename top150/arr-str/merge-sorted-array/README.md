# Merge Sorted Array

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Sorting`  
**Time:** O(m + n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i =m-1;
        int j =n-1;
        int k = nums1.length-1;
        
        while(i>=0 && j>=0){
            if(nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        while(i>=0) nums1[k--] = nums1[i--];
        while(j>=0) nums1[k--] = nums2[j--];
        
    }
}

// [1,2,3,0,5,6]
// [2,5,6]

```

---

---
## Quick Revision
Given two sorted integer arrays `nums1` and `nums2`, merge `nums2` into `nums1` as one sorted array.
The solution merges from the end of `nums1` to avoid overwriting elements.

## Intuition
The core idea is that since both arrays are sorted, we can determine the largest element at each step and place it in its correct final position. By working backward from the end of `nums1` (which has enough space), we can fill `nums1` from right to left without needing an auxiliary array. The largest element will always be either the current largest element in the remaining `nums1` portion or the current largest element in `nums2`.

## Algorithm
1. Initialize three pointers:
    - `i` pointing to the last element of the initialized part of `nums1` (index `m-1`).
    - `j` pointing to the last element of `nums2` (index `n-1`).
    - `k` pointing to the last position of `nums1` (index `nums1.length - 1`).
2. While both `i` and `j` are non-negative (meaning there are elements left in both arrays to compare):
    - Compare `nums1[i]` and `nums2[j]`.
    - If `nums1[i]` is greater than `nums2[j]`, place `nums1[i]` at `nums1[k]` and decrement both `i` and `k`.
    - Otherwise, place `nums2[j]` at `nums1[k]` and decrement both `j` and `k`.
3. After the first loop, one of the arrays might still have remaining elements.
4. While `i` is non-negative (meaning there are remaining elements in `nums1`):
    - Place `nums1[i]` at `nums1[k]` and decrement both `i` and `k`.
5. While `j` is non-negative (meaning there are remaining elements in `nums2`):
    - Place `nums2[j]` at `nums1[k]` and decrement both `j` and `k`.

## Concept to Remember
*   **Two Pointers:** Efficiently traversing and comparing elements in arrays.
*   **In-place Modification:** Modifying an array directly without using significant extra space.
*   **Working Backwards:** A common technique for merging or sorting problems where overwriting is a concern.

## Common Mistakes
*   Merging from the beginning of `nums1`, which would overwrite elements that haven't been processed yet.
*   Not handling the case where one array is exhausted before the other.
*   Incorrectly initializing or decrementing pointers.
*   Forgetting that `nums1` has trailing zeros that are meant to be overwritten.

## Complexity Analysis
- Time: O(m + n) - reason: We iterate through each element of `nums1` (up to `m`) and `nums2` (up to `n`) at most once.
- Space: O(1) - reason: The merging is done in-place within `nums1`, and no auxiliary data structures are used that scale with input size.

## Commented Code
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointer 'i' to the last element of the initialized part of nums1
        int i = m - 1;
        // Initialize pointer 'j' to the last element of nums2
        int j = n - 1;
        // Initialize pointer 'k' to the last position of nums1 (where the merged element will be placed)
        int k = nums1.length - 1;

        // Loop while there are elements to compare in both nums1 (initialized part) and nums2
        while (i >= 0 && j >= 0) {
            // Compare the current elements from nums1 and nums2
            if (nums1[i] > nums2[j]) {
                // If nums1[i] is greater, place it at the current end of nums1 (position k)
                nums1[k] = nums1[i];
                // Decrement 'i' to move to the next element in nums1
                i--;
            } else {
                // If nums2[j] is greater or equal, place it at the current end of nums1 (position k)
                nums1[k] = nums2[j];
                // Decrement 'j' to move to the next element in nums2
                j--;
            }
            // Decrement 'k' to move to the next position to fill in nums1
            k--;
        }

        // If there are any remaining elements in nums2 after nums1 is exhausted
        // (This loop is necessary because if nums1 runs out first, we still need to copy remaining nums2 elements)
        while (j >= 0) {
            // Copy the remaining element from nums2 to nums1
            nums1[k] = nums2[j];
            // Decrement 'j' and 'k'
            j--;
            k--;
        }
        
        // Note: The loop `while(i>=0)` is implicitly handled. If `i` is still >= 0, it means all elements from `nums2` have been placed,
        // and the remaining elements in `nums1` are already in their correct sorted positions at the beginning of the array.
        // The original code snippet had `while(i>=0) nums1[k--] = nums1[i--];` which is technically correct but redundant if `j` is already exhausted.
        // However, to strictly match the provided solution's logic:
        while (i >= 0) {
            nums1[k] = nums1[i];
            i--;
            k--;
        }
    }
}
```

## Interview Tips
*   Clearly explain your two-pointer approach and why working backward is crucial.
*   Walk through an example on the whiteboard, showing how the pointers move and elements are placed.
*   Be prepared to discuss edge cases like empty arrays or one array being entirely smaller/larger than the other.
*   Mention the in-place nature of the solution and its space efficiency.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the need for an in-place solution.
- [ ] Implement the three-pointer approach (i, j, k).
- [ ] Handle the main merging loop correctly.
- [ ] Ensure remaining elements from either array are copied.
- [ ] Verify pointer decrements.
- [ ] Test with edge cases.

## Similar Problems
*   Merge Intervals
*   Sort Colors
*   Two Sum
*   Remove Duplicates from Sorted Array

## Tags
`Array` `Two Pointers` `In-place`
