class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long min =Long.MAX_VALUE;
        long max =Long.MIN_VALUE;
        for(long i : nums){max = Math.max(max,i); min = Math.min(min,i);}
        return (max-min)*k;
    }
}