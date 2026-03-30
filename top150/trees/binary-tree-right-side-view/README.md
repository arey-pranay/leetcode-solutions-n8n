# Binary Tree Right Side View

**Difficulty:** Medium  
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        if(root==null) return ans;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
                if(i==sz-1) ans.add(curr.val);
            }
        }
        
        return ans;
    }
}
```

---

---
## Quick Revision
Given a binary tree, return the values of the nodes visible from the right side.
This can be solved using Level Order Traversal (BFS) and picking the last node of each level.

## Intuition
When we look at a binary tree from the right side, we are essentially seeing the rightmost node at each level. A Level Order Traversal (BFS) naturally processes nodes level by level. If we can identify the *last* node processed at each level, that node will be the one visible from the right.

## Algorithm
1. Initialize an empty list `ans` to store the result.
2. If the `root` is null, return `ans`.
3. Initialize a queue `q` and add the `root` to it.
4. While the queue is not empty:
    a. Get the current size of the queue, `sz`. This represents the number of nodes at the current level.
    b. Iterate `sz` times:
        i. Dequeue a node `curr` from the queue.
        ii. If `curr` has a left child, enqueue it.
        iii. If `curr` has a right child, enqueue it.
        iv. If this is the last node of the current level (i.e., `i == sz - 1`), add `curr.val` to the `ans` list.
5. Return `ans`.

## Concept to Remember
*   **Breadth-First Search (BFS) / Level Order Traversal:** Essential for processing trees level by level.
*   **Queue Data Structure:** The backbone of BFS, used to maintain the order of nodes to visit.
*   **Tree Traversal:** Understanding how to systematically visit all nodes in a tree.

## Common Mistakes
*   **Incorrectly identifying the rightmost node:** Not realizing that the last node processed in a BFS level is the rightmost one.
*   **Handling null root:** Forgetting to check if the input tree is empty.
*   **Off-by-one errors in loop conditions:** Miscalculating the last element of a level.
*   **Not adding children to the queue correctly:** Missing nodes or adding them in the wrong order.

## Complexity Analysis
*   **Time:** O(N) - where N is the number of nodes in the tree. Each node is visited and processed exactly once.
*   **Space:** O(W) - where W is the maximum width of the tree. In the worst case (a complete binary tree), this can be O(N/2) which simplifies to O(N). This is due to the queue storing nodes at the widest level.

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
    public List<Integer> rightSideView(TreeNode root) {
        // Initialize a list to store the values of nodes visible from the right side.
        List<Integer> ans = new ArrayList<>();
        // Initialize a queue for Breadth-First Search (BFS).
        Queue<TreeNode> q = new LinkedList<>();
        // Add the root node to the queue to start the traversal.
        q.add(root);
        // If the root is null, the tree is empty, so return the empty result list.
        if(root==null) return ans;
        // Continue the BFS as long as there are nodes in the queue.
        while(!q.isEmpty()){
            // Get the number of nodes at the current level. This is crucial for identifying the last node of the level.
            int sz = q.size();
            // Iterate through all nodes at the current level.
            for(int i=0;i<sz;i++){
                // Dequeue the current node to process it.
                TreeNode curr = q.poll();
                // If the current node has a left child, add it to the queue for the next level.
                if(curr.left != null) q.add(curr.left);
                // If the current node has a right child, add it to the queue for the next level.
                if(curr.right != null) q.add(curr.right);
                // If this is the last node processed at the current level (i.e., the rightmost node), add its value to the result list.
                if(i==sz-1) ans.add(curr.val);
            }
        }
        // Return the list containing the right side view of the tree.
        return ans;
    }
}
```

## Interview Tips
*   **Explain BFS first:** Clearly articulate why BFS is suitable for level-based problems.
*   **Walk through an example:** Use a small tree to demonstrate how the queue and `sz` variable work to identify the rightmost node at each level.
*   **Consider edge cases:** Discuss what happens with an empty tree, a single-node tree, or a skewed tree.
*   **Ask clarifying questions:** If unsure about the definition of "right side view" or constraints, ask.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Recall BFS and its implementation using a queue.
- [ ] Identify how to track nodes at the current level in BFS.
- [ ] Implement the logic to pick the last node of each level.
- [ ] Handle the null root case.
- [ ] Analyze time and space complexity.

## Similar Problems
*   102. Binary Tree Level Order Traversal
*   103. Binary Tree Zigzag Level Order Traversal
*   199. Binary Tree Right Side View (This problem)
*   513. Find Bottom Left Tree Value

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`

## My Notes
classic
