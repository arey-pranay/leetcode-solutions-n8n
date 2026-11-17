# Scramble String

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(n^3)  
**Space:** O(n^2)

---

## Solution (java)

```java
class Solution {
    HashMap<String, Boolean> memo = new HashMap<>();
    // Input: s1 = "great", s2 = "rgeat"
    public boolean isScramble(String s1, String s2) {
      if(s1.equals(s2)) return true;
      if(s1.length() != s2.length()) return false;
      int n = s1.length();
      String key = s1+s2;
      if(memo.containsKey(key)) return memo.get(key);
      //   gr eat <-> rg eat
      for(int i=1;i<n;i++){
        // break and compare
        if(isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))) {
            memo.put(key,true); return true;
        };
        
        // reverse after break. then compare
        if(isScramble(s1.substring(0,i),s2.substring(n-i)) && isScramble(s1.substring(i),s2.substring(0,n-i))) {
            memo.put(key,true); return true;
        };
      }
      memo.put(key,false); return false;
      
    }
}
// a | b | c | d | e
```

---

---

## Quick Revision
Given two strings `s1` and `s2`, determine if they are scrambled versions of each other.
Solve this problem using memoization and recursive comparison of substrings.

## Intuition
The key insight is that two strings are scrambled versions of each other if and only if their subsequences (including the empty subsequence) are also scrambled versions of each other. This allows us to break down the problem into smaller subproblems and use memoization to avoid redundant computation.

## Algorithm
1. Check if the input strings are equal. If they are, return true.
2. Check if the input strings have the same length. If not, return false.
3. Create a key by concatenating the two input strings. Use this key to check if the result is already stored in the memoization table.
4. If the result is already stored, return the stored value.
5. Otherwise, iterate through the input strings, breaking them into two parts at each possible position `i`.
6. For each `i`, recursively check if the first `i` characters of `s1` are scrambled versions of the first `i` characters of `s2`, and if the remaining characters of `s1` are scrambled versions of the remaining characters of `s2`. If both conditions are true, return true.
7. If the recursive call returns true for any `i`, store the result in the memoization table and return true.
8. If all recursive calls return false, store the result in the memoization table and return false.

## Concept to Remember
* Memoization: storing the results of expensive function calls so that they can be reused instead of recomputed.
* Recursion: breaking down a problem into smaller subproblems that are similar in form.

## Common Mistakes
* Failing to memoize the results of recursive calls, leading to exponential time complexity.
* Not checking for the base case where the input strings are equal or have the same length.
* Not handling the case where the input strings have different lengths.

## Complexity Analysis
- Time: O(n^3) where n is the length of the input strings, due to the recursive comparison of substrings.
- Space: O(n^2) where n is the length of the input strings, due to the memoization table.

## Commented Code
```java
class Solution {
    HashMap<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base case: if the input strings are equal, return true
        if (s1.equals(s2)) return true;
        
        // Base case: if the input strings have different lengths, return false
        if (s1.length() != s2.length()) return false;
        
        // Create a key by concatenating the two input strings
        int n = s1.length();
        String key = s1 + s2;
        
        // Check if the result is already stored in the memoization table
        if (memo.containsKey(key)) return memo.get(key);
        
        // Iterate through the input strings, breaking them into two parts at each possible position i
        for (int i = 1; i < n; i++) {
            // Recursively check if the first i characters of s1 are scrambled versions of the first i characters of s2
            // and if the remaining characters of s1 are scrambled versions of the remaining characters of s2
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true); return true;
            }
            
            // Recursively check if the first i characters of s1 are scrambled versions of the remaining characters of s2
            // and if the remaining characters of s1 are scrambled versions of the first i characters of s2
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true); return true;
            }
        }
        
        // If all recursive calls return false, store the result in the memoization table and return false
        memo.put(key, false); return false;
    }
}
```

## Interview Tips
* Make sure to explain the memoization technique and how it helps avoid redundant computation.
* Emphasize the importance of checking for the base cases where the input strings are equal or have the same length.
* Be prepared to explain the time and space complexity of the algorithm.

## Revision Checklist
- [ ] Understand the problem and the memoization technique
- [ ] Check for base cases where the input strings are equal or have the same length
- [ ] Implement the recursive comparison of substrings
- [ ] Use memoization to avoid redundant computation
- [ ] Analyze the time and space complexity of the algorithm

## Similar Problems
* [LeetCode 46. Permutations](https://leetcode.com/problems/permutations/)
* [LeetCode 60. Permutation Sequence](https://leetcode.com/problems/permutation-sequence/)
* [LeetCode 894. Alien Dictionary](https://leetcode.com/problems/alien-dictionary/)

## Tags
`Array` `Hash Map` `Memoization` `Recursion` `String`
