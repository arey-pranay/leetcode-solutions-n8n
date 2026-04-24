# Find Minimum In Rotated Sorted Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public int findMin(int[] nums) {
        return bs(0,nums.length-1,nums);
    }
    public int bs(int start, int end, int[] nums){
        if(start == end) return nums[start];
        int mid = start + (end-start)/2;
        if(nums[mid]>nums[end]) return bs(mid+1,end,nums);
        else return bs(start,mid,nums);
    }
}

```

---

---
## Quick Revision
Given a sorted array that has been rotated at some pivot point. Find the minimum element.
This is solved efficiently using a modified Binary Search.

## Intuition
The key insight is that in a rotated sorted array, the minimum element will always be the *only* element that is smaller than its preceding element (if it exists). More importantly, when we perform binary search, we can determine which half of the array contains the minimum element by comparing the middle element with the rightmost element. If `nums[mid] > nums[end]`, it means the pivot (and thus the minimum) must be in the right half (`mid + 1` to `end`). Otherwise, the minimum is in the left half (including `mid` itself, as `nums[mid]` could be the minimum).

## Algorithm
1. Initialize `left` pointer to 0 and `right` pointer to `nums.length - 1`.
2. While `left < right`:
   a. Calculate `mid = left + (right - left) / 2`.
   b. If `nums[mid] > nums[right]`: This implies the minimum element is in the right half of the array (from `mid + 1` to `right`). So, update `left = mid + 1`.
   c. Else (`nums[mid] <= nums[right]`): This implies the minimum element is in the left half of the array (from `left` to `mid`). So, update `right = mid`.
3. When the loop terminates, `left` and `right` will point to the same index, which is the index of the minimum element. Return `nums[left]`.

## Concept to Remember
*   Binary Search: Efficiently searching a sorted (or partially sorted) data structure.
*   Array Properties: Understanding how rotation affects the sorted order.
*   Divide and Conquer: Breaking down the problem into smaller, similar subproblems.

## Common Mistakes
*   Incorrectly handling the `mid` pointer update when `nums[mid] <= nums[right]`. Setting `right = mid + 1` would incorrectly discard the potential minimum.
*   Off-by-one errors in the `left` and `right` pointer updates, especially when `mid` is involved.
*   Not considering the base case or termination condition of the binary search correctly.
*   Forgetting that the array might not be rotated at all (i.e., it's still fully sorted).

## Complexity Analysis
*   Time: O(log n) - The algorithm uses binary search, which halves the search space in each step.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `left`, `right`, and `mid`.

## Commented Code
```java
class Solution {
    // The main function to find the minimum element in a rotated sorted array.
    public int findMin(int[] nums) {
        // Call the recursive binary search helper function.
        // It starts with the entire array range from index 0 to the last index.
        return bs(0, nums.length - 1, nums);
    }

    // Recursive helper function implementing binary search.
    public int bs(int start, int end, int[] nums) {
        // Base case: If the start and end pointers meet, we've found the minimum element.
        if (start == end) {
            // Return the element at this index.
            return nums[start];
        }

        // Calculate the middle index to divide the array.
        // Using (end - start) / 2 prevents potential integer overflow.
        int mid = start + (end - start) / 2;

        // If the middle element is greater than the rightmost element,
        // it means the pivot (and thus the minimum) is in the right half.
        if (nums[mid] > nums[end]) {
            // Recursively search the right half, excluding the middle element.
            return bs(mid + 1, end, nums);
        } else {
            // Otherwise, the minimum element is in the left half (including mid).
            // This is because nums[mid] could be the minimum itself, or the minimum is to its left.
            // Recursively search the left half.
            return bs(start, mid, nums);
        }
    }
}
```

## Interview Tips
*   Clearly explain your binary search logic, especially the condition `nums[mid] > nums[end]` and why it guides your search.
*   Walk through an example with a rotated array and a non-rotated array to demonstrate your understanding.
*   Be prepared to discuss edge cases like an array with only one element or an array that is not rotated at all.
*   If asked for an iterative solution, be ready to convert your recursive logic.

## Revision Checklist
- [ ] Understand the problem statement: finding the minimum in a rotated sorted array.
- [ ] Recall the core idea: binary search can be adapted.
- [ ] Master the comparison `nums[mid] > nums[end]` to determine the search direction.
- [ ] Implement the pointer updates correctly (`mid + 1` vs. `mid`).
- [ ] Handle the base case `start == end`.
- [ ] Consider edge cases: single element, no rotation.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Minimum In Rotated Sorted Array II
*   Search in Rotated Sorted Array
*   Search in Rotated Sorted Array II

## Tags
`Array` `Binary Search`
