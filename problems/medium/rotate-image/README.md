# Rotate Image

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    public void rotate(int[][] matrix) {
        // transpose the matrix, diagonally
        for(int i=0;i<matrix.length;i++){
            for(int j=i;j<matrix[0].length;j++){
                swapMat(matrix,i,j);
            }
        }
        // rotate each row
        for(int[] a : matrix) revRow(a);
    }
    public void swapMat (int[][] mat, int i, int j){
        int temp = mat[i][j];
        mat[i][j] = mat[j][i];
        mat[j][i] = temp;
    }
    public void revRow (int[] row){
        int i=0;
        int j= row.length-1;
        while(i<=j){
            swap(row,i,j);
            i++;
            j--;
        }
    }
    public void swap (int[] arr, int i, int j){
        int temp  = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

---

---
## Problem Summary
Given an n x n 2D matrix representing an image, rotate the image by 90 degrees clockwise. You must rotate the image in-place, meaning you cannot allocate another 2D matrix and do the rotation.

## Approach and Intuition
The provided solution uses a two-step in-place rotation strategy:

1.  **Transpose the Matrix:** The first step is to transpose the matrix. Transposing means swapping elements across the main diagonal. For an element at `matrix[i][j]`, it gets swapped with `matrix[j][i]`. This effectively mirrors the matrix along its main diagonal. The inner loop starts from `j=i` to avoid swapping elements twice and to only consider the upper triangle of the matrix (including the diagonal).

2.  **Reverse Each Row:** After transposing, each row of the matrix needs to be reversed to achieve the 90-degree clockwise rotation. For example, if a row was `[1, 2, 3]`, after transposition it might become `[1, 4, 7]`. Reversing this row gives `[7, 4, 1]`, which is the correct rotated state for that row.

**Intuition:**
Imagine the matrix as a grid.
*   **Transpose:** Think of folding the grid along the main diagonal. Elements on one side of the diagonal swap places with their counterparts on the other side.
*   **Reverse Rows:** After the fold, the elements are in the correct columns but in the wrong order within those columns. Reversing each row puts them into their final, rotated positions.

## Complexity Analysis
*   **Time:** O(n^2) - The code iterates through the matrix twice. The first loop (transpose) visits roughly n^2/2 elements (due to `j=i`), and the second loop (reverse rows) iterates through each of the n rows, reversing each row of length n, which also takes O(n^2) operations in total.
*   **Space:** O(1) - The rotation is performed in-place. Only a few temporary variables are used for swapping, which do not depend on the input size.

## Code Walkthrough
1.  `rotate(int[][] matrix)`:
    *   This is the main method that orchestrates the rotation.
    *   It first calls `transpose` for the entire matrix.
    *   Then, it iterates through each row of the matrix and calls `revRow` on it.

2.  `swapMat(int[][] mat, int i, int j)`:
    *   A helper function to swap two elements `mat[i][j]` and `mat[j][i]`.
    *   It uses a `temp` variable to hold one of the values during the swap.

3.  `revRow(int[] row)`:
    *   A helper function to reverse a given 1D array (a row of the matrix).
    *   It uses two pointers, `i` starting from the beginning and `j` starting from the end of the row.
    *   It swaps elements pointed to by `i` and `j` and moves the pointers towards the center until `i` crosses `j`.

4.  `swap(int[] arr, int i, int j)`:
    *   A generic helper function to swap two elements in a 1D array.

## Interview Tips
*   **Clarify Constraints:** Always ask about the dimensions of the matrix (n x n), whether it's guaranteed to be square, and if it can be empty.
*   **In-Place Requirement:** Emphasize that the solution must be in-place. This is a key constraint.
*   **Break Down the Problem:** Explain the two-step approach clearly: transpose, then reverse rows. This makes the solution easier to understand.
*   **Visualize:** Draw a small matrix on a whiteboard or paper and trace the steps of transposition and row reversal to demonstrate your understanding.
*   **Edge Cases:** Consider matrices of size 1x1, 2x2. The current solution handles these correctly.
*   **Alternative Approaches:** Be prepared to discuss other methods, even if they are not in-place (e.g., creating a new matrix).

## Optimization and Alternatives
*   **Single Pass Rotation (More Complex):** It's possible to achieve 90-degree rotation in a single pass by rotating elements in groups of four. For an element at `(row, col)`, its destination after rotation is `(col, n - 1 - row)`. You can swap `matrix[row][col]` with `matrix[col][n - 1 - row]`, then swap the original `matrix[col][n - 1 - row]` with `matrix[n - 1 - row][n - 1 - col]`, and so on, for all four corners of a conceptual square within the matrix. This is more complex to implement correctly and might be harder to explain under pressure.
*   **Using Extra Space:** A simpler, non-in-place approach would be to create a new `n x n` matrix and populate it such that `new_matrix[j][n - 1 - i] = matrix[i][j]`. This has O(n^2) space complexity.

## Revision Checklist
*   [ ] Understand the problem statement thoroughly.
*   [ ] Identify the in-place constraint.
*   [ ] Implement the transpose operation correctly.
*   [ ] Implement the row reversal operation correctly.
*   [ ] Analyze time and space complexity.
*   [ ] Consider edge cases (e.g., 1x1 matrix).
*   [ ] Be ready to explain the intuition behind the two-step approach.

## Similar Problems
*   Spiral Matrix
*   Set Matrix Zeroes
*   Matrix Diagonal Traverse

## Tags
`Array` `Matrix`
