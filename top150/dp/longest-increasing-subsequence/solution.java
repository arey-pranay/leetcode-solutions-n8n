class Solution {
    int[] memo;
    public int lengthOfLIS(int[] nums) {
        int ans = Integer.MIN_VALUE;
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        for(int i = 0 ; i<nums.length;i++) ans = Math.max(ans,func(nums,i));
        return ans;
    }
    public int func(int[] nums, int i){
        if(memo[i] != -1) return memo[i];
        int max = 1;
        for(int j=0;j<i;j++){
            if(nums[j] < nums[i]){
                int temp = 1 + memo[j];
                max = Math.max(max,temp);
            }
        }
        return memo[i] = max;
    }
}