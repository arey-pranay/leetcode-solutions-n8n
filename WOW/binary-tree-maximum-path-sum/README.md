# Binary Tree Maximum Path Sum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Tree` `Depth-First Search` `Binary Tree` `DP on Trees`  
**Time:** O(N)  
**Space:** O(H)

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
    int ans =Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }
    public int func(TreeNode root){
        if(root==null) return 0;
        int lsum = Math.max(0,func(root.left));
        int rsum = Math.max(0,func(root.right));
        ans = Math.max(ans,lsum+rsum+root.val);
        return root.val+Math.max(lsum,rsum); 
        //kyuki mera parent, mere through -> ye est path le skta hai. (me + leftSubtree) or (me+rightSubTree)
    }
}
```

---

---
## Quick Revision
This problem asks for the maximum sum of a path in a binary tree, where a path can start and end at any node.
The solution uses a recursive approach to explore all possible paths and track the maximum sum found.

## Intuition
The "aha moment" comes from realizing that for any given node, the maximum path sum *passing through* that node can be formed by taking the node's value, plus the maximum path sum from its left subtree (if positive), plus the maximum path sum from its right subtree (if positive). However, when returning a value *up* to the parent, a path can only extend in one direction (either left or right from the current node). This means the recursive function needs to return the maximum path sum that *starts* at the current node and goes down into one of its subtrees.

## Algorithm
1. Initialize a global variable `maxSum` to the smallest possible integer value to store the overall maximum path sum found.
2. Define a recursive helper function `dfs(node)` that returns the maximum path sum starting from `node` and going downwards into one of its subtrees.
3. Base Case: If `node` is null, return 0 (as an empty path contributes nothing to the sum).
4. Recursively call `dfs` on the left child (`leftSum = dfs(node.left)`) and the right child (`rightSum = dfs(node.right)`).
5. For each child's sum, take `Math.max(0, childSum)`. This is crucial because if a subtree's maximum path sum is negative, we'd rather not include it in any path, effectively treating it as an empty path (sum of 0).
6. Calculate the potential maximum path sum that *passes through* the current `node`: `currentPathSum = leftSum + rightSum + node.val`.
7. Update the global `maxSum` with `Math.max(maxSum, currentPathSum)`. This step considers paths that might "turn" at the current node.
8. Return the maximum path sum that *starts* at the current `node` and extends downwards: `node.val + Math.max(leftSum, rightSum)`. This is the value that can be used by the parent node to extend its own path.
9. Call the `dfs` function with the `root` of the tree.
10. Return the final `maxSum`.

## Concept to Remember
*   **Tree Traversal (DFS):** Recursively visiting nodes in a depth-first manner is essential for exploring all subtrees.
*   **Dynamic Programming (Implicit):** The recursive calls solve subproblems (maximum path sums in subtrees) and combine their results. The `max(0, ...)` optimization is a form of pruning or choosing the best sub-solution.
*   **Path Definition:** Understanding that a path doesn't have to start at the root or end at a leaf, and can be a single node.

## Common Mistakes
*   **Not handling negative node values correctly:** Forgetting to use `Math.max(0, ...)` for subtree sums can lead to incorrect results if subtrees have negative maximum path sums.
*   **Confusing the return value of the recursive function:** The function needs to return the maximum path sum *extending downwards* from the current node, not the maximum path sum *passing through* the current node.
*   **Not initializing `maxSum` correctly:** Initializing `maxSum` to 0 might be incorrect if all node values are negative. It should be initialized to `Integer.MIN_VALUE`.
*   **Forgetting to update the global maximum:** The maximum path sum that turns at a node needs to be compared with the global maximum, but the function's return value is different.

## Complexity Analysis
*   Time: O(N) - Each node in the tree is visited exactly once by the DFS traversal.
*   Space: O(H) - Where H is the height of the tree. This is due to the recursion stack. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
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
    // Declare a class-level variable to store the maximum path sum found so far.
    // Initialize it to the smallest possible integer value to ensure any valid path sum will be greater.
    int ans = Integer.MIN_VALUE;

    // The main function that initiates the path sum calculation.
    public int maxPathSum(TreeNode root) {
        // Call the recursive helper function to traverse the tree and calculate path sums.
        func(root);
        // After the traversal, 'ans' will hold the maximum path sum.
        return ans;
    }

    // Recursive helper function to calculate the maximum path sum.
    // It returns the maximum path sum starting from 'root' and going downwards into one of its subtrees.
    public int func(TreeNode root) {
        // Base case: If the current node is null, it contributes 0 to any path.
        if (root == null) {
            return 0;
        }

        // Recursively calculate the maximum path sum from the left subtree.
        // We take Math.max(0, ...) because if the left subtree's max path sum is negative,
        // we don't want to include it, effectively treating it as an empty path (sum of 0).
        int lsum = Math.max(0, func(root.left));

        // Recursively calculate the maximum path sum from the right subtree.
        // Similar logic as for the left subtree.
        int rsum = Math.max(0, func(root.right));

        // Calculate the maximum path sum that *passes through* the current node.
        // This path can include the current node's value, plus the best path from the left, plus the best path from the right.
        // This is a potential candidate for the overall maximum path sum.
        ans = Math.max(ans, lsum + rsum + root.val);

        // Return the maximum path sum that *starts* at the current node and extends downwards into *one* of its subtrees.
        // This is the value that the parent node can use to extend its own path.
        // A path extending upwards can only go through one child branch.
        return root.val + Math.max(lsum, rsum);
    }
}
```

## Interview Tips
*   **Clarify Path Definition:** Ensure you understand what constitutes a "path" (can start/end anywhere, not necessarily root-to-leaf).
*   **Explain the Two Roles of the Recursive Function:** Clearly articulate that the function has two responsibilities:
    1.  Updating the global maximum with paths that *turn* at the current node.
    2.  Returning the maximum path sum that *extends downwards* to its parent.
*   **Justify `Math.max(0, ...)`:** Explain why this is crucial for handling negative subtree sums and not dragging down the overall path sum.
*   **Trace an Example:** Be prepared to walk through a small tree example to demonstrate how your algorithm works, especially with negative numbers.

## Revision Checklist
- [ ] Understand the definition of a path in a binary tree.
- [ ] Implement DFS traversal for binary trees.
- [ ] Correctly handle the base case for null nodes.
- [ ] Use `Math.max(0, ...)` for subtree sums.
- [ ] Differentiate between paths that "turn" at a node and paths that extend downwards.
- [ ] Initialize the global maximum sum variable correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Path Sum in a Binary Tree (LeetCode 124) - This is the exact problem.
*   Diameter of Binary Tree (LeetCode 543) - Similar in that it involves finding a maximum length path, but the definition of path and calculation are different.
*   Lowest Common Ancestor of a Binary Tree (LeetCode 236) - Related to tree traversal and understanding node relationships.

## Tags
`Tree` `Depth-First Search` `Dynamic Programming` `Binary Tree`
