# Lowest Common Ancestor Of A Binary Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Tree`  
**Time:** O(n)  
**Space:** O(h)

---

## Solution (java)

```java
class Solution {    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;
        TreeNode pfound = lowestCommonAncestor(root.left , p , q);
        TreeNode qfound = lowestCommonAncestor(root.right , p , q);
        if(pfound!=null && qfound!=null) return root;
        if(pfound == null) return qfound;
        return pfound;
    }
}
```

---

---

## Quick Revision
The problem asks to find the lowest common ancestor of two nodes in a binary tree. The solution uses a recursive approach to traverse the tree and find the LCA.

## Intuition
This problem works because we can use the properties of a binary search tree (BST) where each node's value is greater than its left child's value and less than its right child's value. By traversing down the tree, we can determine if a given node's value is in the left or right subtree, effectively searching for both nodes at the same time.

## Algorithm
1. Base case: If the current node is null, return null.
2. Check if the current node is either of the two target nodes (p or q). If so, return the current node as it is one of the LCA candidates.
3. Recursively call the function on both left and right child subtrees for both p and q.
4. In the recursive calls, check if p is found in the left subtree (`pfound`) and q is found in the right subtree (`qfound`).
5. If both `pfound` and `qfound` are not null, it means both nodes are in different subtrees, so return the current node as the LCA.
6. If only one of them is not null, return that as the other node must be a descendant.

## Concept to Remember
* Recursive function calls to traverse the tree
* Properties of BSTs (each node's value is greater than its left child and less than its right child)
* Using two recursive calls to check for both nodes

## Common Mistakes
* Failing to properly handle null cases
* Not checking if a node is either of the target nodes in each recursive call
* Returning the wrong LCA when both nodes are in the same subtree

## Complexity Analysis
- Time: O(n) where n is the number of nodes in the tree, as we traverse all nodes at most once.
- Space: O(h) where h is the height of the tree due to recursive function call stack.

## Commented Code
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: If the current node is null, return null
        if (root == null) return null;

        // Check if the current node is either of the two target nodes (p or q)
        if (root == p || root == q) return root;

        // Recursively call the function on both left and right child subtrees for both p and q
        TreeNode pfound = lowestCommonAncestor(root.left, p, q);
        TreeNode qfound = lowestCommonAncestor(root.right, p, q);

        // If both pfound and qfound are not null, it means both nodes are in different subtrees, so return the current node as the LCA
        if (pfound != null && qfound != null) return root;

        // If only one of them is not null, return that as the other node must be a descendant
        if (pfound == null) return qfound;
        return pfound;
    }
}
```

## Interview Tips
* Make sure to handle all possible cases, including null nodes.
* Use recursive function calls to traverse the tree effectively.
* Practice writing clear and concise code with proper comments.

## Revision Checklist
- [ ] Understand properties of BSTs and how they apply to this problem
- [ ] Implement recursive function calls correctly
- [ ] Properly handle all possible cases, including null nodes

## Similar Problems
* Lowest Common Ancestor in a Binary Tree III (LeetCode 1309)
* Find the Closest Leaf Node in a Binary Tree (LeetCode 1733)

## Tags
`Array`, `Hash Map`, `Recursion`, `BST`
