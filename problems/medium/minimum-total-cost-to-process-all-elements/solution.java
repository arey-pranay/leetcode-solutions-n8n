class Solution {
    public int minimumCost(int[] nums, int k) {
        int MOD = 1000000007;
        long count = 0;
        long d = k;
        for(int i =0 ; i<nums.length;i++){
            if(nums[i]>d){
                long n = (nums[i]-d + k-1L)/k;
                count = (count+n)%MOD;
                d +=(n*k);
            }
            d-=nums[i];
        }    
        return (int)((1L * count * (count + 1) / 2) % MOD);
    
    }
}
// // 1 2 3 4
// // 4
// // k=4

// // if(nums[i]<k) k-= nums[i]