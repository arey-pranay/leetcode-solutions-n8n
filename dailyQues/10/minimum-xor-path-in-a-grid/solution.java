class Solution {
    int[][] grid;
    int m,n;
    int min = Integer.MAX_VALUE;
    Boolean[][][] memo;
    public int minCost(int[][] g) {
        grid=g;
        m=g.length; n = g[0].length;
        memo = new Boolean[m][n][1024];
        func(0,0,0);
        return min;
    }
    public void func(int i, int j, int curr){
        if(i == m || j == n) return;
        
        if(memo[i][j][curr]!=null) return;
        memo[i][j][curr] = true;
        
        curr ^= grid[i][j];
        if(i==m-1 && j==n-1){min = Math.min(min,curr); return;}
        func(i+1,j,curr); func(i,j+1,curr);
    }
}