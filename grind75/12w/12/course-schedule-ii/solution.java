class Solution {
    public int[] findOrder(int numCourses, int[][] preqs) {
        //topological sort
        int[] indegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numCourses;i++) adj.add(new ArrayList<>());
        for(int[] pre : preqs){
          int a = pre[0];
          int b = pre[1];
          indegree[a]++; 
          adj.get(b).add(a);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++)if(indegree[i]==0) q.add(i);
        int[] ans = new int[numCourses];
        Arrays.fill(ans,-1);
        int index=0;
        boolean[] vis = new boolean[numCourses];
        while(!q.isEmpty()){
          int curr = q.poll();
          vis[curr] = true;
          ans[index++] = curr;
          for(int neigh : adj.get(curr)){
            indegree[neigh]--;
            if(!vis[neigh] && indegree[neigh]==0){q.add(neigh); vis[neigh]=true;}
          }
        }
        return index != numCourses ? new int[]{} : ans;
    }
}
