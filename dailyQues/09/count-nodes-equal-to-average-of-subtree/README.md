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
Given a binary tree, count nodes where the node's value equals the average of all node values in its subtree.
This is solved using a post-order traversal (DFS) to calculate subtree sums and counts.

## Intuition
To determine if a node's value equals the average of its subtree, we need two pieces of information for each subtree: the sum of all node values within that subtree and the total number of nodes in that subtree. If we can efficiently compute these for every node, we can then calculate the average and perform the comparison. A recursive approach, specifically a post-order traversal, is ideal because it allows us to process the children's subtrees *before* processing the parent node. This way, when we are at a parent node, we already have the sum and count for its left and right subtrees.

## Algorithm
1. Initialize a global counter `ans` to 0. This will store the final count of nodes satisfying the condition.
2. Define a recursive helper function, let's call it `dfs`, that takes a `TreeNode` as input and returns an integer array of size 2: `[subtree_sum, subtree_node_count]`.
3. **Base Case:** If the current node `root` is `null`, return `[0, 0]` (sum is 0, count is 0).
4. **Recursive Step:**
    a. Recursively call `dfs` on the left child: `int[] left_result = dfs(root.left)`.
    b. Recursively call `dfs` on the right child: `int[] right_result = dfs(root.right)`.
    c. Calculate the sum of the current subtree: `current_sum = left_result[0] + right_result[0] + root.val`.
    d. Calculate the number of nodes in the current subtree: `current_count = left_result[1] + right_result[1] + 1`.
    e. Calculate the average of the current subtree: `average = current_sum / current_count`.
    f. **Check Condition:** If `root.val == average`, increment the global counter `ans`.
    g. **Return:** Return the `[current_sum, current_count]` for the current subtree to be used by its parent.
5. In the main `averageOfSubtree` function, call `dfs(root)` to initiate the traversal and then return the final `ans`.

## Concept to Remember
*   **Tree Traversal (DFS/Post-order):** Essential for processing subtrees before their parents.
*   **Recursion:** Natural fit for tree problems, breaking down the problem into smaller, self-similar subproblems.
*   **State Passing in Recursion:** Returning multiple values (sum and count) from a recursive call to aggregate information upwards.

## Common Mistakes
*   **Incorrect Base Case:** Not handling `null` nodes properly, leading to `NullPointerException` or incorrect calculations.
*   **Integer Division:** Forgetting that `sum / count` in Java performs integer division, which might be intended but needs awareness.
*   **Global vs. Local Variables:** Mismanaging the `ans` counter, either by not making it accessible to all recursive calls or by incorrectly resetting it.
*   **Order of Operations:** Calculating the average *before* summing up the current node's value, or mixing up sum and count.

## Complexity Analysis
- Time: O(N) - reason: Each node in the tree is visited exactly once during the DFS traversal.
- Space: O(H) - reason: Due to the recursion stack. In the worst case (a skewed tree), H can be N. In a balanced tree, H is log N.

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
    // Global variable to store the final count of nodes whose value equals the average of their subtree.
    int ans = 0;

    // Main function to initiate the process and return the final count.
    public int averageOfSubtree(TreeNode root) {
        // Call the recursive helper function to traverse the tree and populate 'ans'.
        func(root);
        // Return the accumulated count.
        return ans;
    }

    // Recursive helper function (DFS) that performs a post-order traversal.
    // It returns an array: [sum of nodes in subtree, count of nodes in subtree].
    public int[] func(TreeNode root) {
        // Base case: If the current node is null, it contributes 0 to sum and 0 to count.
        if (root == null) {
            // Return an array representing an empty subtree.
            return new int[]{0, 0};
        }

        // Recursively call 'func' on the left child to get its subtree sum and count.
        int[] l = func(root.left);
        // Recursively call 'func' on the right child to get its subtree sum and count.
        int[] r = func(root.right);

        // Calculate the total sum of the current subtree: sum from left + sum from right + current node's value.
        int sum = l[0] + r[0] + root.val;
        // Calculate the total number of nodes in the current subtree: count from left + count from right + 1 (for the current node).
        int num = l[1] + r[1] + 1;

        // Calculate the average of the current subtree. Integer division is used here.
        int avg = sum / num;

        // Check if the current node's value is equal to the calculated average of its subtree.
        if (root.val == avg) {
            // If it is, increment the global counter 'ans'.
            ans++;
        }

        // Return the calculated sum and count for the current subtree. This information is passed up to the parent node.
        return new int[]{sum, num};
    }
}
```

## Interview Tips
*   **Explain the Post-order Traversal:** Clearly articulate why post-order traversal is necessary to gather subtree information before processing the parent.
*   **Data Structure for Return Value:** Discuss the choice of returning an array `[sum, count]` and why it's efficient for passing multiple pieces of information up the recursion.
*   **Edge Cases:** Be prepared to discuss the `null` node base case and how it prevents errors.
*   **Clarity on Average Calculation:** Emphasize that `sum / num` uses integer division and confirm if this is the intended behavior based on problem constraints.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the need for subtree sum and count.
- [ ] Implement a recursive DFS (post-order traversal).
- [ ] Handle the base case for null nodes correctly.
- [ ] Aggregate sum and count from children.
- [ ] Calculate the average and perform the comparison.
- [ ] Increment the global counter when the condition is met.
- [ ] Return the correct sum and count from the recursive function.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Path Sum in a Binary Tree
*   Diameter of Binary Tree
*   Binary Tree Maximum Path Sum

## Tags
`Tree` `Depth-First Search` `Recursion` `Binary Tree`
