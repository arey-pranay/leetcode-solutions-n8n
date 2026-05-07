class Solution {
    String a;
    String b;
    String c;
    Boolean[][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        a=s1;
        b=s2;
        c=s3;
        memo = new Boolean[s1.length()+1][s2.length()+1];
        if(s1.length() + s2.length() != s3.length()) return false;
        return func(0,0,0);
    }
    public boolean func(int i, int j,int k){
        if(i==a.length() && j==b.length()) return true;
        if(memo[i][j]!=null) return memo[i][j];
        boolean ans = false;
        if(i<a.length() && a.charAt(i) == c.charAt(k)) ans = func(i+1,j,k+1);
        if(j<b.length() && b.charAt(j) == c.charAt(k)) ans |= func(i,j+1,k+1);
        return memo[i][j] = ans;
        
    }
}
