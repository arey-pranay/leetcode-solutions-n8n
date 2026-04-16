# Word Search

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `String` `Backtracking` `Depth-First Search` `Matrix`  
**Time:** O(m * n * 3^L)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == word.charAt(0)){
                    boolean[][] vis = new boolean[m][n];
                    if(func(i,j,board,vis,word,1)) return true;
                }
            }
        }
        return false;
    }
    public boolean func(int x , int y, char[][] board, boolean[][] vis, String word, int index){
        if(index == word.length()) return true;
        int m = board.length;
        int n = board[0].length;  
        vis[x][y] = true;
        int[] neighs = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X >= m || Y >= n) continue;
            if(board[X][Y] == word.charAt(index) && !vis[X][Y]){
                if(func(X,Y,board,vis,word,index+1)) return true;
                else vis[X][Y] = false;
            }
            
        }
        return false;
    }
}
```

---

---
## Quick Revision
This problem asks if a given word can be formed by traversing adjacent cells in a 2D grid.
We solve this using Depth First Search (DFS) with backtracking to explore all possible paths.

## Intuition
The core idea is to treat the grid as a graph where each cell is a node and adjacent cells are connected by edges. We need to find a path in this graph that spells out the target word. Since we can't reuse cells within a single word path, we need a way to keep track of visited cells. DFS is a natural fit for exploring paths, and backtracking allows us to undo choices if a path doesn't lead to the solution. The "aha moment" is realizing that we can start the search from *any* cell that matches the first character of the word and then recursively explore its neighbors.

## Algorithm
1. Iterate through each cell `(i, j)` of the `board`.
2. If the character at `board[i][j]` matches the first character of the `word`, initiate a Depth First Search (DFS) from this cell.
3. The DFS function `func(x, y, board, visited, word, index)` will:
    a. Base Case: If `index` equals the length of the `word`, it means we have successfully found the word, so return `true`.
    b. Mark the current cell `(x, y)` as visited.
    c. Define possible neighbor movements (up, down, left, right).
    d. For each neighbor `(X, Y)`:
        i. Check if the neighbor is within the board boundaries.
        ii. Check if the character at `board[X][Y]` matches the character at `word[index]`.
        iii. Check if the neighbor cell `(X, Y)` has not been visited yet.
        iv. If all conditions are met, recursively call `func(X, Y, board, visited, word, index + 1)`.
        v. If the recursive call returns `true`, propagate `true` upwards.
        vi. If the recursive call returns `false` (meaning this path didn't work), backtrack by unmarking the neighbor cell `(X, Y)` as visited.
    e. If none of the neighbors lead to a solution from the current cell, return `false`.
4. If the DFS initiated from any starting cell returns `true`, the `exist` function returns `true`.
5. If the loops complete without finding the word, return `false`.

## Concept to Remember
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to a computational problem, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Grid Traversal:** Efficiently navigating through a 2D array, often using recursion or iteration with boundary checks.
*   **State Management (Visited Array):** Keeping track of visited nodes/cells to avoid cycles and redundant computations.

## Common Mistakes
*   **Not handling boundary conditions:** Failing to check if neighbor coordinates are within the grid limits.
*   **Forgetting to backtrack:** Not unmarking visited cells after a failed recursive call, which can lead to incorrect results for subsequent searches.
*   **Reusing cells within a single path:** Not using a `visited` array or similar mechanism to prevent using the same cell multiple times for one word instance.
*   **Incorrect base case for recursion:** The recursion should stop when the entire word is found, not just when a single character matches.
*   **Starting DFS from every cell unconditionally:** Only start DFS if the current cell matches the *first* character of the word.

## Complexity Analysis
*   **Time:** O(m * n * 3^L) where `m` is the number of rows, `n` is the number of columns, and `L` is the length of the `word`. In the worst case, we might start DFS from every cell (m*n), and for each cell, we explore up to 3 directions (since one direction is blocked by the visited cell). The `3^L` comes from the fact that at each step of DFS, we have at most 3 choices (excluding the direction we came from).
*   **Space:** O(m * n) for the `visited` boolean array. In the worst case, the recursion depth can also be up to `L`, contributing O(L) to the call stack space. However, `L` can be at most `m*n`, so the dominant factor is the `visited` array.

## Commented Code
```java
class Solution {
    // Main function to check if the word exists in the board.
    public boolean exist(char[][] board, String word) {
        // Get the dimensions of the board.
        int m = board.length; // Number of rows.
        int n = board[0].length; // Number of columns.

        // Iterate through each cell of the board as a potential starting point.
        for(int i=0;i<m;i++){ // Loop through rows.
            for(int j=0;j<n;j++){ // Loop through columns.
                // If the current cell's character matches the first character of the word.
                if(board[i][j] == word.charAt(0)){
                    // Initialize a visited array to keep track of visited cells for the current path.
                    boolean[][] vis = new boolean[m][n];
                    // Start the recursive DFS search from this cell.
                    // If the DFS finds the word, return true immediately.
                    if(func(i,j,board,vis,word,1)) return true;
                }
            }
        }
        // If the word is not found after checking all possible starting points, return false.
        return false;
    }

    // Recursive DFS function to search for the word.
    // x: current row, y: current column, board: the grid, vis: visited array, word: target word, index: current character index in word.
    public boolean func(int x , int y, char[][] board, boolean[][] vis, String word, int index){
        // Base case: If the index has reached the end of the word, it means the entire word has been found.
        if(index == word.length()) return true;

        // Get the dimensions of the board (needed for boundary checks).
        int m = board.length;
        int n = board[0].length;

        // Mark the current cell as visited for this path.
        vis[x][y] = true;

        // Define the relative movements for neighbors: up, down, left, right.
        // neighs[0] = -1 (up), neighs[1] = 0 (no row change)
        // neighs[2] = 1 (down), neighs[3] = 0 (no row change)
        // neighs[4] = -1 (left), neighs[5] = 0 (no col change) - this is incorrect, should be:
        // int[] dx = {-1, 1, 0, 0}; // row changes
        // int[] dy = {0, 0, -1, 1}; // column changes
        // The provided code uses a slightly unusual but functional way to get neighbors:
        int[] neighs = new int[]{-1,0,1,0,-1}; // This array is used to calculate X and Y coordinates for neighbors.
                                             // For i=0: X = x + neighs[0] = x-1, Y = y + neighs[1] = y
                                             // For i=1: X = x + neighs[1] = x,   Y = y + neighs[2] = y+1
                                             // For i=2: X = x + neighs[2] = x+1, Y = y + neighs[3] = y
                                             // For i=3: X = x + neighs[3] = x,   Y = y + neighs[4] = y-1
                                             // This is a common pattern for 4-directional movement.

        // Iterate through the 4 possible neighbors.
        for(int i=0;i<4;i++){
            // Calculate the coordinates of the neighbor.
            int X = x + neighs[i];     // Neighbor's row.
            int Y = y + neighs[i+1];   // Neighbor's column.

            // Check if the neighbor is out of bounds.
            if(X<0 || Y<0 || X >= m || Y >= n) continue; // If out of bounds, skip this neighbor.

            // Check if the neighbor's character matches the next character in the word AND if it hasn't been visited.
            if(board[X][Y] == word.charAt(index) && !vis[X][Y]){
                // If conditions met, recursively call DFS for the neighbor.
                if(func(X,Y,board,vis,word,index+1)) return true; // If the recursive call finds the rest of the word, return true.
                else vis[X][Y] = false; // Backtrack: If the recursive call did NOT find the word, unmark the neighbor as visited.
                                        // This allows other paths to potentially use this cell.
            }
        }

        // If none of the neighbors led to a solution from the current cell, return false.
        // Note: The current cell (x,y) is NOT unmarked here. This is because if we return false,
        // it means the path starting from the initial cell and going through (x,y) failed.
        // The initial call to `func` from `exist` will then try a different starting cell.
        // If we were to unmark `vis[x][y] = false` here, it would mean that the cell (x,y)
        // is available for *any* subsequent path, which is incorrect if it was part of a failed path.
        // The backtracking happens on the *neighbor* `vis[X][Y] = false` when a recursive call returns false.
        return false;
    }
}
```

## Interview Tips
1.  **Explain DFS and Backtracking Clearly:** Before coding, articulate the DFS approach and why backtracking is necessary. Use a small example to walk through the process.
2.  **Handle Edge Cases:** Discuss how you'll handle an empty board, an empty word, or a word longer than the total number of cells.
3.  **Trace with an Example:** Be prepared to trace your code with a small 2x2 or 3x3 board and a short word. This demonstrates your understanding and helps catch bugs.
4.  **Discuss Complexity:** Be ready to explain the time and space complexity of your solution, justifying each part.

## Revision Checklist
- [ ] Understand the problem statement: Can a word be formed by adjacent cells?
- [ ] Recognize DFS/Backtracking as the appropriate approach.
- [ ] Implement the `exist` function to iterate through starting points.
- [ ] Implement the `func` (DFS) function with correct base cases and recursive calls.
- [ ] Correctly manage the `visited` array for each DFS path.
- [ ] Implement backtracking by unmarking cells when a path fails.
- [ ] Handle boundary conditions for neighbor exploration.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Search II
*   Pacific Atlantic Water Flow
*    Surrounded Regions
*    Number of Islands
*    Path Sum II

## Tags
`Array` `Depth-First Search` `Backtracking` `Matrix`
