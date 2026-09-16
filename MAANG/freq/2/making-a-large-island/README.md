# Making A Large Island

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search` `Union-Find` `Matrix`  
**Time:** O(N*N)  
**Space:** O(N*N)

---

## Solution (java)

```java
class Solution {
    int[][] groupID;
    HashMap<Integer,Integer> groupArea = new HashMap<>();
    int[] neighs = new int[]{-1,0,1,0,-1};
    public int largestIsland(int[][] grid) {
       int n = grid.length;
       groupID = new int[n][n];
       int ID = 1;
       int maxArea = 0;
        for(int i =0;i<n;i++){
            for(int j =0 ; j<n;j++){
                if(grid[i][j]==1 && groupID[i][j]==0) {
                   int area = dfs(grid,i,j,ID++);
                   maxArea = Math.max(area,maxArea);
                }
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1) continue;
                int area = 1;
                HashSet<Integer> vis = new HashSet<>();
                for(int k=0;k<4;k++){
                    
                    int X = i+neighs[k];
                    int Y = j+neighs[k+1];
                    if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || vis.contains(groupID[X][Y])) continue;
                    
                    area += groupArea.get(groupID[X][Y]); 
                    vis.add(groupID[X][Y]);
                    
                }
                maxArea = Math.max(area,maxArea);
            }
        }

        return maxArea;
    }
    public int dfs(int[][] grid, int x, int y, int ID){
        int area = 1;
        groupID[x][y] = ID;
        int n = grid.length;
        
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || groupID[X][Y]!=0) continue;
            area += dfs(grid, X, Y, ID);
        }
        
        groupArea.put(ID,area);
        return area;
    }
}
// 62,50,00,00,000
// 1,00,00,00,000
```

---

---
## Quick Revision
Given a binary matrix where 0 represents water and 1 represents land, find the largest island area after changing at most one 0 to a 1.
This is solved by first identifying existing islands and their areas, then checking each water cell to see the maximum island size it can form by connecting adjacent islands.

## Intuition
The core idea is that if we flip a '0' to a '1', it can potentially merge adjacent islands. To efficiently calculate the potential merged island size, we first need to know the sizes of all existing islands. We can do this by performing a graph traversal (like DFS or BFS) on all connected '1's, assigning a unique ID to each island and storing its area. Then, for each '0', we look at its neighbors. If a neighbor belongs to an island, we add that island's area to the potential new island size. We must be careful not to double-count areas if multiple neighbors belong to the same island.

## Algorithm
1.  **Island Identification and Area Calculation:**
    *   Initialize a `groupID` matrix of the same dimensions as the input `grid`, filled with zeros. This matrix will store the unique ID of the island each land cell belongs to.
    *   Initialize a `groupArea` HashMap to store the area of each island ID.
    *   Initialize `ID` to 1 (for the first island) and `maxArea` to 0.
    *   Iterate through each cell `(i, j)` of the `grid`:
        *   If `grid[i][j]` is 1 and `groupID[i][j]` is 0 (meaning it's an unvisited land cell):
            *   Perform a Depth First Search (DFS) or Breadth First Search (BFS) starting from `(i, j)` to find all connected land cells.
            *   During the traversal, assign the current `ID` to `groupID[x][y]` for all visited land cells `(x, y)`.
            *   Count the total number of cells in this island (its area).
            *   Store the calculated area in `groupArea` with the key `ID`.
            *   Update `maxArea` with the maximum of `maxArea` and the current island's area.
            *   Increment `ID` for the next island.

2.  **Flipping Water Cells and Merging Islands:**
    *   Iterate through each cell `(i, j)` of the `grid` again:
        *   If `grid[i][j]` is 0 (a water cell):
            *   Initialize `currentArea` to 1 (for the flipped cell itself).
            *   Initialize a `HashSet` called `visitedGroups` to keep track of island IDs already considered for this water cell to avoid double-counting.
            *   For each of the four neighbors `(X, Y)` of `(i, j)`:
                *   Check if `(X, Y)` is within the grid boundaries, is land (`grid[X][Y] == 1`), and its group ID (`groupID[X][Y]`) has not been added to `visitedGroups`.
                *   If these conditions are met:
                    *   Add the area of the island `groupID[X][Y]` from `groupArea` to `currentArea`.
                    *   Add `groupID[X][Y]` to `visitedGroups`.
            *   Update `maxArea` with the maximum of `maxArea` and `currentArea`.

3.  **Handle the case where the entire grid is land:** If no '0' was found, the `maxArea` calculated in step 1 will be the correct answer. If the grid is all '0's, the initial `maxArea` will be 0, and flipping one '0' will result in an area of 1. The algorithm correctly handles this.

4.  Return `maxArea`.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** Essential for identifying connected components (islands) and calculating their sizes.
*   **Disjoint Set Union (DSU) / Union-Find:** Can be an alternative to DFS/BFS for grouping connected components and efficiently merging them.
*   **Hash Maps:** Used to store and retrieve island areas efficiently based on their unique IDs.
*   **Set Data Structure:** Crucial for preventing double-counting of island areas when a water cell has multiple neighbors belonging to the same island.

## Common Mistakes
*   **Double-counting island areas:** When a water cell has multiple neighbors belonging to the same island, failing to use a `HashSet` to track visited island IDs will lead to overestimating the merged island size.
*   **Not handling the "all land" or "all water" edge cases:** The algorithm needs to correctly return `n*n` if the grid is all land, or `1` if it's all water and we flip one cell.
*   **Incorrect boundary checks:** Errors in checking if neighbor coordinates are within the grid bounds can lead to `ArrayIndexOutOfBoundsException` or incorrect logic.
*   **Modifying the original grid during DFS/BFS:** While not strictly wrong, it's often cleaner to use a separate `visited` or `groupID` matrix to avoid altering the input grid if it's intended to be preserved.

## Complexity Analysis
- Time: O(N*N) - reason The grid is traversed a constant number of times (once for DFS/BFS to identify islands, and once to check water cells). Each cell is visited at most a few times.
- Space: O(N*N) - reason For the `groupID` matrix and potentially the recursion stack for DFS (in the worst case, a single large island). The `groupArea` HashMap can store up to N*N/2 entries in the worst case.

## Commented Code
```java
class Solution {
    // groupID matrix to store the unique ID of the island each land cell belongs to.
    // Initialized to 0, meaning unvisited or water.
    int[][] groupID;
    // HashMap to store the area of each island, keyed by its unique ID.
    HashMap<Integer,Integer> groupArea = new HashMap<>();
    // Array to represent the four possible neighbor directions (up, right, down, left).
    // Used for moving between adjacent cells in the grid.
    int[] neighs = new int[]{-1,0,1,0,-1}; // Corresponds to (dx, dy) pairs: (-1,0), (0,1), (1,0), (0,-1)

    // Main function to find the largest island area after changing at most one '0' to '1'.
    public int largestIsland(int[][] grid) {
       int n = grid.length; // Get the dimension of the square grid.
       groupID = new int[n][n]; // Initialize the groupID matrix.
       int ID = 1; // Start with island ID 1.
       int maxArea = 0; // Initialize the maximum area found so far.

        // First pass: Identify all existing islands, assign them unique IDs, and calculate their areas.
        for(int i =0;i<n;i++){ // Iterate through each row.
            for(int j =0 ; j<n;j++){ // Iterate through each column.
                // If the current cell is land (1) and hasn't been assigned an island ID yet (0).
                if(grid[i][j]==1 && groupID[i][j]==0) {
                   // Perform DFS to find the area of this new island and assign it the current ID.
                   int area = dfs(grid,i,j,ID++); // Increment ID for the next island.
                   // Update maxArea with the largest island found so far.
                   maxArea = Math.max(area,maxArea);
                }
            }
        }
        
        // Second pass: Iterate through all water cells ('0') and calculate the potential island size if flipped.
        for(int i=0;i<n;i++){ // Iterate through each row.
            for(int j=0;j<n;j++){ // Iterate through each column.
                // If the current cell is land, we've already accounted for its island in the first pass. Skip.
                if(grid[i][j]==1) continue;

                // If the current cell is water ('0'), calculate the potential merged island size.
                int area = 1; // Start with area 1 for the flipped water cell itself.
                HashSet<Integer> vis = new HashSet<>(); // Set to keep track of visited island IDs for this water cell.
                                                        // This prevents double-counting if multiple neighbors belong to the same island.
                
                // Check all four neighbors of the current water cell.
                for(int k=0;k<4;k++){
                    
                    int X = i+neighs[k]; // Calculate neighbor's row coordinate.
                    int Y = j+neighs[k+1]; // Calculate neighbor's column coordinate.

                    // Check if the neighbor is within grid bounds, is land, and its island ID hasn't been processed yet for this water cell.
                    if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || vis.contains(groupID[X][Y])) continue;
                    
                    // If the neighbor is valid and belongs to an uncounted island:
                    // Add the area of that island to the current potential merged area.
                    area += groupArea.get(groupID[X][Y]); 
                    // Mark this island ID as visited for this water cell.
                    vis.add(groupID[X][Y]);
                    
                }
                // Update maxArea with the largest potential island size found by flipping a water cell.
                maxArea = Math.max(area,maxArea);
            }
        }

        // If the grid was entirely land, maxArea would be n*n from the first pass.
        // If the grid was entirely water, maxArea would be 0, and flipping one cell makes it 1.
        // The logic correctly handles these cases.
        return maxArea; // Return the overall maximum island area.
    }

    // Depth First Search function to traverse an island, assign IDs, and calculate its area.
    public int dfs(int[][] grid, int x, int y, int ID){
        int area = 1; // Initialize area for the current cell.
        groupID[x][y] = ID; // Assign the current island ID to this cell.
        int n = grid.length; // Get grid dimension.
        
        // Explore all four neighbors.
        for(int i=0;i<4;i++){
            int X = x + neighs[i]; // Neighbor's row.
            int Y = y + neighs[i+1]; // Neighbor's column.

            // Check if the neighbor is within bounds, is land, and has not been visited/assigned an ID yet.
            if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || groupID[X][Y]!=0) continue;
            
            // Recursively call DFS for the valid neighbor and add its returned area to the current island's area.
            area += dfs(grid, X, Y, ID);
        }
        
        // After exploring all connected land cells for this island, store its total area.
        groupArea.put(ID,area);
        // Return the total area of this island.
        return area;
    }
}
```

## Interview Tips
*   **Clarify the problem:** Ensure you understand that you can change *at most one* '0' to a '1'. This means the original largest island is also a candidate for the answer.
*   **Explain your two-pass approach:** Clearly articulate why you need to first identify existing islands and then consider flipping water cells. This demonstrates structured thinking.
*   **Address edge cases:** Discuss how you handle grids that are entirely land or entirely water. This shows thoroughness.
*   **Explain the `HashSet` for avoiding double-counting:** This is a critical detail for correctness and efficiency. Emphasize why it's necessary when a water cell has multiple neighbors belonging to the same island.

## Revision Checklist
- [ ] Understand the problem statement: change at most one '0' to '1'.
- [ ] Identify existing islands and their areas using DFS/BFS.
- [ ] Assign unique IDs to each island.
- [ ] Store island areas in a map.
- [ ] Iterate through water cells ('0').
- [ ] For each water cell, check its neighbors.
- [ ] Sum areas of adjacent, distinct islands.
- [ ] Use a `HashSet` to avoid double-counting island areas.
- [ ] Handle the case where the grid is all land.
- [ ] Handle the case where the grid is all water.
- [ ] Calculate time and space complexity.

## Similar Problems
*   Number of Islands
*   Max Area of Island
*   Island Perimeter
*   Flood Fill

## Tags
`Array` `Hash Map` `Depth-First Search` `Breadth-First Search` `Union-Find`
