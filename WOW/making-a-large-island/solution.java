class Solution {
    int[][] groupID;
    HashMap<Integer,Integer> groupArea = new HashMap<>();
    int[] neighs = new int[]{-1,0,1,0,-1};
    public int largestIsland(int[][] grid) {
       int n = grid.length;
       groupID = new int[n][n];
       int ID = 1;
       int maxArea = 0;
        for(int i =0;i<n;i++){
            for(int j =0 ; j<n;j++){
                if(grid[i][j]==1 && groupID[i][j]==0) {
                   int area = dfs(grid,i,j,ID++);
                   maxArea = Math.max(area,maxArea);
                }
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1) continue;
                int area = 1;
                HashSet<Integer> vis = new HashSet<>();
                for(int k=0;k<4;k++){
                    
                    int X = i+neighs[k];
                    int Y = j+neighs[k+1];
                    if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || vis.contains(groupID[X][Y])) continue;
                    
                    area += groupArea.get(groupID[X][Y]); 
                    vis.add(groupID[X][Y]);
                    
                }
                maxArea = Math.max(area,maxArea);
            }
        }

        return maxArea;
    }
    public int dfs(int[][] grid, int x, int y, int ID){
        int area = 1;
        groupID[x][y] = ID;
        int n = grid.length;
        
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X==n || Y==n || grid[X][Y]==0 || groupID[X][Y]!=0) continue;
            area += dfs(grid, X, Y, ID);
        }
        
        groupArea.put(ID,area);
        return area;
    }
}
// 62,50,00,00,000
// 1,00,00,00,000