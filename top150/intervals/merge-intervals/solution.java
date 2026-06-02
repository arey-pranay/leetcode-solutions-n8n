class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        ArrayList<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        int j = 0;
        ans.add(intervals[0]);
        for(int i=1;i<n;i++){
            int[] curr = ans.get(j);
            int[] next = intervals[i];
            if(curr[1] > next[1]) i+=0;
            else if(curr[1] >= next[0])curr[1]=next[1];
            else{ans.add(next);j++;} 
        }
        int[][] arr = new int[ans.size()][2];
        int i=0;
        for(int[] temp : ans) arr[i] = ans.get(i++);
        return arr;
    }
}