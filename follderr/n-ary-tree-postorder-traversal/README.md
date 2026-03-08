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
Postorder traversal of a N-ary tree, returning a list of node values in the order they were visited.
Solve it by using a recursive function to traverse the tree postorder, adding each node's value to the result list after visiting its children.

## Intuition
The "aha moment" is realizing that we can use a recursive function to traverse the tree postorder. By visiting a node's children first, we can ensure that we add its value to the result list last, which is the correct order for postorder traversal.

## Algorithm
1. Create an empty list to store the result.
2. If the root node is null, return the empty list.
3. Call the recursive function `func` on the root node, passing the result list and the root node.
4. Return the result list.
5. In the `func` function:
	* If the node has no children, add its value to the result list and return.
	* Otherwise, iterate over the node's children and call `func` on each one, passing the result list and the child node.
	* After visiting all children, add the current node's value to the result list.

## Concept to Remember
• Recursion: a technique for solving problems by breaking them down into smaller sub-problems of the same type.
• Postorder traversal: a type of tree traversal where a node is visited after its children.
• List and array data structures: used to store and manipulate collections of data.

## Common Mistakes
• Failing to handle the base case where the root node is null.
• Not using a recursive function to traverse the tree postorder.
• Adding a node's value to the result list before visiting its children.

## Complexity Analysis
- Time: O(N) - where N is the number of nodes in the tree, since we visit each node once.
- Space: O(N) - due to the recursion stack.

## Commented Code
```java
// Create an empty list to store the result
List<Integer> ans = new ArrayList<>();

// If the root node is null, return the empty list
if (root == null) return ans;

// Call the recursive function on the root node
func(root, ans);

// Return the result list
return ans;

// Recursive function to traverse the tree postorder
public void func(Node root, List<Integer> ans) {
    // If the node has no children, add its value to the result list
    if (root.children == null) {
        ans.add(root.val);
        return;
    }

    // Iterate over the node's children and call func on each one
    for (Node n : root.children) {
        func(n, ans);
    }

    // After visiting all children, add the current node's value to the result list
    ans.add(root.val);
}
```

## Interview Tips
• Be prepared to explain the reasoning behind the solution, especially the recursive function.
• Make sure to handle the base case where the root node is null.
• Use a clear and concise naming convention for the variables and functions.

## Revision Checklist
- [ ] Understand the problem and the recursive function.
- [ ] Review the time and space complexity.
- [ ] Practice solving the problem with different inputs.
- [ ] Review the commented code and ensure it is clear and concise.

## Similar Problems
- LeetCode: 589. N-ary Tree Postorder Traversal
- LeetCode: 590. N-ary Tree Postorder Traversal

## Tags
`Array` `Hash Map` `Recursion` `Tree Traversal`
