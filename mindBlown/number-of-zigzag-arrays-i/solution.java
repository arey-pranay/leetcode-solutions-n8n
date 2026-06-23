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