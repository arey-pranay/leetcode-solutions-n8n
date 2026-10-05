class Solution {
    int i=0;
    public int scoreOfParentheses(String s) {
            return func(s);
        }
        public int func(String s){
            if (i == s.length() || s.charAt(i) == ')') return 0;
            int score=0;
            i++;
            if (s.charAt(i) == ')') {i++; score =1;}
            else { score = 2*func(s); i++; }
            return score + func(s);
        }
}

// class Solution {
//     public int scoreOfParentheses(String s) {
//         Stack<Integer> st = new Stack<>();
//         st.push(0);

//         for (char c : s.toCharArray()) {
//             if (c == '(') {
//                 st.push(0);
//             } else {
//                 int v = st.pop();
//                 int score = (v == 0) ? 1 : 2 * v;
//                 st.push(st.pop() + score);
//             }
//         }

//         return st.pop();
//     }
// }