# Count Complete Tree Nodes

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Binary Search` `Bit Manipulation` `Tree` `Binary Tree`  
**Time:** O((log N)  
**Space:** O(log N)

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
    int count = 0 ;
    public int countNodes(TreeNode root) {
        int lh = leftHeight(root);
        int rh =  rightHeight(root);
        if(lh == rh) return (int)Math.pow(2,lh) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    public int leftHeight(TreeNode root){
        int h = 0;
        while(root!=null){
            root=root.left;
            h++;
        }
        return h;
    }
    public int rightHeight(TreeNode root){
        int h = 0;
        while(root!=null){
            root=root.right;
            h++;
        }
        return h;
    }
}
```

---

---
## Quick Revision
This problem asks to count the number of nodes in a complete binary tree.
The solution leverages the properties of complete binary trees to optimize counting.

## Intuition
A complete binary tree has a special structure: all levels are filled except possibly the last one, which is filled from left to right. If the height of the leftmost path equals the height of the rightmost path from a node, then that subtree is a perfect binary tree, and its node count can be calculated directly as 2^height - 1. Otherwise, it's not a perfect binary tree, and we recursively count nodes in its left and right subtrees.

## Algorithm
1. Define a helper function `leftHeight(TreeNode root)` that calculates the height of the tree by traversing only the left children.
2. Define a helper function `rightHeight(TreeNode root)` that calculates the height of the tree by traversing only the right children.
3. In the `countNodes(TreeNode root)` function:
    a. If the `root` is null, return 0.
    b. Calculate the `lh` (left height) and `rh` (right height) of the current `root`.
    c. If `lh` equals `rh`, it means the subtree rooted at `root` is a perfect binary tree. Return `(int)Math.pow(2, lh) - 1`.
    d. If `lh` does not equal `rh`, it means the subtree is not perfect. Recursively call `countNodes` on the left child and the right child, and return `1 + countNodes(root.left) + countNodes(root.right)`.

## Concept to Remember
*   **Complete Binary Tree Properties**: Understanding that a complete binary tree is filled level by level, from left to right, is crucial.
*   **Perfect Binary Tree**: Recognizing that if the left and right heights are equal, the subtree is perfect, allowing for O(1) calculation of its node count.
*   **Recursion**: The problem is naturally solved using recursion, breaking down the tree into smaller subproblems.
*   **Tree Traversal**: Efficiently calculating heights using specific traversals (leftmost and rightmost paths).

## Common Mistakes
*   **Naive Traversal**: Simply traversing all nodes using DFS or BFS without utilizing the complete binary tree property, leading to O(N) time complexity for every subtree check.
*   **Incorrect Height Calculation**: Miscalculating the height by not strictly following the leftmost or rightmost path.
*   **Off-by-One Errors**: Errors in the formula for perfect binary tree node count (e.g., using 2^h instead of 2^h - 1).
*   **Handling Null Root**: Not properly handling the base case where the root is null.

## Complexity Analysis
*   Time: O((log N)^2) - The `leftHeight` and `rightHeight` functions take O(log N) time. In the worst case, we might call these functions at each level of recursion. Since the recursion depth is O(log N), the total time complexity becomes O(log N * log N).
*   Space: O(log N) - This is due to the recursion stack depth, which in a complete binary tree is proportional to its height (log N).

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
    // The main function to count nodes in a complete binary tree.
    public int countNodes(TreeNode root) {
        // If the root is null, the tree is empty, so return 0 nodes.
        if (root == null) {
            return 0;
        }

        // Calculate the height of the leftmost path from the current root.
        int lh = leftHeight(root);
        // Calculate the height of the rightmost path from the current root.
        int rh = rightHeight(root);

        // If the left height equals the right height, it means the subtree rooted at 'root' is a perfect binary tree.
        // The number of nodes in a perfect binary tree of height 'h' is 2^h - 1.
        if (lh == rh) {
            // Use Math.pow to calculate 2^lh and cast to int. Subtract 1 for the total count.
            return (int) Math.pow(2, lh) - 1;
        }

        // If the left height is not equal to the right height, the subtree is not perfect.
        // We recursively count the nodes in the left subtree and the right subtree.
        // Add 1 for the current root node.
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Helper function to calculate the height of the tree by traversing only the left children.
    public int leftHeight(TreeNode root) {
        // Initialize height to 0.
        int h = 0;
        // Traverse down the left side of the tree until a null node is encountered.
        while (root != null) {
            // Move to the left child.
            root = root.left;
            // Increment height for each level traversed.
            h++;
        }
        // Return the calculated left height.
        return h;
    }

    // Helper function to calculate the height of the tree by traversing only the right children.
    public int rightHeight(TreeNode root) {
        // Initialize height to 0.
        int h = 0;
        // Traverse down the right side of the tree until a null node is encountered.
        while (root != null) {
            // Move to the right child.
            root = root.right;
            // Increment height for each level traversed.
            h++;
        }
        // Return the calculated right height.
        return h;
    }
}
```

## Interview Tips
*   **Explain the "Complete" Property**: Clearly articulate what a complete binary tree is and why its structure is exploitable.
*   **Justify the Optimization**: Explain why checking `lh == rh` is the key optimization and how it leads to calculating nodes in O(1) for perfect subtrees.
*   **Discuss Time Complexity**: Be prepared to explain why the complexity is O((log N)^2) and not O(N) or O(log N).
*   **Edge Cases**: Mention handling the `root == null` case explicitly.

## Revision Checklist
- [ ] Understand the definition of a complete binary tree.
- [ ] Recognize the property of perfect binary trees within a complete tree.
- [ ] Implement height calculation for leftmost and rightmost paths.
- [ ] Apply the recursive formula `1 + countNodes(left) + countNodes(right)` when subtrees are not perfect.
- [ ] Implement the base case for an empty tree (`root == null`).
- [ ] Analyze time and space complexity correctly.

## Similar Problems
*   LeetCode 102: Binary Tree Level Order Traversal
*   LeetCode 104: Maximum Depth of Binary Tree
*   LeetCode 110: Balanced Binary Tree
*   LeetCode 257: Binary Tree Paths

## Tags
`Tree` `Depth-First Search` `Binary Search`

## My Notes
Mindblowing in O(logn2)
