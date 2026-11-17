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
Given two strings s1 and s2, determine if s1 can be scrambled into s2. We solve this problem by using memoization to store previously computed results and breaking down the strings into substrings to compare.

## Intuition
The "aha moment" comes from recognizing that if two strings can be scrambled into each other, they must have the same characters and the same frequency of characters. We can leverage this insight to use memoization and divide the strings into substrings to compare.

## Algorithm
1. Check if the input strings s1 and s2 are equal. If so, return true.
2. Check if the lengths of s1 and s2 are equal. If not, return false.
3. Create a key by concatenating s1 and s2. If this key is already in the memo map, return the stored result.
4. Iterate through the length of s1 (from 1 to n-1) and for each length i:
	* Compare the substrings of s1 and s2 of length i using recursion (isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))).
	* If true, store the result in the memo map and return true.
	* Compare the substrings of s1 and s2 of length i, but with the substring of s2 reversed (isScramble(s1.substring(0,i),s2.substring(n-i)) && isScramble(s1.substring(i),s2.substring(0,n-i))).
	* If true, store the result in the memo map and return true.
5. If none of the above conditions are met, store false in the memo map and return false.

## Concept to Remember
• **Memoization**: Storing the results of expensive function calls and reusing them when the same inputs occur again.
• **Divide and Conquer**: Breaking down a problem into smaller sub-problems of the same type.
• **Recursion**: Solving a problem by recursively calling a function to solve the same problem on smaller inputs.

## Common Mistakes
• Failing to initialize the memo map properly.
• Not using memoization effectively, leading to redundant computations.
• Not considering the case when the strings are equal.
• Not handling the case when the lengths of the strings are not equal.

## Complexity Analysis
- Time: O(n^3) where n is the length of the input strings. This is because in the worst case, we need to compare all substrings of length up to n.
- Space: O(n^2) for storing the memo map.

## Commented Code
```java
class Solution {
    HashMap<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Check if the input strings are equal
        if (s1.equals(s2)) return true;

        // Check if the lengths of s1 and s2 are equal
        if (s1.length() != s2.length()) return false;

        int n = s1.length();
        String key = s1 + s2;

        // Check if the result is already in the memo map
        if (memo.containsKey(key)) return memo.get(key);

        // Compare the substrings of s1 and s2 of length i
        for (int i = 1; i < n; i++) {
            // Compare the substrings of length i
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true); return true;
            }

            // Compare the substrings of length i, but with the substring of s2 reversed
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true); return true;
            }
        }

        // If none of the above conditions are met, store false in the memo map
        memo.put(key, false); return false;
    }
}
```

## Interview Tips
• Make sure to explain the concept of memoization and how it is used in this solution.
• Be prepared to walk through the algorithm and explain the time and space complexity.
• Practice explaining the solution to a non-technical person to ensure you can communicate the idea effectively.
• Be prepared to handle edge cases, such as when the input strings are empty.

## Revision Checklist
- [ ] Review the memoization technique and its application in this solution.
- [ ] Practice explaining the algorithm to a non-technical person.
- [ ] Review the time and space complexity of the solution.

## Similar Problems
- 547. Number of Provinces (Similar problem using DFS and memoization)
- 1035. Uncrossed Lines (Similar problem using dynamic programming and memoization)
- 784. Letter Case Permutation (Similar problem using DFS and memoization)
