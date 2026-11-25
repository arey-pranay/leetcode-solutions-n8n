# Palindrome Pairs

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Trie` `Hash Function`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int index = -1;
        List<Integer> okIndex = new ArrayList<>(); //konse indices wale string ke iske niche wale chars palindrome hai
    }
    TrieNode trie = new TrieNode();
    HashSet<String> done = new HashSet<>();
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        // kisi bhi pair me, ek first-last match to hoga hi and agr dono even length ke hai, to dusra first-last match bhi hoga
        // otherwise no match
        // so let's try trie..
              
        int n = words.length;
        
        // unmatched characters agr 0 hai, ya palindrome hai, then okIndex;
        for(int i=0;i<n;i++) insert(trie,words[i],i); //har word reverse direction me insert, and find the okIndices
        for(int i=0;i<n;i++) search(trie,words[i],i); //forward direction me har word ke valid partners search
        
        return ans;
    }
    private void insert(TrieNode root, String word, int index){
        TrieNode curr = root;
        int n = word.length();
        for(int i=n-1;i>=0;i--){
            if(isPalindrome(word, 0, i)) curr.okIndex.add(index);
            int c = word.charAt(i)-'a';
            if(curr.children[c]==null) curr.children[c] = new TrieNode();
            curr = curr.children[c];
        }
        curr.index = index;
        curr.okIndex.add(index);
    }
    //tum kuch smjh pa rhi ho, ek baar smjhao to zara
    private void search(TrieNode root, String word, int index){
        TrieNode curr = root;
        int n = word.length();
        for(int i=0;i<n;i++){
            if(curr.index != -1 && curr.index != index && isPalindrome(word,i,n-1)) ans.add(Arrays.asList(index,curr.index));
            int c = word.charAt(i)-'a';
            if(curr.children[c]==null) return;
            curr = curr.children[c];
        }
        for(int ok : curr.okIndex) if(ok != index) ans.add(Arrays.asList(index,ok));


    }

    private boolean isPalindrome(String s , int i , int j){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;j--;
        }
        return true;
    }
} 


            //       -
            // a   d   l   s.
            // b   c   l   s
            // c   b   s.  s 4
            // d.   a.     l
            //             l
                        
                    
            //     -
            // d   a
            // c   b
            // b   c
            // a   d
```

---

---
## Quick Revision
The problem is to find all pairs of words in a given list that form a palindrome. The solution uses a Trie data structure to efficiently find the pairs.

## Intuition
The key insight is that for a pair of words to form a palindrome, they must have a common prefix or suffix. We can use a Trie to store the words in both forward and reverse directions, and then find the pairs by checking for common prefixes or suffixes.

## Algorithm
1. Create a TrieNode class with children and okIndex (a list of indices of words that have a common suffix).
2. Create a Trie and insert each word in both forward and reverse directions.
3. For each word, search for common prefixes or suffixes in the Trie.
4. If a common prefix or suffix is found, add the pair to the result list.

## Concept to Remember
* Trie data structure
* String manipulation (reversing strings)
* Dynamic programming (using memoization to store common prefixes or suffixes)

## Common Mistakes
* Failing to consider the case where a word is a palindrome itself.
* Not handling the case where a pair of words has a common prefix but not a suffix.
* Not using a Trie to efficiently store and search for common prefixes or suffixes.

## Complexity Analysis
- Time: O(n^2) where n is the number of words, since we are inserting each word in both forward and reverse directions and searching for common prefixes or suffixes.
- Space: O(n) for storing the Trie.

## Commented Code
```java
class Solution {
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int index = -1;
        List<Integer> okIndex = new ArrayList<>(); //konse indices wale string ke iske niche wale chars palindrome hai
    }
    TrieNode trie = new TrieNode();
    HashSet<String> done = new HashSet<>();
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        
        // unmatched characters agr 0 hai, ya palindrome hai, then okIndex;
        for(int i=0;i<n;i++) {
            insert(trie,words[i],i); //har word reverse direction me insert, and find the okIndices
        }
        
        for(int i=0;i<n;i++) {
            search(trie,words[i],i); //forward direction me har word ke valid partners search
        }
        
        return ans;
    }
    
    private void insert(TrieNode root, String word, int index){
        TrieNode curr = root;
        int n = word.length();
        for(int i=n-1;i>=0;i--){
            if(isPalindrome(word, 0, i)) curr.okIndex.add(index);
            int c = word.charAt(i)-'a';
            if(curr.children[c]==null) curr.children[c] = new TrieNode();
            curr = curr.children[c];
        }
        curr.index = index;
        curr.okIndex.add(index);
    }
    
    private void search(TrieNode root, String word, int index){
        TrieNode curr = root;
        int n = word.length();
        for(int i=0;i<n;i++){
            if(curr.index != -1 && curr.index != index && isPalindrome(word,i,n-1)) {
                ans.add(Arrays.asList(index,curr.index));
            }
            int c = word.charAt(i)-'a';
            if(curr.children[c]==null) return;
            curr = curr.children[c];
        }
        for(int ok : curr.okIndex) if(ok != index) {
            ans.add(Arrays.asList(index,ok));
        }
    }
    
    private boolean isPalindrome(String s , int i , int j){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;j--;
        }
        return true;
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and identify the key insights.
* Use a Trie data structure to efficiently store and search for common prefixes or suffixes.
* Handle edge cases, such as words that are palindromes themselves.
* Use dynamic programming to memoize common prefixes or suffixes.

## Revision Checklist
- [ ] Understand the problem and key insights
- [ ] Use a Trie data structure to efficiently store and search for common prefixes or suffixes
- [ ] Handle edge cases, such as words that are palindromes themselves
- [ ] Use dynamic programming to memoize common prefixes or suffixes

## Similar Problems
* 1343. Number of Steps to Reduce to Zero
* 1406. Stone Game II
* 1511. Leftmost Column with at Least a Given Number of 1s
