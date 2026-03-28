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
    ArrayList<Integer> inorder = new ArrayList<>();
    int pointer = 0;
    public BSTIterator(TreeNode root) {
        func(root);
    }
    
    public int next() {
        return inorder.get(pointer++);
    }
    
    public boolean hasNext() {
        return pointer < inorder.size();
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
The solution involves performing an in-order traversal of the BST and storing the elements in a list.

## Intuition
The core property of a Binary Search Tree is that an in-order traversal visits the nodes in ascending order. If we can perform an in-order traversal and store the values, then iterating through these stored values sequentially will give us the desired behavior of a BST iterator. The `next()` operation will simply return the next element from our stored list, and `hasNext()` will check if there are more elements.

## Algorithm
1.  **Constructor (`BSTIterator(TreeNode root)`):**
    *   Initialize an empty `ArrayList` called `inorder` to store the BST node values.
    *   Initialize an integer `pointer` to 0, which will track the current position in the `inorder` list.
    *   Call a helper function (e.g., `func` or `inorderTraversal`) to populate the `inorder` list by performing an in-order traversal of the `root` node.
2.  **Helper Function (`func(TreeNode node)`):**
    *   **Base Case:** If the current `node` is `null`, return.
    *   **Recursive Step (Left):** Recursively call `func` on the `node.left` child.
    *   **Visit Node:** Add the `node.val` to the `inorder` list.
    *   **Recursive Step (Right):** Recursively call `func` on the `node.right` child.
3.  **`next()` Method:**
    *   Return the element at the current `pointer` index in the `inorder` list.
    *   Increment the `pointer` to move to the next element for subsequent calls.
4.  **`hasNext()` Method:**
    *   Return `true` if the `pointer` is less than the size of the `inorder` list, indicating there are more elements to iterate.
    *   Return `false` otherwise.

## Concept to Remember
*   **In-order Traversal:** Understanding the recursive nature of in-order traversal (Left, Root, Right) is crucial for BSTs.
*   **Iterators:** Familiarity with the iterator design pattern, specifically the `next()` and `hasNext()` methods.
*   **Array/List Manipulation:** Efficiently adding elements to a list and accessing them by index.
*   **Recursion:** The ability to implement recursive functions for tree traversals.

## Common Mistakes
*   **Incorrect Traversal Order:** Implementing pre-order or post-order traversal instead of in-order, leading to incorrect element ordering.
*   **Off-by-One Errors with Pointer:** Incorrectly managing the `pointer` index, leading to `IndexOutOfBoundsException` or skipping elements.
*   **Not Handling Null Root:** Failing to add a base case for a `null` root in the traversal function.
*   **Modifying the Tree:** Accidentally altering the BST structure during traversal (though not an issue with this specific implementation).
*   **Inefficient `next()`/`hasNext()`:** If the traversal were done lazily, not correctly advancing the state for `next()` and `hasNext()`.

## Complexity Analysis
*   **Time:**
    *   Constructor: O(N) - where N is the number of nodes in the BST. This is because the in-order traversal visits every node exactly once.
    *   `next()`: O(1) - Accessing an element from an `ArrayList` by index is a constant time operation.
    *   `hasNext()`: O(1) - Checking the pointer against the list size is a constant time operation.
*   **Space:**
    *   O(N) - where N is the number of nodes in the BST. This is due to storing all node values in the `inorder` `ArrayList`.

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
class BSTIterator {
    // ArrayList to store the in-order traversal of the BST nodes.
    ArrayList<Integer> inorder = new ArrayList<>();
    // Pointer to keep track of the current element in the inorder list.
    int pointer = 0;

    // Constructor: Initializes the iterator by performing an in-order traversal.
    public BSTIterator(TreeNode root) {
        // Call the helper function to populate the inorder list.
        func(root);
    }
    
    // Returns the next smallest element in the BST.
    public int next() {
        // Get the element at the current pointer position and then increment the pointer.
        return inorder.get(pointer++);
    }
    
    // Checks if there are more elements to iterate over.
    public boolean hasNext() {
        // Return true if the pointer is within the bounds of the inorder list.
        return pointer < inorder.size();
    }
    
    // Helper function to perform in-order traversal and populate the inorder list.
    public void func(TreeNode root){
        // Base case: if the current node is null, stop recursion.
        if(root==null) return;
        // Recursively traverse the left subtree.
        func(root.left);
        // Add the current node's value to the inorder list.
        inorder.add(root.val);
        // Recursively traverse the right subtree.
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

## Interview Tips
*   **Explain the BST Property:** Start by explaining that an in-order traversal of a BST yields elements in sorted order. This is the fundamental insight.
*   **Discuss Trade-offs:** Mention that this approach uses O(N) space upfront. Ask if a solution with O(H) space (where H is the height of the tree) is desired, hinting at a more complex lazy approach using a stack.
*   **Clarify Edge Cases:** Be prepared to discuss what happens with an empty tree (`root == null`).
*   **Walk Through an Example:** Use a small BST example to trace the in-order traversal and how the `pointer` moves through the `next()` and `hasNext()` calls.

## Revision Checklist
- [ ] Understand BST in-order traversal.
- [ ] Implement `next()` and `hasNext()` methods.
- [ ] Handle null root case.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative approaches (e.g., using a stack for O(H) space).

## Similar Problems
*   Flatten Binary Tree to Linked List
*   Zigzag Conversion
*   Preorder Iterator
*   Postorder Iterator

## Tags
`Tree` `Binary Tree` `Design` `Stack` `Recursion`
