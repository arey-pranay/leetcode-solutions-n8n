# Kth Smallest Element In A Bst

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`  
**Time:** O(H + k)  
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
    int count = 0;
    int ans = -1;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root,k);
        return ans;
    }
    public void inorder(TreeNode root, int k){
        if(root==null) return;
        inorder(root.left,k);
        count++;

        if(count == k){
            ans = root.val;
            return;
        }
        inorder(root.right,k);
    }
}
```

---

---
## Quick Revision
Find the k-th smallest element in a Binary Search Tree (BST).
Solve by performing an in-order traversal and stopping when the k-th element is found.

## Intuition
The in-order traversal of a Binary Search Tree visits nodes in ascending order. This means the first node visited is the smallest, the second is the second smallest, and so on. Therefore, if we perform an in-order traversal and keep a count of the nodes visited, the k-th node we visit will be the k-th smallest element.

## Algorithm
1. Initialize a counter `count` to 0 and a variable `ans` to store the result (e.g., -1).
2. Define a recursive helper function `inorder(TreeNode node, int k)`:
    a. Base case: If `node` is null, return.
    b. Recursively call `inorder` on the left child: `inorder(node.left, k)`.
    c. Increment the `count`.
    d. Check if `count` is equal to `k`. If it is, set `ans` to `node.val` and return (to stop further traversal).
    e. Recursively call `inorder` on the right child: `inorder(node.right, k)`.
3. In the `kthSmallest` function, call the `inorder` helper function with the `root` and `k`.
4. Return the `ans`.

## Concept to Remember
*   **Binary Search Tree (BST) Properties:** Left subtree contains smaller values, right subtree contains larger values.
*   **In-order Traversal:** Left -> Root -> Right. For a BST, this yields elements in sorted order.
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **State Management in Recursion:** Using global/member variables (`count`, `ans`) to maintain state across recursive calls.

## Common Mistakes
*   **Incorrect Traversal Order:** Using pre-order or post-order traversal instead of in-order, which won't yield sorted elements.
*   **Not Stopping Early:** Continuing the traversal even after finding the k-th element, leading to unnecessary computation.
*   **Handling Edge Cases:** Not properly handling null nodes or an empty tree.
*   **State Resetting:** If the `kthSmallest` function is called multiple times on the same `Solution` object without resetting `count` and `ans`, it can lead to incorrect results. (Though in LeetCode's typical execution, a new `Solution` object is created per test case).

## Complexity Analysis
*   **Time:** O(H + k) in the best/average case, where H is the height of the tree. In the worst case (skewed tree), it's O(N), where N is the number of nodes. This is because we might traverse up to k nodes in an in-order fashion.
*   **Space:** O(H) in the best/average case due to the recursion stack depth (height of the tree). In the worst case (skewed tree), it's O(N).

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
    // Member variable to keep track of the number of nodes visited so far during in-order traversal.
    int count = 0;
    // Member variable to store the value of the k-th smallest element. Initialized to -1 as a placeholder.
    int ans = -1;

    // Public method to find the k-th smallest element in the BST.
    public int kthSmallest(TreeNode root, int k) {
        // Initiate the in-order traversal starting from the root node.
        inorder(root, k);
        // Return the found k-th smallest element.
        return ans;
    }

    // Recursive helper method to perform in-order traversal.
    public void inorder(TreeNode root, int k){
        // Base case: if the current node is null, stop this branch of recursion.
        if(root==null) return;

        // 1. Traverse the left subtree. This ensures we visit smaller elements first.
        inorder(root.left, k);

        // 2. Visit the current node. Increment the count of visited nodes.
        count++;

        // 3. Check if the current node is the k-th smallest element.
        if(count == k){
            // If it is, store its value in 'ans'.
            ans = root.val;
            // Return to stop further traversal, as we've found our answer.
            return;
        }

        // 4. Traverse the right subtree. This visits larger elements after the current node.
        inorder(root.right, k);
    }
}
```

## Interview Tips
*   **Explain In-order Traversal:** Clearly articulate why in-order traversal is crucial for BSTs and how it yields sorted elements.
*   **Discuss State Management:** Explain how `count` and `ans` are used to maintain state across recursive calls and why they are necessary.
*   **Consider Iterative Approach:** Be prepared to discuss or implement an iterative in-order traversal using a stack, which can be more space-efficient in some scenarios (though the recursive approach is often simpler to explain).
*   **Handle Constraints:** Ask about potential constraints on `k` (e.g., `k` is always valid, `k` can be larger than the number of nodes) and how to handle them.

## Revision Checklist
- [ ] Understand BST properties.
- [ ] Recall in-order traversal logic.
- [ ] Implement recursive in-order traversal.
- [ ] Implement state tracking (`count`, `ans`).
- [ ] Handle null nodes.
- [ ] Analyze time and space complexity.
- [ ] Consider iterative approach (optional but good).

## Similar Problems
*   Lowest Common Ancestor of a Binary Search Tree
*   Validate Binary Search Tree
*   Inorder Traversal (general tree traversal)
*   Find Mode in Binary Search Tree

## Tags
`Tree` `Depth-First Search` `Binary Search Tree` `Binary Tree`
