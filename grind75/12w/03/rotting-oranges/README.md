# Rotting Oranges

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Matrix`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[] neighs = new int[]{-1,0,1,0,-1};
        int ones = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2)q.add(new int[]{i,j,0});
                if(grid[i][j]==1) ones++;    
            } 
        }
        if(ones==0) return 0;
        int ans = -1;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int time = curr[2];
            ans = time;
            for(int i=0;i<4;i++){
                int X = curr[0] + neighs[i];
                int Y = curr[1] + neighs[i+1];
                if(X<0 || Y<0 || X==m || Y==n) continue;
                if(grid[X][Y]==1){
                    q.add(new int[]{X,Y,time+1});
                    ones--;
                    grid[X][Y] = 2;
                }
            }
        }
        return ones == 0 ? ans : -1;
    }
}
// 2 2 1 0 1 1
// 2 1
// [[2,2,2,1,1,2,1],
// [0,2,2,1,1,1,1],
// [0,0,2,0,0,2,1]]

// 0,0,0,1,1,0,1
// - 0 0 1 2 1 2
// - - 0 - - 2 3
```

---

---
## Quick Revision
The problem asks for the minimum time required for all fresh oranges to rot, given a grid of fresh, rotten, and empty cells.
This is solved using Breadth-First Search (BFS) starting from all initially rotten oranges.

## Intuition
The rotting process spreads like a wave from rotten oranges to adjacent fresh oranges. This wave-like propagation is a classic characteristic of BFS. We can think of each minute as a "level" in the BFS. All oranges that rot at time `t` will infect their neighbors at time `t+1`. By starting BFS from all rotten oranges simultaneously and keeping track of the time, we can determine the maximum time it takes for any fresh orange to become rotten.

## Algorithm
1.  **Initialization**:
    *   Get the dimensions of the grid (`m` rows, `n` columns).
    *   Create a queue (`q`) to store the coordinates of rotten oranges and the time they became rotten.
    *   Initialize a counter `ones` to store the total number of fresh oranges.
    *   Define an array `neighs` for easy access to the four cardinal directions (up, down, left, right).
2.  **Populate Initial State**:
    *   Iterate through the grid.
    *   If a cell contains a rotten orange (`grid[i][j] == 2`), add its coordinates `(i, j)` and initial time `0` to the queue.
    *   If a cell contains a fresh orange (`grid[i][j] == 1`), increment `ones`.
3.  **Handle Edge Case**: If there are no fresh oranges (`ones == 0`), return `0` immediately.
4.  **BFS Traversal**:
    *   Initialize `ans` to `-1` (this will store the maximum time).
    *   While the queue is not empty:
        *   Dequeue a rotten orange's information: `(row, col, time)`.
        *   Update `ans` to the current `time`. This is because the last orange processed at this time level determines the minimum time.
        *   For each of the four neighbors of the current orange:
            *   Calculate the neighbor's coordinates `(newRow, newCol)`.
            *   **Boundary Check**: If the neighbor is out of bounds, skip it.
            *   **Fresh Orange Check**: If the neighbor is a fresh orange (`grid[newRow][newCol] == 1`):
                *   Enqueue the neighbor's coordinates `(newRow, newCol)` and the next time `time + 1`.
                *   Decrement `ones` as this orange is now rotten.
                *   Mark the neighbor as rotten in the grid (`grid[newRow][newCol] = 2`).
5.  **Final Check**:
    *   After the BFS completes, if `ones` is `0` (all fresh oranges have rotted), return `ans`.
    *   Otherwise, if `ones` is still greater than `0`, it means some fresh oranges are unreachable, so return `-1`.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Ideal for problems involving shortest paths or level-order traversal in graphs or grid-like structures.
*   **Queue Data Structure**: Essential for BFS to maintain the order of nodes to visit.
*   **State Management**: Keeping track of the "time" or "distance" associated with each element in the BFS.
*   **Grid Traversal**: Efficiently exploring adjacent cells in a 2D array.

## Common Mistakes
*   **Not handling the case of no fresh oranges**: If `ones` is initially 0, the loop might not run, and returning `ans` (which is -1) would be incorrect.
*   **Incorrectly updating `ans`**: `ans` should be updated with the `time` of the *dequeued* element, not the *enqueued* element. The time of the dequeued element represents the time it took for that orange to rot.
*   **Forgetting to mark oranges as rotten**: If a fresh orange is added to the queue but not marked as rotten in the `grid`, it might be processed multiple times, leading to incorrect results or infinite loops.
*   **Off-by-one errors in neighbor calculations or boundary checks**: Carefully checking array indices and grid boundaries is crucial.
*   **Not returning -1 when some oranges are unreachable**: If `ones` is not zero at the end, it signifies an impossible scenario.

## Complexity Analysis
*   **Time**: O(m * n) - Each cell in the grid is visited at most a constant number of times (once for initial scan, and at most once when it becomes rotten and is processed from the queue).
*   **Space**: O(m * n) - In the worst case, all oranges could be rotten and added to the queue, occupying space proportional to the grid size.

## Commented Code
```java
class Solution {
    public int orangesRotting(int[][] grid) {
        // Get the number of rows in the grid.
        int m = grid.length;
        // Get the number of columns in the grid.
        int n = grid[0].length;
        // Initialize a queue to store rotten oranges and their rotting time.
        // Each element will be an array: [row, col, time].
        Queue<int[]> q = new LinkedList<>();
        // Define an array for neighbor directions: [-1, 0] (up), [1, 0] (down), [0, -1] (left), [0, 1] (right).
        // This is a common trick to iterate through neighbors using two indices.
        int[] neighs = new int[]{-1,0,1,0,-1};
        // Initialize a counter for the number of fresh oranges.
        int ones = 0;

        // Iterate through each cell of the grid to initialize the queue and count fresh oranges.
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // If the cell contains a rotten orange (value 2), add it to the queue with time 0.
                if(grid[i][j]==2)q.add(new int[]{i,j,0});
                // If the cell contains a fresh orange (value 1), increment the fresh orange count.
                if(grid[i][j]==1) ones++;
            }
        }

        // If there are no fresh oranges initially, no time is needed.
        if(ones==0) return 0;

        // Initialize the answer (maximum time taken for an orange to rot) to -1.
        // This will be updated as we process rotten oranges.
        int ans = -1;

        // Start the Breadth-First Search (BFS) process.
        while(!q.isEmpty()){
            // Dequeue the current rotten orange's information.
            int[] curr = q.poll();
            // Extract the row, column, and the time this orange became rotten.
            int row = curr[0];
            int col = curr[1];
            int time = curr[2];

            // Update the maximum time encountered so far.
            // This 'time' is the time it took for the current orange to rot.
            ans = time;

            // Explore the four neighbors of the current rotten orange.
            for(int i=0;i<4;i++){
                // Calculate the coordinates of the neighbor.
                int X = row + neighs[i];
                int Y = col + neighs[i+1];

                // Check if the neighbor's coordinates are within the grid boundaries.
                if(X<0 || Y<0 || X==m || Y==n) continue; // If out of bounds, skip.

                // Check if the neighbor is a fresh orange.
                if(grid[X][Y]==1){
                    // If it's a fresh orange, it will rot in the next minute.
                    // Add it to the queue with the incremented time.
                    q.add(new int[]{X,Y,time+1});
                    // Decrement the count of fresh oranges.
                    ones--;
                    // Mark this orange as rotten in the grid to avoid reprocessing.
                    grid[X][Y] = 2;
                }
            }
        }

        // After BFS, if all fresh oranges have rotted (ones == 0), return the maximum time recorded.
        // Otherwise, if there are still fresh oranges left (ones > 0), it means they are unreachable, so return -1.
        return ones == 0 ? ans : -1;
    }
}
```

## Interview Tips
*   **Explain BFS clearly**: Articulate why BFS is the correct approach for this "spreading" problem.
*   **Walk through an example**: Use a small grid to demonstrate how the queue and `ones` counter change step-by-step.
*   **Discuss edge cases**: Be prepared to talk about grids with no fresh oranges, grids with no rotten oranges, or grids where some oranges are isolated.
*   **Clarify the meaning of `ans`**: Emphasize that `ans` tracks the time of the *last* orange to rot, which is the minimum time for *all* to rot.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify BFS as the appropriate algorithm.
- [ ] Correctly initialize the queue with all starting rotten oranges.
- [ ] Accurately count initial fresh oranges.
- [ ] Implement the BFS loop with correct neighbor exploration.
- [ ] Handle boundary conditions for neighbors.
- [ ] Mark visited/rotted oranges to prevent cycles and redundant work.
- [ ] Correctly update the time for newly rotten oranges.
- [ ] Manage the `ones` counter to track remaining fresh oranges.
- [ ] Implement the final check to return `ans` or `-1`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   994. Rotting Oranges (This problem)
*   733. Flood Fill
*   102. Binary Tree Level Order Traversal
*   1162. As Far from Land as Possible
*   1091. Shortest Path in Binary Matrix

## Tags
`Array` `Breadth-First Search` `Queue` `Matrix`
