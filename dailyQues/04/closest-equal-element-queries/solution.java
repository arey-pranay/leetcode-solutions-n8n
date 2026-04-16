class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        int n = nums.length;
        for(int i =0;i<n;i++){
            ArrayList<Integer> arr = hm.getOrDefault(nums[i],new ArrayList<>());
            arr.add(i);
            hm.put(nums[i],arr);
        }
        List<Integer> ans = new ArrayList<>();
        for(int query : queries){
            int val = nums[query];
            ArrayList<Integer> indices = hm.getOrDefault(val,new ArrayList<>());
            if(indices.size()<2) ans.add(-1);
            else{
                //agr query is 5. humaare indices ki indicesay me 5 konse index pe hai      
                int index = Collections.binarySearch(indices,query);
                // ab humko uske closes 2 index se dist check krna hai
                int minDist = Math.min(getAns(query,index-1,indices,n),getAns(query,index+1,indices,n));
                ans.add(minDist);
            }
        }
        return ans;
    }
    public int getAns(int query, int neigh, ArrayList<Integer> arr,int n){
        if(neigh==-1) neigh = arr.size()-1;
        if(neigh==arr.size()) neigh = 0;
        
        int d = Math.abs(query - arr.get(neigh));
        return Math.min(d,n-d);
    }
}