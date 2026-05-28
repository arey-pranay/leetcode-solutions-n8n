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
The core idea is to expand a window from left to right until it *potentially* contains all characters of `t`. Once it does, we try to shrink the window from the left as much as possible while still maintaining the condition of containing all characters of `t`. This shrinking process helps us find the *minimum* valid window. We need to efficiently track which characters from `t` are present in our current window and how many more we still need.

## Algorithm
1.  **Initialization**:
    *   Create a frequency map (`og`) for characters in `t`.
    *   Initialize `rem` (remaining characters needed from `t`) to `t.length()`.
    *   Initialize `minLen` to `Integer.MAX_VALUE` and `startIndex` to `-1` to store the best window found so far.
    *   Use a queue (`validIndex`) to store the indices of characters from `s` that are also present in `t`. This helps in efficiently shrinking the window.
2.  **Sliding Window**: Iterate through `s` with a right pointer `j`.
    *   If the current character `s.charAt(j)` is in `t`:
        *   Add its index `j` to `validIndex`.
        *   Decrement its count in `og`.
        *   If the count in `og` becomes non-negative (meaning we just satisfied a required character), decrement `rem`.
3.  **Window Shrinking**: While `rem` is 0 (meaning the current window contains all characters of `t`):
    *   Get the leftmost index `i` from `validIndex`.
    *   Calculate the current window length: `j - i + 1`.
    *   If this length is smaller than `minLen`, update `minLen` and `startIndex` to `i`.
    *   Remove the character `s.charAt(i)` from the window:
        *   Increment its count in `og`.
        *   If its count in `og` becomes positive (meaning we just lost a required character), increment `rem`.
4.  **Result**: After iterating through `s`, if `startIndex` is still `-1`, no valid window was found, return "". Otherwise, return the substring of `s` from `startIndex` with length `minLen`.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous subsegment of a sequence by maintaining a window and moving its boundaries.
*   **Frequency Maps (Hash Maps)**: Used to store and quickly access character counts, essential for tracking required characters and their presence in the window.
*   **Two Pointers**: The left and right pointers of the sliding window.
*   **Greedy Approach**: At each step, we try to make the locally optimal choice (shrinking the window as much as possible) to achieve the globally optimal solution (minimum window).

## Common Mistakes
*   **Incorrectly handling character counts**: Forgetting to decrement `rem` when a needed character is found, or incorrectly incrementing it when a character is removed.
*   **Not properly shrinking the window**: Failing to remove characters from the left and update `rem` when shrinking, leading to incorrect minimum lengths.
*   **Edge cases**: Not handling cases where `s` is shorter than `t`, or when no valid window exists.
*   **Off-by-one errors**: In calculating window length (`j - i + 1`) or substring extraction.

## Complexity Analysis
*   **Time**: O(N + M), where N is the length of `s` and M is the length of `t`. We iterate through `s` once with the right pointer. The left pointer also traverses `s` at most once. Building the frequency map for `t` takes O(M).
*   **Space**: O(K), where K is the number of unique characters in `t`. This is for storing the frequency map. In the worst case, K can be up to 26 for English alphabets or 128/256 for ASCII.

## Commented Code
```java
class Solution {
    public String minWindow(String s, String t) {
        // If the string s is shorter than t, it's impossible to find a window, so return empty string.
        if (s.length() < t.length()) return "";
        
        // Queue to store indices of characters from s that are relevant to t.
        // This helps in efficiently identifying the start of the window when shrinking.
        Queue<Integer> validIndex = new LinkedList<>();
        // HashMap to store the required frequency of characters in t.
        HashMap<Character, Integer> og = new HashMap<>();
        // Populate the frequency map for characters in t.
        for (char i : t.toCharArray()) og.put(i, og.getOrDefault(i, 0) + 1);

        // Variable to store the starting index of the minimum window found so far. Initialized to -1.
        int index = -1;
        // Variable to store the minimum length of the valid window found so far. Initialized to maximum possible integer value.
        int min = Integer.MAX_VALUE;

        // Variable to track how many characters from t we still need to find in the current window.
        // Initially, it's the total length of t.
        int rem = t.length();
                
        // Iterate through the string s with the right pointer 'j'.
        for(int j=0;j<s.length();j++) {
            // Get the current character at the right pointer.
            char curr = s.charAt(j);
            // Check if the current character is one of the characters we need from t.
            if (og.containsKey(curr)) {
                // If it is, add its index to our queue of valid indices.
                validIndex.offer(j);
                // If the count of this character in our required map is greater than 0,
                // it means we just found a character that was still needed.
                if (og.get(curr) > 0) rem--;
                // Decrement the count of this character in our required map.
                // This signifies that we have one less of this character to find.
                og.put(curr, og.get(curr) - 1);
            }
            // This loop executes only when 'rem' is 0, meaning the current window (from the leftmost valid index to 'j')
            // contains all characters of t. We now try to shrink this window from the left.
            while (rem == 0) {
                // Get the leftmost index 'i' from our queue of valid indices. This is the start of our current window.
                int i = validIndex.poll();
                // Calculate the length of the current window.
                // If this window is smaller than the minimum window found so far, update our minimum length and starting index.
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    index = i;
                }
                // Get the character at the leftmost index 'i' of the window.
                char firstMatch = s.charAt(i);
                // Increment the count of this character back in our required map.
                // This is because we are removing it from the window.
                og.put(firstMatch, og.get(firstMatch) + 1);
                // If the count of this character in our required map becomes positive again,
                // it means we just lost a character that was essential for satisfying t.
                // So, we increment 'rem' to indicate we need to find it again.
                if(og.get(firstMatch) > 0) rem++;
            }
        }
        // After iterating through the entire string s, if 'index' is still -1, it means no valid window was found.
        // Otherwise, return the substring of s starting at 'index' with length 'min'.
        return index == -1 ? "" : s.substring(index, index + min);
    }
}
```

## Interview Tips
*   **Explain the Sliding Window**: Clearly articulate the two-pointer approach and how the window expands and contracts.
*   **Frequency Map Logic**: Emphasize how the frequency map (`og`) and the `rem` counter work together to track the satisfaction of `t`'s character requirements.
*   **Queue's Role**: Explain why a queue is used to store indices of relevant characters, enabling efficient removal from the left during shrinking.
*   **Edge Cases**: Be prepared to discuss what happens if `s` is shorter than `t`, or if `t` is empty, or if no valid window exists.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement the sliding window logic with two pointers.
- [ ] Correctly use frequency maps to track character counts.
- [ ] Implement the `rem` counter logic for tracking needed characters.
- [ ] Handle window shrinking efficiently using a queue of indices.
- [ ] Correctly update minimum window length and start index.
- [ ] Handle edge cases (e.g., `s` shorter than `t`, no valid window).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Concatenation of All Words

## Tags
`Array` `Hash Map` `Two Pointers` `Sliding Window`
