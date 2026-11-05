class Solution {
    int[] neighs = new int[]{-1,0,1,0,-1};
    int m,n;
    public int[] hitBricks(int[][] grid, int[][] hits) {
       int h = hits.length;
       m = grid.length; n = grid[0].length;
       int[] results = new int[h];
       
       // remove bricks -> mark as gaps
       for(int i=0;i<h;i++) grid[hits[i][0]][hits[i][1]]--;
       
       // mark stable
       for(int j=0;j<n;j++) if(grid[0][j]==1) dfs(grid,0,j);
       
       // put the bricks
       for(int i=h-1;i>=0;i--){
        int x= hits[i][0],y=hits[i][1];
        if(grid[x][y] == -1) continue;
        grid[x][y]=1;
        // kyonki dfs tb hi chalana hai jb koi waapis daalne waali brick kisi stable waale se connected hai kyonki agr vo restore waali khud hi kisi stable waali se connected nahihai to vo baaki ko kya stable banayegi
        if(x==0 || (x>0 && grid[x-1][y]==2) || (y>0 && grid[x][y-1]==2) || (x<m-1 && grid[x+1][y]==2) || (y<n-1 && grid[x][y+1]==2)) results[i] = dfs(grid,x,y);
       }
       
       return results;
    }
    private int dfs(int[][] grid, int x, int y){
        grid[x][y] = 2;
        int count = 0;
        for(int i=0;i<4;i++){
            int X = x+neighs[i],Y=y+neighs[i+1];
            if(X<0 || Y<0 || X==m || Y==n || grid[X][Y]!=1) continue;
            count += 1 + dfs(grid,X,Y); 
        }
        return count;
    }
}
// [2,0,2,0]
// [0,2,2,0]
// [1,0,0,1]