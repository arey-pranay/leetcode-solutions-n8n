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
This problem asks for the smallest substring in a larger string `s` that contains all characters of a target string `t`, including duplicates.
We solve this using a sliding window approach with two pointers and frequency maps.

## Intuition
The core idea is to expand a window from the left until it *potentially* contains all characters of `t`. Once it does, we try to shrink the window from the left as much as possible while still maintaining the condition of containing all characters of `t`. This shrinking process helps us find the *minimum* window. We keep track of the smallest valid window found so far.

## Algorithm
1.  **Initialization**:
    *   Create a frequency map (`og`) for characters in `t`.
    *   Initialize `rem` (remaining characters needed from `t`) to `t.length()`.
    *   Initialize `minLen` to `Integer.MAX_VALUE` and `startIndex` to -1 to store the best window found.
    *   Use a queue (`validIndex`) to store the indices of characters from `s` that are part of `t`.
2.  **Sliding Window (Expansion)**:
    *   Iterate through `s` with a right pointer `j`.
    *   If `s.charAt(j)` is a character present in `t` (i.e., in `og`):
        *   Add its index `j` to `validIndex`.
        *   If the count of this character in `og` is greater than 0, decrement `rem` (meaning we've found one more required character).
        *   Decrement the count of `s.charAt(j)` in `og`.
3.  **Sliding Window (Contraction)**:
    *   While `rem` is 0 (meaning the current window contains all characters of `t`):
        *   Dequeue the leftmost index `i` from `validIndex`.
        *   Calculate the current window length: `j - i + 1`.
        *   If this length is smaller than `minLen`, update `minLen` and `startIndex` to `i`.
        *   Get the character `firstMatch` at index `i`.
        *   Increment its count in `og`.
        *   If the count of `firstMatch` in `og` becomes greater than 0, increment `rem` (meaning we now need this character again to form a valid window).
4.  **Result**:
    *   If `startIndex` is still -1, it means no valid window was found, return "".
    *   Otherwise, return the substring of `s` starting at `startIndex` with length `minLen`.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous sub-sequence (window) of a sequence by moving its start and end points.
*   **Frequency Maps (Hash Maps)**: Used to store and quickly access character counts for both the target string and the current window.
*   **Two Pointers**: The left and right pointers of the sliding window.
*   **Greedy Approach**: At each step, we try to make the locally optimal choice (shrinking the window as much as possible) to achieve the globally optimal solution (minimum window).

## Common Mistakes
*   **Incorrectly handling character counts**: Forgetting to decrement `rem` when a needed character is found, or incorrectly incrementing it when shrinking the window.
*   **Not handling duplicate characters in `t`**: The frequency map must accurately reflect the required counts of each character.
*   **Off-by-one errors in window length calculation**: `j - i + 1` is crucial for correct length.
*   **Not initializing `startIndex` correctly**: If no window is found, returning an incorrect substring instead of "".
*   **Inefficiently checking window validity**: Using a separate loop to check if the window is valid after expansion, instead of integrating it into the shrinking phase.

## Complexity Analysis
*   **Time**: O(N + M), where N is the length of string `s` and M is the length of string `t`. We iterate through `s` once with the right pointer, and each character in `s` is added to and removed from the `validIndex` queue at most once. The initial population of the frequency map for `t` takes O(M).
*   **Space**: O(K), where K is the number of unique characters in `t`. This is for storing the frequency map of characters in `t`. In the worst case, K can be up to 26 for English lowercase letters, or 128/256 for ASCII/extended ASCII.

## Commented Code
```java
class Solution {
    public String minWindow(String s, String t) {
        // If the string s is shorter than t, it's impossible to find a window, so return empty string.
        if (s.length() < t.length()) return "";
        
        // Queue to store the indices of characters from s that are part of t. This helps in shrinking the window from the left.
        Queue<Integer> validIndex = new LinkedList<>();
        // HashMap to store the frequency of characters required from string t.
        HashMap<Character, Integer> og = new HashMap<>();
        // Populate the frequency map for characters in t.
        for (char i : t.toCharArray()) og.put(i, og.getOrDefault(i, 0) + 1);

        // Variable to store the starting index of the minimum window found so far. Initialized to -1, indicating no window found yet.
        int index = -1;
        // Variable to store the minimum length of the valid window found so far. Initialized to maximum possible integer value.
        int min = Integer.MAX_VALUE;

        // Variable to keep track of how many characters from t are still needed to form a valid window. Initially, it's the total length of t.
        int rem = t.length();
                
        // Iterate through string s with the right pointer 'j' to expand the window.
        for(int j=0;j<s.length();j++) {
            // Get the current character at the right pointer.
            char curr = s.charAt(j);
            // Check if the current character is one of the characters required by t.
            if (og.containsKey(curr)) {
                // If it is, add its index to our queue of valid indices.
                validIndex.offer(j);
                // If the count of this character in our required map is still positive, it means we still need this character. So, decrement 'rem'.
                if (og.get(curr) > 0) rem--;
                // Decrement the count of this character in the required map, as we've now "used" one instance of it.
                og.put(curr, og.get(curr) - 1);
            }
            // This loop executes when 'rem' becomes 0, meaning the current window (from the leftmost valid index to 'j') contains all characters of t.
            while (rem == 0) {
                // Dequeue the leftmost index 'i' from the queue. This is the start of our current valid window.
                int i = validIndex.poll();
                // Calculate the length of the current window.
                // If this window is smaller than the minimum window found so far, update 'min' and 'index'.
                if (j - i + 1 < min) {
                    min = j - i + 1; // Update minimum length.
                    index = i;       // Update starting index of the minimum window.
                }
                // Get the character at the leftmost index 'i'.
                char firstMatch = s.charAt(i);
                // Increment the count of this character back in the required map. This is because we are about to remove it from the window.
                og.put(firstMatch, og.get(firstMatch) + 1);
                // If, after incrementing, the count becomes positive, it means we now need this character again to form a valid window. So, increment 'rem'.
                if(og.get(firstMatch) > 0) rem++;
            }
        }
        // After iterating through s, if 'index' is still -1, it means no valid window was found.
        // Otherwise, return the substring of s starting at 'index' with length 'min'.
        return index == -1 ? "" : s.substring(index, index + min);
    }
}
```

## Interview Tips
*   **Explain the Sliding Window**: Clearly articulate the two-pointer approach and how the window expands and contracts.
*   **Handle Edge Cases**: Discuss what happens if `s` is shorter than `t`, or if `t` is empty, or if no valid window exists.
*   **Frequency Map Logic**: Emphasize how the frequency map and the `rem` counter work together to track the fulfillment of `t`'s character requirements.
*   **Trace an Example**: Walk through a small example like `s = "ADOBECODEBANC", t = "ABC"` to demonstrate the algorithm's execution.
*   **Discuss Complexity**: Be prepared to explain the time and space complexity and justify it.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement the sliding window with two pointers.
- [ ] Use a frequency map for target characters.
- [ ] Correctly manage the count of remaining characters needed.
- [ ] Handle window expansion and contraction logic.
- [ ] Update minimum window length and start index accurately.
- [ ] Consider edge cases (empty strings, no solution).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Concatenation of All Words
*   Smallest Range Covering Elements from K Lists

## Tags
`Array` `Hash Map` `Two Pointers` `Sliding Window`
