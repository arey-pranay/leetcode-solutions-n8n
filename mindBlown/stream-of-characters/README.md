# Stream Of Characters

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Design` `Trie` `Data Stream` `Aho–Corasick Algorithm`  
**Time:** O(m + n)  
**Space:** O(n)

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
The problem requires implementing a StreamChecker class that can efficiently check if any suffix of the current query string is a word in a given set of words. The solution involves creating a trie data structure and maintaining a StringBuilder to store the current query string.

## Intuition
The key insight is to store the words in the trie in reverse order and maintain a StringBuilder to store the current query string. This allows us to efficiently check if any suffix of the current query string is a word in the given set.

## Algorithm

1. Create a trie data structure to store the words in reverse order.
2. Maintain a StringBuilder to store the current query string.
3. In the query method, append the current character to the StringBuilder.
4. Use the findNode method to check if any suffix of the current query string is a word in the given set.
5. If the current query string is a word in the given set, return true. Otherwise, return false.

## Concept to Remember

* Trie data structure: a tree-like data structure in which every node stores a string and a set of child nodes.
* String reversal: the process of reversing a string by iterating over it in reverse order.
* Suffix matching: the process of finding all suffixes of a given string that match a pattern.

## Common Mistakes

* Failing to store the words in the trie in reverse order.
* Not maintaining a StringBuilder to store the current query string.
* Not checking if any suffix of the current query string is a word in the given set.

## Complexity Analysis
- Time: O(m + n) where m is the length of the query string and n is the maximum length of a word in the given set.
- Space: O(n) for storing the trie and O(m) for storing the StringBuilder.

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

    public void addNode(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    public boolean findNode(String word) {
        Node curr = root;
        int ran = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            ran++;
            char c = word.charAt(i);
            int index = c - 'a';
            if (curr.children[index] == null) return false; // iske aage koi valid path nhi hai, no match
            curr = curr.children[index];
            if (curr.isEndOfWord) return true; // mtlb query ka koi suffix match hogya humare given set me se
        }
        return false; // query string khtm hogya, but koi bhi isEndOfWord nhi aaya
    }

    public StreamChecker(String[] words) {
        for (String word : words) {
            addNode((new StringBuilder(word).reverse()).toString());
            maxLength = Math.max(maxLength, word.length());
        }
    }

    public boolean query(char letter) {
        if (sb.length() == maxLength) sb.deleteCharAt(0);
        sb.append(letter);
        return findNode(sb.toString());
    }
}
```

## Interview Tips

* Make sure to understand the problem requirements carefully.
* Use a data structure like trie to efficiently store and search the words.
* Maintain a StringBuilder to store the current query string.
* Think about the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem requirements carefully.
- [ ] Use a data structure like trie to efficiently store and search the words.
- [ ] Maintain a StringBuilder to store the current query string.
- [ ] Think about the time and space complexity of your solution.

## Similar Problems
* LeetCode: 677. Robot Return to the Origin
* LeetCode: 134. Gas Station
* LeetCode: 151. Reverse Words in a String

## Tags
`Array` `Hash Map` `Trie` `String` `Substring` `String Reversal`
