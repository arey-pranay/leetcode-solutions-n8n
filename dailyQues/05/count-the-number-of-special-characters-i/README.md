# Count The Number Of Special Characters I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int numberOfSpecialChars(String word) {
        
        boolean[] caps = new boolean[26];
        boolean[] smalls = new boolean[26];
        for(char c : word.toCharArray()) if(Character.isLowerCase(c)) smalls[c-'a'] = true; else caps[c-'A'] = true;
        
        int count = 0;
        for(int i=0;i<26;i++) if(caps[i] && smalls[i]) count++;
        return count;
        
    }
}
```

---

---
## Quick Revision
This problem asks to count characters that appear as both lowercase and uppercase in a given string.
We solve this by tracking the presence of each lowercase and uppercase letter using boolean arrays and then comparing them.

## Intuition
The core idea is that a "special character" is defined by its dual existence: both its lowercase and uppercase forms must be present in the string. To efficiently check this, we can use two separate data structures to record which lowercase letters and which uppercase letters we've encountered. If, for a given letter (e.g., 'a'/'A'), we find that both its lowercase and uppercase forms have been marked as present, then it's a special character.

## Algorithm
1. Initialize two boolean arrays, `smalls` and `caps`, both of size 26. `smalls[i]` will be true if the character 'a' + i is present in the string, and `caps[i]` will be true if the character 'A' + i is present.
2. Iterate through each character `c` in the input string `word`.
3. If `c` is a lowercase character, mark `smalls[c - 'a']` as true.
4. If `c` is an uppercase character, mark `caps[c - 'A']` as true.
5. Initialize a counter `count` to 0.
6. Iterate from `i = 0` to `25` (representing 'a' through 'z' and 'A' through 'Z').
7. For each `i`, check if both `smalls[i]` and `caps[i]` are true.
8. If both are true, increment `count`.
9. Return `count`.

## Concept to Remember
*   Character representation and ASCII values: Understanding how characters map to numerical values (e.g., 'a' - 'a' = 0, 'A' - 'A' = 0).
*   Boolean arrays for presence tracking: Efficiently marking and checking the existence of elements.
*   Case sensitivity: Differentiating between lowercase and uppercase letters.

## Common Mistakes
*   Confusing character arithmetic: Incorrectly calculating indices for lowercase and uppercase letters (e.g., using `c - 'A'` for lowercase).
*   Not handling both cases: Only checking for lowercase or uppercase presence, not the dual requirement.
*   Off-by-one errors in array indexing or loop bounds.
*   Forgetting to initialize the boolean arrays.

## Complexity Analysis
- Time: O(N) - where N is the length of the input string `word`. We iterate through the string once to populate the boolean arrays, and then iterate through the 26 possible letters, which is a constant time operation.
- Space: O(1) - We use two boolean arrays of fixed size 26, which does not depend on the input string's length.

## Commented Code
```java
class Solution {
    public int numberOfSpecialChars(String word) {
        // Initialize a boolean array to track the presence of lowercase letters.
        // Index i corresponds to the i-th letter of the alphabet ('a' + i).
        boolean[] smalls = new boolean[26];
        // Initialize a boolean array to track the presence of uppercase letters.
        // Index i corresponds to the i-th letter of the alphabet ('A' + i).
        boolean[] caps = new boolean[26];

        // Iterate through each character in the input string.
        for(char c : word.toCharArray()) {
            // Check if the current character is lowercase.
            if(Character.isLowerCase(c)) {
                // If it's lowercase, mark its presence in the 'smalls' array.
                // 'c - 'a'' gives the 0-based index for the letter (e.g., 'a' - 'a' = 0).
                smalls[c - 'a'] = true;
            } else { // If it's not lowercase, it must be uppercase (based on problem constraints).
                // If it's uppercase, mark its presence in the 'caps' array.
                // 'c - 'A'' gives the 0-based index for the letter (e.g., 'A' - 'A' = 0).
                caps[c - 'A'] = true;
            }
        }

        // Initialize a counter for special characters.
        int count = 0;
        // Iterate through all possible letters of the alphabet (0 to 25).
        for(int i = 0; i < 26; i++) {
            // Check if both the lowercase and uppercase versions of the i-th letter are present.
            if(caps[i] && smalls[i]) {
                // If both are present, increment the count of special characters.
                count++;
            }
        }
        // Return the total count of special characters found.
        return count;
    }
}
```

## Interview Tips
*   Clearly explain your approach of using two boolean arrays to track lowercase and uppercase occurrences.
*   Walk through an example string to demonstrate how the arrays are populated and how the final count is derived.
*   Be prepared to discuss the time and space complexity of your solution.
*   Mention that the fixed size of the alphabet (26) makes the space complexity constant.

## Revision Checklist
- [ ] Understand the definition of a "special character".
- [ ] Implement logic to track lowercase character presence.
- [ ] Implement logic to track uppercase character presence.
- [ ] Combine presence information to count special characters.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Count Number of Distinct Integers After Reverse Operations
*   Count Number of Pairs With Absolute Difference K
*   Count Good Substrings

## Tags
`String` `Array` `Hash Set` `Boolean Array`
