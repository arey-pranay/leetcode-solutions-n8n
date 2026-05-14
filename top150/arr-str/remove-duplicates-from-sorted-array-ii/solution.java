class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // if(nums[j] == nums[j-2]) // mtlb yahan pe chain bni hui hai, is j ke liye nayi value dhundhni hogi
        // we need to replace when nums[i] != nums[j-2]
        // so
        int j = 2;
        for(int i = 2; i<n; i++)if(nums[i] != nums[j-2]) nums[j++] = nums[i];
        return j;
    }
}
// 001011122

// 0001111222333344
// 0011111222333344
// 0011222222333344
// 0011223333333344
// 0011223344 444444

