# Recover Binary Search Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java
class Solution {
    // [2,3,1]
    TreeNode prev,a,b;
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        inorder(root);
        swap(a,b);
    }
    public void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        if(prev != null && root.val<prev.val){
            if(a==null) a=prev;//a=3
            b=root;//b=2
        }
        prev=root;
        inorder(root.right);
    }
    public void swap(TreeNode a, TreeNode b){
        int x = a.val;
        a.val = b.val;
        b.val = x;
    }
}
```

---

---
## Quick Revision
The problem asks to fix a Binary Search Tree (BST) where exactly two nodes have been swapped.
The solution involves an in-order traversal to find the misplaced nodes and then swapping their values.

## Intuition
A key property of a Binary Search Tree is that an in-order traversal visits nodes in ascending order. If two nodes are swapped, this ascending order will be violated at one or two points during the traversal. By keeping track of the previously visited node, we can detect these violations.

The first violation occurs when `prev.val > current.val`. The `prev` node is a candidate for the first swapped node (`a`). The `current` node is a candidate for the second swapped node (`b`).
If there's a second violation, it means the swapped nodes are not adjacent. In this case, the `current` node of the second violation is the actual second swapped node (`b`).

## Algorithm
1. Initialize three `TreeNode` pointers: `prev`, `first`, and `second`, all to `null`. `prev` will store the previously visited node during in-order traversal. `first` and `second` will store the two nodes that need to be swapped.
2. Perform an in-order traversal of the BST.
3. During the traversal, for each `current` node:
    a. If `prev` is not `null` and `current.val < prev.val`:
        i. If `first` is `null`, it means this is the first violation. Set `first = prev` and `second = current`.
        ii. If `first` is not `null`, it means this is the second violation. Update `second = current`.
    b. Update `prev = current`.
4. After the in-order traversal is complete, if `first` and `second` are not `null`, swap their values.

## Concept to Remember
*   **Binary Search Tree (BST) Properties**: In-order traversal of a BST yields elements in sorted order.
*   **In-order Traversal**: Recursive or iterative traversal of a binary tree visiting left subtree, then root, then right subtree.
*   **Identifying Violations**: Detecting deviations from the expected sorted order.

## Common Mistakes
*   **Incorrectly identifying the second swapped node**: Assuming the first violation always involves adjacent nodes. If the swapped nodes are not adjacent, the second node of the swap is the `current` node of the *second* violation.
*   **Not handling the case of adjacent swapped nodes**: The logic should correctly identify both nodes even if they are direct children.
*   **Modifying the tree structure instead of swapping values**: The problem statement usually implies swapping values, not restructuring the tree.
*   **Forgetting to reset `prev`**: `prev` must be updated to the current node after processing it.

## Complexity Analysis
- Time: O(N) - reason: We perform a single in-order traversal of the entire tree, visiting each node exactly once.
- Space: O(H) - reason: This is due to the recursion stack used for the in-order traversal, where H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. For a balanced tree, H is logN, leading to O(logN) space.

## Commented Code
```java
class Solution {
    // TreeNode pointer to store the previously visited node during in-order traversal.
    TreeNode prev;
    // TreeNode pointer to store the first node that is out of order.
    TreeNode first;
    // TreeNode pointer to store the second node that is out of order.
    TreeNode second;

    // Main function to recover the BST.
    public void recoverTree(TreeNode root) {
        // If the tree is empty, there's nothing to recover.
        if(root == null) return;
        // Perform in-order traversal to find the misplaced nodes.
        inorder(root);
        // After traversal, swap the values of the two identified misplaced nodes.
        swap(first, second);
    }

    // Recursive helper function for in-order traversal.
    public void inorder(TreeNode root){
        // Base case: if the current node is null, return.
        if(root == null) return;

        // Recursively traverse the left subtree.
        inorder(root.left);

        // Check for violation of BST property: current node's value is less than the previous node's value.
        if(prev != null && root.val < prev.val){
            // If 'first' is null, this is the first violation found.
            // The 'prev' node is the first misplaced node.
            if(first == null) {
                first = prev; // Store the first node of the pair (e.g., 3 in [2,3,1])
            }
            // In any violation, the current node is a candidate for the second misplaced node.
            // If it's the first violation, 'second' is set to 'root'.
            // If it's the second violation, 'second' is updated to the current 'root'.
            second = root; // Store the second node of the pair (e.g., 1 in [2,3,1] or 2 in [3,2,1])
        }

        // Update 'prev' to the current node for the next comparison.
        prev = root;

        // Recursively traverse the right subtree.
        inorder(root.right);
    }

    // Helper function to swap the values of two TreeNodes.
    public void swap(TreeNode node1, TreeNode node2){
        // If either node is null, we can't swap, so return.
        if (node1 == null || node2 == null) return;
        // Store the value of the first node in a temporary variable.
        int temp = node1.val;
        // Assign the value of the second node to the first node.
        node1.val = node2.val;
        // Assign the temporary value (original value of the first node) to the second node.
        node2.val = temp;
    }
}
```

## Interview Tips
*   **Explain the in-order traversal property**: Clearly articulate why in-order traversal is crucial for BSTs and how it helps detect violations.
*   **Walk through an example**: Use a small example like `[2,3,1]` or `[3,1,4,2]` to demonstrate how `prev`, `first`, and `second` are updated.
*   **Discuss edge cases**: Consider trees with only one or two nodes, or cases where the swapped nodes are adjacent.
*   **Mention space optimization**: Briefly discuss if an iterative in-order traversal (using a stack) could be used to achieve O(1) space if modifying the tree structure was allowed (though not applicable here as we only swap values).

## Revision Checklist
- [ ] Understand BST in-order traversal property.
- [ ] Implement in-order traversal recursively.
- [ ] Track `prev`, `first`, and `second` nodes correctly.
- [ ] Handle both adjacent and non-adjacent swapped nodes.
- [ ] Swap values of the identified nodes.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Validate Binary Search Tree
*   Convert BST to Sorted Doubly Linked List
*   Find K-th Smallest Element in a BST

## Tags
`Tree` `Depth-First Search` `Binary Search Tree`
