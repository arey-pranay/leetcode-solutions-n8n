# N Ary Tree Postorder Traversal

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Stack` `Tree` `Depth-First Search`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java
class Solution {
    public List<Integer> postorder(Node root) {
        // If the root is null, return an empty list
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        // Start DFS from the root
        dfs(root, res);

        // Return the result list containing node values in post-order
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        // Recursively call dfs for each child of the current node
        for (Node child : root.children) {
            dfs(child, res);
        }
        // Append the value of the current node to the result list
        res.add(root.val);
    }
}
```

---

---
## Quick Revision
Traverse an N-ary tree such that all children of a node are visited before the node itself.
This is achieved using a Depth First Search (DFS) approach, either recursively or iteratively.

## Intuition
The core idea of postorder traversal is to process children first. For an N-ary tree, this means for any given node, we must visit all of its children's subtrees completely before we can "visit" (i.e., add to our result list) the node itself. This naturally lends itself to a recursive DFS where we dive deep into each child's subtree and only add the parent's value once all its descendants have been processed.

## Algorithm
1. Initialize an empty list `res` to store the postorder traversal result.
2. If the `root` is null, return the empty `res` list.
3. Define a recursive helper function `dfs(node, res)`:
    a. For each `child` in the `node.children` list:
        i. Recursively call `dfs(child, res)`.
    b. After all children have been processed, add `node.val` to the `res` list.
4. Call `dfs(root, res)` to start the traversal.
5. Return the `res` list.

## Concept to Remember
*   **Tree Traversal:** Understanding different traversal orders (preorder, inorder, postorder, level order) and their applications.
*   **Depth First Search (DFS):** The recursive nature of DFS is fundamental for tree traversals.
*   **Recursion:** The ability to break down a problem into smaller, self-similar subproblems.
*   **N-ary Trees:** Handling nodes with an arbitrary number of children.

## Common Mistakes
*   **Incorrect Order of Operations:** Adding the current node's value *before* visiting its children, which would result in a preorder traversal.
*   **Handling Null Root:** Forgetting to check if the initial `root` is null, leading to a `NullPointerException`.
*   **Modifying List During Iteration:** If an iterative approach were used with a stack, modifying the list while iterating over it could lead to errors.
*   **Stack Overflow (Iterative):** For very deep trees, an iterative DFS using a stack might still consume significant memory.

## Complexity Analysis
*   **Time:** O(N) - reason: Each node in the N-ary tree is visited exactly once.
*   **Space:** O(H) - reason: This is the space used by the recursion call stack, where H is the height of the tree. In the worst case (a skewed tree), H can be N, making it O(N).

## Commented Code
```java
class Solution {
    // Public method to initiate the postorder traversal
    public List<Integer> postorder(Node root) {
        // If the root node is null, it means the tree is empty, so return an empty list.
        if (root == null) return new ArrayList<>();

        // Initialize an ArrayList to store the result of the postorder traversal.
        List<Integer> res = new ArrayList<>();

        // Start the Depth First Search (DFS) traversal from the root node.
        dfs(root, res);

        // Return the list containing node values in post-order.
        return res;
    }

    // Private helper method to perform the recursive DFS traversal
    private void dfs(Node root, List<Integer> res) {
        // Iterate through each child of the current node.
        for (Node child : root.children) {
            // Recursively call dfs on each child. This ensures that all descendants of a child are visited before the current node.
            dfs(child, res);
        }
        // After all children (and their subtrees) have been visited, add the value of the current node to the result list.
        res.add(root.val);
    }
}
```

## Interview Tips
*   **Explain the "Why":** Clearly articulate *why* postorder traversal requires visiting children before the parent.
*   **Trace an Example:** Be prepared to walk through a small N-ary tree example to demonstrate your understanding of the traversal.
*   **Discuss Iterative vs. Recursive:** Briefly mention that an iterative solution using a stack is also possible, and discuss its trade-offs (e.g., avoiding recursion depth limits but potentially more complex logic).
*   **Clarify Node Structure:** Ensure you understand the `Node` structure, specifically how children are represented (e.g., a `List<Node>`).

## Revision Checklist
- [ ] Understand the definition of postorder traversal.
- [ ] Implement recursive DFS for N-ary trees.
- [ ] Handle the base case of a null root.
- [ ] Correctly add node values *after* processing children.
- [ ] Analyze time and space complexity.
- [ ] Consider an iterative approach (optional but good for discussion).

## Similar Problems
*   LeetCode 589: N-ary Tree Preorder Traversal
*   LeetCode 590: N-ary Tree Postorder Traversal (this problem)
*   LeetCode 144: Binary Tree Preorder Traversal
*   LeetCode 94: Binary Tree Inorder Traversal
*   LeetCode 145: Binary Tree Postorder Traversal
*   LeetCode 102: Binary Tree Level Order Traversal

## Tags
`Tree` `Depth-First Search` `Recursion` `N-ary Tree`
