# Rotate Image

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Matrix`  
**Time:** O(N^2)  
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
Solve by transposing the matrix and then reversing each row.

## Intuition
The problem asks for a 90-degree clockwise rotation of a square matrix *in-place*. This means we can't create a new matrix to store the result. We need to manipulate the existing one.

Consider a single element `matrix[i][j]`. After a 90-degree clockwise rotation, where does it end up?
If we visualize the rotation, an element at `(i, j)` moves to `(j, n-1-i)`, where `n` is the dimension of the matrix.

Directly mapping elements to their new positions can be tricky to manage in-place, especially ensuring no data is overwritten prematurely.

A common strategy for in-place matrix transformations is to break them down into simpler, known operations. Two such operations are:
1.  **Transposing the matrix**: Swapping `matrix[i][j]` with `matrix[j][i]`. This effectively reflects the matrix across its main diagonal.
2.  **Reversing rows/columns**: Flipping the order of elements within each row or column.

Let's see what happens if we combine these:
*   **Transpose first**:
    Original:
    ```
    1 2 3
    4 5 6
    7 8 9
    ```
    After Transpose:
    ```
    1 4 7
    2 5 8
    3 6 9
    ```
*   Now, **reverse each row**:
    ```
    7 4 1
    8 5 2
    9 6 3
    ```
This is exactly a 90-degree clockwise rotation! The element `1` (at `(0,0)`) moved to `(0,2)`, `2` (at `(0,1)`) moved to `(1,2)`, `3` (at `(0,2)`) moved to `(2,2)`, and so on.

Alternatively, we could reverse each row first and then transpose.
*   **Reverse each row first**:
    Original:
    ```
    1 2 3
    4 5 6
    7 8 9
    ```
    After Reversing Rows:
    ```
    3 2 1
    6 5 4
    9 8 7
    ```
*   Now, **transpose**:
    ```
    3 6 9
    2 5 8
    1 4 7
    ```
This is a 90-degree *counter-clockwise* rotation.

So, the intuition is that a 90-degree clockwise rotation can be achieved by a combination of transpose and row reversal. The provided solution uses transpose and column reversal. Let's re-examine the provided solution's logic:

The solution uses `revertColumns` and `transpose`.
1.  `revertColumns(matrix)`: This function iterates through each column (`i`) and then through the first half of the rows (`j < n/2`). It swaps `matrix[j][i]` with `matrix[n-1-j][i]`. This effectively reverses each *column*.
    Original:
    ```
    1 2 3
    4 5 6
    7 8 9
    ```
    After `revertColumns`:
    ```
    7 8 9
    4 5 6
    1 2 3
    ```
2.  `transpose(matrix)`: This function swaps `matrix[i][j]` with `matrix[j][i]` for `j > i`.
    After `transpose` on the result of `revertColumns`:
    ```
    7 4 1
    8 5 2
    9 6 3
    ```
This is also a 90-degree clockwise rotation. So, the intuition is that reversing columns and then transposing also achieves the desired rotation.

The key insight is that complex in-place transformations can often be decomposed into simpler, well-understood operations like transposing and reversing.

## Algorithm
1.  **Transpose the matrix**: Iterate through the upper triangle of the matrix (where `j > i`). For each element `matrix[i][j]`, swap it with `matrix[j][i]`.
2.  **Reverse each row**: Iterate through each row of the matrix. For each row, reverse the order of its elements. This can be done by using two pointers, one starting at the beginning of the row and one at the end, swapping elements until they meet.

*Alternatively, based on the provided code:*

1.  **Reverse each column**: Iterate through each column. For each column, use two pointers (one at the top row, one at the bottom row) and swap elements until the pointers meet.
2.  **Transpose the matrix**: Iterate through the upper triangle of the matrix (where `j > i`). For each element `matrix[i][j]`, swap it with `matrix[j][i]`.

## Concept to Remember
*   **In-place algorithms**: Modifying data structures without using significant extra memory.
*   **Matrix Transposition**: Swapping elements across the main diagonal (`matrix[i][j]` with `matrix[j][i]`).
*   **Decomposition of Operations**: Breaking down a complex transformation into a sequence of simpler, known transformations.
*   **Array/Matrix Manipulation**: Efficiently swapping elements and managing indices.

## Common Mistakes
*   **Not performing the operation in-place**: Creating a new matrix to store the rotated result, which violates the problem constraint.
*   **Incorrectly calculating new indices**: Forgetting the `n-1-i` part when mapping elements directly, or off-by-one errors in loops.
*   **Overwriting data prematurely**: Swapping elements in a way that loses original values before they are used.
*   **Confusing clockwise and counter-clockwise rotation**: Applying the wrong sequence of operations (e.g., reversing rows then transposing results in counter-clockwise).
*   **Incorrect loop bounds for transpose**: Not ensuring `j > i` to avoid swapping elements twice or swapping elements with themselves.

## Complexity Analysis
*   Time: O(N^2) - reason: We iterate through the matrix elements multiple times. Transposing takes O(N^2) time as we visit roughly half the elements. Reversing each row (or column) also takes O(N^2) time in total (N rows * N/2 swaps per row).
*   Space: O(1) - reason: The rotation is performed in-place. We only use a constant amount of extra space for temporary variables during swaps.

## Commented Code
```java
class Solution {
    // The main function to rotate the image 90 degrees clockwise.
    public void rotate(int[][] matrix) {
        // First, reverse each column of the matrix.
        revertColumns(matrix);
        // Second, transpose the matrix.
        transpose(matrix);
    }

    // Helper function to reverse the elements in each column.
    public void revertColumns(int[][] matrix){
        // Get the number of rows (m) and columns (n). For a square matrix, m == n.
        int m = matrix.length;
        int n = matrix[0].length; // Assuming a non-empty matrix

        // Iterate through each column (i).
        for(int i = 0; i < m; i++){
            // Iterate through the first half of the rows (j) for the current column.
            // We only need to go up to n/2 because we swap elements from both ends.
            for(int j = 0; j < n / 2; j++){
                // Swap the element at matrix[j][i] with the element at matrix[n-1-j][i].
                // This effectively reverses the column.
                int temp = matrix[j][i]; // Store the top element temporarily.
                matrix[j][i] = matrix[n - 1 - j][i]; // Move the bottom element to the top position.
                matrix[n - 1 - j][i] = temp; // Place the stored top element at the bottom position.
            }
        }
    }

    // Helper function to transpose the matrix.
    public void transpose(int[][] matrix){
        // Iterate through the rows of the matrix.
        for(int i = 0; i < matrix.length; i++){
            // Iterate through the columns starting from i+1.
            // We only iterate through the upper triangle (j > i) to avoid swapping elements twice.
            for(int j = i + 1; j < matrix[i].length; j++){
                // Swap the element at matrix[i][j] with the element at matrix[j][i].
                // This reflects the matrix across its main diagonal.
                int temp = matrix[i][j]; // Store the element at (i, j) temporarily.
                matrix[i][j] = matrix[j][i]; // Place the element from (j, i) into (i, j).
                matrix[j][i] = temp; // Place the stored element from (i, j) into (j, i).
            }
        }
    }
}
```

## Interview Tips
*   **Clarify constraints**: Ask if the matrix is always square and non-empty. Confirm the "in-place" requirement.
*   **Visualize the rotation**: Draw a small matrix (e.g., 2x2 or 3x3) and trace the movement of elements for a 90-degree clockwise rotation. This helps in understanding the transformation.
*   **Explain the decomposition**: Clearly articulate how transposing and reversing rows (or columns) combine to achieve the rotation. This shows problem-solving skills.
*   **Discuss alternative approaches**: Mention the transpose-then-reverse-rows method as well, and explain why the chosen method (reverse columns-then-transpose) also works.
*   **Walk through the code**: Be prepared to explain each part of your code, especially the loop bounds and swap logic, as if you were explaining it to a junior engineer.

## Revision Checklist
- [ ] Understand the problem: rotate an NxN matrix 90 degrees clockwise in-place.
- [ ] Recall the two common in-place strategies: transpose + reverse rows, or reverse columns + transpose.
- [ ] Implement transpose correctly (iterate upper triangle, swap `matrix[i][j]` and `matrix[j][i]`).
- [ ] Implement row reversal correctly (two pointers per row).
- [ ] Implement column reversal correctly (two pointers per column).
- [ ] Verify the chosen sequence of operations (e.g., reverse columns then transpose) yields clockwise rotation.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Spiral Matrix
*   Set Matrix Zeroes
*   Rotate Array

## Tags
`Array` `Matrix` `Two Pointers` `In-place`
