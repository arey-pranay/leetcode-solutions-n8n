# Implement Trie Prefix Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Design` `Trie`  
**Time:** O(m)  
**Space:** O(n)

---

## Solution (java)

```java
class Node{
    Node[] children;
    boolean isEof;
    Node(){
        this.children = new Node[26]; // hr ek children ek node hoga
        this.isEof = false;
    }
}
class Trie {
    Node root;
    public Trie() {
        root = new Node();    
    }
    
    public void insert(String word) {
        Node temp = root;
        for(char c : word.toCharArray()){
            if(temp.children[c-'a'] == null) temp.children[c-'a'] = new Node();
            temp = temp.children[c-'a']; // children ki array p jaa rhe hai
        }
        temp.isEof = true;        
    }
    public boolean search(String word) {
        Node temp = root;
        for(char c : word.toCharArray()){
            if(temp.children[c-'a'] == null) return false;
            temp = temp.children[c-'a'];
        }
        return temp.isEof;  
    }
    
    public boolean startsWith(String prefix) {
        Node temp = root;
        for(char c : prefix.toCharArray()){
            if(temp.children[c-'a'] == null) return false;
            temp = temp.children[c-'a'];
        }
        return true;  
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
Implement a data structure to store a dictionary of words, allowing for efficient searching and prefix matching.
The solution involves creating a Trie data structure with Node objects to represent each character in the word.

## Intuition
The key insight is that we can use a Trie to efficiently search for words and prefixes by traversing the nodes based on the characters in the input string. This approach works because each node represents a unique prefix, allowing us to quickly determine if a given string or prefix exists in the dictionary.

## Algorithm

1. Create a Node class with an array of 26 children (one for each lowercase letter) and a boolean flag to indicate the end of a word.
2. In the Trie class, create a root node and initialize it in the constructor.
3. The insert method traverses the Trie based on the characters in the input string, creating new nodes as necessary and marking the end of each word.
4. The search method checks if a given word exists in the Trie by traversing the nodes based on the characters in the input string.
5. The startsWith method checks if a given prefix exists in the Trie by traversing the nodes based on the characters in the input string.

## Concept to Remember

*   **Trie Data Structure**: A Trie is a tree-like data structure that allows for efficient storage and retrieval of strings.
*   **Node Representation**: Each node in the Trie represents a unique prefix, allowing for quick lookup and traversal.
*   **Dynamic Node Creation**: The Trie dynamically creates new nodes as necessary to store each character in the input string.

## Common Mistakes

*   Forgetting to create a new node when inserting a word with a non-existent character.
*   Not properly handling edge cases where the input string is empty or contains invalid characters.
*   Confusing the `isEof` flag with the end of file indicator, rather than its intended purpose as an end-of-word marker.

## Complexity Analysis
- Time: O(m) - reason: Traversing a Trie with m nodes to search for a word or prefix.
- Space: O(n) - reason: Storing n words in the Trie data structure.

## Commented Code

```java
class Node {
    // Array of 26 children (one for each lowercase letter)
    Node[] children;
    // Boolean flag to indicate the end of a word
    boolean isEof;

    Node() {
        this.children = new Node[26];
        this.isEof = false; // Initialize isEof to false by default
    }
}

class Trie {
    Node root;

    public Trie() {
        // Initialize the root node in the constructor
        root = new Node();
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word The word to be inserted
     */
    public void insert(String word) {
        Node temp = root;
        for (char c : word.toCharArray()) {
            // Create a new node if it doesn't exist
            if (temp.children[c - 'a'] == null)
                temp.children[c - 'a'] = new Node();
            // Move to the child node representing the current character
            temp = temp.children[c - 'a'];
        }
        // Mark the end of the word
        temp.isEof = true;
    }

    /**
     * Searches for a word in the Trie.
     *
     * @param word The word to be searched
     * @return True if the word exists, false otherwise
     */
    public boolean search(String word) {
        Node temp = root;
        for (char c : word.toCharArray()) {
            // Return false if the character is not found in the Trie
            if (temp.children[c - 'a'] == null)
                return false;
            // Move to the child node representing the current character
            temp = temp.children[c - 'a'];
        }
        // Check if the end of the word has been marked
        return temp.isEof;
    }

    /**
     * Checks if a prefix exists in the Trie.
     *
     * @param prefix The prefix to be checked
     * @return True if the prefix exists, false otherwise
     */
    public boolean startsWith(String prefix) {
        Node temp = root;
        for (char c : prefix.toCharArray()) {
            // Return false if the character is not found in the Trie
            if (temp.children[c - 'a'] == null)
                return false;
            // Move to the child node representing the current character
            temp = temp.children[c - 'a'];
        }
        // The prefix exists, so return true
        return true;
    }
}
```

## Interview Tips

*   Practice implementing Tries and other data structures from scratch.
*   Focus on understanding the intuition behind the Trie approach.
*   Pay attention to edge cases and error handling in your code.

## Revision Checklist
- [ ] Understand the Trie data structure and its application.
- [ ] Implement a Trie with Node objects representing each character.
- [ ] Review time and space complexity for inserting, searching, and prefix matching operations.
