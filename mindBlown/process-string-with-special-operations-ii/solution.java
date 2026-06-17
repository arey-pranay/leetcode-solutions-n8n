class Solution {
    public char processStr(String s, long k) {
        long len=0;
        for(char c : s.toCharArray()){
            if(c=='*'){if(len>0) len--;}
            else if(c=='#') len*=2;
            else if(c=='%') continue;
            else len++;
        }
        if(k>len-1) return '.';
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c=='*')len++;
            else if(c=='#'){
                if(k>= len/2) k-=len/2; 
                len = len/2;
            }
            else if(c=='%') k = len-1 -k;
            else {if(k == len-1) return c; len--;}
        }
        return '.';
    }
}