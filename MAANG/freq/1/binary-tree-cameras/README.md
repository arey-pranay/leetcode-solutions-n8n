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
Given a binary tree, find the minimum number of cameras required to cover all nodes.
We solve this problem by recursively checking each node and placing cameras as necessary.

## Intuition
The key insight is that a node can be covered in three ways:
1. If the node itself has a camera, it's covered.
2. If one of its children has a camera, it's covered.
3. If a parent node has a camera, the current node is covered.

## Algorithm
1. Define a recursive function `func` that takes a node as input.
2. Base case: If the node is null, return 1 (covered).
3. Recursively call `func` on the left and right children.
4. If either child is not covered (i.e., returns -1), place a camera at the current node and return 0.
5. If either child is covered (i.e., returns 1), the current node is also covered, so return 1.
6. If neither child is covered, the current node is not covered, so return -1.
7. In the main function, call `func` on the root node and check if it's covered.
8. If it's not covered, increment the camera count.

## Concept to Remember
* **Recursion**: The problem can be solved by recursively checking each node and its children.
* **Memoization**: The `func` function uses memoization to avoid redundant calculations.
* **Parent-child relationships**: The problem requires understanding the relationships between parent and child nodes in a binary tree.

## Common Mistakes
* **Overcounting cameras**: Failing to account for the fact that a parent node can cover its children, leading to overcounting of cameras.
* **Undercounting coverage**: Failing to recognize that a node can be covered by its parent or a child, leading to undercounting of coverage.
* **Incorrect base case**: Failing to handle the base case correctly, leading to incorrect results.

## Complexity Analysis
- Time: O(N) - The function visits each node once.
- Space: O(H) - The maximum depth of the call stack, where H is the height of the binary tree.

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
    // 1 = covered, -1 = notCovered, 0 = camera
    int cams = 0;
    public int minCameraCover(TreeNode root) {
        // Check if the root is covered
        boolean rootCovered = func(root) != -1; 
        if(!rootCovered) cams++;// since we are coming bottom-up, there is no parent to root, so root coverage must be checked explicitly by us.
        return cams;
    }
    public int func(TreeNode root){
        // Base case: If the node is null, it's covered
        if(root==null) return 1;
        // Recursively call func on the left and right children
        int left = func(root.left);
        int right = func(root.right);
        // If either child is not covered, place a camera at the current node
        if(left == -1 || right == -1){cams++; return 0;} 
        // If either child is covered, the current node is also covered
        if(left == 0 || right == 0) return 1; 
        // If neither child is covered, the current node is not covered
        return -1; 
    }
    
}
```

## Interview Tips
* Make sure to understand the problem clearly and ask for clarification if needed.
* Use recursion to solve the problem, as it's a natural fit for tree problems.
* Use memoization to avoid redundant calculations and improve performance.
* Pay attention to the base case and make sure to handle it correctly.

## Revision Checklist
- [ ] Understand the problem clearly.
- [ ] Use recursion to solve the problem.
- [ ] Use memoization to avoid redundant calculations.
- [ ] Pay attention to the base case.

## Similar Problems
* `101. Symmetric Tree`
* `104. Maximum Depth of Binary Tree`
* `965. Univalued Binary Tree`
