# Shortest Palindrome

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Rolling Hash` `String Matching` `Hash Function` `Manacher` `Z Algorithm` `Knuth–Morris–Pratt Algorithm`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    static int base = 29;
    static long MOD  = 1_000_000_007;
    public String shortestPalindrome(String s) {
        if(s.isEmpty()) return s;
        int n = s.length();
        long fwd = 0, bwd = 0, power = 1;
        int end = 0;
        for(int i=0;i<n;i++){
            int c = s.charAt(i)-'a';   // aba
            fwd = (fwd*base + c)%MOD;  // 0*29 + a = a // a*29 + b // a*29^2 + b*29 + a
            bwd = (bwd + c*power)%MOD; // 0 + a*1  = a // a + b*29 // a + b*29 + a*29^2
            // ab me a ka weightage zyada hai, b ka km. we multiply base with a, and just add b
            // ba me b ka weightage zyada hai, a ka km. we need to keep a lesser and multiply base with b
            power = (power*base)%MOD;
            if(fwd==bwd) end = i;
        } 
        return new StringBuilder(s.substring(end+1)).reverse().append(s).toString();
    }
}

// a*31 + b*31^2  <=> b*31 + a*31^2


// aabecaaa

// abyeeybaxedcxy

// yzcdexabyeeyba

// a
// aa
// aac <-> caa


// aacecaa a
// a 11----1 -

// acecaca
// acacecaca

// abcad
// dacba

// aaada =>2
// adaaa

// bcdabaabadcba


// abcefgcba

// "abcddcbb"
// "av"
// "bcabccbab"
```

---

---
## Quick Revision
* Given a string, find the shortest palindrome that can be formed by appending characters to the end of the string.
* We use the concept of rolling hash to efficiently find the longest prefix that is also a suffix.

## Intuition
The key idea here is to use the rolling hash technique to find the longest prefix that is also a suffix. We can think of it as finding the longest repeated substring in the given string. By doing this, we can efficiently construct the shortest palindrome by appending the remaining characters to the end of the string.

## Algorithm
1. Initialize the hash values for the forward and backward strings to 0.
2. Iterate over the string from left to right, updating the hash values for the forward and backward strings.
3. Keep track of the length of the longest prefix that is also a suffix.
4. If the hash values for the forward and backward strings are equal at any point, it means we have found a repeated substring.
5. Construct the shortest palindrome by appending the remaining characters to the end of the string.

## Concept to Remember
* Rolling hash: a technique used to efficiently calculate hash values for large strings by breaking them down into smaller sub-strings.
* Longest repeated substring: finding the longest substring that repeats itself in the given string.
* Hash collisions: when two different strings have the same hash value.

## Common Mistakes
* Failing to properly initialize the hash values.
* Not handling edge cases such as empty strings.
* Not considering the modulo operation when updating the hash values.

## Complexity Analysis
- Time: O(n) where n is the length of the string, as we only need to iterate over the string once.
- Space: O(1) as we only need to keep track of a few variables.

## Commented Code
```java
class Solution {
    static int base = 29;
    static long MOD  = 1_000_000_007;

    public String shortestPalindrome(String s) {
        if(s.isEmpty()) return s;

        int n = s.length();
        long fwd = 0, bwd = 0, power = 1;
        int end = 0;

        // Iterate over the string from left to right
        for(int i=0;i<n;i++){
            int c = s.charAt(i)-'a';   // Convert character to index (a=0, b=1, etc.)
            // Update hash value for forward string
            fwd = (fwd*base + c)%MOD;  
            // Update hash value for backward string
            bwd = (bwd + c*power)%MOD;  
            // Update power for next iteration
            power = (power*base)%MOD;
            // Check if hash values are equal
            if(fwd==bwd) end = i;
        } 

        // Construct shortest palindrome
        return new StringBuilder(s.substring(end+1)).reverse().append(s).toString();
    }
}
```

## Interview Tips
* Make sure to understand the concept of rolling hash and how it is used to efficiently find the longest repeated substring.
* Pay attention to edge cases such as empty strings.
* Consider using a debugger to step through the code and understand how it works.

## Revision Checklist
* [ ] Understand rolling hash technique
* [ ] Implement rolling hash in Java
* [ ] Handle edge cases such as empty strings
* [ ] Consider using a debugger to step through the code

## Similar Problems
* Longest Palindromic Substring
* Longest Common Subsequence
* Longest Increasing Subsequence

## Tags
`Array` `Hash Map` `String` `Rolling Hash` `Palindrome`
