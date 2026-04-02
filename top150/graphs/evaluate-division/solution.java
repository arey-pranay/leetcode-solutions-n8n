class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        HashMap<String,ArrayList<Pair>> graph = new HashMap<>();
        HashSet<String> variables = new HashSet<>();
        int i=0;
        
        for(List<String> eq : equations){
            
            String var1 = eq.get(0);
            String var2 = eq.get(1);
            double val = values[i++];
            
            ArrayList<Pair> arr = graph.getOrDefault(var1, new ArrayList<>());
            arr.add(new Pair(var2,val));
            graph.put(var1,arr);
            
            arr = graph.getOrDefault(var2, new ArrayList<>());
            arr.add(new Pair(var1,1/val));
            graph.put(var2,arr);
            
            variables.add(var1);
            variables.add(var2);
        }
        
        double[] ans = new double[queries.size()];
        i = 0;
        for(List<String> query : queries){
            String var1 = query.get(0);
            String var2 = query.get(1);
            if(! (variables.contains(var1) && variables.contains(var2)) ) ans[i] = -1.0;
            else ans[i] = bfs(graph, var1, var2);
            i++;
        }
        
        return ans;
    }
    public double bfs(HashMap<String, ArrayList<Pair>> graph, String src, String dest){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src,1));
        HashSet<String> vis = new HashSet<>();
        vis.add(src);
        while(!q.isEmpty()){
            Pair curr = q.poll();
            for(Pair neigh : graph.get(curr.var)){
                double cost = curr.dist * neigh.dist;
                if(neigh.var.equals(dest)) return cost;
                if(vis.contains(neigh.var)) continue;
                q.add(new Pair(neigh.var,cost));
                vis.add(neigh.var);
            }
        }
        return -1;
    }
    class Pair{
        String var;
        double dist;
        Pair(String var, double dist){
            this.var = var;
            this.dist = dist;
        }
        
        @Override
        public String toString(){
            return this.var + " -> " + this.dist;
        }
    }
}