# Lowest Common Ancestor Of A Binary Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Tree`  
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base cases
        if(root == null) return null;
        if(root.val == p.val || root.val == q.val) return root; 
        
        TreeNode foundL =lowestCommonAncestor(root.left,p,q); // p ya q left me mila ya nahi
        TreeNode foundR =lowestCommonAncestor(root.right,p,q); // p ya q right me mila ya nahi
        if(foundL!=null && foundR!=null) return root; // dono taraf 1-1 mile, mtlb ye LCA hai
        
        // ab agr koi ek null hai, mtlb dono nodes same side pe the
        if(foundL != null) return foundL; // agr left me node mila, to jo LCA tha wo hi return hua
        return foundR; // since p and q always exist, then surely right me se LCA return hua tha 

    }
}


```

---

---
## Quick Revision
Finds the deepest node that is an ancestor of both `p` and `q` in a binary tree.
Solved using a recursive approach that checks for `p` and `q` in subtrees.

## Intuition
The "aha moment" comes from realizing that if we find `p` in one subtree and `q` in another subtree of a given node, then that node *must* be the Lowest Common Ancestor (LCA). If both `p` and `q` are found in the *same* subtree, then the LCA must be deeper within that subtree. The base cases are crucial: if we hit a null node, we haven't found anything; if we find `p` or `q`, that node itself could be the LCA or an ancestor of it.

## Algorithm
1.  **Base Cases:**
    *   If the current `root` is `null`, return `null` (nothing found here).
    *   If the current `root`'s value matches `p`'s value or `q`'s value, return the `root` itself. This node is either `p`, `q`, or an ancestor of one of them.
2.  **Recursive Calls:**
    *   Recursively call `lowestCommonAncestor` on the `root.left` subtree to find `p` or `q`. Store the result in `foundL`.
    *   Recursively call `lowestCommonAncestor` on the `root.right` subtree to find `p` or `q`. Store the result in `foundR`.
3.  **LCA Determination:**
    *   If both `foundL` and `foundR` are not `null`, it means `p` was found in one subtree and `q` in the other. Therefore, the current `root` is the LCA. Return `root`.
4.  **Propagate Results:**
    *   If only `foundL` is not `null`, it means both `p` and `q` (or the LCA if they were in the same subtree) were found in the left subtree. Return `foundL`.
    *   If only `foundR` is not `null`, it means both `p` and `q` (or the LCA) were found in the right subtree. Return `foundR`.
    *   (Implicitly, if both are null, null is returned, which is handled by the base case or propagation).

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Tree Traversal (Post-order like):** The logic processes children's results before deciding on the parent.
*   **Binary Tree Properties:** Understanding parent-child relationships and subtree structures.

## Common Mistakes
*   **Incorrect Base Cases:** Not handling `null` nodes or not returning the node itself when `p` or `q` is found.
*   **Misinterpreting Recursive Return Values:** Not understanding that a non-null return from a recursive call signifies finding `p`, `q`, or their LCA in that subtree.
*   **Not Handling the Case Where One Node is an Ancestor of the Other:** The algorithm correctly handles this by returning the ancestor node when it's found.
*   **Assuming `p` and `q` are always in different subtrees:** The logic must account for both being in the same subtree.

## Complexity Analysis
- Time: O(N) - reason: In the worst case, we visit every node in the tree once.
- Space: O(H) - reason: Due to the recursion stack depth, where H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; } // Constructor for a TreeNode
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case 1: If the current node is null, we've reached the end of a branch without finding p or q.
        if(root == null) return null;
        
        // Base case 2: If the current node's value is equal to p's value OR q's value,
        // then this node is either p, q, or an ancestor of one of them. It could be the LCA.
        if(root.val == p.val || root.val == q.val) return root; 
        
        // Recursively search for p and q in the left subtree.
        // 'foundL' will be non-null if p or q (or their LCA) is found in the left subtree.
        TreeNode foundL = lowestCommonAncestor(root.left,p,q); 
        
        // Recursively search for p and q in the right subtree.
        // 'foundR' will be non-null if p or q (or their LCA) is found in the right subtree.
        TreeNode foundR = lowestCommonAncestor(root.right,p,q); 
        
        // If both 'foundL' and 'foundR' are non-null, it means p was found in one subtree
        // and q was found in the other. Therefore, the current 'root' is the LCA.
        if(foundL!=null && foundR!=null) return root; 
        
        // If only 'foundL' is non-null, it means both p and q (or their LCA) were found in the left subtree.
        // So, we propagate the result from the left subtree upwards.
        if(foundL != null) return foundL; 
        
        // If 'foundL' is null, then 'foundR' must be non-null (since p and q are guaranteed to exist).
        // This means both p and q (or their LCA) were found in the right subtree.
        // Propagate the result from the right subtree upwards.
        return foundR; 
    }
}
```

## Interview Tips
*   **Explain the Recursive Logic Clearly:** Walk through how the function returns `p`, `q`, or `null` from sub-calls and how the parent node uses these results.
*   **Handle Edge Cases:** Explicitly mention the base cases (`root == null`, `root == p` or `root == q`) and why they are important.
*   **Trace an Example:** Use a small binary tree and trace the execution for a specific pair of `p` and `q` to demonstrate understanding.
*   **Discuss Complexity:** Be prepared to explain the time and space complexity and the reasoning behind it.

## Revision Checklist
- [ ] Understand the definition of LCA.
- [ ] Identify base cases for recursion.
- [ ] Implement recursive calls for left and right subtrees.
- [ ] Logic for combining results from subtrees.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
Lowest Common Ancestor of a Binary Search Tree
Lowest Common Ancestor of a Binary Tree II
Lowest Common Ancestor of a Binary Tree III
Lowest Common Ancestor of a Binary Tree IV

## Tags
`Tree` `Depth-First Search` `Binary Tree`
