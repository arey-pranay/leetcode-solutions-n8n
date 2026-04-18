class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n-reverse(n));
    }
    public int reverse(int n){
        int digits = (int)Math.log10(n)+1;
        int ans = 0;
        while(digits>0){
            ans += (n%10)*Math.pow(10,--digits);
            n /= 10;
        }
        return ans;
    }        
}