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
    int solvedHash,m,n;
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
        if(solvedHash == hash) return moves;
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
    public int encode(int[][] board){
      int hash = 0;
      for(int i=0;i<m;i++) for(int j=0;j<n;j++){hash*=10; hash+= board[i][j];}
      return hash;
    }
    public int[][] decode(int hash){
        int[][] board = new int[m][n];
        for(int i=m-1;i>=0;i--) for(int j=n-1;j>=0;j--){board[i][j] = hash%10; hash/=10;}
        return board;
    }
}
```

---

---
## Quick Revision
This problem asks for the minimum number of moves to solve a sliding puzzle.
We solve it using Breadth-First Search (BFS) on the state space of the puzzle.

## Intuition
The sliding puzzle can be viewed as a graph where each configuration of the board is a node, and an edge exists between two nodes if one can be reached from the other by a single valid move (swapping the 0 with an adjacent tile). Since we want the *minimum* number of moves, BFS is the natural choice because it explores the graph layer by layer, guaranteeing that the first time we reach the target state, it's via the shortest path.

The challenge is representing the board states efficiently. A 2D array is not directly hashable for use in a `HashSet` or as a key in a `HashMap`. Therefore, we need a way to convert the 2D board into a unique, comparable representation, like an integer hash.

## Algorithm
1.  **State Representation:** Define a way to uniquely represent each board configuration. An integer hash is suitable, where the digits of the integer correspond to the numbers on the board in a fixed order (e.g., row by row).
2.  **Target State:** Determine the target configuration (e.g., `[[1,2,3],[4,5,0]]` for a 2x3 board). Calculate its hash.
3.  **BFS Initialization:**
    *   Find the initial position of the '0' tile (the empty space).
    *   Create a queue for BFS. Each element in the queue will store the current board state (as a hash), the position of the '0' tile (x, y coordinates), and the number of moves made to reach this state.
    *   Create a `HashSet` to keep track of visited board states (hashes) to avoid cycles and redundant computations.
    *   Add the initial state (board hash, 0's position, 0 moves) to the queue and mark it as visited.
4.  **BFS Loop:**
    *   While the queue is not empty:
        *   Dequeue a state (current board hash, 0's x, 0's y, current moves).
        *   If the current board hash matches the target hash, return the current moves.
        *   **Generate Neighbors:** For the current '0' position (x, y), consider all four possible adjacent moves (up, down, left, right).
        *   For each valid adjacent position (X, Y) within board boundaries:
            *   Create a *new* board configuration by swapping the '0' tile with the tile at (X, Y).
            *   Calculate the hash of this new board configuration.
            *   If this new hash has not been visited:
                *   Mark the new hash as visited.
                *   Enqueue the new state (new board hash, new 0's x, new 0's y, current moves + 1).
5.  **No Solution:** If the queue becomes empty and the target state hasn't been reached, return -1.

## Concept to Remember
*   **Breadth-First Search (BFS):** Optimal for finding the shortest path in an unweighted graph.
*   **State Space Search:** Representing all possible configurations of a problem as nodes in a graph.
*   **Hashing/Serialization:** Converting complex data structures (like 2D arrays) into a comparable and hashable format for efficient lookups.
*   **Immutability/Copying:** When generating new states, ensure you are working with copies of the board to avoid modifying states that are still in the queue or have been processed.

## Common Mistakes
*   **Modifying Board In-Place:** Swapping tiles directly on the board representation used by other states in the queue or visited set, leading to incorrect states. Always work with copies or revert changes after exploring a path.
*   **Inefficient State Representation:** Using a 2D array directly in a `HashSet` or `HashMap` without a proper hashing mechanism.
*   **Forgetting to Mark Visited States:** Leading to infinite loops or redundant exploration of the same board configurations.
*   **Incorrect Neighbor Generation:** Missing valid moves or attempting moves outside the board boundaries.
*   **Off-by-One Errors:** In move counts or boundary checks.

## Complexity Analysis
*   **Time:** O(M * N * 2^(M*N)) - In the worst case, we might visit all possible permutations of the board. For a 2x3 board, there are 3! * 2 = 12 possible states. For a general M x N board, the number of states can be up to (M*N)! / 2. However, the practical number of reachable states is much smaller. The encoding/decoding and swapping operations take O(M*N) time.
*   **Space:** O(M * N * 2^(M*N)) - To store the visited states (hashes) and the queue. Similar to time complexity, this depends on the number of reachable states.

## Commented Code
```java
class Solution {
    // neigh stores the relative movements for the 0 tile: up, down, left, right.
    // neigh[0] = -1 (up), neigh[1] = 0 (no horizontal move), neigh[2] = 1 (down), neigh[3] = 0 (no horizontal move), neigh[4] = -1 (left)
    // This is a clever way to represent (dx, dy) pairs: (neigh[i], neigh[i+1]) for i=0,2.
    int[] neigh = {-1,0,1,0,-1};
    // solvedHash will store the integer representation of the solved board state.
    int solvedHash;
    // m and n store the dimensions of the board.
    int m,n;
    // hashes is a set to store all visited board states (represented as integers) to avoid cycles.
    HashSet<Integer> hashes = new HashSet<>();

    // The main function to solve the sliding puzzle.
    public int slidingPuzzle(int[][] board) {
      // Get the dimensions of the board.
      m = board.length;
      n = board[0].length;
      // x and y will store the initial coordinates of the '0' tile.
      int x =0,y=0;
      // solved is a 2D array to represent the target solved state.
      int[][] solved = new int[m][n];
      // num is used to populate the solved board with numbers 1, 2, 3...
      int num = 1;
      // Iterate through the input board to populate the solved board and find the initial position of '0'.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++){
              // Assign numbers sequentially to the solved board.
              solved[i][j] = num++;
              // If the current cell in the input board is '0', record its coordinates.
              if(board[i][j]==0){
                  x=i;
                  y=j;
              }
          }
      }
      // The '0' tile in the solved state is at the bottom-right corner.
      solved[m-1][n-1] = 0;
      // Calculate the integer hash for the solved board state.
      solvedHash = encode(solved);
      // Start the Breadth-First Search (BFS) from the initial state.
      // Pass the initial coordinates of '0' and the initial board configuration.
      return bfs(x,y,board);
    }

    // BFS function to find the shortest path to the solved state.
    public int bfs(int x, int y, int[][] board){
      // Initialize ans to a large value, though it's not strictly used here as we return immediately upon finding the solution.
      int ans = Integer.MAX_VALUE;
      // Calculate the integer hash for the initial board state.
      int startHash = encode(board);
      // Create a queue for BFS. Each element will store: [0's x, 0's y, moves, current board hash].
      Queue<int[]> q = new LinkedList<>();
      // Offer the initial state to the queue.
      q.offer(new int[]{x,y,0,startHash});
      // Add the initial board hash to the set of visited states.
      hashes.add(startHash);
      // The iteration count is not strictly needed for the logic but can be useful for debugging.
      int iteration=1;

      // Start the BFS loop.
      while(!q.isEmpty()){
        // Dequeue the current state.
        int[] curr = q.poll();
        // Extract current 0's coordinates, moves count, and board hash.
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];

        // If the current board hash matches the solved board hash, we found the shortest path.
        if(solvedHash == hash) return moves;

        // Decode the current board hash back into a 2D array to manipulate it.
        int[][] newBoard = decode(hash);

        // Explore all 4 possible moves for the '0' tile.
        for(int i=0;i<4;i++){
            // Calculate the coordinates of the potential new position for '0'.
            // neigh[i] gives dx, neigh[i+1] gives dy for the i-th direction.
            int X = cx+neigh[i];
            int Y = cy+neigh[i+1];

            // Check if the new position is within the board boundaries.
            if(X<0 || Y<0 || X>=m || Y>=n) continue; // If out of bounds, skip this move.

            // Swap the '0' tile with the tile at the new position (X, Y).
            // This creates a new board configuration.
            swap(newBoard,cx,cy,X,Y);

            // Encode the new board configuration into an integer hash.
            int newHash = encode(newBoard);

            // If this new board state has not been visited yet:
            if(!hashes.contains(newHash)){
                // Add the new hash to the set of visited states.
                hashes.add(newHash);
                // Enqueue the new state: new 0's position (X, Y), incremented moves count, and the new board hash.
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            // IMPORTANT: Swap back to revert the board to its state before this move.
            // This is crucial because we are modifying the 'newBoard' array in place,
            // and we need it to be in the correct state for exploring other moves from the current (cx, cy).
            swap(newBoard,cx,cy,X,Y);
        }
      }
      // If the queue becomes empty and the solved state was not reached, it means the puzzle is unsolvable.
      return -1;
    }

    // Helper function to swap two elements in a 2D array.
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      // Standard swap logic.
      int temp = board[i1][j1];
      board[i1][j1] = board[i2][j2];
      board[i2][j2] = temp;
    }

    // Encodes a 2D board configuration into a single integer hash.
    // Assumes board dimensions are small enough that the resulting integer doesn't overflow.
    public int encode(int[][] board){
      int hash = 0;
      // Iterate through the board row by row, column by column.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++){
              // Multiply hash by 10 to shift existing digits to the left, then add the current digit.
              hash = hash * 10 + board[i][j];
          }
      }
      // Return the final integer hash.
      return hash;
    }

    // Decodes an integer hash back into a 2D board configuration.
    // Assumes the hash was generated by the encode function.
    public int[][] decode(int hash){
        // Create a new 2D array for the board.
        int[][] board = new int[m][n];
        // Iterate from the bottom-right corner backwards.
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--){
                // The last digit of the hash is the current cell's value.
                board[i][j] = hash%10;
                // Remove the last digit from the hash.
                hash/=10;
            }
        }
        // Return the decoded board.
        return board;
    }
}
```

## Interview Tips
1.  **Explain the Graph Analogy:** Clearly articulate that the problem can be modeled as a graph search problem and why BFS is the appropriate algorithm for finding the shortest path.
2.  **Discuss State Representation:** Emphasize the need for a hashable representation of the board state. Walk through your `encode` and `decode` logic and explain its limitations (e.g., potential overflow for larger boards).
3.  **Handle Swapping Carefully:** Explain the importance of either creating a deep copy of the board for each new state or, as done in the provided solution, swapping back after exploring a move to maintain the integrity of the current state for other branches.
4.  **Edge Cases and Constraints:** Discuss what happens if the puzzle is unsolvable (return -1) and any constraints on board size that might affect the integer encoding or complexity.

## Revision Checklist
- [ ] Understand the problem: minimum moves for a sliding puzzle.
- [ ] Recognize BFS as the optimal search algorithm.
- [ ] Implement a robust state representation (integer encoding).
- [ ] Correctly identify the target state.
- [ ] Handle '0' tile movement and boundary checks.
- [ ] Use a `HashSet` to track visited states.
- [ ] Ensure correct swapping logic (copy or revert).
- [ ] Analyze time and space complexity.
- [ ] Consider unsolvable cases.

## Similar Problems
*   8-Puzzle (a specific instance of this problem with a 3x3 board)
*   Knight's Tour
*   Word Ladder
*   Shortest Path in Binary Matrix

## Tags
`Array` `Hash Map` `Breadth-First Search` `State Space Search`
