class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i=0;i<32;i++){
            int bitCount = 0;
            for(int j =0; j<nums.length;j++){
                int num = nums[j];
                if((num& 1) == 1) bitCount++;
                nums[j] = num>>1;
            }
            if(bitCount%3 == 1){
               result += (1<<i);
            };
        }
        return result;
    }
}