# Count Nodes Equal To Average Of Subtree

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
    int ans=0;
    public int averageOfSubtree(TreeNode root) {
        func(root);
        return ans;
    }
    public int[] func(TreeNode root){
        if(root==null) return new int[]{0,0};
        int[] l = func(root.left);
        int[] r = func(root.right);
        int sum = l[0]+r[0]+root.val;
        int num = l[1]+r[1]+1;
        int avg = sum/num;
        if(root.val==avg)ans++;
        return new int[]{sum,num};
    }
  
}
```

---

---
## Quick Revision
Given a binary tree, count nodes whose value equals the average of all nodes in their subtree.
This is solved using a post-order traversal with a helper function returning subtree sum and count.

## Intuition
To determine if a node's value equals its subtree's average, we need two pieces of information for each subtree: the sum of its node values and the count of its nodes. A post-order traversal is ideal because it processes children before the parent. This allows us to aggregate the sum and count from the left and right subtrees, combine them with the current node's value, calculate the average, and then check the condition.

## Algorithm
1. Initialize a global counter `ans` to 0. This will store the final count of nodes satisfying the condition.
2. Define a recursive helper function `func(TreeNode node)` that returns an integer array of size 2: `[sum_of_subtree, count_of_nodes_in_subtree]`.
3. **Base Case:** If `node` is null, return `[0, 0]` (empty subtree has sum 0 and count 0).
4. **Recursive Step:**
    a. Recursively call `func` on the left child: `int[] left_result = func(node.left)`.
    b. Recursively call `func` on the right child: `int[] right_result = func(node.right)`.
    c. Calculate the sum of the current subtree: `current_sum = left_result[0] + right_result[0] + node.val`.
    d. Calculate the count of nodes in the current subtree: `current_count = left_result[1] + right_result[1] + 1`.
    e. Calculate the average of the current subtree: `current_avg = current_sum / current_count`.
    f. If `node.val == current_avg`, increment the global counter `ans`.
    g. Return `[current_sum, current_count]` to the parent call.
5. In the `averageOfSubtree` method, call `func(root)` to initiate the traversal and return the final `ans`.

## Concept to Remember
*   **Tree Traversal (Post-order):** Essential for aggregating information from children before processing the parent.
*   **Recursion:** Naturally fits tree structures and allows for elegant solutions to problems involving subproblems.
*   **Helper Functions with Return Values:** Useful for passing aggregated data up the recursion tree.
*   **Integer Division:** Understanding how integer division truncates remainders is important for average calculation.

## Common Mistakes
*   **Incorrect Base Case:** Not handling the `null` node correctly, leading to errors.
*   **Incorrect Aggregation:** Miscalculating the sum or count by forgetting to include the current node's value or count.
*   **Integer Overflow:** For very large trees, the sum of node values might exceed the integer limit (though less likely in typical LeetCode constraints).
*   **Modifying Global State Incorrectly:** Not properly updating the `ans` counter within the recursive function.
*   **Returning Incorrect Data:** Returning the wrong values from the helper function, breaking the aggregation logic.

## Complexity Analysis
- Time: O(N) - reason: Each node in the tree is visited exactly once by the post-order traversal.
- Space: O(H) - reason: The space complexity is determined by the depth of the recursion stack, which is proportional to the height (H) of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

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
    // Global variable to store the final count of nodes whose value equals their subtree average.
    int ans = 0;

    // Main function to initiate the process and return the final answer.
    public int averageOfSubtree(TreeNode root) {
        // Call the recursive helper function starting from the root.
        func(root);
        // Return the accumulated count.
        return ans;
    }

    // Recursive helper function that performs a post-order traversal.
    // It returns an array: [sum of nodes in subtree, count of nodes in subtree].
    public int[] func(TreeNode root) {
        // Base case: If the current node is null, it represents an empty subtree.
        // An empty subtree has a sum of 0 and a count of 0.
        if (root == null) {
            return new int[]{0, 0};
        }

        // Recursively call func on the left child to get its subtree sum and count.
        int[] l = func(root.left);
        // Recursively call func on the right child to get its subtree sum and count.
        int[] r = func(root.right);

        // Calculate the total sum of the current subtree by adding the sums from left and right subtrees,
        // and the value of the current node.
        int sum = l[0] + r[0] + root.val;
        // Calculate the total count of nodes in the current subtree by adding the counts from left and right subtrees,
        // and 1 for the current node itself.
        int num = l[1] + r[1] + 1;

        // Calculate the average of the current subtree using integer division.
        int avg = sum / num;

        // Check if the current node's value is equal to the calculated average of its subtree.
        if (root.val == avg) {
            // If they are equal, increment the global answer counter.
            ans++;
        }

        // Return the calculated sum and count for the current subtree to its parent.
        return new int[]{sum, num};
    }
}
```

## Interview Tips
*   **Explain the Post-order Traversal:** Clearly articulate why a post-order traversal is necessary to gather subtree information before making decisions about the current node.
*   **Data Structure for Return Value:** Discuss the choice of returning an array `[sum, count]` and why it's efficient for passing up aggregated data.
*   **Edge Cases:** Be prepared to discuss the base case (null node) and how it's handled.
*   **Clarity on Average Calculation:** Emphasize the use of integer division and its implications.
*   **State Management:** Explain how the global `ans` variable is used to accumulate the result across recursive calls.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the need for subtree sum and count.
- [ ] Choose post-order traversal as the appropriate strategy.
- [ ] Implement the recursive helper function correctly.
- [ ] Handle the base case (null node).
- [ ] Correctly aggregate sum and count from children.
- [ ] Calculate subtree average using integer division.
- [ ] Increment the result counter when the condition is met.
- [ ] Analyze time and space complexity.
- [ ] Consider potential edge cases and common mistakes.

## Similar Problems
*   Maximum Path Sum
*   Diameter of Binary Tree
*   Binary Tree Level Order Traversal
*   Sum of Nodes with Even-Valued Grandparent

## Tags
`Tree` `Depth-First Search` `Binary Tree` `Recursion`
