class Solution {
    Boolean [][] memo;
    public boolean canPartition(int[] nums) {
        int totSum = 0 ;
        for(int i : nums) totSum +=i;    
        if(totSum%2!=0) return false;
        memo = new Boolean[nums.length][totSum+1];
        return func(0,nums,totSum,totSum/2);
    }
    public boolean func(int i , int[] nums , int t ,int h){
        if(t == h) return true;
        if(i==nums.length) return false;
        if(memo[i][t] != null) return memo[i][t];
        return memo[i][t] =func(i+1,nums,t-nums[i],h) || func(i+1,nums,t,h);
    }
}
