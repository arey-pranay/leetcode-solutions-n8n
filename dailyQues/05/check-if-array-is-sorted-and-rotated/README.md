# Check If Array Is Sorted And Rotated

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean check(int[] nums) {
        boolean broke = false;
        for(int i=0;i<nums.length;i++) if(nums[i] > nums[(i+1) % nums.length]) if(broke) return false; else broke = true;
        return true;
    }
}
```

---

---
## Quick Revision
Given an array, determine if it's a sorted array that has been rotated zero or more times.
We can solve this by counting the number of "breaks" in the non-decreasing order, considering the wrap-around.

## Intuition
A sorted and rotated array will have at most one "break" in its non-decreasing order. A "break" occurs when `nums[i] > nums[i+1]`. If the array is sorted and not rotated, there are zero breaks. If it's sorted and rotated, there will be exactly one break (where the largest element is followed by the smallest element). If there are two or more breaks, it cannot be a sorted and rotated array. The modulo operator `% nums.length` is crucial to handle the wrap-around comparison between the last and first elements.

## Algorithm
1. Initialize a counter `breaks` to 0.
2. Iterate through the array from the first element up to the second-to-last element (index `i` from 0 to `nums.length - 2`).
3. In each iteration, compare `nums[i]` with `nums[i+1]`.
4. If `nums[i] > nums[i+1]`, increment the `breaks` counter.
5. After the loop, compare the last element `nums[nums.length - 1]` with the first element `nums[0]`.
6. If `nums[nums.length - 1] > nums[0]`, increment the `breaks` counter.
7. If the final `breaks` count is less than or equal to 1, return `true`. Otherwise, return `false`.

*Self-correction/Refinement based on provided code:* The provided code uses a boolean flag `broke` instead of a counter. This is a more concise way to achieve the same result. The logic is: if we encounter a break and `broke` is already true, it means we've found a second break, so return `false`. Otherwise, set `broke` to true. The modulo operator handles the wrap-around comparison implicitly within the loop.

## Algorithm (Refined based on provided code)
1. Initialize a boolean variable `broke` to `false`. This flag will track if we've encountered a "break" in the non-decreasing order.
2. Iterate through the array using an index `i` from 0 to `nums.length - 1`.
3. In each iteration, compare `nums[i]` with the next element, considering wrap-around: `nums[(i + 1) % nums.length]`.
4. If `nums[i] > nums[(i + 1) % nums.length]` (meaning a break in non-decreasing order is found):
    a. Check if `broke` is already `true`. If it is, this is the second break, so the array is not sorted and rotated. Return `false`.
    b. If `broke` is `false`, set `broke` to `true` to mark that we've found the first break.
5. If the loop completes without returning `false`, it means there was at most one break. Return `true`.

## Concept to Remember
*   **Array Traversal:** Iterating through array elements to perform comparisons.
*   **Modulo Operator for Wrap-around:** Using `%` to handle circular comparisons in an array.
*   **Conditionals and Flags:** Using boolean flags to track states or conditions during iteration.
*   **Non-decreasing Order:** Understanding the property of sorted arrays where elements are equal or increasing.

## Common Mistakes
*   **Forgetting Wrap-around:** Not comparing the last element with the first element, leading to incorrect results for rotated arrays.
*   **Counting Breaks Incorrectly:** Using a counter that doesn't handle the "at most one break" rule properly, or misinterpreting the condition for a break.
*   **Off-by-One Errors:** Incorrect loop bounds or index calculations.
*   **Handling Edge Cases:** Not considering arrays with duplicate elements or arrays of size 1.
*   **Overcomplicating the Logic:** Trying to find the pivot point explicitly when a simple count of breaks is sufficient.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array once.
- Space: O(1) - reason: We only use a constant amount of extra space for the `broke` flag.

## Commented Code
```java
class Solution {
    public boolean check(int[] nums) {
        // Initialize a boolean flag to track if we have encountered a "break" in the non-decreasing order.
        boolean broke = false;
        // Iterate through the array. The loop goes up to nums.length - 1 to compare each element with the next.
        for(int i=0; i < nums.length; i++) {
            // Compare the current element with the next element, using modulo for wrap-around.
            // (i + 1) % nums.length ensures that when i is the last index, we compare with the first element.
            if(nums[i] > nums[(i + 1) % nums.length]) {
                // If a break is found (current element is greater than the next):
                // Check if we have already encountered a break before.
                if(broke) {
                    // If 'broke' is already true, it means this is the second break, so the array is not sorted and rotated.
                    return false;
                } else {
                    // If this is the first break, set 'broke' to true.
                    broke = true;
                }
            }
        }
        // If the loop completes, it means we found at most one break (or zero breaks).
        // An array with 0 or 1 break is considered sorted and rotated.
        return true;
    }
}
```

## Interview Tips
*   **Explain the "at most one break" rule:** Clearly articulate why a sorted and rotated array can have at most one point where `nums[i] > nums[i+1]` (considering wrap-around).
*   **Discuss the modulo operator's role:** Emphasize how `% nums.length` elegantly handles the wrap-around comparison between the last and first elements.
*   **Consider edge cases:** Mention how your solution handles arrays of size 1, arrays with all identical elements, and arrays that are already sorted (zero breaks).
*   **Ask clarifying questions:** If unsure about the definition of "sorted and rotated" (e.g., strictly increasing vs. non-decreasing), ask the interviewer.

## Revision Checklist
- [ ] Understand the problem statement: check if an array is sorted and rotated.
- [ ] Identify the key property: at most one "break" in non-decreasing order.
- [ ] Implement wrap-around comparison using the modulo operator.
- [ ] Handle the case of zero breaks (already sorted).
- [ ] Handle the case of one break (rotated).
- [ ] Ensure the solution correctly identifies arrays with more than one break.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Search in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array
*   Rotate Array
*   Sort Array

## Tags
`Array` `Two Pointers`
