class Solution {
    int n ;
    int[] memo ;
    public int jump(int[] nums) {
        n = nums.length;
        memo = new int[n];
        Arrays.fill(memo,10001);
        return func(0,nums);
    }
    public int func(int i , int[] nums){
        if(i>=n-1) return 0;
        if(memo[i]!=10001) return memo[i];
        for(int j=1;j<=nums[i];j++)  memo[i]=Math.min(memo[i],1+func(i+j,nums));
        return memo[i];
    }
}