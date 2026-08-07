# Path Sum Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Backtracking` `Tree` `Depth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> outer = new ArrayList<>();
    int target;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
      target= targetSum;
      func(root,new ArrayList<>(),0);
      return outer;
    }
    public void func(TreeNode root, List<Integer>inner, int currSum){
      if(root==null) return;
      if(root.left==null && root.right==null){
        if(currSum+root.val==target){
            inner.add(root.val);
            outer.add(new ArrayList<>(inner));
            inner.remove(inner.size()-1);
        }
        return;
      }
      currSum+=root.val;
      inner.add(root.val);
      func(root.left,inner,currSum);
      func(root.right,inner,currSum);
      inner.remove(inner.size()-1);
      return;
      
    }
}
```

---

---

## Quick Revision
The problem is to find all paths in a binary tree that sum up to a given target value.
We solve it by recursively traversing the tree and keeping track of the current path and its sum.

## Intuition
This approach works because we can represent each path as a sequence of node values, and by checking if the current sum plus the current node's value equals the target, we can determine if this is a valid path. We use recursion to explore all possible paths in the tree.

## Algorithm

1. Initialize an outer list to store all valid paths.
2. Define a helper function `func` that takes a TreeNode, an inner list of values representing the current path, and the current sum as arguments.
3. In the `func` function:
	* If the current node is null, return immediately.
	* If the current node has no children (i.e., it's a leaf node), check if adding its value to the current sum equals the target. If so, add this path to the outer list and remove the last added value from the inner list.
	* Otherwise, recursively call `func` on the left and right child nodes, passing in the updated current sum and adding the current node's value to the inner list.

## Concept to Remember

• **Recursion**: we use recursion to explore all possible paths in the tree.
• **Dynamic Programming**: while not explicitly stated, this problem involves a form of dynamic programming where we build up solutions to subproblems (paths) to arrive at the final solution.
• **Tree traversal**: we use a pre-order traversal strategy to visit each node before its children.

## Common Mistakes

• Failing to handle edge cases, such as an empty tree or a single-node tree with a sum of 0.
• Not initializing the outer list correctly, leading to incorrect results.
• Forgetting to remove the last added value from the inner list when backtracking.

## Complexity Analysis
- Time: O(N) where N is the number of nodes in the tree, as we visit each node once.
- Space: O(N) for the recursion stack and the outer list of paths.

## Commented Code

```java
class Solution {
    List<List<Integer>> outer = new ArrayList<>();
    int target;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // Set the target sum for the problem
        target = targetSum;
        
        // Call the helper function to find all valid paths
        func(root, new ArrayList<>(), 0);
        
        return outer; // Return the list of all valid paths
    }

    public void func(TreeNode root, List<Integer> inner, int currSum){
        // Base case: if the current node is null, return immediately
        if (root == null) return;
        
        // If the current node has no children (i.e., it's a leaf node)
        if (root.left == null && root.right == null) {
            // Check if adding its value to the current sum equals the target
            if (currSum + root.val == target) {
                // Add this path to the outer list and remove the last added value from the inner list
                inner.add(root.val);
                outer.add(new ArrayList<>(inner));
                inner.remove(inner.size() - 1); // Remove the last added value
            }
            return;
        }

        // Update the current sum by adding the current node's value
        currSum += root.val;
        
        // Add the current node's value to the inner list
        inner.add(root.val);
        
        // Recursively call func on the left and right child nodes
        func(root.left, inner, currSum); // Update the inner list for both recursive calls
        func(root.right, inner, currSum);
        
        // Remove the last added value from the inner list after both recursive calls
        inner.remove(inner.size() - 1);

    }
}
```

## Interview Tips

• Make sure to explain your approach clearly and concisely.
• Use recursion effectively to explore all possible paths in the tree.
• Pay attention to edge cases and initialize variables correctly.

## Revision Checklist
- [ ] Implement a recursive function `func` to find all valid paths.
- [ ] Initialize an outer list to store all valid paths.
- [ ] Handle edge cases, such as an empty tree or a single-node tree with a sum of 0.

## Similar Problems

* LeetCode: Path Sum (127)
* LeetCode: Binary Tree Maximum Node Value at K Levels (965)

## Tags
`Array`, `Hash Map`, `Tree`, `Recursion`, `Dynamic Programming`
