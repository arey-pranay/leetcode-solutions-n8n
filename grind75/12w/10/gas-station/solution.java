class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tGas=0,tCost=0;
        int n = gas.length;
        for(int i=0;i<n;i++){tGas += gas[i]; tCost += cost[i];}
        if(tGas < tCost) return -1; 
        int total = 0;
        int ans = 0;
        for(int i=0;i<n;i++){
            total = total + gas[i] - cost[i];
            if(total < 0){ans = i+1; total = 0;} // mtlb ghaata hogya ab kahi aur se krte start chalo
        }
        return ans;
    }
}
// 0 -> 1 (3)
// + gas[i]
// - cost[i]
// should be > 0