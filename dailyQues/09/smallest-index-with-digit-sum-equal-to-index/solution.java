class Solution {
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++) if(digitSum(nums[i])==i) return i;
        return -1;
    }
    public int digitSum(int num){
        int sum=0;
        while(num>0){
            int rem = num%10;
            num/=10;
            sum += rem;
        }
        return sum;
    }
}