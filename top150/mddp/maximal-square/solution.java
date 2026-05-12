class Solution {
    char[][] mat;
    int[][] memo;
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        mat = matrix;
        int ans = 0;
        memo = new int[m][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        for(int i=0; i<m; i++) for(int j=0; j<n; j++) ans = Math.max(ans,func(i,j,0));
        return ans*ans;
    }
    public int func(int r , int c , int d){
        if(r == mat.length || c == mat[0].length) return 0;
        if(memo[r][c] != -1) return memo[r][c];
        
        int ans = 0;
        if(mat[r][c] == '1'){
            int x = func(r,c+1,d+1);
            int y = func(r+1,c+1,d+1);
            int z = func(r+1,c,d+1);
            ans = 1+Math.min(x,Math.min(y,z)); 
        }
        return memo[r][c] = ans;
    }
}

