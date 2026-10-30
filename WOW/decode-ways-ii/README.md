# Decode Ways Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    long MOD = 1_000_000_007L;
    public int numDecodings(String str) {
        if(str.isEmpty() || str.charAt(0)=='0') return 0;
        char[] s = str.toCharArray();
        long[] dp = new long[s.length+1]; // dp[i] means the number of ways to decode substring s(0,i) 
        dp[0] = 1; 
        
        if(s[0] == '*') dp[1] = 9; // * can become 9 possible digits
        else dp[1] = 1; // every other single digit can only be interpreted in one way
        
        for(int i =2; i<=s.length; i++){ // now let's build for second character onwards
            char first = s[i-2], second = s[i-1]; // we need to access thee possibilites of the previous 2 charactersnow
            
            // just considering the contribution of previous character
            if(second == '*') dp[i] += 9*dp[i-1];
            else if(second != '0') dp[i] += dp[i-1]; // because 0 cannot contribute alone, no character corresponds to it, it is either 10 or 20, always in a pair
            
            // now considering if the previous two could have been merged and how many values would they contribute
            if(first == '1' || first == '2'){        
                if(second == '*') { // 1* means multiplier of 9 and 2* means 6 (20 21 22 23 24 25)
                    if(first == '1') dp[i] += 9*dp[i-2];
                    if(first == '2') dp[i] += 6*dp[i-2];
                } else { // this is for normal cases, we have the same interpretations, 10 .. 19, and 20 .. 26
                    if(first=='1') dp[i] += dp[i-2];
                    else if(second <= '6') dp[i] += dp[i-2];
                }
            } else if(first=='*') {
                if(second=='*') dp[i] += 15*dp[i-2]; // 9+6. 9 for 1* and 6 for 2*, the leftmost star can only contribute by being either 1 or 2
                else {
                    if(second<='6') dp[i] += 2*dp[i-2]; // *5 can mean 10 to 15 and also 20 to 25
                    else dp[i] += dp[i-2]; // then only 17 18 19 etc, so same as before, not doubled nhere
                }
            }
            
            dp[i] %= MOD;   
        }
        return (int) (dp[s.length] % MOD);
    }
}
// 1*2**3*

// 1
// * ->9
// 1* -> 18
// *2 -> 18
// 2 -> 1

//             12*3
//     12*(3)     -     12(*3)
// 12(*)(3) 1(2*)(3)  -  1(2)(*3)   (12)(*3)
// (1)(2)(*)(3)
```

---

---
## Quick Revision
Find the total number of ways to decode a message containing digits and wildcards ('*').
Solve using dynamic programming by breaking down the combinations for single digits and pairs while tracking modulo arithmetic.

## Intuition
The core challenge in Decode Ways II is handling the wildcard character `'*'`, which can represent any digit from `1` to `9`. Instead of exploring every possible expansion (which would result in exponential time), we can leverage dynamic programming. At each index, the number of ways to decode up to that point depends either on the single character at the current position (mapping to `1-9` or `*`) or the combination of the current and previous characters (mapping to valid two-digit numbers like `10-26`, with various wildcard permutations). By breaking down the combinatorial possibilities for each case (e.g., `'*'` alone equals 9 ways, `'*'` paired with a specific second digit can double certain outcomes), we achieve an efficient $O(n)$ solution.

## Algorithm
1. Check if the input string is empty or starts with `'0'`; if so, return `0` because it cannot be decoded.
2. Initialize a DP array `dp` of size `n + 1` where `dp[i]` represents the number of ways to decode the prefix of length `i`.
3. Base cases: Set `dp[0] = 1`. For `dp[1]`, if the first character is `'*'`, set it to `9`; otherwise, set it to `1`.
4. Iterate from `i = 2` to `n` to compute the ways to decode up to index `i - 1`:
   - Calculate contributions from the single character `s[i-1]` (if it is `*`, multiply `dp[i-1]` by 9; if it is not `'0'`, add `dp[i-1]`).
   - Calculate contributions from the two-character combination `s[i-2]` and `s[i-1]` considering fixed digits (`'1'` or `'2'`) and wildcards (`'*'`) interacting with each other.
   - Apply modulo `1_000_000_007` at each step to prevent integer overflow.
5. Return the final result stored in `dp[n]` casted to an integer.

## Concept to Remember
- **Dynamic Programming State Transitions:** Breaking a combinatorics problem into subproblems dependent only on the last 1-2 elements.
- **Wildcard Combinatorics:** Exhaustively accounting for disjoint branches when `'*'` can represent multiple concrete values.
- **Modulo Arithmetic:** Applying `MOD` at every intermediate addition step to avoid overflow with large products.

## Common Mistakes
- Forgetting to handle the base case where a string starts with `'0'`, which instantly yields zero valid decodings.
- Missing edge cases where two wildcards interact (e.g., `"*"` combined with `"*"` allows 15 valid two-digit combinations: 9 from `1*` and 6 from `2*`).
- Not applying the modulo operator at every addition step, leading to integer overflow or incorrect results.
- Off-by-one errors when mapping string indices (`s[i-1]`, `s[i-2]`) to the DP array indices (`dp[i]`).

## Complexity Analysis
- Time: $O(n)$ - We perform a single linear scan of length $n$ with constant-time conditional checks at each step.
- Space: $O(n)$ - We use a DP array of size $n+1$ (which can be optimized to $O(1)$ space since we only look back two states).

## Commented Code
```java
class Solution {
    long MOD = 1_000_000_007L; // Define the modulo constant to prevent integer overflow.
    public int numDecodings(String str) {
        if(str.isEmpty() || str.charAt(0)=='0') return 0; // Quick exit: empty strings or leading zeros cannot be decoded.
        char[] s = str.toCharArray(); // Convert string to character array for faster lookups.
        long[] dp = new long[s.length+1]; // dp[i] means the number of ways to decode substring s(0,i).
        dp[0] = 1; // Base case: 1 way to decode an empty prefix.
        
        if(s[0] == '*') dp[1] = 9; // If the first char is '*', it can map to digits 1-9 (9 possibilities).
        else dp[1] = 1; // Every other valid single digit can only be interpreted in one way.
        
        for(int i = 2; i <= s.length; i++){ // Iterate from the second character to the end of the string.
            char first = s[i-2], second = s[i-1]; // Identify the previous two characters for pattern matching.
            
            // Case 1: Consider the contribution of the single current character alone.
            if(second == '*') dp[i] += 9 * dp[i-1]; // '*' as a single char contributes 9 options (1-9).
            else if(second != '0') dp[i] += dp[i-1]; // Non-zero digits contribute 1 option ('0' cannot stand alone).
            
            // Case 2: Consider the contribution of the previous two characters merged together.
            if(first == '1' || first == '2'){        
                if(second == '*') { // If first is fixed ('1' or '2') and second is '*'
                    if(first == '1') dp[i] += 9 * dp[i-2]; // '1*' can be 11-19 (9 options).
                    if(first == '2') dp[i] += 6 * dp[i-2]; // '2*' can be 21-26 (6 options).
                } else { // Both characters are fixed digits.
                    if(first == '1') dp[i] += dp[i-2]; // '10' through '19' (1 option per valid pair).
                    else if(second <= '6') dp[i] += dp[i-2]; // '20' through '26' (1 option if second <= '6').
                }
            } else if(first == '*') { // If the first character is a wildcard.
                if(second == '*') dp[i] += 15 * dp[i-2]; // '**' can be formed by 1* (9 options) + 2* (6 options) = 15.
                else {
                    if(second <= '6') dp[i] += 2 * dp[i-2]; // '*0' to '*6' can match both 1X and 2X (2 options).
                    else dp[i] += dp[i-2]; // '*7' to '*9' can only match 1X (1 option).
                }
            }
            
            dp[i] %= MOD; // Apply modulo immediately to keep numbers within safe bounds.
        }
        return (int) (dp[s.length] % MOD); // Cast final result back to integer after safe modulo computation.
    }
}
```

## Interview Tips
- Clarify assumptions about constraints, such as the maximum length of the string and whether leading zeros are invalid (they are).
- Walk through a small test case like `"1*"` to demonstrate how you break down the combinations of single digits and pairs.
- Mention space optimization proactively: note that since `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`, you could reduce space complexity to $O(1)$ using rolling variables.

## Revision Checklist
- [ ] Understand why base case `dp[0] = 1` is necessary for combinatorial multiplication.
- [ ] Memorize the exact multipliers for wildcards (`*` alone = 9, `1*` = 9, `2*` = 6, `**` = 15).
- [ ] Remember to apply `MOD` at every addition step.
- [ ] Practice the space-optimized version using three rolling variables.

## Similar Problems
- Decode Ways
- Climbing Stairs
- Integer Break

## Tags
`String` `Dynamic Programming`
