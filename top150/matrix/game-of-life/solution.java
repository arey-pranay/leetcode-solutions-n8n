class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
        int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
        
        for(int i = 0 ; i<m;i++){
            for(int j = 0 ;j<n;j++){
                int count = 0;
                for(int k=0;k<8;k++){
                    int X = i + dx[k];
                    int Y = j + dy[k];
                    if(X<0 || Y<0 || X>=m || Y>=n) continue;
                    if(board[X][Y] == 1 || board[X][Y] == 3) count++;
                }
                if(board[i][j]==1 && (count==2 || count==3)) board[i][j] = 3; 
                else if(count == 3) board[i][j] = 2; 
            } 
        }
        for(int i = 0 ; i<m;i++)
            for(int j = 0 ;j<n;j++)
                if(board[i][j]==2 || board[i][j]==3) board[i][j]=1; 
                else board[i][j] = 0;
                
    }
}

