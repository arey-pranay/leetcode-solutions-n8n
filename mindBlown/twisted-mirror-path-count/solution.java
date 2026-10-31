class Solution {
    int[][][] memo;
    int MOD = 1_000_000_007;
    public int uniquePaths(int[][] grid) {
        memo = new int[grid.length][grid[0].length][3];
        for(int[][] d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        return func(grid,0,0,2)%MOD;
    
    }
    public int func(int grid[][],int i,int j,int dir){ // 0 means we took down, 1 means we took right
        int m = grid.length, n = grid[0].length;
        if(i==m-1 && j==n-1) return 1;
        if(i==m || j==n) return 0;
        if(memo[i][j][dir] != -1) return memo[i][j][dir];
        if(grid[i][j] == 1) return memo[i][j][dir] = (dir == 0 ? func(grid,i,j+1,1) : func(grid,i+1,j,0))%MOD; // change the direction if we encounter 1.
        else return memo[i][j][dir] = func(grid,i+1,j,0)%MOD + func(grid,i,j+1,1)%MOD;
    }
   
}