// class Solution {
//     public boolean isMatch(String s, String p) {
//         int i = 0;
//         int j = 0;
//         int star = -1;
//         int match = 0;

//         while (i < s.length()) {
//             if (j < p.length() &&(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {i++;j++;} //match curr chars
//             else if (j < p.length() && p.charAt(j) == '*') { match = i; star = j++; }  //
//             else if (star != -1) { i = match++; j = star + 1; } 
//             else return false;
//         }
//         while (j < p.length() && p.charAt(j) == '*') j++;
//         return j == p.length();
//     }
// }




class Solution {
    String s,p;
    int m,n;
    Boolean[][] memo;
    boolean[] stars;
    public boolean isMatch(String S, String P) {
       s=S;p=P;m=S.length();n=P.length();
       memo = new Boolean[m][n];
       stars = new boolean[n+1];
       stars[n] = true;
       for(int i=n-1;i>=0;i--) stars[i] = stars[i+1] && p.charAt(i)=='*';
       return func(0,0);
    }
    private boolean func(int i, int j){
        if(i==m) return stars[j];
        if(j==n) return false;
        if(memo[i][j]!=null)return memo[i][j];
        if(p.charAt(j)=='*') return memo[i][j] = func(i+1,j) || func(i,j+1);
        if(p.charAt(j)=='?' || p.charAt(j)==s.charAt(i)) return memo[i][j] = func(i+1,j+1);
        return memo[i][j] = false;
    }
}