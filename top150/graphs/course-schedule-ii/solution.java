class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int pointer = 0;
        if(prerequisites.length==0){
            for(int i=0;i<numCourses;i++) ans[i] = i;
            return ans;
        }
        
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        int[] needed = new int[numCourses];
        for(int[] pair : prerequisites){
            needed[pair[0]]++;
            preqs.get(pair[1]).add(pair[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) if(needed[i]==0){
            q.add(i);
            ans[pointer++] = i;
        }
        int finished = 0;
        if(q.isEmpty()) return new int[]{};
        
        while(!q.isEmpty()){
            int course = q.remove();
            finished++;
            for(int i : preqs.get(course)){
                needed[i]--;
                if(needed[i] == 0){
                    q.add(i);
                    ans[pointer++] = i;
                }
            }
        }
        return pointer == numCourses ? ans : new int[]{};
    }
}