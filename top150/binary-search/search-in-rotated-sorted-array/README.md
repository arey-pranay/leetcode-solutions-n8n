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
       int start = 0;
       int end = nums.length -1; 
        while(start <= end){
            int mid = start + (end-start)/2;
            if(target == nums[start]) return start;
            if(target == nums[end]) return end;
            if(nums[mid] == target) return mid;
            if(nums[mid] > nums[start]){ 
                if(target >= nums[start] && target <= nums[mid]) end = mid-1;
                else start = mid+1;
            } else {
                if(target >= nums[mid] && target <= nums[end]) start = mid+1;
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
This problem asks to find a target value in a sorted array that has been rotated at an unknown pivot point.
We solve this by adapting binary search to handle the rotated nature of the array.

## Intuition
The core idea is that even though the entire array is rotated, at least one half of the array (from `start` to `mid`, or from `mid` to `end`) will always be sorted. We can leverage this sorted subarray to determine which half the `target` *could* be in, and then adjust our search space accordingly. If `nums[mid]` is the target, we're done. Otherwise, we check if the left half (`nums[start]` to `nums[mid]`) is sorted. If it is, we check if the target falls within this sorted range. If not, the target must be in the right half. If the left half is not sorted, then the right half (`nums[mid]` to `nums[end]`) *must* be sorted, and we perform a similar check for the target within that range.

## Algorithm
1. Initialize `start` to 0 and `end` to `nums.length - 1`.
2. While `start` is less than or equal to `end`:
   a. Calculate `mid` as `start + (end - start) / 2` to prevent potential integer overflow.
   b. If `nums[mid]` equals `target`, return `mid`.
   c. If `nums[start]` equals `target`, return `start`.
   d. If `nums[end]` equals `target`, return `end`.
   e. Check if the left half (`nums[start]` to `nums[mid]`) is sorted:
      i. If `nums[mid] > nums[start]`:
         - If `target` is within the range `[nums[start], nums[mid])`, then the target must be in the left half, so set `end = mid - 1`.
         - Otherwise, the target must be in the right half, so set `start = mid + 1`.
   f. Else (the right half `nums[mid]` to `nums[end]` must be sorted):
      i. If `target` is within the range `(nums[mid], nums[end]]`, then the target must be in the right half, so set `start = mid + 1`.
      ii. Otherwise, the target must be in the left half, so set `end = mid - 1`.
3. If the loop finishes without finding the target, return -1.

## Concept to Remember
*   Binary Search: The fundamental algorithm for efficient searching in sorted data.
*   Handling Rotated Arrays: Adapting standard algorithms to work with data that has a specific structural modification.
*   Identifying Sorted Subarrays: Recognizing that in a rotated sorted array, at least one half is always sorted.

## Common Mistakes
*   Not handling the `start` and `end` boundary checks correctly within the loop.
*   Incorrectly determining which half of the array is sorted.
*   Failing to account for the case where `target` might be `nums[start]` or `nums[end]` before the main binary search logic.
*   Integer overflow when calculating `mid` if `start` and `end` are very large.

## Complexity Analysis
*   Time: O(log n) - reason: In each step of the `while` loop, we effectively discard half of the remaining search space, which is characteristic of binary search.
*   Space: O(1) - reason: We are only using a few variables (`start`, `end`, `mid`) to keep track of indices, which requires constant extra space.

## Commented Code
```java
class Solution {
    public int search(int[] nums, int target) {
       // Initialize the start pointer to the beginning of the array.
       int start = 0;
       // Initialize the end pointer to the end of the array.
       int end = nums.length - 1; 
        // Continue searching as long as the start pointer is less than or equal to the end pointer.
        while(start <= end){
            // Calculate the middle index to avoid potential integer overflow.
            int mid = start + (end-start)/2;
            // Check if the target is at the start of the current search space.
            if(target == nums[start]) return start;
            // Check if the target is at the end of the current search space.
            if(target == nums[end]) return end;
            // Check if the target is at the middle of the current search space.
            if(nums[mid] == target) return mid;
            
            // Determine which half of the array is sorted.
            // If the left half (from start to mid) is sorted.
            if(nums[mid] > nums[start]){ 
                // Check if the target falls within the sorted left half.
                if(target >= nums[start] && target <= nums[mid]) {
                    // If it does, search in the left half by moving the end pointer.
                    end = mid-1;
                } else {
                    // Otherwise, the target must be in the unsorted right half, so search there.
                    start = mid+1;
                }
            } else { // If the left half is not sorted, then the right half (from mid to end) must be sorted.
                // Check if the target falls within the sorted right half.
                if(target >= nums[mid] && target <= nums[end]) {
                    // If it does, search in the right half by moving the start pointer.
                    start = mid+1;
                } else {
                    // Otherwise, the target must be in the unsorted left half, so search there.
                    end = mid-1;
                }
            }
        }
        // If the target is not found after the loop, return -1.
        return -1;
    }
}
```

## Interview Tips
*   Clearly explain your thought process for identifying the sorted subarray.
*   Walk through an example with a rotated array and a target value, showing how `start`, `end`, and `mid` change.
*   Be prepared to discuss edge cases like an empty array, an array with one element, or an array that is not rotated at all.
*   Mention the optimization of checking `nums[start]` and `nums[end]` early to potentially exit the loop faster.

## Revision Checklist
- [ ] Understand the problem statement for rotated sorted arrays.
- [ ] Implement binary search with modifications for rotation.
- [ ] Correctly identify the sorted half in each iteration.
- [ ] Handle boundary conditions (`start <= end`).
- [ ] Consider edge cases (empty array, single element).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array II
*   Find Minimum in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array II

## Tags
`Array` `Binary Search`
