class Solution {
    public int findContentChildren(int[] g, int[] s) {
       Thread t1 = new Thread(() -> {
            Arrays.sort(g);
        });
        Thread t2 = new Thread(() -> {
            Arrays.sort(s);
        });

        t1.start(); //2ms
        t2.start(); //2ms

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        int n = g.length, m = s.length, i = 0, j = 0;

        while (i < m && j < n) {
            if (g[j] <= s[i]) j++; 
            i++;  
        }

        return j;
    }
}