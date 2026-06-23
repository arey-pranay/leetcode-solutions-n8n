# Number Of Zigzag Arrays I

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Prefix Sum`  
**Time:** O(N * M)  
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
This problem asks to count the number of arrays of length N where each element is within [L, R] and the elements strictly alternate between increasing and decreasing. The solution uses dynamic programming with prefix sums to optimize state transitions.

## Intuition
The core idea is to build the zigzag array from the end towards the beginning. At each position, we need to know the value of the previous element and the direction of the zigzag (increasing or decreasing). This suggests a DP state `dp[position][previous_digit_index][direction]`. However, iterating through all possible previous digits would be too slow. The key insight is that for a given `position` and `direction`, the sum of valid continuations depends on the *range* of possible previous digits, not their exact values. Prefix sums allow us to efficiently calculate these range sums.

## Algorithm
1.  **Define DP State**: `dp[pos][digit_idx][dir]` represents the number of valid zigzag arrays of length `N - pos + 1` starting from `pos`, where the element at `pos` has the value `L + digit_idx`, and `dir` indicates the required direction for the *next* element (0 for decreasing, 1 for increasing).
2.  **Base Case**: For `pos = N` (the last element), any digit is valid, so `dp[N][digit_idx][0] = 1` and `dp[N][digit_idx][1] = 1` for all `digit_idx` from 0 to `M-1` (where `M = R - L + 1`).
3.  **Iterate Backwards**: Iterate `pos` from `N-1` down to 1.
4.  **Calculate Prefix Sums**: For each `pos`, precompute prefix sums for the DP values of the next position (`pos + 1`).
    *   `pref0[d+1]` will store the sum of `dp[pos+1][i][0]` for `i` from 0 to `d`.
    *   `pref1[d+1]` will store the sum of `dp[pos+1][i][1]` for `i` from 0 to `d`.
5.  **Fill DP Table**: For each `pos` and `digit_idx` (representing the digit `L + digit_idx` at `pos`):
    *   **If `dir = 1` (next element must be smaller)**: The current digit `L + digit_idx` must be followed by a smaller digit. The number of ways is the sum of `dp[pos+1][prev_digit_idx][0]` where `L + prev_digit_idx < L + digit_idx`. This sum can be efficiently calculated using `pref0[digit_idx]`. So, `dp[pos][digit_idx][1] = pref0[digit_idx]`.
    *   **If `dir = 0` (next element must be larger)**: The current digit `L + digit_idx` must be followed by a larger digit. The number of ways is the sum of `dp[pos+1][prev_digit_idx][1]` where `L + prev_digit_idx > L + digit_idx`. This sum can be calculated using the total sum of `dp[pos+1][:][1]` (which is `pref1[M]`) minus the sum of `dp[pos+1][prev_digit_idx][1]` where `L + prev_digit_idx <= L + digit_idx`. This is `(pref1[M] - pref1[digit_idx + 1] + MOD) % MOD`. So, `dp[pos][digit_idx][0] = (pref1[M] - pref1[digit_idx + 1] + MOD) % MOD`.
6.  **Final Answer**: The total number of zigzag arrays is the sum of `dp[1][digit_idx][0]` and `dp[1][digit_idx][1]` for all `digit_idx` from 0 to `M-1`. This represents all valid arrays starting at position 1 with any valid first digit and either an increasing or decreasing trend for the second element.

## Concept to Remember
*   **Dynamic Programming**: Breaking down a problem into overlapping subproblems and storing their solutions.
*   **State Compression/Optimization**: Using prefix sums to reduce the time complexity of transitions from O(M) to O(1).
*   **Modulo Arithmetic**: Handling large numbers by taking the result modulo a given value to prevent overflow.
*   **Bottom-Up DP**: Building the solution from base cases to the final answer.

## Common Mistakes
*   **Incorrect DP State Definition**: Defining the state such that it doesn't capture all necessary information or leads to redundant calculations.
*   **Off-by-One Errors**: In loops, array indexing, or prefix sum calculations, especially when dealing with ranges and modulo operations.
*   **Not Handling Modulo Correctly**: Forgetting to apply modulo at each addition/subtraction step, leading to overflow. Especially important for subtraction: `(a - b + MOD) % MOD`.
*   **Inefficient Transitions**: Not using prefix sums, leading to a Time Limit Exceeded (TLE) error due to O(M) transitions.
*   **Incorrect Base Case**: Setting up the base case incorrectly, which propagates errors throughout the DP calculation.

## Complexity Analysis
*   **Time**: O(N * M) - We have a nested loop structure where the outer loop runs N times (for `pos`) and the inner loop runs M times (for `digit_idx`). Inside the inner loop, calculations with prefix sums are O(1). The prefix sum calculation itself takes O(M) for each `pos`. Thus, the total time is O(N * M).
*   **Space**: O(N * M) - We use a 3D DP table of size `(N+1) * M * 2`. The prefix sum arrays take O(M) space for each `pos`, but these are reused, so they don't add to the overall dominant space complexity.

## Commented Code
```java
class Solution {

    // Define the modulo constant to prevent integer overflow.
    int MOD = (int)1e9 + 7;

    public int zigZagArrays(int N, int L, int R) {

        // M is the number of possible digits in the range [L, R].
        int M = R - L + 1;
        // dp[pos][digit_idx][dir] stores the number of valid zigzag arrays
        // of length N - pos + 1, starting at 'pos', where the digit at 'pos'
        // corresponds to L + digit_idx, and 'dir' indicates the required
        // direction for the next element (0 for decreasing, 1 for increasing).
        long[][][] dp = new long[N + 1][M][2];

        // Base Case: When we are at the last position (N), any digit is valid.
        // There is 1 way to form a sequence of length 1.
        // dp[N][d][0] = 1: The sequence of length 1 starting at N with digit L+d,
        //                   and the *next* element (which doesn't exist) should be decreasing.
        // dp[N][d][1] = 1: The sequence of length 1 starting at N with digit L+d,
        //                   and the *next* element (which doesn't exist) should be increasing.
        for (int d = 0; d < M; d++) {
            dp[N][d][0] = 1;
            dp[N][d][1] = 1;
        }
        
        // Iterate backwards from the second to last position (N-1) down to the first (1).
        for (int pos = N - 1; pos >= 1; pos--) {
            // Initialize prefix sum arrays for the next DP level (pos + 1).
            // pref0[d+1] will store sum of dp[pos+1][i][0] for i from 0 to d.
            // pref1[d+1] will store sum of dp[pos+1][i][1] for i from 0 to d.
            long[] pref0 = new long[M + 1];
            long[] pref1 = new long[M + 1];
            
            // Calculate prefix sums for the DP states at the next position (pos + 1).
            for (int d = 0; d < M; d++) {
                // Accumulate sums for the 'decreasing' direction for the next element.
                pref0[d + 1] = (pref0[d] + dp[pos + 1][d][0]) % MOD;
                // Accumulate sums for the 'increasing' direction for the next element.
                pref1[d + 1] = (pref1[d] + dp[pos + 1][d][1]) % MOD;
            }

            // For the current position 'pos', iterate through all possible digits (represented by digit_idx).
            for (int digit_idx = 0; digit_idx < M; digit_idx++) {

                // Calculate dp[pos][digit_idx][1]: Number of ways to form a zigzag array
                // of length N-pos+1 starting at 'pos' with digit L+digit_idx,
                // where the *next* element (at pos+1) must be SMALLER (dir=0 for next).
                // This means the current digit L+digit_idx must be followed by a digit L+prev_digit_idx
                // such that L+prev_digit_idx < L+digit_idx.
                // The number of such previous digits is 'digit_idx'.
                // We need to sum dp[pos+1][prev_digit_idx][0] for prev_digit_idx < digit_idx.
                // This sum is exactly pref0[digit_idx].
                dp[pos][digit_idx][1] = pref0[digit_idx];

                // Calculate dp[pos][digit_idx][0]: Number of ways to form a zigzag array
                // of length N-pos+1 starting at 'pos' with digit L+digit_idx,
                // where the *next* element (at pos+1) must be LARGER (dir=1 for next).
                // This means the current digit L+digit_idx must be followed by a digit L+prev_digit_idx
                // such that L+prev_digit_idx > L+digit_idx.
                // The number of such previous digits is M - 1 - digit_idx.
                // We need to sum dp[pos+1][prev_digit_idx][1] for prev_digit_idx > digit_idx.
                // This sum can be calculated as:
                // (Total sum of dp[pos+1][:][1]) - (Sum of dp[pos+1][prev_digit_idx][1] where prev_digit_idx <= digit_idx)
                // Total sum is pref1[M].
                // Sum for prev_digit_idx <= digit_idx is pref1[digit_idx + 1].
                // We add MOD before taking modulo to handle potential negative results from subtraction.
                dp[pos][digit_idx][0] = (pref1[M] - pref1[digit_idx + 1] + MOD) % MOD;
            }
        }

        // The final answer is the sum of all valid zigzag arrays of length N.
        // These are arrays starting at position 1.
        // We sum up dp[1][digit_idx][1] (arrays starting with L+digit_idx, next element larger)
        // and dp[1][digit_idx][0] (arrays starting with L+digit_idx, next element smaller).
        long ans = 0;

        for (int digit_idx = 0; digit_idx < M; digit_idx++) {
            // Add counts for arrays where the second element is larger than the first.
            ans = (ans + dp[1][digit_idx][1]) % MOD;
            // Add counts for arrays where the second element is smaller than the first.
            ans = (ans + dp[1][digit_idx][0]) % MOD;
        }

        // Return the final answer cast to an integer.
        return (int) ans;
    }
}
```

## Interview Tips
1.  **Explain the DP State Clearly**: Articulate what `dp[pos][digit_idx][dir]` represents, especially the meaning of `dir` (is it the direction *to* this state or *from* this state?). In this solution, `dir` refers to the required direction for the *next* element.
2.  **Justify Prefix Sums**: Explain why prefix sums are necessary. Highlight that a naive O(M) transition would lead to TLE and how prefix sums optimize it to O(1).
3.  **Walk Through a Small Example**: Use a small N, L, R (e.g., N=3, L=1, R=2) to trace the DP table filling and prefix sum calculations. This demonstrates your understanding of the transitions.
4.  **Discuss Modulo Arithmetic**: Be prepared to explain why modulo is used and how to handle subtraction correctly (`(a - b + MOD) % MOD`).
5.  **Address the TLE Solution**: If asked about the recursive solution, explain why it's inefficient (exponential time complexity without memoization, or O(N*M*M) with memoization due to the inner loop) and how the iterative DP with prefix sums improves it.

## Revision Checklist
- [ ] Understand the problem statement: counting zigzag arrays with elements in a range.
- [ ] Identify the need for dynamic programming.
- [ ] Define an appropriate DP state: `dp[position][previous_digit_index][direction]`.
- [ ] Recognize the inefficiency of direct transitions and the benefit of prefix sums.
- [ ] Implement the DP with prefix sums correctly, iterating backwards.
- [ ] Handle base cases properly.
- [ ] Ensure correct modulo arithmetic for all operations.
- [ ] Calculate the final answer by summing up the relevant DP states for the first position.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Number of Ways to Build a House (LeetCode 2267) - Similar DP structure with range constraints.
*   Count Number of Teams (LeetCode 1395) - Counting increasing/decreasing subsequences, a simpler version of zigzag.
*   Arithmetic Slices (LeetCode 413) - Counting contiguous subarrays with arithmetic progression.
*   Number of Ways to Paint N x 3 Grid (LeetCode 1411) - DP on grids with color constraints.

## Tags
`Dynamic Programming` `Prefix Sum` `Array` `Modulo Arithmetic`
