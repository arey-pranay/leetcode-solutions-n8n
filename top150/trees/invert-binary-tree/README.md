# Invert Binary Tree

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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
```

---

---
## Quick Revision
Given the root of a binary tree, swap the left and right children of all nodes.
This is solved recursively by swapping children and then inverting the subtrees.

## Intuition
The core idea is that to invert a binary tree, we need to invert its left subtree, invert its right subtree, and then swap the left and right children of the current node. This process naturally lends itself to a recursive solution. If we consider a single node, its inversion involves swapping its immediate children. If we extend this to the entire tree, we realize that each node's children need to be swapped, and this must happen for all nodes. The recursive calls ensure that this swapping happens at every level of the tree.

## Algorithm
1. **Base Case:** If the current node (`root`) is null, return null (an empty tree is already inverted).
2. **Swap Children:** Create a temporary variable to hold the right child of the current node. Assign the left child to the right child. Assign the temporary variable (original right child) to the left child.
3. **Recurse on Left Subtree:** Call `invertTree` on the current node's left child.
4. **Recurse on Right Subtree:** Call `invertTree` on the current node's right child.
5. **Return Root:** Return the current node (`root`), which is now the root of the inverted subtree.

## Concept to Remember
*   **Recursion:** Solving a problem by breaking it down into smaller, self-similar subproblems.
*   **Tree Traversal:** Understanding how to visit and manipulate nodes in a tree structure (specifically, a pre-order or post-order like traversal here).
*   **In-place Modification:** Modifying the existing tree structure without creating a new one.

## Common Mistakes
*   **Forgetting the Base Case:** Not handling the `null` root scenario, which can lead to NullPointerExceptions.
*   **Incorrect Swap Logic:** Swapping the children before recursively calling on the original children, leading to incorrect inversions.
*   **Not Returning the Root:** Failing to return the modified root node, which is crucial for the caller to receive the inverted tree.
*   **Infinite Recursion:** If the swap logic is flawed or the base case is missing, the recursion might not terminate.

## Complexity Analysis
- Time: O(N) - reason: Each node in the tree is visited and processed exactly once.
- Space: O(H) - reason: Due to the recursion stack. In the worst case (a skewed tree), H can be N. In a balanced tree, H is log N.

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
    public TreeNode invertTree(TreeNode root) {
        // Base case: If the tree is empty (root is null), return null.
        if(root == null) return null;

        // Temporarily store the right child before overwriting it.
        TreeNode temp = root.right;
        // Set the right child to be the current left child.
        root.right = root.left;
        // Set the left child to be the original right child (stored in temp).
        root.left = temp;

        // Recursively invert the left subtree.
        invertTree(root.left);
        // Recursively invert the right subtree.
        invertTree(root.right);

        // Return the root of the inverted tree.
        return root;
    }
}
```

## Interview Tips
*   **Visualize:** Draw a small tree on paper and trace the recursive calls and swaps. This helps solidify understanding.
*   **Explain Recursion:** Clearly articulate the base case and the recursive step. Explain why the swap happens *before* or *after* the recursive calls (in this case, it's effectively pre-order for the swap, then post-order for the recursive calls to complete).
*   **Consider Iterative Approach:** Be prepared to discuss or implement an iterative solution using a stack or queue (BFS/DFS).
*   **Edge Cases:** Mention handling an empty tree (`root == null`) and a tree with only one node.

## Revision Checklist
- [ ] Understand the problem statement: Swap left and right children for all nodes.
- [ ] Identify the recursive structure: Invert subtrees and swap children.
- [ ] Implement the base case: `root == null`.
- [ ] Implement the swap logic correctly.
- [ ] Make recursive calls for both left and right children.
- [ ] Ensure the root is returned.
- [ ] Analyze time and space complexity.
- [ ] Consider iterative solutions (optional but good).

## Similar Problems
*   Maximum Depth of Binary Tree
*   Symmetric Tree
*   Binary Tree Level Order Traversal
*   Flatten Binary Tree to Linked List

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `Recursion`
