class Solution {
    public int numBusesToDestination(int[][] ipRoutes, int source, int target) {
        
        if(source==target) return 0;
        
        // the idea is => we start from a stop and need to reach another stop, switching as less buses as possible
        // so this is like a shortestPath graph problem, where we need to use buses as our nodes
        // but the source stop can be a stop of multiple buses, and same is true for the target stop
        // we are fine as long as any source-containing bus is able to reach any target-containing bus
        // We are using BFS, so the first answer will be the shortest always.
        // so now it is multi-eligible source and multi-eligible distance shortestPathBFS
        
        // we've established that have a lot of buses, which can be said to connected if they share a stop
        // so create a graph of connected buses. then run a bfs of all sourceBuses to any of targetBuses
        // when we reach any target, we can just return the levels or switches made till that point.
        
        //disjoint sirf set p use kr skte hai , aur isse actual disjo
        int n = ipRoutes.length;
        HashSet<Integer>[] stopsSet = new HashSet[n]; // converting ip [][] to HashSet[] so that we can check .contains
        
        List<List<Integer>> graph = new ArrayList<>();
        
        HashSet<Integer> sourceBuses = new HashSet<>();
        HashSet<Integer> targetBuses = new HashSet<>();
        
        for(int i=0;i<n;i++) {
            stopsSet[i] = new HashSet<>();
            for(int j : ipRoutes[i]) {
                stopsSet[i].add(j);
                
                if(j==source) sourceBuses.add(i);
                if(j==target) targetBuses.add(i);
            }
            graph.add(new ArrayList<>());
        }
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(!Collections.disjoint(stopsSet[i],stopsSet[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return shortestPathBFS(graph,sourceBuses,targetBuses);
    }
    public int shortestPathBFS(List<List<Integer>> graph, HashSet<Integer> sourceBuses, HashSet<Integer> targetBuses){
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[graph.size()];
        
        for(int source : sourceBuses){
            q.add(source);
            vis[source] = true;
        }
        
        int level = 1;
        
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0; i<sz;i++){
                int curr = q.poll();
                if(targetBuses.contains(curr)) return level;
                for(int neigh : graph.get(curr)){
                    if(!vis[neigh]){
                        q.add(neigh);
                        vis[neigh] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}