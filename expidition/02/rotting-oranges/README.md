# Rotting Oranges

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Matrix`  
**Time:** O(M * N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    int time;
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        time = 2;
        boolean freshLeft = false;
        for(int i = 0;i<m;i++) for(int j=0; j<n; j++)if(grid[i][j]==1) freshLeft = true;
        if(!freshLeft) return 0;
        while(true){
            boolean didSomething = false;
            freshLeft = false;
            for(int i = 0;i<m;i++){
                for(int j=0; j<n; j++){
                    if(grid[i][j]==time){
                        func(grid,i,j);
                        didSomething = true;
                    } 
                }
            }
            time++;
            for(int i = 0;i<m;i++) for(int j=0; j<n; j++)if(grid[i][j]==1) freshLeft = true;
            if(!freshLeft) return time-2;
            if(!didSomething) return -1;  
        }
    }
    public void func(int[][] grid, int x, int y){
        int[] nei = new int[]{-1,0,1,0,-1};
        for(int i = 0; i<4;i++){
            int X = x+nei[i];
            int Y = y+nei[i+1];
            if(X < 0 || Y<0 || X >= grid.length || Y >= grid[0].length) continue;
            if(grid[X][Y] == 1){
                grid[X][Y]=time+1;
            }
        }    
    }
}

```

---

---
## Quick Revision
The problem asks for the minimum time required for all fresh oranges to rot, given that rotten oranges spread to adjacent fresh ones each minute. This is solved by simulating the rotting process using a breadth-first search (BFS) like approach.

## Intuition
The core idea is that rotting spreads outwards from existing rotten oranges. This outward spread suggests a level-by-level processing, similar to how BFS explores a graph. We can think of the grid as a graph where oranges are nodes and adjacency represents the possibility of rotting. The time it takes for an orange to rot is its "distance" from an initial rotten orange. We want to find the maximum such distance.

The provided solution uses a slightly different approach than a typical BFS queue. Instead of a queue, it iterates through the grid multiple times. In each iteration, it identifies oranges that became rotten in the *previous* minute (marked with `time`) and then infects their fresh neighbors, marking them with the *current* minute (`time + 1`). This effectively simulates the spread minute by minute.

## Algorithm
1. Initialize `time` to 2 (representing the first minute of rotting).
2. Scan the grid to check if there are any fresh oranges (value 1). If not, return 0.
3. Enter a `while(true)` loop to simulate minutes.
4. Inside the loop, set a flag `didSomething` to `false` and `freshLeft` to `false`.
5. Iterate through the entire grid:
    a. If an orange is rotten from the *previous* minute (value `time`), call `func` to infect its fresh neighbors. Set `didSomething` to `true`.
6. Increment `time` to represent the next minute.
7. Scan the grid again to check if any fresh oranges remain. If not, return `time - 2` (since `time` was incremented one last time after the last rotting occurred).
8. If `didSomething` is `false` (meaning no oranges rotted in the current minute, but fresh oranges still exist), it's impossible to rot all oranges, so return -1.
9. The `func(grid, x, y)` method:
    a. Defines the relative coordinates of adjacent cells (`nei`).
    b. Iterates through the 4 neighbors of the orange at `(x, y)`.
    c. For each neighbor `(X, Y)`:
        i. Check if it's within grid boundaries.
        ii. If the neighbor is a fresh orange (`grid[X][Y] == 1`), mark it as rotten with the current `time + 1`.

## Concept to Remember
*   **Breadth-First Search (BFS):** The problem can be modeled as finding the shortest path from initial rotten oranges to all fresh oranges. BFS is ideal for this.
*   **Grid Traversal:** Efficiently iterating through a 2D grid is crucial.
*   **State Management:** Keeping track of the state of each orange (fresh, rotten, or rotting at a specific time) is key to the simulation.
*   **Simultaneous Updates:** The rotting process happens in discrete time steps, meaning all oranges that *can* rot in a given minute do so simultaneously.

## Common Mistakes
*   **Incorrectly handling the time variable:** Forgetting to increment `time` or using it incorrectly to mark newly rotten oranges.
*   **Not checking grid boundaries:** Accessing invalid indices when checking neighbors.
*   **Infinite loops:** Not having a proper exit condition for the `while` loop, especially when no fresh oranges can be reached.
*   **Modifying the grid while iterating:** In a standard BFS with a queue, you'd add neighbors to the queue. Here, the grid itself is used to store the "time" of rotting, which is a form of state management. The current approach avoids issues by marking with `time + 1` which is a future state.
*   **Off-by-one errors in return value:** Returning `time` instead of `time - 2` or `time - 1` depending on how `time` is managed.

## Complexity Analysis
*   **Time:** O(M * N) - In the worst case, each cell in the M x N grid is visited a constant number of times (during the initial scan, during the `while` loop iterations, and within the `func` method). The `while` loop runs at most M*N times (if each minute only one new orange rots).
*   **Space:** O(1) - The algorithm uses a constant amount of extra space, as it modifies the input grid in-place and uses a few variables.

## Commented Code
```java
class Solution {
    // Variable to keep track of the current minute. It starts at 2 to distinguish from initial rotten oranges (value 2).
    int time;

    public int orangesRotting(int[][] grid) {
        // Get the dimensions of the grid.
        int m = grid.length;
        int n = grid[0].length;
        // Initialize time to 2. This will represent the minute when oranges rot.
        // 0: empty, 1: fresh, 2: rotten (initial)
        // We use time+1 to mark newly rotten oranges.
        time = 2;
        // Flag to check if there are any fresh oranges left in the grid.
        boolean freshLeft = false;
        // Initial scan to see if there are any fresh oranges.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // If a fresh orange is found, set the flag.
                if(grid[i][j] == 1) {
                    freshLeft = true;
                }
            }
        }
        // If no fresh oranges were found initially, no time is needed.
        if(!freshLeft) {
            return 0;
        }

        // Start a loop that simulates the rotting process minute by minute.
        while(true) {
            // Flag to track if any orange rotted in the current minute.
            boolean didSomething = false;
            // Reset freshLeft flag for the current minute's check.
            freshLeft = false;

            // Iterate through the grid to find oranges that became rotten in the *previous* minute (marked with 'time').
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    // If an orange is rotten from the previous minute (value 'time').
                    if(grid[i][j] == time) {
                        // Infect its fresh neighbors.
                        func(grid, i, j);
                        // Mark that at least one orange rotted in this minute.
                        didSomething = true;
                    }
                }
            }

            // Increment time to represent the next minute.
            time++;

            // After processing all oranges that rotted in the previous minute,
            // scan the grid again to see if any fresh oranges are still left.
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    // If a fresh orange is still found.
                    if(grid[i][j] == 1) {
                        freshLeft = true;
                    }
                }
            }

            // If no fresh oranges are left, we have successfully rotted all of them.
            // Return the total time elapsed. 'time' was incremented one last time, so subtract 2.
            // (Initial rotten are 2, first minute rot is 3, second minute rot is 4, etc.)
            if(!freshLeft) {
                return time - 2;
            }

            // If no oranges rotted in this minute ('didSomething' is false) AND there are still fresh oranges left,
            // it means the remaining fresh oranges are unreachable.
            if(!didSomething) {
                return -1;
            }
        }
    }

    // Helper function to infect adjacent fresh oranges.
    public void func(int[][] grid, int x, int y) {
        // Define the relative coordinates for the 4 adjacent neighbors (up, right, down, left).
        int[] nei = new int[]{-1, 0, 1, 0, -1}; // dx: -1, 0, 1, 0; dy: 0, 1, 0, -1

        // Iterate through the 4 neighbors.
        for(int i = 0; i < 4; i++) {
            // Calculate the coordinates of the neighbor.
            int X = x + nei[i];
            int Y = y + nei[i+1];

            // Check if the neighbor's coordinates are within the grid boundaries.
            if(X < 0 || Y < 0 || X >= grid.length || Y >= grid[0].length) {
                // If out of bounds, skip this neighbor.
                continue;
            }
            // Check if the neighbor is a fresh orange (value 1).
            if(grid[X][Y] == 1) {
                // If it's a fresh orange, mark it as rotten with the current time + 1.
                // This means it will rot in the *next* minute.
                grid[X][Y] = time + 1;
            }
        }
    }
}
```

## Interview Tips
*   **Explain the BFS analogy:** Even though the code doesn't use a queue, explain how the problem is fundamentally a BFS problem where you're finding the maximum "distance" from a source (rotten orange) to all reachable nodes (fresh oranges).
*   **Discuss the time variable's role:** Clarify how `time` is used to track the minutes and how `time + 1` marks newly rotting oranges for the next iteration. This is a clever way to avoid a separate queue.
*   **Handle edge cases:** Be prepared to discuss what happens if there are no fresh oranges, no rotten oranges, or if some fresh oranges are isolated.
*   **Walk through an example:** Use a small grid to trace the execution of your algorithm, showing how `time` changes and how oranges get infected.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the BFS pattern for shortest path/minimum time problems on grids.
- [ ] Implement grid boundary checks correctly.
- [ ] Manage the state of oranges (fresh, rotten, rotting).
- [ ] Handle the time progression accurately.
- [ ] Identify and handle impossible scenarios (unreachable fresh oranges).
- [ ] Analyze time and space complexity.

## Similar Problems
*   994. Rotting Oranges (This problem)
*   733. Flood Fill
*   1091. Shortest Path in Binary Matrix
*   1162. As Far from Land as Possible
*   1293. Shortest Path in a Grid with Obstacles Elimination

## Tags
`Array` `Breadth-First Search` `Matrix` `Simulation`

## My Notes
Beats 99% time and 90% memory. Nice Algorithm
