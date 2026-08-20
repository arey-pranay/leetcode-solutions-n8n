class Solution {
    public void solveSudoku(char[][] board) {
        solveThis(board);
    }
    public boolean solveThis(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(char c = '1'; c<='9';c++){
                        if(isValid(board,i,j,c)){
                            board[i][j]=c;
                            if(solveThis(board)) return true;
                            board[i][j]='.';
                        }    
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(char[][] board, int i, int j, char c){
      for(int k=0;k<9;k++) if(board[i][k] == c || board[k][j] == c || board[3*(i/3) + k%3][3*(j/3) + k/3]==c) return false;
      return true;
    //   i/3 or j/3 only gives 0,1,2 => this tells the position of the 3x3 grid we are in
    //   we need the starting i and starting j of our 3x3 grid, so we multiply it by 3
    //   and to traverse all 9 elements, we need to add 00 01 02 10 etc to our starting i and starting j, so we use k/2 and k%3
    }
}