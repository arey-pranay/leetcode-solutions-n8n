class Solution {
    public int myAtoi(String s) {
        int sign = 1;
        int ans = 0;
        int i=0;
        s = s.trim(); if(s.isEmpty()) return 0;
        if(s.charAt(i) == '+' || s.charAt(i)=='-'){
            if(s.charAt(i)=='-') sign = -1;
            i++;
        }    
        while(i<s.length() && (s.charAt(i)>='0' && s.charAt(i)<='9')){
            // 2,147,483,647 aakhir ki value 7 hai na
            if(ans > Integer.MAX_VALUE/10 || (ans==Integer.MAX_VALUE/10 && s.charAt(i)-'0'>7)){
                if(sign == -1) return Integer.MIN_VALUE;
                else return Integer.MAX_VALUE;
            }
            char c = s.charAt(i);
            ans *= 10;
            int digit = c-'0';
            ans += digit;
            i++;
        }
        return ans*sign;
    }
}