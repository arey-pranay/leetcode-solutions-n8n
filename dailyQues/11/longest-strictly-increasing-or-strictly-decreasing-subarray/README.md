# Longest Strictly Increasing Or Strictly Decreasing Subarray

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxLength = 1;
        int incLength = 1;
        int decLength = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                incLength++;
                decLength = 1;
            } else if(nums[i] < nums[i - 1]) {
                decLength++;
                incLength = 1;
            } else {
                incLength = 1;
                decLength = 1;
            }
            maxLength = Math.max(maxLength, Math.max(incLength, decLength));
        }
        return maxLength;
    }
}
```

---

---
## Quick Revision
Finds the length of the longest contiguous subarray that is either strictly increasing or strictly decreasing.
Solved in a single linear pass by tracking current increasing and decreasing run lengths and updating the maximum.

## Intuition
The "aha moment" is realizing that we don't need to look backward to verify subarrays because monotonicity is a local property that extends incrementally. As we iterate through the array, each element either continues the current increasing sequence, continues the current decreasing sequence, or breaks both sequences (resetting them to 1). By simultaneously maintaining the length of the strictly increasing run (`incLength`) and strictly decreasing run (`decLength`) ending at the current index, we can capture the optimal answer in $O(N)$ time without redundant sub-scans.

## Algorithm
1. Initialize `maxLength`, `incLength`, and `decLength` to 1, because any single element array is trivially a monotonic subarray of length 1.
2. Handle the edge case implicitly: if the array length is 1, the loop won't execute and `maxLength` (1) is returned.
3. Iterate with a pointer `i` from index 1 to `nums.length - 1`.
4. Compare `nums[i]` with `nums[i - 1]`:
   - If `nums[i] > nums[i - 1]`, increment `incLength` by 1 and reset `decLength` to 1 because an increasing step breaks any ongoing decreasing streak.
   - If `nums[i] < nums[i - 1]`, increment `decLength` by 1 and reset `incLength` to 1 because a decreasing step breaks any ongoing increasing streak.
   - If `nums[i] == nums[i - 1]`, reset both `incLength` and `decLength` to 1 since strict monotonicity is violated.
5. Update `maxLength` with the maximum of its current value, `incLength`, and `decLength`.
6. Return `maxLength` after the loop finishes.

## Concept to Remember
- Single-pass array traversal.
- State tracking (maintaining running variables for concurrent conditions).
- Handling strict inequalities versus non-strict inequalities in monotonic sequences.

## Common Mistakes
- Forgetting to reset the counter for the opposite trend when a direction changes (e.g., not resetting `decLength` when an increase is detected).
- Failing to reset both counters when adjacent elements are equal (since the problem requires *strictly* increasing or decreasing).
- Initializing lengths to 0 instead of 1, which causes incorrect outputs for single-element arrays.
- Off-by-one errors with array bounds when starting the loop at index 1 versus index 0.

## Complexity Analysis
- Time: O(N) - We iterate through the array of length $N$ exactly once with constant-time operations at each step.
- Space: O(1) - Only a few primitive integer variables are used, requiring constant extra space regardless of input size.

## Commented Code
```java
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        // Initialize the overall maximum length found so far to 1 (minimum possible answer for non-empty arrays).
        int maxLength = 1;
        // Initialize the running length of the current strictly increasing subarray to 1.
        int incLength = 1;
        // Initialize the running length of the current strictly decreasing subarray to 1.
        int decLength = 1;
        
        // Iterate through the array starting from the second element (index 1).
        for(int i = 1; i < nums.length; i++) {
            // Check if the current element is strictly greater than the previous element.
            if(nums[i] > nums[i - 1]) {
                // Extend the strictly increasing sequence length.
                incLength++;
                // Reset the strictly decreasing sequence length since the decreasing trend is broken.
                decLength = 1;
            } 
            // Check if the current element is strictly less than the previous element.
            else if(nums[i] < nums[i - 1]) {
                // Extend the strictly decreasing sequence length.
                decLength++;
                // Reset the strictly increasing sequence length since the increasing trend is broken.
                incLength = 1;
            } 
            // Handle the case where the current element equals the previous element.
            else {
                // Reset the increasing sequence length as strict inequality is violated.
                incLength = 1;
                // Reset the decreasing sequence length as strict inequality is violated.
                decLength = 1;
            }
            
            // Update the global maximum length by comparing it with the current increasing and decreasing lengths.
            maxLength = Math.max(maxLength, Math.max(incLength, decLength));
        }
        
        // Return the maximum monotonic subarray length found.
        return maxLength;
    }
}
```

## Interview Tips
- Clarify edge cases early, such as an array with a single element or an array with all identical elements.
- Discuss alternative approaches (like nested loops taking $O(N^2)$ time) and explain why the single-pass state-tracking approach is optimal.
- Be precise about "strict" vs "non-strict" requirements—point out how equal consecutive elements are handled in your code.

## Revision Checklist
- [ ] Understand why equal elements reset both increasing and decreasing counters.
- [ ] Trace the algorithm manually with an array containing both peaks and valleys.
- [ ] Confirm space complexity is strictly $O(1)$.
- [ ] Explain why single-element arrays return 1 correctly.

## Similar Problems
- Longest Continuous Increasing Subsequence
- Maximum Subarray
- Number of Smooth Descent Periods of a Stock
- Longest Mountain in Array

## Tags
`Array` `Dynamic Programming` `Simulation`
