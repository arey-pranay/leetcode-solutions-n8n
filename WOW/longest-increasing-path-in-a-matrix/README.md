# Longest Increasing Path In A Matrix

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort` `Memoization` `Matrix` `Directed Acyclic Graph`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    HashMap<Integer, Integer> cache = new HashMap<>();
    int[] neighs = new int[] { -1, 0, 1, 0, -1 };
    int ans = 1;

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int i, int j) {
        int start = i * matrix[0].length + j;
        if (cache.containsKey(start))
        return cache.get(start);
        int total = 1;
        for (int k = 0; k < 4; k++) {
            int I = i + neighs[k];
            int J = j + neighs[k + 1];
            if (I < 0 || J < 0 || I == matrix.length || J == matrix[0].length || matrix[I][J] <= matrix[i][j])
                continue;
            total = Math.max(total, 1 + dfs(matrix, I, J));
        }
        cache.put(start, total);
        return total;
    }
}

```

---

---
## Quick Revision
Find the longest path in a matrix where each step moves to an adjacent cell with a strictly greater value.
This is solved using Depth First Search (DFS) with memoization to avoid redundant calculations.

## Intuition
The problem asks for the longest path, which immediately suggests graph traversal. Since we can only move to adjacent cells with a greater value, this forms a Directed Acyclic Graph (DAG). For any cell, the longest increasing path starting from it is 1 (the cell itself) plus the maximum of the longest increasing paths starting from its valid neighbors. To efficiently find this maximum, we can use DFS. However, a naive DFS would recompute the longest path for the same cell multiple times. This is where memoization (dynamic programming) comes in. We store the result for each cell once computed, so if we encounter it again, we can directly return the stored value.

## Algorithm
1. Initialize a memoization cache (e.g., a HashMap or a 2D array) to store the length of the longest increasing path starting from each cell. Initialize all entries to 0 or -1 to indicate they haven't been computed yet.
2. Iterate through each cell (i, j) in the matrix.
3. For each cell, call a DFS function `dfs(matrix, i, j)` to compute the longest increasing path starting from that cell.
4. The `dfs` function will:
    a. Check if the result for cell (i, j) is already in the cache. If yes, return the cached value.
    b. Initialize a variable `maxLength` to 1 (representing the current cell itself).
    c. Define possible movements (up, down, left, right).
    d. For each valid neighbor (ni, nj) of (i, j):
        i. Check if the neighbor is within the matrix bounds.
        ii. Check if the neighbor's value `matrix[ni][nj]` is strictly greater than `matrix[i][j]`.
        iii. If both conditions are met, recursively call `dfs(matrix, ni, nj)` to get the longest increasing path from the neighbor.
        iv. Update `maxLength` to be the maximum of its current value and `1 + dfs(matrix, ni, nj)`.
    e. Store the computed `maxLength` in the cache for cell (i, j).
    f. Return `maxLength`.
5. Keep track of the overall maximum length found across all starting cells.
6. Return the overall maximum length.

## Concept to Remember
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking.
*   **Memoization (Top-Down Dynamic Programming):** Storing the results of expensive function calls and returning the cached result when the same inputs occur again.
*   **Directed Acyclic Graph (DAG):** The problem can be modeled as finding the longest path in a DAG, where edges go from a cell to an adjacent cell with a strictly greater value.
*   **Adjacency and Boundary Checks:** Crucial for correctly navigating the matrix and avoiding out-of-bounds errors.

## Common Mistakes
*   **Not using Memoization:** Leading to exponential time complexity due to redundant computations.
*   **Incorrect Boundary Checks:** Missing checks for `i < 0`, `j < 0`, `i >= m`, or `j >= n`.
*   **Incorrect Condition for Increasing Path:** Using `<=` instead of `<` or checking for strictly greater value in the wrong direction.
*   **Forgetting to add 1:** When calculating the path length from a neighbor, forgetting to add 1 for the current cell.
*   **Cache Key Generation:** If using a HashMap, ensuring a unique and correct key is generated for each cell (e.g., `i * n + j`).

## Complexity Analysis
*   **Time:** O(m * n) - Each cell is visited and processed by DFS at most once due to memoization. For each cell, we explore its 4 neighbors.
*   **Space:** O(m * n) - For the memoization cache (e.g., HashMap or 2D array) to store results for each cell, and for the recursion stack in the worst case (a path that visits all cells).

## Commented Code
```java
/**
 * Definition for a binary tree node. // This comment seems misplaced from a different problem.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // HashMap to store the computed longest increasing path length for each cell.
    // Key: a unique identifier for the cell (e.g., i * n + j), Value: the length.
    HashMap<Integer, Integer> cache = new HashMap<>();
    // Array to represent the four possible movements: up, down, left, right.
    // neighs[0] = -1, neighs[1] = 0  -> (i-1, j)  (Up)
    // neighs[1] = 0,  neighs[2] = 1  -> (i, j+1)  (Right)
    // neighs[2] = 1,  neighs[3] = 0  -> (i+1, j)  (Down)
    // neighs[3] = 0,  neighs[4] = -1 -> (i, j-1)  (Left)
    int[] neighs = new int[] { -1, 0, 1, 0, -1 };
    // Variable to store the overall maximum longest increasing path found so far.
    int ans = 1; // Initialize to 1 because a single cell is a path of length 1.

    // Main function to find the longest increasing path in the matrix.
    public int longestIncreasingPath(int[][] matrix) {
        // Get the number of rows in the matrix.
        int m = matrix.length;
        // Get the number of columns in the matrix.
        int n = matrix[0].length;
        // Iterate through each cell of the matrix.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // For each cell, compute the longest increasing path starting from it using DFS.
                // Update the overall maximum answer if the current path is longer.
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        // Return the overall maximum longest increasing path found.
        return ans;
    }

    // Depth First Search function to compute the longest increasing path starting from cell (i, j).
    public int dfs(int[][] matrix, int i, int j) {
        // Create a unique key for the current cell (i, j) to use in the cache.
        // This maps a 2D coordinate to a 1D integer.
        int start = i * matrix[0].length + j;
        // Check if the result for this cell has already been computed and stored in the cache.
        if (cache.containsKey(start))
            // If yes, return the cached value to avoid recomputation.
            return cache.get(start);
        
        // Initialize the length of the longest increasing path starting from the current cell to 1 (the cell itself).
        int total = 1;
        // Iterate through the four possible directions (up, down, left, right).
        for (int k = 0; k < 4; k++) {
            // Calculate the coordinates of the neighboring cell.
            int I = i + neighs[k];     // Row of the neighbor
            int J = j + neighs[k + 1]; // Column of the neighbor

            // Check if the neighbor is within the matrix bounds.
            // Also, check if the neighbor's value is strictly greater than the current cell's value.
            if (I < 0 || J < 0 || I == matrix.length || J == matrix[0].length || matrix[I][J] <= matrix[i][j])
                // If the neighbor is out of bounds or its value is not strictly greater, skip this neighbor.
                continue;
            
            // If the neighbor is valid, recursively call DFS on the neighbor.
            // The length of the path through this neighbor is 1 (for the current cell) + the longest path from the neighbor.
            // Update 'total' to be the maximum path length found so far through any valid neighbor.
            total = Math.max(total, 1 + dfs(matrix, I, J));
        }
        // Store the computed longest increasing path length for the current cell (i, j) in the cache.
        cache.put(start, total);
        // Return the computed longest increasing path length for the current cell.
        return total;
    }
}
```

## Interview Tips
*   **Explain the DAG analogy:** Clearly articulate that the problem can be viewed as finding the longest path in a DAG, which justifies using DFS.
*   **Emphasize Memoization:** Stress the importance of memoization to optimize from exponential to polynomial time complexity. Explain how it prevents redundant calculations.
*   **Walk through an example:** Use a small 2x2 or 3x3 matrix to trace the DFS calls and how the cache is populated. This demonstrates your understanding of the algorithm's execution.
*   **Discuss edge cases:** Mention handling empty matrices (though constraints usually prevent this) or matrices with all identical values.
*   **Clarify cache key generation:** If using a HashMap, be prepared to explain how you uniquely map 2D coordinates to a 1D key.

## Revision Checklist
- [ ] Understand the problem: Longest path with strictly increasing adjacent values.
- [ ] Recognize the DAG structure.
- [ ] Implement DFS correctly.
- [ ] Implement memoization (cache) to store results.
- [ ] Handle boundary conditions for matrix traversal.
- [ ] Ensure the increasing path condition (`matrix[neighbor] > matrix[current]`) is correct.
- [ ] Calculate path lengths by adding 1 to the recursive result.
- [ ] Iterate through all cells as potential starting points.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Pacific Atlantic Water Flow
*   Course Schedule II
*   Longest Increasing Subsequence
*   Word Search II

## Tags
`Array` `Dynamic Programming` `Depth-First Search` `Graph` `Memoization`
