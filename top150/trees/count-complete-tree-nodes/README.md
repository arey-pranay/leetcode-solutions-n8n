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
Given a complete binary tree, count the number of nodes.
This is solved by checking if the tree is a perfect binary tree, otherwise recursively counting.

## Intuition
A complete binary tree has a special property: if its left subtree's height equals its right subtree's height, it's a perfect binary tree. The number of nodes in a perfect binary tree of height `h` is `2^h - 1`. If the heights differ, it's not perfect, and we can recursively count nodes in its left and right subtrees, adding 1 for the current node. This avoids traversing every single node in the best case.

## Algorithm
1. Define a helper function `leftHeight(TreeNode root)` that calculates the height of the tree by traversing only the left children.
2. Define a helper function `rightHeight(TreeNode root)` that calculates the height of the tree by traversing only the right children.
3. In the `countNodes(TreeNode root)` function:
    a. If `root` is null, return 0.
    b. Calculate the `lh` (left height) and `rh` (right height) of the current `root`.
    c. If `lh` equals `rh`, it means the subtree rooted at `root` is a perfect binary tree. Return `(int)Math.pow(2, lh) - 1`.
    d. If `lh` does not equal `rh`, it means the subtree is not perfect. Recursively call `countNodes` on the left child and the right child, and return `1 + countNodes(root.left) + countNodes(root.right)`.

## Concept to Remember
*   **Complete Binary Tree Properties**: Understanding that a complete binary tree is filled level by level, from left to right.
*   **Perfect Binary Tree**: Recognizing that a perfect binary tree has all its leaves at the same depth, and all internal nodes have two children.
*   **Tree Traversal**: Efficiently calculating heights by only traversing specific paths.
*   **Recursion**: Applying a divide-and-conquer strategy to break down the problem.

## Common Mistakes
*   **Naive Traversal**: Simply traversing every node using DFS or BFS, which doesn't leverage the complete binary tree property for optimization.
*   **Incorrect Height Calculation**: Miscalculating the height by not strictly following left or right paths, or off-by-one errors in height definition.
*   **Integer Overflow**: For very large trees, `Math.pow(2, lh)` could potentially overflow if `lh` is large, though this is less likely with typical LeetCode constraints.
*   **Base Case Handling**: Forgetting the `root == null` base case in the recursive function.

## Complexity Analysis
*   **Time**: O((log N)^2) - In the worst case, we might traverse down the height of the tree (log N) for height calculation, and this happens at each level of recursion. Since the height of a complete binary tree is log N, and at each step we might do two recursive calls, the overall complexity is roughly (log N) * (log N).
*   **Space**: O(log N) - Due to the recursion stack depth, which is proportional to the height of the tree.

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
    // This variable is not actually used in the provided solution,
    // but it's common to see it in recursive counting solutions.
    // In this specific solution, it's redundant.
    int count = 0 ;

    // The main function to count nodes in a complete binary tree.
    public int countNodes(TreeNode root) {
        // Base case: if the tree is empty, it has 0 nodes.
        if (root == null) {
            return 0;
        }

        // Calculate the height of the tree by only traversing the left children.
        int lh = leftHeight(root);
        // Calculate the height of the tree by only traversing the right children.
        int rh =  rightHeight(root);

        // If the left height equals the right height, it means the subtree rooted at 'root'
        // is a perfect binary tree. The number of nodes in a perfect binary tree of height 'h'
        // is 2^h - 1.
        if(lh == rh) {
            // Calculate 2^lh and subtract 1 to get the total nodes.
            return (int)Math.pow(2,lh) - 1;
        }

        // If the heights are not equal, the tree is not perfect.
        // We recursively count the nodes in the left subtree and the right subtree,
        // and add 1 for the current node.
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Helper function to calculate the height of the tree by traversing only left children.
    public int leftHeight(TreeNode root){
        int h = 0; // Initialize height counter.
        // Traverse down the left side of the tree until a null node is encountered.
        while(root!=null){
            root=root.left; // Move to the left child.
            h++; // Increment height for each level.
        }
        return h; // Return the calculated left height.
    }

    // Helper function to calculate the height of the tree by traversing only right children.
    public int rightHeight(TreeNode root){
        int h = 0; // Initialize height counter.
        // Traverse down the right side of the tree until a null node is encountered.
        while(root!=null){
            root=root.right; // Move to the right child.
            h++; // Increment height for each level.
        }
        return h; // Return the calculated right height.
    }
}
```

## Interview Tips
*   **Explain the "Complete Binary Tree" Property**: Clearly articulate what a complete binary tree is and why its structure is key to an optimized solution.
*   **Highlight the Optimization**: Emphasize how checking `leftHeight == rightHeight` avoids a full traversal in many cases, leading to a better time complexity than a simple DFS/BFS.
*   **Walk Through an Example**: Use a small complete binary tree to demonstrate how the `leftHeight` and `rightHeight` checks work and when the recursive calls are made.
*   **Discuss Edge Cases**: Mention the base case (`root == null`) and how the formula `2^h - 1` correctly handles perfect subtrees.

## Revision Checklist
- [ ] Understand the definition of a complete binary tree.
- [ ] Recognize the properties of a perfect binary tree.
- [ ] Implement `leftHeight` and `rightHeight` correctly.
- [ ] Apply the `2^h - 1` formula for perfect subtrees.
- [ ] Implement the recursive step for non-perfect subtrees.
- [ ] Handle the null root base case.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 102: Binary Tree Level Order Traversal
*   LeetCode 104: Maximum Depth of Binary Tree
*   LeetCode 110: Balanced Binary Tree
*   LeetCode 111: Minimum Depth of Binary Tree

## Tags
`Tree` `Depth-First Search` `Binary Search`

## My Notes
Mindblowing in O(logn2)
