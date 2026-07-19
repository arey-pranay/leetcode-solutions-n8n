# Count Dominant Nodes In A Binary Tree

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
    int count = 0;
    public int countDominantNodes(TreeNode root) {
        maxi(root);
        return count;
    }
    
    // mere children ne aur maine milke max kya nikaala
    public int maxi(TreeNode root){
        if(root==null) return 0;
        int max = Math.max(root.val,Math.max(maxi(root.left),maxi(root.right)));
        if(root.val >= max) count++;
        return max;
    }
}
```

---

---
## Quick Revision
Given a binary tree, count nodes whose value is greater than or equal to the maximum value in their subtree.
This is solved using a post-order traversal to find the maximum in each subtree and then checking the dominance condition.

## Intuition
The core idea is that to determine if a node is "dominant," we need to know the maximum value within its *entire* subtree. A post-order traversal naturally allows us to compute information about the children's subtrees *before* processing the parent. If we can return the maximum value of a subtree from a recursive call, we can then compare the current node's value against this maximum.

## Algorithm
1. Initialize a global or class-level counter `count` to 0. This will store the number of dominant nodes.
2. Define a recursive helper function, let's call it `getMaxAndCountDominant(node)`. This function will do two things:
    a. Return the maximum value found in the subtree rooted at `node`.
    b. Increment the `count` if `node` is dominant.
3. Base Case: If `node` is `null`, return a value that won't interfere with `Math.max` (e.g., `Integer.MIN_VALUE` or 0 if node values are non-negative, as in the provided solution). The provided solution uses 0, assuming non-negative node values.
4. Recursive Step:
    a. Recursively call `getMaxAndCountDominant` on the left child: `leftMax = getMaxAndCountDominant(node.left)`.
    b. Recursively call `getMaxAndCountDominant` on the right child: `rightMax = getMaxAndCountDominant(node.right)`.
    c. Determine the maximum value in the current node's subtree: `currentSubtreeMax = Math.max(node.val, Math.max(leftMax, rightMax))`.
    d. Check for dominance: If `node.val >= currentSubtreeMax`, increment the global `count`.
    e. Return `currentSubtreeMax`.
5. In the main `countDominantNodes` function, call `getMaxAndCountDominant(root)` and then return the final `count`.

## Concept to Remember
*   **Tree Traversal (Post-order):** Essential for processing children's information before the parent.
*   **Recursion:** Elegant way to define operations on tree structures.
*   **Subtree Properties:** Understanding how to aggregate information (like maximum value) from subtrees.
*   **Global/Class Variables for Aggregation:** Using a shared variable to accumulate results across recursive calls.

## Common Mistakes
*   **Incorrect Traversal Order:** Using pre-order or in-order would make it difficult to get the subtree maximum before processing the parent.
*   **Not Handling Null Nodes:** Failing to return a sensible value for null children can lead to incorrect maximum calculations.
*   **Scope of Counter:** Not using a global/class variable or passing it by reference correctly can lead to the count not being updated across all recursive calls.
*   **Incorrect Dominance Check:** Comparing `node.val` with only its immediate children's maximums instead of the entire subtree maximum.
*   **Integer Overflow/Underflow:** If node values can be very large or small, ensure appropriate data types are used (though not an issue with `int` here).

## Complexity Analysis
*   **Time:** O(N) - Each node in the tree is visited exactly once by the recursive function.
*   **Space:** O(H) - Where H is the height of the tree. This is due to the recursion call stack. In the worst case (a skewed tree), H can be N, making it O(N). In a balanced tree, H is log N, making it O(log N).

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node
 *     TreeNode left; // Pointer to the left child
 *     TreeNode right; // Pointer to the right child
 *     TreeNode() {} // Default constructor
 *     TreeNode(int val) { this.val = val; } // Constructor with value
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int count = 0; // Initialize a counter to store the number of dominant nodes. This is a class member to be accessible by all recursive calls.

    public int countDominantNodes(TreeNode root) {
        maxi(root); // Start the recursive process from the root of the tree.
        return count; // Return the final count of dominant nodes.
    }
    
    // This helper function performs a post-order traversal.
    // It returns the maximum value in the subtree rooted at 'root'
    // and increments the global 'count' if 'root' is a dominant node.
    public int maxi(TreeNode root){
        if(root==null) return 0; // Base case: If the node is null, return 0. This assumes node values are non-negative. For general cases, Integer.MIN_VALUE might be safer.

        // Recursively find the maximum value in the left subtree.
        int leftMax = maxi(root.left);
        // Recursively find the maximum value in the right subtree.
        int rightMax = maxi(root.right);
        
        // Calculate the maximum value in the current subtree (including the current node).
        // It's the maximum of the current node's value, the maximum from the left subtree, and the maximum from the right subtree.
        int max = Math.max(root.val, Math.max(leftMax, rightMax));
        
        // Check if the current node's value is greater than or equal to the maximum value in its entire subtree.
        if(root.val >= max) {
            count++; // If it is, increment the dominant node counter.
        }
        
        return max; // Return the maximum value found in this subtree to the parent call.
    }
}
```

## Interview Tips
*   **Explain the Post-order Logic:** Clearly articulate why a post-order traversal is necessary to gather subtree information before making decisions about the parent node.
*   **Clarify Edge Cases:** Discuss how `null` nodes are handled and what value is returned. Mention the assumption about non-negative node values if applicable.
*   **Trace an Example:** Walk through a small binary tree (e.g., 3 nodes) to demonstrate how the `maxi` function works, how `max` is calculated, and when `count` is incremented.
*   **Discuss Space Complexity:** Be prepared to explain the O(H) space complexity and how it relates to tree balance.

## Revision Checklist
- [ ] Understand the definition of a "dominant node" in this context.
- [ ] Recognize the need for subtree maximums.
- [ ] Implement a post-order traversal.
- [ ] Correctly handle base cases (null nodes).
- [ ] Aggregate results (maximum value and count) across recursive calls.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Path Sum in a Binary Tree
*   Diameter of Binary Tree
*   Lowest Common Ancestor of a Binary Tree
*   Binary Tree Maximum Path Sum

## Tags
`Tree` `Depth-First Search` `Binary Tree`
