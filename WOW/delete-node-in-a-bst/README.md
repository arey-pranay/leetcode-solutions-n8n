# Delete Node In A Bst

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Binary Search Tree` `Binary Tree`  
**Time:** O(H)  
**Space:** O(H)

---

## Solution (java)

```java
// replace node to be deleted with inorder successor or inorder predecessor
// inorder pred means just chhota element curr se. so left subtree ka rightmost child
       
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return root;
     
        if(key > root.val) root.right = deleteNode(root.right,key); 
        else if(key < root.val) root.left = deleteNode(root.left,key);            
        else {
            if(root.right == null) return root.left;
            if(root.left == null) return root.right;
            
            TreeNode inPre = rightmostChildOf(root.left);
            root.val = inPre.val;
            root.left = deleteNode(root.left, inPre.val);
            
            // TreeNode inSuc = leftmostChildOf(root.right);
            // root.val = inSuc.val;
            // root.right = deleteNode(root.right, inSuc.val);
        }
        
        return root;
    }
    public TreeNode rightmostChildOf(TreeNode root){
        while(root!=null && root.right!=null) root = root.right;
        return root;
    }
    // public TreeNode leftmostChildOf(TreeNode root){
    //     while(root!=null && root.left!=null) root = root.left;
    //     return root;
    // }
} 
// yaa to right waale ka saara left left yaa right waale a saara right right
```

---

---
## Quick Revision
The problem asks to delete a node with a specific key from a Binary Search Tree (BST). The solution involves finding the node and then replacing it with its inorder successor or predecessor to maintain BST properties.

## Intuition
The core idea is that deleting a node from a BST requires careful handling to preserve the BST property (left child < parent < right child). If the node to be deleted has zero or one child, it's straightforward. The complexity arises when the node has two children. In this case, we can replace the node's value with either its inorder successor (the smallest value in its right subtree) or its inorder predecessor (the largest value in its left subtree). After replacing the value, we then recursively delete the successor/predecessor from its original position, which will always be a node with at most one child.

## Algorithm
1.  **Base Case:** If the `root` is `null`, return `null`.
2.  **Search for the Node:**
    *   If `key` is greater than `root.val`, recursively call `deleteNode` on the right subtree: `root.right = deleteNode(root.right, key)`.
    *   If `key` is less than `root.val`, recursively call `deleteNode` on the left subtree: `root.left = deleteNode(root.left, key)`.
3.  **Node Found (key == root.val):**
    *   **Case 1: Node has no right child.** Return the left child (`root.left`). This effectively promotes the left child to take the deleted node's place.
    *   **Case 2: Node has no left child.** Return the right child (`root.right`). This promotes the right child.
    *   **Case 3: Node has both left and right children.**
        *   Find the inorder predecessor: the rightmost node in the left subtree. This can be done with a helper function `rightmostChildOf(root.left)`.
        *   Replace the `root.val` with the value of the inorder predecessor.
        *   Recursively delete the inorder predecessor from the left subtree: `root.left = deleteNode(root.left, inPre.val)`.
        *   *(Alternatively, you could find the inorder successor (leftmost node in the right subtree) and perform a similar replacement and deletion.)*
4.  **Return Root:** After performing the deletion (or if the node wasn't found and the recursive calls returned), return the `root` of the current subtree.

## Concept to Remember
*   Binary Search Tree (BST) properties.
*   Inorder traversal and its relation to sorted order in BSTs.
*   Recursion for tree traversal and modification.
*   Handling edge cases (null nodes, leaf nodes, nodes with one child).

## Common Mistakes
*   Incorrectly finding the inorder successor/predecessor.
*   Failing to re-attach the modified subtrees after deletion (e.g., not assigning the result of recursive calls back to `root.left` or `root.right`).
*   Not handling the case where the node to be deleted is the root of the entire tree.
*   Infinite recursion if the deletion logic for the two-child case is flawed.
*   Forgetting to delete the *original* inorder successor/predecessor node after copying its value.

## Complexity Analysis
- Time: O(H) - In the worst case, we might traverse the height of the tree to find the node, and then traverse again to find the inorder successor/predecessor. H is the height of the tree. For a balanced BST, H is log N. For a skewed BST, H is N.
- Space: O(H) - Due to the recursion stack. In the worst case (skewed tree), this can be O(N).

## Commented Code
```java
class Solution {
    // Main function to delete a node with a given key from the BST.
    public TreeNode deleteNode(TreeNode root, int key) {
        // Base case: If the tree is empty, return null.
        if(root == null) return root;

        // If the key to be deleted is greater than the current node's value,
        // it must be in the right subtree. Recursively call deleteNode on the right subtree.
        if(key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        // If the key to be deleted is less than the current node's value,
        // it must be in the left subtree. Recursively call deleteNode on the left subtree.
        else if(key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        // If the key matches the current node's value, this is the node to delete.
        else {
            // Case 1: Node has no right child.
            // In this case, the left child (if it exists) can replace the current node.
            if(root.right == null) {
                return root.left; // Return the left child to be attached to the parent.
            }
            // Case 2: Node has no left child.
            // In this case, the right child (if it exists) can replace the current node.
            if(root.left == null) {
                return root.right; // Return the right child to be attached to the parent.
            }

            // Case 3: Node has both left and right children.
            // We need to find a replacement node that maintains BST properties.
            // The inorder predecessor (largest node in the left subtree) is a good choice.
            TreeNode inPre = rightmostChildOf(root.left); // Find the rightmost node in the left subtree.
            root.val = inPre.val; // Replace the current node's value with the inorder predecessor's value.
            // Now, we need to delete the inorder predecessor from its original position in the left subtree.
            // This is guaranteed to be a node with at most one child, simplifying deletion.
            root.left = deleteNode(root.left, inPre.val);

            // Alternative approach (commented out): Use inorder successor (leftmost node in right subtree).
            // TreeNode inSuc = leftmostChildOf(root.right);
            // root.val = inSuc.val;
            // root.right = deleteNode(root.right, inSuc.val);
        }

        // Return the (potentially modified) root of the current subtree.
        return root;
    }

    // Helper function to find the rightmost node in a subtree.
    // This is the inorder predecessor of the root of this subtree.
    public TreeNode rightmostChildOf(TreeNode root){
        // Traverse as far right as possible.
        while(root != null && root.right != null) {
            root = root.right;
        }
        // The last node reached is the rightmost one.
        return root;
    }

    // Helper function to find the leftmost node in a subtree (alternative for inorder successor).
    // public TreeNode leftmostChildOf(TreeNode root){
    //     while(root != null && root.left != null) {
    //         root = root.left;
    //     }
    //     return root;
    // }
}
```

## Interview Tips
1.  **Explain the BST Property:** Before diving into the code, clearly articulate what a BST is and why maintaining its properties is crucial after deletion.
2.  **Handle the Three Cases:** Explicitly discuss the three scenarios for the node to be deleted (0, 1, or 2 children) and how each is handled. Pay special attention to the two-child case.
3.  **Inorder Successor/Predecessor Logic:** Be ready to explain *why* using the inorder successor or predecessor works and how to find it efficiently. Walk through an example.
4.  **Recursive Structure:** Emphasize how recursion simplifies the search and deletion process, especially when re-attaching subtrees.

## Revision Checklist
- [ ] Understand BST properties.
- [ ] Identify the node to delete.
- [ ] Handle deletion of nodes with 0 or 1 child.
- [ ] Implement finding the inorder successor/predecessor.
- [ ] Correctly replace the node's value and delete the successor/predecessor.
- [ ] Ensure recursive calls correctly update parent pointers (`root.left = ...`, `root.right = ...`).
- [ ] Consider edge cases: empty tree, deleting the root.

## Similar Problems
*   Insert Into A Binary Search Tree
*   Validate Binary Search Tree
*   Lowest Common Ancestor of a Binary Search Tree
*   Find Minimum in Rotated Sorted Array (conceptually related to finding specific elements in ordered structures)

## Tags
`Tree` `Depth-First Search` `Binary Tree` `Binary Search Tree`
