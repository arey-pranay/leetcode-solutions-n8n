class Solution {
    int ans = 0;
    int N;
    boolean[][] board;
    public int totalNQueens(int n) {
        N=n;
        board = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = false;
            }
        }
        traverseRow(0);
        return ans;
    }
    
    private void traverseRow(int row){
        if(row==N){ans++; return;}
        for(int col=0;col<N;col++){
            if(isSafe(row,col)){
                board[row][col]=true;
                traverseRow(row+1);
                board[row][col]=false;
            }
        }
    }
    private boolean isSafe(int x, int y){
        int i, j; //checking | ans -
        for(i=0; i<N; i++) if(board[i][y] || board[x][i]) return false;

        i=x; j=y; // checking /
        while(i>=0 && j<N) if(board[i--][j++]) return false;

        i=x; j=y; // checking \
        while(i>=0 && j>=0) if(board[i--][j--]) return false;

        return true;
    }
}

// Solution s = new Solution();
// while(i<1000){s.totalNQueens(tc[i])==expected[i];}