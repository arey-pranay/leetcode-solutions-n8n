# Interleaving String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(m * n * o)  
**Space:** O(m * n * o)

---

## Solution (java)

```java
class Solution {
    int m,n,o;
    Boolean[][][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length(); n = s2.length(); o = s3.length();
        if(m+n!=o) return false;   
        memo = new Boolean[m+1][n+1][o+1];
        return func(0,0,0,s1,s2,s3);
    }
    public boolean func(int i,int j, int k, String s1, String s2, String s3){
        if(k==o) return memo[i][j][k] = true;
        if(memo[i][j][k] != null) return memo[i][j][k];
        if(i<m && s1.charAt(i)==s3.charAt(k) && func(i+1,j,k+1,s1,s2,s3)) return memo[i][j][k] = true;
        if(j<n && s2.charAt(j)==s3.charAt(k) && func(i,j+1,k+1,s1,s2,s3)) return memo[i][j][k] = true;
        return memo[i][j][k] = false;
    }
}
// s1 = 
// s2 = 
// s3 = 
```

---

---
## Quick Revision
Given three strings s1, s2, and s3, determine if s3 is formed by interleaving s1 and s2.
This problem can be solved using dynamic programming or recursion with memoization by checking character matches at each step.

## Intuition
The core idea is to check if s3 can be constructed by picking characters from either s1 or s2 in their original order. At any point, if we are trying to match the k-th character of s3, we have two choices:
1. If the k-th character of s3 matches the current character of s1 (say, i-th character), we can potentially use it and then try to match the rest of s3 with the rest of s1 and s2.
2. Similarly, if the k-th character of s3 matches the current character of s2 (say, j-th character), we can potentially use it and then try to match the rest of s3 with the rest of s1 and s2.
If either of these choices leads to a successful interleaving, then s3 is an interleaving of s1 and s2. The base case is when we have successfully consumed all characters of s3.

## Algorithm
1. **Length Check**: First, check if the sum of the lengths of `s1` and `s2` is equal to the length of `s3`. If not, `s3` cannot be an interleaving, so return `false`.
2. **Memoization Table**: Initialize a 3D memoization table `memo` of size `(m+1) x (n+1) x (o+1)` where `m`, `n`, and `o` are the lengths of `s1`, `s2`, and `s3` respectively. This table will store the results of subproblems to avoid redundant calculations. Initialize all entries to `null`.
3. **Recursive Function `func(i, j, k, s1, s2, s3)`**: This function will recursively check if the substring of `s3` starting from index `k` can be formed by interleaving the substring of `s1` starting from index `i` and the substring of `s2` starting from index `j`.
    a. **Base Case**: If `k` reaches the end of `s3` (i.e., `k == o`), it means we have successfully interleaved all characters, so return `true`.
    b. **Memoization Check**: If `memo[i][j][k]` is not `null`, it means this subproblem has already been solved, so return the stored result.
    c. **Recursive Step 1 (Match with s1)**: If `i` is within the bounds of `s1` (`i < m`) AND the `i`-th character of `s1` matches the `k`-th character of `s3` (`s1.charAt(i) == s3.charAt(k)`), then recursively call `func(i+1, j, k+1, s1, s2, s3)`. If this recursive call returns `true`, it means we found a valid interleaving using a character from `s1`. Store `true` in `memo[i][j][k]` and return `true`.
    d. **Recursive Step 2 (Match with s2)**: If `j` is within the bounds of `s2` (`j < n`) AND the `j`-th character of `s2` matches the `k`-th character of `s3` (`s2.charAt(j) == s3.charAt(k)`), then recursively call `func(i, j+1, k+1, s1, s2, s3)`. If this recursive call returns `true`, it means we found a valid interleaving using a character from `s2`. Store `true` in `memo[i][j][k]` and return `true`.
    e. **No Match**: If neither of the above conditions leads to a successful interleaving, it means the current path does not lead to a solution. Store `false` in `memo[i][j][k]` and return `false`.
4. **Initial Call**: Call `func(0, 0, 0, s1, s2, s3)` to start the process from the beginning of all strings.

## Concept to Remember
*   **Recursion with Memoization**: Efficiently solves overlapping subproblems by storing results of already computed states.
*   **Dynamic Programming**: Breaks down a complex problem into simpler, overlapping subproblems and builds up the solution.
*   **String Manipulation**: Understanding how to access and compare characters within strings.
*   **State Representation**: Defining the state of a subproblem (e.g., current indices in `s1`, `s2`, and `s3`).

## Common Mistakes
*   **Forgetting the Length Check**: Not verifying `s1.length() + s2.length() == s3.length()` upfront can lead to incorrect results or infinite recursion.
*   **Incorrect Base Case**: The base case should be when all characters of `s3` are successfully matched (`k == o`), not just when `i` or `j` reach their ends.
*   **Off-by-One Errors**: Incorrectly handling string indices or array bounds in recursive calls or memoization table access.
*   **Not Handling `null` Memoization Entries**: Failing to check if `memo[i][j][k]` is `null` before returning its value, leading to recomputation.
*   **Missing `k` in Memoization State**: The state must include `k` (the index in `s3`) because the decision at `(i, j)` depends on which character of `s3` we are currently trying to match.

## Complexity Analysis
*   **Time**: O(m * n * o) - The state space for memoization is `(m+1) * (n+1) * (o+1)`. Since `o = m + n`, this is effectively O(m * n * (m+n)). Each state is computed only once.
*   **Space**: O(m * n * o) - For the memoization table `memo`. Similar to time complexity, this is O(m * n * (m+n)).

## Commented Code
```java
class Solution {
    // Declare variables to store lengths of s1, s2, and s3.
    int m,n,o;
    // Declare a 3D array for memoization. It stores Boolean results for subproblems.
    // memo[i][j][k] will store whether s3[k:] can be formed by interleaving s1[i:] and s2[j:].
    Boolean[][][] memo;

    // Main function to check if s3 is an interleaving of s1 and s2.
    public boolean isInterleave(String s1, String s2, String s3) {
        // Get the lengths of the input strings.
        m = s1.length();
        n = s2.length();
        o = s3.length();

        // If the combined length of s1 and s2 is not equal to the length of s3,
        // it's impossible for s3 to be an interleaving.
        if(m+n!=o) return false;

        // Initialize the memoization table with dimensions based on string lengths.
        // We use m+1, n+1, o+1 to handle the base cases where indices reach the end of strings.
        memo = new Boolean[m+1][n+1][o+1];

        // Start the recursive helper function from the beginning of all strings (indices 0, 0, 0).
        return func(0,0,0,s1,s2,s3);
    }

    // Recursive helper function with memoization.
    // i: current index in s1
    // j: current index in s2
    // k: current index in s3
    public boolean func(int i,int j, int k, String s1, String s2, String s3){
        // Base case: If we have successfully matched all characters of s3 (k has reached its end).
        if(k==o) return memo[i][j][k] = true; // Mark this state as true and return true.

        // Memoization check: If the result for this state (i, j, k) has already been computed, return it.
        if(memo[i][j][k] != null) return memo[i][j][k];

        // Recursive step 1: Try to match the current character of s3 with the current character of s1.
        // Check if i is within bounds of s1 AND s1's char at i matches s3's char at k.
        if(i<m && s1.charAt(i)==s3.charAt(k) && func(i+1,j,k+1,s1,s2,s3)) {
            // If a match is found and the rest of the strings can be interleaved,
            // store true in memo and return true.
            return memo[i][j][k] = true;
        }

        // Recursive step 2: Try to match the current character of s3 with the current character of s2.
        // Check if j is within bounds of s2 AND s2's char at j matches s3's char at k.
        if(j<n && s2.charAt(j)==s3.charAt(k) && func(i,j+1,k+1,s1,s2,s3)) {
            // If a match is found and the rest of the strings can be interleaved,
            // store true in memo and return true.
            return memo[i][j][k] = true;
        }

        // If neither s1 nor s2 could provide a matching character for s3 at index k,
        // then this path does not lead to a valid interleaving.
        // Store false in memo and return false.
        return memo[i][j][k] = false;
    }
}
```

## Interview Tips
1.  **Explain the State**: Clearly define what `i`, `j`, and `k` represent in your recursive function and how they form the state for memoization.
2.  **Handle Base Cases Carefully**: Emphasize the importance of the `k == o` base case and the initial length check.
3.  **Trace an Example**: Walk through a small example like `s1 = "aab"`, `s2 = "axy"`, `s3 = "aaxaby"` to illustrate how the recursion and memoization work.
4.  **Discuss DP vs. Memoization**: Be prepared to discuss how this problem can also be solved iteratively using a 2D DP table (where `dp[i][j]` represents if `s3[0...i+j-1]` is an interleaving of `s1[0...i-1]` and `s2[0...j-1]`). The 3D memoization is a direct translation of the recursive thought process.

## Revision Checklist
- [ ] Understand the problem statement: s3 is an interleaving of s1 and s2 if it contains all characters of s1 and s2, and the relative order of characters within s1 and s2 is preserved.
- [ ] Initial length check: `s1.length() + s2.length() == s3.length()`.
- [ ] Recursive approach: Consider matching `s3[k]` with `s1[i]` or `s2[j]`.
- [ ] Memoization state: `(i, j, k)` representing current indices in `s1`, `s2`, and `s3`.
- [ ] Base case: `k == s3.length()`.
- [ ] Transitions: If `s1[i] == s3[k]`, recurse on `(i+1, j, k+1)`. If `s2[j] == s3[k]`, recurse on `(i, j+1, k+1)`.
- [ ] Combine results: If either recursive call returns `true`, the current state is `true`.
- [ ] Complexity: Time O(m*n*o), Space O(m*n*o).
- [ ] Alternative DP approach (2D table).

## Similar Problems
*   Edit Distance
*   Longest Common Subsequence
*   Word Break
*   Regular Expression Matching

## Tags
`String` `Dynamic Programming` `Recursion` `Memoization`
