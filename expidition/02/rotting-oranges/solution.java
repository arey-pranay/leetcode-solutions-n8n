class Solution {
    int time;
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        time = 2;
        boolean freshLeft = false;
        for(int i = 0;i<m;i++) for(int j=0; j<n; j++)if(grid[i][j]==1) freshLeft = true;
        if(!freshLeft) return 0;
        while(true){
            boolean didSomething = false;
            freshLeft = false;
            for(int i = 0;i<m;i++){
                for(int j=0; j<n; j++){
                    if(grid[i][j]==time){
                        func(grid,i,j);
                        didSomething = true;
                    } 
                }
            }
            time++;
            for(int i = 0;i<m;i++) for(int j=0; j<n; j++)if(grid[i][j]==1) freshLeft = true;
            if(!freshLeft) return time-2;
            if(!didSomething) return -1;  
        }
    }
    public void func(int[][] grid, int x, int y){
        int[] nei = new int[]{-1,0,1,0,-1};
        for(int i = 0; i<4;i++){
            int X = x+nei[i];
            int Y = y+nei[i+1];
            if(X < 0 || Y<0 || X >= grid.length || Y >= grid[0].length) continue;
            if(grid[X][Y] == 1){
                grid[X][Y]=time+1;
            }
        }    
    }
}
