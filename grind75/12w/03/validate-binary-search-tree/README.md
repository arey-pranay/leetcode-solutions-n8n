# Validate Binary Search Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java
class Solution {
    Integer prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        boolean temp = isValidBST(root.left);
        if(prev != null && prev >= root.val) return false;
        prev=root.val;
        temp &= isValidBST(root.right);
        return temp;
    }
}
```

---

---
## Quick Revision
Checks if a given binary tree adheres to the properties of a Binary Search Tree (BST).
Solves by performing an in-order traversal and checking if the sequence of visited nodes is strictly increasing.

## Intuition
The core property of a BST is that for any node, all values in its left subtree are smaller than the node's value, and all values in its right subtree are larger. An in-order traversal of a BST visits nodes in ascending order. Therefore, if we perform an in-order traversal and keep track of the previously visited node's value, we can validate the BST property. If at any point the current node's value is less than or equal to the previous node's value, it violates the BST property.

## Algorithm
1. Initialize a variable `prev` to `null` to store the value of the previously visited node.
2. Define a recursive helper function `isValidBST(TreeNode node)`.
3. Base Case: If the current `node` is `null`, return `true` (an empty tree is a valid BST).
4. Recursively call `isValidBST` on the left child: `isValidBST(node.left)`.
5. Check the BST property: If `prev` is not `null` and `prev` is greater than or equal to the current `node.val`, return `false` (violation).
6. Update `prev`: Set `prev` to `node.val`.
7. Recursively call `isValidBST` on the right child: `isValidBST(node.right)`.
8. Return the result of the right subtree traversal (which implicitly includes the checks from the left subtree and the current node).

## Concept to Remember
*   **Binary Search Tree (BST) Properties**: Left subtree values < current node value < Right subtree values.
*   **In-order Traversal**: For a BST, an in-order traversal yields nodes in strictly ascending order.
*   **Recursion**: Breaking down the problem into smaller, self-similar subproblems.
*   **State Management in Recursion**: Using a global or class-level variable (`prev` in this case) to maintain state across recursive calls.

## Common Mistakes
*   **Ignoring the Global Minimum/Maximum Constraint**: Only checking `root.left.val < root.val` and `root.right.val > root.val` is insufficient. A node's value must be greater than *all* nodes in its left subtree and smaller than *all* nodes in its right subtree.
*   **Incorrectly Handling `prev`**: Not initializing `prev` correctly or not updating it at the right time can lead to wrong comparisons.
*   **Integer Overflow/Underflow**: If using `Integer.MIN_VALUE` and `Integer.MAX_VALUE` as initial bounds, be mindful of edge cases where node values might be exactly these limits. The provided solution avoids this by using `null` for `prev`.
*   **Not Handling Null Nodes**: Failing to return `true` for `null` nodes in the recursion.

## Complexity Analysis
*   **Time**: O(N) - reason: Each node in the tree is visited exactly once during the in-order traversal.
*   **Space**: O(H) - reason: Due to the recursion stack. In the worst case (a skewed tree), H can be N. In a balanced tree, H is log N.

## Commented Code
```java
class Solution {
    // Declare a class-level variable to store the value of the previously visited node.
    // This variable will maintain state across recursive calls.
    Integer prev = null;

    // The main method to validate if the given binary tree is a valid BST.
    public boolean isValidBST(TreeNode root) {
        // Base case: If the current node is null, it represents an empty subtree, which is a valid BST.
        if(root == null) {
            // Return true as an empty tree is valid.
            return true;
        }

        // Recursively call isValidBST on the left subtree.
        // This ensures that all nodes in the left subtree are processed and validated first.
        boolean leftSubtreeValid = isValidBST(root.left);

        // After visiting the left subtree, we check the current node against the 'prev' node.
        // 'prev' holds the value of the node visited immediately before the current node in an in-order traversal.
        // If 'prev' is not null (meaning we have visited at least one node before) AND
        // the previous node's value is greater than or equal to the current node's value,
        // then the BST property is violated.
        if(prev != null && prev >= root.val) {
            // Return false immediately as the BST is invalid.
            return false;
        }

        // Update 'prev' to the current node's value. This is crucial for the next node in the in-order traversal.
        prev = root.val;

        // Recursively call isValidBST on the right subtree.
        // This processes the right subtree after the current node has been validated.
        boolean rightSubtreeValid = isValidBST(root.right);

        // The overall BST is valid only if the left subtree is valid, the current node satisfies the BST property with the previous node,
        // AND the right subtree is also valid. The '&&' operator ensures all conditions must be true.
        // The 'leftSubtreeValid' is implicitly handled by the fact that if it returned false, the function would have already returned false.
        // So we just need to combine the result of the right subtree validation with the implicit success of the left and current node checks.
        return rightSubtreeValid; // This implicitly means leftSubtreeValid was true and current node check passed.
    }
}
```

## Interview Tips
*   **Explain the In-order Traversal Logic**: Clearly articulate why an in-order traversal is the key to solving this problem.
*   **Discuss the `prev` Variable**: Explain how the `prev` variable (or a similar mechanism like passing bounds) is used to maintain the strictly increasing order.
*   **Consider Edge Cases**: Mention how `null` nodes are handled and what happens with the very first node visited.
*   **Alternative Approach (Bounds)**: Briefly mention the alternative approach of passing `min` and `max` bounds down the recursion, which avoids the need for a class-level variable.

## Revision Checklist
- [ ] Understand BST properties.
- [ ] Recall in-order traversal.
- [ ] Implement recursive in-order traversal.
- [ ] Track the previous node's value.
- [ ] Handle null nodes correctly.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative solutions (e.g., using min/max bounds).

## Similar Problems
*   987. Vertical Order Traversal of a Binary Tree
*   102. Binary Tree Level Order Traversal
*   103. Binary Tree Zigzag Level Order Traversal
*   173. Binary Search Tree Iterator

## Tags
`Tree` `Depth-First Search` `Binary Search Tree` `Recursion`
