class Solution {
    public boolean check(int[] nums) {
        boolean broke = false;
        for(int i=0;i<nums.length;i++) if(nums[i] > nums[(i+1) % nums.length]) if(broke) return false; else broke = true;
        return true;
    }
}