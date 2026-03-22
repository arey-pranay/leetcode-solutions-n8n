class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            switch(c){
                case ')':
                    if(!st.isEmpty() && st.peek() == '(') st.pop();
                    else return false;
                    break;
                    
                case ']':
                    if(!st.isEmpty() && st.peek() == '[' ) st.pop();
                    else return false;
                    break;
                    
                case'}' :
                    if(!st.isEmpty() && st.peek() == '{' ) st.pop();
                    else return false;
                    break;
                    
                default:
                    st.push(c);
                    break;
            }    
        }
        return st.isEmpty();
    }
}

// (][}{)}])