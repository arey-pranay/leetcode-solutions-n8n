# N Queens Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Backtracking`  
**Time:** O(N!)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    int ans = 0;
    int N;
    boolean[][] board;
    public int totalNQueens(int n) {
        N=n;
        board = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = false;
            }
        }
        traverseRow(0);
        return ans;
    }
    
    private void traverseRow(int row){
        if(row==N){ans++; return;}
        for(int col=0;col<N;col++){
            if(isSafe(row,col)){
                board[row][col]=true;
                traverseRow(row+1);
                board[row][col]=false;
            }
        }
    }
    private boolean isSafe(int x, int y){
        int i, j; //checking | ans -
        for(i=0; i<N; i++) if(board[i][y] || board[x][i]) return false;

        i=x; j=y; // checking /
        while(i>=0 && j<N) if(board[i--][j++]) return false;

        i=x; j=y; // checking \
        while(i>=0 && j>=0) if(board[i--][j--]) return false;

        return true;
    }
}

// Solution s = new Solution();
// while(i<1000){s.totalNQueens(tc[i])==expected[i];}
```

---

---
## Quick Revision
The problem asks to count the number of distinct solutions to place N queens on an N x N chessboard such that no two queens threaten each other. This is solved using a backtracking approach.

## Intuition
The core idea is to place queens row by row. For each row, we try to place a queen in every possible column. Before placing a queen, we must check if that position is "safe" – meaning it's not under attack by any previously placed queens. If a position is safe, we place the queen and recursively try to place queens in the next row. If we successfully place queens in all N rows, we've found a valid solution and increment our count. If a path leads to a dead end (no safe spot in a row), we backtrack by removing the last placed queen and trying a different column.

## Algorithm
1. Initialize a counter `ans` to 0, representing the total number of valid solutions.
2. Initialize an N x N boolean `board` to represent the chessboard, where `true` means a queen is present and `false` means it's empty.
3. Create a recursive helper function `traverseRow(row)`:
    a. **Base Case:** If `row` equals `N` (meaning we have successfully placed queens in all rows from 0 to N-1), increment `ans` and return.
    b. **Recursive Step:** Iterate through each `col` from 0 to `N-1` in the current `row`:
        i. Check if placing a queen at `(row, col)` is safe using an `isSafe(row, col)` function.
        ii. If it's safe:
            - Place a queen: `board[row][col] = true`.
            - Recursively call `traverseRow(row + 1)` to try placing a queen in the next row.
            - Backtrack: Remove the queen: `board[row][col] = false`.
4. The `isSafe(x, y)` function checks for conflicts:
    a. **Column Check:** Iterate through all rows `i` from 0 to `N-1`. If `board[i][y]` is `true`, return `false` (queen in the same column).
    b. **Diagonal Check (Top-Left to Bottom-Right):** Iterate diagonally upwards and to the left from `(x, y)`. If `board[i][j]` is `true` at any point, return `false`.
    c. **Diagonal Check (Top-Right to Bottom-Left):** Iterate diagonally upwards and to the right from `(x, y)`. If `board[i][j]` is `true` at any point, return `false`.
    d. If no conflicts are found, return `true`.
5. Call `traverseRow(0)` to start the process from the first row.
6. Return `ans`.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion:** A programming technique where a function calls itself to solve smaller instances of the same problem.
*   **State Representation:** Effectively representing the current state of the chessboard and queen placements.
*   **Constraint Satisfaction:** Ensuring that each placement adheres to the rules of the N-Queens problem.

## Common Mistakes
*   **Incorrect `isSafe` Logic:** Missing one or more of the diagonal checks, or incorrectly implementing the loop bounds for diagonal checks.
*   **Forgetting to Backtrack:** Failing to reset `board[row][col]` to `false` after a recursive call returns, leading to incorrect state for subsequent explorations.
*   **Off-by-One Errors:** Incorrectly handling the base case (`row == N`) or loop conditions in `traverseRow` or `isSafe`.
*   **Inefficient `isSafe`:** Re-calculating occupied columns/diagonals repeatedly instead of using a more optimized approach (though the provided solution's `isSafe` is standard for this problem's typical implementation).

## Complexity Analysis
- Time: O(N!) - In the worst case, we explore a significant portion of the N! permutations of queen placements. For each placement, `isSafe` takes O(N) time. However, due to pruning, it's much better than N! * N, but still exponential.
- Space: O(N^2) - For the `board` representation. The recursion depth can also go up to O(N), contributing to the call stack space.

## Commented Code
```java
class Solution {
    // Variable to store the total count of valid N-Queens solutions.
    int ans = 0;
    // Variable to store the size of the chessboard (N x N).
    int N;
    // 2D boolean array representing the chessboard. board[i][j] is true if a queen is at (i, j).
    boolean[][] board;

    // Main function to initiate the N-Queens solving process.
    public int totalNQueens(int n) {
        // Set the board size.
        N = n;
        // Initialize the board with dimensions N x N.
        board = new boolean[n][n];
        // Initialize all cells on the board to false (no queens initially).
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = false;
            }
        }
        // Start the backtracking process from the first row (row 0).
        traverseRow(0);
        // Return the total count of solutions found.
        return ans;
    }

    // Recursive helper function to place queens row by row.
    private void traverseRow(int row) {
        // Base case: If we have successfully placed queens in all N rows (row index reaches N).
        if (row == N) {
            // Increment the solution count.
            ans++;
            // Return to explore other possibilities.
            return;
        }
        // Iterate through each column in the current row.
        for (int col = 0; col < N; col++) {
            // Check if placing a queen at (row, col) is safe (no conflicts with existing queens).
            if (isSafe(row, col)) {
                // Place a queen at (row, col).
                board[row][col] = true;
                // Recursively call traverseRow for the next row (row + 1).
                traverseRow(row + 1);
                // Backtrack: Remove the queen from (row, col) to explore other possibilities.
                board[row][col] = false;
            }
        }
    }

    // Function to check if placing a queen at (x, y) is safe.
    private boolean isSafe(int x, int y) {
        int i, j; // Declare loop variables.

        // Check for queens in the same column (vertically upwards).
        // Iterate through all rows 'i' in the current column 'y'.
        for (i = 0; i < N; i++) {
            // If a queen is found in the same column (board[i][y] is true), it's not safe.
            if (board[i][y]) return false;
        }
        // The original code also checks board[x][i] which is redundant for column check as we are placing row by row.
        // It would be more efficient to only check board[i][y].
        // However, if the problem was to place queens anywhere, this would be necessary.
        // For this specific row-by-row placement, the check board[x][i] is implicitly handled by the loop structure.
        // Let's assume the intent was to check the current row as well, though it's not strictly needed if we place one queen per row.
        // The provided code checks board[x][i] which means it checks the current row.
        // Since we place one queen per row, this check is only relevant if we were to place multiple queens in the same row, which we don't.
        // For clarity and correctness in the context of row-by-row placement, we can simplify this.
        // However, adhering to the provided solution's logic:
        for(i=0; i<N; i++) {
            if(board[i][y] || board[x][i]) return false; // Checks column and current row.
        }


        // Check for queens in the top-left to bottom-right diagonal.
        // Initialize i and j to the current position (x, y).
        i = x; j = y;
        // Move diagonally upwards and to the right (i decreases, j increases).
        while (i >= 0 && j < N) {
            // If a queen is found on this diagonal, it's not safe.
            if (board[i--][j++]) return false;
        }

        // Check for queens in the top-right to bottom-left diagonal.
        // Initialize i and j to the current position (x, y).
        i = x; j = y;
        // Move diagonally upwards and to the left (i decreases, j decreases).
        while (i >= 0 && j >= 0) {
            // If a queen is found on this diagonal, it's not safe.
            if (board[i--][j--]) return false;
        }

        // If no conflicts were found in the column or diagonals, the position is safe.
        return true;
    }
}
```

## Interview Tips
*   **Explain Backtracking Clearly:** Articulate the process of "try, recurse, backtrack" and why it's suitable for this problem.
*   **Visualize the `isSafe` Checks:** Be prepared to draw the board and explain how the diagonal and column checks work.
*   **Discuss Optimization (Optional but good):** Mention that `isSafe` can be optimized using hash sets or boolean arrays to track occupied columns and diagonals in O(1) time, reducing the overall time complexity of `isSafe` checks.
*   **Handle Edge Cases:** Briefly mention what happens for N=1 or N=0 (though N=0 is usually not tested).

## Revision Checklist
- [ ] Understand the N-Queens problem constraints.
- [ ] Implement backtracking correctly with base case and recursive step.
- [ ] Ensure the `isSafe` function correctly checks columns and both diagonals.
- [ ] Verify that backtracking (unsetting the queen) is performed.
- [ ] Analyze time and space complexity.

## Similar Problems
*   N-Queens (LeetCode 51)
*   Sudoku Solver (LeetCode 37)
*   Combinations (LeetCode 77)
*   Permutations (LeetCode 46)

## Tags
`Backtracking` `Recursion` `Array` `Depth-First Search`
