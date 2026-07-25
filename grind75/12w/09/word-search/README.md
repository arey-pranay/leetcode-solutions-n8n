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
Given a 2D board of characters and a word, determine if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.

## Intuition
The problem asks us to find a path in a grid that spells out a given word. This sounds like a graph traversal problem. We can think of each cell in the grid as a node, and adjacent cells as having edges between them. Since we need to find a specific sequence of characters, a Depth First Search (DFS) approach seems natural. We can start a DFS from any cell that matches the first character of the word and explore its neighbors to see if they match the subsequent characters. To avoid reusing the same cell multiple times within a single path, we need a way to mark visited cells.

## Algorithm
1. Initialize a `visited` 2D boolean array of the same dimensions as the `board` to keep track of visited cells during a DFS traversal.
2. Iterate through each cell `(i, j)` of the `board`.
3. If the character at `board[i][j]` matches the first character of the `word` (`word.charAt(0)`):
    a. Mark `board[i][j]` as visited (e.g., set `vis[i][j] = true`).
    b. Start a recursive DFS function (`func`) from `(i, j)` to search for the rest of the `word` (starting from the second character, index 1).
    c. If the DFS function returns `true` (meaning the word was found), immediately return `true`.
    d. If the DFS function returns `false`, backtrack by unmarking `board[i][j]` as visited (e.g., set `vis[i][j] = false`) to allow it to be part of other potential paths.
4. If the loops complete without finding the word, return `false`.

**Recursive DFS function (`func(board, word, index, x, y)`):**
1. **Base Case:** If `index` equals the length of the `word`, it means we have successfully matched all characters, so return `true`.
2. Define the possible movements to adjacent cells (up, down, left, right) using an array like `{-1, 0, 1, 0, -1}` for `dx` and `dy` offsets.
3. Iterate through the four possible neighbors `(X, Y)` of the current cell `(x, y)`.
4. For each neighbor:
    a. Check if `(X, Y)` is within the grid boundaries (`0 <= X < m` and `0 <= Y < n`).
    b. Check if the character at `board[X][Y]` matches the character at `word.charAt(index)`.
    c. Check if the neighbor cell `(X, Y)` has not been visited in the current path (`!vis[X][Y]`).
    d. If all conditions are met:
        i. Mark `(X, Y)` as visited (`vis[X][Y] = true`).
        ii. Recursively call `func` for the next character (`index + 1`) from the neighbor cell `(X, Y)`.
        iii. If the recursive call returns `true`, propagate `true` upwards.
        iv. **Backtrack:** If the recursive call returns `false`, unmark `(X, Y)` as visited (`vis[X][Y] = false`) to explore other paths.
5. If none of the neighbors lead to a solution, return `false`.

## Concept to Remember
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to a computational problem, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Grid Traversal:** Techniques for moving through a 2D array, often involving checking boundaries and using relative offsets for neighbors.
*   **State Management (Visited Array):** Crucial for preventing cycles and ensuring each cell is used at most once per path.

## Common Mistakes
*   **Forgetting to Backtrack:** Not resetting the `visited` status of a cell after a failed recursive call. This can lead to incorrect results as cells might be incorrectly marked as unavailable for subsequent valid paths.
*   **Incorrect Boundary Checks:** Failing to properly check if the neighboring cells are within the bounds of the grid, leading to `ArrayIndexOutOfBoundsException`.
*   **Reusing Cells:** Not using a `visited` array or not managing it correctly, allowing the same cell to be used multiple times within a single word path.
*   **Off-by-One Errors in Indexing:** Incorrectly handling the `index` for the `word` or the `board` dimensions.
*   **Not Handling the First Character Correctly:** The initial loop should correctly identify starting points and initiate the DFS.

## Complexity Analysis
*   **Time:** O(m * n * 3^L), where `m` is the number of rows, `n` is the number of columns, and `L` is the length of the `word`.
    *   The outer loops iterate through each cell of the `m x n` grid, which is O(m * n).
    *   From each cell, we initiate a DFS. In the worst case, the DFS might explore paths of length `L`. At each step of the DFS, we have up to 3 possible directions to move (since we cannot go back to the cell we just came from, and we cannot go out of bounds). So, for a word of length `L`, the DFS can explore up to `3^L` paths.
    *   Therefore, the total time complexity is O(m * n * 3^L).
*   **Space:** O(m * n) for the `visited` array and O(L) for the recursion stack depth.
    *   The `visited` 2D array takes O(m * n) space.
    *   The recursion depth can go up to `L` (the length of the word) in the worst case, contributing O(L) to the space complexity.
    *   The dominant factor is the `visited` array, so the overall space complexity is O(m * n).

## Commented Code
```java
class Solution {
    // Declare a 2D boolean array to keep track of visited cells during DFS.
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
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // If the current cell's character matches the first character of the word.
                if(board[i][j] == word.charAt(0)){
                    // Mark the current cell as visited for this path.
                    vis[i][j] = true;
                    // Start the recursive DFS function to find the rest of the word.
                    // '1' is the index of the next character to find in the word.
                    if(func(board, word, 1, i, j)) {
                        // If the word is found starting from this cell, return true immediately.
                        return true;
                    }
                    // Backtrack: If the word was not found starting from this cell,
                    // unmark it as visited so it can be part of other paths.
                    vis[i][j] = false;
                }
            }
        }
        // If the loops complete and the word is not found from any starting cell, return false.
        return false;
    }

    // Recursive DFS function to search for the word.
    // board: the 2D character grid.
    // word: the target word to find.
    // index: the current character index in the word we are looking for.
    // x, y: the current cell coordinates (row, column).
    public boolean func(char[][] board, String word, int index, int x, int y) {
        // Base case: If the index reaches the length of the word, it means we have found the entire word.
        if(index == word.length()) {
            return true;
        }

        // Define the relative movements for neighbors: up, right, down, left.
        // neighs[0] = -1 (up), neighs[1] = 0 (no horizontal change)
        // neighs[2] = 1 (down), neighs[3] = 0 (no horizontal change)
        // neighs[4] = -1 (left, wraps around to match the first element for the last iteration)
        int[] neighs = new int[]{-1, 0, 1, 0, -1};

        // Iterate through the 4 possible neighbors (up, down, left, right).
        for(int i = 0; i < 4; i++){
            // Calculate the coordinates of the neighboring cell.
            int X = x + neighs[i];
            int Y = y + neighs[i+1];

            // Check if the neighbor coordinates are out of bounds.
            if(X < 0 || Y < 0 || X == m || Y == n) {
                // If out of bounds, skip this neighbor.
                continue;
            }

            // Check if the neighbor cell's character matches the current character in the word
            // AND if the neighbor cell has not been visited in the current path.
            if(board[X][Y] == word.charAt(index) && !vis[X][Y]){
                // Mark the neighbor cell as visited for this path.
                vis[X][Y] = true;
                // Recursively call func for the next character in the word from this neighbor cell.
                if(func(board, word, index + 1, X, Y)) {
                    // If the recursive call returns true, it means the rest of the word was found.
                    // Propagate true upwards.
                    return true;
                }
                // Backtrack: If the recursive call did not find the rest of the word,
                // unmark the neighbor cell as visited to allow it to be part of other paths.
                vis[X][Y] = false;
            }
        }
        // If none of the neighbors lead to a solution for the rest of the word, return false.
        return false;
    }
}
```

## Interview Tips
1.  **Explain DFS and Backtracking Clearly:** Before coding, articulate your approach using DFS and backtracking. Explain why it's suitable and how you'll handle visited states.
2.  **Trace an Example:** Walk through a small example on paper or a whiteboard. This demonstrates your understanding of the algorithm's execution flow, especially the backtracking part.
3.  **Discuss Edge Cases:** Consider cases like an empty board, an empty word, a word longer than the board's total cells, or a word with repeated characters.
4.  **Optimize if Asked:** If the interviewer asks about performance, discuss the time complexity and potential optimizations (though for this problem, the core DFS approach is standard). Mention that the `3^L` factor is inherent to the problem's nature.

## Revision Checklist
- [ ] Understand the problem statement: finding a word path in a grid.
- [ ] Recognize DFS as a suitable traversal strategy.
- [ ] Implement boundary checks correctly.
- [ ] Use a `visited` array to prevent cell reuse within a path.
- [ ] Implement backtracking by unmarking visited cells.
- [ ] Handle the base case for the recursion (word found).
- [ ] Test with various word placements and board configurations.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Letter Combinations of a Phone Number
*   Combinations
*   Permutations
*   Word Break
*   Path Sum II

## Tags
`Array` `Depth-First Search` `Backtracking` `Matrix`
