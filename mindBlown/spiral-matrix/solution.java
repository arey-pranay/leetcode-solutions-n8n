class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        int top = 0;
        int bottom = m-1;
        int left = 0;
        int right = n-1;
        while(ans.size() != m*n){
            for (int i = left;i <= right; i++) ans.add(matrix[top][i]);
            top++;
            for (int i = top; ans.size() < m*n && i <= bottom; i++) ans.add(matrix[i][right]);
            right--;
            for (int i = right; ans.size() < m*n && i >= left; i--) ans.add(matrix[bottom][i]);
            bottom--;
            for (int i = bottom; ans.size() < m*n && i >=top ; i--) ans.add(matrix[i][left]);
            left++;
        }
        return ans;
    }
}