class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        // return solve(word1,word2,m-1,n-1);
        
        int[][] matrix = new int[m+1][n+1];
        for(int i =0;i<m+1;i++){
            for(int j =0 ; j<n+1;j++){
                if(i==0 || j==0) matrix[i][j]=0;
                else if (word1.charAt(i-1)==word2.charAt(j-1)) matrix[i][j] = matrix[i-1][j-1];
                else{
                    int x = Math.min(matrix[i-1][j]+1,matrix[i][j-1]+1);
                    int y = Math.min(x,matrix[i-1][j-1]+1);
                    matrix[i][j] = y;
                }
            }
        }
        if(matrix[m][n]==0) return 0;
        return matrix[m][n]+1;
    }
    public int solve(String s1, String s2, int i, int j){
        if(i<0) return j+1; 
        if(j<0) return i+1; //i =0 mtlb 1 character. i=4 mtlb 5 character etc. isliye i+1 add kara
        if(memo[i][j]!=-1) return memo[i][j];
        if(s1.charAt(i) == s2.charAt(j)) return solve(s1,s2,i-1,j-1);
        // s1 me se:
        int delete = 1 + solve(s1,s2,i-1,j);// -> i wala character solved, kyuki delete krdiya usko
        int insert = 1 + solve(s1,s2,i,j-1); // j wala character solved, kyuki usi ko s1 me insert krdiya
        int replace= 1 + solve(s1,s2,i-1,j-1); // -> i and j dono solved
        return memo[i][j]= Math.min(replace,Math.min(delete,insert));

    }
}
//     h o r s e 
//     0 0 0 0 0
// r 0 1 1 0 1 1
// o 0 
// s 0


// h o r s e i=4
// - - r o s j=2
