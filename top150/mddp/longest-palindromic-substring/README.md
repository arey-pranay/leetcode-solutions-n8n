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
    int start;
    int maxLength;
    public String longestPalindrome(String s) {
        for(int i=0;i<s.length();i++){
           check(i,i+1,s);
           check(i-1,i+1,s);
        }
        return s.substring(start,start+maxLength);
    }
    public void check(int i, int j, String s){
        int n = s.length();
        while(i>=0 && j<n && s.charAt(i) == s.charAt(j)) {i--;j++;}
        int length = j-i-1; //(j-i+1-2) because loop breaks on false condition, where i and j have already moved 1 extra step
        if(length > maxLength){
            start = i+1;
            maxLength = length;
        }
    }
}
```

---

---
## Quick Revision
Find the longest substring within a given string that reads the same forwards and backward.
This is solved by expanding around each possible center of a palindrome.

## Intuition
A palindrome is symmetric. This symmetry suggests that if we pick a character or a pair of adjacent characters as the "center" of a potential palindrome, we can expand outwards from this center to find the longest palindrome it can form. We need to consider two types of centers: a single character (for odd-length palindromes like "aba") and a pair of adjacent characters (for even-length palindromes like "abba"). By checking every possible center and keeping track of the longest palindrome found so far, we can guarantee finding the overall longest one.

## Algorithm
1. Initialize `start` and `maxLength` to 0. These will store the starting index and length of the longest palindrome found.
2. Iterate through each character of the input string `s` using an index `i` from 0 to `s.length() - 1`.
3. For each `i`, consider it as a potential center for an odd-length palindrome. Call a helper function `check` with `i` as both the left and right boundary (i.e., `check(i, i, s)`).
4. For each `i`, consider the space between `i` and `i+1` as a potential center for an even-length palindrome. Call the helper function `check` with `i` as the left boundary and `i+1` as the right boundary (i.e., `check(i, i+1, s)`).
5. The `check(left, right, s)` helper function:
    a. Takes the current left and right indices and the string `s`.
    b. While `left` is within bounds (`>= 0`), `right` is within bounds (`< s.length()`), and the characters at `s.charAt(left)` and `s.charAt(right)` are equal:
        i. Decrement `left`.
        ii. Increment `right`.
    c. After the loop terminates, the actual palindrome's boundaries are `left + 1` and `right - 1`. The length of this palindrome is `(right - 1) - (left + 1) + 1`, which simplifies to `right - left - 1`.
    d. If this calculated `length` is greater than the current `maxLength`:
        i. Update `maxLength` to `length`.
        ii. Update `start` to `left + 1` (the starting index of this new longest palindrome).
6. After iterating through all possible centers, return the substring of `s` starting at `start` with length `maxLength` using `s.substring(start, start + maxLength)`.

## Concept to Remember
*   **Palindrome Definition**: A string that reads the same forwards and backward.
*   **String Manipulation**: Efficiently extracting substrings.
*   **Two Pointers**: Using two pointers to expand from a center and check for symmetry.
*   **Edge Cases**: Handling empty strings, single-character strings, and boundary conditions in loops.

## Common Mistakes
*   **Incorrectly calculating palindrome length**: Forgetting to adjust for the loop's exit condition where `i` and `j` have moved one step too far.
*   **Missing one type of palindrome center**: Only checking for odd-length palindromes (single character centers) and neglecting even-length ones (adjacent character centers).
*   **Off-by-one errors in boundary checks**: Incorrectly handling `i >= 0` and `j < n` conditions.
*   **Not updating `start` and `maxLength` correctly**: Failing to store the correct start index or length when a new longest palindrome is found.

## Complexity Analysis
*   Time: O(n^2) - For each of the `n` characters, we potentially expand outwards up to `n/2` times in both directions.
*   Space: O(1) - We only use a few variables to store the start index and maximum length, which is constant space.

## Commented Code
```java
class Solution {
    // 'start' will store the starting index of the longest palindromic substring found.
    int start;
    // 'maxLength' will store the length of the longest palindromic substring found.
    int maxLength;

    // The main function to find the longest palindromic substring.
    public String longestPalindrome(String s) {
        // Iterate through each character of the string. This character will serve as a potential center.
        for(int i = 0; i < s.length(); i++){
           // Check for odd-length palindromes, where the center is a single character at index 'i'.
           // We start expanding from 'i' to 'i'.
           check(i, i, s);
           // Check for even-length palindromes, where the center is between characters at index 'i' and 'i+1'.
           // We start expanding from 'i' to 'i+1'.
           check(i, i + 1, s);
        }
        // After checking all possible centers, 'start' and 'maxLength' hold the details of the longest palindrome.
        // Extract and return the substring using these values.
        return s.substring(start, start + maxLength);
    }

    // Helper function to expand around a center and find the longest palindrome.
    // 'i' is the left pointer, 'j' is the right pointer.
    public void check(int i, int j, String s){
        // Get the length of the string for boundary checks.
        int n = s.length();
        // Expand outwards as long as the pointers are within string bounds AND the characters match.
        while(i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            // Move the left pointer inwards.
            i--;
            // Move the right pointer outwards.
            j++;
        }
        // Calculate the length of the palindrome found.
        // The loop breaks when s.charAt(i) != s.charAt(j) OR when i or j go out of bounds.
        // So, the actual palindrome is between i+1 and j-1.
        // Length = (j-1) - (i+1) + 1 = j - 1 - i - 1 + 1 = j - i - 1.
        int length = j - i - 1;
        // If the current palindrome's length is greater than the maximum length found so far.
        if(length > maxLength){
            // Update the starting index of the longest palindrome.
            // Since 'i' has moved one step too far left, the start is 'i+1'.
            start = i + 1;
            // Update the maximum length.
            maxLength = length;
        }
    }
}
```

## Interview Tips
*   **Explain the "Expand Around Center" strategy clearly**: Emphasize why checking every possible center (single character and between characters) is exhaustive.
*   **Walk through an example**: Use a string like "babad" or "cbbd" to demonstrate how the `check` function works for both odd and even length palindromes.
*   **Discuss edge cases**: Mention how your code handles empty strings, single-character strings, and strings with no palindromes longer than one character.
*   **Clarify the length calculation**: Be prepared to explain `j - i - 1` and why `i+1` is used for the start index.

## Revision Checklist
- [ ] Understand the definition of a palindrome.
- [ ] Implement the "expand around center" approach.
- [ ] Handle both odd and even length palindromes.
- [ ] Correctly calculate the length and start index of the palindrome.
- [ ] Manage boundary conditions in the expansion loop.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Palindromic Subsequence
*   Palindromic Substrings

## Tags
`String` `Two Pointers` `Dynamic Programming`
