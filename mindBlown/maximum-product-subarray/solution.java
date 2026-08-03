class Solution {
    public int maxProduct(int[] nums) {
        int product = 1;
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        for(int i=0;i<n;i++){
            product *= nums[i];
            ans = Math.max(ans,product);
            if(product == 0) product = 1;
        }
        product=1;
        for(int i=n-1;i>=0;i--){
            product *= nums[i];
            ans = Math.max(ans,product);
            if(product == 0) product = 1;
        }
        return ans;  
    }
}

