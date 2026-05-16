class Solution {
    Integer[] memo;
    public int jump(int[] nums) {
        int n = nums.length;
        if(n==1) return 0;
        int curr = 0;
        int max = 0;
        int jumps = 0;
        for(int i=0;i<n-1;i++){
            max = Math.max(max, i+nums[i]);
            if(i==curr){
                jumps++;
                curr = max;
            }
        }
        return jumps;
        // memo = new Integer[n];
        // return canReach(nums,n-1);
    }
    // public int canReach(int[] nums, int e){
    //     if(e==0) return 0;
    //     if(memo[e]!=null) return memo[e];
    //     for(int i = 0; i<e;i++) if(i+nums[i]>=e) return memo[e] = 1+canReach(nums,i);
    //     return 0;
    // }
}