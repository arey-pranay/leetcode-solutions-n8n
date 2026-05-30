# Valid Anagram

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sorting`  
**Time:** O(N)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        //we can do this also via using frequency array.[26]
        HashMap<Character,Integer> available = new HashMap<>();
        for(char c : s.toCharArray()) available.put(c,available.getOrDefault(c,0)+1);
        if(s.length() != t.length()) return false;
        for(char c : t.toCharArray()){
            if(!available.containsKey(c)) return false;
            int rem = available.get(c);
            if(rem==1) available.remove(c);
            else available.put(c,rem-1);
        }
        return true;
    }
}
// "aacc" 
// "ccac" a:2 c:0
```

---

---
## Quick Revision
Given two strings, determine if one is an anagram of the other.
This is solved by comparing character frequencies of both strings.

## Intuition
An anagram means that both strings must contain the exact same characters with the exact same frequencies. If we can count the occurrences of each character in the first string and then "subtract" the occurrences of characters from the second string, the counts should all end up at zero (or the character should be removed from our count) if they are anagrams. If at any point we encounter a character in the second string that isn't in our count, or if our count for a character goes below zero, they are not anagrams.

## Algorithm
1. Check if the lengths of the two strings, `s` and `t`, are different. If they are, return `false` immediately, as anagrams must have the same length.
2. Create a frequency map (e.g., a HashMap) to store the character counts of string `s`.
3. Iterate through each character `c` in string `s`. For each character, increment its count in the frequency map. If the character is not already in the map, initialize its count to 1.
4. Iterate through each character `c` in string `t`.
5. For each character `c` in `t`:
    a. Check if the character `c` exists as a key in the frequency map. If it does not, return `false` because `t` contains a character not present in `s`.
    b. Get the current count of character `c` from the map.
    c. If the count is 1, remove the character `c` from the map (as its count will become 0).
    d. If the count is greater than 1, decrement its count in the map.
6. After iterating through all characters in `t`, if the frequency map is empty, it means all characters from `s` were perfectly matched by characters in `t`. Return `true`. If the map is not empty, it means `s` had characters that were not fully accounted for in `t`, so return `false`. (Note: The provided solution implicitly handles this by returning `true` if the loop completes without returning `false`, which is correct if the lengths are equal and all characters in `t` are found and decremented from the map).

## Concept to Remember
*   **Hash Maps/Dictionaries:** Efficient data structures for storing key-value pairs, ideal for frequency counting.
*   **Character Frequency:** The core idea behind anagrams is that character counts must match.
*   **String Manipulation:** Understanding how to iterate through strings and access individual characters.
*   **Edge Cases:** Handling differing string lengths is a crucial first step.

## Common Mistakes
*   **Not checking string lengths first:** This is a quick optimization and a necessary condition for anagrams.
*   **Incorrectly handling character counts:** Forgetting to decrement counts or not removing characters when their count reaches zero can lead to errors.
*   **Case sensitivity:** The problem usually implies case sensitivity unless specified otherwise. The current solution is case-sensitive.
*   **Assuming character set:** Relying on a fixed-size array (like `int[26]`) might fail if the input strings can contain characters outside the English alphabet (e.g., Unicode). A HashMap is more robust.

## Complexity Analysis
- Time: O(N) - reason: We iterate through string `s` once to build the frequency map and then iterate through string `t` once to check and decrement counts. If N is the length of the strings, this is O(N) + O(N) = O(N).
- Space: O(K) - reason: Where K is the number of unique characters in string `s`. In the worst case, if all characters are unique, K can be up to N. However, if the character set is limited (e.g., lowercase English letters), K is constant (26), making it O(1). For a general character set, it's O(N) in the worst case.

## Commented Code
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        // If the lengths of the strings are different, they cannot be anagrams.
        if (s.length() != t.length()) {
            // Return false immediately as a quick check.
            return false;
        }

        // Create a HashMap to store the frequency of characters in string 's'.
        // The key is the character, and the value is its count.
        HashMap<Character, Integer> available = new HashMap<>();

        // Iterate through each character 'c' in the string 's' converted to a character array.
        for (char c : s.toCharArray()) {
            // For each character, update its count in the 'available' map.
            // getOrDefault(c, 0) retrieves the current count of 'c', or 0 if 'c' is not yet in the map.
            // Then, we add 1 to this count and put it back into the map.
            available.put(c, available.getOrDefault(c, 0) + 1);
        }

        // Iterate through each character 'c' in the string 't' converted to a character array.
        for (char c : t.toCharArray()) {
            // Check if the current character 'c' from string 't' is present as a key in our 'available' map.
            if (!available.containsKey(c)) {
                // If 'c' is not in the map, it means 't' has a character that 's' does not, so they are not anagrams.
                return false;
            }

            // Get the current count of character 'c' from the map.
            int rem = available.get(c);

            // If the count of 'c' is 1, it means this is the last occurrence of this character we expect.
            if (rem == 1) {
                // Remove the character from the map because its count will become 0.
                available.remove(c);
            } else {
                // If the count is greater than 1, decrement its count in the map.
                available.put(c, rem - 1);
            }
        }

        // If the loop completes without returning false, it means all characters in 't' were found in 's'
        // and their counts were successfully decremented.
        // Since we already checked that s.length() == t.length(), if the map is now empty,
        // it implies all characters matched perfectly.
        // The condition `available.isEmpty()` is implicitly checked by returning true here,
        // because if any character from 's' was not fully accounted for by 't', the map wouldn't be empty.
        return true;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the character set (e.g., lowercase English letters only, or any Unicode characters). This impacts whether an array or HashMap is better.
*   **Explain the HashMap Logic:** Clearly articulate how the HashMap is used to track frequencies and how decrementing/removing elements signifies a match.
*   **Discuss the Array Alternative:** Mention that if the character set is fixed and small (like 26 lowercase English letters), an `int[26]` array can be more efficient in terms of constant factors and potentially space.
*   **Handle Edge Cases:** Emphasize the importance of the initial length check.

## Revision Checklist
- [ ] Understand the definition of an anagram.
- [ ] Implement frequency counting for one string.
- [ ] Implement frequency checking/decrementing for the second string.
- [ ] Handle cases where characters are missing or counts go negative.
- [ ] Consider the initial length check.
- [ ] Analyze time and space complexity.
- [ ] Be prepared to discuss the array-based solution.

## Similar Problems
*   Group Anagrams (Medium)
*   Palindrome Permutation (Easy)
*   Valid Palindrome II (Easy)

## Tags
`Array` `Hash Map` `String` `Counting`
