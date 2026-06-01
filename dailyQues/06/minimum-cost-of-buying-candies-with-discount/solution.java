class Solution {
    public int minimumCost(int[] cost) {
        // canBuy <= Math.min(bought)
        //sort the array and take 2-2 from last
        
        Arrays.sort(cost);
        // cost = -Arrays.sort(-cost);

        int ans = 0; int mod = 0;
        for(int i=cost.length-1;i>=0;i--){
            if((++mod) %3 == 0) continue;
            ans += cost[i];
        }
        return ans;
    }
}