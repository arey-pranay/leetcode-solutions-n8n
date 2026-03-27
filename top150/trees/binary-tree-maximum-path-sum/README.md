# Binary Tree Maximum Path Sum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Tree` `Depth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java

// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode() {}
//     TreeNode(int val) { this.val = val; }
//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

class Solution {
    int ans = Integer.MIN_VALUE;
    // HashMap<TreeNode,ArrayList<TreeNode>> hm = new HashMap<>();
    public int maxPathSum(TreeNode root) {
        func(root);
        return ans;
    }
    
    
    public int func(TreeNode root){
       
        if(root == null) return 0;
        // agr child ne apna best diya lekin fir bhi -ve aagya, to usko consider mt kro
        int leftKaMax = func(root.left); 
        if(leftKaMax<0) leftKaMax = 0;
        int rightKaMax = func(root.right); 
        if(rightKaMax<0) rightKaMax = 0;
        
        // current node pe rehte hue best answer jo aa skta hai -> pathViaRoot (because leftKaMax and rightKaMax aer non-negative always)
        int pathViaRoot = leftKaMax + rightKaMax + root.val;
        ans = Math.max(ans, pathViaRoot);
        
        // since /\ shape wale children ko node ko kbhi parent se nhi jod skte hai
        // therefore child returns to parent that -> agr aap mujhe loge, to mai apane left aur right walo me se max chain leke aapko itna de skta hu
        return root.val + Math.max(leftKaMax,rightKaMax);    
        
    }
    
    
    
    
    
    
    
    
    
    // public int getBestPath(TreeNode t, TreeNode par){
    //     int b1 = 0;
    //     int b2 = 0;
    //     for(TreeNode neigh : hm.get(t)){
    //         if(neigh==par) continue;
    //         int currAns = getBestPath(neigh,t);
    //         if(currAns > b1){
    //             b2 = b1;
    //             b1 = currAns;
    //         } else if(currAns >b2) b2 = currAns;
    //     }
    //     ans = Math.max(ans, t.val + b1 + b2);
    //     return t.val + b1;
    // }
    
    
    // public void treeToGraph(TreeNode root){
    //     if(root==null) return;
    //     ArrayList<TreeNode> arr = hm.getOrDefault(root, new ArrayList<>());
    //     ArrayList<TreeNode> arr2 = new ArrayList<>();
    //     if(root.left!=null){
    //         arr.add(root.left);
    //         arr2 = hm.getOrDefault(root.left, new ArrayList<>());
    //         arr2.add(root);
    //         hm.put(root.left,arr2);
    //     }
    //     if(root.right!=null){
    //         arr.add(root.right);
    //         arr2 = hm.getOrDefault(root.right, new ArrayList<>());
    //         arr2.add(root);
    //         hm.put(root.right,arr2);
    //     }
    //     hm.put(root,arr);
    //     treeToGraph(root.left);
    //     treeToGraph(root.right);
    // }
}
```

---

---
## Quick Revision
This problem asks for the maximum sum of a path in a binary tree, where a path can start and end at any node. The solution uses a recursive approach to explore all possible paths.

## Intuition
The core idea is that for any given node, the maximum path sum passing through it can be formed by the node's value plus the maximum path sum from its left child and the maximum path sum from its right child. However, a path can only extend upwards from a child to its parent once. This means a child can only contribute its best *single* path sum (either left-leaning or right-leaning) to its parent, not a path that branches out. We need to keep track of the global maximum path sum found so far.

## Algorithm
1. Initialize a global variable `ans` to the smallest possible integer value to store the maximum path sum found.
2. Define a recursive helper function `func(node)` that returns the maximum path sum starting from `node` and going downwards (either to the left or right subtree, but not both branching out).
3. **Base Case:** If `node` is null, return 0 (as a null node contributes nothing to a path sum).
4. Recursively call `func` on the left child: `leftMax = func(node.left)`.
5. Recursively call `func` on the right child: `rightMax = func(node.right)`.
6. **Pruning Negative Paths:** If `leftMax` is negative, set it to 0. This is because a path with a negative sum would only decrease the total path sum, so we'd rather not include it. Do the same for `rightMax`.
7. **Calculate Path Through Current Node:** Calculate the potential maximum path sum that *includes* the current `node` as the highest point (the "peak" of the path). This path sum is `node.val + leftMax + rightMax`.
8. **Update Global Maximum:** Update `ans` with the maximum of its current value and `pathViaRoot`. This step considers paths that might "turn" at the current node.
9. **Return Value for Parent:** The function must return the maximum path sum that can be extended *upwards* to the parent. This path can only go down one branch from the current node. So, it's `node.val + Math.max(leftMax, rightMax)`.
10. Call `func(root)` to start the process.
11. Return the final `ans`.

## Concept to Remember
*   **Recursion and Post-order Traversal:** The problem is naturally solved with recursion, and the logic of processing children before the parent (to determine what to pass up) resembles a post-order traversal.
*   **Dynamic Programming (Implicit):** Although not explicitly using a DP table, the recursive calls with memoization (or the way we reuse computed subtree results) have DP characteristics.
*   **Handling Negative Values:** The crucial part is deciding when to "cut off" a path that yields a negative sum, as it's better to not include it.

## Common Mistakes
*   **Not handling negative node values or subtree sums:** Forgetting to prune negative `leftMax` and `rightMax` can lead to incorrect results.
*   **Confusing the return value of the recursive function:** The function needs to return the best *single-path* sum to the parent, not the best path that *turns* at the current node.
*   **Not updating the global maximum correctly:** Failing to consider paths that might "turn" at the current node (i.e., `node.val + leftMax + rightMax`) will miss potential maximums.
*   **Incorrect base case for recursion:** Returning a non-zero value for a null node can cause issues.

## Complexity Analysis
- Time: O(N) - reason: Each node in the binary tree is visited exactly once by the recursive function.
- Space: O(H) - reason: This is the space used by the recursion call stack, where H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
// Definition for a binary tree node.
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode() {}
//     TreeNode(int val) { this.val = val; }
//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

class Solution {
    // This variable will store the overall maximum path sum found across the entire tree.
    // It's initialized to the smallest possible integer value to ensure any valid path sum will be greater.
    int ans = Integer.MIN_VALUE;

    // The main function that initiates the path sum calculation.
    public int maxPathSum(TreeNode root) {
        // Call the recursive helper function to start the process from the root.
        func(root);
        // After the recursion completes, 'ans' will hold the maximum path sum.
        return ans;
    }

    // This recursive function calculates the maximum path sum that can be extended upwards from the current node.
    // It also updates the global 'ans' with the maximum path sum that *passes through* the current node.
    public int func(TreeNode root){
        // Base case: If the current node is null, it contributes 0 to any path sum.
        if(root == null) return 0;

        // Recursively find the maximum path sum from the left child.
        // This path can only extend downwards from the left child.
        int leftKaMax = func(root.left);
        // If the maximum path sum from the left child is negative, we discard it.
        // A negative contribution would only decrease the total path sum, so we treat it as 0.
        if(leftKaMax<0) leftKaMax = 0;

        // Recursively find the maximum path sum from the right child.
        // This path can only extend downwards from the right child.
        int rightKaMax = func(root.right);
        // If the maximum path sum from the right child is negative, we discard it.
        // A negative contribution would only decrease the total path sum, so we treat it as 0.
        if(rightKaMax<0) rightKaMax = 0;

        // Calculate the maximum path sum that *passes through* the current node.
        // This path can potentially include the current node, the best path from its left, and the best path from its right.
        // 'leftKaMax' and 'rightKaMax' are guaranteed to be non-negative here due to the pruning step.
        int pathViaRoot = leftKaMax + rightKaMax + root.val;

        // Update the global maximum path sum ('ans') if the path passing through the current node is greater.
        ans = Math.max(ans, pathViaRoot);

        // The value returned by this function is the maximum path sum that can be extended *upwards* to the parent.
        // A path extending upwards can only take one branch (either left or right) from the current node.
        // Therefore, we return the current node's value plus the maximum of the (non-negative) left and right path sums.
        return root.val + Math.max(leftKaMax,rightKaMax);
    }
}
```

## Interview Tips
*   **Clarify Path Definition:** Ensure you understand what constitutes a "path" (it doesn't have to go through the root, and it can start/end anywhere).
*   **Explain the Return Value:** Clearly articulate why the recursive function returns `node.val + Math.max(leftMax, rightMax)` and how it differs from the `pathViaRoot` calculation. This is a common point of confusion.
*   **Handle Edge Cases:** Discuss how you handle null nodes and trees with only one node. Also, mention the importance of initializing `ans` to `Integer.MIN_VALUE`.
*   **Walk Through an Example:** Be prepared to trace your algorithm on a small tree, especially one with negative values, to demonstrate your understanding.

## Revision Checklist
- [ ] Understand the definition of a path in a binary tree.
- [ ] Implement a recursive function that returns the maximum path sum extending upwards from a node.
- [ ] Correctly handle negative path sums from children by pruning them to 0.
- [ ] Calculate and update the global maximum path sum considering paths that "turn" at the current node.
- [ ] Ensure the base case for recursion is handled correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Path Sum in a Binary Tree II (LeetCode 124) - This is the exact problem.
*   Diameter of Binary Tree (LeetCode 543) - Similar in that it involves calculating lengths/sums related to paths, but the definition of a path is different.
*   Lowest Common Ancestor of a Binary Tree (LeetCode 236) - Involves tree traversal and understanding node relationships.

## Tags
`Depth-First Search` `Tree` `Binary Tree` `Recursion`
