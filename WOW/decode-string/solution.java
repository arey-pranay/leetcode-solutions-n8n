class Solution {
    int i=0;
    public String decodeString(String s) {
      StringBuilder ans = new StringBuilder();
      while(i<s.length()){
        if(s.charAt(i)==']') return ans.toString();

        if(Character.isLetter(s.charAt(i))) ans.append(s.charAt(i++));
        else{
            int k=0;
            while(Character.isDigit(s.charAt(i))) k = k*10 + s.charAt(i++)-'0';
            i++; //opening bracket
            String temp = decodeString(s);
            i++; //closing
            while(k-->0)ans.append(temp);
        }
    }
    return ans.toString();
  }
}
