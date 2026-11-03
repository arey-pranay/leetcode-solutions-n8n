class Solution {
    public int minMoves(int[] nums) {
        int max = 0, sum=0;
        for(int num : nums){sum+=num; max=Math.max(max,num);}
        return nums.length*max-sum;
    }
}