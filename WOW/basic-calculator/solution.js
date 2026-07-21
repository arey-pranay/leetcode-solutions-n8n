class Solution {
    int index = 0;
    public int calculate(String s) {
        int total=0, curr=0, sign = 1;
        while(index < s.length()){
            char c =  s.charAt(index);
            index++;
            if(c>='0' && c <= '9')  curr = (curr*10) + (c-'0');
            else if(c=='(') curr = calculate(s);
            else if(c==')') return total + (sign*curr); // kyonki function call hogya hai naye open bracket p
            else if(c=='+' || c=='-'){
                total += sign*curr;
                curr=0;
                sign = c=='+' ? 1 : -1;
            } 
        }
        return total + (sign*curr);
    }
}