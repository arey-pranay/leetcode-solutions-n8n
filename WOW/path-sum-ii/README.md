# Path Sum Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Backtracking` `Tree` `Depth-First Search` `Binary Tree`  
**Time:** O(N)  
**Space:** O(H)

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
Find all root-to-leaf paths where the sum of node values equals a given target.
This is solved using a recursive Depth First Search (DFS) approach.

## Intuition
The core idea is to explore every possible path from the root to a leaf node. As we traverse down a path, we keep track of the current sum of node values. When we reach a leaf node, we check if the accumulated sum matches the target. If it does, we've found a valid path. The "aha moment" comes from realizing that a recursive DFS naturally explores all paths, and by passing the current path and sum down the recursion, we can easily check the condition at each leaf.

## Algorithm
1. Initialize an empty list `outer` to store all valid paths.
2. Initialize a `target` variable with the given `targetSum`.
3. Define a recursive helper function `func` that takes:
    - `root`: the current node.
    - `inner`: a list representing the current path being explored.
    - `currSum`: the sum of node values in the current path so far.
4. **Base Case 1:** If `root` is null, return immediately (end of a branch).
5. **Base Case 2 (Leaf Node):** If `root` is a leaf node (both `root.left` and `root.right` are null):
    - Add the current node's value (`root.val`) to `currSum`.
    - If `currSum` equals `target`:
        - Add `root.val` to the `inner` list.
        - Create a *new* `ArrayList` from `inner` and add it to `outer`. This is crucial to avoid modifying paths already added.
        - Remove `root.val` from `inner` (backtrack).
    - Return.
6. **Recursive Step:**
    - Add `root.val` to `currSum`.
    - Add `root.val` to the `inner` list.
    - Recursively call `func` for the left child: `func(root.left, inner, currSum)`.
    - Recursively call `func` for the right child: `func(root.right, inner, currSum)`.
    - **Backtrack:** Remove `root.val` from the `inner` list after exploring both children. This is essential to correctly form other paths.
7. In the main `pathSum` function, call `func` with the `root`, an empty `ArrayList` for `inner`, and an initial `currSum` of 0.
8. Return the `outer` list.

## Concept to Remember
*   **Depth First Search (DFS):** Systematically explores a tree or graph by going as deep as possible along each branch before backtracking.
*   **Recursion:** A programming technique where a function calls itself to solve smaller instances of the same problem.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Mutable Data Structures in Recursion:** Understanding how passing and modifying lists (like `inner`) in recursive calls can lead to unexpected behavior if not handled carefully (e.g., by creating copies).

## Common Mistakes
*   **Not creating a copy of the path:** When a valid path is found, adding the `inner` list directly to `outer` without creating a `new ArrayList<>(inner)` will result in `outer` containing references to the same list, which will be modified during backtracking, leading to incorrect results.
*   **Incorrect backtracking:** Forgetting to remove the current node's value from the `inner` list after exploring its children will cause incorrect paths to be formed.
*   **Handling null root:** Not having a proper base case for a null root will lead to `NullPointerException`.
*   **Modifying `currSum` incorrectly:** Passing `currSum + root.val` directly to recursive calls without updating `currSum` locally or passing a new sum can lead to incorrect sum calculations. The provided solution correctly updates `currSum` before recursive calls.

## Complexity Analysis
*   **Time:** O(N), where N is the number of nodes in the tree. In the worst case, we visit every node once. For each node, we perform constant time operations (add/remove from list, comparisons). Creating a new list for a valid path takes O(H) time where H is the height of the tree, but this is amortized over all paths.
*   **Space:** O(H) in the average case and O(N) in the worst case (skewed tree), where H is the height of the tree. This is due to the recursion stack depth and the space used by the `inner` list to store the current path. The `outer` list can store up to O(N) paths, each of length up to O(H), so in the worst case, the space for the output can be O(N*H). However, typically, space complexity refers to auxiliary space excluding the output.

## Commented Code
```java
class Solution {
    // List to store all the valid root-to-leaf paths that sum up to the target.
    List<List<Integer>> outer = new ArrayList<>();
    // Stores the target sum we are looking for.
    int target;

    // Main function to initiate the path sum finding process.
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
      // Set the global target variable.
      target = targetSum;
      // Start the recursive helper function from the root.
      // Pass an empty list for the current path and an initial sum of 0.
      func(root, new ArrayList<>(), 0);
      // Return the list containing all valid paths.
      return outer;
    }

    // Recursive helper function to perform DFS and find paths.
    public void func(TreeNode root, List<Integer> inner, int currSum) {
      // Base case 1: If the current node is null, we've gone past a leaf or the tree is empty.
      if (root == null) return;

      // Base case 2: If the current node is a leaf node (no left or right children).
      if (root.left == null && root.right == null) {
        // Check if the sum of the current path plus the leaf node's value equals the target.
        if (currSum + root.val == target) {
          // Add the leaf node's value to the current path list.
          inner.add(root.val);
          // Add a *copy* of the current path to the outer list of results.
          // This is crucial to avoid modifying this path later during backtracking.
          outer.add(new ArrayList<>(inner));
          // Backtrack: Remove the leaf node's value from the current path list.
          inner.remove(inner.size() - 1);
        }
        // Return after processing a leaf node.
        return;
      }

      // Add the current node's value to the running sum for this path.
      currSum += root.val;
      // Add the current node's value to the current path list.
      inner.add(root.val);

      // Recursively call func for the left child.
      // Pass the updated current path and sum.
      func(root.left, inner, currSum);
      // Recursively call func for the right child.
      // Pass the updated current path and sum.
      func(root.right, inner, currSum);

      // Backtrack: Remove the current node's value from the current path list.
      // This is essential so that when we return to the parent call,
      // the 'inner' list correctly represents the path up to that parent.
      inner.remove(inner.size() - 1);
      // Return from the current recursive call.
      return;
    }
}
```

## Interview Tips
1.  **Explain DFS and Backtracking:** Clearly articulate how DFS explores all paths and how backtracking is used to "undo" choices and explore alternative paths.
2.  **Emphasize Copying Paths:** Highlight the importance of `new ArrayList<>(inner)` when adding a valid path to the result list. This is a common pitfall.
3.  **Trace an Example:** Be prepared to walk through a small tree example, showing how the `inner` list and `currSum` change at each step, and how backtracking works.
4.  **Discuss Edge Cases:** Mention handling an empty tree (`root == null`) and a tree with only one node.

## Revision Checklist
- [ ] Understand the problem: find all root-to-leaf paths with a specific sum.
- [ ] Implement DFS recursively.
- [ ] Maintain a current path list.
- [ ] Maintain a current sum.
- [ ] Handle leaf nodes correctly.
- [ ] Check sum at leaf nodes.
- [ ] **Crucially:** Create a *copy* of the path when adding to results.
- [ ] Implement backtracking by removing nodes from the current path.
- [ ] Handle null nodes.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Path Sum (LeetCode 112)
*   Binary Tree Paths (LeetCode 257)
*   Sum Root to Leaf Numbers (LeetCode 129)
*   Find Leaves of Binary Tree (LeetCode 366)

## Tags
`Tree` `Depth-First Search` `Recursion` `Backtracking` `Binary Tree`
