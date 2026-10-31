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
Find the number of unique paths from top-left to bottom-right in a grid with directional redirection obstacles.
Solve it using top-down dynamic programming (memoization) tracking current position and incoming direction state.

## Intuition
The "aha moment" is realizing that obstacles (represented by `1`) act as mirrors that force a mandatory turn (if coming from top, turn right; if coming from left, turn down). Empty cells (`0`) allow both choices. By tracking the direction we used to enter or choose the current cell, we can accurately resolve the constraints of the mirror cells and count valid paths without loops or miscalculated branches.

## Algorithm
1. Initialize a 3D memoization table of size `m x n x 3` filled with `-1`, where dimensions represent row `i`, column `j`, and previous/incoming direction `dir`.
2. Define a recursive helper function `func(grid, i, j, dir)` where `dir` can be `0` (down), `1` (right), or an initial state `2`.
3. Base case 1: If `i == m - 1` and `j == n - 1`, we reached the destination, so return `1`.
4. Base case 2: If `i == m` or `j == n`, we went out of bounds, so return `0`.
5. Check if the result for `(i, j, dir)` is already computed in the memoization table; if so, return it.
6. If the current grid cell is `1` (mirror), enforce the twist: if `dir == 0` (came from top), force right `func(grid, i, j+1, 1)`; if `dir == 1` (came from left), force down `func(grid, i+1, j, 0)`.
7. If the current grid cell is `0`, explore both choices (moving down and moving right) and sum their results modulo `1,000,000,007`.
8. Store the result in the memoization table and return it.

## Concept to Remember
- State Space Expansion: Adding extra parameters to the DP state (`dir`) to handle conditional transition rules.
- Memoization with Multiple States: Using multi-dimensional arrays to cache overlapping subproblems.
- Modular Arithmetic: Applying modulo `1,000,000,007` at each addition step to prevent integer overflow.

## Common Mistakes
- Forgetting to handle the initial direction state (e.g., `dir = 2`), leading to `ArrayIndexOutOfBoundsException` on the memoization table.
- Not applying the modulo operation at every addition step, resulting in potential integer overflow.
- Incorrectly defining base cases for boundaries, causing infinite recursion or out-of-bounds access.
- Confusing the mirror logic mapping between incoming directions and forced turns.

## Complexity Analysis
- Time: O(m * n) - Each state `(i, j, dir)` is computed at most once, where `m` and `n` are the grid dimensions and `dir` has 3 possible values.
- Space: O(m * n) - The 3D memoization array requires `m * n * 3` space, and the recursion stack depth goes up to `O(m + n)`.

## Commented Code
```java
class Solution {
    int[][][] memo; // Declare a 3D memoization table to store results for subproblems (row, col, direction).
    int MOD = 1_000_000_007; // Define the modulo constant to prevent integer overflow.
    
    public int uniquePaths(int[][] grid) {
        // Initialize memoization table with dimensions: rows, columns, and 3 possible directions (0, 1, 2).
        memo = new int[grid.length][grid[0].length][3];
        // Populate the memoization table with -1 to indicate uncalculated states.
        for(int[][] d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        // Start recursion from top-left (0, 0) with initial dummy direction 2, and apply modulo.
        return func(grid,0,0,2)%MOD;
    }
    
    public int func(int grid[][], int i, int j, int dir){ // dir: 0 = came from top/moving down, 1 = came from left/moving right
        int m = grid.length, n = grid[0].length; // Get grid dimensions.
        if(i == m-1 && j == n-1) return 1; // Base case: successfully reached the bottom-right corner.
        if(i == m || j == n) return 0; // Base case: went out of bounds, path is invalid.
        if(memo[i][j][dir] != -1) return memo[i][j][dir]; // Return cached result if already computed.
        
        // If current cell is a mirror (1), enforce the directional turn constraint.
        if(grid[i][j] == 1) 
            return memo[i][j][dir] = (dir == 0 ? func(grid,i,j+1,1) : func(grid,i+1,j,0))%MOD; 
        else // If current cell is empty (0), explore both downward and rightward paths.
            return memo[i][j][dir] = (func(grid,i+1,j,0)%MOD + func(grid,i,j+1,1)%MOD)%MOD;
    }
}
```

## Interview Tips
- Clarify the grid constraints and what values the mirror cells can take early in the discussion.
- Discuss alternative approaches, such as why a standard 2D DP won't work directly without expanding the state to include the incoming direction.
- Write clean modular helper functions and clearly explain the space-time trade-offs of adding the 3rd dimension for direction tracking.

## Revision Checklist
- [ ] Understand how state expansion solves conditional movement constraints.
- [ ] Implement top-down memoization with a 3D array.
- [ ] Ensure proper handling of boundary conditions and base cases.
- [ ] Apply modular arithmetic consistently to prevent integer overflow.
- [ ] Trace through a small test case with mirrors manually.

## Similar Problems
- Unique Paths
- Unique Paths II
- Dungeon Game
- Cherry Pickup

## Tags
`Array` `Dynamic Programming` `Memoization` `Matrix`
