# Binary Tree Right Side View

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Breadth-First Search` `Binary Tree`  
**Time:** O(n)  
**Space:** O(w)

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode curr = q.poll();
                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);
                if(i==sz-1) ans.add(curr.val);
            }
        }
        
        return ans;
    }
}
```

---

---
## Quick Revision
This problem involves finding the rightmost node at each level in a binary tree. We solve it using a Breadth-First Search (BFS) approach with a queue.

## Intuition
The key insight here is that we can use a queue to keep track of nodes at each level, and then add the rightmost node's value to our result list. This works because in each iteration of BFS, we visit all nodes at the current level before moving on to the next level.

## Algorithm

1. If the tree is empty (i.e., root is null), return an empty list.
2. Initialize a queue with the root node and a list to store the result.
3. While the queue is not empty, perform the following steps:
	* Get the size of the current level (sz).
	* Iterate through each node at this level (i<=sz-1):
		+ Dequeue the current node.
		+ Enqueue its children (if they exist) in any order.
		+ If we're at the rightmost node of this level (i==sz-1), add its value to the result list.
4. Return the result list.

## Concept to Remember

* **Breadth-First Search (BFS)**: A traversal algorithm that visits all nodes at a given level before moving on to the next level.
* **Queue data structure**: Useful for BFS and other problems where you need to process elements in a First-In-First-Out order.
* **Level Order Traversal**: The idea of processing nodes at each level from left to right.

## Common Mistakes

* Failing to handle edge cases, such as an empty tree or a single-node tree.
* Misunderstanding the concept of rightmost node: it's not just about finding the last node in the list, but also ensuring that we're considering all nodes at each level before moving on.
* Not properly implementing the queue data structure and its operations (enqueue, dequeue).

## Complexity Analysis
- Time: O(n) - where n is the number of nodes in the tree, because we visit each node once.
- Space: O(w) - where w is the maximum width of the tree (i.e., the number of nodes at the widest level), because that's the maximum size of our queue.

## Commented Code
```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // If tree is empty, return an empty list
        if(root == null) return new ArrayList<>();

        // Initialize a queue with the root node and result list
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        q.add(root);

        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode curr = q.poll();  // Dequeue current node

                if(curr.left != null) {
                    // Enqueue left child
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    // Enqueue right child
                    q.add(curr.right);
                }

                // If we're at the rightmost node, add its value to result list
                if(i == sz - 1){
                    ans.add(curr.val);
                }
            }
        }

        return ans;
    }
}
```

## Interview Tips

* Pay attention to edge cases and make sure your solution handles them correctly.
* Understand the problem statement carefully: it's not just about finding a "right" node, but also considering all nodes at each level.
* Practice explaining your thought process and code to an interviewer.

## Revision Checklist
- [ ] Can I write this solution from scratch without looking at any code?
- [ ] Have I handled edge cases correctly (e.g., empty tree, single-node tree)?
- [ ] Do I understand the concept of rightmost node and how it relates to each level in the tree?

## Similar Problems

* 103. Binary Tree Zigzag Level Order Traversal
* 102. Binary Tree Level Order Traversal
* 107. Binary Tree Level Order Traversal II

## Tags
`Array`, `Hash Map`, `Breadth-First Search`, `Queue`, `Binary Tree`
