# Minimum Path Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Matrix`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    int[][]memo;
    int m;
    int n;
    public int minPathSum(int[][] grid) {
        m = grid.length; 
        n = grid[0].length;
        memo = new int[m][n];
        for(int[] arr : memo) Arrays.fill(arr,-1);
        return func(0,0,grid);
    }
    public int func(int i,int j,int[][]grid){
        if(i == m || j == n) return Integer.MAX_VALUE;
        if(i==m-1 && j==n-1) return grid[i][j];
        if(memo[i][j]!=-1) return memo[i][j];
        int a = func(i+1,j,grid);
        int b = func(i,j+1,grid);
        return memo[i][j] = grid[i][j] + Math.min(a,b);
    } 
}
```

---

---
## Quick Revision
Given a grid of non-negative numbers, find a path from the top-left to the bottom-right with the minimum sum.
This problem can be solved using dynamic programming or recursion with memoization by considering the minimum sum to reach each cell.

## Intuition
The core idea is that to reach any cell `(i, j)`, we must have come from either the cell directly above it `(i-1, j)` or the cell directly to its left `(i, j-1)`. Therefore, the minimum path sum to reach `(i, j)` is the value of `grid[i][j]` plus the minimum of the path sums to reach `(i-1, j)` and `(i, j-1)`. This suggests a recursive structure that can be optimized with memoization or transformed into an iterative DP approach.

## Algorithm
1. **Initialization**:
   - Get the dimensions of the grid, `m` (rows) and `n` (columns).
   - Create a memoization table `memo` of size `m x n`, initialized with -1 to indicate uncomputed states.
2. **Recursive Function `func(i, j, grid)`**:
   - **Base Case 1 (Out of Bounds)**: If `i` or `j` goes beyond the grid boundaries (`i == m` or `j == n`), return `Integer.MAX_VALUE` to signify an invalid path.
   - **Base Case 2 (Destination)**: If `(i, j)` is the bottom-right cell (`i == m-1` and `j == n-1`), return the value of `grid[i][j]`.
   - **Memoization Check**: If `memo[i][j]` is not -1, it means the result for this cell has already been computed, so return `memo[i][j]`.
   - **Recursive Step**:
     - Calculate the minimum path sum by going down: `a = func(i+1, j, grid)`.
     - Calculate the minimum path sum by going right: `b = func(i, j+1, grid)`.
     - The minimum path sum to reach `(i, j)` is `grid[i][j] + Math.min(a, b)`.
     - Store this result in `memo[i][j]` before returning it.
3. **Main Function `minPathSum(grid)`**:
   - Initialize `m`, `n`, and `memo`.
   - Call `func(0, 0, grid)` to start the computation from the top-left cell.

## Concept to Remember
*   **Recursion with Memoization**: Storing results of expensive function calls and returning the cached result when the same inputs occur again.
*   **Dynamic Programming (Bottom-Up)**: Building up a solution from smaller subproblems to larger ones.
*   **Grid Traversal**: Problems involving moving through a 2D grid, often with constraints on movement.

## Common Mistakes
*   **Incorrect Base Cases**: Not handling out-of-bounds conditions or the destination cell correctly can lead to infinite recursion or wrong results.
*   **Forgetting Memoization**: Without memoization, the recursive solution will have exponential time complexity due to recomputing the same subproblems.
*   **Off-by-One Errors**: Miscalculating indices when accessing the grid or memoization table.
*   **Integer Overflow**: While not an issue with typical LeetCode constraints for this problem, in general, be mindful of potential overflows with sums.

## Complexity Analysis
- Time: O(m * n) - Each cell in the grid is visited and computed exactly once due to memoization.
- Space: O(m * n) - For the memoization table `memo` and the recursion call stack (in the worst case, proportional to `m + n`).

## Commented Code
```java
class Solution {
    // Declare a 2D array for memoization to store computed results.
    int[][]memo;
    // Declare variables to store the dimensions of the grid.
    int m;
    int n;

    // The main function to find the minimum path sum.
    public int minPathSum(int[][] grid) {
        // Get the number of rows in the grid.
        m = grid.length; 
        // Get the number of columns in the grid.
        n = grid[0].length;
        // Initialize the memoization table with the same dimensions as the grid.
        memo = new int[m][n];
        // Fill the memoization table with -1, indicating that no cell's result has been computed yet.
        for(int[] arr : memo) Arrays.fill(arr,-1);
        // Start the recursive computation from the top-left cell (0, 0).
        return func(0,0,grid);
    }

    // Recursive helper function to compute the minimum path sum from cell (i, j) to the bottom-right.
    public int func(int i,int j,int[][]grid){
        // Base Case 1: If we go out of bounds (either row or column index is invalid), return a very large value.
        // This ensures that invalid paths are not chosen as the minimum.
        if(i == m || j == n) return Integer.MAX_VALUE;
        // Base Case 2: If we reach the bottom-right cell, return its value as it's the end of a valid path.
        if(i==m-1 && j==n-1) return grid[i][j];
        // Memoization Check: If the result for cell (i, j) has already been computed and stored in memo, return it.
        if(memo[i][j]!=-1) return memo[i][j];
        
        // Recursive Step: Calculate the minimum path sum by exploring two possible moves:
        // 1. Move down: Call func for the cell below (i+1, j).
        int a = func(i+1,j,grid);
        // 2. Move right: Call func for the cell to the right (i, j+1).
        int b = func(i,j+1,grid);
        
        // The minimum path sum to reach the current cell (i, j) is its own value plus the minimum of the path sums from the next possible cells.
        // Store this computed minimum sum in the memoization table before returning it.
        return memo[i][j] = grid[i][j] + Math.min(a,b);
    } 
}
```

## Interview Tips
*   **Explain the DP relation**: Clearly articulate how the minimum path sum to a cell depends on the minimum path sums of its adjacent cells.
*   **Discuss memoization vs. tabulation**: Be prepared to explain why memoization (top-down) is used here and how it could be converted to a tabulation (bottom-up) approach.
*   **Handle edge cases**: Explicitly mention how out-of-bounds conditions and the destination cell are handled.
*   **Walk through an example**: Use a small 2x2 or 3x3 grid to trace the execution of your algorithm and demonstrate how the memoization table is filled.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the overlapping subproblems and optimal substructure.
- [ ] Formulate the recursive relation.
- [ ] Implement memoization to avoid redundant calculations.
- [ ] Handle base cases correctly (out of bounds, destination).
- [ ] Analyze time and space complexity.
- [ ] Consider alternative DP approaches (tabulation).

## Similar Problems
*   Unique Paths
*   Unique Paths II
*   Climbing Stairs
*   Coin Change

## Tags
`Array` `Dynamic Programming` `Recursion` `Memoization`
