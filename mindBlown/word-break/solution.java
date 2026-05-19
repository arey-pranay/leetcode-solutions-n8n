class Solution {
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        StringBuilder sb= new StringBuilder(s);
        HashSet<String> hs = new HashSet<>(wordDict);
        memo= new Boolean[s.length()+1];
        return func(0,0,sb,hs);
        
    }
    public boolean func(int i, int j, StringBuilder sb, HashSet<String> hs){
        if(i >= sb.length()) return true;
        if(j >= sb.length()) return false;
        if(memo[i] != null) return memo[i];
        if(hs.contains(sb.substring(i,j+1))){
            return memo[i] = (func(j+1,j+1,sb,hs) || func(i,j+1,sb,hs));
        } else return memo[i] = func(i,j+1,sb,hs);
    }
}