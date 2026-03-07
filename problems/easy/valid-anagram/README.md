# Valid Anagram

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sorting`  
**Time:** See complexity section  
**Space:** See complexity section

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
## Problem Summary
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Approach and Intuition
The core idea behind checking for anagrams is that two strings are anagrams if and only if they contain the same characters with the same frequencies.

The provided solution uses a frequency map (an array of size 26 for lowercase English letters) to count the occurrences of each character.

1.  **Initialization**: An integer array `map` of size 26 is created. This array will store the frequency of each lowercase English letter ('a' through 'z'). All elements are initialized to 0 by default.
2.  **Count Characters in `s`**: The code iterates through each character `c` in the string `s`. For each character, it increments the count at the corresponding index in the `map`. The index is calculated by `c - 'a'`, which maps 'a' to 0, 'b' to 1, and so on.
3.  **Decrement Characters in `t`**: The code then iterates through each character `c` in the string `t`. For each character, it decrements the count at the corresponding index in the `map`.
4.  **Check Frequencies**: Finally, the code iterates through the `map`. If any element in the `map` is not zero, it means the character frequencies do not match between `s` and `t`, so they are not anagrams, and the function returns `false`.
5.  **Return True**: If the loop completes without finding any non-zero elements in the `map`, it means all character frequencies matched, and the function returns `true`.

## Complexity Analysis
- Time: O(N) - The time complexity is dominated by iterating through the strings `s` and `t` once, and then iterating through the frequency map (which has a constant size of 26). If N is the length of the longer string, the operations are proportional to N.
- Space: O(1) - The space complexity is constant because the `map` array has a fixed size of 26, regardless of the input string lengths.

## Code Walkthrough
```java
class Solution {
    public boolean isAnagram(String s, String t) {

        // Initialize an array of size 26 to store character counts.
        // Each index corresponds to a lowercase English letter ('a' to 'z').
        int[] map = new int[26];

        // Iterate through the first string 's'.
        // For each character, increment its count in the 'map'.
        // 'c - 'a'' converts the character to its corresponding index (0-25).
        for(char c: s.toCharArray()){
            map[c-'a']++;
        }

        // Iterate through the second string 't'.
        // For each character, decrement its count in the 'map'.
        // If 't' has a character not in 's' or more of a character than 's',
        // this will result in a negative count.
        for(char c: t.toCharArray()){
            map[c-'a']--;
        }

        // Iterate through the 'map' to check if all counts are zero.
        // If any count is non-zero, it means the character frequencies
        // do not match between 's' and 't', so they are not anagrams.
        for(int m: map){
            if(m != 0) return false;
        }

        // If all counts are zero, it means 't' is an anagram of 's'.
        return true;
    }
}
```

## Interview Tips
*   **Clarify Constraints**: Always ask about the character set (e.g., only lowercase English letters, or can it include uppercase, numbers, or special characters?). This affects the size of your frequency map or whether a HashMap is more appropriate.
*   **Edge Cases**: Consider empty strings, strings of different lengths, and strings with only one character. The current solution handles different lengths implicitly because the final check will reveal mismatches.
*   **Explain Your Logic**: Clearly articulate the "same characters, same frequencies" principle.
*   **Discuss Alternatives**: Be prepared to discuss other approaches, like sorting.
*   **Time/Space Trade-offs**: Explain why the chosen approach is efficient.

## Optimization and Alternatives
1.  **Sorting**:
    *   **Approach**: Convert both strings to character arrays, sort them, and then compare the sorted arrays. If they are identical, the strings are anagrams.
    *   **Time Complexity**: O(N log N) due to sorting.
    *   **Space Complexity**: O(N) or O(log N) depending on the sorting algorithm's space complexity (e.g., `Arrays.sort` in Java for primitives uses O(log N) for quicksort, but for objects it might use O(N) for mergesort).
    *   **Trade-off**: Simpler to implement for some, but less time-efficient than the frequency map approach.

2.  **HashMap (for larger character sets)**:
    *   **Approach**: If the character set is not limited to 26 lowercase letters (e.g., Unicode characters), a `HashMap<Character, Integer>` would be more suitable than a fixed-size array.
    *   **Time Complexity**: O(N) on average.
    *   **Space Complexity**: O(K), where K is the number of unique characters in the strings. In the worst case, K can be up to N.

3.  **Early Exit for Length Mismatch**:
    *   **Optimization**: Add a check at the beginning: `if (s.length() != t.length()) return false;`. This is a quick way to rule out non-anagrams and can save computation if lengths differ. The current solution implicitly handles this, but an explicit check is clearer and potentially faster.

## Revision Checklist
- [ ] Understand the definition of an anagram.
- [ ] Identify the core property: same characters, same frequencies.
- [ ] Choose an appropriate data structure for frequency counting (array for fixed small alphabet, HashMap for larger/unknown alphabets).
- [ ] Implement the counting logic correctly (increment for one string, decrement for the other).
- [ ] Implement the final check for zero frequencies.
- [ ] Consider edge cases like empty strings and different lengths.
- [ ] Analyze time and space complexity.
- [ ] Be ready to discuss alternative approaches and their trade-offs.

## Similar Problems
*   Group Anagrams
*   Palindrome Permutation
*   Longest Word in Dictionary through Deleting

## Tags
`Array` `Hash Map`

## My Notes
cmntt
