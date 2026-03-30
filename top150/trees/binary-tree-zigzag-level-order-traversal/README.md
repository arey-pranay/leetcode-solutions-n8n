# Binary Tree Zigzag Level Order Traversal

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Breadth-First Search` `Binary Tree`  
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>>  ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null) return ans;
        q.add(root);
        boolean flag = false;
        while(!q.isEmpty()){
            List<Integer> arr = new ArrayList<>();
            int sz = q.size();
            for(int i =0; i<sz;i++){
                TreeNode curr = q.poll();
                arr.add(curr.val);
                if(curr.left != null)q.add(curr.left);
                if(curr.right != null)q.add(curr.right);
            }
            if(flag) Collections.reverse(arr);
            ans.add(arr);
            flag = !flag;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
This problem asks for a level order traversal of a binary tree, but with alternating directions for each level.
We can solve this using a Breadth-First Search (BFS) approach, reversing the order of nodes at odd-numbered levels.

## Intuition
The standard level order traversal (BFS) visits nodes level by level from left to right. The "zigzag" requirement means we need to alternate this direction. For the first level, it's left-to-right. For the second, it should be right-to-left. For the third, left-to-right again, and so on.

The core idea is to perform a BFS. For each level, we collect all the nodes. If the level number is even (starting from 0), we add the nodes to our result list in the order they were processed (left-to-right). If the level number is odd, we reverse the list of nodes collected for that level before adding it to the result. A boolean flag can easily track whether to reverse or not.

## Algorithm
1. Initialize an empty list of lists `ans` to store the zigzag traversal result.
2. If the `root` is null, return `ans`.
3. Initialize a queue `q` and add the `root` node to it.
4. Initialize a boolean `flag` to `false`. This flag will determine the direction of traversal for the current level. `false` means left-to-right, `true` means right-to-left.
5. While the queue `q` is not empty:
    a. Initialize an empty list `arr` to store the values of nodes at the current level.
    b. Get the current size of the queue, `sz`. This represents the number of nodes at the current level.
    c. Iterate `sz` times:
        i. Dequeue a node `curr` from `q`.
        ii. Add `curr.val` to the `arr` list.
        iii. If `curr.left` is not null, enqueue `curr.left`.
        iv. If `curr.right` is not null, enqueue `curr.right`.
    d. If `flag` is `true` (meaning we need to reverse for this level), reverse the `arr` list.
    e. Add the `arr` list to the `ans` list.
    f. Toggle the `flag` for the next level: `flag = !flag`.
6. Return `ans`.

## Concept to Remember
*   **Breadth-First Search (BFS):** Essential for level-order traversal, systematically exploring nodes level by level.
*   **Queue Data Structure:** The backbone of BFS, used to manage nodes to be visited.
*   **List Manipulation:** Efficiently adding elements and reversing lists is crucial for the zigzag pattern.
*   **State Management:** Using a flag to track the traversal direction for alternating levels.

## Common Mistakes
*   **Incorrect Flag Toggling:** Forgetting to toggle the `flag` after processing each level, leading to a consistent left-to-right or right-to-left traversal.
*   **Modifying Queue While Iterating:** Trying to add children to the queue while iterating through the *entire* queue instead of just the nodes for the current level. The `sz` variable correctly handles this by capturing the level's size upfront.
*   **Reversing the `ans` list instead of the level list:** The reversal should apply to the `arr` (current level's values), not the final `ans` list.
*   **Off-by-one errors in level numbering:** If using a level counter, ensure it starts correctly and alternates the reversal logic. The boolean flag approach avoids this.

## Complexity Analysis
*   **Time:** O(N) - Each node is visited and processed exactly once. Adding to a list and reversing a list of size K takes O(K) time. Summing this over all levels gives O(N).
*   **Space:** O(W) - where W is the maximum width of the binary tree. In the worst case (a complete binary tree), W can be N/2, so it's O(N). This space is used by the queue and the temporary list `arr` to store nodes/values at each level.

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Initialize the main list to store the result of zigzag traversal.
        List<List<Integer>> ans = new ArrayList<>();
        // Initialize a queue for Breadth-First Search (BFS).
        Queue<TreeNode> q = new LinkedList<>();

        // If the root is null, the tree is empty, so return the empty result list.
        if(root==null) return ans;

        // Add the root node to the queue to start the BFS.
        q.add(root);

        // Initialize a boolean flag to control the direction of traversal for each level.
        // 'false' means left-to-right, 'true' means right-to-left.
        boolean flag = false;

        // Continue the BFS as long as there are nodes in the queue.
        while(!q.isEmpty()){
            // Initialize a list to store the values of nodes at the current level.
            List<Integer> arr = new ArrayList<>();
            // Get the number of nodes at the current level. This is crucial to process one level at a time.
            int sz = q.size();

            // Iterate through all nodes at the current level.
            for(int i =0; i<sz;i++){
                // Dequeue the next node from the front of the queue.
                TreeNode curr = q.poll();
                // Add the value of the current node to the list for this level.
                arr.add(curr.val);

                // If the current node has a left child, add it to the queue for the next level.
                if(curr.left != null)q.add(curr.left);
                // If the current node has a right child, add it to the queue for the next level.
                if(curr.right != null)q.add(curr.right);
            }

            // If the flag is true, it means this level should be traversed from right to left.
            // So, reverse the list of values collected for this level.
            if(flag) Collections.reverse(arr);

            // Add the list of values for the current level (potentially reversed) to the main result list.
            ans.add(arr);
            // Toggle the flag for the next level. If it was false, it becomes true, and vice versa.
            flag = !flag;
        }
        // Return the final list of lists representing the zigzag level order traversal.
        return ans;
    }
}
```

## Interview Tips
*   **Explain BFS First:** Start by explaining the standard BFS approach for level order traversal and then introduce the modification for zigzag.
*   **Clarify Level Processing:** Emphasize how you process nodes level by level using the `sz` variable to avoid mixing nodes from different levels.
*   **Justify Reversal:** Clearly explain why and when you reverse the list for a given level, linking it to the `flag` variable.
*   **Edge Cases:** Be prepared to discuss the `root == null` case and how your code handles it.

## Revision Checklist
- [ ] Understand the problem statement: Binary Tree Zigzag Level Order Traversal.
- [ ] Recall BFS algorithm for level order traversal.
- [ ] Identify the need for alternating traversal direction.
- [ ] Implement queue-based BFS.
- [ ] Use a flag to manage traversal direction.
- [ ] Correctly capture the size of the current level.
- [ ] Add children to the queue for the next level.
- [ ] Reverse the list for odd-numbered levels (or based on flag).
- [ ] Handle the `root == null` edge case.
- [ ] Analyze Time and Space Complexity.

## Similar Problems
*   Binary Tree Level Order Traversal
*   Average of Levels in Binary Tree
*   N-ary Tree Level Order Traversal

## Tags
`Tree` `Breadth-First Search` `Queue` `Binary Tree` `List`
