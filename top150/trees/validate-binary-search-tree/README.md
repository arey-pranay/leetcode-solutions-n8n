# Validate Binary Search Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
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
    Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        return inorder(root);
    }
    public boolean inorder(TreeNode root){
        if(root==null) return true;
        boolean a = inorder(root.left);
        if(prev!=null && root.val <= prev) return false;
        prev = root.val;
        a &= inorder(root.right);
        return a;
    }
}
```

---

---
## Quick Revision
Checks if a given binary tree adheres to the properties of a Binary Search Tree (BST).
Solves by performing an in-order traversal and verifying the sorted order of visited nodes.

## Intuition
The core property of a BST is that for any node, all values in its left subtree are smaller than the node's value, and all values in its right subtree are larger. An in-order traversal of a BST visits nodes in ascending order. Therefore, if we perform an in-order traversal and keep track of the previously visited node's value, we can detect any violation of the BST property if the current node's value is not strictly greater than the previous one.

## Algorithm
1. Initialize a global or class-level variable `prev` to `null` to store the value of the previously visited node during the in-order traversal.
2. Define a recursive helper function `inorder(TreeNode node)`:
   a. Base Case: If `node` is `null`, return `true` (an empty tree is a valid BST).
   b. Recursively traverse the left subtree: Call `inorder(node.left)`. If this call returns `false`, propagate `false` upwards immediately.
   c. Check current node: If `prev` is not `null` (meaning we've visited at least one node before) AND `node.val` is less than or equal to `prev`, then the BST property is violated. Return `false`.
   d. Update `prev`: Set `prev` to `node.val`.
   e. Recursively traverse the right subtree: Call `inorder(node.right)`. If this call returns `false`, propagate `false` upwards immediately.
   f. Return `true` if all checks pass for this subtree.
3. Call the `inorder` function with the `root` of the tree.

## Concept to Remember
*   **Binary Search Tree (BST) Properties:** Left subtree values < node value < Right subtree values.
*   **In-order Traversal:** Visits nodes in Left -> Root -> Right order. For a BST, this yields sorted values.
*   **State Management in Recursion:** Using a variable (`prev`) to maintain state across recursive calls.

## Common Mistakes
*   **Only checking immediate children:** A common mistake is to only check if `root.left.val < root.val` and `root.right.val > root.val`. This doesn't account for values deeper in the subtrees (e.g., a node in the left subtree being greater than the root).
*   **Incorrectly handling `prev`:** Not initializing `prev` correctly or not updating it after visiting a node can lead to wrong comparisons.
*   **Integer Overflow with `Integer.MIN_VALUE`/`MAX_VALUE`:** If the problem statement allows for `Integer.MIN_VALUE` or `Integer.MAX_VALUE` as node values, using these as initial bounds for `prev` can be problematic. Using `null` for `prev` is safer.
*   **Not propagating `false` immediately:** Failing to return `false` as soon as a violation is detected can lead to unnecessary computations.

## Complexity Analysis
*   **Time:** O(N) - reason: Each node in the tree is visited exactly once during the in-order traversal.
*   **Space:** O(H) - reason: This is due to the recursion stack depth, where H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

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
    // 'prev' stores the value of the previously visited node in the in-order traversal.
    // It's initialized to null, indicating no node has been visited yet.
    Integer prev = null;

    // The main function to validate if the binary tree is a valid BST.
    public boolean isValidBST(TreeNode root) {
        // It calls the helper inorder traversal function starting from the root.
        return inorder(root);
    }

    // Recursive helper function to perform in-order traversal and validation.
    public boolean inorder(TreeNode root){
        // Base case: If the current node is null, it's a valid subtree (empty).
        if(root==null) return true;

        // Recursively traverse the left subtree.
        // 'a' will store the result of the left subtree validation.
        boolean a = inorder(root.left);

        // Check if the left subtree validation failed. If so, propagate false immediately.
        if(!a) return false;

        // Check the BST property for the current node.
        // If 'prev' is not null (meaning we've visited a node before)
        // AND the current node's value is less than or equal to the previous node's value,
        // then the BST property is violated.
        if(prev!=null && root.val <= prev) return false;

        // Update 'prev' to the current node's value, as we have now "visited" this node.
        prev = root.val;

        // Recursively traverse the right subtree.
        // The result of the right subtree validation is ANDed with the current state.
        // If the right subtree is invalid, 'a' will become false.
        a &= inorder(root.right);

        // Return the final validation result for this subtree.
        return a;
    }
}
```

## Interview Tips
*   **Explain the in-order traversal property:** Clearly articulate why an in-order traversal is the key to solving this problem.
*   **Discuss edge cases:** Mention handling `null` roots and single-node trees. Also, consider the constraints on node values (e.g., if `Integer.MIN_VALUE` or `MAX_VALUE` are possible).
*   **Clarify state management:** Explain how the `prev` variable is used to maintain state across recursive calls and why it's crucial.
*   **Consider alternative approaches:** Briefly mention the alternative approach using min/max bounds passed down recursively, and discuss its trade-offs (e.g., no global state, but more parameters).

## Revision Checklist
- [ ] Understand BST properties.
- [ ] Recall in-order traversal logic.
- [ ] Implement recursive in-order traversal.
- [ ] Correctly use a `prev` variable for comparison.
- [ ] Handle `null` nodes as base cases.
- [ ] Ensure immediate return on violation.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 987: Vertical Order Traversal of a Binary Tree
*   LeetCode 102: Binary Tree Level Order Traversal
*   LeetCode 103: Binary Tree Zigzag Level Order Traversal
*   LeetCode 230: Kth Smallest Element in a BST

## Tags
`Tree` `Depth-First Search` `Binary Search Tree` `Recursion`

## My Notes
key pattern used of BST, create global variable, use Integer to give null value instead of int, and perform check between inorder traversal wale dono calls
