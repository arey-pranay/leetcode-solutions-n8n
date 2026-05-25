class Solution {
    public boolean isSubsequence(String s, String t) {
        int i =0;
        if(s.isEmpty()) return true;
        for(char c : t.toCharArray()) if(i == s.length()) return true; else if(s.charAt(i)==c) i++;
        return i == s.length();
    }
}