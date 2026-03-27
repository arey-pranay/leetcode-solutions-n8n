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
        int leftKaMax = Math.max(0,func(root.left)); 
        int rightKaMax = Math.max(0,func(root.right));     
            
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
This problem asks for the maximum sum of a path in a binary tree, where a path can start and end at any node. The solution uses a recursive approach to explore all possible paths and track the maximum sum.

## Intuition
The core idea is that for any given node, the maximum path sum passing through it can either be:
1. The node's value itself.
2. The node's value plus the maximum path sum from its left child (if positive).
3. The node's value plus the maximum path sum from its right child (if positive).
4. The node's value plus the maximum path sum from its left child (if positive) plus the maximum path sum from its right child (if positive).

The recursive function should return the maximum path sum that *starts* at the current node and goes *downwards* (either left or right, but not both branching out). This is because a path that branches out at a node cannot be extended upwards to its parent. The global maximum path sum is updated whenever we find a path that includes the current node as the "highest" point (i.e., it includes both left and right sub-paths).

## Algorithm
1. Initialize a global variable `ans` to `Integer.MIN_VALUE` to store the maximum path sum found so far.
2. Define a recursive helper function `func(TreeNode root)` that returns the maximum path sum starting from `root` and going downwards.
3. **Base Case:** If `root` is `null`, return `0` (as an empty path contributes nothing to the sum).
4. **Recursive Step:**
   a. Recursively call `func` on the left child: `leftMax = func(root.left)`.
   b. Recursively call `func` on the right child: `rightMax = func(root.right)`.
   c. **Important:** Since we only want to consider paths that contribute positively, take the maximum of `0` and the returned values from the children: `leftKaMax = Math.max(0, leftMax)` and `rightKaMax = Math.max(0, rightMax)`. This effectively prunes negative sub-paths.
   d. Calculate the maximum path sum that *passes through the current node* as the highest point: `pathViaRoot = leftKaMax + rightKaMax + root.val`.
   e. Update the global `ans` with the maximum of its current value and `pathViaRoot`: `ans = Math.max(ans, pathViaRoot)`.
   f. **Return Value:** The function must return the maximum path sum that *starts at the current node and extends downwards*. This means it can only go down one branch (left or right). So, return `root.val + Math.max(leftKaMax, rightKaMax)`. This value will be used by the parent node.
5. The `maxPathSum` function simply calls `func(root)` and returns the final `ans`.

## Concept to Remember
*   **Recursion and Post-order Traversal:** The problem is naturally solved with recursion, and the logic resembles a post-order traversal where we process children before the parent.
*   **Dynamic Programming (Implicit):** Although not explicitly using a DP table, the recursive calls with memoization (or rather, the return values of subproblems) implicitly solve overlapping subproblems.
*   **Handling Negative Values:** The crucial part is deciding how to handle negative path sums from children. We should only extend paths that increase the total sum.

## Common Mistakes
*   **Not handling negative child path sums:** Forgetting to use `Math.max(0, ...)` for the results of recursive calls can lead to incorrect results if sub-paths are negative.
*   **Incorrect return value from the recursive function:** Returning the `pathViaRoot` instead of `root.val + Math.max(leftKaMax, rightKaMax)` would mean the parent node doesn't get the correct information about the best downward path.
*   **Not initializing `ans` correctly:** Initializing `ans` to `0` might be problematic if all node values are negative. `Integer.MIN_VALUE` is the correct initialization.
*   **Confusing path definition:** A path can start and end anywhere, not necessarily at the root or leaves. The algorithm correctly accounts for this by considering `pathViaRoot` at each node.

## Complexity Analysis
- Time: O(N) - Each node in the tree is visited exactly once by the recursive function.
- Space: O(H) - Where H is the height of the tree. This is due to the recursion call stack. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

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
        // Call the recursive helper function starting from the root.
        func(root);
        // After the recursion completes, 'ans' will hold the maximum path sum.
        return ans;
    }
    
    // This recursive helper function calculates the maximum path sum that *starts* at the current node 'root'
    // and extends downwards into its subtrees. It also updates the global 'ans' if a larger path sum is found
    // that *passes through* the current node as its highest point.
    public int func(TreeNode root){
       
        // Base case: If the current node is null, it contributes 0 to any path sum.
        if(root == null) return 0;
        
        // Recursively find the maximum path sum from the left child.
        // We take Math.max(0, ...) because if the best path from the left child is negative,
        // we are better off not including it in the path extending from the current node.
        int leftKaMax = Math.max(0,func(root.left)); 
        // Recursively find the maximum path sum from the right child.
        // Similar to the left child, we discard negative contributions.
        int rightKaMax = Math.max(0,func(root.right));     
            
        // Calculate the maximum path sum that *includes the current node as the highest point*.
        // This path can potentially use the best downward paths from both left and right children.
        // 'leftKaMax' and 'rightKaMax' are guaranteed to be non-negative here.
        int pathViaRoot = leftKaMax + rightKaMax + root.val;
        
        // Update the global maximum path sum ('ans') if the path passing through the current node is greater.
        ans = Math.max(ans, pathViaRoot);
        
        // The value returned by this function is the maximum path sum that *starts at the current node*
        // and can be extended *upwards* to its parent. This path can only go down *one* of the branches (left or right).
        // We choose the branch that yields a larger sum.
        return root.val + Math.max(leftKaMax,rightKaMax);    
    }
}
```

## Interview Tips
1.  **Clarify Path Definition:** Ensure you understand that a path doesn't need to start at the root or end at a leaf. It can be any sequence of connected nodes.
2.  **Explain the Return Value:** Clearly articulate what the recursive function returns (max path sum starting at current node and going down) versus what it updates globally (max path sum passing through current node as the peak).
3.  **Handle Negative Numbers:** Emphasize how `Math.max(0, ...)` is crucial for correctly handling subtrees with negative sums.
4.  **Trace an Example:** Be prepared to walk through a small tree example to demonstrate how the `ans` variable is updated and what values are returned by the recursive calls.

## Revision Checklist
- [ ] Understand the definition of a "path" in a binary tree.
- [ ] Recognize the need for a recursive approach.
- [ ] Implement the base case for null nodes.
- [ ] Correctly calculate the path sum that passes through the current node as the highest point.
- [ ] Correctly update the global maximum path sum.
- [ ] Ensure the recursive function returns the maximum path sum that can be extended upwards.
- [ ] Handle negative contributions from child paths using `Math.max(0, ...)`.
- [ ] Initialize the global maximum to `Integer.MIN_VALUE`.

## Similar Problems
*   Maximum Path Sum in a Binary Tree (LeetCode 124) - This is the exact problem.
*   Diameter of Binary Tree (LeetCode 543) - Similar in that it involves finding a maximum length/sum path, but the definition of path and the constraints are different.
*   Lowest Common Ancestor of a Binary Tree (LeetCode 236) - Related to tree traversal and node relationships.

## Tags
`Tree` `Depth-First Search` `Dynamic Programming` `Binary Tree`

## My Notes
5 line amazing
