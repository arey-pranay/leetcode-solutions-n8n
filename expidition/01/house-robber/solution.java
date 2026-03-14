class Solution {
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return func(nums,0);
    }
    public int func(int[] nums, int i){
        if(i>=nums.length) return 0;
        if(memo[i] != -1) return memo[i];
        
        int a = func(nums,i+1);
        int b = nums[i] + func(nums,i+2);
        memo[i] = Math.max(a,b);
        
        return memo[i];
    }
}