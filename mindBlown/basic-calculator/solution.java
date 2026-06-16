class Solution {
    int idx=0; // this index traverse the string in one pass, between different level of recursion
    public int calculate(String s) {
       int res = 0, num = 0, sign = 1;
        while (idx < s.length()) {
            char c = s.charAt(idx++);
            if (c >= '0' && c <= '9') num = num * 10 + c - '0';
            else if (c == '(') num = calculate(s); // ( is start of a new sub-problem, Let recursion solve the sub-problem
            else if (c == ')') return res + (sign * num);
            else if (c == '+' || c == '-') { // only when we meet a new sign, we know a while number has been read
                res = res + (sign * num);
                num = 0;
                sign = c == '-' ? -1 : 1;
            }
        }
        return res + (sign * num); // last number is not processed yet
    }
}

// class Solution {
//     public int calculate(String s) {
//         int n = s.length();
//         Stack<String> st = new Stack<>();
//         for(int i=0;i<n;i++){
//             char c= s.charAt(i);
//             switch(c){
//                 case ' ':        // space gayi gadhhe me 
//                     break;
//                 case '(':
//                     st.push(String.valueOf(c));
//                     break;
//                 case ')':
//                     StringBuilder sb = new StringBuilder("");  
//                     while(!st.peek().equals("(")) sb.insert(0,st.pop());
                    
//                     st.pop();
//                     String num2 = String.valueOf(calcNum(sb.toString()));
//                     if(st.isEmpty()) st.push(num2);
//                     else if(st.peek().equals("+")){
//                         st.pop(); 
//                         st.push(String.valueOf( (Integer.parseInt(num2) + (Integer.parseInt(st.pop())))));
//                     }
//                     else if(st.peek().equals("-")){
//                         st.pop();
//                         if(st.isEmpty() || st.peek().equals("(")) st.push(String.valueOf(-Integer.parseInt(num2)) );
//                         else st.push( String.valueOf( Integer.parseInt(st.pop()) - Integer.parseInt(num2)) );
//                     }
//                     else st.push(String.valueOf(num2));
                    
//                     break;
//                 default:
//                     if(Character.isDigit(c)){
//                         StringBuilder sb2 = new StringBuilder();
//                         while(i < s.length() && Character.isDigit(s.charAt(i))) sb2.append(s.charAt(i++));
//                         i--;
//                         String num = sb2.toString();
//                         if(st.isEmpty()) st.push(num);
//                         else if(st.peek().equals("+")){
//                             st.pop(); 
//                             st.push(String.valueOf( (Integer.parseInt(num) + (Integer.parseInt(st.pop())))));
//                         }
//                         else if(st.peek().equals("-")){
//                             st.pop();
//                             if(st.isEmpty() || st.peek().equals("(")) st.push("-"+num);
//                             else st.push( String.valueOf( Integer.parseInt(st.pop()) - Integer.parseInt(num)) );
//                         }
//                         else st.push(String.valueOf(num));
//                     }else st.push(String.valueOf(c));
//                     break;   
//             }         
//         }
//         StringBuilder temp = new StringBuilder("");        
//         while(!st.isEmpty()) temp.insert(0,st.pop());
//         return calcNum(temp.toString());
//     }
// public int calcNum(String s){
//     int ans = 0;
//     char op = ' ';

//     for(int i=0;i<s.length();i++){
//         if(s.charAt(i)=='-'){
//             if(i==0 || s.charAt(i-1)=='+' || s.charAt(i-1)=='-'){
//                 StringBuilder sb = new StringBuilder("-");
//                 i++;
//                 while(i<s.length() && Character.isDigit(s.charAt(i))) sb.append(s.charAt(i++));
//                 i--;

//                 int curr = Integer.parseInt(sb.toString());

//                 if(op=='-') ans -= curr;
//                 else if(op=='+') ans += curr;
//                 else ans = curr;
//             }
//             else{
//                 op='-';
//             }
//         }
//         else if(s.charAt(i)=='+'){
//             op='+';
//         }
//         else{
//             StringBuilder sb = new StringBuilder();

//             while(i<s.length() && Character.isDigit(s.charAt(i)))
//                 sb.append(s.charAt(i++));

//             i--;

//             int curr = Integer.parseInt(sb.toString());

//             if(op=='-') ans -= curr;
//             else if(op=='+') ans += curr;
//             else ans = curr;
//         }
//     }

//     return ans;
// }
// }

// // "2-4-(8+2-6+(8+4-(1)+8-10))"

