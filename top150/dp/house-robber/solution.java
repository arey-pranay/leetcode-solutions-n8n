class Solution {
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return use(nums,0);
    }
    public int use(int[] nums, int i){
        if(i>=nums.length) return 0;
        if(memo[i] == -1){
            int a= nums[i] + use(nums,i+2);
            int b= use(nums,i+1);
            memo[i] = Math.max(a,b);
        }
        return memo[i];
    }
    
}