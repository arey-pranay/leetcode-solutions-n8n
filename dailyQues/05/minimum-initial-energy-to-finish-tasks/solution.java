class Solution {
    public int minimumEffort(int[][] tasks) {
        int min_diff =  Integer.MAX_VALUE;
        int sum = 0;
        int max_needed = Integer.MIN_VALUE;
        int m = tasks.length;
        Arrays.sort(tasks, (a,b) -> (b[1]-b[0])-(a[1]-a[0]));
        int ans = tasks[0][1]; //12 + 9 + 8 + 3 + 1
        int rem = ans - tasks[0][0];
        for(int i = 1;i<m;i++){
            int needed = tasks[i][1];
            if(needed > rem){
                ans += needed-rem;
                rem = needed;
            }
            rem -= tasks[i][0];
            
            // sum+=tasks[i][0];
            // max_needed = Math.max(tasks[i][1],max_needed);
            // int curr = tasks[i][1]-tasks[i][0];
            // min_diff =  Math.min(curr,min_diff);
        }
        // System.out.print(sum+" "+min_diff);
        return ans;
        // return sum>max_nedded ? sum+min_diff: max_nedded; sum = 6 max 5 min 100
    } 
}