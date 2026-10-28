# Shortest Path In A Grid With Obstacles Elimination

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Matrix`  
**Time:** O(M \cdot N \cdot K)  
**Space:** O(M \cdot N \cdot K)

---

## Solution (java)

```java
class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    public int shortestPath(int[][] grid, int K){
        Queue<int[]> q = new LinkedList<>();// x,y,dist,k
        int m = grid.length, n=grid[0].length;
        if(grid[m-1][n-1]==1) return -1;
        q.add(new int[]{0,0,0,K});
        boolean[][][] vis = new boolean[m][n][K+1];
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], dist = curr[2], k=curr[3];
            if(x==m-1 && y==n-1) return dist;
            dist++;
            for(int i=0;i<4;i++){
                int X = x+neigh[i];
                int Y = y+neigh[i+1];
                if(X<0 || Y<0 || X==m || Y==n) continue;
                int newK = k-grid[X][Y];
                if(newK < 0 || vis[X][Y][newK]) continue; 
                q.offer(new int[]{X,Y,dist,newK});
                vis[X][Y][newK] = true;
            }
        }
        return -1;
    }
}
```

---

---
## Quick Revision
Find the shortest path from top-left to bottom-right in a grid while eliminating at most K obstacles.
Solve it using Breadth-First Search (BFS) with a state tracking `(row, col, remaining_k)`.

## Intuition
The standard shortest path problem in an unweighted grid uses BFS because it explores states level by level, ensuring the first time we reach the destination, it is via the shortest path. However, because we can eliminate obstacles, the state space is no longer just the grid coordinates `(x, y)`. Two paths to the same cell `(x, y)` are entirely different states if they arrive with a different number of remaining obstacle eliminations (`k`). The "aha moment" is realizing that `k` is part of the state. By tracking `(x, y, k)` using a 3D visited array, we can safely re-visit grid cells as long as we arrive with strictly more remaining eliminations than a previous visit, allowing BFS to find the absolute shortest path.

## Algorithm
1. Handle edge cases: If the destination is an obstacle and we have $k=0$ (or if the grid size is 1x1 and valid), check validity, though the code checks if the destination itself is an obstacle and returns `-1`.
2. Initialize a queue for BFS and push the starting state `(0, 0, 0, K)` representing `(row, col, distance, remaining_k)`.
3. Initialize a 3D boolean visited array `vis[m][n][k+1]` to track visited states of `(x, y)` with a specific `k`. Mark the starting state as visited.
4. Loop while the queue is not empty:
   - Poll the current state `(x, y, dist, k)`.
   - If `(x, y)` is the bottom-right corner `(m-1, n-1)`, return `dist`.
   - Iterate through the 4 possible neighbor directions.
   - Calculate neighbor coordinates `(X, Y)`.
   - Compute remaining eliminations for the neighbor: `newK = k - grid[X][Y]`.
   - If `newK >= 0` and the state `(X, Y, newK)` has not been visited:
     - Mark `vis[X][Y][newK]` as true.
     - Push `(X, Y, dist + 1, newK)` into the queue.
5. If the queue empties without reaching the destination, return `-1`.

## Concept to Remember
- State-space BFS: Expanding the definition of a node beyond grid coordinates to include resource constraints (`k`).
- 3D Visited Arrays: Managing state deduplication when additional variables dictate validity.
- Shortest Path Optimality: Why BFS guarantees the shortest path in unweighted graphs even when resource limits are added.

## Common Mistakes
- Forgetting to include `k` in the visited state, which leads to incorrectly pruning valid paths that arrive at a cell with more remaining eliminations.
- Not marking nodes as visited *at the time of queue insertion*, causing massive duplicate processing and Time Limit Exceeded (TLE).
- Failing to check if the destination cell itself is blocked and requires an elimination, or checking it incorrectly.
- Using Depth-First Search (DFS), which cannot guarantee the shortest path without exhaustively checking all possibilities and blowing up the call stack.

## Complexity Analysis
- Time: $O(M \cdot N \cdot K)$ - In the worst case, every cell in the grid is visited with every possible value of remaining elimination $K$.
- Space: $O(M \cdot N \cdot K)$ - The 3D visited array and the BFS queue store up to $M \cdot N \cdot K$ unique states.

## Commented Code
```java
class Solution {
    // Array to help navigate the 4 directions (up, right, down, left)
    int[] neigh = new int[]{-1,0,1,0,-1};
    
    public int shortestPath(int[][] grid, int K){
        // Queue to store the BFS states: each element contains {x, y, distance, remaining_k}
        Queue<int[]> q = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        
        // Quick check: if the target is an obstacle and we somehow couldn't bypass it, 
        // though strictly if target is 1, we need 1 k to step on it. 
        // If target is 1 and K is 0, we can never finish there.
        if(grid[m-1][n-1] == 1 && K == 0 && !(m == 1 && n == 1)) {
            // Standard check handled naturally by BFS, but good to catch early if needed.
        }
        
        // Add the starting position (0,0) with distance 0 and initial K eliminations
        q.add(new int[]{0, 0, 0, K});
        
        // 3D visited array to track whether we have visited cell (x, y) with a specific remaining K
        boolean[][][] vis = new boolean[m][n][K+1];
        
        // Standard BFS loop
        while(!q.isEmpty()){
            // Retrieve the current state from the queue
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], dist = curr[2], k = curr[3];
            
            // If we have reached the bottom-right corner, return the accumulated distance
            if(x == m-1 && y == n-1) return dist;
            
            // Increment distance for the next level of neighbors
            int nextDist = dist + 1;
            
            // Explore all 4 possible directions
            for(int i = 0; i < 4; i++){
                int X = x + neigh[i];
                int Y = y + neigh[i+1];
                
                // Check bounds; skip if the neighbor is outside the grid
                if(X < 0 || Y < 0 || X == m || Y == n) continue;
                
                // Calculate remaining K after moving to the neighbor cell
                int newK = k - grid[X][Y];
                
                // If we ran out of eliminations (newK < 0) or already visited this state, skip
                if(newK < 0 || vis[X][Y][newK]) continue; 
                
                // Mark this new state as visited to prevent redundant processing
                vis[X][Y][newK] = true;
                
                // Add the new state to the queue for future exploration
                q.offer(new int[]{X, Y, nextDist, newK});
            }
        }
        
        // If the queue is exhausted and destination was not reached, return -1
        return -1;
    }
}
```

## Interview Tips
- State clearly why Dijkstra's algorithm or BFS is appropriate: since edge weights are uniform (each step costs 1), BFS guarantees the shortest path.
- Discuss space and time complexities upfront, noting how factor $K$ scales the standard grid BFS complexity.
- Mention optimizations, such as early termination if $K$ is greater than or equal to Manhattan distance minus 1 (meaning you can just blast through everything).

## Revision Checklist
- [ ] Understand why standard BFS fails without tracking `k`.
- [ ] Implement the 3D visited array correctly.
- [ ] Handle grid boundary conditions properly.
- [ ] Analyze time and space complexity in terms of $M$, $N$, and $K$.

## Similar Problems
- `Matrix Block Sum`
- `Shortest Path in Binary Matrix`
- `Path With Minimum Effort`
- `Minimum Number of Days to Disconnect Island`

## Tags
`Array` `Breadth-First Search` `Matrix`
