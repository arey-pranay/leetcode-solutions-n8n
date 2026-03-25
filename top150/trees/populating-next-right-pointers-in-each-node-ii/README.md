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
This problem asks to connect nodes at the same level in a binary tree using their `next` pointer.
We solve this using a Level Order Traversal (BFS) and keeping track of the previous node at each level.

## Intuition
The core idea is to process the tree level by level. For each level, we need to iterate through all nodes at that level and link them sequentially. A Breadth-First Search (BFS) naturally processes nodes level by level. During the BFS, if we can identify the start and end of each level, and maintain a pointer to the previously visited node within that level, we can establish the `next` connections. The "aha moment" is realizing that a standard BFS queue, combined with knowing the size of the current level, allows us to precisely iterate through all nodes of a single level before moving to the next.

## Algorithm
1. Handle the edge case: If the `root` is `null`, return `null`.
2. Initialize a `Queue` (e.g., `LinkedList`) to perform BFS and add the `root` to it.
3. Start a `while` loop that continues as long as the `Queue` is not empty.
4. Inside the `while` loop, get the `size` of the `Queue`. This `size` represents the number of nodes at the current level.
5. Initialize a `prev` node pointer to `null`. This will store the previously processed node at the current level.
6. Start a `for` loop that iterates `size` times (once for each node at the current level).
7. In each iteration of the `for` loop:
    a. Dequeue a `Node` from the `Queue` and assign it to `curr`.
    b. If `prev` is not `null` (meaning this is not the first node of the level), set `prev.next = curr`.
    c. If `curr` has a `left` child, enqueue it.
    d. If `curr` has a `right` child, enqueue it.
    e. Update `prev` to `curr` for the next iteration.
8. After the `for` loop finishes (all nodes of the current level have been processed), set `curr.next = null`. This ensures the last node of the level correctly points to `null`.
9. After the `while` loop finishes, return the `root`.

## Concept to Remember
*   **Breadth-First Search (BFS):** Essential for level-order traversal.
*   **Queue Data Structure:** The backbone of BFS, used to store nodes to visit.
*   **Level-Order Processing:** Iterating through all nodes at a specific depth before moving to the next.
*   **Pointer Manipulation:** Carefully updating `next` pointers to establish connections.

## Common Mistakes
*   **Not handling the `null` root case:** Leads to `NullPointerException`.
*   **Incorrectly identifying level boundaries:** Not using the `size` of the queue at the start of each level iteration can lead to mixing nodes from different levels.
*   **Forgetting to set the `next` pointer of the last node in a level to `null`:** This can create incorrect connections if the tree is not complete.
*   **Modifying the queue while iterating based on its initial size:** This can lead to missing nodes or processing them multiple times.
*   **Not initializing `prev` correctly for each level:** `prev` must be reset to `null` at the beginning of processing each new level.

## Complexity Analysis
*   **Time:** O(N) - reason: Each node is visited and processed exactly once during the BFS traversal.
*   **Space:** O(W) - reason: Where W is the maximum width of the tree. In the worst case (a complete binary tree), W can be N/2, so it's O(N). This is due to the queue storing nodes at the widest level.

## Commented Code
```java
/*
// Definition for a Node.
class Node {
    public int val; // The value of the node.
    public Node left; // Pointer to the left child.
    public Node right; // Pointer to the right child.
    public Node next; // Pointer to the next node at the same level.

    public Node() {} // Default constructor.
    
    public Node(int _val) { // Constructor with value.
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) { // Full constructor.
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        // If the root is null, there's nothing to connect, so return null.
        if(root == null) return null;
        
        // Initialize a queue for Breadth-First Search (BFS).
        Queue<Node> q = new LinkedList<>();
        // Add the root node to the queue to start the traversal.
        q.offer(root);
        
        // Continue the BFS as long as there are nodes in the queue.
        while(!q.isEmpty()){
            // Get the number of nodes at the current level. This is crucial for processing level by level.
            int sz = q.size();
            // Initialize 'prev' to null. This will point to the previously processed node at the current level.
            Node prev = null;
            // Initialize 'curr' to null. This will hold the current node being processed.
            Node curr = null;
            
            // Iterate through all nodes at the current level.
            for(int i=0;i<sz;i++){
                // Dequeue the next node from the queue.
                curr = q.poll();
                
                // If 'prev' is not null, it means this is not the first node of the level.
                // Connect the 'next' pointer of the 'prev' node to the 'curr' node.
                if(i>0) prev.next = curr;
                
                // If the current node has a left child, enqueue it for processing in the next level.
                if(curr.left != null) q.offer(curr.left);
                // If the current node has a right child, enqueue it for processing in the next level.
                if(curr.right != null) q.offer(curr.right);
                
                // Update 'prev' to the current node, so it can be used to connect to the next node in the next iteration.
                prev = curr;
            }
            // After processing all nodes at the current level, the 'curr' variable holds the last node of this level.
            // Set its 'next' pointer to null, as it's the end of the line for this level.
            curr.next = null;
        }
        // Return the root of the modified tree.
        return root;
    }
}
```

## Interview Tips
*   **Explain BFS clearly:** Articulate how BFS naturally processes level by level and why this is suitable for the problem.
*   **Trace with an example:** Walk through a small binary tree (e.g., 3-5 nodes) to demonstrate how the queue, `sz`, `prev`, and `curr` variables change.
*   **Discuss space optimization (if applicable):** For "Populating Next Right Pointers In Each Node I" (a perfect binary tree), you can use O(1) space. Mention that this problem (general binary tree) requires O(N) space for the queue in the worst case.
*   **Clarify edge cases:** Explicitly mention handling the `null` root and the last node of each level.

## Revision Checklist
- [ ] Understand the problem statement and the `Node` structure.
- [ ] Recall BFS and its implementation using a queue.
- [ ] Practice identifying level boundaries using `queue.size()`.
- [ ] Implement the logic to connect `prev.next = curr`.
- [ ] Remember to set `curr.next = null` for the last node of each level.
- [ ] Analyze time and space complexity.
- [ ] Trace the algorithm with a sample tree.

## Similar Problems
*   Populating Next Right Pointers In Each Node (LeetCode 116)
*   Binary Tree Level Order Traversal (LeetCode 102)
*   Binary Tree Zigzag Level Order Traversal (LeetCode 103)
*   Average of Levels in Binary Tree (LeetCode 637)

## Tags
`Tree` `Breadth-First Search` `Linked List` `Binary Tree`
