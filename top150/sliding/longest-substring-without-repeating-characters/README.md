# Longest Substring Without Repeating Characters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(n)  
**Space:** O(min(n, m)

---

## Solution (java)

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        HashMap<Character,Integer> hm = new HashMap<>();
        int start=0;
        int curr=0;
        int max=1;
        int n = s.length();
        while(curr<n){
            char temp = s.charAt(curr);
            if(hm.containsKey(temp) && hm.get(temp) >= start) start = hm.get(temp) + 1;
            hm.put(temp,curr);
            max = Math.max(max,curr-start+1);
            curr++;
        }
        return max;
    }
}
// uske pehle k index uss no. se bhare hue hai jo humare i se chote hai , vo ignore maarne hi na ab kyonki dupliacte aagye ahi uske , isliye ye check hai 
```

---

---
## Quick Revision
Find the length of the longest substring in a given string that contains no repeating characters.
This is solved using a sliding window approach with a hash map to track character occurrences.

## Intuition
The core idea is to expand a "window" (substring) as much as possible until a repeating character is found. When a repeat is found, we need to shrink the window from the left side until the repeating character is no longer within the window. A hash map is perfect for quickly checking if a character has been seen within the current window and, importantly, where it was last seen. If the last seen index of a character is *before* our current window's start, it doesn't count as a repeat *within* the current window.

## Algorithm
1. Initialize `start` (left pointer of the window) to 0.
2. Initialize `curr` (right pointer of the window) to 0.
3. Initialize `maxLen` (maximum length found so far) to 0.
4. Create a hash map `charIndexMap` to store characters and their last seen indices.
5. Iterate through the string with `curr` from 0 to `n-1` (where `n` is the string length):
    a. Get the current character `currentChar = s.charAt(curr)`.
    b. Check if `currentChar` is already in `charIndexMap` AND its last seen index (`charIndexMap.get(currentChar)`) is greater than or equal to `start`.
        i. If true, it means we've found a repeating character *within* our current window. To resolve this, move `start` to `charIndexMap.get(currentChar) + 1`. This effectively shrinks the window from the left, excluding the previous occurrence of `currentChar`.
    c. Update the last seen index of `currentChar` in `charIndexMap` to `curr`.
    d. Calculate the current window length: `currentLen = curr - start + 1`.
    e. Update `maxLen = Math.max(maxLen, currentLen)`.
    f. Increment `curr`.
6. Return `maxLen`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-part of a data structure (like a string or array) by maintaining a "window" that slides over it.
*   **Hash Map for Frequency/Index Tracking:** Using hash maps (dictionaries) to store and quickly retrieve information about elements, such as their counts or last seen positions.
*   **Two Pointers:** Using two pointers (often `start` and `end` or `left` and `right`) to define and manipulate a dynamic range within a data structure.

## Common Mistakes
*   **Incorrectly updating `start`:** Not checking if the previous occurrence of a character is *within* the current window (i.e., `hm.get(temp) >= start`). Simply moving `start` to `hm.get(temp) + 1` without this check can lead to incorrect results.
*   **Not handling empty strings:** Forgetting to return 0 for an empty input string.
*   **Off-by-one errors in length calculation:** Calculating `curr - start` instead of `curr - start + 1` for the window length.
*   **Not updating the character's index in the map:** Forgetting to `put` the current character and its index back into the map after processing, which is crucial for future checks.

## Complexity Analysis
*   **Time:** O(n) - The `curr` pointer iterates through the string once. The `start` pointer also moves forward, but never backward, and in total, it traverses at most `n` positions. Hash map operations (containsKey, get, put) are O(1) on average.
*   **Space:** O(min(n, m)) - Where `n` is the length of the string and `m` is the size of the character set (e.g., 26 for lowercase English letters, 128 for ASCII). In the worst case, all characters in the string are unique, and the hash map stores them. If the string is longer than the character set, the map size is bounded by the character set size.

## Commented Code
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Handle the edge case where the input string is empty.
        if(s.isEmpty()) return 0;

        // Create a HashMap to store characters and their last seen indices.
        // Key: character, Value: index of its last occurrence.
        HashMap<Character,Integer> hm = new HashMap<>();

        // 'start' is the left pointer of our sliding window. It marks the beginning of the current substring.
        int start=0;

        // 'curr' is the right pointer of our sliding window. It iterates through the string.
        int curr=0;

        // 'max' stores the maximum length of the substring found so far. Initialize to 1 assuming at least one character.
        int max=1;

        // Get the length of the string for loop bounds.
        int n = s.length();

        // Iterate through the string using the 'curr' pointer.
        while(curr<n){
            // Get the character at the current 'curr' position.
            char temp = s.charAt(curr);

            // Check if the current character 'temp' has been seen before AND if its last occurrence
            // is within the current window (i.e., its index is greater than or equal to 'start').
            if(hm.containsKey(temp) && hm.get(temp) >= start) {
                // If a repeat is found within the window, we need to move the 'start' pointer.
                // The new 'start' will be one position after the previous occurrence of 'temp'.
                // This effectively removes the duplicate from the current window.
                start = hm.get(temp) + 1;
            }

            // Regardless of whether it was a repeat or not, update the last seen index of 'temp' to the current 'curr' position.
            hm.put(temp,curr);

            // Calculate the length of the current valid substring (window).
            // It's the difference between the current pointer and the start pointer, plus one.
            // Update 'max' if the current substring length is greater than the maximum found so far.
            max = Math.max(max,curr-start+1);

            // Move the right pointer 'curr' to the next character in the string.
            curr++;
        }
        // After iterating through the entire string, 'max' holds the length of the longest substring without repeating characters.
        return max;
    }
}
// uske pehle k index uss no. se bhare hue hai jo humare i se chote hai , vo ignore maarne hi na ab kyonki dupliacte aagye ahi uske , isliye ye check hai
```

## Interview Tips
*   **Explain the Sliding Window:** Clearly articulate the concept of the sliding window and how it helps optimize the search.
*   **Justify the Hash Map:** Explain why a hash map is the ideal data structure for O(1) lookups of character occurrences and their indices.
*   **Walk Through an Example:** Use a simple string like "abcabcbb" or "pwwkew" to trace the algorithm's execution, showing how `start`, `curr`, and `max` change.
*   **Discuss Edge Cases:** Be prepared to talk about empty strings, strings with all unique characters, and strings with all repeating characters.

## Revision Checklist
- [ ] Understand the sliding window pattern.
- [ ] Know how to use a hash map for character tracking.
- [ ] Implement the logic for updating the `start` pointer correctly.
- [ ] Calculate the window length accurately.
- [ ] Handle the empty string case.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Repeating Character Replacement
*   Permutation in String
*   Find All Anagrams in a String
*   Minimum Window Substring

## Tags
`String` `Hash Map` `Sliding Window` `Two Pointers`
