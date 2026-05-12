# Maximal Square

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Matrix`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    char[][] mat;
    int[][] memo;
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        mat = matrix;
        int ans = 0;
        memo = new int[m][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        for(int i=0; i<m; i++) for(int j=0; j<n; j++) ans = Math.max(ans,func(i,j,0));
        return ans*ans;
    }
    public int func(int r , int c , int d){
        if(r == mat.length || c == mat[0].length) return 0;
        if(memo[r][c] != -1) return memo[r][c];
        
        int ans = 0;
        if(mat[r][c] == '1'){
            int x = func(r,c+1,d+1);
            int y = func(r+1,c+1,d+1);
            int z = func(r+1,c,d+1);
            ans = 1+Math.min(x,Math.min(y,z)); 
        }
        return memo[r][c] = ans;
    }
}


```

---

---
## Quick Revision
Given a 2D binary matrix, find the largest square containing only ones and return its area.
This is solved using dynamic programming or recursion with memoization.

## Intuition
The core idea is that if a cell `(r, c)` is '1', it can potentially be the bottom-right corner of a square. The size of the largest square ending at `(r, c)` depends on the sizes of the largest squares ending at its adjacent cells: `(r-1, c)`, `(r, c-1)`, and `(r-1, c-1)`. If `mat[r][c]` is '1', the size of the square ending here is 1 plus the minimum of the sizes of squares ending at these three neighbors. This recursive relationship naturally leads to a DP or memoized recursion solution.

## Algorithm
1. Initialize a 2D DP table (or memoization table) `dp` of the same dimensions as the input `matrix`.
2. Initialize a variable `maxSide` to 0 to store the side length of the largest square found so far.
3. Iterate through each cell `(i, j)` of the `matrix`:
    a. If `matrix[i][j]` is '0', set `dp[i][j]` to 0.
    b. If `matrix[i][j]` is '1':
        i. If `i` or `j` is 0 (i.e., it's the first row or first column), set `dp[i][j]` to 1.
        ii. Otherwise, set `dp[i][j]` to `1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`.
    c. Update `maxSide = max(maxSide, dp[i][j])`.
4. The result is `maxSide * maxSide`.

## Concept to Remember
*   Dynamic Programming: Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   Recursion with Memoization: A top-down approach to DP where recursive calls store results in a cache.
*   State Definition: The DP state `dp[i][j]` represents the side length of the largest square whose bottom-right corner is at `(i, j)`.

## Common Mistakes
*   Off-by-one errors when accessing DP table indices, especially for boundary conditions.
*   Forgetting to handle the base cases (first row and first column) correctly.
*   Calculating the area directly instead of the side length and squaring it at the end.
*   Not initializing the memoization table properly (e.g., with a value that could be a valid DP result).

## Complexity Analysis
- Time: O(m*n) - reason: We visit each cell of the matrix exactly once to compute its DP value.
- Space: O(m*n) - reason: We use a 2D DP table (or memoization table) of the same dimensions as the input matrix.

## Commented Code
```java
class Solution {
    char[][] mat; // Stores the input matrix for easy access within helper function.
    int[][] memo; // Memoization table to store computed results for subproblems. Initialized with -1.

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length; // Get the number of rows.
        int n = matrix[0].length; // Get the number of columns.
        mat = matrix; // Assign the input matrix to the class member.
        int ans = 0; // Initialize the maximum side length found so far to 0.
        memo = new int[m][n]; // Create the memoization table with dimensions m x n.
        for(int[] temp : memo) Arrays.fill(temp,-1); // Initialize all entries in the memo table to -1, indicating they haven't been computed yet.

        // Iterate through each cell of the matrix.
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                // Call the recursive function for each cell and update the maximum side length.
                // The result of func is the side length, so we square it at the end.
                ans = Math.max(ans, func(i,j,0));
            }
        }
        return ans*ans; // Return the area of the largest square (side * side).
    }

    // Recursive helper function to calculate the side length of the largest square ending at (r, c).
    // 'd' parameter is unused in this specific implementation, likely a remnant from a different approach or a misunderstanding.
    public int func(int r , int c , int d){
        // Base case: If we go out of bounds, no square can be formed, return 0.
        if(r == mat.length || c == mat[0].length) return 0;
        // If the result for this cell (r, c) has already been computed, return it from memo.
        if(memo[r][c] != -1) return memo[r][c];

        int ans = 0; // Initialize the side length for the current cell to 0.
        // If the current cell contains '1', it can potentially be part of a square.
        if(mat[r][c] == '1'){
            // Recursively find the side lengths of the largest squares ending at the three adjacent cells:
            // (r, c+1) - right
            // (r+1, c+1) - bottom-right diagonal
            // (r+1, c) - bottom
            int x = func(r,c+1,d+1); // This recursive call seems to be exploring a different path than standard DP.
            int y = func(r+1,c+1,d+1); // The standard DP relies on (r-1, c), (r, c-1), (r-1, c-1).
            int z = func(r+1,c,d+1); // This implementation's recursive calls are not aligned with the typical DP state definition for this problem.
            // The side length of the square ending at (r, c) is 1 (for the current cell) plus the minimum of the side lengths of squares ending at its neighbors.
            // This specific recursive structure is incorrect for the standard maximal square DP.
            ans = 1+Math.min(x,Math.min(y,z));
        }
        // Store the computed side length in the memo table before returning.
        return memo[r][c] = ans;
    }
}
```
*Note: The provided solution uses a recursive approach with memoization but its recursive calls `func(r,c+1,d+1)`, `func(r+1,c+1,d+1)`, `func(r+1,c,d+1)` are not the standard ones for this problem. The typical DP relation relies on `dp[i-1][j]`, `dp[i][j-1]`, and `dp[i-1][j-1]`. The provided code's logic for `ans = 1+Math.min(x,Math.min(y,z))` seems to be exploring paths rather than building up the square size from its top-left corner. The correct DP state `dp[i][j]` should represent the side length of the largest square ending at `(i, j)`. The provided code's `func(r, c, d)` seems to be trying to find the size of a square starting at `(r, c)` and extending downwards/rightwards, which is a different problem. However, if we interpret `func(r, c, d)` as returning the side length of the largest square whose *top-left* corner is at `(r, c)` and `d` is the current side length being checked, then the logic `1 + Math.min(...)` would make sense if the recursive calls were checking for a square of size `d+1`. But the current implementation is flawed for the standard Maximal Square problem.*

## Interview Tips
*   Clearly explain the DP state definition: `dp[i][j]` is the side length of the largest square ending at `(i, j)`.
*   Walk through the recurrence relation: `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])` if `matrix[i][j] == '1'`.
*   Discuss the base cases for the first row and first column.
*   Mention the space optimization possibility: you only need the previous row's DP values to compute the current row, so space can be reduced to O(n).

## Revision Checklist
- [ ] Understand the problem statement: find the largest square of '1's.
- [ ] Identify the DP state: `dp[i][j]` = side length of the largest square ending at `(i, j)`.
- [ ] Formulate the recurrence relation.
- [ ] Handle base cases (first row/column).
- [ ] Implement either iterative DP or memoized recursion.
- [ ] Calculate the final area from the maximum side length.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximal Rectangle
*   Largest Square of 1s (similar, often used interchangeably)

## Tags
`Dynamic Programming` `Array` `Matrix` `Recursion`
