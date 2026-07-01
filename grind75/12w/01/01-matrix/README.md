# 01 Matrix

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Breadth-First Search` `Matrix`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // mark 1s as -1
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> q = new LinkedList<>();// it guarantees that the first update is always the correct update (i.e., the one with least value)// sbse pehle 0 wale apne neighbours ko 1 bnayege, fir 1 wale apne univisted neighbours ko 2 bnayege, etc 
        for(int i=0;i<m;i++){ // saare 1 ko -1 kiya
            for(int j=0;j<n;j++){
                if(mat[i][j] == 0)q.add(new int[]{i,j}); // hum shuru hi 0 se kr rhe hai
                else mat[i][j] = -1;
            }
        }
        int[] neighs = new int[]{-1,0,1,0,-1};
        while(!q.isEmpty()){ // har -1 k aas paas k 4 hi dekh rhe hai  aur -1  ko uske padosiyo se +1 krre agr padosi -1 nahi hai to
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            for(int i=0;i<4;i++){
                int X = x + neighs[i];
                int Y = y + neighs[i+1];
                if(X<0 || Y<0 || X==m || Y==n || mat[X][Y]!=-1) continue;
                mat[X][Y] = mat[x][y] + 1;
                q.add(new int[]{X,Y});
            }
        }
        return mat;
    }
}
// 0,0
// 0,1 => 1,1 = 1
// // 0,2 
// 1,0 => 1,1 = 1 & 2,0 = 1
// 1,2 => 1,1 = 1 & 2,2 = 1
// 1,1 => 2,1 = 2
// 2,0
// 2,2
// 2,1
```

---

---

## Quick Revision
Given a matrix of binary values (0s and 1s), replace all the 1s with their Manhattan distance from the nearest 0.
The problem can be solved using a Breadth-First Search (BFS) algorithm.

## Intuition
The key insight here is that we can use BFS to find the shortest path between each cell containing a 1 and its nearest 0. By marking all cells at a certain distance as -1, we essentially create a wavefront that propagates from the 0s towards the 1s.

## Algorithm

1. Mark all 1s as -1.
2. Create a queue to store cells to be processed.
3. Initialize the queue with all 0s and their indices.
4. While the queue is not empty:
	* Dequeue a cell (x, y) from the queue.
	* For each neighbor (i.e., cells at distance 1) of (x, y):
		+ If the neighbor has not been visited before (-1), mark it as -1 and add its index to the queue.
5. Return the updated matrix.

## Concept to Remember
* Breadth-First Search (BFS)
* Manhattan distance (or L1 distance)
* Wavefront propagation

## Common Mistakes
* Failing to initialize the queue with all 0s.
* Not marking cells as -1 when they are dequeued from the queue.
* Misunderstanding the concept of wavefront propagation.

## Complexity Analysis
- Time: O(m*n) where m and n are the dimensions of the matrix, since we visit each cell once.
- Space: O(m*n) for storing the matrix.

## Commented Code

```java
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // Mark all 1s as -1
        int m = mat.length;
        int n = mat[0].length;
        
        // Create a queue to store cells to be processed
        Queue<int[]> q = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) { // If cell is a 0, add its index to the queue
                    q.add(new int[]{i, j});
                } else { // Mark cell as -1
                    mat[i][j] = -1;
                }
            }
        }

        int[] neighs = new int[]{-1, 0, 1, 0}; // Define neighbor offsets
        
        while (!q.isEmpty()) {
            // Dequeue a cell (x, y) from the queue
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++) {
                // Calculate neighbor indices
                int X = x + neighs[i];
                int Y = y + neighs[i + 1];

                if (X >= 0 && Y >= 0 && X < m && Y < n && mat[X][Y] != -1) { // If neighbor is within bounds and not visited before
                    // Mark neighbor as -1 and add its index to the queue
                    mat[X][Y] = mat[x][y] + 1;
                    q.add(new int[]{X, Y});
                }
            }
        }

        return mat; // Return updated matrix
    }
}
```

## Interview Tips

* Be able to explain the concept of wavefront propagation.
* Understand how BFS is used to find shortest paths in graphs.
* Pay attention to details when implementing the algorithm.

## Revision Checklist
- [ ] Mark all 1s as -1 initially.
- [ ] Initialize queue with all 0s and their indices.
- [ ] Use correct neighbor offsets for BFS.

## Similar Problems

* LeetCode: 542. 01 Matrix II
* LeetCode: 287. Find the Duplicate Number
