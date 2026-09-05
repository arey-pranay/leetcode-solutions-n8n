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
We can solve this using Breadth-First Search (BFS) on the state space of the puzzle.

## Intuition
The sliding puzzle can be viewed as a graph where each valid configuration of the board is a node, and an edge exists between two nodes if one configuration can be reached from the other by a single valid move (swapping the 0 with an adjacent tile). Since we want the minimum number of moves, BFS is the natural choice because it explores the graph layer by layer, guaranteeing that the first time we reach the solved state, it will be via the shortest path.

The key challenge is efficiently representing and comparing board states. A 2D array is cumbersome to use as a key in a visited set or a queue. Converting the 2D board into a single integer (hashing) allows for quick lookups and storage.

## Algorithm
1.  **Initialization**:
    *   Determine the dimensions `m` and `n` of the board.
    *   Find the initial position `(x, y)` of the empty tile (0).
    *   Create the `solved` board configuration and calculate its integer hash (`solvedHash`).
    *   Initialize a queue `q` for BFS. Each element in the queue will store `[current_x, current_y, moves, current_hash]`.
    *   Initialize a `HashSet` called `hashes` to keep track of visited board states (represented by their integer hashes).
    *   Add the initial state `[x, y, 0, initial_hash]` to the queue and `initial_hash` to the `hashes` set.

2.  **BFS Loop**:
    *   While the queue is not empty:
        *   Dequeue the current state `[cx, cy, moves, hash]`.
        *   If `hash` is equal to `solvedHash`, return `moves`.
        *   Decode the `hash` back into a 2D `newBoard` for manipulation.
        *   Iterate through the four possible directions (up, down, left, right) using the `neigh` array.
        *   For each direction, calculate the coordinates `(X, Y)` of the adjacent tile.
        *   **Boundary Check**: If `(X, Y)` is out of bounds, continue to the next direction.
        *   **Swap**: Swap the empty tile at `(cx, cy)` with the tile at `(X, Y)` in `newBoard`.
        *   **Encode**: Calculate the `newHash` of the modified `newBoard`.
        *   **Visited Check**: If `newHash` has not been visited (i.e., not in `hashes`):
            *   Add `newHash` to the `hashes` set.
            *   Enqueue the new state `[X, Y, moves + 1, newHash]`.
        *   **Backtrack Swap**: Swap the tiles back in `newBoard` to restore it to its state before the current move, so that other moves from `(cx, cy)` can be explored correctly.

3.  **No Solution**: If the queue becomes empty and the solved state is not found, return -1.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Optimal for finding the shortest path in an unweighted graph.
*   **State Space Search**: Representing all possible configurations of a problem as nodes in a graph.
*   **Hashing/Encoding**: Converting complex data structures (like 2D arrays) into a simpler, comparable form (like integers) for efficient storage and lookup in hash sets/maps.
*   **Backtracking**: Reverting changes after exploring a path to allow exploration of alternative paths.

## Common Mistakes
*   **Inefficient State Representation**: Using the 2D array directly in the queue or visited set, leading to slow comparisons and high memory usage.
*   **Forgetting to Backtrack Swap**: Failing to swap the tiles back after exploring a move, which corrupts the board state for subsequent moves from the same position.
*   **Incorrect Boundary Checks**: Not properly checking if the adjacent tile is within the board dimensions.
*   **Integer Overflow/Encoding Issues**: If the board size is large, the integer encoding might overflow or not uniquely represent all states. (Though for typical puzzle sizes, this is usually fine).
*   **Not Handling the Solved State Correctly**: Missing the check for the solved state or returning the wrong number of moves.

## Complexity Analysis
*   **Time**: O(M * N * 2^(M*N)) in the worst case.
    *   The state space can be up to (M*N)! for a general permutation puzzle. For the sliding puzzle, it's constrained by the 0's movement. The number of reachable states is bounded.
    *   Each state is visited at most once. For each state, we perform constant number of operations (swapping, encoding, decoding, checking neighbors).
    *   The encoding/decoding takes O(M*N) time.
    *   The number of possible states for a 2x3 board is 362,880. For a 3x3 board (8-puzzle), it's 9!/2 = 181,440. The complexity is roughly proportional to the number of reachable states.
*   **Space**: O(M * N * 2^(M*N)) in the worst case.
    *   The `HashSet` `hashes` stores all visited states.
    *   The `Queue` `q` can also store a significant number of states.
    *   The space complexity is dominated by the storage of visited states.

## Commented Code
```java
class Solution {
    // neigh array stores the relative movements for the 4 directions:
    // neigh[0] = -1 (up), neigh[1] = 0 (no horizontal move)
    // neigh[1] = 0 (no vertical move), neigh[2] = 1 (right)
    // neigh[2] = 1 (right), neigh[3] = 0 (no vertical move)
    // neigh[3] = 0 (no horizontal move), neigh[4] = -1 (down)
    // This pattern is used to get (dx, dy) pairs: (-1,0), (0,1), (1,0), (0,-1)
    int[] neigh = {-1,0,1,0,-1};
    // Stores the integer hash of the solved puzzle state.
    int solvedHash;
    // Dimensions of the board.
    int m;
    int n;
    // HashSet to store the integer hashes of all visited board configurations.
    HashSet<Integer> hashes = new HashSet<>();

    // Main function to solve the sliding puzzle.
    public int slidingPuzzle(int[][] board) {
      // Get the dimensions of the board.
      m = board.length;
      n = board[0].length;
      // Variables to store the initial row and column of the empty tile (0).
      int x =0,y=0;
      // Create a 2D array to represent the solved state of the puzzle.
      int[][] solved = new int[m][n];
      // Variable to fill the solved board with numbers 1, 2, 3...
      int num = 1;
      // Iterate through the input board to:
      // 1. Populate the 'solved' board with sequential numbers.
      // 2. Find the initial position (x, y) of the empty tile (0).
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++){
              solved[i][j] = num++; // Assign sequential number to solved board.
              if(board[i][j]==0){ // If the current tile is the empty tile.
                  x=i; // Store its row.
                  y=j; // Store its column.
              }
          }
      }
      // The last tile in the solved board should be 0 (the empty tile).
      solved[m-1][n-1] = 0;
      // Calculate the integer hash of the solved board configuration.
      solvedHash = encode(solved);
      // Start the Breadth-First Search (BFS) from the initial state.
      // Pass the initial position of the empty tile and the initial board.
      return bfs(x,y,board);
    }

    // Breadth-First Search function to find the shortest path to the solved state.
    public int bfs(int x, int y, int[][] board){
      // Initialize the answer to a very large value, indicating no solution found yet.
      int ans = Integer.MAX_VALUE;
      // Calculate the integer hash of the initial board configuration.
      int startHash = encode(board);
      // Create a queue for BFS. Each element will store:
      // [current_x_of_zero, current_y_of_zero, number_of_moves, current_board_hash]
      Queue<int[]> q = new LinkedList<>();
      // Offer the initial state to the queue.
      q.offer(new int[]{x,y,0,startHash});
      // Add the hash of the initial state to the set of visited states.
      hashes.add(startHash);

      // Start the BFS loop.
      while(!q.isEmpty()){
        // Dequeue the current state from the queue.
        int[] curr = q.poll();
        // Extract current position of zero, moves made, and the hash of the current board.
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];

        // Check if the current board configuration is the solved state.
        if(isSolved(hash)) return moves; // If solved, return the number of moves.

        // Decode the current hash back into a 2D board representation for manipulation.
        int[][] newBoard = decode(hash);

        // Explore all 4 possible moves for the empty tile (0).
        for(int i=0;i<4;i++){
            // Calculate the coordinates of the adjacent tile to swap with.
            int X = cx+neigh[i]; // New row
            int Y = cy+neigh[i+1]; // New column

            // Check if the adjacent tile's coordinates are within the board boundaries.
            if(X<0 || Y<0 || X>=m || Y>=n) continue; // If out of bounds, skip this move.

            // Perform the swap: move the empty tile to (X, Y).
            swap(newBoard,cx,cy,X,Y);
            // Calculate the integer hash of the new board configuration after the swap.
            int newHash = encode(newBoard);

            // Check if this new board configuration has been visited before.
            if(!hashes.contains(newHash)){
                // If not visited:
                hashes.add(newHash); // Mark it as visited.
                // Enqueue the new state: new position of zero, incremented moves, and new hash.
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            // Backtrack: Swap the tiles back to restore the board to its state before this move.
            // This is crucial for exploring other possible moves from the original (cx, cy) position.
            swap(newBoard,cx,cy,X,Y);
        }
      }
      // If the queue becomes empty and the solved state was not reached, return -1 (no solution).
      return -1;
    }

    // Helper function to swap two tiles in the board.
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      // Store the value of the first tile.
      int temp = board[i1][j1];
      // Move the second tile to the first position.
      board[i1][j1] = board[i2][j2];
      // Move the stored value (originally from the first tile) to the second position.
      board[i2][j2] = temp;
    }

    // Helper function to check if the current board hash matches the solved board hash.
    public boolean isSolved(int hash){
      return solvedHash == hash; // Return true if they match, false otherwise.
    }

    // Function to encode a 2D board configuration into a single integer hash.
    // Assumes board dimensions are small enough that integer overflow is not an issue.
    public int encode(int[][] board){
      int hash = 0; // Initialize hash to 0.
      // Iterate through each cell of the board.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++){
              hash *= 10; // Shift existing hash to the left by one decimal place.
              hash += board[i][j]; // Add the current tile's value.
          }
      }
      return hash; // Return the final integer hash.
    }

    // Function to decode an integer hash back into a 2D board configuration.
    // This assumes the encoding process was done by multiplying by 10 and adding digits.
    public int[][] decode(int hash){
        // Create a new 2D array to store the decoded board.
        int[][] decodedBoard = new int[m][n];
        // Start filling from the bottom-right corner of the decoded board.
        int i = m-1;
        int j = n-1;
        // While there are still digits in the hash to process.
        while(hash>0){
            // Get the last digit of the hash (which corresponds to the current cell's value).
            decodedBoard[i][j] = hash%10;
            // Remove the last digit from the hash.
            hash /= 10;
            // Move to the previous column.
            j--;
            // If we've reached the beginning of a row (j becomes -1).
            if(j==-1) {
                // Move to the previous row.
                i--;
                // Reset the column index to the last column of the new row.
                j=n-1;
            }
        }
        // Return the decoded 2D board.
        return decodedBoard;
    }
}
```

## Interview Tips
1.  **Explain BFS Clearly**: Articulate why BFS is suitable for finding the minimum number of moves. Emphasize its layer-by-layer exploration.
2.  **Discuss State Representation**: Highlight the importance of converting the 2D board into an integer hash for efficient `visited` set lookups. Explain the trade-offs and potential issues (like overflow for very large boards).
3.  **Trace a Small Example**: Walk through a 2x2 or a simple 2x3 board manually to demonstrate how the BFS queue and visited set would evolve.
4.  **Mention Backtracking**: Explain why the `swap` operation needs to be undone after exploring a move. This shows attention to detail in state management.
5.  **Complexity Justification**: Be prepared to explain the time and space complexity, acknowledging that it's exponential in the worst case but practical for typical puzzle sizes.

## Revision Checklist
- [ ] Understand the problem: Sliding puzzle, minimum moves.
- [ ] Identify BFS as the core algorithm.
- [ ] Implement state representation (hashing).
- [ ] Handle board boundaries correctly.
- [ ] Implement the swap and backtrack logic.
- [ ] Manage the visited states using a HashSet.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its components.

## Similar Problems
*   8 Puzzle (LeetCode 773 is a generalization)
*   Word Ladder (LeetCode 127)
*   Shortest Path in Binary Matrix (LeetCode 1091)
*   Escape the Ghosts (LeetCode 2070)

## Tags
`Array` `Hash Map` `Breadth-First Search` `State Space Search`
