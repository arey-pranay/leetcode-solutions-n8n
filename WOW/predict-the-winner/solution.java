class Solution {
    int[][] memo;
    int[] A;
    public boolean predictTheWinner(int[] arr) {
        int n = arr.length;
        if(n%2==0) return true;// we can always win in even length, because we will always get the cahnce to pick out of the available pair
        memo = new int[n][n];
        A = arr;
        for (int[] temp: memo) Arrays.fill(temp, -1);
        return maxDiff(0, n - 1) >= 0;
    }

    private int maxDiff(int i, int j) {//considering you maximize your difference from their choice at every point
        if (memo[i][j] != -1) return memo[i][j];        
        if (i == j) return memo[i][j] = A[i];
        return memo[i][j] = Math.max(A[i] - maxDiff(i + 1, j), A[j] - maxDiff(i, j - 1));
    }
}
//assuming both cases happen, then we make 2 funtion calls