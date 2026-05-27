# Count The Number Of Special Characters Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] smalls = new boolean[26];
        Boolean[] caps = new Boolean[26];
        for(char c : word.toCharArray()){
            if(Character.isLowerCase(c)){
                smalls[c-'a'] = true;
                char C = Character.toUpperCase(c);
                if(caps[C-'A'] !=null && caps[C-'A'] == true) caps[Character.toUpperCase(c)-'A'] = false;
            } else {
                if(caps[c-'A'] == null) caps[c-'A'] = true;
            }
        }
        int ans = 0;
        for(int i=0;i<26;i++) if(caps[i]!=null && smalls[i] && caps[i]) ans++;
        return ans;
    }
}
```

---

---
## Quick Revision
This problem asks to count characters that appear as both lowercase and uppercase in a string.
We solve this by tracking the presence of lowercase and uppercase versions of each letter.

## Intuition
The core idea is that a "special character" is one where *both* its lowercase and uppercase forms exist in the string. If we see a lowercase 'a', we mark that 'a' is present. If we later see an uppercase 'A', we need to check if 'a' was *already* seen. If it was, then 'a'/'A' is a special character. We also need to handle cases where an uppercase letter appears *before* its lowercase counterpart.

## Algorithm
1. Initialize two data structures to track the presence of lowercase and uppercase letters. A boolean array `smalls[26]` for lowercase and a `Boolean` array `caps[26]` for uppercase is suitable. `Boolean` is used for `caps` to distinguish between "not seen" (null), "seen uppercase only" (true), and "seen both" (false).
2. Iterate through the input string `word` character by character.
3. For each character `c`:
    a. If `c` is lowercase:
        i. Mark its presence in the `smalls` array: `smalls[c - 'a'] = true;`.
        ii. Convert `c` to its uppercase equivalent: `char C = Character.toUpperCase(c);`.
        iii. Check if the uppercase version `C` has already been seen (i.e., `caps[C - 'A'] != null`).
        iv. If `caps[C - 'A']` is not null and is `true` (meaning only uppercase was seen so far), then this character is now a special character. We mark this by setting `caps[C - 'A'] = false;` to indicate both forms have been seen.
    b. If `c` is uppercase:
        i. If `caps[c - 'A']` is currently `null` (meaning this is the first time we're seeing this uppercase letter), mark it as seen: `caps[c - 'A'] = true;`.
4. Initialize a counter `ans` to 0.
5. Iterate from `i = 0` to `25` (representing 'a' through 'z').
6. For each index `i`:
    a. Check if the uppercase letter corresponding to `i` has been seen (`caps[i] != null`).
    b. Check if the lowercase letter corresponding to `i` has been seen (`smalls[i]`).
    c. Check if the uppercase letter has been marked as "seen both" (`caps[i] == false`).
    d. If all three conditions are true, increment `ans`.
7. Return `ans`.

## Concept to Remember
*   Character manipulation (ASCII values, `isLowerCase`, `toUpperCase`).
*   Using arrays as frequency maps or presence trackers.
*   Handling distinct states (e.g., seen lowercase only, seen uppercase only, seen both).
*   Boolean vs. nullable Boolean for tracking states.

## Common Mistakes
*   Not handling the case where the uppercase letter appears before the lowercase letter.
*   Incorrectly updating the state in the `caps` array when a lowercase letter is encountered and its uppercase counterpart was already seen.
*   Using a simple boolean array for `caps` and not being able to distinguish between "not seen" and "seen uppercase only".
*   Off-by-one errors when calculating array indices (`c - 'a'`, `C - 'A'`).
*   Forgetting to check `caps[i] != null` in the final loop, which could lead to false positives if only lowercase was seen.

## Complexity Analysis
- Time: O(N) - where N is the length of the input string `word`. We iterate through the string once to populate the tracking arrays, and then iterate through a fixed-size array (26) once.
- Space: O(1) - We use two fixed-size arrays of size 26, which is constant space regardless of the input string length.

## Commented Code
```java
class Solution {
    public int numberOfSpecialChars(String word) {
        // Initialize a boolean array to track the presence of lowercase letters 'a' through 'z'.
        // Index i corresponds to the i-th letter of the alphabet.
        boolean[] smalls = new boolean[26];

        // Initialize a Boolean array to track the presence of uppercase letters 'A' through 'Z'.
        // We use Boolean (nullable) to distinguish between:
        // null: the uppercase letter has not been seen yet.
        // true: the uppercase letter has been seen, but its lowercase counterpart has not.
        // false: both the uppercase and lowercase versions of the letter have been seen.
        Boolean[] caps = new Boolean[26];

        // Iterate through each character in the input string.
        for(char c : word.toCharArray()){
            // Check if the current character is a lowercase letter.
            if(Character.isLowerCase(c)){
                // Mark the presence of this lowercase letter.
                smalls[c-'a'] = true;
                // Get the uppercase equivalent of the current lowercase character.
                char C = Character.toUpperCase(c);
                // Check if the uppercase version of this character has already been seen (i.e., caps[C-'A'] is not null).
                // And if it was previously marked as 'seen uppercase only' (caps[C-'A'] == true).
                if(caps[C-'A'] !=null && caps[C-'A'] == true) {
                    // If both lowercase and uppercase have now been seen, mark this character as 'seen both'.
                    // Setting it to false indicates that it's a special character.
                    caps[Character.toUpperCase(c)-'A'] = false;
                }
            } else { // The current character is an uppercase letter.
                // If this uppercase letter has not been seen before (caps[c-'A'] is null).
                if(caps[c-'A'] == null) {
                    // Mark that we have seen this uppercase letter.
                    // Initially, we set it to true, meaning 'seen uppercase only' so far.
                    caps[c-'A'] = true;
                }
                // If caps[c-'A'] is already false, it means both lowercase and uppercase were seen previously.
                // If caps[c-'A'] is true, it means only uppercase was seen, and now we've seen it again, which doesn't change its state from 'seen uppercase only'.
            }
        }

        // Initialize a counter for the number of special characters.
        int ans = 0;
        // Iterate through all possible letters from 'a' to 'z' (represented by indices 0 to 25).
        for(int i=0; i<26; i++) {
            // A character is special if:
            // 1. Its uppercase version has been seen (caps[i] != null).
            // 2. Its lowercase version has been seen (smalls[i] is true).
            // 3. It has been marked as 'seen both' (caps[i] == false).
            if(caps[i]!=null && smalls[i] && caps[i] == false) {
                // If all conditions are met, increment the count of special characters.
                ans++;
            }
        }
        // Return the total count of special characters.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain your choice of data structures (`boolean[]` vs. `Boolean[]`) and why `Boolean` is necessary for the uppercase tracking.
*   Walk through an example like "aA" or "Aa" to demonstrate how your logic handles the order of appearance.
*   Discuss edge cases: empty string, string with only lowercase, string with only uppercase, string with no special characters.
*   Be prepared to optimize if asked (though O(N) time and O(1) space is already optimal here).

## Revision Checklist
- [ ] Understand the definition of a "special character".
- [ ] Choose appropriate data structures to track presence of lowercase and uppercase letters.
- [ ] Handle the order of appearance of lowercase and uppercase letters correctly.
- [ ] Implement the logic to update tracking states.
- [ ] Correctly count special characters based on the final states.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Count Number of Distinct Integers After Reverse Operations
*   Find All Anagrams in a String
*   Group Anagrams

## Tags
`Array` `Hash Map` `String` `Boolean`
