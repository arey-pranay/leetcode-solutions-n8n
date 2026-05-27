# Substring With Concatenation Of All Words

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(N * M)  
**Space:** O(K * M)

---

## Solution (java)

```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        
        int wordCount = words.length;
        int unit = words[0].length();
        int size  = unit*wordCount;
        int n = s.length();
        
        HashMap<String,Integer> hm = new HashMap<>();
        for(int i = 0; i<wordCount; i++) hm.put(words[i], hm.getOrDefault(words[i],0)+1);
        
        for(int x=0;x<unit;x++){
            HashMap<String,Integer> hm2 = new HashMap<>();
            int i = x;
            int count = 0;
            for(int j=x;j<= n-unit; j+= unit){
                String currWord = s.substring(j,j+unit);
                if(hm.containsKey(currWord)){
                    hm2.put(currWord,hm2.getOrDefault(currWord,0)+1);
                    count++;
                    
                    while(hm2.get(currWord) > hm.get(currWord)){
                        String startWord = s.substring(i,i+unit);
                        hm2.put(startWord,hm2.get(startWord)-1);
                        i += unit;
                        count--;
                    }
                    
                    if(count == wordCount) ans.add(i);
                
                }else{
                    count = 0;
                    i = j + unit;
                    hm2.clear();
                }
            }
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given a string `s` and an array of words `words` of the same length, find all starting indices of substrings in `s` that are a concatenation of each word in `words` exactly once and without any intervening characters.
This problem is solved using a sliding window approach combined with hash maps to efficiently track word frequencies.

## Intuition
The core idea is that if a substring is a concatenation of all words, it must have a specific length (total length of all words) and contain each word from the `words` array with the correct frequency. We can iterate through all possible starting positions of such a substring. However, a naive check for every possible substring would be too slow.

The "aha moment" comes from realizing that we don't need to check every single character as a potential start. Since all words have the same length, any valid concatenation must start at an index `i` such that `i % unit == x % unit`, where `unit` is the length of each word and `x` is an offset. This allows us to break down the problem into `unit` independent sliding window problems. For each offset `x`, we slide a window of size `size` (total length of all words) across the string, maintaining a count of words within the current window and comparing it to the required counts from the `words` array.

## Algorithm
1.  **Initialization**:
    *   Create an empty list `ans` to store the starting indices of valid substrings.
    *   Get the number of words `wordCount` and the length of each word `unit`.
    *   Calculate the total length of the concatenated substring `size = unit * wordCount`.
    *   Get the length of the input string `n = s.length()`.
    *   If `s` is empty, `words` is empty, or `size` is greater than `n`, return `ans`.
    *   Create a frequency map `hm` for the words in the `words` array. Count the occurrences of each word.

2.  **Iterate through offsets**:
    *   Loop `x` from `0` to `unit - 1`. This `x` represents the starting offset for our sliding windows. This is crucial because a valid concatenation must start at an index `i` such that `i % unit` is constant.

3.  **Sliding Window for each offset**:
    *   For each `x`, initialize a new frequency map `hm2` to track words in the current window.
    *   Initialize `i` (the start of the current window) to `x`.
    *   Initialize `count` (number of valid words found in the current window) to `0`.
    *   Loop `j` from `x` up to `n - unit`, incrementing `j` by `unit` in each step. `j` represents the potential end of a word within the current window.
        *   Extract the current word `currWord = s.substring(j, j + unit)`.
        *   **If `currWord` is in `hm` (i.e., it's one of the target words)**:
            *   Increment its count in `hm2`.
            *   Increment `count`.
            *   **Shrink the window if necessary**: While the count of `currWord` in `hm2` exceeds its count in `hm`:
                *   Get the word at the start of the window: `startWord = s.substring(i, i + unit)`.
                *   Decrement its count in `hm2`.
                *   Move the window start forward: `i += unit`.
                *   Decrement `count`.
            *   **Check for a valid concatenation**: If `count` equals `wordCount`, it means we have found a substring of length `size` starting at `i` that contains all required words with correct frequencies. Add `i` to `ans`.
        *   **If `currWord` is NOT in `hm`**:
            *   This word breaks any potential concatenation. Reset the current window.
            *   Reset `count` to `0`.
            *   Move the window start `i` to `j + unit` (the position after the current invalid word).
            *   Clear `hm2` as the window is reset.

4.  **Return Result**: After iterating through all offsets and sliding windows, return the `ans` list.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous subarray or substring by maintaining a window and moving its start and end points.
*   **Hash Maps (Frequency Counting)**: Using hash maps to store and quickly retrieve the frequency of elements (words in this case).
*   **Handling Multiple Sliding Windows**: Breaking down a problem into smaller, independent sliding window problems based on an offset.
*   **String Manipulation**: Efficiently extracting substrings.

## Common Mistakes
*   **Incorrect Window Shrinking Logic**: Not correctly shrinking the window when a word's frequency exceeds the required count, leading to false positives or missed valid substrings.
*   **Resetting Window Incorrectly**: Failing to properly reset the window and its associated counts when an invalid word is encountered.
*   **Off-by-One Errors**: Mistakes in substring indexing or loop bounds.
*   **Not Handling Edge Cases**: Forgetting to check for empty input strings, empty word arrays, or cases where the total word length exceeds the string length.
*   **Inefficient String Substring Operations**: Repeatedly creating substrings without optimization can impact performance, though Java's `substring` is generally efficient.

## Complexity Analysis
*   **Time**: O(N * M), where N is the length of the string `s` and M is the length of each word.
    *   The outer loop runs `M` times (for each offset `x`).
    *   The inner loop (sliding window) iterates through the string `s` approximately `N/M` times.
    *   Inside the inner loop, substring operations and hash map operations (get, put, containsKey) take O(M) time in the worst case for string hashing and O(1) on average for hash map operations.
    *   Therefore, the total time complexity is roughly `M * (N/M * M) = N * M`.
*   **Space**: O(K * M), where K is the number of unique words in the `words` array and M is the length of each word.
    *   This space is used for the hash maps (`hm` and `hm2`) to store word frequencies. In the worst case, `hm2` might store up to `wordCount` words. The keys are strings of length `M`.

## Commented Code
```java
import java.util.ArrayList; // Import ArrayList for storing results
import java.util.HashMap; // Import HashMap for frequency counting
import java.util.List; // Import List interface

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>(); // Initialize the list to store starting indices of valid substrings

        int wordCount = words.length; // Get the total number of words we need to find
        if (wordCount == 0) return ans; // If there are no words to find, return an empty list
        int unit = words[0].length(); // Get the length of a single word (all words have the same length)
        int size = unit * wordCount; // Calculate the total length of the concatenated substring we are looking for
        int n = s.length(); // Get the length of the input string

        // Edge case: If the total length of words is greater than the string length, no solution is possible
        if (size > n) return ans;

        // Create a frequency map for the words in the input array 'words'
        // Key: word, Value: count of that word
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < wordCount; i++) {
            // For each word, increment its count in the map. If the word is not present, it's added with a count of 1.
            hm.put(words[i], hm.getOrDefault(words[i], 0) + 1);
        }

        // Iterate through all possible starting offsets for the concatenated substring.
        // Since all words have length 'unit', a valid concatenation must start at an index 'i'
        // such that 'i % unit' is constant for all words within that concatenation.
        // We only need to check 'unit' different starting offsets (0 to unit-1).
        for (int x = 0; x < unit; x++) {
            // For each offset 'x', we use a sliding window approach.
            // 'hm2' will store the frequency of words encountered in the current sliding window.
            HashMap<String, Integer> hm2 = new HashMap<>();
            int i = x; // 'i' is the start of the current sliding window. It begins at the current offset 'x'.
            int count = 0; // 'count' tracks how many valid words (from 'words') are currently in our window.

            // 'j' iterates through the string 's' in steps of 'unit', marking the potential end of a word.
            // The loop condition ensures that we can always extract a full word of length 'unit'.
            for (int j = x; j <= n - unit; j += unit) {
                // Extract the current word from the string 's' starting at index 'j' with length 'unit'.
                String currWord = s.substring(j, j + unit);

                // Check if the extracted word is one of the target words we are looking for.
                if (hm.containsKey(currWord)) {
                    // If it's a target word, add it to our current window's frequency map.
                    hm2.put(currWord, hm2.getOrDefault(currWord, 0) + 1);
                    count++; // Increment the count of valid words found in the window.

                    // If the count of 'currWord' in our window ('hm2') exceeds its required count in 'hm',
                    // we need to shrink the window from the left ('i') until the counts are balanced.
                    while (hm2.get(currWord) > hm.get(currWord)) {
                        // Get the word at the start of the current window.
                        String startWord = s.substring(i, i + unit);
                        // Decrement its count in our window's frequency map.
                        hm2.put(startWord, hm2.get(startWord) - 1);
                        // Move the start of the window forward by 'unit'.
                        i += unit;
                        // Decrement the count of valid words in the window.
                        count--;
                    }

                    // If the number of valid words in our window ('count') equals the total number of words required ('wordCount'),
                    // it means we have found a valid concatenation of all words.
                    if (count == wordCount) {
                        // Add the starting index 'i' of this valid concatenation to our result list.
                        ans.add(i);
                    }
                } else {
                    // If the extracted word is NOT a target word, it breaks any potential concatenation.
                    // We must reset the current window and start looking for a new potential concatenation.
                    count = 0; // Reset the count of valid words.
                    i = j + unit; // Move the start of the window to the position after the current invalid word.
                    hm2.clear(); // Clear the frequency map for the window as it's being reset.
                }
            }
        }
        // After checking all offsets and sliding windows, return the list of all found starting indices.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Offset Logic**: Clearly articulate why iterating `unit` times with an offset `x` is necessary and sufficient. This is the key optimization.
*   **Walk Through the Sliding Window**: Describe how the window expands and shrinks. Emphasize the conditions for shrinking (word count exceeded) and resetting (invalid word found).
*   **Discuss Hash Map Usage**: Explain how hash maps are used for efficient frequency tracking and comparison. Mention the time complexity implications of hash map operations.
*   **Consider Edge Cases**: Be prepared to discuss what happens with empty inputs, zero words, or when the total word length exceeds the string length.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the need for frequency tracking (hash maps).
- [ ] Grasp the sliding window concept.
- [ ] Understand the crucial offset iteration (`x` from `0` to `unit-1`).
- [ ] Implement the window expansion and contraction logic correctly.
- [ ] Handle the case where an invalid word is encountered.
- [ ] Verify edge cases (empty inputs, length mismatches).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Find All Anagrams in a String
*   Permutation in String
*   Substring with Exactly K Distinct Characters

## Tags
`Array` `Hash Map` `Sliding Window` `String`
