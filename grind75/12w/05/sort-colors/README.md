# Sort Colors

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Sorting`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
public void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;
    while (mid <= high) {
        if (nums[mid] == 0) {
            int tmp = nums[low];
            nums[low++] = nums[mid];
            nums[mid++] = tmp;
        } else if (nums[mid] == 1) {
            mid++;
        } else {
            int tmp = nums[mid];
            nums[mid] = nums[high];
            nums[high--] = tmp;
        }
    }
}
}
```

---

---
## Quick Revision
Given an array of integers with only values 0, 1, and 2.
Sort the array in-place such that all 0s come first, then all 1s, and finally all 2s.

## Intuition
The problem asks us to sort an array containing only three distinct values (0, 1, 2) in-place. A naive approach would be to use a standard sorting algorithm like quicksort or mergesort, but that would be O(N log N). Since we only have three values, we can do better. The key insight is to partition the array into three sections: elements less than 1 (0s), elements equal to 1 (1s), and elements greater than 1 (2s). We can achieve this by using three pointers: `low`, `mid`, and `high`.

`low` will track the boundary of the 0s section.
`mid` will iterate through the array, examining each element.
`high` will track the boundary of the 2s section.

As `mid` moves, if it encounters a 0, it should be swapped with the element at `low`, and both `low` and `mid` should advance. If `mid` encounters a 1, it's already in its correct relative position, so only `mid` should advance. If `mid` encounters a 2, it should be swapped with the element at `high`, and `high` should decrement. `mid` does *not* advance in this case because the element swapped from `high` needs to be examined. This process continues until `mid` surpasses `high`.

## Algorithm
1. Initialize three pointers: `low = 0`, `mid = 0`, and `high = nums.length - 1`.
2. Iterate while `mid <= high`:
    a. If `nums[mid]` is 0:
        i. Swap `nums[low]` and `nums[mid]`.
        ii. Increment `low`.
        iii. Increment `mid`.
    b. If `nums[mid]` is 1:
        i. Increment `mid`.
    c. If `nums[mid]` is 2:
        i. Swap `nums[mid]` and `nums[high]`.
        ii. Decrement `high`.
        iii. Do *not* increment `mid` (the new `nums[mid]` needs to be checked).
3. The array is now sorted in-place.

## Concept to Remember
*   **Three-Way Partitioning:** This problem is a classic example of the Dutch National Flag problem, which uses three-way partitioning to sort elements into three distinct groups.
*   **In-Place Sorting:** The solution modifies the input array directly without using significant extra space.
*   **Pointer Manipulation:** Efficiently managing multiple pointers is crucial for achieving the desired partitioning and time complexity.

## Common Mistakes
*   **Incorrectly advancing `mid`:** Forgetting to *not* advance `mid` when a swap with `high` occurs (when `nums[mid]` is 2) is a common error. The element swapped from `high` needs to be checked.
*   **Off-by-one errors with pointers:** Incorrectly initializing or updating `low`, `mid`, or `high` can lead to incorrect partitioning or infinite loops.
*   **Not handling all three cases:** Ensuring that the logic correctly addresses 0s, 1s, and 2s is essential.
*   **Using extra space:** While not strictly an error in logic, deviating from the in-place requirement by using auxiliary arrays or hash maps would be suboptimal.

## Complexity Analysis
- Time: O(N) - reason: Each element is visited and swapped at most a constant number of times by the `mid` pointer.
- Space: O(1) - reason: The algorithm sorts the array in-place, using only a few extra variables for pointers.

## Commented Code
```java
class Solution {
    public void sortColors(int[] nums) {
        // Initialize three pointers:
        // low: points to the position where the next 0 should be placed.
        // mid: the current element being examined.
        // high: points to the position where the next 2 should be placed.
        int low = 0, mid = 0, high = nums.length - 1;

        // Iterate through the array as long as the mid pointer is less than or equal to the high pointer.
        // This ensures all elements are examined and placed in their correct partitions.
        while (mid <= high) {
            // If the current element (at mid) is 0:
            if (nums[mid] == 0) {
                // Swap the element at mid with the element at low.
                // This moves the 0 to its correct partition at the beginning of the array.
                int tmp = nums[low];
                nums[low++] = nums[mid]; // Place 0 at low, then increment low to point to the next potential 0 position.
                nums[mid++] = tmp;       // Place the original nums[low] at mid, then increment mid to examine the next element.
            }
            // If the current element (at mid) is 1:
            else if (nums[mid] == 1) {
                // The element is already in its correct relative position (between 0s and 2s).
                // Simply move the mid pointer forward to examine the next element.
                mid++;
            }
            // If the current element (at mid) is 2:
            else { // nums[mid] == 2
                // Swap the element at mid with the element at high.
                // This moves the 2 to its correct partition at the end of the array.
                int tmp = nums[mid];
                nums[mid] = nums[high]; // Place the element from high at mid.
                nums[high--] = tmp;     // Place the original nums[mid] (which was 2) at high, then decrement high.
                                        // IMPORTANT: Do NOT increment mid here, because the element swapped from high
                                        // needs to be examined in the next iteration.
            }
        }
    }
}
```

## Interview Tips
*   **Explain the Dutch National Flag problem:** Mentioning this problem by name shows you're familiar with common algorithmic patterns.
*   **Walk through an example:** Use a small array like `[2, 0, 1, 2, 1, 0]` and trace the pointer movements and swaps step-by-step.
*   **Clarify pointer roles:** Be precise about what `low`, `mid`, and `high` represent and how they partition the array.
*   **Discuss edge cases:** Consider empty arrays, arrays with only one type of number, or arrays already sorted.

## Revision Checklist
- [ ] Understand the problem statement: sort an array of 0s, 1s, and 2s in-place.
- [ ] Recall the Dutch National Flag algorithm (three-way partitioning).
- [ ] Implement the three-pointer approach (`low`, `mid`, `high`).
- [ ] Correctly handle swaps for 0s (swap with `low`, advance `low` and `mid`).
- [ ] Correctly handle 1s (advance `mid`).
- [ ] Correctly handle 2s (swap with `high`, decrement `high`, *do not* advance `mid`).
- [ ] Ensure the loop condition `mid <= high` is correct.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and tracing examples.

## Similar Problems
*   [Partition Array into Two Arrays to Maximize Sum](https://leetcode.com/problems/partition-array-into-two-arrays-to-maximize-sum/) (Uses partitioning concept)
*   [Sort Array](https://leetcode.com/problems/sort-array/) (General sorting, but this is a specialized case)
*   [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) (Uses in-place modification with indices)

## Tags
`Array` `Two Pointers` `Sorting` `In-place`
