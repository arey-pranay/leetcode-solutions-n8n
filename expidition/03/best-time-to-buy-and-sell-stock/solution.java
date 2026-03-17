class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int bp = prices[0];
        for(int i : prices){
           if(i > bp) ans = Math.max(ans,i-bp);
           else bp = i;
        }
        return ans;
    }
}