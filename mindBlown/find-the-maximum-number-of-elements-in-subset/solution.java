class Solution {
    HashSet<Integer> singles = new HashSet<>();
    HashSet<Integer> doubles = new HashSet<>();
    int ans = 1;
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int ones = 0;
        for(int i=0;i<n;i++){
            if(nums[i]==1) ones++;
            if(!singles.contains(nums[i])) singles.add(nums[i]);
            else {singles.remove(nums[i]); doubles.add(nums[i]);}
        }
        ones = ones%2 == 0 ? ones-1 : ones;
        ans = Math.max(ans, ones);
        for(int num : doubles){
            if(num==1) continue;
            func(2,(int)Math.pow(num,2));
        }
        return ans;
    }
    public void func(int curr, int toFind){
        if(!singles.contains(toFind) && !doubles.contains(toFind)) return;
        ans = Math.max(ans,curr+1);
        if(doubles.contains(toFind))func(curr+2, (int)Math.pow(toFind,2));
    }
}
    // 2 and find 2
    // 22 and find 4
    // 242 and return 
    // 2442 and find 16}
    
     // 1 2 2 2 2 4 5
        // int ans = 1;
        // except 1 number, all others should come twice
        // odd length subset
        // maxLength = 1 + (2 baar aane wale numbers)
        // exact = maxLength - invalid pairs
        // a b c d c b a
        // a b c b a
        // a b a
        // a