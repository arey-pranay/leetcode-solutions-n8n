class Solution {
    public int removeElement(int[] nums, int val) {
        int s = 0;
        int e = nums.length-1;
        while(s<=e){
            if(nums[e] == val){
                e--;
                continue;
            }
            if(nums[s] == val){
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
            } 
            s++;
        }
        return s;
    }
}