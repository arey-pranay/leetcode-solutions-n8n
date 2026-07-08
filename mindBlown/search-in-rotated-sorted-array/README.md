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
This problem asks to find a target value in a sorted array that has been rotated at an unknown pivot.
We solve this by adapting binary search to handle the rotated nature of the array.

## Intuition
The core idea is that even though the entire array is rotated, at any given point during a binary search, at least one half of the array (from `start` to `mid` or from `mid` to `end`) *must* be sorted. We can leverage this sorted half to determine which half the `target` *could* be in, and then discard the other half. This allows us to maintain the logarithmic time complexity of binary search.

## Algorithm
1. Initialize `start` to 0 and `end` to `nums.length - 1`.
2. Handle the edge case where the array has only one element.
3. While `start` is less than or equal to `end`:
    a. Calculate `mid` using `start + (end - start) / 2` to prevent overflow.
    b. Check if `nums[start]`, `nums[mid]`, or `nums[end]` is the `target`. If so, return the corresponding index.
    c. Determine which half is sorted:
        i. If `nums[start] <= nums[mid]`, the left half (`start` to `mid`) is sorted.
            - If the `target` falls within the range of the sorted left half (`nums[start] <= target <= nums[mid]`), then search in the left half by setting `end = mid - 1`.
            - Otherwise, the `target` must be in the unsorted right half, so search there by setting `start = mid + 1`.
        ii. Else (if `nums[start] > nums[mid]`), the right half (`mid` to `end`) is sorted.
            - If the `target` falls within the range of the sorted right half (`nums[mid] <= target <= nums[end]`), then search in the right half by setting `start = mid + 1`.
            - Otherwise, the `target` must be in the unsorted left half, so search there by setting `end = mid - 1`.
4. If the loop finishes without finding the `target`, return -1.

## Concept to Remember
*   **Binary Search Adaptation:** Modifying the standard binary search to work with a rotated sorted array.
*   **Identifying Sorted Halves:** Recognizing that at least one half of the array is always sorted, which is key to narrowing down the search space.
*   **Range Checks:** Carefully checking if the target falls within the bounds of the identified sorted half.

## Common Mistakes
*   **Incorrectly handling the pivot:** Not properly identifying which half is sorted or assuming the entire array is sorted.
*   **Off-by-one errors in index updates:** Incorrectly setting `start = mid` or `end = mid` instead of `mid + 1` or `mid - 1`.
*   **Not checking `nums[start]` and `nums[end]`:** While `nums[mid]` is checked, explicitly checking `nums[start]` and `nums[end]` can sometimes simplify logic or catch edge cases early.
*   **Integer overflow:** Using `(start + end) / 2` instead of `start + (end - start) / 2` for calculating `mid`.

## Complexity Analysis
*   **Time:** O(log n) - The algorithm performs a binary search, dividing the search space in half at each step.
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
                    // If it does, search in the left half by moving the end pointer.
                    end = mid - 1;
                } else {
                    // If not, the target must be in the unsorted right half, so search there.
                    start = mid + 1;
                }
            } else { // The right half (from mid to end) is sorted.
                // Check if the target lies within the sorted right half.
                if (nums[mid] <= target && target <= nums[end]) {
                    // If it does, search in the right half by moving the start pointer.
                    start = mid + 1;
                } else {
                    // If not, the target must be in the unsorted left half, so search there.
                    end = mid - 1;
                }
            }
        }
        // If the target is not found after the loop, return -1.
        return -1;
    }
}
```

## Interview Tips
*   **Explain the "sorted half" logic:** Clearly articulate how you identify the sorted portion of the array in each iteration. This is the core of the solution.
*   **Walk through an example:** Use a small rotated array (e.g., `[4, 5, 6, 7, 0, 1, 2]`) and a target value to demonstrate how your algorithm narrows down the search space step-by-step.
*   **Discuss edge cases:** Mention how you handle an empty array (though the problem constraints might prevent this), a single-element array, and cases where the target is at the boundaries or the pivot point.
*   **Be prepared to optimize:** While O(log n) is optimal, ensure you can explain why this approach is better than a linear scan.

## Revision Checklist
- [ ] Understand the problem statement for rotated sorted arrays.
- [ ] Implement binary search correctly.
- [ ] Identify the sorted half in each iteration.
- [ ] Correctly determine if the target is in the sorted half or the other half.
- [ ] Handle boundary conditions and edge cases (single element array).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array II
*   Find Minimum in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array II
*   Search in a Sorted Array of Unknown Size

## Tags
`Array` `Binary Search`
