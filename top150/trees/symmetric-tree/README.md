# Symmetric Tree

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
    boolean checked = false;
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return check(root.left,root.right);
    }
    public boolean check(TreeNode l, TreeNode r){
        if(l==null && r==null) return true;
        if(l==null || r==null || l.val!=r.val) return false;
        return  check(l.right,r.left) && check(l.left,r.right);
    }
}
```

---

---
## Quick Revision
Checks if a binary tree is a mirror image of itself around its center.
Solves by recursively comparing the left subtree's right child with the right subtree's left child, and vice-versa.

## Intuition
The core idea is that for a tree to be symmetric, its left and right subtrees must be mirror images of each other. This means the root's left child's *right* subtree must be a mirror of the root's right child's *left* subtree, and simultaneously, the root's left child's *left* subtree must be a mirror of the root's right child's *right* subtree. This recursive definition naturally leads to a solution.

## Algorithm
1.  **Base Case for Root:** If the root of the tree is null, it's considered symmetric. Return `true`.
2.  **Initiate Comparison:** Call a helper function `check` with the root's left child and the root's right child.
3.  **Helper Function `check(TreeNode l, TreeNode r)`:**
    *   **Both Null:** If both `l` and `r` are null, they are symmetric. Return `true`.
    *   **One Null or Value Mismatch:** If either `l` or `r` is null (but not both), or if their values (`l.val` and `r.val`) are different, they are not symmetric. Return `false`.
    *   **Recursive Check:** If the above conditions are false (meaning both nodes exist and have the same value), recursively check two pairs:
        *   Compare `l`'s right child with `r`'s left child (`check(l.right, r.left)`).
        *   Compare `l`'s left child with `r`'s right child (`check(l.left, r.right)`).
        *   Return `true` only if *both* of these recursive calls return `true`.

## Concept to Remember
*   **Tree Traversal:** Understanding how to traverse a binary tree, particularly using recursion.
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Symmetry/Mirroring:** The concept of comparing elements in a mirrored fashion.

## Common Mistakes
*   **Incorrect Recursive Calls:** Forgetting to swap the children when comparing (e.g., comparing `l.left` with `r.left` instead of `l.left` with `r.right`).
*   **Handling Null Nodes:** Not properly handling cases where one or both nodes in a comparison pair are null.
*   **Base Case Errors:** Missing the base case for an empty tree or for when two nodes are null.
*   **Comparing Root to Itself:** The initial call should be on the children of the root, not the root itself.

## Complexity Analysis
- Time: O(N) - reason: We visit each node in the tree exactly once.
- Space: O(H) - reason: In the worst case (a skewed tree), the recursion depth can be N. In a balanced tree, it's log N, where H is the height of the tree.

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
    // This boolean variable is not used in the provided solution and can be removed.
    // boolean checked = false;

    // The main function to check if the tree is symmetric.
    public boolean isSymmetric(TreeNode root) {
        // If the root is null, an empty tree is considered symmetric.
        if(root==null) return true;
        // Start the recursive comparison from the left and right children of the root.
        return check(root.left,root.right);
    }

    // Helper function to recursively check if two subtrees are mirrors of each other.
    public boolean check(TreeNode l, TreeNode r){
        // If both nodes are null, they are symmetric. This is a base case for recursion.
        if(l==null && r==null) return true;
        // If either node is null, or their values are different, they are not symmetric.
        if(l==null || r==null || l.val!=r.val) return false;
        // Recursively check the outer pair (left's right child vs. right's left child)
        // AND the inner pair (left's left child vs. right's right child).
        // Both must be true for the current pair of nodes to be considered symmetric.
        return  check(l.right,r.left) && check(l.left,r.right);
    }
}
```

## Interview Tips
*   **Explain the Mirroring Logic:** Clearly articulate why you need to compare `l.right` with `r.left` and `l.left` with `r.right`. This is the crux of the problem.
*   **Handle Edge Cases:** Explicitly mention how you handle null nodes and an empty tree.
*   **Trace an Example:** Walk through a small symmetric and a small asymmetric tree to demonstrate your understanding.
*   **Consider Iterative Approach:** Briefly mention that an iterative solution using a queue or stack is also possible, though recursion is often more intuitive here.

## Revision Checklist
- [ ] Understand the definition of a symmetric tree.
- [ ] Implement the recursive helper function correctly.
- [ ] Handle all null node scenarios in the recursion.
- [ ] Ensure the base cases for recursion are correct.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Validate Binary Search Tree
*   Invert Binary Tree
*   Maximum Depth of Binary Tree
*   Binary Tree Level Order Traversal

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `Recursion`

## My Notes
good quesss.
