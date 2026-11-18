# Minimum Number Of Changes To Make Binary String Beautiful

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minChanges(String s) {
        int i=0,n=s.length(),count=0;
        while(i<n-1){
            int j= i+1;
            if(s.charAt(i)!=s.charAt(j)) count++;
            i+=2;
        }
        return count;
    }
}
```

---

---
## Quick Revision
A binary string is beautiful if it can be partitioned into even-length blocks of identical characters (all '0's or all '1's).
We solve this by iterating through the string in non-overlapping pairs and counting how many pairs have differing characters.

## Intuition
The problem requires the string to be partitioned into even-length blocks where each block consists of identical characters. To minimize the number of changes, the most optimal greedy strategy is to check the string in pairs (blocks of length 2). Since every block must be of even length, any valid partition of the string can be thought of as a sequence of adjacent disjoint pairs (e.g., indices 0-1, 2-3, 4-5). If both characters in a pair are the same (e.g., "00" or "11"), 0 changes are needed. If they differ (e.g., "01" or "10"), exactly 1 change is needed to make them identical. We don't need to consider larger blocks because any block of length $4, 6, \dots$ can simply be broken down into valid pairs without affecting the minimum change count.

## Algorithm
1. Initialize a pointer `i` to 0, the length of the string `n` using `s.length()`, and a `count` variable to 0.
2. Loop while `i` is less than `n - 1` to ensure we always have a pair to compare.
3. For the current index `i`, check its adjacent partner at index `j = i + 1`.
4. Compare the characters `s.charAt(i)` and `s.charAt(j)`. If they are not equal, increment the `count` by 1.
5. Increment the pointer `i` by 2 to move to the next disjoint pair.
6. Return the total `count` after the loop finishes.

## Concept to Remember
- **Greedy Strategy**: Making locally optimal choices (checking adjacent pairs) leads to the globally optimal solution.
- **Problem Decomposition**: Breaking a constraint involving variable-length even blocks into fixed-length blocks of size 2.
- **String Traversal**: Stepping through indices with an increment greater than 1.

## Common Mistakes
- Trying to backtrack or use dynamic programming for arbitrary block sizes.
- Failing to realize that any even-length block can be successfully evaluated by looking solely at adjacent pairs.
- Forgetting that the string length is guaranteed to be even (per problem constraints), avoiding out-of-bounds or leftover odd character issues.
- Off-by-one errors in the loop condition (`i < n - 1` vs `i < n`).

## Complexity Analysis
- Time: O(n) - We traverse the string of length `n` once with a step size of 2, performing a constant number of operations per step.
- Space: O(1) - Only a few primitive variables (`i`, `n`, `count`) are used, requiring constant extra space.

## Commented Code
```java
class Solution {
    public int minChanges(String s) {
        // Initialize pointer i at the start of the string
        int i = 0;
        // Get the total length of the binary string
        int n = s.length();
        // Initialize change counter to zero
        int count = 0;
        // Iterate through the string stepping by 2 to check adjacent pairs
        while (i < n - 1) {
            // Define the second element of the current pair
            int j = i + 1;
            // If the two characters in the pair do not match, one change is required
            if (s.charAt(i) != s.charAt(j)) count++;
            // Move the pointer forward by 2 to the next pair
            i += 2;
        }
        // Return the minimum changes accumulated
        return count;
    }
}
```

## Interview Tips
- Clarify constraints: Ask the interviewer if the string length is always even (which is a standard constraint for this problem).
- Explain the greedy choice early: Articulate why breaking the string into pairs of 2 is sufficient and optimal.
- Walk through a dry run: Use a short example like `"1001"` to demonstrate how the pointer moves and how the count increments.

## Revision Checklist
- [ ] Understand why pairs of 2 are sufficient for even-length identical blocks.
- [ ] Implement the `while` loop with step size 2.
- [ ] Handle character comparison efficiently.
- [ ] Verify time and space complexities.

## Similar Problems
- `Check If A String Can Be Made Valid With Operations`
- `Longest Palindromic Substring`
- `Valid Parenthesis String`

## Tags
`String` `Greedy`
