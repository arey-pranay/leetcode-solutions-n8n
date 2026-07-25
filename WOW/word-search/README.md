# Word Search

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `String` `Backtracking` `Depth-First Search` `Matrix`  
**Time:** O(m * n * 3^L)  
**Space:** O(m * n + L)

---

## Solution (java)

```java
class Solution {
    boolean[][] vis;
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        vis = new boolean[m][n];
         for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
               if(board[i][j] == word.charAt(0)){
                 vis[i][j] = true;
                 if(func(board,word,1, i,j))return true;
                 vis[i][j] = false;
               }
             }
          }
        return false;
    }
    public boolean func(char[][] board, String word, int index,int x , int y) {
      if(index==word.length()) return true;
      int[] neighs = new int[]{-1,0,1,0,-1};
      for(int i =0;i<4;i++){
        int X = x + neighs[i];
        int Y = y + neighs[i+1];
        if(X<0 || Y<0 || X==m || Y==n) continue;
        if(board[X][Y] == word.charAt(index) && !vis[X][Y]){
             vis[X][Y] = true;
             if(func(board,word,index+1,X,Y)) return true;
             vis[X][Y] = false;
        }
      } 
      return false;
    }
}
```

---

---
## Quick Revision
Given a 2D board of characters and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.

## Intuition
The problem asks us to find a path in a grid that spells out a given word. This sounds like a graph traversal problem. We can think of each cell in the grid as a node, and adjacent cells as having edges between them. We need to find a path that matches the sequence of characters in the word. A Depth First Search (DFS) is a natural fit here because we want to explore a path as deeply as possible before backtracking. We need to keep track of visited cells to avoid cycles and reusing the same cell for multiple letters in the word.

## Algorithm
1. Initialize a `visited` 2D boolean array of the same dimensions as the `board` to keep track of visited cells.
2. Iterate through each cell (`i`, `j`) of the `board`.
3. If the character at `board[i][j]` matches the first character of the `word`:
    a. Mark `board[i][j]` as visited.
    b. Start a recursive DFS function (`func`) from this cell, trying to find the rest of the `word` (starting from the second character).
    c. If the recursive call returns `true` (meaning the word was found), return `true` immediately.
    d. If the recursive call returns `false`, backtrack: unmark `board[i][j]` as visited.
4. If after checking all starting cells, the word is not found, return `false`.

The recursive DFS function (`func`):
1. Base Case: If the current `index` in the `word` equals the `word.length()`, it means we have successfully found all characters, so return `true`.
2. Define possible neighbor movements (up, down, left, right) using an array like `{-1, 0, 1, 0, -1}`.
3. For each of the four possible neighbors (`X`, `Y`):
    a. Check if the neighbor is within the grid boundaries.
    b. Check if the character at `board[X][Y]` matches the character at `word.charAt(index)`.
    c. Check if the neighbor cell has not been visited (`!vis[X][Y]`).
    d. If all conditions are met:
        i. Mark `board[X][Y]` as visited.
        ii. Recursively call `func` for the next character (`index + 1`) from the neighbor cell (`X`, `Y`).
        iii. If the recursive call returns `true`, return `true`.
        iv. If the recursive call returns `false`, backtrack: unmark `board[X][Y]` as visited.
4. If none of the neighbors lead to a solution, return `false`.

## Concept to Remember
*   **Depth First Search (DFS):** Essential for exploring paths in a grid or graph.
*   **Backtracking:** Crucial for undoing choices when a path doesn't lead to a solution, allowing exploration of alternative paths.
*   **Grid Traversal:** Understanding how to navigate and check boundaries in a 2D array.
*   **State Management:** Using a `visited` array to prevent cycles and redundant computations.

## Common Mistakes
*   **Forgetting to backtrack:** Not unmarking cells as visited after a failed recursive call, which can lead to incorrect results or infinite loops.
*   **Incorrect boundary checks:** Failing to properly check if neighbor coordinates are within the grid dimensions.
*   **Reusing cells:** Not using a `visited` array, allowing the same cell to be used for multiple characters in the word.
*   **Off-by-one errors in indexing:** Mismanaging the `index` for the `word` or the `board` coordinates.
*   **Not handling the first character correctly:** The initial loop needs to correctly identify potential starting points for the DFS.

## Complexity Analysis
- Time: O(m * n * 3^L) - where `m` is the number of rows, `n` is the number of columns, and `L` is the length of the `word`. In the worst case, for each cell, we might start a DFS. From each cell in the DFS, we can explore up to 3 directions (since we can't go back to the cell we just came from). The `3^L` comes from the fact that at each step of the DFS, we have at most 3 unvisited neighbors to explore for the next character.
- Space: O(m * n + L) - O(m * n) for the `visited` array and O(L) for the recursion stack depth in the worst case (if the word forms a long path).

## Commented Code
```java
class Solution {
    // Declare a 2D boolean array to keep track of visited cells in the board.
    boolean[][] vis;
    // Store the number of rows in the board.
    int m;
    // Store the number of columns in the board.
    int n;

    // Main function to check if the word exists in the board.
    public boolean exist(char[][] board, String word) {
        // Get the number of rows from the board.
        m = board.length;
        // Get the number of columns from the board.
        n = board[0].length;
        // Initialize the visited array with the dimensions of the board.
        vis = new boolean[m][n];

        // Iterate through each cell of the board as a potential starting point.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // If the current cell's character matches the first character of the word.
                if(board[i][j] == word.charAt(0)) {
                    // Mark the current cell as visited.
                    vis[i][j] = true;
                    // Start the recursive search (DFS) from this cell for the rest of the word.
                    // The index starts at 1 because the first character is already matched.
                    if(func(board, word, 1, i, j)) {
                        // If the word is found starting from this cell, return true immediately.
                        return true;
                    }
                    // Backtrack: If the word was not found starting from this cell, unmark it as visited.
                    vis[i][j] = false;
                }
            }
        }
        // If the word was not found after checking all possible starting cells, return false.
        return false;
    }

    // Recursive helper function (DFS) to search for the word.
    // board: the character grid.
    // word: the word to search for.
    // index: the current character index in the word we are looking for.
    // x, y: the current cell coordinates in the board.
    public boolean func(char[][] board, String word, int index, int x, int y) {
        // Base case: If the index has reached the end of the word, it means we have found the entire word.
        if(index == word.length()) {
            return true;
        }

        // Define the relative movements for neighbors: up, right, down, left.
        // neighs[0] = -1 (up), neighs[1] = 0 (no horizontal move)
        // neighs[2] = 1 (down), neighs[3] = 0 (no horizontal move)
        // neighs[4] = -1 (left)
        int[] neighs = new int[]{-1, 0, 1, 0, -1};

        // Iterate through the four possible neighbors (up, down, left, right).
        for(int i = 0; i < 4; i++) {
            // Calculate the coordinates of the neighbor cell.
            int X = x + neighs[i];
            int Y = y + neighs[i+1];

            // Check if the neighbor coordinates are out of bounds.
            if(X < 0 || Y < 0 || X == m || Y == n) {
                // If out of bounds, skip this neighbor.
                continue;
            }

            // Check if the neighbor cell's character matches the current character in the word
            // AND if the neighbor cell has not been visited yet.
            if(board[X][Y] == word.charAt(index) && !vis[X][Y]) {
                // Mark the neighbor cell as visited.
                vis[X][Y] = true;
                // Recursively call func for the next character in the word from this neighbor cell.
                if(func(board, word, index + 1, X, Y)) {
                    // If the recursive call returns true, it means the rest of the word was found.
                    return true;
                }
                // Backtrack: If the word was not found through this neighbor, unmark it as visited.
                vis[X][Y] = false;
            }
        }
        // If none of the neighbors led to finding the word, return false.
        return false;
    }
}
```

## Interview Tips
1.  **Explain DFS and Backtracking Clearly:** Be prepared to explain why DFS is suitable and how backtracking works to explore all possibilities.
2.  **Trace an Example:** Walk through a small example on paper or whiteboard to demonstrate your understanding of the algorithm. Show how the `visited` array changes and how backtracking occurs.
3.  **Discuss Edge Cases:** Mention edge cases like an empty board, an empty word, or a word longer than the total number of cells.
4.  **Clarify Constraints:** Ask about the maximum size of the board and the word length, as this can influence the feasibility of certain approaches and the expected complexity.
5.  **Optimize if Asked:** If the interviewer asks about optimizations, consider if there are ways to prune the search space earlier, though for this problem, the core DFS with visited tracking is standard.

## Revision Checklist
- [ ] Understand the problem: finding a word path in a grid.
- [ ] Recognize DFS/backtracking as the primary approach.
- [ ] Implement the main loop to iterate through starting cells.
- [ ] Implement the recursive DFS function.
- [ ] Correctly handle base cases in recursion.
- [ ] Implement boundary checks for neighbors.
- [ ] Use a `visited` array to prevent cycles.
- [ ] Implement backtracking by unmarking visited cells.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing examples.

## Similar Problems
*   Word Search II
*   Pacific Atlantic Water Flow
*   Surrounded Regions
*   Number of Islands

## Tags
`Array` `Depth-First Search` `Backtracking` `Matrix`
