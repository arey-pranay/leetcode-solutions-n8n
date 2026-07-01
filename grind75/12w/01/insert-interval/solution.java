class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        ArrayList<int[]> ans = new ArrayList<>();
        int i=0;
        while(i<n && intervals[i][1] < newInterval[0]) ans.add(intervals[i++]);
        while(i<n && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        ans.add(newInterval);
        while(i<n) ans.add(intervals[i++]);
        i=0;
        int[][] res = new int[ans.size()][2];
        for(int[] temp : ans) res[i++] = temp;
        return res;
    }
}
//newInterval ko sabse bada bana rhe hai , aur intervals ko chhota banate jaa rhe hai 