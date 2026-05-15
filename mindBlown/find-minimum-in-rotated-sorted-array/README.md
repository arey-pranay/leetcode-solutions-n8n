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
The core idea is that in a rotated sorted array, the minimum element is the *only* element that is smaller than its preceding element (considering wrap-around). If we perform a binary search, we can leverage the sorted nature of the two halves created by the `mid` pointer to determine which half contains the minimum element. If the `mid` element is greater than the `end` element, it means the rotation point (and thus the minimum) must be in the right half. Otherwise, the minimum is in the left half or is the `mid` element itself.

## Algorithm
1. Initialize two pointers, `start` to the beginning of the array (index 0) and `end` to the end of the array (index `nums.length - 1`).
2. While `start` is less than `end`:
   a. Calculate the middle index: `mid = start + (end - start) / 2`. This prevents potential integer overflow.
   b. **Check if the array segment from `start` to `end` is already sorted:** If `nums[start] < nums[mid]` and `nums[mid] < nums[end]`, it implies the entire current segment is sorted, and `nums[start]` is the minimum. Return `nums[start]`.
   c. **Determine which half contains the minimum:**
      i. If `nums[end] < nums[mid]`: This means the rotation point (and thus the minimum element) lies in the right half of the array (from `mid + 1` to `end`). So, update `start = mid + 1`.
      ii. Else (`nums[mid] <= nums[end]`): This means the minimum element is either `nums[mid]` or lies in the left half of the array (from `start` to `mid`). So, update `end = mid`.
3. Once the loop terminates (`start == end`), `nums[start]` (or `nums[end]`) will be the minimum element. Return `nums[start]`.

## Concept to Remember
*   Binary Search: Efficiently searching a sorted data structure by repeatedly dividing the search interval in half.
*   Array Rotation: Understanding how the sorted property is broken and maintained in different segments of a rotated array.
*   Edge Cases: Handling scenarios like an already sorted array or an array with only one element.

## Common Mistakes
*   Incorrectly handling the `mid` pointer update when the minimum is found.
*   Not considering the case where the array is not rotated at all (i.e., it's still fully sorted).
*   Integer overflow when calculating `mid` if `start` and `end` are very large.
*   Off-by-one errors in updating `start` and `end` pointers.

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

        // Continue the binary search as long as the start pointer is before the end pointer.
        while (start < end) {
            // Calculate the middle index to divide the array.
            // Using (end - start) / 2 prevents potential integer overflow.
            int mid = start + (end - start) / 2;

            // Check if the current segment from start to end is already sorted.
            // If nums[start] < nums[mid] AND nums[mid] < nums[end], it means the entire segment is sorted.
            // In this case, the minimum element is the first element of this sorted segment.
            if (nums[start] < nums[mid] && nums[mid] < nums[end]) {
                return nums[start];
            }
            // If nums[end] < nums[mid], it implies that the rotation point (and thus the minimum element)
            // must be in the right half of the array (from mid + 1 to end).
            // The left half (start to mid) is sorted and contains larger elements than nums[end].
            else if (nums[end] < nums[mid]) {
                // Move the start pointer to mid + 1 to search in the right half.
                start = mid + 1;
            }
            // If nums[mid] <= nums[end], it implies that the minimum element is either nums[mid] itself
            // or it lies in the left half of the array (from start to mid).
            // The right half (mid to end) is sorted and contains elements greater than or equal to nums[mid].
            else {
                // Move the end pointer to mid to search in the left half (including mid).
                end = mid;
            }
        }
        // When the loop terminates, start and end pointers will be at the same index.
        // This index points to the minimum element in the rotated sorted array.
        return nums[start];
        // The logic here is that the minimum element is the only one where its left neighbor (conceptually) is larger.
        // The loop invariant ensures we always narrow down to the segment containing the minimum.
    }
}
```

## Interview Tips
*   Clearly explain the binary search logic and how you're using the comparison between `nums[mid]` and `nums[end]` (or `nums[start]`) to decide which half to search.
*   Walk through an example like `[4,5,6,7,0,1,2]` and `[3,1,2]` to demonstrate your understanding.
*   Mention the edge case of an already sorted array and how your algorithm handles it.
*   Be prepared to discuss why `mid = start + (end - start) / 2` is preferred over `mid = (start + end) / 2`.

## Revision Checklist
- [ ] Understand the problem statement: find minimum in a rotated sorted array.
- [ ] Recall binary search principles.
- [ ] Identify the condition that signifies the minimum element.
- [ ] Implement the binary search with correct pointer updates.
- [ ] Test with edge cases: sorted array, single element array, array with duplicates (though this problem statement implies unique elements).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Minimum In Rotated Sorted Array II (handles duplicates)
*   Search in Rotated Sorted Array
*   Find First and Last Position of Element in Sorted Array

## Tags
`Array` `Binary Search`
