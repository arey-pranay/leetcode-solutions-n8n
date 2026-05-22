class Solution {
    Integer[][] memo;
    int MOD = (int)(1e9 + 7);
    public int numRollsToTarget(int n, int k, int target) {
        memo = new Integer[n+1][target+1];
       return func(n,k,target) % MOD;
    }
    public int func(int n, int k, int target){
        if(target < 0) return 0;
        if(n==1) return k >= target & target >= 1 ? 1 : 0;
        int ans = 0;
        if(memo[n][target] != null) return memo[n][target]%MOD;
        for(int i=1;i<=k;i++) ans = (ans + func(n-1,k,target-i))%MOD;
        return memo[n][target] = ans % MOD;
    }
}