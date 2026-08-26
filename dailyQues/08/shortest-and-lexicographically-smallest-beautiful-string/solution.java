class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
      int n = s.length();
      int r = 0;
      int l=0;
      int cnt=0;
      String ans = "";
      while(r<n){
        if(s.charAt(r) == '1')cnt++;
        if(cnt > k){
          while(s.charAt(l) == '0') l++;
          l++;cnt--;
        }
        if(cnt==k){
         while(l<r && s.charAt(l)=='0') l++;
          int currLength = r-l+1;
          String curr = s.substring(l,r+1);
          if(ans.isEmpty() || currLength < ans.length() || (currLength==ans.length()&&curr.compareTo(ans)<0)) ans = new String(curr);
        }
        r++;
      }
      return ans;
    }
}