class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        String[] arr = path.split("/");
        
        for(int i=arr.length-1; i>=0; i--){
            String curr = arr[i];
            if(curr.isEmpty() || curr.equals(".")) continue;
            else if(!st.isEmpty() && st.peek().equals("..") && !curr.equals("..")) st.pop();
            else st.push(curr);
        }
        
        while(!st.isEmpty() && st.peek().equals(".."))st.pop();
        
        StringBuilder sb = new StringBuilder("/");
        while(!st.isEmpty()){
            if(st.size() > 1) sb.append(st.pop() + "/");
            else sb.append(st.pop());
        }
        
        return sb.toString();
    }
}