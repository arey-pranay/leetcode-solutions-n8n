class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        int m = restrictions.length;
        if(m==0) return n-1;
        //left to right norm
        int prevPos = 1;
        int prevH = 0;
        for (int i=0; i<m; i++) {
            int dist = restrictions[i][0] - prevPos;
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }
        //right to left norm
        prevPos = n;
        prevH = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            int dist = prevPos - restrictions[i][0];
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }
        //max distance between every pair of restrictions
        int leftPos = 1;
        int leftH = 0;
        int ans = 0;
        for (int[] temp : restrictions) {
            int rightPos = temp[0];
            int rightH = temp[1];

            int d = rightPos - leftPos;
            ans = Math.max(ans, (leftH + rightH + d) / 2);

            leftPos = rightPos;
            leftH = rightH;
        }
        int d = n-leftPos;
        int rightH = leftH+d;
        return Math.max(ans, rightH);
    }
}