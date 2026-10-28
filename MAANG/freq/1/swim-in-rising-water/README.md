# Swim In Rising Water

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Depth-First Search` `Breadth-First Search` `Union-Find` `Minimax` `Heap (Priority Queue)` `Matrix` `Dijkstra's Algorithm`  
**Time:** O(N^2 \log K)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    public int swimInWater(int[][] grid) {
        // newT = currT + diff
        int minTime = grid[0][0];
        int maxTime = maxOf(grid);
        int n = grid.length;
        while(minTime < maxTime){
            int midTime = minTime + (maxTime-minTime)/2;
            boolean[][] vis = new boolean[n][n];
            if(canReach(grid, 0,0,midTime,vis)) maxTime = midTime;
            else minTime = midTime+1;
        }
        return minTime;
    }
   
    private boolean canReach(int[][] grid, int row, int col, int time, boolean[][] vis){
        int n = grid.length;
        if(row<0 || col<0 || row==n || col==n || vis[row][col] || grid[row][col] > time) return false;
        if(row==n-1 && col==n-1) return true;
        vis[row][col] = true;
        for(int i =0;i<4;i++){
            int X = row + neigh[i];
            int Y = col + neigh[i+1];
            if(canReach(grid,X,Y,time,vis)) return true;
        }
        return false;
    }
    
    private int maxOf(int[][] grid){
        int max = 0;
        for(int[] row : grid) for(int cell : row) max = Math.max(max,cell);
        return max;
    }
    
}
```

---

---
## Quick Revision
Find the minimum time required to swim from the top-left to the bottom-right corner of a grid where each cell has an elevation.
Use binary search on the answer space combined with Depth-First Search (DFS) to validate path connectivity at each time threshold.

## Intuition
The problem asks for the *minimum* time $t$ such that a valid path exists from $(0, 0)$ to $(n-1, n-1)$ where every cell on the path has an elevation $\le t$. Notice that if we can successfully reach the destination at time $t$, we can definitely reach it at any time greater than $t$. This monotonicity property tells us that the answer space is sorted, allowing us to apply binary search over the range of possible grid elevations. For any mid-point time, we treat it as a feasibility check and run a graph traversal (like DFS or BFS) to see if the destination is reachable without stepping on any cell with an elevation greater than that time.

## Algorithm
1. Determine the search space boundaries: `minTime` is the elevation of the starting cell `grid[0][0]`, and `maxTime` is the maximum elevation found anywhere in the grid.
2. Perform a binary search while `minTime < maxTime`.
3. Compute the `midTime` as the average of `minTime` and `maxTime`.
4. Initialize a boolean visited matrix of size $N \times N$.
5. Check if a valid path from $(0, 0)$ to $(n-1, n-1)$ exists using DFS (`canReach`) such that no cell exceeds `midTime`.
6. If the destination is reachable, a smaller maximum time might work, so set `maxTime = midTime`.
7. If the destination is not reachable, we need more time, so set `minTime = midTime + 1`.
8. When the loop terminates (`minTime == maxTime`), return `minTime` as the minimum required time.

## Concept to Remember
- Binary Search on Answer: Transforming an optimization problem (finding the minimum valid time) into a decision/feasibility problem (can we reach the end at time $t$?).
- Graph Traversal with Constraints: Using DFS/BFS to explore a grid graph while respecting edge or node weight constraints.
- Monotonicity: Recognizing that feasibility changes monotonically with respect to the parameter being searched.

## Common Mistakes
- Forgetting to check if the starting cell elevation is greater than the target time during traversal.
- Setting the lower bound of binary search to 0 instead of `grid[0][0]`, since we cannot finish earlier than our starting elevation.
- Incorrectly handling array bounds or direction arrays during the DFS traversal, leading to `ArrayIndexOutOfBoundsException` or infinite recursion.
- Reusing the same `visited` array across different binary search iterations without resetting it.

## Complexity Analysis
- Time: $O(N^2 \log K)$ - The binary search runs in $O(\log K)$ where $K$ is the difference between max and min grid values. Each check takes $O(N^2)$ time to traverse the $N \times N$ grid using DFS.
- Space: $O(N^2)$ - The recursion stack for DFS and the `visited` matrix consume space proportional to the grid dimensions in the worst-case scenario.

## Commented Code
```java
class Solution {
    // Define the 4-directional offsets (up, right, down, left) for grid traversal
    int[] neigh = new int[]{-1,0,1,0,-1};
    
    public int swimInWater(int[][] grid) {
        // The minimum possible time to start is the elevation of the starting cell
        int minTime = grid[0][0];
        // The maximum possible time is the highest elevation present in the grid
        int maxTime = maxOf(grid);
        // Get the dimension size of the square grid
        int n = grid.length;
        
        // Perform binary search on the range of possible times
        while(minTime < maxTime){
            // Find the middle time value to test for feasibility
            int midTime = minTime + (maxTime-minTime)/2;
            // Create a fresh visited matrix for the current time constraint check
            boolean[][] vis = new boolean[n][n];
            // If we can reach the end at midTime, try a smaller maximum time
            if(canReach(grid, 0, 0, midTime, vis)) {
                maxTime = midTime;
            } else {
                // Otherwise, midTime is too small; increase the lower bound
                minTime = midTime + 1;
            }
        }
        // When bounds converge, minTime holds the optimal answer
        return minTime;
    }
   
    private boolean canReach(int[][] grid, int row, int col, int time, boolean[][] vis){
        int n = grid.length;
        // Return false if out of bounds, already visited, or current cell elevation exceeds allowed time
        if(row < 0 || col < 0 || row == n || col == n || vis[row][col] || grid[row][col] > time) return false;
        // Return true if we have successfully reached the bottom-right destination
        if(row == n-1 && col == n-1) return true;
        // Mark the current cell as visited to prevent cycles
        vis[row][col] = true;
        // Explore all 4 neighboring cells using the direction offset array
        for(int i = 0; i < 4; i++){
            int X = row + neigh[i];
            int Y = col + neigh[i+1];
            // If any recursive path finds the destination, propagate true upwards
            if(canReach(grid, X, Y, time, vis)) return true;
        }
        // If no neighbor leads to the destination, return false
        return false;
    }
    
    private int maxOf(int[][] grid){
        int max = 0;
        // Iterate through every row and cell in the grid to find the absolute maximum elevation
        for(int[] row : grid) {
            for(int cell : row) {
                max = Math.max(max, cell);
            }
        }
        // Return the maximum elevation found
        return max;
    }
}
```

## Interview Tips
- State clearly why binary search applies here: the property of reachability is monotonic with respect to time.
- Discuss alternative approaches, such as using a Min-Heap (Dijkstra's variant where edge weights are maxed along the path), which has the same asymptotic complexity $O(N^2 \log N)$ and avoids binary search overhead.
- Mention space-time tradeoffs between the Binary Search + DFS approach and the Priority Queue approach.
- Walk through the constraints and edge cases, such as a $1 \times 1$ grid.

## Revision Checklist
- [ ] Understand why binary search on answer is applicable.
- [ ] Implement the feasibility check function cleanly using DFS or BFS.
- [ ] Correctly set initialization bounds (`grid[0][0]` to max grid value).
- [ ] Reset the visited structure on every feasibility check.
- [ ] Analyze time and space complexity clearly.

## Similar Problems
- Path With Minimum Effort
- Minimum Number of Days to Make m Bouquets
- Koko Eating Bananas
- Capacity To Ship Packages Within D Days

## Tags
`Array` `Binary Search` `Depth-First Search` `Breadth-First Search` `Union Find` `Heap (Priority Queue)` `Matrix`
