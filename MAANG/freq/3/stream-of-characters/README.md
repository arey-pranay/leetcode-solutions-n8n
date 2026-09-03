# Stream Of Characters

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Design` `Trie` `Data Stream` `Aho–Corasick Algorithm`  
**Time:** O(N + M)  
**Space:** O(N)

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
    StringBuilder sb = new StringBuilder();
    int maxLength = 0;
    public void addNode(String word){
      Node curr = root;
      for(char c : word.toCharArray()){
        int index = c-'a';
        if(curr.children[index]==null) curr.children[index] = new Node();
        curr = curr.children[index];
      }
      curr.isEndOfWord = true;
    }
    public boolean findNode(String word){
      Node curr = root;
      int ran=0;
      for(int i = word.length()-1; i>=0; i--){
        ran++;
        char c= word.charAt(i);
        int index = c-'a';
        if(curr.children[index]==null) return false; // iske aage koi valid path nhi hai, no match
        curr = curr.children[index];
        if(curr.isEndOfWord) return true; // mtlb query ka koi suffix match hogya humare given set me se
      }
      return false; // query string khtm hogya, but koi bhi isEndOfWord nhi aaya
    }
    
    public StreamChecker(String[] words) {
       for(String word : words){
            addNode((new StringBuilder(word).reverse()).toString());
            maxLength = Math.max(maxLength,word.length());
       }
    }
    
    public boolean query(char letter) {
        if(sb.length()==maxLength) sb.deleteCharAt(0);
        sb.append(letter);
        return findNode(sb.toString());
    }
    // jo bhi query se aa rha hai, that gets appended to the current string
    // we need to tell ki current string ka koi bhi suffix words me hai ya nahi.
}


```

---

---

## Quick Revision
Solve a problem where we need to track the longest suffix of a set of words. We construct a Trie-like data structure and then query it for the given character.

## Intuition
The "aha moment" here is that we can construct a Trie-like data structure where each node represents a character in the word. When we add a word, we traverse the Trie from right to left (i.e., from end of the word to start). This allows us to efficiently query for the given character by traversing the Trie from right to left and checking if the current node is the end of a word.

## Algorithm
1. Initialize a Trie-like data structure with a root node.
2. When adding a word, traverse the Trie from right to left and set `isEndOfWord` to `true` for the last node.
3. When querying a character, traverse the Trie from right to left and check if the current node is the end of a word.
4. If the current node is the end of a word, return `true`.
5. If the query string is longer than the longest suffix found so far, update the longest suffix.

## Concept to Remember
* Trie-like data structures for efficient string matching
* Traversing data structures from right to left for efficient querying
* Keeping track of the longest suffix found so far for efficient querying

## Common Mistakes
* Failing to traverse the Trie from right to left when adding words
* Failing to update the longest suffix found so far when querying a character
* Not handling the case where the query string is longer than the longest suffix found so far

## Complexity Analysis
- Time: O(N + M) where N is the total length of all words and M is the length of the query string
- Space: O(N) for storing the Trie-like data structure

## Commented Code
```java
class StreamChecker {
    class Node {
        Node[] children;
        boolean isEndOfWord;
        Node() {
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }

    Node root = new Node();
    StringBuilder sb = new StringBuilder();
    int maxLength = 0;

    // Add a word to the Trie-like data structure
    public void addNode(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    // Query the Trie-like data structure for the given character
    public boolean findNode(String word) {
        Node curr = root;
        int ran = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            ran++;
            char c = word.charAt(i);
            int index = c - 'a';
            if (curr.children[index] == null) return false; // No match
            curr = curr.children[index];
            if (curr.isEndOfWord) return true; // Match found
        }
        return false; // No match
    }

    // Initialize the Trie-like data structure with a set of words
    public StreamChecker(String[] words) {
        for (String word : words) {
            addNode((new StringBuilder(word).reverse()).toString());
            maxLength = Math.max(maxLength, word.length());
        }
    }

    // Query the Trie-like data structure for the given character
    public boolean query(char letter) {
        if (sb.length() == maxLength) sb.deleteCharAt(0);
        sb.append(letter);
        return findNode(sb.toString());
    }
}
```

## Interview Tips
* Make sure to traverse the Trie-like data structure from right to left when adding words and querying characters.
* Keep track of the longest suffix found so far to efficiently query characters.
* Use a StringBuilder to store the current query string and update it when a character is queried.

## Revision Checklist
- [ ] Understand the problem and requirements
- [ ] Implement the Trie-like data structure and query function
- [ ] Test the solution with sample inputs and edge cases
- [ ] Review and optimize the solution for efficiency and readability

## Similar Problems
* 208. Implement Trie (Prefix Tree)
* 211. Add and Remove Words (Word Dictionary)
* 1275. Find the Nearest-Star-Pattern Word

## Tags
`Array` `Hash Map` `Trie` `String Matching` `Efficient Querying`
