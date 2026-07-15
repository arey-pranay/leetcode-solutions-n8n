class Solution {
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {      
        HashSet<String> hs = new HashSet<>(wordDict);
        memo = new Boolean[s.length()+1];
        return func(0,0,s,hs);
    }
    private boolean func(int i, int j, String s, HashSet<String> hs){
        if(i>= s.length()) return true;
        if(j>=s.length()) return false;
        if(memo[i] != null) return memo[i];
        boolean ans = func(i,j+1,s,hs);
        if(hs.contains(s.substring(i,j+1))) ans |= func(j+1,j+1,s,hs);
        return memo[i] = ans; 
    }
}