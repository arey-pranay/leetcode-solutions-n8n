class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int i=0;
        int j = 1;
        int min = Integer.MAX_VALUE;
        int curr = nums[i];
        while(i<n){
            while(curr >= target){
                min = Math.min(min,j-i);
                curr -= nums[i++];
            }
            if(j>=n) break;
            curr += nums[j];
            j++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}