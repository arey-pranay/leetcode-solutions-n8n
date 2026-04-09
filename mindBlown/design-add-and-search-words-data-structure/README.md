# Design Add And Search Words Data Structure

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Depth-First Search` `Design` `Trie`  
**Time:** See complexity section  
**Space:** See complexity section

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
This problem asks to design a data structure that supports adding words and searching for words, including those with wildcard characters ('.'). A Trie (prefix tree) is the most suitable data structure for this.

## Intuition
The core idea is to use a Trie to store the words. Each node in the Trie represents a character, and a path from the root to a node represents a prefix. When adding a word, we traverse the Trie, creating new nodes if necessary, and mark the end of a word. For searching, we traverse the Trie based on the characters in the word. The wildcard '.' adds a twist: when we encounter a '.', we need to explore all possible children of the current node. This naturally leads to a recursive or iterative depth-first search (DFS) approach.

## Algorithm
1.  **Trie Node Structure**: Define a `Node` class with:
    *   An array `children` of size 26 (for 'a' through 'z').
    *   A boolean flag `isEndOfWord` to indicate if a word ends at this node.
2.  **WordDictionary Constructor**: Initialize the `root` of the Trie to a new `Node`.
3.  **`addWord(String word)` Method**:
    *   Start from the `root` node.
    *   Iterate through each character `c` of the `word`.
    *   Calculate the index `c - 'a'`.
    *   If `curr.children[index]` is `null`, create a new `Node` and assign it.
    *   Move `curr` to `curr.children[index]`.
    *   After processing all characters, set `curr.isEndOfWord = true`.
4.  **`search(String word)` Method**:
    *   This method will be a wrapper that calls a recursive helper function `func`.
    *   Call `func(word, 0, root)`.
5.  **`func(String word, int i, Node curr)` (Recursive Helper)**:
    *   **Base Case 1**: If `curr` is `null`, it means the path doesn't exist, so return `false`.
    *   **Base Case 2**: If `i` reaches the end of the `word` (`i == word.length()`), return `curr.isEndOfWord`. This checks if the current path represents a complete word.
    *   Get the current character `c = word.charAt(i)`.
    *   **If `c` is a wildcard ('.')**:
        *   Iterate through all possible children (from `j = 0` to `25`).
        *   Recursively call `func(word, i + 1, curr.children[j])`.
        *   If any of these recursive calls return `true`, it means a match is found, so return `true`.
        *   If the loop finishes without finding a match, return `false`.
    *   **If `c` is a regular character**:
        *   Calculate the index `index = c - 'a'`.
        *   Recursively call `func(word, i + 1, curr.children[index])`.
        *   Return the result of this recursive call.

## Concept to Remember
*   **Trie (Prefix Tree)**: Efficient for prefix-based searches and storing strings.
*   **Recursion/Backtracking**: Essential for handling wildcard characters, exploring multiple paths.
*   **Depth-First Search (DFS)**: The search logic naturally follows a DFS pattern.
*   **Object-Oriented Design**: Encapsulating data and behavior within classes.

## Common Mistakes
*   **Incorrect Trie Node Implementation**: Forgetting to initialize the `children` array or the `isEndOfWord` flag.
*   **Handling Wildcards Incorrectly**: Not iterating through all 26 children when a '.' is encountered, or incorrectly handling the index for '.' itself.
*   **Off-by-One Errors in Recursion**: Incorrectly managing the index `i` or the word length in recursive calls.
*   **Not Checking `curr == null`**: Failing to handle cases where a path in the Trie doesn't exist.
*   **Forgetting `isEndOfWord` Check**: Returning `true` for a prefix that isn't a complete word.

## Complexity Analysis
*   **Time**:
    *   `addWord`: O(L), where L is the length of the word. We traverse the Trie once for each character.
    *   `search`: O(N * 26^L) in the worst case, where N is the number of words in the Trie and L is the length of the search word. This occurs when the search word consists entirely of '.' characters, forcing a full traversal of all possible paths. In practice, if the Trie is sparse or the search word has few wildcards, it's much faster, closer to O(L).
*   **Space**:
    *   O(Total number of characters in all words) for storing the Trie. Each character in each word adds at most one node.

## Commented Code
```java
class WordDictionary {
    // The root node of our Trie. It doesn't represent any character itself.
    Node root;

    // Constructor to initialize the WordDictionary.
    public WordDictionary() {
        // Create the root node when the dictionary is instantiated.
        this.root = new Node();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        // Start traversal from the root node.
        Node curr = root;
        // Iterate over each character in the word.
        for(char c : word.toCharArray()){
            // Calculate the index for the character ('a' -> 0, 'b' -> 1, ..., 'z' -> 25).
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
        // Start the recursive search from the root node at index 0 of the word.
        return func(word,0,root);
    }

    // Recursive helper function for searching.
    // word: the word being searched.
    // i: the current index in the word we are examining.
    // curr: the current node in the Trie we are at.
    public boolean func(String word, int i, Node curr){
        // If the current node is null, it means the path doesn't exist in the Trie.
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
            // Iterate through all possible children (a-z).
            for(int j=0;j<26;j++){
                // Recursively call func for the next character in the word and the child node.
                // If any of these recursive calls return true, it means we found a match.
                if(func(word, i+1, curr.children[j])) return true;
            }
            // If none of the children lead to a match, return false.
            return false;
        }else{ // If the character is a regular letter.
            // Calculate the index for the character.
            int index = c-'a';
            // Recursively call func for the next character in the word and the specific child node.
            return func(word, i+1, curr.children[index]);
        }
    }

    // Inner class representing a node in the Trie.
    class Node{
        // Array of child nodes, one for each letter of the alphabet.
        Node[] children;
        // Flag to indicate if a word ends at this node.
        boolean isEndOfWord;

        // Constructor for a Trie node.
        Node(){
            // Initialize the children array with 26 null pointers.
            this.children = new Node[26];
            // Initially, no word ends at this node.
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
1.  **Explain the Trie First**: Before diving into code, clearly explain what a Trie is and why it's suitable for this problem.
2.  **Handle Wildcards Explicitly**: Emphasize how the '.' character requires exploring multiple branches, and how recursion or DFS is a natural fit.
3.  **Trace Examples**: Walk through a few examples, especially with wildcards, to demonstrate your understanding of the `search` function's logic.
4.  **Discuss Complexity**: Be prepared to analyze both time and space complexity, and explain the worst-case scenario for `search` due to wildcards.
5.  **Edge Cases**: Consider edge cases like empty strings for `addWord` or `search`, or searching for a word that is a prefix of another but not a full word.

## Revision Checklist
- [ ] Understand the Trie data structure.
- [ ] Implement the `Node` class correctly.
- [ ] Implement `addWord` to build the Trie.
- [ ] Implement `search` using recursion.
- [ ] Handle the '.' wildcard by exploring all children.
- [ ] Correctly manage the index and base cases in the recursive `search` helper.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing examples.

## Similar Problems
*   Implement Trie (Prefix Tree)
*   Word Search II
*   Add and Search Word - Data structure design (this problem)

## Tags
`Trie` `Depth-First Search` `Design` `Recursion`
