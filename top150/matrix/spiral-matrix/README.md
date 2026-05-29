# Spiral Matrix

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m * n)  
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
Traverse the matrix layer by layer, shrinking the boundaries with each full traversal.

## Intuition
The problem asks us to traverse a 2D matrix in a spiral pattern. Imagine peeling an onion layer by layer. We can simulate this by defining four boundaries: top, bottom, left, and right. We traverse along the top row from left to right, then down the rightmost column, then across the bottom row from right to left, and finally up the leftmost column. After completing one full spiral layer, we shrink these boundaries inwards and repeat the process until all elements are visited. The key is to carefully manage the boundary updates and the conditions to stop traversing a particular direction to avoid re-visiting elements or going out of bounds.

## Algorithm
1. Initialize an empty list `ans` to store the spiral order traversal.
2. Get the dimensions of the matrix: `m` (rows) and `n` (columns).
3. Initialize four boundary pointers: `top = 0`, `bottom = m - 1`, `left = 0`, `right = n - 1`.
4. Start a `while` loop that continues as long as the number of elements added to `ans` is less than the total number of elements in the matrix (`m * n`).
5. **Traverse Right:** Iterate from `left` to `right` along the `top` row. Add `matrix[top][i]` to `ans`. Increment `top`.
6. **Traverse Down:** Iterate from `top` to `bottom` along the `right` column. Add `matrix[i][right]` to `ans`. Decrement `right`.
7. **Traverse Left:** Iterate from `right` to `left` along the `bottom` row. Add `matrix[bottom][i]` to `ans`. Decrement `bottom`.
8. **Traverse Up:** Iterate from `bottom` to `top` along the `left` column. Add `matrix[i][left]` to `ans`. Increment `left`.
9. The `while` loop condition `ans.size() != m*n` and the inner loop conditions `ans.size() < m*n` are crucial to handle cases where the matrix might not be a perfect square or when the last layer is traversed.
10. Return the `ans` list.

## Concept to Remember
*   **Boundary Management:** Effectively tracking and updating the boundaries of the traversal is key to simulating the spiral movement.
*   **Layer-by-Layer Traversal:** The problem can be broken down into processing the matrix in concentric layers.
*   **Edge Case Handling:** Ensuring correct termination conditions for loops and handling non-square matrices is important.
*   **In-place Traversal Simulation:** The algorithm simulates traversal without modifying the original matrix.

## Common Mistakes
*   **Incorrect Boundary Updates:** Forgetting to update `top`, `bottom`, `left`, or `right` after each traversal direction can lead to infinite loops or incorrect results.
*   **Overlapping Traversal:** Not checking if all elements have been visited (`ans.size() < m*n`) within the inner loops can cause elements to be added multiple times, especially in smaller or non-square matrices.
*   **Off-by-One Errors:** Incorrect loop bounds (e.g., `i <= right` vs. `i < right`) can lead to missing or extra elements.
*   **Handling Single Row/Column Matrices:** The logic must gracefully handle matrices with only one row or one column.

## Complexity Analysis
*   **Time:** O(m * n) - Each element in the matrix is visited and added to the result list exactly once.
*   **Space:** O(1) - If we don't count the output list. The space used by the boundary variables is constant. If we count the output list, it's O(m * n) as it stores all elements.

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

        // Continue the loop as long as the number of elements in 'ans' is less than the total elements in the matrix.
        while (ans.size() != m * n) {
            // Traverse from left to right along the top row.
            for (int i = left; i <= right; i++) {
                // Add the current element to the result list.
                ans.add(matrix[top][i]);
            }
            // Move the top boundary down by one row.
            top++;

            // Traverse from top to bottom along the rightmost column.
            // The condition 'ans.size() < m*n' is crucial to stop if all elements are already added.
            for (int i = top; ans.size() < m * n && i <= bottom; i++) {
                // Add the current element to the result list.
                ans.add(matrix[i][right]);
            }
            // Move the right boundary left by one column.
            right--;

            // Traverse from right to left along the bottom row.
            // The condition 'ans.size() < m*n' is crucial to stop if all elements are already added.
            for (int i = right; ans.size() < m * n && i >= left; i--) {
                // Add the current element to the result list.
                ans.add(matrix[bottom][i]);
            }
            // Move the bottom boundary up by one row.
            bottom--;

            // Traverse from bottom to top along the leftmost column.
            // The condition 'ans.size() < m*n' is crucial to stop if all elements are already added.
            for (int i = bottom; ans.size() < m * n && i >= top; i--) {
                // Add the current element to the result list.
                ans.add(matrix[i][left]);
            }
            // Move the left boundary right by one column.
            left++;
        }
        // Return the list containing elements in spiral order.
        return ans;
    }
}
```

## Interview Tips
*   **Visualize the Movement:** Before coding, draw a small matrix and trace the spiral path yourself. This helps solidify the boundary logic.
*   **Explain Boundary Updates:** Clearly articulate how and why you update `top`, `bottom`, `left`, and `right` after each directional traversal.
*   **Address Termination Conditions:** Emphasize the importance of the `ans.size() != m*n` condition in the `while` loop and the similar checks within the `for` loops to prevent infinite loops or duplicate entries, especially for non-square matrices.
*   **Handle Edge Cases Verbally:** Mention how your algorithm handles matrices with a single row, single column, or even a 1x1 matrix.

## Revision Checklist
- [ ] Understand the spiral traversal pattern.
- [ ] Implement boundary pointers (top, bottom, left, right).
- [ ] Correctly update boundaries after each directional traversal.
- [ ] Ensure termination conditions prevent infinite loops and duplicate elements.
- [ ] Test with various matrix sizes (square, rectangular, single row/column).

## Similar Problems
*   [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/) (Generates a spiral matrix)
*   [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/) (Traverses diagonally)
*   [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) (Different problem, but tests fundamental data structure manipulation)

## Tags
`Array` `Matrix` `Simulation`
