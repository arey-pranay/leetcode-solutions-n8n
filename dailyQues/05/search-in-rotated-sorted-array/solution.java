class Solution {
    public int search(int[] nums, int target) {
        //binary search. find target. 
        int start = 0;
        int end = nums.length-1;
        if(nums.length==1 && nums[0]==target) return 0;
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[mid]==target) return mid;
            if(nums[start]==target) return start;
            if(nums[end]==target) return end;

            
            if(nums[start] <= nums[mid]){ //left side sorted
                if(target>= nums[start]  && target < nums[mid]) end = mid-1; // search target between s and m
                else start = mid+1; // eliminate left side
            } else{ //right side sorted
                if(target <= nums[end] && target > nums[mid]) start = mid+1; // search target between m and e
                else end = mid-1; // eliminate right side
            }
        }
        return -1;
    }
}