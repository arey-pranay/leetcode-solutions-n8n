# Smallest Stable Index I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Prefix Sum`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int min[] = new int[n];
        int max[] = new int[n];
        max[0] = nums[0];
        min[n-1] = nums[n-1];
        for(int i = 1;i<n;i++){max[i] = Math.max(nums[i],max[i-1]); min[n-1-i] = Math.min(nums[n-1-i],min[n-i]);}
        for(int i = 0;i<n;i++)if(max[i]-min[i] <= k) return i;
        return -1;
        
        
    }
}
```

---

---
## Quick Revision
Given an array `nums` and an integer `k`, find the smallest index `i` such that the maximum element in `nums[0...i]` minus the minimum element in `nums[i...n-1]` is less than or equal to `k`.
This is solved by precomputing prefix maximums and suffix minimums, then iterating to find the first index satisfying the condition.

## Intuition
The problem asks for the smallest index `i` where a specific condition holds. The condition involves two parts: the maximum value up to index `i` and the minimum value from index `i` onwards. If we can efficiently query these two values for any given `i`, we can then iterate through all possible `i` and find the smallest one that satisfies the condition.

The "aha moment" comes from realizing that prefix maximums and suffix minimums can be precomputed in linear time. Once these are available, checking the condition for each index `i` becomes an O(1) operation.

## Algorithm
1.  **Initialization**:
    *   Get the length of the input array `nums` and store it in `n`.
    *   Create two auxiliary arrays: `max_prefix` of size `n` to store prefix maximums, and `min_suffix` of size `n` to store suffix minimums.

2.  **Compute Prefix Maximums**:
    *   Initialize `max_prefix[0]` with `nums[0]`.
    *   Iterate from `i = 1` to `n-1`:
        *   Set `max_prefix[i] = Math.max(nums[i], max_prefix[i-1])`. This ensures `max_prefix[i]` stores the maximum value in `nums[0...i]`.

3.  **Compute Suffix Minimums**:
    *   Initialize `min_suffix[n-1]` with `nums[n-1]`.
    *   Iterate from `i = n-2` down to `0`:
        *   Set `min_suffix[i] = Math.min(nums[i], min_suffix[i+1])`. This ensures `min_suffix[i]` stores the minimum value in `nums[i...n-1]`.

4.  **Find Smallest Stable Index**:
    *   Iterate from `i = 0` to `n-1`:
        *   Check if `max_prefix[i] - min_suffix[i] <= k`.
        *   If the condition is true, return `i` as it's the smallest such index.

5.  **No Stable Index Found**:
    *   If the loop completes without finding a stable index, return `-1`.

## Concept to Remember
*   **Prefix Sums/Maximums/Minimums**: Efficiently calculating cumulative properties of an array.
*   **Suffix Sums/Maximums/Minimums**: Efficiently calculating cumulative properties from the end of an array.
*   **Two-Pointer/Sliding Window (Conceptual)**: While not a direct two-pointer solution, the idea of precomputing information from both ends to solve a problem efficiently is related.
*   **Iterative Improvement**: Building up solutions from smaller subproblems.

## Common Mistakes
*   **Incorrectly calculating suffix minimums**: Forgetting to iterate from right to left or using the wrong base case.
*   **Off-by-one errors**: In loop bounds or array indexing when computing prefix/suffix arrays.
*   **Not handling edge cases**: Such as an empty input array (though constraints usually prevent this) or an array with a single element.
*   **Confusing prefix and suffix definitions**: Ensuring `max_prefix[i]` covers `nums[0...i]` and `min_suffix[i]` covers `nums[i...n-1]`.

## Complexity Analysis
*   **Time**: O(n) - The algorithm involves three separate linear passes: one for prefix maximums, one for suffix minimums, and one to check the condition. Each pass takes O(n) time.
*   **Space**: O(n) - Two auxiliary arrays, `max_prefix` and `min_suffix`, are used, each of size `n`.

## Commented Code
```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length; // Get the length of the input array.
        // Create an array to store the maximum value from index 0 up to the current index.
        int[] max_prefix = new int[n];
        // Create an array to store the minimum value from the current index to the end of the array.
        int[] min_suffix = new int[n];

        // Initialize the first element of max_prefix with the first element of nums.
        max_prefix[0] = nums[0];
        // Compute prefix maximums: iterate from the second element to the end.
        for (int i = 1; i < n; i++) {
            // The maximum up to index i is either nums[i] itself or the maximum up to the previous index.
            max_prefix[i] = Math.max(nums[i], max_prefix[i - 1]);
        }

        // Initialize the last element of min_suffix with the last element of nums.
        min_suffix[n - 1] = nums[n - 1];
        // Compute suffix minimums: iterate from the second to last element backwards to the beginning.
        for (int i = n - 2; i >= 0; i--) {
            // The minimum from index i onwards is either nums[i] itself or the minimum from the next index onwards.
            min_suffix[i] = Math.min(nums[i], min_suffix[i + 1]);
        }

        // Iterate through all possible indices to find the smallest stable index.
        for (int i = 0; i < n; i++) {
            // Check if the condition for a stable index is met:
            // (maximum in nums[0...i]) - (minimum in nums[i...n-1]) <= k
            if (max_prefix[i] - min_suffix[i] <= k) {
                // If the condition is met, this is the smallest such index, so return it.
                return i;
            }
        }

        // If no stable index is found after checking all indices, return -1.
        return -1;
    }
}
```

## Interview Tips
*   **Explain the precomputation**: Clearly articulate why prefix maximums and suffix minimums are needed and how they help optimize the search.
*   **Walk through an example**: Use a small array and `k` value to demonstrate how `max_prefix`, `min_suffix` are populated and how the final loop finds the answer.
*   **Discuss time/space trade-offs**: Mention that the O(n) space is used to achieve O(n) time, which is generally a good trade-off for this type of problem.
*   **Clarify array indexing**: Be precise when explaining the ranges covered by `max_prefix[i]` and `min_suffix[i]`.

## Revision Checklist
- [ ] Understand the problem statement and the definition of a "stable index".
- [ ] Recognize the need for efficient calculation of prefix maximums and suffix minimums.
- [ ] Implement the prefix maximum calculation correctly.
- [ ] Implement the suffix minimum calculation correctly.
- [ ] Combine the precomputed arrays to find the smallest index satisfying the condition.
- [ ] Handle the case where no stable index exists.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Smallest Range I
*   Maximum Subarray
*   Prefix Sum problems
*   Problems involving finding minimum/maximum within a range.

## Tags
`Array` `Prefix Sum` `Suffix Sum` `Two Pointers`
