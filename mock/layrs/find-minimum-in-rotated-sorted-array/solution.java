class Solution {
    public int findMin(int[] nums) {
      if(nums.length==0) return -1;
      return search(nums,0,nums.length-1);
    }
    public int search(int[] nums, int s, int e){
        if(s==e) return nums[e];
        int m = s + (e-s)/2;
        if(nums[m] < nums[e]) return search(nums,s,m); //eliminate end if end is greater
        else return search(nums,m+1,e);
    }
}