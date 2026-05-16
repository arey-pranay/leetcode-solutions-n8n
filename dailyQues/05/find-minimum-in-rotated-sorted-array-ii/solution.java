class Solution {
    public int findMin(int[] nums) {
        int start =0;
        int end = nums.length-1;
        
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[start] < nums[mid] &&  nums[mid] < nums[end]) return nums[start]; 
            else if(nums[end] < nums[mid]) start = mid+1;//mtlb right side sorted hai.
            else if(nums[start] < nums[mid]) end = mid;  //left side sorted hai 
            else end--;  // mid end se bhi bda nhi hai, aur mid start se bhi bda nhi hai. mtlb end me redundant elements hai
        }
        return nums[start];
        // esa number jiska left bhi bda hai aur right bhi bda
    }
}
//[3,1,2]
// [3 3 1 3]