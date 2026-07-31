class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        for(int i=0;i<board.length;i++){
          for(int j=0; j<board[0].length; j++){
            if(board[i][j]=='.') continue;
            int num = board[i][j]-'1';
            int box = (i/3)*3 + (j/3);
            if(rows[i][num] || cols[j][num] || boxes[box][num]) return false;
            rows[i][num] = cols[j][num] = boxes[box][num] = true;
          }
        }
        return true;
    }
}
