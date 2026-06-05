class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i=0;i<=31;i++){
            int ones = 0;
            for(int j=0; j<nums.length;j++){
                int lastbit = nums[j] & 1;
                if(lastbit==1) ones++;
                nums[j] >>= 1;
            }
            if(ones % 3 != 0) ans = ans | 1<<i;
        }
        return ans;
    }
}
// Inside the inner loop:
//  for(int num : nums){
//      num >>= 1;
//      does nothing useful because num is just a copy from the enhanced for-loop.
// therefore I used a normal for-loop instead of for-each loop.
