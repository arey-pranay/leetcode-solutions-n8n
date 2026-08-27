# Minimum Window Substring

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(N + M)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        int min = Integer.MAX_VALUE;
        int start = -1;
        HashMap<Character, Integer> needed = new HashMap<>();
        for (char c : t.toCharArray()) needed.put(c, needed.getOrDefault(c, 0) + 1);
        int wanted = t.length();
        int i = 0;
        for (int j = 0; j < m; j++) {
            char c = s.charAt(j);
            if (needed.containsKey(c)) {//valid character
                int currF = needed.get(c);
                if (currF> 0) wanted--;
                needed.put(c, currF-1);
            }
            while (wanted == 0) {
                if (min > j - i + 1) {
                    min = j - i + 1;
                    start = i;
                }
                char ci = s.charAt(i);
                if (needed.containsKey(ci)) {
                    int nowCount = needed.get(ci) + 1;
                    needed.put(ci, nowCount);
                    if (nowCount > 0) wanted++;
                }
                i++;
            }
        }
        return start == -1 ? "" : s.substring(start, start + min);
    }
}
```

---

---
## Quick Revision
Given two strings `s` and `t`, find the smallest substring in `s` that contains all characters of `t` (including duplicates).
This is solved using a sliding window approach with frequency maps.

## Intuition
The core idea is to expand a window from left to right in string `s` until it contains all characters required by string `t`. Once a valid window is found, we try to shrink it from the left as much as possible while maintaining the condition that it still contains all characters of `t`. This shrinking process helps us find the *minimum* window. We use frequency maps to efficiently track the characters needed from `t` and the characters we currently have within our window.

## Algorithm
1.  **Initialization**:
    *   Create a frequency map (`needed`) for characters in string `t`. Store the count of each character.
    *   Initialize `wanted` to the length of `t` (number of characters we still need to find).
    *   Initialize `minLen` to infinity and `minStart` to -1 to store the length and starting index of the smallest valid window found so far.
    *   Initialize two pointers, `left` (or `i`) and `right` (or `j`), both to 0.

2.  **Expand Window**:
    *   Iterate `right` from 0 to `s.length() - 1`.
    *   For each character `s[right]`:
        *   If `s[right]` is a character present in `needed`:
            *   Decrement its count in `needed`.
            *   If the count becomes 0 or less (meaning we have enough of this character), decrement `wanted`.

3.  **Shrink Window**:
    *   While `wanted` is 0 (meaning the current window `s[left...right]` is valid):
        *   Update `minLen` and `minStart` if the current window's length (`right - left + 1`) is smaller than `minLen`.
        *   Consider the character `s[left]`:
            *   If `s[left]` is a character present in `needed`:
                *   Increment its count in `needed`.
                *   If the count becomes greater than 0 (meaning we now need this character again), increment `wanted`.
        *   Increment `left` to shrink the window from the left.

4.  **Result**:
    *   After the loop finishes, if `minStart` is still -1, it means no valid window was found, so return an empty string.
    *   Otherwise, return the substring of `s` starting at `minStart` with length `minLen`.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous subsegment of a sequence by maintaining a window and moving its boundaries.
*   **Hash Maps (Frequency Maps)**: Used for O(1) average time lookups and updates of character counts, crucial for tracking required and available characters.
*   **Two Pointers**: `left` and `right` pointers define the current window, allowing for expansion and contraction.

## Common Mistakes
*   **Incorrectly handling character counts**: Forgetting to decrement `wanted` when a character's count in `needed` becomes non-positive, or incorrectly incrementing `wanted` when shrinking.
*   **Off-by-one errors**: In window length calculation (`right - left + 1`) or substring extraction.
*   **Not handling edge cases**: Like empty input strings `s` or `t`, or when `t` is longer than `s`, or when no valid window exists.
*   **Inefficient character tracking**: Using nested loops instead of frequency maps, leading to a much higher time complexity.

## Complexity Analysis
*   **Time**: O(N + M), where N is the length of string `s` and M is the length of string `t`. The `right` pointer iterates through `s` once (O(N)). The `left` pointer also iterates through `s` at most once. Building the frequency map for `t` takes O(M).
*   **Space**: O(K), where K is the number of unique characters in string `t`. This is for storing the frequency map. In the worst case, K can be up to the size of the character set (e.g., 256 for ASCII).

## Commented Code
```java
class Solution {
    public String minWindow(String s, String t) {
        // Get the lengths of the input strings.
        int m = s.length();
        int n = t.length();

        // Initialize minLen to a very large value to track the minimum window length found.
        int minLen = Integer.MAX_VALUE;
        // Initialize start to -1 to track the starting index of the minimum window.
        int start = -1;

        // Create a HashMap to store the frequency of characters needed from string t.
        HashMap<Character, Integer> needed = new HashMap<>();
        // Populate the 'needed' map with character counts from string t.
        for (char c : t.toCharArray()) {
            needed.put(c, needed.getOrDefault(c, 0) + 1);
        }

        // 'wanted' represents the number of characters from 't' that we still need to find in the current window.
        // Initially, it's the total length of 't'.
        int wanted = t.length();

        // Initialize the left pointer of the sliding window.
        int i = 0;
        // Iterate through string 's' with the right pointer 'j'.
        for (int j = 0; j < m; j++) {
            // Get the current character at the right pointer.
            char c = s.charAt(j);

            // Check if the current character 'c' is one of the characters we need from 't'.
            if (needed.containsKey(c)) {
                // Get the current count of this character in our 'needed' map.
                int currF = needed.get(c);
                // If the count is greater than 0, it means we still need this character.
                // So, decrement 'wanted' as we've found one more required character.
                if (currF > 0) {
                    wanted--;
                }
                // Decrement the count of this character in the 'needed' map.
                needed.put(c, currF - 1);
            }

            // While 'wanted' is 0, it means the current window s[i...j] contains all characters of 't'.
            while (wanted == 0) {
                // Check if the current window is smaller than the minimum window found so far.
                if (minLen > j - i + 1) {
                    // Update minLen with the new smaller length.
                    minLen = j - i + 1;
                    // Update start with the starting index of this new minimum window.
                    start = i;
                }

                // Now, try to shrink the window from the left by moving the 'i' pointer.
                // Get the character at the left pointer.
                char ci = s.charAt(i);

                // Check if the character being removed from the left is one of the characters we need from 't'.
                if (needed.containsKey(ci)) {
                    // Get the current count of this character in our 'needed' map.
                    int nowCount = needed.get(ci) + 1; // Incrementing because we are removing it from window
                    // Increment its count in the 'needed' map.
                    needed.put(ci, nowCount);
                    // If the count becomes greater than 0 after incrementing, it means we now need this character again.
                    // So, increment 'wanted' because the window is no longer valid without this character.
                    if (nowCount > 0) {
                        wanted++;
                    }
                }
                // Move the left pointer to shrink the window.
                i++;
            }
        }

        // After iterating through 's', if 'start' is still -1, it means no valid window was found.
        // Otherwise, return the substring of 's' from 'start' with length 'minLen'.
        return start == -1 ? "" : s.substring(start, start + minLen);
    }
}
```

## Interview Tips
*   **Explain the Sliding Window**: Clearly articulate how the window expands and contracts, and why this approach is efficient.
*   **Frequency Map Logic**: Detail how you use the `needed` map and the `wanted` counter to track character requirements. Emphasize the conditions for decrementing/incrementing `wanted`.
*   **Edge Cases**: Be prepared to discuss what happens with empty strings, `t` longer than `s`, or when no solution exists.
*   **Optimization**: If asked, discuss potential optimizations (though this solution is already optimal in terms of time complexity).

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement the frequency map for `t`.
- [ ] Implement the sliding window logic (expand and shrink).
- [ ] Correctly manage `wanted` counter.
- [ ] Handle window length and start index updates.
- [ ] Implement substring extraction for the final result.
- [ ] Test with edge cases (empty strings, no solution).

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Concatenation of All Words

## Tags
`Array` `Hash Map` `Two Pointers` `String` `Sliding Window`
