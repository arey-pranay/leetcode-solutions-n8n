# Construct Binary Tree From Preorder And Inorder Traversal

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
    int index = 0;
    int[] pre;
    int[] in;
    HashMap<Integer,Integer> hm = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        in = inorder;
        int n = preorder.length;
        for(int i=0;i<n;i++) hm.put(in[i],i);
        return func(0,n-1);   
    }
    
    // preorder is used to get roots one by one
    // inorder is used to determine which indices are for left subtree and which are for right
    public TreeNode func(int start, int end){
        if(start > end) return null;
        TreeNode root = new TreeNode(pre[index++]);
        int index = hm.get(root.val);
        root.left = func(start,index-1);
        root.right = func(index+1, end);
        return root;  
    }
    
 
}

```

---

---
## Quick Revision
Given preorder and inorder traversals of a binary tree, reconstruct the original tree.
This is solved recursively by identifying the root in preorder and partitioning inorder.

## Intuition
The first element in the preorder traversal is always the root of the current subtree. Once we have the root's value, we can find its position in the inorder traversal. Everything to the left of the root in inorder belongs to its left subtree, and everything to the right belongs to its right subtree. This naturally leads to a recursive approach.

## Algorithm
1.  **Initialization**:
    *   Store the `preorder` and `inorder` arrays globally or pass them recursively.
    *   Use a global `index` variable to keep track of the current root in the `preorder` array. Initialize it to 0.
    *   Create a `HashMap` to store the mapping of each value in the `inorder` array to its index. This allows for O(1) lookup of a root's position in inorder.
2.  **`buildTree` function**:
    *   Populate the `HashMap` with `inorder` values and their indices.
    *   Call a recursive helper function `func` with the initial bounds of the inorder array (0 to n-1, where n is the length of the arrays).
3.  **`func(start, end)` recursive helper function**:
    *   **Base Case**: If `start > end`, it means the current subarray is empty, so return `null`.
    *   **Create Root**: Create a new `TreeNode` with the value `preorder[index]`. Increment `index` to move to the next root in preorder for subsequent recursive calls.
    *   **Find Root in Inorder**: Get the index of the current `root.val` from the `HashMap`. Let this be `inorderIndex`.
    *   **Build Left Subtree**: Recursively call `func` for the left subtree. The inorder range for the left subtree is from `start` to `inorderIndex - 1`.
    *   **Build Right Subtree**: Recursively call `func` for the right subtree. The inorder range for the right subtree is from `inorderIndex + 1` to `end`.
    *   **Return Root**: Return the constructed `root` node.

## Concept to Remember
*   **Tree Traversal Properties**: Understanding how preorder (Root, Left, Right) and inorder (Left, Root, Right) traversals uniquely define a binary tree.
*   **Recursion**: The problem is inherently recursive, breaking down the construction of a tree into constructing its subtrees.
*   **Hash Maps for Efficient Lookups**: Using a hash map to quickly find the index of an element in the inorder array is crucial for performance.

## Common Mistakes
*   **Incorrectly managing the `preorder` index**: Forgetting to increment the global `index` after using a value from `preorder` will lead to using the same root multiple times.
*   **Off-by-one errors in inorder bounds**: Incorrectly defining the `start` and `end` indices for the left and right subtrees in the recursive calls.
*   **Not handling empty subtrees**: Failing to return `null` when `start > end` will cause errors.
*   **Rebuilding the HashMap in every recursive call**: This would be highly inefficient. The map should be built once.

## Complexity Analysis
- Time: O(N) - Each node is visited and processed exactly once. The hash map lookups are O(1) on average.
- Space: O(N) - For the recursion stack in the worst case (a skewed tree) and for the hash map storing N elements.

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
    // Global variable to keep track of the current root's index in the preorder array.
    int index = 0;
    // Store the preorder array globally for easy access in recursive calls.
    int[] pre;
    // Store the inorder array globally for easy access in recursive calls.
    int[] in;
    // HashMap to store the value-to-index mapping for the inorder array for O(1) lookups.
    HashMap<Integer,Integer> hm = new HashMap<>();

    // Main function to build the binary tree.
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Assign the input arrays to the global variables.
        pre = preorder;
        in = inorder;
        // Get the total number of nodes.
        int n = preorder.length;
        // Populate the HashMap with inorder values and their corresponding indices.
        // This allows us to quickly find the position of a root in the inorder traversal.
        for(int i=0;i<n;i++) hm.put(in[i],i);
        // Start the recursive tree construction process.
        // The initial inorder range covers the entire array (from index 0 to n-1).
        return func(0,n-1);
    }

    // Recursive helper function to construct the tree.
    // 'start' and 'end' define the current subarray of the inorder traversal being considered.
    public TreeNode func(int start, int end){
        // Base case: If the start index is greater than the end index, it means the current subarray is empty,
        // so we return null, signifying no node here.
        if(start > end) return null;

        // Create a new TreeNode. The value of this node is the current element pointed to by 'index' in the preorder array.
        // 'index' is incremented after using its value, so the next recursive call will use the next element in preorder.
        TreeNode root = new TreeNode(pre[index++]);

        // Find the index of the current root's value in the inorder array using the pre-computed HashMap.
        // This 'inorderIndex' splits the inorder array into left and right subtrees.
        int inorderIndex = hm.get(root.val);

        // Recursively build the left subtree.
        // The inorder range for the left subtree is from 'start' up to 'inorderIndex - 1'.
        root.left = func(start,inorderIndex-1);

        // Recursively build the right subtree.
        // The inorder range for the right subtree is from 'inorderIndex + 1' up to 'end'.
        root.right = func(inorderIndex+1, end);

        // Return the constructed root node for this subtree.
        return root;
    }
}
```

## Interview Tips
*   **Explain the role of preorder and inorder**: Clearly articulate how preorder gives you the root and inorder helps partition the subtrees.
*   **Trace with a small example**: Walk through a simple tree (e.g., 3 nodes) to demonstrate how your algorithm works step-by-step.
*   **Discuss the HashMap optimization**: Explain why using a HashMap for inorder lookups is crucial for achieving linear time complexity.
*   **Be mindful of index management**: Emphasize how the global `index` for preorder is incremented correctly.

## Revision Checklist
- [ ] Understand the properties of preorder and inorder traversals.
- [ ] Implement the recursive `func` function correctly.
- [ ] Handle the base case `start > end`.
- [ ] Use a HashMap for efficient inorder index lookups.
- [ ] Correctly manage the global `preorder` index.
- [ ] Define the correct inorder bounds for left and right subtrees.

## Similar Problems
*   Construct Binary Tree From Inorder And Postorder Traversal
*   Binary Tree Preorder Traversal
*   Binary Tree Inorder Traversal
*   Binary Tree Postorder Traversal

## Tags
`Array` `Hash Map` `Tree` `Depth-First Search` `Binary Tree`
