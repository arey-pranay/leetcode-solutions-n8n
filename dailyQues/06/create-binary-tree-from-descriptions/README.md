# Create Binary Tree From Descriptions

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Tree` `Binary Tree`  
**Time:** O(N)  
**Space:** O(N)

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
    TreeNode[] tarr = new TreeNode[100001];
    public TreeNode createBinaryTree(int[][] descriptions) {
        for(int[] temp : descriptions) tarr[temp[1]] = new TreeNode(temp[1]); 
        TreeNode root = new TreeNode(-1);
        for(int[] temp : descriptions){
            if(tarr[temp[0]] == null){
                tarr[temp[0]] =  new TreeNode(temp[0]);
                root = tarr[temp[0]];
            }
            TreeNode par = tarr[temp[0]];
            if(temp[2] == 1) par.left = tarr[temp[1]];
            else par.right = tarr[temp[1]];
        }    
        return root;
    }
}
// 20 = node(20) 
// 15 = node(15)
// 17 = node(17)
// 50 = null
// 80 = node(80) 
// 19 = node(19)
```

---

---
## Quick Revision
The problem asks to construct a binary tree given an array of descriptions, where each description specifies a parent-child relationship and whether the child is a left or right child.
We can solve this by using a hash map (or an array if values are within bounds) to store and retrieve tree nodes, and then iterating through the descriptions to build the tree structure.

## Intuition
The core idea is that each description tells us how to connect two nodes. We need a way to efficiently find existing nodes or create new ones as we process these connections. A map is perfect for this: we can map node values to their `TreeNode` objects. The challenge is identifying the root. The root node is the only node that is never a child. We can track which nodes are children to find the one that isn't.

## Algorithm
1.  **Initialization**: Create a map (or an array if node values are within a reasonable range, as in the provided solution) to store `TreeNode` objects, mapping their integer values to the `TreeNode` instances.
2.  **Pre-populate Nodes**: Iterate through all `descriptions`. For each description `[parent, child, isLeft]`, if a `TreeNode` for `child` doesn't exist in our map, create it and add it to the map. This ensures all nodes that will be part of the tree are instantiated.
3.  **Build Tree Structure**: Iterate through the `descriptions` again. For each `[parent, child, isLeft]`:
    *   Get the `TreeNode` for the `parent` from the map.
    *   Get the `TreeNode` for the `child` from the map.
    *   If `isLeft` is 1, set the `left` child of the `parent` node to the `child` node.
    *   If `isLeft` is 0, set the `right` child of the `parent` node to the `child` node.
4.  **Identify Root**: To find the root, we can use a set to keep track of all nodes that appear as children. The node that is present in our map but *not* in the set of children is the root. Alternatively, as seen in the provided solution, we can initialize a dummy root and update it whenever we encounter a parent that hasn't been seen before. The last such parent encountered will be the actual root.

## Concept to Remember
*   **Tree Traversal/Construction**: Understanding how to build tree structures from relational data.
*   **Hash Maps/Arrays for Node Management**: Efficiently storing and retrieving node objects by their values.
*   **Identifying the Root Node**: Recognizing that the root is the unique node that is never a child.
*   **Parent-Child Relationships**: Properly interpreting the `isLeft` flag to assign children to the correct side.

## Common Mistakes
*   **Not handling node creation**: Forgetting to create `TreeNode` objects for nodes that are only mentioned as children initially.
*   **Incorrectly identifying the root**: Failing to have a robust method to find the root, especially if the input order is not guaranteed to place the root early.
*   **Off-by-one errors or incorrect indexing**: If using an array, ensuring the indices are handled correctly, especially with potentially large or negative node values (though LeetCode constraints usually simplify this).
*   **Overwriting existing children**: Accidentally overwriting a child link if a parent has multiple descriptions (though the problem statement implies unique parent-child links).

## Complexity Analysis
*   **Time**: O(N), where N is the number of descriptions. We iterate through the descriptions a constant number of times (twice in the provided solution, or once for building and once for finding the root if using a separate set). Map operations (get, put) are O(1) on average.
*   **Space**: O(N), where N is the number of unique nodes. This is for storing the `TreeNode` objects in the map (or array).

## Commented Code
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val; // The value of the node.
 *     TreeNode left; // Pointer to the left child.
 *     TreeNode right; // Pointer to the right child.
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
    // Array to store TreeNode objects, indexed by their values.
    // Size 100001 assumes node values are within [0, 100000].
    TreeNode[] tarr = new TreeNode[100001];

    public TreeNode createBinaryTree(int[][] descriptions) {
        // First pass: Create TreeNode objects for all child nodes.
        // This ensures that any node that will be a child has a corresponding TreeNode instance ready.
        for(int[] temp : descriptions) {
            // If the TreeNode for the child (temp[1]) doesn't exist yet, create it.
            if (tarr[temp[1]] == null) {
                tarr[temp[1]] = new TreeNode(temp[1]);
            }
        }

        // Initialize a dummy root. This will be updated to the actual root later.
        // The value -1 is arbitrary and won't be part of the actual tree.
        TreeNode root = new TreeNode(-1);

        // Second pass: Build the tree structure by connecting parent and child nodes.
        for(int[] temp : descriptions){
            int parentVal = temp[0]; // The value of the parent node.
            int childVal = temp[1];  // The value of the child node.
            int isLeft = temp[2];    // 1 if child is left, 0 if child is right.

            // If the TreeNode for the parent (parentVal) hasn't been created yet,
            // it means this parent is encountered for the first time and is a potential root.
            // Create it and assign it to the root variable.
            if(tarr[parentVal] == null){
                tarr[parentVal] = new TreeNode(parentVal);
                root = tarr[parentVal]; // Update root to this newly found parent.
            }

            // Get the TreeNode object for the parent from our array.
            TreeNode par = tarr[parentVal];
            // Get the TreeNode object for the child from our array.
            TreeNode child = tarr[childVal];

            // Connect the child to the parent based on the isLeft flag.
            if(isLeft == 1) {
                // If isLeft is 1, set the child as the left child of the parent.
                par.left = child;
            } else {
                // If isLeft is 0, set the child as the right child of the parent.
                par.right = child;
            }
        }
        // Return the identified root of the constructed binary tree.
        return root;
    }
}
```

## Interview Tips
*   **Clarify Node Value Range**: Ask about the constraints on node values. If they are small and non-negative, an array is more efficient than a hash map. If they can be large or negative, a `HashMap<Integer, TreeNode>` is necessary.
*   **Explain Root Finding Strategy**: Clearly articulate how you will identify the root node. The "node that is never a child" is a key insight. Discuss both the set-based approach and the dummy-root-update approach.
*   **Handle Edge Cases**: Consider what happens with an empty `descriptions` array, or a single description. The provided solution implicitly handles these.
*   **Walk Through an Example**: Be prepared to trace your algorithm with a small example input to demonstrate its correctness.

## Revision Checklist
- [ ] Understand the problem statement and input format.
- [ ] Choose an appropriate data structure for node lookup (array or map).
- [ ] Implement logic to create `TreeNode` objects for all nodes.
- [ ] Implement logic to establish parent-child links.
- [ ] Implement a robust method to identify the root node.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Construct Binary Tree from Preorder and Inorder Traversal
*   Construct Binary Tree from Inorder and Postorder Traversal
*   Serialize and Deserialize Binary Tree
*   Find Bottom Left Tree Value

## Tags
`Array` `Hash Map` `Tree` `Binary Tree` `Graph`
