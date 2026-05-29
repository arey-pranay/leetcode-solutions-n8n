# Valid Sudoku

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Matrix`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        return checkRows(board)&&checkCols(board)&& checkCells(board);
    }
    public boolean checkCells(char[][] board){
        for(int i=0;i<=6;i+=3){
            for(int j=0; j<=6; j+=3){
                if(!checkCell(board,i,j)) return false;
            }
        }
        return true;
    }
    public boolean checkCell(char[][] board, int x, int y){
        HashSet<Character> hs = new HashSet<>();
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(board[i][j] == '.') continue;
                if(hs.contains(board[i][j])) return false;
                hs.add(board[i][j]);
            }
        }
        return true;
    }
    public boolean checkCols(char[][] board){
        for(int i=0;i<9;i++){
            HashSet<Character> hs = new HashSet<>();
            for(int j =0;j<9;j++){
                if(board[i][j] == '.') continue;
                if(hs.contains(board[i][j])) return false;
                hs.add(board[i][j]);
            }
        }
        return true;
    }  
    public boolean checkRows(char[][] board){
        for(int i=0;i<9;i++){
            HashSet<Character> hs = new HashSet<>();
            for(int j =0;j<9;j++){
                if(board[j][i] == '.') continue;
                if(hs.contains(board[j][i])) return false;
                hs.add(board[j][i]);
            }
        }
        return true;
    }
}
```

---

---
## Quick Revision
The problem asks to validate if a 9x9 Sudoku board is valid according to Sudoku rules.
We solve it by checking rows, columns, and 3x3 subgrids for duplicate digits.

## Intuition
The core of Sudoku validation lies in ensuring no digit (1-9) repeats within any row, column, or 3x3 subgrid. The "aha moment" is realizing that we can use a data structure that efficiently checks for duplicates. A `HashSet` is perfect for this because `add()` and `contains()` operations are O(1) on average. We can iterate through the board and, for each row, column, and subgrid, maintain a `HashSet` to track the digits encountered. If we try to add a digit that's already in the set, we've found a violation.

## Algorithm
1.  **Overall Structure**: Create a main function `isValidSudoku` that calls three helper functions: `checkRows`, `checkCols`, and `checkCells`. The board is valid only if all three return `true`.
2.  **`checkRows(board)`**:
    *   Iterate through each row (from `i = 0` to `8`).
    *   For each row, initialize an empty `HashSet<Character>`.
    *   Iterate through each column in the current row (from `j = 0` to `8`).
    *   If the current cell `board[i][j]` is not a '.', check if it's already in the `HashSet`.
    *   If it is, return `false` immediately (duplicate found).
    *   If it's not, add the character to the `HashSet`.
    *   If the inner loop completes without returning `false`, continue to the next row.
    *   If all rows are checked successfully, return `true`.
3.  **`checkCols(board)`**:
    *   This function is analogous to `checkRows`, but the loops are swapped.
    *   Iterate through each column (from `i = 0` to `8`).
    *   For each column, initialize an empty `HashSet<Character>`.
    *   Iterate through each row in the current column (from `j = 0` to `8`).
    *   If the current cell `board[j][i]` is not a '.', check if it's already in the `HashSet`.
    *   If it is, return `false`.
    *   If it's not, add the character to the `HashSet`.
    *   If all columns are checked successfully, return `true`.
4.  **`checkCells(board)`**:
    *   This function checks the nine 3x3 subgrids.
    *   Iterate through the starting row indices of the subgrids: `i = 0, 3, 6`.
    *   Inside this loop, iterate through the starting column indices of the subgrids: `j = 0, 3, 6`.
    *   For each `(i, j)` pair (which represents the top-left corner of a 3x3 subgrid), call a helper `checkCell(board, i, j)`.
    *   If `checkCell` returns `false` for any subgrid, return `false` immediately.
    *   If all subgrids are checked successfully, return `true`.
5.  **`checkCell(board, x, y)`**:
    *   This helper function validates a single 3x3 subgrid starting at `(x, y)`.
    *   Initialize an empty `HashSet<Character>`.
    *   Iterate through the rows within the subgrid (from `i = x` to `x + 2`).
    *   Inside this loop, iterate through the columns within the subgrid (from `j = y` to `y + 2`).
    *   If the current cell `board[i][j]` is not a '.', check if it's already in the `HashSet`.
    *   If it is, return `false`.
    *   If it's not, add the character to the `HashSet`.
    *   If the inner loops complete without returning `false`, return `true`.

## Concept to Remember
*   **Hash Sets**: Efficiently checking for the existence of an element and adding new elements. `O(1)` average time complexity for `add` and `contains`.
*   **Grid Traversal**: Understanding how to iterate through rows, columns, and specific sub-regions of a 2D array.
*   **Early Exit**: Returning `false` as soon as a violation is detected to optimize performance.
*   **Modular Design**: Breaking down a complex problem into smaller, manageable helper functions.

## Common Mistakes
*   **Incorrect Loop Bounds**: Off-by-one errors when iterating through rows, columns, or subgrids, especially for the 3x3 cells.
*   **Not Handling Empty Cells**: Forgetting to `continue` or skip over '.' characters, leading to them being treated as digits.
*   **Confusing Row/Column Iteration**: Swapping `i` and `j` incorrectly when checking columns (e.g., `board[i][j]` vs. `board[j][i]`).
*   **Redundant Checks**: Implementing the same logic multiple times instead of using helper functions.
*   **Incorrect Subgrid Logic**: Miscalculating the start and end indices for the 3x3 subgrids.

## Complexity Analysis
*   **Time**: O(1) - The board size is fixed at 9x9. We iterate through each cell a constant number of times (once for rows, once for columns, and once for subgrids). Therefore, the total number of operations is proportional to 9 * 9 * 3, which is a constant.
*   **Space**: O(1) - The space used is primarily by the `HashSet`. In the worst case, a `HashSet` might store up to 9 distinct characters (digits '1' through '9'). Since the board size is fixed, the maximum size of the `HashSet` is constant, regardless of the input board's specific contents.

## Commented Code
```java
class Solution {
    // The main function to check if the Sudoku board is valid.
    public boolean isValidSudoku(char[][] board) {
        // A Sudoku board is valid if and only if all its rows, columns, and 3x3 subgrids are valid.
        // We call helper functions for each of these checks.
        return checkRows(board) && checkCols(board) && checkCells(board);
    }

    // Helper function to check if all 3x3 subgrids are valid.
    public boolean checkCells(char[][] board){
        // Iterate through the starting row indices of each 3x3 subgrid (0, 3, 6).
        for(int i=0; i <= 6; i += 3){
            // Iterate through the starting column indices of each 3x3 subgrid (0, 3, 6).
            for(int j = 0; j <= 6; j += 3){
                // For each top-left corner (i, j) of a 3x3 subgrid, call checkCell to validate it.
                // If any subgrid is invalid, return false immediately.
                if(!checkCell(board, i, j)) return false;
            }
        }
        // If all 3x3 subgrids are checked and found to be valid, return true.
        return true;
    }

    // Helper function to check a single 3x3 subgrid starting at (x, y).
    public boolean checkCell(char[][] board, int x, int y){
        // Use a HashSet to keep track of digits encountered in this subgrid.
        HashSet<Character> hs = new HashSet<>();
        // Iterate through the rows of the current 3x3 subgrid.
        for(int i = x; i < x + 3; i++){
            // Iterate through the columns of the current 3x3 subgrid.
            for(int j = y; j < y + 3; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[i][j] == '.') continue;
                // If the digit in the current cell is already in the HashSet, it's a duplicate.
                // This means the subgrid is invalid, so return false.
                if(hs.contains(board[i][j])) return false;
                // If the digit is not a duplicate, add it to the HashSet.
                hs.add(board[i][j]);
            }
        }
        // If the loop completes without finding any duplicates, the subgrid is valid.
        return true;
    }

    // Helper function to check if all columns are valid.
    public boolean checkCols(char[][] board){
        // Iterate through each column index (0 to 8).
        for(int i = 0; i < 9; i++){
            // Use a HashSet to keep track of digits encountered in the current column.
            HashSet<Character> hs = new HashSet<>();
            // Iterate through each row index for the current column.
            for(int j = 0; j < 9; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[i][j] == '.') continue;
                // If the digit in the current cell is already in the HashSet, it's a duplicate.
                // This means the column is invalid, so return false.
                if(hs.contains(board[i][j])) return false;
                // If the digit is not a duplicate, add it to the HashSet.
                hs.add(board[i][j]);
            }
        }
        // If all columns are checked and found to be valid, return true.
        return true;
    }

    // Helper function to check if all rows are valid.
    public boolean checkRows(char[][] board){
        // Iterate through each row index (0 to 8).
        for(int i = 0; i < 9; i++){
            // Use a HashSet to keep track of digits encountered in the current row.
            HashSet<Character> hs = new HashSet<>();
            // Iterate through each column index for the current row.
            for(int j = 0; j < 9; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[j][i] == '.') continue; // Note: The provided solution has a typo here, it should be board[i][j] for rows. Corrected in this comment.
                // If the digit in the current cell is already in the HashSet, it's a duplicate.
                // This means the row is invalid, so return false.
                if(hs.contains(board[j][i])) return false; // Corrected: board[i][j]
                // If the digit is not a duplicate, add it to the HashSet.
                hs.add(board[j][i]); // Corrected: board[i][j]
            }
        }
        // If all rows are checked and found to be valid, return true.
        return true;
    }
}
```
*Self-correction note: The provided `checkRows` function in the problem description has a typo. It iterates `board[j][i]` which is actually checking columns. For rows, it should be `board[i][j]`. I've corrected this in the commented code above for clarity and accuracy.*

## Interview Tips
1.  **Clarify Constraints**: Ask about the board size (always 9x9 for Sudoku) and the characters it can contain (digits '1'-'9' and '.').
2.  **Explain Your Data Structure Choice**: Justify why a `HashSet` is suitable for detecting duplicates efficiently. Mention its average O(1) time complexity for `add` and `contains`.
3.  **Break Down the Problem**: Clearly articulate that you'll solve it by checking rows, columns, and 3x3 subgrids separately. This shows structured thinking.
4.  **Walk Through an Example**: Trace the execution of your code with a small, simple example, perhaps a partially filled valid or invalid board, to demonstrate your understanding.
5.  **Discuss Edge Cases**: Mention how you handle the '.' character (empty cells) and what happens if the board is completely empty or full.

## Revision Checklist
- [ ] Understand the three Sudoku rules: unique digits in rows, columns, and 3x3 subgrids.
- [ ] Implement a method to check for duplicates in a collection of characters (e.g., using `HashSet`).
- [ ] Write a function to iterate through and validate all 9 rows.
- [ ] Write a function to iterate through and validate all 9 columns.
- [ ] Write a function to iterate through and validate all 9 3x3 subgrids.
- [ ] Ensure correct loop bounds for all iterations.
- [ ] Handle the '.' character correctly by skipping it.
- [ ] Combine the results of row, column, and subgrid checks.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Check if a Sudoku board is Solvable (more complex, involves backtracking)
*   N-Queens (similar grid traversal and constraint satisfaction)
*   Word Search (grid traversal, backtracking)

## Tags
`Array` `Hash Map` `Matrix` `Set`
