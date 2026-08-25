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
This problem asks for the smallest positive integer missing from an unsorted array.
The solution uses in-place swapping to place numbers in their correct positions.

## Intuition
The core idea is that if we have an array of length `n`, the first missing positive integer must be between `1` and `n+1` (inclusive). If all numbers from `1` to `n` are present, then `n+1` is the missing positive. Otherwise, the first positive integer that is *not* in its correct place (i.e., `nums[i]` is not `i+1`) is our answer. We can use the array itself as a hash map to track the presence of positive integers within the range `[1, n]`.

## Algorithm
1. Iterate through the array. For each element `nums[i]`:
2. Check if `nums[i]` is a positive integer within the range `[1, n]`.
3. If it is, and if `nums[i]` is not already at its correct index (i.e., `nums[nums[i]-1]` is not equal to `nums[i]`), swap `nums[i]` with the element at `nums[i]-1`.
4. Repeat step 3 for the current `nums[i]` until it's either out of range, negative, zero, or in its correct position.
5. After rearranging the array, iterate through it again. The first index `i` where `nums[i]` is not equal to `i+1` indicates that `i+1` is the first missing positive.
6. If the loop completes without finding such an index, it means all numbers from `1` to `n` are present, so the first missing positive is `n+1`.

## Concept to Remember
*   **In-place modification:** Using the array itself to store information without requiring extra space.
*   **Cyclic Sort/Placement:** Rearranging elements such that `nums[i]` ideally holds the value `i+1`.
*   **Pigeonhole Principle:** If you have `n` slots and `n` items, and each item should go into a specific slot, you can detect missing items by checking which slots are empty or contain the wrong item.

## Common Mistakes
*   Not handling edge cases like empty arrays, arrays with only negative numbers, or arrays with duplicates correctly.
*   Infinite loops during swapping if the condition `nums[correctI] != nums[i]` is not checked, leading to swapping the same elements repeatedly.
*   Incorrectly calculating the `correctI` index (e.g., off-by-one errors).
*   Forgetting to check if `nums[i]` is within the valid range `[1, n]` before attempting to swap.

## Complexity Analysis
- Time: O(n) - Each number is swapped at most once to its correct position. The outer loop runs `n` times, and the inner `while` loop, in total across all outer loop iterations, performs at most `n` swaps. The final scan is O(n).
- Space: O(1) - The algorithm modifies the input array in-place and does not use any auxiliary data structures that grow with the input size.

## Commented Code
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        // The first missing positive integer will always be between 1 and n+1,
        // where n is the length of the array.
        // This is because if all numbers from 1 to n are present, then n+1 is the answer.
        // Otherwise, one of the numbers from 1 to n must be missing.

        int n = nums.length; // Get the length of the array.

        // This loop aims to place each positive number `k` in the range [1, n]
        // at index `k-1`.
        for (int i = 0; i < n; i++) {
            // `correctI` is the index where `nums[i]` *should* be if it's a positive
            // number within the range [1, n]. For example, if nums[i] is 3, it should be at index 2.
            int correctI = nums[i] - 1;

            // We enter the while loop if:
            // 1. `nums[i]` is a positive number (nums[i] > 0).
            // 2. `nums[i]` is within the bounds of our array indices (nums[i] <= n).
            //    This ensures we are only trying to place numbers that *could* belong
            //    in the array's indices [0, n-1].
            // 3. The number `nums[i]` is not already at its correct position.
            //    `nums[correctI] != nums[i]` prevents infinite loops if a number
            //    is already in its correct place or if there are duplicates.
            while (nums[i] > 0 && nums[i] <= n && nums[correctI] != nums[i]) {
                // Swap `nums[i]` with the element at its correct index `correctI`.
                // This moves `nums[i]` closer to its intended position.
                int temp = nums[i]; // Store the current value of nums[i] temporarily.
                nums[i] = nums[correctI]; // Place the value from the correct index into the current index.
                nums[correctI] = temp; // Place the original nums[i] value into its correct index.

                // After swapping, `nums[i]` has a new value. We need to re-evaluate
                // its correct position and continue the swapping process for this new value.
                // So, we update `correctI` based on the new `nums[i]`.
                correctI = nums[i] - 1;
            }
        }

        // After the rearrangement, iterate through the array to find the first
        // position where the number does not match its expected value.
        for (int i = 0; i < n; i++) {
            // If `nums[i]` is not equal to `i+1`, it means `i+1` is the first
            // positive integer that is missing from its correct place.
            if (nums[i] != i + 1) {
                return i + 1; // Return the missing positive integer.
            }
        }

        // If the loop completes, it means all numbers from 1 to n are present
        // in their correct positions. Therefore, the first missing positive
        // integer is `n+1`.
        return n + 1;
    }
}
```

## Interview Tips
*   Clearly explain the intuition that the answer is bounded by `n+1` and how the array can act as a hash map.
*   Walk through the swapping logic carefully, especially the conditions in the `while` loop and why they are necessary to prevent infinite loops and out-of-bounds access.
*   Discuss the time and space complexity, emphasizing the O(1) space due to in-place modification.
*   Be prepared to trace the algorithm with a small example array like `[1, 2, 0]` or `[3, 4, -1, 1]`.

## Revision Checklist
- [ ] Understand the problem statement: find the smallest missing positive integer.
- [ ] Grasp the intuition: answer is in `[1, n+1]`, use array as hash map.
- [ ] Implement the cyclic sort/placement logic correctly.
- [ ] Handle edge cases: empty array, all negatives, duplicates.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
*   [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/) (This problem)
*   [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) (Uses similar in-place manipulation)
*   [442. Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/)
*   [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)

## Tags
`Array` `Hash Map` `Two Pointers` `In-place Modification`
