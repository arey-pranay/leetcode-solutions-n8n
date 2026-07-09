class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] vis = new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!vis[i][j] && board[i][j]==word.charAt(0)){
                    vis[i][j] = true;
                    if(func(board,word,1,vis,i,j)) return true;
                    vis[i][j] = false;
                }
            }
        }
        return false;
    }
    public boolean func(char[][] board, String word, int currI, boolean[][] vis, int x, int y){
        if(currI==word.length()) return true;
        int[] neighs = new int[]{-1,0,1,0,-1};
        for(int i=0;i<4;i++){
            int X = x + neighs[i];
            int Y = y + neighs[i+1];
            if(X<0 || Y<0 || X==board.length || Y==board[0].length) continue;
            if(!vis[X][Y] && board[X][Y]==word.charAt(currI)){
                vis[X][Y] = true;
                if(func(board,word,currI+1,vis,X,Y)) return true;
                vis[X][Y] = false;
            }
        }
        return false;
    }
}