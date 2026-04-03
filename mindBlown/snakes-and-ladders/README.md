# Snakes And Ladders

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Breadth-First Search` `Matrix`  
**Time:** O(N*N)  
**Space:** O(N*N)

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
This problem asks for the minimum number of dice rolls to reach the end of a board with snakes and ladders.
We solve it using Breadth-First Search (BFS) to explore possible moves layer by layer.

## Intuition
The problem is about finding the shortest path in a graph. Each square on the board can be considered a node, and a dice roll represents an edge. Since we want the minimum number of rolls, BFS is the natural choice because it explores the graph level by level, guaranteeing that the first time we reach the destination, it's via the shortest path. The snakes and ladders are just special transitions that can either move us forward or backward to a different square.

## Algorithm
1.  **Initialization**:
    *   Create a queue `q` to store pairs of `(current_square, number_of_moves)`.
    *   Add the starting square `(1, 0)` to the queue.
    *   Create a boolean array `visited` of size `n*n + 1` to keep track of visited squares. Mark square 1 as visited.
    *   Get the board dimension `n`.

2.  **BFS Traversal**:
    *   While the queue is not empty:
        *   Dequeue a pair `(current_square, moves)`.
        *   If `current_square` is the destination (`n*n`), return `moves`.
        *   For each possible dice roll `i` from 1 to 6:
            *   Calculate the `next_square = current_square + i`.
            *   If `next_square` exceeds `n*n`, break the inner loop.
            *   Determine the row and column coordinates `(r, c)` for `next_square` using the `getCoordinates` helper function.
            *   Check if there's a snake or ladder at `(r, c)`:
                *   If `board[r][c]` is not -1, update `next_square` to `board[r][c]`.
            *   If `next_square` has not been visited:
                *   Mark `next_square` as visited.
                *   Enqueue `(next_square, moves + 1)`.

3.  **No Solution**:
    *   If the queue becomes empty and the destination is not reached, return -1.

4.  **`getCoordinates` Helper Function**:
    *   Takes `num` (square number) and `n` (board dimension) as input.
    *   Calculates the base row and column:
        *   `row = n - 1 - (num - 1) / n`
        *   `col = (num - 1) % n`
    *   Adjusts the column for alternating row directions:
        *   If the row index (derived from `(num - 1) / n`) is odd, reverse the column: `col = n - 1 - col`.
    *   Returns `[row, col]`.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Essential for finding the shortest path in an unweighted graph.
*   **Graph Representation**: Implicitly representing the board as a graph where squares are nodes and dice rolls are edges.
*   **Coordinate Transformation**: Mapping a 1D square number to 2D board coordinates, considering the serpentine layout.
*   **State Management**: Using a `visited` array to avoid cycles and redundant computations.

## Common Mistakes
*   **Incorrect Coordinate Mapping**: Errors in the `getCoordinates` function, especially handling the alternating row directions, are very common.
*   **Forgetting to Mark Visited**: Not marking squares as visited can lead to infinite loops or exploring suboptimal paths.
*   **Off-by-One Errors**: Miscalculating `next_square` or array indices.
*   **Handling Snakes/Ladders Incorrectly**: Not updating `next_square` when a snake or ladder is encountered, or applying it after marking as visited.
*   **Not Handling the Destination Check**: Failing to check if the `current_square` is the destination *before* exploring its neighbors.

## Complexity Analysis
*   **Time**: O(N*N) - We visit each square on the N*N board at most once. For each square, we perform a constant number of operations (dice rolls, coordinate lookup, queue operations).
*   **Space**: O(N*N) - In the worst case, the queue can hold up to O(N*N) elements, and the `visited` array also takes O(N*N) space.

## Commented Code
```java
class Solution {
    // No instance variable needed for diceroll in this BFS approach.
    // int diceroll =0; 

    // Main function to initiate the snakes and ladders game.
    public int snakesAndLadders(int[][] board) {
        // Call the helper function that performs the BFS.
        return func(board);
    }

    // Helper function implementing the Breadth-First Search.
    public int func(int[][] board ){
        // Initialize a queue for BFS. Each element is an array: [current_square, number_of_moves].
        Queue<int[]> q= new LinkedList<>();
        // Add the starting position: square 1, with 0 moves.
        q.add(new int[]{1,0});

        // Get the dimension of the square board.
        int n = board.length;
        // Initialize a boolean array to keep track of visited squares. Size is n*n + 1 because squares are 1-indexed.
        boolean[] vis = new boolean[n*n + 1];
        // Mark the starting square (1) as visited.
        vis[1] = true;

        // Start the BFS loop. Continue as long as there are squares to explore.
        while(!q.isEmpty()){
            // Dequeue the current square and its associated moves.
            int[] currPair = q.poll();
            int curr = currPair[0]; // The current square number.
            int moves = currPair[1]; // The number of moves taken to reach 'curr'.

            // If we have reached the final square (n*n), we have found the shortest path.
            if(curr == n*n) return moves;

            // Simulate dice rolls from 1 to 6.
            for(int i=1;i<=6 && curr+i <= n*n; i++){
                // Calculate the potential next square after rolling the dice.
                int next = curr+i;
                // Get the row and column coordinates for this 'next' square.
                int[] rc = getCoordinates(next,n);

                // Check if there's a snake or ladder at the calculated coordinates.
                // If board[rc[0]][rc[1]] is not -1, it means there's a snake or ladder.
                if(board[rc[0]][rc[1]]!=-1) {
                    // If there is, update 'next' to the destination square of the snake/ladder.
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
        // If the queue becomes empty and we haven't reached the destination, it's impossible.
        return -1;
    }

   // Helper function to convert a square number (1-indexed) to its 2D board coordinates (row, col).
   private int[] getCoordinates(int num, int n) {
        // Calculate the 0-indexed row. The board is read from bottom-left to top-right in a serpentine manner.
        // (num - 1) / n gives the 0-indexed row from the bottom. Subtracting from n-1 gives the actual row index from the top.
        int row = n - 1 - (num - 1) / n;
        // Calculate the 0-indexed column.
        int col = (num - 1) % n;

        // Check if the row (from the bottom) is odd. If it is, the columns are reversed.
        // ((num - 1) / n) % 2 == 1 checks if the row index from the bottom is odd.
        if (((num - 1) / n) % 2 == 1) {
            // If the row is odd, reverse the column index.
            col = n - 1 - col;
        }
        // Return the calculated [row, col] coordinates.
        return new int[]{row, col};
    }
}
```

## Interview Tips
*   **Explain BFS Clearly**: Articulate why BFS is suitable for finding the shortest path and how it works step-by-step.
*   **Focus on `getCoordinates`**: This is the trickiest part. Walk through an example to show how you derive the formula and handle the alternating row logic.
*   **Edge Cases**: Discuss what happens if the board is 1x1, if the start or end has a snake/ladder, or if it's impossible to reach the end.
*   **Code Structure**: Emphasize the use of a queue and a visited set/array for efficient BFS.

## Revision Checklist
- [ ] Understand the problem statement and goal.
- [ ] Identify BFS as the appropriate algorithm for shortest path.
- [ ] Implement the `getCoordinates` function correctly, paying attention to serpentine layout.
- [ ] Use a queue to manage BFS states `(square, moves)`.
- [ ] Use a `visited` array/set to prevent cycles and redundant work.
- [ ] Handle snakes and ladders by updating the `next` square.
- [ ] Check for the destination square upon dequeuing.
- [ ] Consider the case where the destination is unreachable.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [127. Word Ladder](https://leetcode.com/problems/word-ladder/)
*   [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
*   [752. Open the Lock](https://leetcode.com/problems/open-the-lock/)
*   [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

## Tags
`Array` `Breadth-First Search` `Graph`
