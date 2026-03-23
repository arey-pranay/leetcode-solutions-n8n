# Same Tree

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(H)

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        if(p.val==q.val) return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        return false;    
    }
}
```

---

---
## Quick Revision
Checks if two binary trees are structurally identical and have the same node values.
Solves by recursively comparing nodes and their children.

## Intuition
The core idea is that two trees are the same if and only if:
1. Their root nodes have the same value.
2. Their left subtrees are the same.
3. Their right subtrees are the same.
This naturally leads to a recursive solution. We need to handle the base cases where one or both trees are empty.

## Algorithm
1. **Base Case 1:** If both `p` and `q` are `null` (empty trees or subtrees), they are considered the same. Return `true`.
2. **Base Case 2:** If one of `p` or `q` is `null` but the other is not, they cannot be the same. Return `false`.
3. **Value Check:** If the values of the current nodes `p.val` and `q.val` are different, the trees are not the same. Return `false`.
4. **Recursive Step:** If the values are the same, recursively check if the left subtrees are the same (`isSameTree(p.left, q.left)`) AND if the right subtrees are the same (`isSameTree(p.right, q.right)`). Return the result of this logical AND operation.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Tree Traversal:** Implicitly performing a pre-order traversal (checking the current node before its children).
*   **Base Cases:** Essential for terminating recursive calls and handling edge conditions (like empty trees).

## Common Mistakes
*   **Missing Null Checks:** Forgetting to handle cases where one or both nodes are `null` can lead to `NullPointerException` or incorrect results.
*   **Incorrect Base Case Logic:** Confusing the conditions for when two `null` nodes are equal versus when one `null` and one non-`null` node are unequal.
*   **Not Combining Recursive Results:** Forgetting to use the logical AND (`&&`) to ensure *both* left and right subtrees match.
*   **Only Checking Values:** Assuming trees are the same if all values match, without verifying structural identity.

## Complexity Analysis
- Time: O(N) - reason: In the worst case, we visit every node in both trees exactly once. N is the number of nodes in the smaller tree.
- Space: O(H) - reason: Due to the recursion stack. H is the height of the tree. In the worst case (a skewed tree), H can be N, leading to O(N) space. In a balanced tree, H is log N, leading to O(log N) space.

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node.
 *     TreeNode left; // Reference to the left child node.
 *     TreeNode right; // Reference to the right child node.
 *     TreeNode() {} // Default constructor.
 *     TreeNode(int val) { this.val = val; } // Constructor with value.
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children.
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) { // Method to check if two trees are the same.
        if(p==null && q==null) return true; // Base case: If both nodes are null, they are the same (empty trees/subtrees).
        if(p==null || q==null) return false; // Base case: If one node is null and the other isn't, they are different.
        if(p.val==q.val) return isSameTree(p.left,q.left) && isSameTree(p.right,q.right); // If current node values match, recursively check left AND right subtrees.
        return false; // If current node values do not match, the trees are different.
    }
}
```

## Interview Tips
*   **Verbalize Base Cases First:** Clearly state how you'll handle `null` nodes before diving into the recursive logic. This shows structured thinking.
*   **Explain the Recursive Relation:** Articulate why comparing the current node's value and then recursively checking both children is the correct approach.
*   **Trace an Example:** Walk through a small tree example (e.g., one with a difference in value or structure) to demonstrate your understanding.
*   **Discuss Complexity:** Be ready to explain the time and space complexity of your recursive solution.

## Revision Checklist
- [ ] Understand the problem statement: comparing structure and values.
- [ ] Identify base cases: both null, one null.
- [ ] Implement value comparison for current nodes.
- [ ] Implement recursive calls for left and right children.
- [ ] Combine recursive results using logical AND.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Invert Binary Tree
*   Maximum Depth of Binary Tree
*   Symmetric Tree
*   Validate Binary Search Tree

## Tags
`Tree` `Depth-First Search` `Recursion` `Binary Tree`
