class Solution {
    static int base = 29;
    static long MOD  = 1_000_000_007;
    public String shortestPalindrome(String s) {
        if(s.isEmpty()) return s;
        int n = s.length();
        long fwd = 0, bwd = 0, power = 1;
        int end = 0;
        for(int i=0;i<n;i++){
            int c = s.charAt(i)-'a';   // aba
            fwd = (fwd*base + c)%MOD;  // 0*29 + a = a // a*29 + b // a*29^2 + b*29 + a
            bwd = (bwd + c*power)%MOD; // 0 + a*1  = a // a + b*29 // a + b*29 + a*29^2
            // ab me a ka weightage zyada hai, b ka km. we multiply base with a, and just add b
            // ba me b ka weightage zyada hai, a ka km. we need to keep a lesser and multiply base with b
            power = (power*base)%MOD;
            if(fwd==bwd) end = i;
        } 
        return new StringBuilder(s.substring(end+1)).reverse().append(s).toString();
    }
}

// a*31 + b*31^2  <=> b*31 + a*31^2


// aabecaaa

// abyeeybaxedcxy

// yzcdexabyeeyba

// a
// aa
// aac <-> caa


// aacecaa a
// a 11----1 -

// acecaca
// acacecaca

// abcad
// dacba

// aaada =>2
// adaaa

// bcdabaabadcba


// abcefgcba

// "abcddcbb"
// "av"
// "bcabccbab"