# Binary Tree Cameras

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Tree` `Depth-First Search` `Binary Tree` `DP on Trees`  
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
    // 1 = covered, -1 = notCovered, 0 = camera
    int cams = 0;
    public int minCameraCover(TreeNode root) {
        boolean rootCovered = func(root) != -1; 
        if(!rootCovered) cams++;// since we are coming bottom-up, there is no parent to root, so root coverage must be checked explicitly by us.
        return cams;
    }
    public int func(TreeNode root){
        if(root==null) return 1;
        int left = func(root.left);
        int right = func(root.right);
        if(left == -1 || right == -1){cams++; return 0;} // agr koi bhi child uncovered hai, we need a camera
        if(left == 0 || right == 0) return 1; // agr koi bhi child covered hai, we are covered
        return -1; // else we are not covered, parent will handle it
    }
    
}
```

---

---
## Quick Revision
Given a binary tree, find the minimum number of cameras needed to monitor all nodes.
Solve it bottom-up using a greedy post-order traversal with a 3-state return schema.

## Intuition
The "aha moment" for this problem is realizing that we should place cameras as low as possible in the tree—specifically on the parents of leaves—because a camera placed higher up wastes its coverage on nodes that could have been monitored more efficiently from below. By visiting nodes post-order (children before parents), we can make optimal greedy decisions based on three states: whether a node is covered (-1 for not covered, 0 for having a camera, 1 for covered without a camera). Leaves should tell their parents they are uncovered (-1) so the parent is forced to place a camera.

## Algorithm
1. Initialize a global counter `cams` to 0 to track the total number of cameras placed.
2. Define a helper function `func(TreeNode root)` that returns an integer state for the current node: `0` (has camera), `1` (covered), or `-1` (not covered).
3. Base case: If the node is `null`, return `1` (considered covered so it doesn't force a parent to place a useless camera).
4. Recursively call `func` on the left and right children to get their states.
5. If either the left or right child returns `-1` (uncovered), we must place a camera at the current node: increment `cams` and return `0`.
6. If either child returns `0` (has a camera), the current node is successfully monitored by that child, so return `1`.
7. If neither child has a camera nor is uncovered (both returned `1`), the current node is left uncovered; return `-1` so its parent can handle it.
8. In the main function, after the recursion finishes, check if the root node returned `-1`. If so, increment `cams` once more because the root has no parent to cover it.
9. Return `cams`.

## Concept to Remember
- Greedy algorithms on trees
- Post-order traversal (Bottom-up processing)
- State-machine dynamic programming/recursion patterns

## Common Mistakes
- Placing cameras at the leaf nodes instead of their parents, which results in suboptimal coverage.
- Forgetting to check if the root node itself is uncovered after the recursion completes.
- Returning `0` for `null` nodes instead of `1`, which incorrectly forces leaf nodes to place unnecessary cameras.
- Misinterpreting the state definitions and mixing up when a node is covered versus when it needs a camera.

## Complexity Analysis
- Time: O(N) - We visit every node in the binary tree exactly once during the post-order traversal.
- Space: O(H) - The call stack uses space proportional to the height of the tree `H` due to recursion, which is O(N) in the worst case (skewed tree) and O(log N) in the best case (balanced tree).

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
    // Global counter to track total cameras placed across the tree
    int cams = 0;
    
    public int minCameraCover(TreeNode root) {
        // Evaluate the root node using our recursive helper function
        boolean rootCovered = func(root) != -1; 
        
        // If the root itself is not covered (-1), we must place a camera here
        if(!rootCovered) cams++; // since we are coming bottom-up, there is no parent to root, so root coverage must be checked explicitly by us.
        
        // Return the final accumulated count of cameras
        return cams;
    }
    
    public int func(TreeNode root){
        // Base case: null nodes are considered covered so they don't force leaves to place cameras
        if(root == null) return 1;
        
        // Recursively get the state of left and right subtrees (post-order traversal)
        int left = func(root.left);
        int right = func(root.right);
        
        // If any child is not covered (-1), we need to place a camera at the current node
        if(left == -1 || right == -1){cams++; return 0;} // agr koi bhi child uncovered hai, we need a camera
        
        // If any child has a camera (0), the current node is successfully covered
        if(left == 0 || right == 0) return 1; // agr koi bhi child covered hai, we are covered
        
        // Otherwise, both children are covered but have no cameras, so current node is left uncovered
        return -1; // else we are not covered, parent will handle it
    }
}
```

## Interview Tips
- Clearly explain the three states upfront before diving into coding; interviewers love well-defined state spaces.
- Emphasize why a greedy bottom-up approach beats a top-down approach for this problem.
- Walk through a small test case (like a 3-node tree or a skewed 4-node tree) to trace the state transitions and prove correctness.

## Revision Checklist
- [ ] Understand why we place cameras on parents of leaves rather than leaves.
- [ ] Memorize the 3-state values (`-1`, `0`, `1`) and what each represents.
- [ ] Remember to check the root node explicitly after recursion.
- [ ] Be able to write the post-order traversal logic cleanly from scratch.

## Similar Problems
- `Monotone Increasing Digits`
- `House Robber III`
- `Distribute Coins in Binary Tree`

## Tags
`Tree` `Depth-First Search` `Dynamic Programming` `Binary Tree`
