class Solution {
    int start;
    int maxLength;
    public String longestPalindrome(String s) {
        for(int i=0;i<s.length();i++){
           check(i,i+1,s);
           check(i-1,i+1,s);
        }
        return s.substring(start,start+maxLength);
    }
    public void check(int i, int j, String s){
        int n = s.length();
        while(i>=0 && j<n && s.charAt(i) == s.charAt(j)) {i--;j++;}
        int length = j-i-1; //(j-i+1-2) because loop breaks on false condition, where i and j have already moved 1 extra step
        if(length > maxLength){
            start = i+1;
            maxLength = length;
        }
    }
}