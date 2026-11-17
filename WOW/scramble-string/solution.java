class Solution {
    HashMap<String, Boolean> memo = new HashMap<>();
    // Input: s1 = "great", s2 = "rgeat"
    public boolean isScramble(String s1, String s2) {
      if(s1.equals(s2)) return true;
      if(s1.length() != s2.length()) return false;
      int n = s1.length();
      String key = s1+s2;
      if(memo.containsKey(key)) return memo.get(key);
      //   gr eat <-> rg eat
      for(int i=1;i<n;i++){
        // break and compare
        if(isScramble(s1.substring(0,i),s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))) {
            memo.put(key,true); return true;
        };
        
        // reverse after break. then compare
        if(isScramble(s1.substring(0,i),s2.substring(n-i)) && isScramble(s1.substring(i),s2.substring(0,n-i))) {
            memo.put(key,true); return true;
        };
      }
      memo.put(key,false); return false;
      
    }
}
// a | b | c | d | e