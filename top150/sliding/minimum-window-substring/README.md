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
        if (s.length() < t.length()) return "";
        
        Queue<Integer> validIndex = new LinkedList<>();
        HashMap<Character, Integer> og = new HashMap<>();
        for (char i : t.toCharArray()) og.put(i, og.getOrDefault(i, 0) + 1);

        int index = -1;
        int min = Integer.MAX_VALUE;

        int rem = t.length();
                
        for(int j=0;j<s.length();j++) {
            char curr = s.charAt(j);
            if (og.containsKey(curr)) {
                validIndex.offer(j);
                if (og.get(curr) > 0) rem--;
                og.put(curr, og.get(curr) - 1);
            }
            while (rem == 0) {
                int i = validIndex.poll();
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    index = i;
                }
                char firstMatch = s.charAt(i);
                og.put(firstMatch, og.get(firstMatch) + 1);
                if(og.get(firstMatch) > 0) rem++;
            }
        }
        return index == -1 ? "" : s.substring(index, index + min);
    }
}
```

---

---
## Quick Revision
This problem asks for the smallest substring in a larger string `s` that contains all characters of a target string `t` (including duplicates).
We solve this using a sliding window approach with two pointers and frequency maps.

## Intuition
The core idea is to expand a window from the left until it contains all characters of `t`. Once it does, we try to shrink the window from the left as much as possible while still satisfying the condition. This shrinking process helps us find the minimum possible window. We need to efficiently track which characters from `t` are present in our current window and how many more we need.

## Algorithm
1.  **Initialization**:
    *   Create a frequency map (`og`) for characters in `t`.
    *   Initialize `rem` (remaining characters needed from `t`) to `t.length()`.
    *   Initialize `minLen` to `Integer.MAX_VALUE` and `startIndex` to `-1` to store the best window found so far.
    *   Use a queue (`validIndex`) to store the indices of characters from `s` that are part of `t`.
    *   Initialize a left pointer `i` (implicitly handled by the queue's `poll` operation) and a right pointer `j` to `0`.

2.  **Sliding Window Expansion**:
    *   Iterate through `s` with the right pointer `j` from `0` to `s.length() - 1`.
    *   For each character `curr` at `s.charAt(j)`:
        *   If `curr` is a character present in `t` (i.e., `og.containsKey(curr)`):
            *   Add its index `j` to the `validIndex` queue.
            *   If the count of `curr` in `og` is greater than 0, it means we still need this character, so decrement `rem`.
            *   Decrement the count of `curr` in `og`.

3.  **Sliding Window Contraction**:
    *   While `rem` is 0 (meaning the current window contains all characters of `t`):
        *   Dequeue the leftmost index `i` from `validIndex`.
        *   Calculate the current window length: `currentLen = j - i + 1`.
        *   If `currentLen` is less than `minLen`:
            *   Update `minLen = currentLen`.
            *   Update `startIndex = i`.
        *   Get the character `firstMatch` at `s.charAt(i)`.
        *   Increment the count of `firstMatch` in `og`.
        *   If the count of `firstMatch` in `og` becomes greater than 0, it means we now need this character again, so increment `rem`.

4.  **Result**:
    *   After iterating through `s`, if `startIndex` is still `-1`, it means no valid window was found, so return an empty string `""`.
    *   Otherwise, return the substring of `s` starting at `startIndex` with length `minLen`: `s.substring(startIndex, startIndex + minLen)`.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous subsegment of an array or string.
*   **Frequency Maps (Hash Maps)**: Used to store and quickly access character counts for both the target string and the current window.
*   **Two Pointers**: One pointer expands the window, and another (implicitly managed by the queue here) contracts it.
*   **Greedy Approach**: At each step, we try to make the locally optimal choice (shrinking the window) to achieve the globally optimal solution (minimum window).

## Common Mistakes
*   **Incorrectly handling character counts**: Forgetting to decrement `rem` when a needed character is found, or incorrectly incrementing it when shrinking.
*   **Not handling duplicate characters in `t`**: The frequency map must account for multiple occurrences of the same character.
*   **Off-by-one errors in window length calculation**: `j - i + 1` is crucial.
*   **Not initializing `minLen` and `startIndex` correctly**: Leading to incorrect or no results when no window is found.
*   **Inefficiently checking if a window is valid**: Using a frequency map for the window itself can be slower than tracking the `rem` count.

## Complexity Analysis
*   **Time**: O(N + M), where N is the length of string `s` and M is the length of string `t`.
    *   We iterate through `s` once with the right pointer `j`.
    *   The left pointer (implicitly via `validIndex.poll()`) also traverses `s` at most once.
    *   Building the frequency map for `t` takes O(M) time.
    *   Hash map operations (get, put, containsKey) are O(1) on average.
*   **Space**: O(K), where K is the number of unique characters in string `t`.
    *   The `og` hash map stores character counts for `t`. In the worst case, `K` can be up to the size of the character set (e.g., 26 for English lowercase letters, or 128/256 for ASCII).
    *   The `validIndex` queue can store up to N indices in the worst case, but its size is bounded by the number of characters from `t` encountered in `s`. However, the dominant space complexity comes from the hash map if K is smaller than N. If we consider the queue's potential size, it could be O(N) in a pathological case where `t` has many unique characters and `s` is very long. But typically, K is much smaller than N.

## Commented Code
```java
class Solution {
    public String minWindow(String s, String t) {
        // If the string s is shorter than t, it's impossible to find a window, so return empty.
        if (s.length() < t.length()) return "";
        
        // Queue to store the indices of characters from 's' that are part of 't'.
        // This helps in efficiently removing characters from the left of the window.
        Queue<Integer> validIndex = new LinkedList<>();
        
        // HashMap to store the frequency of characters required from string 't'.
        // Key: character, Value: count of that character needed.
        HashMap<Character, Integer> og = new HashMap<>();
        // Populate the frequency map for characters in 't'.
        for (char i : t.toCharArray()) {
            // For each character in 't', increment its count in the map.
            // getOrDefault handles cases where the character is not yet in the map.
            og.put(i, og.getOrDefault(i, 0) + 1);
        }

        // Variable to store the starting index of the minimum window found so far.
        // Initialized to -1, indicating no valid window found yet.
        int index = -1;
        // Variable to store the minimum length of the window found so far.
        // Initialized to the maximum possible integer value.
        int min = Integer.MAX_VALUE;

        // 'rem' represents the number of characters from 't' that are still needed in the current window.
        // Initially, it's the total length of 't' because we need all its characters.
        int rem = t.length();
                
        // Iterate through the string 's' using a right pointer 'j'.
        for(int j = 0; j < s.length(); j++) {
            // Get the current character at the right pointer.
            char curr = s.charAt(j);
            
            // Check if the current character is one of the characters required by 't'.
            if (og.containsKey(curr)) {
                // If it is, add its index 'j' to our queue of valid indices.
                validIndex.offer(j);
                
                // If the count of this character in 'og' is still positive, it means we still needed this character.
                // So, decrement 'rem' as we've now found one more required character.
                if (og.get(curr) > 0) {
                    rem--;
                }
                // Decrement the count of this character in 'og'. This signifies that we've "used" one instance of this character.
                og.put(curr, og.get(curr) - 1);
            }
            
            // This loop executes when 'rem' becomes 0, meaning the current window (from the leftmost valid index to 'j')
            // contains all characters of 't' with their required frequencies.
            while (rem == 0) {
                // Dequeue the leftmost index 'i' from the queue. This is the start of our current valid window.
                int i = validIndex.poll();
                
                // Calculate the length of the current window.
                // If this window is smaller than the minimum window found so far, update 'min' and 'index'.
                if (j - i + 1 < min) {
                    min = j - i + 1; // Update minimum length.
                    index = i;       // Update the starting index of the minimum window.
                }
                
                // Get the character at the leftmost index 'i' of the current window.
                char firstMatch = s.charAt(i);
                
                // Increment the count of 'firstMatch' back in 'og'. This is because we are about to remove it from the window.
                og.put(firstMatch, og.get(firstMatch) + 1);
                
                // If the count of 'firstMatch' in 'og' becomes positive again, it means we now need this character again
                // to satisfy the condition for 't'. So, increment 'rem'.
                if(og.get(firstMatch) > 0) {
                    rem++;
                }
                // The 'while (rem == 0)' loop will then terminate if 'rem' is no longer 0, or continue shrinking if 'rem' is still 0.
            }
        }
        // After iterating through 's', if 'index' is still -1, it means no valid window was found.
        // Otherwise, return the substring of 's' from 'index' with length 'min'.
        return index == -1 ? "" : s.substring(index, index + min);
    }
}
```

## Interview Tips
1.  **Explain the Sliding Window**: Clearly articulate the two-pointer approach (expansion and contraction) and why it's suitable for finding minimum/maximum contiguous subarrays/substrings.
2.  **Frequency Map Management**: Emphasize how the frequency map (`og`) is used to track required characters and how `rem` efficiently signals when the window is valid. Be ready to explain the logic for incrementing/decrementing `rem`.
3.  **Edge Cases**: Discuss handling cases where `s` is shorter than `t`, or when no valid window exists. Also, consider empty strings for `s` or `t` if not explicitly ruled out by constraints.
4.  **Queue Usage**: Explain the role of the queue in storing indices of relevant characters and how it helps in efficiently identifying the start of the window for contraction.

## Revision Checklist
- [ ] Understand the problem: find the smallest substring in `s` containing all chars of `t`.
- [ ] Recognize the Sliding Window pattern.
- [ ] Implement frequency map for target string `t`.
- [ ] Use `rem` to track needed characters.
- [ ] Implement window expansion (right pointer `j`).
- [ ] Implement window contraction (left pointer `i`, managed by queue).
- [ ] Correctly update minimum window length and start index.
- [ ] Handle edge cases (e.g., `s` shorter than `t`, no solution).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Concatenation of All Words
*   Smallest Range Covering Elements from K Lists

## Tags
`Array` `Hash Map` `Two Pointers` `Sliding Window`
