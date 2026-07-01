# Find The Safest Path In A Grid

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Breadth-First Search` `Union-Find` `Heap (Priority Queue)` `Matrix`  
**Time:** O(n^2)  
**Space:** O(n^2)

---

## Solution (java)

```java
class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1)
            return 0;

        Queue<int[]> thief = new LinkedList<>();
        int[][] mat = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    thief.offer(new int[] { i, j });
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        int[] neighs = {-1, 0, 1, 0, -1};

        while (!thief.isEmpty()) {
            int[] cur = thief.poll();

            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X < 0 || Y < 0 || X >= n || Y >= n || mat[X][Y] != -1)
                    continue;

                mat[X][Y] = mat[x][y] + 1;
                thief.offer(new int[] { X, Y });
            }
        }

        // Maximum Bottleneck Path (Priority Queue)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[n][n];

        pq.offer(new int[] { mat[0][0], 0, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int safe = cur[0];
            int x = cur[1];
            int y = cur[2];

            if (vis[x][y])
                continue;

            vis[x][y] = true;

            if (x == n - 1 && y == n - 1)
                return safe;

            for (int i = 0; i < 4; i++) {
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X < 0 || Y < 0 || X >= n || Y >= n || vis[X][Y])
                    continue;

                pq.offer(new int[] {
                    Math.min(safe, mat[X][Y]),
                    X,
                    Y
                });
            }
        }

        return 0;
    }
}
```

---

---
## Quick Revision
Find the safest path in a grid where some cells are blocked by thieves. We solve this problem using a breadth-first search (BFS) algorithm to find the minimum safe factor for each cell, and then use a priority queue to find the maximum bottleneck path.

## Intuition
The key insight here is that we can break down the problem into two parts: finding the minimum safe factor for each cell, and then finding the maximum bottleneck path. By using BFS to calculate the minimum safe factor, we can efficiently propagate this information across the grid. Then, by using a priority queue to select the most critical cells, we can find the maximum bottleneck path.

## Algorithm
1. Initialize a 2D matrix `mat` to store the minimum safe factor for each cell.
2. Use BFS to calculate the minimum safe factor for each cell. Start from each blocked cell (thief) and mark its neighbors with a minimum safe factor of 0.
3. Create a priority queue to select the most critical cells based on their minimum safe factor.
4. For each selected cell, mark its neighbors as visited and update their minimum safe factor if necessary.
5. Repeat steps 3-4 until the maximum bottleneck path is found.

## Concept to Remember
* **Breadth-First Search (BFS)**: A graph traversal algorithm that visits all vertices at a given depth before moving on to the next level.
* **Priority Queue**: A data structure that allows for efficient retrieval of the element with the highest priority.
* **Minimum Safe Factor**: The minimum factor by which each cell's value is reduced due to the presence of thieves.

## Common Mistakes
* Failing to properly initialize the `mat` matrix, leading to incorrect calculations.
* Not correctly implementing the BFS algorithm, resulting in an incomplete or incorrect solution.
* Misusing the priority queue, causing it to select cells with low safe factors instead of high ones.

## Complexity Analysis
- Time: O(n^2) - The grid size is n x n, and we perform a constant number of operations for each cell.
- Space: O(n^2) - We need to store the minimum safe factor for each cell in the `mat` matrix.

## Commented Code
```java
class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        // Get the grid size
        int n = grid.size();

        // Check if there are any thieves at the edges
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1)
            return 0;

        // Initialize a queue for BFS and a matrix to store safe factors
        Queue<int[]> thief = new LinkedList<>();
        int[][] mat = new int[n][n];

        // Mark blocked cells as thieves and initialize the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    thief.offer(new int[] {i, j});
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        // Define the possible movements
        int[] neighs = {-1, 0, 1, 0, -1};

        // Perform BFS to calculate safe factors
        while (!thief.isEmpty()) {
            int[] cur = thief.poll();

            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X < 0 || Y < 0 || X >= n || Y >= n || mat[X][Y] != -1)
                    continue;

                mat[X][Y] = mat[x][y] + 1;
                thief.offer(new int[] {X, Y});
            }
        }

        // Initialize a priority queue for the maximum bottleneck path
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[n][n];

        // Add the starting cell to the priority queue
        pq.offer(new int[] {mat[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int safe = cur[0];
            int x = cur[1];
            int y = cur[2];

            if (vis[x][y])
                continue;

            vis[x][y] = true;

            // Check if we've reached the bottom-right cell
            if (x == n - 1 && y == n - 1)
                return safe;

            for (int i = 0; i < 4; i++) {
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X < 0 || Y < 0 || X >= n || Y >= n || vis[X][Y])
                    continue;

                pq.offer(new int[] {
                    Math.min(safe, mat[X][Y]),
                    X,
                    Y
                });
            }
        }

        return 0;
    }
}
```

## Interview Tips
* Make sure to properly initialize the `mat` matrix and implement the BFS algorithm correctly.
* Use a priority queue to efficiently select the most critical cells based on their minimum safe factor.
* Pay attention to edge cases, such as when there are thieves at the edges of the grid.

## Revision Checklist
- [ ] Review the initialization of the `mat` matrix.
- [ ] Ensure that the BFS algorithm is correctly implemented.
- [ ] Verify that the priority queue is used correctly to select cells with high safe factors.
- [ ] Test the solution with various edge cases, such as an empty grid or a grid with only one thief.

## Similar Problems
* Minimum Bounded Path in Grid: Find the minimum path length between two points in a grid where some cells have obstacles.
* Shortest Path in a Grid with Obstacles: Find the shortest path from a source cell to a target cell in a grid with obstacles.
* Maximum Product of 3 Numbers in an Array: Given three numbers, find the maximum product of these numbers.

## Tags
`Array` `Hash Map` `Breadth-First Search` `Priority Queue`
