class Solution {
    public boolean isGood(int[] nums) {
       int[] count = new int[201];
       int n= nums.length;
       for(int i = 0;i<n;i++) count[nums[i]]++;
       
       int rem = n;
       for(int i=1;i<=200;i++){
         if(count[i] == 2){
            if(rem==2) return true;
            else return false;
         }
         if(count[i] == 1) rem--;
         else return false;
       }
       return false;
    }
}