# Concatenate Non Zero Digits And Multiply By Sum Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `String` `Prefix Sum`  
**Time:** O(n + m * log MOD)  
**Space:** O(n + m)

---

## Solution (java)

```java
class Solution {
    int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        int[] eff = new int[n + 1];     // number of non-zero digits till i
        int[] val = new int[n + 1];     // concatenated value (mod MOD)
        int[] total = new int[n + 1];   // sum of non-zero digits
        int[] pow10 = new int[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (int) (1L * pow10[i - 1] * 10 % MOD);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                cnt++;
                // ******** FIX 1 ********
                val[cnt] = (int) ((1L * val[cnt - 1] * 10 + digit) % MOD);
                total[cnt] = total[cnt - 1] + digit;
            }
            eff[i + 1] = cnt;
        }

        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int l = eff[q[0]];
            int r = eff[q[1] + 1];
            if (l == r) {
                ans[idx++] = 0;
                continue;
            }
            int len = r - l;
            // ******** FIX 2 ********
            long remove = (1L * val[l] * pow10[len]) % MOD;
            int x = (int) ((val[r] - remove + MOD) % MOD);
            int sum = total[r] - total[l];
            // ******** FIX 3 ********
            ans[idx++] = (int) ((1L * x * sum) % MOD);
        }
        return ans;
    }
}
```

---

---

## Quick Revision
The problem requires you to concatenate non-zero digits in a string and multiply the result by the sum of these non-zero digits, considering modulo operations.

You solve this problem using dynamic programming to efficiently calculate the concatenated value and the sum of non-zero digits for each prefix of the input string.

## Intuition
The key insight is that we can maintain an array `val` where `val[i]` represents the concatenated value of non-zero digits up to index `i`. We update this value by multiplying it with 10 (mod MOD) and adding the new digit. Additionally, we keep track of the sum of non-zero digits in a separate array `total`.

## Algorithm
1. Initialize arrays: `eff`, `val`, `total`, and `pow10`.
2. Iterate through the input string to update `val` and `total`: for each non-zero digit, multiply `val` by 10 (mod MOD) and add the new digit.
3. Update `eff` with the number of non-zero digits till each index.
4. Iterate through queries:
	* Calculate `l` and `r` using `eff`.
	* If `l == r`, return 0.
	* Otherwise, calculate the length of the subarray (`len`), remove value from `val[l]` to get `remove`, and update the result by multiplying `x` with `sum`.

## Concept to Remember
* Modular arithmetic: use modulo operations to avoid large numbers.
* Dynamic programming: break down the problem into smaller subproblems to efficiently calculate results.

## Common Mistakes
* Forgetting to handle modulo operations when updating `val`.
* Not initializing arrays properly.
* Misunderstanding the meaning of `eff` and using it incorrectly.

## Complexity Analysis
- Time: O(n + m * log MOD) where n is the length of the string and m is the number of queries. This is because we iterate through the string once to update `val`, `total`, and `eff`.
- Space: O(n + m) for the additional arrays.

## Commented Code
```java
class Solution {
    int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        // Initialize arrays
        int n = s.length();
        int[] eff = new int[n + 1];     // number of non-zero digits till i
        int[] val = new int[n + 1];     // concatenated value (mod MOD)
        int[] total = new int[n + 1];   // sum of non-zero digits
        int[] pow10 = new int[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (int) (1L * pow10[i - 1] * 10 % MOD);
        }

        // Update val and total
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                cnt++;
                // ******** FIX 1 ********
                val[cnt] = (int) ((1L * val[cnt - 1] * 10 + digit) % MOD);
                total[cnt] = total[cnt - 1] + digit;
            }
            eff[i + 1] = cnt;
        }

        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            // Calculate l and r
            int l = eff[q[0]];
            int r = eff[q[1] + 1];

            if (l == r) {
                // If the query is a single digit, return 0.
                ans[idx++] = 0;
                continue;
            }

            // Calculate len, remove value from val[l], and update result
            int len = r - l;
            long remove = (1L * val[l] * pow10[len]) % MOD;
            int x = (int) ((val[r] - remove + MOD) % MOD);
            int sum = total[r] - total[l];
            // ******** FIX 3 ********
            ans[idx++] = (int) ((1L * x * sum) % MOD);
        }
        return ans;
    }
}
```

## Interview Tips
* Pay close attention to the modulo operations and handle them correctly.
* Make sure to initialize arrays properly and use them correctly.
* Break down the problem into smaller subproblems and solve each one efficiently.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Initialize arrays properly.
- [ ] Handle modulo operations correctly.
- [ ] Break down the problem into smaller subproblems.

## Similar Problems
* LeetCode 1684 - Count the Number of Substrings With K Distinct Characters

## Tags
`Array` `Hash Map`
