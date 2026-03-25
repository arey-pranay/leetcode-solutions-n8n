# Populating Next Right Pointers In Each Node Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(W)

---

## Solution (java)

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int sz = q.size();
            Node prev = null;
            Node curr = null;
            for(int i=0;i<sz;i++){
                curr = q.poll();
                if(i>0) prev.next = curr;
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
                prev = curr;
            }
            curr.next = null;
        }
        return root;
    }
}
```

---

---
## Quick Revision
Connect nodes at the same level in a binary tree using the `next` pointer.
This is solved using a Level Order Traversal (BFS) with a queue.

## Intuition
The problem asks us to link nodes at the same level. A natural way to process nodes level by level is Breadth-First Search (BFS). During BFS, we process all nodes at the current level before moving to the next. If we can keep track of the nodes at the current level and link them sequentially as we process them, we can solve this. The key insight is that within a single level, the order in which we process nodes (left to right) is exactly the order we want to set the `next` pointers.

## Algorithm
1. Handle the edge case: If the root is null, return null.
2. Initialize a queue and add the root node to it.
3. While the queue is not empty:
    a. Get the size of the queue. This represents the number of nodes at the current level.
    b. Initialize `prev` to null. This will store the previously processed node at the current level.
    c. Iterate `sz` times (for each node at the current level):
        i. Dequeue a node and assign it to `curr`.
        ii. If `prev` is not null (meaning this is not the first node of the level), set `prev.next = curr`.
        iii. If `curr` has a left child, enqueue it.
        iv. If `curr` has a right child, enqueue it.
        v. Update `prev = curr` for the next iteration.
    d. After the loop, `curr` will be the last node of the current level. Set `curr.next = null` to properly terminate the `next` pointers for this level.
4. Return the root node.

## Concept to Remember
*   **Breadth-First Search (BFS):** Essential for level-order traversal of trees and graphs.
*   **Queue Data Structure:** The backbone of BFS, used to manage nodes to visit.
*   **Tree Traversal:** Understanding different ways to visit nodes in a tree (level-order, pre-order, in-order, post-order).
*   **Pointer Manipulation:** Efficiently updating `next` pointers to establish connections.

## Common Mistakes
*   **Not handling the last node of a level:** Forgetting to set `curr.next = null` for the rightmost node of each level can lead to incorrect `next` pointers.
*   **Incorrectly managing `prev`:** If `prev` is not updated correctly or initialized properly, the `next` pointers will not be set to the correct nodes.
*   **Off-by-one errors in the inner loop:** Miscalculating the number of nodes to process at each level.
*   **Not handling null children:** Failing to check if `left` or `right` children exist before enqueuing them.

## Complexity Analysis
*   **Time:** O(N) - Each node is enqueued and dequeued exactly once. We visit each node and perform constant time operations (enqueue, dequeue, pointer assignment).
*   **Space:** O(W), where W is the maximum width of the tree. In the worst case (a complete binary tree), W can be N/2, so it's O(N). This is due to the queue storing nodes at the widest level.

## Commented Code
```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        // Handle the edge case where the tree is empty.
        if(root == null) return null;
        
        // Initialize a queue for Breadth-First Search (BFS).
        Queue<Node> q = new LinkedList<>();
        // Add the root node to the queue to start the traversal.
        q.offer(root);
        
        // Continue as long as there are nodes to process in the queue.
        while(!q.isEmpty()){
            // Get the number of nodes at the current level. This is crucial for processing level by level.
            int sz = q.size();
            // 'prev' will store the previously processed node at the current level to set its 'next' pointer.
            Node prev = null;
            // 'curr' will store the node currently being processed.
            Node curr = null;
            
            // Iterate through all nodes at the current level.
            for(int i=0;i<sz;i++){
                // Dequeue the next node from the queue.
                curr = q.poll();
                
                // If 'prev' is not null, it means this is not the first node of the level.
                // So, set the 'next' pointer of the 'prev' node to the 'curr' node.
                if(i>0) prev.next = curr;
                
                // If the current node has a left child, enqueue it for processing in the next level.
                if(curr.left != null) q.offer(curr.left);
                // If the current node has a right child, enqueue it for processing in the next level.
                if(curr.right != null) q.offer(curr.right);
                
                // Update 'prev' to the current node for the next iteration.
                prev = curr;
            }
            // After processing all nodes at the current level, 'curr' will be the last node.
            // Set its 'next' pointer to null to correctly terminate the list for this level.
            // This line is crucial for the problem's requirement.
            curr.next = null; 
        }
        // Return the root of the modified tree.
        return root;
    }
}
```

## Interview Tips
*   **Explain BFS clearly:** Articulate why BFS is a good fit for level-order problems.
*   **Trace with an example:** Walk through a small tree (e.g., 3-4 nodes) to show how the queue and `prev` pointer work.
*   **Discuss edge cases:** Mention handling an empty tree (`root == null`).
*   **Clarify the `next` pointer logic:** Emphasize how `prev.next = curr` connects nodes and how `curr.next = null` terminates the level.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement BFS using a queue.
- [ ] Correctly manage the `prev` pointer for linking nodes.
- [ ] Handle the termination of `next` pointers for the last node of each level.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Populating Next Right Pointers In Each Node (LeetCode 116)
*   Binary Tree Level Order Traversal (LeetCode 102)
*   Binary Tree Zigzag Level Order Traversal (LeetCode 103)

## Tags
`Tree` `Breadth-First Search` `Queue` `Binary Tree`

## My Notes
level order.
