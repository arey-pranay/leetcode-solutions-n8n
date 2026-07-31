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
Find the first decreasing element from the right, swap it with the smallest element to its right that is larger than it, and then reverse the suffix.

## Intuition
The core idea is to find the smallest possible change that results in a lexicographically larger permutation. We want to increase the number represented by the array as little as possible. This means we should try to modify the rightmost part of the array first. If we find a "pivot" element (the first element from the right that is smaller than its right neighbor), we can swap it with the smallest element to its right that is still larger than it. This ensures we get the *next* greater permutation. After the swap, the suffix to the right of the pivot needs to be in the smallest possible order (ascending) to guarantee it's the lexicographically next permutation. Reversing this suffix achieves this. If no such pivot is found, the array is already in descending order, meaning it's the largest permutation, so we reverse the entire array to get the smallest permutation.

## Algorithm
1. Find the largest index `i` such that `nums[i] < nums[i+1]`. This `nums[i]` is the "pivot". If no such index exists, the array is in descending order, and we reverse the entire array to get the smallest permutation.
2. If such an index `i` is found, find the largest index `j` such that `nums[j] > nums[i]`. This `nums[j]` is the smallest element to the right of `nums[i]` that is greater than `nums[i]`.
3. Swap `nums[i]` and `nums[j]`.
4. Reverse the subarray starting from index `i+1` to the end of the array.

## Concept to Remember
*   Lexicographical Order: Understanding how to compare sequences of elements to determine which one comes "after" another.
*   In-place Modification: The requirement to modify the array directly without using extra space for a new array.
*   Two Pointers Technique: Used effectively for swapping and reversing subarrays.

## Common Mistakes
*   Not handling the edge case where the array is already in descending order (e.g., `[3, 2, 1]`). In this case, the next permutation is the smallest one (`[1, 2, 3]`).
*   Incorrectly finding the element to swap with the pivot. It must be the *smallest* element to the right that is *greater* than the pivot.
*   Failing to reverse the suffix after the swap. This is crucial for ensuring the resulting permutation is the *next* lexicographically greater one.
*   Off-by-one errors when calculating indices for the pivot, the swap element, or the reversal start point.

## Complexity Analysis
- Time: O(n) - The algorithm involves at most three passes through the array: one to find the pivot, one to find the swap element, and one to reverse the suffix. Each pass takes O(n) time.
- Space: O(1) - The algorithm modifies the array in-place and uses only a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length; // Get the length of the input array.
        int i = n - 2; // Start from the second-to-last element to find the first decreasing element from the right.

        // Step 1: Find the largest index i such that nums[i] < nums[i+1].
        // This loop finds the first element from the right that is smaller than its right neighbor.
        // This element is our "pivot".
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--; // Move left if the current element is greater than or equal to the next.
        }

        // If i is non-negative, it means we found a pivot.
        if (i >= 0) {
            int j = n - 1; // Start from the last element to find the element to swap with the pivot.

            // Step 2: Find the largest index j such that nums[j] > nums[i].
            // This loop finds the smallest element to the right of nums[i] that is greater than nums[i].
            while (j >= 0 && nums[i] >= nums[j]) {
                j--; // Move left if the current element is less than or equal to the pivot.
            }
            // Step 3: Swap nums[i] and nums[j].
            swap(nums, i, j); // Perform the swap.
        }

        // Step 4: Reverse the subarray starting from index i+1 to the end.
        // If no pivot was found (i.e., i < 0), this will reverse the entire array,
        // which is the correct behavior for the smallest permutation.
        reverse(nums, i + 1);
    }

    // Helper function to reverse a subarray from a given start index to the end of the array.
    public void reverse(int[] arr, int start) {
        int end = arr.length - 1; // The end index of the array.
        // Use two pointers to swap elements from the start and end, moving inwards.
        while (start < end) {
            swap(arr, start++, end--); // Swap elements and move pointers.
        }
    }

    // Helper function to swap two elements in an array.
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // Store the element at index i temporarily.
        arr[i] = arr[j]; // Assign the element at index j to index i.
        arr[j] = temp; // Assign the temporary element (original arr[i]) to index j.
    }
}
```

## Interview Tips
*   Clearly explain the logic of finding the pivot and the element to swap. Emphasize why this specific swap leads to the *next* permutation.
*   Walk through an example like `[1, 2, 3]` -> `[1, 3, 2]` and `[3, 2, 1]` -> `[1, 2, 3]` to demonstrate your understanding of edge cases.
*   Be prepared to explain the time and space complexity and justify it based on your algorithm.
*   Mention the in-place requirement and how your solution adheres to it.

## Revision Checklist
- [ ] Understand the definition of lexicographically next permutation.
- [ ] Identify the pivot element correctly (first element from right smaller than its right neighbor).
- [ ] Handle the case where the array is already in descending order.
- [ ] Find the smallest element to the right of the pivot that is greater than the pivot.
- [ ] Implement the swap operation correctly.
- [ ] Reverse the suffix after the swap.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Permutations II
*   Permutations

## Tags
`Array` `Two Pointers` `Greedy`
