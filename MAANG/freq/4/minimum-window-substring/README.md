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
Find the smallest substring in string `s` that contains all characters of string `t` (including duplicates).
This is solved using a sliding window approach with frequency maps.

## Intuition
The core idea is to expand a window from left to right (`j`) until it contains all characters of `t`. Once it does, we try to shrink the window from the left (`i`) as much as possible while still maintaining the condition of containing all characters of `t`. This shrinking process helps us find the *minimum* window. We use frequency maps to efficiently track which characters are needed and how many of each we still require.

## Algorithm
1.  **Initialization**:
    *   Create a frequency map (`needed`) for characters in `t`. Store the count of each character.
    *   Initialize `wanted` to the length of `t` (number of characters we still need to find).
    *   Initialize `minLen` to infinity and `startIdx` to -1 to store the minimum window's length and starting index.
    *   Initialize two pointers, `i` (left) and `j` (right), both to 0.

2.  **Expand Window**:
    *   Iterate `j` from 0 to `s.length() - 1`.
    *   For each character `s.charAt(j)`:
        *   If `s.charAt(j)` is in `needed`:
            *   Decrement its count in `needed`.
            *   If the count becomes non-negative (meaning we just found a character we were looking for), decrement `wanted`.

3.  **Shrink Window**:
    *   While `wanted` is 0 (meaning the current window `s[i...j]` contains all characters of `t`):
        *   Update `minLen` and `startIdx` if the current window `(j - i + 1)` is smaller than `minLen`.
        *   Consider the character `s.charAt(i)` at the left end of the window:
            *   If `s.charAt(i)` is in `needed`:
                *   Increment its count in `needed`.
                *   If the count becomes positive (meaning we just removed a character that was essential for satisfying `t`'s requirements), increment `wanted`.
        *   Increment `i` to shrink the window from the left.

4.  **Result**:
    *   After the loop finishes, if `startIdx` is still -1, it means no valid window was found, return an empty string.
    *   Otherwise, return the substring `s.substring(startIdx, startIdx + minLen)`.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous subsegment of a sequence by maintaining a window that moves across the sequence.
*   **Frequency Maps (Hash Maps)**: Used to store and quickly access character counts, crucial for checking if a window satisfies the required character set.
*   **Two Pointers**: `i` and `j` define the boundaries of the sliding window.

## Common Mistakes
*   **Incorrectly handling character counts**: Forgetting to decrement `wanted` when a needed character is found, or incorrectly incrementing it when shrinking.
*   **Not updating minimum window correctly**: Failing to compare the current window's length with the minimum found so far, or not storing the correct start index.
*   **Edge cases**: Not handling cases where `t` is empty, `s` is empty, or no valid window exists.
*   **Off-by-one errors**: In calculating window length `(j - i + 1)` or substring indices.

## Complexity Analysis
*   **Time**: O(N + M), where N is the length of `s` and M is the length of `t`. The outer loop iterates through `s` once (pointer `j`), and the inner loop (pointer `i`) also traverses `s` at most once in total across all iterations. Building the frequency map for `t` takes O(M).
*   **Space**: O(K), where K is the number of unique characters in `t`. This is for storing the frequency map. In the worst case, K can be up to 26 for lowercase English letters or 128/256 for ASCII.

## Commented Code
```java
class Solution {
    public String minWindow(String s, String t) {
        // Get the lengths of the input strings.
        int m = s.length();
        int n = t.length();

        // Initialize minLen to a very large value to track the minimum window length.
        int minLen = Integer.MAX_VALUE;
        // Initialize start to -1 to track the starting index of the minimum window.
        int start = -1;

        // Create a HashMap to store the frequency of characters needed from string t.
        HashMap<Character, Integer> needed = new HashMap<>();
        // Populate the needed map with character counts from string t.
        for (char c : t.toCharArray()) {
            needed.put(c, needed.getOrDefault(c, 0) + 1);
        }

        // Initialize 'wanted' to the total number of characters we need to find from t.
        int wanted = t.length();

        // Initialize the left pointer of the sliding window.
        int i = 0;
        // Iterate through the string s with the right pointer 'j'.
        for (int j = 0; j < m; j++) {
            // Get the current character at the right pointer.
            char c = s.charAt(j);

            // Check if the current character is one of the characters we need from t.
            if (needed.containsKey(c)) {
                // Get the current count of this character in our 'needed' map.
                int currF = needed.get(c);
                // If the count is greater than 0, it means we still need this character.
                if (currF > 0) {
                    // Decrement 'wanted' because we've found one more required character.
                    wanted--;
                }
                // Decrement the count of this character in the 'needed' map.
                needed.put(c, currF - 1);
            }

            // While loop to shrink the window from the left (pointer 'i')
            // as long as the current window contains all characters of t (wanted == 0).
            while (wanted == 0) {
                // If the current window's length (j - i + 1) is smaller than the minimum found so far.
                if (minLen > j - i + 1) {
                    // Update the minimum length.
                    minLen = j - i + 1;
                    // Update the starting index of the minimum window.
                    start = i;
                }

                // Get the character at the left end of the window.
                char ci = s.charAt(i);

                // Check if this character at the left end is one of the characters we need from t.
                if (needed.containsKey(ci)) {
                    // Increment its count in the 'needed' map because we are removing it from the window.
                    int nowCount = needed.get(ci) + 1;
                    needed.put(ci, nowCount);
                    // If the count becomes positive again, it means we now need this character again.
                    if (nowCount > 0) {
                        // Increment 'wanted' because the window no longer satisfies the condition.
                        wanted++;
                    }
                }
                // Move the left pointer 'i' one step to the right to shrink the window.
                i++;
            }
        }

        // If 'start' is still -1, it means no valid window was found.
        // Otherwise, return the substring from 'start' with length 'minLen'.
        return start == -1 ? "" : s.substring(start, start + minLen);
    }
}
```

## Interview Tips
*   **Explain the Sliding Window**: Clearly articulate the expand-and-shrink strategy. Draw it out on a whiteboard if possible.
*   **Frequency Map Logic**: Emphasize how the `needed` map and `wanted` counter work together to track the satisfaction of `t`'s character requirements.
*   **Edge Cases**: Be prepared to discuss what happens if `s` or `t` is empty, or if `t` contains characters not present in `s`.
*   **Optimization**: Briefly mention why this approach is better than brute-force (checking all substrings).

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement the frequency map for `t`.
- [ ] Correctly manage the `wanted` counter.
- [ ] Implement the window expansion logic.
- [ ] Implement the window shrinking logic.
- [ ] Handle the update of minimum window length and start index.
- [ ] Consider edge cases (empty strings, no solution).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Concatenation of All Words

## Tags
`Array` `Hash Map` `Two Pointers` `String` `Sliding Window`
