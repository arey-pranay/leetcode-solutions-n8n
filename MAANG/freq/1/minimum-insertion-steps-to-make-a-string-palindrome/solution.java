class Solution {
    int[][] memo;
    public int minInsertions(String s) {
        int n = s.length(); memo = new int[n][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return n-lps(s,0,n-1);
    }
    public int lps(String s, int i, int j){
        if(memo[i][j]!=-1)return memo[i][j];
        if(i>j) return 0; if(i==j) return 1;
        if(s.charAt(i)==s.charAt(j)) return memo[i][j] = 2 + lps(s,i+1,j-1); //take
        return memo[i][j]=Math.max(lps(s,i,j-1),lps(s,i+1,j)); //notTake
    }
}
