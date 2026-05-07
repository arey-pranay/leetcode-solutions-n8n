# Unique Paths Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Matrix`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    int m;
    int n;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        memo = new int[m][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return move(0,0,obstacleGrid);
    }
    public int move(int i , int j , int[][] grid){
        if(i>=m || j>=n) return 0;
        if(grid[i][j] == 1) return 0;
        if(memo[i][j] !=-1) return memo[i][j];
        if(i==m-1 && j==n-1) return 1;
        return memo[i][j] = move(i,j+1,grid) + move(i+1,j,grid);
    }
}

```

---

---
## Quick Revision
Find the number of unique paths in an obstacle grid.
Solve it using dynamic programming with memoization.

## Intuition
The key insight here is that we can use a 2D array (memo) to store the results of subproblems. This way, we avoid redundant calculations and optimize our solution.

## Algorithm
1. Initialize a 2D array `memo` with size `m x n`, where `m` and `n` are the dimensions of the obstacle grid.
2. Fill the `memo` array with -1 to indicate that no result has been computed yet.
3. Define a helper function `move(i, j, grid)` that takes the current position `(i, j)` and the obstacle grid as input.
4. If the current position is out of bounds or there's an obstacle at this position, return 0.
5. If the result for the current position has already been computed (memo[i][j] != -1), return the stored value.
6. If we've reached the bottom-right corner of the grid, return 1 (only one way to reach it).
7. Recursively call `move(i, j+1, grid)` and `move(i+1, j, grid)`, and store their results in the memo array.

## Concept to Remember
* Dynamic programming with memoization: avoid redundant calculations by storing intermediate results.
* 2D arrays for representing and manipulating data structures.

## Common Mistakes
* Failing to initialize the memo array properly (e.g., not filling it with -1).
* Not using the correct base case in the recursive function (e.g., returning 0 instead of 1 when reaching the bottom-right corner).
* Not storing the results of subproblems in the memo array.

## Complexity Analysis
- Time: O(m*n) - each position is visited once.
- Space: O(m*n) - the memo array has size m x n.

## Commented Code
```java
class Solution {
    int[][] memo; // 2D array for storing results of subproblems
    int m, n; // dimensions of the obstacle grid

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;

        // Initialize memo array with size m x n and fill it with -1.
        memo = new int[m][n];
        for (int[] temp : memo) Arrays.fill(temp, -1);

        return move(0, 0, obstacleGrid); // Start from the top-left corner.
    }

    public int move(int i, int j, int[][] grid) {
        // Base case: if out of bounds or obstacle at this position, return 0.
        if (i >= m || j >= n || grid[i][j] == 1) return 0;

        // If result for current position has already been computed, return it.
        if (memo[i][j] != -1) return memo[i][j];

        // Base case: if we've reached the bottom-right corner, return 1.
        if (i == m - 1 && j == n - 1) return 1;

        // Recursively call move for neighboring positions and store results in memo array.
        int result = move(i, j + 1, grid) + move(i + 1, j, grid);
        memo[i][j] = result;
        return result;
    }
}
```

## Interview Tips
* Make sure to explain your approach clearly and concisely.
* Highlight the key insights (e.g., using memoization to avoid redundant calculations).
* Be prepared to discuss edge cases (e.g., what if there's an obstacle at the starting position?).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Review dynamic programming with memoization techniques.
- [ ] Practice solving similar problems.

## Similar Problems
* LeetCode: 63. Unique Paths, 66. Plus One, 70. Climbing Stairs

## Tags
`Dynamic Programming`, `Memoization`, `Array`, `Recursion`
