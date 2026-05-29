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
Checks if a 9x9 Sudoku board is valid according to Sudoku rules.
Solves by verifying rows, columns, and 3x3 subgrids for duplicate digits.

## Intuition
The core idea is that each row, each column, and each of the nine 3x3 subgrids must contain the digits 1-9 without repetition. The '.' character represents an empty cell and doesn't violate any rules. We can use a data structure to efficiently track seen digits within each of these units. A HashSet is ideal for this because it provides O(1) average time complexity for insertion and checking for existence.

## Algorithm
1.  **Overall Structure**: The `isValidSudoku` function will orchestrate the checks. It will call three helper functions: `checkRows`, `checkCols`, and `checkCells`. If all three return `true`, the Sudoku is valid.
2.  **`checkRows(board)`**:
    *   Iterate through each row (0 to 8).
    *   For each row, initialize an empty `HashSet` to store digits encountered in that row.
    *   Iterate through each cell in the current row (0 to 8).
    *   If the cell is not '.', check if the digit is already in the `HashSet`.
        *   If it is, return `false` immediately (duplicate found).
        *   If it's not, add the digit to the `HashSet`.
    *   If the inner loop completes without returning `false`, the row is valid.
    *   If all rows are checked and found valid, return `true`.
3.  **`checkCols(board)`**:
    *   This function is analogous to `checkRows`, but it iterates through columns.
    *   Iterate through each column (0 to 8).
    *   For each column, initialize an empty `HashSet`.
    *   Iterate through each cell in the current column (0 to 8).
    *   If the cell is not '.', check for duplicates in the `HashSet` and add if not present. Return `false` on duplicate.
    *   If all columns are checked and found valid, return `true`.
4.  **`checkCells(board)`**:
    *   This function checks the nine 3x3 subgrids.
    *   Iterate through the starting row indices of the 3x3 subgrids: `i = 0, 3, 6`.
    *   Iterate through the starting column indices of the 3x3 subgrids: `j = 0, 3, 6`.
    *   For each `(i, j)` pair (which represents the top-left corner of a 3x3 subgrid), call a helper `checkCell(board, i, j)`.
    *   If `checkCell` returns `false` for any subgrid, return `false` immediately.
    *   If all subgrids are checked and found valid, return `true`.
5.  **`checkCell(board, x, y)`**:
    *   This helper function checks a single 3x3 subgrid starting at `(x, y)`.
    *   Initialize an empty `HashSet`.
    *   Iterate through the rows of the subgrid: `i` from `x` to `x+2`.
    *   Iterate through the columns of the subgrid: `j` from `y` to `y+2`.
    *   If the cell `board[i][j]` is not '.', check for duplicates in the `HashSet` and add if not present. Return `false` on duplicate.
    *   If the subgrid is checked and found valid, return `true`.

## Concept to Remember
*   **Hash Sets**: Efficiently store and check for the existence of unique elements. Crucial for detecting duplicates in O(1) average time.
*   **Grid Traversal**: Understanding how to iterate through rows, columns, and specific sub-regions of a 2D array.
*   **Modular Arithmetic/Pattern Recognition**: Identifying patterns for iterating through subgrids (e.g., `i+=3`, `j+=3`).

## Common Mistakes
*   **Incorrect Subgrid Iteration**: Off-by-one errors or incorrect step sizes when iterating through the 3x3 subgrids.
*   **Ignoring Empty Cells**: Forgetting to skip '.' characters, which would incorrectly flag them as duplicates.
*   **Confusing Row/Column Iteration**: Swapping `i` and `j` indices incorrectly when checking rows vs. columns.
*   **Redundant Checks**: Implementing separate logic for rows, columns, and cells without a clear, unified approach.

## Complexity Analysis
- Time: O(1) - The board size is fixed at 9x9. We iterate through each cell a constant number of times (once for row check, once for column check, once for cell check). Therefore, the time complexity is proportional to the number of cells, which is 81, a constant.
- Space: O(1) - We use HashSets to store digits. In the worst case, a HashSet can store up to 9 digits. Since the board size is fixed, the space used by these HashSets is also constant, regardless of the input board's content (as long as it's 9x9).

## Commented Code
```java
class Solution {
    // The main function that validates the Sudoku board.
    public boolean isValidSudoku(char[][] board) {
        // It calls three helper functions to check rows, columns, and 3x3 subgrids.
        // If all checks pass, the Sudoku is valid.
        return checkRows(board) && checkCols(board) && checkCells(board);
    }

    // Helper function to check if all 3x3 subgrids are valid.
    public boolean checkCells(char[][] board){
        // Iterate through the starting row indices of each 3x3 subgrid (0, 3, 6).
        for(int i=0; i <= 6; i += 3){
            // Iterate through the starting column indices of each 3x3 subgrid (0, 3, 6).
            for(int j=0; j <= 6; j += 3){
                // Call checkCell to validate the current 3x3 subgrid.
                // If any subgrid is invalid, return false immediately.
                if(!checkCell(board, i, j)) return false;
            }
        }
        // If all 3x3 subgrids are valid, return true.
        return true;
    }

    // Helper function to check a single 3x3 subgrid starting at (x, y).
    public boolean checkCell(char[][] board, int x, int y){
        // Use a HashSet to keep track of digits seen in this subgrid.
        HashSet<Character> hs = new HashSet<>();
        // Iterate through the rows of the current 3x3 subgrid.
        for(int i = x; i < x + 3; i++){
            // Iterate through the columns of the current 3x3 subgrid.
            for(int j = y; j < y + 3; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[i][j] == '.') continue;
                // If the digit in the current cell is already in the HashSet, it's a duplicate.
                // Return false as the Sudoku is invalid.
                if(hs.contains(board[i][j])) return false;
                // Add the digit to the HashSet if it's not a duplicate.
                hs.add(board[i][j]);
            }
        }
        // If no duplicates were found in this 3x3 subgrid, return true.
        return true;
    }

    // Helper function to check if all columns are valid.
    public boolean checkCols(char[][] board){
        // Iterate through each column (index i from 0 to 8).
        for(int i = 0; i < 9; i++){
            // Use a HashSet to keep track of digits seen in the current column.
            HashSet<Character> hs = new HashSet<>();
            // Iterate through each row (index j from 0 to 8) to access cells in the current column.
            for(int j = 0; j < 9; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[i][j] == '.') continue; // Note: This line has a typo in the original code, should be board[j][i] for columns. Corrected below.
                // If the digit in the current cell is already in the HashSet, it's a duplicate.
                // Return false as the Sudoku is invalid.
                if(hs.contains(board[i][j])) return false; // Typo here too.
                // Add the digit to the HashSet if it's not a duplicate.
                hs.add(board[i][j]);
            }
        }
        // If all columns are valid, return true.
        return true;
    }

    // Helper function to check if all rows are valid.
    public boolean checkRows(char[][] board){
        // Iterate through each row (index i from 0 to 8).
        for(int i = 0; i < 9; i++){
            // Use a HashSet to keep track of digits seen in the current row.
            HashSet<Character> hs = new HashSet<>();
            // Iterate through each column (index j from 0 to 8) to access cells in the current row.
            for(int j = 0; j < 9; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[j][i] == '.') continue; // Note: This line has a typo in the original code, should be board[i][j] for rows. Corrected below.
                // If the digit in the current cell is already in the HashSet, it's a duplicate.
                // Return false as the Sudoku is invalid.
                if(hs.contains(board[j][i])) return false; // Typo here too.
                // Add the digit to the HashSet if it's not a duplicate.
                hs.add(board[j][i]);
            }
        }
        // If all rows are valid, return true.
        return true;
    }
}

// Corrected Code (addressing typos in original provided solution)
class SolutionCorrected {
    public boolean isValidSudoku(char[][] board) {
        return checkRows(board) && checkCols(board) && checkCells(board);
    }

    public boolean checkCells(char[][] board){
        for(int i=0; i <= 6; i += 3){
            for(int j=0; j <= 6; j += 3){
                if(!checkCell(board, i, j)) return false;
            }
        }
        return true;
    }

    public boolean checkCell(char[][] board, int x, int y){
        HashSet<Character> hs = new HashSet<>();
        for(int i = x; i < x + 3; i++){
            for(int j = y; j < y + 3; j++){
                if(board[i][j] == '.') continue;
                if(hs.contains(board[i][j])) return false;
                hs.add(board[i][j]);
            }
        }
        return true;
    }

    public boolean checkCols(char[][] board){
        for(int i = 0; i < 9; i++){ // Iterate through columns
            HashSet<Character> hs = new HashSet<>();
            for(int j = 0; j < 9; j++){ // Iterate through rows for the current column
                if(board[j][i] == '.') continue; // Access cell at row j, column i
                if(hs.contains(board[j][i])) return false;
                hs.add(board[j][i]);
            }
        }
        return true;
    }

    public boolean checkRows(char[][] board){
        for(int i = 0; i < 9; i++){ // Iterate through rows
            HashSet<Character> hs = new HashSet<>();
            for(int j = 0; j < 9; j++){ // Iterate through columns for the current row
                if(board[i][j] == '.') continue; // Access cell at row i, column j
                if(hs.contains(board[i][j])) return false;
                hs.add(board[i][j]);
            }
        }
        return true;
    }
}
```

## Interview Tips
1.  **Clarify Constraints**: Ask about the board size (always 9x9) and the characters allowed (digits '1'-'9' and '.'). This confirms the fixed complexity.
2.  **Explain the "Why"**: Clearly articulate why HashSets are a good choice for detecting duplicates efficiently.
3.  **Break Down the Problem**: Explain that you'll tackle rows, columns, and subgrids separately, and then combine the results. This shows structured thinking.
4.  **Handle Edge Cases**: Mention how you'll handle the '.' character (by skipping it) and what happens if a duplicate is found early (return `false` immediately).
5.  **Address the Subgrid Logic**: Be prepared to explain how you'll iterate through the 3x3 subgrids, perhaps using nested loops with steps of 3.

## Revision Checklist
- [ ] Understand the Sudoku validation rules.
- [ ] Identify the three main checks: rows, columns, 3x3 subgrids.
- [ ] Choose an appropriate data structure for duplicate detection (HashSet).
- [ ] Implement row validation logic.
- [ ] Implement column validation logic.
- [ ] Implement 3x3 subgrid validation logic.
- [ ] Handle empty cells ('.') correctly.
- [ ] Ensure correct indexing for 2D arrays.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the approach and code.

## Similar Problems
*   Sudoku Solver (Hard)
*   Check if a Sudoku Board is Solvable (Hard)

## Tags
`Array` `Hash Map` `Matrix` `Set`
