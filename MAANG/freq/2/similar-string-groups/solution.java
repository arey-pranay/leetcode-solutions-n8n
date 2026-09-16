class Solution {
    public int numSimilarGroups(String[] strs) {
        int groups = 0;
        int n = strs.length;
        boolean[] vis= new boolean[n];
        for(int i=0;i<n;i++){
            if(vis[i]) continue;
            dfs(i,strs,vis);
            groups++;
        }
        return groups;
    }
    public void dfs(int i, String[] strs, boolean[] vis){
        vis[i] = true;
        for(int j=0;j<strs.length;j++){
            if(!vis[j] && isNeigh(strs[i],strs[j])){
                dfs(j,strs,vis);
            }
        }
    }
    public boolean isNeigh(String s1, String s2){
        int count = 0;
        for(int i=0; i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)) count++;
            if(count>2) return false;
        }
        return count==0 || count==2; // 0 or 1 pair of mismatch is found, so similar
    }
}

// tars
// rats
// a,b
// b,c

// a,b,c