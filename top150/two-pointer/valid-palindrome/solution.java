class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(Character.isLetterOrDigit(c))sb.append(Character.toLowerCase(c));
        }
        int i = 0;
        int j = sb.length()-1;
        // i<j is used to handle both conditions -> even and odd palindrome condtions
        while(i<j) if(sb.charAt(i++) != sb.charAt(j--)) return false;
        return true;
    }
}
// even hua to i aur j pados pados mei aakr cross hojayenge 
// agr odd hua to i aur j barabari p aajaynge 