class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;      
        int runs = 0;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1' ){     
                    runs++;                               
                    check(grid, i, j,m,n);
                }
            }
        }
            
        return runs;
    }
    public void check(char[][] grid, int x, int y, int m, int n){
        if(x < 0 || y < 0 || x >= m || y>= n) return;
        if(grid[x][y] == '0') return;
        grid[x][y] = '0';
        int[] neighs = {-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i]; //x-1,x,x+1,x
            int Y = y + neighs[i+1];//y,y+1,y,y-1
            check(grid,X,Y,m,n);
        }
    }
}

