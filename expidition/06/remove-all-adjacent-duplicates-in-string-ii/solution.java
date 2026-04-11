class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(!st.isEmpty() && st.peek()[0]==c){
                st.peek()[1]++;
                if(st.peek()[1] == k) st.pop();
            } else{
                st.push(new int[]{c,1});
            }
        }
        StringBuilder sb = new StringBuilder("");
        while(!st.isEmpty()){
            int[] temp = st.pop();
            for(int i=0;i<temp[1];i++){
                sb.append((char)temp[0]);
            }
        }
        return sb.reverse().toString();
    }
    
}

