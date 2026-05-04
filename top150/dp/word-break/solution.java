class Solution {
    Boolean[] memo;
    HashSet<String> hs;
    public boolean wordBreak(String s, List<String> wordDict) {
        hs = new HashSet<>(wordDict);
        memo=new Boolean[s.length()+1];
        return func(s,0);
    }
    
    public boolean func(String s, int start){
        if(start==s.length()) return true;
        if(memo[start] != null) return memo[start];
        for(int i=start+1;i<=s.length();i++) if(hs.contains(s.substring(start,i)) && func(s,i)) return memo[start]=true;
        return memo[start] = false;
    }

}
