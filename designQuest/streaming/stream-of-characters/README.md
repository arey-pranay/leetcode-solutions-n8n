# Stream Of Characters

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Design` `Trie` `Data Stream`  
**Time:** O(n + m)  
**Space:** O(n + m)

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
        stream.append(letter);
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
Given a list of words, implement a stream checker that checks if the given word is a suffix of any word in the list.
We solve this problem by using a trie data structure to store the reversed words and checking for matches in the query function.

## Intuition
The key insight here is to reverse each word before adding it to the trie. This allows us to efficiently check for suffixes by traversing the trie from left to right.
By storing the maximum length of the words, we can prune branches in the trie that are too long and return false early if necessary.

## Algorithm
1. Create a trie data structure with Node class containing children array and isEndOfWord flag.
2. In the constructor, add each reversed word to the trie by traversing from root node down.
3. Store the maximum length of the words in maxLen variable.
4. In query function, append the given letter to stream StringBuilder and traverse down the trie from left to right.
5. If a null child is encountered, return false immediately. Otherwise, if an end-of-word flag is set, return true.
6. Prune branches that are too long by checking against maxLen.

## Concept to Remember
* Trie data structure for efficient string matching
* Reversing words to enable suffix checking
* Traversing down the trie from left to right

## Common Mistakes
* Failing to reverse words before adding them to the trie
* Not pruning branches that are too long in query function
* Inefficient memory usage due to lack of bounds on StringBuilder size

## Complexity Analysis
- Time: O(n + m) - where n is the total length of all words and m is the maximum length of a word
  - Why: We add each character once when constructing the trie, and traverse down the trie at most maxLen times in query function.
- Space: O(n + m) - for storing trie nodes and StringBuilder

## Commented Code
```java
class StreamChecker {
    class Node{
        // Array of children nodes (26 letters)
        Node[] children;
        // Flag to indicate end-of-word node
        boolean isEndOfWord;

        // Initialize with empty array and false flag
        Node(){
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }

    // Root node of trie
    Node root = new Node();
    // StringBuilder to store input stream
    StringBuilder stream = new StringBuilder("");
    // Maximum length of words seen so far
    int maxLen = 0;

    /**
     * Add word to trie by traversing from root down.
     */
    private void addWord(String word){
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        // Mark end-of-word node
        curr.isEndOfWord = true;
    }

    /**
     * Constructor: add reversed words to trie and store maxLen.
     */
    public StreamChecker(String[] words) {
        for(String word : words){
            // Reverse each word before adding
            addWord(new StringBuilder(word).reverse().toString());
            maxLen = Math.max(maxLen,word.length());
        }
    }

    /**
     * Query function: append letter to stream and traverse down trie.
     */
    public boolean query(char letter) {
        stream.append(letter);
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
```

## Interview Tips
* Make sure to reverse words before adding them to the trie.
* Prune branches that are too long in query function to avoid unnecessary traversals.
* Use StringBuilder efficiently and bound its size to prevent excessive memory usage.

## Revision Checklist
- [ ] Review trie data structure and its implementation.
- [ ] Ensure reversal of words is correctly handled in constructor.
- [ ] Verify query function correctness for suffix matching.
- [ ] Address potential issues with StringBuilder size and pruning branches.

## Similar Problems
* 211. Design Add and Search Words Data Structure (LeetCode)
* 677. Map Sum Pairs (LeetCode)
