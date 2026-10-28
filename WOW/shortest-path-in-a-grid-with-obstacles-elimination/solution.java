class Solution {
    int[] neigh = new int[]{-1,0,1,0,-1};
    public int shortestPath(int[][] grid, int K){
        Queue<int[]> q = new LinkedList<>();// x,y,dist,k
        int m = grid.length, n=grid[0].length;
        if(grid[m-1][n-1]==1) return -1;
        q.add(new int[]{0,0,0,K});
        boolean[][][] vis = new boolean[m][n][K+1];
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], dist = curr[2], k=curr[3];
            if(x==m-1 && y==n-1) return dist;
            dist++;
            for(int i=0;i<4;i++){
                int X = x+neigh[i];
                int Y = y+neigh[i+1];
                if(X<0 || Y<0 || X==m || Y==n) continue;
                int newK = k-grid[X][Y];
                if(newK < 0 || vis[X][Y][newK]) continue; 
                q.offer(new int[]{X,Y,dist,newK});
                vis[X][Y][newK] = true;
            }
        }
        return -1;
    }
}