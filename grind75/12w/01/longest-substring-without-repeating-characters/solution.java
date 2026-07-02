class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        int[] index = new int[256];
        Arrays.fill(index,-1);
        int i = 0;
        int j = 0;
        int ans = 1;
        while(j<s.length()){
            char c = s.charAt(j);
            if(index[c] >= i) 
            i = index[c] + 1;
            index[c] = j;
            ans = Math.max(ans,j-i+1);
            j++;
        }
        return ans;
    }
}
// abcabcdd
// abc a