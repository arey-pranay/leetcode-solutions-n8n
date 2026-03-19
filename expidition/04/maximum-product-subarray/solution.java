class Solution {
    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int ans = nums[0];
        int n = nums.length;
        for(int i=1;i<n;i++){
            int curr = nums[i];
            if(curr < 0){
                int temp= min;
                min =  max;
                max = temp;
            }
            max = Math.max(max*curr,curr);
            min = Math.min(min*curr,curr);
            ans = Math.max(max, ans);
        }
        return ans;
    }
}