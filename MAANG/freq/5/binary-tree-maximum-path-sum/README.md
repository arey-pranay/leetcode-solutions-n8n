# Binary Tree Maximum Path Sum

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
    int ans =Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }
    public int func(TreeNode root){
        if(root==null) return 0;
        int lsum = Math.max(0,func(root.left));
        int rsum = Math.max(0,func(root.right));
        ans = Math.max(ans,lsum+rsum+root.val);
        return root.val+Math.max(lsum,rsum); 
        //kyuki mera parent, mere through -> ye est path le skta hai. (me + leftSubtree) or (me+rightSubTree)
    }
}
```

---

---
## Quick Revision
Find the maximum sum of a path in a binary tree where each path passes through the root node.

We solve this problem by recursively traversing the left and right subtrees, keeping track of the maximum sum that can be obtained by passing through each node.

## Intuition
The key insight is to realize that the maximum path sum must pass through the root node. We can therefore calculate the maximum sum for each subtree and combine them with the current node's value to find the overall maximum path sum.

## Algorithm

1. Define a helper function `func` that takes a TreeNode as input.
2. If the input tree is null, return 0 (base case).
3. Recursively calculate the maximum sum of the left and right subtrees (`lsum` and `rsum`) by calling `func` on their respective children.
4. Update the global variable `ans` with the maximum path sum passing through the current node (`lsum + rsum + root.val`).
5. Return the maximum sum that can be obtained by passing through the current node, which is either `root.val + lsum` or `root.val + rsum`.

## Concept to Remember

*   Recursion and dynamic programming
*   Path optimization problems in graphs
*   Importance of keeping track of intermediate results (e.g., maximum sum at each node)

## Common Mistakes

*   Failing to realize that the maximum path must pass through the root node.
*   Not properly handling edge cases, such as when a subtree is empty.
*   Not updating the global `ans` variable correctly.

## Complexity Analysis
- Time: O(N) - reason / We visit each node once in the recursive function calls.
- Space: O(H) - reason / The maximum recursion depth is equal to the height of the tree.

## Commented Code
```java
class Solution {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // Call the helper function on the input root node and return the result.
        func(root);
        return ans;
    }

    /**
     * Recursive helper function to calculate the maximum path sum passing through a given node.
     *
     * @param root The current TreeNode
     */
    public int func(TreeNode root) {
        // Base case: If the input tree is null, return 0 (no path).
        if (root == null) return 0;

        // Recursively calculate the maximum sum of the left and right subtrees.
        int lsum = Math.max(0, func(root.left)); // Ensure we don't go below 0.
        int rsum = Math.max(0, func(root.right));

        // Update the global ans with the maximum path sum passing through this node.
        ans = Math.max(ans, lsum + rsum + root.val);

        // Return the maximum sum that can be obtained by passing through this node.
        return root.val + Math.max(lsum, rsum); // Include the current node's value in the sum.
    }
}
```

## Interview Tips

*   Make sure to clearly understand the problem and ask for clarification if needed.
*   Identify key insights, such as the fact that the maximum path must pass through the root node.
*   Use recursion and dynamic programming to efficiently solve the problem.

## Revision Checklist
- [ ] Understand the problem statement and requirements
- [ ] Implement recursive helper function `func`
- [ ] Properly handle edge cases (e.g., empty subtrees)
- [ ] Update global variable `ans` correctly

## Similar Problems

*   **Path Sum II**: Find all paths in a binary tree whose sum is equal to a given target value.
*   **Minimum Depth of Binary Tree**: Find the minimum depth of a binary tree, considering each node's value.

## Tags
`Binary Tree` `Recursion` `Dynamic Programming`
