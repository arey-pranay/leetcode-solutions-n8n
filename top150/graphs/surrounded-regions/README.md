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
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        int m = board.length, n = board[0].length;

        // boundary + condition check
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = '#'; // mark safe

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
The problem asks to capture all 'O' regions that are completely surrounded by 'X's.
We solve this by identifying 'O's connected to the border and marking them, then flipping the rest.

## Intuition
The key insight is that any 'O' that is *not* surrounded must be connected to an 'O' on the border of the grid. If an 'O' can reach the border, it's safe. All other 'O's are surrounded. So, we can start from the borders and explore all reachable 'O's. These are the "safe" 'O's. Any 'O's that remain unmarked after this exploration must be surrounded and can be flipped to 'X'.

## Algorithm
1.  **Identify Border-Connected 'O's:** Iterate through all cells on the four borders of the grid (top row, bottom row, left column, right column).
2.  **Mark Safe 'O's:** If a border cell contains an 'O', start a Depth First Search (DFS) or Breadth First Search (BFS) from that cell. During the traversal, mark every visited 'O' with a temporary character (e.g., '#') to indicate it's connected to the border and thus "safe".
3.  **Flip Surrounded 'O's:** After marking all border-connected 'O's, iterate through the entire grid.
    *   If a cell contains the temporary marker ('#'), change it back to 'O' (it's safe).
    *   If a cell still contains an 'O', it means it was not reachable from the border, so it's surrounded. Flip it to 'X'.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** This problem can be modeled as finding connected components in a graph where adjacent 'O's are connected. DFS or BFS is ideal for exploring these components.
*   **In-place Modification:** The problem requires modifying the input grid directly, which is a common technique to save space.
*   **Boundary Conditions:** Carefully handling grid boundaries is crucial to avoid index out-of-bounds errors.
*   **Marking Visited Nodes:** Using a temporary marker is essential to distinguish between safe 'O's and those that need to be flipped, and to prevent infinite loops in traversal.

## Common Mistakes
*   **Flipping 'O's without checking border connection:** Directly flipping all 'O's to 'X's and then trying to revert them is inefficient and complex. The correct approach is to identify what *not* to flip first.
*   **Not handling boundary cases correctly in DFS/BFS:** Forgetting to check if `i` or `j` are within grid bounds before accessing `board[i][j]`.
*   **Using recursion without a proper base case or visited check:** This can lead to stack overflow errors or infinite recursion.
*   **Modifying the grid in a way that interferes with subsequent traversals:** For example, flipping an 'O' to 'X' too early might prevent a connected 'O' from being discovered.

## Complexity Analysis
*   **Time:** O(m * n) - Each cell in the grid is visited at most a constant number of times (once during the border traversal/DFS and once during the final flip).
*   **Space:** O(m * n) - In the worst case, the recursion depth of DFS can be proportional to the number of cells in the grid if the grid is filled with 'O's and forms a long path. This is for the call stack. If BFS were used with a queue, it would also be O(m*n) in the worst case.

## Commented Code
```java
class Solution {
    public void solve(char[][] board) {
        // Get the dimensions of the board.
        int m = board.length, n = board[0].length;

        // Step 1: Mark boundary connected O's.
        // Iterate through the first and last columns to find 'O's connected to the border.
        for (int i = 0; i < m; i++) {
            // Check the leftmost column.
            dfs(i, 0, board);
            // Check the rightmost column.
            dfs(i, n - 1, board);
        }

        // Iterate through the first and last rows to find 'O's connected to the border.
        for (int j = 0; j < n; j++) {
            // Check the topmost row.
            dfs(0, j, board);
            // Check the bottommost row.
            dfs(m - 1, j, board);
        }

        // Step 2: Flip remaining O -> X, # -> O.
        // Iterate through the entire board to finalize the changes.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a cell is still 'O', it means it was not reached from the border, so it's surrounded. Flip it to 'X'.
                if (board[i][j] == 'O') board[i][j] = 'X';
                // If a cell is marked with '#', it means it was connected to the border and is safe. Revert it back to 'O'.
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    // Helper function for Depth First Search (DFS) to mark connected 'O's.
    private void dfs(int i, int j, char[][] board) {
        // Get the dimensions of the board.
        int m = board.length, n = board[0].length;

        // Boundary and condition check:
        // If the current cell is out of bounds (i < 0, j < 0, i >= m, j >= n)
        // OR if the current cell is not an 'O' (it's 'X' or already marked '#'), then return.
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;

        // Mark the current 'O' cell with '#' to indicate it's safe (connected to the border).
        board[i][j] = '#';

        // Recursively call DFS for adjacent cells (up, down, left, right).
        dfs(i + 1, j, board); // Move down
        dfs(i - 1, j, board); // Move up
        dfs(i, j + 1, board); // Move right
        dfs(i, j - 1, board); // Move left
    }
}
```

## Interview Tips
*   **Explain the "Why":** Clearly articulate why starting from the border is the correct strategy. Emphasize that any 'O' not connected to the border is by definition surrounded.
*   **Discuss DFS vs. BFS:** Be prepared to explain both DFS and BFS approaches and their trade-offs (e.g., DFS uses call stack space, BFS uses queue space). For this problem, either is fine.
*   **Edge Cases:** Mention handling empty boards or boards with only one row/column. Also, consider a board entirely filled with 'X's or 'O's.
*   **In-place Modification:** Highlight that the solution modifies the board in-place, which is often a desirable constraint for space efficiency.

## Revision Checklist
- [ ] Understand the problem: capture surrounded 'O's.
- [ ] Identify the core idea: 'O's connected to the border are safe.
- [ ] Choose a traversal method: DFS or BFS.
- [ ] Implement border traversal: iterate through all border cells.
- [ ] Implement DFS/BFS: mark reachable 'O's with a temporary character.
- [ ] Implement final pass: flip remaining 'O's to 'X' and temporary markers back to 'O'.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   Number of Islands
*   Max Area of Island
*   Rotting Oranges
*   Pacific Atlantic Water Flow

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Matrix`
