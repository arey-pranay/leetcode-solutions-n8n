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
The core idea is that rotating an array to the right by `k` steps is equivalent to performing three reversals. Imagine the array split into two parts: the last `k` elements and the first `n-k` elements. If we reverse the entire array, these two parts swap positions. Then, reversing each of these parts individually brings them back to their correct relative order within their new positions.

For example, if `nums = [1, 2, 3, 4, 5, 6, 7]` and `k = 3`:
1. Reverse the whole array: `[7, 6, 5, 4, 3, 2, 1]`
2. Reverse the first `k` elements (`[7, 6, 5]`): `[5, 6, 7, 4, 3, 2, 1]`
3. Reverse the remaining `n-k` elements (`[4, 3, 2, 1]`): `[5, 6, 7, 1, 2, 3, 4]` - This is the rotated array.

## Algorithm
1. Handle edge cases: If the array is null or has only one element, or if `k` is 0, no rotation is needed.
2. Normalize `k`: Since rotating by `n` (the length of the array) brings the array back to its original state, we only care about `k % n`. This ensures `k` is within the bounds of the array length.
3. Reverse the entire array: Swap elements from the beginning to the end.
4. Reverse the first `k` elements: Swap elements from index 0 up to `k-1`.
5. Reverse the remaining `n-k` elements: Swap elements from index `k` up to `n-1`.

## Concept to Remember
*   **In-place modification:** The problem often requires modifying the array directly without using extra space for a new array.
*   **Modular arithmetic:** Used to handle `k` values larger than the array length.
*   **Two-pointer technique:** Essential for efficient in-place reversal of array segments.

## Common Mistakes
*   Not handling `k` values greater than the array length (e.g., `k = 10` for an array of length `7`).
*   Incorrectly calculating the indices for the second and third reversals, especially off-by-one errors.
*   Forgetting to reverse the entire array first, which is crucial for the three-reversal strategy.
*   Implementing the `reverse` helper function incorrectly, leading to partial or incorrect reversals.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array a constant number of times (three full reversals, each taking O(n) time).
- Space: O(1) - reason: The rotation is performed in-place, using only a few extra variables for swapping.

## Commented Code
```java
class Solution {
    // The main method to rotate the array.
    public void rotate(int[] nums, int k) {
        // Get the length of the array.
        int n = nums.length;
        // Normalize k to be within the bounds of the array length.
        // If k is larger than n, rotating by k is the same as rotating by k % n.
        k %= n;
        // Reverse the entire array. This brings the last k elements to the front.
        reverse(nums, 0, n - 1);
        // Reverse the first k elements. These are now the elements that should be at the end.
        reverse(nums, 0, k - 1);
        // Reverse the remaining n-k elements. These are now the elements that should be at the beginning.
        reverse(nums, k, n - 1);
    }

    // Helper method to reverse a portion of the array in-place.
    // Takes the array, a starting index i, and an ending index j.
    public void reverse(int[] nums, int i, int j) {
        // Continue swapping as long as the start index is less than the end index.
        while (i < j) {
            // Store the element at the start index in a temporary variable.
            int temp = nums[i];
            // Replace the element at the start index with the element at the end index.
            nums[i++] = nums[j]; // Increment i after assignment.
            // Replace the element at the end index with the stored temporary element.
            nums[j--] = temp; // Decrement j after assignment.
        }
    }
}
```

## Interview Tips
*   Clearly explain the three-reversal intuition. Walk through an example on the whiteboard.
*   Emphasize the `k %= n` step and why it's important for correctness and efficiency.
*   Discuss the space complexity advantage of this in-place solution compared to creating a new array.
*   Be prepared to implement the `reverse` helper function correctly and efficiently.

## Revision Checklist
- [ ] Understand the problem statement: rotate array right by k steps.
- [ ] Recognize the three-reversal pattern.
- [ ] Implement `k %= n` correctly.
- [ ] Implement the `reverse` helper function with two pointers.
- [ ] Test with edge cases: empty array, single element array, `k=0`, `k=n`, `k > n`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse String
*   Rotate Image
*   K-th Largest Element in an Array (can sometimes use similar partitioning ideas)

## Tags
`Array` `Two Pointers` `Math`
