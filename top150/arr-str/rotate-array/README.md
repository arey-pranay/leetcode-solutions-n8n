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
        k%=n;
        reverse(nums,0,n-k-1);
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }
    public void reverse(int[] arr, int s, int e){
        while(s<e) swap(arr,s++,e--);
    }
    public void swap(int[] arr, int s, int e){
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp; 
    }
}
```

---

---
## Quick Revision
Given an array, rotate the elements to the right by k steps.
This can be solved efficiently by reversing parts of the array.

## Intuition
The core idea is that rotating an array `k` steps to the right is equivalent to performing three reversals. Imagine the array split into two parts: the last `k` elements and the first `n-k` elements. If we reverse these two parts individually, and then reverse the entire array, we achieve the desired rotation.

For example, if `nums = [1, 2, 3, 4, 5, 6, 7]` and `k = 3`:
1. Split: `[1, 2, 3, 4]` and `[5, 6, 7]`
2. Reverse first part: `[4, 3, 2, 1]` and `[5, 6, 7]`
3. Reverse second part: `[4, 3, 2, 1]` and `[7, 6, 5]`
4. Reverse the whole array: `[5, 6, 7, 1, 2, 3, 4]` which is the rotated array.

## Algorithm
1. Handle edge cases: If the array is null or has only one element, or if `k` is 0, no rotation is needed.
2. Normalize `k`: Calculate `k = k % n`, where `n` is the length of the array. This handles cases where `k` is greater than `n`.
3. Reverse the first `n-k` elements: Call a helper `reverse` function on the subarray from index `0` to `n-k-1`.
4. Reverse the last `k` elements: Call the `reverse` function on the subarray from index `n-k` to `n-1`.
5. Reverse the entire array: Call the `reverse` function on the entire array from index `0` to `n-1`.
6. The `reverse` helper function takes the array, a start index `s`, and an end index `e`. It swaps elements from the ends inwards until `s` is no longer less than `e`.
7. The `swap` helper function simply exchanges two elements in the array.

## Concept to Remember
*   In-place array manipulation: Modifying the array directly without using extra space.
*   Reversal technique for array rotation: A clever way to achieve rotation using multiple reversals.
*   Modulo operator for cyclic operations: Essential for handling rotations that exceed the array's length.

## Common Mistakes
*   Not handling `k` greater than `n`: Forgetting to use the modulo operator (`k % n`) can lead to incorrect results or infinite loops.
*   Off-by-one errors in index calculations: Incorrectly defining the start or end indices for the reversal operations.
*   Not considering edge cases: Failing to handle empty arrays, single-element arrays, or `k=0`.
*   Using extra space unnecessarily: Implementing a solution that creates a new array instead of modifying the original in-place.

## Complexity Analysis
*   Time: O(n) - The algorithm performs three passes of reversal, each taking O(n) time. The `reverse` function iterates through at most `n/2` elements, and `swap` is O(1).
*   Space: O(1) - The rotation is performed in-place, meaning no additional data structures that grow with the input size are used. Only a few variables for indices and temporary storage for swapping are needed.

## Commented Code
```java
class Solution {
    // Main method to rotate the array nums by k steps to the right.
    public void rotate(int[] nums, int k) {
        // Get the total number of elements in the array.
        int n = nums.length;
        // Normalize k: if k is larger than n, rotating k times is the same as rotating k % n times.
        // This also handles cases where k is a multiple of n (k % n == 0), resulting in no effective rotation.
        k %= n;
        // If k is 0 after normalization, no rotation is needed, so we can return early.
        if (k == 0) {
            return;
        }
        // Step 1: Reverse the first n-k elements.
        // This brings the elements that should end up at the beginning of the array to the end of the first part.
        // Example: [1,2,3,4,5,6,7], k=3, n=7. n-k-1 = 7-3-1 = 3. Reverse [1,2,3,4] -> [4,3,2,1,5,6,7]
        reverse(nums, 0, n - k - 1);
        // Step 2: Reverse the last k elements.
        // This brings the elements that should end up at the end of the array to the beginning of the second part.
        // Example: [4,3,2,1,5,6,7]. n-k = 7-3 = 4. Reverse [5,6,7] -> [4,3,2,1,7,6,5]
        reverse(nums, n - k, n - 1);
        // Step 3: Reverse the entire array.
        // This combines the two reversed parts into the final rotated array.
        // Example: [4,3,2,1,7,6,5]. Reverse the whole array -> [5,6,7,1,2,3,4]
        reverse(nums, 0, n - 1);
    }

    // Helper method to reverse a portion of an array in-place.
    // Takes the array, a start index 's', and an end index 'e'.
    public void reverse(int[] arr, int s, int e) {
        // Continue swapping elements from the start and end pointers as long as the start pointer is before the end pointer.
        while (s < e) {
            // Swap the elements at the current start and end indices.
            swap(arr, s++, e--); // Increment s and decrement e after the swap.
        }
    }

    // Helper method to swap two elements in an array.
    // Takes the array and the indices of the two elements to swap.
    public void swap(int[] arr, int s, int e) {
        // Store the element at the start index in a temporary variable.
        int temp = arr[s];
        // Assign the element at the end index to the start index.
        arr[s] = arr[e];
        // Assign the temporary stored element (original start element) to the end index.
        arr[e] = temp;
    }
}
```

## Interview Tips
*   Explain the three-reversal intuition clearly. Walk through an example.
*   Emphasize the in-place nature of the solution and its O(1) space complexity.
*   Be prepared to discuss alternative approaches (like using an extra array or cyclic replacements) and their trade-offs.
*   Pay close attention to index calculations, especially `n-k-1` and `n-k`, and explain them.

## Revision Checklist
- [ ] Understand the problem statement: rotate array elements to the right by `k` steps.
- [ ] Normalize `k` using the modulo operator: `k = k % n`.
- [ ] Implement the three-reversal strategy: reverse first `n-k`, reverse last `k`, reverse all.
- [ ] Implement a helper `reverse` function for subarrays.
- [ ] Implement a helper `swap` function.
- [ ] Consider edge cases: empty array, single element array, `k=0`.
- [ ] Analyze time and space complexity: O(n) time, O(1) space.

## Similar Problems
*   Reverse String
*   Move Zeroes
*   K-th Smallest Element in a Sorted Matrix (can sometimes use similar partitioning ideas)

## Tags
`Array` `Two Pointers` `In-place`
