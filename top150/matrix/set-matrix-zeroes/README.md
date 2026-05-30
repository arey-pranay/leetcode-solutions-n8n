# Set Matrix Zeroes

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Matrix`  
**Time:** O(m*n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

---

---
## Quick Revision
Given an m x n integer matrix, if an element is 0, set its entire row and column to 0.
Use the first row and first column as markers to avoid using extra space.

## Intuition
The core challenge is to mark which rows and columns need to be zeroed out without overwriting information that's still needed. If we immediately set a row/column to zero upon finding a zero, we might lose track of other zeros that were originally in that row/column. The "aha moment" is realizing that the first row and first column themselves can act as these markers. We can use `matrix[i][0]` to indicate if row `i` should be zeroed, and `matrix[0][j]` to indicate if column `j` should be zeroed. However, we need separate flags for the first row and first column because `matrix[0][0]` is used for both.

## Algorithm
1. Initialize two boolean flags, `firstRowZero` and `firstColZero`, to `false`. These will track if the first row or first column, respectively, needs to be zeroed.
2. Iterate through the first row of the matrix. If any element is 0, set `firstRowZero` to `true` and break.
3. Iterate through the first column of the matrix. If any element is 0, set `firstColZero` to `true` and break.
4. Iterate through the rest of the matrix (from `i=1` to `m-1` and `j=1` to `n-1`). If `matrix[i][j]` is 0, set `matrix[i][0]` to 0 and `matrix[0][j]` to 0. This uses the first row and column as markers.
5. Iterate through the rest of the matrix again (from `i=1` to `m-1` and `j=1` to `n-1`). If `matrix[i][0]` is 0 or `matrix[0][j]` is 0, set `matrix[i][j]` to 0.
6. If `firstRowZero` is `true`, iterate through the first row and set all its elements to 0.
7. If `firstColZero` is `true`, iterate through the first column and set all its elements to 0.

## Concept to Remember
*   **In-place modification:** Modifying the input data structure directly to save space.
*   **Marker-based approach:** Using parts of the input structure itself to store auxiliary information.
*   **Handling edge cases:** Special consideration for the first row and column due to their dual role as markers.

## Common Mistakes
*   Zeroing out rows/columns immediately upon finding a zero, which corrupts future checks.
*   Not handling the first row and first column separately, leading to incorrect marking.
*   Incorrectly iterating through the matrix (e.g., starting from `i=0` or `j=0` in the marking phase).
*   Forgetting to zero out the first row/column at the end if their respective flags are set.

## Complexity Analysis
- Time: O(m*n) - reason: We iterate through the matrix a constant number of times (initial checks, marking, zeroing, and final first row/column zeroing).
- Space: O(1) - reason: We only use a few boolean variables, which is constant extra space, regardless of the matrix size.

## Commented Code
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        // Get the number of rows in the matrix.
        int m = matrix.length;
        // Get the number of columns in the matrix.
        int n = matrix[0].length;

        // Flag to indicate if the first row needs to be zeroed.
        boolean firstRowZero = false;
        // Flag to indicate if the first column needs to be zeroed.
        boolean firstColZero = false;

        // Check if any element in the first row is zero.
        for (int j = 0; j < n; j++) {
            // If a zero is found in the first row...
            if (matrix[0][j] == 0) {
                // ...set the flag to true.
                firstRowZero = true;
                // No need to check further in this row.
                break;
            }
        }

        // Check if any element in the first column is zero.
        for (int i = 0; i < m; i++) {
            // If a zero is found in the first column...
            if (matrix[i][0] == 0) {
                // ...set the flag to true.
                firstColZero = true;
                // No need to check further in this column.
                break;
            }
        }

        // Iterate through the rest of the matrix (excluding the first row and column).
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // If an element is zero...
                if (matrix[i][j] == 0) {
                    // ...use the first row and first column as markers.
                    // Mark the corresponding cell in the first column to indicate this row needs zeroing.
                    matrix[i][0] = 0;
                    // Mark the corresponding cell in the first row to indicate this column needs zeroing.
                    matrix[0][j] = 0;
                }
            }
        }

        // Now, iterate through the rest of the matrix again to set zeroes based on markers.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // If the marker in the first column for this row is zero OR
                // the marker in the first row for this column is zero...
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    // ...set the current element to zero.
                    matrix[i][j] = 0;
                }
            }
        }

        // If the first row was marked for zeroing...
        if (firstRowZero) {
            // ...iterate through the first row and set all its elements to zero.
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // If the first column was marked for zeroing...
        if (firstColZero) {
            // ...iterate through the first column and set all its elements to zero.
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

## Interview Tips
*   Clearly explain your strategy for using the first row/column as markers and why separate flags are needed for them.
*   Walk through an example on a whiteboard, showing how the markers are set and used.
*   Discuss the space complexity trade-off if you were to use an auxiliary matrix or sets.
*   Be prepared to explain why the order of operations (marking, then zeroing the inner matrix, then zeroing the first row/column) is crucial.

## Revision Checklist
- [ ] Understand the problem statement: identify rows/columns to zero.
- [ ] Recognize the need for auxiliary space or an in-place solution.
- [ ] Devise a strategy to mark rows/columns without overwriting data.
- [ ] Implement the marker-based approach using the first row/column.
- [ ] Handle the special case of the first row and first column correctly.
- [ ] Verify the time and space complexity of the solution.
- [ ] Test with edge cases: empty matrix, matrix with all zeros, matrix with no zeros.

## Similar Problems
Set Matrix Zeroes II (if applicable, though this is the standard one)
Spiral Matrix
Rotate Image

## Tags
`Array` `Matrix` `In-place`
