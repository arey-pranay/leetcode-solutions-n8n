# Longest Substring Without Repeating Characters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(n)  
**Space:** O(min(n, alphabet_size)

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
Find the length of the longest substring in a given string that does not contain any repeating characters.
This is solved using a sliding window approach with a hash map to track character occurrences.

## Intuition
The core idea is to expand a "window" (substring) as much as possible until a repeating character is found. When a repeat is found, we need to shrink the window from the left side until the repeating character is no longer within the window. A hash map is perfect for quickly checking if a character has already appeared within the current window and, crucially, where its last occurrence was. This allows us to efficiently adjust the window's start.

## Algorithm
1. Initialize `start` and `curr` pointers to 0. These define the current window `s[start...curr]`.
2. Initialize `max` length to 0 (or 1 if the string is not empty, as a single character is a valid substring).
3. Use a `HashMap` to store characters and their last seen indices within the string.
4. Iterate through the string with the `curr` pointer from left to right (`curr` from 0 to `n-1`).
5. For each character `s.charAt(curr)`:
    a. Check if the character is already in the `HashMap` AND if its last seen index (`hm.get(temp)`) is greater than or equal to the current `start` pointer. This condition ensures the duplicate is within our *current* window.
    b. If a duplicate is found within the current window, update `start` to be one position *after* the last occurrence of that character (`hm.get(temp) + 1`). This effectively shrinks the window from the left.
    c. Update the `HashMap` with the current character and its current index (`hm.put(temp, curr)`). This records the latest position of this character.
    d. Calculate the length of the current valid substring (`curr - start + 1`) and update `max` if this length is greater than the current `max`.
    e. Increment `curr` to expand the window to the right.
6. Return `max`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-part of a data structure (like a string or array) by maintaining a "window" that moves across it.
*   **Hash Map for Frequency/Index Tracking:** Using a hash map to store key-value pairs (character to its index) allows for O(1) average time complexity for lookups, insertions, and deletions.
*   **Two Pointers:** Using two pointers (`start` and `curr`) to define the boundaries of the sliding window.

## Common Mistakes
*   Not handling the case where the string is empty.
*   Incorrectly updating the `start` pointer when a duplicate is found. Forgetting to check if the duplicate's last seen index is *within the current window* (i.e., `hm.get(temp) >= start`).
*   Not updating the `HashMap` with the *current* index of the character, leading to stale information.
*   Calculating the window length incorrectly (e.g., `curr - start` instead of `curr - start + 1`).

## Complexity Analysis
*   Time: O(n) - The `curr` pointer iterates through the string once. The `start` pointer also moves forward at most `n` times. HashMap operations (put, get, containsKey) are O(1) on average.
*   Space: O(min(n, alphabet_size)) - In the worst case, the HashMap might store all unique characters in the string. If the alphabet size is fixed (e.g., ASCII or Unicode), it's O(alphabet_size). If the string can contain arbitrary characters and is very long, it's O(n).

## Commented Code
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Handle the edge case where the input string is empty.
        if(s.isEmpty()) return 0;
        // Initialize a HashMap to store characters and their last seen indices.
        HashMap<Character,Integer> hm = new HashMap<>();
        // 'start' pointer marks the beginning of the current valid substring (window).
        int start=0;
        // 'curr' pointer marks the end of the current valid substring (window).
        int curr=0;
        // 'max' stores the maximum length found so far. Initialize to 1 for non-empty strings.
        int max=1;
        // Get the length of the string for loop bounds.
        int n = s.length();
        // Iterate through the string using the 'curr' pointer.
        while(curr<n){
            // Get the character at the current 'curr' position.
            char temp = s.charAt(curr);
            // Check if the current character 'temp' is already in the HashMap AND
            // if its last seen index is within the current window (>= start).
            if(hm.containsKey(temp) && hm.get(temp) >= start) {
                // If a duplicate is found within the current window,
                // move the 'start' pointer to the position right after the last occurrence of 'temp'.
                // This effectively removes the duplicate and all characters before it from the window.
                start = hm.get(temp) + 1;
            }
            // Update the HashMap with the current character and its current index.
            // This records the latest position of 'temp'.
            hm.put(temp,curr);
            // Calculate the length of the current valid substring (curr - start + 1)
            // and update 'max' if the current length is greater.
            max = Math.max(max,curr-start+1);
            // Move the 'curr' pointer one step to the right to expand the window.
            curr++;
        }
        // Return the maximum length of a substring without repeating characters.
        return max;
    }
}
```

## Interview Tips
*   Clearly explain the sliding window approach and why a HashMap is suitable.
*   Walk through an example string like "abcabcbb" or "pwwkew" to demonstrate how the `start` and `curr` pointers move and how the `HashMap` is updated.
*   Be prepared to discuss the time and space complexity of your solution.
*   Mention edge cases like empty strings or strings with all unique characters.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify the sliding window pattern.
- [ ] Choose an appropriate data structure (HashMap) for tracking characters.
- [ ] Implement the two-pointer logic correctly (`start` and `curr`).
- [ ] Handle duplicate character detection and window adjustment.
- [ ] Calculate and update the maximum length.
- [ ] Consider edge cases (empty string).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Sliding Window Maximum
*   Minimum Window Substring
*   Longest Repeating Character Replacement
*   Find All Anagrams in a String

## Tags
`String` `Hash Map` `Sliding Window` `Two Pointers`
