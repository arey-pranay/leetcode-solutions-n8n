# Longest Palindromic Substring

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Two Pointers` `String` `Dynamic Programming`  
**Time:** O(n^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    // we need to have startIndex and maxLength to determine the lps from our string
    int startIndex = -1;
    int maxLength = 0;
    public String longestPalindrome(String s) {
        // for every index, explore the palindrome- even length and odd length.
        for(int i=0;i<s.length();i++){check(i,i+1,s);check(i-1,i+1,s);}
        return s.substring(startIndex,startIndex+maxLength);
    }
    public void check(int i, int j, String s){
        int n = s.length();
        while(i>=0 && j<n && s.charAt(i)==s.charAt(j)){i--;j++;}
        if(j-i-1>maxLength){maxLength = j-i-1; startIndex = i+1;}
    }
}
```

---

---
## Quick Revision
Find the longest substring within a given string that reads the same forwards and backward.
This is solved by expanding around each possible center of a palindrome.

## Intuition
A palindrome is symmetric around its center. The center can be a single character (for odd-length palindromes like "aba") or the space between two characters (for even-length palindromes like "abba"). If we iterate through every possible center and expand outwards as long as the characters match, we can find the longest palindrome.

## Algorithm
1. Initialize `startIndex` to -1 and `maxLength` to 0 to keep track of the longest palindromic substring found so far.
2. Iterate through each character of the input string `s` using an index `i` from 0 to `s.length() - 1`.
3. For each index `i`, consider two potential palindrome centers:
    a. An odd-length palindrome centered at `i`. Call a helper function `check` with `i` as the left boundary and `i+1` as the right boundary.
    b. An even-length palindrome centered between `i` and `i+1`. Call a helper function `check` with `i-1` as the left boundary and `i+1` as the right boundary.
4. The `check(left, right, s)` helper function:
    a. Takes two indices, `left` and `right`, representing the potential boundaries of a palindrome.
    b. While `left` is within string bounds (>= 0), `right` is within string bounds (< `s.length()`), and the characters at `s.charAt(left)` and `s.charAt(right)` are equal:
        i. Decrement `left`.
        ii. Increment `right`.
    c. After the loop, `left` and `right` are one step *outside* the actual palindrome. The length of the palindrome is `right - left - 1`.
    d. If this calculated length is greater than the current `maxLength`:
        i. Update `maxLength` to `right - left - 1`.
        ii. Update `startIndex` to `left + 1` (since `left` is one step before the start of the palindrome).
5. After iterating through all possible centers, the longest palindromic substring can be extracted from `s` using `s.substring(startIndex, startIndex + maxLength)`.

## Concept to Remember
*   String manipulation and indexing.
*   The definition of a palindrome and its symmetric properties.
*   Iterative expansion from a center point.
*   Handling edge cases for string boundaries.

## Common Mistakes
*   Incorrectly calculating the length of the palindrome after the expansion loop (e.g., off-by-one errors).
*   Not considering both odd and even length palindromes.
*   Failing to handle empty strings or strings with a single character.
*   Incorrectly updating `startIndex` and `maxLength`.
*   Index out of bounds errors when `left` goes below 0 or `right` goes beyond `s.length()`.

## Complexity Analysis
*   Time: O(n^2) - For each of the `n` characters, we potentially expand outwards up to `n` times in the `check` function.
*   Space: O(1) - We only use a few variables to store `startIndex`, `maxLength`, `i`, `j`, `left`, and `right`, which is constant space.

## Commented Code
```java
class Solution {
    // These variables will store the starting index and length of the longest palindromic substring found.
    int startIndex = -1; // Initialize startIndex to -1, indicating no palindrome found yet.
    int maxLength = 0;   // Initialize maxLength to 0.

    // This is the main function that finds the longest palindromic substring.
    public String longestPalindrome(String s) {
        // Iterate through each character of the string. Each character can be a potential center of a palindrome.
        for(int i = 0; i < s.length(); i++){
            // For each character 'i', we check for two types of palindromes:
            // 1. Odd length palindrome: centered at 'i'. We start expanding from 'i' itself.
            //    The initial call to check is with left=i and right=i+1. The check function will handle the expansion.
            check(i, i + 1, s);

            // 2. Even length palindrome: centered between 'i' and 'i+1'.
            //    The initial call to check is with left=i-1 and right=i+1. The check function will handle the expansion.
            check(i - 1, i + 1, s);
        }
        // After checking all possible centers, we extract the longest palindromic substring using the stored startIndex and maxLength.
        // If startIndex is still -1 (e.g., for an empty string), substring will handle it gracefully or we might need an explicit check.
        // However, for non-empty strings, startIndex will be updated.
        return s.substring(startIndex, startIndex + maxLength);
    }

    // This helper function expands outwards from a given center (defined by initial left and right indices)
    // to find the longest palindrome centered there and updates the global startIndex and maxLength if a longer palindrome is found.
    public void check(int i, int j, String s){
        // Get the length of the string for boundary checks.
        int n = s.length();
        // Expand outwards as long as:
        // 1. The left index 'i' is within the string bounds (i >= 0).
        // 2. The right index 'j' is within the string bounds (j < n).
        // 3. The characters at the left and right indices are equal, meaning they form a palindrome.
        while(i >= 0 && j < n && s.charAt(i) == s.charAt(j)){
            // If characters match, expand the potential palindrome outwards.
            i--; // Move the left boundary one step to the left.
            j++; // Move the right boundary one step to the right.
        }
        // After the loop, 'i' is one step to the left of the palindrome's start, and 'j' is one step to the right of the palindrome's end.
        // The length of the palindrome found is (j - 1) - (i + 1) + 1, which simplifies to j - i - 1.
        // We compare this length with the current maximum length found so far.
        if(j - i - 1 > maxLength){
            // If the current palindrome is longer than the previously found longest palindrome:
            maxLength = j - i - 1; // Update maxLength to the length of this new longest palindrome.
            startIndex = i + 1;    // Update startIndex to the starting index of this new longest palindrome.
                                   // (i + 1) is the actual start of the palindrome because 'i' was decremented one step too far.
        }
    }
}
```

## Interview Tips
*   Clearly explain the "expand around center" approach and why it covers all possibilities.
*   Walk through an example like "babad" or "cbbd" to demonstrate how `check` works for both odd and even length palindromes.
*   Be prepared to discuss the time and space complexity and justify it.
*   Mention alternative approaches like Dynamic Programming (though this solution is more efficient in space).
*   Handle edge cases like empty strings or single-character strings gracefully.

## Revision Checklist
- [ ] Understand the definition of a palindrome.
- [ ] Identify the two types of palindrome centers (single character, between characters).
- [ ] Implement the "expand around center" logic correctly.
- [ ] Accurately calculate palindrome length and update `startIndex`/`maxLength`.
- [ ] Handle string boundary conditions in the expansion loop.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty string, single character string).

## Similar Problems
*   Longest Palindromic Subsequence
*   Palindromic Substrings

## Tags
`String` `Two Pointers` `Dynamic Programming`
