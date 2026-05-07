class Solution {
    int[][] memo;
    int m;
    int n;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        memo = new int[m][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return move(0,0,obstacleGrid);
    }
    public int move(int i , int j , int[][] grid){
        if(i>=m || j>=n) return 0;
        if(grid[i][j] == 1) return 0;
        if(memo[i][j] !=-1) return memo[i][j];
        if(i==m-1 && j==n-1) return 1;
        return memo[i][j] = move(i,j+1,grid) + move(i+1,j,grid);
    }
}
