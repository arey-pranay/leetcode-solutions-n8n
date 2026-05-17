class Solution {
    Boolean[] memo;
    HashSet<Integer> checking;
    public boolean canReach(int[] arr, int start) {
        HashSet<Integer> zeroes = new HashSet<>();
        checking = new HashSet<>();
        int n = arr.length;
        memo = new Boolean[n];
        for(int i=0;i<n;i++)if(arr[i]==0)zeroes.add(i);
        return func(arr, start, zeroes);
    }
    public boolean func(int[] arr, int start, HashSet<Integer> zeroes){
        if(zeroes.contains(start)) return true;
        int n = arr.length;
        if(checking.contains(start) || start < 0 || start >= n) return false;
        if(memo[start] != null) return memo[start];
        checking.add(start);
        memo[start] = func(arr,start-arr[start],zeroes) || func(arr,start+arr[start],zeroes);
        checking.remove(start);
        return memo[start];
    }
}