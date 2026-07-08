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
The problem asks for the minimum time required for all fresh oranges to rot, given a grid of oranges and their rotting spread.
This is solved using Breadth-First Search (BFS) starting from all initially rotten oranges.

## Intuition
The rotting process spreads like a wave from rotten oranges to adjacent fresh oranges. This wave-like propagation is a classic characteristic of BFS. We can think of each minute as a "level" in the BFS. All oranges that rot in minute `t` will infect their fresh neighbors in minute `t+1`. By starting BFS from all initial rotten oranges simultaneously, we ensure we find the shortest time for any orange to rot, and thus the minimum time for all to rot.

## Algorithm
1.  **Initialization**:
    *   Get the dimensions of the grid (`m` rows, `n` columns).
    *   Create a queue (`q`) to store oranges that are currently rotting. Each element in the queue will be an array `[row, col, time]`, where `time` is the minute the orange at `(row, col)` became rotten.
    *   Define an array `neighs` for easy access to the four cardinal directions (up, down, left, right). `neighs = {-1, 0, 1, 0, -1}` allows us to get `(dx, dy)` pairs by `(neighs[i], neighs[i+1])` for `i` from 0 to 3.
    *   Initialize a counter `ones` to keep track of the number of fresh oranges.
2.  **Populate Initial State**:
    *   Iterate through the entire grid.
    *   If an orange is rotten (`grid[i][j] == 2`), add it to the queue with time `0` (`q.add(new int[]{i, j, 0})`).
    *   If an orange is fresh (`grid[i][j] == 1`), increment the `ones` counter.
3.  **Handle Edge Case**:
    *   If `ones` is `0` initially (no fresh oranges), return `0` as no time is needed.
4.  **BFS Traversal**:
    *   Initialize `ans` to `-1`. This will store the maximum time taken for any orange to rot.
    *   While the queue is not empty:
        *   Dequeue an orange `curr = q.poll()`.
        *   Extract its row (`curr[0]`), column (`curr[1]`), and the time it became rotten (`time = curr[2]`).
        *   Update `ans` to the current `time`. This ensures `ans` always holds the latest time an orange rotted.
        *   For each of the four neighbors of the current orange:
            *   Calculate the neighbor's coordinates `X = curr[0] + neighs[i]` and `Y = curr[1] + neighs[i+1]`.
            *   **Boundary Check**: If `X` or `Y` are out of bounds (`X < 0 || Y < 0 || X == m || Y == n`), skip this neighbor.
            *   **Fresh Orange Check**: If the neighbor is a fresh orange (`grid[X][Y] == 1`):
                *   Enqueue the neighbor with an incremented time: `q.add(new int[]{X, Y, time + 1})`.
                *   Decrement the `ones` counter.
                *   Mark the neighbor as rotten: `grid[X][Y] = 2`.
5.  **Final Result**:
    *   After the BFS completes, check if `ones` is `0`.
    *   If `ones == 0`, it means all fresh oranges have rotted. Return `ans` (the maximum time recorded).
    *   If `ones > 0`, it means some fresh oranges are unreachable and could not rot. Return `-1`.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Ideal for finding the shortest path or minimum time in an unweighted graph/grid where states propagate level by level.
*   **Queue Data Structure**: Essential for BFS to maintain the order of nodes to visit, ensuring exploration happens layer by layer.
*   **Grid Traversal**: Efficiently iterating through a 2D array and checking adjacent cells.
*   **State Management**: Modifying the grid in-place to mark visited/rotted states prevents cycles and redundant processing.

## Common Mistakes
*   **Not handling the initial state correctly**: Forgetting to add all initial rotten oranges to the queue or not counting all fresh oranges.
*   **Incorrectly updating time**: Not incrementing the time for newly infected oranges, or using the wrong time value.
*   **Not checking boundaries**: Accessing grid cells outside the defined `m x n` dimensions, leading to `ArrayIndexOutOfBoundsException`.
*   **Not handling unreachable oranges**: Failing to return `-1` when some fresh oranges remain unrotten after the BFS.
*   **Modifying the grid before checking its value**: For example, marking an orange as rotten before checking if it was initially fresh.

## Complexity Analysis
*   **Time**: O(m * n) - Each cell in the grid is visited and processed at most a constant number of times (enqueued, dequeued, checked as a neighbor).
*   **Space**: O(m * n) - In the worst case, all oranges could be fresh and become rotten simultaneously, leading to the queue storing all `m * n` cells.

## Commented Code
```java
class Solution {
    public int orangesRotting(int[][] grid) {
        // Get the number of rows and columns in the grid.
        int m = grid.length;
        int n = grid[0].length;

        // Initialize a queue for BFS. It will store [row, col, time].
        Queue<int[]> q = new LinkedList<>();

        // Define the relative movements for neighbors: up, right, down, left.
        // neighs[i] and neighs[i+1] give the (dx, dy) for the i-th neighbor.
        int[] neighs = new int[]{-1,0,1,0,-1};

        // Initialize a counter for the number of fresh oranges.
        int ones = 0;

        // Iterate through the grid to populate the initial state.
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // If an orange is rotten (value 2), add it to the queue with time 0.
                if(grid[i][j] == 2) {
                    q.add(new int[]{i, j, 0});
                }
                // If an orange is fresh (value 1), increment the fresh orange count.
                if(grid[i][j] == 1) {
                    ones++;
                }
            }
        }

        // If there are no fresh oranges initially, no time is needed.
        if(ones == 0) {
            return 0;
        }

        // Initialize the answer (maximum time taken) to -1.
        // This will be updated as oranges rot.
        int ans = -1;

        // Start the BFS traversal.
        while(!q.isEmpty()){
            // Dequeue the current rotten orange and its rotting time.
            int[] curr = q.poll();
            int time = curr[2];

            // Update the maximum time recorded so far.
            ans = time;

            // Explore the four neighbors of the current rotten orange.
            for(int i = 0; i < 4; i++){
                // Calculate the coordinates of the neighbor.
                int X = curr[0] + neighs[i];
                int Y = curr[1] + neighs[i+1];

                // Check if the neighbor is within the grid boundaries.
                if(X < 0 || Y < 0 || X == m || Y == n) {
                    continue; // Skip if out of bounds.
                }

                // Check if the neighbor is a fresh orange.
                if(grid[X][Y] == 1){
                    // If it's fresh, add it to the queue to rot in the next minute.
                    q.add(new int[]{X, Y, time + 1});
                    // Decrement the count of fresh oranges.
                    ones--;
                    // Mark this orange as rotten in the grid.
                    grid[X][Y] = 2;
                }
            }
        }

        // After BFS, if all fresh oranges have rotted (ones == 0), return the max time.
        // Otherwise, some fresh oranges are unreachable, so return -1.
        return ones == 0 ? ans : -1;
    }
}
```

## Interview Tips
*   **Explain BFS clearly**: Articulate why BFS is the correct approach for this problem, emphasizing the level-by-level spread.
*   **Trace an example**: Walk through a small grid example, showing how the queue changes and how `ones` and `ans` are updated.
*   **Discuss edge cases**: Be prepared to talk about scenarios like an empty grid, a grid with no fresh oranges, or a grid where some oranges are unreachable.
*   **Clarify state representation**: Explain how the `grid` values (0, 1, 2) and the queue elements `[row, col, time]` represent the state of the system.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify BFS as the appropriate algorithm.
- [ ] Correctly initialize the queue with all rotten oranges and count fresh oranges.
- [ ] Implement the BFS loop, processing neighbors and updating time.
- [ ] Handle boundary conditions for grid access.
- [ ] Correctly update the grid state (marking fresh as rotten).
- [ ] Track the number of fresh oranges to determine reachability.
- [ ] Return the correct value based on whether all oranges rotted.
- [ ] Analyze time and space complexity.

## Similar Problems
*   994. Rotting Oranges (This problem)
*   733. Flood Fill
*   1091. Shortest Path in Binary Matrix
*   1162. As Far from Land as Possible
*   1293. Shortest Path in a Grid with Obstacles Elimination

## Tags
`Array` `Breadth-First Search` `Queue` `Matrix`
