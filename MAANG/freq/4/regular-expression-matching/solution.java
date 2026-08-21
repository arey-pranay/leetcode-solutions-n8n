class Solution {
    Boolean memo[][];
    public boolean isMatch(String s, String p) {
      memo = new Boolean[s.length()+1][p.length()+1];
      return func(0,0,s,p);
    }
    public boolean func(int i, int j, String s, String p){
      if(j==p.length()) return i==s.length();
      if(memo[i][j] != null) return memo[i][j];
      boolean matched = i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j));
      if(j< p.length()-1 && p.charAt(j+1)=='*'){
        if(matched) return memo[i][j] = func(i,j+2,s,p) || func(i+1,j,s,p);
        return memo[i][j] = func(i,j+2,s,p);
      }
      if(matched) return memo[i][j] = func(i+1,j+1,s,p);
      return memo[i][j] = false;
    }
}