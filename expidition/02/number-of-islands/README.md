# Number Of Islands

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search` `Union-Find` `Matrix`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;      
        int runs = 0;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1' ){     
                    runs++;                               
                    check(grid, i, j,m,n);
                }
            }
        }
            
        return runs;
    }
    public void check(char[][] grid, int x, int y, int m, int n){
        if(x < 0 || y < 0 || x >= m || y>= n) return;
        if(grid[x][y] == '0') return;
        grid[x][y] = '0';
        int[] neighs = {-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i]; //x-1,x,x+1,x
            int Y = y + neighs[i+1];//y,y+1,y,y-1
            check(grid,X,Y,m,n);
        }
    }
}


```

---

---
## Quick Revision

* Problem: Given a 2D grid, count the number of islands where an island is defined as a group of connected '1's.
* Solution strategy: Use depth-first search (DFS) to traverse each '1' in the grid and mark it as visited by changing its value to '0'.

## Intuition

The problem can be solved using DFS because we need to traverse each island in the grid. The key insight is that once a cell is marked as visited, no other cell with the same row and column indices should be counted again.

## Algorithm

1. Initialize a variable `runs` to count the number of islands.
2. Iterate through each cell in the grid using two nested loops.
3. If the current cell is '1', increment `runs` by 1 and call the `check` function to mark all connected '1's as visited.
4. The `check` function uses DFS to traverse all connected '1's and marks them as visited by changing their values to '0'.

## Concept to Remember

* **Depth-First Search (DFS)**: A traversal algorithm that explores a graph or tree by visiting a node and then visiting all of its neighbors before backtracking.
* **Graph Traversal**: The process of visiting each node in a graph or tree data structure.
* **Connected Components**: Groups of nodes in a graph that are connected to each other.

## Common Mistakes

* Not initializing the `runs` variable correctly, leading to incorrect counting of islands.
* Failing to mark all connected '1's as visited during DFS traversal.
* Not handling edge cases such as empty grids or grids with only one cell.

## Complexity Analysis

- Time: O(m * n) - We need to iterate through each cell in the grid once.
- Space: O(m * n) - In the worst case, we may need to store all connected '1's in the stack during DFS traversal.

## Commented Code
```java
class Solution {
    public int numIslands(char[][] grid) {
        // Initialize variables to count islands and get grid dimensions.
        int m = grid.length;
        int n = grid[0].length;      
        int runs = 0;

        // Iterate through each cell in the grid.
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // If current cell is '1', increment island count and mark connected '1's as visited.
                if(grid[i][j] == '1' ){     
                    runs++;                               
                    check(grid, i, j,m,n);
                }
            }
        }
            
        return runs;
    }

    /**
     * Marks all connected '1's as visited using DFS traversal.
     */
    public void check(char[][] grid, int x, int y, int m, int n){
        // Base cases for DFS: out of bounds or already visited cell.
        if(x < 0 || y < 0 || x >= m || y>= n) return;
        if(grid[x][y] == '0') return;

        // Mark current cell as visited by changing its value to '0'.
        grid[x][y] = '0';

        // Define possible directions for DFS traversal.
        int[] neighs = {-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i]; 
            int Y = y + neighs[i+1];//y,y+1,y,y-1
            // Recursively mark all connected '1's as visited.
            check(grid,X,Y,m,n);
        }
    }
}
```

## Interview Tips

* Be prepared to explain the algorithm and its time/space complexity.
* Practice explaining the solution in a way that is easy to understand for non-technical audiences.
* Be ready to handle edge cases and special input scenarios.

## Revision Checklist
- [ ] Review DFS traversal basics and implementation.
- [ ] Practice solving similar graph traversal problems.
- [ ] Study how to optimize the algorithm for larger inputs.

## Similar Problems

* 200. Number of Islands II (LeetCode)
* 542. 01 Matrix (LeetCode)
* 733. Flood Fill (LeetCode)

## Tags
`Array`, `Hash Map`, `Graph Traversal`, `Depth-First Search`, `Backtracking`

## My Notes
Simplest DFS idealistic appraoch
