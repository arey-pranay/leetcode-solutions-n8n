class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        StringBuilder sb = new StringBuilder("");
        sb.repeat(".",n);
        ans[0] = sb.toString();
        for(int i=1;i<m;i++){
            sb = new StringBuilder("");
            sb.repeat("#",n-1);
            sb.append(".");
            ans[i] = sb.toString();
        }
        return ans;
    }
}