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
        int[] index = new int[256];
        Arrays.fill(index,-1);
        int i = 0;
        int j = 0;
        int ans = 1;
        while(j<s.length()){
            char c = s.charAt(j);
            if(index[c] >= i) 
            i = index[c] + 1;
            index[c] = j;
            ans = Math.max(ans,j-i+1);
            j++;
        }
        return ans;
    }
}
// abcabcdd
// abc a
```

---

---
## Quick Revision
Find the length of the longest substring in a given string that contains no repeating characters.
This is solved using a sliding window approach with a character map to track character occurrences.

## Intuition
The core idea is to expand a "window" (substring) as much as possible until a repeating character is found. When a repeat is encountered, we need to shrink the window from the left side until the repeating character is no longer within the window. We keep track of the maximum window size seen so far. The "aha moment" is realizing that we don't need to re-scan the entire string to find the next valid window; we can intelligently move the left boundary based on the last seen index of the repeating character.

## Algorithm
1. Initialize `maxLength` to 0.
2. Initialize a data structure (e.g., a hash map or an array acting as a frequency map) to store the last seen index of each character.
3. Initialize two pointers, `left` (start of the window) and `right` (end of the window), both to 0.
4. Iterate through the string with the `right` pointer from 0 to `s.length() - 1`:
    a. Get the current character `currentChar` at `s.charAt(right)`.
    b. Check if `currentChar` is already present in our data structure and if its last seen index is within the current window (i.e., `lastSeenIndex >= left`).
    c. If a repeat is found within the window:
        i. Update `left` to be `lastSeenIndex + 1`. This effectively shrinks the window from the left, discarding the repeating character and everything before it.
    d. Update the last seen index of `currentChar` in the data structure to the current `right` pointer's position.
    e. Calculate the current window length: `currentLength = right - left + 1`.
    f. Update `maxLength = Math.max(maxLength, currentLength)`.
    g. Increment `right` to expand the window.
5. Return `maxLength`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-part of a sequence by maintaining a "window" that moves across the sequence.
*   **Hash Map/Frequency Array:** Used to store and quickly retrieve information about elements within the current window, specifically their last seen positions.
*   **Two Pointers:** `left` and `right` pointers define the boundaries of the sliding window.

## Common Mistakes
*   **Incorrectly updating the `left` pointer:** Not properly accounting for the `lastSeenIndex + 1` when a repeat is found, leading to incorrect window shrinking.
*   **Not handling empty strings:** Failing to return 0 for an empty input string.
*   **Using a simple boolean flag for seen characters:** This doesn't allow us to know *where* the character was last seen, which is crucial for efficiently moving the `left` pointer.
*   **Off-by-one errors in window length calculation:** `right - left + 1` is the correct way to calculate the length of an inclusive window.

## Complexity Analysis
*   **Time:** O(n) - The `right` pointer iterates through the string once. The `left` pointer also moves forward, and in the worst case, it traverses the string at most once. Each character is processed a constant number of times.
*   **Space:** O(min(n, m)) - Where `n` is the length of the string and `m` is the size of the character set (e.g., 256 for ASCII). In the worst case, all characters in the string are unique and stored in the map. If the character set is smaller than the string length, the space is bounded by the character set size.

## Commented Code
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Handle the edge case where the input string is empty.
        if(s.isEmpty()) return 0;
        
        // Initialize an array to store the last seen index of each character.
        // The size 256 covers all possible ASCII characters.
        // Initialize all entries to -1, indicating no character has been seen yet.
        int[] index = new int[256];
        Arrays.fill(index,-1);
        
        // 'i' represents the left boundary of the sliding window.
        int i = 0;
        // 'j' represents the right boundary of the sliding window.
        int j = 0;
        // 'ans' stores the maximum length of the substring found so far.
        // Initialize to 1 because a single character is always a valid substring.
        int ans = 1;
        
        // Iterate through the string using the 'j' pointer (right boundary).
        while(j<s.length()){
            // Get the current character at the right boundary.
            char c = s.charAt(j);
            
            // Check if the current character 'c' has been seen before AND
            // if its last seen index 'index[c]' is within the current window (i.e., >= i).
            if(index[c] >= i) 
            // If a repeating character is found within the window,
            // move the left boundary 'i' to the right of the last occurrence of 'c'.
            // This ensures the window only contains unique characters.
            i = index[c] + 1;
            
            // Update the last seen index of the current character 'c' to its current position 'j'.
            index[c] = j;
            
            // Calculate the length of the current valid substring (window).
            // The length is (right boundary - left boundary + 1).
            // Update 'ans' if the current substring length is greater than the maximum found so far.
            ans = Math.max(ans,j-i+1);
            
            // Move the right boundary 'j' one step forward to expand the window.
            j++;
        }
        // Return the maximum length of the substring without repeating characters.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Sliding Window:** Clearly articulate the concept of expanding and shrinking the window and why it's efficient.
*   **Justify Data Structure Choice:** Explain why a hash map or an array (for fixed character sets) is suitable for tracking character indices.
*   **Walk Through an Example:** Use a string like "abcabcbb" or "pwwkew" to demonstrate how the pointers and the character map update step-by-step.
*   **Discuss Edge Cases:** Mention handling empty strings and strings with all unique characters.

## Revision Checklist
- [ ] Understand the problem statement: Longest substring without repeating characters.
- [ ] Identify the sliding window pattern.
- [ ] Choose an appropriate data structure (map/array) for character tracking.
- [ ] Implement the two-pointer logic (`left`, `right`).
- [ ] Correctly update the `left` pointer when a repeat is found.
- [ ] Calculate and track the maximum window size.
- [ ] Handle the empty string edge case.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Repeating Character Replacement
*   Minimum Window Substring
*   Permutation in String
*   Find All Anagrams in a String

## Tags
`String` `Hash Map` `Sliding Window` `Array`
