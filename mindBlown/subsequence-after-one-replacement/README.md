# Subsequence After One Replacement

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Two Pointers` `Greedy`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        if(s.isEmpty()) return true;
        if(s.length()>t.length()) return false;
        int i=0,j=0;
        // i ko humesha j+1 rkh skte hai 
        // kyuki j wo position hai jahan tk bina replacement ke subsequence bn rhi hai
        for(char c : t.toCharArray()){
            if(s.charAt(i)==c) i++; // 
            i = Math.max(i,j+1); // // i ko aage bdha diya, mtlb replacement use krliya
            if(s.charAt(j)==c) j++; // this guarantees ki hum j ko dirf match krne pe increment kr rhe hai (no replacements)
            if(i==s.length() || j==s.length()) return true;
        }
        return false;
    }
}
```

---

---
## Quick Revision
Given two strings `s` and `t`, determine if `t` can be a subsequence of `s` after replacing at most one character in `s`.
We can solve this by iterating through `t` and greedily matching characters in `s`, allowing for one "skip" or replacement in `s`.

## Intuition
The core idea is to find if `t` can be formed as a subsequence of `s`. The twist is that we can use at most one character replacement in `s`. This means that if we encounter a character in `t` that doesn't match the current character in `s`, we can potentially "use up" our one replacement to make it match. However, we need to be careful not to misuse this replacement.

The key insight is to maintain two pointers, one for `s` and one for `t`. Let's call them `i` for `s` and `j` for `t`.
If `s[i]` matches `t[j]`, we advance both pointers.
If `s[i]` does *not* match `t[j]`, we have two options:
1. Use our replacement: If we haven't used our replacement yet, we can consider `s[i]` as a match for `t[j]` and advance `j`. We also need to advance `i` to move past this character in `s`. Crucially, we must mark that we've used our replacement.
2. Don't use replacement: If we've already used our replacement, or if we choose not to use it for this mismatch, we simply advance `i` to look for a match for `t[j]` further in `s`.

The provided solution uses a slightly different but equivalent approach. It uses two pointers, `i` and `j`, both initially pointing to the start of `s`. `j` tracks the progress of matching `t` *without* any replacements. `i` tracks the progress of matching `t` *with* at most one replacement.

When iterating through `t` (let's say with character `c`):
- If `s[i]` matches `c`, we advance `i`. This means we found a match for the current character of `t` using `s[i]` without replacement.
- The line `i = Math.max(i, j + 1);` is the clever part. It ensures that `i` is always at least one step ahead of `j`. If `s[i]` didn't match `c`, this line effectively "uses up" the replacement by advancing `i` to `j+1`. This means we are considering `s[j+1]` (or a later character) as a potential match for `t[j]`, effectively skipping `s[j]` and using a replacement.
- If `s[j]` matches `c`, we advance `j`. This is the pointer that tracks matches *without* replacements.
- If either `i` or `j` reaches the end of `s` (meaning we've successfully formed the subsequence `t` with at most one replacement), we return `true`.

The logic `i = Math.max(i, j + 1);` is key. It ensures that `i` (the pointer for potential replacement usage) is always at least one step ahead of `j` (the pointer for strict subsequence matching). If `s[i]` doesn't match `c`, `i` is advanced to `j+1`, effectively using the "one replacement" to skip `s[j]` and consider `s[j+1]` as a match for `t[j]`. If `s[i]` *does* match `c`, `i` is advanced, and `Math.max(i, j+1)` ensures `i` stays ahead or equal to `j+1`.

## Algorithm
1. Initialize two pointers, `i = 0` and `j = 0`, both pointing to the beginning of string `s`.
2. Iterate through each character `c` of string `t`.
3. Inside the loop:
    a. If `i` is within the bounds of `s` and `s.charAt(i)` is equal to `c`, increment `i`. This signifies a direct match without replacement.
    b. Update `i` using `i = Math.max(i, j + 1);`. This is the crucial step for handling the one replacement. If `s.charAt(i)` did not match `c` in the previous step, this line effectively advances `i` to `j + 1`, meaning we are using our "one replacement" to skip `s.charAt(j)` and consider `s.charAt(j+1)` (or a later character) as a potential match for `t.charAt(j)`. If `s.charAt(i)` *did* match `c`, this line ensures `i` remains at least one step ahead of `j+1`.
    c. If `j` is within the bounds of `s` and `s.charAt(j)` is equal to `c`, increment `j`. This pointer tracks matches made *without* any replacements.
    d. Check if either `i` or `j` has reached the length of `s`. If `i == s.length()` or `j == s.length()`, it means we have successfully formed the subsequence `t` using at most one replacement. Return `true`.
4. If the loop finishes without returning `true`, it means `t` cannot be formed as a subsequence of `s` with at most one replacement. Return `false`.

## Concept to Remember
*   **Subsequence:** A subsequence is formed by deleting zero or more characters from a string without changing the order of the remaining characters.
*   **Greedy Approach:** The algorithm makes locally optimal choices (matching characters whenever possible) with the hope of finding a global optimum (forming the subsequence).
*   **Two-Pointer Technique:** Using multiple pointers to traverse and compare elements in one or more sequences.
*   **Handling Constraints:** Carefully managing the "at most one replacement" constraint is key to the solution's correctness.

## Common Mistakes
*   **Incorrectly handling the "one replacement" logic:** Misinterpreting when the replacement is used or how it affects pointer movement.
*   **Off-by-one errors:** Incorrectly checking boundary conditions for pointers `i` and `j` against the lengths of `s` and `t`.
*   **Not considering the order of operations:** The order of `i++`, `i = Math.max(i, j+1)`, and `j++` is critical.
*   **Forgetting edge cases:** Not handling empty strings or cases where `s` is shorter than `t`.
*   **Over-complicating the state:** Trying to track the number of replacements explicitly when a clever pointer manipulation can achieve the same result.

## Complexity Analysis
- Time: O(N), where N is the length of string `t`. The algorithm iterates through string `t` once. String `s` is accessed via pointers, but each character in `s` is effectively considered at most a constant number of times.
- Space: O(1). The algorithm uses a constant amount of extra space for pointers and variables, regardless of the input string sizes.

## Commented Code
```java
class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        // If the target subsequence 't' is empty, it can always be formed.
        if(t.isEmpty()) return true;
        // If the source string 's' is shorter than 't', 't' cannot be a subsequence.
        if(s.length() < t.length()) return false; // Corrected condition for clarity

        // 'i' is the pointer for string 's' that considers the possibility of using one replacement.
        int i = 0;
        // 'j' is the pointer for string 't' that tracks matches made WITHOUT any replacements.
        int j = 0;

        // Iterate through each character 'c' of the target subsequence 't'.
        for(char c : t.toCharArray()){
            // If the current character in 's' (at pointer 'i') matches the current character 'c' from 't',
            // advance pointer 'i'. This is a direct match without using a replacement.
            if(i < s.length() && s.charAt(i) == c) {
                i++;
            }

            // This is the core logic for handling the "at most one replacement".
            // 'i' must always be at least one step ahead of 'j+1'.
            // If s.charAt(i) did NOT match 'c' in the previous step, this line effectively advances 'i' to 'j+1'.
            // This means we are "using" our one replacement to skip s.charAt(j) and consider s.charAt(j+1)
            // (or a later character) as a potential match for t.charAt(j).
            // If s.charAt(i) DID match 'c', this line ensures 'i' stays ahead or equal to 'j+1'.
            // We need to ensure 'i' is within bounds before accessing s.charAt(i) in the next iteration.
            i = Math.max(i, j + 1);

            // If the current character in 's' (at pointer 'j') matches the current character 'c' from 't',
            // advance pointer 'j'. This pointer strictly tracks matches made without any replacements.
            // This check is done AFTER potentially advancing 'i' with a replacement.
            if(j < s.length() && s.charAt(j) == c) {
                j++;
            }

            // If pointer 'i' has reached the end of 's', it means we have successfully formed 't'
            // using at most one replacement.
            // OR if pointer 'j' has reached the end of 's', it means we have successfully formed 't'
            // using ZERO replacements (a pure subsequence).
            // In either case, we've found a valid subsequence.
            if(i == s.length() || j == s.length()) {
                return true;
            }
        }

        // If the loop completes and we haven't returned true, it means 't' cannot be formed
        // as a subsequence of 's' with at most one replacement.
        return false;
    }
}
```

## Interview Tips
1.  **Clarify the "one replacement" rule:** Ensure you understand if it means replacing one character in `s` to match *any* character in `t`, or if it's about skipping one character in `s`. The provided solution interprets it as being able to skip one character in `s` to make a match.
2.  **Explain the two-pointer strategy:** Clearly articulate the roles of `i` and `j`. `j` tracks the strict subsequence progress, while `i` tracks progress with the allowance of one "skip" (replacement).
3.  **Walk through the `Math.max(i, j + 1)` line:** This is the most critical and potentially confusing part. Explain how it effectively "uses up" the replacement by ensuring `i` is always at least one step ahead of `j`, allowing `s[j+1]` to be considered for `t[j]` if `s[j]` didn't match.
4.  **Consider edge cases:** Discuss what happens with empty strings, `s` shorter than `t`, or when `t` is a direct subsequence of `s` (zero replacements).

## Revision Checklist
- [ ] Understand the definition of a subsequence.
- [ ] Grasp the "at most one replacement" constraint.
- [ ] Implement the two-pointer approach correctly.
- [ ] Analyze the logic of `i = Math.max(i, j + 1)`.
- [ ] Handle edge cases like empty strings and length mismatches.
- [ ] Verify time and space complexity.

## Similar Problems
*   1967. Number of Strings That Appear as Substrings in Word
*   392. Is Subsequence
*   1143. Longest Common Subsequence
*   1055. Shortest Way to Form String

## Tags
`String` `Two Pointers` `Greedy`
