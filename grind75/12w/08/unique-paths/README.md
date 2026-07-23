# Unique Paths

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Dynamic Programming` `Combinatorics`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(m-1,n-1);
    }
    public int func(int m, int n){
        if(m==0 && n==0) return 1;
        else if(m<0 || n<0) return 0;
        if(memo[m][n]!=-1) return memo[m][n];
        return memo[m][n] = func(m-1,n) + func(m,n-1);
    }
}
```

---

---
## Quick Revision
Given an m x n grid, find the number of unique paths from the top-left to the bottom-right, moving only down or right.
This is solved using dynamic programming or recursion with memoization.

## Intuition
The "aha moment" comes from realizing that to reach any cell (m, n), you must have come from either the cell directly above it (m-1, n) or the cell directly to its left (m, n-1). Therefore, the total number of unique paths to (m, n) is the sum of the unique paths to (m-1, n) and (m, n-1). This naturally leads to a recursive structure. To avoid redundant calculations, we use memoization to store results of subproblems.

## Algorithm
1. Initialize a 2D array `memo` of size `m x n` to store computed results. Fill it with a sentinel value (e.g., -1) indicating that the result for that cell hasn't been computed yet.
2. Define a recursive helper function `func(row, col)` that calculates the number of unique paths to reach cell `(row, col)`.
3. Base Cases for `func(row, col)`:
    * If `row == 0` and `col == 0` (the starting cell), return 1 (there's one way to be at the start).
    * If `row < 0` or `col < 0` (out of bounds), return 0 (no valid paths from here).
4. Memoization Check: If `memo[row][col]` is not the sentinel value, return the stored result `memo[row][col]`.
5. Recursive Step: Otherwise, calculate the number of paths by summing the paths from the cell above (`func(row-1, col)`) and the cell to the left (`func(row, col-1)`).
6. Store the result in `memo[row][col]` before returning it.
7. The main `uniquePaths` function initializes the `memo` table and calls `func(m-1, n-1)` to get the result for the bottom-right cell.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Memoization (Top-Down Dynamic Programming):** Storing the results of expensive function calls and returning the cached result when the same inputs occur again.
*   **Combinatorics:** The problem can also be viewed as choosing `m-1` down moves (or `n-1` right moves) out of a total of `(m-1) + (n-1)` moves.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly defining the base cases or the target cell for the recursive calls (e.g., using `m` and `n` instead of `m-1` and `n-1` for the target).
*   **Not handling out-of-bounds conditions:** Failing to return 0 when `row` or `col` become negative, leading to infinite recursion or incorrect results.
*   **Forgetting to memoize:** Implementing a pure recursive solution without storing intermediate results, leading to exponential time complexity due to repeated computations.
*   **Incorrect initialization of memo table:** Not filling the memo table with a sentinel value, or using a value that could be a valid result.

## Complexity Analysis
- Time: O(m*n) - Each cell in the m x n grid is computed exactly once due to memoization.
- Space: O(m*n) - For the memoization table `memo` and the recursion call stack (which can go up to `m+n` in depth in the worst case, but is bounded by the grid size for practical purposes when memoized).

## Commented Code
```java
class Solution {
    // Declare a 2D array to store the results of subproblems (memoization table).
    int[][] memo;

    // Main function to calculate unique paths.
    public int uniquePaths(int m, int n) {
        // Initialize the memoization table with dimensions m x n.
        memo = new int[m][n];
        // Fill the memo table with -1, indicating that no results have been computed yet.
        for(int[] temp : memo) {
            Arrays.fill(temp,-1);
        }
        // Call the recursive helper function to compute paths to the bottom-right cell (m-1, n-1).
        return func(m-1,n-1);
    }

    // Recursive helper function with memoization.
    public int func(int m, int n){
        // Base case: If we reach the starting cell (0,0), there's exactly one path.
        if(m==0 && n==0) {
            return 1;
        }
        // Base case: If we go out of bounds (negative row or column), there are no valid paths.
        else if(m<0 || n<0) {
            return 0;
        }
        // Memoization check: If the result for this cell (m, n) has already been computed, return it.
        if(memo[m][n]!=-1) {
            return memo[m][n];
        }
        // Recursive step: The number of paths to (m, n) is the sum of paths from the cell above (m-1, n)
        // and the cell to the left (m, n-1).
        // Store the computed result in the memo table before returning it.
        return memo[m][n] = func(m-1,n) + func(m,n-1);
    }
}
```

## Interview Tips
*   **Explain the recurrence relation:** Clearly articulate why `paths(m, n) = paths(m-1, n) + paths(m, n-1)`.
*   **Discuss memoization:** Explain how it optimizes the solution from exponential to polynomial time.
*   **Consider the DP approach:** Mention that this problem can also be solved iteratively using a bottom-up DP approach, which might be preferred for avoiding recursion depth limits.
*   **Edge cases:** Be prepared to discuss what happens for `m=1` or `n=1` grids.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the overlapping subproblems and optimal substructure.
- [ ] Formulate the recurrence relation.
- [ ] Implement the recursive solution with memoization.
- [ ] Test with base cases and edge cases (1xN, Mx1 grids).
- [ ] Analyze time and space complexity.
- [ ] Consider the iterative DP approach.

## Similar Problems
Unique Paths II
Minimum Path Sum
Robot Unique Paths

## Tags
`Dynamic Programming` `Recursion` `Memoization`
