class Solution {
        public int maxProfit(int[] prices)  {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int prof1 = 0, prof2 = 0;

        for (int price :  prices) {
            buy1 = Math.min(buy1, price);
            prof1 = Math.max(prof1, price - buy1);
            buy2 = Math.min(buy2, price - prof1);// prof1 ka discount milega 2nd buy pe, kyuki already utna gain hai humaare paas
            prof2 = Math.max(prof2, price - buy2); 
            System.out.println(buy1 + " " + prof1 + " " + buy2 + " " + prof2);
        }
        
        return prof2;
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