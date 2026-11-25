# Palindrome Pairs

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Trie` `Hash Function`  
**Time:** O(n*m)  
**Space:** O(n*m)

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
Palindrome Pairs problem involves finding all pairs of strings from a given array that form a palindrome when combined. The solution uses a Trie data structure to efficiently search for matching pairs.

## Intuition
The key insight is that for a palindrome to be formed, either the first and last characters of the combined string must match, or the middle characters must be the same (if the combined string has an even length). The Trie data structure is well-suited for this problem because it can efficiently store and search for substrings.

## Algorithm

1. Create a Trie data structure with a TrieNode class.
2. Insert each word from the input array into the Trie in reverse order.
3. Search for each word in the Trie to find its corresponding palindrome pairs.
4. If a word has a corresponding index in the Trie, add the pair to the result list.

## Concept to Remember

* Tries can be used to efficiently store and search for substrings.
* The key to solving this problem is to use the Trie to find matching pairs, rather than trying to directly compare each pair of strings.

## Common Mistakes

* Failing to handle the case where the combined string has an even length.
* Not using a Trie to efficiently search for matching pairs.
* Not considering the case where the first and last characters of the combined string do not match.

## Complexity Analysis
- Time: O(n*m) where n is the number of words and m is the maximum length of a word.
- Space: O(n*m) for storing the Trie.

## Commented Code
```java
class Solution {
    class TrieNode {
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
        for (int i = 0; i < n; i++) {
            insert(trie, words[i], i); //har word reverse direction me insert, and find the okIndices
        }
        for (int i = 0; i < n; i++) {
            search(trie, words[i], i); //forward direction me har word ke valid partners search
        }

        return ans;
    }

    private void insert(TrieNode root, String word, int index) {
        TrieNode curr = root;
        int n = word.length();
        for (int i = n - 1; i >= 0; i--) {
            if (isPalindrome(word, 0, i)) curr.okIndex.add(index);
            int c = word.charAt(i) - 'a';
            if (curr.children[c] == null) curr.children[c] = new TrieNode();
            curr = curr.children[c];
        }
        curr.index = index;
        curr.okIndex.add(index);
    }

    private void search(TrieNode root, String word, int index) {
        TrieNode curr = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            if (curr.index != -1 && curr.index != index && isPalindrome(word, i, n - 1)) ans.add(Arrays.asList(index, curr.index));
            int c = word.charAt(i) - 'a';
            if (curr.children[c] == null) return;
            curr = curr.children[c];
        }
        for (int ok : curr.okIndex) if (ok != index) ans.add(Arrays.asList(index, ok));
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
```

## Interview Tips

* Make sure to handle all edge cases, including empty strings and words with different lengths.
* Use a Trie data structure to efficiently search for matching pairs.
* Consider using a HashSet to keep track of visited words to avoid duplicates.

## Revision Checklist
- [ ] Understand the Trie data structure and its applications.
- [ ] Implement the Trie data structure correctly.
- [ ] Use the Trie to efficiently search for matching pairs.
- [ ] Handle all edge cases, including empty strings and words with different lengths.

## Similar Problems
* 316. Remove Duplicates from Sorted Array
* 349. Intersection of Two Arrays
* 88. Merge Sorted Array
