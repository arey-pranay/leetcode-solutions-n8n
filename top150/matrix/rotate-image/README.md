# Rotate Image

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Matrix`  
**Time:** O(n^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public void rotate(int[][] matrix) {
        revertColumns(matrix);
        transpose(matrix);
    }
    public void revertColumns(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n/2;j++){
                int temp = matrix[j][i];
                matrix[j][i] = matrix[n-1-j][i];
                matrix[n-1-j][i] = temp;
            }
        }
    }
    public void transpose(int[][] matrix){
        for(int i =0 ; i<matrix.length;i++){
            for(int j =i+1 ; j<matrix[i].length;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
```

---

---
## Quick Revision
Given an n x n 2D matrix, rotate it 90 degrees clockwise in-place.
This can be achieved by first transposing the matrix and then reversing each row.

## Intuition
The problem asks for an in-place rotation. A common way to think about matrix transformations is through simpler operations. Rotating 90 degrees clockwise can be decomposed into two fundamental operations: transposing the matrix (swapping elements across the main diagonal) and then reversing each row. This decomposition simplifies the logic and allows for an in-place solution.

## Algorithm
1. **Transpose the matrix:** Iterate through the upper triangle of the matrix (where `j > i`). For each element `matrix[i][j]`, swap it with `matrix[j][i]`. This effectively mirrors the matrix across its main diagonal.
2. **Reverse each row:** Iterate through each row of the transposed matrix. For each row, reverse its elements. This can be done by using two pointers, one starting from the beginning of the row and the other from the end, swapping elements until the pointers meet.

## Concept to Remember
*   **In-place algorithms:** Modifying the input data structure directly without using significant extra memory.
*   **Matrix Transposition:** Swapping elements `matrix[i][j]` with `matrix[j][i]`.
*   **Row Reversal:** Efficiently reversing elements within a 1D array or a row of a 2D array.

## Common Mistakes
*   Incorrectly implementing the transpose operation, leading to incorrect element swaps.
*   Reversing columns instead of rows after transposition, which results in a 90-degree counter-clockwise rotation.
*   Off-by-one errors in loop bounds or index calculations, especially when dealing with `n/2` for reversal or `j+1` for transpose.
*   Not handling the `n x n` constraint correctly, assuming a rectangular matrix.

## Complexity Analysis
- Time: O(n^2) - reason The algorithm involves two main steps: transposing the matrix and reversing each row. Both operations iterate through approximately n^2 elements of the n x n matrix.
- Space: O(1) - reason The rotation is performed in-place, meaning no auxiliary data structures are used that grow with the input size. Only a few temporary variables are used for swapping.

## Commented Code
```java
class Solution {
    // The main function to rotate the image 90 degrees clockwise.
    public void rotate(int[][] matrix) {
        // First, transpose the matrix.
        transpose(matrix);
        // Then, reverse each row of the transposed matrix.
        revertColumns(matrix); // Note: The provided solution names this 'revertColumns' but it actually reverses rows.
    }

    // This function reverses each row of the matrix.
    // It iterates through each row and uses two pointers to swap elements from the ends towards the center.
    public void revertColumns(int[][] matrix){
        int m = matrix.length; // Get the number of rows.
        int n = matrix[0].length; // Get the number of columns.
        // Iterate through each row.
        for(int i=0;i<m;i++){
            // Iterate through the first half of the columns in the current row.
            // We only need to go up to n/2 because we swap elements in pairs.
            for(int j=0;j<n/2;j++){
                // Swap the element at the beginning of the row with the element at the end.
                int temp = matrix[i][j]; // Store the element from the left side.
                matrix[i][j] = matrix[i][n-1-j]; // Replace the left element with the right element.
                matrix[i][n-1-j] = temp; // Place the stored left element on the right side.
            }
        }
    }

    // This function transposes the matrix.
    // It swaps elements matrix[i][j] with matrix[j][i] for the upper triangle of the matrix.
    public void transpose(int[][] matrix){
        // Iterate through the rows.
        for(int i =0 ; i<matrix.length;i++){
            // Iterate through the columns starting from i+1.
            // This ensures we only process the upper triangle (elements above the main diagonal)
            // to avoid swapping elements twice.
            for(int j =i+1 ; j<matrix[i].length;j++){
                // Swap the element at (i, j) with the element at (j, i).
                int temp = matrix[i][j]; // Store the element at (i, j).
                matrix[i][j] = matrix[j][i]; // Replace matrix[i][j] with matrix[j][i].
                matrix[j][i] = temp; // Place the stored element at matrix[j][i].
            }
        }
    }
}
```

## Interview Tips
*   Clearly explain the two-step process (transpose then reverse rows) and why it works for a 90-degree clockwise rotation.
*   Emphasize the in-place nature of the solution and how it achieves O(1) space complexity.
*   Walk through a small example (e.g., a 2x2 or 3x3 matrix) to demonstrate the transpose and row reversal steps.
*   Be prepared to discuss alternative approaches, even if they are not optimal (e.g., creating a new matrix), and explain their trade-offs.

## Revision Checklist
- [ ] Understand the problem statement: rotate an n x n matrix 90 degrees clockwise in-place.
- [ ] Recall the two-step approach: transpose, then reverse rows.
- [ ] Implement the transpose function correctly, swapping `matrix[i][j]` with `matrix[j][i]` for `j > i`.
- [ ] Implement the row reversal function correctly, using two pointers for each row.
- [ ] Verify the time and space complexity: O(n^2) time, O(1) space.
- [ ] Practice writing the code without looking at the solution.

## Similar Problems
*   Rotate Image (LeetCode 48) - This is the exact problem.
*   Spiral Matrix (LeetCode 54)
*   Set Matrix Zeroes (LeetCode 73)

## Tags
`Array` `Matrix` `Two Pointers`
