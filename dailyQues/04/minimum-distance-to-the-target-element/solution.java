class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int i = 0;
        while(true){
            if(start-i >= 0) if(nums[start-i] == target) return i;
            if(start+i < n) if(nums[start+i] == target) return i;
            i++;
        }
    }
}
