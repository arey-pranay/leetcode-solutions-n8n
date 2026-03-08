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
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        for (Node child : root.children) dfs(child, res);
        res.add(root.val);
    }
}
```

---

---
## Quick Revision
Traverse an N-ary tree such that all children of a node are visited before the node itself.
This is solved using Depth First Search (DFS), either recursively or iteratively.

## Intuition
A postorder traversal for a binary tree visits left, then right, then the node. For an N-ary tree, this generalizes to visiting all children from left to right, and *then* visiting the node itself. The recursive DFS naturally handles this order: for each child, we recursively call DFS, ensuring all its descendants are processed. Once all children's subtrees are fully traversed, we add the current node's value.

## Algorithm
1. Initialize an empty list `res` to store the postorder traversal result.
2. If the `root` is `null`, return the empty `res` list.
3. Define a recursive helper function `dfs(node, res)`:
    a. For each `child` in the `node.children` list:
        i. Recursively call `dfs(child, res)`.
    b. After all children have been processed, add `node.val` to the `res` list.
4. Call `dfs(root, res)` to start the traversal.
5. Return the `res` list.

## Concept to Remember
*   **Tree Traversal:** Understanding different orders (preorder, inorder, postorder) and their applications.
*   **Depth First Search (DFS):** The core algorithmic technique for exploring tree and graph structures.
*   **Recursion:** A powerful technique for solving problems that can be broken down into smaller, self-similar subproblems.
*   **N-ary Trees:** Trees where a node can have an arbitrary number of children, not just two.

## Common Mistakes
*   **Incorrect Order:** Forgetting that in postorder, the node is visited *after* its children.
*   **Handling Null Root:** Not including a base case for an empty tree.
*   **Modifying List During Iteration:** While not directly applicable in this specific recursive solution, iterative DFS might involve modifying a stack/queue while iterating, which can lead to errors.
*   **Not Traversing All Children:** Missing a child in the loop when processing `node.children`.

## Complexity Analysis
*   Time: O(N) - Each node in the N-ary tree is visited exactly once.
*   Space: O(H) - Where H is the height of the tree. This is due to the recursion call stack. In the worst case (a skewed tree), H can be N, leading to O(N) space.

## Commented Code
```java
class Solution {
    // The main function to initiate the postorder traversal.
    public List<Integer> postorder(Node root) {
        // If the tree is empty (root is null), return an empty list.
        if (root == null) return new ArrayList<>();
        // Initialize a list to store the result of the traversal.
        List<Integer> res = new ArrayList<>();
        // Call the recursive helper function to perform the DFS traversal.
        dfs(root, res);
        // Return the list containing the postorder traversal.
        return res;
    }

    // Recursive helper function for Depth First Search (DFS).
    private void dfs(Node root, List<Integer> res) {
        // Iterate through each child of the current node.
        for (Node child : root.children) {
            // Recursively call dfs on each child to traverse its subtree first.
            dfs(child, res);
        }
        // After all children's subtrees have been traversed, add the current node's value to the result list.
        res.add(root.val);
    }
}
```

## Interview Tips
*   **Clarify the Definition:** Ensure you and the interviewer are on the same page about what "postorder" means for an N-ary tree (children first, then parent).
*   **Explain the Recursion:** Clearly articulate how the recursive calls ensure all children are processed before the parent.
*   **Consider Iterative Approach:** Be prepared to discuss or implement an iterative solution using a stack, which is a common follow-up. This often involves a slight modification to the standard iterative preorder/inorder to achieve postorder.
*   **Edge Cases:** Mention handling the `null` root case explicitly.

## Revision Checklist
- [ ] Understand N-ary tree structure.
- [ ] Define postorder traversal for N-ary trees.
- [ ] Implement recursive DFS correctly.
- [ ] Handle the null root edge case.
- [ ] Analyze time and space complexity.
- [ ] Be ready to discuss iterative solutions.

## Similar Problems
*   N Ary Tree Preorder Traversal
*   Binary Tree Postorder Traversal
*   N Ary Tree Level Order Traversal

## Tags
`Tree` `Depth-First Search` `Recursion` `N-ary Tree`
