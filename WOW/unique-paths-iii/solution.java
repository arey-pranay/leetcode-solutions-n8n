class Solution {
    int[] neighs = new int[]{-1,0,1,0,-1};
    int totalEmpty = 0;
    int targetX=0,targetY=0;
    int m,n;
    int[][] grid;
    int ans=0;
    public int uniquePathsIII(int[][] g) {  
        grid = g;m = g.length;n = g[0].length;  
        int beginX=-1,beginY=-1;  
        for(int i=0;i<m;i++){    
            for(int j=0;j<n;j++){       
                if(grid[i][j]!=-1)totalEmpty++; 
                if(grid[i][j]==1){beginX=i; beginY=j;}
                if(grid[i][j]==2){targetX=i; targetY=j;}
            }
        }
        grid[beginX][beginY] = -1;
        func(beginX,beginY,grid,1);
        return ans;
    }
    
    public void func(int x, int y, int[][] grid,int visCount){
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || X>=m || Y<0 || Y>=n) continue;
            int ogVal = grid[X][Y];
            if(grid[X][Y] == 2){
                if(visCount+1==totalEmpty)ans++; 
                continue;
            }
            if(grid[X][Y] != -1){grid[X][Y]=-1; func(X,Y,grid,visCount+1); grid[X][Y] = ogVal;}
        }
    }  
}