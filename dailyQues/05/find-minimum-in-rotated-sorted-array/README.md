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
        int start =0;
        int end = nums.length-1;
        
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[start] < nums[mid] &&  nums[mid] < nums[end]) return nums[start]; 
            else if(nums[end] < nums[mid]) start = mid+1;//mtlb right side sorted hai.
            else end = mid;  //left side sorted hai        
        }
        return nums[start];
        // esa number jiska left bhi bda hai aur right bhi bda
    }
}
//[3,1,2]
```

---

---
## Quick Revision
This problem asks to find the minimum element in a sorted array that has been rotated.
We can solve this efficiently using a modified binary search approach.

## Intuition
The core idea is that in a rotated sorted array, the minimum element is the *only* element that is smaller than its preceding element (considering wrap-around). If the array is not rotated, the minimum is simply the first element.

In a rotated sorted array, there will be a "pivot" point where the sorted order breaks. For example, in `[4, 5, 6, 7, 0, 1, 2]`, the pivot is between 7 and 0. The minimum element will always be on the "unsorted" side of the pivot.

Binary search helps us quickly narrow down which half of the array contains this pivot and thus the minimum element. If `nums[mid]` is greater than `nums[end]`, it means the minimum element *must* be in the right half (including `mid+1` because `mid` itself cannot be the minimum if it's greater than `nums[end]`). If `nums[mid]` is less than or equal to `nums[end]`, it means the minimum element is either `nums[mid]` or in the left half.

## Algorithm
1. Initialize two pointers, `start` to the beginning of the array (index 0) and `end` to the end of the array (index `nums.length - 1`).
2. While `start` is less than `end`:
    a. Calculate the middle index: `mid = start + (end - start) / 2`. This prevents potential integer overflow.
    b. **Check if the left half is sorted and `nums[mid]` is within the sorted range:** If `nums[start] < nums[mid]` and `nums[mid] < nums[end]`, it implies the entire segment from `start` to `end` is sorted (or the rotation point is outside this segment and the minimum is `nums[start]`). In this case, `nums[start]` is the minimum. Return `nums[start]`.
    c. **Check if the minimum is in the right half:** If `nums[end] < nums[mid]`, it means the rotation point (and thus the minimum element) lies in the right half of the array (from `mid + 1` to `end`). So, update `start = mid + 1`.
    d. **Check if the minimum is in the left half (or is `nums[mid]`):** If `nums[mid] <= nums[end]`, it means the minimum element is either `nums[mid]` or in the left half of the array (from `start` to `mid`). So, update `end = mid`.
3. When the loop terminates (`start == end`), `start` (or `end`) will point to the minimum element. Return `nums[start]`.

## Concept to Remember
*   Binary Search: Efficiently searching a sorted (or partially sorted) data structure by repeatedly dividing the search interval in half.
*   Array Rotation: Understanding how the sorted property is broken and where the minimum element is located relative to the pivot.
*   Edge Cases: Handling arrays that are not rotated or have only one element.

## Common Mistakes
*   Incorrectly handling the `mid` pointer update: Forgetting to add 1 when moving `start` or not correctly setting `end` to `mid` can lead to infinite loops or incorrect results.
*   Not considering the case where the array is not rotated at all: The algorithm should correctly identify `nums[0]` as the minimum in such scenarios.
*   Off-by-one errors in loop conditions or pointer updates.
*   Integer overflow when calculating `mid` if `start + end` is used directly.

## Complexity Analysis
*   Time: O(log n) - The algorithm uses binary search, which halves the search space in each iteration.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `start`, `end`, and `mid`.

## Commented Code
```java
class Solution {
    public int findMin(int[] nums) {
        // Initialize the start pointer to the beginning of the array.
        int start = 0;
        // Initialize the end pointer to the end of the array.
        int end = nums.length - 1;

        // Continue the loop as long as the start pointer is before the end pointer.
        // This ensures we are always searching within a valid range.
        while (start < end) {
            // Calculate the middle index to divide the array.
            // Using (end - start) / 2 prevents potential integer overflow.
            int mid = start + (end - start) / 2;

            // Case 1: If the element at the start is less than the element at mid,
            // AND the element at mid is less than the element at end,
            // it means the subarray from start to end is sorted in ascending order.
            // In this scenario, the minimum element is the first element of this sorted subarray.
            if (nums[start] < nums[mid] && nums[mid] < nums[end]) {
                return nums[start];
            }
            // Case 2: If the element at the end is less than the element at mid,
            // it implies that the rotation point (and thus the minimum element)
            // must lie in the right half of the array (from mid + 1 to end).
            // So, we discard the left half by moving the start pointer to mid + 1.
            else if (nums[end] < nums[mid]) {
                start = mid + 1;
            }
            // Case 3: If the element at mid is less than or equal to the element at end,
            // it implies that the minimum element is either nums[mid] itself or
            // lies in the left half of the array (from start to mid).
            // So, we discard the right half by moving the end pointer to mid.
            else { // nums[mid] <= nums[end]
                end = mid;
            }
        }
        // When the loop terminates, start and end pointers will be at the same index,
        // which points to the minimum element in the rotated sorted array.
        return nums[start];
    }
}
```

## Interview Tips
*   Clearly explain your binary search logic and how you're narrowing down the search space.
*   Walk through an example like `[4, 5, 6, 7, 0, 1, 2]` and `[3, 1, 2]` to demonstrate your understanding.
*   Be prepared to discuss edge cases like an already sorted array or an array with duplicates (though this specific problem statement implies unique elements).
*   Mention the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the definition of a rotated sorted array.
- [ ] Identify the property of the minimum element (smaller than its predecessor).
- [ ] Implement binary search correctly with appropriate pointer updates.
- [ ] Handle the case where the array is not rotated.
- [ ] Test with various examples, including edge cases.

## Similar Problems
*   Find Minimum In Rotated Sorted Array II (handles duplicates)
*   Search in Rotated Sorted Array
*   Find Peak Element

## Tags
`Array` `Binary Search`
