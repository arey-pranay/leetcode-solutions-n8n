class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] smalls = new boolean[26];
        Boolean[] caps = new Boolean[26];
        for(char c : word.toCharArray()){
            if(Character.isLowerCase(c)){
                smalls[c-'a'] = true;
                char C = Character.toUpperCase(c);
                if(caps[C-'A'] !=null && caps[C-'A'] == true) caps[Character.toUpperCase(c)-'A'] = false;
            } else {
                if(caps[c-'A'] == null) caps[c-'A'] = true;
            }
        }
        int ans = 0;
        for(int i=0;i<26;i++) if(caps[i]!=null && smalls[i] && caps[i]) ans++;
        return ans;
    }
}