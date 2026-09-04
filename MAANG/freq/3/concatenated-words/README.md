# Concatenated Words

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Dynamic Programming` `Depth-First Search` `Trie` `Sorting`  
**Time:** O(N \* M \* 2^M)  
**Space:** O(N \* M)

---

## Solution (java)

```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
      Arrays.sort(words,(a,b) -> a.length()-b.length());
      List<String> ans = new ArrayList<>();
      HashSet<String> set = new HashSet<>();
      for(String word : words) {
        if(func(word,0,set, new Boolean[word.length()])) ans.add(word);
        set.add(word);
      }
      return ans;
    }
    public boolean func(String word, int start, HashSet<String> set, Boolean[] memo){
      int n = word.length();
      if(start==n) return true;
      if(memo[start] != null) return memo[start];
      for(int end=start;end<n;end++)if(set.contains(word.substring(start,end+1)) && func(word,end+1,set,memo)) return memo[start]= true;
      return memo[start]=false;
    }
}
```

---

---

## Quick Revision
Find all words in a dictionary that can be formed by concatenating other words in the dictionary.
Use a depth-first search (DFS) approach with memoization to efficiently check for concatenated words.

## Intuition
The key insight here is that we can use a combination of sorting the words by length and a DFS approach with memoization to efficiently check for concatenated words. By sorting the words by length, we can reduce the number of possible combinations to check, making the problem more manageable. The DFS approach with memoization allows us to avoid re-checking the same sub-words multiple times, significantly improving the efficiency of the algorithm.

## Algorithm
1. Sort the input words by length in descending order.
2. Create an empty list to store the concatenated words.
3. Create an empty set to store the words that have been seen so far.
4. Iterate over each word in the sorted list:
   1. Check if the word can be formed by concatenating other words in the set using the `func` method.
   2. If it can be formed, add the word to the list of concatenated words.
   3. Add the word to the set of seen words.
5. Return the list of concatenated words.

## Concept to Remember
* **Memoization**: storing the results of expensive function calls and reusing them when the same inputs occur again.
* **Depth-First Search (DFS)**: a traversal strategy in which the algorithm explores as far as possible along each branch before backtracking.
* **Hash Set**: a data structure that stores unique elements and provides efficient lookup, insertion, and deletion operations.

## Common Mistakes
* Not sorting the words by length, leading to inefficient DFS.
* Not using memoization, resulting in redundant calculations and exponential time complexity.
* Not handling the edge case where the input list is empty.

## Complexity Analysis
- Time: O(N \* M \* 2^M) where N is the number of words and M is the maximum length of a word.
- Space: O(N \* M) for storing the memoization table.

## Commented Code
```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // Sort the words by length in descending order
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        List<String> ans = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        
        // Iterate over each word in the sorted list
        for (String word : words) {
            // Check if the word can be formed by concatenating other words in the set
            if (func(word, 0, set, new Boolean[word.length()])) {
                ans.add(word);
            }
            // Add the word to the set of seen words
            set.add(word);
        }
        
        return ans;
    }
    
    public boolean func(String word, int start, HashSet<String> set, Boolean[] memo) {
        // Base case: if the start index is equal to the length of the word, return true
        if (start == word.length()) {
            return true;
        }
        
        // If the memoization table already contains the result for the current start index, return it
        if (memo[start] != null) {
            return memo[start];
        }
        
        // Iterate over each possible end index for the current substring
        for (int end = start; end < word.length(); end++) {
            // Check if the current substring is in the set of seen words
            if (set.contains(word.substring(start, end + 1)) && func(word, end + 1, set, memo)) {
                // If it is, and the recursive call returns true, set the memoization table to true and return true
                memo[start] = true;
                return true;
            }
        }
        
        // If no combination of words forms the current word, set the memoization table to false and return false
        memo[start] = false;
        return false;
    }
}
```

## Interview Tips
* Make sure to explain the intuition behind the algorithm and the trade-offs made.
* Emphasize the importance of memoization in reducing the time complexity.
* Be prepared to defend the sorting step and the use of a hash set.

## Revision Checklist
- [ ] Understand the problem and the input constraints.
- [ ] Explain the intuition behind the algorithm.
- [ ] Implement the algorithm from scratch, without relying on existing code.
- [ ] Test the algorithm with edge cases and large inputs.

## Similar Problems
* [127. Word Ladder](https://leetcode.com/problems/word-ladder/)
* [79. Word Search](https://leetcode.com/problems/word-search/)
* [291. Word Pattern II](https://leetcode.com/problems/word-pattern-ii/)

## My Notes
also check word break 1 and 2
