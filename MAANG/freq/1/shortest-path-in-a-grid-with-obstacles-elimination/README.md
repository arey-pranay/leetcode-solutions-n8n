# Shortest Path In A Grid With Obstacles Elimination

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Matrix`  
**Time:** O(m \cdot n \cdot K)  
**Space:** O(m \cdot n \cdot K)

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
Find the shortest path from the top-left to the bottom-right corner of a grid while eliminating at most $K$ obstacles.
Solve it using Breadth-First Search (BFS) combined with a 3D visited array tracking coordinates and remaining elimination budget.

## Intuition
The "aha moment" is realizing that state is not just defined by grid coordinates $(x, y)$, but also by the number of obstacles we can still eliminate ($k$). A cell visited previously with fewer remaining eliminations might be revisited if we arrive with more remaining eliminations, potentially opening up shorter or previously blocked paths. BFS naturally guarantees the shortest path in unweighted grids, so the first time we reach the bottom-right cell, we are guaranteed to have found the minimum distance.

## Algorithm
1. Handle edge cases: if the destination cell itself is an obstacle and we have $0$ eliminations (or even with eliminations, though destination won't need it removed unless starting there, but specifically if the start/end conditions are impossible), return early. Check if the bottom-right cell is blocked when $K$ is insufficient, or simply let BFS handle reaching it.
2. Initialize a BFS queue storing state arrays: `[x, y, distance, remaining_k]`.
3. Create a 3D boolean visited array `vis[m][n][k+1]` to track whether a state `(x, y)` has been visited with a specific remaining elimination count `k`.
4. Add the starting state `(0, 0, 0, K)` to the queue and mark `vis[0][0][K] = true`.
5. While the queue is not empty, poll the current state.
6. If the current state reaches the bottom-right corner `(m-1, n-1)`, return the accumulated distance.
7. Explore all 4 cardinal directions. For each neighbor, calculate the new remaining obstacles budget `newK = k - grid[X][Y]`.
8. If `newK >= 0` and the state `(X, Y, newK)` has not been visited, mark it as visited and push it into the queue with incremented distance.
9. If the queue becomes empty without reaching the target, return `-1`.

## Concept to Remember
- **State-Space BFS**: Expanding graph nodes beyond simple 2D coordinates to include path-dependent resource constraints.
- **Pruning with 3D Visited Arrays**: Preventing redundant traversals by ensuring we only revisit a cell if we have strictly more resources ($k$) than a previous visit.
- **Shortest Path Optimality**: Leveraging BFS for unweighted shortest path discovery.

## Common Mistakes
- Using a standard 2D visited array instead of 3D, which prematurely blocks valid paths that arrive at the same cell with more remaining eliminations.
- Forgetting to mark the state as visited *when pushing to the queue*, leading to duplicate states, queue explosion, and Time Limit Exceeded (TLE).
- Incorrectly handling the $K$ budget when stepping onto an obstacle versus an empty cell.
- Failing to check boundaries before accessing the grid or visited array.

## Complexity Analysis
- Time: $O(m \cdot n \cdot K)$ - In the worst case, every cell in the grid can be visited up to $K$ times, once for each possible remaining obstacle elimination count.
- Space: $O(m \cdot n \cdot K)$ - The 3D visited array and the BFS queue store up to $m \cdot n \cdot K$ unique states in the worst-case scenario.

## Commented Code
```java
class Solution {
    // Direction array to easily iterate over 4 cardinal neighbors (up, right, down, left)
    int[] neigh = new int[]{-1, 0, 1, 0, -1};
    
    public int shortestPath(int[][] grid, int K) {
        // Queue to store BFS states: each element is an array of [x, y, distance, remaining_k]
        Queue<int[]> q = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        
        // Optimization: if the destination is an obstacle and K=0, we can never reach it.
        // Though handled naturally, an immediate check can save cycles if needed.
        
        // Start BFS from top-left corner (0, 0) with distance 0 and initial budget K
        q.add(new int[]{0, 0, 0, K});
        
        // 3D visited array to track if we've visited cell (x, y) with exactly k remaining eliminations
        boolean[][][] vis = new boolean[m][n][K + 1];
        vis[0][0][K] = true; // Mark starting state as visited
        
        // Standard BFS loop
        while (!q.isEmpty()) {
            int[] curr = q.poll(); // Retrieve current state
            int x = curr[0], y = curr[1], dist = curr[2], k = curr[3];
            
            // If we reached the bottom-right corner, return the current distance
            if (x == m - 1 && y == n - 1) return dist;
            
            // Increment distance for the next level of neighbor nodes
            int nextDist = dist + 1;
            
            // Explore all 4 directions
            for (int i = 0; i < 4; i++) {
                int X = x + neigh[i];     // Next X coordinate
                int Y = y + neigh[i + 1]; // Next Y coordinate
                
                // Check grid boundaries
                if (X < 0 || Y < 0 || X == m || Y == n) continue;
                
                // Calculate remaining obstacle elimination budget for the neighbor cell
                int newK = k - grid[X][Y];
                
                // Skip if we run out of eliminations (newK < 0) or already visited this state
                if (newK < 0 || vis[X][Y][newK]) continue; 
                
                // Mark the new state as visited before pushing to prevent duplicates
                vis[X][Y][newK] = true;
                
                // Add the new valid state to the queue
                q.offer(new int[]{X, Y, nextDist, newK});
            }
        }
        
        // If queue empties and destination was not reached, return -1
        return -1;
    }
}
```

## Interview Tips
- Clearly state why a standard 2D visited array fails (because a longer path with more remaining $K$ can yield a valid overall shortest path later).
- Discuss space and time complexities upfront, tying them directly to the state space dimensions ($M \times N \times K$).
- Mention alternative approaches like A* search if the interviewer asks about optimization, though BFS is optimal for this state formulation.

## Revision Checklist
- [ ] Understand why 3D state is necessary.
- [ ] Correctly manage the $K$ decrement logic when encountering `1` vs `0`.
- [ ] Mark visited *upon queue insertion*, not upon popping, to avoid memory/time blowups.
- [ ] Handle grid boundary conditions properly.

## Similar Problems
- `Matrix 01`
- `Word Ladder`
- `Path With Minimum Effort`
- `Minimum Cost to Reach Destination in Time`

## Tags
`Breadth-First Search` `Graph` `Array` `Matrix`
