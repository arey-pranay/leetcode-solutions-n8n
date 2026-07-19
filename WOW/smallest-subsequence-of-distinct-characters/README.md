# Smallest Subsequence Of Distinct Characters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack` `Greedy` `Monotonic Stack`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public String smallestSubsequence(String text) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        
        for (char c : text.toCharArray()) count[c-'a']++;
        for ( char c : text.toCharArray()) {
            count[c-'a']--;
            if (used[c-'a']) continue; // sbko exactly 1 hi baar use krna hai
            while (sb.length() > 0 && last(sb) > c && count[last(sb)-'a'] > 0) {
                //agr lexicographically bda character last pe already rkha hai, and wo humko aage firse mil skta hai, to usko hata do
                used[last(sb)-'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            used[c-'a'] = true;
            sb.append(c);
        }
        return sb.toString();
    }

    private char last(StringBuilder sb) {
        return sb.charAt(sb.length() - 1);
    }
}
```

---

---
## Quick Revision
Find the lexicographically smallest subsequence containing all distinct characters from a given string.
This is solved using a greedy approach with a stack-like structure.

## Intuition
The goal is to build the smallest subsequence character by character. When considering a new character, we want to append it if it helps make the subsequence lexicographically smaller. This means if the current character is smaller than the last character added to our subsequence, and the last character can be found later in the string, we should remove the last character to make room for the smaller one. This ensures we prioritize smaller characters earlier in the subsequence.

## Algorithm
1.  **Character Counts:** Create an array `count` of size 26 to store the frequency of each character in the input string `text`.
2.  **Character Usage Tracking:** Create a boolean array `used` of size 26 to keep track of whether a character has already been included in our subsequence.
3.  **Iterate Through String:** Iterate through the input string `text` character by character.
4.  **Decrement Count:** For each character `c`, decrement its count in the `count` array.
5.  **Skip if Used:** If the character `c` has already been added to our subsequence (i.e., `used[c-'a']` is true), skip to the next character.
6.  **Greedy Removal (Monotonic Stack Logic):** While the subsequence `sb` is not empty, the last character in `sb` is lexicographically greater than the current character `c`, AND the last character in `sb` still has occurrences remaining in the rest of the string (i.e., `count[last(sb)-'a'] > 0`):
    *   Mark the last character in `sb` as not used (`used[last(sb)-'a'] = false`).
    *   Remove the last character from `sb`.
7.  **Add Current Character:** Mark the current character `c` as used (`used[c-'a'] = true`) and append it to `sb`.
8.  **Return Result:** After iterating through the entire string, convert `sb` to a string and return it.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Monotonic Stack:** A stack where elements are always in increasing or decreasing order. Used here to maintain a lexicographically increasing subsequence.
*   **Character Frequency Tracking:** Essential for determining if a character can be safely removed from the subsequence.

## Common Mistakes
*   **Not handling character counts correctly:** Forgetting to decrement counts or incorrectly checking if a character can be re-added.
*   **Incorrectly applying the greedy condition:** Not checking if the last character *can* be found later in the string before removing it.
*   **Off-by-one errors in array indexing:** Using `c` directly instead of `c - 'a'` for array access.
*   **Not ensuring distinct characters:** Failing to use a mechanism (like the `used` array) to ensure each character appears only once.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string twice. The inner `while` loop might seem concerning, but each character is pushed onto and popped from the `StringBuilder` at most once.
- Space: O(1) - reason: The `count` and `used` arrays are of fixed size (26), independent of the input string length. The `StringBuilder` can grow up to the size of the alphabet (26), also constant.

## Commented Code
```java
class Solution {
    public String smallestSubsequence(String text) {
        // StringBuilder to build the result subsequence. Acts like a stack.
        StringBuilder sb = new StringBuilder();
        // Array to store the frequency of each character ('a' through 'z').
        int[] count = new int[26];
        // Boolean array to track if a character has already been included in the subsequence.
        boolean[] used = new boolean[26];
        
        // First pass: Populate the character counts.
        for (char c : text.toCharArray()) {
            // Increment the count for the current character.
            count[c-'a']++;
        }
        
        // Second pass: Build the smallest subsequence.
        for ( char c : text.toCharArray()) {
            // Decrement the count for the current character as we've encountered it.
            count[c-'a']--;
            
            // If the current character has already been used in our subsequence, skip it.
            // We only want distinct characters.
            if (used[c-'a']) continue; 
            
            // Greedy removal phase:
            // While the subsequence is not empty, AND the last character in the subsequence
            // is lexicographically greater than the current character, AND the last character
            // can still be found later in the string (its count is > 0):
            while (sb.length() > 0 && last(sb) > c && count[last(sb)-'a'] > 0) {
                // Mark the last character as not used anymore, because we are removing it.
                used[last(sb)-'a'] = false;
                // Remove the last character from the subsequence.
                sb.deleteCharAt(sb.length() - 1);
            }
            
            // Add the current character to the subsequence.
            // Mark it as used.
            used[c-'a'] = true;
            // Append it to the subsequence.
            sb.append(c);
        }
        // Convert the StringBuilder to a String and return it.
        return sb.toString();
    }

    // Helper method to get the last character of the StringBuilder.
    private char last(StringBuilder sb) {
        // Return the character at the last index.
        return sb.charAt(sb.length() - 1);
    }
}
```

## Interview Tips
*   **Explain the Greedy Choice:** Clearly articulate *why* removing a larger character in favor of a smaller one is the correct greedy step. Emphasize the condition that the larger character must appear again later.
*   **Trace an Example:** Walk through a small example like "cbacdcbc" to demonstrate how the `count`, `used`, and `sb` arrays/StringBuilder change.
*   **Discuss Edge Cases:** Consider cases like an empty string, a string with all same characters, or a string with already distinct characters.
*   **Clarify "Lexicographically Smallest":** Ensure you understand and can explain what this term means in the context of strings.

## Revision Checklist
- [ ] Understand the problem: find the smallest subsequence with distinct characters.
- [ ] Identify the core idea: greedy approach with a monotonic stack-like behavior.
- [ ] Implement character frequency counting.
- [ ] Implement tracking of used characters.
- [ ] Correctly implement the greedy removal logic (while loop conditions).
- [ ] Handle the case where a character is already used.
- [ ] Test with various examples.

## Similar Problems
*   Remove Duplicate Letters (LeetCode 316)
*   Smallest String With A Given Numeric Value (LeetCode 1663)
*   Longest Substring Without Repeating Characters (LeetCode 3)

## Tags
`String` `Stack` `Greedy` `Monotonic Stack`
