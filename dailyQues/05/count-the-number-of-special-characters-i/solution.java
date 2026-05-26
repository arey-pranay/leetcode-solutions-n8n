class Solution {
    public int numberOfSpecialChars(String word) {
        
        boolean[] caps = new boolean[26];
        boolean[] smalls = new boolean[26];
        for(char c : word.toCharArray()) if(Character.isLowerCase(c)) smalls[c-'a'] = true; else caps[c-'A'] = true;
        
        int count = 0;
        for(int i=0;i<26;i++) if(caps[i] && smalls[i]) count++;
        return count;
        
    }
}