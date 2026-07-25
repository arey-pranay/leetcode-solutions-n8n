class Solution {
    boolean[][] vis;
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        vis = new boolean[m][n];
         for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
               if(board[i][j] == word.charAt(0)){
                 vis[i][j] = true;
                 if(func(board,word,1, i,j))return true;
                 vis[i][j] = false;
               }
             }
          }
        return false;
    }
    public boolean func(char[][] board, String word, int index,int x , int y) {
      if(index==word.length()) return true;
      int[] neighs = new int[]{-1,0,1,0,-1};
      for(int i =0;i<4;i++){
        int X = x + neighs[i];
        int Y = y + neighs[i+1];
        if(X<0 || Y<0 || X==m || Y==n) continue;
        if(board[X][Y] == word.charAt(index) && !vis[X][Y]){
             vis[X][Y] = true;
             if(func(board,word,index+1,X,Y)) return true;
             vis[X][Y] = false;
        }
      } 
      return false;
    }
}