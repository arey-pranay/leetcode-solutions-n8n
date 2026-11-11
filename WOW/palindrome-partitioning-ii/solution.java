class Solution {
    int[][] memo;
    boolean[][] palindromes;
    public int minCut(String s) {
        int n = s.length();
        memo = new int[n][n];
        palindromes = new boolean[n][n];
        for(int len=1;len<=n;len++)
            for(int i=0;i+len<=n;i++){
                int j = i+len-1;
                if(s.charAt(i)==s.charAt(j) && (len <=2 || palindromes[i+1][j-1] == true)) palindromes[i][j] = true;
            }
        
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(s,0,n-1);
    }
    private int func(String s, int i, int j){
        if(palindromes[i][j]) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        int min = Integer.MAX_VALUE;
        for(int k=i;k<=j;k++) if(palindromes[i][k]==true) min = Math.min(min, 1+func(s,k+1,j));
        return memo[i][j] = min;
    }
}