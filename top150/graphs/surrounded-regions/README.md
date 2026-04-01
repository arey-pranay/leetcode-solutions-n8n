# Surrounded Regions

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search` `Union-Find` `Matrix`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;

        // Step 1: Mark boundary connected O's
        for (int i = 0; i < m; i++) {
            dfs(i, 0, board);
            dfs(i, n - 1, board);
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, board);
            dfs(m - 1, j, board);
        }

        // Step 2: Flip remaining O -> X, # -> O
        //koi O agr abhi tk bacha hua hai, to mtlb wo doob jayega because it is not connected to any boundary group
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        int m = board.length, n = board[0].length;

        // out of bound OR water check
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = '#'; // mark unsafe
        
        dfs(i + 1, j, board);
        dfs(i - 1, j, board);
        dfs(i, j + 1, board);
        dfs(i, j - 1, board);
    }
}
```

---

---
## Quick Revision
The problem asks to capture regions of 'X's surrounded by 'O's on a 2D board.
We solve this by identifying 'O's connected to the boundary and marking them, then flipping all other 'O's to 'X's.

## Intuition
The key insight is that any 'O' that is *not* surrounded must be connected to an 'O' on the boundary of the board. If an 'O' can reach the edge, it's safe. All other 'O's are surrounded. So, instead of trying to find surrounded regions directly, we find the *un-surrounded* regions. We can do this by starting a traversal (like DFS or BFS) from every 'O' on the border. Any 'O' reachable from the border is safe. We can mark these safe 'O's with a temporary character (e.g., '#'). After marking all safe 'O's, we iterate through the board: any remaining 'O's must be surrounded and can be flipped to 'X', and the temporary '#' characters can be flipped back to 'O'.

## Algorithm
1.  **Identify Boundary-Connected 'O's:**
    *   Iterate through the cells on the top and bottom rows of the board. If a cell contains 'O', start a Depth First Search (DFS) or Breadth First Search (BFS) from it.
    *   Iterate through the cells on the left and right columns of the board. If a cell contains 'O', start a DFS or BFS from it.
    *   During the traversal (DFS/BFS), if you encounter an 'O', mark it with a temporary character (e.g., '#') to indicate it's connected to the boundary and thus "safe". Also, recursively/iteratively visit its adjacent cells (up, down, left, right).
2.  **Flip Surrounded 'O's and Restore Safe 'O's:**
    *   Iterate through the entire board.
    *   If a cell contains 'O', it means this 'O' was not reachable from any boundary 'O', so it's surrounded. Flip it to 'X'.
    *   If a cell contains '#', it means this was a boundary-connected 'O'. Flip it back to 'O'.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** Essential for exploring connected components in a grid.
*   **Boundary Conditions:** Handling edges and corners of the grid is crucial.
*   **In-place Modification:** Modifying the input array directly to save space.
*   **Marking Visited/Safe States:** Using temporary markers to distinguish different states of cells.

## Common Mistakes
*   **Incorrect Boundary Check:** Not considering all four boundaries (top, bottom, left, right) for starting the traversal.
*   **Infinite Recursion/Loops:** Not marking visited cells or using a temporary marker, leading to revisiting cells and potential stack overflow or infinite loops.
*   **Flipping 'O's Before Marking:** Trying to flip surrounded 'O's without first identifying and marking the safe 'O's.
*   **Not Restoring Marked Cells:** Forgetting to change the temporary marker back to 'O' after the second pass.
*   **Off-by-One Errors:** Incorrectly calculating row/column indices or boundary conditions.

## Complexity Analysis
*   **Time:** O(m * n) - We visit each cell at most a constant number of times (once for marking, once for flipping). The DFS/BFS from boundary cells will explore each reachable 'O' once.
*   **Space:** O(m * n) - In the worst case, the recursion depth of DFS can be proportional to the number of cells in the grid (e.g., a board filled with 'O's). This is for the call stack. If BFS is used, the queue can also store up to O(m*n) elements.

## Commented Code
```java
class Solution {
    public void solve(char[][] board) {
        // Get the dimensions of the board.
        int m = board.length, n = board[0].length;

        // Step 1: Mark boundary connected O's.
        // Iterate through the first and last columns.
        for (int i = 0; i < m; i++) {
            // If an 'O' is found on the left boundary, start DFS to mark it and its connected 'O's.
            dfs(i, 0, board);
            // If an 'O' is found on the right boundary, start DFS to mark it and its connected 'O's.
            dfs(i, n - 1, board);
        }

        // Iterate through the first and last rows.
        for (int j = 0; j < n; j++) {
            // If an 'O' is found on the top boundary, start DFS to mark it and its connected 'O's.
            dfs(0, j, board);
            // If an 'O' is found on the bottom boundary, start DFS to mark it and its connected 'O's.
            dfs(m - 1, j, board);
        }

        // Step 2: Flip remaining O -> X, # -> O.
        // Iterate through the entire board to finalize the changes.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a cell is still 'O', it means it was not connected to any boundary 'O', so it's surrounded. Flip it to 'X'.
                if (board[i][j] == 'O') board[i][j] = 'X';
                // If a cell is '#', it means it was a boundary-connected 'O' that we marked. Restore it back to 'O'.
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    // Helper function for Depth First Search.
    private void dfs(int i, int j, char[][] board) {
        // Get the dimensions of the board.
        int m = board.length, n = board[0].length;

        // Base case for recursion:
        // Check if the current cell is out of bounds OR if it's not an 'O' (it's 'X' or already marked '#').
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return; // Stop the traversal for this path.

        // Mark the current 'O' cell with '#' to indicate it's safe (connected to the boundary).
        board[i][j] = '#';
        
        // Recursively call DFS for adjacent cells (down, up, right, left).
        dfs(i + 1, j, board); // Move down
        dfs(i - 1, j, board); // Move up
        dfs(i, j + 1, board); // Move right
        dfs(i, j - 1, board); // Move left
    }
}
```

## Interview Tips
*   **Explain the "Why":** Clearly articulate why marking boundary-connected 'O's is more efficient than trying to find surrounded regions directly.
*   **Trace an Example:** Walk through a small 3x3 or 4x4 board with a mix of 'X's and 'O's to demonstrate your understanding of the algorithm.
*   **Discuss Alternatives:** Briefly mention BFS as an alternative to DFS and discuss its trade-offs (e.g., iterative vs. recursive, space usage).
*   **Handle Edge Cases:** Be prepared to discuss how your solution handles empty boards, boards with only 'X's, or boards with only 'O's.

## Revision Checklist
- [ ] Understand the problem: capture 'O's not connected to the boundary.
- [ ] Identify boundary 'O's as the starting point for safe regions.
- [ ] Use DFS or BFS to mark all 'O's reachable from the boundary.
- [ ] Use a temporary marker (e.g., '#') for safe 'O's.
- [ ] Iterate through the board to flip remaining 'O's to 'X's.
- [ ] Restore temporary markers back to 'O's.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   Number of Islands
*   Max Area of Island
*   Rotting Oranges
*   Pacific Atlantic Water Flow

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Matrix`

## My Notes
reverse DFS
