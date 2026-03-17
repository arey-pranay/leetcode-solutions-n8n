class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0) return true;
        
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        int[] needed = new int[numCourses];
        for(int[] pair : prerequisites){
            needed[pair[0]]++;
            preqs.get(pair[1]).add(pair[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) if(needed[i]==0) q.add(i);
        int finished = 0;
        if(q.isEmpty()) return false;
        
        while(!q.isEmpty()){
            int course = q.remove();
            finished++;
            
            for(int i : preqs.get(course)){
                needed[i]--;
                if(needed[i] == 0){
                    q.add(i);
                }
            }
            
        }
        return finished == numCourses;
    }
}
