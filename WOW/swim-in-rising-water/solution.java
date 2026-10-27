class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    public int swimInWater(int[][] grid) {
        // newT = currT + diff
        int minTime = grid[0][0];
        int maxTime = maxOf(grid);
        int n = grid.length;
        while(minTime < maxTime){
            int midTime = minTime + (maxTime-minTime)/2;
            boolean[][] vis = new boolean[n][n];
            if(canReach(grid, 0,0,midTime,vis)) maxTime = midTime;
            else minTime = midTime+1;
        }
        return minTime;
    }
   
    private boolean canReach(int[][] grid, int row, int col, int time, boolean[][] vis){
        int n = grid.length;
        if(row<0 || col<0 || row==n || col==n || vis[row][col] || grid[row][col] > time) return false;
        if(row==n-1 && col==n-1) return true;
        vis[row][col] = true;
        for(int i =0;i<4;i++){
            int X = row + neigh[i];
            int Y = col + neigh[i+1];
            if(canReach(grid,X,Y,time,vis)) return true;
        }
        return false;
    }
    
    private int maxOf(int[][] grid){
        int max = 0;
        for(int[] row : grid) for(int cell : row) max = Math.max(max,cell);
        return max;
    }
    
}