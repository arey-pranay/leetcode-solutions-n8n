# Design Add And Search Words Data Structure

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Depth-First Search` `Design` `Trie`  
**Time:** See complexity section  
**Space:** O(L)

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
This problem asks to design a data structure that supports adding words and searching for words, including those with wildcard characters.
A Trie (prefix tree) is the ideal data structure for this, with modifications to handle the wildcard '.' character during search.

## Intuition
The core idea is to use a Trie to store words efficiently. Each node in the Trie represents a character, and paths from the root to a node represent prefixes. When adding a word, we traverse the Trie, creating new nodes if necessary. The `isEndOfWord` flag in a node indicates if the path to that node forms a complete word.

The challenge comes with the wildcard character '.'. When searching for a word with a '.', we can't just follow a single path. Instead, we need to explore *all possible paths* that match the wildcard. This suggests a recursive or backtracking approach. If we encounter a '.', we try searching for the rest of the word from *each* of the current node's children. If any of these recursive calls return true, then the word is found.

## Algorithm
1.  **Trie Node Structure**: Define a `Node` class for the Trie. Each node will have an array of 26 children (for 'a' through 'z') and a boolean flag `isEndOfWord` to mark the end of a valid word.
2.  **WordDictionary Constructor**: Initialize the `WordDictionary` with a `root` node, which is an empty `Node`.
3.  **`addWord(String word)`**:
    *   Start at the `root` node.
    *   Iterate through each character `c` of the `word`.
    *   Calculate the index for the character (e.g., `c - 'a'`).
    *   If the child node at that index is `null`, create a new `Node` and assign it.
    *   Move to the child node.
    *   After processing all characters, mark the `isEndOfWord` flag of the current node as `true`.
4.  **`search(String word)`**:
    *   This is the public interface for searching. It calls a helper recursive function `func` starting from the `root` node and index `0` of the word.
5.  **`func(String word, int i, Node curr)` (Recursive Helper)**:
    *   **Base Case 1**: If `curr` is `null`, it means the path doesn't exist, so return `false`.
    *   **Base Case 2**: If `i` (current index in `word`) reaches the length of the `word`, it means we have successfully traversed the path. Return `curr.isEndOfWord` to check if this path actually forms a complete word.
    *   **Wildcard Handling (`.`)**: If the character `word.charAt(i)` is '.', iterate through all 26 possible children (`j` from 0 to 25). For each child, recursively call `func` with `i + 1` (move to the next character in the word) and the child node `curr.children[j]`. If *any* of these recursive calls return `true`, then the word is found, so return `true`. If none of the children lead to a match, return `false`.
    *   **Normal Character Handling**: If the character is not '.', calculate its index (`c - 'a'`). Recursively call `func` with `i + 1` and the specific child node `curr.children[index]`. Return the result of this recursive call.

## Concept to Remember
*   **Trie (Prefix Tree)**: An efficient tree-like data structure for storing and searching strings, particularly useful for prefix-based operations.
*   **Recursion/Backtracking**: Essential for handling the wildcard character, where multiple paths need to be explored.
*   **State Management in Recursion**: Passing the current index in the word and the current Trie node is crucial for tracking progress.
*   **`isEndOfWord` Flag**: A common pattern in Trie implementations to distinguish between prefixes and complete words.

## Common Mistakes
*   **Incorrect Wildcard Handling**: Not exploring all 26 children when a '.' is encountered, or incorrectly returning `false` without trying all possibilities.
*   **Off-by-One Errors in Indexing**: Mishandling the character-to-index conversion (`c - 'a'`) or the word index `i`.
*   **Missing Base Cases in Recursion**: Forgetting to handle `curr == null` or `i == word.length()`, leading to `NullPointerException` or incorrect results.
*   **Not Marking `isEndOfWord`**: Forgetting to set `isEndOfWord = true` in `addWord` means that even if a path exists, it won't be recognized as a valid word.
*   **Inefficient Search for Wildcards**: Trying to optimize wildcard search in a way that breaks the exhaustive exploration.

## Complexity Analysis
*   **Time**:
    *   `addWord(String word)`: O(L), where L is the length of the word. We traverse the Trie once for each character.
    *   `search(String word)`: O(N * 26^L) in the worst case, where L is the length of the word and N is the number of nodes in the Trie. This occurs when the word consists entirely of '.' characters. For each '.', we potentially branch out to 26 children. In a more typical scenario with few wildcards, it's closer to O(L).
*   **Space**:
    *   `addWord(String word)`: O(L) in the worst case, where L is the length of the word. This is the space used to create new nodes for a new word.
    *   `search(String word)`: O(L) due to the recursion depth, where L is the length of the word.

## Commented Code
```java
class WordDictionary {
    // The root node of our Trie. It's an empty node to start.
    Node root;

    // Constructor: Initializes the WordDictionary with an empty root node.
    public WordDictionary() {
        this.root = new Node();
    }

    // Adds a word to the data structure.
    public void addWord(String word) {
        // Start traversal from the root node.
        Node curr = root;
        // Iterate through each character of the word.
        for(char c : word.toCharArray()){
            // Calculate the index for the character ('a' -> 0, 'b' -> 1, ..., 'z' -> 25).
            int index = c-'a';
            // If the child node for this character doesn't exist, create a new one.
            if(curr.children[index]== null) curr.children[index] = new Node();
            // Move to the child node corresponding to the current character.
            curr = curr.children[index];
        }
        // After adding all characters, mark the current node as the end of a valid word.
        curr.isEndOfWord = true;
    }

    // Searches for a word in the data structure. Can contain '.' as a wildcard.
    public boolean search(String word) {
        // Start the recursive search from the root node, at index 0 of the word.
        return func(word,0,root);
    }

    // Recursive helper function for searching.
    // word: the word being searched for.
    // i: the current index in the word we are trying to match.
    // curr: the current node in the Trie we are at.
    public boolean func(String word, int i, Node curr){
        // Base Case 1: If the current node is null, it means the path doesn't exist.
        if(curr == null) return false;
        // Base Case 2: If we have reached the end of the word string.
        if(i==word.length()) {
            // We have successfully traversed the path for the word.
            // Return true only if this node marks the end of a complete word.
            return curr.isEndOfWord;
        }

        // Get the current character from the word.
        char c = word.charAt(i);

        // If the current character is a wildcard '.'.
        if(c=='.'){
            // We need to explore all possible children of the current node.
            for(int j=0;j<26;j++){
                // Recursively call func for the next character (i+1) and each child.
                // If any of these recursive calls return true, it means a match is found.
                if(func(word, i+1, curr.children[j])) return true;
            }
            // If none of the children lead to a match, return false.
            return false;
        }else{
            // If the current character is a normal letter.
            // Calculate its index.
            int index = c-'a';
            // Recursively call func for the next character (i+1) and the specific child node.
            // The result of this call determines if the rest of the word can be found.
            return func(word, i+1, curr.children[index]);
        }
    }

    // Inner class representing a node in the Trie.
    class Node{
        // Array of child nodes, one for each letter 'a' through 'z'.
        Node[] children;
        // Flag to indicate if this node marks the end of a complete word.
        boolean isEndOfWord;

        // Constructor for a Trie node.
        Node(){
            // Initialize children array with 26 null pointers.
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
*   **Explain the Trie First**: Before diving into code, clearly explain what a Trie is and why it's suitable for this problem.
*   **Handle Wildcards Separately**: Emphasize how the '.' character requires a different search strategy (recursion/backtracking) compared to normal characters.
*   **Trace Examples**: Walk through a few examples, especially with wildcards, to demonstrate your understanding of the `func` method's logic. For instance, search for "a.b" in a Trie containing "axb" and "ayb".
*   **Discuss Complexity**: Be prepared to analyze the time and space complexity, paying special attention to the worst-case scenario for wildcard searches.
*   **Edge Cases**: Consider edge cases like empty strings, searching for a word that is a prefix of another word, or searching for a word that doesn't exist.

## Revision Checklist
- [ ] Understand the Trie data structure.
- [ ] Implement `addWord` correctly by traversing and creating nodes.
- [ ] Implement `search` using a recursive helper function.
- [ ] Handle the base cases in the recursive `func` (null node, end of word).
- [ ] Implement wildcard '.' logic by iterating through all children.
- [ ] Implement normal character logic by following the specific child.
- [ ] Ensure `isEndOfWord` is correctly set and checked.
- [ ] Analyze time and space complexity for both operations.
- [ ] Consider edge cases like empty words or empty Trie.

## Similar Problems
*   Implement Trie (Prefix Tree)
*   Word Search II
*   Add and Search Word - Data structure design (this problem)

## Tags
`Trie` `Design` `Recursion` `Backtracking`
