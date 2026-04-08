# Implement Trie Prefix Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Design` `Trie`  
**Time:** See complexity section  
**Space:** O(N * L)

---

## Solution (java)

```java
class Trie {
    Node root;
    public Trie() {
        this.root = new Node();
    }
    
    public void insert(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
        }
        return curr.isEndOfWord;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = root;
        for(char c : prefix.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
        }
        return true;
    }
    
    class Node{
        Node[] children;
        boolean isEndOfWord;
        Node(){
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

---

---
## Quick Revision
A Trie (prefix tree) is a tree-like data structure for efficient retrieval of keys in a dataset of strings.
We solve this by implementing `insert`, `search`, and `startsWith` operations using a custom `Node` class representing Trie nodes.

## Intuition
The core idea is to represent strings character by character in a tree. Each node in the Trie will represent a character, and the path from the root to a node will form a prefix. This structure naturally allows for efficient prefix-based operations. For example, to check if a word exists, we traverse the Trie following the characters of the word. If we reach the end of the word and the last node is marked as an end-of-word, the word exists. For `startsWith`, we just need to successfully traverse the Trie for the given prefix.

## Algorithm
1.  **Node Structure**: Define a `Node` class. Each `Node` will have an array of children (size 26 for lowercase English letters) and a boolean flag `isEndOfWord` to mark if a word ends at this node.
2.  **Trie Initialization**: In the `Trie` constructor, initialize the `root` node.
3.  **`insert(String word)`**:
    *   Start from the `root` node.
    *   Iterate through each character `c` of the `word`.
    *   Calculate the index for the character (e.g., `c - 'a'`).
    *   If the child node at that index is `null`, create a new `Node` and assign it.
    *   Move to the child node.
    *   After iterating through all characters, mark the current node's `isEndOfWord` as `true`.
4.  **`search(String word)`**:
    *   Start from the `root` node.
    *   Iterate through each character `c` of the `word`.
    *   Calculate the index for the character.
    *   If the child node at that index is `null`, the word does not exist, return `false`.
    *   Move to the child node.
    *   After iterating through all characters, return the `isEndOfWord` status of the current node.
5.  **`startsWith(String prefix)`**:
    *   Start from the `root` node.
    *   Iterate through each character `c` of the `prefix`.
    *   Calculate the index for the character.
    *   If the child node at that index is `null`, the prefix does not exist, return `false`.
    *   Move to the child node.
    *   If the loop completes, it means the entire prefix path exists, return `true`.

## Concept to Remember
*   Tree Data Structures: Understanding how to represent hierarchical data.
*   String Manipulation: Efficiently processing characters within strings.
*   Prefix-based Operations: Designing data structures optimized for prefix searches.
*   Space-Time Tradeoffs: Using extra space (the Trie) for faster query times.

## Common Mistakes
*   Forgetting to initialize the `root` node in the `Trie` constructor.
*   Incorrectly calculating the index for characters (e.g., off-by-one errors or not handling character sets).
*   Not marking `isEndOfWord` correctly during insertion, leading to false positives in `search`.
*   Confusing `search` (requires `isEndOfWord` to be true) with `startsWith` (only requires path existence).
*   Not handling edge cases like empty strings for `insert`, `search`, or `startsWith`.

## Complexity Analysis
*   **Time**:
    *   `insert(String word)`: O(L), where L is the length of the word. We traverse the Trie once for each character.
    *   `search(String word)`: O(L), where L is the length of the word. We traverse the Trie once for each character.
    *   `startsWith(String prefix)`: O(P), where P is the length of the prefix. We traverse the Trie once for each character.
*   **Space**: O(N * L), where N is the number of words and L is the average length of a word. In the worst case, each character of each word might create a new node. However, if words share prefixes, the space complexity is better. For a fixed alphabet size (26), it can also be viewed as O(Total number of nodes).

## Commented Code
```java
class Trie {
    // The root node of the Trie. It doesn't represent any character itself.
    Node root;

    // Constructor for the Trie. Initializes the root node.
    public Trie() {
        // Create a new Node for the root.
        this.root = new Node();
    }

    // Inserts a word into the Trie.
    public void insert(String word) {
        // Start traversal from the root node.
        Node curr = root;
        // Iterate over each character in the word.
        for (char c : word.toCharArray()) {
            // Calculate the index for the character (0-25 for 'a'-'z').
            int index = c - 'a';
            // If the child node for this character doesn't exist, create it.
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            // Move to the child node corresponding to the current character.
            curr = curr.children[index];
        }
        // After inserting all characters, mark the current node as the end of a word.
        curr.isEndOfWord = true;
    }

    // Searches for a word in the Trie. Returns true if the word is found, false otherwise.
    public boolean search(String word) {
        // Start traversal from the root node.
        Node curr = root;
        // Iterate over each character in the word.
        for (char c : word.toCharArray()) {
            // Calculate the index for the character.
            int index = c - 'a';
            // If the child node for this character doesn't exist, the word is not in the Trie.
            if (curr.children[index] == null) {
                return false;
            }
            // Move to the child node.
            curr = curr.children[index];
        }
        // After traversing all characters, return whether the current node marks the end of a word.
        // This ensures we found the exact word, not just a prefix.
        return curr.isEndOfWord;
    }

    // Checks if there is any word in the Trie that starts with the given prefix.
    public boolean startsWith(String prefix) {
        // Start traversal from the root node.
        Node curr = root;
        // Iterate over each character in the prefix.
        for (char c : prefix.toCharArray()) {
            // Calculate the index for the character.
            int index = c - 'a';
            // If the child node for this character doesn't exist, no word starts with this prefix.
            if (curr.children[index] == null) {
                return false;
            }
            // Move to the child node.
            curr = curr.children[index];
        }
        // If we successfully traversed all characters of the prefix, it means a path exists.
        // Therefore, at least one word starts with this prefix.
        return true;
    }

    // Inner class representing a node in the Trie.
    class Node {
        // An array of child nodes. Each index corresponds to a letter ('a' to 'z').
        Node[] children;
        // A boolean flag indicating if a word ends at this node.
        boolean isEndOfWord;

        // Constructor for a Node. Initializes the children array and sets isEndOfWord to false.
        Node() {
            // Initialize the array with 26 null pointers, one for each lowercase English letter.
            this.children = new Node[26];
            // Initially, no word ends at this new node.
            this.isEndOfWord = false;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

## Interview Tips
*   **Clarify Constraints**: Ask about the character set (e.g., lowercase English letters, uppercase, numbers) and maximum word length. This helps confirm the `Node` array size.
*   **Explain the `Node` Structure**: Clearly articulate what `children` and `isEndOfWord` represent and why they are necessary.
*   **Trace Examples**: Walk through `insert`, `search`, and `startsWith` with a few example words and prefixes to demonstrate your understanding.
*   **Distinguish `search` and `startsWith`**: Emphasize the difference: `search` requires the path to exist AND the final node to be marked as `isEndOfWord`, while `startsWith` only requires the path to exist.

## Revision Checklist
- [ ] Understand the Trie data structure and its purpose.
- [ ] Implement the `Node` class with `children` array and `isEndOfWord` flag.
- [ ] Implement the `Trie` constructor to initialize the `root`.
- [ ] Implement the `insert` method correctly, creating nodes as needed and marking `isEndOfWord`.
- [ ] Implement the `search` method, checking for path existence and the `isEndOfWord` flag.
- [ ] Implement the `startsWith` method, checking only for path existence.
- [ ] Analyze time and space complexity for all operations.
- [ ] Consider edge cases like empty strings.

## Similar Problems
*   Word Search II
*   Design Add and Search Words Data Structure
*   Autocomplete System

## Tags
`Trie` `Data Structure` `String`

## My Notes
trie creation
