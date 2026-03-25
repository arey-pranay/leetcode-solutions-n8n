# Flatten Binary Tree To Linked List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Stack` `Tree` `Depth-First Search` `Binary Tree`  
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
  
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root==null) return;
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = prev;
        prev = root;
    }

      // ArrayList<TreeNode> arr = new ArrayList<>();
      // root.left = null;
        // TreeNode tail = root;        
        // // root.right = @12893712
        // // root.left = @1897393
        
        // for(int i = 1; i<arr.size(); i++){
        //     TreeNode curr =arr.get(i);
        //     curr.right = null;
        //     curr.left = null;
        //     tail.right = curr;
        //     tail.left = null;
        //     if(tail==root){
        //         tail = tail.right;
        //         root.right = tail;
        //     } else tail = tail.right;
        // }
}
```

---

---
## Quick Revision
The problem asks to transform a binary tree into a linked list in-place, where each node's right child points to the next node in the pre-order traversal, and the left child is always null.
This is achieved by a post-order traversal (right, left, root) that re-wires the pointers.

## Intuition
The goal is to rearrange the tree such that it resembles a linked list following a pre-order traversal. If we consider a node `root`, its left subtree should come *before* its right subtree in the flattened list. However, the provided solution uses a clever post-order traversal.

Imagine processing the tree from right to left, and bottom up. When we are at a node `root`, we have already processed its right subtree and its left subtree. Let's say `prev` is the node that was *just processed* before `root` in this reversed post-order traversal. This `prev` node will be the *next* node in the flattened list after `root`.

So, for the current `root`:
1. We recursively flatten the `right` subtree. After this call, `prev` will point to the head of the flattened right subtree.
2. We recursively flatten the `left` subtree. After this call, `prev` will point to the head of the flattened left subtree.
3. Now, `root` needs to be connected. Its `left` child must be null. Its `right` child should point to whatever `prev` is currently pointing to (which is the head of the flattened left subtree, or if the left subtree was null, then the head of the flattened right subtree).
4. Finally, we update `prev` to be the current `root`, so that the parent of `root` can connect to it.

The "aha moment" is realizing that a reversed post-order traversal (right, left, root) naturally allows us to keep track of the "next" node in the flattened list using a global or passed-by-reference `prev` pointer.

## Algorithm
1. Initialize a global or instance variable `prev` to `null`. This variable will keep track of the previously processed node in the reversed post-order traversal.
2. Define a recursive function `flatten(TreeNode root)`:
   a. **Base Case:** If `root` is `null`, return immediately.
   b. **Recurse on Right:** Call `flatten(root.right)`. This will flatten the right subtree and update `prev` to point to the head of the flattened right subtree.
   c. **Recurse on Left:** Call `flatten(root.left)`. This will flatten the left subtree and update `prev` to point to the head of the flattened left subtree.
   d. **Re-wire Current Node:**
      i. Set `root.left` to `null`.
      ii. Set `root.right` to `prev`. This connects the current node's right pointer to the head of the previously flattened subtree (which is now the next node in the pre-order sequence).
   e. **Update `prev`:** Set `prev` to `root`. This makes the current node the "previous" node for the next recursive call up the stack.
3. Call `flatten(root)` on the root of the tree to start the process.

## Concept to Remember
*   **Tree Traversal:** Understanding different traversal orders (pre-order, in-order, post-order) and how they can be adapted.
*   **Recursion:** Using recursion effectively to break down a problem into smaller, self-similar subproblems.
*   **In-place Modification:** Modifying the tree structure directly without using significant extra space for auxiliary data structures.
*   **Pointer Manipulation:** Carefully managing and updating node pointers (`left` and `right`) to achieve the desired structure.

## Common Mistakes
*   **Incorrect Traversal Order:** Trying to use a standard pre-order or in-order traversal directly, which doesn't easily lend itself to re-wiring pointers in this manner.
*   **Modifying Pointers Prematurely:** Changing `root.right` before processing the left subtree, leading to loss of the original right subtree.
*   **Not Handling Null Children:** Failing to correctly handle cases where a node has no left or right child.
*   **Losing Track of `prev`:** Incorrectly updating or managing the `prev` pointer, causing nodes to be linked in the wrong order.
*   **Using Extra Space Unnecessarily:** Opting for an O(N) space solution (like storing nodes in a list) when an in-place O(1) space solution is possible.

## Complexity Analysis
- Time: O(N) - reason: Each node in the tree is visited and processed exactly once.
- Space: O(H) - reason: Due to the recursion stack. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

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
    // 'prev' is a member variable to keep track of the previously processed node.
    // It will store the head of the flattened list segment processed so far.
    TreeNode prev = null;

    // The main function to flatten the binary tree.
    public void flatten(TreeNode root) {
        // Base case: if the current node is null, we've reached the end of a branch, so return.
        if(root==null) return;

        // Recursively flatten the right subtree first.
        // After this call, 'prev' will point to the head of the flattened right subtree.
        flatten(root.right);

        // Recursively flatten the left subtree.
        // After this call, 'prev' will point to the head of the flattened left subtree.
        // This is crucial because the left subtree should come before the right subtree in the flattened list.
        flatten(root.left);

        // Now, re-wire the current node 'root'.
        // Set the left child to null, as per the problem's requirement for a linked list.
        root.left = null;

        // Set the right child of the current node to 'prev'.
        // 'prev' currently holds the head of the flattened left subtree (or the flattened right subtree if left was null).
        // This effectively links the current node to the next node in the pre-order sequence.
        root.right = prev;

        // Update 'prev' to be the current node 'root'.
        // This makes 'root' the 'previous' node for the next recursive call higher up the stack,
        // allowing it to connect to 'root'.
        prev = root;
    }
}
```

## Interview Tips
*   **Explain the Traversal:** Clearly articulate why a reversed post-order traversal (right, left, root) is used and how the `prev` pointer helps.
*   **Visualize the Pointer Changes:** Draw a small tree on the whiteboard and trace the execution, showing how `root.left`, `root.right`, and `prev` change at each step.
*   **Discuss Space Optimization:** Highlight that the solution is in-place and discuss the space complexity of the recursive approach (O(H)).
*   **Consider Iterative Solutions:** Be prepared to discuss or even implement an iterative solution using a stack or Morris traversal if asked, as these can achieve O(1) space complexity.

## Revision Checklist
- [ ] Understand the problem: flatten tree to linked list in pre-order.
- [ ] Recognize the need for in-place modification.
- [ ] Grasp the reversed post-order traversal (right, left, root).
- [ ] Understand the role of the `prev` pointer.
- [ ] Trace the algorithm with a small example.
- [ ] Analyze time and space complexity.
- [ ] Be ready to explain the solution clearly.

## Similar Problems
*   Convert Binary Search Tree to Sorted Doubly Linked List
*   Binary Tree Inorder Traversal
*   Binary Tree Preorder Traversal
*   Binary Tree Postorder Traversal

## Tags
`Tree` `Depth-First Search` `Recursion` `Linked List`
