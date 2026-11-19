class Solution {
    int[] arr;
    int[][] memo;
    public int maxCoins(int[] nums) {
        int n = nums.length; arr = new int[n+2]; arr[0] = 1; int j=1;
        for(int i=0;i<n;i++) if(nums[i]!=0) arr[j++] = nums[i];
        arr[j++] = 1; 
        memo = new int[j][j]; for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(0,j-1);
    }
    private int func(int left, int right){
        int max = 0;
        if(left == right-1) return 0;
        if(memo[left][right] != -1) return memo[left][right];
        for(int i=left+1;i<right;i++){
            max = Math.max(max, arr[i]*arr[left]*arr[right] + func(left,i) + func(i,right)); //ye number last me udaayege, then only boundary will remain
        }
        return memo[left][right]=max;
    }
}