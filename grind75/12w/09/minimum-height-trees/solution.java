class Solution {
  int minHeight = Integer.MAX_VALUE;
  HashMap<Integer,ArrayList<Integer>> adj= new HashMap<>();
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        for(int i =0 ; i<n;i++) adj.put(i,new ArrayList<>());
        for(int[] temp : edges){
          int src = temp[0];
          int dest = temp[1];
          adj.get(src).add(dest);
          adj.get(dest).add(src);
        }
        int end = bfs(0,n,null);
        
        int[] parent = new int[n];
        
        end = bfs(end,n,parent);
            
        List<Integer> path = new ArrayList<>();
        for(int i : parent ) 
        while(end!=-1){
          path.add(end);
          end = parent[end];
        }    
        
        List<Integer> ans = new ArrayList<>();
        int sz = path.size();
        ans.add(path.get((sz-1)/2));
        if(sz%2==0)ans.add(path.get(sz/2));
        return ans;
    } 
    public int bfs(int start, int n,int[] parent){
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;
        int last = start;
        if(parent!=null) parent[start] = -1;
        while(!q.isEmpty()){
            int curr = q.poll();
            last = curr;
            for(int i : adj.get(curr)){
              if(!vis[i]){
                vis[i] = true;
                if(parent!=null) parent[i] = curr;
                q.add(i);
              }
            }
        }
      return last;
       
    }
}