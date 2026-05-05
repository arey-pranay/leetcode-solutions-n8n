class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        
        int curr = 0;
        for(int i = 0;i<n;i++) curr += nums[i] * i;
        int ans = curr;
        
        int arrSum = 0;
        for(int i : nums) arrSum +=i;
        int offset = 0;
        
        while(offset<n){
            curr -= (n) * nums[n-1-offset];
            curr += arrSum;
            ans = Math.max(curr,ans);
            offset++;
        }
        return ans;
    }
}
