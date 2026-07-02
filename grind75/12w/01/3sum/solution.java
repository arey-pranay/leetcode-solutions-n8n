class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i =0 ; i<n;i++){
            if(i>0 && nums[i]==nums[i-1])continue;
            int j = i+1;
            int k = n-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum>0) k--;
                else if(sum < 0)j++;
                else{
                    ans.add(List.of(nums[i],nums[j++],nums[k--]));
                    while(j<k && nums[k]==nums[k+1])k--; // j aur k m se koi ek hi chalega check k liye
                }
            }   
        }
        return ans;
    }
}