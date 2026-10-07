class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        // last se start kyuki left to right jaane me insert ya delete krne se indices move hojayege, messy.
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        return func(word1, word2, m-1, n-1);
    }
    public int func(String word1, String word2, int i, int j){
        if(i<0) return j+1; //word1 khtm hua, to word2 ke saare char delete
        if(j<0) return i+1; //word2khtm hua, to word1 ke saare char delete
        
        if(memo[i][j] != -1) return memo[i][j];
        if(word1.charAt(i)==word2.charAt(j)) return memo[i][j] = func(word1,word2, i-1, j-1); // both moved one index, no op done
        
        int replace = 1 + func(word1,word2, i-1, j-1);
        
        // delete and insert practically mean the same thing, just for different strings
        int delete = 1 + func(word1, word2, i-1,j);                         // or (i-1,j)
        int insert = 1 + func(word1, word2, i,j-1);                         // or (j-1,i)
        
        return memo[i][j] = Math.min(delete,Math.min(insert,replace));
    }
}