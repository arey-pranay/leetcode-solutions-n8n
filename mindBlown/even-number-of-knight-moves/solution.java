class Solution {
    public boolean canReach(int[] s, int[] t) {
        return  ( (s[0] + s[1])%2 == (t[0] + t[1]) % 2 );
    }
    // if target and start are of the same color, then possible in even moves
}
// / even (x+y) sum wale black hai