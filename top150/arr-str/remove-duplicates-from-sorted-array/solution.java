class Solution {
    public int removeDuplicates(int[] nums) {
        int done = 0;
        int checking = 1;
        while(checking<nums.length){
            if(nums[done]!=nums[checking]){
                int temp = nums[done+1];
                nums[done+1] = nums[checking];
                nums[checking] = temp;
                done++;
            }
            checking++;
        }
        return done+1;
    }
}