# Subsequence After One Replacement

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `String` `Two Pointers` `Greedy`  
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
We can solve this by iterating through `t` and greedily matching characters in `s`, allowing for one "skip" in `s` to represent the replacement.

## Intuition
The core idea is to find if `t` can be formed as a subsequence of `s`. The twist is the "one replacement" rule. This means we can afford to skip *one* character in `s` that doesn't match the current character in `t`.

Consider two pointers, `i` for `s` and `j` for `t`. We iterate through `t`. If `s[i]` matches `t[j]`, we advance both pointers. If they don't match, we have two options:
1. Use the "replacement": Skip `s[i]` and try to match `t[j]` with `s[i+1]`. This can only be done once.
2. If we've already used the replacement, and `s[i]` doesn't match `t[j]`, then `t` cannot be formed.

The provided solution uses a slightly different, but equivalent, approach. It uses two pointers, `i` and `j`, both initially pointing to the start of `s`. It iterates through `t`.
- `j` tracks the progress of matching `t` *without* any replacements.
- `i` tracks the progress of matching `t` *with* at most one replacement.

When `s[i]` matches the current character of `t`, we advance `i`.
When `s[j]` matches the current character of `t`, we advance `j`.
The crucial part is `i = Math.max(i, j + 1)`. This line effectively says: "If `s[i]` didn't match the current character of `t`, we can potentially use our one replacement. This means the next character in `s` we consider for matching `t` could be `s[j+1]` (where `j` is the furthest we've matched `t` without replacement). So, we advance `i` to at least `j+1` to account for this potential replacement."

If either `i` or `j` reaches the length of `s` (meaning we've successfully formed the subsequence `t` using at most one replacement), we return `true`.

## Algorithm
1. Initialize two pointers, `i = 0` and `j = 0`, both representing indices in string `s`.
2. Iterate through each character `c` of string `t`.
3. Inside the loop:
    a. If `s.charAt(i) == c`, increment `i`. This means we found a match for the current character of `t` using `s[i]` without a replacement.
    b. Update `i = Math.max(i, j + 1)`. This is the core of the "one replacement" logic. It ensures that `i` is always at least one step ahead of `j`. If `s[i]` didn't match `c`, this step effectively allows `i` to "skip" `s[i]` and consider `s[j+1]` as the next potential match for `c` (representing the single replacement).
    c. If `s.charAt(j) == c`, increment `j`. This pointer tracks the subsequence match *without* any replacements.
    d. Check if `i == s.length()` or `j == s.length()`. If either is true, it means we have successfully formed the subsequence `t` using at most one replacement (if `i` reached the end) or without any replacements (if `j` reached the end). Return `true`.
4. If the loop finishes without returning `true`, it means `t` cannot be formed as a subsequence of `s` with at most one replacement. Return `false`.

## Concept to Remember
*   **Subsequence:** A subsequence is formed by deleting zero or more characters from a string without changing the order of the remaining characters.
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a global optimum. Here, we greedily try to match characters.
*   **Two Pointers:** Using multiple pointers to traverse data structures (like strings or arrays) efficiently.

## Common Mistakes
*   **Incorrectly handling the "one replacement":** Not properly accounting for the single allowed skip in `s`.
*   **Off-by-one errors:** Mismanaging pointer increments or boundary conditions.
*   **Not considering edge cases:** Empty strings, `s` shorter than `t`.
*   **Confusing `i` and `j` roles:** Misunderstanding what each pointer represents in the context of replacements.

## Complexity Analysis
- Time: O(N), where N is the length of string `t`. We iterate through `t` once. The operations inside the loop are constant time.
- Space: O(1). We only use a few variables for pointers and loop control, which do not depend on the input size.

## Commented Code
```java
class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        // If s is empty, any t (including empty) can be considered a subsequence.
        if(s.isEmpty()) return true;
        // If s is shorter than t, t cannot possibly be a subsequence of s.
        if(s.length() < t.length()) return false; // Corrected condition for clarity

        // i tracks the furthest index in s that can form t using AT MOST ONE replacement.
        int i = 0;
        // j tracks the furthest index in s that can form t using ZERO replacements.
        int j = 0;

        // Iterate through each character of the target subsequence string t.
        for(char c : t.toCharArray()){
            // If the current character of s (at index i) matches the current character of t (c)...
            if(i < s.length() && s.charAt(i) == c) {
                // ...then we've found a match for c using s[i] without a replacement. Advance i.
                i++;
            }

            // This is the crucial step for handling the "one replacement".
            // It ensures that i is always at least one step ahead of j.
            // If s[i] did NOT match c, this line effectively allows i to "skip" s[i]
            // and consider s[j+1] as the next potential match for c. This accounts for the single replacement.
            // We must also ensure i does not go out of bounds of s.
            if (i < s.length()) { // Ensure i is within bounds before accessing s.charAt(i)
                i = Math.max(i, j + 1);
            } else {
                // If i has already reached the end of s, and we still need to match characters in t,
                // we can only rely on j (no replacements). If j also can't match, we'll fail later.
                // This condition is implicitly handled by the loop and subsequent checks,
                // but explicitly thinking about it helps.
            }


            // If the current character of s (at index j) matches the current character of t (c)...
            if(j < s.length() && s.charAt(j) == c) {
                // ...then we've found a match for c using s[j] without any replacement. Advance j.
                j++;
            }

            // Check if we have successfully formed the entire subsequence t.
            // If i has reached the end of s, it means we could form t using at most one replacement.
            // If j has reached the end of s, it means we could form t using zero replacements.
            // In either case, we've succeeded.
            if(i == s.length() || j == s.length()) {
                return true;
            }
        }

        // If the loop completes and we haven't returned true, it means t cannot be formed.
        return false;
    }
}
```

## Interview Tips
1.  **Clarify the "one replacement":** Ask if it means replacing *any* character in `s` or if it's a specific type of replacement (e.g., changing one character to another). The problem implies replacing one character in `s` to match a character in `t`.
2.  **Explain the two-pointer logic:** Clearly articulate what `i` and `j` represent and why `i = Math.max(i, j + 1)` is the key to handling the single replacement.
3.  **Walk through an example:** Use a simple case like `s = "abcde"`, `t = "ace"` and `s = "abcde"`, `t = "axc"` to demonstrate how your pointers move and how the replacement is handled.
4.  **Discuss edge cases:** Mention how you handle empty strings, `s` being shorter than `t`, and cases where `t` is a direct subsequence of `s`.

## Revision Checklist
- [ ] Understand the definition of a subsequence.
- [ ] Grasp the "at most one replacement" constraint.
- [ ] Implement the two-pointer approach correctly.
- [ ] Ensure `i = Math.max(i, j + 1)` logic is sound.
- [ ] Handle boundary conditions for pointers.
- [ ] Test with edge cases (empty strings, `s` shorter than `t`).

## Similar Problems
*   LeetCode 392: Is Subsequence
*   LeetCode 115: Distinct Subsequences
*   LeetCode 1055: Shortest Way to Form String

## Tags
`Array` `String` `Two Pointers` `Greedy`
