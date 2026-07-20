# Shift 2d Grid

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int total = m * n;
        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++)
                row.add(0);
            ans.add(row);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // index in 1D array (before rotation)
                int oldIndex = i * n + j;

                // index in 1D array (after rotation)
                int newIndex = (oldIndex + k) % total;

                // changing from 1D back to 2D
                int newRow = newIndex / n;
                int newCol = newIndex % n;

                ans.get(newRow).set(newCol, grid[i][j]);
            }
        }

        return ans;
    }
}
```

---

---
## Quick Revision
The problem asks to shift elements of a 2D grid `k` times.
We can solve this by treating the grid as a 1D array, performing the shift, and then converting back to a 2D grid.

## Intuition
Imagine flattening the 2D grid into a single 1D array. Shifting the grid `k` times is equivalent to cyclically shifting this 1D array `k` positions to the right. The "aha moment" is realizing that we can map the 2D grid coordinates `(i, j)` to a 1D index `i * n + j`, perform the shift on this 1D index, and then map the new 1D index back to 2D coordinates `(newRow, newCol)`.

## Algorithm
1. Get the dimensions of the grid: `m` (rows) and `n` (columns).
2. Calculate the total number of elements in the grid: `total = m * n`.
3. Normalize `k` by taking the modulo of `total`: `k %= total`. This handles cases where `k` is larger than the total number of elements.
4. Create a new result grid (or list of lists) of the same dimensions, initialized with placeholder values (e.g., 0s).
5. Iterate through each element of the original grid using nested loops (row `i` from 0 to `m-1`, column `j` from 0 to `n-1`).
6. For each element `grid[i][j]`:
    a. Calculate its 1D index: `oldIndex = i * n + j`.
    b. Calculate its new 1D index after shifting `k` positions: `newIndex = (oldIndex + k) % total`.
    c. Convert the `newIndex` back to 2D grid coordinates:
        i. `newRow = newIndex / n`
        ii. `newCol = newIndex % n`
    d. Place the element `grid[i][j]` into the result grid at `ans[newRow][newCol]`.
7. Return the result grid.

## Concept to Remember
*   **2D to 1D Mapping:** Understanding how to convert 2D array indices `(row, col)` to a single 1D index `row * num_cols + col` and vice-versa.
*   **Modulo Arithmetic for Cyclic Shifts:** Using the modulo operator (`%`) to handle wrap-around behavior in cyclic shifts.
*   **In-place vs. New Array:** Deciding whether to modify the original grid or create a new one for the result. This problem implies creating a new grid.

## Common Mistakes
*   **Incorrect Modulo Calculation:** Forgetting to take `k % total` can lead to incorrect shifts if `k` is very large.
*   **Off-by-One Errors:** Mistakes in calculating the 1D index or converting back to 2D indices.
*   **Handling Edge Cases:** Not considering `k=0` or `k` being a multiple of `total` (which should result in no change).
*   **Modifying Original Grid:** Attempting to shift in-place without a proper strategy can lead to overwriting values needed later.

## Complexity Analysis
- Time: O(m * n) - reason: We iterate through each element of the m x n grid exactly once to calculate its new position and place it in the result grid.
- Space: O(m * n) - reason: We create a new grid (list of lists) of the same dimensions as the input grid to store the result.

## Commented Code
```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // Get the number of rows in the grid.
        int m = grid.length;
        // Get the number of columns in the grid.
        int n = grid[0].length;

        // Calculate the total number of elements in the grid.
        int total = m * n;
        // Normalize k by taking modulo total. This ensures k is within the bounds of the grid size and handles large k values.
        k %= total;

        // Initialize the result list of lists (representing the shifted 2D grid).
        List<List<Integer>> ans = new ArrayList<>();

        // Pre-populate the result grid with empty rows.
        for (int i = 0; i < m; i++) {
            // Create a new list for the current row.
            List<Integer> row = new ArrayList<>();
            // Add n placeholder elements (0s) to the row. This is just to pre-size the row for efficient 'set' operations later.
            for (int j = 0; j < n; j++)
                row.add(0);
            // Add the initialized row to the answer list.
            ans.add(row);
        }

        // Iterate through each element of the original grid.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Calculate the 1D index of the current element grid[i][j] before shifting.
                // This maps the 2D coordinate (i, j) to a linear index.
                int oldIndex = i * n + j;

                // Calculate the new 1D index after shifting k positions to the right.
                // The modulo operator ensures that the index wraps around cyclically.
                int newIndex = (oldIndex + k) % total;

                // Convert the new 1D index back to 2D grid coordinates (newRow, newCol).
                // Integer division by n gives the new row.
                int newRow = newIndex / n;
                // The remainder when divided by n gives the new column.
                int newCol = newIndex % n;

                // Place the current element grid[i][j] into its new position in the answer grid.
                // ans.get(newRow) retrieves the list representing the target row.
                // .set(newCol, grid[i][j]) updates the element at the target column in that row.
                ans.get(newRow).set(newCol, grid[i][j]);
            }
        }

        // Return the final shifted grid.
        return ans;
    }
}
```

## Interview Tips
1.  **Explain the 1D Mapping:** Clearly articulate how you're converting 2D coordinates to a 1D index and back. This is the core of the solution.
2.  **Discuss Modulo Arithmetic:** Emphasize why `k %= total` is crucial for correctness and efficiency, especially for large `k`.
3.  **Consider Edge Cases:** Mention how your solution handles `k=0` or `k` being a multiple of `m*n` (no change) and how the modulo operator naturally covers these.
4.  **Clarify Space Usage:** Be prepared to explain why you're using O(m*n) extra space (to create the new grid) and if an in-place solution is feasible (it's more complex and might not be required for an "easy" problem).

## Revision Checklist
- [ ] Understand the problem statement: shifting elements `k` times.
- [ ] Recognize the 2D to 1D mapping strategy.
- [ ] Implement the `oldIndex = i * n + j` calculation.
- [ ] Implement the `newIndex = (oldIndex + k) % total` calculation.
- [ ] Implement the `newRow = newIndex / n` and `newCol = newIndex % n` conversions.
- [ ] Handle `k` normalization: `k %= total`.
- [ ] Correctly initialize and populate the result grid.
- [ ] Analyze time and space complexity.

## Similar Problems
*   1886. Rotate Image
*   1260. Shift 2D Grid (This is the same problem)
*   237. Delete Node in a Linked List (Concept of shifting/rearranging)

## Tags
`Array` `Matrix` `Simulation`
