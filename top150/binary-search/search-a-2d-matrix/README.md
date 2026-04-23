# Search A 2d Matrix

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Matrix`  
**Time:** O(m + n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length-1;
        while(i<matrix.length && j>=0){
            if(matrix[i][j]==target) return true;
            if(matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }
}
```

---

---
## Quick Revision
Given an m x n integer matrix where each row is sorted in ascending order and the first integer of each row is greater than the last integer of the previous row. Find if a target value exists in the matrix.
This problem can be solved efficiently by treating the 2D matrix as a single sorted 1D array and applying binary search, or by using a two-pointer approach starting from the top-right corner.

## Intuition
The matrix has a special property: rows are sorted, and the first element of a row is greater than the last element of the previous row. This implies that if we flatten the matrix into a single sorted array, it would still be sorted. This hints at a binary search approach. Alternatively, consider the top-right element. If it's equal to the target, we found it. If it's greater than the target, the target cannot be in this column (since the column is sorted downwards), so we move left. If it's less than the target, the target cannot be in this row (since the row is sorted leftwards), so we move down. This leads to an efficient linear scan.

## Algorithm
1. Initialize two pointers: `row` to 0 (the first row) and `col` to `matrix[0].length - 1` (the last column).
2. While `row` is within the matrix bounds (`row < matrix.length`) and `col` is within the matrix bounds (`col >= 0`):
   a. Get the current element: `current = matrix[row][col]`.
   b. If `current == target`, return `true` (target found).
   c. If `current > target`, it means the target, if it exists, must be to the left of the current element in this row (or in a previous row). So, decrement `col` by 1.
   d. If `current < target`, it means the target, if it exists, must be below the current element in this column (or in a subsequent row). So, increment `row` by 1.
3. If the loop finishes without finding the target, return `false`.

## Concept to Remember
*   **Sorted Matrix Properties**: Understanding how the sorted nature of rows and the relationship between consecutive rows enables efficient searching.
*   **Two-Pointer Technique**: Using multiple pointers to traverse a data structure in a coordinated way to find a solution.
*   **Linear Time Search in a Sorted Structure**: Even though it's a 2D matrix, the specific properties allow for a search that is not O(m*n).

## Common Mistakes
*   **Incorrectly applying Binary Search**: Trying to apply standard 1D binary search directly without accounting for the 2D structure or the specific row-to-row relationship.
*   **Off-by-one errors in pointer initialization or boundary checks**: Mismanaging the `row` and `col` indices, leading to out-of-bounds access or missed elements.
*   **Not handling empty matrices or matrices with empty rows**: The provided solution assumes a non-empty matrix with at least one row and one column.
*   **Confusing row and column traversal logic**: Mixing up when to increment `row` and decrement `col` based on the comparison with the target.

## Complexity Analysis
*   Time: O(m + n) - reason: In the worst case, the pointers traverse from the top-right corner to the bottom-left corner. The `row` pointer can move down at most `m` times, and the `col` pointer can move left at most `n` times.
*   Space: O(1) - reason: The algorithm uses a constant amount of extra space for the `row` and `col` pointers.

## Commented Code
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Check if the matrix is null or empty, or if the first row is empty.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            // If so, the target cannot be found, return false.
            return false;
        }

        // Initialize the row pointer to the first row (index 0).
        int i = 0;
        // Initialize the column pointer to the last column of the first row.
        // This is the starting point for our search (top-right corner).
        int j = matrix[0].length - 1;

        // Continue searching as long as the row pointer is within matrix bounds
        // and the column pointer is within matrix bounds.
        while (i < matrix.length && j >= 0) {
            // Get the value at the current position (row i, column j).
            int currentElement = matrix[i][j];

            // If the current element is equal to the target, we found it.
            if (currentElement == target) {
                // Return true immediately.
                return true;
            }
            // If the current element is greater than the target,
            // the target cannot be in this column (since columns are sorted descendingly from top-right).
            // So, we move to the left by decrementing the column pointer.
            else if (currentElement > target) {
                j--;
            }
            // If the current element is less than the target,
            // the target cannot be in this row (since rows are sorted ascendingly from left-right).
            // So, we move down to the next row by incrementing the row pointer.
            else { // currentElement < target
                i++;
            }
        }

        // If the loop finishes without finding the target, it means the target is not in the matrix.
        return false;
    }
}
```

## Interview Tips
*   **Clarify Matrix Properties**: Before coding, confirm the exact properties of the matrix (rows sorted, first of next row > last of previous). This is crucial for choosing the right algorithm.
*   **Explain the Two-Pointer Logic**: Clearly articulate why starting from the top-right (or bottom-left) and moving based on comparisons works. Emphasize how each move eliminates a row or a column.
*   **Consider Edge Cases**: Discuss how you would handle an empty matrix, a matrix with one row, or a matrix with one column.
*   **Discuss Alternative Approaches**: Briefly mention the binary search approach on the flattened matrix and why the two-pointer approach might be preferred for its simplicity and directness given the problem constraints.

## Revision Checklist
- [ ] Understand the problem statement and matrix properties.
- [ ] Identify the starting point for the search (top-right or bottom-left).
- [ ] Implement the two-pointer movement logic correctly.
- [ ] Handle boundary conditions for the pointers.
- [ ] Test with edge cases (empty matrix, target not present, target at boundaries).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array
*   Search in a 2D Matrix II (where rows and columns are sorted independently)
*   Find Minimum in Rotated Sorted Array

## Tags
`Array` `Matrix` `Two Pointers` `Binary Search`
