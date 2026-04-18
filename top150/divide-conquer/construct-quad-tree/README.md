# Construct Quad Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Tree` `Matrix`  
**Time:** O(N^2)  
**Space:** O(log N)

---

## Solution (java)

```java
/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] grid) {
        return func(grid,0,0,grid.length);
    }
    public Node func(int[][] grid,int x , int y, int w){
        int val = grid[x][y];
        boolean bool = val == 1 ? true : false;
        Node root = new Node(bool,true);
        for(int i=x;i<x+w;i++){
            for(int j=y;j<y+w;j++){
                if(grid[i][j]!=val) {
                    root.isLeaf = false;
                    break;
                }
            }
            if(root.isLeaf == false) break;
        }
        if(root.isLeaf) return root;
        root.topLeft = func(grid,x,y,w/2);
        root.topRight = func(grid,x,y+w/2,w/2);
        root.bottomLeft = func(grid,x+w/2,y,w/2);
        root.bottomRight = func(grid,x+w/2,y+w/2,w/2);
        return root;
    }
}

```

---

---
## Quick Revision
The problem asks to build a Quad Tree from a 2D grid of 0s and 1s.
This is solved using a recursive divide-and-conquer approach.

## Intuition
The core idea of a Quad Tree is to represent a 2D grid by recursively dividing it into four quadrants. If a quadrant is homogeneous (all cells have the same value), it becomes a leaf node. Otherwise, it's an internal node whose children are the Quad Trees of its four sub-quadrants. The "aha moment" is realizing that we can check for homogeneity within a given square region and, if it's not homogeneous, recursively call the construction function on its four sub-regions.

## Algorithm
1.  Define a recursive function `func(grid, x, y, width)` that constructs a Quad Tree for the square region of the `grid` starting at `(x, y)` with dimensions `width x width`.
2.  Inside `func`:
    a.  Determine the value of the top-left cell `grid[x][y]` as the potential value for the current node.
    b.  Initialize a new `Node` with this value and `isLeaf` set to `true`.
    c.  Iterate through the entire `width x width` region starting from `(x, y)`.
    d.  If any cell within this region has a different value than the initial `val`, set `root.isLeaf` to `false` and break out of the loops.
    e.  If after checking the entire region, `root.isLeaf` is still `true`, return the current `root` node (it's a homogeneous leaf).
    f.  If `root.isLeaf` is `false`, it means the region is not homogeneous. Recursively call `func` for the four sub-quadrants:
        *   Top-Left: `func(grid, x, y, width / 2)`
        *   Top-Right: `func(grid, x, y + width / 2, width / 2)`
        *   Bottom-Left: `func(grid, x + width / 2, y, width / 2)`
        *   Bottom-Right: `func(grid, x + width / 2, y + width / 2, width / 2)`
    g.  Assign the results of these recursive calls to `root.topLeft`, `root.topRight`, `root.bottomLeft`, and `root.bottomRight` respectively.
    h.  Return the `root` node.
3.  The main `construct(grid)` function will initiate the process by calling `func(grid, 0, 0, grid.length)`.

## Concept to Remember
*   **Recursion:** The problem naturally lends itself to a recursive solution where a larger problem is broken down into smaller, self-similar subproblems.
*   **Divide and Conquer:** This is a classic application of the divide and conquer paradigm, where a problem is divided into subproblems, solved independently, and then combined.
*   **Quad Tree Data Structure:** Understanding how a Quad Tree represents spatial data by recursively partitioning a 2D space.

## Common Mistakes
*   **Incorrect Base Case:** Not properly handling the case where a region is homogeneous and should become a leaf node.
*   **Off-by-One Errors in Recursion:** Miscalculating the starting coordinates or dimensions for the four sub-quadrants, leading to incorrect region processing.
*   **Inefficient Homogeneity Check:** Iterating through the entire sub-grid for every node, even when it's already determined to be non-leaf. The provided solution optimizes this by breaking early.
*   **Not Handling Grid Dimensions:** Assuming the grid is always square and a power of 2, which is usually the case for this problem but good to be mindful of.

## Complexity Analysis
*   Time: O(N^2) - where N is the side length of the grid. Each cell in the grid is visited a constant number of times. The homogeneity check for a region of size `k x k` takes O(k^2) time. Since we divide the grid into four sub-regions, the recurrence relation is T(N) = 4T(N/2) + O(N^2) for the initial check, but the homogeneity check is optimized to break early. A tighter analysis shows that each cell is effectively visited a constant number of times across all recursive calls.
*   Space: O(log N) - due to the recursion depth. The maximum depth of the recursion is log base 2 of N, as we keep dividing the grid size by 2. The space is used for the call stack.

## Commented Code
```java
/*
// Definition for a QuadTree node.
class Node {
    public boolean val; // Stores the value (true for 1, false for 0) if it's a leaf node.
    public boolean isLeaf; // True if this node is a leaf node, false otherwise.
    public Node topLeft; // Pointer to the top-left child node.
    public Node topRight; // Pointer to the top-right child node.
    public Node bottomLeft; // Pointer to the bottom-left child node.
    public Node bottomRight; // Pointer to the bottom-right child node.
    
    // Default constructor
    public Node() {
        this.val = false; // Default value
        this.isLeaf = false; // Default to not a leaf
        this.topLeft = null; // Initialize children to null
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    // Constructor for leaf nodes
    public Node(boolean val, boolean isLeaf) {
        this.val = val; // Set the value
        this.isLeaf = isLeaf; // Mark as a leaf
        this.topLeft = null; // Leaf nodes have no children
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    // Constructor for internal nodes
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val; // Value is typically irrelevant for internal nodes, but can be set.
        this.isLeaf = isLeaf; // Mark as not a leaf
        this.topLeft = topLeft; // Assign children
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    // Main function to construct the Quad Tree from the grid.
    public Node construct(int[][] grid) {
        // Start the recursive construction process from the top-left corner (0,0)
        // with the full grid dimension (grid.length).
        return func(grid,0,0,grid.length);
    }
    
    // Recursive helper function to build the Quad Tree for a given square region.
    // x: starting row index of the region
    // y: starting column index of the region
    // w: width (and height) of the square region
    public Node func(int[][] grid,int x , int y, int w){
        // Get the value of the top-left cell in the current region.
        // This will be the potential value for this node if it's a leaf.
        int val = grid[x][y];
        // Convert the integer value (0 or 1) to a boolean.
        boolean bool = val == 1 ? true : false;
        // Create a new node, initially assuming it's a leaf with the determined boolean value.
        Node root = new Node(bool,true);
        
        // Iterate through the entire current region (w x w) to check for homogeneity.
        for(int i=x;i<x+w;i++){ // Iterate through rows
            for(int j=y;j<y+w;j++){ // Iterate through columns
                // If any cell in the region has a different value than the initial 'val'...
                if(grid[i][j]!=val) {
                    // ...then this region is not homogeneous.
                    root.isLeaf = false; // Mark the current node as not a leaf.
                    break; // Exit the inner loop (columns).
                }
            }
            // If we've already determined it's not a leaf, no need to check further rows.
            if(root.isLeaf == false) break; // Exit the outer loop (rows).
        }
        
        // If after checking the entire region, the node is still marked as a leaf...
        if(root.isLeaf) {
            // ...it means the region is homogeneous, so return this leaf node.
            return root;
        }
        
        // If the region is not homogeneous (root.isLeaf is false), we need to divide it into four children.
        // Recursively call 'func' for each of the four sub-quadrants, each with half the width.
        
        // Top-Left quadrant: starts at (x, y) with width w/2.
        root.topLeft = func(grid,x,y,w/2);
        // Top-Right quadrant: starts at (x, y + w/2) with width w/2.
        root.topRight = func(grid,x,y+w/2,w/2);
        // Bottom-Left quadrant: starts at (x + w/2, y) with width w/2.
        root.bottomLeft = func(grid,x+w/2,y,w/2);
        // Bottom-Right quadrant: starts at (x + w/2, y + w/2) with width w/2.
        root.bottomRight = func(grid,x+w/2,y+w/2,w/2);
        
        // Return the current node, which is now an internal node with its four children.
        return root;
    }
}
```

## Interview Tips
1.  **Explain the Quad Tree Concept:** Before diving into code, clearly explain what a Quad Tree is and how it represents spatial data. This shows you understand the problem domain.
2.  **Walk Through a Small Example:** Use a 2x2 or 4x4 grid to manually trace the recursive calls and how the tree is built. This helps the interviewer follow your logic.
3.  **Discuss Base Cases and Recursive Steps:** Emphasize the conditions for creating a leaf node (homogeneity) and how the problem is broken down into smaller subproblems for internal nodes.
4.  **Clarify Grid Assumptions:** Mention that the grid is assumed to be square and its dimensions are powers of 2, as this simplifies the division logic. If not, you'd need to handle padding or different division strategies.

## Revision Checklist
- [ ] Understand the Quad Tree node structure.
- [ ] Implement the recursive `construct` function.
- [ ] Correctly identify homogeneous regions for leaf nodes.
- [ ] Properly divide non-homogeneous regions into four sub-quadrants.
- [ ] Handle base cases and recursive calls accurately.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Binary Tree Serialization and Deserialization
*   Convert Sorted Array to Binary Search Tree
*   Maximum Depth of Binary Tree

## Tags
`Recursion` `Divide and Conquer` `Tree` `Depth-First Search` `Matrix`
