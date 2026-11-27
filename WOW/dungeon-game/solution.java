class Solution {
    int[][] arr;
    Integer[][] memo;
    int  m,n;
    public int calculateMinimumHP(int[][] dungeon) {
        arr = dungeon; m =arr.length; n=arr[0].length; memo = new Integer[m][n];
        int ans = func(0,0);
        return ans >= 0 ? 1 : (-1*ans)+1;
    }
    private int func(int i, int j){ // aage jaane ke liye kitni energy aur lgegi
        if(i==m || j==n) return Integer.MIN_VALUE;
        if(i==m-1 && j==n-1) return arr[i][j] >= 0 ? 0 : arr[i][j];
        if(memo[i][j] != null) return memo[i][j];
        int temp = arr[i][j] + Math.max(func(i+1,j),func(i,j+1)); // negative me magnitude km hai
        return memo[i][j] = temp >= 0 ? 0 : temp; // kisi point pe agr positive output aara hai, to wahan jaane ke liye kuch extra energy nhi needed hai
    }
}