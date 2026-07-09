class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis = new boolean[21];
        func(new ArrayList<Integer>(), nums, vis);
        return ans;
    }
    private void func(List<Integer> curr, int[] nums, boolean[] vis){
        if(curr.size()==nums.length){
            ans.add(new ArrayList<>(curr)); 
            return;
        }
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            if(vis[num+10]) continue;
            vis[num+10] = true; curr.add(num);
            func(curr,nums,vis);
            curr.remove(curr.size()-1); vis[num+10] = false;
        }
    }  
}