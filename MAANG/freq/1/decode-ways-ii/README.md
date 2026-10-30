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
Find the total number of ways to decode a string containing digits and wildcards ('*') mapping to letters A-Z.
Use dynamic programming to track the number of valid decodings at each step, considering single and double character combinations modulo $10^9 + 7$.

## Intuition
The "aha moment" is realizing that wildcards ('*') introduce branching multipliers for both single-digit and double-digit decodings. Instead of generating all possibilities (which would result in an exponential time complexity), we can break down the problem into subproblems: how many ways can we decode up to index $i$? By examining the last character and the combination of the last two characters (incorporating wildcards), we can add weighted contributions from $dp[i-1]$ and $dp[i-2]$ efficiently.

## Algorithm
1. Handle edge cases: If the string is empty or starts with '0', return 0 immediately.
2. Initialize a DP array `dp` of size `s.length() + 1` with `0`s, where `dp[i]` represents the number of ways to decode the prefix of length `i`.
3. Set base cases: `dp[0] = 1` (empty string has 1 way), and `dp[1] = 9` if the first character is '*', otherwise `1` (if it's a valid non-zero digit).
4. Iterate from index `i = 2` up to `s.length()` to build the DP table:
   - Calculate contributions from the single character `second` (`s[i-1]`). If it's '*', add $9 \times dp[i-1]$; if it's not '0', add $dp[i-1]$.
   - Calculate contributions from the two-character pair formed by `first` (`s[i-2]`) and `second` (`s[i-1]`):
     - If `first` is '1' or '2', handle specific multiplier cases when `second` is '*' (9 or 6 ways) or concrete digits (1 way depending on the range).
     - If `first` is '*', split into sub-cases where `second` is '*' (15 ways total: 9 for '1*' and 6 for '2*') or a concrete digit (doubled if the digit $\le 6$, otherwise single).
5. Apply modulo arithmetic (`MOD = 1_000_000_007`) at each step to prevent integer overflow.
6. Return `dp[s.length()]` cast to an integer.

## Concept to Remember
- **Dynamic Programming State Transitions:** Breaking down state dependency into immediate previous ($i-1$) and two steps prior ($i-2$).
- **Combinatorics with Wildcards:** Exhaustively analyzing all match combinations for characters, 1-digits, 2-digits, and '*' wildcards.
- **Modulo Arithmetic:** Handling extremely large output values by applying modulo operations at every intermediate addition step.

## Common Mistakes
- Forgetting to handle edge cases where the string starts with '0' (which is completely invalid).
- Incorrectly counting wildcard multipliers (e.g., confusing the 9 ways for '1*' with the 6 ways for '2*').
- Missing the overflow condition by failing to use `long` data types and applying modulo arithmetic late.
- Forgetting that '0' cannot stand alone as a single character decode, but only forms valid pairs like '10' or '20'.

## Complexity Analysis
- Time: $O(n)$ - We iterate through the string of length $n$ exactly once, performing constant-time arithmetic operations at each step.
- Space: $O(n)$ - We allocate a DP array of size $n + 1$ to store intermediate decode counts. (This can be optimized to $O(1)$ space using variables).

## Commented Code
```java
class Solution {
    // Define the modulo constant required by the problem statement to prevent integer overflow
    long MOD = 1_000_000_007L;
    
    public int numDecodings(String str) {
        // Base check: empty string or starting with '0' can never be decoded
        if(str.isEmpty() || str.charAt(0)=='0') return 0;
        
        // Convert string to character array for efficient index-based access
        char[] s = str.toCharArray();
        
        // dp[i] represents the number of ways to decode the substring from index 0 to i-1
        long[] dp = new long[s.length+1]; 
        
        // Base case: an empty prefix has exactly 1 way to be decoded
        dp[0] = 1; 
        
        // Base case for the first character: '*' can map to digits 1-9 (9 ways), others map to 1 way
        if(s[0] == '*') dp[1] = 9; 
        else dp[1] = 1; 
        
        // Iterate from the second character to the end of the string
        for(int i = 2; i <= s.length; i++){ 
            // Extract the previous character (first) and current character (second)
            char first = s[i-2], second = s[i-1]; 
            
            // 1. Calculate contribution from the single character (second)
            if(second == '*') {
                dp[i] += 9 * dp[i-1]; // '*' can be any digit from 1 to 9
            } else if(second != '0') {
                dp[i] += dp[i-1]; // Non-zero digits can be decoded on their own (1 way)
            }
            // Note: '0' contributes 0 alone because it must be paired with '1' or '2'
            
            // 2. Calculate contribution from the combined two-character pair (first + second)
            if(first == '1' || first == '2'){        
                if(second == '*') { 
                    // If first is '1', second '*' can be 0-9 (9 ways: 10-19)
                    if(first == '1') dp[i] += 9 * dp[i-2];
                    // If first is '2', second '*' can be 0-6 (6 ways: 20-26)
                    if(first == '2') dp[i] += 6 * dp[i-2];
                } else { 
                    // Standard explicit digit pairing checks
                    if(first == '1') {
                        dp[i] += dp[i-2]; // '10' through '19'
                    } else if(second <= '6') {
                        dp[i] += dp[i-2]; // '20' through '26'
                    }
                }
            } else if(first == '*') {
                if(second == '*') { 
                    // '*' paired with '*' can represent '10'-'19' (9 ways) and '20'-'26' (6 ways), total 15 ways
                    dp[i] += 15 * dp[i-2]; 
                } else {
                    // '*' paired with a concrete digit
                    if(second <= '6') {
                        // If second <= '6', the '*' could be '1' (e.g., 15) or '2' (e.g., 25), giving 2 ways
                        dp[i] += 2 * dp[i-2]; 
                    } else {
                        // If second > '6', the '*' can only be '1' (e.g., 18), giving 1 way
                        dp[i] += dp[i-2]; 
                    }
                }
            }
            
            // Apply modulo at each transition step to keep numbers within bounds
            dp[i] %= MOD;   
        }
        
        // Return total decodings for the full string cast back to an integer
        return (int) (dp[s.length] % MOD);
    }
}
```

## Interview Tips
- Clarify edge cases early: Make sure to emphasize how '0' behaves (it cannot exist independently) and how '*' acts as a multiplier.
- Walk through a small test case like `"*"` or `"1*"` during the explanation to showcase how state transitions accumulate.
- Mention space optimization: If asked how to optimize space, state that since `dp[i]` only depends on `dp[i-1]` and `dp[i-2]`, you can reduce space complexity to $O(1)$ by using rolling variables.

## Revision Checklist
- [ ] Understand why base cases are set to 9 for '*' and 1 for regular digits.
- [ ] Memorize the conditional split when `first` is '1', '2', or '*'.
- [ ] Remember the 15-way combination when both `first` and `second` are '*'.
- [ ] Always apply modulo arithmetic (`1_000_000_007`) during additions to prevent overflow.

## Similar Problems
- `Decode Ways`
- `Climbing Stairs`
- `Fibonacci Number`

## Tags
`String` `Dynamic Programming`
