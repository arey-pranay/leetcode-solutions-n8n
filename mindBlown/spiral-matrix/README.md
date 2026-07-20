# Spiral Matrix

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m*n)  
**Space:** O(m*n)

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
Spiral through a 2D matrix by traversing in a clockwise spiral order.
This problem can be solved using four nested loops to traverse the matrix from left to right, top to bottom, right to left, and then back to top.

## Intuition
The key insight is that we need to keep track of the boundaries of the remaining unvisited cells. By maintaining four pointers (x0, x1, y0, y1) representing the top-left and bottom-right corners of the current rectangle, we can efficiently traverse the matrix in a spiral order.

## Algorithm
1. Initialize variables: `m` and `n` to store the number of rows and columns, respectively; `x0`, `x1`, `y0`, and `y1` to store the top-left and bottom-right corners of the current rectangle.
2. Create an empty list `ans` to store the spiral order of elements.
3. While there are still unvisited cells:
   1. Traverse from left to right (j=y0 to y1) and add elements to `ans`.
   2. Move the top boundary down by incrementing `x0`.
   3. Traverse from top to bottom (i=x0 to x1) and add elements to `ans`. Decrement `y1` since we're moving right.
   4. Traverse from right to left (j=y1 to y0) and add elements to `ans`. Decrement `x1`.
   5. Traverse from bottom to top (i=x1 to x0) and add elements to `ans`. Increment `y0`.

## Concept to Remember
*   **Boundary tracking**: Keeping track of the boundaries of a problem or data structure can be essential in solving complex problems efficiently.
*   **Divide and Conquer**: Breaking down a problem into smaller sub-problems that are easier to solve can help us tackle more complex problems.
*   **Corner cases**: Don't forget to handle edge cases, such as an empty matrix.

## Common Mistakes
*   Failing to initialize variables correctly or using incorrect data types.
*   Incorrectly implementing the four nested loops and their corresponding boundary updates.
*   Forgetting to check for corner cases (e.g., an empty matrix).
*   Not considering cases with very large input sizes, which might cause performance issues.

## Complexity Analysis
- Time: O(m*n) - We visit each cell once in the spiral order.
- Space: O(m*n) - In the worst case, we need to store all elements of the matrix in the `ans` list.

## Commented Code
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Get the dimensions of the matrix
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize pointers for boundary tracking
        int x0 = 0, x1 = m - 1, y0 = 0, y1 = n - 1;

        // Initialize an empty list to store the spiral order
        List<Integer> ans = new ArrayList<>();

        while (ans.size() != m * n) {
            // Traverse from left to right and add elements to ans
            for (int j = y0; j <= y1; j++) ans.add(matrix[x0][j]);
            x0++; // Move the top boundary down

            // Traverse from top to bottom and add elements to ans
            for (int i = x0; ans.size() < m * n && i <= x1; i++)
                ans.add(matrix[i][y1]); // Decrement y1 since we're moving right
            y1--; // Move the right boundary left

            // Traverse from right to left and add elements to ans
            for (int j = y1; ans.size() < m * n && j >= y0; j--)
                ans.add(matrix[x1][j]);
            x1--; // Move the bottom boundary up

            // Traverse from bottom to top and add elements to ans
            for (int i = x1; ans.size() < m * n && i >= x0; i--)
                ans.add(matrix[i][y0]); // Increment y0 since we're moving left
            y0++;
        }

        return ans;
    }
}
```

## Interview Tips
*   Make sure to initialize variables correctly and use the right data types.
*   Clearly understand how the four nested loops and their boundary updates work together to traverse the matrix in a spiral order.
*   Practice with different input sizes, including very large ones, to ensure your solution handles corner cases well.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Initialize variables correctly and use suitable data types.
- [ ] Implement the four nested loops accurately for boundary tracking.
- [ ] Verify that your solution handles all edge cases, especially an empty matrix.

## Similar Problems
*   "Matrix" problems like `Rotate Image`, where you're required to perform a specific operation on a 2D matrix.
*   Other problems that involve traversing or manipulating data structures in a particular order.
