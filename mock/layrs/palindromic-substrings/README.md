# Palindromic Substrings

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Two Pointers` `String` `Dynamic Programming`  
**Time:** O(n^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {

            // Case 1: Odd length palindrome (center at i)
            count += expandFromCenter(s, i, i);

            // Case 2: Even length palindrome (center between i and i+1)
            count += expandFromCenter(s, i, i + 1);
        }

        return count;
    }

    private int expandFromCenter(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            count++;     // Found one palindrome
            left--;      // Expand outward
            right++;
        }

        return count;
    }
}

```

---

---
## Quick Revision
This problem asks to count all palindromic substrings within a given string.
The solution involves expanding outwards from every possible center of a palindrome.

## Intuition
A palindrome reads the same forwards and backward. We can identify palindromes by checking if characters equidistant from a center point are the same. The key insight is that any palindrome must have a "center." This center can either be a single character (for odd-length palindromes like "aba") or the space between two characters (for even-length palindromes like "abba"). By iterating through all possible centers and expanding outwards, we can systematically find and count all palindromic substrings.

## Algorithm
1. Initialize a counter `count` to 0.
2. Iterate through each character of the string `s` using an index `i` from 0 to `n-1` (where `n` is the length of `s`).
3. For each `i`, consider two cases for potential palindrome centers:
    a. **Odd length palindromes:** The center is the character at index `i`. Call a helper function `expandFromCenter` with `left = i` and `right = i`. Add the returned count to the total `count`.
    b. **Even length palindromes:** The center is between the characters at index `i` and `i+1`. Call a helper function `expandFromCenter` with `left = i` and `right = i + 1`. Add the returned count to the total `count`.
4. The `expandFromCenter(s, left, right)` helper function:
    a. Initializes a local `count` to 0.
    b. Enters a `while` loop that continues as long as `left` is within the string bounds (>= 0), `right` is within the string bounds (< `s.length()`), and the characters at `s.charAt(left)` and `s.charAt(right)` are equal.
    c. Inside the loop, increment the local `count` (as a valid palindrome is found).
    d. Decrement `left` and increment `right` to expand outwards.
    e. Return the local `count`.
5. After iterating through all possible centers, return the total `count`.

## Concept to Remember
*   **String Manipulation:** Understanding how to access and compare characters within a string.
*   **Two Pointers:** Using `left` and `right` pointers to efficiently check for palindromic properties by expanding outwards.
*   **Edge Cases:** Handling boundary conditions for `left` and `right` pointers to avoid index out of bounds errors.
*   **Substrings:** Recognizing that a substring is a contiguous sequence of characters within a string.

## Common Mistakes
*   **Missing Even Length Palindromes:** Forgetting to check for palindromes centered *between* characters (e.g., "aa" from "aaa").
*   **Incorrect Boundary Checks:** Not properly handling the `left >= 0` and `right < s.length()` conditions in the expansion loop, leading to `IndexOutOfBoundsException`.
*   **Double Counting:** If not careful, one might count the same palindrome multiple times. The "expand from center" approach naturally avoids this.
*   **Inefficient Palindrome Check:** Using a naive approach to check if each substring is a palindrome (e.g., reversing each substring) would be much slower.

## Complexity Analysis
- Time: O(n^2) - reason: We iterate through `n` possible centers. For each center, the `expandFromCenter` function can expand up to `n/2` times in the worst case (e.g., a string of all the same characters). Thus, the total time complexity is roughly `n * (n/2 + n/2)`, which simplifies to O(n^2).
- Space: O(1) - reason: We are only using a few variables (`count`, `left`, `right`, `n`) to store intermediate results. The space used does not grow with the input string size.

## Commented Code
```java
class Solution {
    public int countSubstrings(String s) {
        int n = s.length(); // Get the length of the input string.
        int count = 0; // Initialize a counter for palindromic substrings.

        // Iterate through each character of the string, considering it as a potential center.
        for (int i = 0; i < n; i++) {

            // Case 1: Odd length palindrome. The center is a single character at index 'i'.
            // Expand outwards from 'i' and add the number of palindromes found to the total count.
            count += expandFromCenter(s, i, i);

            // Case 2: Even length palindrome. The center is between characters at 'i' and 'i+1'.
            // Expand outwards from 'i' and 'i+1' and add the number of palindromes found to the total count.
            count += expandFromCenter(s, i, i + 1);
        }

        return count; // Return the total count of palindromic substrings.
    }

    // Helper function to expand outwards from a given center and count palindromes.
    private int expandFromCenter(String s, int left, int right) {
        int count = 0; // Initialize a local counter for palindromes found from this center.

        // Continue expanding as long as the pointers are within string bounds
        // AND the characters at the left and right pointers are equal.
        while (left >= 0 && right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            count++;     // A valid palindrome is found, so increment the count.
            left--;      // Move the left pointer one step to the left.
            right++;     // Move the right pointer one step to the right.
        }

        return count; // Return the number of palindromes found centered at the initial (left, right).
    }
}
```

## Interview Tips
*   **Explain the "Center" Concept:** Clearly articulate why considering both single characters and spaces between characters as centers covers all possible palindromes.
*   **Walk Through an Example:** Use a small string like "aaa" or "aba" to demonstrate how the `expandFromCenter` function works for both odd and even length cases.
*   **Discuss Time/Space Trade-offs:** If asked about alternative solutions (like dynamic programming), be prepared to discuss their complexities and why the "expand from center" approach is often preferred for its simplicity and O(1) space.
*   **Handle Edge Cases Verbally:** Mention how the `while` loop conditions (`left >= 0` and `right < s.length()`) prevent out-of-bounds errors.

## Revision Checklist
- [ ] Understand the definition of a palindrome.
- [ ] Identify all possible centers for palindromes (single characters and spaces between characters).
- [ ] Implement the "expand from center" logic correctly.
- [ ] Ensure boundary conditions in the expansion loop are handled.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Longest Palindromic Substring
*   Longest Palindromic Subsequence
*   Valid Palindrome
*   Palindrome Permutation

## Tags
`String` `Two Pointers` `Dynamic Programming`
