class Solution {
    int[] arr;
    int[] memo;
    public int coinChange(int[] coins, int amount) {
        arr = coins;
        memo = new int[amount+1];
        Arrays.fill(memo,-1);
        int ans = func(amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // 7, 416, 419
    // 10
    // 848
    public int func(int amount){   
        if(amount == 0) return 0;
        if(amount < 0) return Integer.MAX_VALUE;
        if(memo[amount] != -1) return memo[amount];
        int ans = Integer.MAX_VALUE;
        for(int coin : arr){
            int temp = func(amount-coin);
            if(temp != Integer.MAX_VALUE) ans = Math.min(ans, temp+1);
        }
        return memo[amount] =ans;
    }
}