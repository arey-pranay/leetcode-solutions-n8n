# Search Insert Position

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        return bs(nums, target, 0, nums.length - 1);
    }

    private int bs(int[] nums, int target, int start, int end) {
        if (start > end) {
            return start;
        }
        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return bs(nums, target, start, mid - 1);
        } else {
            return bs(nums, target, mid + 1, end);
        }
    }
}
```

---

---
## Quick Revision
Search for the position to insert a target value in a sorted array.
Solve it using Binary Search.

## Intuition
The "aha moment" here is that we can use Binary Search to find the insertion point in O(log n) time, because the input array is already sorted. This approach works because we can divide the search space in half with each recursive call, effectively reducing the number of comparisons needed to find the target value.

## Algorithm
1. Define a helper function `bs` that performs Binary Search on the given array.
2. Initialize two pointers, `start` and `end`, to represent the current search range.
3. If `start > end`, return `start` as it's the insertion point (because the target value is not found in the current range).
4. Calculate the midpoint `mid` of the current search range.
5. Compare the middle element `nums[mid]` with the target value:
	* If they're equal, return `mid`.
	* If `nums[mid] > target`, move the search range to the left half (`bs(nums, target, start, mid - 1)`).
	* Otherwise, move the search range to the right half (`bs(nums, target, mid + 1, end)`).

## Concept to Remember
• **Binary Search**: a divide-and-conquer algorithm that finds an element in a sorted array by repeatedly dividing the search space in half.
• **Sorted Arrays**: Binary Search works efficiently on sorted arrays because we can eliminate half of the search space with each comparison.

## Common Mistakes
• Failing to update the search range correctly after comparing elements.
• Not handling edge cases properly (e.g., when `start > end`).
• Using a loop instead of recursion for Binary Search.

## Complexity Analysis
- Time: O(log n) - because we divide the search space in half with each recursive call.
- Space: O(1) - since we only use a constant amount of space to store the recursive function calls.

## Commented Code
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        // Call the helper function bs to find the insertion point
        return bs(nums, target, 0, nums.length - 1);
    }

    private int bs(int[] nums, int target, int start, int end) {
        // Base case: if search range is empty (start > end), return start as the insertion point
        if (start > end) {
            return start;
        }
        
        // Calculate midpoint of current search range
        int mid = start + (end - start) / 2;

        // Compare middle element with target value
        if (nums[mid] == target) {
            // Target found, return midpoint as insertion point
            return mid;
        } else if (nums[mid] > target) {
            // Move search range to left half
            return bs(nums, target, start, mid - 1);
        } else {
            // Move search range to right half
            return bs(nums, target, mid + 1, end);
        }
    }
}
```

## Interview Tips
• Practice implementing Binary Search from scratch.
• Be prepared to explain why this approach is efficient (O(log n)).
• Pay attention to edge cases and handling them correctly.

## Revision Checklist
- [ ] Implement Binary Search from scratch.
- [ ] Understand the concept of sorted arrays and Binary Search.
- [ ] Review edge cases and how to handle them properly.

## Similar Problems
* LeetCode: 81. `Search in Rotated Sorted Array`
* LeetCode: 153. `Find Minimum in Rotated Sorted Array`

## Tags
`Array` `Hash Map` `Binary Search`
