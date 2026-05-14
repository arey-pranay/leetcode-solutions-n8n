class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int boughtAt = prices[0];
        for(int price : prices){
            boughtAt = Math.min(boughtAt,price);
            profit = Math.max(profit,price - boughtAt);
        }
        return profit;
    }
}