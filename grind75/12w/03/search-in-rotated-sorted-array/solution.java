class Solution {
    public int search(int[] nums, int target) {
      
        // agr target r se bhi bda hai, to left me dekho
        // agr l se bhi chhota hai to right me dekho
        
        // at every index, ya to left sorted hoga, ya right sorted hoga
        int start = 0;
        int end = nums.length-1;
        if(nums.length==1) return nums[0] == target ? 0 : -1;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(nums[start]==target) return start;
            if(nums[mid]==target) return mid;
            if(nums[end]==target) return end;
            
            if(nums[start] <= nums[mid]){//left sorted hai
                if(nums[start] <= target && target <= nums[mid]) end = mid-1; // mtlb left half me hai target
                else start = mid+1;
            } 
            else{ //right sorted hai
                if(nums[mid] <= target && target <= nums[end]) start = mid+1; // mtlb right half me hai target
                else end = mid-1;
            } 
        }
        return -1;
    }
}