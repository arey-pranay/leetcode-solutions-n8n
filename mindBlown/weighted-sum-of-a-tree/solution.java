class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        long ans = 0;
        long sum = 0;
        int n = nums.length;
        Queue<Integer> q = new LinkedList<>();
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n;i++) if(parent[i]!=-1)adj.get(parent[i]).add(i);
        q.add(0);
        int depth = 0;
        while(!q.isEmpty()){
          int sz = q.size();
          depth++;
          for(int i=0;i<sz;i++){
            int curr = q.poll();
            sum += (long)nums[curr]*depth;
            for(int neigh : adj.get(curr)) q.add(neigh);
          }
        }
        for(int i=0;i<n;i++) ans+= (long)nums[i]*(depth+1);
        return ans-sum;
    }
}