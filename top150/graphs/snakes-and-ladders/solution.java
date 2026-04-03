class Solution {
    int diceroll =0;
    public int snakesAndLadders(int[][] board) {
        return func(board);
    }
    public int func(int[][] board ){
        Queue<int[]> q= new LinkedList<>();
        q.add(new int[]{1,0});
        int n = board.length;
        boolean[] vis = new boolean[n*n + 1];
        while(!q.isEmpty()){
            int[] currPair = q.poll();
            int curr = currPair[0];
            int moves = currPair[1];
            if(curr == n*n) return moves;
            for(int i=1;i<=6 && curr+i <= n*n; i++){
                int next = curr+i;
                int[] rc = getCoordinates(next,n);
                if(board[rc[0]][rc[1]]!=-1) next = board[rc[0]][rc[1]];
                if(!vis[next]){
                    vis[next] =true;
                    q.offer(new int[]{next,moves+1});
                }
            }
        }
        return -1;
       
    }
   private int[] getCoordinates(int num, int n) {
        int row = n - 1 - (num - 1) / n;
        int col = (num - 1) % n;

        if (((num - 1) / n) % 2 == 1) {
            col = n - 1 - col;
        }
        return new int[]{row, col};
    }
}
// int n = board.length;
// int k =0 ;
// int[] flat = new int[n*n];
// for(int i =n-1 ; i>=0;i--){
//     for(int j = 0 ; j<n;j++){
//         flat[k] = board[i][j];
//         k++;
//     }
// }
// int dice = 0 ;
// for(int i =0 ; i<flat.length;i++){
//     for(int j = 0;j<6;j++){
//         if(flat[j+1] == -1) {
//             i+=6;
//             dice++;
//             break;
//         }
//         else{
//             i+=flat[i]-i;
//         }
//     }
// }
// public issnakeorladder(int current , int goto,int dice){
    
// }
