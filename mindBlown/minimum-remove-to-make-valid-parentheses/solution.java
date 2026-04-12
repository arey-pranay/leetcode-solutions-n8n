class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        int i=0;
        for(char c : sb.toString().toCharArray()){
            switch(c){
                case '(':
                    st.push(i);
                    break;
                case ')':
                    if(st.isEmpty()) sb.deleteCharAt(i--);
                    else st.pop();
                    break;
                default:
                    break;
            }
            i++;
        }
        while(!st.isEmpty()) sb.deleteCharAt(st.pop());
        return sb.toString();
    }
}
