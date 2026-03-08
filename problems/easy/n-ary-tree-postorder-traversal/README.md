# N Ary Tree Postorder Traversal

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Stack` `Tree` `Depth-First Search`  
**Time:** O(N)  
**Space:** O(N)

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

## Quick Revision
The problem requires traversing a N-ary tree in postorder. To solve it, we use a recursive approach, traversing the children of each node first and then the node itself.

## Intuition
The reason we can solve this problem using a recursive approach is that in postorder traversal, we visit the children of a node before visiting the node itself. This allows us to use a recursive function to traverse the tree, with each recursive call handling the children of the current node.

## Algorithm
1. Check if the root is null. If so, return an empty list.
2. Call the `func` function with the root and an empty list as arguments.
3. In the `func` function:
	* If the node has no children, add its value to the list and return.
	* Otherwise, iterate over the children of the node, calling the `func` function recursively for each one.
	* After visiting all children, add the value of the current node to the list.

## Concept to Remember
* Postorder traversal: visiting children before visiting the node itself.
* Recursive function: a function that calls itself to solve a problem.
* N-ary tree: a tree where each node can have multiple children.

## Common Mistakes
* Forgetting to handle the base case of the recursion (a node with no children).
* Not correctly implementing the postorder traversal order.
* Not using a recursive approach when a problem can be solved using one.

## Complexity Analysis
- Time: O(N) - where N is the number of nodes in the tree, since we visit each node once.
- Space: O(N) - due to the recursive call stack.

## Commented Code
```java
class Solution {
    public List<Integer> postorder(Node root) {
        // Check if root is null, if so return an empty list
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        // Call the func function with the root and an empty list as arguments
        func(root, ans);

        // Return the list of node values in postorder
        return ans;
    }

    public void func(Node root, List<Integer> ans) {
        // If the node has no children, add its value to the list and return
        if(root.children == null) {
            ans.add(root.val);
            return;
        }

        // Iterate over the children of the node, calling the func function recursively for each one
        for(Node n : root.children) {
            func(n, ans);
        }

        // After visiting all children, add the value of the current node to the list
        ans.add(root.val);
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and ask for clarification if needed.
* Use a recursive approach when a problem can be solved using one.
* Pay attention to the base case of the recursion and ensure it is correctly implemented.
* Practice solving problems on a N-ary tree to improve your skills.

## Revision Checklist
- [ ] Understand the problem and the postorder traversal order.
- [ ] Use a recursive approach to solve the problem.
- [ ] Correctly implement the base case of the recursion.
- [ ] Practice solving problems on a N-ary tree.

## Similar Problems
* LeetCode 589: N-ary Tree Postorder Traversal
* LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal

## Tags
`N-ary Tree` `Postorder Traversal` `Recursive Function`

## My Notes
easier question
