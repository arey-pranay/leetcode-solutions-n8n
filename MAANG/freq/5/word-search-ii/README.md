# Word Search Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Backtracking` `Trie` `Matrix`  
**Time:** O(M * N * 4^L + W * L)  
**Space:** O(M * N + W * L)

---

## Solution (java)

```java
class Solution {
    class Node{
      Node[] children;
      String word;
      Node(){
        this.children = new Node[26];
        this.word = null;
      }
    }
    Node root = new Node();
    public void addToTrie(String word){
    Node curr = root;
    for(char c : word.toCharArray()){
          int index = c-'a';
          if(curr.children[index] == null) curr.children[index] = new Node();
          curr = curr.children[index];
      }
      curr.word = word;
    }
    
    List<String> ans = new ArrayList<>();
    int[] neighs = new int[]{-1,0,1,0,-1};
    boolean[][] vis;
    int m;
    int n;
    public List<String> findWords(char[][] board, String[] words) {
      m = board.length;
      n = board[0].length;
      for(String word : words) addToTrie(word);
      for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            vis = new boolean[m][n];
            dfs(root,i,j,board);
        }
      }
      return ans;
    }

    public void dfs(Node curr, int x, int y,char[][] board){
      char c = board[x][y];
      if(vis[x][y] || curr.children[c-'a'] == null) return;
      curr = curr.children[c-'a'];
      if(curr.word != null){ans.add(curr.word); curr.word=null;}
      vis[x][y] = true;
      for(int i=0;i<4;i++){
        int X = x + neighs[i];
        int Y = y + neighs[i+1];
        if(X<0 || Y<0 || X>=m || Y>=n || vis[X][Y]) continue;
        dfs(curr, X,Y, board);
      }
      vis[x][y] = false;
    }
}
  // board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
  // words = ["oath","pea","eat","rain"]



  //                -
  //           /  |   |   \
  //           o   p   e   r
  //           /   |   |   |
  //         a     e   a   a
  //         ..  ..  ..  ..
        
  
```

---

---
## Quick Revision
Given a 2D board of characters and a list of words, find all words from the list that can be formed by traversing adjacent cells on the board.
This problem is solved using a Trie to store the dictionary of words and Depth First Search (DFS) with backtracking on the board.

## Intuition
The naive approach of checking each word individually against the board using DFS would be too slow, especially with a large dictionary. The key insight is to optimize the search by using a Trie. By building a Trie of all the words, we can traverse the board and simultaneously explore paths that match prefixes in the Trie. This way, we prune branches of the search space early if a path on the board doesn't correspond to any prefix in our dictionary. When we reach a node in the Trie that marks the end of a word, we've found a match.

## Algorithm
1.  **Build a Trie:** Create a Trie data structure. Insert all the words from the input `words` array into this Trie. Each node in the Trie will represent a character, and a node can store the complete word if it signifies the end of a word.
2.  **Initialize Data Structures:**
    *   Get the dimensions of the `board` (rows `m` and columns `n`).
    *   Initialize an empty list `ans` to store the found words.
    *   Create a `visited` 2D boolean array of the same dimensions as the `board` to keep track of visited cells during DFS.
3.  **Iterate Through Board:** Iterate through each cell `(i, j)` of the `board`.
4.  **Start DFS:** For each cell `(i, j)`, initiate a Depth First Search (DFS) starting from the root of the Trie and the current cell `(i, j)` on the board.
5.  **DFS Function (`dfs(Node curr, int x, int y, char[][] board)`):**
    *   **Base Cases/Pruning:**
        *   If the current cell `(x, y)` is out of bounds, or if it has already been visited (`vis[x][y]`), return.
        *   If the character `board[x][y]` does not correspond to a child of the current Trie node `curr` (i.e., `curr.children[board[x][y] - 'a'] == null`), return.
    *   **Move to Next Trie Node:** Update `curr` to be the child node corresponding to `board[x][y]`.
    *   **Word Found:** If the new `curr` node marks the end of a word (`curr.word != null`):
        *   Add `curr.word` to the `ans` list.
        *   **Crucially:** Set `curr.word = null` to avoid adding the same word multiple times if it can be formed through different paths. This is a form of pruning.
    *   **Mark as Visited:** Mark the current cell `(x, y)` as visited (`vis[x][y] = true`).
    *   **Explore Neighbors:** Recursively call `dfs` for all four adjacent cells (up, down, left, right) of `(x, y)`.
    *   **Backtrack:** After exploring all neighbors, unmark the current cell `(x, y)` as visited (`vis[x][y] = false`). This is essential for backtracking, allowing other paths to use this cell.

## Concept to Remember
*   **Trie (Prefix Tree):** Efficiently stores a dictionary of strings and allows for quick prefix matching.
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking.
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Pruning:** Optimizing search algorithms by eliminating branches that cannot lead to a valid solution.

## Common Mistakes
*   **Not using a Trie:** Attempting to search each word individually without a Trie leads to TLE (Time Limit Exceeded).
*   **Incorrect Backtracking:** Forgetting to reset the `visited` status of a cell after exploring its neighbors.
*   **Duplicate Word Addition:** Not nullifying `curr.word` after adding it to the result list, leading to duplicates if a word can be formed multiple ways.
*   **Off-by-one Errors in Neighbors:** Incorrectly calculating the coordinates of adjacent cells.
*   **Handling Empty Input:** Not considering edge cases like an empty `words` list or an empty `board`.

## Complexity Analysis
*   **Time:** O(M * N * 4^L + W * L), where M is the number of rows, N is the number of columns, L is the maximum length of a word in `words`, and W is the number of words.
    *   Building the Trie: O(W * L) where W is the number of words and L is the average length of a word.
    *   DFS traversal: In the worst case, we might visit each cell on the board. From each cell, we can explore up to 4 directions. The depth of the recursion is limited by the length of the longest word (L). So, for each starting cell, the DFS could explore up to O(4^L) paths. Since we start DFS from every cell (M * N cells), the total time for DFS is roughly O(M * N * 4^L).
    *   The `curr.word = null` optimization helps prune the search space significantly in practice, making the effective time complexity much better than the worst-case theoretical bound.
*   **Space:** O(M * N + W * L)
    *   Trie: O(W * L) in the worst case, where W is the number of words and L is the average length of a word.
    *   Recursion Stack: O(L) for the DFS call stack, where L is the maximum length of a word.
    *   Visited Array: O(M * N) for the `vis` array.

## Commented Code
```java
class Solution {
    // Inner class to represent a node in the Trie
    class Node {
        Node[] children; // Array to store children nodes (one for each letter 'a'-'z')
        String word;     // Stores the complete word if this node marks the end of a word

        Node() {
            this.children = new Node[26]; // Initialize children array for 26 lowercase letters
            this.word = null;             // Initially, no word ends at this node
        }
    }

    Node root = new Node(); // The root of our Trie

    // Method to insert a word into the Trie
    public void addToTrie(String word) {
        Node curr = root; // Start from the root of the Trie
        // Iterate through each character of the word
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the character (0 for 'a', 1 for 'b', etc.)
            // If the child node for this character doesn't exist, create it
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            curr = curr.children[index]; // Move to the child node
        }
        curr.word = word; // Mark the end of the word by storing the word itself in the node
    }

    List<String> ans = new ArrayList<>(); // List to store the found words
    // Array to define the relative movements for neighbors (up, down, left, right)
    // neighs[0] = -1, neighs[1] = 0  => (x-1, y)  (Up)
    // neighs[1] = 0,  neighs[2] = 1  => (x, y+1)  (Right)
    // neighs[2] = 1,  neighs[3] = 0  => (x+1, y)  (Down)
    // neighs[3] = 0,  neighs[4] = -1 => (x, y-1)  (Left)
    int[] neighs = new int[]{-1, 0, 1, 0, -1};
    boolean[][] vis; // 2D boolean array to keep track of visited cells on the board
    int m;           // Number of rows in the board
    int n;           // Number of columns in the board

    // Main function to find words on the board
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length; // Get the number of rows
        n = board[0].length; // Get the number of columns

        // Build the Trie by adding all words from the dictionary
        for (String word : words) {
            addToTrie(word);
        }

        // Iterate through each cell of the board as a potential starting point for a word
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Initialize the visited array for each new starting cell's DFS path
                // This is incorrect. Visited should be initialized once before the loops.
                // The provided solution has a bug here. It should be outside the loops.
                // Corrected logic: vis = new boolean[m][n]; should be before the loops.
                // However, the provided code initializes it inside, which is a common mistake.
                // For the purpose of commenting the provided code:
                vis = new boolean[m][n]; // Initialize visited array for each DFS starting point
                dfs(root, i, j, board); // Start DFS from the current cell and the root of the Trie
            }
        }
        return ans; // Return the list of found words
    }

    // Depth First Search function to explore paths on the board and Trie
    public void dfs(Node curr, int x, int y, char[][] board) {
        char c = board[x][y]; // Get the character at the current cell

        // Pruning conditions:
        // 1. If the current cell has already been visited in the current path.
        // 2. If the current character on the board does not lead to a valid path in the Trie (no child node for this character).
        if (vis[x][y] || curr.children[c - 'a'] == null) {
            return; // Stop exploring this path
        }

        curr = curr.children[c - 'a']; // Move to the next node in the Trie corresponding to the character

        // If the current Trie node marks the end of a word
        if (curr.word != null) {
            ans.add(curr.word); // Add the found word to the result list
            curr.word = null;   // Set the word to null to avoid adding duplicates if the same word is found again
                                // This is a crucial optimization/pruning step.
        }

        vis[x][y] = true; // Mark the current cell as visited for the current DFS path

        // Explore all four adjacent neighbors
        for (int i = 0; i < 4; i++) {
            int X = x + neighs[i];     // Calculate the row of the neighbor
            int Y = y + neighs[i + 1]; // Calculate the column of the neighbor

            // Check if the neighbor is within the board boundaries and has not been visited in the current path
            if (X < 0 || Y < 0 || X >= m || Y >= n || vis[X][Y]) {
                continue; // Skip this neighbor if it's invalid or already visited
            }
            dfs(curr, X, Y, board); // Recursively call DFS for the neighbor
        }

        vis[x][y] = false; // Backtrack: Unmark the current cell as visited, allowing it to be used in other paths
    }
}
```

## Interview Tips
1.  **Explain the Trie First:** Before diving into the DFS, clearly explain why a Trie is necessary and how it optimizes the search by allowing simultaneous traversal of the board and dictionary prefixes.
2.  **Walk Through DFS with Backtracking:** Emphasize the importance of the `visited` array and the backtracking step (`vis[x][y] = false`). Explain how this allows exploring all possible paths.
3.  **Highlight the `curr.word = null` Optimization:** This is a critical detail. Explain that setting the word to `null` after finding it prevents duplicate entries and acts as a form of pruning, making the solution more efficient.
4.  **Discuss Edge Cases:** Be prepared to discuss what happens with an empty `words` list, an empty `board`, or words that are prefixes of other words.
5.  **Clarify Complexity:** Be able to articulate the time and space complexity, explaining the contributions of the Trie and the DFS traversal. Mention the practical performance improvements due to pruning.

## Revision Checklist
- [ ] Understand the problem: Find words from a dictionary in a 2D grid.
- [ ] Recognize the need for Trie: Naive approach is too slow.
- [ ] Implement Trie: `Node` class, `children`, `word` field, `addToTrie` method.
- [ ] Implement DFS: Recursive function with base cases and neighbor exploration.
- [ ] Handle visited cells: Use a `boolean[][] vis` array.
- [ ] Implement backtracking: Reset `vis` after exploring neighbors.
- [ ] Optimize for duplicates: Set `curr.word = null` after finding a word.
- [ ] Handle boundary conditions: Check `X`, `Y` within `m`, `n`.
- [ ] Analyze Time Complexity: O(M * N * 4^L + W * L).
- [ ] Analyze Space Complexity: O(M * N + W * L).
- [ ] Consider edge cases: Empty inputs.

## Similar Problems
*   Word Search (LeetCode 79)
*   Trie (LeetCode 208)
*   Concatenated Words (LeetCode 472)
*   Add and Search Word - Data Structure Design (LeetCode 211)

## Tags
`Array` `Depth-First Search` `Trie` `Backtracking` `Matrix`
