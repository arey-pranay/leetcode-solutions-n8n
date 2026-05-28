# Longest Common Suffix Queries

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Trie`  
**Time:**   
**Space:** 

---

## Solution (java)

```java
class Solution {
    class Pair {
        String word;
        int index;

        Pair(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    class Node {
        Node[] children;
        Pair p;

        Node(String word, int index) {
            this.children = new Node[26];
            this.p = new Pair(word, index);
        }
    }

    Node root = new Node("", -1);

    private boolean isBetter(String s, int i, Pair p) {
        return p.index == -1
                || s.length() < p.word.length()
                || (s.length() == p.word.length() && i < p.index);
    }

    public void addNode(String s, int i) {

        if (isBetter(s, i, root.p)) {
            root.p = new Pair(s, i);
        }

        Node curr = root;

        for (char c : s.toCharArray()) {

            int index = c - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new Node(s, i);
            } else if (isBetter(s, i, curr.children[index].p)) {
                curr.children[index].p = new Pair(s, i);
            }

            curr = curr.children[index];
        }
    }

    public int findPrefix(String s) {
        Node curr = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null)
                return curr.p.index;
            curr = curr.children[index];
        }
        return curr.p.index;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int index = 0;
        for (String s : wordsContainer)
            addNode(new StringBuilder(s).reverse().toString(), index++);
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < ans.length; i++)
            ans[i] = findPrefix(new StringBuilder(wordsQuery[i]).reverse().toString());
        return ans;
        //          ()
        //         / 
        //         d  = dcb, 1, false;
        //         /
        //        c = dcb, 1, false
        //       /
        //     b = dcb,1,true
        //   /        \
        // a = dcba,0  x = dcbx,2
    }
}
```

---

---
## Quick Revision
This problem asks to find the index of the shortest word in a container that shares the longest common suffix with each query word. The solution uses a Trie data structure.

## Intuition
The core idea is to efficiently find the "best" matching word for each query. Since we're dealing with suffixes, reversing the words and then using a Trie (which is excellent for prefix matching) allows us to treat suffix matching as prefix matching on reversed strings. For each node in the Trie, we need to store information about the "best" word encountered so far that passes through that node. "Best" is defined as the shortest word, and among words of the same length, the one with the smallest original index.

## Algorithm
1.  **Reverse and Build Trie:**
    *   Iterate through `wordsContainer`. For each word `s` at index `i`:
        *   Reverse `s`.
        *   Insert the reversed `s` into a Trie.
        *   At each node visited during insertion, store a `Pair` containing the reversed word and its original index `i`. This `Pair` should represent the "best" word encountered so far that has this reversed string as a prefix (which corresponds to a suffix in the original string).
        *   The `isBetter` helper function determines if a new word is "better" than the currently stored `Pair` at a node. A word is better if it's shorter, or if it's the same length but has a smaller original index.
        *   The root node should initially store a dummy `Pair` with index -1 to handle cases where no match is found.

2.  **Query Trie:**
    *   Initialize an answer array `ans` of the same length as `wordsQuery`.
    *   Iterate through `wordsQuery`. For each query word `q` at index `i`:
        *   Reverse `q`.
        *   Traverse the Trie using the reversed `q`.
        *   At each step, if the current Trie node has a child corresponding to the current character, move to that child.
        *   If at any point a character in the reversed query does not have a corresponding child in the Trie, it means no word in the container shares this prefix (and thus no word shares the corresponding suffix). In this case, the `Pair` stored at the *current* node (before the mismatch) represents the best match found so far.
        *   After traversing the entire reversed query word, the `Pair` stored at the final node reached represents the best match.
        *   The `index` from this `Pair` is the answer for the current query. Store it in `ans[i]`.

3.  **Return Results:**
    *   Return the `ans` array.

## Concept to Remember
*   **Trie (Prefix Tree):** Efficient for prefix-based searches. By reversing strings, we can adapt it for suffix matching.
*   **Greedy Approach:** At each Trie node, we maintain the "best" candidate word encountered so far. This greedy choice at each step leads to the overall optimal solution.
*   **Custom Comparison Logic:** The `isBetter` function defines the criteria for selecting the "best" word (shortest length, then smallest index).

## Common Mistakes
*   **Forgetting to Reverse:** The core of the solution relies on reversing strings to use a Trie for suffix matching.
*   **Incorrect `isBetter` Logic:** Errors in defining "better" (e.g., prioritizing longer words, or not handling same-length words with different indices correctly) will lead to wrong answers.
*   **Handling No Match:** Not initializing the root node's `Pair` correctly or not returning the `index` from the last valid node when a mismatch occurs can cause issues.
*   **Trie Node Structure:** Incorrectly storing or updating the `Pair` at each node, especially when multiple words share the same prefix in the reversed string.

## Complexity Analysis
*   **Time:**
    *   Building the Trie: O(N * L), where N is the number of words in `wordsContainer` and L is the maximum length of a word. Each character of each word is processed once.
    *   Querying the Trie: O(Q * L), where Q is the number of queries and L is the maximum length of a query word. Each character of each query word is processed once.
    *   Total Time: O(N * L + Q * L)
*   **Space:**
    *   Trie: O(N * L) in the worst case, where each character of each word creates a new node.
    *   `Pair` objects: O(N) as each node stores at most one `Pair`.
    *   Total Space: O(N * L)

## Commented Code
```java
class Solution {
    // Inner class to store a word and its original index.
    // This is used to keep track of the "best" matching word found so far.
    class Pair {
        String word; // The reversed word
        int index;   // The original index of the word in wordsContainer

        Pair(String word, int index) {
            this.word = word; // Initialize the reversed word
            this.index = index; // Initialize the original index
        }
    }

    // Inner class representing a node in the Trie.
    class Node {
        Node[] children; // Array of children nodes, indexed by character ('a' to 'z').
        Pair p;          // Stores the "best" Pair encountered that passes through this node.

        // Constructor for a Trie node.
        Node(String word, int index) {
            this.children = new Node[26]; // Initialize children array for 26 lowercase English letters.
            // Initialize the Pair for this node. This Pair will be updated as we insert words.
            // Initially, it's set with the word and index passed to the constructor.
            this.p = new Pair(word, index);
        }
    }

    // The root of the Trie.
    // Initialized with an empty string and -1 index, serving as a sentinel.
    Node root = new Node("", -1);

    // Helper function to determine if a new word (s, i) is "better" than an existing Pair p.
    // "Better" means:
    // 1. If p is a sentinel (index -1), any new word is better.
    // 2. If the new word is shorter than p.word.
    // 3. If they have the same length, and the new word has a smaller original index i.
    private boolean isBetter(String s, int i, Pair p) {
        return p.index == -1 // If the current best is the sentinel, any new word is better.
                || s.length() < p.word.length() // If the new word is shorter.
                || (s.length() == p.word.length() && i < p.index); // If same length, check for smaller original index.
    }

    // Inserts a reversed word into the Trie.
    public void addNode(String s, int i) {
        // First, check if the current word (s, i) is better than the Pair stored at the root.
        // This handles cases where the best word overall is the first word inserted, or
        // if the current word is better than the current best at the root.
        if (isBetter(s, i, root.p)) {
            root.p = new Pair(s, i); // Update the root's Pair if the current word is better.
        }

        Node curr = root; // Start traversal from the root.

        // Iterate through each character of the reversed word.
        for (char c : s.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the children array (0-25).

            // If the child node for this character doesn't exist, create it.
            // When creating a new node, we pass the current word (s) and its index (i)
            // to initialize its Pair. This Pair will be updated if a better word is found later.
            if (curr.children[index] == null) {
                curr.children[index] = new Node(s, i);
            }
            // If the child node already exists, check if the current word (s, i) is better
            // than the Pair stored in that child node.
            else if (isBetter(s, i, curr.children[index].p)) {
                // If it's better, update the Pair in the child node.
                curr.children[index].p = new Pair(s, i);
            }

            // Move to the child node for the next character.
            curr = curr.children[index];
        }
    }

    // Finds the index of the best matching word for a given reversed query string.
    public int findPrefix(String s) {
        Node curr = root; // Start traversal from the root.

        // Traverse the Trie based on the characters of the reversed query string.
        for (char c : s.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the children array.

            // If there is no child node for the current character, it means no word in the
            // container shares this prefix (and thus no word shares the corresponding suffix).
            // The Pair stored at the *current* node (before the mismatch) represents the best match found so far.
            if (curr.children[index] == null) {
                return curr.p.index; // Return the index from the current node's Pair.
            }
            // If a child node exists, move to it.
            curr = curr.children[index];
        }
        // If we successfully traversed the entire reversed query string, the Pair at the
        // final node reached represents the best match.
        return curr.p.index; // Return the index from the final node's Pair.
    }

    // Main function to process the queries.
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int index = 0; // Initialize an index counter for wordsContainer.

        // Iterate through each word in wordsContainer.
        for (String s : wordsContainer) {
            // Reverse the word and insert it into the Trie along with its original index.
            addNode(new StringBuilder(s).reverse().toString(), index++);
        }

        // Initialize the answer array to store results for each query.
        int[] ans = new int[wordsQuery.length];

        // Iterate through each query word.
        for (int i = 0; i < ans.length; i++) {
            // Reverse the query word and find the index of the best matching word in the Trie.
            ans[i] = findPrefix(new StringBuilder(wordsQuery[i]).reverse().toString());
        }

        // Return the array of indices.
        return ans;
        //          ()
        //         /
        //         d  = dcb, 1, false; // Example: Node for 'd' in reversed "dcb"
        //         /
        //        c = dcb, 1, false // Example: Node for 'c' in reversed "dcb"
        //       /
        //     b = dcb,1,true // Example: Node for 'b' in reversed "dcb", Pair is ("dcb", 1)
        //   /        \
        // a = dcba,0  x = dcbx,2 // Example: Node for 'a' in reversed "dcba", Pair is ("dcba", 0)
                               // Example: Node for 'x' in reversed "dcbx", Pair is ("dcbx", 2)
    }
}
```

## Interview Tips
*   **Explain the Reversal:** Clearly articulate *why* you are reversing the strings. This is the key insight to using a Trie for suffix matching.
*   **Define "Best":** Be precise about the criteria for selecting the "best" word (shortest length, then smallest index). This is crucial for the `isBetter` logic.
*   **Trie Traversal Logic:** Walk through an example of how a query word is traversed in the Trie and what happens when a mismatch occurs. Explain that the `Pair` at the *current* node before the mismatch is the answer.
*   **Edge Cases:** Discuss what happens if `wordsContainer` or `wordsQuery` are empty, or if no common suffix exists for a query. The sentinel `Pair` at the root handles the "no match" scenario.

## Revision Checklist
- [ ] Understand the problem statement: find shortest word with longest common suffix.
- [ ] Recognize the need for suffix matching and how reversing helps with Trie.
- [ ] Implement the `Pair` class to store word and index.
- [ ] Implement the `Node` class for the Trie.
- [ ] Implement the `isBetter` logic correctly (length, then index).
- [ ] Implement `addNode` to build the Trie, updating `Pair`s greedily.
- [ ] Implement `findPrefix` to query the Trie with reversed query strings.
- [ ] Handle cases where no match is found.
- [ ] Test with examples, including edge cases.

## Similar Problems
*   Longest Common Prefix
*   Implement Trie (Prefix Tree)
*   Word Break II
*   Autocomplete System

## Tags
`Trie` `String` `Array` `Greedy`
