# Construct Binary Tree From Inorder And Postorder Traversal

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(N)

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
    HashMap<Integer,Integer> hm = new HashMap<>();
    int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for(int i =0;i<n;i++) hm.put(inorder[i],i);
        index = n-1;
        return func(0,n-1,inorder,postorder);
    }
    public TreeNode func(int start, int end, int[] inorder, int[] postorder){
        if(start > end) return null;
        TreeNode root = new TreeNode(postorder[index--]);
        int ind = hm.get(root.val);
        
        root.right = func(ind+1,end, inorder, postorder);
        root.left = func(start,ind-1, inorder, postorder);
        
        return root;
    }
}
```

---

---
## Quick Revision
Given inorder and postorder traversals of a binary tree, reconstruct the tree.
This is solved using a recursive approach, identifying the root from postorder and partitioning inorder.

## Intuition
The key insight is that the *last element* in the postorder traversal is always the *root* of the current subtree. Once we identify the root, we can find its position in the inorder traversal. Everything to the left of the root in inorder belongs to its left subtree, and everything to the right belongs to its right subtree. This naturally leads to a recursive solution.

## Algorithm
1.  **Map inorder values to their indices:** Create a hash map to store each value in the `inorder` array and its corresponding index. This allows for O(1) lookup of a value's position in `inorder`.
2.  **Initialize postorder index:** Use a global or class-level variable `index` to keep track of the current root element in the `postorder` array. Initialize it to the last element's index (`n-1`).
3.  **Recursive function `func(start, end, inorder, postorder)`:**
    *   **Base Case:** If `start > end`, it means the current subarray is empty, so return `null`.
    *   **Identify Root:** The current root's value is `postorder[index]`. Create a new `TreeNode` with this value. Decrement `index` to move to the next potential root for subsequent recursive calls.
    *   **Find Root in Inorder:** Get the index (`ind`) of the root's value in the `inorder` array using the pre-computed hash map.
    *   **Build Right Subtree:** Recursively call `func` for the right subtree. The inorder range for the right subtree is from `ind + 1` to `end`. **Crucially, build the right subtree *first*** because we are processing the `postorder` array from right to left (due to `index--`).
    *   **Build Left Subtree:** Recursively call `func` for the left subtree. The inorder range for the left subtree is from `start` to `ind - 1`.
    *   **Return Root:** Return the constructed `root` node.
4.  **Initial Call:** Call `func(0, n-1, inorder, postorder)` to start the construction process.

## Concept to Remember
*   **Tree Traversal Properties:** Understanding how inorder, preorder, and postorder traversals uniquely identify a binary tree structure.
*   **Recursion:** Decomposing a problem into smaller, self-similar subproblems.
*   **Hash Maps for Efficient Lookups:** Using hash maps to achieve O(1) average time complexity for searching elements.
*   **Postorder Traversal Structure:** The last element is the root, followed by the right subtree's traversal, then the left subtree's traversal.

## Common Mistakes
*   **Incorrect Order of Recursive Calls:** Building the left subtree before the right subtree when processing `postorder` from right to left will lead to an incorrect tree structure.
*   **Off-by-One Errors in Inorder Indices:** Incorrectly defining the `start` and `end` bounds for the recursive calls, especially when calculating `ind + 1` and `ind - 1`.
*   **Not Handling Empty Subtrees:** Failing to return `null` when `start > end`, which is the base case for recursion.
*   **Modifying Global Index Incorrectly:** Not decrementing the `index` variable properly after using it to pick a root, or decrementing it at the wrong time.
*   **Inefficient Inorder Lookup:** Not using a hash map and instead linearly searching `inorder` in each recursive call, leading to O(N^2) time complexity.

## Complexity Analysis
*   **Time:** O(N) - Each node is visited and processed exactly once. The hash map creation takes O(N), and each recursive call does constant work (hash map lookup, node creation, index decrement) plus the recursive calls.
*   **Space:** O(N) - For the hash map storing inorder elements and for the recursion call stack in the worst case (a skewed tree).

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node
 *     TreeNode left; // Pointer to the left child
 *     TreeNode right; // Pointer to the right child
 *     TreeNode() {} // Default constructor
 *     TreeNode(int val) { this.val = val; } // Constructor with value
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // HashMap to store the index of each element in the inorder traversal for quick lookups.
    HashMap<Integer,Integer> hm = new HashMap<>();
    // Global index to keep track of the current root in the postorder traversal.
    // We process postorder from right to left.
    int index;

    // Main function to build the tree.
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length; // Get the number of nodes.
        // Populate the hash map with inorder elements and their indices.
        for(int i =0;i<n;i++) hm.put(inorder[i],i);
        // Initialize the index to the last element of the postorder array, which is the root of the entire tree.
        index = n-1;
        // Start the recursive construction process.
        // The initial inorder range covers the entire array (0 to n-1).
        return func(0,n-1,inorder,postorder);
    }

    // Recursive helper function to build the tree.
    // start: the starting index of the current inorder subarray.
    // end: the ending index of the current inorder subarray.
    // inorder: the inorder traversal array.
    // postorder: the postorder traversal array.
    public TreeNode func(int start, int end, int[] inorder, int[] postorder){
        // Base case: if the start index is greater than the end index, it means this subarray is empty, so return null.
        if(start > end) return null;

        // The current root's value is the element at the current 'index' in the postorder array.
        // Decrement 'index' because we've used this element as a root and will move to the next element in postorder (which is the root of the right or left subtree).
        TreeNode root = new TreeNode(postorder[index--]);

        // Find the index of the current root's value in the inorder array.
        // This index splits the inorder array into left and right subtrees.
        int ind = hm.get(root.val);

        // Recursively build the right subtree FIRST.
        // This is because we are processing the postorder array from right to left.
        // The inorder range for the right subtree is from 'ind + 1' to 'end'.
        root.right = func(ind+1,end, inorder, postorder);

        // Recursively build the left subtree.
        // The inorder range for the left subtree is from 'start' to 'ind - 1'.
        root.left = func(start,ind-1, inorder, postorder);

        // Return the constructed root node for this subtree.
        return root;
    }
}
```

## Interview Tips
*   **Explain the Postorder Property:** Clearly articulate why the last element of `postorder` is the root.
*   **Trace an Example:** Walk through a small example (e.g., `inorder = [9,3,15,20,7]`, `postorder = [9,15,7,20,3]`) to demonstrate how the algorithm works step-by-step.
*   **Discuss the Hash Map's Role:** Emphasize how the hash map optimizes the lookup of the root's index in `inorder` from O(N) to O(1).
*   **Clarify the Recursive Call Order:** Explain why the right subtree is built *before* the left subtree due to the right-to-left processing of the `postorder` array.
*   **Handle Edge Cases:** Mention how the `start > end` condition correctly handles empty subtrees.

## Revision Checklist
- [ ] Understand the properties of inorder and postorder traversals.
- [ ] Recognize the last element of postorder as the root.
- [ ] Use inorder to partition left and right subtrees.
- [ ] Implement the recursive function with correct base cases and bounds.
- [ ] Use a hash map for efficient inorder index lookup.
- [ ] Pay attention to the order of recursive calls (right then left).
- [ ] Manage the postorder index correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Construct Binary Tree From Preorder And Inorder Traversal
*   Binary Tree Inorder Traversal
*   Binary Tree Preorder Traversal
*   Binary Tree Postorder Traversal

## Tags
`Array` `Hash Map` `Tree` `Depth-First Search` `Recursion`
