class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        func(candidates,target,0,new ArrayList<>());
        return ans;
    }
    public void func(int[] nums, int target, int index, List<Integer> curr){    
        if(target==0){ans.add(new ArrayList<>(curr)); return;}
        if(target < 0 || index == nums.length) return;

        curr.add(nums[index]);
        func(nums,target-nums[index],index,curr);
        curr.remove(curr.size()-1);
        
        func(nums,target,index+1,curr);
    }
}
