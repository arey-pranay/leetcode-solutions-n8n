# Validate Binary Search Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(n)  
**Space:** O(h)

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
Validate a binary search tree by checking if each node's value is within the range of its children's values.

We solve this problem using a recursive approach, traversing the tree and checking if each node's value meets the BST property.

## Intuition
The key insight here is that in a valid BST, all nodes to the left of a given node must have values less than it, and all nodes to the right must have values greater. This allows us to traverse the tree and check this property recursively.

## Algorithm

1. If the root is null, return true (an empty tree is always valid)
2. Check if the left subtree is valid
3. If `prev` (the previous node's value) is not null and its value is greater than or equal to the current node's value, return false (this violates the BST property)
4. Update `prev` with the current node's value
5. Check if the right subtree is valid
6. Return true only if both subtrees are valid

## Concept to Remember
* Binary Search Trees: a tree data structure where each node has a comparable value, and for any given node, all elements in its left child are less than it, and all elements in its right child are greater.
* Recursive algorithms: a programming technique where a function calls itself repeatedly until it reaches the base case.

## Common Mistakes
* Not handling the `prev` variable correctly (e.g., not initializing it or using it incorrectly)
* Failing to check if both subtrees are valid before returning true
* Overcomplicating the solution with unnecessary checks or variables

## Complexity Analysis
- Time: O(n) - where n is the number of nodes in the tree, since we visit each node once
- Space: O(h) - where h is the height of the tree, due to recursion stack space

## Commented Code
```java
class Solution {
    // Keep track of the previous node's value
    Integer prev = null;

    public boolean isValidBST(TreeNode root) {
        // Base case: an empty tree is valid
        if (root == null) return true;

        // Recursively check the left subtree
        boolean temp = isValidBST(root.left);

        // Check if this node violates the BST property
        if (prev != null && prev >= root.val) return false;

        // Update prev with the current node's value
        prev = root.val;

        // Recursively check the right subtree
        temp &= isValidBST(root.right);

        // Return true only if both subtrees are valid
        return temp;
    }
}
```

## Interview Tips
* Pay attention to edge cases (e.g., an empty tree)
* Use a clear and concise approach, avoiding unnecessary complexity
* Practice explaining your thought process and solution clearly

## Revision Checklist
- [ ] Understand the BST property and its implications on node values
- [ ] Implement recursive algorithm correctly
- [ ] Handle `prev` variable correctly
- [ ] Check for edge cases (e.g., empty tree)

## Similar Problems
* 110. Balanced Binary Tree (`isBalanced`)
* 538. Conversion for Conclusion (`convertBST`)

## Tags
`Array` `Hash Map` `Tree` `Recursive` `BST`
