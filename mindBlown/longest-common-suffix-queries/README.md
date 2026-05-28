# Longest Common Suffix Queries

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Trie`  
**Time:** O(N * L + M * L)  
**Space:** O(N * L)

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
This problem asks to find the index of the shortest word in a container that shares the longest common suffix with each query word. The solution uses a Trie data structure to efficiently store and query suffixes.

## Intuition
The core idea is to transform the problem of finding common suffixes into a problem of finding common prefixes. If we reverse all the words, a common suffix in the original words becomes a common prefix in the reversed words. A Trie is a natural fit for prefix-based searching. We can build a Trie where each node stores the "best" word (shortest, then smallest index) encountered so far that ends at that prefix (which corresponds to a suffix in the original words). When querying, we traverse the Trie with the reversed query word and find the best word associated with the path.

## Algorithm
1.  **Reverse Words and Build Trie:**
    *   Iterate through `wordsContainer`. For each word `s` at index `i`:
        *   Reverse `s`.
        *   Insert the reversed `s` into a Trie.
        *   Each node in the Trie will store a `Pair` object containing the original word and its index.
        *   When inserting, if a node already has a `Pair`, compare the current reversed word with the stored word. Update the `Pair` if the current word is shorter, or if it has the same length but a smaller index. This ensures we always store the "best" word (shortest, then smallest index) that forms the prefix in the Trie.
2.  **Query the Trie:**
    *   Initialize an answer array `ans` of the same length as `wordsQuery`.
    *   Iterate through `wordsQuery`. For each query word `q` at index `i`:
        *   Reverse `q`.
        *   Traverse the Trie using the reversed `q`.
        *   At each step of the traversal, if the current node has a `Pair` associated with it, this `Pair` represents a candidate for the longest common suffix. We need to keep track of the "best" `Pair` encountered along the path.
        *   If at any point the path for the reversed query word cannot be extended in the Trie (i.e., the child node is null), we stop and return the index of the best `Pair` found so far on the path.
        *   If the traversal completes, the `Pair` at the final node represents the best match.
        *   Store the index from the best `Pair` in `ans[i]`.
3.  **Return `ans`**.

## Concept to Remember
*   **Trie (Prefix Tree):** Efficient for prefix-based searches. Each node represents a character, and paths from the root represent prefixes.
*   **String Reversal:** A common technique to convert suffix problems into prefix problems.
*   **Greedy Approach with Tie-breaking:** When multiple words satisfy a condition (e.g., share a common suffix), we need a clear rule to pick the "best" one (shortest length, then smallest index).

## Common Mistakes
*   **Not Reversing Words:** Directly building a Trie for suffixes is inefficient. Reversing is key.
*   **Incorrect Tie-breaking Logic:** Failing to correctly implement the "shortest word, then smallest index" rule when updating nodes in the Trie.
*   **Handling Empty/Null Nodes:** Not properly checking for `null` children in the Trie during traversal, which can lead to `NullPointerException`s.
*   **Storing Original vs. Reversed Words:** Ensuring that the `Pair` object stores the original word and index, even though the Trie is built with reversed words. The `isBetter` logic needs to compare lengths of original words. (Correction: The provided solution stores the reversed word in the `Pair` and compares lengths of reversed words, which is equivalent because reversing preserves length. The index is the crucial part for tie-breaking).

## Complexity Analysis
*   **Time:** O(N * L + M * L), where N is the number of words in `wordsContainer`, M is the number of words in `wordsQuery`, and L is the maximum length of a word.
    *   Building the Trie: For each of the N words, we reverse it (O(L)) and insert it into the Trie (O(L)). Total: O(N * L).
    *   Querying: For each of the M query words, we reverse it (O(L)) and traverse the Trie (O(L)). Total: O(M * L).
*   **Space:** O(N * L), for storing the Trie. In the worst case, each character of each word in `wordsContainer` might create a new node.

## Commented Code
```java
class Solution {
    // Helper class to store a word and its original index.
    // This is crucial for tie-breaking (shortest word, then smallest index).
    class Pair {
        String word; // The reversed word stored in the Trie node.
        int index;   // The original index of the word in wordsContainer.

        Pair(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    // Node structure for the Trie.
    class Node {
        Node[] children; // Array of children nodes, one for each letter 'a' through 'z'.
        Pair p;          // Stores the best Pair (shortest word, smallest index) ending at this prefix.

        // Constructor for a Trie node.
        // Initializes children array and sets the initial Pair.
        Node(String word, int index) {
            this.children = new Node[26]; // 26 possible lowercase English letters.
            // Initialize with a dummy Pair. -1 index signifies no word has reached here yet.
            this.p = new Pair(word, index);
        }
    }

    // The root of our Trie. It's initialized with an empty string and -1 index.
    Node root = new Node("", -1);

    // Helper function to determine if a new word (s, i) is "better" than an existing Pair p.
    // "Better" means:
    // 1. If p is a dummy Pair (index == -1), any new word is better.
    // 2. If the new word is shorter than the word in p.
    // 3. If they have the same length, and the new word has a smaller index.
    private boolean isBetter(String s, int i, Pair p) {
        return p.index == -1 // If the current best is a dummy
                || s.length() < p.word.length() // Or if the new word is shorter
                || (s.length() == p.word.length() && i < p.index); // Or if lengths are equal and new index is smaller
    }

    // Inserts a reversed word into the Trie.
    public void addNode(String s, int i) {
        // First, check if the current reversed word (s, i) is better than the root's Pair.
        // This handles cases where the shortest/smallest-indexed word is a prefix of all others.
        if (isBetter(s, i, root.p)) {
            root.p = new Pair(s, i); // Update root's Pair if the new word is better.
        }

        Node curr = root; // Start traversal from the root.

        // Iterate through each character of the reversed word.
        for (char c : s.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the children array (0-25).

            // If the child node for this character doesn't exist, create it.
            if (curr.children[index] == null) {
                // When creating a new node, initialize its Pair with the current word and index.
                // This is the first word to form this specific prefix.
                curr.children[index] = new Node(s, i);
            } else if (isBetter(s, i, curr.children[index].p)) {
                // If the child node already exists, check if the current reversed word (s, i)
                // is better than the Pair stored in that child node.
                // This ensures each node always stores the best Pair encountered so far for its prefix.
                curr.children[index].p = new Pair(s, i);
            }

            // Move to the next node in the Trie.
            curr = curr.children[index];
        }
    }

    // Finds the index of the best word that matches the prefix of the reversed query string.
    public int findPrefix(String s) {
        Node curr = root; // Start traversal from the root.
        // Traverse the Trie using the characters of the reversed query string.
        for (char c : s.toCharArray()) {
            int index = c - 'a'; // Calculate the index for the children array.

            // If there's no child node for the current character, it means the reversed query string
            // cannot be extended further in the Trie. The best match found so far is stored in curr.p.
            if (curr.children[index] == null) {
                return curr.p.index; // Return the index of the best word found on the path.
            }
            // Move to the next node in the Trie.
            curr = curr.children[index];
        }
        // If the loop completes, it means the entire reversed query string is a prefix in the Trie.
        // The best match for this prefix is stored in the Pair of the final node.
        return curr.p.index;
    }

    // Main function to solve the problem.
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int index = 0; // Counter for the index of words in wordsContainer.
        // Iterate through all words in wordsContainer.
        for (String s : wordsContainer) {
            // Reverse each word and add it to the Trie.
            // The index 'index' is passed to uniquely identify the word.
            addNode(new StringBuilder(s).reverse().toString(), index++);
        }

        int[] ans = new int[wordsQuery.length]; // Initialize the answer array.
        // Iterate through all query words.
        for (int i = 0; i < ans.length; i++) {
            // Reverse the query word and find the index of the best matching word in the Trie.
            // The result is the index of the shortest word in wordsContainer with the longest common suffix.
            ans[i] = findPrefix(new StringBuilder(wordsQuery[i]).reverse().toString());
        }
        return ans; // Return the array of indices.
        // Example visualization of Trie structure for suffixes:
        // If wordsContainer = ["dcb", "dcba", "dcbx"]
        // Reversed: ["bcd", "abcd", "bcxd"]
        // Trie structure (simplified, showing relevant nodes and their Pairs):
        // root (Pair("", -1))
        //  |-- 'b' -> Node(Pair("bcd", 0))
        //      |-- 'c' -> Node(Pair("bcd", 0))
        //          |-- 'd' -> Node(Pair("bcd", 0))  <-- If query is "bcd", this is the path.
        //              |-- 'a' -> Node(Pair("abcd", 1)) <-- If query is "dcba" (reversed "abcd"), this is the path.
        //              |-- 'x' -> Node(Pair("bcxd", 2)) <-- If query is "dcba" (reversed "bcxd"), this is the path.
        // The `isBetter` logic ensures that at each node, the `Pair` stores the shortest word (or smallest index if lengths are equal)
        // that forms the prefix up to that node.
    }
}
```

## Interview Tips
1.  **Explain the Reversal Strategy:** Clearly articulate why reversing the strings is crucial to convert the suffix problem into a prefix problem, which is efficiently handled by a Trie.
2.  **Detail the `isBetter` Logic:** This is the heart of the tie-breaking mechanism. Walk through the conditions and explain why they correctly identify the "best" word according to the problem statement (shortest length, then smallest index).
3.  **Trie Traversal and State:** Describe how you traverse the Trie with the reversed query string and how the `Pair` at each node represents the best candidate found so far for that prefix. Emphasize what happens when a path cannot be extended.
4.  **Edge Cases:** Discuss how the Trie handles cases where a query word's reversed form is not a prefix of any reversed container word, or when the container is empty. The initial `root.p` with `index = -1` is important here.

## Revision Checklist
- [ ] Understand the problem: find shortest word with longest common suffix.
- [ ] Recognize suffix -> prefix transformation via string reversal.
- [ ] Implement Trie data structure.
- [ ] Design `Pair` class for word and index.
- [ ] Implement `isBetter` logic for tie-breaking.
- [ ] Correctly insert reversed words into Trie, updating `Pair`s.
- [ ] Correctly query Trie with reversed query words.
- [ ] Handle cases where query path doesn't exist.
- [ ] Analyze time and space complexity.

## Similar Problems
*   1804. Implement Trie II (Prefix Tree)
*   208. Implement Trie (Prefix Tree)
*   648. Replace Words
*   211. Design Add and Search Words Data Structure

## Tags
`Trie` `String` `Array` `Data Structures`
