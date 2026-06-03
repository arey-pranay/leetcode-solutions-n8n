class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int curr = 0;
        int  n = intervals.length;
        for(int i=1;i<n;i++){
            if(intervals[curr][1] >= intervals[i][0]){ // merge
                intervals[curr][1] = Math.max(intervals[i][1], intervals[curr][1]);
            } else {
                curr++;
                intervals[curr] = intervals[i]; // copy it ahead kyuki uspe updated intervals aana chaiye for later comparison(s)
            }
        }
        return Arrays.copyOfRange(intervals,0,curr+1);
    }
}