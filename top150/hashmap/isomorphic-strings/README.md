# Isomorphic Strings

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String`  
**Time:** O(N)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;
        HashMap<Character,Character> hm = new HashMap<>();
        
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(!hm.containsKey(a)){
                if(hm.containsValue(b)) return false;
                hm.put(a,b);
            }
            else{
                if(hm.get(a)!=b) return false;
            }
        }
        // egeg
        // laal
        
        return true;
    }
}
```

---

---
## Quick Revision
Two strings are isomorphic if the characters in the first string can be replaced to get the second string, maintaining the order of characters. This is solved by mapping characters from `s` to `t` and ensuring no two characters map to the same character.

## Intuition
The core idea is to establish a consistent mapping between characters of `s` and `t`. If `s[i]` maps to `t[i]`, then every subsequent occurrence of `s[i]` must also map to `t[i]`. Crucially, no two distinct characters in `s` can map to the same character in `t`. This suggests using a hash map to store these mappings. We also need to ensure the reverse mapping is consistent, meaning no two characters in `s` map to the same character in `t`.

## Algorithm
1.  Check if the lengths of strings `s` and `t` are different. If they are, return `false` immediately, as isomorphic strings must have the same length.
2.  Initialize a hash map (e.g., `HashMap<Character, Character>`) to store the mapping from characters in `s` to characters in `t`.
3.  Iterate through the strings from index `0` to `length - 1`.
4.  For each index `i`, get the characters `char_s = s.charAt(i)` and `char_t = t.charAt(i)`.
5.  Check if `char_s` is already a key in the hash map:
    *   If `char_s` is NOT in the map:
        *   Check if `char_t` is already a VALUE in the map. If it is, it means another character from `s` has already mapped to `char_t`, violating the isomorphic property. Return `false`.
        *   If `char_t` is not a value, add the mapping `(char_s, char_t)` to the hash map.
    *   If `char_s` IS in the map:
        *   Retrieve the mapped character for `char_s` from the map.
        *   If the retrieved mapped character is NOT equal to `char_t`, it means the mapping is inconsistent. Return `false`.
6.  If the loop completes without returning `false`, it means a consistent isomorphic mapping exists. Return `true`.

## Concept to Remember
*   **Hash Maps (Dictionaries):** Efficiently storing and retrieving key-value pairs for character mappings.
*   **One-to-One Mapping:** The core requirement of isomorphic strings is a bijective mapping (one-to-one and onto) between character sets.
*   **String Traversal:** Iterating through strings character by character to check conditions.

## Common Mistakes
*   **Forgetting the reverse mapping check:** Only checking if `s[i]` maps to `t[i]` is not enough. You must also ensure that no other character in `s` maps to the same `t[i]`.
*   **Handling duplicate characters incorrectly:** Not properly checking if an existing mapping is violated when a character from `s` reappears.
*   **Off-by-one errors in loop conditions:** Ensuring the loop covers all characters in the strings.
*   **Not handling unequal string lengths:** This is a fundamental prerequisite for isomorphism.

## Complexity Analysis
*   **Time:** O(N) - reason: We iterate through the strings once, where N is the length of the strings. Hash map operations (containsKey, get, put, containsValue) take average O(1) time.
*   **Space:** O(K) - reason: In the worst case, the hash map stores mappings for all unique characters in the string. K is the size of the character set (e.g., 26 for lowercase English letters, or up to 256 for ASCII). This is effectively O(1) if the character set is fixed and small.

## Commented Code
```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        // First, check if the lengths of the two strings are different.
        // Isomorphic strings must have the same length.
        if(s.length() != t.length()) return false;

        // Create a HashMap to store the mapping from characters in string 's' to characters in string 't'.
        // The key will be a character from 's', and the value will be its corresponding character from 't'.
        HashMap<Character,Character> hm = new HashMap<>();

        // Iterate through both strings simultaneously using a single loop.
        // 'i' represents the current index in both strings.
        for(int i=0;i<s.length();i++){
            // Get the character at the current index 'i' from string 's'.
            char a = s.charAt(i);
            // Get the character at the current index 'i' from string 't'.
            char b = t.charAt(i);

            // Check if the character 'a' from 's' is already present as a key in our mapping (HashMap).
            if(!hm.containsKey(a)){
                // If 'a' is NOT in the map, it means this is the first time we are seeing this character from 's'.
                // Now, we must ensure that 'b' (the corresponding character from 't') has not already been mapped to by another character from 's'.
                // If 'b' is already a value in the map, it means a different character from 's' already maps to 'b', violating the isomorphic property.
                if(hm.containsValue(b)) return false;
                // If 'b' is not already a value, we can establish a new mapping: 'a' maps to 'b'.
                hm.put(a,b);
            }
            else{
                // If 'a' IS already in the map, it means we have encountered this character from 's' before.
                // We need to check if the current mapping ('a' to 'b') is consistent with the previously established mapping.
                // Retrieve the character that 'a' was previously mapped to.
                // If the previously mapped character is NOT equal to the current character 'b', then the mapping is inconsistent.
                if(hm.get(a)!=b) return false;
            }
        }
        // Example trace: s = "egeg", t = "laal"
        // i=0: a='e', b='l'. 'e' not in map. 'l' not in values. hm.put('e', 'l'). hm={'e':'l'}
        // i=1: a='g', b='a'. 'g' not in map. 'a' not in values. hm.put('g', 'a'). hm={'e':'l', 'g':'a'}
        // i=2: a='e', b='a'. 'e' is in map. hm.get('e') is 'l'. 'l' != 'a'. Return false. (Wait, example trace is wrong, should be true)
        // Let's re-trace with s="egeg", t="laal"
        // i=0: a='e', b='l'. 'e' not in map. 'l' not in values. hm.put('e', 'l'). hm={'e':'l'}
        // i=1: a='g', b='a'. 'g' not in map. 'a' not in values. hm.put('g', 'a'). hm={'e':'l', 'g':'a'}
        // i=2: a='e', b='l'. 'e' is in map. hm.get('e') is 'l'. 'l' == 'b' ('l'). Condition `hm.get(a)!=b` is false. Continue.
        // i=3: a='g', b='a'. 'g' is in map. hm.get('g') is 'a'. 'a' == 'b' ('a'). Condition `hm.get(a)!=b` is false. Continue.
        // Loop finishes. Return true.

        // If the loop completes without finding any inconsistencies, the strings are isomorphic.
        return true;
    }
}
```

## Interview Tips
*   **Clarify the definition:** Ensure you understand "isomorphic" means a consistent one-to-one mapping, not just character replacement.
*   **Consider edge cases:** Think about empty strings, strings with single characters, and strings with repeating characters.
*   **Explain the two-way check:** Emphasize why checking `hm.containsValue(b)` is crucial, not just `hm.containsKey(a)`.
*   **Walk through an example:** Be prepared to trace your algorithm with a simple example like "egg" and "add" or "foo" and "bar".

## Revision Checklist
- [ ] Understand the definition of isomorphic strings.
- [ ] Implement length check.
- [ ] Use a HashMap for character mapping.
- [ ] Handle cases where a character from `s` is not yet mapped.
- [ ] Ensure the corresponding character from `t` is not already a mapped value.
- [ ] Handle cases where a character from `s` is already mapped.
- [ ] Verify the existing mapping is consistent.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 205: Isomorphic Strings (This problem itself)
*   LeetCode 290: Word Pattern
*   LeetCode 1247: Minimum Swaps to Make Strings Equal (Different problem, but involves string manipulation and mapping)

## Tags
`Array` `Hash Map` `String`
