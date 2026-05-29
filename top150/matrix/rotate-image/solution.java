class Solution {
    public void rotate(int[][] matrix) {
        revertColumns(matrix);
        transpose(matrix);
    }
    public void revertColumns(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n/2;j++){
                int temp = matrix[j][i];
                matrix[j][i] = matrix[n-1-j][i];
                matrix[n-1-j][i] = temp;
            }
        }
    }
    public void transpose(int[][] matrix){
        for(int i =0 ; i<matrix.length;i++){
            for(int j =i+1 ; j<matrix[i].length;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}