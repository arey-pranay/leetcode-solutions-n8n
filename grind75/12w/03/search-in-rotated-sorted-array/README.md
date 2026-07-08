# Search In Rotated Sorted Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int search(int[] nums, int target) {
      
        // agr target r se bhi bda hai, to left me dekho
        // agr l se bhi chhota hai to right me dekho
        
        // at every index, ya to left sorted hoga, ya right sorted hoga
        int start = 0;
        int end = nums.length-1;
        if(nums.length==1) return nums[0] == target ? 0 : -1;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(nums[start]==target) return start;
            if(nums[mid]==target) return mid;
            if(nums[end]==target) return end;
            
            if(nums[start] <= nums[mid]){//left sorted hai
                if(nums[start] <= target && target <= nums[mid]) end = mid-1; // mtlb left half me hai target
                else start = mid+1;
            } 
            else{ //right sorted hai
                if(nums[mid] <= target && target <= nums[end]) start = mid+1; // mtlb right half me hai target
                else end = mid-1;
            } 
        }
        return -1;
    }
}
```

---

---
## Quick Revision
This problem asks to find a target in a sorted array that has been rotated at an unknown pivot.
We can solve this by adapting binary search to handle the rotated nature of the array.

## Intuition
The core idea is that even though the entire array is rotated, at any given `mid` point, at least one half of the array (from `start` to `mid` or from `mid` to `end`) must be sorted. We can leverage this sorted half to determine which half the target *could* be in, and then adjust our search space accordingly. This is a modification of the standard binary search.

## Algorithm
1. Initialize `start` to 0 and `end` to `nums.length - 1`.
2. Handle the edge case where the array has only one element.
3. While `start` is less than or equal to `end`:
    a. Calculate `mid` as `start + (end - start) / 2` to prevent potential integer overflow.
    b. Check if `nums[start]`, `nums[mid]`, or `nums[end]` is the target. If so, return the corresponding index.
    c. Determine which half is sorted:
        i. If `nums[start] <= nums[mid]` (the left half is sorted):
            - If the `target` is within the range of the sorted left half (`nums[start] <= target <= nums[mid]`), then the target must be in the left half. Update `end = mid - 1`.
            - Otherwise, the target must be in the right half. Update `start = mid + 1`.
        ii. Else (the right half is sorted):
            - If the `target` is within the range of the sorted right half (`nums[mid] <= target <= nums[end]`), then the target must be in the right half. Update `start = mid + 1`.
            - Otherwise, the target must be in the left half. Update `end = mid - 1`.
4. If the loop finishes without finding the target, return -1.

## Concept to Remember
*   **Binary Search Adaptation:** Understanding how to modify binary search for non-standard array structures.
*   **Identifying Sorted Subarrays:** Recognizing that in a rotated sorted array, at least one half (left or right of `mid`) will always be sorted.
*   **Range Checking:** Using the sorted subarray's bounds to efficiently prune the search space.

## Common Mistakes
*   **Incorrectly handling the pivot:** Not realizing that one half is always sorted and trying to apply standard binary search logic.
*   **Off-by-one errors in boundary updates:** Incorrectly setting `start = mid` or `end = mid` instead of `mid + 1` or `mid - 1`.
*   **Not checking `nums[start]` and `nums[end]`:** While `nums[mid]` is checked, explicitly checking `nums[start]` and `nums[end]` can sometimes simplify logic or catch edge cases, though it's not strictly necessary if the main logic is sound.
*   **Integer Overflow:** Not using `start + (end - start) / 2` for `mid` calculation.

## Complexity Analysis
*   **Time:** O(log n) - The algorithm repeatedly divides the search interval in half, similar to standard binary search.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `start`, `end`, and `mid`.

## Commented Code
```java
class Solution {
    public int search(int[] nums, int target) {
        // Initialize the start and end pointers for binary search.
        int start = 0;
        int end = nums.length - 1;

        // Handle the edge case where the array has only one element.
        if (nums.length == 1) {
            // If the single element is the target, return its index (0), otherwise return -1.
            return nums[0] == target ? 0 : -1;
        }

        // Continue the binary search as long as the start pointer is less than or equal to the end pointer.
        while (start <= end) {
            // Calculate the middle index to avoid potential integer overflow.
            int mid = start + (end - start) / 2;

            // Check if the element at the start pointer is the target.
            if (nums[start] == target) return start;
            // Check if the element at the middle pointer is the target.
            if (nums[mid] == target) return mid;
            // Check if the element at the end pointer is the target.
            if (nums[end] == target) return end;

            // Determine which half of the array is sorted.
            if (nums[start] <= nums[mid]) { // The left half (from start to mid) is sorted.
                // Check if the target lies within the sorted left half.
                if (nums[start] <= target && target <= nums[mid]) {
                    // If target is in the left sorted half, discard the right half.
                    end = mid - 1;
                } else {
                    // If target is not in the left sorted half, it must be in the right half.
                    start = mid + 1;
                }
            } else { // The right half (from mid to end) is sorted.
                // Check if the target lies within the sorted right half.
                if (nums[mid] <= target && target <= nums[end]) {
                    // If target is in the right sorted half, discard the left half.
                    start = mid + 1;
                } else {
                    // If target is not in the right sorted half, it must be in the left half.
                    end = mid - 1;
                }
            }
        }
        // If the loop finishes without finding the target, return -1.
        return -1;
    }
}
```

## Interview Tips
*   **Explain the "sorted half" logic:** Clearly articulate how you identify which half is sorted and how that helps you narrow down the search.
*   **Walk through an example:** Use a small rotated array (e.g., `[4, 5, 6, 7, 0, 1, 2]`) and trace the execution for a target that exists and one that doesn't.
*   **Discuss edge cases:** Mention how you handle empty arrays (though constraints might prevent this), single-element arrays, and cases where the target is at the boundaries or the pivot.
*   **Clarify constraints:** Ask about potential duplicates in the array, as this can complicate the logic (though this specific problem statement usually implies unique elements).

## Revision Checklist
- [ ] Understand the problem: search in a rotated sorted array.
- [ ] Recall binary search principles.
- [ ] Identify the key insight: at least one half is always sorted.
- [ ] Implement the logic to check which half is sorted.
- [ ] Implement the logic to check if the target falls within the sorted half.
- [ ] Correctly update `start` and `end` pointers.
- [ ] Handle edge cases (e.g., single element array).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array II (with duplicates)
*   Find Minimum in Rotated Sorted Array
*   Find First and Last Position of Element in Sorted Array

## Tags
`Array` `Binary Search`
