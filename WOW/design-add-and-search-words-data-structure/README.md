# Design Add And Search Words Data Structure

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Depth-First Search` `Design` `Trie`  
**Time:** See complexity section  
**Space:** O(Total number of characters in all words)

---

## Solution (java)

```java
class WordDictionary {
  class Node{
    Node[] children;
    boolean isEndOfWord;
    Node(){
      this.children = new Node[26];
      this.isEndOfWord = false;
    }
  }

    Node root;
    public WordDictionary() {
        this.root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
          if(curr.children[c-'a'] == null) curr.children[c-'a'] = new Node();
          curr = curr.children[c-'a'];
        }
        curr.isEndOfWord = true;
    }
    
    public boolean search(String word) {
      return func(word,0,root);
    }

    public boolean func(String word, int start, Node curr){
        for(int i=start;i<word.length();i++){
          char c= word.charAt(i);
          if(c == '.') { for(Node child : curr.children){if(child != null) if(func(word,i+1,child)) return true;} return false;}
          else{if(curr.children[c-'a'] ==null) return false; else curr=curr.children[c-'a'];}
        }
        return curr.isEndOfWord;
    }
}

```

---

---
## Quick Revision
This problem asks to design a data structure that supports adding words and searching for words, including those with wildcard characters.
A Trie (prefix tree) is the ideal data structure for this, with modifications to handle wildcard searches.

## Intuition
The core idea is to use a Trie to store words efficiently. Each node in the Trie represents a character, and paths from the root to a node represent prefixes. When adding a word, we traverse the Trie, creating new nodes as needed. For searching, we traverse the Trie based on the characters in the word. The wildcard '.' introduces a branching search: if we encounter a '.', we must explore all possible children of the current node.

## Algorithm
1.  **Trie Node Structure**: Define a `Node` class with an array of children (size 26 for 'a'-'z') and a boolean flag `isEndOfWord` to mark the end of a valid word.
2.  **Initialization**: In the `WordDictionary` constructor, initialize the `root` of the Trie to a new `Node`.
3.  **`addWord(String word)`**:
    *   Start from the `root` node.
    *   Iterate through each character `c` of the `word`.
    *   For each character, calculate its index (`c - 'a'`).
    *   If the child node at that index is `null`, create a new `Node` and assign it.
    *   Move to the child node.
    *   After processing all characters, set `isEndOfWord` to `true` for the current node.
4.  **`search(String word)`**:
    *   This is the public interface for searching. It calls a recursive helper function `func` starting from the `root` node and index 0 of the word.
5.  **`func(String word, int start, Node curr)` (Recursive Helper)**:
    *   Iterate through the `word` starting from the `start` index.
    *   Get the current character `c`.
    *   **If `c` is '.' (wildcard)**:
        *   Iterate through all possible children of the `curr` node.
        *   If a child node is not `null`, recursively call `func` with the rest of the word (`i + 1`) and the child node.
        *   If any recursive call returns `true`, immediately return `true`.
        *   If no child leads to a match, return `false`.
    *   **If `c` is a regular character**:
        *   Calculate its index (`c - 'a'`).
        *   If the child node at that index is `null`, return `false` (word not found).
        *   Otherwise, move `curr` to the child node.
    *   **After the loop**: If the loop completes, it means we have successfully traversed the path for the given word (or its wildcard substitutions). Return `curr.isEndOfWord` to check if this path actually forms a complete word in the dictionary.

## Concept to Remember
*   **Trie (Prefix Tree)**: Efficiently stores strings and allows for prefix-based searching.
*   **Recursion**: Essential for handling the wildcard character, allowing exploration of multiple paths.
*   **Backtracking (Implicit)**: The recursive calls implicitly backtrack when a path doesn't lead to a solution.
*   **Character Mapping**: Using `c - 'a'` to map characters to array indices.

## Common Mistakes
*   **Incorrect Wildcard Handling**: Not properly exploring all children when a '.' is encountered, or not advancing the index correctly in recursive calls.
*   **Missing `isEndOfWord` Check**: Returning `true` just because a path exists, without verifying if it marks the end of a valid word.
*   **Off-by-One Errors**: Incorrectly handling the `start` index in the recursive `func` method, especially when dealing with wildcards.
*   **Not Handling Empty Strings**: Although not explicitly stated, consider how empty strings would be added or searched.
*   **Inefficient `search` for Wildcards**: Iterating through all 26 children for every '.' without checking if they are `null`.

## Complexity Analysis
*   **Time**:
    *   `addWord(String word)`: O(L), where L is the length of the word. We traverse the Trie once for each character.
    *   `search(String word)`: O(N * 26^L) in the worst case, where N is the length of the word. This occurs when the word consists entirely of '.' characters. For each '.', we might explore up to 26 branches. In the best case (no wildcards), it's O(L).
*   **Space**:
    *   O(Total number of characters in all words). The space complexity is determined by the total number of nodes in the Trie, which is proportional to the sum of the lengths of all words added.

## Commented Code
```java
class WordDictionary {
    // Inner class to represent a node in the Trie
    class Node {
        Node[] children; // Array to store pointers to child nodes (26 for 'a'-'z')
        boolean isEndOfWord; // Flag to indicate if this node marks the end of a word

        // Constructor for Node
        Node() {
            this.children = new Node[26]; // Initialize children array with nulls
            this.isEndOfWord = false; // Initially, no node marks the end of a word
        }
    }

    Node root; // The root node of the Trie

    // Constructor for WordDictionary
    public WordDictionary() {
        this.root = new Node(); // Initialize the Trie with a root node
    }

    // Adds a word to the data structure
    public void addWord(String word) {
        Node curr = root; // Start traversal from the root
        // Iterate over each character in the word
        for (char c : word.toCharArray()) {
            // Calculate the index for the character ('a' -> 0, 'b' -> 1, ...)
            int index = c - 'a';
            // If the child node for this character doesn't exist, create it
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            // Move to the child node
            curr = curr.children[index];
        }
        // Mark the current node as the end of a word
        curr.isEndOfWord = true;
    }

    // Searches for a word in the data structure, supporting '.' as a wildcard
    public boolean search(String word) {
        // Call the recursive helper function starting from the root, index 0, and the word
        return func(word, 0, root);
    }

    // Recursive helper function for searching
    // word: the word to search for
    // start: the current index in the word being processed
    // curr: the current node in the Trie
    public boolean func(String word, int start, Node curr) {
        // Iterate through the word from the 'start' index
        for (int i = start; i < word.length(); i++) {
            char c = word.charAt(i); // Get the current character

            // If the character is a wildcard '.'
            if (c == '.') {
                // Iterate through all possible children of the current node
                for (Node child : curr.children) {
                    // If a child exists
                    if (child != null) {
                        // Recursively call func for the rest of the word (i+1) and the child node
                        // If any recursive call returns true, it means a match is found
                        if (func(word, i + 1, child)) {
                            return true;
                        }
                    }
                }
                // If no child leads to a match, return false
                return false;
            } else { // If the character is a regular letter
                // Calculate the index for the character
                int index = c - 'a';
                // If the child node for this character doesn't exist, the word is not in the Trie
                if (curr.children[index] == null) {
                    return false;
                }
                // Move to the child node
                curr = curr.children[index];
            }
        }
        // After iterating through the entire word, check if the current node marks the end of a word
        return curr.isEndOfWord;
    }
}
```

## Interview Tips
*   **Explain the Trie**: Clearly articulate why a Trie is suitable for this problem, highlighting its prefix-matching capabilities.
*   **Handle Wildcards First**: When discussing the search algorithm, focus on how the wildcard '.' is handled, as this is the trickiest part. Explain the recursive branching.
*   **Trace Examples**: Walk through examples, especially those involving wildcards, to demonstrate your understanding of the `func` method's logic.
*   **Discuss Edge Cases**: Mention how you would handle empty strings, or words with only wildcards.
*   **Complexity Justification**: Be prepared to explain the worst-case time complexity for `search` and why it's exponential with wildcards.

## Revision Checklist
- [ ] Understand the Trie data structure.
- [ ] Implement `addWord` correctly by traversing and creating nodes.
- [ ] Implement `search` using a recursive helper function.
- [ ] Handle regular characters in `search` by traversing the Trie.
- [ ] Handle wildcard characters ('.') in `search` by exploring all children recursively.
- [ ] Ensure `isEndOfWord` is checked at the end of a successful path.
- [ ] Analyze time and space complexity for both operations.
- [ ] Consider edge cases like empty strings.

## Similar Problems
*   Implement Trie (Prefix Tree)
*   Word Search II
*   Autocomplete System

## Tags
`Trie` `Depth-First Search` `Recursion` `Design`
