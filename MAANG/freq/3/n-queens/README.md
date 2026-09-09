# N Queens

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Backtracking` `Algorithm X`  
**Time:** O(N!)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    boolean[][] borad;
    int N;
    List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        borad = new boolean[n][n];
        N=n;
        ans = new ArrayList<>();
        fill(0);
        return ans;
    }
    public void fill(int row){
        if(row==N){
            List<String> state = new ArrayList<>();
            for(int i=0;i<N;i++){
                StringBuilder sb = new StringBuilder("");
                for(int j=0;j<N;j++){
                    char c = borad[i][j] ? 'Q' : '.';
                    sb.append(c);
                }
                state.add(sb.toString());
            }
            ans.add(state);
            return;
        }
        
        for(int col=0;col<N;col++){
            if(rowColOk(row,col) && diagOk(row,col)){
                borad[row][col] = true;
                fill(row+1);
                borad[row][col] = false;
            }   
        }  
    }
    public boolean rowColOk(int row , int col){
        for(int i=0;i<N;i++) if(borad[i][col] || borad[row][i]) return false;
        return true;
    }
    public boolean diagOk(int row , int col){
        for(int i=0;i<=row;i++) for(int j=0;j<N;j++) if(Math.abs(i-row)==Math.abs(j-col)) if(borad[i][j]) return false;
        return true;
    }
}

```

---

---
## Quick Revision
The N-Queens problem asks to place N chess queens on an N×N chessboard such that no two queens threaten each other. This is solved using backtracking by placing queens row by row and checking for conflicts.

## Intuition
The core idea is to explore all possible placements of queens systematically. Since each queen must be in a different row and column, we can iterate through rows and try placing a queen in each column of that row. If a placement is valid (doesn't conflict with previously placed queens), we move to the next row. If we successfully place queens in all N rows, we've found a solution. If a placement leads to a dead end (no valid column in the current row), we backtrack and try a different column in the previous row.

## Algorithm
1.  Initialize an N×N chessboard (e.g., a 2D boolean array) to all empty cells.
2.  Initialize an empty list to store all valid solutions.
3.  Define a recursive function `solve(row)` that attempts to place a queen in the given `row`.
    *   **Base Case:** If `row == N` (all rows have been filled), construct the current board state as a list of strings and add it to the list of solutions. Then, return.
    *   **Recursive Step:** Iterate through each `col` from 0 to N-1 in the current `row`.
        *   Check if placing a queen at `(row, col)` is valid (i.e., it doesn't conflict with any queens in previous rows).
        *   If valid:
            *   Place a queen at `(row, col)` on the board.
            *   Recursively call `solve(row + 1)` to place a queen in the next row.
            *   **Backtrack:** Remove the queen from `(row, col)` (reset the board cell to empty) to explore other possibilities.
4.  The `isValid(row, col)` function checks for conflicts:
    *   **Column Conflict:** Check if any queen is already placed in the same `col` in previous rows.
    *   **Diagonal Conflict:** Check if any queen is already placed on the same diagonals (both main and anti-diagonal) in previous rows.
5.  Start the process by calling `solve(0)`.
6.  Return the list of all solutions.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Recursion:** The process of a function calling itself, essential for exploring the decision tree in backtracking.
*   **State Representation:** How to efficiently represent the chessboard and queen placements.

## Common Mistakes
*   **Inefficient Conflict Checking:** Checking the entire board for conflicts in `isValid` can be slow. Optimizing this check is crucial.
*   **Forgetting to Backtrack:** Failing to reset the board state after a recursive call returns leads to incorrect solutions.
*   **Incorrect Diagonal Check:** Miscalculating or missing one of the diagonal conditions.
*   **Off-by-One Errors:** In loop bounds or array indexing, especially during conflict checks.

## Complexity Analysis
- Time: O(N!) - In the worst case, we explore a decision tree where at each of the N rows, we have up to N choices. However, due to pruning (invalid placements), it's significantly less than N^N. The exact complexity is closer to O(N!), as for each valid placement, we do O(N) work to check validity and O(N^2) to construct the solution.
- Space: O(N^2) - For storing the chessboard itself and the recursion call stack, which can go up to N levels deep. Each solution also takes O(N^2) space to store.

## Commented Code
```java
class Solution {
    // Declare a 2D boolean array to represent the chessboard. 'true' means a queen is present.
    boolean[][] borad;
    // Store the size of the board (N x N).
    int N;
    // List to store all valid solutions. Each solution is a List of Strings representing the board.
    List<List<String>> ans;

    // The main function to solve the N-Queens problem.
    public List<List<String>> solveNQueens(int n) {
        // Initialize the board with the given size n.
        borad = new boolean[n][n];
        // Set the global variable N to the board size.
        N = n;
        // Initialize the list to store the answers.
        ans = new ArrayList<>();
        // Start the recursive backtracking process from the first row (row 0).
        fill(0);
        // Return the list of all found solutions.
        return ans;
    }

    // Recursive function to place queens row by row.
    public void fill(int row) {
        // Base case: If we have successfully placed queens in all N rows.
        if (row == N) {
            // Create a new list to store the current board configuration as strings.
            List<String> state = new ArrayList<>();
            // Iterate through each row of the board.
            for (int i = 0; i < N; i++) {
                // Use StringBuilder for efficient string construction.
                StringBuilder sb = new StringBuilder("");
                // Iterate through each column in the current row.
                for (int j = 0; j < N; j++) {
                    // Determine the character to append: 'Q' if a queen is present, '.' otherwise.
                    char c = borad[i][j] ? 'Q' : '.';
                    // Append the character to the StringBuilder.
                    sb.append(c);
                }
                // Add the constructed string representation of the row to the current state.
                state.add(sb.toString());
            }
            // Add the complete board configuration (a valid solution) to the answer list.
            ans.add(state);
            // Return from this recursive call as a solution has been found.
            return;
        }

        // Recursive step: Try placing a queen in each column of the current 'row'.
        for (int col = 0; col < N; col++) {
            // Check if placing a queen at (row, col) is valid (no conflicts).
            // The original code combines row/col and diagonal checks into two separate functions.
            // A more optimized approach would use sets/arrays to track occupied columns and diagonals.
            if (rowColOk(row, col) && diagOk(row, col)) {
                // If it's safe to place a queen, mark the cell as occupied.
                borad[row][col] = true;
                // Recursively call 'fill' for the next row (row + 1).
                fill(row + 1);
                // Backtrack: After exploring possibilities from this placement, unmark the cell.
                // This allows exploration of other column placements in the current row.
                borad[row][col] = false;
            }
        }
    }

    // Helper function to check for conflicts in the same column and same row.
    // Note: This implementation is inefficient as it re-checks rows and columns repeatedly.
    // A better approach would use boolean arrays/sets to track occupied columns and diagonals.
    public boolean rowColOk(int row, int col) {
        // Check for queens in the same column (iterate through all rows 'i' for the current 'col').
        for (int i = 0; i < N; i++) {
            if (borad[i][col]) {
                return false; // Conflict found in the same column.
            }
        }
        // Check for queens in the same row (iterate through all columns 'i' for the current 'row').
        // This check is actually redundant if we are placing queens row by row, as we only place one queen per row.
        // However, the original code includes it.
        for (int i = 0; i < N; i++) {
            if (borad[row][i]) {
                return false; // Conflict found in the same row.
            }
        }
        // If no conflicts are found in the column or row, return true.
        return true;
    }

    // Helper function to check for conflicts on diagonals.
    // Note: This implementation is also inefficient as it iterates through a large portion of the board.
    public boolean diagOk(int row, int col) {
        // Iterate through all cells (i, j) on the board.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Check if the cell (i, j) is on the same diagonal as (row, col).
                // Two cells (r1, c1) and (r2, c2) are on the same diagonal if |r1 - r2| == |c1 - c2|.
                if (Math.abs(i - row) == Math.abs(j - col)) {
                    // If a queen is found on this diagonal and it's not the current cell itself (which is implicitly handled by checking borad[i][j]).
                    if (borad[i][j]) {
                        return false; // Conflict found on a diagonal.
                    }
                }
            }
        }
        // If no diagonal conflicts are found, return true.
        return true;
    }
}
```

## Interview Tips
*   **Explain Backtracking Clearly:** Walk the interviewer through the recursive process, emphasizing the "choice, explore, un-choice" (place, recurse, un-place) pattern.
*   **Discuss Optimization:** The provided `rowColOk` and `diagOk` are inefficient. Be prepared to discuss how to optimize conflict checking using boolean arrays or hash sets for columns and diagonals (e.g., `cols[c]`, `diag1[r+c]`, `diag2[r-c+N-1]`). This shows a deeper understanding.
*   **Trace an Example:** For N=4, trace the execution for the first few steps to demonstrate your understanding of the algorithm's flow.
*   **Edge Cases:** Mention N=1 (trivial solution) and N=2, N=3 (no solutions) as simple cases.

## Revision Checklist
- [ ] Understand the N-Queens problem statement.
- [ ] Implement a backtracking solution.
- [ ] Correctly handle base cases and recursive steps.
- [ ] Implement valid conflict checking (column, row, diagonals).
- [ ] Ensure proper backtracking (un-placing queens).
- [ ] Understand and explain time and space complexity.
- [ ] Be ready to discuss optimizations for conflict checking.

## Similar Problems
*   Permutations
*   Combinations
*   Subsets
*   Sudoku Solver
*   M-Coloring Problem

## Tags
`Array` `Backtracking` `Recursion` `Matrix`
