class Solution {
    int[] memo;
    public int climbStairs(int n) {
        memo = new int[n+1];
        Arrays.fill(memo,-1);
        return func(n);
    }
    public int func(int curr){
        if(curr==0) return 1;
        if(curr<0) return 0;
        if(memo[curr] == -1) memo[curr] = func(curr-1) + func(curr-2);
        return memo[curr];
    }
}
// 2 = 11 , 2
// 3 = 111 , 12, 21, 
// 4 = 1111 , 22 , 121 , 1112, 2111, 1211, 
// 0 1 2 3 5 8 
// 0 1 1 2 3 5 8 13