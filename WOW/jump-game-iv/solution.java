class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> occ = new HashMap<>();
        for(int i=0;i<n;i++) occ.computeIfAbsent(arr[i],k->new ArrayList<>()).add(i);
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        
        boolean[] vis = new boolean[n];
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int index = curr[0];
            int steps = curr[1];
            if(index==n-1) return steps;
            steps++;
            if(index-1 >= 0 && !vis[index-1]){
                vis[index-1] = true;
                q.offer(new int[]{index-1,steps});
            }
            if(index+1 < n && !vis[index+1]){
                vis[index+1] = true;
                q.offer(new int[]{index+1,steps});
            }
            int val = arr[index];
            for(int duplicate : occ.get(val)){
                if(vis[duplicate]) continue;
                vis[duplicate] = true;
                q.offer(new int[]{duplicate,steps});
            }
            occ.get(val).clear();
        }
        return -1;
    }
}
// 100 1 2 3 4 10 4 5 6 100 10 8 9 10 404