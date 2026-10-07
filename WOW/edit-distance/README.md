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
This problem asks for the minimum number of operations (insert, delete, replace) to transform one string into another.
It can be solved using dynamic programming with memoization or tabulation.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. If we consider the last characters of `word1` and `word2`, we have a few choices:
1. If the last characters match, we don't need any operation for them, and we move to the preceding characters.
2. If they don't match, we can either:
   - **Replace** the last character of `word1` with the last character of `word2`. This costs 1 operation, and we then solve for the strings excluding their last characters.
   - **Delete** the last character of `word1`. This costs 1 operation, and we then solve for `word1` (excluding its last char) and `word2`.
   - **Insert** the last character of `word2` into `word1`. This costs 1 operation, and we then solve for `word1` and `word2` (excluding its last char).

We want the minimum of these options. Working backward from the end of the strings simplifies index management, as insertions/deletions don't shift the indices of characters we've already considered.

## Algorithm
1. **Initialization**: Create a 2D array `memo` of size `m x n` (where `m` and `n` are lengths of `word1` and `word2` respectively) to store results of subproblems. Initialize all entries to -1 to indicate they haven't been computed yet.
2. **Recursive Function `func(word1, word2, i, j)`**: This function calculates the minimum edit distance between `word1[0...i]` and `word2[0...j]`.
3. **Base Cases**:
   - If `i < 0` (meaning `word1` is exhausted), we need to insert all remaining characters of `word2` (from index 0 to `j`). The cost is `j + 1`.
   - If `j < 0` (meaning `word2` is exhausted), we need to delete all remaining characters of `word1` (from index 0 to `i`). The cost is `i + 1`.
4. **Memoization Check**: If `memo[i][j]` is not -1, return the stored value.
5. **Character Match**: If `word1.charAt(i) == word2.charAt(j)`, the last characters match. No operation is needed for these characters. Recursively call `func` for `i-1` and `j-1` and store the result in `memo[i][j]`.
6. **Character Mismatch**: If `word1.charAt(i) != word2.charAt(j)`:
   - **Replace**: Calculate `1 + func(word1, word2, i-1, j-1)`.
   - **Delete**: Calculate `1 + func(word1, word2, i-1, j)`.
   - **Insert**: Calculate `1 + func(word1, word2, i, j-1)`.
   - Take the minimum of these three options, store it in `memo[i][j]`, and return it.
7. **Initial Call**: Call `func(word1, word2, m-1, n-1)` to get the final answer.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Recursion with Memoization**: A top-down DP approach where recursive calls store results in a lookup table.
*   **String Manipulation**: Understanding how operations like insertion, deletion, and replacement affect string indices.
*   **Minimization Problem**: Finding the smallest value among several possible outcomes.

## Common Mistakes
*   **Off-by-one errors in base cases**: Incorrectly calculating the cost when one string is exhausted.
*   **Incorrect recursive calls for operations**: Mixing up `i-1, j-1`, `i-1, j`, and `i, j-1` for replace, delete, and insert.
*   **Not handling memoization correctly**: Forgetting to check `memo` before computation or not storing the result after computation.
*   **Index out of bounds**: Accessing `word1.charAt(i)` or `word2.charAt(j)` when `i` or `j` are negative.
*   **Inefficient approach**: Trying to solve it greedily or with brute force without DP.

## Complexity Analysis
*   **Time**: O(m * n) - Each state `(i, j)` is computed only once due to memoization. There are `m * n` such states.
*   **Space**: O(m * n) - For the `memo` table. The recursion depth can also go up to `m + n` in the worst case, contributing to the call stack space, but the memo table dominates.

## Commented Code
```java
class Solution {
    // Declare a 2D array for memoization to store results of subproblems.
    int[][] memo;

    // Main function to calculate the minimum edit distance.
    public int minDistance(String word1, String word2) {
        // Get the lengths of the two input strings.
        int m = word1.length(), n = word2.length();
        // Initialize the memoization table with dimensions m x n.
        memo = new int[m][n];
        // Fill the memo table with -1 to indicate that no subproblem has been solved yet.
        for(int[] temp : memo)Arrays.fill(temp,-1);
        // Start the recursive calculation from the end of both strings (m-1, n-1).
        // Working from the end simplifies handling insertions/deletions.
        return func(word1, word2, m-1, n-1);
    }

    // Recursive helper function to compute edit distance between word1[0...i] and word2[0...j].
    public int func(String word1, String word2, int i, int j){
        // Base case 1: If word1 is exhausted (i < 0), we need to insert all remaining characters of word2.
        // The number of insertions needed is j + 1 (from index 0 to j).
        if(i<0) return j+1;
        // Base case 2: If word2 is exhausted (j < 0), we need to delete all remaining characters of word1.
        // The number of deletions needed is i + 1 (from index 0 to i).
        if(j<0) return i+1;

        // Memoization check: If the result for state (i, j) is already computed, return it.
        if(memo[i][j] != -1) return memo[i][j];

        // If the current characters at indices i and j match:
        if(word1.charAt(i)==word2.charAt(j))
            // No operation is needed for these characters. Move to the previous characters in both strings.
            // Store the result in memo[i][j] and return it.
            return memo[i][j] = func(word1,word2, i-1, j-1);

        // If the current characters do not match, we have three possible operations:

        // Option 1: Replace word1.charAt(i) with word2.charAt(j).
        // This costs 1 operation. Then, solve for the remaining strings (i-1, j-1).
        int replace = 1 + func(word1,word2, i-1, j-1);

        // Option 2: Delete word1.charAt(i).
        // This costs 1 operation. Then, solve for word1[0...i-1] and word2[0...j].
        int delete = 1 + func(word1, word2, i-1,j);

        // Option 3: Insert word2.charAt(j) into word1.
        // This costs 1 operation. Then, solve for word1[0...i] and word2[0...j-1].
        int insert = 1 + func(word1, word2, i,j-1);

        // The minimum edit distance for state (i, j) is the minimum of the three options.
        // Store this minimum value in memo[i][j] and return it.
        return memo[i][j] = Math.min(delete,Math.min(insert,replace));
    }
}
```

## Interview Tips
*   **Explain the DP recurrence relation clearly**: Walk through the choices (match, replace, insert, delete) and how they relate to subproblems.
*   **Discuss the base cases thoroughly**: Ensure you correctly explain why `j+1` or `i+1` are the costs when one string is exhausted.
*   **Mention the trade-off between memoization and tabulation**: While this solution uses memoization, be prepared to discuss the tabulation approach as well.
*   **Consider edge cases**: What if one or both strings are empty? The base cases should handle this.

## Revision Checklist
- [ ] Understand the problem: minimum operations to transform one string to another.
- [ ] Identify the subproblem structure: edit distance between prefixes.
- [ ] Formulate the recurrence relation for matching and mismatching characters.
- [ ] Define the base cases for empty strings or exhausted strings.
- [ ] Implement using recursion with memoization.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative DP approach (tabulation).

## Similar Problems
*   Longest Common Subsequence
*   Longest Common Substring
*   Minimum ASCII Delete Sum for Two Strings
*   Word Break

## Tags
`Dynamic Programming` `Recursion` `String`
