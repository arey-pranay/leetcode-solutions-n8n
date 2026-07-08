# Number Of Islands

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search` `Union-Find` `Matrix`  
**Time:** O(M * N)  
**Space:** O(M * N)

---

## Solution (java)

```java
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1'){
                    func(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    public void func(char[][] grid, int x, int y){
        grid[x][y]='2';
        int[]  neighs = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X==grid.length || Y==grid[0].length) continue;
            if(grid[X][Y] == '1') func(grid,X,Y);
        }
        return;
    }
}
```

---

---
## Quick Revision
Given a 2D grid representing a map of '1's (land) and '0's (water), count the number of distinct islands.
We solve this by iterating through the grid, and whenever we find land ('1'), we increment the island count and then use DFS/BFS to "sink" all connected land cells to avoid recounting them.

## Intuition
The core idea is that an island is a connected component of land cells. If we find a piece of land, we know it belongs to *some* island. To count distinct islands, we need a way to mark all parts of that island as "visited" or "counted" so we don't count them again. A common way to explore connected components in a grid is using Depth First Search (DFS) or Breadth First Search (BFS). When we encounter a '1', we can start a traversal from that cell. During the traversal, we change all connected '1's to something else (like '0' or '2' as in the provided solution) to signify they've been processed. Each time we initiate such a traversal from an unvisited '1', it means we've found a new, distinct island.

## Algorithm
1. Initialize an `island_count` to 0.
2. Iterate through each cell `(i, j)` of the `grid`.
3. If the cell `grid[i][j]` is '1' (land):
    a. Increment `island_count`.
    b. Start a traversal (DFS or BFS) from `(i, j)` to mark all connected land cells as visited. This can be done by changing their value to '0' or '2'.
    c. The traversal function should:
        i. Mark the current cell `(x, y)` as visited (e.g., `grid[x][y] = '2'`).
        ii. Explore its neighbors (up, down, left, right).
        iii. For each neighbor `(nx, ny)`:
            - If `(nx, ny)` is within the grid boundaries and is '1', recursively call the traversal function on `(nx, ny)`.
4. After iterating through all cells, return `island_count`.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** Essential for exploring connected components in a grid.
*   **In-place Modification:** Modifying the input grid to mark visited cells is an efficient way to avoid using extra space for a visited set.
*   **Connected Components:** Identifying and counting distinct groups of connected elements.
*   **Grid Manipulation:** Handling boundary conditions and neighbor exploration in a 2D array.

## Common Mistakes
*   **Not Marking Visited Cells:** Failing to mark land cells as visited after processing them leads to recounting the same island multiple times.
*   **Incorrect Boundary Checks:** Not properly checking if neighbor coordinates are within the grid dimensions, leading to `IndexOutOfBoundsException`.
*   **Infinite Recursion (DFS):** If visited cells are not marked correctly, DFS can get stuck in a loop.
*   **Not Handling All Neighbors:** Missing one of the four cardinal directions when exploring neighbors.
*   **Modifying Grid While Iterating:** While this solution modifies the grid, it's crucial to understand that if you were using a separate `visited` array, you'd need to be careful about the order of operations.

## Complexity Analysis
*   **Time:** O(M * N) - Each cell in the grid is visited at most a constant number of times (once by the outer loop, and potentially a few times by the DFS/BFS if it's land).
*   **Space:** O(M * N) - In the worst case, the recursion depth of DFS can be proportional to the number of cells in the grid (e.g., a grid filled entirely with land). This is for the call stack. If BFS were used with a queue, the queue could also hold up to O(M * N) elements.

## Commented Code
```java
class Solution {
    // Main function to count the number of islands.
    public int numIslands(char[][] grid) {
        // Get the number of rows in the grid.
        int m = grid.length;
        // Get the number of columns in the grid.
        int n = grid[0].length;
        // Initialize a counter for the number of islands.
        int count = 0;
        // Iterate through each row of the grid.
        for(int i=0;i<m;i++){
            // Iterate through each column in the current row.
            for(int j=0;j<n;j++){
                // If the current cell is land ('1').
                if(grid[i][j] == '1'){
                    // Call a helper function (DFS) to explore and mark the entire island.
                    func(grid,i,j);
                    // Increment the island count because we found a new, unvisited island.
                    count++;
                }
            }
        }
        // Return the total number of islands found.
        return count;
    }

    // Helper function (DFS) to explore and mark connected land cells.
    public void func(char[][] grid, int x, int y){
        // Mark the current cell as visited by changing it to '2' (or any non-'1' character).
        grid[x][y]='2';
        // Define the relative movements for neighbors: up, right, down, left.
        // neighs[0] = -1 (up), neighs[1] = 0 (same column)
        // neighs[2] = 1 (down), neighs[3] = 0 (same column)
        // neighs[4] = -1 (same row), neighs[5] = 0 (up/down) -- this is a common pattern for 4-directional movement
        // The provided code uses a slightly different pattern:
        // neighs[0] = -1 (dx for up), neighs[1] = 0 (dy for up)
        // neighs[2] = 1 (dx for down), neighs[3] = 0 (dy for down)
        // neighs[4] = -1 (dx for left), neighs[5] = 0 (dy for left) -- this is incorrect.
        // The correct pattern for 4 directions is:
        // int[] dx = {-1, 1, 0, 0}; // Up, Down, Left, Right
        // int[] dy = {0, 0, -1, 1}; // Up, Down, Left, Right
        // The provided code's `neighs` array is intended for this:
        // For i=0: X = x + neighs[0] (-1), Y = y + neighs[1] (0) -> Up
        // For i=1: X = x + neighs[2] (1), Y = y + neighs[3] (0) -> Down
        // For i=2: X = x + neighs[4] (-1), Y = y + neighs[5] (0) -> Left (This is wrong, should be Y = y + neighs[i+1] where i+1 is the corresponding dy)
        // Let's assume the intent was:
        int[] dx = {-1, 1, 0, 0}; // Row changes for Up, Down, Left, Right
        int[] dy = {0, 0, -1, 1}; // Column changes for Up, Down, Left, Right

        // Iterate through the four possible neighbor directions.
        for(int i=0; i<4; i++){
            // Calculate the coordinates of the neighbor.
            int X = x + dx[i]; // New row coordinate
            int Y = y + dy[i]; // New column coordinate

            // Check if the neighbor is within the grid boundaries.
            // X < 0 or Y < 0: checks if the neighbor is above the top row or to the left of the first column.
            // X == grid.length or Y == grid[0].length: checks if the neighbor is below the last row or to the right of the last column.
            if(X < 0 || Y < 0 || X == grid.length || Y == grid[0].length) {
                // If out of bounds, skip this neighbor.
                continue;
            }
            // If the neighbor is land ('1') and has not been visited yet.
            if(grid[X][Y] == '1') {
                // Recursively call func on the neighbor to explore its connected land.
                func(grid,X,Y);
            }
        }
        // The function implicitly returns void.
        return;
    }
}
```
*Note: The provided `func` method's `neighs` array usage is slightly unconventional and potentially confusing. The commented code above uses a more standard `dx`/`dy` array for clarity, assuming the intent was standard 4-directional movement.*

## Interview Tips
1.  **Clarify Input/Output:** Confirm the grid contains only '0's and '1's, and that the output is an integer count. Ask about edge cases like an empty grid or a grid with no land.
2.  **Explain Traversal Choice:** Be ready to explain why DFS or BFS is suitable. Mention that DFS is often simpler to implement recursively, while BFS guarantees finding the shortest path (though not relevant here) and can be better for very deep recursion scenarios to avoid stack overflow.
3.  **Discuss In-place Modification:** Explain the trade-off of modifying the input grid (saves space) versus using a separate `visited` array (preserves original input). If the interviewer asks to preserve the input, you'd need a `visited` boolean array.
4.  **Walk Through an Example:** Be prepared to trace your algorithm on a small example grid, showing how the `count` increments and how cells are marked as visited.

## Revision Checklist
- [ ] Understand the problem: counting connected components of '1's.
- [ ] Choose a traversal algorithm: DFS or BFS.
- [ ] Implement the traversal correctly, handling boundary conditions.
- [ ] Ensure visited cells are marked to avoid recounting.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its trade-offs.

## Similar Problems
*   Max Area of Island
*   Flood Fill
*    Surrounded Regions
*    Rotting Oranges
*    Walls and Gates

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Matrix`
