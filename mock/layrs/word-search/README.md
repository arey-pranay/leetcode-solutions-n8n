# Word Search

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `String` `Backtracking` `Depth-First Search` `Matrix`  
**Time:** O(m*n*w)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] vis = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!vis[i][j] && board[i][j]==word.charAt(0)){
                    vis[i][j] = true;
                    if(func(board,word,1,vis,i,j)) return true;
                    vis[i][j] = false;
                }
            }
        }
        return false;
    }
    public boolean func(char[][] board, String word, int currI, boolean[][] vis, int x, int y){
        if(currI==word.length()) return true;
        int[] neighs = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X==board.length || Y==board[0].length) continue;
            if(!vis[X][Y] && board[X][Y]==word.charAt(currI)){
                vis[X][Y] = true;
                if(func(board,word,currI+1,vis,X,Y)) return true;
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
Search for a word in a 2D grid of characters.

Solve it by using a backtracking algorithm to explore all possible paths from each cell that matches the first character of the word.

## Intuition
The idea is to start at each cell that matches the first character of the word and recursively explore its neighbors. If we find a match for the next character in the word, we move on to the next step. If not, we backtrack to the previous step.

## Algorithm

1. Initialize a boolean array `vis` to keep track of visited cells.
2. Iterate over each cell in the grid and check if it matches the first character of the word.
3. If it does, mark the cell as visited and call the helper function `func` to explore its neighbors.
4. In `func`, iterate over all four directions (up, down, left, right) from the current cell.
5. For each direction, check if the neighboring cell is within bounds and has not been visited before.
6. If both conditions are true, mark the neighboring cell as visited and recursively call `func` with the updated coordinates.
7. If we find a match for the next character in the word, move on to the next step.
8. If we reach the end of the word (i.e., `currI == word.length()`), return true.
9. If none of the above conditions are met, backtrack by marking the current cell as unvisited and returning false.

## Concept to Remember

* Backtracking algorithm
* Recursive function calls
* Iterating over all possible paths in a grid

## Common Mistakes

* Not initializing the `vis` array properly
* Not checking if the neighboring cell is within bounds
* Not marking cells as visited before recursive calls
* Getting stuck in an infinite loop due to unvisited cells

## Complexity Analysis
- Time: O(m*n*w) - m*n is the size of the grid, w is the length of the word.
  - This is because we are exploring all possible paths from each cell that matches the first character of the word.

- Space: O(m*n) - this is for storing the `vis` array to keep track of visited cells.

## Commented Code
```java
public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    
    // Initialize a boolean array to keep track of visited cells.
    boolean[][] vis = new boolean[m][n];
    
    // Iterate over each cell in the grid and check if it matches the first character of the word.
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (!vis[i][j] && board[i][j] == word.charAt(0)) {
                // Mark the cell as visited and call the helper function to explore its neighbors.
                vis[i][j] = true;
                if (func(board, word, 1, vis, i, j)) return true; // Found a match for the next character in the word.
                vis[i][j] = false; // Backtrack by marking the cell as unvisited.
            }
        }
    }
    
    // If we reach this point, it means that we did not find any matches for the word.
    return false;
}

public boolean func(char[][] board, String word, int currI, boolean[][] vis, int x, int y) {
    if (currI == word.length()) return true; // We found a match for all characters in the word.

    // Iterate over all four directions (up, down, left, right) from the current cell.
    int[] neighs = new int[]{-1, 0, 1, 0, -1};
    for (int i = 0; i < 4; i++) {
        int X = x + neighs[i];
        int Y = y + neighs[i+1];

        // Check if the neighboring cell is within bounds and has not been visited before.
        if (X < 0 || Y < 0 || X == board.length || Y == board[0].length) continue;
        if (!vis[X][Y] && board[X][Y] == word.charAt(currI)) {
            // Mark the neighboring cell as visited and recursively call func with the updated coordinates.
            vis[X][Y] = true;
            if (func(board, word, currI+1, vis, X, Y)) return true; // Found a match for the next character in the word.
            vis[X][Y] = false; // Backtrack by marking the cell as unvisited.
        }
    }

    // If we reach this point, it means that we did not find any matches for the word starting from the current cell.
    return false;
}
```

## Interview Tips

* Make sure to initialize the `vis` array properly and check if neighboring cells are within bounds before recursive calls.
* Practice backtracking algorithms and recursive function calls to improve problem-solving skills.
* Pay attention to edge cases, such as empty grids or words with no matches.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Initialize the `vis` array properly.
- [ ] Check if neighboring cells are within bounds before recursive calls.
- [ ] Practice backtracking algorithms and recursive function calls.

## Similar Problems

* LeetCode: 79. Word Search (similar problem with slight modifications)
* LeetCode: 127. Word Ladder (find a sequence of words that forms a ladder from start to end)

## Tags
`Array`, `Hash Map`, `Backtracking`, `Recursive Functions`, `Word Search`
