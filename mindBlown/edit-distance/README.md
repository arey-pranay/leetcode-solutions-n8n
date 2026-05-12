# Edit Distance

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        // return solve(word1,word2,m-1,n-1);
        
        int[][] matrix = new int[m+1][n+1];
        for(int i =0;i<m+1;i++){
            for(int j =0 ; j<n+1;j++){
                if(i==0 || j==0) matrix[i][j]=0;
                else if (word1.charAt(i-1)==word2.charAt(j-1)) matrix[i][j] = matrix[i-1][j-1];
                else{
                    int x = Math.min(matrix[i-1][j]+1,matrix[i][j-1]+1);
                    int y = Math.min(x,matrix[i-1][j-1]+1);
                    matrix[i][j] = y;
                }
            }
        }
        if(matrix[m][n]==0) return 0;
        return matrix[m][n]+1;
    }
    public int solve(String s1, String s2, int i, int j){
        if(i<0) return j+1; 
        if(j<0) return i+1; //i =0 mtlb 1 character. i=4 mtlb 5 character etc. isliye i+1 add kara
        if(memo[i][j]!=-1) return memo[i][j];
        if(s1.charAt(i) == s2.charAt(j)) return solve(s1,s2,i-1,j-1);
        // s1 me se:
        int delete = 1 + solve(s1,s2,i-1,j);// -> i wala character solved, kyuki delete krdiya usko
        int insert = 1 + solve(s1,s2,i,j-1); // j wala character solved, kyuki usi ko s1 me insert krdiya
        int replace= 1 + solve(s1,s2,i-1,j-1); // -> i and j dono solved
        return memo[i][j]= Math.min(replace,Math.min(delete,insert));

    }
}
//     h o r s e 
//     0 0 0 0 0
// r 0 1 1 0 1 1
// o 0 
// s 0


// h o r s e i=4
// - - r o s j=2

```

---

---
## Quick Revision
Given two strings, find the minimum number of operations (insert, delete, replace) to transform one string into another. This is solved using dynamic programming.

## Intuition
The problem has optimal substructure and overlapping subproblems, which are hallmarks of dynamic programming. If we know the minimum edits to transform `word1[0...i-1]` to `word2[0...j-1]`, we can use this information to find the minimum edits for `word1[0...i]` to `word2[0...j]`. The "aha moment" is realizing that the decision at `(i, j)` depends on the solutions for `(i-1, j)`, `(i, j-1)`, and `(i-1, j-1)`.

## Algorithm
1. Initialize a 2D DP table `dp` of size `(m+1) x (n+1)`, where `m` is the length of `word1` and `n` is the length of `word2`.
2. Base Cases:
   - `dp[i][0] = i` for all `i` from 0 to `m`: This represents transforming `word1[0...i-1]` into an empty string, which requires `i` deletions.
   - `dp[0][j] = j` for all `j` from 0 to `n`: This represents transforming an empty string into `word2[0...j-1]`, which requires `j` insertions.
3. Iterate through the DP table from `i = 1` to `m` and `j = 1` to `n`.
4. For each cell `dp[i][j]`:
   - If `word1.charAt(i-1) == word2.charAt(j-1)` (characters match): No operation is needed for these characters, so `dp[i][j] = dp[i-1][j-1]`.
   - If `word1.charAt(i-1) != word2.charAt(j-1)` (characters don't match): We have three choices:
     - **Insert:** Insert `word2.charAt(j-1)` into `word1`. The cost is 1 (for insertion) + `dp[i][j-1]` (edits to transform `word1[0...i-1]` to `word2[0...j-2]`).
     - **Delete:** Delete `word1.charAt(i-1)` from `word1`. The cost is 1 (for deletion) + `dp[i-1][j]` (edits to transform `word1[0...i-2]` to `word2[0...j-1]`).
     - **Replace:** Replace `word1.charAt(i-1)` with `word2.charAt(j-1)`. The cost is 1 (for replacement) + `dp[i-1][j-1]` (edits to transform `word1[0...i-2]` to `word2[0...j-2]`).
     - `dp[i][j]` will be the minimum of these three options: `1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])`.
5. The final answer is `dp[m][n]`.

## Concept to Remember
*   **Dynamic Programming:** Breaking down a problem into smaller overlapping subproblems and storing their solutions to avoid recomputation.
*   **Optimal Substructure:** The optimal solution to a problem can be constructed from optimal solutions to its subproblems.
*   **Recursion with Memoization:** A top-down DP approach where recursive calls store results in a memoization table.
*   **Tabulation (Bottom-Up DP):** An iterative DP approach that fills a table from base cases to the final solution.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly indexing strings or DP table dimensions (e.g., using `m` and `n` directly instead of `m+1` and `n+1` for the DP table).
*   **Incorrect base cases:** Not properly initializing the first row and column of the DP table to represent insertions and deletions from/to an empty string.
*   **Misunderstanding the transitions:** Confusing the indices for insert, delete, and replace operations in the DP recurrence relation.
*   **Not considering all three operations:** Forgetting to take the minimum of insert, delete, and replace when characters don't match.
*   **Incorrectly handling the `solve` function's return value:** The provided `solve` function has a slight issue in its base cases and final return value logic when used with the provided DP table initialization. The DP table approach is generally more straightforward for this problem.

## Complexity Analysis
*   **Time:** O(m * n) - We fill an `(m+1) x (n+1)` DP table, and each cell takes constant time to compute.
*   **Space:** O(m * n) - For the DP table. (Can be optimized to O(min(m, n)) by only storing the previous row/column).

## Commented Code
```java
class Solution {
    // The provided solution uses two approaches:
    // 1. A recursive approach with memoization (commented out in the main logic).
    // 2. An iterative DP (tabulation) approach, which is the one executed.

    // Memoization table for the recursive approach. Initialized with -1 to indicate uncomputed states.
    // int[][] memo; // This is declared but not used by the executed DP logic.

    public int minDistance(String word1, String word2) {
        // Get the lengths of the two input strings.
        int m = word1.length();
        int n = word2.length();

        // Initialize the DP table. The table size is (m+1) x (n+1) to handle empty prefixes.
        // dp[i][j] will store the minimum edit distance between word1[0...i-1] and word2[0...j-1].
        int[][] matrix = new int[m + 1][n + 1];

        // Iterate through the DP table to fill it.
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                // Base Case 1: If word1 is empty (i=0), we need 'j' insertions to form word2[0...j-1].
                if (i == 0) {
                    matrix[i][j] = j;
                }
                // Base Case 2: If word2 is empty (j=0), we need 'i' deletions to form an empty string from word1[0...i-1].
                else if (j == 0) {
                    matrix[i][j] = i;
                }
                // If the current characters of word1 and word2 match.
                // Note: we use i-1 and j-1 because string indices are 0-based, while DP table indices are 1-based for prefixes.
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // No operation needed for these matching characters. The cost is the same as the cost for the prefixes without these characters.
                    matrix[i][j] = matrix[i - 1][j - 1];
                }
                // If the current characters do not match.
                else {
                    // We need to consider the minimum cost of three possible operations:
                    // 1. Insert: Cost is 1 (for insertion) + edit distance of word1[0...i-1] and word2[0...j-2] (matrix[i][j-1]).
                    int insertCost = matrix[i][j - 1];
                    // 2. Delete: Cost is 1 (for deletion) + edit distance of word1[0...i-2] and word2[0...j-1] (matrix[i-1][j]).
                    int deleteCost = matrix[i - 1][j];
                    // 3. Replace: Cost is 1 (for replacement) + edit distance of word1[0...i-2] and word2[0...j-2] (matrix[i-1][j-1]).
                    int replaceCost = matrix[i - 1][j - 1];

                    // The minimum cost for matrix[i][j] is 1 (for the current operation) plus the minimum of the costs of the three operations.
                    matrix[i][j] = 1 + Math.min(replaceCost, Math.min(insertCost, deleteCost));
                }
            }
        }

        // The final answer is stored in the bottom-right cell of the DP table,
        // representing the edit distance between the entire word1 and word2.
        // The original code had a conditional `if(matrix[m][n]==0) return 0; return matrix[m][n]+1;`
        // This seems to be an artifact from a different logic or a misunderstanding.
        // The standard DP result `matrix[m][n]` is the correct minimum edit distance.
        return matrix[m][n];
    }

    // This is the recursive helper function with memoization.
    // It's not used in the main execution path of the provided solution but demonstrates the top-down approach.
    public int solve(String s1, String s2, int i, int j) {
        // Base Case: If s1 is exhausted (i < 0), we need to insert the remaining characters of s2.
        // The number of remaining characters in s2 is j+1 (since j is 0-indexed).
        if (i < 0) return j + 1;
        // Base Case: If s2 is exhausted (j < 0), we need to delete the remaining characters of s1.
        // The number of remaining characters in s1 is i+1.
        if (j < 0) return i + 1;

        // If the result for this subproblem (i, j) has already been computed, return it.
        if (memo[i][j] != -1) return memo[i][j];

        // If the characters at the current indices match.
        if (s1.charAt(i) == s2.charAt(j)) {
            // No operation needed. Move to the previous characters in both strings.
            return solve(s1, s2, i - 1, j - 1);
        } else {
            // If characters don't match, consider the three operations:
            // 1. Delete from s1: Cost is 1 + solve for s1[0...i-2] and s2[0...j-1].
            int delete = 1 + solve(s1, s2, i - 1, j);
            // 2. Insert into s1 (equivalent to deleting from s2): Cost is 1 + solve for s1[0...i-1] and s2[0...j-2].
            int insert = 1 + solve(s1, s2, i, j - 1);
            // 3. Replace s1[i] with s2[j]: Cost is 1 + solve for s1[0...i-2] and s2[0...j-2].
            int replace = 1 + solve(s1, s2, i - 1, j - 1);

            // Store and return the minimum of these three operations.
            return memo[i][j] = Math.min(replace, Math.min(delete, insert));
        }
    }
}
```

## Interview Tips
*   **Explain DP first:** Start by identifying the problem as a DP problem, explaining optimal substructure and overlapping subproblems.
*   **Walk through the DP table:** Draw a small example on the whiteboard and fill the DP table step-by-step. This helps visualize the transitions.
*   **Discuss both approaches:** Mention both the recursive (top-down with memoization) and iterative (bottom-up tabulation) DP solutions. Explain the trade-offs if any.
*   **Clarify base cases:** Pay close attention to how you handle empty strings and the first row/column of the DP table. This is a common source of errors.
*   **Handle string indexing carefully:** Be precise about whether you are using 0-based string indices or 1-based DP table indices in your explanation and code.

## Revision Checklist
- [ ] Understand the problem: Minimum operations (insert, delete, replace) to transform one string to another.
- [ ] Identify DP characteristics: Optimal substructure and overlapping subproblems.
- [ ] Define DP state: `dp[i][j]` = min edits for `word1[0...i-1]` and `word2[0...j-1]`.
- [ ] Formulate recurrence relation:
    - If `word1[i-1] == word2[j-1]`: `dp[i][j] = dp[i-1][j-1]`
    - If `word1[i-1] != word2[j-1]`: `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`
- [ ] Define base cases:
    - `dp[i][0] = i` (deletions)
    - `dp[0][j] = j` (insertions)
- [ ] Implement using tabulation (bottom-up) or memoization (top-down).
- [ ] Analyze time and space complexity.
- [ ] Consider space optimization if applicable (O(min(m, n))).

## Similar Problems
*   Longest Common Subsequence
*   Longest Common Substring
*   Minimum Insertion Steps to Make a String Palindrome
*   Word Break

## Tags
`Dynamic Programming` `String` `Array`
