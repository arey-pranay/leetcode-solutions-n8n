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
The core idea is that each row, each column, and each of the nine 3x3 subgrids must contain the digits 1-9 without repetition. If we encounter a digit that has already been seen in its respective row, column, or subgrid, the Sudoku is invalid. We need a way to efficiently track what we've seen.

## Algorithm
1. Initialize three 2D boolean arrays: `rows[9][9]`, `cols[9][9]`, and `boxes[9][9]`. These will act as our seen-trackers. `rows[i][k]` will be true if digit `k+1` has been seen in row `i`. Similarly for `cols` and `boxes`.
2. Iterate through each cell of the 9x9 `board` using nested loops (row `i` from 0 to 8, column `j` from 0 to 8).
3. For each cell `board[i][j]`:
    a. If the cell contains a '.', skip it as it's an empty cell and doesn't violate any rules.
    b. If the cell contains a digit, convert it to an integer `num`. Since digits are '1' through '9', we can get a 0-indexed value by `num = board[i][j] - '1'`.
    c. Determine which 3x3 subgrid (box) this cell belongs to. The box index can be calculated as `box = (i / 3) * 3 + (j / 3)`.
    d. Check if this `num` has already been seen in the current row `i`, current column `j`, or current `box`. This is done by checking `rows[i][num]`, `cols[j][num]`, and `boxes[box][num]`.
    e. If any of these checks are true, it means a duplicate digit is found, so return `false` immediately.
    f. If no duplicate is found, mark this `num` as seen in the current row, column, and box by setting `rows[i][num] = true`, `cols[j][num] = true`, and `boxes[box][num] = true`.
4. If the loops complete without returning `false`, it means no duplicates were found, and the Sudoku board is valid. Return `true`.

## Concept to Remember
*   **Grid Traversal:** Efficiently iterating through a 2D array.
*   **Hashing/Set-like Behavior:** Using boolean arrays as a quick lookup mechanism to check for duplicates, similar to using a hash set.
*   **Index Mapping:** Calculating the correct index for subgrids based on row and column indices.

## Common Mistakes
*   **Incorrect Box Calculation:** Errors in the formula `(i / 3) * 3 + (j / 3)` leading to cells being assigned to the wrong subgrid.
*   **Off-by-One Errors:** Mismanaging the 0-indexed conversion of characters to numbers (e.g., using `board[i][j] - '0'` instead of `board[i][j] - '1'`).
*   **Not Handling Empty Cells:** Forgetting to `continue` when encountering a '.' character, leading to errors when trying to process it as a digit.
*   **Overwriting Seen Status:** Not correctly updating the `rows`, `cols`, and `boxes` arrays after a valid placement.

## Complexity Analysis
- Time: O(1) - The board is always 9x9, so the nested loops run a fixed number of times (81 iterations). The operations inside the loop are constant time.
- Space: O(1) - We use three 2D boolean arrays of fixed size 9x9, which is constant space regardless of the input size (since the input size is fixed at 9x9).

## Commented Code
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Initialize boolean arrays to track seen numbers.
        // rows[i][k] is true if digit k+1 has been seen in row i.
        boolean[][] rows = new boolean[9][9];
        // cols[j][k] is true if digit k+1 has been seen in column j.
        boolean[][] cols = new boolean[9][9];
        // boxes[b][k] is true if digit k+1 has been seen in box b.
        boolean[][] boxes = new boolean[9][9];

        // Iterate through each row of the board.
        for(int i = 0; i < board.length; i++){
            // Iterate through each column of the board.
            for(int j = 0; j < board[0].length; j++){
                // If the current cell is empty ('.'), skip it.
                if(board[i][j] == '.'){
                    continue;
                }

                // Convert the character digit to a 0-indexed integer (0-8).
                // '1' becomes 0, '2' becomes 1, ..., '9' becomes 8.
                int num = board[i][j] - '1';

                // Calculate the index of the 3x3 subgrid (box) this cell belongs to.
                // Integer division by 3 groups rows and columns into threes.
                // (i/3)*3 maps row index to the top-left row of the box (0, 3, 6).
                // (j/3) maps column index to the column of the box (0, 1, 2).
                // Combining them gives a unique box index from 0 to 8.
                int box = (i / 3) * 3 + (j / 3);

                // Check if this number has already been seen in the current row, column, or box.
                // If any of these conditions are true, it means a duplicate exists, so the Sudoku is invalid.
                if(rows[i][num] || cols[j][num] || boxes[box][num]){
                    return false; // Found a duplicate, board is invalid.
                }

                // Mark the current number as seen in its respective row, column, and box.
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[box][num] = true;
            }
        }
        // If the loops complete without finding any duplicates, the board is valid.
        return true;
    }
}
```

## Interview Tips
*   **Explain the Box Calculation:** Be ready to clearly explain how `(i / 3) * 3 + (j / 3)` correctly maps a cell's `(i, j)` coordinates to its 3x3 subgrid index.
*   **Discuss Alternatives:** Briefly mention other approaches, like using HashSets for each row, column, and box, and explain why the boolean array approach is efficient for this fixed-size problem.
*   **Edge Cases:** While this problem has a fixed size, consider if the problem statement implied variable sizes (it doesn't here, but good to think about). Mention that the current solution is optimized for the 9x9 constraint.
*   **Clarity of Variables:** Use descriptive variable names (`rows`, `cols`, `boxes`, `num`, `box`) to make your code easier to understand.

## Revision Checklist
- [ ] Understand the Sudoku rules for validity.
- [ ] Implement the 3 boolean arrays for tracking.
- [ ] Correctly convert character digits to 0-indexed integers.
- [ ] Accurately calculate the 3x3 box index.
- [ ] Handle empty cells ('.') by skipping them.
- [ ] Implement the duplicate check logic.
- [ ] Mark seen numbers correctly.
- [ ] Ensure the function returns `true` only if all checks pass.

## Similar Problems
*   Sudoku Solver (Hard)
*   N-Queens
*   Check if Matrix Is X-Matrix

## Tags
`Array` `Hash Map` `Matrix` `Boolean Matrix`

## My Notes
boolean[][] rows = new boolean[9][9];
                    int box = (i/3)*3 + (j/3);
