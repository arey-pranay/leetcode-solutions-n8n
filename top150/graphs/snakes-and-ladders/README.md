# Snakes And Ladders

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Matrix`  
**Time:** O(N^2)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    int diceroll =0;
    public int snakesAndLadders(int[][] board) {
        return func(board);
    }
    public int func(int[][] board ){
        Queue<int[]> q= new LinkedList<>();
        q.add(new int[]{1,0});
        int n = board.length;
        boolean[] vis = new boolean[n*n + 1];
        while(!q.isEmpty()){
            int[] currPair = q.poll();
            int curr = currPair[0];
            int moves = currPair[1];
            if(curr == n*n) return moves;
            for(int i=1;i<=6 && curr+i <= n*n; i++){
                int next = curr+i;
                int[] rc = getCoordinates(next,n);
                if(board[rc[0]][rc[1]]!=-1) next = board[rc[0]][rc[1]];
                if(!vis[next]){
                    vis[next] =true;
                    q.offer(new int[]{next,moves+1});
                }
            }
        }
        return -1;
       
    }
   private int[] getCoordinates(int num, int n) {
        int row = n - 1 - (num - 1) / n;
        int col = (num - 1) % n;

        if (((num - 1) / n) % 2 == 1) {
            col = n - 1 - col;
        }
        return new int[]{row, col};
    }
}
// int n = board.length;
// int k =0 ;
// int[] flat = new int[n*n];
// for(int i =n-1 ; i>=0;i--){
//     for(int j = 0 ; j<n;j++){
//         flat[k] = board[i][j];
//         k++;
//     }
// }
// int dice = 0 ;
// for(int i =0 ; i<flat.length;i++){
//     for(int j = 0;j<6;j++){
//         if(flat[j+1] == -1) {
//             i+=6;
//             dice++;
//             break;
//         }
//         else{
//             i+=flat[i]-i;
//         }
//     }
// }
// public issnakeorladder(int current , int goto,int dice){
    
// }

```

---

---
## Quick Revision
This problem asks for the minimum number of moves to reach the end of a board with snakes and ladders.
We can solve this using Breadth-First Search (BFS) to explore possible moves layer by layer.

## Intuition
The core idea is that we want to find the *shortest* path. BFS is naturally suited for finding shortest paths in unweighted graphs. We can model the board as a graph where each square is a node, and possible dice rolls represent edges. Snakes and ladders are just special edges that instantly move us to a different node. By exploring level by level, the first time we reach the final square, we are guaranteed to have done so in the minimum number of moves.

## Algorithm
1.  **Initialization**:
    *   Create a queue `q` to store `[current_square, moves_taken]`.
    *   Add the starting position `[1, 0]` to the queue.
    *   Create a boolean array `visited` of size `n*n + 1` to keep track of visited squares. Mark square 1 as visited.
    *   Get the board dimension `n`.

2.  **BFS Traversal**:
    *   While the queue is not empty:
        *   Dequeue the current state `[current_square, moves_taken]`.
        *   If `current_square` is the final square (`n*n`), return `moves_taken`.
        *   For each possible dice roll (1 to 6):
            *   Calculate the `next_square` by adding the dice roll to `current_square`.
            *   If `next_square` exceeds `n*n`, skip this roll.
            *   Determine the row and column coordinates (`rc`) of `next_square` using a helper function `getCoordinates`.
            *   Check if there's a snake or ladder at `board[rc[0]][rc[1]]`. If it's not -1, update `next_square` to the destination of the snake/ladder.
            *   If `next_square` has not been visited:
                *   Mark `next_square` as visited.
                *   Enqueue `[next_square, moves_taken + 1]`.

3.  **No Solution**:
    *   If the queue becomes empty and the final square hasn't been reached, return -1.

4.  **`getCoordinates` Helper Function**:
    *   Takes `num` (square number) and `n` (board dimension) as input.
    *   Calculates the 0-indexed row: `row = n - 1 - (num - 1) / n`.
    *   Calculates the 0-indexed column: `col = (num - 1) % n`.
    *   Adjusts the column for alternating row directions: if the row index (derived from `(num - 1) / n`) is odd, reverse the column: `col = n - 1 - col`.
    *   Returns `[row, col]`.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Essential for finding the shortest path in an unweighted graph.
*   **Graph Representation**: Implicitly modeling the board as a graph where squares are nodes and dice rolls are edges.
*   **Coordinate Transformation**: Mapping a 1D square number to 2D board coordinates, considering the serpentine layout.
*   **State Management**: Using a `visited` array to avoid cycles and redundant computations.

## Common Mistakes
*   **Incorrect Coordinate Mapping**: Errors in the `getCoordinates` function, especially handling the alternating row directions, are very common.
*   **Forgetting to Mark Visited**: Not marking squares as visited can lead to infinite loops or exploring suboptimal paths.
*   **Handling Snakes/Ladders**: Not correctly updating the `next_square` when a snake or ladder is encountered, or processing the snake/ladder destination as if it were a normal dice roll.
*   **Off-by-One Errors**: Issues with 1-based vs. 0-based indexing for square numbers and array indices.
*   **Queue Management**: Incorrectly adding or removing elements from the queue, or not storing the correct state (square number and moves).

## Complexity Analysis
*   **Time**: O(N^2) - where N is the dimension of the board. Each square (N*N total) is visited and processed at most once. For each square, we iterate through 6 possible dice rolls.
*   **Space**: O(N^2) - for the `visited` array and the queue. In the worst case, the queue might hold a significant portion of the board's squares.

## Commented Code
```java
class Solution {
    // No instance variable needed for dice roll in this BFS approach.
    // int diceroll =0; // This variable is unused and can be removed.

    // Main function to start the snakes and ladders game.
    public int snakesAndLadders(int[][] board) {
        // Calls the helper function that implements the BFS logic.
        return func(board);
    }

    // Helper function that performs the Breadth-First Search.
    public int func(int[][] board ){
        // Initialize a queue for BFS. Each element is an int array: [current_square, moves_taken].
        Queue<int[]> q= new LinkedList<>();
        // Add the starting position: square 1, with 0 moves taken.
        q.add(new int[]{1,0});

        // Get the dimension of the square board.
        int n = board.length;
        // Initialize a boolean array to keep track of visited squares. Size is n*n + 1 for 1-based indexing of squares.
        boolean[] vis = new boolean[n*n + 1];
        // Mark the starting square (1) as visited.
        vis[1] = true; // Important: Mark the starting node as visited *before* adding to queue or immediately after.

        // Start the BFS loop. Continue as long as there are squares to explore.
        while(!q.isEmpty()){
            // Dequeue the current state: current square number and the number of moves to reach it.
            int[] currPair = q.poll();
            int curr = currPair[0]; // The current square number.
            int moves = currPair[1]; // The number of moves taken to reach 'curr'.

            // Check if we have reached the final square.
            if(curr == n*n) return moves; // If so, return the total moves taken.

            // Simulate dice rolls from 1 to 6.
            for(int i=1;i<=6 && curr+i <= n*n; i++){
                // Calculate the potential next square based on the dice roll.
                int next = curr+i;

                // Get the row and column coordinates for the 'next' square.
                int[] rc = getCoordinates(next,n);

                // Check if there's a snake or ladder at the calculated coordinates.
                // If board[rc[0]][rc[1]] is not -1, it means there's a snake/ladder.
                if(board[rc[0]][rc[1]]!=-1) {
                    // Update 'next' to the destination square of the snake or ladder.
                    next = board[rc[0]][rc[1]];
                }

                // If the 'next' square has not been visited yet:
                if(!vis[next]){
                    // Mark the 'next' square as visited to avoid cycles and redundant processing.
                    vis[next] =true;
                    // Enqueue the 'next' square along with an incremented move count.
                    q.offer(new int[]{next,moves+1});
                }
            }
        }
        // If the queue becomes empty and the final square was not reached, it's impossible.
        return -1;
    }

   // Helper function to convert a square number (1-based) to its 2D board coordinates (0-based).
   private int[] getCoordinates(int num, int n) {
        // Calculate the 0-indexed row. The board is read from bottom-left to top-right in a serpentine manner.
        // (num - 1) / n gives the "row block" (0-indexed from bottom).
        // n - 1 - ... flips it to be 0-indexed from top.
        int row = n - 1 - (num - 1) / n;

        // Calculate the 0-indexed column.
        // (num - 1) % n gives the position within the row block.
        int col = (num - 1) % n;

        // Check if the row block is odd (meaning the row is traversed from right to left).
        // ((num - 1) / n) % 2 == 1 checks if the row block index is odd.
        if (((num - 1) / n) % 2 == 1) {
            // If the row is traversed from right to left, reverse the column index.
            col = n - 1 - col;
        }
        // Return the calculated [row, col] coordinates.
        return new int[]{row, col};
    }
}
```

## Interview Tips
*   **Explain BFS First**: Before diving into code, clearly explain why BFS is the appropriate algorithm for finding the shortest path.
*   **Walk Through `getCoordinates`**: This is a tricky part. Verbally explain how you map a 1D square number to 2D coordinates, paying special attention to the serpentine pattern. Use an example like a 3x3 board.
*   **Handle Edge Cases**: Discuss what happens if the board is 1x1, if the start or end has a snake/ladder, or if it's impossible to reach the end.
*   **Clarify Board Representation**: Ensure you understand the board's numbering and traversal direction (usually bottom-left to top-right, alternating directions).

## Revision Checklist
- [ ] Understand the problem: minimum moves on a board with snakes/ladders.
- [ ] Identify BFS as the optimal approach for shortest path.
- [ ] Implement the `getCoordinates` function correctly, handling serpentine traversal.
- [ ] Use a queue to manage states `[square, moves]`.
- [ ] Use a `visited` array to prevent cycles and redundant work.
- [ ] Correctly handle snake and ladder transitions by updating the `next` square.
- [ ] Consider edge cases like an impossible path or a 1x1 board.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [LeetCode 127. Word Ladder](https://leetcode.com/problems/word-ladder/) (BFS for shortest transformation sequence)
*   [LeetCode 752. Open the Lock](https://leetcode.com/problems/open-the-lock/) (BFS for shortest sequence of lock turns)
*   [LeetCode 1129. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/) (BFS on a grid)

## Tags
`Array` `Breadth-First Search` `Graph` `Matrix`
