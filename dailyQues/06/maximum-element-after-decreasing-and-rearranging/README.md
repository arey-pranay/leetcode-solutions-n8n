# Maximum Element After Decreasing And Rearranging

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(N log N)  
**Space:** O(1)

---

## Solution (java)

```java
// class Solution {
//     public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
//         HashSet<Integer> hs = new HashSet<>();
//         for(int i : arr) hs.add(i);
//         return hs.size();
//     }
// } not correct
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++)arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        return arr[arr.length - 1];
    }
}
```

---

---
## Quick Revision
Given an array of positive integers, we can decrease any element and rearrange the array. Find the maximum possible value of the largest element.
Sort the array and greedily assign values, ensuring no two adjacent elements are the same and the first element is 1.

## Intuition
The problem asks for the maximum possible value of the largest element after performing operations. To maximize the largest element, we should try to make the elements as large as possible while adhering to the constraints. The constraints are:
1. We can decrease any element's value.
2. We can rearrange the array.
3. After operations, for any two adjacent elements `arr[i]` and `arr[j]`, `abs(arr[i] - arr[j]) <= 1`.

The third constraint is the most crucial. If we sort the array, say `a_1 <= a_2 <= ... <= a_n`, then after rearrangement and decreasing, the new values `b_1, b_2, ..., b_n` must satisfy `abs(b_i - b_{i+1}) <= 1` for all `i`. This implies that if we sort the modified array, the values must be consecutive or have gaps of at most 1. For example, `[1, 2, 3, 4]` or `[1, 2, 2, 3]`.

To maximize the largest element, we want to make the sequence of numbers as large as possible. If we sort the original array `arr` in non-decreasing order, and then try to assign new values `new_arr` such that `new_arr[0] = 1` (the smallest possible positive integer), and `new_arr[i] <= new_arr[i-1] + 1`, we are essentially trying to fill the array with the largest possible consecutive integers starting from 1.

Consider the sorted original array: `arr[0] <= arr[1] <= ... <= arr[n-1]`.
If we want to assign `new_arr[0] = 1`, then `new_arr[1]` can be at most `new_arr[0] + 1 = 2`.
In general, `new_arr[i]` can be at most `new_arr[i-1] + 1`.
Also, we can only decrease values. So, `new_arr[i]` must be less than or equal to the original `arr[i]` (after sorting).
Combining these, `new_arr[i] <= min(arr[i], new_arr[i-1] + 1)`.

If we set `new_arr[0] = 1`, and then iteratively set `new_arr[i] = min(arr[i], new_arr[i-1] + 1)`, we are greedily assigning the largest possible valid value to each position. The final element `new_arr[n-1]` will be the maximum possible value for the largest element in the rearranged array.

The "aha moment" is realizing that sorting the array and then greedily assigning values from left to right, ensuring the adjacent difference constraint and the original value constraint, will lead to the maximum possible value for the last element. The constraint `abs(arr[i] - arr[j]) <= 1` for adjacent elements in the *final* array is key. If we sort the final array, this means `b_{i+1} - b_i <= 1`. Combined with the fact that we can only decrease values, if the original sorted array is `a_1, a_2, ..., a_n`, the final sorted array `b_1, b_2, ..., b_n` must satisfy `b_i <= a_i` and `b_{i+1} <= b_i + 1`. To maximize `b_n`, we should make `b_1` as small as possible (which is 1) and then greedily make each subsequent `b_i` as large as possible.

## Algorithm
1. Sort the input array `arr` in non-decreasing order.
2. Set the first element `arr[0]` to 1. This is the smallest possible positive integer value.
3. Iterate through the array starting from the second element (index 1).
4. For each element `arr[i]`, update its value to be the minimum of its current value and the value of the previous element plus one (`arr[i-1] + 1`). This ensures that the adjacent difference constraint (`abs(arr[i] - arr[i-1]) <= 1`) is maintained and we are using the largest possible valid value for `arr[i]` given the previous element's value and the original value constraint.
5. After iterating through the entire array, the last element `arr[arr.length - 1]` will hold the maximum possible value for the largest element in the rearranged array.
6. Return `arr[arr.length - 1]`.

## Concept to Remember
*   Greedy Approach: Making locally optimal choices at each step to achieve a globally optimal solution.
*   Sorting: Essential for establishing order and applying the greedy strategy based on adjacent elements.
*   Constraints: Understanding and effectively using all problem constraints (decreasing values, rearrangement, adjacent difference).

## Common Mistakes
*   Not sorting the array: The greedy strategy relies on the sorted order to correctly determine the maximum possible values for adjacent elements.
*   Incorrectly handling the first element: Forgetting to set `arr[0]` to 1, or setting it to a value other than 1, will lead to an incorrect result.
*   Ignoring the original value constraint: Only considering the `arr[i-1] + 1` constraint without also ensuring `arr[i]` does not exceed its original value (after sorting) will be wrong.
*   Misinterpreting the adjacent difference constraint: Applying it to the original array instead of the modified array.

## Complexity Analysis
- Time: O(N log N) - due to the sorting step. The subsequent loop takes O(N) time.
- Space: O(1) - if the sorting is done in-place. Some sorting algorithms might use O(log N) or O(N) auxiliary space depending on the implementation.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Sort the array in non-decreasing order. This is crucial for the greedy approach.
        Arrays.sort(arr);

        // Set the first element to 1. This is the smallest possible positive integer value,
        // and it's the starting point for our greedy assignment.
        arr[0] = 1;

        // Iterate through the array starting from the second element (index 1).
        for (int i = 1; i < arr.length; i++) {
            // For each element, we want to assign it the largest possible value that satisfies two conditions:
            // 1. It must be at most one greater than the previous element (arr[i-1] + 1).
            // 2. It cannot be greater than its original value in the sorted array (arr[i]).
            // We take the minimum of these two to ensure both conditions are met.
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }

        // After the loop, the last element of the array (arr[arr.length - 1]) will hold
        // the maximum possible value for the largest element in the rearranged array.
        return arr[arr.length - 1];
    }
}
```

## Interview Tips
*   Explain the greedy strategy clearly: Emphasize why sorting and then filling values from left to right is optimal.
*   Walk through an example: Use a small array like `[3, 1, 5, 4, 2]` to demonstrate the sorting and step-by-step assignment process.
*   Discuss the constraints: Highlight how the `abs(diff) <= 1` and "decreasing" constraints are handled by the algorithm.
*   Be prepared to discuss edge cases: What if the array has only one element? What if all elements are already 1?

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the importance of sorting.
- [ ] Implement the greedy assignment logic correctly.
- [ ] Handle the base case for the first element.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [1846. Maximum Element After Decreasing And Rearranging](https://leetcode.com/problems/maximum-element-after-decrementing-and-rearranging/) (This is the problem itself)
*   [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/) (Greedy approach on an array)
*   [1326. Minimum Number of Taps to Open to Water a Garden](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/) (Greedy interval covering)

## Tags
`Array` `Sorting` `Greedy`
