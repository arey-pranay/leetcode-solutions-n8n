class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        int[] indeg = new int[n];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int arr[]:invocations){indeg[arr[1]]++; adj.get(arr[0]).add(arr[1]);}
        HashSet<Integer> sus = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        sus.add(k);
        while(!q.isEmpty()){
            int curr = q.remove();
            for(int neigh : adj.get(curr)){indeg[neigh]--; if(!sus.contains(neigh)){q.add(neigh); sus.add(neigh);}}
        }
        boolean canRemoveAll = true;
        List<Integer> rem = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(sus.contains(i) && indeg[i]>0){canRemoveAll = false; break;} else if(!sus.contains(i)) rem.add(i);
        }
        if(!canRemoveAll){
            List<Integer>ans = new ArrayList<>();
            for(int i=0;i<n;i++)ans.add(i);
            return ans;
        }
        return rem;

    }
}