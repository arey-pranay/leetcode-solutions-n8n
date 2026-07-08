class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1'){
                    func(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    public void func(char[][] grid, int x, int y){
        grid[x][y]='2';
        int[]  neighs = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X==grid.length || Y==grid[0].length) continue;
            if(grid[X][Y] == '1') func(grid,X,Y);
        }
        return;
    }
}