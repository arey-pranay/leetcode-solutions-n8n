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
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int top = 0;
        int bottom = m-1;
        int left = 0;
        int right = n-1;
        while(ans.size() != m*n){
            for (int i = left;i <= right; i++) ans.add(matrix[top][i]);
            top++;
            for (int i = top; ans.size() < m*n && i <= bottom; i++) ans.add(matrix[i][right]);
            right--;
            for (int i = right; ans.size() < m*n && i >= left; i--) ans.add(matrix[bottom][i]);
            bottom--;
            for (int i = bottom; ans.size() < m*n && i >=top ; i--) ans.add(matrix[i][left]);
            left++;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given an m x n 2D matrix, return all elements of the matrix in spiral order.
Traverse the matrix layer by layer, shrinking the boundaries after each full traversal.

## Intuition
The "aha moment" comes from realizing that we can simulate the spiral traversal by maintaining four boundary pointers: `top`, `bottom`, `left`, and `right`. As we traverse each side of the current layer, we shrink the corresponding boundary inwards. This process continues until all elements are visited. The key is to carefully manage the boundary updates and the conditions to stop the traversal, especially for non-square matrices or when the number of elements is exhausted.

## Algorithm
1. Initialize an empty list `ans` to store the spiral order traversal.
2. Get the dimensions of the matrix: `m` (rows) and `n` (columns).
3. Initialize four boundary pointers: `top = 0`, `bottom = m - 1`, `left = 0`, `right = n - 1`.
4. Start a `while` loop that continues as long as the size of `ans` is less than the total number of elements (`m * n`).
5. **Traverse Right:** Iterate from `left` to `right` along the `top` row. Add `matrix[top][i]` to `ans`. Increment `top`.
6. **Traverse Down:** Iterate from `top` to `bottom` along the `right` column. Add `matrix[i][right]` to `ans`. Decrement `right`.
7. **Traverse Left:** Iterate from `right` to `left` along the `bottom` row. Add `matrix[bottom][i]` to `ans`. Decrement `bottom`.
8. **Traverse Up:** Iterate from `bottom` to `top` along the `left` column. Add `matrix[i][left]` to `ans`. Increment `left`.
9. The `while` loop condition `ans.size() != m*n` ensures we stop once all elements are collected. The inner loop conditions `ans.size() < m*n` are crucial to prevent adding duplicate elements when boundaries cross or when the matrix is fully traversed in a partial layer.
10. Return the `ans` list.

## Concept to Remember
*   **Boundary Traversal:** Simulating movement by adjusting explicit boundary pointers.
*   **State Management:** Keeping track of the current traversal boundaries (`top`, `bottom`, `left`, `right`).
*   **Termination Conditions:** Carefully defining when to stop traversing each direction and the overall traversal.
*   **Edge Cases:** Handling matrices with single rows, single columns, or 1x1 dimensions.

## Common Mistakes
*   **Incorrect Boundary Updates:** Forgetting to increment `top`, decrement `right`, decrement `bottom`, or increment `left` after each traversal direction.
*   **Off-by-One Errors:** Using `<=` or `>=` incorrectly in loop conditions, leading to missing or extra elements.
*   **Not Handling Inner Loop Termination:** Failing to check `ans.size() < m*n` within the inner loops, which can cause issues with non-square matrices or when the last layer is incomplete.
*   **Infinite Loops:** If the termination condition is not met correctly, the loop might never end.
*   **Overlapping Traversal:** Traversing elements that have already been added to the result list.

## Complexity Analysis
*   **Time:** O(m*n) - reason: Each element in the m x n matrix is visited and added to the result list exactly once.
*   **Space:** O(1) - reason: Excluding the space required for the output list, we only use a constant amount of extra space for the boundary pointers. If the output list is considered, then it's O(m*n).

## Commented Code
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Get the number of rows in the matrix.
        int m = matrix.length;
        // Get the number of columns in the matrix.
        int n = matrix[0].length;
        // Initialize an ArrayList to store the spiral order traversal.
        List<Integer> ans = new ArrayList<>();

        // Initialize the top boundary pointer.
        int top = 0;
        // Initialize the bottom boundary pointer.
        int bottom = m - 1;
        // Initialize the left boundary pointer.
        int left = 0;
        // Initialize the right boundary pointer.
        int right = n - 1;

        // Continue the loop as long as the number of elements added to 'ans' is less than the total elements in the matrix.
        while (ans.size() != m * n) {
            // Traverse from left to right along the current top row.
            for (int i = left; i <= right; i++) {
                // Add the element at matrix[top][i] to the result list.
                ans.add(matrix[top][i]);
            }
            // Move the top boundary down by one, as the top row has been processed.
            top++;

            // Traverse from top to bottom along the current right column.
            // The condition 'ans.size() < m*n' is crucial to stop if all elements are already collected.
            for (int i = top; ans.size() < m * n && i <= bottom; i++) {
                // Add the element at matrix[i][right] to the result list.
                ans.add(matrix[i][right]);
            }
            // Move the right boundary left by one, as the right column has been processed.
            right--;

            // Traverse from right to left along the current bottom row.
            // The condition 'ans.size() < m*n' is crucial to stop if all elements are already collected.
            for (int i = right; ans.size() < m * n && i >= left; i--) {
                // Add the element at matrix[bottom][i] to the result list.
                ans.add(matrix[bottom][i]);
            }
            // Move the bottom boundary up by one, as the bottom row has been processed.
            bottom--;

            // Traverse from bottom to top along the current left column.
            // The condition 'ans.size() < m*n' is crucial to stop if all elements are already collected.
            for (int i = bottom; ans.size() < m * n && i >= top; i--) {
                // Add the element at matrix[i][left] to the result list.
                ans.add(matrix[i][left]);
            }
            // Move the left boundary right by one, as the left column has been processed.
            left++;
        }
        // Return the list containing all elements in spiral order.
        return ans;
    }
}
```

## Interview Tips
*   **Visualize the Movement:** Before coding, draw a small matrix and trace the spiral path with your finger, noting how the boundaries change.
*   **Handle Edge Cases Explicitly (or implicitly):** Think about 1x1, 1xN, Nx1 matrices. The boundary logic should naturally handle these, but it's good to mentally verify.
*   **Explain Boundary Updates:** Clearly articulate why and when you update `top`, `bottom`, `left`, and `right`. This is the core of the solution.
*   **Justify Inner Loop Conditions:** Explain why `ans.size() < m*n` is necessary inside the inner loops, especially for non-square matrices.

## Revision Checklist
- [ ] Understand the problem: traverse a 2D matrix in a spiral pattern.
- [ ] Identify boundary pointers: `top`, `bottom`, `left`, `right`.
- [ ] Implement traversal in four directions: right, down, left, up.
- [ ] Correctly update boundary pointers after each direction.
- [ ] Ensure termination conditions are robust (especially `ans.size() < m*n` in inner loops).
- [ ] Test with various matrix dimensions (square, rectangular, single row/column).
- [ ] Analyze time and space complexity.

## Similar Problems
*   [LeetCode 54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) (This problem)
*   [LeetCode 498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/)
*   [LeetCode 200. Number of Islands](https://leetcode.com/problems/number-of-islands/) (Uses BFS/DFS, but related to grid traversal)
*   [LeetCode 59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/) (Similar logic, but filling a matrix)

## Tags
`Array` `Matrix` `Simulation`
