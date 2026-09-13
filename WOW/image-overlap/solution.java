class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        ArrayList<int[]> l1= new ArrayList<>();
        ArrayList<int[]> l2= new ArrayList<>();
        int n = img1.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(img1[i][j]==1) l1.add(new int[]{i,j});
                if(img2[i][j]==1) l2.add(new int[]{i,j});
            }
        }
        // 2,2   2,3   3,3
        // 3,3   3,4   4,4
        
        // 1,1   1,1  1,1
        // 1     2    3
        int max = 0;
        int[][] cnt = new int[2 * n][2 * n];
        for(int[] p1 : l1){
            for(int[] p2 : l2){
                int rx = p1[0]-p2[0]+n;
                int ry = p1[1]-p2[1]+n;
              
             //   int key = (rx*100) + ry; //0,0 se 30,30
                // 101 //1,1
                // 1001 //10,1
                // 110  // 1,10
                // 1010 // 10,10
        
                max = Math.max(max,++cnt[rx][ry]);
            }
        }
        return max;
    }
}