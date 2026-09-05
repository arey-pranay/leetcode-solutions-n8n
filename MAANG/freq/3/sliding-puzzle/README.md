# Sliding Puzzle

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Backtracking` `Breadth-First Search` `Memoization` `Matrix` `Heuristic Search` `Bidirectional Search` `A* Search`  
**Time:** O(M * N * 2^(M*N)  
**Space:** O(M * N * 2^(M*N)

---

## Solution (java)

```java
class Solution { 
    int[] neigh = {-1,0,1,0,-1};
    int solvedHash;
    int m;
    int n;
    HashSet<Integer> hashes = new HashSet<>();
    public int slidingPuzzle(int[][] board) {
      m = board.length;
      n = board[0].length;
      int x =0,y=0;
      int[][] solved = new int[m][n];
      int num = 1;
      for(int i=0;i<m;i++) for(int j=0;j<n;j++){solved[i][j] = num++; if(board[i][j]==0){x=i;y=j;}}
      solved[m-1][n-1] = 0;  
      solvedHash = encode(solved);                                    
      return bfs(x,y,board);
    }
    public int bfs(int x, int y, int[][] board){
      int ans = Integer.MAX_VALUE;
      int startHash = encode(board);
      Queue<int[]> q = new LinkedList<>(); //x,y,moves,hash
      q.offer(new int[]{x,y,0,encode(board)});
      hashes.add(startHash);
      int iteration=1;
      while(!q.isEmpty()){
        int[] curr = q.poll();
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];
        if(isSolved(hash)) return moves;
        int[][] newBoard = decode(hash);
        
        for(int i=0;i<4;i++){
            int X = cx+neigh[i];
            int Y = cy+neigh[i+1];
            if(X<0 || Y<0 || X>=m || Y>=n) continue;
            swap(newBoard,cx,cy,X,Y);
            int newHash = encode(newBoard);
            if(!hashes.contains(newHash)){
                hashes.add(newHash); 
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            swap(newBoard,cx,cy,X,Y);
        }
      }
      return -1;
    }
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      int temp = board[i1][j1];
      board[i1][j1] = board[i2][j2];
      board[i2][j2] = temp;
    }
    public boolean isSolved(int hash){
      return solvedHash == hash;
    }
    public int encode(int[][] board){
      int hash = 0;
      for(int i=0;i<m;i++) for(int j=0;j<n;j++){hash*=10; hash+= board[i][j];}
      return hash;
    }
    public int[][] decode(int hash){
        int[][] decodedBoard = new int[m][n];
        int i = m-1;
        int j = n-1;
        while(hash>0){
            decodedBoard[i][j] = hash%10;
            hash /= 10;
            j--;
            if(j==-1) {i--;j=n-1;}
        }
        return decodedBoard;
    }
}
```

---

---
## Quick Revision
This problem asks for the minimum number of moves to solve a sliding puzzle, similar to the 8-puzzle.
We solve it using Breadth-First Search (BFS) to explore all possible states and find the shortest path.

## Intuition
The core idea is that this problem can be modeled as a graph where each state of the puzzle is a node, and a valid move (swapping the blank tile with an adjacent tile) represents an edge. Since we want the minimum number of moves, BFS is the natural choice. The challenge lies in efficiently representing and comparing puzzle states. Using a hash (or string representation) for each board state allows us to quickly check if we've visited a state before, preventing infinite loops and redundant computations.

## Algorithm
1.  **State Representation:** Represent the 2D `board` as a single integer (hash) for efficient storage and comparison in a `HashSet`.
2.  **Target State:** Determine the target solved state and its corresponding hash.
3.  **BFS Initialization:**
    *   Create a queue for BFS, storing `[zero_row, zero_col, moves, current_hash]`.
    *   Add the initial state (given board, its hash, 0 moves, and the initial zero tile's coordinates) to the queue.
    *   Add the initial hash to a `HashSet` to keep track of visited states.
4.  **BFS Loop:**
    *   While the queue is not empty:
        *   Dequeue a state: `[cx, cy, moves, hash]`.
        *   If the current `hash` matches the `solvedHash`, return `moves`.
        *   **Explore Neighbors:** For each of the four possible directions (up, down, left, right):
            *   Calculate the coordinates `(X, Y)` of the potential neighbor tile to swap with the zero tile.
            *   Check if `(X, Y)` is within the board boundaries.
            *   If valid, create a `newBoard` by swapping the zero tile at `(cx, cy)` with the tile at `(X, Y)`.
            *   Calculate the `newHash` of `newBoard`.
            *   If `newHash` has not been visited (i.e., not in the `HashSet`):
                *   Add `newHash` to the `HashSet`.
                *   Enqueue the new state: `[X, Y, moves + 1, newHash]`.
            *   **Backtrack:** Swap the tiles back in `newBoard` to restore it for exploring other neighbors.
5.  **No Solution:** If the queue becomes empty and the solved state is not found, return -1.

## Concept to Remember
*   **Breadth-First Search (BFS):** Optimal for finding the shortest path in an unweighted graph.
*   **State Space Search:** Representing complex problems as graphs where nodes are states and edges are transitions.
*   **Hashing/Serialization:** Efficiently representing and comparing complex data structures (like 2D arrays) for lookup in sets/maps.
*   **Grid Traversal:** Handling movement and boundary checks on a 2D grid.

## Common Mistakes
*   **Inefficient State Representation:** Using 2D arrays directly in the queue or `HashSet` leads to slow comparisons and high memory usage.
*   **Not Handling Visited States:** Failing to use a `HashSet` to track visited states can lead to infinite loops and exponential time complexity.
*   **Incorrect Swap/Backtrack:** Modifying the board in place without correctly swapping back after exploring a neighbor can corrupt subsequent states.
*   **Off-by-One Errors:** In boundary checks or neighbor calculations.
*   **Encoding/Decoding Errors:** Incorrectly converting between the 2D board and its integer hash representation.

## Complexity Analysis
*   **Time:** O(M * N * 2^(M*N)) - In the worst case, we might visit all possible permutations of the board. For a 2x3 board, there are 3! = 6 permutations. For a general M x N board, the number of states can be up to (M*N)!. However, the number of reachable states is much smaller, and for the 2x3 case, it's bounded. The `encode` and `decode` operations take O(M*N) time. The BFS explores states, and each state involves O(M*N) work for exploring neighbors and hashing.
*   **Space:** O(M * N * 2^(M*N)) - For storing visited states in the `HashSet` and states in the queue. Similar to time complexity, the actual space used is for reachable states.

## Commented Code
```java
class Solution {
    // Array to define the relative positions of neighbors for the blank tile (0).
    // neigh[0] = -1 (up), neigh[1] = 0 (no horizontal move), neigh[2] = 1 (down), neigh[3] = 0 (no vertical move), neigh[4] = -1 (left)
    // This pattern is used to get (dx, dy) pairs: (0, -1), (0, 1), (-1, 0), (1, 0)
    int[] neigh = {-1,0,1,0,-1};
    // Stores the integer hash of the solved state.
    int solvedHash;
    // Dimensions of the board.
    int m;
    int n;
    // HashSet to store the hashes of all visited board states to avoid cycles and redundant computations.
    HashSet<Integer> hashes = new HashSet<>();

    // Main function to solve the sliding puzzle.
    public int slidingPuzzle(int[][] board) {
      // Get the dimensions of the board.
      m = board.length;
      n = board[0].length;
      // Variables to store the initial coordinates of the blank tile (0).
      int x =0,y=0;
      // Create a 2D array to represent the solved state.
      int[][] solved = new int[m][n];
      // Variable to fill the solved board with numbers 1 to M*N-1.
      int num = 1;
      // Populate the 'solved' board and find the initial position of the blank tile.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++) {
              // Fill the solved board sequentially.
              solved[i][j] = num++;
              // If the current cell in the input board is 0, record its coordinates.
              if(board[i][j]==0){
                  x=i;
                  y=j;
              }
          }
      }
      // The blank tile (0) should be at the bottom-right corner in the solved state.
      solved[m-1][n-1] = 0;
      // Calculate and store the hash of the solved state.
      solvedHash = encode(solved);
      // Start the Breadth-First Search (BFS) from the initial state.
      // Pass the initial coordinates of the blank tile and the initial board.
      return bfs(x,y,board);
    }

    // Breadth-First Search function to find the shortest path to the solved state.
    public int bfs(int x, int y, int[][] board){
      // Initialize the answer to a very large value, indicating no solution found yet.
      int ans = Integer.MAX_VALUE;
      // Calculate the hash of the initial board state.
      int startHash = encode(board);
      // Create a queue for BFS. Each element will be an array: [zero_row, zero_col, moves, current_hash].
      Queue<int[]> q = new LinkedList<>();
      // Offer the initial state to the queue.
      q.offer(new int[]{x,y,0,startHash});
      // Add the initial hash to the set of visited states.
      hashes.add(startHash);

      // Start the BFS loop.
      while(!q.isEmpty()){
        // Dequeue the current state from the queue.
        int[] curr = q.poll();
        // Extract current zero tile coordinates, number of moves, and current hash.
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];

        // Check if the current state is the solved state.
        if(isSolved(hash)) {
            // If solved, return the number of moves taken.
            return moves;
        }

        // Decode the current hash back into a 2D board representation to perform swaps.
        int[][] newBoard = decode(hash);

        // Explore all four possible moves for the blank tile.
        for(int i=0;i<4;i++){
            // Calculate the coordinates of the potential tile to swap with the blank tile.
            // neigh[i] gives the row offset, neigh[i+1] gives the column offset.
            int X = cx+neigh[i];
            int Y = cy+neigh[i+1];

            // Check if the calculated neighbor coordinates are within the board boundaries.
            if(X<0 || Y<0 || X>=m || Y>=n) continue; // Skip if out of bounds.

            // Swap the blank tile with the neighbor tile.
            swap(newBoard,cx,cy,X,Y);
            // Calculate the hash of the new board state after the swap.
            int newHash = encode(newBoard);

            // If this new state has not been visited before:
            if(!hashes.contains(newHash)){
                // Add the new hash to the set of visited states.
                hashes.add(newHash);
                // Enqueue the new state with updated coordinates, incremented moves, and new hash.
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            // Swap back the tiles to restore the board to its state before this move.
            // This is crucial for exploring other possible moves from the same parent state.
            swap(newBoard,cx,cy,X,Y);
        }
      }
      // If the queue becomes empty and the solved state was not found, return -1.
      return -1;
    }

    // Helper function to swap two tiles in the board.
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      // Store the value of the first tile.
      int temp = board[i1][j1];
      // Move the value of the second tile to the first tile's position.
      board[i1][j1] = board[i2][j2];
      // Move the stored value of the first tile to the second tile's position.
      board[i2][j2] = temp;
    }

    // Helper function to check if the current hash matches the solved hash.
    public boolean isSolved(int hash){
      // Return true if the current hash is equal to the pre-calculated solved hash.
      return solvedHash == hash;
    }

    // Function to encode a 2D board into a single integer hash.
    // Assumes board values are single digits (0-9).
    public int encode(int[][] board){
      int hash = 0;
      // Iterate through each cell of the board.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++) {
              // Shift the current hash one decimal place to the left (multiply by 10).
              hash *= 10;
              // Add the value of the current cell to the hash.
              hash += board[i][j];
          }
      }
      // Return the final integer hash.
      return hash;
    }

    // Function to decode an integer hash back into a 2D board.
    // Assumes the hash was generated from a board of dimensions m x n.
    public int[][] decode(int hash){
        // Create a new 2D array for the decoded board.
        int[][] decodedBoard = new int[m][n];
        // Start filling from the bottom-right corner of the decoded board.
        int i = m-1;
        int j = n-1;
        // While there are still digits in the hash to process.
        while(hash>0){
            // Get the last digit of the hash and place it in the current cell.
            decodedBoard[i][j] = hash%10;
            // Remove the last digit from the hash.
            hash /= 10;
            // Move to the previous column.
            j--;
            // If we've reached the beginning of a row (j becomes -1):
            if(j==-1) {
                // Move to the previous row.
                i--;
                // Reset the column to the last column of the new row.
                j=n-1;
            }
        }
        // Return the decoded 2D board.
        return decodedBoard;
    }
}
```

## Interview Tips
*   **Explain BFS Clearly:** Articulate why BFS is suitable for finding the shortest path and how it explores states level by level.
*   **Discuss State Representation:** Emphasize the importance of an efficient way to represent and compare board states (hashing). Explain the trade-offs if asked.
*   **Handle Edge Cases:** Mention what happens if the puzzle is unsolvable (returning -1) and how the BFS naturally handles this.
*   **Walk Through an Example:** Be prepared to trace the BFS for a small example (e.g., a 2x2 board) to demonstrate your understanding.
*   **Complexity Justification:** Be ready to explain the time and space complexity, acknowledging the worst-case scenario and the practical implications for this specific problem size.

## Revision Checklist
- [ ] Understand the problem: Sliding puzzle, minimum moves.
- [ ] Identify BFS as the core algorithm.
- [ ] Implement state representation (hashing).
- [ ] Handle visited states using a `HashSet`.
- [ ] Correctly implement neighbor exploration and boundary checks.
- [ ] Ensure swap and backtrack logic is sound.
- [ ] Handle the case where no solution exists.
- [ ] Analyze time and space complexity.
- [ ] Practice coding the solution from scratch.

## Similar Problems
*   8 Puzzle (LeetCode 773) - This is essentially the same problem, often presented with a 3x3 grid.
*   Word Ladder (LeetCode 127) - Another BFS problem where states are words and transitions are single-letter changes.
*   Shortest Path in Binary Matrix (LeetCode 1091) - BFS on a grid to find the shortest path.
*   Shortest Path in a Grid with Obstacles Elimination (LeetCode 1293) - BFS with an additional state parameter (obstacles eliminated).

## Tags
`Array` `Hash Map` `Breadth-First Search` `State Space Search`
