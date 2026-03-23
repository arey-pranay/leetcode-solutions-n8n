# Maximum Depth Of Binary Tree

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
    public int maxDepth(TreeNode root) {
       if(root == null) return 0;
       return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
```

---

---
## Quick Revision
Given the root of a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Intuition
The depth of a binary tree is essentially the height of the tree. The height of a tree can be defined recursively:
- If the tree is empty (null root), its height is 0.
- Otherwise, the height is 1 (for the current node) plus the maximum height of its left or right subtree.
This recursive definition directly translates into a solution.

## Algorithm
1. **Base Case:** If the current node (`root`) is `null`, return `0` (an empty tree has depth 0).
2. **Recursive Step:**
   a. Recursively find the maximum depth of the left subtree: `maxDepth(root.left)`.
   b. Recursively find the maximum depth of the right subtree: `maxDepth(root.right)`.
   c. The maximum depth of the current tree is `1` (for the current node) plus the maximum of the depths of its left and right subtrees. Return `Math.max(depth_left, depth_right) + 1`.

## Concept to Remember
*   **Recursion:** Solving a problem by breaking it down into smaller, self-similar subproblems.
*   **Tree Traversal:** Understanding how to navigate through the nodes of a tree (in this case, implicitly via recursion).
*   **Base Cases:** Crucial for terminating recursive functions and preventing infinite loops.

## Common Mistakes
*   Forgetting the base case: Not handling `null` nodes, leading to `NullPointerException` or infinite recursion.
*   Incorrectly calculating depth: Forgetting to add `1` for the current node, or adding `2` instead.
*   Confusing depth with other tree properties: Misinterpreting what "maximum depth" means.
*   Iterative vs. Recursive thinking: Struggling to translate the recursive definition into an iterative approach (though recursion is more natural here).

## Complexity Analysis
- Time: O(N) - reason: Each node in the tree is visited exactly once.
- Space: O(H) - reason: In the worst case (a skewed tree), the recursion depth can be N, leading to O(N) space on the call stack. In a balanced tree, H is log N, so space is O(log N). H is the height of the tree.

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
    /**
     * Calculates the maximum depth of a binary tree.
     * @param root The root node of the binary tree.
     * @return The maximum depth of the tree.
     */
    public int maxDepth(TreeNode root) {
       // Base case: If the current node is null, it means we've reached the end of a path.
       // An empty tree or subtree has a depth of 0.
       if(root == null) return 0;

       // Recursive step:
       // 1. Calculate the maximum depth of the left subtree.
       int leftDepth = maxDepth(root.left);
       // 2. Calculate the maximum depth of the right subtree.
       int rightDepth = maxDepth(root.right);

       // The maximum depth of the current tree is 1 (for the current node)
       // plus the maximum of the depths of its left and right subtrees.
       return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

## Interview Tips
*   Clearly explain the recursive definition of tree depth before coding.
*   Walk through a small example (e.g., a tree with 3 nodes) to illustrate the recursion.
*   Mention the base case and why it's essential.
*   Discuss the time and space complexity, especially the dependency on tree height for space.

## Revision Checklist
- [ ] Understand the definition of tree depth.
- [ ] Implement the base case for `null` nodes.
- [ ] Implement the recursive calls for left and right children.
- [ ] Correctly combine results using `Math.max` and adding 1.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Minimum Depth of Binary Tree
*   Balanced Binary Tree
*   Diameter of Binary Tree

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`
