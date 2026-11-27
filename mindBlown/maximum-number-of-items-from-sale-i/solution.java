class Solution {
    int[][] memo;
    int minPrice = Integer.MAX_VALUE;
    public int maximumSaleItems(int[][] items, int budget) {
        int count = 0;
        int n = items.length;
        int minIndex= -1;
        int[][] info = new int[n][3];   //factor, price, quantity
        for(int i=0;i<n;i++){
            minPrice = Math.min(minPrice, items[i][1]);
            if(items[i][1] == minPrice) minIndex = i;
            info[i][0] = items[i][0];
            info[i][1] = items[i][1];
            for(int j = 0;j<n;j++) if(items[j][0]%items[i][0] == 0) info[i][2]++;
        }
        memo = new int[n+1][budget+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(0,budget, info);
    }
    private int func(int currIndex, int budget, int[][] info){
        if(currIndex == info.length) return budget/minPrice;
        if(memo[currIndex][budget] != -1) return memo[currIndex][budget];
        int skip =  func(currIndex+1, budget, info);
        int take = 0;
        if(info[currIndex][1] <= budget) take = info[currIndex][2] + func(currIndex+1, budget-info[currIndex][1], info);
        return memo[currIndex][budget] = Math.max(skip,take);
    }
}
// f p
// 9 100
// 6 2 x2
// 2 6
// 3 4 x1 (+2 free copy of 0th item)


  
        // given budget = 3
        // 2,3,4,6,8,9,10,12,14..
        // 2 -> 2 element    (ind price 4, but divides 1 element)   (3,9)
        // 3 -> 1000 element (ind price = 3000, but divides 999 elements) (2,4,6,8,4000)
        
        // 4 -> 1 (+3) => 1
        // 2 -> 2
        //  (1 free)  (isi ko kharidenge)
        // 7 -> 3 (+2 free) item (2.1>2)
        
        
           // after creating a effective group price we will check it with the minumum value item if it is less we will buy those 
        
        //once all the free item offer will be exausted for the rest amount we will buy the smallest item
        
        //
        // 0 -> []
        // 1 -> [0]
        // 2 -> [0]
        
        // group 0 = 6
        // group 1 = 6+2 = 8/2 = 4
        //group 2 = 4+2 = 6/2 = 3.