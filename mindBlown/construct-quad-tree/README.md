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
This is solved using a recursive divide and conquer approach.

## Intuition
The core idea behind constructing a Quad Tree is to recursively divide a square region into four equal quadrants. If a region is homogeneous (all cells have the same value), it becomes a leaf node. Otherwise, it's an internal node, and we recursively build its four children. The "aha moment" is realizing that we can check for homogeneity by iterating through the current region. If we find any cell with a different value than the top-left cell of that region, we know it's not homogeneous and must be split.

## Algorithm
1.  Define a recursive function `func(grid, x, y, width)` that takes the grid, the starting row `x`, starting column `y`, and the current `width` of the square region as input.
2.  Inside `func`, determine the value of the top-left cell `grid[x][y]`. This will be the potential value for the current node if it's a leaf.
3.  Initialize a new `Node` with this value and `isLeaf` set to `true`.
4.  Iterate through the entire `width x width` region starting from `(x, y)`.
5.  If any cell within this region has a value different from `grid[x][y]`, set the `isLeaf` property of the current `Node` to `false` and break out of the loops.
6.  If after checking the entire region, `isLeaf` is still `true`, return the current `Node` (it's a homogeneous leaf).
7.  If `isLeaf` is `false`, it means the region needs to be divided. Recursively call `func` for each of the four quadrants:
    *   Top-Left: `func(grid, x, y, width / 2)`
    *   Top-Right: `func(grid, x, y + width / 2, width / 2)`
    *   Bottom-Left: `func(grid, x + width / 2, y, width / 2)`
    *   Bottom-Right: `func(grid, x + width / 2, y + width / 2, width / 2)`
8.  Assign the results of these recursive calls to the `topLeft`, `topRight`, `bottomLeft`, and `bottomRight` children of the current `Node`.
9.  Return the current `Node`.
10. The initial call will be `func(grid, 0, 0, grid.length)`.

## Concept to Remember
*   **Recursion:** The problem naturally lends itself to a recursive solution where a larger problem is broken down into smaller, identical subproblems.
*   **Divide and Conquer:** This is a classic application of the divide and conquer paradigm, where a problem is divided into subproblems, solved independently, and then combined.
*   **Quad Tree Structure:** Understanding how a Quad Tree represents spatial data by recursively subdividing a 2D space.

## Common Mistakes
*   **Incorrect Base Case:** Not correctly identifying when a region is homogeneous and should become a leaf node.
*   **Off-by-One Errors in Recursion:** Miscalculating the starting coordinates or dimensions for the recursive calls to the four quadrants.
*   **Modifying Grid In-Place (Unnecessary):** While not directly applicable to this solution, some might consider modifying the grid, which is usually not the intended approach for construction problems.
*   **Not Handling Grid Dimensions:** Assuming the grid is always a perfect power of 2, which is usually the case for Quad Tree problems but good to be mindful of.

## Complexity Analysis
*   **Time:** O(N^2), where N is the dimension of the grid (e.g., N x N). Each cell in the grid is visited a constant number of times during the homogeneity check across all recursive calls. The total number of nodes in the Quad Tree is at most O(N^2).
*   **Space:** O(log N) due to the recursion depth. In the worst case (a checkerboard pattern), the recursion depth can be log N. In a perfectly homogeneous grid, it's O(log N). The space for the Quad Tree itself can be up to O(N^2) in the worst case.

## Commented Code
```java
/*
// Definition for a QuadTree node.
class Node {
    public boolean val; // Stores the value (true for 1, false for 0) if it's a leaf node.
    public boolean isLeaf; // Indicates if this node is a leaf.
    public Node topLeft; // Pointer to the top-left child.
    public Node topRight; // Pointer to the top-right child.
    public Node bottomLeft; // Pointer to the bottom-left child.
    public Node bottomRight; // Pointer to the bottom-right child.

    // Default constructor
    public Node() {
        this.val = false; // Default value
        this.isLeaf = false; // Default to not a leaf
        this.topLeft = null; // Initialize children to null
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    // Constructor with value and isLeaf flag
    public Node(boolean val, boolean isLeaf) {
        this.val = val; // Set the value
        this.isLeaf = isLeaf; // Set the leaf status
        this.topLeft = null; // Initialize children to null
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    // Full constructor
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val; // Set the value
        this.isLeaf = isLeaf; // Set the leaf status
        this.topLeft = topLeft; // Assign children
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    // Main function to start the quad tree construction.
    public Node construct(int[][] grid) {
        // Call the recursive helper function starting from (0,0) with the full grid dimension.
        return func(grid,0,0,grid.length);
    }

    // Recursive helper function to build the quad tree for a given subgrid.
    // x: starting row index of the current subgrid.
    // y: starting column index of the current subgrid.
    // w: width (and height) of the current square subgrid.
    public Node func(int[][] grid,int x , int y, int w){
        // Get the value of the top-left cell of the current subgrid.
        int val = grid[x][y];
        // Convert the integer value (0 or 1) to a boolean.
        boolean bool = val == 1 ? true : false;
        // Create a new node. Initially assume it's a leaf with the determined boolean value.
        Node root = new Node(bool,true);
        
        // Iterate through the entire current subgrid to check if it's homogeneous.
        for(int i=x;i<x+w;i++){ // Loop through rows of the subgrid.
            for(int j=y;j<y+w;j++){ // Loop through columns of the subgrid.
                // If any cell's value is different from the top-left cell's value...
                if(grid[i][j]!=val) {
                    // ...then this subgrid is not homogeneous. Mark the node as not a leaf.
                    root.isLeaf = false;
                    // Break out of the inner loop.
                    break;
                }
            }
            // If we've already determined it's not a leaf, no need to check further rows.
            if(root.isLeaf == false) break;
        }
        
        // If the subgrid was found to be homogeneous (isLeaf is still true)...
        if(root.isLeaf) return root; // ...return this leaf node.
        
        // If the subgrid is not homogeneous, we need to divide it into four quadrants.
        // Recursively call func for each quadrant, dividing the width by 2.
        // Top-Left quadrant: starts at (x, y) with width w/2.
        root.topLeft = func(grid,x,y,w/2);
        // Top-Right quadrant: starts at (x, y + w/2) with width w/2.
        root.topRight = func(grid,x,y+w/2,w/2);
        // Bottom-Left quadrant: starts at (x + w/2, y) with width w/2.
        root.bottomLeft = func(grid,x+w/2,y,w/2);
        // Bottom-Right quadrant: starts at (x + w/2, y + w/2) with width w/2.
        root.bottomRight = func(grid,x+w/2,y+w/2,w/2);
        
        // Return the current node, which is now an internal node with its children populated.
        return root;
    }
}
```

## Interview Tips
*   **Explain the Quad Tree Structure:** Before diving into code, clearly explain what a Quad Tree is and how it represents spatial data.
*   **Walk Through a Small Example:** Use a 2x2 or 4x4 grid to manually trace the recursion and show how the tree is built. This demonstrates your understanding of the divide-and-conquer process.
*   **Focus on the Homogeneity Check:** Emphasize how you determine if a region is a leaf node. This is the critical decision point in the algorithm.
*   **Discuss Edge Cases (Implicitly):** While the problem usually guarantees a grid size that's a power of 2, be prepared to discuss how you'd handle non-square or non-power-of-2 grids if asked, though it's unlikely for this specific problem.

## Revision Checklist
- [ ] Understand the Quad Tree node structure.
- [ ] Implement the recursive `func` helper.
- [ ] Correctly identify leaf nodes (homogeneous regions).
- [ ] Correctly calculate coordinates and dimensions for recursive calls.
- [ ] Handle the base case of the recursion.
- [ ] Analyze time and space complexity.

## Similar Problems
*   297. Serialize and Deserialize Binary Tree
*   101. Symmetric Tree
*   116. Populating Next Right Pointers in Each Node
*   117. Populating Next Right Pointers in Each Node II

## Tags
`Recursion` `Divide and Conquer` `Tree` `Matrix`
