class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[] neighs = new int[]{-1,0,1,0,-1};
        int ones = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2)q.add(new int[]{i,j,0});
                if(grid[i][j]==1) ones++;    
            } 
        }
        if(ones==0) return 0;
        int ans = -1;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int time = curr[2];
            ans = time;
            for(int i=0;i<4;i++){
                int X = curr[0] + neighs[i];
                int Y = curr[1] + neighs[i+1];
                if(X<0 || Y<0 || X==m || Y==n) continue;
                if(grid[X][Y]==1){
                    q.add(new int[]{X,Y,time+1});
                    ones--;
                    grid[X][Y] = 2;
                }
            }
        }
        return ones == 0 ? ans : -1;
    }
}
// 2 2 1 0 1 1
// 2 1
// [[2,2,2,1,1,2,1],
// [0,2,2,1,1,1,1],
// [0,0,2,0,0,2,1]]

// 0,0,0,1,1,0,1
// - 0 0 1 2 1 2
// - - 0 - - 2 3