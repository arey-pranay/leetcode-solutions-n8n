# Isomorphic Strings

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
import java.util.HashMap;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // If lengths are not the same, they cannot be isomorphic
        if (s.length() != t.length()) return false;

        // Maps to store character mappings from s to t and t to s
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check and establish mapping for s to t
            if (mapS.containsKey(charS)) {
                if (mapS.get(charS) != charT) return false;
            } else {
                mapS.put(charS, charT);
            }

            // Check and establish mapping for t to s
            if (mapT.containsKey(charT)) {
                if (mapT.get(charT) != charS) return false;
            } else {
                mapT.put(charT, charS);
            }
        }

        // If all characters map consistently, return true
        return true;
    }
}
```

---

---
## Problem Summary
Given two strings `s` and `t`, determine if they are isomorphic. Two strings `s` and `t` are isomorphic if the characters in `s` can be replaced to get `t`. All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

## Approach and Intuition
The core idea is to ensure a consistent one-to-one mapping between characters of `s` and `t` while preserving their order.

1.  **Length Check:** If the lengths of `s` and `t` are different, they cannot be isomorphic. This is a quick initial check.
2.  **Two-Way Mapping:** To ensure a true one-to-one mapping, we need to check in both directions:
    *   **`s` to `t`:** For each character in `s`, it must map to a *specific* character in `t`. If we encounter a character in `s` that we've seen before, it *must* map to the same character in `t` as it did previously. If it maps to a different character, the strings are not isomorphic.
    *   **`t` to `s`:** Similarly, for each character in `t`, it must map to a *specific* character in `s`. This prevents two different characters in `s` from mapping to the same character in `t`. For example, if `s = "aba"` and `t = "baa"`, 'a' in `s` maps to 'b' in `t`, and 'b' in `s` maps to 'a' in `t`. However, if we only checked `s` to `t`, we might miss that 'a' in `s` also maps to 'a' in `t` at the end, which is a conflict. The `t` to `s` mapping ensures that if 'a' in `s` maps to 'b' in `t`, then 'b' in `t` *must* map back to 'a' in `s`. If we encounter a character in `t` that we've seen before, it *must* map to the same character in `s` as it did previously.

3.  **Data Structures:** HashMaps are ideal for storing these character mappings. We'll use two HashMaps:
    *   `mapS`: To store mappings from characters in `s` to characters in `t`.
    *   `mapT`: To store mappings from characters in `t` to characters in `s`.

4.  **Iteration:** We iterate through both strings simultaneously using a single loop. For each pair of characters `charS` and `charT` at the same index `i`:
    *   **Check `mapS`:**
        *   If `charS` is already in `mapS`, check if its mapped value is equal to `charT`. If not, return `false`.
        *   If `charS` is not in `mapS`, add the mapping `charS -> charT` to `mapS`.
    *   **Check `mapT`:**
        *   If `charT` is already in `mapT`, check if its mapped value is equal to `charS`. If not, return `false`.
        *   If `charT` is not in `mapT`, add the mapping `charT -> charS` to `mapT`.

5.  **Return True:** If the loop completes without returning `false`, it means a consistent one-to-one mapping was found, and the strings are isomorphic.

## Complexity Analysis
- Time: O(N) - We iterate through the strings once, where N is the length of the strings. HashMap operations (put, get, containsKey) take average O(1) time.
- Space: O(K) - Where K is the number of unique characters in the strings. In the worst case, K can be up to the size of the character set (e.g., 26 for lowercase English letters, or 256 for ASCII). Since the alphabet size is constant, this is often considered O(1) space.

## Code Walkthrough
```java
import java.util.HashMap;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // If lengths are not the same, they cannot be isomorphic
        if (s.length() != t.length()) return false; // O(1) check

        // Maps to store character mappings from s to t and t to s
        HashMap<Character, Character> mapS = new HashMap<>(); // O(1) initialization
        HashMap<Character, Character> mapT = new HashMap<>(); // O(1) initialization

        // Iterate through the strings character by character
        for (int i = 0; i < s.length(); i++) { // Loop runs N times
            char charS = s.charAt(i); // O(1)
            char charT = t.charAt(i); // O(1)

            // Check and establish mapping for s to t
            if (mapS.containsKey(charS)) { // Average O(1)
                if (mapS.get(charS) != charT) return false; // Average O(1)
            } else {
                mapS.put(charS, charT); // Average O(1)
            }

            // Check and establish mapping for t to s
            if (mapT.containsKey(charT)) { // Average O(1)
                if (mapT.get(charT) != charS) return false; // Average O(1)
            } else {
                mapT.put(charT, charS); // Average O(1)
            }
        }

        // If all characters map consistently, return true
        return true; // O(1)
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the character set (e.g., lowercase English letters, ASCII, Unicode). This impacts the space complexity if you were to use arrays instead of hashmaps.
*   **Edge Cases:** Consider empty strings, strings of length 1, and strings with all the same characters.
*   **Explain the Two-Way Mapping:** Emphasize *why* two maps are necessary. A single map from `s` to `t` is insufficient because it doesn't prevent different characters in `s` from mapping to the same character in `t`.
*   **Walk Through Examples:** Use examples like `s = "egg", t = "add"` (isomorphic) and `s = "foo", t = "bar"` (not isomorphic because 'o' maps to 'a' and then 'r') and `s = "paper", t = "title"` (isomorphic) and `s = "badc", t = "baba"` (not isomorphic because 'd' in `s` maps to 'b' in `t`, but 'b' in `t` already maps to 'a' in `s`).
*   **Discuss Complexity:** Be ready to explain the time and space complexity and justify it.

## Optimization and Alternatives
*   **Using Arrays:** If the character set is known and small (e.g., lowercase English letters), you can use two arrays of size 26 (or 128/256 for ASCII) instead of HashMaps. This would guarantee O(1) time for lookups and insertions, and the space complexity would be strictly O(1) (constant size regardless of input string length).
    ```java
    // Example using arrays for lowercase English letters
    class Solution {
        public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) return false;

            int[] mapS = new int[26]; // Stores mapping from s char to t char (index is s char - 'a')
            int[] mapT = new int[26]; // Stores mapping from t char to s char (index is t char - 'a')

            for (int i = 0; i < s.length(); i++) {
                char charS = s.charAt(i);
                char charT = t.charAt(i);

                int indexS = charS - 'a';
                int indexT = charT - 'a';

                // Check s to t mapping
                if (mapS[indexS] == 0) { // If not mapped yet
                    mapS[indexS] = charT; // Map charS to charT
                } else if (mapS[indexS] != charT) { // If already mapped but to a different char
                    return false;
                }

                // Check t to s mapping
                if (mapT[indexT] == 0) { // If not mapped yet
                    mapT[indexT] = charS; // Map charT to charS
                } else if (mapT[indexT] != charS) { // If already mapped but to a different char
                    return false;
                }
            }
            return true;
        }
    }
    ```
    *Note: This array approach assumes ASCII or a limited character set and requires careful handling of the "unmapped" state (e.g., using 0 or a sentinel value if characters can be 0).*

## Revision Checklist
- [ ] Understand the definition of isomorphic strings.
- [ ] Recognize the need for a consistent one-to-one mapping.
- [ ] Implement the length check.
- [ ] Use two HashMaps (or arrays) for bidirectional mapping.
- [ ] Handle cases where a character has already been mapped.
- [ ] Ensure no two characters map to the same character.
- [ ] Test with edge cases (empty strings, single characters, repeated characters).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Pattern
*   Group Anagrams (conceptually related to character counts/patterns)
*   Find And Replace Pattern

## Tags
`Array` `Hash Map`

## My Notes
String question
