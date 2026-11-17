# Scramble String

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(n^2 * 4^n)  
**Space:** O(n * 4^n)

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
Given two strings, determine if they can be scrambled versions of each other.
This problem can be solved using dynamic programming and memoization to optimize the recursive approach.

## Intuition
The "aha moment" for this problem is recognizing that a scrambled version of a string can be obtained by breaking the string into two parts, comparing the scrambled versions of these parts, and then reversing the order of the parts and comparing again. This approach ensures that all possible scrambles of the original string are considered.

## Algorithm
1. Check if the input strings are equal or have different lengths. If they are equal, return true. If they have different lengths, return false.
2. Create a memoization table to store the results of subproblems.
3. For each possible split point `i` in the string, recursively check if the scrambled versions of the two parts can be obtained from the other string.
4. If a scrambled version is found, store the result in the memoization table and return true.
5. If no scrambled version is found, store the result in the memoization table and return false.

## Concept to Remember
• **Memoization**: a technique to store the results of expensive function calls and return the cached result when the same inputs occur again.
• **Dynamic Programming**: a technique to break down a problem into smaller subproblems, solve each subproblem only once, and store the results to avoid redundant computation.
• **String manipulation**: the ability to break down strings into substrings and compare them.

## Common Mistakes
• Failing to implement memoization, leading to redundant computation and high time complexity.
• Not considering all possible split points in the string.
• Not handling the case where the input strings are equal.

## Complexity Analysis
- Time: O(n^2 * 4^n) / The number of possible splits in the string grows exponentially with the length of the string, and for each split, we recursively check all possible scrambles.
- Space: O(n * 4^n) / We store the results of subproblems in the memoization table, which grows exponentially with the length of the string.

## Commented Code
```java
class Solution {
    HashMap<String, Boolean> memo = new HashMap<>();
    
    public boolean isScramble(String s1, String s2) {
        // Base cases
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        
        int n = s1.length();
        String key = s1 + s2;
        
        // Check if the result is already stored in the memoization table
        if (memo.containsKey(key)) return memo.get(key);
        
        // Iterate over all possible split points
        for (int i = 1; i < n; i++) {
            // Check if the scrambled versions of the two parts can be obtained from the other string
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true); return true;
            }
            
            // Check if the scrambled versions of the two parts can be obtained from the other string
            // by reversing the order of the parts
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true); return true;
            }
        }
        
        // If no scrambled version is found, store the result in the memoization table and return false
        memo.put(key, false); return false;
    }
}
```

## Interview Tips
• Make sure to understand the problem thoroughly before starting to code.
• Implement memoization to optimize the recursive approach.
• Consider all possible split points in the string.
• Handle the base cases carefully.

## Revision Checklist
- [ ] Understand the problem thoroughly
- [ ] Implement memoization
- [ ] Consider all possible split points
- [ ] Handle base cases carefully

## Similar Problems
• [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)
• [Wildcard Matching](https://leetcode.com/problems/wildcard-matching/)

## Tags
`Array` `Hash Map` `String Manipulation` `Memoization` `Dynamic Programming`
