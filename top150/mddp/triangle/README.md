# Triangle

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(N^2)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    Integer[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        memo= new Integer[triangle.size()][triangle.size()];
        return func(triangle,0,0);
    } 
    public int func(List<List<Integer>> triangle , int row, int col){
        if(row >= triangle.size()) return 0;
        if(memo[row][col] != null) return memo[row][col];
        int val = triangle.get(row).get(col);
        int a = func(triangle,row+1,col);
        int b = func(triangle,row+1,col+1);
        return memo[row][col] = val + Math.min(a,b);
    }
}
```

---

---
## Quick Revision
Given a triangle array, find the minimum path sum from top to bottom.
Solve using dynamic programming with memoization or bottom-up tabulation.

## Intuition
The problem asks for the minimum path sum. This suggests exploring all possible paths and finding the minimum. A brute-force recursive approach would explore all paths, but this leads to redundant calculations for overlapping subproblems. For example, reaching a specific node in the triangle from the top might be possible through multiple paths, and we'd recompute the minimum path from that node downwards each time. This redundancy is a hallmark of dynamic programming.

The "aha moment" comes from realizing that the minimum path sum to reach any node `(row, col)` from the top is dependent on the minimum path sums to reach its children nodes `(row+1, col)` and `(row+1, col+1)`. This recursive structure, combined with overlapping subproblems, points towards a DP solution. We can either build up the solution from the bottom (tabulation) or use recursion with memoization to store and reuse results of subproblems. The provided solution uses memoization.

## Algorithm
1. **Initialization**: Create a 2D array `memo` of the same dimensions as the triangle (or slightly larger to handle boundary conditions) to store the results of subproblems. Initialize all entries to `null` (or a sentinel value indicating not computed).
2. **Recursive Function `func(triangle, row, col)`**:
   a. **Base Case**: If `row` is out of bounds (i.e., `row >= triangle.size()`), it means we've reached beyond the bottom of the triangle, so the path sum from here is 0. Return 0.
   b. **Memoization Check**: If `memo[row][col]` is not `null`, it means we've already computed the minimum path sum from this `(row, col)` to the bottom. Return the stored value `memo[row][col]`.
   c. **Current Value**: Get the value of the current node: `val = triangle.get(row).get(col)`.
   d. **Recursive Calls**:
      i. Recursively call `func` for the left child: `a = func(triangle, row + 1, col)`.
      ii. Recursively call `func` for the right child: `b = func(triangle, row + 1, col + 1)`.
   e. **Combine and Store**: The minimum path sum from the current node `(row, col)` to the bottom is the current node's value plus the minimum of the path sums from its children. Store this result in `memo[row][col]` and return it: `memo[row][col] = val + Math.min(a, b)`.
3. **Main Function `minimumTotal(triangle)`**:
   a. Initialize the `memo` array.
   b. Call the recursive function starting from the top of the triangle: `func(triangle, 0, 0)`.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a problem into overlapping subproblems and solving each subproblem only once, storing their solutions to avoid recomputation.
*   **Recursion with Memoization (Top-Down DP)**: Using a recursive function to solve subproblems and a data structure (like a 2D array) to store results of already solved subproblems.
*   **Greedy Approach vs. Optimal Substructure**: While it might seem greedy to pick the smaller of the two children at each step, this is only valid because the problem exhibits optimal substructure (the optimal solution to the problem contains optimal solutions to subproblems).

## Common Mistakes
*   **Off-by-one errors**: Incorrectly handling the base cases or indices when accessing the triangle or memoization table.
*   **Not handling memoization correctly**: Forgetting to check if a subproblem has already been solved or not storing the result after computation.
*   **Inefficient base case**: Returning a large value instead of 0 when `row >= triangle.size()`, which would incorrectly inflate the minimum path sum.
*   **Stack Overflow**: For very large triangles, a purely recursive solution without memoization can lead to a stack overflow due to excessive recursion depth. Memoization helps mitigate this by reducing redundant calls.

## Complexity Analysis
*   **Time**: O(N^2) - where N is the number of rows in the triangle. Each cell in the triangle is visited and computed exactly once due to memoization. The number of cells in a triangle with N rows is approximately N*(N+1)/2, which is O(N^2).
*   **Space**: O(N^2) - for the memoization table (`memo`) which stores the results for each cell in the triangle.

## Commented Code
```java
class Solution {
    // Declare a 2D array to store memoized results.
    // Using Integer allows us to use null to indicate uncomputed states.
    Integer[][] memo;

    // The main function to find the minimum total path sum.
    public int minimumTotal(List<List<Integer>> triangle) {
        // Initialize the memoization table with dimensions matching the triangle's size.
        // The number of columns in each row increases, but we can use triangle.size() as a safe upper bound for columns.
        memo = new Integer[triangle.size()][triangle.size()];
        // Start the recursive calculation from the top of the triangle (row 0, column 0).
        return func(triangle, 0, 0);
    }

    // Recursive helper function to calculate the minimum path sum from a given cell (row, col) to the bottom.
    public int func(List<List<Integer>> triangle, int row, int col) {
        // Base case: If we have gone past the last row, it means we've reached the end of a path.
        // The sum from here onwards is 0.
        if (row >= triangle.size()) {
            return 0;
        }
        // Memoization check: If the result for this cell (row, col) has already been computed, return it.
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        // Get the value of the current node in the triangle.
        int val = triangle.get(row).get(col);

        // Recursively calculate the minimum path sum from the left child (row+1, col).
        int a = func(triangle, row + 1, col);
        // Recursively calculate the minimum path sum from the right child (row+1, col+1).
        int b = func(triangle, row + 1, col + 1);

        // The minimum path sum from the current cell is its value plus the minimum of the path sums from its children.
        // Store this computed result in the memoization table before returning.
        return memo[row][col] = val + Math.min(a, b);
    }
}
```

## Interview Tips
*   **Explain the DP approach**: Clearly articulate why a brute-force recursive solution is inefficient and how dynamic programming (specifically memoization or tabulation) optimizes it.
*   **Trace an example**: Walk through a small triangle example (e.g., 3 rows) to show how the `func` method explores paths and how memoization prevents redundant calculations.
*   **Discuss space optimization**: Mention that the space complexity can be reduced to O(N) if using a bottom-up approach with only two rows (current and previous) or even one row if iterating carefully.
*   **Handle edge cases**: Be prepared to discuss what happens with an empty triangle or a triangle with only one row.

## Revision Checklist
- [ ] Understand the problem statement: minimum path sum from top to bottom.
- [ ] Recognize overlapping subproblems and optimal substructure.
- [ ] Implement recursion with memoization (top-down DP).
- [ ] Implement bottom-up DP (tabulation) as an alternative.
- [ ] Analyze time and space complexity for both approaches.
- [ ] Consider space optimization for bottom-up DP.
- [ ] Practice tracing examples.

## Similar Problems
*   Unique Paths
*   Minimum Path Sum
*   Climbing Stairs
*   House Robber
*   Coin Change

## Tags
`Dynamic Programming` `Array` `Recursion`
