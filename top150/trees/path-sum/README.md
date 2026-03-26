# Path Sum

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`  
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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        if(root.left == null && root.right == null) return targetSum == root.val;
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }
}
```

---

---
## Quick Revision
Given the root of a binary tree and an integer targetSum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
This is solved using recursion, traversing down the tree and subtracting node values from the target sum.

## Intuition
The core idea is to explore all possible root-to-leaf paths. As we traverse down a path, we can keep track of the remaining sum needed. If we reach a leaf node and the remaining sum is exactly zero, we've found a valid path. If we go down a path and the remaining sum becomes negative or we reach a null node without satisfying the condition, that path is invalid. The "aha moment" is realizing that we can modify the `targetSum` as we go down, effectively checking if the *remaining* sum at a leaf node is zero.

## Algorithm
1.  **Base Case 1 (Empty Tree):** If the `root` is `null`, there are no paths, so return `false`.
2.  **Base Case 2 (Leaf Node):** If the current node is a leaf node (i.e., `root.left` is `null` AND `root.right` is `null`), check if the `targetSum` is equal to the current node's value (`root.val`). If they are equal, return `true`; otherwise, return `false`.
3.  **Recursive Step:** For non-leaf nodes, recursively call `hasPathSum` on the left child and the right child.
    *   For the left child, the new `targetSum` will be `targetSum - root.val`.
    *   For the right child, the new `targetSum` will be `targetSum - root.val`.
4.  **Combine Results:** Return `true` if *either* the recursive call on the left child returns `true` *or* the recursive call on the right child returns `true`. This signifies that at least one valid path exists.

## Concept to Remember
*   **Tree Traversal (Depth-First Search - DFS):** The problem inherently involves exploring paths in a tree, which is a classic application of DFS. Recursion is a natural way to implement DFS.
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems. The solution to the larger problem depends on the solutions to these subproblems.
*   **Base Cases:** Essential for terminating recursive calls and providing a definitive answer for the simplest scenarios.

## Common Mistakes
*   **Incorrect Leaf Node Check:** Forgetting to check if a node is *both* a left and right child being null, leading to incorrect path termination.
*   **Not Subtracting from Target Sum:** Failing to update the `targetSum` as we move down the tree, thus not tracking the remaining sum needed.
*   **Handling Empty Tree:** Not considering the edge case where the input `root` is `null`.
*   **Returning Incorrectly for Non-Leaf Nodes:** Returning `false` immediately if one branch doesn't find a path, instead of checking the other branch.

## Complexity Analysis
*   **Time:** O(N) - reason: In the worst case, we visit every node in the tree exactly once. N is the number of nodes in the tree.
*   **Space:** O(H) - reason: This is due to the recursion call stack. H is the height of the tree. In the worst case (a skewed tree), H can be N, making it O(N). In a balanced tree, H is log N, making it O(log N).

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node
 *     TreeNode left; // Pointer to the left child
 *     TreeNode right; // Pointer to the right child
 *     TreeNode() {} // Default constructor
 *     TreeNode(int val) { this.val = val; } // Constructor with value
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Method to check if there's a root-to-leaf path that sums up to targetSum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: If the current node is null, there's no path, so return false.
        if(root==null) return false;
        // Base case: If the current node is a leaf node (no left or right children)
        if(root.left == null && root.right == null) {
            // Check if the remaining targetSum equals the value of this leaf node.
            return targetSum == root.val;
        }
        // Recursive step: Explore the left and right subtrees.
        // For each recursive call, subtract the current node's value from the targetSum.
        // Return true if EITHER the left path OR the right path leads to a valid sum.
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }
}
```

## Interview Tips
*   **Clarify "Root-to-Leaf":** Ensure you understand that the path must start at the root and end at a leaf node.
*   **Trace with Examples:** Walk through a small tree with a few nodes, both for a successful path and an unsuccessful one, to demonstrate your understanding.
*   **Discuss Edge Cases:** Mention how you handle an empty tree (`root == null`) and a tree with only one node.
*   **Explain Recursion:** Clearly articulate how the `targetSum` is being reduced and how the base cases stop the recursion.

## Revision Checklist
- [ ] Understand the problem statement: root-to-leaf path sum.
- [ ] Identify base cases: null node, leaf node.
- [ ] Implement recursive calls for left and right children.
- [ ] Correctly update `targetSum` in recursive calls.
- [ ] Combine results from recursive calls using OR logic.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases like an empty tree.

## Similar Problems
*   LeetCode 112: Path Sum
*   LeetCode 113: Path Sum II
*   LeetCode 129: Sum Root to Leaf Numbers
*   LeetCode 102: Binary Tree Level Order Traversal (related for tree traversal concepts)

## Tags
`Tree` `Depth-First Search` `Recursion` `Binary Tree`

## My Notes
3 liner
