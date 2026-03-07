class Solution {
    public void rotate(int[][] matrix) {
        // transpose the matrix, diagonally
        for(int i=0;i<matrix.length;i++){
            for(int j=i;j<matrix[0].length;j++){
                swapMat(matrix,i,j);
            }
        }
        // rotate each row
        for(int[] a : matrix) revRow(a);
    }
    public void swapMat (int[][] mat, int i, int j){
        int temp = mat[i][j];
        mat[i][j] = mat[j][i];
        mat[j][i] = temp;
    }
    public void revRow (int[] row){
        int i=0;
        int j= row.length-1;
        while(i<=j){
            swap(row,i,j);
            i++;
            j--;
        }
    }
    public void swap (int[] arr, int i, int j){
        int temp  = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}