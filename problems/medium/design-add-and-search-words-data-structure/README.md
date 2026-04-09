# Design Add And Search Words Data Structure

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Depth-First Search` `Design` `Trie`  
**Time:** See complexity section  
**Space:** O(Total Characters * Alphabet Size)

---

## Solution (java)

```java
class WordDictionary {
    Node root;
    public WordDictionary() {
        this.root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index]== null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        return func(word,0,root);
    }
    public boolean func(String word, int i, Node curr){
        if(curr == null) return false;
        if(i==word.length()) return curr.isEndOfWord;
          
        char c = word.charAt(i);
        if(c=='.'){
            for(int j=0;j<26;j++){
                if(func(word, i+1, curr.children[j])) return true;
            }
            return false;
        }else{
            int index = c-'a';
            return func(word, i+1, curr.children[index]);
        }
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


//     ..bxyz
//      .bxyz.
//      bxyz..
//      b.x.y
// 


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```

---

---
## Quick Revision
This problem asks to design a data structure that supports adding words and searching for words, including those with wildcard characters. A Trie (prefix tree) is the most suitable data structure for this.

## Intuition
The core idea is to use a Trie to store the words. Each node in the Trie represents a character, and a path from the root to a node represents a prefix. When adding a word, we traverse the Trie, creating new nodes if necessary. For searching, we traverse the Trie based on the characters in the word. The wildcard '.' introduces a branching search, where we explore all possible children of a node.

## Algorithm
1.  **Trie Node Structure**: Define a `Node` class with an array of children (size 26 for lowercase English letters) and a boolean flag `isEndOfWord` to mark the end of a complete word.
2.  **Constructor**: Initialize the `WordDictionary` with a root `Node`.
3.  **`addWord(String word)`**:
    *   Start from the `root` node.
    *   Iterate through each character `c` of the `word`.
    *   Calculate the index `c - 'a'`.
    *   If the child node at this index is `null`, create a new `Node` and assign it.
    *   Move to the child node.
    *   After processing all characters, set `isEndOfWord` to `true` for the current node.
4.  **`search(String word)`**:
    *   This method will be a wrapper that calls a recursive helper function `func`.
    *   Call `func` with the `word`, starting index `0`, and the `root` node.
5.  **`func(String word, int i, Node curr)` (Recursive Helper)**:
    *   **Base Case 1**: If `curr` is `null`, it means the path doesn't exist, so return `false`.
    *   **Base Case 2**: If `i` reaches the end of the `word` (`i == word.length()`), return `curr.isEndOfWord`. This checks if the current path forms a complete word.
    *   **Wildcard Character ('.')**: If the current character `c` is '.', iterate through all 26 possible children (`j` from 0 to 25). For each child, recursively call `func` with `i + 1` and the child node. If any of these recursive calls return `true`, return `true`. If none of them find a match, return `false`.
    *   **Regular Character**: If the current character `c` is a regular letter:
        *   Calculate the index `c - 'a'`.
        *   Recursively call `func` with `i + 1` and the child node at that index. Return the result of this call.

## Concept to Remember
*   **Trie (Prefix Tree)**: Efficient for prefix-based searches and storing strings.
*   **Recursion/Backtracking**: Essential for handling the wildcard character, exploring multiple paths.
*   **State Management in Recursion**: Passing the current index and node correctly is crucial.
*   **Handling Nulls**: Properly checking for `null` nodes prevents `NullPointerException`s.

## Common Mistakes
*   **Incorrect Trie Implementation**: Forgetting to initialize children arrays or not setting `isEndOfWord` correctly.
*   **Wildcard Handling Logic**: Not exploring all 26 children when a '.' is encountered, or incorrectly returning `false` prematurely.
*   **Off-by-One Errors**: In index calculations or loop bounds for characters and word length.
*   **Not Handling Empty Strings**: Although not explicitly mentioned, consider how an empty string would be added or searched.
*   **Stack Overflow**: For very deep Tries or extremely long words with many wildcards, a recursive solution might hit stack limits (though less likely in typical LeetCode constraints).

## Complexity Analysis
*   **Time**:
    *   `addWord(String word)`: O(L), where L is the length of the word. We traverse the Trie once for each character.
    *   `search(String word)`: O(N * 26^L) in the worst case, where L is the length of the word and N is the number of nodes in the Trie. This occurs when the word consists entirely of '.' characters. In the best case (no wildcards), it's O(L).
*   **Space**:
    *   O(Total Characters * Alphabet Size) in the worst case for the Trie. If all possible prefixes of all added words are unique, the space can be significant. For a fixed alphabet size (26), it's proportional to the total number of characters in all unique prefixes of the added words.

## Commented Code
```java
class WordDictionary {
    // The root node of our Trie. It doesn't represent any character itself.
    Node root;

    // Constructor to initialize the WordDictionary.
    public WordDictionary() {
        // Create a new root node when the dictionary is instantiated.
        this.root = new Node();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        // Start traversal from the root node.
        Node curr = root;
        // Iterate over each character in the word.
        for(char c : word.toCharArray()){
            // Calculate the index for the character (0 for 'a', 1 for 'b', etc.).
            int index = c-'a';
            // If the child node for this character doesn't exist, create it.
            if(curr.children[index]== null) curr.children[index] = new Node();
            // Move to the child node corresponding to the current character.
            curr = curr.children[index];
        }
        // Mark the end of the word at the current node.
        curr.isEndOfWord = true;
    }

    // Returns true if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Start the recursive search from the root node, at the beginning of the word (index 0).
        return func(word,0,root);
    }

    // Recursive helper function to perform the search.
    // word: the word being searched.
    // i: the current index in the word we are examining.
    // curr: the current node in the Trie we are at.
    public boolean func(String word, int i, Node curr){
        // If the current node is null, it means the path doesn't exist for the given prefix.
        if(curr == null) return false;
        // If we have reached the end of the word string.
        if(i==word.length()) {
            // Check if the current node marks the end of a valid word.
            return curr.isEndOfWord;
        }

        // Get the character at the current index.
        char c = word.charAt(i);
        // If the character is a wildcard '.'.
        if(c=='.'){
            // Iterate through all possible children (representing 'a' through 'z').
            for(int j=0;j<26;j++){
                // Recursively call func for the next character (i+1) and the child node.
                // If any of these recursive calls return true, it means a match is found.
                if(func(word, i+1, curr.children[j])) return true;
            }
            // If none of the children lead to a match, return false.
            return false;
        }else{
            // If it's a regular character.
            // Calculate the index for the character.
            int index = c-'a';
            // Recursively call func for the next character (i+1) and the corresponding child node.
            return func(word, i+1, curr.children[index]);
        }
    }

    // Inner class representing a node in the Trie.
    class Node{
        // Array of children nodes, one for each letter of the alphabet.
        Node[] children;
        // Boolean flag to indicate if this node marks the end of a word.
        boolean isEndOfWord;

        // Constructor for a Node.
        Node(){
            // Initialize the children array with nulls.
            this.children = new Node[26];
            // Initially, a new node does not mark the end of a word.
            this.isEndOfWord = false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```

## Interview Tips
1.  **Explain the Trie**: Start by explaining why a Trie is a good fit for this problem, highlighting its prefix-matching capabilities.
2.  **Handle Wildcards Clearly**: Emphasize how the recursive `func` method explores all possibilities when a '.' is encountered. Walk through an example with a wildcard.
3.  **Discuss Edge Cases**: Mention how you handle `null` nodes, reaching the end of the word, and the case where the word itself is empty (though the problem constraints might simplify this).
4.  **Complexity Trade-offs**: Be ready to discuss the time complexity for `search` with wildcards and why it can be exponential in the worst case, contrasting it with the linear time for `addWord` and wildcard-free `search`.

## Revision Checklist
- [ ] Understand the Trie data structure.
- [ ] Implement `addWord` correctly by traversing and creating nodes.
- [ ] Implement `search` using a recursive helper function.
- [ ] Correctly handle the base cases in the recursive `search` function.
- [ ] Implement the wildcard '.' logic by iterating through all children.
- [ ] Analyze time and space complexity for both `addWord` and `search`.
- [ ] Consider potential optimizations or alternative approaches (though Trie is standard here).

## Similar Problems
*   Implement Trie (Prefix Tree)
*   Word Search II
*   Add and Search Word - Data structure design (this problem)

## Tags
`Trie` `Depth-First Search` `Design` `Recursion`
