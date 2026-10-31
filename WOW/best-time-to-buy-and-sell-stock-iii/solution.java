class Solution {
        public int maxProfit(int[] prices)  {
        // 2 transactions => buy1 sell1, buy2 sell2. 
        // buy ke liye udhaar, sell ke liye money increment, we need to maximise all. because -1 > -20. 
        // for buy1, we do not have money currently, so that we count as negative. Math.max(buy1, -prices[i]) buy1 is neg
        // for sell1, we would get a revenue of prices[i], but we need to pay back buy1, so net is Math.max(sell1, prices[i]+buy1)
        // for buy2, we currently have the sell1 money, we need only sell1 - prices[i] amount, so Math.max(buy2,sell1-prices[i])
        // for sell2, we would get prices[i] in wallet, but we need to pay back the buy2 amount. Math.max(sell2, prices[i]+buy2)
        // so finally we would end up with sell2 amount of net net profit, considering best cases as we go forward.
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE, sell1 = 0, sell2 = 0;
        for(int price : prices){
         buy1 = Math.max(buy1, -price);
         sell1 = Math.max(sell1, price+buy1);
         buy2 = Math.max(buy2,sell1-price);
         sell2 = Math.max(sell2, price+buy2);
        }
        return sell2;   
    }
}

// [3,3,5,0,0,3,1,4]
// b1 = 3 , 3
// s1 = 0,  2
// b2 = 3
// s2 = 0

// class Solution {
//     int[] memo;
//     public int maxProfit(int[] prices) {
//         memo = new int[prices.length];
//         Arrays.fill(memo,-1);
//         return func(prices,0,2);
//     }
//     public int func(int[] arr, int i, int left){
//         if(left == 0) return 0;
//         int boughtAt = arr[i];
//         int max = 0;
//         if(memo[i] != -1) return memo[i];
//         for(int j=i+1;j<arr.length;j++){
//             if(arr[j] > boughtAt){
//                 int profit = arr[j]-boughtAt;
//                 int sold= profit + func(arr, j, left-1);
//                 max = Math.max(max,sold);
//             } else {
//                 boughtAt = arr[j];
//             }
//         }
//         return memo[i] = max;
//     }
// }