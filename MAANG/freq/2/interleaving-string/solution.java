class Solution {
    int m,n,o;
    Boolean[][][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length(); n = s2.length(); o = s3.length();
        if(m+n!=o) return false;   
        memo = new Boolean[m+1][n+1][o+1];
        return func(0,0,0,s1,s2,s3);
    }
    public boolean func(int i,int j, int k, String s1, String s2, String s3){
        if(k==o) return memo[i][j][k] = true;
        if(memo[i][j][k] != null) return memo[i][j][k];
        if(i<m && s1.charAt(i)==s3.charAt(k) && func(i+1,j,k+1,s1,s2,s3)) return memo[i][j][k] = true;
        if(j<n && s2.charAt(j)==s3.charAt(k) && func(i,j+1,k+1,s1,s2,s3)) return memo[i][j][k] = true;
        return memo[i][j][k] = false;
    }
}
// s1 = 
// s2 = 
// s3 = 