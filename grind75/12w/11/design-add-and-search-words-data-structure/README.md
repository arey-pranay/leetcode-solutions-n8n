# Design Add And Search Words Data Structure

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Depth-First Search` `Design` `Trie`  
**Time:** O(M)  
**Space:** O(N)

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
The problem requires designing a data structure that can add words and search for words with potentially unknown characters represented by '.'. The solution uses a Trie-like data structure to efficiently store and retrieve words.

## Intuition
This approach works because each character in the word can be associated with a node in the Trie, and the '.' wildcard allows us to traverse all possible paths from that node. By using a recursive function, we can effectively search for words with unknown characters by exploring all possible branches of the Trie.

## Algorithm
1. Create a Node class to represent each node in the Trie:
	* `children`: an array of 26 nodes, one for each letter of the alphabet
	* `isEndOfWord`: a boolean indicating whether this node represents the end of a word
2. In the WordDictionary constructor, create a root node and initialize it with the Node class.
3. The `addWord` function adds a new word to the Trie:
	* Iterate over each character in the word
	* If the current node's child for that letter is null, create a new node and add it as the child
	* Move to the child node and continue iterating
4. After adding all characters of the word, set `isEndOfWord` to true on the final node.
5. The `search` function uses a recursive helper function `func` to search for words in the Trie:
	* Iterate over each character in the word (or return false if it's a '.' and we're not at the start of the word)
	* If it's a '.', recursively call `func` on all non-null child nodes
	* If it's not a '.', move to the corresponding child node and continue iterating
6. If we reach the end of the word, return whether `isEndOfWord` is true on the final node.

## Concept to Remember
* Tries can be used for efficient string matching and retrieval
* Recursive functions can simplify complex traversals through tree-like data structures
* The '.' wildcard character allows for flexible searching in Trie-based systems

## Common Mistakes
* Forgetting to handle null child nodes when using the '.' wildcard
* Not properly initializing the root node with a Node class instance
* Confusing the `children` array index with the ASCII value of characters (e.g., 'a' -> 0, not 97)

## Complexity Analysis
- Time: O(M) - reason: each character in the word is visited at most once during search or add operations
- Space: O(N) - reason: N is the total number of words stored in the Trie

## Commented Code
```java
class WordDictionary {
  class Node{
    // Array to store children nodes for letters a-z (0-25)
    Node[] children;
    // Flag to indicate whether this node represents the end of a word
    boolean isEndOfWord;

    Node(){
      // Initialize children array and flag in constructor
      this.children = new Node[26];
      this.isEndOfWord = false;
    }
  }

  // Root node for Trie-like structure
  Node root;

  public WordDictionary() {
    // Create root node with Node class instance
    this.root = new Node();
  }

  /**
   * Add a word to the Trie
   */
  public void addWord(String word) {
    // Start at root node and iterate over each character in the word
    Node curr = root;
    for(char c : word.toCharArray()){
      // If child node for current letter is null, create one
      if(curr.children[c-'a'] == null) curr.children[c-'a'] = new Node();
      // Move to child node corresponding to current letter
      curr = curr.children[c-'a'];
    }
    // Mark end of word on final node
    curr.isEndOfWord = true;
  }

  /**
   * Search for a word in the Trie, allowing '.' as wildcard character
   */
  public boolean search(String word) {
    // Call recursive helper function to perform search
    return func(word,0,root);
  }

  /**
   * Recursive helper function to search for words in Trie
   */
  public boolean func(String word, int start, Node curr){
    // Iterate over each character in the word (or from current index)
    for(int i=start;i<word.length();i++){
      char c= word.charAt(i);
      // If '.' is encountered and not at start of word, recursively search child nodes
      if(c == '.') {
        for(Node child : curr.children){
          if(child != null) {
            if(func(word,i+1,child)) return true;
          }
        }
        return false;
      } else { // If character is not '.', move to corresponding child node
        if(curr.children[c-'a'] ==null) return false;
        else curr = curr.children[c-'a'];
      }
    }
    // Return whether final node represents the end of a word
    return curr.isEndOfWord;
  }
}
```

## Interview Tips
* Make sure to handle edge cases and special characters like '.' correctly.
* Practice explaining your thought process behind designing the Trie-like data structure.
* Be prepared to discuss trade-offs between using Tries, Hash Maps, or other data structures for similar problems.

## Revision Checklist
- [ ] Review handling of null child nodes when using '.' wildcard
- [ ] Ensure proper initialization of root node with Node class instance
- [ ] Verify recursive function `func` correctly traverses Trie

## Similar Problems
* LeetCode 211: Design Add and Search Words Data Structure (similar problem statement)
* LeetCode 677: KeyPad (uses similar Trie-like data structure)
