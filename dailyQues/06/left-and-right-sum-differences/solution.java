class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for(int i=0;i<n;i++) ans[i] += i==0 ? 0 : nums[i-1] + ans[i-1];
        
        int sum = 0;
        for (int i = n - 1; i >= 0; --i) { ans[i] = Math.abs(ans[i] - sum); sum += nums[i]; }
        
        return ans;
    }
}