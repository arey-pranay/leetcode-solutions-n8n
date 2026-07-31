# Next Permutation

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length; 
        int i = n-2;
        while(i>=0 && nums[i] >= nums[i+1]) i--;
        int j=n-1;
        if(i>=0){
          while(j>=0 && nums[i] >= nums[j]) j--;
          swap(nums,i,j);
        }
        reverse(nums, i+1);
    }
    public void reverse(int[] arr, int i){
      int j = arr.length-1;
      while(i<j) swap(arr,i++,j--);
    }
    public void swap(int[] arr, int i, int j){
      int temp = arr[i];
      arr[i]= arr[j];
      arr[j] = temp;
    }
}
```

---

---
## Quick Revision
Given an array of integers, rearrange it to the lexicographically next greater permutation.
This is achieved by finding the first decreasing element from the right, swapping it with the smallest element to its right that is greater than it, and then reversing the suffix.

## Intuition
The core idea is to find the smallest possible change that results in a lexicographically larger permutation. We want to modify the number from the rightmost possible position to achieve this. If we find a digit that is smaller than the digit to its right, we can potentially swap it with a larger digit to its right to create a larger number. To make it the *next* greater permutation, we should swap it with the smallest digit to its right that is still larger than it. After the swap, the suffix to the right of the swapped position needs to be in the smallest possible order (ascending) to ensure we have the lexicographically next permutation. Reversing this suffix achieves this. If the entire array is in descending order, it's already the largest permutation, and the next one is the smallest (ascending order).

## Algorithm
1. Find the largest index `i` such that `nums[i] < nums[i+1]`. This is the "pivot" element. If no such index exists, the array is in descending order, meaning it's the last permutation.
2. If a pivot `i` is found:
    a. Find the largest index `j` such that `nums[j] > nums[i]`. This `nums[j]` is the smallest element to the right of `nums[i]` that is greater than `nums[i]`.
    b. Swap `nums[i]` and `nums[j]`.
    c. Reverse the subarray starting from index `i+1` to the end of the array.
3. If no pivot `i` is found (i.e., the array is in descending order), reverse the entire array to get the smallest permutation.

## Concept to Remember
*   **Lexicographical Order:** Understanding how permutations are ordered alphabetically or numerically.
*   **In-place Modification:** The requirement to modify the array directly without using extra space for a new array.
*   **Two Pointers:** Efficiently traversing and manipulating parts of an array.
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a global optimum.

## Common Mistakes
*   Not handling the edge case where the array is already the largest permutation (descending order).
*   Incorrectly identifying the pivot element `i` (e.g., looking for `nums[i] > nums[i+1]` instead of `nums[i] < nums[i+1]`).
*   Incorrectly finding the element `j` to swap with `i` (e.g., picking the first element greater than `nums[i]` instead of the smallest one).
*   Failing to reverse the suffix after the swap, leading to a permutation that is larger but not necessarily the *next* one.

## Complexity Analysis
- Time: O(n) - The algorithm involves three passes over the array in the worst case: one to find `i`, one to find `j`, and one to reverse the suffix. Each pass takes O(n) time.
- Space: O(1) - The algorithm modifies the array in-place and uses only a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length; // Get the length of the input array.
        int i = n - 2; // Initialize pointer 'i' to the second-to-last element. We start from the right to find the first decreasing element.

        // Step 1: Find the largest index 'i' such that nums[i] < nums[i+1].
        // This loop moves 'i' leftwards as long as the current element is greater than or equal to the next element.
        // This identifies the first element from the right that is smaller than its right neighbor.
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // If 'i' is non-negative, it means we found such an index.
        if (i >= 0) {
            int j = n - 1; // Initialize pointer 'j' to the last element.

            // Step 2a: Find the largest index 'j' such that nums[j] > nums[i].
            // This loop moves 'j' leftwards until it finds an element that is greater than nums[i].
            // Since the subarray from i+1 to n-1 is in descending order, this will be the smallest element greater than nums[i].
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // Step 2b: Swap nums[i] and nums[j].
            swap(nums, i, j);
        }

        // Step 2c (if i >= 0) or Step 3 (if i < 0): Reverse the subarray from index i+1 to the end.
        // If i < 0, this reverses the entire array, handling the case where the input was the largest permutation.
        // If i >= 0, this reverses the suffix to make it the smallest possible permutation after the swap.
        reverse(nums, i + 1);
    }

    // Helper function to reverse a subarray from a given starting index 'i' to the end of the array.
    public void reverse(int[] arr, int i) {
        int j = arr.length - 1; // Initialize pointer 'j' to the last element of the array.
        // Use two pointers, 'i' starting from the given index and 'j' from the end, to swap elements.
        while (i < j) {
            swap(arr, i++, j--); // Swap elements at 'i' and 'j', then move 'i' forward and 'j' backward.
        }
    }

    // Helper function to swap two elements in an array.
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // Store the element at index 'i' in a temporary variable.
        arr[i] = arr[j];   // Assign the element at index 'j' to index 'i'.
        arr[j] = temp;     // Assign the stored element (original arr[i]) to index 'j'.
    }
}
```

## Interview Tips
*   Clearly explain the logic of finding the pivot `i` and the swap element `j`. Emphasize why `j` must be the *smallest* element greater than `nums[i]` to the right.
*   Walk through an example like `[1, 2, 3]` -> `[1, 3, 2]` and `[3, 2, 1]` -> `[1, 2, 3]` to demonstrate the algorithm's steps.
*   Be prepared to discuss the edge case where the input array is already the largest permutation (e.g., `[3, 2, 1]`).
*   Mention the in-place modification requirement and how the algorithm achieves O(1) space complexity.

## Revision Checklist
- [ ] Understand the definition of lexicographically next permutation.
- [ ] Identify the pivot element `i` correctly (first element from right smaller than its right neighbor).
- [ ] Handle the case where no such pivot exists (array is in descending order).
- [ ] Find the smallest element `j` to the right of `i` that is greater than `nums[i]`.
- [ ] Swap `nums[i]` and `nums[j]`.
- [ ] Reverse the subarray from `i+1` to the end.
- [ ] Implement helper functions for `swap` and `reverse` if needed.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Permutations II (LeetCode 47)
*   Permutations (LeetCode 46)
*   K-th Smallest in Lexicographical Order (LeetCode 440)

## Tags
`Array` `Two Pointers` `Greedy`
