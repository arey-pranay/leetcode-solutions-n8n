# Valid Palindrome

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Two Pointers` `String`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(Character.isLetterOrDigit(c))sb.append(Character.toLowerCase(c));
        }
        int i = 0;
        int j = sb.length()-1;
        // i<j is used to handle both conditions -> even and odd palindrome condtions
        while(i<j) if(sb.charAt(i++) != sb.charAt(j--)) return false;
        return true;
    }
}
// even hua to i aur j pados pados mei aakr cross hojayenge 
// agr odd hua to i aur j barabari p aajaynge 
```

---

---
## Quick Revision
Checks if a string is a palindrome after converting to lowercase and removing non-alphanumeric characters.
Solves by filtering the string and then using two pointers to compare characters from both ends.

## Intuition
The core idea of a palindrome is that it reads the same forwards and backward. When we consider only alphanumeric characters and ignore case, we can simplify the problem. If we can transform the original string into a "clean" version containing only lowercase alphanumeric characters, we can then easily check if this clean string is a palindrome. The most efficient way to check if a string is a palindrome is by comparing characters from the beginning and end simultaneously, moving inwards.

## Algorithm
1. Initialize an empty `StringBuilder` to store the filtered characters.
2. Iterate through each character of the input string `s`.
3. For each character, check if it is a letter or a digit using `Character.isLetterOrDigit()`.
4. If it is alphanumeric, convert it to lowercase using `Character.toLowerCase()` and append it to the `StringBuilder`.
5. After processing all characters, convert the `StringBuilder` to a `String` (or use its `toString()` method).
6. Initialize two pointers, `i` to the beginning of the filtered string (index 0) and `j` to the end of the filtered string (index `length - 1`).
7. While `i` is less than `j`:
    a. Compare the character at index `i` with the character at index `j`.
    b. If they are not equal, the string is not a palindrome, so return `false`.
    c. Increment `i` and decrement `j` to move the pointers inwards.
8. If the loop completes without returning `false`, it means all corresponding characters matched, so the string is a palindrome. Return `true`.

## Concept to Remember
*   **String Manipulation:** Efficiently processing and transforming strings.
*   **Two-Pointer Technique:** A common algorithmic pattern for problems involving arrays or strings where you need to compare elements from opposite ends.
*   **Character Properties:** Understanding how to check character types (alphanumeric) and case conversion.

## Common Mistakes
*   **Case Sensitivity:** Forgetting to convert all characters to the same case (e.g., lowercase) before comparison.
*   **Non-Alphanumeric Characters:** Not filtering out spaces, punctuation, or other special characters, which would lead to incorrect palindrome checks.
*   **Off-by-One Errors:** Incorrectly setting the initial `j` pointer (e.g., `length` instead of `length - 1`) or loop conditions.
*   **Handling Empty or Single-Character Strings:** The algorithm should correctly handle these edge cases (they are palindromes).

## Complexity Analysis
*   **Time:** O(N) - The first loop iterates through the input string once to filter characters (N is the length of the input string). The second loop (two-pointer comparison) iterates at most N/2 times. Therefore, the total time complexity is dominated by the linear scan.
*   **Space:** O(N) - In the worst case, if all characters in the input string are alphanumeric, the `StringBuilder` will store all of them, leading to O(N) space complexity.

## Commented Code
```java
class Solution {
    public boolean isPalindrome(String s) {
        // Create a StringBuilder to efficiently build the filtered string.
        StringBuilder sb = new StringBuilder();
        // Iterate over each character in the input string s.
        for(char c : s.toCharArray()){
            // Check if the current character is a letter or a digit.
            if(Character.isLetterOrDigit(c)){
                // If it's alphanumeric, convert it to lowercase and append it to the StringBuilder.
                sb.append(Character.toLowerCase(c));
            }
        }
        // Initialize a pointer 'i' to the beginning of the filtered string.
        int i = 0;
        // Initialize a pointer 'j' to the end of the filtered string.
        int j = sb.length()-1;
        // Loop while the left pointer 'i' is less than the right pointer 'j'.
        // This condition ensures we compare characters from both ends moving inwards.
        // It correctly handles both even and odd length palindromes.
        // For even length, i and j will eventually cross.
        // For odd length, i and j will meet at the middle character.
        while(i<j) {
            // Compare the characters at the current positions of pointers 'i' and 'j'.
            // If they are not equal, the string is not a palindrome.
            if(sb.charAt(i++) != sb.charAt(j--)) {
                // Return false immediately if a mismatch is found.
                return false;
            }
            // The i++ and j-- are post-increment/decrement, meaning the character is accessed
            // with the current value of i/j, and then i/j is updated for the next iteration.
        }
        // If the loop completes without finding any mismatches, the string is a palindrome.
        return true;
    }
}
```

## Interview Tips
*   **Clarify Requirements:** Ask the interviewer if case and non-alphanumeric characters should be ignored. This is crucial for understanding the problem scope.
*   **Explain Your Approach:** Clearly articulate the two-pointer strategy and why filtering is necessary.
*   **Edge Cases:** Be prepared to discuss how your solution handles empty strings, single-character strings, and strings with only non-alphanumeric characters.
*   **Alternative Solutions:** Briefly mention other approaches (e.g., reversing the filtered string and comparing) and why the two-pointer method is generally preferred for its space efficiency (if not using an intermediate string builder).

## Revision Checklist
- [ ] Understand the definition of a palindrome.
- [ ] Know how to filter alphanumeric characters.
- [ ] Know how to handle case insensitivity.
- [ ] Implement the two-pointer technique correctly.
- [ ] Test with edge cases (empty, single char, all non-alphanumeric).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Palindrome Permutation
*   Valid Palindrome II
*   Longest Palindromic Substring
*   Longest Palindromic Subsequence

## Tags
`String` `Two Pointers` `Algorithm`
