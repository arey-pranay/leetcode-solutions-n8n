# Recover Binary Search Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(n)  
**Space:** O(h)

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
Recover the inorder traversal of a corrupted Binary Search Tree (BST) to its correct form.
We can solve this problem by performing an inorder traversal of the BST and swapping the two nodes with values out of order.

## Intuition
The key insight is that in a valid BST, for any node, all elements in its left subtree are less than the node, and all elements in its right subtree are greater than the node. We can leverage this property to detect the two nodes that need to be swapped.

## Algorithm
1. Perform an inorder traversal of the BST, keeping track of the previous node (`prev`) and the two nodes with values out of order (`a` and `b`).
2. If the current node's value is less than the previous node's value, it means that the previous node's value should be greater than the current node's value.
3. If `a` is null, set `a` to the previous node, and set `b` to the current node.
4. After the traversal is complete, swap the values of `a` and `b`.

## Concept to Remember
* Binary Search Tree properties:
	+ For any node, all elements in its left subtree are less than the node.
	+ For any node, all elements in its right subtree are greater than the node.
* Inorder traversal: visits nodes in ascending order.

## Common Mistakes
* Failing to keep track of the previous node (`prev`) correctly.
* Not setting `a` to the correct node when swapping.
* Not swapping the values of `a` and `b` after the traversal is complete.

## Complexity Analysis
- Time: O(n) - where n is the number of nodes in the tree, as we visit each node once.
- Space: O(h) - where h is the height of the tree, as we need to keep track of the current node and its parent.

## Commented Code
```java
class Solution {
    // [2,3,1]
    TreeNode prev, a, b;

    public void recoverTree(TreeNode root) {
        if (root == null) return;
        inorder(root);
        // Swap the values of a and b
        swap(a, b);
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (a == null) a = prev; // a = 3
            b = root; // b = 2
        }
        prev = root;
        inorder(root.right);
    }

    public void swap(TreeNode a, TreeNode b) {
        int x = a.val;
        a.val = b.val;
        b.val = x;
    }
}
```

## Interview Tips
* Make sure to understand the properties of a Binary Search Tree.
* Practice implementing inorder traversal.
* Be careful when swapping the values of `a` and `b`.

## Revision Checklist
- [ ] Understand the properties of a Binary Search Tree.
- [ ] Implement inorder traversal correctly.
- [ ] Swap the values of `a` and `b` after the traversal is complete.

## Similar Problems
* `Validate Binary Search Tree` (LeetCode 98)
* `Binary Search Tree Traversal` (LeetCode 94, 145)

## Tags
`Array` `Hash Map` `Binary Search Tree` `Inorder Traversal` `Tree Traversal`
