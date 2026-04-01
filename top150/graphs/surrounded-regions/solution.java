class Solution {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;

        // Step 1: Mark boundary connected O's
        for (int i = 0; i < m; i++) {
            dfs(i, 0, board);
            dfs(i, n - 1, board);
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, board);
            dfs(m - 1, j, board);
        }

        // Step 2: Flip remaining O -> X, # -> O
        //koi O agr abhi tk bacha hua hai, to mtlb wo doob jayega because it is not connected to any boundary group
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        int m = board.length, n = board[0].length;

        // out of bound OR water check
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = '#'; // mark unsafe
        
        dfs(i + 1, j, board);
        dfs(i - 1, j, board);
        dfs(i, j + 1, board);
        dfs(i, j - 1, board);
    }
}