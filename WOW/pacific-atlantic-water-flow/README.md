# Pacific Atlantic Water Flow

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search` `Matrix`  
**Time:** O(M * N)  
**Space:** O(M * N)

---

## Solution (java)

```java
class Solution {
    int[] neighs = new int[] { -1, 0, 1, 0, -1 };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            func(p, 0, j, heights);
            func(a, m - 1, j, heights);
        }
        for (int i = 0; i < m; i++) {
            func(p, i, 0, heights);
            func(a, i, n - 1, heights);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j] && p[i][j])
                    ans.add(Arrays.asList(i, j));
        return ans;
    }

    public void func(boolean[][] vis, int x, int y, int[][] heights) {
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int X = x + neighs[i];
            int Y = y + neighs[i + 1];
            if (X < 0 || Y < 0 || X >= vis.length || Y >= vis[0].length || vis[X][Y] || heights[X][Y] < heights[x][y])
                continue;
            func(vis, X, Y, heights);
        }
    }
}
```

---

---
## Quick Revision
Given an M x N matrix representing land heights, find all cells from which water can flow to both the Pacific and Atlantic oceans.
Solve by performing two separate traversals (DFS or BFS) from the ocean borders inwards, marking reachable cells for each ocean.

## Intuition
The problem asks us to find cells that can reach *both* oceans. A naive approach would be to start from each cell and try to reach both oceans. This would be very inefficient. The "aha moment" comes from reversing the problem: instead of asking "can this cell reach the ocean?", we ask "can the ocean reach this cell?". If we can find all cells reachable *from* the Pacific and all cells reachable *from* the Atlantic, then the intersection of these two sets will be our answer. This is because water flows downhill, so if the ocean can reach a cell, that cell can also reach the ocean (by flowing uphill in reverse, which is equivalent to flowing downhill from the cell).

## Algorithm
1.  Initialize two boolean matrices, `pacificReachable` and `atlanticReachable`, of the same dimensions as the `heights` matrix, to keep track of cells that can reach the Pacific and Atlantic oceans, respectively.
2.  Define a helper function (e.g., `dfs` or `bfs`) that takes the current cell's coordinates `(x, y)`, the `heights` matrix, and the `visited` matrix (either `pacificReachable` or `atlanticReachable`) as input.
3.  Inside the helper function:
    *   Mark the current cell `(x, y)` as visited in the provided `visited` matrix.
    *   Iterate through the four possible neighbors `(nx, ny)` of the current cell.
    *   For each neighbor, check if it's within the grid boundaries, hasn't been visited yet in the current traversal, and if its height is greater than or equal to the current cell's height (since water flows downhill, we can only move to cells with equal or greater height when traversing *from* the ocean *inwards*).
    *   If all conditions are met, recursively call the helper function for the neighbor `(nx, ny)`.
4.  Initiate the traversal for the Pacific Ocean:
    *   Iterate through the first row (top border) of the `heights` matrix. For each cell `(0, j)`, call the helper function to mark all cells reachable from the Pacific.
    *   Iterate through the first column (left border) of the `heights` matrix. For each cell `(i, 0)`, call the helper function to mark all cells reachable from the Pacific.
5.  Initiate the traversal for the Atlantic Ocean:
    *   Iterate through the last row (bottom border) of the `heights` matrix. For each cell `(m-1, j)`, call the helper function to mark all cells reachable from the Atlantic.
    *   Iterate through the last column (right border) of the `heights` matrix. For each cell `(i, n-1)`, call the helper function to mark all cells reachable from the Atlantic.
6.  Finally, iterate through the entire `heights` matrix. If a cell `(i, j)` is marked as reachable in *both* `pacificReachable` and `atlanticReachable` matrices, add its coordinates `[i, j]` to the result list.
7.  Return the result list.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** This problem can be modeled as a graph where cells are nodes and adjacent cells with valid height differences are edges. DFS or BFS are standard algorithms for exploring such graphs.
*   **Reverse Thinking/Problem Transformation:** Instead of solving the problem directly (cell to ocean), solving the reverse problem (ocean to cell) simplifies the approach significantly.
*   **Grid Traversal:** Efficiently navigating and processing elements in a 2D grid.
*   **State Management:** Using boolean matrices to keep track of visited states for different traversal origins.

## Common Mistakes
*   **Incorrect Traversal Direction:** Trying to traverse from the cell outwards to the ocean instead of from the ocean inwards. This leads to incorrect logic for height comparisons.
*   **Not Handling Duplicates:** If using BFS, ensuring that a cell is not added to the queue multiple times if it's reachable from different paths. DFS naturally handles this with the `visited` array.
*   **Off-by-One Errors:** Incorrectly defining the boundaries of the grid or the starting points for ocean traversals.
*   **Forgetting to Reset Visited States:** If not using separate `visited` arrays for each ocean, failing to reset them between the Pacific and Atlantic traversals would lead to incorrect results.
*   **Incorrect Height Comparison:** Using `heights[X][Y] > heights[x][y]` instead of `heights[X][Y] >= heights[x][y]` when traversing from the ocean inwards.

## Complexity Analysis
*   **Time:** O(M * N) - Each cell is visited at most twice (once for Pacific traversal, once for Atlantic traversal). The initial loops to start DFS/BFS and the final loop to collect results also take O(M*N) time.
*   **Space:** O(M * N) - For the two boolean `visited` matrices (`pacificReachable` and `atlanticReachable`) and the recursion stack depth in the worst case (which can be M*N for DFS in a snake-like path).

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class for dynamic lists
import java.util.Arrays; // Import the Arrays class for utility methods like asList
import java.util.List; // Import the List interface

class Solution {
    // Define an array for neighbor movements: up, right, down, left.
    // neighs[i] and neighs[i+1] give the delta for x and y coordinates respectively.
    // For i=0: (-1, 0) -> up
    // For i=1: (0, 1) -> right
    // For i=2: (1, 0) -> down
    // For i=3: (0, -1) -> left
    int[] neighs = new int[] { -1, 0, 1, 0, -1 };

    // Main function to find cells reachable by both Pacific and Atlantic oceans.
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Get the number of rows (m) and columns (n) of the grid.
        int m = heights.length;
        int n = heights[0].length;
        // Initialize the result list to store coordinates of cells reachable by both oceans.
        List<List<Integer>> ans = new ArrayList<>();
        // Create a boolean matrix to track cells reachable by the Pacific Ocean.
        boolean[][] p = new boolean[m][n];
        // Create a boolean matrix to track cells reachable by the Atlantic Ocean.
        boolean[][] a = new boolean[m][n];

        // Start DFS from the top border (row 0) for the Pacific Ocean.
        // Iterate through each column in the first row.
        for (int j = 0; j < n; j++) {
            // Call the helper function (DFS) starting from cell (0, j) for Pacific.
            func(p, 0, j, heights);
            // Call the helper function (DFS) starting from cell (m-1, j) for Atlantic.
            // The Atlantic ocean is at the bottom border.
            func(a, m - 1, j, heights);
        }
        // Start DFS from the left border (column 0) for the Pacific Ocean.
        // Iterate through each row in the first column.
        for (int i = 0; i < m; i++) {
            // Call the helper function (DFS) starting from cell (i, 0) for Pacific.
            func(p, i, 0, heights);
            // Call the helper function (DFS) starting from cell (i, n-1) for Atlantic.
            // The Atlantic ocean is at the right border.
            func(a, i, n - 1, heights);
        }

        // After marking all reachable cells for both oceans, find the intersection.
        // Iterate through each cell in the grid.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a cell is reachable by both Atlantic (a[i][j]) and Pacific (p[i][j]),
                // add its coordinates to the result list.
                if (a[i][j] && p[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        // Return the list of cells that can reach both oceans.
        return ans;
    }

    // Helper function (DFS) to mark reachable cells from an ocean.
    // vis: the visited matrix for the current ocean (Pacific or Atlantic).
    // x, y: current cell coordinates.
    // heights: the input grid of heights.
    public void func(boolean[][] vis, int x, int y, int[][] heights) {
        // Mark the current cell (x, y) as visited for the current ocean.
        vis[x][y] = true;
        // Iterate through the four possible neighbors (up, right, down, left).
        for (int i = 0; i < 4; i++) {
            // Calculate the coordinates of the neighbor.
            int X = x + neighs[i]; // Neighbor's row
            int Y = y + neighs[i + 1]; // Neighbor's column

            // Check boundary conditions:
            // 1. X < 0: Neighbor is above the grid.
            // 2. Y < 0: Neighbor is to the left of the grid.
            // 3. X >= vis.length: Neighbor is below the grid.
            // 4. Y >= vis[0].length: Neighbor is to the right of the grid.
            // Check if the neighbor has already been visited in this traversal.
            // Check height condition: heights[X][Y] < heights[x][y]
            // This condition is crucial: we can only move from an ocean INWARDS to a cell
            // if the neighbor's height is GREATER THAN OR EQUAL TO the current cell's height.
            // If any of these conditions are true, skip this neighbor.
            if (X < 0 || Y < 0 || X >= vis.length || Y >= vis[0].length || vis[X][Y] || heights[X][Y] < heights[x][y])
                continue; // Skip this neighbor and move to the next one.

            // If the neighbor is valid and unvisited, recursively call func for the neighbor.
            // This continues the DFS traversal.
            func(vis, X, Y, heights);
        }
    }
}
```

## Interview Tips
*   **Explain the Reverse Thinking:** Clearly articulate why starting from the oceans and moving inwards is more efficient than starting from each cell and moving outwards. This demonstrates problem-solving skills.
*   **Discuss DFS vs. BFS:** Be prepared to explain how you would implement this using BFS as well. Mention the trade-offs (e.g., BFS uses a queue, DFS uses recursion/stack; both have similar time/space complexity here).
*   **Edge Cases:** Mention handling empty grids or grids with only one row/column. The current code implicitly handles these due to loop conditions.
*   **Clarity of `visited` Arrays:** Emphasize that two separate `visited` arrays are necessary because the reachability for the Pacific and Atlantic oceans are independent until the final intersection step.

## Revision Checklist
- [ ] Understand the problem statement: find cells that can reach *both* oceans.
- [ ] Recognize the efficiency gain of reversing the problem: ocean to cell.
- [ ] Implement DFS (or BFS) correctly for grid traversal.
- [ ] Ensure correct height comparison for inward traversal (`>=`).
- [ ] Handle boundary conditions for grid traversal.
- [ ] Use separate `visited` arrays for Pacific and Atlantic.
- [ ] Correctly identify starting points for traversals (all border cells).
- [ ] Combine results by finding the intersection of the two `visited` sets.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Number of Islands
*   Max Area of Island
*   Surrounded Regions
*   Rotting Oranges
*   Walls and Gates

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Matrix`
