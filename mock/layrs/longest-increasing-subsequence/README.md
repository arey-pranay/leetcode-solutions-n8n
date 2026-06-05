# Longest Increasing Subsequence

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int[] memo;
    public int lengthOfLIS(int[] nums) {
        int ans = Integer.MIN_VALUE;
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        for(int i = 0 ; i<nums.length;i++) ans = Math.max(ans,func(nums,i));
        return ans;
    }
    public int func(int[] nums, int i){
        if(memo[i] != -1) return memo[i];
        int max = 1;
        for(int j=0;j<i;j++){
            if(nums[j] < nums[i]){
                int temp = 1 + memo[j];
                max = Math.max(max,temp);
            }
        }
        return memo[i] = max;
    }
}
```

---

---
## Quick Revision
Find the length of the longest subsequence where elements are in strictly increasing order.
Solved using dynamic programming with memoization or a binary search approach.

## Intuition
For each number `nums[i]`, we want to find the longest increasing subsequence ending *at* `nums[i]`. This subsequence can be formed by taking `nums[i]` and appending it to any longest increasing subsequence ending at a previous number `nums[j]` (where `j < i`) if `nums[j] < nums[i]`. The length of this new subsequence would be `1 + length_of_LIS_ending_at_nums[j]`. We take the maximum of all such possibilities. If no such `nums[j]` exists, the longest increasing subsequence ending at `nums[i]` is just `nums[i]` itself, with length 1.

## Algorithm
1. Initialize a memoization array `memo` of the same size as `nums`, filled with -1 (or any indicator that the value hasn't been computed yet). This array will store the length of the longest increasing subsequence ending at each index.
2. Iterate through each element `nums[i]` from `i = 0` to `nums.length - 1`.
3. For each `nums[i]`, call a recursive helper function `func(nums, i)` to compute the length of the LIS ending at `nums[i]`.
4. The `func(nums, i)` function:
    a. If `memo[i]` is not -1, return the stored value.
    b. Initialize `max_len` to 1 (representing the subsequence containing only `nums[i]`).
    c. Iterate through all previous elements `nums[j]` where `j < i`.
    d. If `nums[j] < nums[i]`, it means `nums[i]` can extend an increasing subsequence ending at `nums[j]`.
    e. Recursively call `func(nums, j)` to get the length of the LIS ending at `nums[j]`.
    f. Update `max_len = Math.max(max_len, 1 + func(nums, j))`.
    g. Store the computed `max_len` in `memo[i]` and return it.
5. Keep track of the overall maximum length found across all `func(nums, i)` calls.
6. Return the overall maximum length.

## Concept to Remember
*   **Dynamic Programming (DP):** Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Memoization:** A top-down DP approach where results of function calls are cached.
*   **Subsequence vs. Substring:** A subsequence maintains relative order but doesn't require contiguous elements, unlike a substring.

## Common Mistakes
*   Confusing subsequence with substring.
*   Incorrectly handling the base case (LIS ending at an element is at least 1).
*   Not initializing the memoization table correctly or not checking it before computation.
*   Forgetting to update the overall maximum answer after computing the LIS ending at each index.

## Complexity Analysis
*   Time: O(n^2) - The outer loop iterates `n` times, and the inner loop (or recursive calls within `func`) also iterates up to `n` times for each outer iteration. Each `func(i)` is computed only once due to memoization.
*   Space: O(n) - For the memoization array `memo` and the recursion call stack (which can go up to `n` in the worst case).

## Commented Code
```java
class Solution {
    // memo array to store the length of the LIS ending at each index.
    // Initialized with -1 to indicate values haven't been computed yet.
    int[] memo;

    // Main function to find the length of the longest increasing subsequence.
    public int lengthOfLIS(int[] nums) {
        // Initialize the overall maximum answer to the smallest possible integer.
        int ans = Integer.MIN_VALUE;
        // Initialize the memoization array with the size of the input array.
        memo = new int[nums.length];
        // Fill the memo array with -1, indicating no values have been computed.
        Arrays.fill(memo,-1);
        // Iterate through each element of the array.
        for(int i = 0 ; i<nums.length;i++) {
            // For each element, calculate the LIS ending at this element using the func.
            // Update the overall maximum answer found so far.
            ans = Math.max(ans,func(nums,i));
        }
        // Return the overall maximum length of any increasing subsequence.
        return ans;
    }

    // Recursive helper function to compute the length of the LIS ending at index i.
    public int func(int[] nums, int i){
        // If the result for index i is already computed and stored in memo, return it.
        if(memo[i] != -1) return memo[i];

        // Initialize the maximum length for the LIS ending at index i to 1.
        // This represents the subsequence containing only nums[i] itself.
        int max = 1;
        // Iterate through all elements before index i.
        for(int j=0;j<i;j++){
            // If the previous element nums[j] is strictly smaller than the current element nums[i],
            // it means nums[i] can extend an increasing subsequence ending at nums[j].
            if(nums[j] < nums[i]){
                // Calculate the potential new length: 1 (for nums[i]) + length of LIS ending at nums[j].
                // We use memo[j] here because func(nums, j) would have already computed and stored it.
                int temp = 1 + memo[j]; // Note: This line assumes memo[j] is already computed. This is guaranteed by the outer loop's structure and the recursive calls.
                // Update max to be the longest possible subsequence ending at nums[i].
                max = Math.max(max,temp);
            }
        }
        // Store the computed maximum length for LIS ending at index i in the memo array.
        // Then, return this computed value.
        return memo[i] = max;
    }
}
```

## Interview Tips
*   Clearly explain the DP state: "What is `dp[i]` or `memo[i]` representing?" (Length of LIS ending at index `i`).
*   Walk through a small example (e.g., `[10, 9, 2, 5, 3, 7, 101, 18]`) to illustrate the logic of building up the solution.
*   Discuss the time and space complexity of the DP approach. Mention the O(n log n) solution if time permits or if asked for optimization.
*   Be prepared to explain the difference between subsequence and substring.

## Revision Checklist
- [ ] Understand the problem statement: Longest Increasing Subsequence.
- [ ] Identify the DP state: `memo[i]` = LIS length ending at `nums[i]`.
- [ ] Define the recurrence relation: `memo[i] = 1 + max(memo[j])` for all `j < i` where `nums[j] < nums[i]`.
- [ ] Handle the base case: `memo[i]` is at least 1.
- [ ] Implement memoization to avoid recomputing subproblems.
- [ ] Track the overall maximum answer.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Common Subsequence
*   Maximum Sum Increasing Subsequence
*   Longest Continuous Increasing Subsequence
*   Russian Doll Envelopes

## Tags
`Array` `Dynamic Programming` `Binary Search`
