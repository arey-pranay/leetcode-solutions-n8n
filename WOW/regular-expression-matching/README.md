# Regular Expression Matching

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Recursion`  
**Time:** O(S * P)  
**Space:** O(S * P)

---

## Solution (java)

```java
class Solution {
    Boolean memo[][];
    public boolean isMatch(String s, String p) {
      memo = new Boolean[s.length()+1][p.length()+1];
      return func(0,0,s,p);
    }
    public boolean func(int i, int j, String s, String p){
      if(j==p.length()) return i==s.length();
      if(memo[i][j] != null) return memo[i][j];
      boolean matched = i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j));
      if(j< p.length()-1 && p.charAt(j+1)=='*'){
        if(matched) return memo[i][j] = func(i,j+2,s,p) || func(i+1,j,s,p);
        return memo[i][j] = func(i,j+2,s,p);
      }
      if(matched) return memo[i][j] = func(i+1,j+1,s,p);
      return memo[i][j] = false;
    }
}
```

---

---
## Quick Revision
This problem asks to implement regular expression matching with support for '.' and '*'.
We solve it using dynamic programming with memoization to avoid redundant computations.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. We consider the current characters of the string `s` and the pattern `p`. The decision at each step depends on whether the current characters match and what the next character in the pattern is. If the next character is '*', it introduces two possibilities: either the '*' matches zero occurrences of the preceding element, or it matches one or more occurrences. This recursive structure with overlapping subproblems strongly suggests a dynamic programming approach. Memoization is crucial to store results of subproblems and prevent re-computation, making the solution efficient.

## Algorithm
1.  **Initialization**: Create a 2D memoization table `memo` of size `(s.length() + 1) x (p.length() + 1)` to store results of subproblems. Initialize all entries to `null`.
2.  **Recursive Function `func(i, j, s, p)`**: This function checks if the substring `s[i:]` matches the subpattern `p[j:]`.
    *   **Base Case 1**: If `j` reaches the end of the pattern `p` (`j == p.length()`), then the match is successful only if `i` also reaches the end of the string `s` (`i == s.length()`).
    *   **Memoization Check**: If `memo[i][j]` is not `null`, return the stored result.
    *   **Character Match**: Determine if the current characters `s[i]` and `p[j]` match. A match occurs if `i` is within bounds of `s` and either `p[j]` is '.' or `s[i]` equals `p[j]`. Let this be `matched`.
    *   **Handling '*'**: If the next character in the pattern `p` is '*' (`j + 1 < p.length() && p.charAt(j + 1) == '*'`) :
        *   If `matched` is true: The '*' can match zero or more occurrences.
            *   Option 1: '*' matches zero occurrences of `p[j]`. We move past `p[j]` and `p[j+1]` in the pattern: `func(i, j + 2, s, p)`.
            *   Option 2: '*' matches one or more occurrences of `p[j]`. We consume one character from `s` and stay at the same position in `p` (to allow for multiple matches): `func(i + 1, j, s, p)`.
            *   The result is `true` if either option leads to a match.
        *   If `matched` is false: The '*' must match zero occurrences of `p[j]`. We move past `p[j]` and `p[j+1]` in the pattern: `func(i, j + 2, s, p)`.
    *   **Handling Normal Characters/'.'**: If the next character is not '*' :
        *   If `matched` is true: We consume one character from both `s` and `p`: `func(i + 1, j + 1, s, p)`.
        *   If `matched` is false: No match is possible from this state.
    *   **Store and Return**: Store the computed result in `memo[i][j]` and return it.
3.  **Initial Call**: Call `func(0, 0, s, p)` to start the matching process from the beginning of both strings.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a problem into overlapping subproblems and storing their solutions.
*   **Recursion with Memoization**: A top-down DP approach where recursive calls store results to avoid re-computation.
*   **Backtracking/State Exploration**: The '*' character introduces branching possibilities (zero or more matches), requiring exploration of different paths.
*   **String Manipulation**: Understanding how to access characters and manage indices in strings.

## Common Mistakes
*   **Incorrect Base Cases**: Failing to handle the end of the string `s` or pattern `p` correctly, especially when `*` is involved.
*   **Off-by-One Errors**: Mismanaging string indices `i` and `j`, particularly when advancing them in recursive calls.
*   **Not Handling '*' Correctly**: Forgetting to consider both the "zero occurrences" and "one or more occurrences" cases for `*`.
*   **Missing Memoization**: Implementing a purely recursive solution without memoization, leading to exponential time complexity.
*   **Incorrect `matched` Logic**: Not properly checking if `i` is within bounds before accessing `s.charAt(i)`.

## Complexity Analysis
*   **Time**: O(S * P) - where S is the length of string `s` and P is the length of pattern `p`. Each state `(i, j)` in the DP table is computed at most once.
*   **Space**: O(S * P) - for the memoization table `memo`.

## Commented Code
```java
class Solution {
    // Declare a 2D array to store results of subproblems (memoization).
    // It stores Boolean values: true if a match is found, false otherwise, null if not computed yet.
    Boolean memo[][];

    // The main function that initiates the regular expression matching.
    public boolean isMatch(String s, String p) {
        // Initialize the memoization table with dimensions (s.length() + 1) x (p.length() + 1).
        // The +1 is to handle the base cases where either string or pattern is empty.
        memo = new Boolean[s.length() + 1][p.length() + 1];
        // Start the recursive matching process from the beginning of both strings (index 0).
        return func(0, 0, s, p);
    }

    // Recursive helper function to perform the matching.
    // i: current index in string s
    // j: current index in pattern p
    // s: the input string
    // p: the pattern string
    public boolean func(int i, int j, String s, String p) {
        // Base Case 1: If we have reached the end of the pattern.
        // The match is successful only if we have also reached the end of the string.
        if (j == p.length()) {
            return i == s.length();
        }

        // Memoization Check: If the result for this state (i, j) has already been computed, return it.
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // Check if the current characters match.
        // 'matched' is true if:
        // 1. We are within the bounds of string 's' (i < s.length()).
        // 2. AND (the pattern character is '.' OR the string character matches the pattern character).
        boolean matched = i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));

        // Handle the case where the next character in the pattern is '*'.
        // This means p.charAt(j) can match zero or more of the preceding element.
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            // If the current characters 'matched':
            // We have two possibilities for '*':
            // 1. '*' matches zero occurrences of p.charAt(j):
            //    We skip p.charAt(j) and p.charAt(j+1) in the pattern and continue matching from i in s.
            //    This is represented by func(i, j + 2, s, p).
            // 2. '*' matches one or more occurrences of p.charAt(j):
            //    We consume one character from 's' (i+1) and stay at the same position 'j' in the pattern.
            //    This allows '*' to match multiple characters in 's'.
            //    This is represented by func(i + 1, j, s, p).
            // The result is true if either of these possibilities leads to a match.
            if (matched) {
                return memo[i][j] = func(i, j + 2, s, p) || func(i + 1, j, s, p);
            }
            // If the current characters did NOT match:
            // The '*' must match zero occurrences of p.charAt(j).
            // So, we skip p.charAt(j) and p.charAt(j+1) in the pattern and continue matching from i in s.
            // This is represented by func(i, j + 2, s, s, p).
            return memo[i][j] = func(i, j + 2, s, p);
        }

        // Handle the case where the next character is NOT '*'.
        // If the current characters 'matched':
        // We consume one character from both 's' and 'p' and proceed to the next state.
        // This is represented by func(i + 1, j + 1, s, p).
        if (matched) {
            return memo[i][j] = func(i + 1, j + 1, s, p);
        }

        // If none of the above conditions lead to a match (i.e., current characters don't match and next is not '*'),
        // then this state does not lead to a match.
        // Store false in memo and return it.
        return memo[i][j] = false;
    }
}
```

## Interview Tips
1.  **Explain DP First**: Before diving into code, explain the intuition behind dynamic programming and why it's suitable for this problem (overlapping subproblems, optimal substructure).
2.  **Walk Through Examples**: Use simple examples like `s = "aa", p = "a"` or `s = "aab", p = "c*a*b"` to illustrate how the recursion and memoization work.
3.  **Clarify '*' Behavior**: Be very clear about the two main cases for `*`: matching zero elements and matching one or more elements. This is the trickiest part.
4.  **Discuss Edge Cases**: Mention how you handle empty strings, patterns with only `*`, and patterns ending with `*`.
5.  **Complexity Justification**: Be ready to explain the time and space complexity clearly, referencing the DP table size.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the role of '.' and '*'.
- [ ] Recognize the applicability of Dynamic Programming.
- [ ] Formulate the recursive relation for `dp(i, j)`.
- [ ] Define the base cases correctly.
- [ ] Implement memoization to store computed subproblem results.
- [ ] Handle the '*' character's dual nature (zero or more matches).
- [ ] Trace execution with simple examples.
- [ ] Analyze time and space complexity.

## Similar Problems
Regular Expression Matching (LeetCode 10)
Wildcard Matching (LeetCode 44)

## Tags
`Dynamic Programming` `Recursion` `String`
