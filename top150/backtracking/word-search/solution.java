class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == word.charAt(0)){
                    boolean[][] vis = new boolean[m][n];
                    if(func(i,j,board,vis,word,1)) return true;
                }
            }
        }
        return false;
    }
    public boolean func(int x , int y, char[][] board, boolean[][] vis, String word, int index){
        if(index == word.length()) return true;
        int m = board.length;
        int n = board[0].length;  
        vis[x][y] = true;
        int[] neighs = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X >= m || Y >= n) continue;
            if(board[X][Y] == word.charAt(index) && !vis[X][Y]){
                if(func(X,Y,board,vis,word,index+1)) return true;
                else vis[X][Y] = false;
            }
            
        }
        return false;
    }
}