class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<Integer>();
        for (String s : tokens) {
            switch (s) {
                case "*":
                    st.push(st.pop() *  st.pop());
                    break;
                case "+":
                    st.push(st.pop() + st.pop());
                    break;
                case "-":
                    st.push( - (st.pop() - st.pop()));
                    break;
                case "/":
                    int a = st.pop();
                    int b = st.pop();
                    st.push(b / a);
                    break;
                default:
                    st.push(Integer.valueOf(s));
                    break;
            }
        }
        return st.pop();
    }
}
// -4, 5
// 3 - 3