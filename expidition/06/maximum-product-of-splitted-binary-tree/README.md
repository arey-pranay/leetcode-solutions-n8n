# Maximum Product Of Splitted Binary Tree

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
    long max = 0;
    long totalSum = 0;
    int MOD = 1000000007;
    public int maxProduct(TreeNode root) {
        // get sum of full tree
        // then to get sum of any 2 subtrees, you need sum of only 1, because s2 = total-s1
        // for every node, check ans = Math.max(ans,(s1 * (total-s1))
        totalSum = sumOfTree(root,0);
        pre(root);
        return (int)(max%MOD);
    }
    public long pre(TreeNode root){
        if(root==null) return 0;
        long a = pre(root.left);
        long b = pre(root.right);
        long currSum = a + b + root.val;
        max = Math.max(max, (currSum * (totalSum-currSum)));
        return currSum;
    }
    public int sumOfTree(TreeNode root, int sum){
        if(root==null) return sum;
        return sumOfTree(root.left,sum)+sumOfTree(root.right,sum)+root.val;
    }
    
}
```

---

---
## Quick Revision
Given a binary tree, split it into two subtrees by removing one edge. Find the maximum product of the sums of the two resulting subtrees.
This is solved by calculating the total sum of the tree, then for each node, calculating the sum of its subtree and finding the product with the remaining sum.

## Intuition
The problem asks us to split the tree into two by removing one edge. This means we'll have two separate trees. The sum of values in these two trees will always add up to the total sum of the original tree. If we know the sum of one subtree (let's call it `sum1`), the sum of the other subtree will be `totalSum - sum1`. Our goal is to maximize `sum1 * (totalSum - sum1)`.

The key insight is that any subtree formed by removing an edge from the original tree will be a valid subtree. We can find the sum of *every* possible subtree. A post-order traversal is perfect for this because it calculates the sum of a node's subtree only after it has calculated the sums of its left and right children's subtrees.

## Algorithm
1.  **Calculate the Total Sum:** Perform a traversal (e.g., DFS) of the entire binary tree to compute the sum of all node values. Store this as `totalSum`.
2.  **Calculate Subtree Sums and Max Product:** Perform another traversal (e.g., DFS, specifically post-order) starting from the root.
    *   For each node, recursively calculate the sum of its left subtree (`leftSum`) and its right subtree (`rightSum`).
    *   The sum of the current node's subtree (`currentSubtreeSum`) is `leftSum + rightSum + node.val`.
    *   At this point, `currentSubtreeSum` represents the sum of one of the two possible subtrees if we were to cut the edge connecting this node to its parent. The sum of the other subtree would be `totalSum - currentSubtreeSum`.
    *   Calculate the product: `product = currentSubtreeSum * (totalSum - currentSubtreeSum)`.
    *   Update the global maximum product found so far: `maxProduct = max(maxProduct, product)`.
    *   Return `currentSubtreeSum` to the parent call.
3.  **Handle Modulo:** Since the product can be very large, take the result modulo `10^9 + 7` before returning.

## Concept to Remember
*   **Tree Traversal (DFS/Post-order):** Essential for visiting all nodes and calculating subtree sums efficiently. Post-order is particularly useful as it computes child results before the parent.
*   **Sum of Subtrees:** The ability to recursively calculate the sum of values within any subtree.
*   **Maximization Problem:** Identifying the objective function (`sum1 * (totalSum - sum1)`) and iterating through all possibilities to find the maximum.
*   **Modulo Arithmetic:** Handling potential integer overflows by applying the modulo operation at the end.

## Common Mistakes
*   **Integer Overflow:** Not using `long` for sums and products, leading to incorrect results for large trees.
*   **Incorrect Traversal Order:** Using pre-order or in-order traversal might not correctly compute subtree sums before they are needed for the product calculation.
*   **Missing the Total Sum Calculation:** Forgetting to first calculate the total sum of the tree, which is crucial for determining the sum of the "other" subtree.
*   **Not Considering All Split Points:** Failing to realize that cutting any edge results in a valid split, and thus needing to check the product for every possible subtree sum.
*   **Modulo Application:** Applying modulo too early in intermediate calculations, which can lead to incorrect final results. It should be applied to the final maximum product.

## Complexity Analysis
- Time: O(N) - reason: We perform two full traversals of the tree (one to get the total sum, and another to calculate subtree sums and the maximum product). Each node is visited a constant number of times.
- Space: O(H) - reason: This is due to the recursion stack depth, where H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node.
 *     TreeNode left; // Pointer to the left child.
 *     TreeNode right; // Pointer to the right child.
 *     TreeNode() {} // Default constructor.
 *     TreeNode(int val) { this.val = val; } // Constructor with value.
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children.
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
class Solution {
    long max = 0; // Variable to store the maximum product found so far, initialized to 0. Use long to prevent overflow.
    long totalSum = 0; // Variable to store the sum of all nodes in the tree. Use long to prevent overflow.
    int MOD = 1000000007; // The modulo value as specified in the problem.
    
    public int maxProduct(TreeNode root) {
        // First, calculate the total sum of all nodes in the tree.
        // This is needed because if we know the sum of one subtree (s1),
        // the sum of the other subtree will be (totalSum - s1).
        totalSum = sumOfTree(root); // Call helper to get total sum.
        
        // Second, traverse the tree again to calculate the sum of each possible subtree
        // and find the maximum product.
        // The 'pre' function will return the sum of the subtree rooted at 'root'.
        // During this traversal, it also updates the 'max' variable.
        pre(root); 
        
        // The result needs to be returned modulo 10^9 + 7.
        // Cast the final maximum product to int after applying modulo.
        return (int)(max % MOD);
    }
    
    // This is a recursive helper function that performs a post-order traversal.
    // It calculates the sum of the subtree rooted at 'root' and updates the global 'max' product.
    public long pre(TreeNode root){
        // Base case: if the node is null, its subtree sum is 0.
        if(root == null) return 0;
        
        // Recursively calculate the sum of the left subtree.
        long leftSum = pre(root.left);
        // Recursively calculate the sum of the right subtree.
        long rightSum = pre(root.right);
        
        // Calculate the sum of the current subtree (including the current node's value).
        long currentSubtreeSum = leftSum + rightSum + root.val;
        
        // Calculate the product of the current subtree sum and the sum of the remaining part of the tree.
        // The remaining part's sum is totalSum - currentSubtreeSum.
        // Update the global maximum product if the current product is greater.
        max = Math.max(max, (currentSubtreeSum * (totalSum - currentSubtreeSum)));
        
        // Return the sum of the current subtree to its parent.
        return currentSubtreeSum;
    }
    
    // Helper function to calculate the total sum of all nodes in the tree.
    // This is a standard DFS traversal.
    public long sumOfTree(TreeNode root){
        // Base case: if the node is null, it contributes 0 to the sum.
        if(root == null) return 0;
        
        // Recursively sum the left subtree, the right subtree, and add the current node's value.
        return sumOfTree(root.left) + sumOfTree(root.right) + root.val;
    }
}
```

## Interview Tips
1.  **Clarify the Split:** Ensure you understand that removing *one edge* creates two trees. This means any subtree sum can be `sum1`, and the rest of the tree is `totalSum - sum1`.
2.  **Data Types:** Be mindful of potential integer overflows. Use `long` for sums and products to avoid issues, especially with large trees.
3.  **Traversal Strategy:** Explain why a post-order traversal is suitable for calculating subtree sums. It ensures that child sums are computed before the parent's sum, which is necessary for the product calculation.
4.  **Modulo Operation:** Discuss when and how to apply the modulo operation. It should be applied to the final maximum product, not intermediate sums or products, to maintain accuracy.
5.  **Edge Cases:** Consider edge cases like an empty tree or a tree with only one node. The provided solution handles these gracefully.

## Revision Checklist
- [ ] Understand the problem: splitting a tree by removing one edge.
- [ ] Identify the objective: maximize `sum1 * sum2` where `sum1 + sum2 = totalSum`.
- [ ] Implement a function to calculate the total sum of the tree.
- [ ] Implement a recursive function (post-order DFS) to calculate subtree sums.
- [ ] During subtree sum calculation, update the maximum product found.
- [ ] Use `long` for sums and products to prevent overflow.
- [ ] Apply the modulo operation correctly to the final result.
- [ ] Consider edge cases (empty tree, single node tree).

## Similar Problems
*   [Sum of Nodes in a Binary Tree](https://leetcode.com/problems/sum-of-nodes-in-a-binary-tree/) (Simpler version of subtree sum calculation)
*   [Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) (Involves finding a path with max sum, related to subtree sums)
*   [Delete Nodes And Return Forest](https://leetcode.com/problems/delete-nodes-and-return-forest/) (Involves splitting a tree based on conditions)

## Tags
`Tree` `Depth-First Search` `Binary Tree` `Recursion`
