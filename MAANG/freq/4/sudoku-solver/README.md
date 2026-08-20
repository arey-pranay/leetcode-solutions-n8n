# Sudoku Solver

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Backtracking` `Matrix` `Algorithm X` `Dancing Links`  
**Time:** O(9^M)  
**Space:** O(M)

---

## Solution (java)

```java
class Solution {
    public void solveSudoku(char[][] board) {
        solveThis(board);
    }
    public boolean solveThis(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(char c = '1'; c<='9';c++){
                        if(isValid(board,i,j,c)){
                            board[i][j]=c;
                            if(solveThis(board)) return true;
                            board[i][j]='.';
                        }    
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(char[][] board, int i, int j, char c){
      for(int k=0;k<9;k++) if(board[i][k] == c || board[k][j] == c || board[3*(i/3) + k%3][3*(j/3) + k/3]==c) return false;
      return true;
    //   i/3 or j/3 only gives 0,1,2 => this tells the position of the 3x3 grid we are in
    //   we need the starting i and starting j of our 3x3 grid, so we multiply it by 3
    //   and to traverse all 9 elements, we need to add 00 01 02 10 etc to our starting i and starting j, so we use k/2 and k%3
    }
}
```

---

---
## Quick Revision
This problem asks to fill a partially filled 9x9 Sudoku grid such that all rows, columns, and 3x3 subgrids contain digits 1-9 exactly once.
The solution uses a backtracking approach to explore possible placements of numbers.

## Intuition
The core idea is to treat the Sudoku board as a constraint satisfaction problem. We need to find a valid assignment of numbers to empty cells. When we encounter an empty cell, we try placing each digit from '1' to '9'. If a digit is valid (doesn't violate Sudoku rules), we tentatively place it and recursively try to solve the rest of the board. If the recursive call succeeds, we've found a solution. If it fails, we backtrack by removing the digit and trying the next one. The "aha moment" is realizing that this systematic trial-and-error with backtracking is guaranteed to find a solution if one exists, because we explore all valid possibilities.

## Algorithm
1.  **Iterate through the board**: Traverse the 9x9 grid row by row, then column by column.
2.  **Find an empty cell**: If the current cell `board[i][j]` is empty (represented by '.'), proceed to step 3. If no empty cells are found, the board is solved, return `true`.
3.  **Try digits '1' to '9'**: For the empty cell `board[i][j]`, iterate through characters `c` from '1' to '9'.
4.  **Check validity**: For each character `c`, call a helper function `isValid(board, i, j, c)` to check if placing `c` at `board[i][j]` is valid according to Sudoku rules (no duplicates in row, column, or 3x3 subgrid).
5.  **Place and recurse**: If `isValid` returns `true`:
    *   Place `c` in `board[i][j]`.
    *   Recursively call `solveThis(board)` to try and solve the rest of the board.
    *   If the recursive call returns `true`, it means a solution was found, so return `true` immediately.
6.  **Backtrack**: If the recursive call returns `false` (meaning placing `c` did not lead to a solution), reset `board[i][j]` back to '.' and continue to the next character `c`.
7.  **No solution for this cell**: If all digits from '1' to '9' have been tried for `board[i][j]` and none led to a solution, return `false`.
8.  **`isValid` helper function**:
    *   Checks the current row `i` for duplicates of `c`.
    *   Checks the current column `j` for duplicates of `c`.
    *   Checks the 3x3 subgrid containing `(i, j)` for duplicates of `c`. The starting row of the 3x3 grid is `(i / 3) * 3` and the starting column is `(j / 3) * 3`. Iterate through the 9 cells of this subgrid.
    *   If `c` is found in any of these checks, return `false`. Otherwise, return `true`.

## Concept to Remember
*   **Backtracking**: A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion**: The process of defining a problem in terms of itself. In this case, solving a Sudoku is defined in terms of solving smaller Sudoku subproblems.
*   **Constraint Satisfaction**: Problems where a solution must satisfy certain conditions or constraints. Sudoku is a classic example.

## Common Mistakes
*   **Incorrect `isValid` logic**: Errors in calculating the 3x3 subgrid indices or missing one of the three checks (row, column, subgrid).
*   **Forgetting to backtrack**: Not resetting the cell `board[i][j]` to '.' after a failed recursive call, which corrupts the board state for subsequent attempts.
*   **Infinite recursion**: If the base case (board is full) is not handled correctly, or if the logic for finding empty cells is flawed.
*   **Inefficient `isValid`**: Repeatedly scanning the entire row, column, and subgrid for every single check can be optimized, though for a 9x9 grid, it's often acceptable.

## Complexity Analysis
*   **Time**: O(9^M), where M is the number of empty cells. In the worst case, for each empty cell, we might try up to 9 digits, and the depth of recursion can be up to M. Since M can be at most 81, this is technically exponential. However, due to the constraints of Sudoku, the actual performance is much better in practice. A tighter bound is often considered O(1) because the board size is fixed at 9x9, meaning the maximum number of operations is bounded by a constant.
*   **Space**: O(M) or O(1) for the recursion stack. In the worst case, the recursion depth can be up to M (number of empty cells). Since M <= 81, this is O(1) as the board size is fixed. The board itself is modified in-place, so it doesn't add to the auxiliary space complexity.

## Commented Code
```java
class Solution {
    // The main function to initiate the Sudoku solving process.
    public void solveSudoku(char[][] board) {
        // Calls the recursive helper function to solve the board.
        solveThis(board);
    }

    // Recursive helper function that attempts to solve the Sudoku board.
    // Returns true if a solution is found, false otherwise.
    public boolean solveThis(char[][] board){
        // Iterate through each row of the board.
        for(int i=0;i<9;i++){
            // Iterate through each column of the board.
            for(int j=0;j<9;j++){
                // Check if the current cell is empty (represented by '.').
                if(board[i][j]=='.'){
                    // If the cell is empty, try placing digits from '1' to '9'.
                    for(char c = '1'; c<='9';c++){
                        // Check if placing character 'c' at board[i][j] is valid according to Sudoku rules.
                        if(isValid(board,i,j,c)){
                            // If valid, tentatively place 'c' in the current cell.
                            board[i][j]=c;
                            // Recursively call solveThis to try and solve the rest of the board.
                            if(solveThis(board)) return true; // If the recursive call returns true, a solution is found.
                            // If the recursive call returns false, it means placing 'c' here did not lead to a solution.
                            // So, backtrack: reset the current cell to empty.
                            board[i][j]='.';
                        }    
                    }
                    // If no digit from '1' to '9' can be placed in this empty cell to form a valid solution,
                    // then the current path is invalid. Return false to trigger backtracking in the caller.
                    return false;
                }
            }
        }
        // If the loops complete without finding any empty cells, it means the board is fully solved.
        return true;
    }

    // Helper function to check if placing character 'c' at board[row][col] is valid.
    public boolean isValid(char[][] board, int row, int col, char c){
      // Iterate through all 9 cells in the current row, column, and 3x3 subgrid.
      for(int k=0;k<9;k++){
          // Check if 'c' already exists in the current row (board[row][k]).
          // Check if 'c' already exists in the current column (board[k][col]).
          // Check if 'c' already exists in the current 3x3 subgrid.
          // The expression 3*(row/3) + k/3 calculates the row index within the 3x3 subgrid.
          // The expression 3*(col/3) + k/3 calculates the column index within the 3x3 subgrid.
          // Example: For cell (4,5), row/3 = 1, col/3 = 1. The 3x3 grid starts at (3,3).
          // k=0: (3+0, 3+0) -> (3,3)
          // k=1: (3+0, 3+1) -> (3,4)
          // k=2: (3+0, 3+2) -> (3,5)
          // k=3: (3+1, 3+0) -> (4,3)
          // ... and so on, covering all 9 cells of the 3x3 subgrid.
          if(board[row][k] == c || board[k][col] == c || board[3*(row/3) + k/3][3*(col/3) + k/3]==c) {
              // If 'c' is found in any of these checks, it's an invalid placement.
              return false;
          }
      }
      // If 'c' was not found in the row, column, or 3x3 subgrid, the placement is valid.
      return true;
    }
}
```

## Interview Tips
1.  **Explain Backtracking Clearly**: Before coding, articulate the backtracking strategy: find an empty cell, try a valid number, recurse, and backtrack if the path fails.
2.  **Walk Through `isValid`**: Be prepared to explain how you determine the 3x3 subgrid indices and why your checks cover all Sudoku rules. Drawing a small 3x3 grid on the whiteboard can help.
3.  **Discuss Base Case and Recursive Step**: Clearly identify the base case (board is full) and the recursive step (placing a number and calling the function again).
4.  **Mention Time/Space Complexity**: Be ready to discuss the exponential nature of backtracking but also why it's often treated as O(1) for fixed-size problems like Sudoku.

## Revision Checklist
- [ ] Understand the Sudoku rules (row, column, 3x3 subgrid uniqueness).
- [ ] Implement the backtracking logic correctly.
- [ ] Ensure the `isValid` function accurately checks all three constraints.
- [ ] Verify that backtracking (resetting the cell) is performed when a path fails.
- [ ] Identify the base case for the recursion.
- [ ] Analyze time and space complexity.

## Similar Problems
*   N-Queens
*   Word Search
*   Combination Sum
*   Generate Parentheses

## Tags
`Array` `Hash Map` `Backtracking` `Recursion` `Matrix`
