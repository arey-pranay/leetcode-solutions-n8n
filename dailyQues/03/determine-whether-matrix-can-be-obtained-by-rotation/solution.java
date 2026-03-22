class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        boolean found = true;
        int n = mat.length;
        boolean r0=true,r1=true,r2=true,r3= true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) r0 = false;
                if (mat[i][j] != target[n - 1 - i][n-1-j]) r1 = false;
                if (mat[i][j] != target[n-1-j][i]) r2 = false;
                if (mat[i][j] != target[j][n-1-i]) r3 = false;
            }
        }
        return r0 || r1 || r2 || r3;
    }
}

//Another solution to be considered.
// class Solution {
//     public boolean findRotation(int[][] mat, int[][] target) {
//         boolean found = true;
//         int n = mat.length;
//         // ith row becomes ((n-1)-i)th col

//         //row to row
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (mat[i][j] != target[i][j]) {
//                     found = false;
//                     break;
//                 }
//                 if (!found)
//                     break;
//             }
//         }
//         if (found)
//             return true;
//         //row to opp row
//         found = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (mat[i][j] != target[n - 1 - i][n-1-j]) {
                    
//                     found = false;
//                     break;
//                 }
//             }
//             if (!found)
//                 break;
//         }
//         if (found)
//             return true;

//         //row to col
//         found = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
                
//                 if (mat[i][j] != target[n-1-j][i]) {
//                     found = false;
//                     break;
//                 }
//             }
//             if (!found)
//                 break;
//         }
//         if (found)
//             return true;

//         //row to opp col
//         found = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
                
//                 if (mat[i][j] != target[j][n-1-i]) {
//                     found = false;
//                     break;
//                 }
//             }
//             if (!found)
//                 break;
//         }        
//         return found;
//     }
// }