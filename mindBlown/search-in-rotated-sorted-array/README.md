# Search In Rotated Sorted Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public int search(int[] nums, int target) {
        //binary search. find target. 
        int start = 0;
        int end = nums.length-1;
        if(nums.length==1 && nums[0]==target) return 0;
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[mid]==target) return mid;
            if(nums[start]==target) return start;
            if(nums[end]==target) return end;

            
            if(nums[start] <= nums[mid]){ //left side sorted
                if(target>= nums[start]  && target < nums[mid]) end = mid-1; // search target between s and m
                else start = mid+1; // eliminate left side
            } else{ //right side sorted
                if(target <= nums[end] && target > nums[mid]) start = mid+1; // search target between m and e
                else end = mid-1; // eliminate right side
            }
        }
        return -1;
    }
}
```

---

---
## Quick Revision
Given a sorted array that has been rotated at an unknown pivot point, find the index of a given target value.
This problem is solved using a modified binary search algorithm.

## Intuition
The core idea is that even though the entire array is rotated, at least one half of the array (from `start` to `mid` or from `mid` to `end`) will always be sorted. We can leverage this sorted sub-array to determine which half the `target` *could* be in, and then discard the other half. This allows us to maintain the O(log n) time complexity of binary search.

## Algorithm
1. Initialize `start` to 0 and `end` to `nums.length - 1`.
2. Handle the edge case where the array has only one element and it matches the target.
3. While `start` is less than `end`:
    a. Calculate `mid` using `start + (end - start) / 2` to prevent potential integer overflow.
    b. If `nums[mid]` is the `target`, return `mid`.
    c. Check if `nums[start]` or `nums[end]` is the `target` and return their indices if they match. This is an optimization to quickly find the target if it's at the boundaries.
    d. Determine which half of the array is sorted:
        i. If `nums[start] <= nums[mid]` (the left half is sorted):
            - If the `target` is within the range of the sorted left half (`target >= nums[start]` and `target < nums[mid]`), then the `target` must be in the left half. Update `end = mid - 1`.
            - Otherwise, the `target` must be in the right (unsorted) half. Update `start = mid + 1`.
        ii. Else (`nums[mid] < nums[start]`, meaning the right half is sorted):
            - If the `target` is within the range of the sorted right half (`target <= nums[end]` and `target > nums[mid]`), then the `target` must be in the right half. Update `start = mid + 1`.
            - Otherwise, the `target` must be in the left (unsorted) half. Update `end = mid - 1`.
4. If the loop finishes without finding the `target`, return -1.

## Concept to Remember
*   **Binary Search:** The fundamental algorithm for searching in sorted data structures.
*   **Divide and Conquer:** Breaking down a problem into smaller subproblems.
*   **Identifying Sorted Sub-arrays:** Recognizing that in a rotated sorted array, at least one half is always sorted.
*   **Edge Case Handling:** Properly managing conditions like single-element arrays or target at boundaries.

## Common Mistakes
*   **Integer Overflow:** Using `(start + end) / 2` for `mid` can lead to overflow if `start` and `end` are very large. The correct way is `start + (end - start) / 2`.
*   **Incorrect Boundary Conditions:** Mishandling the `start < end` condition or the `mid - 1` / `mid + 1` updates can lead to infinite loops or missed elements.
*   **Not Handling the Rotated Nature:** Applying a standard binary search without accounting for the rotation will fail.
*   **Missing Edge Cases:** Forgetting to check for single-element arrays or the target being at the `start` or `end` indices.

## Complexity Analysis
*   **Time:** O(log n) - The algorithm halves the search space in each iteration, similar to standard binary search.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `start`, `end`, and `mid`.

## Commented Code
```java
class Solution {
    public int search(int[] nums, int target) {
        // Initialize the start and end pointers for binary search.
        int start = 0;
        int end = nums.length - 1;

        // Handle the edge case where the array has only one element.
        if (nums.length == 1 && nums[0] == target) return 0;

        // Continue the binary search as long as the start pointer is less than the end pointer.
        while (start < end) {
            // Calculate the middle index to avoid potential integer overflow.
            int mid = start + (end - start) / 2;

            // If the middle element is the target, return its index.
            if (nums[mid] == target) return mid;
            // Optimization: Check if the start element is the target.
            if (nums[start] == target) return start;
            // Optimization: Check if the end element is the target.
            if (nums[end] == target) return end;

            // Determine which half of the array is sorted.
            if (nums[start] <= nums[mid]) { // The left half (from start to mid) is sorted.
                // Check if the target lies within the sorted left half.
                if (target >= nums[start] && target < nums[mid]) {
                    // If it does, discard the right half by moving the end pointer.
                    end = mid - 1;
                } else {
                    // If it doesn't, the target must be in the right (unsorted) half. Discard the left half.
                    start = mid + 1;
                }
            } else { // The right half (from mid to end) is sorted.
                // Check if the target lies within the sorted right half.
                if (target <= nums[end] && target > nums[mid]) {
                    // If it does, discard the left half by moving the start pointer.
                    start = mid + 1;
                } else {
                    // If it doesn't, the target must be in the left (unsorted) half. Discard the right half.
                    end = mid - 1;
                }
            }
        }
        // If the loop finishes and the target is not found, return -1.
        return -1;
    }
}
```

## Interview Tips
*   **Explain the "Why":** Clearly articulate *why* one half of the array is always sorted and how you use that fact to narrow down the search.
*   **Walk Through Examples:** Use a small rotated array (e.g., `[4, 5, 6, 7, 0, 1, 2]`) and trace the algorithm with different target values, especially those at the pivot point or boundaries.
*   **Discuss Edge Cases:** Be prepared to discuss how your solution handles arrays with one element, arrays where the target is the first or last element, and arrays where the target is not present.
*   **Clarify Ambiguities:** If the problem statement mentions duplicates, ask how they should be handled, as duplicates can complicate the logic. (This specific problem statement implies no duplicates, but it's a good general interview practice).

## Revision Checklist
- [ ] Understand the problem of searching in a rotated sorted array.
- [ ] Implement binary search logic.
- [ ] Correctly identify the sorted half of the array in each step.
- [ ] Accurately determine if the target falls within the sorted half.
- [ ] Handle boundary conditions and edge cases (single element, target at ends).
- [ ] Ensure correct pointer updates (`start = mid + 1`, `end = mid - 1`).
- [ ] Verify time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array II (handles duplicates)
*   Find Minimum in Rotated Sorted Array
*   Find First and Last Position of Element in Sorted Array
*   Binary Search

## Tags
`Array` `Binary Search`
