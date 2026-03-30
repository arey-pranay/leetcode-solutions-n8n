# Minimum Absolute Difference In Bst

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Breadth-First Search` `Binary Search Tree` `Binary Tree`  
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
    int ans = Integer.MAX_VALUE;
    int prev =Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }
    public void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        ans = Math.min(Math.abs(root.val-prev),ans);
        prev = root.val;
        inorder(root.right);
        return;
    }
}
```

---

---
## Quick Revision
Given a Binary Search Tree (BST), find the minimum difference between the values of any two different nodes.
This is solved by performing an in-order traversal and tracking the minimum difference between consecutive nodes.

## Intuition
The key property of a Binary Search Tree (BST) is that an in-order traversal visits the nodes in ascending order. This means that if we traverse the BST in-order, any two adjacent nodes in the traversal sequence will have the smallest possible difference between them compared to any other pair of nodes that are not adjacent in the sorted sequence. Therefore, we only need to compare each node's value with the value of the node visited immediately before it in the in-order traversal.

## Algorithm
1. Initialize a variable `minDiff` to a very large value (e.g., `Integer.MAX_VALUE`) to store the minimum absolute difference found so far.
2. Initialize a variable `prevVal` to a very large value (e.g., `Integer.MAX_VALUE`) to store the value of the previously visited node during the in-order traversal.
3. Define a recursive helper function, `inorderTraversal(TreeNode node)`, that performs an in-order traversal:
    a. If the current `node` is null, return.
    b. Recursively call `inorderTraversal` on the left child: `inorderTraversal(node.left)`.
    c. After visiting the left subtree, calculate the absolute difference between the current node's value (`node.val`) and `prevVal`. Update `minDiff` if this difference is smaller: `minDiff = Math.min(minDiff, Math.abs(node.val - prevVal))`.
    d. Update `prevVal` to the current node's value: `prevVal = node.val`.
    e. Recursively call `inorderTraversal` on the right child: `inorderTraversal(node.right)`.
4. Call the `inorderTraversal` function starting from the `root` of the BST.
5. Return the final `minDiff`.

## Concept to Remember
*   **Binary Search Tree (BST) Properties**: In-order traversal of a BST yields elements in sorted order.
*   **In-order Traversal**: A tree traversal algorithm that visits nodes in the order: left subtree, root, right subtree.
*   **Absolute Difference**: The non-negative difference between two numbers.
*   **State Management in Recursion**: Using global or member variables to maintain state (like `prevVal` and `minDiff`) across recursive calls.

## Common Mistakes
*   **Not using in-order traversal**: Attempting to find the minimum difference without leveraging the sorted property of BSTs, leading to inefficient comparisons.
*   **Incorrectly updating `prevVal`**: Forgetting to update `prevVal` after processing a node, or updating it at the wrong time (e.g., before calculating the difference).
*   **Handling the first node**: The initial `prevVal` should be a sentinel value that doesn't affect the first difference calculation.
*   **Integer Overflow**: While less likely with standard integer types for typical LeetCode constraints, it's good practice to be mindful of potential overflows with very large differences.

## Complexity Analysis
*   **Time**: O(N) - reason: We visit each node in the BST exactly once during the in-order traversal.
*   **Space**: O(H) - reason: This is the space used by the recursion call stack. In the worst case (a skewed tree), H can be N. In a balanced tree, H is log N.

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
    // Initialize `ans` to store the minimum absolute difference found so far.
    // Start with the maximum possible integer value to ensure any difference will be smaller.
    int ans = Integer.MAX_VALUE;
    // Initialize `prev` to store the value of the previously visited node in the in-order traversal.
    // Start with a large value so the first difference calculation is valid.
    int prev = Integer.MAX_VALUE;

    // The main function to get the minimum absolute difference.
    public int getMinimumDifference(TreeNode root) {
        // Start the in-order traversal from the root of the BST.
        inorder(root);
        // Return the minimum difference found.
        return ans;
    }

    // Recursive helper function to perform in-order traversal.
    public void inorder(TreeNode root){
        // Base case: if the current node is null, stop the recursion for this branch.
        if(root==null) return;

        // 1. Traverse the left subtree.
        inorder(root.left);

        // 2. Process the current node:
        // Calculate the absolute difference between the current node's value and the previous node's value.
        // Update `ans` if this new difference is smaller than the current minimum difference.
        ans = Math.min(Math.abs(root.val - prev), ans);
        // Update `prev` to the current node's value, as this will be the "previous" node for the next element in the in-order sequence.
        prev = root.val;

        // 3. Traverse the right subtree.
        inorder(root.right);

        // The return statement here is technically redundant as the method is void,
        // but it explicitly signifies the end of the function's execution for this call.
        return;
    }
}
```

## Interview Tips
*   **Explain BST Property**: Clearly articulate why an in-order traversal is crucial for this problem due to BST properties.
*   **Trace Example**: Walk through a small BST example (e.g., a tree with 3-5 nodes) to demonstrate how `prevVal` and `minDiff` are updated.
*   **Edge Cases**: Discuss how the code handles an empty tree (root is null) or a tree with only one node.
*   **Variable Scope**: Be prepared to explain why `ans` and `prev` are member variables (or global) rather than local variables passed through recursion.

## Revision Checklist
- [ ] Understand BST in-order traversal property.
- [ ] Implement recursive in-order traversal.
- [ ] Correctly track the previous node's value.
- [ ] Update the minimum difference at each step.
- [ ] Handle null nodes and initial states.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 98: Validate Binary Search Tree
*   LeetCode 501: Find Mode in Binary Search Tree
*   LeetCode 230: Kth Smallest Element in a BST

## Tags
`Tree` `Depth-First Search` `Binary Search Tree` `Recursion`

## My Notes
Constant space, O(N) time
