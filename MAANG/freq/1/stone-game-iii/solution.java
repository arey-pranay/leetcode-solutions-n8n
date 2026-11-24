class Solution {
    int[] arr;
    int[] memo;
    public String stoneGameIII(int[] stoneValue) {
        arr = stoneValue;
        memo = new int[stoneValue.length];
        Arrays.fill(memo,-1);
        int aliceNetAdv = getNetAdv(0); 
        //alice ne apna pick krke, bob ko chance diya. so 1,2,3 teeno picks me se max jo alice le skti thi, wo humne use kiya
        if(aliceNetAdv > 0) return "Alice";
        else if(aliceNetAdv < 0) return "Bob";
        else return "Tie";
        
        // take -> 1,2,3
        // curr += stoneValue[i];
        
        // algo -> i index se start krke, current player humesha apna max find krega, aur next player ko chance dega
        //         take can be 1,2,3. teeno case me sum me wo val add hogi, and humusme se, jo net nuksaan hoga, (opponent ka score, wo hata dege)
        //         agr humara +ve bacha, to hum jeet gye, nhi to opponent jeet gya.
    }
    private int getNetAdv(int i){ 
        if(i>=arr.length) return 0;
        if(memo[i]!=-1) return memo[i];
        int sum =0,  max=Integer.MIN_VALUE;
        for(int extra=0;extra<3;extra++){
            int j=i+extra;
            if(j >= arr.length) break;
            sum += arr[j];
            int netAdv = sum - getNetAdv(j+1); //optimized playing (minimax)
            max = Math.max(max,netAdv);
        }
        return memo[i]=max;
    }
}
// 1 2 
// 3 4 6 100