# Construct Binary Tree From Preorder And Inorder Traversal

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Tree` `Binary Tree`  
**Time:** O(n)  
**Space:** O(n)

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
Given a preorder and inorder traversal of a binary tree, construct the binary tree.
We can build the tree by iterating through the preorder traversal and using the inorder indices to find the left and right children.

## Intuition
The key insight here is that in the preorder traversal, the root node comes first, followed by its left child subtree, and then its right child subtree. Meanwhile, in the inorder traversal, the root node is placed at a position that separates the left and right subtrees.
By combining these two traversals, we can uniquely determine the structure of the binary tree.

## Algorithm
1. Create a HashMap to store the indices of the inorder array for each value.
2. Define a recursive function `func` that takes in a start and end index for the current subtree.
3. In the `func` function:
	* If the start index is greater than the end index or if we've already processed all preorder nodes, return null.
	* Get the next preorder node and increment the `done` counter.
	* Create a new TreeNode with the current value as its key.
	* Find the index of the current value in the inorder array using the HashMap.
	* Recursively build the left subtree from the start index to the found index - 1.
	* Recursively build the right subtree from the found index + 1 to the end index.

## Concept to Remember
•   Hash Map: a data structure that stores key-value pairs and allows for efficient lookup and insertion operations.
•   Recursive functions: a programming technique where a function calls itself to solve subproblems and combine their solutions.
•   Binary Tree: a data structure composed of nodes, where each node has at most two children (left child and right child).

## Common Mistakes
*   Failing to handle the case when there are duplicate values in the preorder or inorder traversals.
*   Not initializing the HashMap correctly before using it to find indices.
*   Overlooking the edge cases when the input arrays have zero length.

## Complexity Analysis
- Time: O(n) - The function iterates over each element in both the preorder and inorder arrays once, resulting in a linear time complexity.
- Space: O(n) - The HashMap stores n elements (the indices of the inorder array), and the recursive call stack can grow up to n levels.

## Commented Code
```java
class Solution {
    int[] pre; // store the preorder traversal
    HashMap<Integer, Integer> hm = new HashMap<>(); // map values to their inorder indices
    int done = 0; // keep track of the next preorder node

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // set the preorder array and calculate its length
        pre = preorder;
        int n = inorder.length;

        // initialize the HashMap with inorder indices for each value
        for (int i = 0; i < n; i++) hm.put(inorder[i], i);

        // recursively build the binary tree starting from the root node
        return func(0, n);
    }

    public TreeNode func(int start, int end) {
        // base case: if start > end or we've processed all preorder nodes, return null
        if (start > end || done == pre.length) return null;

        // get the next preorder node and increment the done counter
        int curr = pre[done++];
        TreeNode root = new TreeNode(curr); // create a new TreeNode with the current value

        // find the index of the current value in the inorder array using the HashMap
        int index = hm.get(curr);

        // recursively build the left subtree from start to index - 1
        root.left = func(start, index - 1);
        // recursively build the right subtree from index + 1 to end
        root.right = func(index + 1, end);

        return root; // return the root node of the current subtree
    }
}
```

## Interview Tips
*   Pay attention to edge cases, especially when dealing with empty arrays or duplicate values.
*   Make sure to initialize data structures correctly before using them.
*   Break down the problem into smaller subproblems and solve each recursively.

## Revision Checklist
- [ ] Review Hash Map usage and initialization.
- [ ] Verify recursive function implementation.
- [ ] Test edge cases, including empty input arrays.

## Similar Problems
*   Construct Binary Tree from Inorder and Postorder Traversal (LeetCode 106)
*   Build a Binary Search Tree from a Sorted Array (LeetCode 108)
