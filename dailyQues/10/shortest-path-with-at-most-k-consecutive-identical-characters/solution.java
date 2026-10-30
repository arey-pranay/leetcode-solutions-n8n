class Solution {
    
    public int shortestPath(int n, int[][] edges, String labels, int K) {
        List<List<int[]>> adj  = new ArrayList<>(); 
        for(int i =0;i<n;i++) adj.add(new ArrayList<>());
        
        for(int i=0; i<edges.length;i++){
            int[] arr = edges[i];
            int u = arr[0], v = arr[1], w=arr[2];
            adj.get(u).add(new int[] {v,w});
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[1]-b[1]);
        q.add(new int[]{0,0,1});
        int[][] dists = new int[n][K+1]; // given node pe, given consecutive occurences leke, humara best dist kitna hai
        for(int[] arr : dists)Arrays.fill(arr,Integer.MAX_VALUE);
        dists[0][1] = 0; // 0th node ke 1 occurence se start kara, uska dist covered is 0
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int u = curr[0], dist = curr[1], cons = curr[2];
            if(dist > dists[u][cons]) continue;
            if(u==n-1) return dist;
            for(int[] neigh : adj.get(u)){
                int cons2 = cons;
                int v = neigh[0], w=neigh[1];
                if(labels.charAt(u)==labels.charAt(v)) cons2++; else cons2 = 1;
                if(cons2>K || dists[u][cons]+w >= dists[v][cons2]) continue;
                dists[v][cons2] = dists[u][cons]+w;
                q.add(new int[]{v,dists[v][cons2],cons2});
            }
        }
        
        return -1;
    }
}