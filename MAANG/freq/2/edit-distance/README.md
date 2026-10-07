# Edit Distance

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        // last se start kyuki left to right jaane me insert ya delete krne se indices move hojayege, messy.
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        return func(word1, word2, m-1, n-1);
    }
    public int func(String word1, String word2, int i, int j){
        if(i<0) return j+1; //word1 khtm hua, to word2 ke saare char delete
        if(j<0) return i+1; //word2khtm hua, to word1 ke saare char delete
        
        if(memo[i][j] != -1) return memo[i][j];
        if(word1.charAt(i)==word2.charAt(j)) return memo[i][j] = func(word1,word2, i-1, j-1); // both moved one index, no op done
        
        int replace = 1 + func(word1,word2, i-1, j-1);
        
        // delete and insert practically mean the same thing, just for different strings
        int delete = 1 + func(word1, word2, i-1,j);                         // or (i-1,j)
        int insert = 1 + func(word1, word2, i,j-1);                         // or (j-1,i)
        
        return memo[i][j] = Math.min(delete,Math.min(insert,replace));
    }
}
```

---

---
## Quick Revision
This problem asks for the minimum number of operations (insert, delete, replace) to transform one string into another. It can be solved using dynamic programming or recursion with memoization.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. If the last characters of both strings match, we don't need an operation for them and can move to the preceding characters. If they don't match, we have three choices: replace the last character of `word1` with the last character of `word2`, delete the last character of `word1`, or insert the last character of `word2` into `word1`. We choose the operation that results in the minimum total operations for the remaining substrings. Working backward from the end of the strings simplifies index management.

## Algorithm
1. Initialize a 2D array `memo` of size `m x n` (where `m` and `n` are lengths of `word1` and `word2`) to store results of subproblems, initialized with -1.
2. Define a recursive function `func(word1, word2, i, j)` that calculates the minimum edit distance between `word1[0...i]` and `word2[0...j]`.
3. Base Cases:
    * If `i < 0` (meaning `word1` is exhausted), we need `j + 1` insertions to match the remaining characters of `word2`.
    * If `j < 0` (meaning `word2` is exhausted), we need `i + 1` deletions to match the remaining characters of `word1`.
4. Memoization Check: If `memo[i][j]` is not -1, return the stored value.
5. If `word1.charAt(i) == word2.charAt(j)`: The characters match, so no operation is needed for these characters. Recursively call `func(word1, word2, i-1, j-1)` and store the result in `memo[i][j]`.
6. If `word1.charAt(i) != word2.charAt(j)`: Consider the three possible operations:
    * **Replace:** `1 + func(word1, word2, i-1, j-1)` (cost of 1 for replacement + edit distance of remaining strings).
    * **Delete:** `1 + func(word1, word2, i-1, j)` (cost of 1 for deletion + edit distance of `word1` without its last char and `word2`).
    * **Insert:** `1 + func(word1, word2, i, j-1)` (cost of 1 for insertion + edit distance of `word1` and `word2` without its last char).
7. Store the minimum of these three operations in `memo[i][j]` and return it.
8. The initial call will be `func(word1, word2, m-1, n-1)`.

## Concept to Remember
*   **Dynamic Programming:** Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Recursion with Memoization:** A top-down approach to DP where recursive calls are augmented with a cache (memoization table).
*   **String Manipulation:** Understanding character-by-character comparison and operations.
*   **Minimization Problems:** Finding the optimal solution by exploring all valid choices and selecting the best one.

## Common Mistakes
*   Incorrectly handling base cases where one string is empty.
*   Off-by-one errors in array indexing or recursive calls, especially when working from the end of strings.
*   Forgetting to add the cost of the current operation (1) when making recursive calls for non-matching characters.
*   Not initializing the memoization table correctly or not checking it before computation.
*   Confusing the roles of `i` and `j` in the `delete` and `insert` operations.

## Complexity Analysis
- Time: O(m*n) - reason: Each state `(i, j)` is computed only once due to memoization. There are `m * n` such states.
- Space: O(m*n) - reason: For the memoization table `memo` of size `m x n`. The recursion depth can also go up to `m + n` in the worst case, contributing to the call stack space.

## Commented Code
```java
class Solution {
    // Declare a 2D array for memoization to store results of subproblems.
    int[][] memo;

    // Main function to calculate the minimum edit distance between word1 and word2.
    public int minDistance(String word1, String word2) {
        // Get the lengths of the two input strings.
        int m = word1.length(), n = word2.length();
        // Initialize the memoization table with dimensions m x n.
        memo = new int[m][n];
        // Fill the memoization table with -1 to indicate that no subproblem has been solved yet.
        for(int[] temp : memo)Arrays.fill(temp,-1);
        // Start the recursive calculation from the end of both strings (m-1, n-1).
        // We start from the end because it simplifies handling insertions/deletions without index shifts.
        return func(word1, word2, m-1, n-1);
    }

    // Recursive helper function to compute the edit distance.
    // i: current index in word1 (from the end)
    // j: current index in word2 (from the end)
    public int func(String word1, String word2, int i, int j){
        // Base case: If word1 is exhausted (i < 0), we need to insert all remaining characters of word2.
        // The number of insertions needed is j + 1 (since j is 0-indexed from the end).
        if(i<0) return j+1;
        // Base case: If word2 is exhausted (j < 0), we need to delete all remaining characters of word1.
        // The number of deletions needed is i + 1 (since i is 0-indexed from the end).
        if(j<0) return i+1;

        // Memoization check: If the result for this subproblem (i, j) is already computed, return it.
        if(memo[i][j] != -1) return memo[i][j];

        // If the characters at the current indices of both strings match:
        if(word1.charAt(i)==word2.charAt(j))
            // No operation is needed for these matching characters.
            // Move to the previous characters in both strings and store the result.
            return memo[i][j] = func(word1,word2, i-1, j-1);

        // If the characters do not match, we have three possible operations:

        // 1. Replace operation:
        // Cost is 1 (for replacement) + the edit distance of the remaining strings (i-1, j-1).
        int replace = 1 + func(word1,word2, i-1, j-1);

        // 2. Delete operation (from word1):
        // Cost is 1 (for deletion) + the edit distance of word1[0...i-1] and word2[0...j].
        int delete = 1 + func(word1, word2, i-1,j);

        // 3. Insert operation (into word1, effectively deleting from word2's perspective):
        // Cost is 1 (for insertion) + the edit distance of word1[0...i] and word2[0...j-1].
        int insert = 1 + func(word1, word2, i,j-1);

        // Store the minimum of the three operations in the memoization table for (i, j) and return it.
        return memo[i][j] = Math.min(delete,Math.min(insert,replace));
    }
}
```

## Interview Tips
*   Clearly explain the recursive relation and the meaning of each operation (insert, delete, replace).
*   Walk through a small example (e.g., "horse" to "ros") to illustrate the DP states and transitions.
*   Discuss the time and space complexity and how memoization optimizes it.
*   Be prepared to convert the recursive solution to an iterative DP solution if asked.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the subproblems and the recursive relation.
- [ ] Implement base cases correctly.
- [ ] Use memoization to store and retrieve subproblem solutions.
- [ ] Analyze time and space complexity.
- [ ] Practice converting recursive to iterative DP.

## Similar Problems
*   Longest Common Subsequence
*   Longest Common Substring
*   Word Break
*   Palindrome Partitioning II

## Tags
`Dynamic Programming` `Recursion` `String`
