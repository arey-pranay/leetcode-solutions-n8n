class Solution {
    int[][]memo;
    int m;
    int n;
    public int minPathSum(int[][] grid) {
        m = grid.length; 
        n = grid[0].length;
        memo = new int[m][n];
        for(int[] arr : memo) Arrays.fill(arr,-1);
        return func(0,0,grid);
    }
    public int func(int i,int j,int[][]grid){
        if(i == m || j == n) return Integer.MAX_VALUE;
        if(i==m-1 && j==n-1) return grid[i][j];
        if(memo[i][j]!=-1) return memo[i][j];
        int a = func(i+1,j,grid);
        int b = func(i,j+1,grid);
        return memo[i][j] = grid[i][j] + Math.min(a,b);
    } 
}