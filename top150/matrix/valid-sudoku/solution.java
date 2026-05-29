class Solution {
    public boolean isValidSudoku(char[][] board) {
        return checkRows(board)&&checkCols(board)&& checkCells(board);
    }
    public boolean checkCells(char[][] board){
        for(int i=0;i<=6;i+=3){
            for(int j=0; j<=6; j+=3){
                if(!checkCell(board,i,j)) return false;
            }
        }
        return true;
    }
    public boolean checkCell(char[][] board, int x, int y){
        HashSet<Character> hs = new HashSet<>();
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(board[i][j] == '.') continue;
                if(hs.contains(board[i][j])) return false;
                hs.add(board[i][j]);
            }
        }
        return true;
    }
    public boolean checkCols(char[][] board){
        for(int i=0;i<9;i++){
            HashSet<Character> hs = new HashSet<>();
            for(int j =0;j<9;j++){
                if(board[i][j] == '.') continue;
                if(hs.contains(board[i][j])) return false;
                hs.add(board[i][j]);
            }
        }
        return true;
    }  
    public boolean checkRows(char[][] board){
        for(int i=0;i<9;i++){
            HashSet<Character> hs = new HashSet<>();
            for(int j =0;j<9;j++){
                if(board[j][i] == '.') continue;
                if(hs.contains(board[j][i])) return false;
                hs.add(board[j][i]);
            }
        }
        return true;
    }
}