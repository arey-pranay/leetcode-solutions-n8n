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
The goal is to build the smallest possible subsequence. This means we want to place smaller characters earlier in the subsequence. When we encounter a character, we consider adding it. If the current character is smaller than the last character added to our subsequence, and that last character *can* be added again later (because it appears more times in the original string), then it's beneficial to remove the larger character from the end of our subsequence to make room for the smaller one. This ensures we maintain the lexicographically smallest order while guaranteeing all distinct characters are eventually included.

## Algorithm
1.  **Character Counts**: Create an array `count` of size 26 to store the frequency of each character ('a' through 'z') in the input string `text`.
2.  **Used Flags**: Create a boolean array `used` of size 26 to track whether a character has already been added to our result subsequence.
3.  **Iterate and Build**: Iterate through the input string `text` character by character.
    a.  **Decrement Count**: For the current character `c`, decrement its count in the `count` array.
    b.  **Skip if Used**: If the current character `c` has already been added to our result subsequence (i.e., `used[c-'a']` is true), skip to the next character.
    c.  **Greedy Removal**: While the result subsequence is not empty, the last character in the subsequence (`last(sb)`) is lexicographically greater than the current character `c`, AND the last character in the subsequence still has occurrences remaining in the original string (i.e., `count[last(sb)-'a'] > 0`):
        i.  Mark the last character as not used (`used[last(sb)-'a'] = false`).
        ii. Remove the last character from the result subsequence (`sb.deleteCharAt(sb.length() - 1)`).
    d.  **Add Current Character**: Mark the current character `c` as used (`used[c-'a'] = true`) and append it to the result subsequence (`sb.append(c)`).
4.  **Return Result**: Convert the `StringBuilder` to a `String` and return it.

## Concept to Remember
*   **Greedy Algorithms**: Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Monotonic Stack/Queue**: Using a stack-like structure where elements are maintained in a specific order (e.g., increasing or decreasing).
*   **Lexicographical Ordering**: Comparing strings character by character from left to right.
*   **Character Frequency Tracking**: Efficiently managing counts of elements.

## Common Mistakes
*   **Incorrectly handling the "can be added again" condition**: Forgetting to check `count[last(sb)-'a'] > 0` before removing a character from the subsequence.
*   **Not marking characters as "used"**: Leading to duplicate characters in the result when they should be unique.
*   **Off-by-one errors in character indexing**: Using `c` directly instead of `c - 'a'` for array indices.
*   **Not initializing `used` array correctly**: Forgetting to reset `used` flags when removing characters from the subsequence.

## Complexity Analysis
- Time: O(N) - Each character is processed at most twice: once when iterating through the string and potentially once when being removed from the `StringBuilder`.
- Space: O(1) - The `count` and `used` arrays are of fixed size (26), and the `StringBuilder` can store at most 26 distinct characters.

## Commented Code
```java
class Solution {
    public String smallestSubsequence(String text) {
        // StringBuilder to build the result subsequence. Acts like a stack.
        StringBuilder sb = new StringBuilder();
        // Array to store the frequency of each character ('a' to 'z').
        int[] count = new int[26];
        // Boolean array to track if a character has already been included in the subsequence.
        boolean[] used = new boolean[26];
        
        // First pass: Calculate the frequency of each character in the input string.
        for (char c : text.toCharArray()) {
            // Increment the count for the current character. 'c - 'a'' converts char to 0-25 index.
            count[c-'a']++;
        }
        
        // Second pass: Iterate through the string to build the smallest subsequence.
        for ( char c : text.toCharArray()) {
            // Decrement the count of the current character as we've processed one instance of it.
            count[c-'a']--;
            
            // If the current character has already been used in our subsequence, skip it.
            // We only want distinct characters.
            if (used[c-'a']) continue; 
            
            // This is the core greedy logic:
            // While the subsequence is not empty, AND the last character in the subsequence is GREATER than the current character,
            // AND the last character in the subsequence can still be found later in the string (its count is > 0):
            while (sb.length() > 0 && last(sb) > c && count[last(sb)-'a'] > 0) {
                // It's beneficial to remove the larger character from the end of our subsequence
                // to make room for the smaller current character, ensuring lexicographical order.
                
                // Mark the character being removed as "not used" so it can be added again later if needed.
                used[last(sb)-'a'] = false;
                // Remove the last character from the subsequence.
                sb.deleteCharAt(sb.length() - 1);
            }
            
            // Now that we've potentially removed larger characters, add the current character.
            // Mark the current character as "used" so we don't add it again.
            used[c-'a'] = true;
            // Append the current character to our subsequence.
            sb.append(c);
        }
        // Convert the StringBuilder to a String and return the result.
        return sb.toString();
    }

    // Helper function to get the last character of the StringBuilder.
    private char last(StringBuilder sb) {
        // Return the character at the last index.
        return sb.charAt(sb.length() - 1);
    }
}
```

## Interview Tips
*   **Explain the Greedy Choice**: Clearly articulate *why* removing a larger character from the end is the correct greedy choice. Emphasize that it's only done if that character can be re-added later.
*   **Trace an Example**: Walk through a simple example like "cbacdcbc" to demonstrate how the `count`, `used`, and `sb` arrays/`StringBuilder` change.
*   **Discuss Edge Cases**: Consider empty strings, strings with all same characters, or strings with already sorted distinct characters.
*   **Clarify "Smallest"**: Ensure you understand that "smallest" refers to lexicographical order.

## Revision Checklist
- [ ] Understand the problem statement: smallest subsequence, distinct characters, lexicographical order.
- [ ] Identify the greedy approach: build the result character by character.
- [ ] Implement character frequency counting.
- [ ] Implement tracking of used characters.
- [ ] Correctly implement the `while` loop condition for removing characters.
- [ ] Ensure `used` flags are updated correctly when adding and removing characters.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
*   Remove Duplicate Letters (LeetCode 316)
*   Monotonic Stack problems in general.

## Tags
`String` `Stack` `Greedy` `Monotonic Stack`
