# N Queens Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Backtracking`  
**Time:** O(N!)  
**Space:** O(N x N)

---

## Solution (java)

```java
class Solution {
    int ans = 0;
    int N;
    boolean[][] board;
    public int totalNQueens(int n) {
        N=n;
        board = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = false;
            }
        }
        traverseRow(0);
        return ans;
    }
    
    private void traverseRow(int row){
        if(row==N){ans++; return;}
        for(int col=0;col<N;col++){
            if(isSafe(row,col)){
                board[row][col]=true;
                traverseRow(row+1);
                board[row][col]=false;
            }
        }
    }
    private boolean isSafe(int x, int y){
        int i, j; //checking | ans -
        for(i=0; i<N; i++) if(board[i][y] || board[x][i]) return false;

        i=x; j=y; // checking /
        while(i>=0 && j<N) if(board[i--][j++]) return false;

        i=x; j=y; // checking \
        while(i>=0 && j>=0) if(board[i--][j--]) return false;

        return true;
    }
}

// Solution s = new Solution();
// while(i<1000){s.totalNQueens(tc[i])==expected[i];}
```

---

---
## Quick Revision
The problem asks to find the number of ways to place n queens on an nxn chessboard such that no queen attacks any other.
We solve this problem using backtracking and depth-first search.

## Intuition
The key insight is to use a 2D boolean array `board` to represent the chessboard, where `board[i][j]` indicates whether there is a queen at position `(i, j)` or not. We can then use three functions: `isSafe` to check if it's safe to place a queen at a given position, `traverseRow` to recursively explore all possible placements, and `totalNQueens` to return the total number of solutions.

## Algorithm
1. Initialize a 2D boolean array `board` with size `n x n`, where `n` is the input parameter.
2. Define three functions: `isSafe` to check if it's safe to place a queen at a given position, `traverseRow` to recursively explore all possible placements, and `totalNQueens` to return the total number of solutions.
3. In `traverseRow`, iterate over each column in the current row and use `isSafe` to check if it's safe to place a queen at that position. If it is, mark the position as occupied (`board[row][col] = true`) and recursively call `traverseRow` with the next row.
4. After exploring all possible placements for the current row, backtrack by resetting the occupied position to unoccupied (`board[row][col] = false`).
5. In `isSafe`, check if there is a queen in the same column, diagonal (from top-left to bottom-right), or diagonal (from top-right to bottom-left) of the given position.

## Concept to Remember
*   **Backtracking**: a technique used to explore all possible solutions by recursively trying different branches.
*   **Depth-First Search**: a graph traversal algorithm that explores as far as possible along each branch before backtracking.
*   **2D Array Representation**: representing a chessboard using a 2D array, where each element indicates whether there is a queen at that position or not.

## Common Mistakes
*   Forgetting to reset the occupied positions after backtracking.
*   Incorrectly implementing the `isSafe` function, leading to incorrect solutions.
*   Not handling the base case properly in the recursive `traverseRow` function.

## Complexity Analysis
- Time: O(N!), where N is the input parameter. This is because we are exploring all possible placements of queens on the board.
- Space: O(N x N), which is the space required to store the 2D boolean array representing the chessboard.

## Commented Code
```java
class Solution {
    int ans = 0;
    int N;

    public int totalNQueens(int n) {
        // Initialize variables and board
        N = n;
        boolean[][] board = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = false; // Initialize all positions as unoccupied
            }
        }

        // Start the backtracking process from row 0
        traverseRow(0, board);

        return ans;
    }

    private void traverseRow(int row, boolean[][] board) {
        if (row == N) { // If we have reached the last row, increment the answer and return
            ans++;
            return;
        }

        for (int col = 0; col < N; col++) {
            // Check if it's safe to place a queen at this position
            if (isSafe(row, col, board)) {
                board[row][col] = true; // Mark the position as occupied

                // Recursively try placing queens in the next row
                traverseRow(row + 1, board);

                // Backtrack by resetting the occupied position to unoccupied
                board[row][col] = false;
            }
        }
    }

    private boolean isSafe(int x, int y, boolean[][] board) {
        // Check if there is a queen in the same column or diagonals
        for (int i = 0; i < N; i++) {
            if (board[i][y] || board[x][i]) return false;
        }

        // Check diagonal from top-left to bottom-right
        int i = x, j = y;
        while (i >= 0 && j < N) {
            if (board[i--][j++]) return false;
        }

        // Check diagonal from top-right to bottom-left
        i = x; j = y;
        while (i >= 0 && j >= 0) {
            if (board[i--][j--]) return false;
        }

        return true; // It's safe to place a queen at this position
    }
}
```

## Interview Tips
*   Make sure to understand the problem clearly and ask for clarification if needed.
*   Use a systematic approach, such as backtracking or depth-first search, to explore all possible solutions.
*   Pay attention to time and space complexity, and ensure your solution meets these requirements.

## Revision Checklist
- [ ] Understand the problem statement and clarify any doubts.
- [ ] Develop a clear plan for solving the problem using a systematic approach (backtracking or DFS).
- [ ] Implement the `isSafe` function correctly to check for conflicts between queens.
- [ ] Ensure proper handling of base cases in the recursive `traverseRow` function.

## Similar Problems
*   N-Queens Problem: The original N-Queens problem, where we need to place n queens on an nxn chessboard such that no queen attacks any other.
*   Sudoku Solver: A similar problem that involves solving a Sudoku puzzle using backtracking or depth-first search.
*   Knight's Tour: Another problem involving placing knights on a chessboard in a way that each knight moves to every square exactly once.

## Tags
`Array`, `Hash Map`, `Backtracking`, `Depth-First Search`, `2D Array Representation`
