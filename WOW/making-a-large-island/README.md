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
Given a binary matrix, find the largest island area after changing at most one 0 to a 1.
We solve this by first identifying all existing islands, calculating their areas, and then checking each 0 to see the maximum island size it can form by connecting adjacent islands.

## Intuition
The core idea is that if we change a '0' to a '1', it can potentially merge adjacent islands. To efficiently calculate the potential merged area, we first need to know the size of each existing island. We can do this by performing a Depth First Search (DFS) or Breadth First Search (BFS) on all connected '1's, assigning a unique ID to each island and storing its area. Once we have this information, we iterate through all '0's. For each '0', we look at its neighbors. If a neighbor belongs to an island, we add that island's area to the current '0's potential area. We must be careful not to double-count areas if multiple neighbors belong to the same island.

## Algorithm
1.  **Island Identification and Area Calculation:**
    *   Initialize a `groupID` matrix of the same dimensions as the input `grid`, filled with zeros. This matrix will store the unique ID of the island each '1' belongs to.
    *   Initialize a `groupArea` HashMap to store the area of each island ID.
    *   Initialize `ID` to 1 (for the first island) and `maxArea` to 0.
    *   Iterate through each cell `(i, j)` of the `grid`.
    *   If `grid[i][j]` is 1 and `groupID[i][j]` is 0 (meaning it's an unvisited part of an island):
        *   Perform a DFS (or BFS) starting from `(i, j)` to find all connected '1's.
        *   During DFS/BFS, mark all visited cells with the current `ID` in the `groupID` matrix.
        *   Calculate the `area` of this island.
        *   Store the `area` in `groupArea` with the key as `ID`.
        *   Update `maxArea` with the maximum of `maxArea` and the current island's `area`.
        *   Increment `ID` for the next island.

2.  **Zero Flipping and Merging:**
    *   Iterate through each cell `(i, j)` of the `grid` again.
    *   If `grid[i][j]` is 0:
        *   Initialize `currentArea` to 1 (for the flipped '0' itself).
        *   Initialize a `HashSet` called `visitedGroups` to keep track of island IDs already considered for this '0'.
        *   Iterate through the four neighbors `(X, Y)` of `(i, j)`.
        *   For each neighbor:
            *   Check if `(X, Y)` is within bounds, is a '1' in the original `grid`, and its `groupID` has not been added to `visitedGroups`.
            *   If these conditions are met:
                *   Add the area of the island `groupID[X][Y]` from `groupArea` to `currentArea`.
                *   Add `groupID[X][Y]` to `visitedGroups`.
        *   Update `maxArea` with the maximum of `maxArea` and `currentArea`.

3.  **Return `maxArea`**. If the grid was all '1's, the initial `maxArea` from step 1 will be the correct answer.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** Essential for identifying connected components (islands) in a grid.
*   **Disjoint Set Union (DSU) / Union-Find:** Can be an alternative to DFS/BFS for grouping connected components and efficiently merging them.
*   **Hash Maps/Sets:** Crucial for storing and efficiently retrieving island areas and for avoiding duplicate island area additions when merging.
*   **Grid Manipulation:** Understanding how to iterate through grids and handle boundary conditions.

## Common Mistakes
*   **Double Counting Island Areas:** When a '0' has multiple neighbors belonging to the same island, failing to use a `HashSet` to track visited island IDs will lead to overcounting.
*   **Not Handling the "All 1s" Case:** If the grid is entirely filled with '1's, the second loop (iterating through '0's) won't run. The initial `maxArea` calculation from the first loop must correctly capture this.
*   **Incorrect Boundary Checks:** Missing checks for out-of-bounds neighbors during DFS/BFS or when checking '0's neighbors.
*   **Modifying Original Grid During DFS:** If the original `grid` is modified directly during DFS to mark visited cells, it can interfere with the second pass where we need to check `grid[i][j] == 0`. Using a separate `groupID` matrix is safer.
*   **Forgetting to Initialize `currentArea` to 1:** When considering a '0', its own value contributes 1 to the potential island size.

## Complexity Analysis
- Time: O(N*N) - reason: We iterate through the grid twice. The first pass (DFS) visits each cell at most once. The second pass iterates through all cells and for each '0', checks its 4 neighbors.
- Space: O(N*N) - reason: The `groupID` matrix takes O(N*N) space. The `groupArea` HashMap can store up to O(N*N) entries in the worst case (many small islands). The recursion stack for DFS can also go up to O(N*N) in the worst case (a single large island).

## Commented Code
```java
class Solution {
    // groupID matrix to store the unique ID of each island.
    // Initialized with 0s, meaning unassigned.
    int[][] groupID;
    // HashMap to store the area of each island ID. Key: island ID, Value: area.
    HashMap<Integer,Integer> groupArea = new HashMap<>();
    // Array to represent the 4 possible neighbor directions (up, right, down, left).
    // neighs[k] and neighs[k+1] give the delta for x and y respectively.
    int[] neighs = new int[]{-1,0,1,0,-1};
    
    // Main function to find the largest island after changing at most one 0 to 1.
    public int largestIsland(int[][] grid) {
       // Get the dimension of the square grid.
       int n = grid.length;
       // Initialize the groupID matrix with the same dimensions as the grid.
       groupID = new int[n][n];
       // Start with island ID 1.
       int ID = 1;
       // Initialize maxArea to 0. This will store the largest island found so far.
       int maxArea = 0;
        
        // First pass: Identify all existing islands, calculate their areas, and assign IDs.
        for(int i =0;i<n;i++){ // Iterate through each row.
            for(int j =0 ; j<n;j++){ // Iterate through each column.
                // If the current cell is land (1) and has not been assigned an island ID yet (0).
                if(grid[i][j]==1 && groupID[i][j]==0) {
                   // Perform DFS to find the area of this island and assign it the current ID.
                   int area = dfs(grid,i,j,ID++); // Increment ID for the next island.
                   // Update maxArea with the largest island found so far.
                   maxArea = Math.max(area,maxArea);
                }
            }
        }
        
        // Second pass: Iterate through all water cells (0) to see if flipping one can create a larger island.
        for(int i=0;i<n;i++){ // Iterate through each row.
            for(int j=0;j<n;j++){ // Iterate through each column.
                // If the current cell is land (1), we can't flip it, so skip.
                if(grid[i][j]==1) continue;
                
                // If the current cell is water (0), calculate the potential island size if flipped.
                // Start with area 1 for the flipped '0' itself.
                int area = 1;
                // Use a HashSet to store the IDs of adjacent islands to avoid double counting.
                HashSet<Integer> vis = new HashSet<>();
                
                // Check all four neighbors of the current water cell.
                for(int k=0;k<4;k++){
                    // Calculate the coordinates of the neighbor.
                    int X = i+neighs[k];
                    int Y = j+neighs[k+1];
                    
                    // Check if the neighbor is within grid bounds, is land (1), and its island ID hasn't been visited yet for this '0'.
                    if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || vis.contains(groupID[X][Y])) continue;
                    
                    // If the neighbor is valid and belongs to an unvisited island:
                    // Add the area of that island to the current potential area.
                    area += groupArea.get(groupID[X][Y]); 
                    // Mark this island ID as visited for the current '0' to prevent double counting.
                    vis.add(groupID[X][Y]);
                }
                // Update maxArea with the largest potential island size found by flipping a '0'.
                maxArea = Math.max(area,maxArea);
            }
        }

        // Return the maximum island area found.
        // If the grid was all '1's, maxArea would have been updated in the first pass.
        // If the grid was all '0's, maxArea would be 1 (flipping one '0').
        return maxArea;
    }
    
    // Depth First Search function to explore an island, assign it an ID, and calculate its area.
    public int dfs(int[][] grid, int x, int y, int ID){
        // Initialize the area of the current island to 1 (for the current cell).
        int area = 1;
        // Assign the current island ID to this cell in the groupID matrix.
        groupID[x][y] = ID;
        // Get the grid dimension.
        int n = grid.length;
        
        // Explore the 4 neighbors of the current cell.
        for(int i=0;i<4;i++){
            // Calculate neighbor coordinates.
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            
            // Check if the neighbor is within bounds, is land (1), and has not been visited/assigned an ID yet.
            if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || groupID[X][Y]!=0) continue;
            
            // If the neighbor is valid and unvisited land:
            // Recursively call DFS on the neighbor, adding its returned area to the current island's area.
            area += dfs(grid, X, Y, ID);
        }
        
        // After exploring all connected land cells for this island, store its total area in the groupArea map.
        groupArea.put(ID,area);
        // Return the calculated area of this island.
        return area;
    }
}
```

## Interview Tips
1.  **Clarify Constraints:** Ask about the grid size, whether it's always square, and if it can be empty. This helps in edge case handling.
2.  **Explain the Two-Pass Approach:** Clearly articulate why a two-pass approach is necessary: first to identify and measure existing islands, and second to explore the impact of flipping a '0'.
3.  **Discuss DFS vs. BFS:** Be prepared to explain why DFS (or BFS) is suitable for island identification and how you'd implement it. Mention the use of a visited set or a separate `groupID` matrix.
4.  **Highlight the `HashSet` for Merging:** Emphasize the importance of the `HashSet` when checking neighbors of a '0' to prevent double-counting areas from the same island.
5.  **Edge Cases:** Be ready to discuss edge cases like an all-'1' grid, an all-'0' grid, or a grid with only one cell.

## Revision Checklist
- [ ] Understand the problem: find the largest island after changing at most one '0' to '1'.
- [ ] Implement DFS/BFS to find connected components (islands).
- [ ] Store island IDs and their corresponding areas.
- [ ] Iterate through '0's and check their neighbors.
- [ ] Use a `HashSet` to avoid double-counting island areas when merging.
- [ ] Handle boundary conditions correctly.
- [ ] Consider the case where the grid is all '1's.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Number of Islands
*   Max Area of Island
*   Island Perimeter
*   Shortest Bridge
*   Surrounded Regions

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Hash Map` `Union-Find`
