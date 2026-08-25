# First Missing Positive

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        // in every case answer is less than or equal to n+1.

        // hum shuru ke n numbers agr apni array me jama le

        // then agr sb jm gya to answer is n+1,

        // nhi jama to jo first empty hai, wo hua humara answer
        int n = nums.length;
        int iteration=0;
        for(int i=0;i<n;i++){
            int correctI = nums[i]-1;
            iteration++;
            while(nums[i]>0 && nums[i]<=n && nums[correctI] != nums[i]){
                iteration++;
                correctI = nums[i]-1;
                int temp = nums[i];
                nums[i] = nums[correctI];
                nums[correctI] = temp;
            }
        }
        System.out.println(iteration);
        for(int i=0;i<n;i++) if(nums[i] !=i+1) return i+1;
        return n+1;
    }
}
// 111
// 1000
// 1001
// 1011
// 1110
```

---

---
## Quick Revision
Given an unsorted integer array, find the smallest missing positive integer.
The solution uses in-place modification of the array to place numbers in their correct positions.

## Intuition
The core idea is that if we have an array of length `n`, the first missing positive integer must be between `1` and `n+1` (inclusive). If all numbers from `1` to `n` are present, then `n+1` is the answer. Otherwise, the smallest missing positive is one of the numbers from `1` to `n`. We can use the array itself as a hash map. If we see the number `k`, we try to place it at index `k-1`. After rearranging, we iterate through the array to find the first index `i` where `nums[i]` is not equal to `i+1`. This `i+1` is our missing positive.

## Algorithm
1.  **Handle Edge Cases/Initialization**: Get the length of the array, `n`.
2.  **In-place Rearrangement**: Iterate through the array from index `0` to `n-1`.
    *   For each element `nums[i]`, check if it's a positive number within the range `[1, n]`.
    *   If it is, calculate its "correct" index: `correctI = nums[i] - 1`.
    *   While `nums[i]` is positive, within the range `[1, n]`, and `nums[i]` is not already at its correct position (`nums[correctI] != nums[i]`), swap `nums[i]` with `nums[correctI]`. This process ensures that if a number `k` is present, it will eventually be moved to index `k-1`.
3.  **Find the First Missing Positive**: After the rearrangement, iterate through the array again from index `0` to `n-1`.
    *   If `nums[i]` is not equal to `i+1`, then `i+1` is the first missing positive integer. Return `i+1`.
4.  **All Positives Present**: If the loop completes without finding any mismatch, it means all numbers from `1` to `n` are present in their correct positions. In this case, the first missing positive is `n+1`. Return `n+1`.

## Concept to Remember
*   **In-place Array Manipulation**: Using the array's indices to store information about the presence of numbers.
*   **Cyclic Sort/Placement**: The swapping mechanism is a form of cyclic sort, aiming to place each element `k` at index `k-1`.
*   **Pigeonhole Principle**: If we have `n` slots and `n` items, and all items are within a specific range, we can deduce properties about missing items.

## Common Mistakes
*   **Ignoring Non-Positive Numbers**: Not filtering out numbers less than or equal to 0 or greater than `n` during the swapping phase.
*   **Infinite Loops**: Incorrectly handling the `while` loop condition, leading to infinite swaps if `nums[i] == nums[correctI]` but `nums[i]` is not yet at its final correct index.
*   **Off-by-One Errors**: Miscalculating the `correctI` (e.g., using `nums[i]` instead of `nums[i]-1`) or the final return value.
*   **Not Handling Duplicates Correctly**: The condition `nums[correctI] != nums[i]` is crucial to prevent infinite loops when duplicates are present.

## Complexity Analysis
*   **Time**: O(n) - Although there's a nested `while` loop, each number is swapped at most once into its correct position. The outer loop runs `n` times, and the inner `while` loop's total operations across all outer loop iterations are bounded by `n` swaps. The final scan is O(n).
*   **Space**: O(1) - The algorithm modifies the input array in-place and uses only a few extra variables.

## Commented Code
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        // The smallest missing positive integer will always be between 1 and n+1,
        // where n is the length of the array. This is because if all numbers from 1 to n are present,
        // then n+1 is the smallest missing positive. Otherwise, one of the numbers from 1 to n is missing.

        int n = nums.length; // Get the length of the array.

        // This loop aims to place each positive number 'k' (where 1 <= k <= n)
        // into its correct index, which is 'k-1'.
        for(int i = 0; i < n; i++) {
            // Calculate the target index for the current number nums[i].
            // If nums[i] is 'k', its correct index should be 'k-1'.
            int correctI = nums[i] - 1;

            // We need to perform swaps as long as:
            // 1. The current number nums[i] is positive (we only care about positive integers).
            // 2. The current number nums[i] is within the bounds of the array's indices (1 to n).
            // 3. The current number nums[i] is NOT already at its correct position.
            //    The condition `nums[correctI] != nums[i]` is crucial to prevent infinite loops
            //    if there are duplicate numbers or if the number is already in place.
            while(nums[i] > 0 && nums[i] <= n && nums[correctI] != nums[i]) {
                // Store the current number in a temporary variable before swapping.
                int temp = nums[i];
                // Move the number from the correct index to the current index.
                nums[i] = nums[correctI];
                // Place the original current number into its correct index.
                nums[correctI] = temp;
                // After swapping, we need to re-evaluate the new nums[i] and its correct index.
                // So, we recalculate correctI for the new value at nums[i].
                correctI = nums[i] - 1;
            }
        }

        // After the rearrangement, iterate through the array to find the first index 'i'
        // where the number at that index is NOT 'i+1'.
        for(int i = 0; i < n; i++) {
            // If nums[i] is not equal to i+1, it means i+1 is the smallest missing positive integer.
            if(nums[i] != i + 1) {
                return i + 1; // Return the first missing positive.
            }
        }

        // If the loop completes without finding any mismatch, it means all numbers from 1 to n
        // are present in their correct positions. Therefore, the smallest missing positive is n+1.
        return n + 1;
    }
}
```

## Interview Tips
*   **Explain the In-place Idea**: Clearly articulate why using the array itself as a hash map is efficient and how the `k-1` index mapping works.
*   **Discuss the `while` loop condition**: Emphasize the importance of each part of the `while` loop condition (`nums[i] > 0`, `nums[i] <= n`, `nums[correctI] != nums[i]`) for correctness and avoiding infinite loops.
*   **Trace an Example**: Walk through a small example (e.g., `[3, 4, -1, 1]`) to show how the array transforms step-by-step.
*   **Consider Constraints**: Mention that the solution works because the answer is bounded by `n+1`.

## Revision Checklist
- [ ] Understand the problem statement: find the smallest missing positive integer.
- [ ] Recognize the `1` to `n+1` range for the answer.
- [ ] Implement the in-place rearrangement using swaps.
- [ ] Correctly handle the `while` loop conditions for swapping.
- [ ] Implement the final scan to find the first mismatch.
- [ ] Handle the case where all numbers from `1` to `n` are present.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Missing Number
*   Find All Numbers Disappeared in an Array
*   First Missing Positive (this problem)
*   Set Mismatch

## Tags
`Array` `Hash Table` `Two Pointers` `In-place Operation`
