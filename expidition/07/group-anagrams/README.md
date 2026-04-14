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
        for(int i=0;i<strs.length;i++){
            char[] cArr = strs[i].toCharArray();
            Arrays.sort(cArr);
            String str = new String(cArr); //naye  string m character array daalte hai to string bann jaati hai
            List<String> l = hm.getOrDefault(str, new ArrayList<>());
            l.add(strs[i]);
            hm.put(str,l);
        }
        
        List<List<String>> res = new ArrayList<>();  
        for(List<String> l : hm.values()) res.add(l);
        return res;
    }
}
```

---

---
## Quick Revision
Given an array of strings, group anagrams together.
Anagrams can be identified by sorting their characters to create a canonical representation.

## Intuition
The core idea is that anagrams are words formed by rearranging the same letters. If we can find a way to represent each word such that all its anagrams have the same representation, we can use this representation as a key to group them. Sorting the characters of a string provides exactly this canonical representation. For example, "eat", "tea", and "ate" all become "aet" after sorting. We can then use a hash map where the sorted string is the key and a list of original strings (that map to this sorted key) is the value.

## Algorithm
1. Initialize an empty hash map `hm` where keys will be sorted strings and values will be lists of original strings.
2. Iterate through each string `s` in the input array `strs`.
3. For each string `s`:
    a. Convert the string `s` into a character array `cArr`.
    b. Sort the character array `cArr` alphabetically.
    c. Convert the sorted character array `cArr` back into a string, let's call it `sortedStr`. This `sortedStr` is the canonical representation.
    d. Check if `sortedStr` already exists as a key in the hash map `hm`.
        i. If it exists, retrieve the list of strings associated with `sortedStr` and add the original string `s` to this list.
        ii. If it doesn't exist, create a new empty list, add the original string `s` to it, and then put `sortedStr` as the key and this new list as the value into `hm`.
4. Initialize an empty list of lists `res` to store the final grouped anagrams.
5. Iterate through all the values (which are lists of strings) in the hash map `hm`.
6. Add each of these lists to the `res` list.
7. Return the `res` list.

## Concept to Remember
*   **Anagrams:** Understanding the definition and properties of anagrams.
*   **Hash Maps (Dictionaries):** Using hash maps for efficient key-value storage and retrieval.
*   **String Manipulation:** Converting strings to character arrays, sorting, and converting back.
*   **Sorting Algorithms:** Familiarity with sorting, particularly for character arrays.

## Common Mistakes
*   **Incorrectly generating the canonical key:** Forgetting to sort or using a method that doesn't uniquely identify anagrams.
*   **Modifying original strings:** Accidentally altering the input strings instead of creating new representations.
*   **Inefficient grouping:** Not using a hash map, leading to O(N^2) or worse comparisons.
*   **Handling empty input or edge cases:** Not considering cases like an empty input array or arrays with single-character strings.

## Complexity Analysis
*   **Time:** O(N * K log K) - where N is the number of strings in the input array and K is the maximum length of a string. This is because for each of the N strings, we sort its characters, which takes O(K log K) time.
*   **Space:** O(N * K) - In the worst case, all strings are unique and not anagrams of each other, so the hash map will store all N strings, and the total length of strings stored is N * K.

## Commented Code
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Initialize a HashMap to store sorted strings as keys and lists of original strings as values.
        HashMap<String, List<String>> hm = new HashMap<>();

        // Iterate through each string in the input array.
        for (int i = 0; i < strs.length; i++) {
            // Convert the current string to a character array.
            char[] cArr = strs[i].toCharArray();
            // Sort the character array alphabetically. This creates a canonical representation for anagrams.
            Arrays.sort(cArr);
            // Convert the sorted character array back into a string. This will be our key in the HashMap.
            String sortedStr = new String(cArr);

            // Get the list associated with the sorted string key.
            // If the key doesn't exist, create a new ArrayList and return it.
            List<String> anagramList = hm.getOrDefault(sortedStr, new ArrayList<>());
            // Add the original string to the list of anagrams.
            anagramList.add(strs[i]);
            // Put the sorted string as the key and the updated list of anagrams as the value into the HashMap.
            hm.put(sortedStr, anagramList);
        }

        // Initialize a list of lists to store the final result.
        List<List<String>> res = new ArrayList<>();
        // Iterate through all the values (which are lists of anagrams) in the HashMap.
        for (List<String> anagramList : hm.values()) {
            // Add each list of anagrams to the result list.
            res.add(anagramList);
        }
        // Return the final list containing groups of anagrams.
        return res;
    }
}
```

## Interview Tips
*   **Explain your thought process:** Clearly articulate why sorting characters is a good way to identify anagrams.
*   **Discuss alternative approaches:** Briefly mention other ways to create a canonical representation (e.g., character count arrays) and their trade-offs.
*   **Handle edge cases:** Be prepared to discuss what happens with an empty input array, an array with one string, or strings with special characters.
*   **Ask clarifying questions:** If unsure about constraints (e.g., character set, string length), ask the interviewer.

## Revision Checklist
- [ ] Understand the definition of an anagram.
- [ ] Know how to generate a canonical representation for anagrams (sorting characters).
- [ ] Be comfortable using `HashMap` for grouping.
- [ ] Understand `toCharArray()`, `Arrays.sort()`, and `new String(char[])`.
- [ ] Be able to analyze time and space complexity for this approach.
- [ ] Practice writing the code from scratch.

## Similar Problems
*   Valid Anagram
*   Find All Anagrams in a String
*   Group Shifted Strings

## Tags
`Array` `Hash Map` `String` `Sorting`

## My Notes
creativvee solution
