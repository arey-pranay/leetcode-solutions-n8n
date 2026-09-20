class Solution {
    public int reverseDegree(String s) {
        int ans = 0, i=1;
        for(char c: s.toCharArray()) ans += (i++)*(26-(c-'a'));
        return ans;
    }
}