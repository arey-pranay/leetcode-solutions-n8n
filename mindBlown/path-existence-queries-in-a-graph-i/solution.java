class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int j=0;
        int[] comp = new int[n];
        comp[0] = 0;
        boolean ans[] = new boolean[queries.length];
        for(int i=1;i<n;i++){
            if(Math.abs(nums[i]-nums[i-1]) > maxDiff) j++;
            comp[i] = j;
        }
        for(int i=0;i<queries.length;i++){
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = comp[u]==comp[v];
        }
        return ans;
    }
}