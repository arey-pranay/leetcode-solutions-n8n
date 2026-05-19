class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        
        int gSum = 0;
        int cSum = 0;
        for(int i=0;i<n;i++){
            gSum += gas[i];
            cSum += cost[i];
        }
        if(gSum < cSum) return -1;
        
        int balance = 0;
        int ans = 0;
        for(int i=0;i<n;i++){
            balance += gas[i] - cost[i];
            if(balance < 0){
                ans = i+1;
                balance = 0;
            }
        }
        
        return ans;
    }
}