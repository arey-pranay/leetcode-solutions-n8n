class Solution {
    public int minMoves(int[] nums) {
        // incrementing n-1 numbers is same as decrementing 1. so we need to make all equal to min
        int min = Integer.MAX_VALUE, ans = 0;
        for(int num : nums) min = Math.min(min,num);
        for(int num : nums) ans += num-min;

        // another thing is to return arrSum - (n*min)
        return ans;
    }
}