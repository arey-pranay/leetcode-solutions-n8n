# Kth Smallest Element In A Bst

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java
class Solution {
    int rem;
    int ans=-1;
    public int kthSmallest(TreeNode root, int k) {
        rem = k;
        inorder(root);
        return ans;
    }
    public void inorder(TreeNode root){
      
        if(ans!=-1 || root==null) return;
      
        inorder(root.left);
      
        rem--;
        if(rem==0) ans=root.val;

        inorder(root.right);
        
    }
}
```

---

---

## Quick Revision
Find the kth smallest element in a Binary Search Tree (BST).
Solve this by using an in-order traversal of the BST and returning the value at the kth position.

## Intuition
The key insight here is that an in-order traversal of a BST visits nodes in ascending order. By stopping the traversal when we reach the kth node, we can efficiently find the kth smallest element without needing to traverse the entire tree.

## Algorithm

1. Initialize a variable `rem` to store the number of elements remaining to visit (i.e., k).
2. Define a recursive function `inorder(root)` that performs an in-order traversal of the BST.
3. In `inorder(root)`, if `ans` is not -1 or `root` is null, return immediately.
4. Recursively call `inorder(root.left)` to visit the left subtree.
5. Decrement `rem` by 1 and check if it equals 0. If so, set `ans` to the current node's value (i.e., `root.val`).
6. Recursively call `inorder(root.right)` to visit the right subtree.

## Concept to Remember

* In-order traversal of a BST visits nodes in ascending order.
* Recursive functions can be used to traverse tree data structures.
* Decrementing a counter variable is an efficient way to track the current position during traversal.

## Common Mistakes
* Failing to handle the base case (i.e., when `root` is null) properly.
* Not decrementing `rem` correctly, leading to incorrect results.
* Using recursion without considering potential stack overflow issues.

## Complexity Analysis

- Time: O(N) - We visit each node in the tree once during traversal.
- Space: O(H) - Recursive function calls create a stack of size up to the height of the tree.

## Commented Code
```java
class Solution {
    int rem; // remaining elements to visit (k)
    int ans = -1; // store the kth smallest element

    public int kthSmallest(TreeNode root, int k) {
        rem = k;
        inorder(root);
        return ans;
    }

    public void inorder(TreeNode root){
        // base case: don't traverse if ans is found or node is null
        if(ans != -1 || root == null)
            return;

        // recursively visit left subtree
        inorder(root.left);

        // decrement rem and check for kth smallest element
        rem--;
        if(rem == 0) {
            ans = root.val; // store current node's value
        }

        // recursively visit right subtree
        inorder(root.right);
    }
}
```

## Interview Tips

* Practice in-order traversal on small trees to develop intuition.
* Pay attention to edge cases (e.g., when k is larger than the tree size).
* Consider optimizing the solution for large inputs (e.g., using iterative approaches).

## Revision Checklist
- [ ] Understand in-order traversal of BSTs
- [ ] Implement recursive function correctly
- [ ] Handle base case and decrementing rem properly

## Similar Problems
* LeetCode: #230 (Kth Smallest Element in a Sorted List)
* LeetCode: #98 (Validate Binary Search Tree)

## Tags
`Array`, `Hash Map`, `Tree`, `BST`, `Traversal`
