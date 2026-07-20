# Spiral Matrix

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m*n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int x0 = 0, x1 = m-1, y0 = 0, y1 = n-1;
        List<Integer> ans = new ArrayList<>();
        while(ans.size() != m*n){
            for(int j=y0;j<=y1;j++) ans.add(matrix[x0][j]); x0++;
            for(int i=x0;ans.size() < m*n && i<=x1;i++)ans.add(matrix[i][y1]); y1--;
            for(int j=y1;ans.size() < m*n && j>=y0;j--) ans.add(matrix[x1][j]); x1--;
            for(int i=x1;ans.size() < m*n && i>=x0;i--)ans.add(matrix[i][y0]); y0++;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given an m x n 2D matrix, return all elements of the matrix in spiral order.
Traverse the matrix layer by layer, shrinking the boundaries inwards.

## Intuition
The problem asks us to traverse a 2D matrix in a spiral pattern. Imagine peeling an onion, layer by layer. We can simulate this by maintaining four boundary pointers: top, bottom, left, and right. We traverse along the top row from left to right, then down the rightmost column, then left along the bottom row, and finally up the leftmost column. After completing one full spiral layer, we shrink the boundaries inwards and repeat the process until all elements are visited.

## Algorithm
1. Initialize four boundary pointers: `top` (row 0), `bottom` (last row index), `left` (column 0), and `right` (last column index).
2. Initialize an empty list `result` to store the spiral order traversal.
3. While `top <= bottom` and `left <= right`:
    a. Traverse from `left` to `right` along the `top` row: Add `matrix[top][j]` to `result` for `j` from `left` to `right`. Increment `top`.
    b. Traverse from `top` to `bottom` along the `right` column: Add `matrix[i][right]` to `result` for `i` from `top` to `bottom`. Decrement `right`.
    c. If `top <= bottom` (to handle cases where a row might have been fully processed): Traverse from `right` to `left` along the `bottom` row: Add `matrix[bottom][j]` to `result` for `j` from `right` to `left`. Decrement `bottom`.
    d. If `left <= right` (to handle cases where a column might have been fully processed): Traverse from `bottom` to `top` along the `left` column: Add `matrix[i][left]` to `result` for `i` from `bottom` to `top`. Increment `left`.
4. Return the `result` list.

## Concept to Remember
*   **Boundary Traversal:** Simulating movement along the edges of a shrinking sub-matrix.
*   **State Management:** Keeping track of the current boundaries (`top`, `bottom`, `left`, `right`) to define the traversal area.
*   **Edge Cases:** Handling matrices with single rows, single columns, or 1x1 dimensions.
*   **Loop Termination:** Ensuring the traversal stops correctly when all elements are visited.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly setting or updating boundary pointers, leading to missing or duplicate elements.
*   **Not handling single row/column matrices:** The logic might fail if the matrix is not square or has only one row/column.
*   **Incorrect loop conditions:** The `while` loop or `for` loop conditions might not correctly account for the shrinking boundaries or the total number of elements.
*   **Not checking boundaries within loops:** For example, after traversing a row, the `top` boundary is incremented. The subsequent column traversal needs to ensure `top` hasn't crossed `bottom`.

## Complexity Analysis
- Time: O(m*n) - reason: Each element in the m x n matrix is visited and added to the result list exactly once.
- Space: O(1) - reason: We are only using a few variables to store boundary pointers and the result list. If the space for the output list is considered, it would be O(m*n).

## Commented Code
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Get the number of rows (m) and columns (n) from the matrix.
        int m = matrix.length, n = matrix[0].length;
        // Initialize boundary pointers: x0 for top row, x1 for bottom row, y0 for left column, y1 for right column.
        int x0 = 0, x1 = m-1, y0 = 0, y1 = n-1;
        // Initialize an ArrayList to store the spiral order traversal.
        List<Integer> ans = new ArrayList<>();
        // Continue as long as the number of elements added to 'ans' is less than the total number of elements in the matrix.
        while(ans.size() != m*n){
            // Traverse the top row from left to right.
            // 'j' iterates from the current left boundary (y0) to the current right boundary (y1).
            for(int j=y0;j<=y1;j++) {
                // Add the element at the current top row (x0) and column 'j' to the result list.
                ans.add(matrix[x0][j]);
            }
            // After traversing the top row, move the top boundary down by one.
            x0++;
            // Traverse the rightmost column from top to bottom.
            // 'i' iterates from the new top boundary (x0) to the current bottom boundary (x1).
            // The condition 'ans.size() < m*n' is a safeguard to stop if all elements are already added.
            for(int i=x0;ans.size() < m*n && i<=x1;i++) {
                // Add the element at the current row 'i' and the rightmost column (y1) to the result list.
                ans.add(matrix[i][y1]);
            }
            // After traversing the right column, move the right boundary left by one.
            y1--;
            // Traverse the bottom row from right to left.
            // 'j' iterates from the new right boundary (y1) down to the current left boundary (y0).
            // The condition 'ans.size() < m*n' is a safeguard.
            for(int j=y1;ans.size() < m*n && j>=y0;j--) {
                // Add the element at the current bottom row (x1) and column 'j' to the result list.
                ans.add(matrix[x1][j]);
            }
            // After traversing the bottom row, move the bottom boundary up by one.
            x1--;
            // Traverse the leftmost column from bottom to top.
            // 'i' iterates from the new bottom boundary (x1) down to the new top boundary (x0).
            // The condition 'ans.size() < m*n' is a safeguard.
            for(int i=x1;ans.size() < m*n && i>=x0;i--) {
                // Add the element at the current row 'i' and the leftmost column (y0) to the result list.
                ans.add(matrix[i][y0]);
            }
            // After traversing the left column, move the left boundary right by one.
            y0++;
        }
        // Return the list containing all elements in spiral order.
        return ans;
    }
}
```

## Interview Tips
*   **Visualize the boundaries:** Clearly explain how you are shrinking the traversal area with the four pointers.
*   **Handle edge cases explicitly:** Discuss how your logic handles matrices with one row, one column, or a single element.
*   **Trace with an example:** Walk through a small 3x3 or 2x4 matrix to demonstrate your algorithm's steps.
*   **Explain the `ans.size() < m*n` condition:** This is crucial for correctness, especially in non-square matrices or when a layer is fully processed.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core idea: layer-by-layer traversal.
- [ ] Define boundary variables (`top`, `bottom`, `left`, `right`).
- [ ] Implement the four traversal directions (right, down, left, up).
- [ ] Correctly update boundary variables after each traversal.
- [ ] Add checks to prevent redundant traversals (e.g., `top <= bottom` and `left <= right` within loops).
- [ ] Ensure the loop terminates correctly when all elements are visited.
- [ ] Test with various matrix dimensions (square, rectangular, single row/column).

## Similar Problems
*   [LeetCode 54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) (This problem)
*   [LeetCode 498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/)
*   [LeetCode 200. Number of Islands](https://leetcode.com/problems/number-of-islands/) (Uses BFS/DFS, but involves grid traversal)
*   [LeetCode 59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/) (Similar, but fills a matrix with numbers)

## Tags
`Array` `Matrix` `Simulation`
