class Solution {
    public int maxProfit(int[] prices) {
        int boughtAt = prices[0];
        int ans = 0;
        for(int price : prices){
            boughtAt = Math.min(boughtAt,price);
            int profit = price-boughtAt;
            if(profit> 0){
                ans += profit;
                boughtAt = price;
            }
        }
        return ans;
    }
}