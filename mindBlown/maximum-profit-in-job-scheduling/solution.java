class Solution {
    int[][] jobs;
    int[] memo;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit){
      int n = startTime.length;
      jobs = new int[n][3];
      memo = new int[n];
      for(int i=0;i<n;i++) {jobs[i][0] = startTime[i];jobs[i][1]=endTime[i];jobs[i][2]=profit[i];}
      Arrays.sort(jobs,(a,b)->a[0]-b[0]);
      Arrays.fill(memo,-1);
      return func(0);
    }
    public int func(int i){
        if(i==jobs.length) return 0;
        if(memo[i]!=-1) return memo[i];
        int skip = func(i+1);
        int nextIndex = findNext(jobs[i][1]);
        int take = jobs[i][2] + func(nextIndex);
        return memo[i] = Math.max(skip,take);
    }
    public int findNext(int endTime){
        int i =0 ;
        int j =jobs.length;
        while(i<j){
            int m = i+ (j-i)/2;
            if(jobs[m][0]<endTime) i = m+1;
            else j = m;
        }
        return i;
    }
}