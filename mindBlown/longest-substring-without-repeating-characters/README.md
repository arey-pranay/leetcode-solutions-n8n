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
Find the length of the longest substring in a given string that does not contain any repeating characters.
This is solved using a sliding window approach with a hash map to track character occurrences.

## Intuition
The core idea is to expand a "window" (substring) as much as possible until a repeating character is found. When a repeat is found, we need to shrink the window from the left side until the repeating character is no longer within the window. A hash map is perfect for quickly checking if a character has already been seen within the current window and, importantly, where its last occurrence was. This allows us to efficiently adjust the window's start.

## Algorithm
1. Initialize `start` (left pointer of the window) to 0.
2. Initialize `curr` (right pointer of the window) to 0.
3. Initialize `maxLen` (maximum length found so far) to 0.
4. Create a hash map `charIndexMap` to store characters and their last seen indices.
5. Iterate through the string with the `curr` pointer from 0 to `n-1` (where `n` is the string length):
    a. Get the current character `currentChar` at `s.charAt(curr)`.
    b. Check if `currentChar` is already in `charIndexMap` AND its last seen index (`charIndexMap.get(currentChar)`) is greater than or equal to `start`. This condition ensures the duplicate is within the *current* window.
    c. If a duplicate is found within the current window:
        i. Update `start` to be `charIndexMap.get(currentChar) + 1`. This effectively slides the window's start past the previous occurrence of the repeating character.
    d. Update the `charIndexMap` with `currentChar` and its current index `curr`.
    e. Calculate the current window length: `curr - start + 1`.
    f. Update `maxLen` to be the maximum of `maxLen` and the current window length.
    g. Increment `curr` to expand the window.
6. Return `maxLen`.

## Concept to Remember
*   **Sliding Window:** A technique used to solve problems on arrays or strings where a contiguous sub-part (window) is considered and moved across the data.
*   **Hash Map (Dictionary):** Efficient data structure for key-value lookups, crucial here for O(1) average time complexity to check for character existence and retrieve their indices.
*   **Two Pointers:** `start` and `curr` act as pointers defining the boundaries of the sliding window.

## Common Mistakes
*   Not handling the case where the duplicate character's previous occurrence is *outside* the current window (i.e., `hm.get(temp) < start`). In this case, it's not a true duplicate within the current valid substring.
*   Incorrectly updating `start` when a duplicate is found, e.g., simply moving `start` by one instead of jumping to the position after the duplicate.
*   Forgetting to update the character's index in the hash map *after* checking for duplicates, which is essential for future checks.
*   Not initializing `maxLen` correctly (e.g., to 0 when the string might have length 1).

## Complexity Analysis
*   **Time:** O(n) - The `curr` pointer iterates through the string once. Each character is visited at most twice (once by `curr` and potentially once when `start` is updated). Hash map operations (put, get, containsKey) are O(1) on average.
*   **Space:** O(min(n, m)) - Where `n` is the length of the string and `m` is the size of the character set (e.g., 26 for lowercase English letters, 128 for ASCII). In the worst case, all characters in the string are unique and stored in the hash map.

## Commented Code
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Handle the edge case where the input string is empty.
        if(s.isEmpty()) return 0;

        // Initialize a HashMap to store characters and their last seen indices.
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
            // Get the character at the current position.
            char temp = s.charAt(curr);

            // Check if the current character 'temp' has been seen before AND if its last occurrence
            // is within the current window (i.e., its index is >= 'start').
            if(hm.containsKey(temp) && hm.get(temp) >= start) {
                // If a duplicate is found within the current window, we need to move the 'start' pointer.
                // We move 'start' to the position right after the previous occurrence of 'temp'.
                // This effectively removes the duplicate and all characters before it from the current window.
                start = hm.get(temp) + 1;
            }

            // Update the HashMap with the current character and its current index.
            // This records the latest position of 'temp'.
            hm.put(temp,curr);

            // Calculate the length of the current valid substring (window).
            // The length is 'curr' (end index) - 'start' (start index) + 1.
            // Update 'max' if the current substring length is greater than the maximum found so far.
            max = Math.max(max,curr-start+1);

            // Move the right pointer 'curr' one step forward to expand the window.
            curr++;
        }

        // Return the maximum length of the substring without repeating characters.
        return max;
    }
}
```

## Interview Tips
*   Clearly explain the sliding window concept and how the two pointers (`start` and `curr`) work together.
*   Emphasize the role of the hash map in efficiently tracking character occurrences and their indices.
*   Walk through an example string like "abcabcbb" or "pwwkew" to demonstrate the algorithm's step-by-step execution.
*   Be prepared to discuss the time and space complexity and justify it.
*   Mention how you handle the edge case of an empty string.

## Revision Checklist
- [ ] Understand the problem statement: Longest substring without repeating characters.
- [ ] Identify the sliding window pattern.
- [ ] Understand the role of the hash map for character tracking.
- [ ] Implement the two pointers (`start`, `curr`).
- [ ] Correctly update `start` when a duplicate is found within the window.
- [ ] Correctly update the hash map with the latest character index.
- [ ] Calculate and update the maximum length.
- [ ] Handle edge cases (empty string).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Repeating Character Replacement
*   Permutation in String
*   Find All Anagrams in a String
*   Minimum Window Substring

## Tags
`Array` `Hash Map` `Sliding Window` `String` `Two Pointers`
