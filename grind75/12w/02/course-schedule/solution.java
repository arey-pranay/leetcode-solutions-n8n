class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0) return true;
        int[] needed = new int[numCourses];
        List<List<Integer>> preqs = new ArrayList<>();
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        for(int[] pair : prerequisites){
            needed[pair[0]]++;
            preqs.get(pair[1]).add(pair[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) if(needed[i] == 0) q.add(i);
        int completed = 0;
        
        while(!q.isEmpty()){
            int curr = q.poll();
            completed++;
            for(int neigh : preqs.get(curr)){
                needed[neigh]--;
                if(needed[neigh]==0) q.add(neigh);
            }
        }
        return completed == numCourses;
    }
}