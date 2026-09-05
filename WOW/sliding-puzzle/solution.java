class Solution { 
    int[] neigh = {-1,0,1,0,-1};
    int solvedHash,m,n;
    HashSet<Integer> hashes = new HashSet<>();
    public int slidingPuzzle(int[][] board) {
      m = board.length;
      n = board[0].length;
      int x =0,y=0;
      int[][] solved = new int[m][n];
      int num = 1;
      for(int i=0;i<m;i++) for(int j=0;j<n;j++){solved[i][j] = num++; if(board[i][j]==0){x=i;y=j;}}
      solved[m-1][n-1] = 0;  
      solvedHash = encode(solved);                                    
      return bfs(x,y,board);
    }
    public int bfs(int x, int y, int[][] board){
      int ans = Integer.MAX_VALUE;
      int startHash = encode(board);
      Queue<int[]> q = new LinkedList<>(); //x,y,moves,hash
      q.offer(new int[]{x,y,0,encode(board)});
      hashes.add(startHash);
      while(!q.isEmpty()){
        int[] curr = q.poll();
        int cx = curr[0], cy = curr[1], moves = curr[2], hash = curr[3];
        if(solvedHash == hash) return moves;
        int[][] newBoard = decode(hash);
        for(int i=0;i<4;i++){
            int X = cx+neigh[i];
            int Y = cy+neigh[i+1];
            if(X<0 || Y<0 || X>=m || Y>=n) continue;
            swap(newBoard,cx,cy,X,Y);
            int newHash = encode(newBoard);
            if(!hashes.contains(newHash)){
                hashes.add(newHash); 
                q.offer(new int[]{X,Y,moves+1,newHash});
            }
            swap(newBoard,cx,cy,X,Y);
        }
      }
      return -1;
    }
    public void swap(int[][] board, int i1, int j1, int i2, int j2){
      int temp = board[i1][j1];
      board[i1][j1] = board[i2][j2];
      board[i2][j2] = temp;
    }
    public int encode(int[][] board){
      int hash = 0;
      for(int i=0;i<m;i++) for(int j=0;j<n;j++){hash*=10; hash+= board[i][j];}
      return hash;
    }
    public int[][] decode(int hash){
        int[][] board = new int[m][n];
        for(int i=m-1;i>=0;i--) for(int j=n-1;j>=0;j--){board[i][j] = hash%10; hash/=10;}
        return board;
    }
}