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
Given an array of integers, rearrange it to the next lexicographically greater permutation.
Find the first decreasing element from the right, swap it with the smallest element greater than it to its right, and then reverse the suffix.

## Intuition
The goal is to find the "next" permutation in lexicographical order. This means we want to make the smallest possible change to the current permutation to get a larger one.
Imagine the numbers as digits of a large number. To get the next larger number, we typically look from right to left. We find the first digit that can be increased. This digit is the first element from the right that is smaller than its right neighbor. Once we find this "pivot," we need to swap it with the smallest digit to its right that is *larger* than the pivot. This ensures we make the smallest possible increase at the pivot position. After the swap, the suffix to the right of the pivot will be in descending order. To make the overall number as small as possible (thus the *next* permutation), we reverse this suffix to put it in ascending order. If no such pivot is found (the array is already in descending order), it means it's the largest permutation, and the next one is the smallest permutation (ascending order), which is achieved by reversing the entire array.

## Algorithm
1. Find the largest index `i` such that `nums[i] < nums[i+1]`. This is the first element from the right that is smaller than its immediate right neighbor. If no such index exists, the array is in descending order, meaning it's the last permutation.
2. If `i` is found (i.e., `i >= 0`):
   a. Find the largest index `j` such that `nums[j] > nums[i]` and `j > i`. This `nums[j]` is the smallest element to the right of `nums[i]` that is greater than `nums[i]`.
   b. Swap `nums[i]` and `nums[j]`.
3. Reverse the subarray starting from index `i+1` up to the end of the array. This ensures the suffix is in ascending order, creating the lexicographically smallest permutation after the swap.
4. If no such index `i` was found in step 1, it means the array was already in descending order. In this case, reversing the entire array (from index 0) will result in the smallest possible permutation (ascending order).

## Concept to Remember
*   **Lexicographical Order:** Understanding how permutations are ordered alphabetically or numerically.
*   **In-place Modification:** The requirement to modify the array directly without using extra space.
*   **Two Pointers:** Efficiently traversing and manipulating parts of an array.
*   **Greedy Approach:** Making the locally optimal choice (smallest possible increase) to achieve a global optimum (next permutation).

## Common Mistakes
*   Not handling the edge case where the array is already in descending order (e.g., `[3, 2, 1]`).
*   Incorrectly finding the element to swap with `nums[i]` (e.g., picking any element greater than `nums[i]` instead of the smallest one).
*   Forgetting to reverse the suffix after the swap, leading to a permutation that is larger but not necessarily the *next* one.
*   Off-by-one errors when calculating indices `i` and `j`, or when defining the bounds for reversal.

## Complexity Analysis
- Time: O(n) - The algorithm involves three passes over the array: one to find `i`, one to find `j`, and one to reverse the suffix. Each pass takes at most O(n) time.
- Space: O(1) - The algorithm modifies the array in-place and uses only a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length; // Get the length of the input array.
        int i = n - 2; // Start from the second-to-last element to find the first decreasing element from the right.

        // Step 1: Find the largest index i such that nums[i] < nums[i+1].
        // This is the pivot point where we can make a change to get a larger permutation.
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--; // Move left if the current element is not smaller than the next one.
        }

        // If i is non-negative, it means we found such an index.
        if (i >= 0) {
            int j = n - 1; // Start from the last element to find the element to swap with nums[i].

            // Step 2a: Find the largest index j such that nums[j] > nums[i].
            // This is the smallest element to the right of nums[i] that is greater than nums[i].
            while (j >= 0 && nums[i] >= nums[j]) {
                j--; // Move left until we find an element greater than nums[i].
            }
            // Step 2b: Swap nums[i] and nums[j].
            swap(nums, i, j);
        }

        // Step 3: Reverse the subarray starting from index i+1 to the end.
        // This ensures that the suffix is in ascending order, making it the smallest possible permutation
        // after the swap, thus giving us the "next" permutation.
        // If i was -1 (array was in descending order), this reverses the entire array.
        reverse(nums, i + 1);
    }

    // Helper function to reverse a portion of the array from index i to the end.
    public void reverse(int[] arr, int i) {
        int j = arr.length - 1; // Start from the end of the array.
        // Swap elements from both ends moving inwards until they meet.
        while (i < j) {
            swap(arr, i++, j--); // Swap current elements and move pointers.
        }
    }

    // Helper function to swap two elements in an array.
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // Store the element at index i temporarily.
        arr[i] = arr[j]; // Assign the element at index j to index i.
        arr[j] = temp; // Assign the stored element (originally at i) to index j.
    }
}
```

## Interview Tips
*   Clearly explain the logic of finding the pivot (`i`) and the swap element (`j`). Emphasize why `j` must be the smallest element greater than `nums[i]` to its right.
*   Walk through an example like `[1, 2, 3]` -> `[1, 3, 2]` and `[3, 2, 1]` -> `[1, 2, 3]` to demonstrate your understanding of the algorithm and edge cases.
*   Be prepared to discuss the time and space complexity and justify it based on the three passes.
*   If asked to implement without helper functions, be ready to inline the swap and reverse logic.

## Revision Checklist
- [ ] Understand the definition of lexicographical order for permutations.
- [ ] Identify the pivot element `i` from the right.
- [ ] Find the smallest element `j` to the right of `i` that is greater than `nums[i]`.
- [ ] Implement the swap operation correctly.
- [ ] Implement the reversal of the suffix from `i+1` to the end.
- [ ] Handle the edge case where the array is already in descending order.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Permutations (LeetCode 46)
*   Permutations II (LeetCode 47)
*   K-th Smallest in Lexicographical Order (LeetCode 440)

## Tags
`Array` `Two Pointers` `Greedy`
