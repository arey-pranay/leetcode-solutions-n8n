class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        int minL = smallestEnd(landStartTime, landDuration);
        int minW = smallestEnd(waterStartTime, waterDuration);
        int m = landStartTime.length; int n = waterStartTime.length;
        
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++) ans = Math.min(ans,Math.max(minL,waterStartTime[i]) + waterDuration[i]);
        for(int i=0;i<m;i++) ans = Math.min(ans,Math.max(minW,landStartTime[i]) + landDuration[i]);
        return ans;
    }

    public int smallestEnd(int time[], int[] dur) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < time.length; i++) min = Math.min(min,time[i] + dur[i]);
        return  min;
    }
}