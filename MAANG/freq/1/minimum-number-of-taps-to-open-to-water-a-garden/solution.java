class Solution {
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n+1][2];
        for(int i=0;i<=n;i++){
            intervals[i][0] = Math.max(0,i-ranges[i]); // to prevent out of bounds
            intervals[i][1] = Math.min(n,i+ranges[i]);
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int done=0, taps=0,i=0;
        while(done < n){
            int maxReach = done;
            while(i<=n && intervals[i][0] <= done) maxReach = Math.max(maxReach, intervals[i++][1]); //eligible taps, jahan tk ki range humne cover kr li hai.
            if(maxReach == done) return -1;
            
            done = maxReach;
            taps++;
        }
        return taps;
    }
}
// 6
// 0-> -3,+3
// 1-> -3,5  => ans
// 2-> 1,3
// 3-> 2,4
// 4-> 4
// 5-> 5