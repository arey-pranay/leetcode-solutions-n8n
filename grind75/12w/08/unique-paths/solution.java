class Solution {
    int[][] memo;
    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(m-1,n-1);
    }
    public int func(int m, int n){
        if(m==0 && n==0) return 1;
        else if(m<0 || n<0) return 0;
        if(memo[m][n]!=-1) return memo[m][n];
        return memo[m][n] = func(m-1,n) + func(m,n-1);
    }
}