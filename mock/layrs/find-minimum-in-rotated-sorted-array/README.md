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
      if(nums.length==0) return -1;
      return search(nums,0,nums.length-1);
    }
    public int search(int[] nums, int s, int e){
        if(s==e) return nums[e];
        int m = s + (e-s)/2;
        if(nums[m] < nums[e]) return search(nums,s,m); //eliminate end if end is greater
        else return search(nums,m+1,e);
    }
}
```

---

---
## Quick Revision
Given a sorted array that has been rotated at some pivot point, find the minimum element.
This is solved efficiently using a modified binary search.

## Intuition
The key insight is that in a rotated sorted array, the minimum element will always be the one that is smaller than its preceding element (if it exists). In a standard binary search, we compare the middle element with the target. Here, we compare the middle element with the *rightmost* element. If `nums[mid] < nums[right]`, it means the right half (including `mid`) is sorted, and the minimum must be in the left half (including `mid`). If `nums[mid] > nums[right]`, it means the pivot point (and thus the minimum) must be in the right half (excluding `mid`). The base case is when the search space shrinks to a single element.

## Algorithm
1. Handle the edge case of an empty array.
2. Initialize `left` pointer to 0 and `right` pointer to `nums.length - 1`.
3. While `left < right`:
    a. Calculate the middle index: `mid = left + (right - left) / 2`.
    b. If `nums[mid] < nums[right]`: The minimum is in the left half (including `mid`). So, update `right = mid`.
    c. Else (`nums[mid] > nums[right]`): The minimum is in the right half (excluding `mid`). So, update `left = mid + 1`.
4. When the loop terminates, `left` will be equal to `right`, and this index will point to the minimum element. Return `nums[left]`.

## Concept to Remember
*   Binary Search: Efficiently searching sorted data by repeatedly dividing the search interval in half.
*   Pivot Point: Understanding how rotation affects the sorted property and where the minimum element lies relative to the pivot.
*   Invariant Maintenance: Ensuring that the minimum element always remains within the `[left, right]` search space.

## Common Mistakes
*   Incorrectly handling the `mid` pointer update: Forgetting to include `mid` in the left half when `nums[mid] < nums[right]`, or incorrectly excluding `mid` when `nums[mid] > nums[right]`.
*   Not handling the base case correctly: The loop condition `left < right` is crucial, and the termination condition `left == right` correctly identifies the minimum.
*   Off-by-one errors in pointer updates: Miscalculating `mid + 1` or `mid` when narrowing the search space.
*   Not considering the case where the array is not rotated at all (i.e., it's still fully sorted). The algorithm should still correctly return the first element.

## Complexity Analysis
*   Time: O(log n) - reason: The algorithm uses binary search, which halves the search space in each step.
*   Space: O(1) - reason: The algorithm uses a constant amount of extra space for variables like `left`, `right`, and `mid`. (Note: The provided recursive solution uses O(log n) space due to the call stack, but an iterative solution is O(1)).

## Commented Code
```java
class Solution {
    public int findMin(int[] nums) {
      // Handle the edge case where the input array is empty.
      if(nums.length==0) return -1; // Returning -1 as per problem constraints or a sensible default.
      // Initiate the recursive search function with the entire array range.
      return search(nums,0,nums.length-1);
    }

    // Recursive helper function to find the minimum element.
    public int search(int[] nums, int s, int e){
        // Base case: If the start and end pointers meet, we've found the minimum element.
        if(s==e) return nums[e];
        // Calculate the middle index to divide the array. Using (e-s)/2 prevents potential integer overflow.
        int m = s + (e-s)/2;
        // If the middle element is less than the rightmost element,
        // it means the right half (including mid) is sorted, and the minimum must be in the left half.
        if(nums[m] < nums[e]) return search(nums,s,m); // Recurse on the left half, including mid.
        // Otherwise, the minimum element must be in the right half (excluding mid),
        // because the pivot point lies somewhere to the right of mid.
        else return search(nums,m+1,e); // Recurse on the right half, starting from mid + 1.
    }
}
```

## Interview Tips
*   Clearly explain the binary search logic and why comparing `nums[mid]` with `nums[right]` is the correct approach for a rotated array.
*   Walk through an example with a rotated array and trace how the `left` and `right` pointers move.
*   Discuss the edge cases: empty array, array with one element, and an array that is not rotated at all.
*   Be prepared to convert the recursive solution to an iterative one to achieve O(1) space complexity.

## Revision Checklist
- [ ] Understand the problem statement for rotated sorted arrays.
- [ ] Implement binary search with correct pointer updates.
- [ ] Handle edge cases (empty array, single element, no rotation).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Find Minimum In Rotated Sorted Array II
*   Search in Rotated Sorted Array
*   Search in Rotated Sorted Array II

## Tags
`Array` `Binary Search`
