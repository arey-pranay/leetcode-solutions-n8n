# Flatten Binary Tree To Linked List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Stack` `Tree` `Depth-First Search` `Binary Tree`  
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
  
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root==null) return;
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = prev;
        prev = root;
    }

      // ArrayList<TreeNode> arr = new ArrayList<>();
      // root.left = null;
        // TreeNode tail = root;        
        // // root.right = @12893712
        // // root.left = @1897393
        
        // for(int i = 1; i<arr.size(); i++){
        //     TreeNode curr =arr.get(i);
        //     curr.right = null;
        //     curr.left = null;
        //     tail.right = curr;
        //     tail.left = null;
        //     if(tail==root){
        //         tail = tail.right;
        //         root.right = tail;
        //     } else tail = tail.right;
        // }
}
```

---

---
## Quick Revision
The problem asks to transform a binary tree into a linked list in-place, where each node's right child points to the next node in the pre-order traversal, and the left child is always null.
This is achieved by recursively flattening the right and left subtrees, then rearranging the pointers to link them in pre-order.

## Intuition
The goal is to create a structure where `node.right` points to the next node in a pre-order traversal, and `node.left` is always null. If we consider a node `N`, its pre-order successor will be the first node in its left subtree (if it exists), or the first node in its right subtree (if the left subtree doesn't exist).

The key insight comes from thinking about the order of operations. If we process the tree in a way that allows us to know the "next" node in the flattened list when we are at a particular node, we can perform the re-linking. A post-order traversal (specifically, processing right, then left, then the current node) is very effective here.

Imagine we are at a node `root`. If we have already flattened its right subtree and its left subtree, and we know what the *previous* node processed in the flattened list was, we can easily link `root` to it.
Specifically, when we process `root` *after* its right and left subtrees have been flattened:
1. The `root.right` should point to whatever `prev` was (which is the node that should come *after* `root` in the flattened list).
2. The `root.left` should become `null`.
3. Then, `root` itself becomes the new `prev` for the node that will be processed before it (which is its parent in the original tree, or a node further up the recursion stack).

This recursive approach, working from the "end" of the flattened list backwards towards the root, naturally builds the linked list structure.

## Algorithm
1. **Base Case:** If the `root` is `null`, return immediately.
2. **Recursive Calls:**
   - Recursively call `flatten` on the `root.right` subtree. This ensures the right subtree is flattened first.
   - Recursively call `flatten` on the `root.left` subtree. This ensures the left subtree is flattened next.
3. **Re-linking:** After both subtrees are flattened:
   - Store the current `root.right` in a temporary variable (let's call it `tempRight`). This is the head of the already flattened right subtree.
   - Set `root.left` to `null`.
   - Set `root.right` to `prev`. `prev` holds the head of the *previously* processed flattened subtree (which would be the flattened left subtree, or if no left subtree, the flattened right subtree).
   - Update `prev` to be the current `root`. This `root` will now be the "previous" node for the node that called this recursive step.

A global or member variable `prev` is used to keep track of the head of the already flattened portion of the tree as we backtrack from the recursion. It's initialized to `null`.

## Concept to Remember
*   **Tree Traversal:** Understanding pre-order, in-order, and post-order traversals is crucial. This solution leverages a modified post-order approach.
*   **Recursion:** The problem is elegantly solved using recursion, breaking down the tree into smaller subproblems.
*   **In-place Modification:** The requirement to modify the tree in-place without using extra data structures (like an `ArrayList` for traversal) is a key constraint.
*   **Pointer Manipulation:** Carefully managing `left` and `right` pointers is essential for correctly restructuring the tree.

## Common Mistakes
*   **Incorrect Traversal Order:** Trying to flatten by processing the left subtree before the right subtree, or processing the current node too early, leads to incorrect linking.
*   **Not Handling Nulls:** Failing to check for `null` nodes at various stages of recursion can lead to `NullPointerException`s.
*   **Losing Track of Pointers:** Incorrectly overwriting `root.right` or `root.left` before they are used or saved can break the structure.
*   **Using Extra Space (if not allowed):** While an `ArrayList` approach is intuitive for collecting nodes, it violates the in-place requirement.
*   **Confusing `prev`:** Misunderstanding what `prev` represents (the head of the *already flattened* portion that should come *after* the current node) can lead to incorrect assignments.

## Complexity Analysis
*   **Time:** O(N) - reason: Each node in the tree is visited exactly once during the recursive traversal and pointer manipulation.
*   **Space:** O(H) - reason: This is due to the recursion stack depth, where H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node.
 *     TreeNode left; // Pointer to the left child.
 *     TreeNode right; // Pointer to the right child.
 *     TreeNode() {} // Default constructor.
 *     TreeNode(int val) { this.val = val; } // Constructor with value.
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children.
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 'prev' is a member variable to keep track of the previously processed node
    // in the flattened list. It's initialized to null.
    // As we backtrack from recursion, 'prev' will point to the head of the
    // already flattened portion of the tree that should come *after* the current node.
    TreeNode prev = null;

    /**
     * Flattens a binary tree into a linked list in-place.
     * The flattened list should follow the pre-order traversal of the original tree.
     * Each node's left child should be null, and its right child should point to the next node.
     * @param root The root of the binary tree to flatten.
     */
    public void flatten(TreeNode root) {
        // Base case: If the current node is null, there's nothing to flatten, so return.
        if(root==null) return;

        // Recursively flatten the right subtree first.
        // This ensures that when we process the current node, its right subtree
        // is already flattened and 'prev' will point to its head.
        flatten(root.right);

        // Recursively flatten the left subtree.
        // After this call, 'prev' will point to the head of the flattened left subtree.
        flatten(root.left);

        // Now, re-link the current node 'root' to form the flattened list.

        // 1. Set the left child of the current node to null.
        // According to the problem statement, all left children must be null in the flattened list.
        root.left = null;

        // 2. Set the right child of the current node to 'prev'.
        // 'prev' currently holds the head of the flattened left subtree (or the flattened right subtree
        // if there was no left subtree). This correctly links the current node to the next part of the list.
        root.right = prev;

        // 3. Update 'prev' to be the current node.
        // The current node 'root' now becomes the head of the flattened portion processed so far.
        // This 'root' will be the 'prev' for the node that called this recursive step (its parent).
        prev = root;
    }
}
```

## Interview Tips
*   **Explain the `prev` variable:** Clearly articulate what `prev` represents and why it's crucial for linking nodes correctly. Emphasize that it tracks the head of the *already flattened* portion.
*   **Trace an example:** Walk through a small tree (e.g., a root with a left and right child) to demonstrate how the `flatten` function and the `prev` variable work step-by-step.
*   **Discuss alternative approaches:** Briefly mention that an iterative approach using a stack or Morris traversal is possible, but the recursive solution is often more intuitive for this problem.
*   **Clarify in-place requirement:** Ensure you understand if auxiliary space is strictly forbidden. If not, an `ArrayList` approach could be a starting point for discussion before optimizing.

## Revision Checklist
- [ ] Understand the pre-order traversal requirement.
- [ ] Grasp the role of the `prev` variable in the recursive solution.
- [ ] Trace the recursive calls and pointer reassignments for a small tree.
- [ ] Implement the recursive solution from scratch.
- [ ] Analyze time and space complexity.
- [ ] Be prepared to discuss alternative iterative solutions.

## Similar Problems
*   [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)
*   [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
*   [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/)
*   [114. Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) (This is the problem itself)
*   [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

## Tags
`Tree` `Depth-First Search` `Recursion` `Linked List`

## My Notes
reverse of what is told .
