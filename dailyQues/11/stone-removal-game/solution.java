class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        boolean aliceWin = false;
        while(n >= take){ n -= take; take--; aliceWin = !aliceWin; }
        return aliceWin;
    }
    // int k = (int) ((21 - Math.sqrt(441 - 8 * n)) / 2);
    // return k % 2 == 1;
}