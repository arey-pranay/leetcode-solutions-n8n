# Group Anagrams

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Sorting`  
**Time:** O(N * K log K)  
**Space:** O(N * K)

---

## Solution (java)

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hm = new HashMap<>();
        for(String str : strs){
            char[] carr =str.toCharArray();
            Arrays.sort(carr);
            String key = new String(carr);
            List<String> temp = hm.getOrDefault(key, new ArrayList<>());
            temp.add(str);
            hm.put(key,temp);
        }
        List<List<String>> ans = new ArrayList<>();
        for(Map.Entry<String,List<String>> e : hm.entrySet()) ans.add(e.getValue());
        return ans;
    }
}
```

---

---
## Quick Revision
Given an array of strings, group anagrams together.
Anagrams can be identified by sorting their characters to create a canonical representation.

## Intuition
The core idea is that anagrams are strings that contain the same characters with the same frequencies. If we can find a way to represent each string such that all its anagrams map to the same representation, we can use this representation as a key in a hash map. The values of the hash map will be lists of strings that share the same canonical representation, effectively grouping the anagrams. Sorting the characters of a string provides such a canonical representation.

## Algorithm
1. Initialize an empty hash map where keys will be sorted strings and values will be lists of original strings.
2. Iterate through each string in the input array `strs`.
3. For each string:
    a. Convert the string to a character array.
    b. Sort the character array alphabetically.
    c. Convert the sorted character array back into a string. This sorted string will be our key.
    d. Check if this sorted string (key) already exists in the hash map.
    e. If it exists, retrieve the list of strings associated with that key and add the current original string to it.
    f. If it doesn't exist, create a new list, add the current original string to it, and put this new list into the hash map with the sorted string as the key.
4. Initialize an empty list of lists to store the final result.
5. Iterate through all the values (which are lists of strings) in the hash map.
6. Add each of these lists to the result list.
7. Return the result list.

## Concept to Remember
*   **Anagrams:** Strings that contain the same characters with the same frequencies, regardless of order.
*   **Hash Maps (Dictionaries):** Data structures that store key-value pairs, allowing for efficient lookups, insertions, and deletions based on keys.
*   **Sorting:** A fundamental algorithm for arranging elements in a specific order, useful here for creating a canonical representation of strings.
*   **Canonical Representation:** A standardized form of data that allows for easy comparison and grouping.

## Common Mistakes
*   **Incorrectly identifying anagrams:** Not realizing that sorting characters is a reliable way to group anagrams.
*   **Inefficient key generation:** Using something other than a sorted string or character count array as the key, which might not be unique for anagrams.
*   **Modifying original strings:** If the problem statement implies immutability, be careful not to modify the input strings directly if they are needed later.
*   **Handling empty input or empty strings:** Ensuring the code gracefully handles edge cases like an empty input array or strings that are empty.

## Complexity Analysis
*   **Time:** O(N * K log K) - where N is the number of strings in the input array and K is the maximum length of a string. This is because for each of the N strings, we sort its characters, which takes O(K log K) time.
*   **Space:** O(N * K) - The space complexity is dominated by the hash map, which stores all the strings. In the worst case, all strings are unique and have length K, so the total space used is proportional to the total number of characters across all strings.

## Commented Code
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Initialize a HashMap to store groups of anagrams.
        // The key will be the sorted version of a string, and the value will be a list of original strings that are anagrams of each other.
        HashMap<String, List<String>> hm = new HashMap<>();

        // Iterate through each string in the input array 'strs'.
        for (String str : strs) {
            // Convert the current string 'str' into a character array.
            char[] carr = str.toCharArray();
            // Sort the character array alphabetically. This creates a canonical representation for anagrams.
            Arrays.sort(carr);
            // Convert the sorted character array back into a string. This will be our key in the HashMap.
            String key = new String(carr);

            // Retrieve the list of strings associated with this 'key' from the HashMap.
            // If the key is not present, 'getOrDefault' returns a new empty ArrayList.
            List<String> temp = hm.getOrDefault(key, new ArrayList<>());
            // Add the current original string 'str' to the list.
            temp.add(str);
            // Put the updated list back into the HashMap with the 'key'.
            hm.put(key, temp);
        }

        // Initialize a list of lists to store the final result.
        List<List<String>> ans = new ArrayList<>();
        // Iterate through all the entries (key-value pairs) in the HashMap.
        for (Map.Entry<String, List<String>> e : hm.entrySet()) {
            // For each entry, add its value (which is a list of anagrams) to the result list.
            ans.add(e.getValue());
        }
        // Return the list containing all grouped anagrams.
        return ans;
    }
}
```

## Interview Tips
1.  **Explain the "Why":** Clearly articulate *why* sorting characters works for identifying anagrams. This shows you understand the underlying concept, not just memorized a solution.
2.  **Consider Alternatives:** Briefly mention alternative approaches, like using a character count array (e.g., an array of 26 integers for lowercase English letters) as the key. Discuss the trade-offs (e.g., sorting is simpler to implement, character count might be slightly faster if K is large and alphabet is small).
3.  **Edge Cases:** Be prepared to discuss how your solution handles empty input arrays, empty strings within the array, and strings with special characters or different cases (though the problem usually implies lowercase English letters).
4.  **Complexity Justification:** Be ready to explain the time and space complexity clearly, breaking down where each part of the calculation comes from.

## Revision Checklist
- [ ] Understand the definition of an anagram.
- [ ] Recognize that sorting characters creates a unique identifier for anagrams.
- [ ] Implement a hash map to store groups.
- [ ] Correctly convert strings to char arrays, sort them, and convert back to strings for keys.
- [ ] Use `getOrDefault` for efficient handling of new keys.
- [ ] Iterate through map values to construct the final result.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases like empty input or empty strings.

## Similar Problems
*   Valid Anagram (LeetCode 242)
*   Group Shifted Strings (LeetCode 249)
*   Find All Anagrams in a String (LeetCode 438)

## Tags
`Array` `Hash Map` `String` `Sorting`
