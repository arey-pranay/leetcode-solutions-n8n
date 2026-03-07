# Word Pattern

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        
    }
}
```

---

---
## Problem Summary
Given a `pattern` string consisting of letters and a `s` string consisting of words separated by spaces, determine if there is a bijection between letters in the `pattern` and non-empty words in `s`. A bijection means that each letter maps to exactly one word, and each word maps to exactly one letter.

## Approach and Intuition
The core idea is to establish and verify a one-to-one mapping between characters in the `pattern` and words in the string `s`. We can use hash maps to store these mappings.

1.  **Split the string `s` into words:** The first step is to break down the input string `s` into individual words based on spaces.
2.  **Check length consistency:** If the number of characters in `pattern` does not match the number of words in `s`, a bijection is impossible, so we can immediately return `false`.
3.  **Maintain two mappings:**
    *   A map from characters in `pattern` to words in `s` (`charToWord`).
    *   A map from words in `s` to characters in `pattern` (`wordToChar`).
4.  **Iterate and check mappings:** Iterate through the `pattern` and the words simultaneously. For each pair of `(character, word)`:
    *   **Check `charToWord`:**
        *   If the character is already in `charToWord`, check if its mapped word is the same as the current word. If not, return `false`.
        *   If the character is not in `charToWord`, add the mapping `character -> word`.
    *   **Check `wordToChar`:**
        *   If the word is already in `wordToChar`, check if its mapped character is the same as the current character. If not, return `false`.
        *   If the word is not in `wordToChar`, add the mapping `word -> character`.
5.  **Return `true`:** If the loop completes without returning `false`, it means a consistent bijection exists, so return `true`.

## Complexity Analysis
- Time: O(N + M) - where N is the length of the `pattern` string and M is the length of the string `s`. Splitting `s` takes O(M) time. Iterating through the pattern and words takes O(N) time (since N must equal the number of words). Hash map operations (put, get, containsKey) are O(1) on average.
- Space: O(N + K) - where N is the number of unique characters in `pattern` and K is the number of unique words in `s`. This is due to the storage required for the two hash maps. In the worst case, all characters and words are unique, so it could be O(N) for `charToWord` and O(K) for `wordToChar`. Since the number of words is equal to the length of the pattern (let's say L), the space complexity is O(L) for `charToWord` and O(L) for `wordToWord`, totaling O(L).

## Code Walkthrough
```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        // 1. Split the string s into words
        String[] words = s.split(" ");

        // 2. Check length consistency
        if (pattern.length() != words.length) {
            return false;
        }

        // 3. Maintain two mappings
        // Map from character to word
        Map<Character, String> charToWord = new HashMap<>();
        // Map from word to character
        Map<String, Character> wordToChar = new HashMap<>();

        // 4. Iterate and check mappings
        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);
            String currentWord = words[i];

            // Check charToWord mapping
            if (charToWord.containsKey(currentChar)) {
                // If char is already mapped, check if it maps to the current word
                if (!charToWord.get(currentChar).equals(currentWord)) {
                    return false; // Mismatch
                }
            } else {
                // If char is not mapped, add the mapping
                charToWord.put(currentChar, currentWord);
            }

            // Check wordToChar mapping
            if (wordToChar.containsKey(currentWord)) {
                // If word is already mapped, check if it maps to the current character
                if (!wordToChar.get(currentWord).equals(currentChar)) {
                    return false; // Mismatch
                }
            } else {
                // If word is not mapped, add the mapping
                wordToChar.put(currentWord, currentChar);
            }
        }

        // 5. Return true if all checks pass
        return true;
    }
}
```

## Interview Tips
*   **Clarify the bijection:** Ensure you understand what a bijection means in this context (one-to-one and onto).
*   **Edge cases:** Consider empty strings for `pattern` or `s`, or `s` with no spaces. The problem statement implies non-empty words, but it's good to think about.
*   **Data structures:** Hash maps are the natural choice for maintaining mappings. Think about why two maps are needed (to enforce the "one-to-one" aspect from both directions).
*   **Early exit:** Identify conditions where you can return `false` early (e.g., length mismatch).
*   **Code clarity:** Use meaningful variable names (`charToWord`, `wordToChar`).
*   **Testing:** Mentally walk through examples like:
    *   `pattern = "abba", s = "dog cat cat dog"` (True)
    *   `pattern = "abba", s = "dog cat cat fish"` (False)
    *   `pattern = "aaaa", s = "dog cat cat dog"` (False)
    *   `pattern = "abba", s = "dog dog dog dog"` (False)

## Optimization and Alternatives
*   **Single Pass with Two Maps:** The current approach is already quite efficient. The use of two hash maps ensures that we check the bijection property from both directions in a single pass.
*   **Alternative: Using `indexOf` and `lastIndexOf` (Less Efficient):** One could try to use string manipulation functions like `indexOf` and `lastIndexOf` to find the first occurrence of a character/word and check if all occurrences match. However, this would likely lead to a less efficient solution, potentially O(N*M) or worse, due to repeated string searching. The hash map approach is superior.
*   **Alternative: Using `Arrays.asList` and `Collections.frequency` (Less Efficient):** Another less efficient approach might involve converting the pattern and words to lists and then using frequency checks, but this would also be more complex and less performant than the hash map solution.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core requirement: bijection.
- [ ] Choose appropriate data structures (hash maps).
- [ ] Handle length mismatch early.
- [ ] Implement bidirectional mapping checks.
- [ ] Test with various valid and invalid inputs.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Isomorphic Strings
*   Group Anagrams (uses similar hashing/mapping concepts)
*   Two Sum (basic hash map usage)

## Tags
`Array` `Hash Map`
