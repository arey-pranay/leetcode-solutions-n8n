# Number Of Zigzag Arrays I

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Prefix Sum`  
**Time:** O(N * M^2)  
**Space:** O(N * M)

---

## Solution (java)

```java
class Solution {

    int MOD = (int)1e9 + 7;

    public int zigZagArrays(int N, int L, int R) {

        int M = R - L + 1;
        long[][][] dp = new long[N + 1][M][2];

        for (int d = 0; d < M; d++) {
            dp[N][d][0] = 1;
            dp[N][d][1] = 1;
        }
        
        for (int pos = N - 1; pos >= 1; pos--) {
            long[] pref0 = new long[M + 1];
            long[] pref1 = new long[M + 1];
            // prefix sums of next level
            for (int d = 0; d < M; d++) {
                pref0[d + 1] = (pref0[d] + dp[pos + 1][d][0]) % MOD;
                pref1[d + 1] = (pref1[d] + dp[pos + 1][d][1]) % MOD;
            }

            for (int digit = 0; digit < M; digit++) {

                // Original:
                // if(dir==1)
                dp[pos][digit][1] = pref0[digit];

                // Original:
                // if(dir==0)
                //   sum func(largerDigit,1,pos+1)

                dp[pos][digit][0] = (pref1[M] - pref1[digit + 1] + MOD) % MOD;
            }
        }

        long ans = 0;

        // Same as:
        // for(i=L;i<=R;i++)
        //    func(i,1,1) + func(i,0,1)

        for (int digit = 0; digit < M; digit++) {
            ans = (ans + dp[1][digit][1]) % MOD;
            ans = (ans + dp[1][digit][0]) % MOD;
        }

        return (int) ans;
    }
}

// TLE
// class Solution {
//     int N,L,R;
//     int[][][] memo;
//     int MOD =(int) 1e9 + 7;
//     public int zigZagArrays(int n, int l, int r) {
//         N=n;
//         L=l;
//         R=r;
//         memo = new int[R-L+1][2][N];
//         for(int[][] temp1 : memo) for(int[] temp : temp1) Arrays.fill(temp,-1);
//         int ans = 0;
//         for(int i=l;i<=r;i++){
//             int a = func(i,1,1); a%=MOD;
//             int b = func(i,0,1); b%= MOD;
//             ans += (a+b)%MOD;
//             ans %= MOD;
//         }
//         return (int)ans%MOD;
//     }   
//     public int func(int digit, int dir, int curr){        
//         if(curr == N) return 1;
//         if(memo[digit-L][dir][curr] != -1) return memo[digit-L][dir][curr];
//         long ans =0;
//         if(dir==1) for(int i=L;i<digit;i++){ans += (func(i,0,curr+1)%MOD); ans %= MOD;}
//         else for(int i=digit+1;i<=R;i++){ans += (func(i,1,curr+1)%MOD); ans%=MOD;}
//         return memo[digit-L][dir][curr] = (int)ans;
//     }
// }
```

---

---
## Quick Revision
This problem asks to count the number of arrays of length N where elements are within a range [L, R] and form a zigzag pattern. The solution uses dynamic programming with prefix sums to optimize calculations.

## Intuition
The core idea is to build the zigzag arrays from the end to the beginning. At each position, we need to know the previous digit and the direction of the zigzag (increasing or decreasing). This suggests a DP state that includes the current position, the previous digit, and the direction. However, the previous digit can be any value from L to R, leading to a large state space. The optimization comes from realizing that we only care about the *relative* order of the current digit with respect to the previous one. By iterating from the end and using prefix sums, we can efficiently sum up valid continuations.

## Algorithm
1.  **Define DP State**: `dp[pos][digit_idx][dir]` will store the number of valid zigzag array suffixes of length `N - pos + 1`, starting at `pos`, where the digit at `pos - 1` (conceptually) was `L + digit_idx`, and `dir` indicates the required direction for the next element (0 for decreasing, 1 for increasing).
2.  **Base Case**: For `pos = N` (the last element), any digit can be the last element, so `dp[N][d][0] = 1` and `dp[N][d][1] = 1` for all valid `d` (0 to M-1). This means there's one way to complete an array of length 1.
3.  **Iterate Backwards**: Iterate `pos` from `N-1` down to 1.
4.  **Calculate Prefix Sums**: For each `pos`, precompute prefix sums for the next DP state (`dp[pos + 1]`). `pref0[d]` will store the sum of `dp[pos + 1][i][0]` for `i` from 0 to `d-1`. `pref1[d]` will store the sum of `dp[pos + 1][i][1]` for `i` from 0 to `d-1`. These prefix sums help in quickly calculating sums over ranges.
5.  **Fill DP Table**: For each `pos` and each possible current digit `digit` (represented by `digit_idx` from 0 to M-1):
    *   **If `dir = 1` (increasing)**: The previous digit must be *smaller* than the current digit. The number of ways is the sum of `dp[pos + 1][prev_digit_idx][0]` for all `prev_digit_idx < digit_idx`. This sum can be efficiently calculated using `pref0[digit_idx]`. So, `dp[pos][digit_idx][1] = pref0[digit_idx]`.
    *   **If `dir = 0` (decreasing)**: The previous digit must be *larger* than the current digit. The number of ways is the sum of `dp[pos + 1][prev_digit_idx][1]` for all `prev_digit_idx > digit_idx`. This sum can be calculated using the total sum of `dp[pos + 1][...][1]` minus the sum of `dp[pos + 1][i][1]` for `i <= digit_idx`. This is `(pref1[M] - pref1[digit_idx + 1] + MOD) % MOD`. So, `dp[pos][digit_idx][0] = (pref1[M] - pref1[digit_idx + 1] + MOD) % MOD`.
6.  **Final Answer**: The total number of zigzag arrays of length N is the sum of `dp[1][digit_idx][0]` and `dp[1][digit_idx][1]` for all `digit_idx` from 0 to M-1. This represents all possible starting digits and directions for the first element.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a problem into overlapping subproblems and storing their solutions.
*   **State Compression/Optimization**: Reducing the DP state space by observing that only relative order matters, not absolute values.
*   **Prefix Sums**: Efficiently calculating sums over ranges in O(1) time after O(M) precomputation.
*   **Modulo Arithmetic**: Handling large numbers by taking the remainder modulo a given number (MOD).

## Common Mistakes
*   **Incorrect DP State**: Defining a DP state that is too large or doesn't capture all necessary information (e.g., including the absolute previous digit instead of its index).
*   **Off-by-One Errors**: Mistakes in loop bounds, array indexing, or prefix sum calculations, especially when dealing with ranges and modulo operations.
*   **Forgetting Modulo**: Not applying the modulo operation at each addition step, leading to integer overflow.
*   **Inefficient Transitions**: Not using prefix sums, leading to O(M) transitions within the DP, resulting in TLE.
*   **Base Case Misunderstanding**: Incorrectly defining the base case for `pos = N`.

## Complexity Analysis
- Time: O(N * M^2) without prefix sums, O(N * M) with prefix sums. The outer loops are `pos` (N times) and `digit` (M times). Inside, prefix sum calculation is O(M), and DP transition is O(1) with prefix sums.
- Space: O(N * M) for the DP table.

## Commented Code
```java
class Solution {

    // Define the modulo constant to prevent integer overflow
    int MOD = (int)1e9 + 7;

    public int zigZagArrays(int N, int L, int R) {

        // M is the number of distinct digits available in the range [L, R]
        int M = R - L + 1;
        // dp[pos][digit_idx][dir] stores the number of valid zigzag suffixes
        // starting from 'pos', where the previous digit was (L + digit_idx),
        // and 'dir' indicates the required direction for the next element (0 for decreasing, 1 for increasing).
        // pos: 1 to N
        // digit_idx: 0 to M-1 (representing digits L to R)
        // dir: 0 (decreasing) or 1 (increasing)
        long[][][] dp = new long[N + 1][M][2];

        // Base Case: For the last position (pos = N), any digit can be the last element.
        // There is 1 way to complete an array of length 1, regardless of the required direction.
        for (int d = 0; d < M; d++) {
            dp[N][d][0] = 1; // If the last element needs to be smaller than the previous (conceptually)
            dp[N][d][1] = 1; // If the last element needs to be larger than the previous (conceptually)
        }
        
        // Iterate backwards from the second to last position (N-1) down to the first position (1).
        for (int pos = N - 1; pos >= 1; pos--) {
            // Initialize prefix sum arrays for the next DP state (pos + 1).
            // pref0[d] will store the sum of dp[pos + 1][i][0] for i from 0 to d-1.
            // pref1[d] will store the sum of dp[pos + 1][i][1] for i from 0 to d-1.
            long[] pref0 = new long[M + 1];
            long[] pref1 = new long[M + 1];
            
            // Calculate prefix sums for dp[pos + 1]
            for (int d = 0; d < M; d++) {
                // Accumulate sums for decreasing sequences from the next level
                pref0[d + 1] = (pref0[d] + dp[pos + 1][d][0]) % MOD;
                // Accumulate sums for increasing sequences from the next level
                pref1[d + 1] = (pref1[d] + dp[pos + 1][d][1]) % MOD;
            }

            // For each possible digit at the current position 'pos'
            for (int digit = 0; digit < M; digit++) {

                // Calculate dp[pos][digit][1]: Number of ways to form a zigzag suffix
                // where the current digit is (L + digit), and the next digit must be LARGER.
                // This means the previous digit (at pos+1) must have been smaller than (L + digit).
                // The number of such valid previous digits is the sum of dp[pos + 1][prev_digit_idx][0]
                // for all prev_digit_idx < digit. This is exactly pref0[digit].
                dp[pos][digit][1] = pref0[digit];

                // Calculate dp[pos][digit][0]: Number of ways to form a zigzag suffix
                // where the current digit is (L + digit), and the next digit must be SMALLER.
                // This means the previous digit (at pos+1) must have been larger than (L + digit).
                // The number of such valid previous digits is the sum of dp[pos + 1][prev_digit_idx][1]
                // for all prev_digit_idx > digit.
                // This sum is (Total sum of dp[pos + 1][...][1]) - (Sum of dp[pos + 1][i][1] for i <= digit).
                // Total sum is pref1[M]. Sum for i <= digit is pref1[digit + 1].
                // We add MOD before taking modulo to handle potential negative results from subtraction.
                dp[pos][digit][0] = (pref1[M] - pref1[digit + 1] + MOD) % MOD;
            }
        }

        // Calculate the final answer.
        // The total number of zigzag arrays of length N is the sum of all valid arrays starting at position 1.
        // For each possible first digit (represented by 'digit' from 0 to M-1),
        // we sum up the ways where the second element is larger (dp[1][digit][1])
        // and the ways where the second element is smaller (dp[1][digit][0]).
        long ans = 0;
        for (int digit = 0; digit < M; digit++) {
            ans = (ans + dp[1][digit][1]) % MOD; // Add ways where the sequence starts increasing
            ans = (ans + dp[1][digit][0]) % MOD; // Add ways where the sequence starts decreasing
        }

        // Return the final answer cast to int.
        return (int) ans;
    }
}
```

## Interview Tips
*   **Explain the DP State Clearly**: Be able to articulate what `dp[pos][digit_idx][dir]` represents and why it's structured that way.
*   **Justify the Prefix Sum Optimization**: Explain why prefix sums are necessary to avoid TLE and how they reduce the time complexity of transitions.
*   **Handle Modulo Carefully**: Emphasize that modulo operations must be applied at every addition and subtraction to prevent overflow and maintain correctness.
*   **Walk Through an Example**: If time permits, walk through a small example (e.g., N=3, L=1, R=2) to illustrate the DP transitions and prefix sum calculations.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the overlapping subproblems and optimal substructure.
- [ ] Define the DP state correctly.
- [ ] Implement the base case accurately.
- [ ] Develop the recurrence relation for DP transitions.
- [ ] Implement prefix sums for efficient range queries.
- [ ] Handle modulo arithmetic correctly at all steps.
- [ ] Calculate the final answer from the DP table.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Number of Ways to Make a Product
*   Count Number of Teams
*   Number of Ways to Paint N Houses
*   Longest ZigZag Path in a Binary Tree

## Tags
`Dynamic Programming` `Array` `Prefix Sum`
