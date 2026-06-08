# Partition Array According To Given Pivot

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Simulation`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int i=0;
        for(int num : nums)if(num<pivot)ans[i++] = num;
        for(int num : nums)if(num==pivot)ans[i++] = num;
        for(int num : nums)if(num>pivot)ans[i++] = num;
        return ans;
    }
}
```

---

---
## Quick Revision
The problem asks to rearrange an array such that all elements less than a pivot come first, followed by elements equal to the pivot, and then elements greater than the pivot. This is achieved by iterating through the array multiple times and placing elements into a new array based on their comparison with the pivot.

## Intuition
The core idea is to maintain the relative order of elements within each partition (less than, equal to, greater than). A simple way to achieve this is to create a new array and populate it in three distinct passes: first, copy all elements smaller than the pivot; second, copy all elements equal to the pivot; and finally, copy all elements larger than the pivot. This ensures that the original relative order within each group is preserved.

## Algorithm
1. Initialize a new array `ans` of the same size as the input array `nums`.
2. Initialize an index variable `i` to 0, which will track the current position in the `ans` array.
3. Iterate through the `nums` array. For each element `num`:
    a. If `num` is less than `pivot`, place `num` at `ans[i]` and increment `i`.
4. Reset the iteration (or conceptually start a new pass). Iterate through the `nums` array again. For each element `num`:
    a. If `num` is equal to `pivot`, place `num` at `ans[i]` and increment `i`.
5. Reset the iteration (or conceptually start a new pass). Iterate through the `nums` array a third time. For each element `num`:
    a. If `num` is greater than `pivot`, place `num` at `ans[i]` and increment `i`.
6. Return the `ans` array.

## Concept to Remember
*   **In-place vs. Out-of-place algorithms:** This solution uses an out-of-place approach by creating a new array.
*   **Stable Partitioning:** The requirement to maintain relative order within partitions is a key aspect of stable sorting/partitioning algorithms.
*   **Multiple Passes:** Sometimes, solving a problem efficiently involves iterating over the input data multiple times.

## Common Mistakes
*   **Modifying the array in-place incorrectly:** Attempting to partition in-place without careful handling of swaps can disrupt the relative order of elements.
*   **Not preserving relative order:** A naive partitioning might place elements correctly relative to the pivot but scramble their original order within their respective groups.
*   **Off-by-one errors with indices:** Incorrectly managing the insertion index `i` can lead to elements being placed in the wrong positions or overwriting existing ones.
*   **Handling edge cases:** Not considering empty arrays or arrays where all elements are the same as the pivot.

## Complexity Analysis
*   Time: O(N) - reason: The algorithm iterates through the input array `nums` three times. Each iteration takes O(N) time, where N is the length of `nums`. Therefore, the total time complexity is O(N) + O(N) + O(N) = O(N).
*   Space: O(N) - reason: A new array `ans` of size N is created to store the partitioned elements. This requires O(N) additional space.

## Commented Code
```java
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        // Get the length of the input array.
        int n = nums.length;
        // Create a new array 'ans' of the same size to store the partitioned result.
        int[] ans = new int[n];
        // Initialize an index 'i' to keep track of the current position in the 'ans' array.
        int i = 0;

        // First pass: Iterate through 'nums' and add all elements less than 'pivot' to 'ans'.
        for (int num : nums) {
            // If the current number is less than the pivot...
            if (num < pivot) {
                // ...place it at the current index 'i' in the 'ans' array.
                ans[i++] = num;
            }
        }

        // Second pass: Iterate through 'nums' again and add all elements equal to 'pivot' to 'ans'.
        for (int num : nums) {
            // If the current number is equal to the pivot...
            if (num == pivot) {
                // ...place it at the current index 'i' in the 'ans' array.
                ans[i++] = num;
            }
        }

        // Third pass: Iterate through 'nums' a final time and add all elements greater than 'pivot' to 'ans'.
        for (int num : nums) {
            // If the current number is greater than the pivot...
            if (num > pivot) {
                // ...place it at the current index 'i' in the 'ans' array.
                ans[i++] = num;
            }
        }

        // Return the newly created and partitioned array.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify "relative order":** Ensure you understand that the problem requires maintaining the original relative order of elements within each partition (less than, equal to, greater than).
*   **Discuss space-time trade-offs:** Mention that while this solution is O(N) time and O(N) space, an in-place solution might be possible but more complex to implement while preserving relative order.
*   **Walk through an example:** Be prepared to trace your algorithm with a small example array and pivot to demonstrate its correctness.
*   **Consider alternative approaches:** Briefly mention if you can think of other ways to solve it, even if they are less optimal (e.g., using three separate lists and then concatenating them).

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify the requirement of maintaining relative order.
- [ ] Implement the three-pass approach correctly.
- [ ] Verify index management (`i`).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm.

## Similar Problems
*   Sort Colors
*   Partition Labels
*   Three-Way Partition

## Tags
`Array` `Two Pointers` `Partition`
