# Convert Sorted Array To Binary Search Tree

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Tree` `Binary Search Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(log N)

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
    public TreeNode sortedArrayToBST(int[] nums) {
      return func(nums,0,nums.length-1);
    }
    public TreeNode func(int[] nums, int left, int right){
        if(left>right) return null;
        int mid = left+ (right-left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = func(nums,left,mid-1);
        root.right = func(nums,mid+1,right);
        return root;
    }
}

```

---

---
## Quick Revision
Given a sorted array, construct a height-balanced Binary Search Tree (BST).
This is achieved by recursively picking the middle element as the root and building left and right subtrees from the remaining halves.

## Intuition
The key insight is that to create a *height-balanced* BST from a sorted array, we should always choose the middle element of the current subarray as the root of the subtree. This naturally divides the remaining elements into two roughly equal halves, which will form the left and right subtrees. This recursive partitioning ensures that the resulting BST is as balanced as possible.

## Algorithm
1. Define a recursive helper function `func(nums, left, right)` that takes the array `nums` and the start (`left`) and end (`right`) indices of the current subarray.
2. **Base Case:** If `left > right`, it means the subarray is empty, so return `null`.
3. **Find Middle:** Calculate the middle index `mid` of the current subarray: `mid = left + (right - left) / 2`. This formula prevents potential integer overflow compared to `(left + right) / 2`.
4. **Create Root:** Create a new `TreeNode` with the value `nums[mid]`. This will be the root of the current subtree.
5. **Build Left Subtree:** Recursively call `func` for the left half of the array: `root.left = func(nums, left, mid - 1)`.
6. **Build Right Subtree:** Recursively call `func` for the right half of the array: `root.right = func(nums, mid + 1, right)`.
7. **Return Root:** Return the created `root` node.
8. The initial call to start the process will be `sortedArrayToBST(nums)` which calls `func(nums, 0, nums.length - 1)`.

## Concept to Remember
*   **Binary Search Tree (BST) Properties:** For any node, all values in its left subtree are smaller, and all values in its right subtree are larger.
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Height-Balanced Tree:** A tree where the heights of the two child subtrees of any node differ by at most one.
*   **Array Partitioning:** Efficiently dividing a sorted array into sub-problems.

## Common Mistakes
*   **Incorrect Middle Calculation:** Using `(left + right) / 2` can lead to integer overflow for very large arrays. The `left + (right - left) / 2` approach is safer.
*   **Off-by-One Errors in Indices:** Incorrectly defining the `left` and `right` bounds for recursive calls (e.g., `mid` instead of `mid - 1` for the left subtree).
*   **Not Handling Empty Subarrays:** Failing to return `null` when `left > right`, which is crucial for terminating the recursion.
*   **Forgetting BST Property:** While the algorithm naturally builds a BST from a sorted array, understanding *why* it works relies on the BST property.

## Complexity Analysis
*   **Time:** O(N) - reason: Each element in the array is visited exactly once to create a node. The recursive calls effectively traverse the entire array.
*   **Space:** O(log N) - reason: This is due to the recursion depth. In the worst case (a perfectly balanced tree), the depth of the recursion stack will be logarithmic to the number of nodes (N).

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node
 *     TreeNode left; // Pointer to the left child
 *     TreeNode right; // Pointer to the right child
 *     TreeNode() {} // Default constructor
 *     TreeNode(int val) { this.val = val; } // Constructor with value
 *     TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with value and children
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * Main function to convert a sorted array to a height-balanced BST.
     * @param nums The input sorted integer array.
     * @return The root of the constructed BST.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
      // Initiate the recursive helper function with the entire array range.
      return func(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper function to build the BST from a subarray.
     * @param nums The input sorted integer array.
     * @param left The starting index of the current subarray.
     * @param right The ending index of the current subarray.
     * @return The root of the BST constructed from the subarray.
     */
    public TreeNode func(int[] nums, int left, int right) {
        // Base case: If the left index crosses the right index, the subarray is empty.
        if (left > right) {
            // Return null to signify an empty subtree.
            return null;
        }

        // Calculate the middle index of the current subarray.
        // Using left + (right - left) / 2 prevents potential integer overflow.
        int mid = left + (right - left) / 2;

        // Create a new TreeNode with the value at the middle index. This will be the root of the current subtree.
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build the left subtree using the elements to the left of the middle element.
        // The range for the left subtree is from 'left' to 'mid - 1'.
        root.left = func(nums, left, mid - 1);

        // Recursively build the right subtree using the elements to the right of the middle element.
        // The range for the right subtree is from 'mid + 1' to 'right'.
        root.right = func(nums, mid + 1, right);

        // Return the constructed root node for this subtree.
        return root;
    }
}
```

## Interview Tips
*   **Explain the "Why":** Clearly articulate *why* picking the middle element is crucial for height-balancing. This shows you understand the core principle.
*   **Trace an Example:** Walk through a small sorted array (e.g., `[-10, -3, 0, 5, 9]`) to demonstrate how the recursion works and how the BST is formed.
*   **Discuss Edge Cases:** Mention what happens with an empty array or an array with a single element.
*   **Clarify Constraints:** Ask about potential constraints on the array size or value range, especially regarding integer overflow for the middle index calculation.

## Revision Checklist
- [ ] Understand BST properties.
- [ ] Implement recursive solution.
- [ ] Correctly calculate the middle index.
- [ ] Handle base case (`left > right`).
- [ ] Define correct recursive ranges for left/right subtrees.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing an example.

## Similar Problems
*   Convert BST to Sorted Doubly Linked List
*   Balanced Binary Tree
*   Minimum Height Trees

## Tags
`Array` `Tree` `Depth-First Search` `Binary Search Tree` `Recursion`
