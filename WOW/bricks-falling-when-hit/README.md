# Bricks Falling When Hit

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Union-Find` `Matrix`  
**Time:** O(m*n + h*m*n)  
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
        if(
        x==0 ||  
        (x>0 && grid[x-1][y]==2) ||
        (y>0 && grid[x][y-1]==2) ||
        (x<m-1 && grid[x+1][y]==2) ||
        (y<n-1 && grid[x][y+1]==2)
        ) results[i] = dfs(grid,x,y);
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
The problem is to determine the number of bricks that will fall when a series of bricks are hit in a 2D grid. The solution uses a depth-first search (DFS) to count the number of bricks connected to each hit brick.

## Intuition
The key insight is that a brick will only fall if it is connected to a hit brick and there is no stable brick (i.e., a brick that is not part of any connected component) between it and the hit brick. This can be efficiently checked using DFS.

## Algorithm
1. Mark the bricks that will fall as -1 and remove them from the grid.
2. Perform DFS from the top row of the grid to mark stable bricks as 2.
3. Iterate over the hits from bottom to top and for each hit, check if the brick is connected to a hit brick and if there are any stable bricks between it and the hit brick. If both conditions are met, use DFS to count the number of connected bricks.
4. The count obtained in step 3 is the number of bricks that will fall when the hit brick is hit.

## Concept to Remember
* Depth-First Search (DFS) for graph traversal
* Connected components in a graph
* Importance of checking for stability in a connected component

## Common Mistakes
* Failing to correctly identify connected components and stable bricks
* Incorrectly implementing DFS to count connected bricks
* Not considering the case where a brick is hit but no bricks fall
* Not handling the edge cases where a brick is at the border of the grid

## Complexity Analysis
- Time: O(m*n + h*m*n) where m and n are the dimensions of the grid and h is the number of hits. This is because we perform DFS for each hit and each brick in the grid.
- Space: O(m*n) for storing the grid and the count of connected bricks.

## Commented Code
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
            if(x==0 ||  
                (x>0 && grid[x-1][y]==2) ||
                (y>0 && grid[x][y-1]==2) ||
                (x<m-1 && grid[x+1][y]==2) ||
                (y<n-1 && grid[x][y+1]==2)
            ) results[i] = dfs(grid,x,y);
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
* Practice implementing DFS on a whiteboard or a scratch piece of paper to get a feel for the algorithm.
* Pay attention to the details of the problem and the constraints (e.g., brick positions, hit positions).
* Consider using a different data structure (e.g., a graph) to represent the bricks and their connections.
* Be prepared to explain the time and space complexity of your solution.

## Revision Checklist
- [ ] Implement DFS correctly
- [ ] Handle edge cases correctly
- [ ] Consider using a more efficient data structure
- [ ] Review time and space complexity

## Similar Problems
* LeetCode 1304: Remove Duplicates from Sorted Array
* LeetCode 1091: Shortest Path in a Grid with Obstacles

## Tags
`Array` `Hash Map` `Depth-First Search` `Graph Traversal` `Connected Components` `Stable Bricks`
