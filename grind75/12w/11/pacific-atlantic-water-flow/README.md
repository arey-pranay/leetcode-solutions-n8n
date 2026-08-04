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
The problem asks for cells that can reach *both* oceans. Instead of trying to simulate water flowing *from* each cell *to* the oceans (which would be inefficient, potentially O(M*N * M*N)), we can reverse the thinking. Imagine water flowing *from* the oceans *inwards*. If a cell can be reached by water flowing from the Pacific, and it can also be reached by water flowing from the Atlantic, then it's a valid cell. This "reverse flow" approach allows us to use graph traversal algorithms like DFS or BFS efficiently.

## Algorithm
1.  **Initialization**:
    *   Get the dimensions of the `heights` matrix: `m` (rows) and `n` (columns).
    *   Create two boolean matrices, `pacificReachable` and `atlanticReachable`, of the same dimensions as `heights`. These will store whether a cell can reach the respective ocean.
    *   Initialize an empty list `result` to store the coordinates of cells that can reach both oceans.
    *   Define a `directions` array (e.g., `{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}`) for moving to adjacent cells.

2.  **Pacific Ocean Traversal**:
    *   Iterate through the cells in the first row (`i = 0`) and the first column (`j = 0`) of the `heights` matrix. These are the cells adjacent to the Pacific Ocean.
    *   For each of these border cells, if it hasn't been visited for the Pacific yet, start a Depth First Search (DFS) or Breadth First Search (BFS) from it.
    *   The DFS/BFS function should:
        *   Mark the current cell `(x, y)` as reachable for the Pacific (`pacificReachable[x][y] = true`).
        *   Explore its neighbors `(newX, newY)`.
        *   A neighbor is valid to visit if:
            *   It's within the matrix bounds.
            *   It hasn't been visited for the Pacific yet.
            *   The height of the neighbor `heights[newX][newY]` is greater than or equal to the current cell's height `heights[x][y]` (water flows uphill in reverse).
        *   Recursively call DFS/BFS for valid neighbors.

3.  **Atlantic Ocean Traversal**:
    *   Iterate through the cells in the last row (`i = m - 1`) and the last column (`j = n - 1`) of the `heights` matrix. These are the cells adjacent to the Atlantic Ocean.
    *   For each of these border cells, if it hasn't been visited for the Atlantic yet, start a DFS or BFS from it.
    *   The DFS/BFS function should be similar to the Pacific traversal, but it marks cells as reachable for the Atlantic (`atlanticReachable[x][y] = true`).

4.  **Collect Results**:
    *   Iterate through all cells `(i, j)` in the `heights` matrix.
    *   If a cell `(i, j)` is marked as reachable for *both* the Pacific (`pacificReachable[i][j] == true`) and the Atlantic (`atlanticReachable[i][j] == true`), add its coordinates `[i, j]` to the `result` list.

5.  **Return**: Return the `result` list.

## Concept to Remember
*   **Graph Traversal (DFS/BFS)**: The core of the solution involves exploring connected components in a grid, which is a classic application of DFS or BFS.
*   **Reverse Thinking/Problem Transformation**: Instead of simulating flow from cell to ocean, simulate flow from ocean to cell. This significantly simplifies the problem.
*   **Grid as a Graph**: Treating the 2D grid as an adjacency list representation of a graph, where cells are nodes and adjacent cells with non-decreasing height are connected by edges.
*   **State Management**: Using boolean matrices to keep track of visited states for each ocean traversal is crucial to avoid redundant computations and infinite loops.

## Common Mistakes
*   **Simulating Flow from Cell to Ocean**: Trying to run DFS/BFS from every single cell to check reachability to both oceans, leading to a very high time complexity.
*   **Incorrect Height Comparison**: Forgetting that in the "reverse flow" approach, water can only flow from a lower height to a higher or equal height (i.e., `heights[neighbor] >= heights[current]`). The provided solution correctly uses `heights[X][Y] < heights[x][y]` to `continue`, meaning it only proceeds if `heights[X][Y] >= heights[x][y]`.
*   **Not Handling Boundary Conditions**: Failing to correctly check if neighbor coordinates are within the grid boundaries.
*   **Revisiting Cells Unnecessarily**: Not using the `visited` (or `p`/`a` boolean matrices) correctly, leading to redundant computations or infinite recursion.
*   **Confusing Pacific and Atlantic Traversal**: Mixing up the starting points or the `visited` arrays for the two ocean traversals.

## Complexity Analysis
*   **Time**: O(M * N) - Each cell is visited at most twice (once for Pacific, once for Atlantic) during the DFS/BFS traversals. The final collection step also iterates through all M*N cells.
*   **Space**: O(M * N) - For the `pacificReachable` and `atlanticReachable` boolean matrices. In the worst case, the recursion depth for DFS could also be O(M * N) if the grid forms a long path.

## Commented Code
```java
import java.util.ArrayList; // Import ArrayList for storing results
import java.util.Arrays; // Import Arrays for utility functions like asList
import java.util.List; // Import List interface

class Solution {
    // Define relative movements for neighbors: up, down, left, right
    // neighs[0] = -1 (up), neighs[1] = 0 (no row change)
    // neighs[2] = 1 (down), neighs[3] = 0 (no row change)
    // neighs[4] = -1 (left), neighs[5] = 0 (no col change) -- this is incorrect, should be neighs[i+1] for Y
    // Corrected interpretation: neighs[i] and neighs[i+1] together form a direction.
    // i=0: (-1, 0) -> Up
    // i=1: (0, 1) -> Right (This is where the provided code has a slight issue, it uses i and i+1 for both X and Y, which is not standard for 4 directions)
    // Let's assume the intent is:
    // For i=0: X = x + neighs[0], Y = y + neighs[1] -> (-1, 0) Up
    // For i=1: X = x + neighs[1], Y = y + neighs[2] -> (0, 1) Right
    // For i=2: X = x + neighs[2], Y = y + neighs[3] -> (1, 0) Down
    // For i=3: X = x + neighs[3], Y = y + neighs[4] -> (0, -1) Left
    // The provided `neighs` array is `{-1, 0, 1, 0, -1}`.
    // Let's trace `func` with `i` from 0 to 3:
    // i=0: X = x + neighs[0] = x - 1, Y = y + neighs[1] = y + 0. (Up)
    // i=1: X = x + neighs[1] = x + 0, Y = y + neighs[2] = y + 1. (Right)
    // i=2: X = x + neighs[2] = x + 1, Y = y + neighs[3] = y + 0. (Down)
    // i=3: X = x + neighs[3] = x + 0, Y = y + neighs[4] = y - 1. (Left)
    // This interpretation makes sense and covers all 4 directions.
    int[] neighs = new int[] { -1, 0, 1, 0, -1 }; // Offsets for neighbors: {dx1, dy1, dx2, dy2, dx3}

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Get the number of rows (m) and columns (n) of the grid.
        int m = heights.length;
        int n = heights[0].length;
        // Initialize the list to store the final result (cells reachable by both oceans).
        List<List<Integer>> ans = new ArrayList<>();
        // Create a boolean matrix to track cells reachable by the Pacific Ocean.
        boolean[][] p = new boolean[m][n];
        // Create a boolean matrix to track cells reachable by the Atlantic Ocean.
        boolean[][] a = new boolean[m][n];

        // Start DFS from all cells adjacent to the Pacific Ocean.
        // Iterate through the top row (j from 0 to n-1) for Pacific.
        for (int j = 0; j < n; j++) {
            // Call DFS for the top-left cell (0, j) for Pacific.
            func(p, 0, j, heights);
            // Call DFS for the bottom-left cell (m-1, j) for Atlantic.
            func(a, m - 1, j, heights);
        }
        // Iterate through the left column (i from 0 to m-1) for Pacific.
        for (int i = 0; i < m; i++) {
            // Call DFS for the top-left cell (i, 0) for Pacific.
            func(p, i, 0, heights);
            // Call DFS for the top-right cell (i, n-1) for Atlantic.
            func(a, i, n - 1, heights);
        }

        // After populating reachability for both oceans, find common cells.
        // Iterate through each cell in the grid.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a cell is reachable by both Atlantic (a[i][j]) and Pacific (p[i][j]).
                if (a[i][j] && p[i][j]) {
                    // Add its coordinates [i, j] to the result list.
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        // Return the list of cells that can reach both oceans.
        return ans;
    }

    // Depth First Search (DFS) function to explore reachable cells.
    // vis: the boolean matrix for the current ocean (Pacific or Atlantic).
    // x, y: current cell coordinates.
    // heights: the input grid of heights.
    public void func(boolean[][] vis, int x, int y, int[][] heights) {
        // Mark the current cell (x, y) as visited for the current ocean.
        vis[x][y] = true;
        // Loop through the 4 possible directions (up, right, down, left).
        for (int i = 0; i < 4; i++) {
            // Calculate the coordinates of the neighboring cell.
            int X = x + neighs[i]; // Neighbor's row offset
            int Y = y + neighs[i + 1]; // Neighbor's column offset

            // Check if the neighbor is out of bounds, already visited, or has a lower height.
            // If any of these conditions are true, skip this neighbor.
            if (X < 0 || Y < 0 || X >= vis.length || Y >= vis[0].length || vis[X][Y] || heights[X][Y] < heights[x][y])
                continue; // Skip this neighbor if invalid or not traversable in reverse flow.

            // If the neighbor is valid and traversable, recursively call DFS on it.
            func(vis, X, Y, heights);
        }
    }
}
```

## Interview Tips
*   **Explain the Reverse Flow**: Clearly articulate why simulating flow from the oceans inwards is more efficient than simulating flow from each cell outwards. This demonstrates problem-solving and optimization thinking.
*   **Trace an Example**: Walk through a small 2x2 or 3x3 grid manually, showing how the `pacificReachable` and `atlanticReachable` matrices are populated and how the final result is derived.
*   **Discuss DFS vs. BFS**: Be prepared to discuss the trade-offs between DFS and BFS for this problem. Both work, but DFS might be slightly simpler to implement recursively. Mention space complexity implications (recursion stack for DFS vs. queue for BFS).
*   **Edge Cases**: Consider edge cases like a 1x1 grid, a grid with all same heights, or a grid where only one ocean is reachable from certain cells.

## Revision Checklist
- [ ] Understand the problem: identify cells reachable by *both* oceans.
- [ ] Recognize the "reverse flow" optimization: simulate ocean-to-cell reachability.
- [ ] Implement DFS or BFS for traversal.
- [ ] Correctly handle height comparisons for reverse flow (`neighbor_height >= current_height`).
- [ ] Use separate `visited` arrays for Pacific and Atlantic traversals.
- [ ] Initialize traversals from all border cells adjacent to each ocean.
- [ ] Combine results by finding cells marked in both `visited` arrays.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Number of Islands
*   Max Area of Island
*   Surrounded Regions
*   Rotting Oranges
*   Flood Fill

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Matrix`
