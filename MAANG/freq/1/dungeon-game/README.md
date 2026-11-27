# Dungeon Game

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Matrix`  
**Time:** O(m \times n)  
**Space:** O(m \times n)

---

## Solution (java)

```java
class Solution {
    int[][] arr;
    Integer[][] memo;
    int  m,n;
    public int calculateMinimumHP(int[][] dungeon) {
        arr = dungeon; m =arr.length; n=arr[0].length; memo = new Integer[m][n];
        int ans = func(0,0);
        return ans >= 0 ? 1 : (-1*ans)+1;
    }
    private int func(int i, int j){ // aage jaane ke liye kitni energy aur lgegi
        if(i==m || j==n) return Integer.MIN_VALUE;
        if(i==m-1 && j==n-1) return arr[i][j] >= 0 ? 0 : arr[i][j];
        if(memo[i][j] != null) return memo[i][j];
        int temp = arr[i][j] + Math.max(func(i+1,j),func(i,j+1)); // negative me magnitude km hai
        return memo[i][j] = temp >= 0 ? 0 : temp; // kisi point pe agr positive output aara hai, to wahan jaane ke liye kuch extra energy nhi needed hai
    }
}
```

---

---
## Quick Revision
Compute the minimum initial health a knight needs to reach the bottom-right corner from the top-left without dying.
Solve it using bottom-up or top-down dynamic programming, working backwards from the destination to the start.

## Intuition
The "aha moment" is realizing that you cannot solve this problem from top-left to bottom-right easily because your choices at any step depend on what lies ahead, and your health must never drop to zero or below at *any* intermediate point. Working backwards from the destination (bottom-right) to the source (top-left) completely solves this dependency. By tracking the exact minimum health required from each cell to finish the path (clamping excess positive health gains to zero since health cannot exceed the required minimum baseline for future survival), the recurrence relation falls naturally into place.

## Algorithm
1. Initialize dimensions $m$ and $n$ of the dungeon grid, and create a memoization table `memo` of size $m \times n$.
2. Define a recursive helper function `func(i, j)` that calculates the net health deficit or surplus needed from coordinate $(i, j)$ to the exit $(m-1, n-1)$.
3. Base Case 1: If $i == m$ or $j == n$ (out of bounds), return `Integer.MIN_VALUE` to signify an invalid path.
4. Base Case 2: If $i == m-1$ and $j == n-1$ (destination), return `arr[i][j] >= 0 ? 0 : arr[i][j]` (if the cell gives health, you need 0 extra; if it damages you, you need that exact negative amount).
5. Check if `memo[i][j]` is already computed; if so, return the cached result.
6. Compute the required health from adjacent valid moves: `temp = arr[i][j] + Math.max(func(i+1, j), func(i, j+1))`.
7. Clamp positive values to zero (`temp >= 0 ? 0 : temp`) because excess health accumulated beyond zero does not help past requirements, store it in `memo[i][j]`, and return it.
8. After computing `func(0, 0)`, convert the final net health deficit to the starting positive health requirement: if the result is $\ge 0$, return $1$; otherwise, return $(-1 \times \text{ans}) + 1$.

## Concept to Remember
- **Backward Dynamic Programming:** Transitioning states from the goal backwards to the start when future constraints dictate current decisions.
- **State Reduction & Clamping:** Managing bounds where excess resources (health) do not carry forward benefits beyond zero.
- **Memoization / Tabulation:** Avoiding exponential time complexity in grid traversal problems with overlapping subproblems.

## Common Mistakes
- Attempting to solve the problem greedily from top-left to bottom-right by picking the maximum health path at each step.
- Failing to handle bounds correctly when moving right or down at the boundary cells.
- Not clamping positive health surpluses to zero, which can lead to incorrect calculations when subsequent cells require absorbing damage.
- Forgetting that the knight's health must remain strictly greater than 0 at every single step.

## Complexity Analysis
- Time: $O(m \times n)$ - Each cell in the $m \times n$ grid is visited and computed at most once due to memoization.
- Space: $O(m \times n)$ - Required for the recursion call stack in the worst-case path and the memoization table of size $m \times n$.

## Commented Code
```java
class Solution {
    int[][] arr;       // Reference to the dungeon grid
    Integer[][] memo;  // Memoization table to store subproblem results
    int m, n;          // Dimensions of the dungeon (rows and columns)
    
    public int calculateMinimumHP(int[][] dungeon) {
        arr = dungeon; 
        m = arr.length; 
        n = arr[0].length; 
        memo = new Integer[m][n]; // Initialize memoization table
        
        int ans = func(0, 0);     // Find min net health from top-left
        
        // If ans >= 0, we only need 1 health unit to survive; otherwise, invert negative and add 1
        return ans >= 0 ? 1 : (-1 * ans) + 1;
    }
    
    private int func(int i, int j) { 
        // Base case: If we go out of bounds, return minimum value to invalidate this path
        if (i == m || j == n) return Integer.MIN_VALUE;
        
        // Base case: If we reach the bottom-right destination cell
        if (i == m - 1 && j == n - 1) return arr[i][j] >= 0 ? 0 : arr[i][j];
        
        // Return cached result if already computed to avoid redundant calculations
        if (memo[i][j] != null) return memo[i][j];
        
        // Calculate health needed from current cell by taking the best path forward (max remaining health needed)
        int temp = arr[i][j] + Math.max(func(i + 1, j), func(i, j + 1)); 
        
        // If current cell gives a net positive or zero balance, clamp it to 0 (no extra starting health needed)
        return memo[i][j] = temp >= 0 ? 0 : temp; 
    }
}
```

## Interview Tips
- Clarify why a forward greedy approach fails with a quick counter-example (e.g., taking a path with high initial health that suddenly leads to a deadly trap vs. a path with a minor penalty leading to safety).
- Discuss both top-down (memoization) and bottom-up (tabular) approaches during the interview.
- Mention space optimization: since each row only depends on the next row, space complexity can be optimized to $O(n)$.

## Revision Checklist
- [ ] Understand why backward DP is mandatory for this grid problem.
- [ ] Trace the base case for the destination cell.
- [ ] Explain why excess positive health is clamped to zero.
- [ ] Convert final negative deficit value into positive starting health.

## Similar Problems
- `Unique Paths`
- `Minimum Path Sum`
- `Cherry Pickup`
- `Minimum Path Cost in a Grid`

## Tags
`Dynamic Programming` `Matrix` `Memoization` `Array`
