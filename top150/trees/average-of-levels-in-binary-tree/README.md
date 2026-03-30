# Average Of Levels In Binary Tree

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(W)

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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) return ans;
        q.add(root);
        while(!q.isEmpty()){
            int sz = q.size();
            double sum = 0;
            for(int i =0 ; i<sz;i++){
                TreeNode curr = q.poll();
                sum += curr.val;
                
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            double av = sum /sz;
            ans.add(av);
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given a binary tree, calculate the average value of nodes at each level.
This is solved using a Breadth-First Search (BFS) traversal.

## Intuition
The problem asks for averages *per level*. This immediately suggests a level-order traversal. BFS is the natural algorithm for level-order traversal. During BFS, we process nodes level by level. If we can keep track of all nodes at the current level, sum their values, and count them, we can easily compute the average for that level.

## Algorithm
1. Initialize an empty list `ans` to store the average of each level.
2. If the `root` is null, return the empty `ans` list.
3. Initialize a queue `q` and add the `root` node to it.
4. While the queue is not empty:
    a. Get the current size of the queue, `sz`. This represents the number of nodes at the current level.
    b. Initialize a variable `sum` to 0.0 to store the sum of node values at the current level.
    c. Iterate `sz` times (for each node at the current level):
        i. Dequeue a node `curr` from the queue.
        ii. Add `curr.val` to `sum`.
        iii. If `curr` has a left child, enqueue it.
        iv. If `curr` has a right child, enqueue it.
    d. Calculate the average for the current level: `av = sum / sz`.
    e. Add `av` to the `ans` list.
5. Return the `ans` list.

## Concept to Remember
*   **Breadth-First Search (BFS):** Essential for level-order traversal of trees and graphs.
*   **Queue Data Structure:** The core component of BFS, used to manage nodes to visit.
*   **Level-Order Traversal:** Processing nodes layer by layer from top to bottom.
*   **Handling Empty Tree:** Edge case where the input tree might be null.

## Common Mistakes
*   **Incorrectly calculating `sz`:** Not capturing the queue size *before* processing the current level's nodes, leading to an incorrect count.
*   **Integer division:** Performing `sum / sz` using integer types when `sum` might be large or `sz` is 1, leading to precision loss. Using `double` for `sum` and `av` is crucial.
*   **Not handling null root:** Forgetting to check if the input `root` is null before starting the traversal.
*   **Adding children to queue before processing current level:** This would mix nodes from different levels in the queue.

## Complexity Analysis
*   **Time:** O(N) - Each node in the binary tree is visited and processed exactly once.
*   **Space:** O(W) - Where W is the maximum width of the binary tree. In the worst case (a complete binary tree), W can be N/2, so it's O(N). This is due to the queue storing nodes at the widest level.

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
    public List<Double> averageOfLevels(TreeNode root) {
        // Initialize a list to store the average of each level.
        List<Double> ans = new ArrayList<>();
        // Initialize a queue for Breadth-First Search (BFS).
        Queue<TreeNode> q = new LinkedList<>();

        // If the root is null, the tree is empty, so return an empty list.
        if(root==null) return ans;

        // Add the root node to the queue to start the BFS.
        q.add(root);

        // Continue BFS as long as there are nodes in the queue.
        while(!q.isEmpty()){
            // Get the number of nodes at the current level. This is crucial for processing one level at a time.
            int sz = q.size();
            // Initialize a variable to sum the values of nodes at the current level. Use double for precision.
            double sum = 0;

            // Iterate through all nodes at the current level.
            for(int i = 0 ; i<sz;i++){
                // Dequeue the current node from the front of the queue.
                TreeNode curr = q.poll();
                // Add the value of the current node to the sum.
                sum += curr.val;

                // If the current node has a left child, enqueue it for the next level.
                if(curr.left != null) q.offer(curr.left);
                // If the current node has a right child, enqueue it for the next level.
                if(curr.right != null) q.offer(curr.right);
            }
            // Calculate the average for the current level by dividing the sum by the number of nodes.
            double av = sum /sz;
            // Add the calculated average to the result list.
            ans.add(av);
        }
        // Return the list containing the average of each level.
        return ans;
    }
}
```

## Interview Tips
*   **Explain BFS first:** Clearly articulate why BFS is the right choice for level-based problems.
*   **Emphasize the `sz` trick:** Highlight how capturing `q.size()` *before* the inner loop is key to processing levels distinctly.
*   **Discuss edge cases:** Mention handling the null root and potential for empty levels (though not applicable here due to tree structure).
*   **Clarify data types:** Explain why `double` is used for `sum` and `av` to avoid integer division issues.

## Revision Checklist
- [ ] Understand the problem: Calculate average per level.
- [ ] Identify BFS as the traversal method.
- [ ] Implement queue for BFS.
- [ ] Correctly capture level size (`sz`).
- [ ] Sum node values for the current level.
- [ ] Calculate average using floating-point division.
- [ ] Handle null root edge case.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Binary Tree Level Order Traversal
*   Zigzag Conversion (can be adapted with BFS)
*   N-ary Tree Level Order Traversal

## Tags
`Tree` `Breadth-First Search` `Binary Tree` `Queue`
