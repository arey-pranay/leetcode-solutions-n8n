# Unique Paths Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Backtracking` `Bit Manipulation` `Matrix` `Hamiltonian Path`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    int[] neighs = new int[]{-1,0,1,0,-1};
    int totalEmpty = 0;
    int targetX=0,targetY=0;
    int m,n;
    int[][] grid;
    int ans=0;
    public int uniquePathsIII(int[][] g) {  
        grid = g;m = g.length;n = g[0].length;  
        int beginX=-1,beginY=-1;  
        for(int i=0;i<m;i++){    
            for(int j=0;j<n;j++){       
                if(grid[i][j]!=-1)totalEmpty++; 
                if(grid[i][j]==1){beginX=i; beginY=j;}
                if(grid[i][j]==2){targetX=i; targetY=j;}
            }
        }
        grid[beginX][beginY] = -1;
        func(beginX,beginY,grid,1);
        return ans;
    }
    
    public void func(int x, int y, int[][] grid,int visCount){
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || X>=m || Y<0 || Y>=n) continue;
            int ogVal = grid[X][Y];
            if(grid[X][Y] == 2){
                if(visCount+1==totalEmpty)ans++; 
                continue;
            }
            if(grid[X][Y] != -1){grid[X][Y]=-1; func(X,Y,grid,visCount+1); grid[X][Y] = ogVal;}
        }
    }  
}
```

---

---
## Quick Revision
The problem is to find the number of unique paths from a start point to a target point in a given grid, where some cells are obstacles and some cells are empty.
This problem can be solved using a depth-first search (DFS) approach, where we explore all possible paths from the start point to the target point.

## Intuition
The key insight is to use DFS to explore all possible paths, and to keep track of the number of empty cells we visit. We also need to handle the obstacles and the target point correctly. By using DFS, we can efficiently explore all possible paths and count the number of unique paths.

## Algorithm
1. Initialize variables to keep track of the grid dimensions, the number of empty cells, the start and target points, and the number of unique paths.
2. Iterate over the grid to find the start and target points, and to count the number of empty cells.
3. Use DFS to explore all possible paths from the start point to the target point. For each path, increment the count of unique paths if the path visits all empty cells.
4. Return the count of unique paths.

## Concept to Remember
* **Depth-First Search (DFS)**: a graph traversal algorithm that explores a graph or tree by visiting a node and then visiting all of its neighbors before backtracking.
* **Graph Traversal**: the process of visiting all nodes in a graph or tree, often used in solving problems involving graph structures.
* **Recursion**: a programming technique where a function calls itself repeatedly until it reaches a base case that stops the recursion.

## Common Mistakes
* **Inefficient Grid Traversal**: not using DFS to explore all possible paths, resulting in a high time complexity.
* **Incorrect Counting of Unique Paths**: not keeping track of the number of empty cells visited, resulting in incorrect counting of unique paths.
* **Not Handling Obstacles and Target Point Correctly**: not correctly handling the obstacles and target point in the DFS traversal, resulting in incorrect counting of unique paths.

## Complexity Analysis
- Time: O(m*n) - reason: we need to iterate over all cells in the grid to find the start and target points and to count the number of empty cells.
- Space: O(m*n) - reason: we need to store the grid and the variables to keep track of the number of empty cells, the start and target points, and the number of unique paths.

## Commented Code
```java
class Solution {
    int[] neighs = new int[]{-1,0,1,0,-1};
    int totalEmpty = 0;
    int targetX=0,targetY=0;
    int m,n;
    int[][] grid;
    int ans=0;

    public int uniquePathsIII(int[][] g) {  
        grid = g;m = g.length;n = g[0].length;  
        int beginX=-1,beginY=-1;  
        for(int i=0;i<m;i++){    
            for(int j=0;j<n;j++){       
                if(grid[i][j]!=-1)totalEmpty++; 
                if(grid[i][j]==1){beginX=i; beginY=j;}
                if(grid[i][j]==2){targetX=i; targetY=j;}
            }
        }
        grid[beginX][beginY] = -1;
        func(beginX,beginY,grid,1);
        return ans;
    }

    public void func(int x, int y, int[][] grid,int visCount){
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || X>=m || Y<0 || Y>=n) continue;
            int ogVal = grid[X][Y];
            if(grid[X][Y] == 2){
                if(visCount+1==totalEmpty)ans++; 
                continue;
            }
            if(grid[X][Y] != -1){grid[X][Y]=-1; func(X,Y,grid,visCount+1); grid[X][Y] = ogVal;}
        }
    }  
}
```

## Interview Tips
* **Make sure to keep track of the number of empty cells visited**: this is crucial to counting the number of unique paths.
* **Use DFS to explore all possible paths**: this is the most efficient way to solve this problem.
* **Handle obstacles and target point correctly**: make sure to correctly handle the obstacles and target point in the DFS traversal.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Implement DFS to explore all possible paths.
- [ ] Keep track of the number of empty cells visited.
- [ ] Handle obstacles and target point correctly.

## Similar Problems
* **Unique Paths II**: a similar problem where we need to find the number of unique paths from a start point to a target point in a grid with obstacles.
* **Unique Paths**: a similar problem where we need to find the number of unique paths from a start point to a target point in a grid with obstacles.

## Tags
`Array` `Hash Map` `Graph Traversal` `DFS` `Recursion`
