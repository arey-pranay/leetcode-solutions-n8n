class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int ones = 0;
            for (int j = 0; j < nums.length; j++) {
                ones += nums[j] & 1;
                nums[j] >>= 1;
            }
            res |= (ones % 3) << i;  // place bit at position i directly
        }
        return res;
    }
}