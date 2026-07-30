class Solution {
    int[] arr;
    int[] memo;
    public int rob(int[] nums){
        arr = nums;
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        return func(0);
    }
    public int func(int i){
      if(i >= arr.length) return 0;
      if(memo[i]!=-1) return memo[i];
      return memo[i] = Math.max(arr[i]+func(i+2),func(i+1));
    }
}