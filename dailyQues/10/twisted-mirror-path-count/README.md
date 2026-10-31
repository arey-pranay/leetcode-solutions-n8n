# Twisted Mirror Path Count

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Matrix`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    int[][][] memo;
    int MOD = 1_000_000_007;
    public int uniquePaths(int[][] grid) {
        memo = new int[grid.length][grid[0].length][3];
        for(int[][] d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        return func(grid,0,0,2)%MOD;
    
    }
    public int func(int grid[][],int i,int j,int dir){ // 0 means we took down, 1 means we took right
        int m = grid.length, n = grid[0].length;
        if(i==m-1 && j==n-1) return 1;
        if(i==m || j==n) return 0;
        if(memo[i][j][dir] != -1) return memo[i][j][dir];
        if(grid[i][j] == 1) return memo[i][j][dir] = (dir == 0 ? func(grid,i,j+1,1) : func(grid,i+1,j,0))%MOD; // change the direction if we encounter 1.
        else return memo[i][j][dir] = func(grid,i+1,j,0)%MOD + func(grid,i,j+1,1)%MOD;
    }
   
}
```

---

---
## Quick Revision
Finds the number of unique paths from the top-left to the bottom-right of a grid with directional mirrors.
Solved using top-down dynamic programming (memoization) tracking current position and incoming direction.

## Intuition
The standard unique paths problem only allows moves down and right at any cell. In this problem, certain cells act as "twistors" or mirrors (`grid[i][j] == 1`), which force a strict directional change (if we came from above, we are forced right; if we came from the left, we are forced down). For normal cells (`grid[i][j] == 0`), we can freely choose to go either down or right. The "aha moment" is that the state of our traversal is defined not just by our coordinates `(i, j)`, but also by the direction `dir` we entered or took, because mirrors alter our freedom of choice. Memoization using a 3D table `memo[i][j][dir]` ensures we avoid redundant subproblem calculations.

## Algorithm
1. Initialize a 3D memoization array `memo` of dimensions `m x n x 3` filled with `-1`, where `m` is the number of rows and `n` is the number of columns.
2. Define a recursive helper function `func(grid, i, j, dir)` where `dir` represents the current direction state (`0` for down, `1` for right, and `2` for the initial start state).
3. Check base cases inside the helper function:
   - If `i == m - 1` and `j == n - 1`, we reached the destination, so return `1`.
   - If `i == m` or `j == n`, we went out of bounds, so return `0`.
4. Check if the subproblem result is already cached in `memo[i][j][dir]`. If so, return the cached value.
5. If `grid[i][j] == 1` (mirror cell):
   - If `dir == 0` (came from top/going down), force a right move: `func(grid, i, j + 1, 1)`.
   - If `dir == 1` (came from left/going right), force a down move: `func(grid, i + 1, j, 0)`.
   - Modulo the result by `1_000_000_007` and save it to `memo[i][j][dir]`.
6. If `grid[i][j] == 0` (normal cell):
   - Sum the paths from both moving down (`func(grid, i + 1, j, 0)`) and moving right (`func(grid, i, j + 1, 1)`).
   - Apply modulo arithmetic to prevent integer overflow at each addition step.
   - Save the result to `memo[i][j][dir]` and return it.
7. Return the final result from the initial call `func(grid, 0, 0, 2)` modulated by `1_000_000_007`.

## Concept to Remember
- **State Augmentation in DP**: Sometimes a 2D grid problem requires expanding the state space (adding a 3rd dimension for direction) to maintain the Markov property (making future choices dependent on past trajectory).
- **Modulo Arithmetic**: When path counts grow exponentially large, modulo operations must be applied at every addition step to prevent arithmetic overflow.

## Common Mistakes
- Forgetting to include the direction `dir` in the memoization state, leading to incorrect overlapping subproblem solutions.
- Not handling out-of-bounds checks before accessing the grid or checking memo tables.
- Forgetting to apply the modulo operation on intermediate addition sums, causing potential overflow issues.
- Incorrect base case ordering (e.g., checking grid boundaries after checking destination or vice versa).

## Complexity Analysis
- Time: O(m * n) - There are `m * n` cells, and each cell has a constant number of directional states (`3`), meaning we compute each state at most once.
- Space: O(m * n) - The 3D memoization table takes `m * n * 3` space, and the recursion call stack can go as deep as `O(m + n)` in the worst case.

## Commented Code
```java
class Solution {
    // Declare a 3D memoization array to store results of subproblems: [row][col][direction]
    int[][][] memo;
    // Define the constant modulo value required for large path counts
    int MOD = 1_000_000_007;
    
    public int uniquePaths(int[][] grid) {
        // Initialize the memoization table with dimensions rows x cols x 3 directions
        memo = new int[grid.length][grid[0].length][3];
        // Iterate through the 3D array and fill all entries with -1 to denote uncalculated states
        for(int[][] d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        // Start the recursive traversal from top-left (0,0) with initial direction state 2
        return func(grid,0,0,2)%MOD;
    }
    
    public int func(int grid[][],int i,int j,int dir){ // dir: 0 = down, 1 = right, 2 = start
        // Cache dimensions of the grid
        int m = grid.length, n = grid[0].length;
        // Base case: if we reach the bottom-right corner, return 1 valid path found
        if(i==m-1 && j==n-1) return 1;
        // Base case: if we go out of bounds, return 0 paths
        if(i==m || j==n) return 0;
        // If this state has already been computed, return the cached result
        if(memo[i][j][dir] != -1) return memo[i][j][dir];
        // If the current cell contains a mirror (1), force a turn based on the incoming direction
        if(grid[i][j] == 1) return memo[i][j][dir] = (dir == 0 ? func(grid,i,j+1,1) : func(grid,i+1,j,0))%MOD; 
        // If it's a normal cell (0), branch out to both down and right moves and sum their results
        else return memo[i][j][dir] = (func(grid,i+1,j,0)%MOD + func(grid,i,j+1,1)%MOD)%MOD;
    }
}
```

## Interview Tips
- Clarify grid constraints and possible values inside `grid` during the problem statement phase.
- Discuss why a standard 2D DP approach fails here (due to path-dependent constraints introduced by mirrors) and justify the addition of the 3rd dimension.
- Mention space optimization techniques if applicable (though 3D space is necessary here due to state dependency, analyzing memory footprint shows strong engineering awareness).

## Revision Checklist
- [ ] Understand why direction state is required in addition to coordinates.
- [ ] Properly initialize and populate the 3D memoization array with `-1`.
- [ ] Handle boundary conditions correctly to prevent `ArrayIndexOutOfBoundsException`.
- [ ] Apply modulo arithmetic consistently at every summation step.
- [ ] Trace mirror conditions (`grid[i][j] == 1`) accurately.

## Similar Problems
- Unique Paths
- Unique Paths II
- Dungeon Game
- Minimum Path Sum

## Tags
`Array` `Dynamic Programming` `Matrix` `Memoization`
