class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i=0;
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();
        while(i<n && intervals[i][1] < newInterval[0]) ans.add(intervals[i++]);
        while(i<n && (newInterval[1] >= intervals[i][0])){
          newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
          newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
          i++;
        }
        ans.add(newInterval);
        while(i<n)ans.add(intervals[i++]);
        int[][] arr = new int[ans.size()][2];
        i=0;
        for(int[] temp : ans) arr[i++] = temp;
        return arr;
    }
}