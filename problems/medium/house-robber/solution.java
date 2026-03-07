class Solution {
   int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robRecursion(nums, 0);
    }
    public int robRecursion(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }
        if(memo[i] != -1) return memo[i];
        int take = nums[i] + robRecursion(nums, i + 2);
        int skip = robRecursion(nums, i + 1);
        return memo[i] = Math.max(take, skip);
    }

//  public int rob(int[] nums) {
//         int[] dp = new int[nums.length];
//         dp[0] = nums[0];

//         for(int i=1; i<nums.length; i++){
//             int take = nums[i];
//             if(i>1) take+= dp[i-2];

//             int notTake = dp[i-1];

//             dp[i] = Math.max(take, notTake);
//         }

//         return dp[nums.length-1];
//     }
}
