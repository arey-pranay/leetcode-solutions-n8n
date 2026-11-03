class Solution {
    public int minMoves2(int[] nums) {
        int sum = 0;
        int n = nums.length;
        Arrays.sort(nums);
        int median = nums[n/2];
        int op = 0;
        for(int i =0;i<n;i++)op+=Math.abs(nums[i]-median);
        return op;
    }
}
