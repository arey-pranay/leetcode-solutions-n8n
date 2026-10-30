class Solution {
    long MOD = 1_000_000_007L;
    public int numDecodings(String str) {
        if(str.isEmpty() || str.charAt(0)=='0') return 0;
        char[] s = str.toCharArray();
        long[] dp = new long[s.length+1]; // dp[i] means the number of ways to decode substring s(0,i) 
        dp[0] = 1; 
        
        if(s[0] == '*') dp[1] = 9; // * can become 9 possible digits
        else dp[1] = 1; // every other single digit can only be interpreted in one way
        
        for(int i =2; i<=s.length; i++){ // now let's build for second character onwards
            char first = s[i-2], second = s[i-1]; // we need to access thee possibilites of the previous 2 charactersnow
            
            // just considering the contribution of previous character
            if(second == '*') dp[i] += 9*dp[i-1];
            else if(second != '0') dp[i] += dp[i-1]; // because 0 cannot contribute alone, no character corresponds to it, it is either 10 or 20, always in a pair
            
            // now considering if the previous two could have been merged and how many values would they contribute
            if(first == '1' || first == '2'){        
                if(second == '*') { // 1* means multiplier of 9 and 2* means 6 (20 21 22 23 24 25)
                    if(first == '1') dp[i] += 9*dp[i-2];
                    if(first == '2') dp[i] += 6*dp[i-2];
                } else { // this is for normal cases, we have the same interpretations, 10 .. 19, and 20 .. 26
                    if(first=='1') dp[i] += dp[i-2];
                    else if(second <= '6') dp[i] += dp[i-2];
                }
            } else if(first=='*') {
                if(second=='*') dp[i] += 15*dp[i-2]; // 9+6. 9 for 1* and 6 for 2*, the leftmost star can only contribute by being either 1 or 2
                else {
                    if(second<='6') dp[i] += 2*dp[i-2]; // *5 can mean 10 to 15 and also 20 to 25
                    else dp[i] += dp[i-2]; // then only 17 18 19 etc, so same as before, not doubled nhere
                }
            }
            
            dp[i] %= MOD;   
        }
        return (int) (dp[s.length] % MOD);
    }
}
// 1*2**3*

// 1
// * ->9
// 1* -> 18
// *2 -> 18
// 2 -> 1

//             12*3
//     12*(3)     -     12(*3)
// 12(*)(3) 1(2*)(3)  -  1(2)(*3)   (12)(*3)
// (1)(2)(*)(3)