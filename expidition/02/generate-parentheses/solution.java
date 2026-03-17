class Solution {
    List<String> res = new ArrayList<>();
    int n;
    public List<String> generateParenthesis(int N) {
        StringBuilder s = new StringBuilder("");
        int open = 0;
        int close = 0;
        n = N;
        func(s,0,0);
        return res;
    }
    public void func(StringBuilder s, int open, int close){
        if(s.length()==n*2){
            res.add(s.toString());
            return ;
        }
        if(open < n){
            func(s.append('('),open+1,close);
            s.deleteCharAt(s.length()-1);
        }
        if(close < open){
            func(s.append(')'),open,close+1);
            s.deleteCharAt(s.length()-1);    
        }
        return;    
    }
}
