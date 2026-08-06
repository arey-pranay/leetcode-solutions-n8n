# Binary Tree Maximum Path Sum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Tree` `Depth-First Search` `Binary Tree` `DP on Trees`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java
class Solution {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }

    public int func(TreeNode root){
        if(root==null) return 0;
        int leftMax = Math.max(0,func(root.left));
        int rightMax = Math.max(0,func(root.right));
        ans = Math.max(ans, root.val+rightMax+leftMax);
       // parent se kahege ki if you take me-> then the best I can do is:
        return root.val + Math.max(leftMax , rightMax); 
    }
  
}
```

---

---
## Quick Revision
Finds the maximum sum of a path in a binary tree, where a path can start and end at any node.
Solved using a recursive approach that calculates path sums from children and updates a global maximum.

## Intuition
The "aha moment" comes from realizing that for any given node, the maximum path sum *passing through* that node can be formed by taking the node's value, plus the maximum possible path sum from its left child (if positive), plus the maximum possible path sum from its right child (if positive). However, when returning a value *up* to the parent, a node can only contribute *one* path (either through its left or right child, or just itself), not both. This distinction is crucial. We need a global variable to track the overall maximum path sum found so far, as the maximum path might not necessarily go through the root.

## Algorithm
1. Initialize a global variable `ans` to `Integer.MIN_VALUE` to store the maximum path sum found.
2. Define a recursive helper function `func(TreeNode node)` that returns the maximum path sum starting from `node` and going downwards (either left or right, but not both).
3. Base Case: If `node` is null, return 0 (an empty path contributes nothing).
4. Recursively call `func` on the left child: `leftMax = func(node.left)`.
5. Recursively call `func` on the right child: `rightMax = func(node.right)`.
6. Crucially, if `leftMax` or `rightMax` are negative, we should ignore them for path calculations originating from the current node, as a negative contribution would decrease the sum. So, take `leftMax = Math.max(0, leftMax)` and `rightMax = Math.max(0, rightMax)`.
7. Calculate the path sum that *passes through* the current `node` (potentially forming a "V" shape): `currentPathSum = node.val + leftMax + rightMax`.
8. Update the global `ans` with the maximum of `ans` and `currentPathSum`: `ans = Math.max(ans, currentPathSum)`.
9. For the value returned to the parent, the current node can only extend a path in one direction. So, return `node.val + Math.max(leftMax, rightMax)`. This represents the maximum path sum that *starts* at the current node and goes down one of its branches.
10. Call `func(root)` to start the recursion.
11. Return the final `ans`.

## Concept to Remember
*   **Recursion and Tree Traversal**: Understanding how to traverse a tree recursively and process nodes.
*   **Dynamic Programming (Implicit)**: The subproblem solutions (max path from children) are used to build up the solution for the parent.
*   **Global State vs. Return Value**: Differentiating between information needed for the overall solution (`ans`) and information needed for parent nodes (return value of `func`).

## Common Mistakes
*   **Not handling negative path sums**: Forgetting to take `Math.max(0, ...)` for `leftMax` and `rightMax` when calculating the path sum through the current node. This can lead to incorrect maximums if children contribute negatively.
*   **Confusing return value with global maximum**: Returning `node.val + leftMax + rightMax` from the recursive function instead of `node.val + Math.max(leftMax, rightMax)`. The former is a path that *splits* at the current node, which cannot be extended upwards.
*   **Not initializing `ans` correctly**: Initializing `ans` to 0 or a small positive number instead of `Integer.MIN_VALUE` can lead to incorrect results for trees with all negative node values.
*   **Forgetting the base case**: Not handling `root == null` correctly, which can lead to `NullPointerException` or incorrect calculations.

## Complexity Analysis
- Time: O(N) - reason: Each node in the binary tree is visited exactly once by the recursive function.
- Space: O(H) - reason: The space complexity is determined by the recursion depth, which is the height (H) of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
class Solution {
    // Initialize ans to the smallest possible integer value to ensure any valid path sum will be greater.
    int ans = Integer.MIN_VALUE;

    // The main function that initiates the path sum calculation.
    public int maxPathSum(TreeNode root) {
        // Call the recursive helper function to compute path sums and update ans.
        func(root);
        // Return the overall maximum path sum found.
        return ans;
    }

    // Recursive helper function to calculate the maximum path sum.
    // It returns the maximum path sum starting from 'root' and going downwards (one branch).
    public int func(TreeNode root){
        // Base case: If the node is null, it contributes 0 to any path.
        if(root==null) return 0;

        // Recursively find the maximum path sum from the left child.
        // If the left path sum is negative, we discard it by taking max(0, ...), as it would decrease the total sum.
        int leftMax = Math.max(0,func(root.left));

        // Recursively find the maximum path sum from the right child.
        // Similarly, discard negative path sums from the right.
        int rightMax = Math.max(0,func(root.right));

        // Calculate the maximum path sum that *passes through* the current node.
        // This path can potentially include the current node, its left subtree's max path, and its right subtree's max path.
        // This is a candidate for the overall maximum path sum.
        ans = Math.max(ans, root.val + rightMax + leftMax);

        // For the value returned to the parent, the current node can only extend a path in one direction (either left or right).
        // So, we return the current node's value plus the maximum of the left and right path sums.
        // This ensures that the path returned to the parent is a single, continuous path.
        return root.val + Math.max(leftMax , rightMax);
    }
}
```

## Interview Tips
*   **Clarify Path Definition**: Ensure you understand that a path does not need to start or end at the root, and it can be a single node.
*   **Explain the Two Roles of the Recursive Function**: Clearly articulate that the function has two responsibilities: (1) updating the global maximum path sum, and (2) returning the maximum path sum that can be extended upwards to its parent.
*   **Walk Through an Example**: Use a small tree (e.g., with 3-5 nodes) to trace the execution of your `func` function, showing how `ans` is updated and what values are returned.
*   **Address Edge Cases**: Discuss how you handle null nodes and trees with all negative values.

## Revision Checklist
- [ ] Understand the problem statement: path can start/end anywhere.
- [ ] Implement recursive helper function.
- [ ] Handle base case (null node).
- [ ] Correctly calculate path sum through current node (using `Math.max(0, ...)`).
- [ ] Update global maximum (`ans`).
- [ ] Correctly return value for parent (single branch path).
- [ ] Initialize `ans` to `Integer.MIN_VALUE`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Subarray
*   Path Sum II
*   Path Sum

## Tags
`Tree` `Depth-First Search` `Dynamic Programming` `Binary Tree`
