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
        int[][] board = new int[m][n];
        for(int i=m-1;i>=0;i--) for(int j=n-1;j>=0;j--){board[i][j] = hash%10; hash/=10;}
        return board;
    }
}
```

---

---
## Quick Revision
This problem asks for the minimum number of moves to solve a sliding puzzle by moving the empty tile (0).
We solve this using Breadth-First Search (BFS) to explore all possible states and find the shortest path.

## Intuition
The sliding puzzle can be viewed as a state-space search problem. Each configuration of the puzzle is a state, and a valid move (swapping the empty tile with an adjacent tile) transitions between states. Since we want the minimum number of moves, BFS is the natural choice because it explores states layer by layer, guaranteeing that the first time we reach the solved state, it will be via the shortest path.

The key challenge is efficiently representing and comparing states. Using a 2D array directly in a `HashSet` is not feasible. Therefore, we need a way to uniquely identify each puzzle configuration. Encoding the 2D board into a single integer (or string) allows us to use a `HashSet` to keep track of visited states and avoid redundant computations.

## Algorithm
1.  **Initialization**:
    *   Determine the dimensions `m` and `n` of the board.
    *   Find the initial position (`x`, `y`) of the empty tile (0).
    *   Create the `solved` board configuration and calculate its integer `solvedHash`.
    *   Initialize a queue `q` for BFS, storing `[row, col, moves, current_hash]`.
    *   Initialize a `HashSet` `hashes` to store visited states (encoded as integers).
    *   Add the initial state `[x, y, 0, encode(board)]` to the queue and its hash to `hashes`.

2.  **BFS Loop**:
    *   While the queue is not empty:
        *   Dequeue a state `[cx, cy, moves, hash]`.
        *   If `hash` equals `solvedHash`, return `moves`.
        *   Decode the `hash` back into a 2D `newBoard`.
        *   Iterate through the four possible directions (up, down, left, right) for the empty tile.
        *   For each direction, calculate the new coordinates (`X`, `Y`) of the tile to swap with.
        *   **Boundary Check**: If `(X, Y)` is out of bounds, continue to the next direction.
        *   **Swap**: Swap the empty tile at `(cx, cy)` with the tile at `(X, Y)` in `newBoard`.
        *   **Encode**: Calculate the `newHash` of the modified `newBoard`.
        *   **Visited Check**: If `newHash` has not been visited (i.e., not in `hashes`):
            *   Add `newHash` to `hashes`.
            *   Enqueue the new state `[X, Y, moves + 1, newHash]`.
        *   **Backtrack Swap**: Swap the tiles back in `newBoard` to restore it for exploring other moves from the current state.

3.  **No Solution**: If the queue becomes empty and the solved state is not reached, return -1.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Optimal for finding the shortest path in an unweighted graph (state space).
*   **State Representation**: Efficiently encoding complex states (like a 2D board) into a hashable format (like an integer) for use in sets/maps.
*   **Graph Traversal**: Understanding how to explore nodes (states) and edges (moves) in a graph.
*   **Visited Set**: Crucial for preventing infinite loops and redundant computations in graph search algorithms.

## Common Mistakes
*   **Inefficient State Representation**: Trying to use 2D arrays directly in `HashSet` or `HashMap` without proper serialization.
*   **Not Handling Boundary Conditions**: Failing to check if the new tile position is within the board dimensions.
*   **Forgetting to Backtrack Swap**: Modifying the board in place and not reverting the swap after exploring a path, leading to incorrect subsequent states.
*   **Incorrect Encoding/Decoding**: Errors in converting the 2D board to an integer and back, leading to incorrect state comparisons.
*   **Not initializing the visited set correctly**: Missing the initial state or not adding it to the visited set.

## Complexity Analysis
*   **Time**: O(M * N * 2^(M*N)) - In the worst case, we might visit all possible permutations of the board. For a 2x3 board, there are 362,880 permutations. The `encode` and `decode` operations take O(M*N) time. The `neigh` array is constant size.
*   **Space**: O(M * N * 2^(M*N)) - The `HashSet` can store up to all possible states, and each state's encoded representation takes O(M*N) space (implicitly, as it's an integer derived from M*N digits). The queue can also store a significant number of states.

## Commented Code
```java
class Solution {
    // Array to represent the neighbors of a tile (up, right, down, left)
    // neigh[0] = dx for up, neigh[1] = dy for up
    // neigh[2] = dx for right, neigh[3] = dy for right
    // ... and so on. This is a common trick for 4-directional movement.
    int[] neigh = {-1,0,1,0,-1}; // Corresponds to: up, right, down, left relative moves in (dx, dy) pairs.
    
    // The integer representation of the solved board state.
    int solvedHash;
    
    // Dimensions of the board.
    int m;
    int n;
    
    // A set to keep track of all visited board configurations (encoded as integers).
    HashSet<Integer> hashes = new HashSet<>();
    
    // Main function to solve the sliding puzzle.
    public int slidingPuzzle(int[][] board) {
      // Get the dimensions of the board.
      m = board.length;
      n = board[0].length;
      
      // Variables to store the initial row and column of the empty tile (0).
      int x =0,y=0;
      
      // Create a 2D array to represent the solved state.
      int[][] solved = new int[m][n];
      // Variable to fill the solved board with numbers 1, 2, 3...
      int num = 1;
      
      // Populate the 'solved' board and find the initial position of '0'.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++) {
              // Assign the next number to the solved board.
              solved[i][j] = num++;
              // If the current cell in the input board is '0', record its position.
              if(board[i][j]==0){
                  x=i;
                  y=j;
              }
          }
      }
      // The solved state has '0' at the bottom-right corner.
      solved[m-1][n-1] = 0;
      
      // Calculate the integer hash for the solved board state.
      solvedHash = encode(solved);
      
      // Start the Breadth-First Search (BFS) from the initial state.
      // Pass the initial position of '0' and the initial board configuration.
      return bfs(x,y,board);
    }
    
    // Breadth-First Search function to find the shortest path.
    public int bfs(int x, int y, int[][] board){
      // Initialize the answer to a very large value, indicating no solution found yet.
      int ans = Integer.MAX_VALUE;
      
      // Encode the initial board configuration into an integer hash.
      int startHash = encode(board);
      
      // Queue for BFS. Each element is an array: [current_row, current_col, moves_made, current_hash].
      Queue<int[]> q = new LinkedList<>();
      
      // Offer the initial state to the queue: starting position of '0', 0 moves, and its hash.
      q.offer(new int[]{x,y,0,startHash});
      
      // Add the initial state's hash to the set of visited states.
      hashes.add(startHash);
      
      // Loop while there are states to explore in the queue.
      while(!q.isEmpty()){
        // Dequeue the current state.
        int[] curr = q.poll();
        // Extract current row, column, moves, and hash from the dequeued state.
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];
        
        // Check if the current state is the solved state.
        if(isSolved(hash)) return moves; // If solved, return the number of moves.
        
        // Decode the current hash back into a 2D board representation to perform swaps.
        int[][] newBoard = decode(hash);
        
        // Explore possible moves from the current empty tile position (cx, cy).
        // Iterate through the 4 possible directions (up, right, down, left).
        for(int i=0;i<4;i++){
            // Calculate the coordinates of the neighbor tile to swap with.
            // neigh[i] gives the row offset, neigh[i+1] gives the column offset.
            int X = cx+neigh[i];
            int Y = cy+neigh[i+1];
            
            // Check if the neighbor coordinates are within the board boundaries.
            if(X<0 || Y<0 || X>=m || Y>=n) continue; // If out of bounds, skip this direction.
            
            // Perform the swap: move the empty tile to (X, Y) by swapping with the tile at (X, Y).
            swap(newBoard,cx,cy,X,Y);
            
            // Encode the new board configuration into an integer hash.
            int newHash = encode(newBoard);
            
            // Check if this new state has been visited before.
            if(!hashes.contains(newHash)){
                // If not visited:
                // Add the new hash to the set of visited states.
                hashes.add(newHash);
                // Enqueue the new state: new position of '0', incremented moves, and its hash.
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            
            // Backtrack: Swap the tiles back to restore the board to its state before this move.
            // This is crucial for exploring other paths from the original (cx, cy) position.
            swap(newBoard,cx,cy,X,Y);
        }
      }
      // If the queue becomes empty and the solved state was not reached, it means no solution exists.
      return -1;
    }
    
    // Helper function to swap two tiles on the board.
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      // Store the value of the first tile.
      int temp = board[i1][j1];
      // Move the second tile to the first position.
      board[i1][j1] = board[i2][j2];
      // Move the stored value (originally from the first tile) to the second position.
      board[i2][j2] = temp;
    }
    
    // Helper function to check if the current hash matches the solved hash.
    public boolean isSolved(int hash){
      return solvedHash == hash;
    }
    
    // Encodes a 2D board configuration into a single integer.
    // Assumes board dimensions are small enough that the resulting integer doesn't overflow.
    public int encode(int[][] board){
      int hash = 0;
      // Iterate through each cell of the board.
      for(int i=0;i<m;i++) {
          for(int j=0;j<n;j++) {
              // Shift the current hash left by one decimal place (multiply by 10).
              hash *= 10;
              // Add the value of the current cell to the hash.
              hash += board[i][j];
          }
      }
      // Return the final integer hash representing the board state.
      return hash;
    }
    
    // Decodes an integer hash back into a 2D board configuration.
    // This is the inverse operation of 'encode'.
    public int[][] decode(int hash){
        // Create a new 2D board of the correct dimensions.
        int[][] board = new int[m][n];
        // Iterate through the board cells in reverse order (bottom-right to top-left).
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                // The last digit of the hash corresponds to the current cell.
                board[i][j] = hash%10;
                // Remove the last digit from the hash by integer division.
                hash /= 10;
            }
        }
        // Return the reconstructed 2D board.
        return board;
    }
}
```

## Interview Tips
*   **Explain BFS Clearly**: Articulate why BFS is suitable for finding the minimum number of moves.
*   **Discuss State Representation**: Emphasize the need for an efficient way to represent and store board states, and explain your encoding/decoding strategy.
*   **Handle Edge Cases**: Be prepared to discuss what happens if the puzzle is unsolvable or if the input board is invalid (though LeetCode constraints usually handle this).
*   **Trace an Example**: Walk through a small example (e.g., a 2x2 board) to demonstrate how your BFS and state transitions work.
*   **Complexity Justification**: Be ready to explain the time and space complexity, especially the exponential factor due to the state space size.

## Revision Checklist
- [ ] Understand the problem: Sliding puzzle, minimum moves.
- [ ] Identify BFS as the core algorithm.
- [ ] Devise a state encoding/decoding mechanism (integer hash).
- [ ] Implement BFS with a queue and visited set.
- [ ] Handle board boundary checks.
- [ ] Correctly implement swap and backtrack swap.
- [ ] Calculate and compare hashes for solved state.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its trade-offs.

## Similar Problems
*   81. Search in Rotated Sorted Array II (similar state-space search, but different problem)
*   752. Open the Lock (BFS on states, string representation)
*   1730. Shortest Path to Get Food (BFS on grid)
*   1091. Shortest Path in Binary Matrix (BFS on grid)

## Tags
`Array` `Breadth-First Search` `Hash Table` `Matrix` `State Machine`
