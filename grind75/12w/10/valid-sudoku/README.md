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
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        for(int i=0;i<board.length;i++){
          for(int j=0; j<board[0].length; j++){
            if(board[i][j]=='.') continue;
            int num = board[i][j]-'1';
            int box = (i/3)*3 + (j/3);
            if(rows[i][num] || cols[j][num] || boxes[box][num]) return false;
            rows[i][num] = cols[j][num] = boxes[box][num] = true;
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
Solves by using boolean arrays to track seen numbers in rows, columns, and 3x3 subgrids.

## Intuition
The core idea is that each row, each column, and each of the nine 3x3 subgrids must contain the digits 1-9 without repetition. We can efficiently check for repetitions by keeping track of which numbers we've already encountered in each of these constraints. If we find a duplicate, the Sudoku is invalid.

## Algorithm
1. Initialize three 2D boolean arrays: `rows[9][9]`, `cols[9][9]`, and `boxes[9][9]`. These will act as our seen-set for each row, column, and 3x3 box respectively. The second dimension of each array will store the digit (0-8, corresponding to '1'-'9').
2. Iterate through each cell of the 9x9 `board` using nested loops (outer loop for rows `i` from 0 to 8, inner loop for columns `j` from 0 to 8).
3. For each cell `board[i][j]`:
    a. If the cell contains a '.', skip it as it's an empty cell and doesn't violate any rules.
    b. If the cell contains a digit, convert it to an integer `num` by subtracting the ASCII value of '1' (e.g., '5' - '1' = 4). This maps digits '1'-'9' to indices 0-8.
    c. Calculate the index of the 3x3 subgrid (box) the current cell belongs to. This can be done using `box = (i / 3) * 3 + (j / 3)`. For example, cells in the top-left 3x3 box will have `box = 0`, top-middle `box = 1`, etc.
    d. Check if the current `num` has already been seen in the current row `i`, current column `j`, or current `box`. This is done by checking `rows[i][num]`, `cols[j][num]`, and `boxes[box][num]`.
    e. If any of these checks are `true`, it means a duplicate is found, so return `false` immediately.
    f. If no duplicate is found, mark the current `num` as seen in the corresponding row, column, and box by setting `rows[i][num] = true`, `cols[j][num] = true`, and `boxes[box][num] = true`.
4. If the loops complete without returning `false`, it means no duplicates were found, and the Sudoku board is valid. Return `true`.

## Concept to Remember
*   **Hashing/Set Simulation:** Using boolean arrays to efficiently track the presence of elements (digits in this case) within specific constraints (rows, columns, boxes).
*   **2D Array Traversal:** Systematically iterating through all elements of a 2D grid.
*   **Integer Arithmetic for Indexing:** Using division and multiplication to map 2D grid coordinates to a single index for subgrids.
*   **Character to Integer Conversion:** Converting character digits to their numerical integer equivalents for array indexing.

## Common Mistakes
*   **Incorrect Box Calculation:** Errors in the formula `(i/3)*3 + (j/3)` leading to incorrect subgrid assignments.
*   **Off-by-One Errors:** Mismanaging the 0-based indexing of arrays versus the 1-based nature of Sudoku digits. Forgetting to subtract '1' from the character digit.
*   **Not Handling Empty Cells:** Failing to `continue` when encountering a '.' character, leading to incorrect checks or errors.
*   **Overwriting Seen Status:** Incorrectly updating the `rows`, `cols`, or `boxes` arrays, or not updating them at all after a valid placement.
*   **Using `HashSet` inefficiently:** While `HashSet` could be used, boolean arrays are more space-efficient and often faster for a fixed small range of values (1-9).

## Complexity Analysis
- Time: O(1) - The board size is fixed at 9x9. We iterate through each cell exactly once. The operations inside the loop (array access, arithmetic) are constant time. Therefore, the total time complexity is proportional to the number of cells, which is constant (81).
- Space: O(1) - We use three 2D boolean arrays of size 9x9. The total space used is fixed and does not grow with input size (as the input size is fixed). Thus, the space complexity is constant.

## Commented Code
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Initialize boolean arrays to track seen numbers.
        // rows[i][num] will be true if digit 'num+1' has been seen in row 'i'.
        boolean[][] rows = new boolean[9][9];
        // cols[j][num] will be true if digit 'num+1' has been seen in column 'j'.
        boolean[][] cols = new boolean[9][9];
        // boxes[k][num] will be true if digit 'num+1' has been seen in box 'k'.
        boolean[][] boxes = new boolean[9][9];

        // Iterate through each row of the board.
        for(int i = 0; i < board.length; i++){
            // Iterate through each column of the board.
            for(int j = 0; j < board[0].length; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[i][j] == '.'){
                    continue;
                }

                // Convert the character digit to an integer index (0-8).
                // '1' becomes 0, '2' becomes 1, ..., '9' becomes 8.
                int num = board[i][j] - '1';

                // Calculate the index of the 3x3 subgrid (box) this cell belongs to.
                // Integer division by 3 maps rows 0,1,2 to 0; 3,4,5 to 1; 6,7,8 to 2.
                // Multiplying by 3 gives a base for the box row (0, 3, 6).
                // Similarly, (j/3) gives the box column index (0, 1, 2).
                // Adding them together gives a unique box index from 0 to 8.
                int box = (i / 3) * 3 + (j / 3);

                // Check if this number has already been seen in the current row, column, or box.
                // If any of these are true, it means a duplicate exists, so the Sudoku is invalid.
                if(rows[i][num] || cols[j][num] || boxes[box][num]){
                    return false; // Found a duplicate, return false immediately.
                }

                // Mark the current number as seen in the current row, column, and box.
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[box][num] = true;
            }
        }

        // If the loops complete without finding any duplicates, the Sudoku is valid.
        return true;
    }
}
```

## Interview Tips
*   **Explain the Box Calculation:** Be ready to clearly explain how `(i/3)*3 + (j/3)` correctly maps a cell's `(i, j)` coordinates to its 3x3 subgrid index.
*   **Discuss Alternatives:** Briefly mention that `HashSet` could be used, but explain why boolean arrays are preferred here (fixed, small range of values, better performance/space).
*   **Edge Cases:** While this problem has a fixed 9x9 size, for similar problems, always consider edge cases like empty input, invalid characters, or different board sizes if applicable.
*   **Clarity of Variables:** Use descriptive variable names like `rows`, `cols`, `boxes`, and `num` to make your code easy to understand.

## Revision Checklist
- [ ] Understand the Sudoku validation rules.
- [ ] Implement the 3 boolean arrays for tracking seen numbers.
- [ ] Correctly convert character digits to integer indices.
- [ ] Accurately calculate the 3x3 box index.
- [ ] Handle empty cells ('.') by skipping them.
- [ ] Implement the duplicate check logic.
- [ ] Mark numbers as seen after validation.
- [ ] Ensure the function returns `true` if all checks pass.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Sudoku Solver (Hard)
*   Check if a Sudoku Board is Solvable (similar logic but requires backtracking)

## Tags
`Array` `Hash Map` `Matrix` `Boolean Matrix`
