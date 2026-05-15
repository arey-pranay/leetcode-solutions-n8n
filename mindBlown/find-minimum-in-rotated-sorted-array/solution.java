class Solution {
    public int findMin(int[] nums) {
        int start =0;
        int end = nums.length-1;
        
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[start] < nums[mid] &&  nums[mid] < nums[end]) return nums[start]; 
            else if(nums[end] < nums[mid]) start = mid+1;//mtlb right side sorted hai.
            else end = mid;  //left side sorted hai        
        }
        return nums[start];
        // esa number jiska left bhi bda hai aur right bhi bda
    }
}
//[3,1,2]