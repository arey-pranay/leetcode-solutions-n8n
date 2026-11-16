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
The problem requires finding the shortest possible palindrome that can be formed by appending characters to the end of the given string. This is achieved by finding the longest proper prefix that is also a suffix of the string.

## Intuition
The key insight is to use a hash-based approach to efficiently find the longest proper prefix that is also a suffix of the string. By treating the string as a sequence of numbers using a fixed base and modulo operation, we can efficiently compute the hash values for both the forward and backward traversals of the string.

## Algorithm
1. Initialize the base and modulo values for the hash calculation.
2. Initialize the hash values for both the forward and backward traversals to 0.
3. Iterate through the string, updating the hash values for both the forward and backward traversals at each step.
4. Keep track of the length of the longest proper prefix that is also a suffix of the string.
5. Once the iteration is complete, construct the shortest palindrome by appending the reversed suffix to the original string.

## Concept to Remember
* Hash functions can be used to efficiently compute complex properties of strings.
* The modulo operation can be used to reduce the size of large numbers and prevent overflow.
* Proper prefixes and suffixes can be used to efficiently find the longest repeated substring.

## Common Mistakes
* Failing to properly initialize the hash values and base values.
* Misunderstanding the relationship between the forward and backward traversals.
* Not using the modulo operation to prevent overflow.

## Complexity Analysis
- Time: O(n) - where n is the length of the string, as each character is visited once.
- Space: O(1) - as the space required does not grow with the size of the input string.

## Commented Code
```java
class Solution {
    // Initialize the base and modulo values for the hash calculation
    static int base = 29;
    static long MOD = 1_000_000_007;

    // Function to find the shortest palindrome
    public String shortestPalindrome(String s) {
        // Handle the empty string case
        if (s.isEmpty()) return s;

        int n = s.length();
        long fwd = 0, bwd = 0, power = 1;
        int end = 0;

        // Iterate through the string, updating the hash values for both the forward and backward traversals
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a'; // Convert character to 0-based index
            fwd = (fwd * base + c) % MOD; // Update the forward hash value
            bwd = (bwd + c * power) % MOD; // Update the backward hash value
            power = (power * base) % MOD; // Update the power value

            // Keep track of the length of the longest proper prefix that is also a suffix of the string
            if (fwd == bwd) end = i;
        }

        // Construct the shortest palindrome by appending the reversed suffix to the original string
        return new StringBuilder(s.substring(end + 1)).reverse().append(s).toString();
    }
}
```

## Interview Tips
* Practice using hash functions and modulo operations to efficiently solve string problems.
* Pay close attention to the initialization of hash values and base values.
* Use the modulo operation to prevent overflow and ensure accurate results.

## Revision Checklist
* [ ] Understand the concept of proper prefixes and suffixes.
* [ ] Familiarize yourself with hash functions and modulo operations.
* [ ] Practice using the commented code to solve similar problems.

## Similar Problems
* [LeetCode 459. Repeated Substrings Pattern](https://leetcode.com/problems/repeated-substrings-pattern/)
* [LeetCode 718. Maximum Length of Repeated Subarray](https://leetcode.com/problems/maximum-length-of-repeated-subarray/)

## Tags
`Array` `Hash Map` `String` `Palindrome` `Dynamic Programming`
