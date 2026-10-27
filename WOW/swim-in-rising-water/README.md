# Swim In Rising Water

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Depth-First Search` `Breadth-First Search` `Union-Find` `Minimax` `Heap (Priority Queue)` `Matrix` `Dijkstra's Algorithm`  
**Time:** O(m * n * log(max(grid)  
**Space:** O(m * n)

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
The problem is to find the minimum time it takes for a person to swim from the top left cell to the bottom right cell in a grid where the water level rises over time. We solve this problem by using a binary search approach.

## Intuition
The key insight here is to use a binary search to find the minimum time. We maintain a time range [minTime, maxTime] and at each iteration, we check if we can reach the bottom right cell in the current time midTime. If we can, we update maxTime to midTime; otherwise, we update minTime to midTime + 1. This process continues until minTime = maxTime.

## Algorithm
1. Initialize minTime to the value of the top left cell and maxTime to the maximum value in the grid.
2. While minTime < maxTime, calculate midTime = (minTime + maxTime) / 2.
3. Create a boolean matrix vis to keep track of visited cells.
4. Call canReach(grid, 0, 0, midTime, vis) to check if we can reach the bottom right cell in midTime time.
5. If canReach returns true, update maxTime to midTime; otherwise, update minTime to midTime + 1.
6. Repeat steps 2-5 until minTime = maxTime.

## Concept to Remember
* Binary search: We use a binary search approach to find the minimum time it takes to reach the bottom right cell.
* Time complexity: O(m * n * log(max(grid))), where max(grid) is the maximum value in the grid.
* Space complexity: O(m * n), where m and n are the dimensions of the grid.

## Common Mistakes
* Not initializing minTime and maxTime correctly.
* Not updating minTime and maxTime correctly in the binary search loop.
* Not checking if we can reach the bottom right cell in the current time.

## Complexity Analysis
- Time: O(m * n * log(max(grid))) / We use a binary search approach, and at each iteration, we visit O(m * n) cells in the worst case.
- Space: O(m * n) / We use a boolean matrix vis to keep track of visited cells.

## Commented Code
```java
class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    
    public int swimInWater(int[][] grid) {
        // Initialize minTime and maxTime
        int minTime = grid[0][0];
        int maxTime = maxOf(grid);
        
        // Initialize n
        int n = grid.length;
        
        // Binary search loop
        while(minTime < maxTime){
            // Calculate midTime
            int midTime = minTime + (maxTime-minTime)/2;
            
            // Create a boolean matrix vis
            boolean[][] vis = new boolean[n][n];
            
            // Check if we can reach the bottom right cell in midTime time
            if(canReach(grid, 0, 0, midTime, vis)) maxTime = midTime;
            else minTime = midTime+1;
        }
        
        // Return the minimum time it takes to reach the bottom right cell
        return minTime;
    }
    
    private boolean canReach(int[][] grid, int row, int col, int time, boolean[][] vis){
        // Check if we are out of bounds or if the cell has been visited or if the value of the cell is greater than time
        if(row<0 || col<0 || row==grid.length || col==grid[0].length || vis[row][col] || grid[row][col] > time) return false;
        
        // If we have reached the bottom right cell, return true
        if(row==grid.length-1 && col==grid[0].length-1) return true;
        
        // Mark the cell as visited
        vis[row][col] = true;
        
        // Check if we can reach the adjacent cells in time
        for(int i =0;i<4;i++){
            int X = row + neigh[i];
            int Y = col + neigh[i+1];
            if(canReach(grid,X,Y,time,vis)) return true;
        }
        
        // If we cannot reach any adjacent cells, return false
        return false;
    }
    
    private int maxOf(int[][] grid){
        // Initialize max to the minimum value
        int max = 0;
        
        // Iterate over each cell in the grid
        for(int[] row : grid) for(int cell : row) max = Math.max(max,cell);
        
        // Return the maximum value
        return max;
    }
    
}
```

## Interview Tips
* Make sure to initialize minTime and maxTime correctly.
* Use a binary search approach to find the minimum time it takes to reach the bottom right cell.
* Use a boolean matrix vis to keep track of visited cells.
* Make sure to update minTime and maxTime correctly in the binary search loop.
* Practice the problem until you can solve it within a reasonable time.

## Revision Checklist
- [ ] Initialize minTime and maxTime correctly.
- [ ] Use a binary search approach to find the minimum time it takes to reach the bottom right cell.
- [ ] Use a boolean matrix vis to keep track of visited cells.
- [ ] Make sure to update minTime and maxTime correctly in the binary search loop.

## Similar Problems
* 1632. Rank Teams by Votes
* 1004. Max Consecutive Ones III
* 1544. Make Array Strictly Increasing

## Tags
`Array` `Hash Map` `Binary Search` `Graph` `Dynamic Programming`
