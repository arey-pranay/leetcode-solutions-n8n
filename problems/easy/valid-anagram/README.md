# Valid Anagram

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sorting`  
**Time:** O(N + M)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean isAnagram(String s, String t) {

        int[] map = new int[26];

        for(char c: s.toCharArray()){
            map[c-'a']++;
        }

        for(char c: t.toCharArray()){
            map[c-'a']--;
        }

        for(int m: map){
            if(m != 0) return false;
        }

        return true;

        
    }
}
```

---

---
## Quick Revision
Given two strings, determine if one is an anagram of the other.
This is solved by counting character frequencies in both strings and comparing them.

## Intuition
An anagram means the two strings have the exact same characters with the exact same frequencies, just in a different order. If we can count the occurrences of each character in the first string and then "subtract" the occurrences of each character in the second string, all counts should end up at zero if they are anagrams.

## Algorithm
1. Initialize an integer array `map` of size 26 (for lowercase English letters 'a' through 'z') to all zeros. This array will store character frequencies.
2. Iterate through the first string `s`. For each character `c` in `s`:
    a. Increment the count in the `map` at the index corresponding to the character (e.g., `map[c - 'a']++`).
3. Iterate through the second string `t`. For each character `c` in `t`:
    a. Decrement the count in the `map` at the index corresponding to the character (e.g., `map[c - 'a']--`).
4. Iterate through the `map` array. For each count `m`:
    a. If any count `m` is not equal to 0, it means the character frequencies don't match, so return `false`.
5. If the loop completes without finding any non-zero counts, it means all character frequencies match, so return `true`.

## Concept to Remember
*   **Character Frequency Counting:** Using arrays or hash maps to store the counts of elements (characters in this case).
*   **Array Indexing with Characters:** The ability to map characters to array indices (e.g., `char - 'a'`).
*   **Two-Pointer/Sliding Window (though not directly used here, related to string manipulation):** Understanding how to iterate and compare parts of strings.

## Common Mistakes
*   **Incorrect Array Size:** Using an array smaller than 26, or not accounting for uppercase letters if the problem statement allowed them.
*   **Off-by-One Errors:** Incorrectly calculating the index for characters (e.g., `c - 'b'` instead of `c - 'a'`).
*   **Not Handling Different Lengths:** Assuming strings are of equal length without an explicit check (though the frequency counting naturally handles this if one string is longer, it's good practice to consider).
*   **Modifying Original Strings:** If the problem disallowed modification, one might accidentally alter the input strings.

## Complexity Analysis
*   **Time:** O(N + M), where N is the length of string `s` and M is the length of string `t`. We iterate through `s` once and `t` once. The final loop through the `map` is O(26), which is constant time.
*   **Space:** O(1). We use a fixed-size array of 26 integers, regardless of the input string lengths.

## Commented Code
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        // Initialize an integer array of size 26 to store character frequencies.
        // Each index corresponds to a lowercase English letter ('a' to 'z').
        int[] map = new int[26];

        // Iterate through the first string 's' to count character frequencies.
        for(char c: s.toCharArray()){
            // For each character 'c', increment its corresponding count in the map.
            // 'c - 'a'' calculates the 0-based index for the character.
            map[c-'a']++;
        }

        // Iterate through the second string 't' to decrement character frequencies.
        for(char c: t.toCharArray()){
            // For each character 'c', decrement its corresponding count in the map.
            // This effectively cancels out characters present in both strings.
            map[c-'a']--;
        }

        // Iterate through the frequency map to check if all counts are zero.
        for(int m: map){
            // If any count 'm' is not zero, it means the character frequencies do not match.
            if(m != 0) return false; // The strings are not anagrams.
        }

        // If all counts are zero, it means both strings have the same characters with the same frequencies.
        return true; // The strings are anagrams.
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the character set (e.g., only lowercase English letters, or can it include uppercase, numbers, symbols?). This affects the size of your frequency map.
*   **Explain Your Approach:** Clearly articulate the frequency counting logic. Walk through an example.
*   **Consider Edge Cases:** Mention what happens with empty strings, strings of different lengths, or strings with only one character.
*   **Alternative Solutions:** Briefly discuss other approaches like sorting both strings and comparing them (though less efficient for this specific problem).

## Revision Checklist
- [ ] Understand the definition of an anagram.
- [ ] Implement character frequency counting using an array.
- [ ] Handle character-to-index mapping correctly (`c - 'a'`).
- [ ] Iterate through both strings to update counts.
- [ ] Verify all counts are zero at the end.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Group Anagrams
*   Palindrome Permutation
*   Longest Substring Without Repeating Characters

## Tags
`Array` `Hash Map` `String` `Counting`
