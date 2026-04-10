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
Given a 2D board of characters and a list of words, find all words that exist in the board.
This problem is solved using a Trie data structure combined with Depth First Search (DFS) on the board.

## Intuition
The naive approach of checking each word against the board independently would be too slow, especially with many words and a large board. We need a way to efficiently search for multiple words simultaneously. A Trie is perfect for this because it allows us to group words by their prefixes. By building a Trie of all the words, we can then traverse the board using DFS. As we move through the board, we simultaneously traverse the Trie. If a path on the board corresponds to a path in the Trie that ends in a word, we've found a match. This avoids redundant checks and significantly speeds up the search.

## Algorithm
1.  **Build a Trie:** Create a Trie data structure and insert all the words from the input `words` array into it. Each node in the Trie will have an array of children (one for each letter 'a'-'z') and a field to store the complete word if the path to that node represents a valid word.
2.  **Initialize Results:** Create an empty list to store the found words.
3.  **Iterate Through Board:** Iterate through each cell `(i, j)` of the 2D `board`.
4.  **Start DFS:** For each cell, initiate a Depth First Search (DFS) from that cell. The DFS function will take the current board cell coordinates `(i, j)`, the current Trie node, and the results list as parameters.
5.  **DFS Traversal:**
    *   **Base Cases/Pruning:**
        *   If the current cell `(i, j)` is out of bounds, return.
        *   If the current cell has already been visited (marked with a special character like '#'), return.
        *   If the character `board[i][j]` does not have a corresponding child in the current Trie node, return.
    *   **Move in Trie:** Get the character `c` at `board[i][j]`. Move to the child Trie node corresponding to `c`.
    *   **Word Found:** If the current Trie node marks the end of a word (i.e., `node.word` is not null), add this word to the `result` list. To prevent adding the same word multiple times if it can be formed in different ways or if it's a prefix of another word, set `node.word = null` after adding it.
    *   **Mark Visited:** Mark the current cell `board[i][j]` as visited by changing its character to a special marker (e.g., '#'). This prevents cycles and re-visiting cells within the same path.
    *   **Explore Neighbors:** Recursively call DFS for all four adjacent cells (up, down, left, right). Pass the updated Trie node to the recursive calls.
    *   **Backtrack:** After exploring all neighbors, unmark the current cell `board[i][j]` by restoring its original character. This is crucial for exploring other potential paths that might use this cell.
6.  **Return Results:** After iterating through all cells and completing all DFS traversals, return the `result` list containing all found words.

## Concept to Remember
*   **Trie (Prefix Tree):** Efficiently stores and searches for strings based on prefixes. Ideal for problems involving multiple word lookups.
*   **Depth First Search (DFS):** A graph traversal algorithm that explores as far as possible along each branch before backtracking. Essential for exploring paths on the board.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to a computational problem, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Marking Visited Cells:** Crucial in grid-based DFS to avoid infinite loops and ensure each cell is used at most once per path.

## Common Mistakes
*   **Not using a Trie:** Attempting to search each word individually on the board leads to a very high time complexity.
*   **Incorrect Backtracking:** Forgetting to restore the original character of a cell after visiting it in DFS can lead to incorrect results, as other paths might be blocked.
*   **Handling Duplicates:** Not nullifying `node.word` after finding a word can lead to duplicate entries in the result list if a word can be formed multiple ways.
*   **Off-by-one Errors in DFS Bounds:** Incorrectly checking boundary conditions for `ni` and `nj` in the DFS can lead to index out of bounds exceptions or missed valid paths.
*   **Inefficient Trie Node Structure:** Using a `HashMap` for children in `TrieNode` can be slightly slower than a fixed-size array for lowercase English letters, though both are acceptable.

## Complexity Analysis
*   **Time:** O(M * N * 4^L + W * L), where M is the number of rows, N is the number of columns, L is the maximum length of a word, and W is the number of words.
    *   Building the Trie: O(W * L) - Each character of each word is inserted once.
    *   DFS traversal: In the worst case, for each cell (M*N), we might explore paths up to the maximum word length (L). The `4^L` comes from the branching factor of 4 at each step of the DFS. However, this is heavily pruned by the Trie. A tighter bound is often considered O(M * N * 3^L) because we don't go back to the immediate parent. The `4^L` is a loose upper bound. The actual performance is much better due to Trie pruning.
*   **Space:** O(W * L) for the Trie. In the worst case, the Trie can store all characters of all words. The recursion depth of DFS can be up to L, contributing O(L) to the call stack space.

## Commented Code
```java
class Solution {

    // Main function to find words on the board.
    public List<String> findWords(char[][] board, String[] words) {
        // Build a Trie from the given list of words.
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

    // Depth First Search function to explore paths on the board and match with Trie.
    public void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        // Get the character at the current cell.
        char c = board[i][j];

        // Pruning conditions:
        // 1. If the cell has already been visited (marked with '#').
        // 2. If the current Trie node does not have a child corresponding to the character 'c'.
        if (c == '#' || node.children[c - 'a'] == null) return;

        // Move to the next Trie node that corresponds to the current character.
        node = node.children[c - 'a'];

        // Check if the current Trie node represents the end of a word.
        if (node.word != null) {
            // If it's a word, add it to the result list.
            result.add(node.word);
            // Set node.word to null to avoid adding the same word multiple times if found again.
            node.word = null;
        }

        // Mark the current cell as visited by changing its character to '#'.
        board[i][j] = '#';

        // Define directions for exploring adjacent cells (up, down, left, right).
        int[] dir = {-1, 0, 1, 0, -1}; // {row_offset_up, col_offset_up, row_offset_down, col_offset_down, ...}
        // Iterate through the four possible directions.
        for (int d = 0; d < 4; d++) {
            // Calculate the coordinates of the next cell.
            int ni = i + dir[d];
            int nj = j + dir[d + 1];

            // Check if the next cell is within the board boundaries.
            if (ni >= 0 && nj >= 0 && ni < board.length && nj < board[0].length) {
                // Recursively call DFS for the adjacent cell, passing the current Trie node.
                dfs(board, ni, nj, node, result);
            }
        }

        // Backtrack: Restore the original character of the current cell.
        // This is crucial to allow other paths to use this cell.
        board[i][j] = c;
    }

    // TrieNode class to represent a node in the Trie.
    class TrieNode {
        // Array of children nodes, indexed by character ('a' to 'z').
        TrieNode[] children = new TrieNode[26];
        // Stores the complete word if this node marks the end of a word. Otherwise, null.
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
                // Calculate the index for the children array.
                int idx = c - 'a';

                // If the child node for this character doesn't exist, create it.
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                // Move to the child node.
                node = node.children[idx];
            }

            // After processing all characters, mark the current node as the end of a word
            // by storing the word itself.
            node.word = word;
        }

        // Return the root of the constructed Trie.
        return root;
    }
}
```

## Interview Tips
1.  **Explain the Trie-DFS Combination:** Clearly articulate why a Trie is necessary for efficiency and how DFS explores the board. Emphasize that they work together to prune the search space.
2.  **Walk Through an Example:** Use a small board and a few words to demonstrate how the DFS traverses the board and the Trie simultaneously. Show how backtracking works.
3.  **Discuss Edge Cases:** Mention handling empty `words` array, empty `board`, words that are prefixes of others, and words that can be formed multiple ways.
4.  **Complexity Justification:** Be prepared to explain the time and space complexity, especially how the Trie prunes the search and why the `4^L` term is a loose upper bound.
5.  **Code Structure:** Ensure your code is well-organized with clear function separation (e.g., `buildTrie`, `dfs`).

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement a Trie data structure correctly.
- [ ] Implement the `buildTrie` function.
- [ ] Implement the DFS traversal with boundary checks.
- [ ] Implement the Trie node traversal within DFS.
- [ ] Handle marking visited cells and backtracking correctly.
- [ ] Handle duplicate words by nullifying `node.word`.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Word Search (LeetCode 79)
*   Trie (LeetCode 208)
*   Implement Trie (Prefix Tree) (LeetCode 208)
*   Add and Search Word - Data structure design (LeetCode 211)

## Tags
`Array` `Hash Map` `Depth-First Search` `Trie` `Backtracking`

## My Notes
reverse approach
add all words to tries
