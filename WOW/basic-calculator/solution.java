class Solution {
    int index=0;
    public int calculate(String s) {
        int curr = 0;
        int total = 0;
        int sign = 1;
        while(index<s.length()){
            char c= s.charAt(index++);
            if(c==' '){continue;}
            else if(c=='(')curr = calculate(s); 
            else if(c==')') return total + (sign*curr);
            else if(c=='+' || c=='-'){
                total = total + (sign*curr);
                curr=0;
                sign = c=='+' ? 1 : -1;
            }
            else{   
                curr = (curr*10)+ (c-'0');
            }
        }
        return total + (sign*curr);
    }
}