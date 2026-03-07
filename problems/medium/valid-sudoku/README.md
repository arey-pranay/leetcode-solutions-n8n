# Valid Sudoku

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int boxIndex = (i / 3) * 3 + (j / 3);

                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        return false;
                    }

                    rows[i][num] = cols[j][num] = boxes[boxIndex][num] = true;
                }
            }
        }
        return true;
    }
}
```

---

---
## Problem Summary
The problem asks us to determine if a given 9x9 Sudoku board is valid. A Sudoku board is valid if:
1. Each row contains the digits 1-9 without repetition.
2. Each column contains the digits 1-9 without repetition.
3. Each of the nine 3x3 sub-boxes of the grid contains the digits 1-9 without repetition.

The input is a `char[][]` representing the Sudoku board, where '.' denotes an empty cell.

## Approach and Intuition
The core idea is to efficiently check for duplicates within each row, column, and 3x3 sub-box. Since we only care about the presence of a digit (1-9) and not its count, a boolean array or a hash set can be used to keep track of seen digits.

The provided solution uses three 2D boolean arrays:
1. `rows[9][9]`: `rows[i][num]` will be `true` if the digit `num` has been seen in row `i`.
2. `cols[9][9]`: `cols[j][num]` will be `true` if the digit `num` has been seen in column `j`.
3. `boxes[9][9]`: `boxes[boxIndex][num]` will be `true` if the digit `num` has been seen in the `boxIndex`-th 3x3 sub-box.

We iterate through each cell of the 9x9 board. If a cell contains a digit (not '.'), we perform the following checks:
- Convert the character digit to an integer index (0-8) by subtracting '1'.
- Calculate the index of the 3x3 sub-box the current cell belongs to. The formula `(i / 3) * 3 + (j / 3)` effectively maps the 9x9 grid into 9 distinct 3x3 boxes, indexed from 0 to 8.
- Check if the current digit has already been seen in the current row, current column, or current box using the respective boolean arrays. If any of these checks return `true`, it means a duplicate is found, and the board is invalid, so we return `false`.
- If no duplicate is found, mark the current digit as seen in the corresponding row, column, and box by setting the respective boolean array entries to `true`.

If we iterate through the entire board without finding any duplicates, the board is valid, and we return `true`.

## Complexity Analysis
- Time: O(1)
  - The board size is fixed at 9x9. We iterate through each cell exactly once. For each cell, we perform constant-time operations (array lookups and assignments). Therefore, the time complexity is proportional to the number of cells, which is 81, a constant.
- Space: O(1)
  - We use three 2D boolean arrays, each of size 9x9. The total space used is 3 * 9 * 9, which is a constant amount of space, independent of the input size (as the input size is fixed).

## Code Walkthrough
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Initialize boolean arrays to track seen digits.
        // rows[i][num]: true if digit 'num' is seen in row 'i'.
        // cols[j][num]: true if digit 'num' is seen in column 'j'.
        // boxes[boxIndex][num]: true if digit 'num' is seen in the 'boxIndex'-th 3x3 box.
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        // Iterate through each cell of the 9x9 board.
        for (int i = 0; i < 9; i++) { // i represents the row index
            for (int j = 0; j < 9; j++) { // j represents the column index
                // If the cell is not empty (i.e., it contains a digit).
                if (board[i][j] != '.') {
                    // Convert the character digit to an integer index (0-8).
                    // '1' -> 0, '2' -> 1, ..., '9' -> 8.
                    int num = board[i][j] - '1';

                    // Calculate the index of the 3x3 sub-box.
                    // There are 9 boxes, indexed 0-8.
                    // i/3 gives the row block (0, 1, or 2).
                    // j/3 gives the column block (0, 1, or 2).
                    // (i/3)*3 maps the row block to the start of its box row (0, 3, or 6).
                    // Adding j/3 assigns the correct box index within that row block.
                    int boxIndex = (i / 3) * 3 + (j / 3);

                    // Check for duplicates:
                    // If the digit 'num' has already been seen in the current row 'i',
                    // OR in the current column 'j',
                    // OR in the current 3x3 box 'boxIndex'.
                    if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                        // If a duplicate is found, the Sudoku is invalid.
                        return false;
                    }

                    // Mark the digit 'num' as seen in the current row, column, and box.
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }
        // If the loop completes without finding any duplicates, the Sudoku is valid.
        return true;
    }
}
```

## Interview Tips
- **Clarity of Thought:** Explain your approach clearly. Start by identifying the constraints and requirements.
- **Data Structures:** Discuss why boolean arrays (or hash sets) are suitable for tracking seen elements. Mention the trade-offs if you were to use hash sets (slightly more overhead but more flexible for non-numeric or sparse data).
- **Edge Cases:** While this problem has a fixed size, consider what would happen if the board size varied (though not applicable here). The logic for calculating `boxIndex` is crucial.
- **Optimization:** The current solution is already optimal in terms of time and space complexity for a fixed-size board. Discuss this.
- **Alternative Approaches:** Briefly mention using HashSets instead of boolean arrays. This might be a good point to discuss if the problem allowed for characters other than digits or if the range of numbers was much larger and sparse.
- **Code Readability:** Emphasize the importance of meaningful variable names (`rows`, `cols`, `boxes`, `num`, `boxIndex`) and comments.

## Optimization and Alternatives
**Alternative using HashSets:**
Instead of 2D boolean arrays, one could use `HashSet<Integer>` for each row, column, and box.
- `List<Set<Integer>> rows = new ArrayList<>(9);`
- `List<Set<Integer>> cols = new ArrayList<>(9);`
- `List<Set<Integer>> boxes = new ArrayList<>(9);`
Initialize each set. Then, for each cell:
- If `set.add(num)` returns `false`, it means the number was already present, so return `false`.
This approach is conceptually similar but might have slightly higher constant factors for time and space due to object creation and hashing overhead. However, it's more flexible if the problem constraints were different (e.g., larger or non-contiguous number ranges).

**Further Optimization:**
For this specific problem with a fixed 9x9 board, the current solution is already optimal. There isn't a way to solve it faster or with less space.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the three conditions for a valid Sudoku.
- [ ] Choose an appropriate data structure to track seen digits (boolean array or HashSet).
- [ ] Implement the logic to iterate through the board.
- [ ] Correctly calculate the index for the 3x3 sub-boxes.
- [ ] Handle the '.' (empty cell) case.
- [ ] Implement the duplicate check for rows, columns, and boxes.
- [ ] Ensure correct marking of seen digits.
- [ ] Return `false` immediately upon finding a violation.
- [ ] Return `true` if the entire board is traversed without violations.
- [ ] Analyze time and space complexity.

## Similar Problems
- Sudoku Solver (Hard)
- Check if a Sudoku Board is Solvable (related to Sudoku Solver)
- N-Queens (uses backtracking, similar constraint satisfaction idea)

## Tags
`Array` `Hash Map`

## My Notes
My extra comments on sudoku question study
