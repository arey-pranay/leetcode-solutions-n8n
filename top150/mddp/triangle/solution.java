class Solution {
    Integer[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {
        memo= new Integer[triangle.size()][triangle.size()];
        return func(triangle,0,0);
    } 
    public int func(List<List<Integer>> triangle , int row, int col){
        if(row >= triangle.size()) return 0;
        if(memo[row][col] != null) return memo[row][col];
        int val = triangle.get(row).get(col);
        int a = func(triangle,row+1,col);
        int b = func(triangle,row+1,col+1);
        return memo[row][col] = val + Math.min(a,b);
    }
}