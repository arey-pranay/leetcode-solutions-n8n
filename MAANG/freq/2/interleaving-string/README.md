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
This problem can be solved using dynamic programming or recursion with memoization.

## Intuition
The core idea is to check if we can construct `s3` by picking characters from either `s1` or `s2` in order. At any point, if the current character of `s3` matches the current character of `s1`, we can try to match the rest of `s3` by advancing in `s1`. Similarly, if it matches `s2`, we can try advancing in `s2`. If either of these paths leads to a successful interleaving, then `s3` is an interleaving. The "aha moment" comes from realizing that overlapping subproblems exist: the decision at a certain point depends on whether the remaining parts of `s1`, `s2`, and `s3` can be interleaved. This suggests memoization or DP.

## Algorithm
1. **Length Check**: If the sum of lengths of `s1` and `s2` is not equal to the length of `s3`, return `false` immediately, as interleaving is impossible.
2. **Memoization Initialization**: Create a 3D memoization table (e.g., `Boolean[][][] memo`) of size `(m+1) x (n+1) x (o+1)` to store results of subproblems. Initialize all entries to `null`.
3. **Recursive Function `func(i, j, k)`**: This function will check if the substring `s3[k:]` can be formed by interleaving `s1[i:]` and `s2[j:]`.
    a. **Base Case**: If `k` reaches the end of `s3` (i.e., `k == o`), it means we have successfully interleaved all characters, so return `true`.
    b. **Memoization Check**: If `memo[i][j][k]` is not `null`, return the stored result.
    c. **Recursive Step 1 (Match s1)**: If `i` is within bounds of `s1` (`i < m`) AND the current character of `s1` (`s1.charAt(i)`) matches the current character of `s3` (`s3.charAt(k)`), recursively call `func(i+1, j, k+1)`. If this recursive call returns `true`, store `true` in `memo[i][j][k]` and return `true`.
    d. **Recursive Step 2 (Match s2)**: If `j` is within bounds of `s2` (`j < n`) AND the current character of `s2` (`s2.charAt(j)`) matches the current character of `s3` (`s3.charAt(k)`), recursively call `func(i, j+1, k+1)`. If this recursive call returns `true`, store `true` in `memo[i][j][k]` and return `true`.
    e. **No Match**: If neither of the above conditions leads to a `true` result, it means the current characters cannot be matched to form an interleaving. Store `false` in `memo[i][j][k]` and return `false`.
4. **Initial Call**: Call `func(0, 0, 0, s1, s2, s3)` to start the process.

## Concept to Remember
*   **Recursion with Memoization**: Efficiently solves problems with overlapping subproblems by storing and reusing results of previously computed subproblems.
*   **Dynamic Programming**: A broader technique that involves breaking down a problem into smaller, overlapping subproblems and solving each subproblem only once, storing their solutions.
*   **String Manipulation**: Understanding how to access and compare characters within strings.
*   **State Representation**: Defining the state of a subproblem (e.g., current indices in `s1`, `s2`, and `s3`) is crucial for DP/memoization.

## Common Mistakes
*   **Forgetting the Length Check**: Not verifying `s1.length() + s2.length() == s3.length()` upfront can lead to incorrect results or infinite recursion.
*   **Incorrect Base Case**: Not handling the case where `k` reaches the end of `s3` correctly can lead to errors.
*   **Off-by-One Errors in Indices**: Mismanaging the indices `i`, `j`, and `k` when accessing characters or making recursive calls.
*   **Not Storing Results in Memoization**: Failing to store the result of `func(i, j, k)` before returning can negate the benefits of memoization, leading to exponential time complexity.
*   **Incorrect State Definition**: Using a 2D memoization table when a 3D table is required to capture all necessary state information (indices in all three strings).

## Complexity Analysis
*   **Time**: O(m * n * o) - The memoization table has dimensions `(m+1) x (n+1) x (o+1)`. Each state `(i, j, k)` is computed at most once. In the worst case, `o` is `m+n`, so it's O(m * n * (m+n)). However, since `k` is always `i+j` in a valid interleaving, the state space is effectively O(m*n). The provided solution uses `o` in the memoization table, leading to O(m*n*o). A more optimized DP approach can achieve O(m*n).
*   **Space**: O(m * n * o) - For the memoization table. Similar to time complexity, a more optimized DP approach can reduce space to O(m*n) or even O(min(m,n)).

## Commented Code
```java
class Solution {
    // Declare instance variables for lengths of s1, s2, and s3
    int m,n,o;
    // Declare a 3D array for memoization. Stores Boolean results for states (i, j, k).
    // i: current index in s1, j: current index in s2, k: current index in s3
    Boolean[][][] memo;

    // Main function to check if s3 is an interleaving of s1 and s2
    public boolean isInterleave(String s1, String s2, String s3) {
        // Get the lengths of the input strings
        m = s1.length();
        n = s2.length();
        o = s3.length();

        // Initial check: if the combined length of s1 and s2 is not equal to s3's length,
        // it's impossible to form s3 by interleaving.
        if(m+n!=o) return false;

        // Initialize the memoization table with dimensions (m+1) x (n+1) x (o+1).
        // The +1 is to accommodate the base case where an index reaches the end of a string.
        memo = new Boolean[m+1][n+1][o+1];

        // Start the recursive helper function from the beginning of all strings (indices 0, 0, 0).
        return func(0,0,0,s1,s2,s3);
    }

    // Recursive helper function with memoization
    // i: current index in s1
    // j: current index in s2
    // k: current index in s3
    // s1, s2, s3: the input strings
    public boolean func(int i,int j, int k, String s1, String s2, String s3){
        // Base case: If we have successfully traversed through all characters of s3 (k reaches its length 'o'),
        // it means s3 is an interleaving. Store true in memo and return true.
        if(k==o) return memo[i][j][k] = true;

        // Memoization check: If the result for the current state (i, j, k) has already been computed,
        // return the stored result to avoid redundant calculations.
        if(memo[i][j][k] != null) return memo[i][j][k];

        // Recursive step 1: Try to match the current character of s3 with the current character of s1.
        // Check if 'i' is within bounds of s1 AND if s1.charAt(i) matches s3.charAt(k).
        // If they match, recursively call func advancing 'i' and 'k' by 1.
        // If the recursive call returns true, it means a valid interleaving is possible from this path.
        // Store true in memo[i][j][k] and return true.
        if(i<m && s1.charAt(i)==s3.charAt(k) && func(i+1,j,k+1,s1,s2,s3)) return memo[i][j][k] = true;

        // Recursive step 2: If the first path didn't work or wasn't taken, try to match the current character of s3
        // with the current character of s2.
        // Check if 'j' is within bounds of s2 AND if s2.charAt(j) matches s3.charAt(k).
        // If they match, recursively call func advancing 'j' and 'k' by 1.
        // If the recursive call returns true, it means a valid interleaving is possible from this path.
        // Store true in memo[i][j][k] and return true.
        if(j<n && s2.charAt(j)==s3.charAt(k) && func(i,j+1,k+1,s1,s2,s3)) return memo[i][j][k] = true;

        // If neither of the above recursive steps led to a successful interleaving,
        // it means s3 cannot be formed by interleaving s1 and s2 from the current state.
        // Store false in memo[i][j][k] and return false.
        return memo[i][j][k] = false;
    }
}
```

## Interview Tips
*   **Explain the State**: Clearly articulate what `i`, `j`, and `k` represent in your recursive function or DP table. This shows you understand the problem's state.
*   **Discuss Base Cases and Transitions**: Walk through how you handle the base case (reaching the end of `s3`) and the recursive/DP transitions (matching characters from `s1` or `s2`).
*   **Mention Optimization**: If you initially think of pure recursion, be prepared to discuss why memoization or DP is necessary to avoid exponential time complexity. Explain the overlapping subproblems.
*   **Consider Edge Cases**: Think about empty strings for `s1`, `s2`, or `s3`, and how your solution handles them. The length check at the beginning is crucial here.

## Revision Checklist
- [ ] Understand the problem statement: s3 is an interleaving of s1 and s2.
- [ ] Recognize overlapping subproblems.
- [ ] Implement recursion with memoization or a DP approach.
- [ ] Handle the length constraint `s1.length() + s2.length() == s3.length()`.
- [ ] Define the state correctly (indices in s1, s2, and s3).
- [ ] Implement base cases for recursion/DP.
- [ ] Implement transitions for matching characters from s1 or s2.
- [ ] Analyze time and space complexity.
- [ ] Test with edge cases (empty strings, identical strings, etc.).

## Similar Problems
*   Edit Distance
*   Longest Common Subsequence
*   Word Break
*   Regular Expression Matching

## Tags
`Dynamic Programming` `Recursion` `String` `Memoization`
