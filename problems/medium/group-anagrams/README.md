# Group Anagrams

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Sorting`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
    }
}
```

---

---
## Problem Summary
Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## Approach and Intuition
The core idea is to identify strings that are anagrams of each other. Two strings are anagrams if they contain the same characters with the same frequencies.

A common way to represent the "signature" of an anagram is by sorting its characters. If two strings have the same sorted character sequence, they are anagrams.

We can use a hash map to store groups of anagrams. The key of the hash map will be the sorted version of a string (its signature), and the value will be a list of all strings from the input array that have this signature.

Here's the step-by-step intuition:
1. **Iterate through each string** in the input array `strs`.
2. For each string, **generate its canonical representation (signature)**. The simplest way to do this is to sort the characters of the string alphabetically. For example, "eat" becomes "aet", "tea" becomes "aet", and "tan" becomes "ant".
3. **Use a hash map** where:
    * The **key** is the sorted string (the signature).
    * The **value** is a `List<String>` that will store all original strings that map to this signature.
4. When processing a string:
    * Calculate its sorted signature.
    * Check if this signature already exists as a key in the hash map.
    * If it exists, add the current original string to the `List<String>` associated with that key.
    * If it doesn't exist, create a new entry in the hash map with the signature as the key and a new `List<String>` containing the current original string as the value.
5. After iterating through all strings, the **values of the hash map** will be the desired groups of anagrams. Collect these lists into a final `List<List<String>>` to return.

## Complexity Analysis
- **Time:** O(N * K log K)
    - N: The number of strings in the input array `strs`.
    - K: The maximum length of a string in `strs`.
    - For each of the N strings, we sort its characters. Sorting a string of length K takes O(K log K) time.
    - Hash map operations (put, get) are typically O(1) on average, but in the worst case (due to hash collisions), they can be O(K) if the key is a string of length K. However, the dominant factor is the sorting.

- **Space:** O(N * K)
    - N: The number of strings in the input array `strs`.
    - K: The maximum length of a string in `strs`.
    - The hash map stores all the strings. In the worst case, all strings are unique and have different anagram signatures, so we store all N strings, each of length up to K. The keys (sorted strings) also contribute to space, but their total length is bounded by the total length of all input strings.

## Code Walkthrough
(Since the provided code is an empty template, I will describe the implementation based on the approach.)

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 1. Initialize a HashMap to store anagram groups.
        //    Key: The sorted string (signature of an anagram).
        //    Value: A list of original strings that are anagrams of each other.
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // 2. Iterate through each string in the input array.
        for (String str : strs) {
            // 3. Generate the canonical representation (signature) of the string.
            //    Convert the string to a character array.
            char[] charArray = str.toCharArray();
            //    Sort the character array alphabetically.
            Arrays.sort(charArray);
            //    Convert the sorted character array back to a string. This is our signature.
            String sortedStr = new String(charArray);

            // 4. Check if the signature already exists in the HashMap.
            //    If it doesn't exist, create a new list for this signature.
            //    The computeIfAbsent method is a concise way to do this.
            anagramGroups.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);

            /*
            // Alternative way without computeIfAbsent:
            if (!anagramGroups.containsKey(sortedStr)) {
                anagramGroups.put(sortedStr, new ArrayList<>());
            }
            anagramGroups.get(sortedStr).add(str);
            */
        }

        // 5. The values of the HashMap are the grouped anagrams.
        //    Collect all these lists into a single List<List<String>>.
        return new ArrayList<>(anagramGroups.values());
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the size of the input array, the length of strings, and the character set (e.g., lowercase English letters only). This helps in choosing the most efficient approach.
*   **Explain the "Signature":** Clearly articulate *why* sorting the string works as a unique identifier for anagrams. Mention that it captures the character counts.
*   **Hash Map Choice:** Explain why a hash map is suitable here – for efficient lookups and grouping based on a key.
*   **Edge Cases:** Consider empty input array (`strs` is empty), array with one string, array with all identical strings, array with strings of different lengths.
*   **Alternative Signatures:** Be prepared to discuss other ways to create a signature, such as using a frequency count array (e.g., an array of 26 integers for lowercase English letters). This can sometimes be faster if K is large and the alphabet is small.
*   **Code Readability:** Write clean, well-commented code. Use meaningful variable names.

## Optimization and Alternatives
1.  **Frequency Count as Signature:** Instead of sorting, we can use a frequency map (or an array of size 26 for lowercase English letters) as the key for the hash map.
    *   For each string, create a count of each character.
    *   Convert this count into a string or a custom object to be used as a hash map key. For example, for "aab", the count would be {a: 2, b: 1}. This could be represented as a string like "a2b1" or a more structured key.
    *   **Time Complexity:** O(N * K) because counting characters takes O(K) for each string, and hash map operations are O(1) on average.
    *   **Space Complexity:** O(N * K) to store the strings, plus O(alphabet_size) for each key (which is constant, e.g., 26).
    *   This is generally faster than sorting if K is large.

2.  **Using `Map<Integer, List<String>>` with Hashing:** If the alphabet is small and fixed (like lowercase English letters), you could potentially devise a hashing function for the character counts that results in an integer key. This might offer slightly better performance than string keys. However, designing a good, collision-free hash function can be tricky.

## Revision Checklist
- [ ] Understand the definition of an anagram.
- [ ] Identify a canonical representation for anagrams (e.g., sorted string, character count).
- [ ] Choose an appropriate data structure for grouping (HashMap is ideal).
- [ ] Implement the logic to generate the canonical form for each string.
- [ ] Implement the logic to add strings to the correct group in the HashMap.
- [ ] Extract the grouped lists from the HashMap.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty input, single string, etc.).
- [ ] Think about alternative approaches (frequency counting).

## Similar Problems
*   Valid Anagram (LeetCode 242)
*   Find All Anagrams in a String (LeetCode 438)
*   Permutations (LeetCode 46) - Related to rearranging characters.

## Tags
`Array` `Hash Map`

## My Notes
Anagram code
