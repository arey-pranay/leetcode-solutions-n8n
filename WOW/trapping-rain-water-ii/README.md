# Trapping Rain Water Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Heap (Priority Queue)` `Matrix`  
**Time:** O(m \cdot n \log(m \cdot n)  
**Space:** O(m \cdot n)

---

## Solution (java)

```java
// initially humne queue m saari boundaries daali , phir smallest waali nikaal kr uske neighbors daalenge aur ese daalte jaayenge 
class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length,n = heightMap[0].length;
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->a[0]-b[0]);
        boolean[][] vis = new boolean[m][n];
        for(int row=0;row<m;row++){
            pq.offer(new int[]{heightMap[row][0],row,0});
            vis[row][0] = true;
            pq.offer(new int[]{heightMap[row][n-1],row,n-1});
            vis[row][n-1] = true;
        }
        for(int col=0;col<n;col++){
            pq.offer(new int[]{heightMap[0][col],0,col});
            vis[0][col] = true;
            pq.offer(new int[]{heightMap[m-1][col],m-1,col});
            vis[m-1][col] = true;
        }
        int water = 0;
        while(!pq.isEmpty()){
            int[] cell= pq.poll();
            int height = cell[0], x=cell[1], y=cell[2];
            for(int i=0;i<4;i++){
                int X = x+neigh[i], Y = y+neigh[i+1];
                if(X<0 || Y<0 || X==m || Y==n || vis[X][Y]) continue;
                water += Math.max(0,height - heightMap[X][Y]);
                pq.offer(new int[]{Math.max(height,heightMap[X][Y]), X, Y});
                vis[X][Y] = true;
            }
        }
        return water;
    }
}
```

---

---
## Quick Revision
Given a 2D elevation map, calculate how much water it can trap after raining using a min-heap.
Initialize the boundary cells in a min-heap, then greedily process the lowest boundary, trapping water for unvisited neighbors and pushing them inward.

## Intuition
Unlike the 1D version where we use two pointers from the edges inward based on maximums, the 2D version requires us to consider boundaries from all four sides. The "aha moment" is realizing that water retention is strictly bottlenecked by the lowest boundary surrounding an inner cell. By utilizing a min-heap (priority queue), we can always process the absolute lowest boundary cell first. Since it is the lowest boundary, any water trapped at its unvisited neighbors is bounded precisely by this cell's height, allowing us to safely calculate water and push the neighbor into the heap with an updated effective height.

## Algorithm
1. Handle edge cases where dimensions $m$ or $n$ are less than 3, returning 0 since no water can be trapped.
2. Initialize a min-heap to store cells as `[height, row, col]`, ordered by their height in ascending order.
3. Initialize a 2D boolean visited array of dimensions $m \times n$.
4. Push all boundary cells (first and last rows, first and last columns) of the `heightMap` into the min-heap and mark them as visited.
5. Initialize a variable `water` to 0 to keep track of the total trapped water.
6. While the min-heap is not empty:
   - Pop the cell with the lowest height (`height`, `x`, `y`).
   - Iterate through its four neighbors using directional offsets.
   - For each valid unvisited neighbor `(X, Y)`:
     - Calculate trapped water: `Math.max(0, height - heightMap[X][Y])`.
     - Add this to the total `water`.
     - Push the neighbor into the min-heap with its effective boundary height: `Math.max(height, heightMap[X][Y])`, and mark it as visited.
7. Return the total accumulated `water`.

## Concept to Remember
- **Greedy Boundary Processing:** In 2D trapping rain water, the lowest outer barrier dictates the water level, making a min-heap essential for greedy expansion.
- **Dijkstra-like Traversal:** The propagation mirrors Dijkstra's algorithm where the distance/cost function is replaced by the maximum height constraint along the path.
- **State Compression:** Using a priority queue to always expand the frontier from the minimum possible boundary.

## Common Mistakes
- Failing to push all four borders (top, bottom, left, right rows/columns) into the priority queue initially.
- Forgetting to mark newly discovered neighbor cells as visited when pushing them into the heap, leading to infinite loops or redundant processing.
- Updating the heap with the neighbor's original height instead of the maximum of the current boundary height and the neighbor's height.
- Assuming 1D two-pointer logic can be directly extended to 2D matrices.

## Complexity Analysis
- Time: $O(m \cdot n \log(m \cdot n))$ - Each cell is pushed and popped from the priority queue at most once, and heap operations take logarithmic time relative to the number of cells.
- Space: $O(m \cdot n)$ - Required for the visited matrix and the priority queue holding up to all cells in the grid.

## Commented Code
```java
class Solution {
    // Define direction arrays for moving Up, Right, Down, Left (dx, dy pairs)
    int[] neigh = new int[]{-1, 0, 1, 0, -1};
    
    public int trapRainWater(int[][] heightMap) {
        // Get the number of rows (m) and columns (n) in the height map
        int m = heightMap.length, n = heightMap[0].length;
        
        // Min-heap to store cells as [height, row, col], ordered by height ascending
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Visited matrix to keep track of processed cells and prevent reprocessing
        boolean[][] vis = new boolean[m][n];
        
        // Add all left and right boundary columns to the priority queue
        for (int row = 0; row < m; row++) {
            pq.offer(new int[]{heightMap[row][0], row, 0});
            vis[row][0] = true;
            pq.offer(new int[]{heightMap[row][n - 1], row, n - 1});
            vis[row][n - 1] = true;
        }
        
        // Add all top and bottom boundary rows to the priority queue
        for (int col = 0; col < n; col++) {
            pq.offer(new int[]{heightMap[0][col], 0, col});
            vis[0][col] = true;
            pq.offer(new int[]{heightMap[m - 1][col], m - 1, col});
            vis[m - 1][col] = true;
        }
        
        // Initialize total trapped water accumulator
        int water = 0;
        
        // Process cells starting from the lowest boundary inward
        while (!pq.isEmpty()) {
            // Extract the current boundary cell with the minimum height
            int[] cell = pq.poll();
            int height = cell[0], x = cell[1], y = cell[2];
            
            // Explore all 4 neighboring directions (Up, Right, Down, Left)
            for (int i = 0; i < 4; i++) {
                int X = x + neigh[i], Y = y + neigh[i + 1];
                
                // Skip if the neighbor is out of bounds or has already been visited
                if (X < 0 || Y < 0 || X == m || Y == n || vis[X][Y]) continue;
                
                // Water trapped is the difference between current boundary height and neighbor height
                water += Math.max(0, height - heightMap[X][Y]);
                
                // Push the neighbor into the heap with its effective wall height (max of current wall and its own height)
                pq.offer(new int[]{Math.max(height, heightMap[X][Y]), X, Y});
                
                // Mark the neighbor as visited so it is never processed again
                vis[X][Y] = true;
            }
        }
        
        // Return the final computed trapped water amount
        return water;
    }
}
```

## Interview Tips
- Clarify constraints: Mention that grids smaller than $3 \times 3$ cannot trap any water.
- Explain the analogy: Relate this to a fortress wall where water spills over the lowest wall section first.
- Discuss alternatives: Briefly mention why two pointers fail in 2D (due to internal peaks blocking water flows in multiple directions).

## Revision Checklist
- [ ] Understand why a min-heap is necessary instead of a max-heap or queue.
- [ ] Correctly populate all four outer boundaries of the 2D matrix.
- [ ] Ensure visited states are tracked properly to avoid duplicate processing.
- [ ] Calculate trapped water using `Math.max(0, height - heightMap[X][Y])`.
- [ ] Push neighbors with `Math.max(height, heightMap[X][Y])` into the heap.

## Similar Problems
- Trapping Rain Water
- Maximal Rectangle
- Pacific Atlantic Water Flow
- Wall and Gates

## Tags
`Array` `Matrix` `Heap (Priority Queue)` `Breadth-First Search` `Greedy`
