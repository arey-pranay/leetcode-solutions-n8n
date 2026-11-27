# Dungeon Game

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Matrix`  
**Time:** O(m * n)  
**Space:** O(m * n)

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
Find the minimum initial health required for a knight to rescue a princess from the top-left to the bottom-right of a dungeon grid.
Solve this using bottom-up or top-down dynamic programming, working backwards from the destination to the start.

## Intuition
The "aha moment" in this problem is realizing that moving forward from the start doesn't work because the minimum health requirement depends on future obstacles and rewards. If we start from `(0,0)`, we don't know what lies ahead, so we can't determine how much health is "enough" right now. By working backwards from the princess at `(m-1, n-1)` to the knight at `(0, 0)`, we always know the exact minimum health required for the rest of the path. We want to maximize our health at each step (or minimize the deficit), ensuring our health never drops to zero or below at any cell.

## Algorithm
1. Initialize class variables for the dungeon grid `arr`, dimensions `m` and `n`, and a memoization table `memo` of size `m x n`.
2. Define a recursive helper function `func(i, j)` that calculates the minimum health change needed from cell `(i, j)` to the end.
3. Handle base cases in `func`: if `i == m` or `j == n`, return `Integer.MIN_VALUE` (out of bounds). If `i == m-1` and `j == n-1` (destination), return `arr[i][j] >= 0 ? 0 : arr[i][j]`.
4. Check if `memo[i][j]` is already computed; if so, return the cached result.
5. Compute the required health by adding the current cell's value `arr[i][j]` to the maximum of the results from moving right `func(i, j+1)` and moving down `func(i+1, j)`.
6. If the combined health requirement is positive, cap it at `0` because excess health does not carry over as a debt buffer for earlier steps. Memoize and return this value.
7. In the main method, call `func(0, 0)` and convert the final negative health requirement into a positive initial health starting requirement (`ans >= 0 ? 1 : (-1 * ans) + 1`).

## Concept to Remember
- **Backward Dynamic Programming:** Sometimes state transitions are only viable or logical when computed in reverse chronological or spatial order.
- **State Definition Optimization:** Designing state values to represent deficits or exact requirements rather than cumulative sums.
- **Boundary Capping:** Managing floors and ceilings on values (like capping positive health accumulation at zero) to prevent state explosion.

## Common Mistakes
- Trying to solve the problem greedily or using standard forward DP from `(0, 0)` to `(m-1, n-1)`.
- Forgetting to cap positive health values at zero, which incorrectly assumes extra health accumulated later can rescue a knight from a deficit earlier.
- Incorrectly handling the base case of the destination cell itself containing a positive or negative value.
- Failing to offset the final negative output correctly to translate it into a minimum starting health of at least `1`.

## Complexity Analysis
- Time: O(m * n) - Each of the `m * n` cells in the grid is visited once and computed in constant time due to memoization.
- Space: O(m * n) - The memoization table requires `O(m * n)` space, and the recursion stack can go as deep as `O(m + n)` in the worst case.

## Commented Code
```java
class Solution {
    int[][] arr;       // Reference to the dungeon grid
    Integer[][] memo;  // Memoization table to store subproblem results
    int m, n;          // Dimensions of the dungeon grid (rows and columns)

    public int calculateMinimumHP(int[][] dungeon) {
        arr = dungeon; 
        m = arr.length; 
        n = arr[0].length; 
        memo = new Integer[m][n]; // Initialize memoization table
        
        int ans = func(0, 0); // Start the recursive function from the top-left corner
        
        // If ans is >= 0, we only need 1 HP to survive; otherwise, convert deficit to positive HP needed
        return ans >= 0 ? 1 : (-1 * ans) + 1;
    }

    private int func(int i, int j) { 
        // Base case: If we go out of bounds, return a very small number representing an invalid path
        if (i == m || j == n) return Integer.MIN_VALUE;
        
        // Base case: If we reach the bottom-right destination cell
        if (i == m - 1 && j == n - 1) return arr[i][j] >= 0 ? 0 : arr[i][j];
        
        // Return cached result if already computed to avoid redundant calculations
        if (memo[i][j] != null) return memo[i][j];
        
        // Calculate required energy by adding current cell value to the best future path (max of right or down)
        int temp = arr[i][j] + Math.max(func(i + 1, j), func(i, j + 1)); 
        
        // If health at this point is positive, we don't need extra starting energy, so cap it at 0
        return memo[i][j] = temp >= 0 ? 0 : temp; 
    }
}
```

## Interview Tips
- Clearly articulate why forward DP fails during the initial problem breakdown; this demonstrates deep analytical skills.
- Explain the significance of the backward approach within the first 3 minutes of the design phase.
- Discuss how state compression or bottom-up tabulation could optimize the space complexity further to `O(n)`.

## Revision Checklist
- [ ] Understand why forward DP does not work.
- [ ] Derive the backward recurrence relation.
- [ ] Handle boundary and destination base cases correctly.
- [ ] Explain the transformation from final negative deficit to minimum initial HP `1`.
- [ ] Write clean memoized code without index out-of-bound errors.

## Similar Problems
- Unique Paths
- Minimum Path Sum
- Triangle
- Cherry Pickup

## Tags
`Array` `Dynamic Programming` `Matrix`
