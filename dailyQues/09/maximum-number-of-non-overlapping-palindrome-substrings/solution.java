class Solution {
    int count =0;
    public int maxPalindromes(String s, int k) {
        if(k==1) return s.length();
        
        for(int i = 0 ; i<=s.length()-k;i++)
            if(isPali(s.substring(i,i+k))) {i+=k-1 ; count++;}
            else if(i<s.length()-k && isPali(s.substring(i,i+k+1))) {i+=k; count++;}
        
        return count;
    }
    public boolean isPali(String p){
        int i = 0;
        int j = p.length()-1;
        while(i<j){
            if(p.charAt(i)!=p.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}