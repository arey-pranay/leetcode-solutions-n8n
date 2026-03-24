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
This is solved using a recursive approach, identifying the root from preorder and partitioning inorder.

## Intuition
The first element in the preorder traversal is always the root of the current subtree. Once we have the root's value, we can find its position in the inorder traversal. Everything to the left of the root in inorder belongs to its left subtree, and everything to the right belongs to its right subtree. This naturally leads to a recursive solution. The key optimization is using a hash map to quickly find the index of the root in the inorder array.

## Algorithm
1.  **Initialization**:
    *   Store the preorder and inorder arrays globally or pass them by reference.
    *   Initialize a global `index` variable to 0, which will track the current root in the preorder array.
    *   Create a `HashMap` to store the mapping of each value in the inorder array to its index. This allows for O(1) lookup of a value's position in inorder.
2.  **Build Tree Function (`func`)**:
    *   This function will take `start` and `end` indices representing the current segment of the inorder array being considered for the subtree.
    *   **Base Case**: If `start > end`, it means the segment is empty, so return `null` (representing an empty subtree).
    *   **Create Root**: Create a new `TreeNode` with the value `pre[index]`. Increment `index` to move to the next potential root in the preorder array for subsequent recursive calls.
    *   **Find Root in Inorder**: Use the `HashMap` to find the index (`inorderIndex`) of the current root's value in the inorder array.
    *   **Build Left Subtree**: Recursively call `func` for the left subtree. The inorder segment for the left subtree is from `start` to `inorderIndex - 1`.
    *   **Build Right Subtree**: Recursively call `func` for the right subtree. The inorder segment for the right subtree is from `inorderIndex + 1` to `end`.
    *   **Return Root**: Return the constructed `root` node.
3.  **Main Function (`buildTree`)**:
    *   Initialize the global `pre`, `in` arrays, and the `HashMap`.
    *   Populate the `HashMap` with inorder values and their indices.
    *   Call the `func` with the initial inorder segment covering the entire array (from index 0 to `n-1`, where `n` is the length of the arrays).

## Concept to Remember
*   **Tree Traversal Properties**: Understanding how preorder (Root, Left, Right) and inorder (Left, Root, Right) traversals uniquely define a binary tree.
*   **Recursion**: The problem naturally breaks down into smaller, self-similar subproblems (building left and right subtrees).
*   **Hash Maps for Efficient Lookups**: Using a hash map to quickly find the index of an element in an array, which is crucial for performance.
*   **Divide and Conquer**: The strategy of dividing the inorder array based on the root's position to solve subproblems.

## Common Mistakes
*   **Incorrectly managing the preorder index**: Forgetting to increment the global `index` after using a value from `preorder` as a root, or incrementing it at the wrong time.
*   **Off-by-one errors in inorder segment boundaries**: Incorrectly defining the `start` and `end` indices for the recursive calls to `func`, especially when calculating `index - 1` and `index + 1`.
*   **Not handling empty subtrees**: Failing to return `null` when `start > end`, leading to infinite recursion or incorrect tree structures.
*   **Rebuilding the hash map in each recursive call**: Instead of initializing it once, which would be very inefficient.

## Complexity Analysis
- Time: O(N) - reason: Each node is visited and processed exactly once. The hash map lookups are O(1) on average.
- Space: O(N) - reason: For the recursion stack (in the worst case, a skewed tree) and for the hash map storing N elements.

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
    // Global variable to keep track of the current root index in the preorder array
    int index = 0;
    // Global variables to store the preorder and inorder arrays for easy access in recursive calls
    int[] pre;
    int[] in;
    // HashMap to store the mapping of inorder values to their indices for O(1) lookup
    HashMap<Integer,Integer> hm = new HashMap<>();

    // Main function to build the binary tree from preorder and inorder traversals
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Assign the input arrays to the global variables
        pre = preorder;
        in = inorder;
        // Get the total number of nodes
        int n = preorder.length;
        // Populate the hash map with inorder values and their corresponding indices
        for(int i=0;i<n;i++) hm.put(in[i],i);
        // Start the recursive building process with the entire inorder array range
        return func(0,n-1);
    }

    // Recursive helper function to build the tree
    // start: the starting index of the current inorder subarray
    // end: the ending index of the current inorder subarray
    public TreeNode func(int start, int end){
        // Base case: if the start index is greater than the end index, it means this subarray is empty, so return null
        if(start > end) return null;
        // Create a new TreeNode using the current value from the preorder array (pointed to by 'index')
        // This value is the root of the current subtree
        TreeNode root = new TreeNode(pre[index++]);
        // Find the index of the current root's value in the inorder array using the hash map
        // This index splits the inorder array into left and right subtrees
        int inorderIndex = hm.get(root.val);
        // Recursively build the left subtree:
        // The inorder subarray for the left subtree ranges from 'start' to 'inorderIndex - 1'
        root.left = func(start,inorderIndex-1);
        // Recursively build the right subtree:
        // The inorder subarray for the right subtree ranges from 'inorderIndex + 1' to 'end'
        root.right = func(inorderIndex+1, end);
        // Return the constructed root node for this subtree
        return root;
    }
}
```

## Interview Tips
*   **Explain the preorder/inorder relationship**: Clearly articulate why the first element of preorder is the root and how inorder helps partition the subtrees.
*   **Walk through an example**: Use a small tree (e.g., 3-4 nodes) and trace the execution of your `func` function, showing how `index` and the `start`/`end` pointers change.
*   **Discuss the hash map optimization**: Explain why using a hash map for inorder lookups is crucial for achieving O(N) time complexity, and what the complexity would be without it.
*   **Consider edge cases**: Mention what happens with an empty tree, a tree with only one node, or a skewed tree.

## Revision Checklist
- [ ] Understand preorder and inorder traversal properties.
- [ ] Implement the recursive `buildTree` function.
- [ ] Correctly manage the global `preorder` index.
- [ ] Use a hash map for efficient inorder value lookups.
- [ ] Handle base cases for recursion (empty subtrees).
- [ ] Verify the correct boundaries for left and right subtree recursive calls.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Construct Binary Tree From Inorder And Postorder Traversal
*   Binary Tree Level Order Traversal
*   Binary Tree Inorder Traversal
*   Binary Tree Preorder Traversal

## Tags
`Array` `Hash Map` `Tree` `Depth-First Search` `Recursion`
