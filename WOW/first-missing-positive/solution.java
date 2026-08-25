class Solution {
    public int firstMissingPositive(int[] nums) {
        // in every case answer is less than or equal to n+1.

        // hum shuru ke n numbers agr apni array me jama le

        // then agr sb jm gya to answer is n+1,

        // nhi jama to jo first empty hai, wo hua humara answer
        int n = nums.length;
        int iteration=0;
        for(int i=0;i<n;i++){
            int correctI = nums[i]-1;
            iteration++;
            while(nums[i]>0 && nums[i]<=n && nums[correctI] != nums[i]){
                iteration++;
                correctI = nums[i]-1;
                int temp = nums[i];
                nums[i] = nums[correctI];
                nums[correctI] = temp;
            }
        }
        System.out.println(iteration);
        for(int i=0;i<n;i++) if(nums[i] !=i+1) return i+1;
        return n+1;
    }
}
// 111
// 1000
// 1001
// 1011
// 1110