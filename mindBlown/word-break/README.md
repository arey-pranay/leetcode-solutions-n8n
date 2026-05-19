# Word Break

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Dynamic Programming` `Trie` `Memoization`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        StringBuilder sb= new StringBuilder(s);
        HashSet<String> hs = new HashSet<>(wordDict);
        memo= new Boolean[s.length()+1];
        return func(0,0,sb,hs);
        
    }
    public boolean func(int i, int j, StringBuilder sb, HashSet<String> hs){
        if(i >= sb.length()) return true;
        if(j >= sb.length()) return false;
        if(memo[i] != null) return memo[i];
        if(hs.contains(sb.substring(i,j+1))){
            return memo[i] = (func(j+1,j+1,sb,hs) || func(i,j+1,sb,hs));
        } else return memo[i] = func(i,j+1,sb,hs);
    }
}
```

---

---

## Quick Revision
Solve whether a given string can be segmented into words from a dictionary. We use dynamic programming and memoization to optimize the solution.

## Intuition
The key insight here is that we need to try all possible word breaks, but we only need to consider each substring once due to the properties of dynamic programming. This leads us to use memoization to avoid redundant calculations.

## Algorithm

1. Create a hash set `hs` from the given list of words for efficient lookup.
2. Initialize a boolean array `memo` with size `s.length + 1` to store the results of subproblems.
3. Define a helper function `func(i, j, sb, hs)` that takes four parameters: current index `i`, current substring's length `j`, string builder `sb`, and hash set `hs`.
4. In the `func` function:
   1. If `i >= sb.length()`, return true as we've reached the end of the string.
   2. If `j >= sb.length()`, return false as it's not a valid substring.
   3. If `memo[i] != null`, return the memoized result directly.
   4. Check if the current substring (from `i` to `j`) is in the hash set `hs`. If yes, recursively call `func(j+1, j+1, sb, hs)` or `func(i, j+1, sb, hs)`, and memoize the result.
   5. Otherwise, recursively call `func(i, j+1, sb, hs)`.
5. In the main function, call `func(0, 0, sb, hs)` with an initial substring of length 0.

## Concept to Remember

* Dynamic Programming: Breaking down a problem into smaller subproblems and solving each one only once.
* Memoization: Storing the results of expensive function calls and reusing them when necessary to avoid redundant calculations.
* Hash Set: An efficient data structure for storing unique elements and checking membership in constant time.

## Common Mistakes

* Not initializing memo array correctly, leading to incorrect or missing results.
* Forgetting to handle edge cases like an empty string or a dictionary with no words.
* Misunderstanding the properties of dynamic programming and memoization, resulting in redundant calculations or incorrect solutions.

## Complexity Analysis
- Time: O(n^2) - where n is the length of the input string, due to the worst-case scenario of checking all substrings.
- Space: O(n) - for storing the memoized results and other auxiliary data structures.

## Commented Code
```java
class Solution {
    Boolean[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        // Create a hash set from the given list of words
        HashSet<String> hs = new HashSet<>(wordDict);
        
        // Initialize memo array with size s.length + 1
        memo = new Boolean[s.length() + 1];
        
        // Call helper function func(0, 0, sb, hs) to start solving the problem
        return func(0, 0, new StringBuilder(s), hs);
    }

    public boolean func(int i, int j, StringBuilder sb, HashSet<String> hs){
        // Base case: if we've reached the end of the string, return true
        if(i >= sb.length()) return true;
        
        // Base case: if current substring's length exceeds the input string's length, return false
        if(j >= sb.length()) return false;
        
        // Check memoization for this subproblem
        if(memo[i] != null) return memo[i];
        
        // Check if current substring is in hash set
        if(hs.contains(sb.substring(i,j+1))){
            // If it's a valid word, recursively call func with updated parameters and memoize result
            return memo[i] = (func(j+1,j+1,sb,hs) || func(i,j+1,sb,hs));
        } else {
            // If not a valid word, recursively call func with updated parameters and memoize result
            return memo[i] = func(i,j+1,sb,hs);
        }
    }
}
```

## Interview Tips

* Make sure to explain the dynamic programming approach and how memoization is used to optimize the solution.
* Be prepared to handle edge cases like an empty string or a dictionary with no words.
* Emphasize the importance of correctly initializing the memo array and handling base cases.

## Revision Checklist
- [ ] Review dynamic programming concepts and practice more problems on this topic.
- [ ] Practice implementing memoization in different scenarios.
- [ ] Test code with edge cases like an empty string or a dictionary with no words.

## Similar Problems

* LeetCode 140. Word Break II (same problem, but requires outputting all possible word breaks)
* LeetCode 1012. Write Code to Remove Words Which Are Not in Dictionary
* LeetCode 409. Longest Palindrome
