class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        if(s.isEmpty()) return true;
        if(s.length()>t.length()) return false;
        int i=0,j=0;
        // i ko humesha j+1 rkh skte hai 
        // kyuki j wo position hai jahan tk bina replacement ke subsequence bn rhi hai
        for(char c : t.toCharArray()){
            if(s.charAt(i)==c) i++; // 
            i = Math.max(i,j+1); // // i ko aage bdha diya, mtlb replacement use krliya
            if(s.charAt(j)==c) j++; // this guarantees ki hum j ko dirf match krne pe increment kr rhe hai (no replacements)
            if(i==s.length() || j==s.length()) return true;
        }
        return false;
    }
}