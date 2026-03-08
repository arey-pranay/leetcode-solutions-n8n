# N Ary Tree Postorder Traversal

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Stack` `Tree` `Depth-First Search`  
**Time:** O(N)  
**Space:** O(H)

---

## Solution (java)

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
*/

class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;
        func(root,ans);
        return ans;
    }
    public void func(Node root , List<Integer> ans){
        if(root.children == null){
            ans.add(root.val);
            return;
        }
        for(Node n : root.children) func(n,ans);
        ans.add(root.val);
        return;
    }
}
```

---

---
## Problem Summary
Given an N-ary tree, traverse the tree in postorder and return the result as a list of integers. Postorder traversal visits the child nodes before the parent node.

## Approach and Intuition
The approach is to use a recursive function to traverse the tree. The function starts at the root and visits all its child nodes first, then adds the root node's value to the result list.

## Complexity Analysis
- Time: O(N) - where N is the number of nodes in the tree, as each node is visited once.
- Space: O(H) - where H is the height of the tree, as this is the maximum depth of the recursion call stack.

## Code Walkthrough
The solution consists of two methods: `postorder` and `func`. The `postorder` method initializes an empty list to store the result and checks if the root is null. If it is, the method returns an empty list. Otherwise, it calls the `func` method with the root and the result list.

The `func` method checks if the current node has children. If not, it adds the node's value to the result list and returns. If the node has children, it iterates over each child node and calls the `func` method recursively. After visiting all child nodes, it adds the current node's value to the result list.

## Interview Tips
- Pay attention to the problem's requirements and ensure you understand what is being asked.
- Use recursive functions to traverse trees and graphs.
- Keep track of the current node and its children to avoid visiting the same node multiple times.
- Use a list to store the result, and add each node's value to the list in the correct order.

## Optimization and Alternatives
- This solution is already optimized for time and space complexity.
- An alternative solution using iteration instead of recursion is also possible.

## Revision Checklist
- [ ] Understand the problem requirements.
- [ ] Use recursive functions to traverse trees.
- [ ] Keep track of the current node and its children.
- [ ] Use a list to store the result.
- [ ] Add each node's value to the list in the correct order.

## Similar Problems
- N-Ary Tree Preorder Traversal
- N-Ary Tree Inorder Traversal
- Binary Tree Postorder Traversal

## Tags
`Array` `Hash Map`
