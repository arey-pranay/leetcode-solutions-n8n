class Solution {
    // we need to have startIndex and maxLength to determine the lps from our string
    int startIndex = -1;
    int maxLength = 0;
    public String longestPalindrome(String s) {
        // for every index, explore the palindrome- even length and odd length.
        for(int i=0;i<s.length();i++){check(i,i+1,s);check(i-1,i+1,s);}
        return s.substring(startIndex,startIndex+maxLength);
    }
    public void check(int i, int j, String s){
        int n = s.length();
        while(i>=0 && j<n && s.charAt(i)==s.charAt(j)){i--;j++;}
        if(j-i-1>maxLength){maxLength = j-i-1; startIndex = i+1;}
    }
}