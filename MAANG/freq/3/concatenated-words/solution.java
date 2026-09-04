class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
      Arrays.sort(words,(a,b) -> a.length()-b.length());
      List<String> ans = new ArrayList<>();
      HashSet<String> set = new HashSet<>();
      for(String word : words) {
        if(func(word,0,set, new Boolean[word.length()])) ans.add(word);
        set.add(word);
      }
      return ans;
    }
    public boolean func(String word, int start, HashSet<String> set, Boolean[] memo){
      int n = word.length();
      if(start==n) return true;
      if(memo[start] != null) return memo[start];
      for(int end=start;end<n;end++)if(set.contains(word.substring(start,end+1)) && func(word,end+1,set,memo)) return memo[start]= true;
      return memo[start]=false;
    }
}