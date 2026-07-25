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
This problem asks to find all starting indices of substrings in a larger string `s` that are anagrams of a smaller string `p`.
We solve this using a sliding window approach with frequency maps (arrays) to track character counts.

## Intuition
The core idea is that two strings are anagrams if they have the exact same character counts. We can efficiently check this by maintaining a "window" of characters in `s` that has the same length as `p`. As we slide this window one character at a time, we update the character counts. If the counts within the current window match the counts of `p`, we've found an anagram.

## Algorithm
1. **Pre-computation:** Create a frequency map (an array of size 26 for lowercase English letters) for the pattern string `p`. This map will store the count of each character in `p`.
2. **Initialization:**
   - Initialize an empty list `ans` to store the starting indices of anagrams.
   - Initialize a frequency map `found` for the current window in `s`, also of size 26.
   - Initialize two pointers, `i` (start of the window) and `j` (end of the window), both to 0.
   - Store the length of `p` in `total`.
3. **Sliding Window:** Iterate through the string `s` with the `j` pointer from 0 to `s.length() - 1`.
   - **Expand Window:** For each character `s.charAt(j)`, increment its count in the `found` map.
   - **Shrink Window (if necessary):** If the current window size (`j - i + 1`) exceeds the length of `p` (`total`), decrement the count of the character at the start of the window (`s.charAt(i)`) in the `found` map and increment `i`.
   - **Check for Anagram:** If the current window size (`j - i + 1`) is equal to the length of `p` (`total`), compare the `found` map with the `og` map (frequency map of `p`). If they are identical, it means the current window is an anagram of `p`, so add the starting index `i` to the `ans` list.
4. **Return Result:** After iterating through `s`, return the `ans` list.
5. **Helper Function `isEqual`:** This function takes two frequency maps (arrays) and returns `true` if all corresponding elements are equal, `false` otherwise.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-section of a data structure (like a string or array) by maintaining a window and moving its boundaries.
*   **Frequency Maps (Hash Maps/Arrays):** Using data structures to store and quickly retrieve counts of elements, crucial for anagram checks.
*   **Character Encoding:** Understanding how characters are represented (e.g., ASCII) and how to map them to array indices (e.g., `c - 'a'`).

## Common Mistakes
*   **Incorrect Window Size Management:** Not properly shrinking the window when it exceeds the target length, leading to incorrect character counts.
*   **Off-by-One Errors:** Miscalculating window boundaries (`j - i + 1`) or indices when accessing characters.
*   **Inefficient Anagram Check:** Re-calculating character counts from scratch for each window instead of incrementally updating them.
*   **Forgetting to Handle Edge Cases:** Not considering empty strings `s` or `p`, or `p` being longer than `s`.

## Complexity Analysis
- Time: O(N), where N is the length of string `s`. We iterate through `s` once with the `j` pointer. The `i` pointer also moves at most N times. The `isEqual` function takes O(26) which is constant time.
- Space: O(1). We use two arrays of fixed size 26 to store character frequencies, which is constant space regardless of the input string lengths.

## Commented Code
```java
class Solution {
    // Main function to find all anagrams of p in s
    public List<Integer> findAnagrams(String s, String p) {
        // Initialize an array 'og' to store the frequency of characters in pattern 'p'.
        // Size 26 for lowercase English letters 'a' through 'z'.
        int[] og = new int[26];
        // Iterate through each character in 'p' to populate its frequency map.
        for(char c : p.toCharArray()) {
            // Increment the count for the character c by subtracting 'a' to get its index (0-25).
            og[c-'a']++;
        }
        
        // Initialize 'i' as the start pointer of the sliding window.
        int i=0;
        // Initialize an array 'found' to store the frequency of characters in the current window of 's'.
        int[] found = new int[26];
        // Initialize an ArrayList 'ans' to store the starting indices of anagrams found.
        List<Integer> ans = new ArrayList<>();
        // Store the length of the pattern 'p' for easy comparison.
        int total = p.length();
        
        // Iterate through the string 's' with 'j' as the end pointer of the sliding window.
        for(int j=0;j<s.length();j++){
          // Add the current character s.charAt(j) to the window by incrementing its count in 'found'.
          found[s.charAt(j)-'a']++;
          // If the current window size (j - i + 1) exceeds the length of 'p' (total)...
          if(j-i+1 > total) {
              // ...remove the character at the start of the window (s.charAt(i)) by decrementing its count in 'found'.
              found[s.charAt(i++)-'a']--;
              // Move the start pointer 'i' one step forward.
          }
          // If the current window size is exactly equal to the length of 'p' AND the character frequencies match...
          if(j-i+1 == total && isEqual(og,found)) {
              // ...then we have found an anagram. Add the starting index 'i' of this window to the 'ans' list.
              ans.add(i);
          }
        }
        
        // Return the list of starting indices where anagrams were found.
        return ans;
    }
    
    // Helper function to compare two frequency maps (arrays).
    public boolean isEqual(int[] a, int[] b){
        // Iterate through all possible character indices (0 to 25).
        for(int k=0;k<26;k++) {
            // If any character count in array 'a' does not match the corresponding count in array 'b'...
            if(a[k] != b[k]) {
                // ...return false, as the maps are not equal.
                return false;
            }
        }
        // If all character counts match, return true, indicating the maps are equal.
        return true;
    }
}
```

## Interview Tips
*   **Explain the Sliding Window:** Clearly articulate why a sliding window is the optimal approach and how it avoids redundant computations.
*   **Frequency Map Logic:** Be prepared to explain how you're using the arrays to track character counts and why this is efficient for anagram detection.
*   **Edge Case Handling:** Discuss how you would handle cases like empty strings, or `p` being longer than `s`.
*   **Code Walkthrough:** Walk through a small example manually to demonstrate how your algorithm processes the input and updates the window and frequency maps.

## Revision Checklist
- [ ] Understand the problem: Find anagrams of `p` in `s`.
- [ ] Identify the core technique: Sliding window.
- [ ] Implement frequency maps (arrays) for character counts.
- [ ] Correctly manage window expansion and contraction.
- [ ] Implement an efficient comparison of frequency maps.
- [ ] Handle edge cases (empty strings, `p` longer than `s`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Minimum Window Substring
*   Find All Duplicates in an Array

## Tags
`Array` `Hash Map` `Sliding Window`
