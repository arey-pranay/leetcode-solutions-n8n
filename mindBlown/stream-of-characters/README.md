# Stream Of Characters

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Design` `Trie` `Data Stream`  
**Time:** O(N + M)  
**Space:** O(N + M)

---

## Solution (java)

```java
class StreamChecker {
    class Node{
        Node[] children;
        boolean isEndOfWord;
        Node(){
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }
    Node root = new Node();
    StringBuilder stream = new StringBuilder("");
    int maxLen = 0;
    private void addWord(String word){
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }   curr.isEndOfWord = true;
    }
    public StreamChecker(String[] words) {
        for(String word : words){
            addWord(new StringBuilder(word).reverse().toString()); 
            maxLen = Math.max(maxLen,word.length());
        }      
    }
    public boolean query(char letter) {
        stream.append(letter); //ab
        Node curr = root;
        for(int i=stream.length()-1; i>=0 && stream.length() - i <= maxLen; i--){
            int index = stream.charAt(i)-'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
            if(curr.isEndOfWord) return true;
        }
        return false;
    }
}

//     cd f kl
    
//        root
//      /   \  \
//     d    f   l
//     /         \
//    c           k .
        // a 
        // b 
        // c 
        // d true
        // e 
        // f true
        // g 
        // h
        // i
        // j
        // k
        // l true

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
```

---

---

## Quick Revision
Given a list of words, build an automaton that can query whether a given character stream matches any of the words.
Solve it by building a trie and traversing it in reverse order.

## Intuition
The problem requires us to check if a given character stream matches any word in the input array. We can use a trie data structure to solve this problem efficiently. By building a trie from the reversed words, we can traverse the trie in reverse order for each query, effectively matching the character stream with the words.

## Algorithm
1. Build a trie using the reversed words and store it as `root`.
2. For each word, reverse it and add it to the trie by traversing through its characters.
3. Store the maximum length of any word in `maxLen`.
4. When querying a character stream, start from the end of the stream and traverse the trie in reverse order until we reach a leaf node or exceed the maximum length.

## Concept to Remember
* Trie data structure and its applications.
* Dynamic programming techniques for solving problems with overlapping subproblems.
* Importance of considering time and space complexity when designing algorithms.

## Common Mistakes
* Failing to consider the reversed words while building the trie.
* Not properly handling edge cases, such as an empty character stream or a word that exceeds `maxLen`.
* Not using dynamic programming techniques to optimize the query operation.

## Complexity Analysis
- Time: O(N + M) - N is the total number of characters in all words and M is the maximum length of any word. This is because we build the trie once and traverse it for each query.
- Space: O(N + M) - We need to store the trie, which has a size proportional to the input.

## Commented Code
```java
class StreamChecker {
    class Node{
        // Children nodes for each character (0-25)
        Node[] children;
        // Flag to indicate if this node represents the end of a word
        boolean isEndOfWord;
        Node(){
            // Initialize children array and flag
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }
    
    // Root node of the trie
    Node root = new Node();
    // Character stream being queried
    StringBuilder stream = new StringBuilder("");
    // Maximum length of any word
    int maxLen = 0;

    /**
     * Add a word to the trie by traversing its characters in reverse order.
     */
    private void addWord(String word){
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            // If the child node doesn't exist, create it
            if(curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true; // Mark the end of the word
    }

    /**
     * Constructor to initialize the trie with reversed words.
     */
    public StreamChecker(String[] words) {
        for(String word : words){
            addWord(new StringBuilder(word).reverse().toString()); 
            maxLen = Math.max(maxLen,word.length());
        }      
    }

    /**
     * Query whether a character stream matches any word in the trie.
     */
    public boolean query(char letter) {
        // Append the new character to the stream
        stream.append(letter); 
        Node curr = root;
        for(int i=stream.length()-1; i>=0 && stream.length() - i <= maxLen; i--){
            int index = stream.charAt(i)-'a';
            if(curr.children[index] == null) return false; // No match found
            curr = curr.children[index];
            if(curr.isEndOfWord) return true; // Found a matching word
        }
        return false;
    }
}
```

## Interview Tips

* Be prepared to explain the time and space complexity of your solution.
* Show how you handle edge cases, such as an empty character stream or a word that exceeds `maxLen`.
* Consider using dynamic programming techniques to optimize the query operation.

## Revision Checklist
- [ ] Understand the problem requirements.
- [ ] Build a trie from reversed words efficiently.
- [ ] Optimize the query operation using dynamic programming.
- [ ] Handle edge cases properly.

## Similar Problems

* LeetCode: 211. Design Add and Search Words Data Structure
* LeetCode: 677. Map Sum Pairs
