class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // mark 1s as -1
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> q = new LinkedList<>();// it guarantees that the first update is always the correct update (i.e., the one with least value)// sbse pehle 0 wale apne neighbours ko 1 bnayege, fir 1 wale apne univisted neighbours ko 2 bnayege, etc 
        for(int i=0;i<m;i++){ // saare 1 ko -1 kiya
            for(int j=0;j<n;j++){
                if(mat[i][j] == 0)q.add(new int[]{i,j}); // hum shuru hi 0 se kr rhe hai
                else mat[i][j] = -1;
            }
        }
        int[] neighs = new int[]{-1,0,1,0,-1};
        while(!q.isEmpty()){ // har -1 k aas paas k 4 hi dekh rhe hai  aur -1  ko uske padosiyo se +1 krre agr padosi -1 nahi hai to
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            for(int i=0;i<4;i++){
                int X = x + neighs[i];
                int Y = y + neighs[i+1];
                if(X<0 || Y<0 || X==m || Y==n || mat[X][Y]!=-1) continue;
                mat[X][Y] = mat[x][y] + 1;
                q.add(new int[]{X,Y});
            }
        }
        return mat;
    }
}
// 0,0
// 0,1 => 1,1 = 1
// // 0,2 
// 1,0 => 1,1 = 1 & 2,0 = 1
// 1,2 => 1,1 = 1 & 2,2 = 1
// 1,1 => 2,1 = 2
// 2,0
// 2,2
// 2,1