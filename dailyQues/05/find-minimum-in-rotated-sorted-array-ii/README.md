# Find Minimum In Rotated Sorted Array Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log N)  
**Space:** O(1)

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
            else if(nums[start] < nums[mid]) end = mid;  //left side sorted hai 
            else end--;  // mid end se bhi bda nhi hai, aur mid start se bhi bda nhi hai. mtlb end me redundant elements hai
        }
        return nums[start];
        // esa number jiska left bhi bda hai aur right bhi bda
    }
}
//[3,1,2]
// [3 3 1 3]
```

---

---
## Quick Revision
Given a rotated sorted array that may contain duplicates, find the minimum element.
This problem is solved using a modified binary search to handle duplicates efficiently.

## Intuition
The core idea is to adapt binary search. In a standard rotated sorted array, we can always determine which half is sorted by comparing `nums[mid]` with `nums[start]` or `nums[end]`. However, duplicates introduce ambiguity. If `nums[mid] == nums[end]`, we can't definitively say which side the minimum is on. In such cases, we can safely discard `nums[end]` by decrementing `end` because it's either a duplicate of `nums[mid]` or the minimum itself, and if it's the minimum, `nums[mid]` would also be the minimum (or greater), and we'd eventually find it.

## Algorithm
1. Initialize two pointers, `start` to the beginning of the array (index 0) and `end` to the end of the array (index `nums.length - 1`).
2. While `start` is less than `end`:
    a. Calculate the middle index: `mid = start + (end - start) / 2`.
    b. If `nums[mid]` is greater than `nums[end]`: This means the minimum element must be in the right half (from `mid + 1` to `end`), so update `start = mid + 1`.
    c. If `nums[mid]` is less than `nums[end]`: This means the minimum element is either `nums[mid]` or in the left half (from `start` to `mid`), so update `end = mid`.
    d. If `nums[mid]` is equal to `nums[end]`: We cannot determine which half contains the minimum. To make progress, we can safely discard the `end` element as it's a duplicate or the minimum itself, and we will eventually find the minimum. So, decrement `end` by 1.
3. After the loop terminates, `start` will point to the index of the minimum element. Return `nums[start]`.

## Concept to Remember
*   **Binary Search Adaptation:** Modifying the standard binary search to handle specific array properties (like rotation and duplicates).
*   **Handling Duplicates:** Recognizing when duplicates break standard binary search logic and devising strategies to overcome them (e.g., shrinking the search space by one element).
*   **Monotonicity:** Understanding how the rotated sorted property, even with duplicates, still provides some form of ordering that can be exploited.

## Common Mistakes
*   **Incorrectly handling `nums[mid] == nums[end]`:** Simply moving `start` or `end` without careful consideration can lead to infinite loops or incorrect results.
*   **Off-by-one errors:** Incorrectly updating `start` or `end` (e.g., `mid` instead of `mid + 1` or `mid - 1`).
*   **Not considering edge cases:** Arrays with all duplicates, or arrays that are not rotated at all.
*   **Assuming `nums[start] < nums[mid]` implies left sorted:** This is not always true when `nums[start] == nums[mid]`.

## Complexity Analysis
*   **Time:** O(log N) in the best and average case. In the worst case (e.g., an array with all identical elements like `[1, 1, 1, 1]`), the `end--` operation might be executed N times, leading to O(N) time complexity.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables.

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
            // Calculate the middle index to avoid potential integer overflow.
            int mid = start + (end - start) / 2;

            // If the middle element is greater than the end element,
            // it means the pivot (and thus the minimum) is in the right half.
            if (nums[mid] > nums[end]) {
                // Move the start pointer to mid + 1 to search in the right half.
                start = mid + 1;
            }
            // If the middle element is less than the end element,
            // it means the minimum element is either nums[mid] or in the left half.
            else if (nums[mid] < nums[end]) {
                // Move the end pointer to mid, as nums[mid] could be the minimum.
                end = mid;
            }
            // If nums[mid] == nums[end], we cannot determine which half contains the minimum.
            // This happens when there are duplicates.
            else { // nums[mid] == nums[end]
                // In this case, we can safely discard the end element because it's a duplicate
                // or the minimum itself. If it's the minimum, nums[mid] is also the minimum
                // (or greater), and we will eventually find it.
                // Decrementing end helps to shrink the search space.
                end--;
            }
        }
        // When the loop terminates, start == end, and this index points to the minimum element.
        return nums[start];
    }
}
```

## Interview Tips
*   Clearly explain the challenge duplicates pose to standard binary search.
*   Walk through the `nums[mid] == nums[end]` case and justify why `end--` is a safe operation.
*   Discuss the worst-case time complexity and why it occurs.
*   Be prepared to trace the algorithm with examples like `[3,1,3,3,3]` or `[1,1,1,0,1]`.

## Revision Checklist
- [ ] Understand the problem statement for rotated sorted arrays with duplicates.
- [ ] Implement binary search logic correctly.
- [ ] Handle the `nums[mid] > nums[end]` case.
- [ ] Handle the `nums[mid] < nums[end]` case.
- [ ] Implement the `nums[mid] == nums[end]` case by decrementing `end`.
- [ ] Analyze time and space complexity, including the worst-case scenario.
- [ ] Test with various edge cases (all duplicates, no rotation, single element).

## Similar Problems
*   Find Minimum In Rotated Sorted Array
*   Search in Rotated Sorted Array
*   Search in Rotated Sorted Array II

## Tags
`Array` `Binary Search`

## My Notes
Just one difference from 1st part. this has duplicates, so we added a new condition of end--, and changed the previous  else to an else if case.
because now there can be cases jahan mid start se bhi bda nhi hai aur end se bhi bda nai hai etc
