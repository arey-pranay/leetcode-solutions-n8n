class Solution {
    int[] memo;
    int[] arr;
    public int coinChange(int[] coins, int amount){
        memo = new int[amount+1];
        Arrays.fill(memo,-1);
        arr = coins;
        
        int ans = func(amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
        
    }
    public int func(int amount){
        if(amount==0) return 0;
        if(amount<0) return Integer.MAX_VALUE;
        if(memo[amount] != -1) return memo[amount];
        
        int min = Integer.MAX_VALUE;
        for(int coin : arr){
            int res = func(amount-coin);
            if(res == Integer.MAX_VALUE) continue;
            min = Math.min(min,res+1);
        }
        return memo[amount] = min;
    }
}