class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {

        int n = s1.length();
        int m = s2.length();

        //agr 2 strings tod ke teesri bnani hai to wo dono equal number of parts me tutegi, ya 1 ke fark me. Not more than that, kyuki if you try breaking s1 into 2 parts and s2 into 7 parts, to anyway jud ke connect hojayege.

        // ❗ If total length doesn't match, interleaving is impossible
        if(n + m != s3.length()) 
            return false;

        // dp[i][j] = true means:
        // First i characters of s1
        // and first j characters of s2
        // can form first (i + j) characters of s3
        boolean[][] dp = new boolean[n+1][m+1];

        // Base case:
        // Empty s1 and empty s2 form empty s3
        dp[0][0] = true;

        // Fill first column
        // Using only s1 to match s3
        for(int i = 1; i <= n; i++){
            dp[i][0] = dp[i-1][0] && 
                       (s1.charAt(i-1) == s3.charAt(i-1));
        }
// aabcbi
// aaowiqqwdiowjdql
// t t f f f f f f f f f 

        // Fill first row
        // Using only s2 to match s3
        for(int j = 1; j <= m; j++){
            dp[0][j] = dp[0][j-1] && 
                       (s2.charAt(j-1) == s3.charAt(j-1));
        }

        // Fill rest of DP table
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                // Current character in s3 we want to match
                char currentChar = s3.charAt(i + j - 1);

                // Option 1: Take character from s1
                boolean takeFromS1 =
                        dp[i-1][j] && 
                        (s1.charAt(i-1) == currentChar);

                // Option 2: Take character from s2
                boolean takeFromS2 =
                        dp[i][j-1] && 
                        (s2.charAt(j-1) == currentChar);

                // If either option works, it's valid
                dp[i][j] = takeFromS1 || takeFromS2;
            }
        }

        // Final answer:
        // Can entire s1 and s2 form entire s3?
        return dp[n][m];
    }
}