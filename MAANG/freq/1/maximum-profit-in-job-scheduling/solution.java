class Solution {
    int[][] jobs;
    int[] memo;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        jobs = new int[n][3];
        memo = new int[n];
        Arrays.fill(memo,-1);
        for(int i=0;i<n;i++){
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs,(a,b)->a[0]-b[0]);
        return func(0);
    }
    private int func(int i){
        if(i==jobs.length) return 0;
        if(memo[i] != -1) return memo[i];
        int skip = func(i+1);
        int take = jobs[i][2] + func(upperBound(jobs[i][1])); // jb ye job khtm hogi, uske baad start hone wali first job
        return memo[i] = Math.max(skip,take);
    }
    private int upperBound(int target){
        int s = 0;
        int e = jobs.length;
        while(s<e){
            int m = s + (e-s)/2;
            if(jobs[m][0] < target) s = m+1;
            else e = m;
        }
        return s;
    }
}