class Solution {
    int[] arr;
    int[] memo;
    int t;
    public int maximumJumps(int[] nums, int target) {
        arr = nums;
        t = target;
        memo = new int[nums.length];
        Arrays.fill(memo,-2);
        return func(0);
    }
    public int func(int i){
        int n  = arr.length;
        if(i==n-1) return 0;
        if(memo[i] != -2) return memo[i];
        
        int max = -1;
        for(int j=i+1; j<n; j++){
            if(Math.abs(arr[i]-arr[j]) <= t){
                int temp =func(j);
                if(temp != -1) max = Math.max(max,1+temp);
            }
        }
        
        return memo[i] = max;
    }
}
// nums =
// [1,0,2]
// target =
// 1

// Use Testcase
// Output
// -1
// Expected
// 1