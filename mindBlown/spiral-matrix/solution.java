class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int x0 = 0, x1 = m-1, y0 = 0, y1 = n-1;
        List<Integer> ans = new ArrayList<>();
        while(ans.size() != m*n){
            for(int j=y0;j<=y1;j++) ans.add(matrix[x0][j]); x0++;
            for(int i=x0;ans.size() < m*n && i<=x1;i++)ans.add(matrix[i][y1]); y1--;
            for(int j=y1;ans.size() < m*n && j>=y0;j--) ans.add(matrix[x1][j]); x1--;
            for(int i=x1;ans.size() < m*n && i>=x0;i--)ans.add(matrix[i][y0]); y0++;
        }
        return ans;
    }
}