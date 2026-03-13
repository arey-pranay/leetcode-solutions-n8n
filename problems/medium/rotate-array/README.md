# Rotate Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Two Pointers`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

---

---
## Quick Revision
Given an array, rotate the elements to the right by k steps.
This can be solved efficiently by reversing parts of the array.

## Intuition
The core idea is to realize that rotating an array `k` steps to the right is equivalent to performing three reversals.
Imagine the array split into two parts: the last `k` elements and the first `n-k` elements.
If we reverse the entire array, these two parts swap positions.
Then, reversing the first `k` elements puts the original last `k` elements in their correct rotated positions.
Finally, reversing the remaining `n-k` elements puts the original first `n-k` elements in their correct rotated positions.

## Algorithm
1. Calculate the effective rotation amount: `k = k % n`, where `n` is the length of the array. This handles cases where `k` is larger than `n`.
2. Reverse the entire array from index `0` to `n-1`.
3. Reverse the first `k` elements of the array, from index `0` to `k-1`.
4. Reverse the remaining `n-k` elements of the array, from index `k` to `n-1`.

## Concept to Remember
*   In-place array manipulation: Modifying the array directly without using extra space proportional to the input size.
*   Reversal as a transformation: Understanding how reversing segments of an array can achieve complex rearrangements.
*   Modular arithmetic: Using the modulo operator to handle cyclic or repeating patterns.

## Common Mistakes
*   Not handling `k` larger than `n`: Forgetting to use `k % n` can lead to incorrect rotations or out-of-bounds errors.
*   Incorrect reversal indices: Off-by-one errors when defining the `start` and `end` indices for the `reverse` helper function.
*   Misunderstanding the three-reversal logic: Not visualizing how each reversal contributes to the final rotated state.
*   Using extra space: Resorting to creating a new array or using auxiliary data structures when an in-place solution is expected.

## Complexity Analysis
- Time: O(n) - reason: We perform three passes of reversal, each taking O(n) time. The total time is proportional to the number of elements in the array.
- Space: O(1) - reason: The algorithm modifies the array in-place and only uses a few constant extra variables (like `temp`, `start`, `end`, `n`, `k`).

## Commented Code
```java
class Solution {
    public void rotate(int[] nums, int k) {
        // Get the total number of elements in the array.
        int n = nums.length;
        // Calculate the effective rotation amount by taking modulo n.
        // This handles cases where k is greater than n or negative.
        k = k % n;

        // Reverse the entire array. This brings the last k elements to the front, but in reverse order.
        reverse(nums, 0, n - 1);
        // Reverse the first k elements. This puts the original last k elements in their correct rotated positions.
        reverse(nums, 0, k - 1);
        // Reverse the remaining n-k elements. This puts the original first n-k elements in their correct rotated positions.
        reverse(nums, k, n - 1);
    }

    // Helper function to reverse a portion of the array in-place.
    private void reverse(int[] nums, int start, int end) {
        // Continue swapping elements as long as the start index is less than the end index.
        while (start < end) {
            // Store the element at the start index in a temporary variable.
            int temp = nums[start];
            // Replace the element at the start index with the element at the end index.
            nums[start] = nums[end];
            // Replace the element at the end index with the value stored in the temporary variable.
            nums[end] = temp;
            // Move the start index one step forward.
            start++;
            // Move the end index one step backward.
            end--;
        }
    }
}
```

## Interview Tips
*   Explain the three-reversal logic clearly. Draw it out on a whiteboard if possible.
*   Emphasize the in-place nature of the solution and its O(1) space complexity.
*   Be prepared to discuss alternative solutions (e.g., using an extra array, cyclic replacements) and their trade-offs.
*   Walk through an example with `k` larger than `n` to show you understand the modulo operation.

## Revision Checklist
- [ ] Understand the problem statement: rotate array elements to the right by `k` steps.
- [ ] Recognize the three-reversal approach as an efficient in-place solution.
- [ ] Implement the `reverse` helper function correctly with proper `start` and `end` indices.
- [ ] Handle `k` values greater than the array length using the modulo operator.
- [ ] Analyze time and space complexity.
- [ ] Be able to explain the intuition behind the reversals.

## Similar Problems
*   Reverse String
*   Reverse Vowels of a String
*   Two Sum (for understanding hash maps if discussing alternative solutions)

## Tags
`Array` `Two Pointers` `In-place`
