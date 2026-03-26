# Sum Root To Leaf Numbers

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Depth-First Search` `Binary Tree`  
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
    int s = 0;
    int sum = 0 ;
    public int sumNumbers(TreeNode root) {
        if(root==null) return 0;
        s = s*10 + root.val;
        if(root.left==null && root.right==null){
            sum += s;
            s /= 10;
            return sum;
        }  
        sumNumbers(root.left);
        sumNumbers(root.right);
        s /= 10;
        return sum;
    }
}
```

---

---
## Quick Revision
Given a binary tree where each node contains a digit, calculate the sum of all numbers formed by root-to-leaf paths.
This is solved using a recursive Depth First Search (DFS) approach to traverse paths and build numbers.

## Intuition
The core idea is to traverse the tree from the root down to each leaf. As we traverse, we can build the number represented by the path from the root to the current node. When we reach a leaf node, the number we've built is a complete root-to-leaf number, and we add it to our total sum.

The "aha moment" comes from realizing that we can maintain a running "current number" as we go down the tree. For each node, we append its digit to the current number by multiplying the current number by 10 and adding the node's value. When we backtrack (return from a recursive call), we need to "undo" this append operation to ensure the correct number is formed for sibling paths.

## Algorithm
1. Initialize a global variable `totalSum` to 0.
2. Initialize a global variable `currentNumber` to 0.
3. Define a recursive helper function `dfs(TreeNode node)`:
    a. If `node` is null, return.
    b. Update `currentNumber`: `currentNumber = currentNumber * 10 + node.val`.
    c. Check if `node` is a leaf node (i.e., `node.left` is null AND `node.right` is null).
        i. If it's a leaf, add `currentNumber` to `totalSum`.
    d. Recursively call `dfs` on the left child: `dfs(node.left)`.
    e. Recursively call `dfs` on the right child: `dfs(node.right)`.
    f. **Backtrack**: Before returning from the current `dfs` call, "remove" the current node's digit from `currentNumber` to prepare for sibling paths. This is done by `currentNumber = currentNumber / 10`.
4. Call the `dfs` function starting from the `root` node.
5. Return `totalSum`.

## Concept to Remember
*   **Tree Traversal (DFS):** Understanding how to systematically visit every node in a tree, particularly using recursion.
*   **Path Summation:** Accumulating values along a specific path from the root to a leaf.
*   **State Management in Recursion:** Effectively passing and updating information (like the current number) across recursive calls and handling backtracking.
*   **Integer Manipulation:** Using arithmetic operations (multiplication and division by 10) to build and deconstruct numbers digit by digit.

## Common Mistakes
*   **Incorrect Backtracking:** Forgetting to divide `currentNumber` by 10 when returning from a recursive call, leading to incorrect numbers for sibling paths.
*   **Modifying Global State Incorrectly:** If `currentNumber` is not properly managed during backtracking, it can lead to incorrect sums. The provided solution has a slight issue with how `s` is managed globally.
*   **Handling Null Nodes:** Not properly checking for null children before making recursive calls.
*   **Off-by-one Errors in Number Construction:** Incorrectly multiplying or adding the node's value, leading to malformed numbers.
*   **Not Identifying Leaf Nodes Correctly:** Failing to recognize when a node is a leaf and thus when a complete number has been formed.

## Complexity Analysis
*   **Time:** O(N) - where N is the number of nodes in the binary tree. We visit each node exactly once during the DFS traversal.
*   **Space:** O(H) - where H is the height of the binary tree. This is due to the recursion call stack. In the worst case (a skewed tree), H can be N, making it O(N). In a balanced tree, H is log N, making it O(log N).

## Commented Code
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
    // 's' is used to build the current number along the path from root to current node.
    // It's a global variable to be accessible across recursive calls.
    int s = 0;
    // 'sum' is the total sum of all root-to-leaf numbers.
    // It's a global variable to accumulate the results.
    int sum = 0 ;

    // The main function to initiate the process.
    public int sumNumbers(TreeNode root) {
        // If the tree is empty, there are no numbers to sum, so return 0.
        if(root==null) return 0;

        // Append the current node's value to the number being built.
        // Multiply 's' by 10 to shift existing digits to the left, then add the current digit.
        s = s*10 + root.val;

        // Check if the current node is a leaf node (no left and no right children).
        if(root.left==null && root.right==null){
            // If it's a leaf, the number 's' represents a complete root-to-leaf number.
            // Add this number to the total sum.
            sum += s;
            // **Crucial Backtracking Step**: Before returning, remove the last digit added (root.val)
            // from 's' so that when the recursion unwinds to the parent, 's' correctly represents
            // the number up to the parent. This is done by integer division by 10.
            s /= 10;
            // Return the current total sum. Note: This return is problematic as it doesn't
            // allow sibling paths to be processed correctly if this leaf is not the last node visited.
            // A better approach would be to not return here and let the recursion unwind naturally.
            return sum;
        }

        // Recursively call sumNumbers on the left child.
        // This will continue building the number down the left subtree.
        sumNumbers(root.left);
        // Recursively call sumNumbers on the right child.
        // This will continue building the number down the right subtree.
        sumNumbers(root.right);

        // **Crucial Backtracking Step**: After visiting both left and right children,
        // we are returning from the current node's call. We must remove the current node's
        // digit from 's' so that when the recursion unwinds to the parent, 's' correctly
        // represents the number up to the parent.
        s /= 10;
        // Return the accumulated total sum. This return value is what propagates up the call stack.
        return sum;
    }
}
```
*Self-correction note on the provided code: The way `s` and `sum` are managed globally and the early returns in the leaf node condition can lead to incorrect results if not carefully handled. A more robust approach would be to pass `currentNumber` as a parameter and return only the final `totalSum` at the end of the initial call.*

## Interview Tips
1.  **Explain the DFS approach:** Clearly articulate how you'll traverse the tree and build numbers.
2.  **Emphasize Backtracking:** Highlight the importance of "undoing" the addition of a digit when returning from a recursive call to ensure correct path sums.
3.  **Discuss State Management:** Explain how you're managing the `currentNumber` (e.g., using a global variable or passing it as a parameter) and the `totalSum`.
4.  **Consider Edge Cases:** Mention how you'd handle an empty tree (`root == null`).
5.  **Clarify the provided solution's logic:** If asked to analyze the given code, point out the global variable usage and the backtracking mechanism, and potentially suggest improvements for clarity or robustness.

## Revision Checklist
- [ ] Understand the problem: sum of root-to-leaf numbers.
- [ ] Identify the core technique: Depth First Search (DFS).
- [ ] Implement number building logic: `current_num = current_num * 10 + node.val`.
- [ ] Implement leaf node detection: `node.left == null && node.right == null`.
- [ ] Implement backtracking: `current_num = current_num / 10`.
- [ ] Handle null root case.
- [ ] Analyze Time and Space Complexity.

## Similar Problems
*   Path Sum
*   Binary Tree Paths
*   Sum of Left Leaves
*   Average of Levels in Binary Tree

## Tags
`Tree` `Depth-First Search` `Recursion` `Binary Tree`

## My Notes
beats 100%, elegant
