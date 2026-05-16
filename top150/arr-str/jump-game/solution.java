class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        return canReach(nums,n-1);
    }
    public boolean canReach(int[] nums, int e){
        if(e==0) return true;
        for(int i = e-1; i>=0;i--){
            if(i+nums[i]>=e) return canReach(nums,i);
        }
        return false;
    }
}