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