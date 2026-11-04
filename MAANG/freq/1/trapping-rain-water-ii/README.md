# Trapping Rain Water Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Heap (Priority Queue)` `Matrix`  
**Time:** O(M \cdot N \log(M \cdot N)  
**Space:** O(M \cdot N)

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
Given a 2D elevation map, calculate how much water it can trap after raining using a min-heap approach.
We push all boundary cells into a priority queue, then iteratively pop the lowest boundary and process its unvisited neighbors, trapping water if the neighbor is lower.

## Intuition
The 1D trapping rain water problem relies on moving pointers inward while tracking the maximum height seen so far. In 2D, the "boundaries" form a closed loop around any inner cell. Water trapped at any inner cell is bottlenecked by the lowest boundary wall surrounding it. 
The "aha moment" is realizing that water will always spill over the *lowest* boundary first. By maintaining a min-heap of all current boundary cells, we can greedily process the lowest boundary, expand inward, and guarantee that any neighbor we encounter is constrained primarily by this current boundary height. If a neighbor is lower than the current boundary, it traps water equal to the difference; if it is higher, it becomes the new, taller boundary constraint for subsequent cells.

## Algorithm
1. Handle edge cases where the grid dimensions are too small to trap water (e.g., $m < 3$ or $n < 3$).
2. Initialize a min-heap (`PriorityQueue`) ordered by cell height, and a 2D boolean visited array of the same dimensions.
3. Push all outer boundary cells (first and last rows, first and last columns) into the min-heap and mark them as visited.
4. Initialize a variable `water` to 0 to accumulate the trapped water volume.
5. While the min-heap is not empty:
   - Extract the cell with the minimum height (`height`, `x`, `y`).
   - Iterate through its 4 cardinal neighbors (`X`, `Y`).
   - Skip neighbors that are out of bounds or already visited.
   - If the neighbor's height is less than the current cell's height, it traps water equal to `height - heightMap[X][Y]`. Add this difference to `water`.
   - Push the neighbor into the min-heap with an effective height of `Math.max(height, heightMap[X][Y])` and mark it as visited.
6. Return the total accumulated `water`.

## Concept to Remember
- **Greedy Approach with Min-Heaps:** Using a priority queue to always process the globally optimal (lowest) boundary constraint first.
- **Dimensional Generalization:** Extending 1D two-pointer techniques to 2D grid graphs using Dijkstra-like shortest path or surface propagation concepts.
- **Boundary Shrinking:** Treating the outermost unvisited layer as the active frontier that progressively marches inward.

## Common Mistakes
- Forgetting to mark boundary cells as visited when initially pushing them into the priority queue, leading to infinite loops or duplicate processing.
- Using a max-heap instead of a min-heap, which violates the principle that water spills over the lowest wall.
- Failing to update the pushed neighbor's height to `Math.max(current_height, neighbor_height)`, which incorrectly allows water to escape over lower walls.
- Incorrectly handling grid boundary checks for the 4-directional traversal arrays.

## Complexity Analysis
- Time: $O(M \cdot N \log(M \cdot N))$ - Each of the $M \times N$ cells is pushed and popped from the priority queue at most once, and heap operations take logarithmic time relative to the number of cells.
- Space: $O(M \cdot N)$ - The visited matrix and the priority queue both store up to $M \times N$ elements at maximum capacity.

## Commented Code
```java
class Solution {
    // Array to easily navigate 4 directions (up, right, down, left) using sliding pairs
    int[] neigh = new int[]{-1, 0, 1, 0, -1};
    
    public int trapRainWater(int[][] heightMap) {
        // Get the number of rows (m) and columns (n) in the grid
        int m = heightMap.length, n = heightMap[0].length;
        
        // Min-heap to store cells as [height, row, col], ordered ascending by height
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Visited matrix to keep track of cells already added to the heap
        boolean[][] vis = new boolean[m][n];
        
        // Add all cells in the first and last columns to the min-heap as initial boundaries
        for (int row = 0; row < m; row++) {
            pq.offer(new int[]{heightMap[row][0], row, 0});
            vis[row][0] = true;
            pq.offer(new int[]{heightMap[row][n - 1], row, n - 1});
            vis[row][n - 1] = true;
        }
        
        // Add all cells in the first and last rows to the min-heap as initial boundaries
        for (int col = 0; col < n; col++) {
            pq.offer(new int[]{heightMap[0][col], 0, col});
            vis[0][col] = true;
            pq.offer(new int[]{heightMap[m - 1][col], m - 1, col});
            vis[m - 1][col] = true;
        }
        
        // Variable to accumulate total trapped water
        int water = 0;
        
        // Process boundaries from the lowest height outward/inward
        while (!pq.isEmpty()) {
            // Extract the boundary cell with the lowest height
            int[] cell = pq.poll();
            int height = cell[0], x = cell[1], y = cell[2];
            
            // Explore all 4 cardinal directions (up, right, down, left)
            for (int i = 0; i < 4; i++) {
                int X = x + neigh[i], Y = y + neigh[i + 1];
                
                // Skip if neighbor is out of bounds or has already been visited
                if (X < 0 || Y < 0 || X == m || Y == n || vis[X][Y]) continue;
                
                // If neighbor is shorter than current boundary, it traps water
                water += Math.max(0, height - heightMap[X][Y]);
                
                // Push neighbor into heap with updated effective boundary height and mark visited
                pq.offer(new int[]{Math.max(height, heightMap[X][Y]), X, Y});
                vis[X][Y] = true;
            }
        }
        
        // Return the total calculated water volume
        return water;
    }
}
```

## Interview Tips
- Clarify constraints upfront: mention that grids with dimensions smaller than $3 \times 3$ cannot trap any water and will return 0.
- Draw a small 3x3 matrix on the whiteboard to explain how the min-heap acts as a moving boundary constraint.
- Compare this problem directly with Trapping Rain Water I to demonstrate your ability to scale concepts from 1D to 2D.
- Discuss alternative approaches like Dijkstra's algorithm perspective to show depth in graph theory.

## Revision Checklist
- [ ] Understand why a min-heap is required instead of a max-heap.
- [ ] Know how to initialize the boundary elements correctly.
- [ ] Remember the `Math.max(height, heightMap[X][Y])` logic when pushing neighbors.
- [ ] Practice writing the 4-direction offset loop cleanly.
- [ ] Review time and space complexity derivations.

## Similar Problems
- `Trapping Rain Water`
- `Swim in Rising Water`
- `Minimum Cost to Reach Destination in Time`
- `Pacific Atlantic Water Flow`

## Tags
`Array` `Matrix` `Heap (Priority Queue)` `Breadth-First Search` `Graph`
