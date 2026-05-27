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
This problem is solved using a sliding window approach with hash maps to efficiently track word frequencies.

## Intuition
The core idea is to check every possible starting position for a valid concatenation. Since all words have the same length, a valid concatenation will always be a multiple of that length. We can iterate through all possible starting offsets (from 0 up to `word_length - 1`) and then use a sliding window of size `total_words * word_length` for each offset. Inside the sliding window, we maintain a count of words encountered and compare it against the required counts from the input `words` array.

## Algorithm
1.  **Initialization**:
    *   Create an empty list `ans` to store the starting indices of valid substrings.
    *   Get the number of words `wordCount` and the length of each word `unit`.
    *   Calculate the total length of the concatenated substring `size = unit * wordCount`.
    *   Get the length of the main string `n = s.length()`.
    *   Create a frequency map `hm` for the words in the input `words` array.

2.  **Outer Loop (Offset Iteration)**:
    *   Iterate `x` from `0` to `unit - 1`. This loop handles different starting alignments of the words within `s`. For example, if `unit` is 3, we check starting positions 0, 3, 6...; then 1, 4, 7...; then 2, 5, 8....

3.  **Inner Loop (Sliding Window)**:
    *   For each offset `x`, initialize a new frequency map `hm2` for the current window and a `count` of matched words to 0.
    *   Initialize the left pointer of the window `i` to `x`.
    *   Iterate `j` from `x` up to `n - unit` with a step of `unit`. This `j` represents the start of the current word being considered for the window.
    *   Extract the current word `currWord = s.substring(j, j + unit)`.

4.  **Window Management**:
    *   **If `currWord` is in `hm` (i.e., it's one of the target words)**:
        *   Increment its count in `hm2`.
        *   Increment `count`.
        *   **Shrink Window if Necessary**: While the count of `currWord` in `hm2` exceeds its count in `hm`:
            *   Get the word at the start of the window `startWord = s.substring(i, i + unit)`.
            *   Decrement its count in `hm2`.
            *   Move the left pointer `i` forward by `unit`.
            *   Decrement `count`.
        *   **Check for Valid Concatenation**: If `count` equals `wordCount`, it means we have found a valid concatenation. Add the current left pointer `i` to `ans`.

    *   **If `currWord` is NOT in `hm`**:
        *   This word breaks the potential concatenation. Reset `count` to 0.
        *   Move the left pointer `i` to `j + unit` (the position after the current invalid word).
        *   Clear `hm2` as the window is now invalid.

5.  **Return Result**: After iterating through all offsets and windows, return the `ans` list.

## Concept to Remember
*   **Sliding Window Technique**: Efficiently processing a contiguous subsegment of data by maintaining a window and moving its boundaries.
*   **Hash Maps (Frequency Counting)**: Using dictionaries/hash tables to store and retrieve counts of elements (words in this case) in O(1) average time.
*   **String Manipulation**: Efficiently extracting substrings.
*   **Handling Edge Cases/Offsets**: Recognizing that the concatenation might not start at an index divisible by `word_length`.

## Common Mistakes
*   **Not handling offsets correctly**: Forgetting to iterate through all possible starting offsets (0 to `unit - 1`) can lead to missing valid substrings.
*   **Inefficient window shrinking**: Not shrinking the window from the left when a word count exceeds the target can lead to incorrect matches or infinite loops.
*   **Incorrectly resetting the window**: When an invalid word is encountered, failing to reset the `count`, `i`, and `hm2` properly will lead to incorrect results.
*   **Off-by-one errors in substring extraction or loop bounds**: Common in string manipulation problems.
*   **Not handling duplicate words in the input `words` array**: The frequency map approach correctly handles duplicates.

## Complexity Analysis
*   **Time**: O(N * M), where N is the length of the string `s` and M is the length of each word.
    *   The outer loop runs `M` times (for each offset).
    *   The inner loop iterates through `s` with a step of `M`, so it runs approximately `N/M` times.
    *   Inside the inner loop, substring operations take O(M) time. Hash map operations (put, get, containsKey) take O(M) time on average because string keys are involved (hashing and comparison take time proportional to string length).
    *   The `while` loop for shrinking the window also processes each word at most once per outer loop iteration.
    *   Therefore, the total time complexity is roughly `M * (N/M * M) = N * M`.
*   **Space**: O(K * M), where K is the number of unique words in `words` and M is the length of each word.
    *   This space is used for the hash maps (`hm` and `hm2`) to store word frequencies. In the worst case, `hm2` could store up to `wordCount` words.

## Commented Code
```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // Initialize an empty list to store the starting indices of valid substrings.
        List<Integer> ans = new ArrayList<>();
        
        // Get the total number of words in the input array.
        int wordCount = words.length;
        // If there are no words, no concatenation is possible, return empty list.
        if (wordCount == 0) return ans;
        
        // Get the length of each individual word. All words have the same length.
        int unit = words[0].length();
        // Calculate the total length of the concatenated substring we are looking for.
        int size  = unit * wordCount;
        // Get the length of the main string s.
        int n = s.length();
        
        // If the total size of the concatenated words is greater than the string s,
        // it's impossible to find such a substring.
        if (size > n) return ans;
        
        // Create a frequency map for the words in the input array.
        // Key: word, Value: count of that word.
        HashMap<String,Integer> hm = new HashMap<>();
        // Populate the frequency map.
        for(int i = 0; i < wordCount; i++) {
            // For each word, increment its count in the map.
            // getOrDefault handles cases where the word is not yet in the map.
            hm.put(words[i], hm.getOrDefault(words[i], 0) + 1);
        }
        
        // This outer loop iterates through all possible starting offsets for the concatenation.
        // Since words have length 'unit', a valid concatenation can start at index 0, 1, ..., unit-1,
        // and then subsequent words will align at multiples of 'unit' from these starting offsets.
        // For example, if unit=3, we check starting positions:
        // 0, 3, 6, ...
        // 1, 4, 7, ...
        // 2, 5, 8, ...
        for(int x = 0; x < unit; x++){
            // For each offset 'x', we use a sliding window approach.
            // hm2 will store the frequency of words within the current sliding window.
            HashMap<String,Integer> hm2 = new HashMap<>();
            // 'i' is the left pointer of our sliding window. It marks the start of a potential valid substring.
            int i = x;
            // 'count' tracks how many words from the 'words' array we have successfully matched in the current window.
            int count = 0;
            
            // This inner loop slides the window across the string 's'.
            // 'j' is the right pointer (start of the current word being examined).
            // We increment 'j' by 'unit' to move to the next potential word boundary.
            // The loop condition ensures that we don't go out of bounds when extracting the current word.
            for(int j = x; j <= n - unit; j += unit){
                // Extract the current word of length 'unit' starting at index 'j'.
                String currWord = s.substring(j, j + unit);
                
                // Check if the current word is one of the target words we are looking for.
                if(hm.containsKey(currWord)){
                    // If it is a target word, add it to our current window's frequency map.
                    hm2.put(currWord, hm2.getOrDefault(currWord, 0) + 1);
                    // Increment the count of matched words.
                    count++;
                    
                    // This 'while' loop handles the case where we have too many occurrences of 'currWord'
                    // in our current window compared to the required frequency in 'hm'.
                    // We need to shrink the window from the left ('i') until the count is valid again.
                    while(hm2.get(currWord) > hm.get(currWord)){
                        // Get the word at the start of the window ('i').
                        String startWord = s.substring(i, i + unit);
                        // Decrement its count in the window's frequency map.
                        hm2.put(startWord, hm2.get(startWord) - 1);
                        // Move the left pointer 'i' forward by 'unit' to shrink the window.
                        i += unit;
                        // Decrement the count of matched words as we removed one from the window.
                        count--;
                    }
                    
                    // If the number of matched words ('count') equals the total number of words required ('wordCount'),
                    // it means the current window (starting at 'i' and ending at 'j + unit') is a valid concatenation.
                    if(count == wordCount) {
                        // Add the starting index 'i' of this valid concatenation to our result list.
                        ans.add(i);
                    }
                
                } else {
                    // If the current word is NOT in our target words ('hm'), it breaks the concatenation.
                    // Reset the count of matched words to 0.
                    count = 0;
                    // Move the left pointer 'i' to the position right after the current invalid word.
                    i = j + unit;
                    // Clear the window's frequency map 'hm2' because the window is now invalid.
                    hm2.clear();
                }
            }
        }
        // Return the list of all starting indices where a valid concatenation was found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the offset loop**: Clearly articulate why you need to iterate from `0` to `unit - 1`. This is crucial for correctness.
*   **Detail the sliding window logic**: Explain how `i` and `j` move, how `hm2` is updated, and especially the shrinking mechanism (`while` loop) when counts exceed targets.
*   **Discuss edge cases**: Mention what happens if `words` is empty, if `s` is shorter than the concatenated words, or if `s` contains words not in `words`.
*   **Clarify complexity**: Be prepared to explain the time and space complexity, especially how string operations and hash map lookups contribute.

## Revision Checklist
- [ ] Understand the problem statement: find all starting indices of substrings that are concatenations of all words exactly once.
- [ ] Recognize the need for a sliding window approach.
- [ ] Implement frequency maps (`HashMap`) to track word counts.
- [ ] Handle the offset iteration (`x` from `0` to `unit - 1`).
- [ ] Correctly manage the sliding window:
    - [ ] Expand window by moving `j`.
    - [ ] Shrink window by moving `i` when word counts exceed target.
    - [ ] Reset window when an invalid word is encountered.
- [ ] Check for valid concatenation when `count == wordCount`.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty `words`, `s` too short).

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Permutation in String
*   Find All Anagrams in a String
*   Substring with Largest Variance

## Tags
`Array` `Hash Map` `String` `Sliding Window`
