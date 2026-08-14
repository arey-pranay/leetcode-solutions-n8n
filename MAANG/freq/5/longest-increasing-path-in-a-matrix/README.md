# Longest Increasing Path In A Matrix

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort` `Memoization` `Matrix` `Directed Acyclic Graph`  
**Time:** O(m*n)  
**Space:** O(m*n)

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
The problem is to find the longest increasing path in a given matrix. The solution uses depth-first search (DFS) to traverse the matrix and keep track of the longest path found so far.

## Intuition
The key insight here is that we can use DFS to explore all possible paths from each cell, and if we encounter a larger path, we update our result. We also use a cache to store the length of the longest increasing path starting from each cell, which helps us avoid redundant calculations.

## Algorithm

1. Initialize an answer variable `ans` to 1.
2. Create a hashmap `cache` to store the length of the longest increasing path starting from each cell.
3. Iterate over all cells in the matrix using nested loops.
4. For each cell `(i, j)`, call the `dfs` function with the current cell as the start point.
5. Update the answer variable `ans` with the maximum value between the current answer and the length of the longest increasing path starting from the current cell.
6. Return the updated answer.

## Concept to Remember

* **Depth-First Search (DFS)**: a graph traversal algorithm that uses a stack or recursion to explore nodes in a tree-like structure.
* **Memoization**: a technique used to optimize recursive functions by storing intermediate results in a cache.
* **Greedy Algorithm**: an algorithm that makes locally optimal choices with the hope of finding a global optimum.

## Common Mistakes

* **Not using memoization**: failure to store and reuse intermediate results can lead to redundant calculations and slow performance.
* **Incorrectly updating the answer variable**: forgetting to update `ans` with the maximum length found in the DFS traversal.
* **Ignoring edge cases**: failing to consider matrix boundaries, negative indices, or other special cases.

## Complexity Analysis
- Time: O(m*n) - reason / 4 nested loops over the matrix cells and their neighbors
- Space: O(m*n) - reason / cache stores lengths of longest paths for all cells

## Commented Code

```java
class Solution {
    HashMap<Integer, Integer> cache = new HashMap<>(); // store lengths of longest paths starting from each cell
    int[] neighs = new int[] { -1, 0, 1, 0, -1 }; // offsets for neighboring cells
    int ans = 1; // maximum length of increasing path found so far

    public int longestIncreasingPath(int[][] matrix) {
        // get matrix dimensions
        int m = matrix.length;
        int n = matrix[0].length;

        // iterate over all cells in the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // call dfs function to explore paths starting from current cell
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }

        return ans;
    }

    public int dfs(int[][] matrix, int i, int j) {
        // calculate unique start index for cache lookup
        int start = i * matrix[0].length + j;

        // check if result is already cached
        if (cache.containsKey(start)) {
            return cache.get(start);
        }

        int total = 1; // initialize length of longest path starting from current cell

        // explore neighboring cells
        for (int k = 0; k < 4; k++) {
            int I = i + neighs[k]; // get row index of neighbor
            int J = j + neighs[k + 1]; // get column index of neighbor

            // skip invalid or duplicate neighbors
            if (I < 0 || J < 0 || I == matrix.length || J == matrix[0].length || matrix[I][J] <= matrix[i][j]) {
                continue;
            }

            // recursively explore path starting from neighbor
            total = Math.max(total, 1 + dfs(matrix, I, J));
        }

        // cache result and return it
        cache.put(start, total);
        return total;
    }
}
```

## Interview Tips

* Make sure to understand the problem statement and constraints clearly.
* Be prepared to explain your solution and code to the interviewer.
* Use memoization or dynamic programming techniques when dealing with recursive problems like this one.
* Don't be afraid to ask for clarification if you're unsure about any part of the problem.

## Revision Checklist
- [ ] Review matrix dimensions and cell indices.
- [ ] Understand the role of the `cache` hashmap in memoizing intermediate results.
- [ ] Be prepared to explain DFS traversal and how it's used in this solution.
- [ ] Practice explaining your code and thought process to a peer or mentor.

## Similar Problems
* Longest Increasing Subsequence (LeetCode 300)
* Largest Submatrix (LeetCode 821)
* Matrix Search (LeetCode 73)

## Tags
`Array` `Hash Map` `Depth-First Search` `Memoization` `Greedy Algorithm`
