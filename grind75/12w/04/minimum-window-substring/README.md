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
        HashMap<Character,Integer> freq = new HashMap<>();
        for (char i : t.toCharArray()) freq.put(i, freq.getOrDefault(i, 0) + 1);
        int index=-1;
        int minLength = Integer.MAX_VALUE;
        int count = t.length();
        int i =0;
        for(int j=0;j<s.length();j++){
            char c = s.charAt(j);
            if(freq.containsKey(c)){
                if(freq.get(c) > 0) count--;
                freq.put(c,freq.get(c)-1);
            } 
            
                // XIADOBECODEBANC
                //    i      j
                //    2      7
            while(count==0){
// we will increment count only when we realize that we lost a useful character, so now let's increment j again to find that character
              if(j+1 - i < minLength){
                minLength = j+1 -i;
                index = i;
              }
              char firstMatch = s.charAt(i);
              if(freq.containsKey(firstMatch)){
                int newCount = freq.get(firstMatch)+1;
                freq.put(firstMatch, newCount);
                if(newCount > 0) count++;
              }
              i++;
            }
        }
        return index == -1 ? "" : s.substring(index,index+minLength);
    }
}
// ADOBECODEBA
// ADOBECODEBANC
// ABC
```

---

---
## Quick Revision
Find the smallest substring in string `s` that contains all characters of string `t`, including duplicates.
This is solved using a sliding window approach with frequency maps.

## Intuition
The core idea is to expand a window from left to right until it contains all characters of `t`. Once it does, we try to shrink the window from the left as much as possible while still maintaining the condition of containing all characters of `t`. This shrinking process helps us find the *minimum* window. We use frequency maps to efficiently track the required characters and the characters currently within our window.

## Algorithm
1.  **Initialize Frequency Maps:**
    *   Create a frequency map (`targetFreq`) for characters in string `t`.
    *   Create a frequency map (`windowFreq`) for characters in the current sliding window of `s`.
2.  **Initialize Pointers and Counters:**
    *   `left`: Left pointer of the sliding window, initialized to 0.
    *   `right`: Right pointer of the sliding window, initialized to 0.
    *   `required`: The number of unique characters in `t` that we still need to find in the window. Initialize it to the size of `targetFreq`.
    *   `formed`: The number of unique characters in `t` that are currently present in the window with at least their required frequency. Initialize it to 0.
    *   `minLength`: Stores the length of the smallest valid window found so far. Initialize to infinity.
    *   `minStart`: Stores the starting index of the smallest valid window found so far. Initialize to -1.
3.  **Expand Window:**
    *   Iterate `right` from 0 to `s.length() - 1`.
    *   Let `currentChar = s.charAt(right)`.
    *   Add `currentChar` to `windowFreq`.
    *   If `currentChar` is in `targetFreq` and `windowFreq.get(currentChar)` is now equal to `targetFreq.get(currentChar)`, increment `formed`.
4.  **Contract Window:**
    *   While `left <= right` and `formed == required`:
        *   This means the current window `s[left...right]` is a valid candidate.
        *   Calculate the current window length: `currentLength = right - left + 1`.
        *   If `currentLength < minLength`:
            *   Update `minLength = currentLength`.
            *   Update `minStart = left`.
        *   Let `leftChar = s.charAt(left)`.
        *   Remove `leftChar` from `windowFreq`.
        *   If `leftChar` is in `targetFreq` and `windowFreq.get(leftChar)` is now less than `targetFreq.get(leftChar)`, decrement `formed`.
        *   Increment `left`.
5.  **Return Result:**
    *   If `minStart` is still -1, it means no valid window was found, return "".
    *   Otherwise, return `s.substring(minStart, minStart + minLength)`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-sequence (window) of a sequence by moving the window's start and end points.
*   **Frequency Maps (Hash Maps):** Used for O(1) average time lookups, insertions, and deletions to track character counts.
*   **Two Pointers:** `left` and `right` pointers define the boundaries of the sliding window.
*   **Greedy Approach:** At each step, we try to make the locally optimal choice (shrinking the window) to achieve the globally optimal solution (minimum window).

## Common Mistakes
*   **Incorrectly handling character counts:** Forgetting to decrement `formed` when a character's count in the window drops below its required count in `t`.
*   **Off-by-one errors in window length calculation:** Using `right - left` instead of `right - left + 1`.
*   **Not initializing `minLength` and `minStart` correctly:** Leading to incorrect results when no valid window is found or when the first valid window is the smallest.
*   **Inefficiently updating frequency maps:** Not using `getOrDefault` or similar methods, leading to more complex code.
*   **Not considering duplicate characters in `t`:** The frequency maps must correctly account for multiple occurrences of the same character.

## Complexity Analysis
- Time: O(N + M) - where N is the length of string `s` and M is the length of string `t`. We iterate through `s` with the `right` pointer once (O(N)). The `left` pointer also moves at most N times. Building the frequency map for `t` takes O(M).
- Space: O(K) - where K is the number of unique characters in `t` (or the alphabet size if we consider a fixed alphabet). This is for storing the frequency maps. In the worst case, K can be up to 26 for English lowercase letters or 128/256 for ASCII.

## Commented Code
```java
class Solution {
    public String minWindow(String s, String t) {
        // Create a frequency map to store the required characters and their counts from string t.
        HashMap<Character, Integer> freq = new HashMap<>();
        // Populate the frequency map for string t.
        for (char c : t.toCharArray()) {
            // For each character in t, increment its count in the map. If it's not present, initialize to 1.
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Initialize variables to track the minimum window found.
        int minLength = Integer.MAX_VALUE; // Stores the length of the smallest valid window.
        int minStartIndex = -1; // Stores the starting index of the smallest valid window.

        // Initialize pointers for the sliding window.
        int left = 0; // The left boundary of the window.

        // `requiredCharsCount` is the number of unique characters in `t` that we need to match.
        // This is equivalent to the number of entries in our `freq` map.
        int requiredCharsCount = freq.size();
        // `formedCharsCount` is the number of unique characters in `t` that are currently satisfied within our window.
        int formedCharsCount = 0;

        // Create a frequency map to store the characters within the current sliding window.
        HashMap<Character, Integer> windowFreq = new HashMap<>();

        // Iterate through string s using the right pointer to expand the window.
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right); // Get the character at the current right pointer.

            // Add the current character to the window's frequency map.
            windowFreq.put(currentChar, windowFreq.getOrDefault(currentChar, 0) + 1);

            // Check if the current character is one of the required characters from t.
            if (freq.containsKey(currentChar)) {
                // If the count of this character in the window now matches its required count in t,
                // it means we have satisfied one more unique character requirement.
                if (windowFreq.get(currentChar).intValue() == freq.get(currentChar).intValue()) {
                    formedCharsCount++;
                }
            }

            // Try to contract the window from the left if all required characters are formed.
            // `formedCharsCount == requiredCharsCount` means the current window `s[left...right]` is a valid candidate.
            while (left <= right && formedCharsCount == requiredCharsCount) {
                // Calculate the current window's length.
                int currentWindowLength = right - left + 1;

                // If this window is smaller than the minimum found so far, update our minimums.
                if (currentWindowLength < minLength) {
                    minLength = currentWindowLength;
                    minStartIndex = left;
                }

                // Now, try to shrink the window by moving the left pointer.
                char leftChar = s.charAt(left); // Get the character at the left pointer.

                // Remove the left character from the window's frequency map.
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                // Check if removing this character breaks the condition of having all required characters.
                if (freq.containsKey(leftChar)) {
                    // If the count of this character in the window drops below its required count in t,
                    // it means we no longer satisfy this character's requirement.
                    if (windowFreq.get(leftChar).intValue() < freq.get(leftChar).intValue()) {
                        formedCharsCount--; // Decrement the count of formed characters.
                    }
                }

                // Move the left pointer to the right to shrink the window.
                left++;
            }
        }

        // After iterating through the entire string s, if `minStartIndex` is still -1,
        // it means no valid window was found.
        // Otherwise, return the substring corresponding to the smallest valid window.
        return minStartIndex == -1 ? "" : s.substring(minStartIndex, minStartIndex + minLength);
    }
}
```

## Interview Tips
1.  **Explain the Sliding Window:** Clearly articulate the two-pointer approach and how the window expands and contracts.
2.  **Frequency Map Logic:** Emphasize how frequency maps are used to track character requirements and window contents, and how `formedCharsCount` and `requiredCharsCount` work together.
3.  **Edge Cases:** Discuss what happens if `s` or `t` is empty, if `t` is longer than `s`, or if no valid window exists.
4.  **Optimization:** Mention why this approach is efficient and contrast it with a brute-force O(N^3) or O(N^2) solution.
5.  **Clarity of Variables:** Use descriptive variable names (e.g., `requiredCharsCount`, `formedCharsCount`, `minLength`, `minStartIndex`) to make your code and explanation easier to follow.

## Revision Checklist
- [ ] Understand the problem: Find the smallest substring containing all characters of `t`.
- [ ] Identify the core technique: Sliding Window.
- [ ] Implement frequency maps for `t` and the current window.
- [ ] Use two pointers (`left`, `right`) to define the window.
- [ ] Track `requiredCharsCount` and `formedCharsCount` correctly.
- [ ] Handle window expansion (increment `right`).
- [ ] Handle window contraction (increment `left` when valid).
- [ ] Update `minLength` and `minStart` when a smaller valid window is found.
- [ ] Handle the case where no valid window exists.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Concatenation of All Words
*   Smallest Range Covering Elements from K Lists

## Tags
`Array` `Hash Map` `Two Pointers` `Sliding Window`
