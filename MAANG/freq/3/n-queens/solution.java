class Solution {
    boolean[][] borad;
    int N;
    List<List<String>> ans;
    public List<List<String>> solveNQueens(int n) {
        borad = new boolean[n][n];
        N=n;
        ans = new ArrayList<>();
        fill(0);
        return ans;
    }
    public void fill(int row){
        if(row==N){
            List<String> state = new ArrayList<>();
            for(int i=0;i<N;i++){
                StringBuilder sb = new StringBuilder("");
                for(int j=0;j<N;j++){
                    char c = borad[i][j] ? 'Q' : '.';
                    sb.append(c);
                }
                state.add(sb.toString());
            }
            ans.add(state);
            return;
        }
        
        for(int col=0;col<N;col++){
            if(rowColOk(row,col) && diagOk(row,col)){
                borad[row][col] = true;
                fill(row+1);
                borad[row][col] = false;
            }   
        }  
    }
    public boolean rowColOk(int row , int col){
        for(int i=0;i<N;i++) if(borad[i][col] || borad[row][i]) return false;
        return true;
    }
    public boolean diagOk(int row , int col){
        for(int i=0;i<=row;i++) for(int j=0;j<N;j++) if(Math.abs(i-row)==Math.abs(j-col)) if(borad[i][j]) return false;
        return true;
    }
}
