class Solution {
    public int coinChange(int[] a, int k) {
        int[] f = new int[k + 1];
        java.util.Arrays.fill(f, (int)1e9);
        f[0] = 0;
        
        for (int x : a) {
            for (int i = x; i <= k; i++) {
                if (f[i - x] + 1 < f[i]) {
                    f[i] = f[i - x] + 1;
                }
            }
        }
        
        int ans = f[k];
        if (ans == (int)1e9) {
            ans = -1;
        }
        return ans;
    }
}