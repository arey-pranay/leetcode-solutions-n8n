# Binary Search Tree Iterator

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Stack` `Tree` `Design` `Binary Search Tree` `Binary Tree` `Iterator`  
**Time:**   
**Space:** 

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
class BSTIterator {
    Queue<Integer> inorder = new LinkedList<>();
    public BSTIterator(TreeNode root) {
        func(root);
    }
    
    public int next() {
        return inorder.poll();
    }
    
    public boolean hasNext() {
        return inorder.size() != 0;
    }
    
    public void func(TreeNode root){
        if(root==null) return;
        func(root.left);
        inorder.add(root.val);
        func(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

---

---
## Quick Revision
This problem asks to implement an iterator for a Binary Search Tree (BST) that supports `next()` and `hasNext()` operations.
The solution involves performing an in-order traversal of the BST and storing the elements in a queue.

## Intuition
The core property of a Binary Search Tree is that an in-order traversal visits the nodes in ascending order. If we can perform an in-order traversal and store the visited node values, we can then simply serve them one by one using a queue. The `next()` operation will dequeue an element, and `hasNext()` will check if the queue is empty.

## Algorithm
1.  **Constructor (`BSTIterator(TreeNode root)`):**
    *   Initialize an empty queue (e.g., `LinkedList`) to store the in-order traversal of the BST.
    *   Call a helper function (e.g., `inorderTraversal`) to populate the queue.
2.  **In-order Traversal Helper (`inorderTraversal(TreeNode node)`):**
    *   If the current `node` is `null`, return.
    *   Recursively call `inorderTraversal` on the `node.left`.
    *   Add the `node.val` to the queue.
    *   Recursively call `inorderTraversal` on the `node.right`.
3.  **`next()` Method:**
    *   Remove and return the front element from the queue using `poll()`.
4.  **`hasNext()` Method:**
    *   Return `true` if the queue is not empty (`queue.size() != 0`), and `false` otherwise.

## Concept to Remember
*   **In-order Traversal:** The fundamental traversal for BSTs to get elements in sorted order.
*   **Queues:** A First-In, First-Out (FIFO) data structure suitable for processing elements in the order they are added.
*   **Recursion:** A common technique for tree traversals.
*   **Iterators:** Design patterns for traversing collections.

## Common Mistakes
*   **Incorrect Traversal Order:** Implementing pre-order or post-order traversal instead of in-order.
*   **Not Handling Null Nodes:** Failing to check for `null` children during traversal, leading to `NullPointerException`.
*   **Modifying the Tree:** Accidentally altering the BST structure while iterating.
*   **Inefficient `next()`/`hasNext()`:** If not using a pre-populated structure, repeatedly traversing parts of the tree for each call.

## Complexity Analysis
*   **Time:**
    *   Constructor: O(N) - where N is the number of nodes in the BST. This is because we perform a full in-order traversal to populate the queue.
    *   `next()`: O(1) - dequeuing from a `LinkedList` is constant time.
    *   `hasNext()`: O(1) - checking the size of a `LinkedList` is constant time.
*   **Space:**
    *   O(N) - where N is the number of nodes in the BST. This is due to storing all node values in the `inorder` queue.

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
class BSTIterator {
    // A queue to store the in-order traversal of the BST.
    // LinkedList implements the Queue interface and is suitable here.
    Queue<Integer> inorder = new LinkedList<>();

    // The constructor initializes the iterator.
    public BSTIterator(TreeNode root) {
        // Call the helper function to perform in-order traversal and populate the queue.
        func(root);
    }

    // Returns the next smallest element.
    public int next() {
        // Remove and return the element at the front of the queue.
        // This is the next smallest element in the BST.
        return inorder.poll();
    }

    // Checks if there are more elements to iterate over.
    public boolean hasNext() {
        // If the queue is not empty, there are more elements.
        return inorder.size() != 0;
    }

    // Helper function to perform in-order traversal recursively.
    public void func(TreeNode root){
        // Base case: if the current node is null, stop recursion.
        if(root==null) return;
        // Recursively traverse the left subtree.
        func(root.left);
        // Add the current node's value to the queue.
        // This happens after visiting all left children, ensuring in-order.
        inorder.add(root.val);
        // Recursively traverse the right subtree.
        func(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root); // Create an instance of the iterator.
 * int param_1 = obj.next(); // Get the next element.
 * boolean param_2 = obj.hasNext(); // Check if there are more elements.
 */
```

## Interview Tips
*   **Explain In-order Traversal:** Clearly articulate why in-order traversal is crucial for BSTs and how it guarantees sorted output.
*   **Discuss Space-Time Trade-off:** Mention that this approach uses O(N) space to achieve O(1) time for `next()` and `hasNext()`. Ask if an O(H) space solution (where H is height) is preferred, hinting at a more optimized iterative in-order traversal.
*   **Edge Cases:** Be prepared to discuss how `null` roots or empty trees are handled.
*   **Alternative Approaches:** Briefly mention that a more space-efficient approach exists using a stack to perform an iterative in-order traversal on demand, which would have O(H) space complexity.

## Revision Checklist
- [ ] Understand the definition of a Binary Search Tree (BST).
- [ ] Recall the properties of an in-order traversal for BSTs.
- [ ] Implement the `BSTIterator` class with `next()` and `hasNext()` methods.
- [ ] Use a suitable data structure (like a `Queue`) to store traversal results.
- [ ] Handle `null` nodes correctly during traversal.
- [ ] Analyze the time and space complexity of the chosen approach.
- [ ] Consider alternative, more space-efficient solutions.

## Similar Problems
*   173. Binary Search Tree Iterator (This problem)
*   285. Inorder Successor in BST
*   156. Binary Tree Upside Down
*   94. Binary Tree Inorder Traversal
*   144. Binary Tree Preorder Traversal
*   145. Binary Tree Postorder Traversal

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `Design` `Binary Search Tree` `Stack` `Queue`

## My Notes
using queue, inorder traversal
