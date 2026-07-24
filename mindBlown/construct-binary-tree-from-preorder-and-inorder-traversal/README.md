# Construct Binary Tree From Preorder And Inorder Traversal

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int[] pre;
    HashMap<Integer,Integer> hm = new HashMap<>();
    int done = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        int n = inorder.length;
        for(int i=0;i<n;i++)hm.put(inorder[i],i);
        return func(0,n);
    }
    public TreeNode func(int start, int end){
        if(start > end || done == pre.length) return null;
        int curr = pre[done++];
        TreeNode root = new TreeNode(curr);
        
        int index = hm.get(curr);
        root.left = func(start,index-1);
        root.right = func(index+1,end);
        
        return root;
    }
   
    
}
```

---

---
## Quick Revision
Given preorder and inorder traversals of a binary tree, reconstruct the tree.
This is solved by using preorder to identify the root and inorder to partition left/right subtrees.

## Intuition
The first element in the preorder traversal is always the root of the current subtree. Once we know the root's value, we can find its position in the inorder traversal. Everything to the left of the root in inorder belongs to its left subtree, and everything to the right belongs to its right subtree. This recursive partitioning is the key.

## Algorithm
1.  **Initialization**:
    *   Store the `preorder` array globally or pass it by reference.
    *   Create a `HashMap` to store the index of each value in the `inorder` array for quick lookups. This allows O(1) retrieval of a node's position in inorder.
    *   Initialize a global index `done` (or a similar variable) to keep track of the current root being processed from the `preorder` array.
2.  **Recursive Helper Function `func(start, end)`**:
    *   **Base Case**: If `start` is greater than `end` (empty range) or if all elements from `preorder` have been used (`done == pre.length`), return `null`.
    *   **Identify Root**: The current root's value is `preorder[done]`. Increment `done` to move to the next element in `preorder` for subsequent recursive calls.
    *   **Create Root Node**: Create a new `TreeNode` with the identified root value.
    *   **Find Root's Index in Inorder**: Use the `HashMap` to get the index of the root's value in the `inorder` array. Let this be `index`.
    *   **Build Left Subtree**: Recursively call `func(start, index - 1)` to build the left subtree. The range for the left subtree in inorder is from `start` to `index - 1`.
    *   **Build Right Subtree**: Recursively call `func(index + 1, end)` to build the right subtree. The range for the right subtree in inorder is from `index + 1` to `end`.
    *   **Return Root**: Return the created `root` node with its left and right children attached.
3.  **Main Function `buildTree(preorder, inorder)`**:
    *   Initialize the global `pre` array.
    *   Populate the `HashMap` with inorder values and their indices.
    *   Call the recursive helper function `func(0, inorder.length - 1)` to start the construction process.

## Concept to Remember
*   **Tree Traversal Properties**: Understanding how preorder (Root, Left, Right) and inorder (Left, Root, Right) traversals uniquely define a binary tree.
*   **Recursion**: The problem naturally lends itself to a recursive solution where subproblems (building subtrees) are solved independently.
*   **Hash Maps for Efficient Lookups**: Using a hash map to quickly find the index of an element in the inorder array is crucial for optimizing the algorithm.

## Common Mistakes
*   **Incorrectly managing the `preorder` index**: Forgetting to increment the global `done` index after using an element from `preorder` as a root.
*   **Off-by-one errors in recursive ranges**: Incorrectly defining the `start` and `end` indices for the left and right subtrees in the recursive calls.
*   **Not handling empty subtrees**: Failing to return `null` when the `start` index exceeds the `end` index, leading to infinite recursion or incorrect tree structures.
*   **Rebuilding the HashMap in every recursive call**: Instead of pre-populating it once.

## Complexity Analysis
- Time: O(N) - reason: Each node is visited and processed exactly once. The hash map lookups are O(1) on average.
- Space: O(N) - reason: For the hash map storing N elements and for the recursion call stack, which can go up to O(N) in the worst case (a skewed tree).

## Commented Code
```java
class Solution {
    // Store the preorder traversal globally to access it in the recursive function.
    int[] pre;
    // HashMap to store inorder elements and their indices for O(1) lookup.
    HashMap<Integer,Integer> hm = new HashMap<>();
    // Index to keep track of the current root element in the preorder array.
    int done = 0;

    // Main function to build the tree.
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Assign the preorder array to the class member.
        pre = preorder;
        // Get the total number of nodes.
        int n = inorder.length;
        // Populate the hashmap with inorder elements and their corresponding indices.
        for(int i=0;i<n;i++)hm.put(inorder[i],i);
        // Start the recursive tree building process.
        // The initial range for inorder is from index 0 to n-1.
        return func(0,n-1); // Corrected end index to n-1
    }

    // Recursive helper function to build the tree.
    // 'start' and 'end' define the current range in the inorder array being considered for the subtree.
    public TreeNode func(int start, int end){
        // Base case: If the start index is greater than the end index, it means the current range is invalid or empty, so return null.
        // Also, if we have used all elements from preorder, we can't form more nodes.
        if(start > end || done == pre.length) return null;

        // The current root's value is the element at the 'done' index in the preorder array.
        int curr = pre[done++]; // Increment 'done' to move to the next element in preorder for subsequent calls.
        // Create a new TreeNode with the current root's value.
        TreeNode root = new TreeNode(curr);

        // Find the index of the current root's value in the inorder array using the hashmap.
        int index = hm.get(curr);

        // Recursively build the left subtree.
        // The left subtree's inorder range is from 'start' to 'index - 1'.
        root.left = func(start,index-1);
        // Recursively build the right subtree.
        // The right subtree's inorder range is from 'index + 1' to 'end'.
        root.right = func(index+1,end);

        // Return the constructed root node with its left and right children.
        return root;
    }
}
```

## Interview Tips
*   **Explain the Preorder/Inorder Relationship**: Clearly articulate how the first element of preorder is the root and how inorder splits the remaining elements into left and right subtrees.
*   **Trace an Example**: Walk through a small example (e.g., `preorder = [3,9,20,15,7]`, `inorder = [9,3,15,20,7]`) to demonstrate your understanding of the recursive partitioning.
*   **Discuss Optimization**: Mention the use of a hash map for O(1) inorder lookups and why it's important for achieving linear time complexity.
*   **Handle Edge Cases**: Be prepared to discuss what happens with empty trees, single-node trees, or skewed trees.

## Revision Checklist
- [ ] Understand the properties of preorder and inorder traversals.
- [ ] Implement the recursive partitioning logic correctly.
- [ ] Use a hash map for efficient index lookups in inorder.
- [ ] Manage the preorder index correctly across recursive calls.
- [ ] Handle base cases and invalid ranges properly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Construct Binary Tree From Inorder And Postorder Traversal
*   Binary Tree Level Order Traversal
*   Binary Tree Inorder Traversal
*   Binary Tree Preorder Traversal

## Tags
`Array` `Hash Map` `Tree` `Depth-First Search` `Binary Tree`
