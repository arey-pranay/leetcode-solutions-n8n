class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[0][0]-b[0][0]);
        int filling=0;
        
        for(int i=1;i<intervals.length;i++){
            if(intervals[filling][1]>=intervals[i][0]){
                intervals[filling][1]=Math.max(intervals[i][1],intervals[filling][1]);
                
            } else {
                filling++;
                intervals[filling] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals, 0, filling+1);
    }
}