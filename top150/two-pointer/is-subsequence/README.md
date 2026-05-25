# Is Subsequence

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Two Pointers` `String` `Dynamic Programming`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i =0;
        if(s.isEmpty()) return true;
        for(char c : t.toCharArray()) if(i == s.length()) return true; else if(s.charAt(i)==c) i++;
        return i == s.length();
    }
}
```

---

---
## Quick Revision
Given two strings `s` and `t`, determine if `s` is a subsequence of `t`.
A subsequence is formed by deleting zero or more characters from the original string without changing the relative order of the remaining characters.

## Intuition
The core idea is to iterate through the target string `t` and try to match characters of the source string `s` in order. We use two pointers, one for `s` and one for `t`. If the current character in `t` matches the character pointed to by the `s` pointer, we advance the `s` pointer. If we successfully traverse all characters of `s`, then `s` is a subsequence of `t`.

## Algorithm
1. Initialize two pointers, `s_ptr` to 0 (for string `s`) and `t_ptr` to 0 (for string `t`).
2. Handle the edge case: if `s` is empty, it is always a subsequence of any `t`, so return `true`.
3. Iterate through the string `t` using `t_ptr`.
4. Inside the loop, check if `s_ptr` has reached the end of `s`. If it has, it means all characters of `s` have been found in `t` in order, so return `true`.
5. Compare the character at `s.charAt(s_ptr)` with the character at `t.charAt(t_ptr)`.
6. If they match, increment `s_ptr` to look for the next character in `s`.
7. Always increment `t_ptr` to move to the next character in `t`.
8. After the loop finishes (meaning we've iterated through all of `t`), check if `s_ptr` has reached the end of `s`. If it has, return `true`; otherwise, return `false`.

## Concept to Remember
*   **Two Pointers:** Efficiently traversing and comparing elements in one or two sequences.
*   **Subsequence Definition:** Understanding that order matters but characters can be skipped.
*   **Edge Case Handling:** Recognizing and correctly addressing scenarios like an empty source string.

## Common Mistakes
*   Forgetting to handle the case where `s` is an empty string.
*   Incorrectly advancing pointers, e.g., advancing `s_ptr` even when characters don't match.
*   Not checking if `s_ptr` has reached the end of `s` *before* attempting to access `s.charAt(s_ptr)` within the loop, which could lead to an `IndexOutOfBoundsException`.
*   Returning `true` prematurely if `s_ptr` reaches the end of `s` but the loop for `t` hasn't finished yet (though the provided solution handles this correctly by returning early).

## Complexity Analysis
*   Time: O(N) - where N is the length of string `t`. We iterate through `t` at most once.
*   Space: O(1) - We only use a few variables (pointers), which do not depend on the input size.

## Commented Code
```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0; // Initialize pointer for string s
        if (s.isEmpty()) return true; // If s is empty, it's always a subsequence, return true immediately.
        // Iterate through each character 'c' in the target string 't'
        for (char c : t.toCharArray()) {
            // If the pointer for 's' has already reached the end of 's', it means all characters of 's' have been found.
            if (i == s.length()) return true;
            // If the current character 'c' from 't' matches the character at the current pointer 'i' in 's'
            else if (s.charAt(i) == c) {
                i++; // Move the pointer for 's' to the next character.
            }
        }
        // After iterating through all of 't', check if the pointer for 's' has reached the end of 's'.
        // If it has, all characters of 's' were found in order.
        return i == s.length();
    }
}
```

## Interview Tips
*   Clearly explain your two-pointer approach and why it works for checking subsequences.
*   Walk through an example with the interviewer, showing how the pointers move.
*   Discuss edge cases like empty strings and identical strings.
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the definition of a subsequence.
- [ ] Implement the two-pointer approach correctly.
- [ ] Handle the empty string edge case for `s`.
- [ ] Ensure pointers are advanced only when characters match.
- [ ] Verify the final condition for returning `true` or `false`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Common Subsequence
*   Valid Anagram
*   Two Sum

## Tags
`Two Pointers` `String`
