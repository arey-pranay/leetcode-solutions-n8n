class Solution {
    int[] arr;
    Integer[][] memo ;
    public int maxProfit(int k, int[] prices) {
        memo = new Integer[prices.length+1][k+1];
        arr = prices;
        return func(0,k);
    }
    public int func(int start, int k){
        int selling = 0;
        if(k == 0 || start>=arr.length) return 0;
        if(memo[start][k] != null) return memo[start][k];
        
        int boughtAt = start;
        
        for(int i=boughtAt + 1; i<arr.length; i++){
            if (arr[i]<=arr[boughtAt]) boughtAt = i; 
            else selling = Math.max(selling,  arr[i]-arr[boughtAt] + func(i+1,k-1)); //10
        }
        selling = Math.max(selling,func(start + 1, k));
        return memo[start][k] = selling;           
    }
}
