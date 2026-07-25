# Find All Anagrams In A String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] og = new int[26];
        for(char c : p.toCharArray()) og[c-'a']++;
        
        int i=0;
        int[] found = new int[26];
        List<Integer> ans = new ArrayList<>();
        int total = p.length();
        
        for(int j=0;j<s.length();j++){
          found[s.charAt(j)-'a']++;
          if(j-i+1 > total) found[s.charAt(i++)-'a']--;
          if(j-i+1 == total && isEqual(og,found)) ans.add(i);
        }
        
        return ans;
    }
    public boolean isEqual(int[] a, int[] b){
        for(int i=0;i<26;i++) if(a[i] != b[i]) return false;
        return true;
    }
}
```

---

---
## Quick Revision
Find all starting indices of substrings in `s` that are anagrams of `p`.
This is solved using a sliding window approach with frequency maps.

## Intuition
The core idea is that an anagram of `p` will have the exact same character counts as `p`. We can maintain a "window" of characters in `s` that has the same length as `p`. As we slide this window one character at a time, we update the character counts within the window. If the character counts in the current window match the character counts of `p`, then we've found an anagram.

## Algorithm
1. **Pre-computation:** Create a frequency map (an array of size 26 for lowercase English letters) for the characters in string `p`. Let's call this `pFreq`.
2. **Initialization:**
   - Initialize an empty list `result` to store the starting indices of anagrams.
   - Initialize a frequency map `windowFreq` for the current window in `s`, also of size 26.
   - Initialize two pointers, `left` (start of the window) and `right` (end of the window), both to 0.
   - Store the length of `p` in `pLen`.
3. **Sliding Window:** Iterate through string `s` with the `right` pointer from 0 to `s.length() - 1`.
   - **Expand Window:** Add the character `s.charAt(right)` to the `windowFreq` by incrementing its count.
   - **Shrink Window (if necessary):** If the current window size (`right - left + 1`) exceeds `pLen`, remove the character `s.charAt(left)` from `windowFreq` by decrementing its count, and then increment `left`.
   - **Check for Anagram:** If the current window size (`right - left + 1`) is equal to `pLen`, compare `windowFreq` with `pFreq`. If they are identical, add `left` to the `result` list.
4. **Return Result:** After iterating through `s`, return the `result` list.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-sequence of data by maintaining a window that moves across the data.
*   **Frequency Maps (Hash Maps/Arrays):** Used to store and compare character counts for anagram detection.
*   **Character Encoding:** Understanding how characters can be mapped to array indices (e.g., `char - 'a'`).

## Common Mistakes
*   **Incorrect Window Size Management:** Not properly shrinking the window when it exceeds the target length, leading to incorrect comparisons.
*   **Off-by-One Errors:** Miscalculating window size or indices, especially when adding or removing characters.
*   **Inefficient Comparison:** Repeatedly rebuilding frequency maps instead of incrementally updating them.
*   **Forgetting to Handle Edge Cases:** Empty strings `s` or `p`, or `p` being longer than `s`.

## Complexity Analysis
- Time: O(N), where N is the length of string `s`. We iterate through `s` once with the `right` pointer. The `left` pointer also moves at most N times. The comparison of frequency maps takes constant time (O(26) which is O(1)).
- Space: O(1). We use two frequency arrays of fixed size 26, which is constant space.

## Commented Code
```java
class Solution {
    // Main method to find all anagrams of p in s
    public List<Integer> findAnagrams(String s, String p) {
        // Initialize an array to store the frequency of characters in pattern p.
        // Size 26 for lowercase English letters 'a' through 'z'.
        int[] pFreq = new int[26];
        // Iterate through each character in string p.
        for(char c : p.toCharArray()) {
            // Increment the count for the corresponding character in pFreq.
            // 'c - 'a'' converts the character to an index (0 for 'a', 1 for 'b', etc.).
            pFreq[c - 'a']++;
        }

        // Initialize the left pointer of the sliding window.
        int left = 0;
        // Initialize an array to store the frequency of characters in the current window of s.
        int[] windowFreq = new int[26];
        // Initialize an ArrayList to store the starting indices of anagrams found.
        List<Integer> result = new ArrayList<>();
        // Store the length of the pattern string p.
        int pLen = p.length();

        // Iterate through string s using the right pointer of the sliding window.
        for(int right = 0; right < s.length(); right++) {
            // Add the current character at the right pointer to the window frequency.
            windowFreq[s.charAt(right) - 'a']++;

            // Check if the current window size exceeds the length of p.
            // The window size is (right - left + 1).
            if (right - left + 1 > pLen) {
                // If the window is too large, remove the character at the left pointer from the window frequency.
                windowFreq[s.charAt(left) - 'a']--;
                // Move the left pointer one step to the right to shrink the window.
                left++;
            }

            // Check if the current window size is exactly equal to the length of p.
            // And if the character frequencies in the window match the frequencies in p.
            if (right - left + 1 == pLen && isEqual(pFreq, windowFreq)) {
                // If both conditions are met, it means we found an anagram.
                // Add the starting index of the window (which is 'left') to the result list.
                result.add(left);
            }
        }

        // Return the list of starting indices where anagrams were found.
        return result;
    }

    // Helper method to compare two frequency arrays.
    public boolean isEqual(int[] arr1, int[] arr2) {
        // Iterate through all possible character indices (0 to 25).
        for (int i = 0; i < 26; i++) {
            // If any corresponding element in the arrays is not equal, they are not the same.
            if (arr1[i] != arr2[i]) {
                // Return false immediately if a mismatch is found.
                return false;
            }
        }
        // If the loop completes without finding any mismatches, the arrays are equal.
        return true;
    }
}
```

## Interview Tips
*   **Explain the Sliding Window:** Clearly articulate why a sliding window is suitable for this problem and how it helps optimize the solution.
*   **Discuss Frequency Maps:** Explain the use of frequency maps (arrays in this case) for efficient character counting and comparison.
*   **Handle Edge Cases:** Be prepared to discuss what happens if `s` or `p` is empty, or if `p` is longer than `s`.
*   **Walk Through an Example:** Use a small example string `s` and pattern `p` to trace the algorithm's execution step-by-step.

## Revision Checklist
- [ ] Understand the problem: finding anagrams of `p` within `s`.
- [ ] Recognize the sliding window pattern.
- [ ] Implement frequency counting using arrays.
- [ ] Correctly manage window expansion and contraction.
- [ ] Accurately compare frequency maps.
- [ ] Handle edge cases like empty strings.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Minimum Window Substring
*   Substring with Concatenation of All Words

## Tags
`Array` `Hash Map` `Sliding Window`
