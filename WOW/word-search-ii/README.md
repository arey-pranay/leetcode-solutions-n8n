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
            func(root,i,j,board);
        }
      }
      return ans;
    }

    public void func(Node curr, int x, int y,char[][] board){
      char c = board[x][y];
      if(vis[x][y] || curr.children[c-'a'] == null) return;
      curr = curr.children[c-'a'];
      if(curr.word != null){ans.add(curr.word); curr.word=null;}
      vis[x][y] = true;
      for(int i=0;i<4;i++){
        int X = x + neighs[i];
        int Y = y + neighs[i+1];
        if(X<0 || Y<0 || X>=m || Y>=n || vis[X][Y]) continue;
        func(curr, X,Y, board);
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
Given a 2D board of characters and a list of words, find all words that exist in the board.
This problem is solved using a Trie to store the dictionary and Depth First Search (DFS) on the board.

## Intuition
The naive approach of checking each word individually against the board using DFS would be too slow, especially with many words. We need a way to efficiently search for multiple words simultaneously. A Trie is perfect for this. By building a Trie of all the words, we can traverse the board and the Trie in parallel. When we move to a new cell on the board, we check if the character corresponds to a child node in the current Trie node. If it does, we move to that child node and continue the search. This way, we prune search paths that don't match any prefix of the dictionary words.

## Algorithm
1.  **Build a Trie:** Insert all words from the given `words` list into a Trie data structure. Each node in the Trie will represent a character, and a node will store the complete word if it marks the end of a word.
2.  **Initialize Data Structures:**
    *   Create a `List<String>` to store the found words.
    *   Initialize a `boolean[][] visited` array of the same dimensions as the `board` to keep track of visited cells during DFS.
    *   Store the dimensions of the board (`m` and `n`).
3.  **Iterate Through Board:** Iterate through each cell `(i, j)` of the `board`.
4.  **Start DFS:** For each cell `(i, j)`, initiate a Depth First Search (DFS) from that cell. The DFS function will take the current Trie node, the current cell coordinates `(x, y)`, and the `board` as parameters.
5.  **DFS Function (`func`):**
    *   **Base Cases/Pruning:**
        *   If the current cell `(x, y)` is out of bounds, already visited, or if the character `board[x][y]` does not correspond to a child of the current Trie node, return.
    *   **Move in Trie:** Get the character `c` at `board[x][y]`. Find the corresponding child node in the Trie. If no such child exists, return. Update the current Trie node to this child.
    *   **Word Found:** If the current Trie node marks the end of a word (i.e., `curr.word != null`), add this word to the result list. To avoid duplicates and redundant searches, set `curr.word = null` after adding it.
    *   **Mark Visited:** Mark the current cell `(x, y)` as visited.
    *   **Explore Neighbors:** Recursively call the DFS function for all four adjacent cells (up, down, left, right).
    *   **Backtrack:** After exploring all neighbors, unmark the current cell `(x, y)` as visited (backtracking). This is crucial to allow other paths to use this cell.

## Concept to Remember
*   **Trie (Prefix Tree):** Efficiently stores and searches for strings based on prefixes. Useful for problems involving dictionaries or pattern matching.
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking. Essential for pathfinding and exploring connected components.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Optimization:** Using a Trie significantly optimizes the search by pruning invalid paths early, avoiding redundant checks for words that don't share prefixes.

## Common Mistakes
*   **Not using a Trie:** Attempting to search each word individually from scratch on the board will lead to a Time Limit Exceeded (TLE) error.
*   **Incorrect Backtracking:** Forgetting to unmark cells as visited after exploring them in DFS will prevent finding words that might reuse cells in different paths.
*   **Duplicate Words:** Not handling the case where a word might be found multiple times or not clearing the `word` field in the Trie node after finding it can lead to duplicate entries in the result.
*   **Off-by-one Errors:** Incorrectly handling boundary conditions for the board dimensions or neighbor calculations in DFS.
*   **Trie Node Structure:** Not properly initializing `children` arrays or `word` fields in the Trie nodes.

## Complexity Analysis
*   **Time:** O(M * N * 4^L + W * L), where M is the number of rows, N is the number of columns, L is the maximum length of a word, and W is the number of words.
    *   Building the Trie: O(W * L) where W is the number of words and L is the average length of a word.
    *   DFS traversal: In the worst case, for each cell (M*N), we might explore paths up to the length of the longest word (L). The branching factor is 4. However, the Trie prunes many paths. A tighter bound considers that each DFS path is limited by the Trie structure. Each cell can be visited at most once per DFS path initiated from a starting cell. The total number of DFS calls is bounded by M * N * 4^L in a naive DFS, but with the Trie, it's more like M * N * (average path length in Trie). The `4^L` part is a loose upper bound for DFS on a grid without pruning. The Trie effectively limits the depth and branching. A more accurate analysis is that each cell `(i, j)` is visited at most once for each path in the Trie. The total number of nodes in the Trie is at most `W * L`. The DFS explores paths on the board. The total number of states explored by DFS is roughly `M * N * (number of Trie nodes)`. However, a common way to express this is `M * N * 4^L` as a worst-case grid traversal, but the Trie significantly reduces this. The `W*L` is for Trie construction.
*   **Space:** O(M * N + W * L), where M is the number of rows, N is the number of columns, and W is the number of words, L is the average length of a word.
    *   Trie storage: O(W * L) in the worst case, where each character of each word forms a unique path.
    *   Visited array: O(M * N) for the `boolean[][] vis` array.
    *   Recursion stack: O(L) in the worst case for the DFS recursion depth.

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

    // Root of the Trie
    Node root = new Node();

    // Method to add a word to the Trie
    public void addToTrie(String word) {
        Node curr = root; // Start from the root of the Trie
        // Iterate through each character of the word
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the character (0 for 'a', 1 for 'b', etc.)
            // If the child node for this character doesn't exist, create it
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            // Move to the child node
            curr = curr.children[index];
        }
        // Once all characters are processed, mark the current node as the end of the word
        curr.word = word;
    }

    // List to store the found words
    List<String> ans = new ArrayList<>();
    // Array to define the 4 possible neighbor movements (up, down, left, right)
    // neighs[0] = -1, neighs[1] = 0  => (x-1, y) Up
    // neighs[1] = 0,  neighs[2] = 1  => (x, y+1) Right
    // neighs[2] = 1,  neighs[3] = 0  => (x+1, y) Down
    // neighs[3] = 0,  neighs[4] = -1 => (x, y-1) Left
    int[] neighs = new int[]{-1, 0, 1, 0, -1};
    // 2D boolean array to keep track of visited cells during DFS
    boolean[][] vis;
    // Dimensions of the board
    int m; // number of rows
    int n; // number of columns

    // Main function to find words in the board
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length; // Get the number of rows
        n = board[0].length; // Get the number of columns

        // 1. Build the Trie with all the words
        for (String word : words) {
            addToTrie(word);
        }

        // 2. Iterate through each cell of the board to start DFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Initialize the visited array for each starting cell.
                // This is incorrect. The visited array should be initialized ONCE before the loops,
                // and then managed by the DFS function for backtracking.
                // The provided solution re-initializes `vis` for every `(i, j)` which is wrong.
                // A correct implementation would initialize `vis` once before the loops.
                // However, adhering to the provided code structure for commenting:
                vis = new boolean[m][n]; // Re-initializing `vis` here is a bug.
                // Start the DFS from the current cell (i, j) using the root of the Trie
                func(root, i, j, board);
            }
        }
        // Return the list of found words
        return ans;
    }

    // Recursive DFS function to search for words on the board
    public void func(Node curr, int x, int y, char[][] board) {
        char c = board[x][y]; // Get the character at the current cell
        int index = c - 'a';   // Calculate the index for the character

        // Pruning conditions:
        // 1. If the cell is already visited in the current path.
        // 2. If the current Trie node does not have a child for the current character.
        if (vis[x][y] || curr.children[index] == null) {
            return; // Stop exploring this path
        }

        // Move to the next node in the Trie corresponding to the current character
        curr = curr.children[index];

        // Check if the current Trie node marks the end of a word
        if (curr.word != null) {
            ans.add(curr.word); // Add the found word to the result list
            // Important: Set curr.word to null to avoid adding the same word multiple times
            // and to prune further searches for this word if it's a prefix of another.
            curr.word = null;
        }

        // Mark the current cell as visited for the current DFS path
        vis[x][y] = true;

        // Explore all 4 neighbors
        for (int i = 0; i < 4; i++) {
            int X = x + neighs[i];     // Calculate neighbor's row coordinate
            int Y = y + neighs[i + 1]; // Calculate neighbor's column coordinate

            // Check if the neighbor is within board bounds and not visited
            if (X < 0 || Y < 0 || X >= m || Y >= n || vis[X][Y]) {
                continue; // Skip this neighbor if it's invalid or already visited
            }
            // Recursively call DFS for the neighbor
            func(curr, X, Y, board);
        }

        // Backtrack: Unmark the current cell as visited so it can be used in other paths
        vis[x][y] = false;
    }
}
```
*Self-correction on the commented code:* The original code has a bug where `vis` is re-initialized inside the `findWords` loops. This means each DFS starting from `(i, j)` would have a fresh `vis` array, effectively allowing cells to be reused across different starting points, which is not the intended behavior for a single word search. For Word Search II, the `vis` array should be managed by the `func` for backtracking within a single DFS traversal originating from a specific `(i, j)`. The `vis` array should be initialized *once* before the outer loops in `findWords`. However, to comment the provided code as is, I've noted this bug. A correct implementation would initialize `vis` once.

## Interview Tips
1.  **Explain the Trie First:** Before diving into the DFS, clearly explain why a Trie is necessary and how it optimizes the search compared to a naive approach.
2.  **Walk Through DFS with Backtracking:** Emphasize the importance of marking cells as visited and, crucially, unmarking them (backtracking) to allow exploration of all valid paths.
3.  **Handle Edge Cases and Duplicates:** Discuss how the `curr.word = null` step prevents duplicate word additions and how boundary checks are handled.
4.  **Complexity Discussion:** Be prepared to discuss both time and space complexity, explaining the contributions of the Trie and the DFS. Mention the trade-offs.
5.  **Bug Identification:** If asked to review code or if you notice the `vis` re-initialization bug, point it out and explain why it's an issue and how to fix it (initialize `vis` once before the loops).

## Revision Checklist
- [ ] Understand the problem: Find all words from a dictionary in a 2D grid.
- [ ] Recognize the need for optimization: Naive search is too slow.
- [ ] Implement Trie: Correctly build and traverse the Trie.
- [ ] Implement DFS: Correctly explore neighbors and handle board boundaries.
- [ ] Implement Backtracking: Ensure `visited` array is managed correctly.
- [ ] Handle Duplicates: Prevent adding the same word multiple times.
- [ ] Analyze Complexity: Time and Space.
- [ ] Consider Edge Cases: Empty board, empty words list, single-character words.

## Similar Problems
*   Word Search (LeetCode 79)
*   Trie (LeetCode 208)
*   Concatenated Words (LeetCode 472)
*   Word Break II (LeetCode 140)

## Tags
`Array` `Hash Map` `Trie` `Depth-First Search` `Backtracking` `Matrix`
