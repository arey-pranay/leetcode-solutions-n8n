# Reverse Degree Of A String

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `String` `Simulation`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int reverseDegree(String s) {
        int ans = 0, i=1;
        for(char c: s.toCharArray()) ans += (i++)*(26-(c-'a'));
        return ans;
    }
}
```

---

---
## Quick Revision
Given a string, calculate its "reverse degree" by summing contributions from each character.
Each character's contribution is its position multiplied by a value based on its alphabetical distance from 'z'.

## Intuition
The problem defines a specific way to calculate a "reverse degree". The core idea is that each character contributes to the total degree based on its position in the string and its "reverse alphabetical value". The reverse alphabetical value is derived from how far it is from 'z'. For example, 'a' is furthest from 'z', and 'z' is closest. The formula `(i++) * (26 - (c - 'a'))` directly implements this: `i++` gives the 1-based position, and `26 - (c - 'a')` calculates the reverse alphabetical value (e.g., for 'a', `26 - (0)` is 26; for 'z', `26 - (25)` is 1).

## Algorithm
1. Initialize an integer variable `ans` to 0. This will store the total reverse degree.
2. Initialize an integer variable `i` to 1. This will represent the 1-based position of the characters in the string.
3. Iterate through each character `c` in the input string `s`.
4. For each character `c`:
    a. Calculate its reverse alphabetical value: `26 - (c - 'a')`. This maps 'a' to 26, 'b' to 25, ..., 'z' to 1.
    b. Multiply this value by the current position `i`.
    c. Add the result to `ans`.
    d. Increment `i` for the next character's position.
5. After iterating through all characters, return the final `ans`.

## Concept to Remember
*   **Character Arithmetic:** Understanding how to perform arithmetic operations on characters to get their positional values (e.g., `c - 'a'`).
*   **Iterative Calculation:** Accumulating a result by processing elements of a sequence one by one.
*   **1-Based Indexing:** The problem uses 1-based indexing for character positions, which needs to be handled correctly.

## Common Mistakes
*   **0-Based vs. 1-Based Indexing:** Forgetting to use 1-based indexing for the character position, leading to an incorrect calculation.
*   **Incorrect Reverse Alphabetical Value:** Miscalculating the value based on distance from 'z', perhaps by using `c - 'a'` directly or `25 - (c - 'a')`.
*   **Integer Overflow:** While unlikely for typical string lengths in competitive programming, for extremely long strings, the accumulated `ans` could potentially overflow if not using a larger data type (though `int` is usually sufficient for LeetCode constraints).
*   **Off-by-one errors in loop or calculation:** Small errors in loop bounds or the formula itself.

## Complexity Analysis
- Time: O(N) - The code iterates through the string once, where N is the length of the string. Each character processing is a constant time operation.
- Space: O(N) - In Java, `s.toCharArray()` creates a new character array of size N. If we iterate directly using `s.charAt(i)`, the space complexity would be O(1).

## Commented Code
```java
class Solution {
    public int reverseDegree(String s) {
        // Initialize the answer variable to store the total reverse degree.
        int ans = 0;
        // Initialize the position counter to 1 (1-based indexing).
        int i = 1;
        // Iterate through each character in the string by converting it to a character array.
        for (char c : s.toCharArray()) {
            // Calculate the contribution of the current character:
            // (i++) increments i after its current value is used, so it uses the current position.
            // (26 - (c - 'a')) calculates the reverse alphabetical value:
            // 'a' - 'a' = 0, so 26 - 0 = 26
            // 'b' - 'a' = 1, so 26 - 1 = 25
            // ...
            // 'z' - 'a' = 25, so 26 - 25 = 1
            ans += (i++) * (26 - (c - 'a'));
        }
        // Return the calculated total reverse degree.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify the Definition:** Before coding, ensure you fully understand the "reverse degree" definition and how each character contributes. Ask for an example if needed.
*   **Walk Through an Example:** Mentally trace the execution with a small string like "abc" to confirm your understanding of the formula and algorithm.
*   **Consider Edge Cases:** Think about empty strings, strings with only one character, or strings with only 'a's or 'z's.
*   **Explain Your Logic:** Clearly articulate your thought process, especially how you derived the formula for the character's contribution.

## Revision Checklist
- [ ] Understand the problem statement and the definition of "reverse degree".
- [ ] Implement the character arithmetic correctly (`c - 'a'`).
- [ ] Ensure 1-based indexing for character positions is used.
- [ ] Verify the formula for the reverse alphabetical value (`26 - (c - 'a')`).
- [ ] Test with example strings.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Excel Sheet Column Title
*   Excel Sheet Column Number
*   Base 7
*   Base Conversion

## Tags
`String` `Math`
