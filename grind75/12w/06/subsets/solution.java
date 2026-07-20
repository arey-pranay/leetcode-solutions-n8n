class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums){
        func(nums,0,new ArrayList<>());
        return ans;
    }
    public void func(int[] nums, int i, List<Integer> curr){           
        if(i==nums.length) {ans.add(new ArrayList<>(curr)); return;}
        func(nums,i+1,curr);
        curr.add(nums[i]);
        func(nums,i+1,curr);
        curr.remove(curr.size()-1);
    }
}