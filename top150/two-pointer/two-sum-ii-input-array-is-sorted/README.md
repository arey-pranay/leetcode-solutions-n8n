# Two Sum Ii Input Array Is Sorted

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        while(i < j){
            if(nums[i] + nums[j] > target) j--;
            else if (nums[i] + nums[j] < target) i++;
            else return new int[]{i+1,j+1};
        }
        return new int[]{1,1};
    }
}
```

---

---
## Quick Revision
Given a 1-indexed array of integers `nums` sorted in non-decreasing order, find two numbers such that they add up to a specific `target` number.
Use a two-pointer approach to efficiently find the pair in a sorted array.

## Intuition
Since the array is sorted, we can leverage this property. If we pick two numbers and their sum is too large, we know we need to decrease the sum. Because the array is sorted, decreasing the larger number (by moving the right pointer inwards) is the only way to potentially decrease the sum. Conversely, if the sum is too small, we need to increase it, which can be done by increasing the smaller number (by moving the left pointer inwards). This systematic narrowing of the search space is the core intuition.

## Algorithm
1. Initialize two pointers, `left` (or `i`) at the beginning of the array (index 0) and `right` (or `j`) at the end of the array (index `nums.length - 1`).
2. Start a `while` loop that continues as long as `left` is less than `right`.
3. Inside the loop, calculate the `currentSum` by adding `nums[left]` and `nums[right]`.
4. Compare `currentSum` with the `target`:
    a. If `currentSum` is greater than `target`, it means the sum is too large. To decrease the sum, move the `right` pointer one step to the left (`right--`).
    b. If `currentSum` is less than `target`, it means the sum is too small. To increase the sum, move the `left` pointer one step to the right (`left++`).
    c. If `currentSum` is equal to `target`, we have found the two numbers. Return a new integer array containing `left + 1` and `right + 1` (since the problem asks for 1-indexed results).
5. If the loop finishes without finding a pair (which shouldn't happen given the problem constraints usually guarantee a solution), return a default or error indicator (e.g., `new int[]{1, 1}` as in the provided solution, though a more robust solution might throw an exception or return an empty array if no solution is guaranteed).

## Concept to Remember
*   **Two Pointers:** A technique where two pointers traverse a data structure (like an array) from different directions or at different speeds to solve problems efficiently.
*   **Sorted Array Properties:** Leveraging the ordered nature of an array to make decisions about search space reduction.
*   **Time-Space Tradeoff:** Understanding how different algorithms use time and memory resources.

## Common Mistakes
*   Forgetting to add 1 to the indices when returning the result, as the problem requires 1-based indexing.
*   Incorrectly updating pointers (e.g., moving both pointers when only one should move).
*   Not handling the loop termination condition correctly, potentially leading to infinite loops or missed solutions.
*   Assuming the array might not contain a solution and not having a fallback return statement.

## Complexity Analysis
- Time: O(n) - reason: In the worst case, each pointer traverses the array at most once. The `left` pointer moves from 0 to `n-1`, and the `right` pointer moves from `n-1` to 0.
- Space: O(1) - reason: We are only using a constant amount of extra space for the two pointers and the result array.

## Commented Code
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Initialize the left pointer to the beginning of the array.
        int i = 0;
        // Initialize the right pointer to the end of the array.
        int j = nums.length - 1;
        // Loop as long as the left pointer is before the right pointer.
        while (i < j) {
            // Calculate the sum of the elements at the current left and right pointers.
            if (nums[i] + nums[j] > target) {
                // If the sum is greater than the target, we need to decrease the sum.
                // Since the array is sorted, we move the right pointer to a smaller element.
                j--;
            } else if (nums[i] + nums[j] < target) {
                // If the sum is less than the target, we need to increase the sum.
                // Since the array is sorted, we move the left pointer to a larger element.
                i++;
            } else {
                // If the sum equals the target, we found the pair.
                // Return the 1-indexed positions of the elements.
                return new int[]{i + 1, j + 1};
            }
        }
        // This line should ideally not be reached if a solution is guaranteed.
        // It's a fallback return, returning {1,1} as per the provided solution.
        return new int[]{1, 1};
    }
}
```

## Interview Tips
*   Clearly explain the two-pointer approach and why it works specifically because the array is sorted.
*   Emphasize the 1-based indexing requirement and ensure your code correctly adjusts the indices before returning.
*   Discuss the time and space complexity of your solution, contrasting it with a brute-force or hash map approach for the unsorted version.
*   Be prepared to handle edge cases, although this problem usually guarantees a solution exists.

## Revision Checklist
- [ ] Understand the problem statement: find two numbers in a sorted array that sum to a target.
- [ ] Implement the two-pointer approach correctly.
- [ ] Ensure correct pointer movement based on sum comparison.
- [ ] Remember to return 1-indexed results.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition behind the two-pointer method.

## Similar Problems
*   Two Sum
*   3Sum
*   Container With Most Water
*   Valid Triangle Number

## Tags
`Array` `Two Pointers` `Binary Search`
