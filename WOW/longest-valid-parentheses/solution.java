class Solution {
    public int longestValidParentheses(String s) {
      int open = 0,close =0,max = 0;
      int n = s.length();
      for(int i=0;i<n;i++){
        if(s.charAt(i)=='(') open++;
        else{
            close++;
            if(open==close) max = Math.max(max,2*open);
        }
        if(close > open) {open=0;close=0;}
      }
      open=close=0;
       for(int i=n-1;i>=0;i--){
        if(s.charAt(i)==')') close++;
        else{
            open++;
            if(open==close) max = Math.max(max,2*open);
        }
        if(close < open) {open=0;close=0;}
      }
      return max;
    }
}