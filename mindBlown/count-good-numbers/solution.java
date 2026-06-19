class Solution {
    int MOD = 1000000007;
    public int countGoodNumbers(long n) {
        long oddPos = n/2;
        long evenPos = n-oddPos;
        long evens = exp(5,evenPos);
        long odds = exp(4,oddPos);
        return (int) ((odds*evens)%MOD);
    }
    public long exp(long base, long power){
        long res = 1;
        base %= MOD;
        while(power > 0){
            if(power % 2 == 1) res = (res*base)%MOD;
            power /=2;
            base = (base*base)%MOD;
        }
        return res;
    }
}