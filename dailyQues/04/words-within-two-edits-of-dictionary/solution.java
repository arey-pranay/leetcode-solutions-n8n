class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        for(String query: queries){
            for(String dict: dictionary){
                if(match(query,dict)){
                    ans.add(query);
                    break;
                }
            }
        }
        return ans;
    }
    public boolean match(String a,String b){
        int n =a.length();
        if(n!=b.length()) return false;
        int i = 0;
        int mismatch =0 ;
        while(i<n){
            if(a.charAt(i)!=b.charAt(i)){
                mismatch++;
                if(mismatch>2) return false;
            }
            i++;
        }
        return true;
    }
}