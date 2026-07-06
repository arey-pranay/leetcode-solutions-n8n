class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)-> a[0] == b[0] ? b[1]-a[1] :a[0]-b[0]);
        int[] curr = intervals[0];
        int n = intervals.length;
        int ans = n;
        for(int i=1;i<n;i++) if(curr[0] <= intervals[i][0] && intervals[i][1] <= curr[1]) ans--;else curr = intervals[i];
        return ans;
    }
}