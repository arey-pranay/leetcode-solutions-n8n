class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] preProds = new int[n];
        int[] suffProds = new int[n];
        preProds[0] = 1;
        suffProds[n-1] = 1;
        for(int i=1;i<n;i++) preProds[i] = preProds[i-1]*nums[i-1];
        for(int i=n-2;i>=0;i--) suffProds[i] = suffProds[i+1]*nums[i+1];        
        for(int i=0;i<n;i++)nums[i] = preProds[i]*suffProds[i];
        return nums;
    }
}