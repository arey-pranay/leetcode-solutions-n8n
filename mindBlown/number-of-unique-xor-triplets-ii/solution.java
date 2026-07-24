class Solution {
    // 1024 < 1500 < 2048;
    public int uniqueXorTriplets(int[] nums) {
        int max = 2048;
        boolean[] pairXors = new boolean[max];
        boolean[] tripletXors = new boolean[max];
        int n =nums.length;
        int ans = 0;
        for(int i=0;i<n;i++) for(int j=i;j<n;j++) pairXors[nums[i]^nums[j]]=true;
        for(int i=0;i<2048;i++){
            for(int j=0;j<n;j++){
                if(pairXors[i] && !tripletXors[i^nums[j]] )  {
                    tripletXors[i^nums[j]] = true;
                    ans++;
                }
            }
        }
        return ans;
    }
}