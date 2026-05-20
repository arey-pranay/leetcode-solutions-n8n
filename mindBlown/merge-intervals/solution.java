class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if(n==0) return new int[][]{};

        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(intervals[0]);

        for(int i=1;i<n;i++){
            int[] curr = arr.get(arr.size()-1);
            if(curr[1] < intervals[i][0]){
                arr.add(intervals[i]);
            } else{
                if(curr[1] >= intervals[i][1]) continue;
                else curr[1] = intervals[i][1];
            } 
        }
        int[][] ans = new int[arr.size()][2];
        int i=0;
        for(int[] temp : arr)ans[i++] = temp;
        return ans;
    }
}