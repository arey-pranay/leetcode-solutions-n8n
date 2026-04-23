class Solution {
    public int findPeakElement(int[] nums){
        int left = 0;
        int right = nums.length-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid] < nums[mid+1]) left = mid+1;
            else right = mid;
        }
        return left;
        //agar array bdhti hi rahi to last element hai peak. agr bdhti nhi rahi to peak beech me aayega mid aur right ke
        //agar array kam hoti hi rahi to first element hai peak. agr kamti nhi rahi to peak beech me aayega mid aur left ke
    }
}
//  [1,2,1,3,5,6,4,2] 