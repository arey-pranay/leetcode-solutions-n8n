# Wildcard Matching

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Greedy` `Recursion`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
// class Solution {
//     public boolean isMatch(String s, String p) {
//         int i = 0;
//         int j = 0;
//         int star = -1;
//         int match = 0;

//         while (i < s.length()) {
//             if (j < p.length() &&(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {i++;j++;} //match curr chars
//             else if (j < p.length() && p.charAt(j) == '*') { match = i; star = j++; }  //
//             else if (star != -1) { i = match++; j = star + 1; } 
//             else return false;
//         }
//         while (j < p.length() && p.charAt(j) == '*') j++;
//         return j == p.length();
//     }
// }




class Solution {
    String s,p;
    int m,n;
    Boolean[][] memo;
    boolean[] stars;
    public boolean isMatch(String S, String P) {
       s=S;p=P;m=S.length();n=P.length();
       memo = new Boolean[m][n];
       stars = new boolean[n+1];
       stars[n] = true;
       for(int i=n-1;i>=0;i--) stars[i] = stars[i+1] && p.charAt(i)=='*';
       return func(0,0);
    }
    private boolean func(int i, int j){
        if(i==m) return stars[j];
        if(j==n) return false;
        if(memo[i][j]!=null)return memo[i][j];
        if(p.charAt(j)=='*') return memo[i][j] = func(i+1,j) || func(i,j+1);
        if(p.charAt(j)=='?' || p.charAt(j)==s.charAt(i)) return memo[i][j] = func(i+1,j+1);
        return memo[i][j] = false;
    }
}
```

---

---
## Quick Revision
This problem asks to implement wildcard pattern matching with support for '?' and '*'.
The solution uses dynamic programming with memoization to efficiently check for matches.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. For any given characters in the string `s` and pattern `p`, we can determine if they match based on the characters themselves and the results of matching the remaining substrings. The '*' character introduces a branching logic: it can match zero or more characters. This suggests a recursive structure where we explore different possibilities for '*'. Memoization is crucial to avoid redundant computations for the same subproblems.

## Algorithm
1. **Initialization**:
   - Store the input string `s` and pattern `p` in class variables.
   - Get the lengths of `s` and `p` and store them in `m` and `n` respectively.
   - Initialize a 2D array `memo` of `Boolean` type with dimensions `m x n` to store results of subproblems. This will be used for memoization.
   - Initialize a boolean array `stars` of size `n+1`. `stars[j]` will be true if the suffix of `p` starting from index `j` consists only of '*' characters.
   - Set `stars[n]` to `true` (empty suffix is considered valid).
   - Iterate from `n-1` down to `0`: `stars[i]` is true if `p.charAt(i)` is '*' AND `stars[i+1]` is true. This pre-computes whether a suffix of the pattern is all stars.

2. **Recursive Function `func(i, j)`**: This function checks if the substring `s[i:]` matches the sub-pattern `p[j:]`.
   - **Base Case 1**: If `i == m` (end of string `s` reached), return `stars[j]`. This means if the rest of the pattern `p[j:]` is all '*' characters, then it's a match.
   - **Base Case 2**: If `j == n` (end of pattern `p` reached), return `false` (since `i` is not yet `m`, there are remaining characters in `s` that cannot be matched).
   - **Memoization Check**: If `memo[i][j]` is not null, return the stored result.
   - **Wildcard '*'**: If `p.charAt(j)` is '*':
     - The '*' can match zero characters: `func(i, j+1)` (move to the next character in pattern, stay at current string character).
     - The '*' can match one or more characters: `func(i+1, j)` (move to the next character in string, stay at current pattern character, effectively consuming one character from `s` with '*').
     - The result is `true` if either of these possibilities leads to a match. Store this result in `memo[i][j]`.
   - **Wildcard '?' or Exact Match**: If `p.charAt(j)` is '?' OR `p.charAt(j)` is equal to `s.charAt(i)`:
     - This is a match for the current characters. Recursively call `func(i+1, j+1)` to check the rest of the strings. Store this result in `memo[i][j]`.
   - **No Match**: If none of the above conditions are met, it means the current characters do not match and `p.charAt(j)` is not a wildcard that can handle it. Return `false` and store it in `memo[i][j]`.

3. **Initial Call**: Call `func(0, 0)` to start the matching process from the beginning of both the string and the pattern.

## Concept to Remember
*   **Dynamic Programming (DP)**: Breaking down a complex problem into simpler, overlapping subproblems and storing their solutions to avoid recomputation.
*   **Recursion with Memoization**: A top-down approach to DP where recursive calls are used, and results are cached.
*   **Wildcard Matching Logic**: Understanding how '?' and '*' characters affect the matching process, especially the multiple possibilities introduced by '*'.

## Common Mistakes
*   **Incorrect Base Cases**: Mishandling the scenarios where either the string or the pattern (or both) are exhausted.
*   **Infinite Recursion with '*'**: Not correctly handling the two possibilities for '*' (matching zero characters vs. matching one or more characters), which can lead to infinite loops.
*   **Inefficient State Transitions**: Forgetting to store results in the memoization table, leading to exponential time complexity.
*   **Off-by-One Errors**: Incorrectly indexing into the string or pattern, especially when dealing with the '*' character's ability to consume multiple characters.

## Complexity Analysis
- Time: O(m * n) - The DP table has `m * n` states, and each state is computed once. The pre-computation of `stars` takes O(n) time.
- Space: O(m * n) - For the memoization table `memo`. The `stars` array takes O(n) space.

## Commented Code
```java
class Solution {
    // Class variables to store the input string, pattern, and their lengths.
    String s,p;
    int m,n;
    // Memoization table to store results of subproblems. memo[i][j] stores if s[i:] matches p[j:].
    Boolean[][] memo;
    // Boolean array to efficiently check if a suffix of the pattern consists only of '*'.
    boolean[] stars;

    // Main function to initiate the wildcard matching.
    public boolean isMatch(String S, String P) {
       // Assign input strings and their lengths to class variables.
       s=S;p=P;m=S.length();n=P.length();
       // Initialize the memoization table with null values.
       memo = new Boolean[m][n];
       // Initialize the stars array. stars[j] will be true if p[j:] is all '*'.
       stars = new boolean[n+1];
       // The suffix of an empty pattern (after the last character) is considered valid (all stars).
       stars[n] = true;
       // Pre-compute the stars array from right to left.
       // stars[i] is true if p.charAt(i) is '*' AND the rest of the pattern (from i+1) is also all stars.
       for(int i=n-1;i>=0;i--) stars[i] = stars[i+1] && p.charAt(i)=='*';
       // Start the recursive matching process from the beginning of both string and pattern (index 0, 0).
       return func(0,0);
    }

    // Recursive helper function with memoization.
    // i: current index in string s
    // j: current index in pattern p
    private boolean func(int i, int j){
        // Base Case 1: If we have reached the end of the string 's'.
        // We need to check if the remaining pattern 'p' consists only of '*' characters.
        if(i==m) return stars[j];
        // Base Case 2: If we have reached the end of the pattern 'p' but not the end of string 's'.
        // This means there are unmatched characters in 's', so it's not a match.
        if(j==n) return false;
        // Memoization Check: If the result for this subproblem (i, j) has already been computed, return it.
        if(memo[i][j]!=null)return memo[i][j];

        // Case 1: Current pattern character is '*'.
        if(p.charAt(j)=='*')
            // '*' can match zero characters (move to next pattern char: func(i, j+1))
            // OR '*' can match one or more characters (move to next string char: func(i+1, j)).
            // The result is true if either of these possibilities leads to a match.
            return memo[i][j] = func(i+1,j) || func(i,j+1);

        // Case 2: Current pattern character is '?' or matches the current string character.
        if(p.charAt(j)=='?' || p.charAt(j)==s.charAt(i))
            // If they match, move to the next character in both string and pattern.
            return memo[i][j] = func(i+1,j+1);

        // Case 3: Current characters do not match and pattern character is not a wildcard that can handle it.
        // This is not a match.
        return memo[i][j] = false;
    }
}
```

## Interview Tips
*   **Clarify Wildcard Meanings**: Ensure you understand the exact behavior of '?' (matches any single character) and '*' (matches any sequence of characters, including an empty sequence).
*   **Start with a Simpler Approach**: If DP is not immediately obvious, consider a brute-force recursive solution first. Then, identify overlapping subproblems to introduce memoization.
*   **Trace with Examples**: Walk through a few examples, especially edge cases like empty strings/patterns, patterns with only '*', and patterns with consecutive '*'. This helps solidify your understanding and identify potential bugs.
*   **Explain the DP State**: Clearly articulate what `dp[i][j]` (or `memo[i][j]` in this case) represents. For this problem, it's the result of matching `s[i:]` with `p[j:]`.

## Revision Checklist
- [ ] Understand the problem statement and wildcard definitions.
- [ ] Recognize the overlapping subproblems suitable for DP.
- [ ] Implement the recursive relation for '?' and exact matches.
- [ ] Implement the recursive relation for '*' (zero or more matches).
- [ ] Implement memoization to store and retrieve subproblem results.
- [ ] Handle base cases correctly (end of string, end of pattern).
- [ ] Consider the pre-computation of `stars` for optimization.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Regular Expression Matching (LeetCode 10)
*   String Matching in a Tree (LeetCode 1392 - conceptually related to pattern matching)

## Tags
`Dynamic Programming` `Recursion` `Memoization` `String`
