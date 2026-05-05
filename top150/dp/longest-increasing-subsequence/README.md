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
For each number `nums[i]`, we want to find the longest increasing subsequence ending *at* `nums[i]`. This subsequence can be formed by appending `nums[i]` to any existing increasing subsequence ending at `nums[j]` (where `j < i`) if `nums[j] < nums[i]`. The length of such a subsequence would be `1 + length_of_LIS_ending_at_nums[j]`. We take the maximum of these possibilities. If no such `nums[j]` exists, the longest increasing subsequence ending at `nums[i]` is just `nums[i]` itself, with length 1. The overall answer is the maximum length found across all ending positions.

## Algorithm
1. Initialize a memoization array `memo` of the same size as `nums`, filled with -1 (or any indicator that the value hasn't been computed yet). This array will store the length of the longest increasing subsequence ending at each index.
2. Initialize a variable `ans` to `Integer.MIN_VALUE` to store the overall maximum length found.
3. Iterate through each element `nums[i]` from `i = 0` to `nums.length - 1`.
4. For each `i`, call a recursive helper function `func(nums, i)` to compute the length of the LIS ending at index `i`.
5. Update `ans = Math.max(ans, func(nums, i))`.
6. The `func(nums, i)` function:
    a. If `memo[i]` is not -1, return `memo[i]` (result already computed).
    b. Initialize `max` to 1 (the subsequence containing only `nums[i]` itself).
    c. Iterate through all previous elements `nums[j]` where `j` ranges from `0` to `i - 1`.
    d. If `nums[j] < nums[i]`, it means `nums[i]` can extend an increasing subsequence ending at `nums[j]`.
    e. Calculate a potential new length: `temp = 1 + func(nums, j)`.
    f. Update `max = Math.max(max, temp)`.
    g. Store the computed `max` in `memo[i]` and return it.
7. After the loop, return `ans`.

## Concept to Remember
*   **Dynamic Programming (DP):** Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Memoization (Top-Down DP):** Using recursion with a cache (like an array) to store results of expensive function calls.
*   **Subsequence vs. Substring:** A subsequence doesn't require contiguous elements, while a substring does.

## Common Mistakes
*   Confusing subsequence with substring (requiring contiguous elements).
*   Incorrectly handling the base case for the LIS length (should be at least 1 for any single element).
*   Forgetting to check `nums[j] < nums[i]` before considering extending a subsequence.
*   Not initializing the memoization table correctly or not checking its values before recomputing.
*   Returning the LIS ending at the last element instead of the maximum LIS across all ending elements.

## Complexity Analysis
- Time: O(n^2) - reason: The outer loop iterates `n` times, and the inner loop (within `func`) also iterates up to `n` times. Each call to `func` might involve an O(n) loop, and with memoization, each `func(i)` is computed only once.
- Space: O(n) - reason: For the memoization array `memo` of size `n`, and O(n) for the recursion call stack in the worst case.

## Commented Code
```java
class Solution {
    // memo array to store the length of the LIS ending at each index.
    // Initialized with -1 to indicate that the value has not been computed yet.
    int[] memo;

    // Main function to find the length of the longest increasing subsequence.
    public int lengthOfLIS(int[] nums) {
        // Initialize the overall maximum length found so far to the smallest possible integer.
        int ans = Integer.MIN_VALUE;
        // Initialize the memoization array with -1.
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        // Iterate through each element of the array.
        for(int i = 0 ; i<nums.length;i++) {
            // For each element, calculate the LIS ending at this element using the recursive function.
            // Update the overall maximum length if the current LIS is longer.
            ans = Math.max(ans,func(nums,i));
        }
        // Return the overall maximum length found.
        return ans;
    }

    // Recursive helper function to compute the length of the LIS ending at index 'i'.
    public int func(int[] nums, int i){
        // If the result for index 'i' is already computed and stored in memo, return it.
        if(memo[i] != -1) return memo[i];

        // Initialize the maximum length of LIS ending at 'i' to 1.
        // This represents the subsequence containing only nums[i] itself.
        int max = 1;
        // Iterate through all elements before index 'i'.
        for(int j=0;j<i;j++){
            // If the element at index 'j' is strictly smaller than the element at index 'i',
            // it means nums[i] can extend an increasing subsequence ending at nums[j].
            if(nums[j] < nums[i]){
                // Calculate the potential new length: 1 (for nums[i]) + LIS ending at nums[j].
                // We recursively call func(nums, j) to get the LIS ending at j.
                int temp = 1 + func(nums,j);
                // Update 'max' if this new length is greater than the current maximum.
                max = Math.max(max,temp);
            }
        }
        // Store the computed maximum length for index 'i' in the memoization array.
        // Then, return this computed maximum length.
        return memo[i] = max;
    }
}
```

## Interview Tips
*   Clearly explain the DP state: "What is `dp[i]` or `memo[i]` representing?" In this case, it's the length of the LIS *ending* at index `i`.
*   Walk through an example: Use a small array like `[10, 9, 2, 5, 3, 7, 101, 18]` and trace how `memo` gets filled.
*   Discuss the time and space complexity of the DP approach and mention the possibility of an O(n log n) solution (using binary search) if time permits or if prompted.
*   Be prepared to explain the difference between subsequence and substring.

## Revision Checklist
- [ ] Understand the problem statement: Longest Increasing Subsequence.
- [ ] Identify the DP state: `memo[i]` = LIS length ending at `nums[i]`.
- [ ] Define the recurrence relation: `memo[i] = 1 + max(memo[j])` for all `j < i` where `nums[j] < nums[i]`.
- [ ] Handle the base case: `memo[i]` is at least 1.
- [ ] Implement memoization to avoid recomputation.
- [ ] Iterate through all possible ending points to find the overall maximum.
- [ ] Analyze time and space complexity.
- [ ] Consider the O(n log n) approach (Patience Sorting/Binary Search).

## Similar Problems
*   Longest Increasing Subsequence II (LeetCode 1235) - Variation with constraints.
*   Number of Longest Increasing Subsequences (LeetCode 673) - Find count of LIS.
*   Maximum Sum Increasing Subsequence (GeeksforGeeks) - Variation with sum.
*   Longest Common Subsequence (LeetCode 1143) - Related DP concept.

## Tags
`Array` `Dynamic Programming` `Binary Search` `Memoization`
