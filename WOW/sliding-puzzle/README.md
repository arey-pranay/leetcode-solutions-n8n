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
This problem asks for the minimum number of moves to solve a sliding tile puzzle.
We can solve this using Breadth-First Search (BFS) on the state space of the puzzle.

## Intuition
The sliding puzzle can be viewed as a graph where each state (configuration of the board) is a node, and an edge exists between two states if one can be reached from the other by a single valid move (swapping the 0 with an adjacent tile). Since we are looking for the minimum number of moves, BFS is the natural choice because it explores the graph layer by layer, guaranteeing that the first time we reach the target state, it will be via the shortest path.

The challenge lies in representing the board states efficiently and checking for visited states to avoid cycles and redundant computations. Encoding the 2D board into a single integer (or string) allows us to use a hash set for quick lookups of visited states.

## Algorithm
1.  **Initialization**:
    *   Determine the dimensions `m` and `n` of the board.
    *   Create a `solved` board configuration and calculate its integer representation (`solvedHash`). This is typically `[[1, 2, 3], [4, 5, 0]]` for a 2x3 board.
    *   Find the initial position (`x`, `y`) of the `0` tile in the input `board`.
    *   Initialize a queue for BFS. Each element in the queue will store the current `x` and `y` coordinates of the `0` tile, the number of `moves` made so far, and the integer `hash` representing the current board state.
    *   Initialize a `HashSet` called `hashes` to keep track of visited board states (represented by their integer hashes).
    *   Add the initial state (starting `x`, `y`, 0 moves, and the hash of the initial board) to the queue and the `hashes` set.

2.  **BFS Loop**:
    *   While the queue is not empty:
        *   Dequeue the current state: `cx`, `cy`, `moves`, `hash`.
        *   If the current `hash` matches `solvedHash`, return `moves` as this is the shortest path.
        *   Decode the current `hash` back into a 2D `newBoard` to manipulate it.
        *   Iterate through the four possible directions (up, down, left, right) for the `0` tile.
        *   For each direction, calculate the potential new coordinates (`X`, `Y`) of the `0` tile.
        *   **Boundary Check**: If `X` or `Y` are out of bounds (`< 0` or `>= m`/`>= n`), continue to the next direction.
        *   **Swap**: Swap the `0` tile at (`cx`, `cy`) with the tile at (`X`, `Y`) in `newBoard`.
        *   **Encode**: Calculate the `newHash` of the modified `newBoard`.
        *   **Visited Check**: If `newHash` is not in the `hashes` set:
            *   Add `newHash` to the `hashes` set.
            *   Enqueue the new state: `X`, `Y`, `moves + 1`, `newHash`.
        *   **Backtrack (Swap Back)**: Swap the tiles back in `newBoard` to restore it to its state before the swap. This is crucial for exploring other moves from the same parent state.

3.  **No Solution**: If the queue becomes empty and the `solvedHash` was never reached, return `-1` (indicating no solution).

## Concept to Remember
*   **Breadth-First Search (BFS)**: Optimal for finding the shortest path in an unweighted graph.
*   **State Space Search**: Representing all possible configurations of a problem as nodes in a graph.
*   **Hashing/Encoding**: Converting complex data structures (like a 2D array) into a single value for efficient storage and lookup in hash-based collections.
*   **Backtracking**: Reverting changes made during exploration to explore alternative paths.

## Common Mistakes
*   **Not handling visited states**: This can lead to infinite loops or exponential time complexity if the same board configuration is re-explored.
*   **Incorrectly encoding/decoding board states**: Small errors here will prevent correct state comparison and traversal.
*   **Forgetting to swap back after exploring a move**: This corrupts the board state for subsequent moves from the same parent node.
*   **Off-by-one errors in boundary checks or neighbor calculations**.
*   **Not handling the case where the puzzle is already solved**.

## Complexity Analysis
*   **Time**: O(M * N * 2^(M*N)) - In the worst case, we might visit all possible permutations of the board. For a 2x3 board, there are 3! * 2 = 12 possible states. For a general MxN board, the number of states can be very large, but for typical puzzle sizes (like 2x3), it's manageable. The `encode` and `decode` operations take O(M*N) time.
*   **Space**: O(M * N * 2^(M*N)) - The space is dominated by the `HashSet` storing visited states and the queue storing states to visit. Each state stored requires O(M*N) space for the board representation (implicitly via hash) and O(1) for coordinates and moves.

## Commented Code
```java
class Solution {
    // Array to define neighbors for a tile at (x, y).
    // neigh[0] = -1 (up), neigh[1] = 0 (no horizontal move), neigh[2] = 1 (down), neigh[3] = 0 (no vertical move), neigh[4] = -1 (left)
    // This is a clever way to represent (dx, dy) pairs: (-1,0), (0,1), (1,0), (0,-1)
    int[] neigh = {-1,0,1,0,-1};
    // Integer representation of the solved board state.
    int solvedHash;
    // Dimensions of the board.
    int m,n;
    // HashSet to store the integer hashes of visited board states to avoid cycles.
    HashSet<Integer> hashes = new HashSet<>();

    // Main function to solve the sliding puzzle.
    public int slidingPuzzle(int[][] board) {
      // Get the dimensions of the input board.
      m = board.length;
      n = board[0].length;
      // Variables to store the initial row and column of the '0' tile.
      int x =0,y=0;
      // Create a 2D array to represent the solved state of the puzzle.
      int[][] solved = new int[m][n];
      // Variable to fill the solved board with numbers 1, 2, 3...
      int num = 1;
      // Iterate through the input board to:
      // 1. Populate the 'solved' board with sequential numbers.
      // 2. Find the initial position (x, y) of the '0' tile.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++) {
              solved[i][j] = num++; // Assign sequential number
              if(board[i][j]==0){ // If the current cell contains '0'
                  x=i; // Store its row
                  y=j; // Store its column
              }
          }
      }
      // The '0' tile should be at the bottom-right corner in the solved state.
      solved[m-1][n-1] = 0;
      // Calculate the integer hash for the solved board state.
      solvedHash = encode(solved);
      // Start the Breadth-First Search (BFS) from the initial position of '0' and the initial board state.
      return bfs(x,y,board);
    }

    // Breadth-First Search function to find the shortest path.
    public int bfs(int x, int y, int[][] board){
      // Initialize the minimum moves to a very large value.
      int ans = Integer.MAX_VALUE;
      // Calculate the integer hash for the initial board state.
      int startHash = encode(board);
      // Queue for BFS. Each element is an array: {current_x, current_y, moves_so_far, current_board_hash}.
      Queue<int[]> q = new LinkedList<>();
      // Offer the starting state to the queue.
      q.offer(new int[]{x,y,0,startHash});
      // Add the hash of the starting state to the set of visited states.
      hashes.add(startHash);

      // Continue BFS as long as there are states to explore in the queue.
      while(!q.isEmpty()){
        // Dequeue the current state.
        int[] curr = q.poll();
        // Extract current coordinates of '0', moves made, and board hash.
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];

        // If the current board state matches the solved state, return the number of moves.
        if(solvedHash == hash) return moves;

        // Decode the current hash back into a 2D board representation to manipulate it.
        int[][] newBoard = decode(hash);

        // Explore all 4 possible moves for the '0' tile (up, down, left, right).
        for(int i=0;i<4;i++){
            // Calculate the potential new coordinates of the '0' tile.
            int X = cx+neigh[i];
            int Y = cy+neigh[i+1];

            // Check if the new coordinates are within the board boundaries.
            if(X<0 || Y<0 || X>=m || Y>=n) continue; // If out of bounds, skip this move.

            // Swap the '0' tile with the adjacent tile at (X, Y).
            swap(newBoard,cx,cy,X,Y);
            // Calculate the integer hash of the new board state after the swap.
            int newHash = encode(newBoard);

            // If this new board state has not been visited before:
            if(!hashes.contains(newHash)){
                // Add the new hash to the set of visited states.
                hashes.add(newHash);
                // Enqueue the new state: new coordinates of '0', incremented moves, and new hash.
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            // Backtrack: Swap the tiles back to restore the board to its state before this move.
            // This is crucial for exploring other possible moves from the same parent state.
            swap(newBoard,cx,cy,X,Y);
        }
      }
      // If the queue becomes empty and the solved state was not reached, return -1 (no solution).
      return -1;
    }

    // Helper function to swap two tiles in a 2D board.
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      // Store the value of the first tile temporarily.
      int temp = board[i1][j1];
      // Move the value of the second tile to the first tile's position.
      board[i1][j1] = board[i2][j2];
      // Move the temporary value (original value of the first tile) to the second tile's position.
      board[i2][j2] = temp;
    }

    // Helper function to encode a 2D board into a single integer.
    // This assumes the board contains single digits (0-9).
    public int encode(int[][] board){
      int hash = 0;
      // Iterate through the board row by row, column by column.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++) {
              // For each digit, multiply the current hash by 10 and add the digit.
              // This effectively creates a base-10 number representation of the flattened board.
              hash*=10;
              hash+= board[i][j];
          }
      }
      // Return the resulting integer hash.
      return hash;
    }

    // Helper function to decode an integer hash back into a 2D board.
    public int[][] decode(int hash){
        // Create a new 2D board of the same dimensions.
        int[][] board = new int[m][n];
        // Iterate through the board in reverse (bottom-right to top-left) to extract digits.
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                // The last digit of the hash is the current cell's value.
                board[i][j] = hash%10;
                // Remove the last digit from the hash for the next iteration.
                hash/=10;
            }
        }
        // Return the decoded 2D board.
        return board;
    }
}
```

## Interview Tips
1.  **Explain BFS Clearly**: Articulate why BFS is suitable for finding the minimum number of moves and how it explores the state space.
2.  **Discuss State Representation**: Emphasize the importance of an efficient way to represent board states (e.g., integer encoding) and how it enables using a `HashSet` for visited states.
3.  **Walk Through a Small Example**: If time permits, or if asked, trace the BFS for a very simple 2x2 puzzle to demonstrate the algorithm's steps, including swaps and visited checks.
4.  **Handle Edge Cases**: Mention what happens if the puzzle is already solved or if it's unsolvable (though this problem guarantees solvability for valid inputs).
5.  **Complexity Justification**: Be prepared to explain the time and space complexity, especially the exponential factor related to the number of possible board states.

## Revision Checklist
- [ ] Understand the problem: minimum moves for a sliding puzzle.
- [ ] Identify BFS as the core algorithm for shortest path.
- [ ] Implement state representation (e.g., integer encoding).
- [ ] Use a `HashSet` to track visited states.
- [ ] Correctly implement the BFS loop with queue operations.
- [ ] Handle neighbor calculations and boundary checks.
- [ ] Implement `swap` and `encode`/`decode` functions accurately.
- [ ] Remember to backtrack (swap back) after exploring a move.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases and potential mistakes.

## Similar Problems
*   8. String to Integer (atoi) - Similar encoding/decoding logic.
*   75. Sort Colors - Can be solved with BFS on permutations if viewed as a state problem.
*   130. Surrounded Regions - Uses BFS/DFS for connected components.
*   103. Binary Tree Zigzag Level Order Traversal - Another example of level-order traversal (BFS).
*   773. Sliding Puzzle (This is the same problem, but good to note if you see variations).

## Tags
`Array` `Hash Map` `Breadth-First Search` `State Space Search`
