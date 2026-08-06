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
Solved using a recursive approach that calculates path sums from left and right children.

## Intuition
The "aha moment" is realizing that for any given node, the maximum path sum *passing through* that node can be formed by its value plus the maximum path sum extending downwards into its left subtree and the maximum path sum extending downwards into its right subtree. However, a path can only extend *downwards* from a parent to a child once. This means when returning a value from a recursive call, we can only choose *one* of the child paths (left or right) to extend upwards to the parent. We also need to keep track of the global maximum path sum found so far, which might not necessarily pass through the root of the entire tree.

## Algorithm
1. Initialize a global variable `ans` to the smallest possible integer value to store the maximum path sum found.
2. Define a recursive helper function `func(TreeNode node)` that returns the maximum path sum starting from `node` and extending downwards into *one* of its subtrees (either left or right).
3. Base Case: If `node` is null, return 0 (an empty path has a sum of 0).
4. Recursively call `func` on the left child: `leftMax = func(node.left)`.
5. Recursively call `func` on the right child: `rightMax = func(node.right)`.
6. Crucially, if a subtree's maximum path sum extending downwards is negative, we should not include it in any path extending upwards. Therefore, take the maximum of 0 and the returned `leftMax` and `rightMax`. This effectively prunes negative path contributions.
   `leftMax = Math.max(0, leftMax)`
   `rightMax = Math.max(0, rightMax)`
7. Calculate the maximum path sum that *passes through the current `node`*. This path includes the node's value, the best path from its left subtree, and the best path from its right subtree. Update the global `ans` if this sum is greater than the current `ans`.
   `ans = Math.max(ans, node.val + leftMax + rightMax)`
8. For the value returned to the parent, we can only extend the path in *one* direction. So, return the current node's value plus the maximum of the `leftMax` and `rightMax` (after they've been potentially zeroed out).
   `return node.val + Math.max(leftMax, rightMax)`
9. Call the helper function `func` with the root of the tree.
10. Return the final `ans`.

## Concept to Remember
*   **Tree Traversal (Post-order like):** The solution processes children before the parent, which is characteristic of post-order traversal.
*   **Dynamic Programming (Implicit):** Subproblems (maximum path sums from subtrees) are solved and their results are used to solve larger problems.
*   **Greedy Approach:** At each node, we make the locally optimal choice of extending a path downwards or not (if it's negative) to maximize the potential path sum.

## Common Mistakes
*   **Not handling negative path sums:** Forgetting to take `Math.max(0, ...)` for child path sums can lead to incorrect results if subtrees have negative maximum path sums.
*   **Confusing the return value with the global maximum:** The value returned by the recursive function is the maximum path sum *extending downwards from the current node*, which is different from the overall maximum path sum that might "turn" at the current node.
*   **Incorrectly updating the global maximum:** Not considering the path sum that *includes* the current node and both its best left and right downward paths.
*   **Off-by-one errors in path definition:** Misunderstanding that a path can start and end anywhere, not necessarily at the root or leaves.

## Complexity Analysis
- Time: O(N) - reason: Each node in the tree is visited exactly once by the recursive function.
- Space: O(H) - reason: The space complexity is determined by the recursion stack depth, which is proportional to the height (H) of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
class Solution {
    // Initialize a variable 'ans' to store the maximum path sum found so far.
    // It's initialized to the smallest possible integer value to ensure any valid path sum will be greater.
    int ans = Integer.MIN_VALUE;

    // The main function that initiates the path sum calculation.
    public int maxPathSum(TreeNode root) {
        // Call the recursive helper function starting from the root.
        func(root);
        // Return the globally tracked maximum path sum.
        return ans;
    }

    // Recursive helper function to calculate maximum path sums.
    // It returns the maximum path sum starting from 'root' and extending downwards into *one* of its subtrees.
    public int func(TreeNode root){
        // Base case: If the current node is null, it contributes 0 to any path.
        if(root==null) return 0;

        // Recursively calculate the maximum path sum from the left subtree.
        // Math.max(0, ...) ensures that if the left subtree's max path sum is negative, we don't include it, effectively treating it as an empty path (sum 0).
        int leftMax = Math.max(0,func(root.left));

        // Recursively calculate the maximum path sum from the right subtree.
        // Similar to the left side, we prune negative path sums.
        int rightMax = Math.max(0,func(root.right));

        // Calculate the maximum path sum that *passes through the current node*.
        // This path includes the current node's value, the best path from its left, and the best path from its right.
        // We update the global 'ans' if this path sum is greater than the current maximum.
        ans = Math.max(ans, root.val+rightMax+leftMax);

        // For the value returned to the parent, we can only extend the path in *one* direction (either left or right).
        // So, we return the current node's value plus the maximum of the left and right downward path sums.
        return root.val + Math.max(leftMax , rightMax);
    }
}
```

## Interview Tips
*   **Clarify Path Definition:** Ensure you understand that a path doesn't need to start at the root or end at a leaf. It can be any sequence of connected nodes.
*   **Distinguish Return Value vs. Global Max:** Emphasize that the recursive function's return value is for extending paths upwards, while the global `ans` tracks the overall maximum path sum found anywhere in the tree.
*   **Explain the `Math.max(0, ...)`:** Clearly articulate why this step is crucial for handling negative contributions from subtrees.
*   **Walk Through an Example:** Use a small tree (e.g., with negative values) to trace the execution of your `func` and `ans` updates.

## Revision Checklist
- [ ] Understand the definition of a "path" in a binary tree.
- [ ] Recognize the need for a recursive approach.
- [ ] Implement the base case for null nodes.
- [ ] Correctly calculate the path sum that *includes* the current node and both its children's best downward paths.
- [ ] Correctly determine the value to *return* to the parent (maximum of one downward path).
- [ ] Handle negative path sums from subtrees using `Math.max(0, ...)`.
- [ ] Use a global variable to track the overall maximum path sum.

## Similar Problems
*   Maximum Path Sum in a Binary Tree II (LeetCode 124 - this problem)
*   Maximum Subarray (LeetCode 53) - Similar concept of finding maximum sum segments.
*   Diameter of Binary Tree (LeetCode 543) - Related to finding longest paths, but for length, not sum.

## Tags
`Depth-First Search` `Tree` `Binary Tree` `Recursion`
