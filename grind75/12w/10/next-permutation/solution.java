class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length; 
        int i = n-2;
        while(i>=0 && nums[i] >= nums[i+1]) i--;
        int j=n-1;
        if(i>=0){
          while(j>=0 && nums[i] >= nums[j]) j--;
          swap(nums,i,j);
        }
        reverse(nums, i+1);
    }
    public void reverse(int[] arr, int i){
      int j = arr.length-1;
      while(i<j) swap(arr,i++,j--);
    }
    public void swap(int[] arr, int i, int j){
      int temp = arr[i];
      arr[i]= arr[j];
      arr[j] = temp;
    }
}