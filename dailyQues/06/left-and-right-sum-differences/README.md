# Left And Right Sum Differences

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Prefix Sum` `Two Pointers`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) ans[i] += i==0 ? 0 : nums[i-1] + ans[i-1];
        
        int sum = 0;
        for (int i = n - 1; i >= 0; --i) { ans[i] = Math.abs(ans[i] - sum); sum += nums[i]; }
        
        return ans;
    }
}
```

---

---
## Quick Revision
Given an array of integers, calculate the absolute difference between the sum of elements to the left and the sum of elements to the right for each element.
This is solved by pre-calculating left sums and then iterating from right to left to calculate right sums and the final differences.

## Intuition
The core idea is to efficiently compute the sum of elements to the left and right of each index. Instead of recalculating these sums for every element (which would be O(n^2)), we can use prefix sums. We can calculate the sum of all elements first. Then, as we iterate from left to right, we maintain a `leftSum`. For each element `nums[i]`, the `rightSum` can be derived by subtracting `leftSum` and `nums[i]` from the `totalSum`. Finally, we take the absolute difference.

Alternatively, and as seen in the provided solution, we can compute the left sums in one pass and then compute the right sums and the differences in a second pass from right to left, which avoids needing the total sum explicitly.

## Algorithm
1. Initialize an integer array `ans` of the same size as `nums` to store the results.
2. Initialize a variable `leftSum` to 0.
3. Iterate through the `nums` array from left to right (index `i` from 0 to `n-1`):
    a. For the current index `i`, set `ans[i]` to the current `leftSum`.
    b. Add `nums[i]` to `leftSum` for the next iteration.
4. Initialize a variable `rightSum` to 0.
5. Iterate through the `nums` array from right to left (index `i` from `n-1` down to 0):
    a. Calculate the absolute difference between `ans[i]` (which currently holds the left sum) and `rightSum`. Update `ans[i]` with this absolute difference.
    b. Add `nums[i]` to `rightSum` for the next iteration.
6. Return the `ans` array.

## Concept to Remember
*   **Prefix Sums:** Efficiently calculating sums of subarrays.
*   **Two-Pointer/Two-Pass Approach:** Using separate passes to gather information from different directions.
*   **In-place Modification:** Reusing an array to store intermediate and final results.
*   **Absolute Value:** Understanding the `Math.abs()` function for differences.

## Common Mistakes
*   **Incorrectly calculating `rightSum`:** Forgetting to subtract the current element `nums[i]` when calculating the `rightSum` from the total sum.
*   **Off-by-one errors:** Mismanaging loop bounds or array indices, especially when dealing with the first or last elements.
*   **Not handling the base cases:** For the first element, the left sum is 0. For the last element, the right sum is 0.
*   **Integer overflow:** While unlikely with typical LeetCode constraints for this problem, it's a general consideration for sum-related problems.
*   **Inefficient calculation:** Recomputing sums from scratch for each element (O(n^2)) instead of using prefix sums.

## Complexity Analysis
*   **Time:** O(n) - The solution involves two separate passes through the array, each taking O(n) time.
*   **Space:** O(n) - An additional array `ans` of size `n` is used to store the results. If we consider the output array as part of the space complexity, it's O(n). If not, and we were allowed to modify the input array, it could be O(1) extra space.

## Commented Code
```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length; // Get the length of the input array.
        int[] ans = new int[n]; // Initialize the result array with the same size.

        // First pass: Calculate the sum of elements to the left of each index.
        // 'ans[i]' will store the sum of elements from index 0 to i-1.
        int leftSum = 0; // Initialize the running sum of elements to the left.
        for (int i = 0; i < n; i++) { // Iterate through the array from left to right.
            ans[i] = leftSum; // Assign the current leftSum to ans[i]. This is the sum of elements *before* nums[i].
            leftSum += nums[i]; // Update leftSum by adding the current element for the next iteration.
        }

        // Second pass: Calculate the sum of elements to the right of each index and the absolute difference.
        int rightSum = 0; // Initialize the running sum of elements to the right.
        for (int i = n - 1; i >= 0; i--) { // Iterate through the array from right to left.
            // ans[i] currently holds the left sum.
            // We need to calculate the absolute difference between left sum and right sum.
            ans[i] = Math.abs(ans[i] - rightSum); // Update ans[i] with the absolute difference.
            rightSum += nums[i]; // Update rightSum by adding the current element for the next iteration.
        }

        return ans; // Return the array containing the left-right sum differences.
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of values in `nums` and the maximum size of the array to anticipate potential overflow issues (though unlikely here).
*   **Explain the Two-Pass Approach:** Clearly articulate why a single pass is insufficient and how two passes (or one pass with pre-computation of total sum) are necessary for efficiency.
*   **Walk Through an Example:** Use a small example array (e.g., `[10, 4, 8, 3]`) to manually trace the algorithm's execution, showing how `leftSum`, `rightSum`, and `ans` are updated.
*   **Discuss Space Optimization:** Mention that if modifying the input array were allowed, the space complexity could be reduced to O(1) by storing intermediate sums directly in `nums`.

## Revision Checklist
- [ ] Understand the problem statement: calculate absolute difference between left and right sums for each element.
- [ ] Recognize the need for prefix sums or a two-pass approach to avoid O(n^2).
- [ ] Implement the first pass to calculate left sums.
- [ ] Implement the second pass (from right to left) to calculate right sums and the final differences.
- [ ] Correctly use `Math.abs()` for the difference.
- [ ] Handle edge cases (first and last elements) implicitly or explicitly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [1991. Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/)
*   [724. Find Pivot Index](https://leetcode.com/problems/find-pivot-index/)
*   [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) (related concept of prefix sums)

## Tags
`Array` `Prefix Sum` `Two Pointers`
