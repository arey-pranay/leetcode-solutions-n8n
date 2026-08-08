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
        k %= n;
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);     
    }
    public void reverse(int[]nums, int i, int j){
       while(i<j){
           int temp = nums[i];
           nums[i++] = nums[j];
           nums[j--] = temp;
        } 
    }
}


```

---

---
## Quick Revision
Given an array, rotate the array to the right by k steps.
This problem can be solved efficiently using the reverse technique.

## Intuition
The core idea is to realize that rotating an array to the right by `k` steps is equivalent to performing three reversals.
Imagine the array split into two parts: the last `k` elements and the first `n-k` elements.
If we reverse the entire array, these two parts swap positions.
Then, reversing the first `k` elements puts them in their correct rotated order.
Finally, reversing the remaining `n-k` elements puts them in their correct rotated order as well.

## Algorithm
1. Calculate the effective rotation steps: `k = k % n`, where `n` is the length of the array. This handles cases where `k` is larger than `n`.
2. Reverse the entire array from index `0` to `n-1`.
3. Reverse the first `k` elements of the array, from index `0` to `k-1`.
4. Reverse the remaining `n-k` elements of the array, from index `k` to `n-1`.

## Concept to Remember
*   In-place array manipulation: Modifying the array directly without using extra space.
*   Modular arithmetic: Using the modulo operator to handle cyclic behavior or large inputs.
*   Reversal as a transformation: Understanding how reversals can achieve specific array rearrangements.

## Common Mistakes
*   Not handling `k` larger than `n`: Forgetting to use `k % n` can lead to incorrect rotations or out-of-bounds errors.
*   Off-by-one errors in reversal indices: Incorrectly defining the start or end indices for the `reverse` function.
*   Not considering edge cases: Forgetting to test with empty arrays, arrays with one element, or `k=0`.
*   Using extra space unnecessarily: Opting for a solution that creates a new array instead of modifying in-place.

## Complexity Analysis
- Time: O(n) - The algorithm involves three passes of reversing parts of the array, each taking O(n) time.
- Space: O(1) - The rotation is performed in-place, using only a constant amount of extra space for temporary variables during swaps.

## Commented Code
```java
class Solution {
    // The main function to rotate the array.
    public void rotate(int[] nums, int k) {
        // Get the length of the array.
        int n = nums.length;
        // Calculate the effective number of rotations needed.
        // If k is larger than n, rotating k times is the same as rotating k % n times.
        k %= n;
        // Reverse the entire array. This brings the last k elements to the front.
        reverse(nums, 0, n - 1);
        // Reverse the first k elements. These are now the elements that should be at the end.
        reverse(nums, 0, k - 1);
        // Reverse the remaining n-k elements. These are now the elements that should be at the beginning.
        reverse(nums, k, n - 1);
    }

    // Helper function to reverse a portion of the array in-place.
    public void reverse(int[] nums, int i, int j) {
        // Continue swapping elements as long as the start index is less than the end index.
        while (i < j) {
            // Store the element at the start index in a temporary variable.
            int temp = nums[i];
            // Replace the element at the start index with the element at the end index.
            nums[i++] = nums[j]; // Increment i after assignment.
            // Replace the element at the end index with the temporary value.
            nums[j--] = temp; // Decrement j after assignment.
        }
    }
}
```

## Interview Tips
*   Clearly explain the three-reversal intuition. Walk through an example on the whiteboard.
*   Emphasize the in-place nature of the solution and its O(1) space complexity.
*   Be prepared to discuss alternative approaches (like using an extra array or cyclic replacements) and their trade-offs.
*   Pay close attention to the `k % n` step and the boundary conditions for the `reverse` function.

## Revision Checklist
- [ ] Understand the problem statement: rotate array right by k steps.
- [ ] Implement `k %= n` for effective rotations.
- [ ] Implement the `reverse` helper function correctly.
- [ ] Apply the three `reverse` calls in the correct order.
- [ ] Test with edge cases: empty array, single element array, `k=0`, `k=n`, `k > n`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse String
*   Move Zeroes
*   K-th Largest Element in an Array (can be solved with quickselect, related to partitioning)

## Tags
`Array` `Two Pointers` `In-place`
