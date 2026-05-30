# Game Of Life

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m * n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
        int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
        
        for(int i = 0 ; i<m;i++){
            for(int j = 0 ;j<n;j++){
                int count = 0;
                for(int k=0;k<8;k++){
                    int X = i + dx[k];
                    int Y = j + dy[k];
                    if(X<0 || Y<0 || X>=m || Y>=n) continue;
                    if(board[X][Y] == 1 || board[X][Y] == 3) count++;
                }
                if(board[i][j]==1 && (count==2 || count==3)) board[i][j] = 3; 
                else if(count == 3) board[i][j] = 2; 
            } 
        }
        for(int i = 0 ; i<m;i++)
            for(int j = 0 ;j<n;j++)
                if(board[i][j]==2 || board[i][j]==3) board[i][j]=1; 
                else board[i][j] = 0;
                
    }
}


```

---

---
## Quick Revision
Simulate Conway's Game of Life on a 2D grid.
Solve by iterating through cells, counting neighbors, and applying rules using intermediate states.

## Intuition
The core challenge is that updating a cell's state based on its neighbors affects the neighbor count for *other* cells in the same pass. To avoid this, we need a way to store the *next* state without immediately overwriting the *current* state. The provided solution cleverly uses "in-place" modification by introducing temporary states (2 and 3) to represent transitions.

## Algorithm
1.  Initialize the dimensions of the board: `m` (rows) and `n` (columns).
2.  Define two arrays, `dx` and `dy`, to represent the 8 possible relative offsets for neighbors (top-left, top, top-right, etc.).
3.  Iterate through each cell `(i, j)` of the board.
4.  For each cell, initialize a `count` of live neighbors to 0.
5.  Iterate through the 8 possible neighbor directions using `dx` and `dy`.
6.  Calculate the coordinates `(X, Y)` of the neighbor.
7.  Check if the neighbor's coordinates are within the board boundaries. If not, `continue` to the next neighbor.
8.  If the neighbor is within bounds, check its current state. If `board[X][Y]` is 1 (live) or 3 (was live, will be dead), increment `count`. The `3` state is crucial here because it represents a cell that was alive in the *original* state but is transitioning to dead, and we still need to count it as a live neighbor for the current cell's calculation.
9.  After counting all neighbors for cell `(i, j)`:
    *   If the current cell `board[i][j]` is 1 (live) AND `count` is 2 or 3, it will remain alive. Mark it with state `3` (was live, will be live).
    *   If the current cell `board[i][j]` is 0 (dead) AND `count` is exactly 3, it will become alive. Mark it with state `2` (was dead, will be live).
10. After the first pass, the board contains cells with states:
    *   `0`: Dead, stays dead.
    *   `1`: Live, stays live (but will be updated in the next step).
    *   `2`: Dead, becomes live.
    *   `3`: Live, stays live.
11. Perform a second pass through the board to finalize the states:
    *   If `board[i][j]` is 2 or 3, set it to 1 (it becomes or remains live).
    *   Otherwise (if it's 0 or 1 that was supposed to die), set it to 0.

## Concept to Remember
*   **In-place modification:** Modifying data structures without using significant extra space.
*   **State representation:** Using temporary or encoded states to store intermediate information within the original data structure.
*   **Neighbor traversal:** Efficiently iterating through adjacent elements in a 2D grid.
*   **Boundary conditions:** Handling edge cases where calculations might go out of bounds.

## Common Mistakes
*   **Modifying the board directly without intermediate states:** This leads to incorrect neighbor counts as updates propagate immediately.
*   **Incorrect neighbor counting logic:** Missing some neighbors or misinterpreting the temporary states (e.g., not counting `3` as a live neighbor).
*   **Off-by-one errors in boundary checks:** Incorrectly defining `m` or `n`, or using `<` instead of `<=` (or vice-versa) for boundary conditions.
*   **Forgetting the second pass:** Not converting the temporary states (2 and 3) back to the final 0s and 1s.

## Complexity Analysis
- Time: O(m * n) - reason: We iterate through each cell of the m x n board twice. For each cell, we perform a constant number of operations (checking 8 neighbors).
- Space: O(1) - reason: We are modifying the board in-place and only using a few extra variables for loop counters and neighbor offsets.

## Commented Code
```java
class Solution {
    public void gameOfLife(int[][] board) {
        // Get the number of rows in the board.
        int m = board.length;
        // Get the number of columns in the board.
        int n = board[0].length;
        
        // Define the relative x-coordinates for the 8 neighbors.
        // These represent movements: up, down, left, right, and diagonals.
        int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
        // Define the relative y-coordinates for the 8 neighbors.
        // These correspond to the dx movements.
        int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
        
        // First pass: Iterate through each cell (i, j) to determine its next state.
        for(int i = 0 ; i<m;i++){ // Loop through each row.
            for(int j = 0 ;j<n;j++){ // Loop through each column in the current row.
                // Initialize a counter for live neighbors for the current cell (i, j).
                int count = 0;
                // Iterate through all 8 possible neighbor directions.
                for(int k=0;k<8;k++){
                    // Calculate the coordinates of the neighbor.
                    int X = i + dx[k];
                    int Y = j + dy[k];
                    
                    // Check if the neighbor's coordinates are out of bounds.
                    if(X<0 || Y<0 || X>=m || Y>=n) continue; // If out of bounds, skip this neighbor.
                    
                    // Check if the neighbor is alive in the original state.
                    // board[X][Y] == 1 means it was originally alive and is still alive.
                    // board[X][Y] == 3 means it was originally alive but is transitioning to dead (state 3 is a temporary marker).
                    // We count both as live neighbors for the current cell's calculation.
                    if(board[X][Y] == 1 || board[X][Y] == 3) count++;
                }
                
                // Apply the Game of Life rules based on the current cell's state and neighbor count.
                // Rule 1 & 3: A live cell with 2 or 3 live neighbors survives.
                // We mark it with '3' (was live, will be live) to distinguish from cells that were dead and become live.
                if(board[i][j]==1 && (count==2 || count==3)) board[i][j] = 3; 
                // Rule 4: A dead cell with exactly 3 live neighbors becomes a live cell.
                // We mark it with '2' (was dead, will be live).
                else if(count == 3) board[i][j] = 2; 
                // Cells that don't meet these conditions will implicitly become 0 in the second pass.
            } 
        }
        
        // Second pass: Update the board to the final states based on the temporary markers.
        for(int i = 0 ; i<m;i++) // Loop through each row.
            for(int j = 0 ;j<n;j++){ // Loop through each column.
                // If the cell was marked as '2' (dead -> live) or '3' (live -> live), it becomes live (1).
                if(board[i][j]==2 || board[i][j]==3) board[i][j]=1; 
                // Otherwise, it becomes dead (0). This handles cells that were originally 0 and stayed 0,
                // or cells that were originally 1 but died due to insufficient/excessive neighbors.
                else board[i][j] = 0;
            }
    }
}
```

## Interview Tips
*   **Explain the in-place constraint:** Clearly articulate why a direct update is problematic and how you plan to handle it (e.g., using temporary states).
*   **Walk through an example:** Use a small 2x2 or 3x3 board to demonstrate how your neighbor counting and state updates work step-by-step.
*   **Discuss the temporary states:** Explain the meaning of states `2` and `3` and why they are necessary for the two-pass approach.
*   **Consider edge cases:** Mention how you handle cells on the borders and corners of the grid.

## Revision Checklist
- [ ] Understand the 4 rules of Conway's Game of Life.
- [ ] Recognize the need for a two-pass approach or intermediate states.
- [ ] Implement neighbor counting correctly, including boundary checks.
- [ ] Use temporary states (like 2 and 3) to represent transitions.
- [ ] Correctly convert temporary states back to final states in the second pass.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Set Matrix Zeroes
*   Island Perimeter
*   Number of Islands
*   Surrounded Regions

## Tags
`Array` `Matrix` `Simulation`
