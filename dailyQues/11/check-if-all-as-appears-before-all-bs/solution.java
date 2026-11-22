class Solution {
    public boolean checkString(String s) {
        boolean bFound = false;
        for(char c : s.toCharArray()){
            if(c=='b') bFound=true;
            if(bFound && c=='a') return false;
        }
        return true;
    }
}