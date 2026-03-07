# Interleaving String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(n * m)  
**Space:** O(n * m)

---

## Solution (java)

```java
class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {

        int n = s1.length();
        int m = s2.length();

        //agr 2 strings tod ke teesri bnani hai to wo dono equal number of parts me tutegi, ya 1 ke fark me. Not more than that, kyuki if you try breaking s1 into 2 parts and s2 into 7 parts, to anyway jud ke connect hojayege.

        // ❗ If total length doesn't match, interleaving is impossible
        if(n + m != s3.length()) 
            return false;

        // dp[i][j] = true means:
        // First i characters of s1
        // and first j characters of s2
        // can form first (i + j) characters of s3
        boolean[][] dp = new boolean[n+1][m+1];

        // Base case:
        // Empty s1 and empty s2 form empty s3
        dp[0][0] = true;

        // Fill first column
        // Using only s1 to match s3
        for(int i = 1; i <= n; i++){
            dp[i][0] = dp[i-1][0] && 
                       (s1.charAt(i-1) == s3.charAt(i-1));
        }
// aabcbi
// aaowiqqwdiowjdql
// t t f f f f f f f f f 

        // Fill first row
        // Using only s2 to match s3
        for(int j = 1; j <= m; j++){
            dp[0][j] = dp[0][j-1] && 
                       (s2.charAt(j-1) == s3.charAt(j-1));
        }

        // Fill rest of DP table
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                // Current character in s3 we want to match
                char currentChar = s3.charAt(i + j - 1);

                // Option 1: Take character from s1
                boolean takeFromS1 =
                        dp[i-1][j] && 
                        (s1.charAt(i-1) == currentChar);

                // Option 2: Take character from s2
                boolean takeFromS2 =
                        dp[i][j-1] && 
                        (s2.charAt(j-1) == currentChar);

                // If either option works, it's valid
                dp[i][j] = takeFromS1 || takeFromS2;
            }
        }

        // Final answer:
        // Can entire s1 and s2 form entire s3?
        return dp[n][m];
    }
}
```

---

---
## Problem Summary

Given three strings `s1`, `s2`, and `s3`, determine if `s3` is formed by interleaving the characters of `s1` and `s2`. Interleaving means that the characters of `s1` and `s2` are combined in a way that preserves their original relative order. For example, if `s1 = "aab"` and `s2 = "axy"`, then `s3 = "aaxaby"` is an interleaving, but `s3 = "abaaxy"` is not because the relative order of characters in `s1` is not preserved.

## Approach and Intuition

This problem can be effectively solved using dynamic programming. The core idea is to build up a solution by considering smaller subproblems.

Let `dp[i][j]` be a boolean value indicating whether the first `i` characters of `s1` and the first `j` characters of `s2` can interleave to form the first `i + j` characters of `s3`.

**Intuition:**

1.  **Base Case:** `dp[0][0]` should be `true` because two empty strings can interleave to form an empty string.

2.  **Initialization (First Row and Column):**
    *   The first column (`dp[i][0]`) represents using only characters from `s1` to form a prefix of `s3`. `dp[i][0]` is `true` if `dp[i-1][0]` is `true` AND the `i`-th character of `s1` matches the `i`-th character of `s3`.
    *   Similarly, the first row (`dp[0][j]`) represents using only characters from `s2` to form a prefix of `s3`. `dp[0][j]` is `true` if `dp[0][j-1]` is `true` AND the `j`-th character of `s2` matches the `j`-th character of `s3`.

3.  **General Case (`dp[i][j]`):** To determine if the first `i` characters of `s1` and the first `j` characters of `s2` can form the first `i + j` characters of `s3`, we consider the last character of the `i + j` prefix of `s3`, which is `s3.charAt(i + j - 1)`. This character must have come from either `s1` or `s2`.

    *   **Option 1: The character came from `s1`.** If `s1.charAt(i - 1)` (the `i`-th character of `s1`) matches `s3.charAt(i + j - 1)`, AND the preceding parts (`s1` up to `i-1` and `s2` up to `j`) could form `s3` up to `i + j - 1` (i.e., `dp[i-1][j]` is `true`), then this path is valid.
    *   **Option 2: The character came from `s2`.** If `s2.charAt(j - 1)` (the `j`-th character of `s2`) matches `s3.charAt(i + j - 1)`, AND the preceding parts (`s1` up to `i` and `s2` up to `j-1`) could form `s3` up to `i + j - 1` (i.e., `dp[i][j-1]` is `true`), then this path is valid.

    `dp[i][j]` will be `true` if either Option 1 OR Option 2 is `true`.

4.  **Final Result:** The answer to the problem is `dp[n][m]`, where `n` is the length of `s1` and `m` is the length of `s2`. This checks if the entire `s1` and entire `s2` can interleave to form the entire `s3`.

**Pre-condition Check:** An important initial check is that the sum of the lengths of `s1` and `s2` must equal the length of `s3`. If they don't match, interleaving is impossible, and we can return `false` immediately.

## Complexity Analysis

*   **Time:** O(n * m)
    *   We are filling a 2D DP table of size `(n+1) x (m+1)`. Each cell takes constant time to compute (a few comparisons and logical operations). Therefore, the total time complexity is proportional to the number of cells in the DP table.

*   **Space:** O(n * m)
    *   We use a 2D boolean array `dp` of size `(n+1) x (m+1)` to store the intermediate results.

## Code Walkthrough

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {

        int n = s1.length(); // Length of s1
        int m = s2.length(); // Length of s2

        // ❗ If total length doesn't match, interleaving is impossible
        // This is a crucial early exit condition.
        if(n + m != s3.length())
            return false;

        // dp[i][j] = true means:
        // First i characters of s1 (s1[0...i-1])
        // and first j characters of s2 (s2[0...j-1])
        // can form first (i + j) characters of s3 (s3[0...i+j-1])
        boolean[][] dp = new boolean[n+1][m+1];

        // Base case:
        // Empty s1 and empty s2 form empty s3.
        // dp[0][0] represents using 0 chars from s1 and 0 from s2 to form 0 chars of s3.
        dp[0][0] = true;

        // Fill first column:
        // This loop checks if prefixes of s3 can be formed using ONLY s1.
        // dp[i][0] means using first i chars of s1 and 0 chars of s2.
        for(int i = 1; i <= n; i++){
            // To form s3[0...i-1] using s1[0...i-1] and empty s2:
            // 1. The previous state dp[i-1][0] must be true (s1[0...i-2] formed s3[0...i-2]).
            // 2. The current character s1.charAt(i-1) must match s3.charAt(i-1).
            dp[i][0] = dp[i-1][0] &&
                       (s1.charAt(i-1) == s3.charAt(i-1));
        }

        // Fill first row:
        // This loop checks if prefixes of s3 can be formed using ONLY s2.
        // dp[0][j] means using 0 chars of s1 and first j chars of s2.
        for(int j = 1; j <= m; j++){
            // To form s3[0...j-1] using empty s1 and s2[0...j-1]:
            // 1. The previous state dp[0][j-1] must be true (s2[0...j-2] formed s3[0...j-2]).
            // 2. The current character s2.charAt(j-1) must match s3.charAt(j-1).
            dp[0][j] = dp[0][j-1] &&
                       (s2.charAt(j-1) == s3.charAt(j-1));
        }

        // Fill rest of DP table:
        // This is the core logic for general cases.
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                // Current character in s3 we want to match is at index (i + j - 1)
                // because we are considering prefixes of length i+j.
                char currentChar = s3.charAt(i + j - 1);

                // Option 1: Try to match currentChar using the i-th character of s1.
                // This is possible IF:
                // a) The state dp[i-1][j] is true (meaning s1[0...i-2] and s2[0...j-1] formed s3[0...i+j-2]).
                // b) The i-th character of s1 (s1.charAt(i-1)) matches currentChar.
                boolean takeFromS1 =
                        dp[i-1][j] &&
                        (s1.charAt(i-1) == currentChar);

                // Option 2: Try to match currentChar using the j-th character of s2.
                // This is possible IF:
                // a) The state dp[i][j-1] is true (meaning s1[0...i-1] and s2[0...j-2] formed s3[0...i+j-2]).
                // b) The j-th character of s2 (s2.charAt(j-1)) matches currentChar.
                boolean takeFromS2 =
                        dp[i][j-1] &&
                        (s2.charAt(j-1) == currentChar);

                // If either option works, then dp[i][j] is true.
                dp[i][j] = takeFromS1 || takeFromS2;
            }
        }

        // Final answer:
        // Can entire s1 (n characters) and entire s2 (m characters) form entire s3 (n+m characters)?
        return dp[n][m];
    }
}
```

## Interview Tips

1.  **Clarify the Problem:** Ensure you understand "interleaving" and "preserving relative order." Ask for examples if needed.
2.  **Initial Check:** Always start with the length check (`s1.length() + s2.length() == s3.length()`). This is a quick win and shows attention to edge cases.
3.  **DP State Definition:** Clearly define what `dp[i][j]` represents. This is crucial for explaining your logic.
4.  **Base Cases:** Explain the `dp[0][0]` base case and how the first row/column are initialized. This sets up the recursive structure.
5.  **Transitions:** Walk through the `dp[i][j]` calculation, explaining the two possibilities (taking from `s1` or `s2`) and the conditions for each.
6.  **Indices:** Be careful with 0-based vs. 1-based indexing. The DP table uses `i` and `j` to represent counts of characters, so `s1.charAt(i-1)` and `s3.charAt(i+j-1)` are important to get right.
7.  **Complexity:** Be ready to explain the time and space complexity and why it's O(n*m).
8.  **Optimization (if asked):** Discuss space optimization if the interviewer probes further.

## Optimization and Alternatives

**Space Optimization:**
The current DP approach uses O(n*m) space. Notice that to compute `dp[i][j]`, we only need values from the previous row (`dp[i-1][...]`) and the current row (`dp[i][j-1]`). This suggests that we can optimize the space complexity to O(m) (or O(n)) by using only two rows (current and previous) or even a single row if we iterate carefully.

*   **Using one row:** We can use a 1D DP array `dp[j]` representing `dp[i][j]` for the current `i`. When calculating `dp[j]` for the current `i`, `dp[j]` itself would store the value for `dp[i][j]`, `dp[j-1]` would store `dp[i][j-1]`, and the *old* value of `dp[j]` (before it's updated for the current `i`) would represent `dp[i-1][j]`. This requires careful handling of updates.

**Recursive with Memoization:**
An alternative to the iterative DP approach is a recursive solution with memoization. This often mirrors the DP recurrence relation more directly.

```java
// Example of recursive approach with memoization (not provided in original code)
class Solution {
    Boolean[][] memo;
    String s1, s2, s3;
    int n, m;

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.n = s1.length();
        this.m = s2.length();

        if (n + m != s3.length()) {
            return false;
        }

        memo = new Boolean[n + 1][m + 1];
        return solve(0, 0);
    }

    private boolean solve(int i, int j) {
        // Base case: If we have used all characters from s1 and s2
        if (i == n && j == m) {
            return true;
        }

        // Check memoization table
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int k = i + j; // Current index in s3

        boolean res = false;
        // Option 1: Take from s1
        if (i < n && s1.charAt(i) == s3.charAt(k)) {
            res = solve(i + 1, j);
        }

        // Option 2: Take from s2 (only if Option 1 didn't lead to a solution or if we want to explore both)
        if (!res && j < m && s2.charAt(j) == s3.charAt(k)) {
            res = solve(i, j + 1);
        }

        // Store result in memoization table
        memo[i][j] = res;
        return res;
    }
}
```
This recursive approach also has O(n*m) time and O(n*m) space (due to recursion stack and memoization table).

## Revision Checklist

*   [ ] Understand the problem statement thoroughly.
*   [ ] Identify the base case(s) for DP.
*   [ ] Define the DP state `dp[i][j]` clearly.
*   [ ] Formulate the recurrence relation for `dp[i][j]`.
*   [ ] Handle the initial length check.
*   [ ] Implement the DP table filling correctly (base cases, first row/column, general case).
*   [ ] Pay close attention to array/string indices (0-based vs. 1-based).
*   [ ] Analyze time and space complexity.
*   [ ] Consider space optimization if applicable.
*   [ ] Practice explaining the solution step-by-step.

## Similar Problems

*   Edit Distance
*   Longest Common Subsequence
*   Unique Paths
*   Word Break

## Tags
`Dynamic Programming` `String` `Recursion`

## My Notes
a comment
