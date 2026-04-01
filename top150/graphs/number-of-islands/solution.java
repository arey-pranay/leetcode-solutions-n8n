class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    dfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;   
    }
    public void dfs(int x, int y, char[][] grid){
        if(x < 0 || y<0 || x>= grid.length || y>= grid[0].length || grid[x][y] != '1') return;        
        grid[x][y] = '0';
        int[] arr = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + arr[i];
            int Y = y + arr[i+1];
            dfs(X,Y,grid);
        }   
        return;     
    }
}