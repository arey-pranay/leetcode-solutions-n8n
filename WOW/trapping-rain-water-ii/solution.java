// initially humne queue m saari boundaries daali , phir smallest waali nikaal kr uske neighbors daalenge aur ese daalte jaayenge 
class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length,n = heightMap[0].length;
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->a[0]-b[0]);
        boolean[][] vis = new boolean[m][n];
        for(int row=0;row<m;row++){
            pq.offer(new int[]{heightMap[row][0],row,0});
            vis[row][0] = true;
            pq.offer(new int[]{heightMap[row][n-1],row,n-1});
            vis[row][n-1] = true;
        }
        for(int col=0;col<n;col++){
            pq.offer(new int[]{heightMap[0][col],0,col});
            vis[0][col] = true;
            pq.offer(new int[]{heightMap[m-1][col],m-1,col});
            vis[m-1][col] = true;
        }
        int water = 0;
        while(!pq.isEmpty()){
            int[] cell= pq.poll();
            int height = cell[0], x=cell[1], y=cell[2];
            for(int i=0;i<4;i++){
                int X = x+neigh[i], Y = y+neigh[i+1];
                if(X<0 || Y<0 || X==m || Y==n || vis[X][Y]) continue;
                water += Math.max(0,height - heightMap[X][Y]);
                pq.offer(new int[]{Math.max(height,heightMap[X][Y]), X, Y});
                vis[X][Y] = true;
            }
        }
        return water;
    }
}