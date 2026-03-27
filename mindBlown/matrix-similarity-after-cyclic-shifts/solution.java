class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k %= n; // ki effectively kitna ghooma
        for(int i=0;i<m;i++){ // i me kuch nhi krna kyuki rows rotate ho rahi hai
            for(int j=0; j<n; j++){
                int J = j;
                if(i%2 == 0) J -= k;
                else J += k;
                J = J>=n ? J-n : J; // agar size se 1 aage chle gye mtlb 0th index
                J = J<0 ? n+J : J; // agr -1 aagya J mtlb right se last
                if(mat[i][j] != mat[i][J]) return false;
            }
        }
        return true;
    }
}