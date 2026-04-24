class Solution {
    public int findMin(int[] nums) {
        return bs(0,nums.length-1,nums);
    }
    public int bs(int start, int end, int[] nums){
        if(start == end) return nums[start];
        int mid = start + (end-start)/2;
        if(nums[mid]>nums[end]) return bs(mid+1,end,nums);
        else return bs(start,mid,nums);
    }
}
