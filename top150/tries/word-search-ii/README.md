# Word Search Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Backtracking` `Trie` `Matrix`  
**Time:** O(M * N * 4^L + W * L)  
**Space:** O(W * L)

---

## Solution (java)

```java
class Solution {

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    public void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];

        // stop if visited or no matching child
        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];

        // found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicates
        }

        // mark visited
        board[i][j] = '#';

        int[] dir = {-1, 0, 1, 0, -1};
        for (int d = 0; d < 4; d++) {
            int ni = i + dir[d];
            int nj = j + dir[d + 1];

            if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length) {
                dfs(board, ni, nj, node, result);
            }
        }

        // backtrack
        board[i][j] = c;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int idx = c - 'a';

                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                node = node.children[idx];
            }

            node.word = word;
        }

        return root;
    }
}
```

---

---
## Quick Revision
Given a 2D board and a list of words, find all words that exist in the board.
This problem is solved using a Trie and Depth First Search (DFS) with backtracking.

## Intuition
The naive approach of checking each word against the board individually would be too slow, especially with many words and a large board. We need a way to efficiently search for multiple words simultaneously. A Trie is perfect for this because it allows us to group words by their prefixes. By building a Trie of all the words, we can traverse the board and, at each cell, explore paths that match prefixes in the Trie. If a path leads to a complete word in the Trie, we've found a match. DFS with backtracking is then used to explore all possible paths on the board.

## Algorithm
1.  **Build a Trie:** Create a Trie data structure and insert all the words from the input `words` array into it. Each node in the Trie will represent a character, and a node will store the complete word if it marks the end of a word.
2.  **Initialize Results and Board Dimensions:** Create an empty list to store the found words. Get the dimensions (rows `m` and columns `n`) of the `board`.
3.  **Iterate Through Board:** Iterate through each cell `(i, j)` of the `board`.
4.  **Start DFS from Each Cell:** For each cell `(i, j)`, initiate a Depth First Search (DFS) traversal. The DFS function will take the current cell coordinates `(i, j)`, the current Trie node, and the results list as parameters.
5.  **DFS Traversal:**
    *   **Base Cases/Pruning:**
        *   If the current cell `(i, j)` has already been visited (marked with '#'), return.
        *   If the current character `board[i][j]` does not have a corresponding child in the current Trie node, return.
    *   **Move to Next Trie Node:** Update the current Trie node to its child corresponding to `board[i][j]`.
    *   **Word Found:** If the current Trie node marks the end of a word (`node.word != null`), add this word to the `result` list. To prevent duplicates and further redundant searches for the same word, set `node.word = null`.
    *   **Mark as Visited:** Temporarily mark the current cell `board[i][j]` as visited by changing its character to '#'.
    *   **Explore Neighbors:** Recursively call DFS for all four adjacent cells (up, down, left, right). Ensure these neighbors are within the board boundaries.
    *   **Backtrack:** After exploring all neighbors from the current cell, restore the original character of `board[i][j]` to backtrack, allowing other paths to use this cell.
6.  **Return Results:** After iterating through all cells and completing all DFS traversals, return the `result` list containing all found words.

## Concept to Remember
*   **Trie (Prefix Tree):** Efficiently stores a set of strings and allows for fast prefix searching. Crucial for checking multiple words simultaneously.
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking. Ideal for exploring paths on the board.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to a computational problem, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **In-place Modification for Visited Tracking:** Using a special character ('#') to mark visited cells directly on the board avoids the need for a separate `visited` 2D array, saving space.

## Common Mistakes
*   **Not using a Trie:** Trying to search each word individually leads to TLE (Time Limit Exceeded).
*   **Incorrect Trie Implementation:** Errors in building the Trie or traversing it can lead to missed words or incorrect results.
*   **Missing Backtracking:** Failing to restore the board's original characters after visiting a cell prevents other valid paths from being explored.
*   **Duplicate Word Handling:** Not nullifying `node.word` after finding a word can lead to adding the same word multiple times to the result list.
*   **Boundary Checks:** Forgetting to check if neighboring cells are within the board's dimensions during DFS.

## Complexity Analysis
*   **Time:** O(M * N * 4^L + W * L), where M is the number of rows, N is the number of columns, L is the maximum length of a word, and W is the number of words.
    *   Building the Trie: O(W * L) - each character of each word is inserted.
    *   DFS traversal: In the worst case, each cell `(i, j)` can be the start of a DFS. From each cell, we can explore up to 4 directions. The depth of the recursion is limited by the maximum word length `L`. The `4^L` factor comes from the branching factor of the DFS. However, the Trie prunes many branches. A tighter bound considers that each cell is visited at most once per DFS path originating from it. The total number of DFS calls is bounded by M * N * 4^L in the worst case, but practically, it's much better due to Trie pruning. The total number of Trie node visits across all DFS calls is roughly proportional to the number of cells times the average depth of Trie traversal. A more accurate, though still complex, analysis often involves the total number of characters in the board and the Trie. For practical purposes, it's often considered O(M * N * 3^L) or O(M * N * 4^L) in the worst-case theoretical sense, but the Trie significantly optimizes it. The `W*L` is for Trie construction.
*   **Space:** O(W * L) for the Trie. In the worst case, the Trie can store all characters of all words. The recursion depth of DFS can go up to `L`, contributing O(L) to the call stack space.

## Commented Code
```java
class Solution {

    // Main function to find words on the board.
    public List<String> findWords(char[][] board, String[] words) {
        // Build a Trie from the given list of words. This allows for efficient prefix matching.
        TrieNode root = buildTrie(words);
        // Initialize a list to store the words found on the board.
        List<String> result = new ArrayList<>();

        // Get the dimensions of the board.
        int m = board.length;
        int n = board[0].length;

        // Iterate through each cell of the board.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Start a Depth First Search (DFS) from each cell.
                // The DFS will explore paths on the board and match them against the Trie.
                dfs(board, i, j, root, result);
            }
        }

        // Return the list of words found.
        return result;
    }

    // Depth First Search function to explore paths on the board.
    public void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        // Get the character at the current cell.
        char c = board[i][j];

        // Pruning conditions:
        // 1. If the cell has already been visited (marked with '#'), stop exploring this path.
        // 2. If the current character 'c' does not have a corresponding child in the current Trie node,
        //    it means no word in our dictionary starts with this prefix, so stop.
        if (c == '#' || node.children[c - 'a'] == null) return;

        // Move to the next Trie node that corresponds to the current character.
        node = node.children[c - 'a'];

        // Check if the current Trie node marks the end of a valid word.
        if (node.word != null) {
            // If a word is found, add it to the result list.
            result.add(node.word);
            // Set node.word to null to avoid adding the same word multiple times if it can be formed via different paths.
            // This also helps in pruning further searches for this specific word.
            node.word = null;
        }

        // Mark the current cell as visited by changing its character to '#'.
        // This prevents cycles and re-visiting the same cell in the current DFS path.
        board[i][j] = '#';

        // Define directions for exploring adjacent cells (up, down, left, right).
        int[] dir = {-1, 0, 1, 0, -1}; // Corresponds to {row_offset, col_offset} pairs: (-1,0), (0,1), (1,0), (0,-1)
        // Iterate through the four possible directions.
        for (int d = 0; d < 4; d++) {
            // Calculate the coordinates of the next cell.
            int ni = i + dir[d];
            int nj = j + dir[d + 1];

            // Check if the next cell is within the board boundaries.
            if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length) {
                // Recursively call DFS for the adjacent cell.
                // Pass the updated Trie node to continue matching the prefix.
                dfs(board, ni, nj, node, result);
            }
        }

        // Backtrack: Restore the original character of the current cell.
        // This is crucial to allow other DFS paths to use this cell.
        board[i][j] = c;
    }

    // TrieNode class to represent a node in the Trie.
    class TrieNode {
        // Array of child nodes, indexed by character ('a' to 'z').
        TrieNode[] children = new TrieNode[26];
        // Stores the complete word if this node marks the end of a word. Otherwise, it's null.
        String word = null;
    }

    // Function to build the Trie from a list of words.
    public TrieNode buildTrie(String[] words) {
        // Create the root node of the Trie.
        TrieNode root = new TrieNode();

        // Iterate through each word to insert it into the Trie.
        for (String word : words) {
            // Start from the root for each new word.
            TrieNode node = root;

            // Iterate through each character of the word.
            for (char c : word.toCharArray()) {
                // Calculate the index for the character (0 for 'a', 1 for 'b', etc.).
                int idx = c - 'a';

                // If the child node for this character doesn't exist, create it.
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                // Move to the child node.
                node = node.children[idx];
            }

            // After processing all characters of the word, mark the current node as the end of this word.
            node.word = word;
        }

        // Return the root of the constructed Trie.
        return root;
    }
}
```

## Interview Tips
*   **Explain the Trie-DFS synergy:** Clearly articulate why a Trie is necessary for efficiency and how DFS explores the board in conjunction with the Trie.
*   **Discuss backtracking:** Emphasize the importance of the backtracking step (restoring the board character) and why it's essential for correctness.
*   **Handle edge cases:** Be prepared to discuss what happens with an empty board, an empty word list, or words that are prefixes of other words.
*   **Complexity justification:** Be ready to explain the time and space complexity, especially the factors contributing to the time complexity and how the Trie optimizes it.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement a Trie data structure correctly.
- [ ] Implement the `buildTrie` function.
- [ ] Implement the DFS traversal with boundary checks.
- [ ] Implement the visited marking and backtracking mechanism.
- [ ] Handle word found condition and duplicate prevention.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Word Search (LeetCode 79)
*   Trie (LeetCode 208)
*   Concatenated Words (LeetCode 472)
*   Word Break II (LeetCode 140)

## Tags
`Array` `Hash Map` `Depth-First Search` `Trie` `Backtracking`

## My Notes
reverse approach
add all words to tries
