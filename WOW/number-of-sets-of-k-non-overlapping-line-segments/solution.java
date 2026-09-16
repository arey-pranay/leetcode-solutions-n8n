class Solution {
    int MOD = 1_000_000_007;
    int[][] memo;
    public int numberOfSets(int n, int k) {
        memo = new int[n+k][2*k+1];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        return func(0,2*k,n+k-1)%MOD;
    }
    public int func(int index, int rem, int n){
        if(rem == 0) return 1;
        if(index >= n) return 0;
        if(memo[index][rem] != -1) return memo[index][rem];
        int take = func(index+1, rem-1, n)%MOD; //n+k-1
        int skip = func(index+1, rem, n)%MOD;
        return memo[index][rem] = (take + skip)%MOD;
    }
}
// a 1 1 2
// b 1 2 1

// a+b < n

// {01 23}
// {12 23}
// {01 12}

// 01 => 13

_ _ _ _ _
12 23 34 45
4=> 5

1 
2
3
4
5
_ _ _

01 12 23
01 12 34
01 12 24
01 13 34
---

