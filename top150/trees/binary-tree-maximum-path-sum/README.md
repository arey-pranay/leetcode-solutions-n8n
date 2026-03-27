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
The "aha moment" comes from realizing that for any given node, the maximum path sum *passing through* that node can be formed by taking the node's value, plus the maximum possible path sum from its left child (if positive), plus the maximum possible path sum from its right child (if positive). However, when a node returns a value to its parent, it can only contribute *one* path (either through its left or right subtree, whichever is greater) to avoid creating a "V" shape that would not be a valid path.

## Algorithm
1. Initialize a global variable `ans` to store the maximum path sum found so far, setting it to the smallest possible integer value.
2. Define a recursive helper function `func(TreeNode node)` that returns the maximum path sum starting from `node` and going downwards (i.e., a path that can be extended upwards by its parent).
3. **Base Case:** If `node` is null, return 0 (as a null node contributes nothing to a path sum).
4. Recursively call `func` on the left child: `leftMax = func(node.left)`.
5. Recursively call `func` on the right child: `rightMax = func(node.right)`.
6. **Pruning Negative Paths:** Since we only want to extend paths that increase the sum, if `leftMax` or `rightMax` are negative, treat them as 0. This is done using `Math.max(0, leftMax)` and `Math.max(0, rightMax)`.
7. **Calculate Path Through Current Node:** Calculate the potential maximum path sum that *includes* the current `node` as the highest point (the "peak" of the path). This path sum is `node.val + Math.max(0, leftMax) + Math.max(0, rightMax)`.
8. **Update Global Maximum:** Update the global `ans` with the maximum between its current value and the `pathViaRoot` calculated in the previous step: `ans = Math.max(ans, pathViaRoot)`.
9. **Return Value to Parent:** The function must return the maximum path sum that can be extended upwards to the parent. This path can only go down one branch from the current node. Therefore, return `node.val + Math.max(Math.max(0, leftMax), Math.max(0, rightMax))`.
10. Call `func(root)` to start the recursion.
11. Return the final `ans`.

## Concept to Remember
*   **Recursion and Post-order Traversal:** The problem is naturally solved with recursion, and the logic of processing children before the parent (to decide what to return) resembles a post-order traversal.
*   **Dynamic Programming (Implicit):** Although not explicitly using a DP table, the recursive calls with memoization (or in this case, the return values themselves acting as subproblem solutions) exhibit DP characteristics.
*   **Handling Negative Values:** Crucially, paths with negative sums should be discarded or treated as zero when extending from a child to a parent or when calculating a path through a node.

## Common Mistakes
*   **Not handling negative path sums:** Forgetting to use `Math.max(0, ...)` when considering child paths can lead to incorrect results if subtrees have negative sums.
*   **Incorrect return value:** Returning the `pathViaRoot` instead of the maximum single-branch path to the parent. The parent can only extend one branch.
*   **Not initializing `ans` correctly:** Initializing `ans` to 0 might fail for trees with all negative node values. It should be initialized to `Integer.MIN_VALUE`.
*   **Confusing path definition:** Misunderstanding that a path doesn't need to start at the root or end at a leaf.

## Complexity Analysis
- Time: O(N) - reason: Each node in the binary tree is visited exactly once by the recursive function.
- Space: O(H) - reason: The space complexity is determined by the recursion depth, which is the height (H) of the binary tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

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

    // This recursive helper function calculates the maximum path sum starting from 'root'
    // and going downwards, and also updates the global 'ans' if a new maximum path is found.
    public int func(TreeNode root){
        // Base case: If the current node is null, it contributes 0 to any path sum.
        if(root == null) return 0;

        // Recursively find the maximum path sum from the left child.
        // We take Math.max(0, ...) because if the path sum from the child is negative,
        // we don't want to extend that path; it's better to not include it (effectively adding 0).
        int leftKaMax = Math.max(0,func(root.left));
        // Recursively find the maximum path sum from the right child, applying the same logic.
        int rightKaMax = Math.max(0,func(root.right));

        // Calculate the maximum path sum that *passes through* the current node.
        // This path can potentially include the current node, the best path from its left, and the best path from its right.
        // Since leftKaMax and rightKaMax are already non-negative, this sum represents a valid path that "peaks" at 'root'.
        int pathViaRoot = leftKaMax + rightKaMax + root.val;
        // Update the global maximum path sum if the path through the current node is greater than the current maximum.
        ans = Math.max(ans, pathViaRoot);

        // For the parent node, this current node can only contribute a path that goes downwards from it.
        // It cannot contribute a path that splits into both left and right subtrees (a "V" shape),
        // as that would not be a valid single path extending upwards.
        // Therefore, we return the current node's value plus the maximum of the non-negative path sums from its children.
        // This is the best single-branch path that can be extended upwards to the parent.
        return root.val + Math.max(leftKaMax,rightKaMax);
    }
}
```

## Interview Tips
*   **Clarify Path Definition:** Ensure you understand what constitutes a "path" (can start/end anywhere, doesn't need to include root, can be a single node).
*   **Explain the Return Value:** Clearly articulate why the recursive function returns `node.val + Math.max(leftMax, rightMax)` and not `pathViaRoot`. This is a common point of confusion.
*   **Handle Negatives Explicitly:** Emphasize how `Math.max(0, ...)` is used to prune negative sub-paths, as this is critical for correctness.
*   **Trace an Example:** Be prepared to trace a small tree (e.g., with a few nodes, including negative values) to demonstrate your understanding of the algorithm's execution.

## Revision Checklist
- [ ] Understand the definition of a path in a binary tree.
- [ ] Implement a recursive function that traverses the tree.
- [ ] Correctly handle the base case (null nodes).
- [ ] Use `Math.max(0, ...)` to ignore negative path sums from children.
- [ ] Calculate the path sum that passes *through* the current node and update the global maximum.
- [ ] Determine the correct value to *return* to the parent (maximum single-branch path).
- [ ] Initialize the global maximum variable appropriately (`Integer.MIN_VALUE`).

## Similar Problems
*   [LeetCode 124] Binary Tree Maximum Path Sum (This problem)
*   [LeetCode 543] Diameter of Binary Tree
*   [LeetCode 104] Maximum Depth of Binary Tree
*   [LeetCode 111] Minimum Depth of Binary Tree

## Tags
`Tree` `Depth-First Search` `Binary Tree` `Recursion`

## My Notes
5 line amazing
