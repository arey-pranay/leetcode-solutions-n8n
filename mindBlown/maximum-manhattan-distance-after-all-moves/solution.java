class Solution {
    public int maxDistance(String moves) {
       int us = 0;
       int ds = 0;
       int ls = 0;
       int rs = 0;
       int bs = 0;
       for(char c : moves.toCharArray()){
            if(c=='U') us++;
            else if(c=='D') ds++;
            else if(c=='L') ls++;
            else if(c=='R') rs++;
            else bs++;
       } 
       return Math.abs(us-ds)+Math.abs(ls-rs)+bs;
    }
}