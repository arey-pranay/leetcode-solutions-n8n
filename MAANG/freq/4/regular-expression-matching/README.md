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
We solve it using dynamic programming with memoization, considering character matches and '*' wildcard behavior.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. We can think about matching the string `s` with the pattern `p` from left to right. At each step, we consider the current characters of `s` and `p`. The complexity arises from the `*` character, which can match zero or more of the preceding element.

If the next character in the pattern is `*`, we have two choices:
1. Treat the `*` as matching zero occurrences of the preceding character. In this case, we effectively skip the current pattern character and the `*` (i.e., move `j` by 2).
2. Treat the `*` as matching one or more occurrences of the preceding character. This is only possible if the current characters of `s` and `p` match. If they do, we consume one character from `s` (move `i` by 1) and stay at the same position in `p` (keep `j` the same) to allow for multiple matches of the preceding character.

If the next character in the pattern is NOT `*`, then the current characters of `s` and `p` must match. If they do, we move both `i` and `j` forward by 1. If they don't match, the overall match fails.

The base case is when we've exhausted the pattern `p`. If we've also exhausted the string `s` at the same time, it's a successful match.

Memoization is crucial to avoid redundant computations of the same subproblems.

## Algorithm
1. **Initialization**: Create a 2D memoization table `memo` of size `(s.length() + 1) x (p.length() + 1)` to store results of subproblems. Initialize all entries to `null`.
2. **Recursive Function `func(i, j, s, p)`**: This function checks if the substring `s[i:]` matches the subpattern `p[j:]`.
3. **Base Case**: If `j` reaches the end of the pattern `p` (`j == p.length()`), return `true` if `i` also reaches the end of the string `s` (`i == s.length()`), otherwise return `false`.
4. **Memoization Check**: If `memo[i][j]` is not `null`, return the stored result.
5. **Character Match**: Determine if the current characters `s[i]` and `p[j]` match. A match occurs if `i` is within bounds of `s` AND (`p[j]` is `.` OR `s[i]` equals `p[j]`). Store this in a boolean variable `matched`.
6. **Handle `*`**: If the next character in the pattern `p` is `*` (i.e., `j + 1 < p.length()` and `p.charAt(j+1) == '*'`)
    * If `matched` is true: The result is `func(i, j + 2, s, p)` (treat `*` as zero occurrences) OR `func(i + 1, j, s, p)` (treat `*` as one or more occurrences).
    * If `matched` is false: The result is `func(i, j + 2, s, p)` (the `*` must match zero occurrences as the preceding character doesn't match).
7. **Handle Normal Match**: If the next character in the pattern is NOT `*`:
    * If `matched` is true: The result is `func(i + 1, j + 1, s, p)`.
    * If `matched` is false: The result is `false`.
8. **Store and Return**: Store the computed result in `memo[i][j]` and return it.
9. **Initial Call**: Call `func(0, 0, s, p)` to start the matching process from the beginning of both strings.

## Concept to Remember
*   **Recursion with Memoization (Top-Down Dynamic Programming)**: Breaking down a problem into smaller, overlapping subproblems and storing their solutions to avoid recomputation.
*   **State Representation**: Defining the state of a subproblem (e.g., `(i, j)` representing the remaining parts of `s` and `p` to be matched).
*   **Wildcard Handling**: Understanding the specific rules for special characters like `.` (matches any single character) and `*` (matches zero or more of the preceding element).

## Common Mistakes
*   **Incorrect Base Cases**: Not properly handling the scenarios where one or both strings/patterns are exhausted.
*   **Off-by-One Errors**: Incorrectly managing indices `i` and `j`, especially when dealing with the `*` character and skipping pattern characters.
*   **Missing Memoization**: Failing to store and retrieve results from the memoization table, leading to exponential time complexity.
*   **Incorrect `*` Logic**: Not considering both the "zero occurrences" and "one or more occurrences" possibilities for the `*` wildcard.

## Complexity Analysis
- Time: O(S * P) - reason: Each state `(i, j)` in the `memo` table is computed at most once. There are `S+1` possible values for `i` and `P+1` possible values for `j`, where `S` is the length of string `s` and `P` is the length of pattern `p`.
- Space: O(S * P) - reason: The space is dominated by the memoization table `memo` which stores results for all possible `(i, j)` states. The recursion depth can also go up to `S+P` in the worst case, but this is usually less than the memo table size.

## Commented Code
```java
class Solution {
    // Declare a 2D array to store the results of subproblems (memoization).
    // It's of type Boolean to distinguish between 'false' result and 'not computed yet' (null).
    Boolean memo[][];

    // The main function that initiates the regular expression matching.
    public boolean isMatch(String s, String p) {
      // Initialize the memoization table with dimensions based on string and pattern lengths.
      // Add 1 to lengths to accommodate empty prefixes (base cases).
      memo = new Boolean[s.length()+1][p.length()+1];
      // Start the recursive matching process from the beginning of both strings (index 0, 0).
      return func(0,0,s,p);
    }

    // Recursive helper function to perform the matching.
    // i: current index in string s
    // j: current index in pattern p
    // s: the input string
    // p: the pattern string
    public boolean func(int i, int j, String s, String p){
      // Base Case 1: If we have reached the end of the pattern.
      // If we have also reached the end of the string, it's a match.
      if(j==p.length()) return i==s.length();

      // Memoization Check: If the result for this state (i, j) has already been computed, return it.
      if(memo[i][j] != null) return memo[i][j];

      // Check if the current characters match.
      // 'matched' is true if:
      // 1. We are within the bounds of string s (i < s.length())
      // 2. AND (the pattern character is '.' OR the string character matches the pattern character).
      boolean matched = i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j));

      // Handle the case where the next character in the pattern is '*'.
      // This means the current pattern character p.charAt(j) can appear zero or more times.
      if(j< p.length()-1 && p.charAt(j+1)=='*'){
        // If the current characters 'matched':
        // We have two possibilities:
        // 1. Treat '*' as matching zero occurrences of the preceding element: skip pattern character and '*' (j+2).
        //    This is represented by func(i, j+2, s, p).
        // 2. Treat '*' as matching one or more occurrences of the preceding element: consume string character (i+1)
        //    and stay at the same pattern character (j) to allow for multiple matches.
        //    This is represented by func(i+1, j, s, p).
        // The result is true if either of these possibilities leads to a match.
        if(matched) return memo[i][j] = func(i,j+2,s,p) || func(i+1,j,s,p);
        // If the current characters did NOT 'matched':
        // The '*' must match zero occurrences of the preceding element, because the preceding element doesn't match the current string character.
        // So, we must skip the pattern character and '*' (j+2).
        return memo[i][j] = func(i,j+2,s,p);
      }

      // Handle the case where the next character in the pattern is NOT '*'.
      // This is a simple character match.
      // If the current characters 'matched':
      // We must consume both the string character (i+1) and the pattern character (j+1) to continue matching.
      if(matched) return memo[i][j] = func(i+1,j+1,s,p);

      // If the current characters did NOT 'matched' and the next pattern character is not '*':
      // Then this path cannot lead to a match.
      return memo[i][j] = false;
    }
}
```

## Interview Tips
*   **Clarify Wildcard Behavior**: Ensure you fully understand the meaning of `.` and `*`. Specifically, `*` always refers to the *preceding* character and can match zero occurrences.
*   **Explain DP State**: Clearly articulate what `dp[i][j]` or `func(i, j)` represents. In this case, it's the match status of `s[i:]` and `p[j:]`.
*   **Walk Through Examples**: Use simple examples like `s = "aab", p = "c*a*b"` or `s = "mississippi", p = "mis*is*p*."` to illustrate your logic, especially the `*` handling.
*   **Discuss Base Cases and Transitions**: Be precise about the conditions for the base cases and how you transition from one subproblem to another.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the subproblems and define the DP state.
- [ ] Formulate the recurrence relation for both normal character matches and `*` wildcard.
- [ ] Implement the base cases correctly.
- [ ] Implement memoization to store and retrieve subproblem results.
- [ ] Test with edge cases (empty strings, patterns with only `*`, etc.).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Wildcard Matching (LeetCode 44)
*   Basic Calculator (LeetCode 224)
*   Expression Add Operators (LeetCode 282)

## Tags
`Dynamic Programming` `Recursion` `String Matching`
