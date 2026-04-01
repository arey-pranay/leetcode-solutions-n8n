# Number Of Islands

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search` `Matrix`  
**Time:** O(M * N)  
**Space:** O(M * N)

---

## Solution (java)

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    dfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;   
    }
    public void dfs(int x, int y, char[][] grid){
        if(x < 0 || y<0 || x>= grid.length || y>= grid[0].length || grid[x][y] != '1') return;        
        grid[x][y] = '0';
        int[] arr = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + arr[i];
            int Y = y + arr[i+1];
            dfs(X,Y,grid);
        }   
        return;     
    }
}
```

---

---
## Quick Revision
Given a 2D grid of '1's (land) and '0's (water), count the number of distinct islands.
We solve this by iterating through the grid, and whenever we find a piece of land ('1'), we increment our island count and then use DFS (or BFS) to "sink" all connected land cells to avoid recounting them.

## Intuition
The core idea is to treat each connected component of '1's as a single island. When we encounter a '1', it signifies the start of a new island that we haven't counted yet. To ensure we don't count parts of the same island multiple times, we need a way to mark all connected land cells as visited or "processed." Depth-First Search (DFS) or Breadth-First Search (BFS) are perfect for exploring these connected components. By changing the '1's to '0's (or any other marker) as we visit them, we effectively "sink" the island, ensuring that each island is counted exactly once.

## Algorithm
1. Initialize an `island_count` to 0.
2. Iterate through each cell `(i, j)` of the `grid`.
3. If the current cell `grid[i][j]` is '1' (land):
    a. Increment `island_count`.
    b. Start a traversal (DFS or BFS) from this cell `(i, j)` to find and mark all connected land cells.
    c. During the traversal, change each visited '1' to '0' (or any other marker) to signify it has been processed.
4. After iterating through all cells, return `island_count`.

## Concept to Remember
*   **Graph Traversal:** This problem can be modeled as finding connected components in a graph where land cells are nodes and adjacency is defined by horizontal/vertical neighbors. DFS and BFS are standard algorithms for graph traversal.
*   **In-place Modification:** Modifying the input grid to mark visited cells is an efficient way to avoid using extra space for a visited set.
*   **Grid as an Adjacency Matrix:** A 2D grid can be viewed as a graph where each cell is a vertex, and edges exist between adjacent cells.

## Common Mistakes
*   **Not marking visited cells:** Failing to change '1's to '0's (or similar) will lead to recounting parts of the same island.
*   **Incorrect boundary checks:** Not properly handling out-of-bounds accesses in the DFS/BFS traversal can cause errors.
*   **Diagonal traversal:** The problem typically defines islands as connected horizontally or vertically, not diagonally. Including diagonal checks will lead to incorrect results.
*   **Infinite recursion/loops:** Without proper base cases or marking visited nodes, DFS can get stuck in infinite loops.

## Complexity Analysis
*   **Time:** O(M * N) - Each cell in the grid is visited at most a constant number of times (once by the outer loops and at most once by DFS/BFS).
*   **Space:** O(M * N) - In the worst case (a grid full of land), the recursion depth of DFS can be proportional to the number of cells, leading to O(M * N) space complexity for the call stack. If BFS is used with a queue, the queue could also store up to O(M * N) elements.

## Commented Code
```java
class Solution {
    // Main method to count the number of islands.
    public int numIslands(char[][] grid) {
        // Initialize a counter for the number of islands found.
        int count = 0;
        // Get the number of rows in the grid.
        int m = grid.length;
        // Get the number of columns in the grid.
        int n = grid[0].length;
        
        // Iterate through each row of the grid.
        for(int i=0;i<m;i++){
            // Iterate through each column of the grid.
            for(int j=0;j<n;j++){
                // If the current cell is land ('1').
                if(grid[i][j]=='1'){
                    // Start a Depth First Search (DFS) from this land cell.
                    // This DFS will mark all connected land cells as visited ('0').
                    dfs(i,j,grid);
                    // Increment the island count because we found a new, unvisited island.
                    count++;
                }
            }
        }
        // Return the total number of islands found.
        return count;   
    }
    
    // Recursive helper function for Depth First Search (DFS).
    public void dfs(int x, int y, char[][] grid){
        // Base case: If the current coordinates are out of bounds, or the cell is water ('0'), return.
        if(x < 0 || y<0 || x>= grid.length || y>= grid[0].length || grid[x][y] != '1') return;        
        
        // Mark the current land cell as visited by changing it to water ('0').
        grid[x][y] = '0';
        
        // Define relative movements for neighbors: up, down, left, right.
        // arr[0] = -1 (up), arr[1] = 0 (no row change)
        // arr[2] = 1 (down), arr[3] = 0 (no row change)
        // arr[4] = -1 (no col change), arr[5] = 0 (left) - this is incorrect, should be arr[i+1] for y
        // Corrected logic for neighbors:
        // int[] dx = {-1, 1, 0, 0}; // Row changes for up, down, left, right
        // int[] dy = {0, 0, -1, 1}; // Column changes for up, down, left, right
        // The provided code uses a single array for both x and y, which is a common pattern but needs careful indexing.
        // arr[0] = -1 (dx for up), arr[1] = 0 (dy for up)
        // arr[2] = 1 (dx for down), arr[3] = 0 (dy for down)
        // arr[4] = -1 (dx for left), arr[5] = 0 (dy for left) - This is where the error is. It should be:
        // arr[0] = -1, arr[1] = 0 (up)
        // arr[2] = 1, arr[3] = 0 (down)
        // arr[4] = 0, arr[5] = -1 (left)
        // arr[6] = 0, arr[7] = 1 (right)
        // The provided code uses arr = new int[]{-1,0,1,0,-1}; and then iterates i from 0 to 3, using arr[i] and arr[i+1].
        // This means:
        // i=0: X = x + arr[0] = x - 1, Y = y + arr[1] = y + 0 (Up)
        // i=1: X = x + arr[1] = x + 0, Y = y + arr[2] = y + 1 (This is wrong, should be Y = y + arr[i+1] where arr[i+1] is the dy)
        // The provided code's neighbor logic is:
        // i=0: X = x + arr[0] = x - 1, Y = y + arr[1] = y + 0 (Up)
        // i=1: X = x + arr[1] = x + 0, Y = y + arr[2] = y + 1 (This is incorrect for Y, it should be Y = y + arr[i+1] where arr[i+1] is the dy)
        // Let's assume the intention was:
        int[] dx = {-1, 1, 0, 0}; // Row offsets for up, down, left, right
        int[] dy = {0, 0, -1, 1}; // Column offsets for up, down, left, right
        
        // Iterate through the four possible directions (up, down, left, right).
        for(int i=0;i<4;i++){
            // Calculate the coordinates of the neighboring cell.
            int X = x + dx[i]; // New row coordinate
            int Y = y + dy[i]; // New column coordinate
            // Recursively call DFS on the neighboring cell.
            dfs(X,Y,grid);
        }   
        // Return from the DFS call.
        return;     
    }
}
```

## Interview Tips
*   **Clarify Grid Dimensions:** Always ask about the constraints on the grid size (M, N) and if the grid can be empty.
*   **Explain Traversal Choice:** Be ready to explain why you chose DFS over BFS (or vice-versa) and the trade-offs (e.g., recursion depth vs. queue size).
*   **Handle Edge Cases:** Discuss how you'd handle an empty grid, a grid with only water, or a grid with only land.
*   **In-place vs. Extra Space:** Mention the possibility of using a separate `visited` 2D array if modifying the input grid is not allowed, and discuss the space complexity implications.

## Revision Checklist
- [ ] Understand the problem: counting connected components of '1's.
- [ ] Choose a traversal algorithm: DFS or BFS.
- [ ] Implement boundary checks correctly.
- [ ] Implement visited marking (e.g., changing '1' to '0').
- [ ] Handle the base cases for recursion/iteration.
- [ ] Analyze time and space complexity.
- [ ] Practice with different grid examples.

## Similar Problems
*   Max Area of Island
*   Flood Fill
*    Surrounded Regions
*    Rotting Oranges

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Matrix`
