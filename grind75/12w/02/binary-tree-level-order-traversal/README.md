# Binary Tree Level Order Traversal

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Breadth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(W)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outer = new ArrayList<>();
        if(root==null) return outer;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> inner = new ArrayList<>();
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
                inner.add(curr.val);
            }
            outer.add(inner);
        }
        return outer;
    }
}
```

---

---
## Quick Revision
Traverse a binary tree level by level, from left to right.
Use a Breadth-First Search (BFS) approach with a queue.

## Intuition
The core idea is to visit nodes in a breadth-first manner. A queue is the natural data structure for BFS because it processes elements in a First-In, First-Out (FIFO) order. To achieve level-by-level traversal, we need to process all nodes at the current level before moving to the next. This can be done by keeping track of the number of nodes at the current level and processing exactly that many nodes from the queue in each iteration of the main loop.

## Algorithm
1. Initialize an empty list of lists, `outer`, to store the result.
2. If the `root` is null, return `outer`.
3. Initialize a queue, `q`, and add the `root` node to it.
4. While the queue is not empty:
    a. Initialize an empty list, `inner`, to store the values of the current level.
    b. Get the current size of the queue, `sz`. This represents the number of nodes at the current level.
    c. Loop `sz` times:
        i. Dequeue a node, `curr`, from the queue.
        ii. If `curr` has a left child, enqueue it.
        iii. If `curr` has a right child, enqueue it.
        iv. Add the value of `curr` to the `inner` list.
    d. Add the `inner` list to the `outer` list.
5. Return `outer`.

## Concept to Remember
*   **Breadth-First Search (BFS):** A graph traversal algorithm that explores neighbor nodes first before moving to the next level neighbors.
*   **Queue Data Structure:** Essential for BFS, maintaining the order of nodes to visit.
*   **Tree Traversal:** Understanding different ways to visit all nodes in a tree (pre-order, in-order, post-order, level-order).
*   **Level-by-Level Processing:** The technique of processing all nodes at a specific depth before moving to the next depth.

## Common Mistakes
*   Not handling the `root == null` case, leading to a NullPointerException.
*   Incorrectly managing the queue, e.g., adding children before processing all nodes at the current level.
*   Failing to capture the size of the current level before processing it, leading to mixing nodes from different levels.
*   Not creating a new `inner` list for each level, overwriting previous level data.

## Complexity Analysis
*   **Time:** O(N) - where N is the number of nodes in the tree. Each node is enqueued and dequeued exactly once, and its value is added to a list once.
*   **Space:** O(W) - where W is the maximum width of the tree. In the worst case (a complete binary tree), the queue can hold up to N/2 nodes at the widest level. This is often simplified to O(N) in the worst case for a skewed tree where the queue might hold almost all nodes.

## Commented Code
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Initialize the list to store the final result (list of lists of integers)
        List<List<Integer>> outer = new ArrayList<>();
        // If the root is null, the tree is empty, so return an empty result list
        if(root==null) return outer;
        // Initialize a queue to perform Breadth-First Search (BFS)
        Queue<TreeNode> q = new LinkedList<>();
        // Add the root node to the queue to start the traversal
        q.add(root);
        // Continue the loop as long as there are nodes in the queue to process
        while(!q.isEmpty()){
            // Initialize a list to store the values of nodes at the current level
            List<Integer> inner = new ArrayList<>();
            // Get the number of nodes currently in the queue. This is crucial for processing one level at a time.
            int sz = q.size();
            // Iterate through all nodes at the current level
            for(int i=0;i<sz;i++){
                // Dequeue the next node from the front of the queue
                TreeNode curr = q.poll();
                // If the current node has a left child, add it to the queue for processing in the next level
                if(curr.left != null) q.add(curr.left);
                // If the current node has a right child, add it to the queue for processing in the next level
                if(curr.right != null) q.add(curr.right);
                // Add the value of the current node to the list for the current level
                inner.add(curr.val);
            }
            // After processing all nodes at the current level, add the list of their values to the main result list
            outer.add(inner);
        }
        // Return the complete list of lists representing the level order traversal
        return outer;
    }
}
```

## Interview Tips
*   Clearly explain your BFS approach and why a queue is suitable.
*   Emphasize the importance of processing nodes level by level by capturing the queue's size at the start of each level's iteration.
*   Walk through a small example tree to demonstrate how the queue and the `outer` and `inner` lists are populated.
*   Be prepared to discuss time and space complexity, especially the nuance of space complexity related to the tree's width.

## Revision Checklist
- [ ] Understand the problem: Binary Tree Level Order Traversal.
- [ ] Recall BFS algorithm and its application to trees.
- [ ] Implement queue-based BFS.
- [ ] Correctly handle the `root == null` edge case.
- [ ] Ensure level-by-level processing using queue size.
- [ ] Analyze Time and Space Complexity.
- [ ] Practice explaining the solution and its trade-offs.

## Similar Problems
*   Binary Tree Zigzag Level Order Traversal
*   Binary Tree Right Side View
*   Average of Levels in Binary Tree
*   N-ary Tree Level Order Traversal

## Tags
`Tree` `Breadth-First Search` `Queue` `Binary Tree`
