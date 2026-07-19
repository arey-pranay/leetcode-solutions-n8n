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
The goal is to build the smallest subsequence character by character. When considering a new character, we want to append it if it helps us achieve a lexicographically smaller result. If the current character is smaller than the last character added to our subsequence, and that last character can still be found later in the string, we can remove the last character to make room for the current, smaller character, thus potentially leading to a lexicographically smaller overall subsequence. We must also ensure each character appears only once.

## Algorithm
1.  Initialize a frequency map (or array) to store the counts of each character in the input string.
2.  Initialize a boolean array to keep track of characters already included in the subsequence.
3.  Initialize a `StringBuilder` (acting as a stack) to build the result.
4.  Iterate through the input string character by character:
    a.  Decrement the count of the current character in the frequency map.
    b.  If the current character has already been used (is in the `StringBuilder`), skip it.
    c.  While the `StringBuilder` is not empty, the last character in the `StringBuilder` is lexicographically greater than the current character, AND the count of the last character in the `StringBuilder` is greater than 0 (meaning it can be found again later):
        i.  Mark the last character in the `StringBuilder` as unused.
        ii. Remove the last character from the `StringBuilder`.
    d.  Mark the current character as used.
    e.  Append the current character to the `StringBuilder`.
5.  Return the `StringBuilder` converted to a string.

## Concept to Remember
*   Greedy Algorithms: Making locally optimal choices to achieve a globally optimal solution.
*   Monotonic Stack: A stack where elements are maintained in a specific order (increasing or decreasing).
*   Lexicographical Ordering: Comparing strings character by character from left to right.
*   Frequency Counting: Using arrays or hash maps to store character occurrences.

## Common Mistakes
*   Not handling the condition where a character can be found later in the string, leading to premature removal of characters.
*   Forgetting to mark characters as "used" or "unused" correctly, resulting in duplicate characters in the subsequence.
*   Incorrectly comparing characters or handling the `StringBuilder` operations (e.g., `deleteCharAt` index).
*   Not initializing the frequency map or `used` array properly.

## Complexity Analysis
*   Time: O(N) - Each character is processed at most twice (once when appended, once when potentially removed). The initial frequency count is O(N).
*   Space: O(1) - The `count` and `used` arrays are of fixed size (26 for lowercase English letters), and the `StringBuilder` can store at most 26 distinct characters.

## Commented Code
```java
class Solution {
    public String smallestSubsequence(String text) {
        // StringBuilder to construct the result subsequence. It acts like a stack.
        StringBuilder sb = new StringBuilder();
        // Array to store the frequency of each character ('a' to 'z') in the input string.
        int[] count = new int[26];
        // Boolean array to track if a character has already been added to our subsequence.
        boolean[] used = new boolean[26];
        
        // First pass: Populate the frequency count for all characters in the input string.
        for (char c : text.toCharArray()) {
            count[c-'a']++; // Increment count for the character.
        }
        
        // Second pass: Iterate through the string to build the smallest subsequence.
        for ( char c : text.toCharArray()) {
            // Decrement the count of the current character as we've encountered it.
            count[c-'a']--;
            
            // If the current character is already in our subsequence (sb), skip it.
            // We only want distinct characters.
            if (used[c-'a']) {
                continue; // sbko exactly 1 hi baar use krna hai
            }
            
            // This is the core greedy logic:
            // While the subsequence is not empty, AND the last character in the subsequence
            // is lexicographically GREATER than the current character, AND the last character
            // can still be found later in the string (its count > 0):
            while (sb.length() > 0 && last(sb) > c && count[last(sb)-'a'] > 0) {
                // If the above conditions are met, it means we can potentially form a
                // lexicographically smaller subsequence by removing the last character
                // from sb and adding the current character 'c' instead.
                
                // Mark the last character as unused because we are removing it from sb.
                used[last(sb)-'a'] = false;
                // Remove the last character from the subsequence.
                sb.deleteCharAt(sb.length() - 1);
                // agr lexicographically bda character last pe already rkha hai, and wo humko aage firse mil skta hai, to usko hata do
            }
            
            // Now that we've potentially removed larger characters that can be found later,
            // we can safely add the current character.
            // Mark the current character as used, as it's now in our subsequence.
            used[c-'a'] = true;
            // Append the current character to the subsequence.
            sb.append(c);
        }
        // Convert the StringBuilder to a String and return it as the result.
        return sb.toString();
    }

    // Helper function to get the last character of the StringBuilder.
    private char last(StringBuilder sb) {
        // Return the character at the last index of the StringBuilder.
        return sb.charAt(sb.length() - 1);
    }
}
```

## Interview Tips
*   Explain the greedy choice: Clearly articulate why removing a larger character (if it can be found later) is beneficial for lexicographical order.
*   Trace an example: Walk through a simple string like "cbacdcbc" to demonstrate how the `StringBuilder` and `used` array evolve.
*   Discuss edge cases: Consider empty strings, strings with all same characters, or strings with already sorted distinct characters.
*   Mention the "monotonic stack" pattern: This problem is a classic application of this data structure.

## Revision Checklist
- [ ] Understand the problem statement: smallest subsequence, distinct characters, lexicographically smallest.
- [ ] Identify the greedy approach: build the subsequence character by character.
- [ ] Understand the role of character counts: to know if a character can be revisited.
- [ ] Understand the role of the `used` array: to ensure distinct characters.
- [ ] Implement the `while` loop condition correctly: `sb.length() > 0 && last(sb) > c && count[last(sb)-'a'] > 0`.
- [ ] Handle `used` array updates correctly when characters are removed and added.
- [ ] Test with examples: "bcabc", "cbacdcbc", "leetcode".
- [ ] Analyze time and space complexity.

## Similar Problems
*   Remove Duplicate Letters (LeetCode 316) - This is essentially the same problem with a slightly different phrasing.
*   Monotonic Stack problems in general.

## Tags
`String` `Stack` `Greedy` `Monotonic Stack`
