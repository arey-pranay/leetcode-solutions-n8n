# Bricks Falling When Hit

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Union-Find` `Matrix`  
**Time:** O(m*n + h * (m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    int[] neighs = new int[]{-1,0,1,0,-1};
    int m,n;
    public int[] hitBricks(int[][] grid, int[][] hits) {
       int h = hits.length;
       m = grid.length; n = grid[0].length;
       int[] results = new int[h];
       
       // remove bricks -> mark as gaps
       for(int i=0;i<h;i++) grid[hits[i][0]][hits[i][1]]--;
       
       // mark stable
       for(int j=0;j<n;j++) if(grid[0][j]==1) dfs(grid,0,j);
       
       // put the bricks
       for(int i=h-1;i>=0;i--){
        int x= hits[i][0],y=hits[i][1];
        if(grid[x][y] == -1) continue;
        grid[x][y]=1;
        // kyonki dfs tb hi chalana hai jb koi waapis daalne waali brick kisi stable waale se connected hai kyonki agr vo restore waali khud hi kisi stable waali se connected nahihai to vo baaki ko kya stable banayegi
        if(x==0 || (x>0 && grid[x-1][y]==2) || (y>0 && grid[x][y-1]==2) || (x<m-1 && grid[x+1][y]==2) || (y<n-1 && grid[x][y+1]==2)) results[i] = dfs(grid,x,y);
       }
       
       return results;
    }
    private int dfs(int[][] grid, int x, int y){
        grid[x][y] = 2;
        int count = 0;
        for(int i=0;i<4;i++){
            int X = x+neighs[i],Y=y+neighs[i+1];
            if(X<0 || Y<0 || X==m || Y==n || grid[X][Y]!=1) continue;
            count += 1 + dfs(grid,X,Y); 
        }
        return count;
    }
}
// [2,0,2,0]
// [0,2,2,0]
// [1,0,0,1]
```

---

---
## Quick Revision
The problem involves a grid of bricks where some bricks are removed and we need to find the number of bricks that will fall when hit by a ball. We can solve this by simulating the removal of bricks and then performing a depth-first search (DFS) to count the number of connected bricks.

## Intuition
The key insight here is that a brick will only fall if it is connected to a stable brick (a brick that is not adjacent to a removed brick). By marking the stable bricks first and then performing DFS, we can count the number of connected bricks.

## Algorithm
1. Initialize an empty array `neighs` to store the neighboring coordinates.
2. Initialize variables `m` and `n` to store the number of rows and columns in the grid.
3. Create an empty array `results` to store the number of bricks that will fall when hit.
4. Remove the bricks specified in the `hits` array by decrementing the corresponding grid values.
5. Mark the stable bricks by performing DFS from the first row.
6. For each brick in the `hits` array, check if it is connected to a stable brick. If so, perform DFS to count the number of connected bricks and store the result in the `results` array.

## Concept to Remember
* Depth-First Search (DFS) is a traversal algorithm that explores a graph or tree by visiting a node and then visiting all of its neighbors before backtracking.
* A connected component in a graph is a subgraph in which any two vertices are connected to each other by paths.

## Common Mistakes
* Failing to mark the stable bricks before performing DFS.
* Not checking if a brick is connected to a stable brick before performing DFS.
* Not using a depth-first search algorithm to count the number of connected bricks.

## Complexity Analysis
- Time: O(m*n + h * (m*n)), where m and n are the dimensions of the grid and h is the number of hits.
- Space: O(m*n), for the recursion stack and the grid.

## Commented Code
```java
class Solution {
    int[] neighs = new int[]{-1,0,1,0,-1};
    int m,n;
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int h = hits.length;
        m = grid.length; n = grid[0].length;
        int[] results = new int[h];
        
        // Remove bricks
        for(int i=0;i<h;i++) grid[hits[i][0]][hits[i][1]]--;
        
        // Mark stable bricks
        for(int j=0;j<n;j++) if(grid[0][j]==1) dfs(grid,0,j);
        
        // Put bricks and count connected bricks
        for(int i=h-1;i>=0;i--){
            int x= hits[i][0],y=hits[i][1];
            if(grid[x][y] == -1) continue;
            grid[x][y]=1;
            if(x==0 || (x>0 && grid[x-1][y]==2) || (y>0 && grid[x][y-1]==2) || (x<m-1 && grid[x+1][y]==2) || (y<n-1 && grid[x][y+1]==2)) results[i] = dfs(grid,x,y);
        }
        
        return results;
    }
    private int dfs(int[][] grid, int x, int y){
        grid[x][y] = 2;
        int count = 0;
        for(int i=0;i<4;i++){
            int X = x+neighs[i],Y=y+neighs[i+1];
            if(X<0 || Y<0 || X==m || Y==n || grid[X][Y]!=1) continue;
            count += 1 + dfs(grid,X,Y); 
        }
        return count;
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and break it down into smaller sub-problems.
* Use a depth-first search algorithm to count the number of connected bricks.
* Pay attention to the time and space complexity of the solution.
* Practice solving similar problems to improve your skills.

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Break down the problem into smaller sub-problems
- [ ] Use a depth-first search algorithm to count connected bricks
- [ ] Pay attention to time and space complexity
- [ ] Practice solving similar problems

## Similar Problems
* `Number of Islands`: Count the number of islands in a grid.
* `Surrounded Regions`: Mark the regions that are surrounded by water in a grid.
* `Regions Cut By Slashes`: Count the number of regions that are cut by slashes in a grid.
