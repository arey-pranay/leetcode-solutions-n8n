# Lowest Common Ancestor Of A Binary Search Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(H)  
**Space:** O(H)

---

## Solution (java)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else return root;
    }
}
```

---

---
## Quick Revision
Find the lowest node in a BST that has both `p` and `q` as descendants.
This is solved by traversing the BST, leveraging its ordered property.

## Intuition
The key insight for a Binary Search Tree (BST) is its ordered structure. For any given node, all values in its left subtree are smaller, and all values in its right subtree are larger. This property allows us to efficiently determine where the Lowest Common Ancestor (LCA) must lie relative to the current node. If both `p` and `q` are greater than the current node's value, their LCA must be in the right subtree. Conversely, if both are smaller, the LCA must be in the left subtree. The moment we find a node where `p` and `q` are on opposite sides (one smaller, one larger) or one of them *is* the current node, that node is the LCA.

## Algorithm
1. Start at the `root` of the BST.
2. Compare the values of `p` and `q` with the current `root`'s value.
3. If both `p.val` and `q.val` are greater than `root.val`, it means both nodes `p` and `q` are in the right subtree. Recursively call the function on `root.right`.
4. If both `p.val` and `q.val` are less than `root.val`, it means both nodes `p` and `q` are in the left subtree. Recursively call the function on `root.left`.
5. If neither of the above conditions is met, it implies that the current `root` is the LCA. This happens when:
    a. `p.val <= root.val <= q.val` (or `q.val <= root.val <= p.val`)
    b. `root` is equal to `p` or `q` (and the other node is in its subtree).
    In this case, return the current `root`.
6. If the `root` is `null` at any point, return `null`.

## Concept to Remember
*   Binary Search Tree (BST) properties: Left child < Parent < Right child.
*   Recursion: Breaking down a problem into smaller, self-similar subproblems.
*   Tree Traversal: Efficiently navigating through tree nodes.

## Common Mistakes
*   Not handling the `null` root case, leading to `NullPointerException`.
*   Incorrectly comparing `p` and `q` values, especially when one of them is the current node.
*   Forgetting that the BST property is crucial and trying a generic tree LCA approach.
*   Not considering the edge case where `p` or `q` is an ancestor of the other.

## Complexity Analysis
*   Time: O(H), where H is the height of the BST. In the worst case (a skewed tree), H can be N (number of nodes), making it O(N). In a balanced BST, H is log N, making it O(log N). This is because we traverse down the tree at most once.
*   Space: O(H) due to the recursion call stack. In the worst case (skewed tree), this is O(N). In a balanced BST, it's O(log N).

## Commented Code
```java
class Solution {
    // This method finds the lowest common ancestor (LCA) of two nodes p and q in a Binary Search Tree (BST).
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: If the current node is null, we cannot find an LCA, so return null.
        if(root == null) return null;

        // If both p and q have values greater than the current root's value,
        // it means both p and q must be in the right subtree.
        // So, we recursively search in the right subtree.
        if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);

        // If both p and q have values less than the current root's value,
        // it means both p and q must be in the left subtree.
        // So, we recursively search in the left subtree.
        else if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);

        // If neither of the above conditions is met, it means the current root is the LCA.
        // This occurs when:
        // 1. p is in the left subtree and q is in the right subtree (or vice versa).
        // 2. The current root is one of the nodes (p or q), and the other node is in its subtree.
        // In either case, the current root is the split point, hence the LCA.
        else return root;
    }
}
```

## Interview Tips
*   Clearly explain the BST property and how it guides your search.
*   Walk through an example, showing how the values of `p` and `q` dictate the traversal direction.
*   Discuss the time and space complexity, mentioning the difference between balanced and skewed trees.
*   Be prepared to implement an iterative solution as well, which can optimize space complexity to O(1).

## Revision Checklist
- [ ] Understand BST properties.
- [ ] Trace the recursive calls with example values.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (null root, p or q is LCA).
- [ ] Practice iterative solution.

## Similar Problems
*   Lowest Common Ancestor of a Binary Tree (LeetCode 236)
*   Find Bottom Left Tree Value (LeetCode 513)
*   Validate Binary Search Tree (LeetCode 98)

## Tags
`Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`
