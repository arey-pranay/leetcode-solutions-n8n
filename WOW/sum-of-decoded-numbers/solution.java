class Solution {
    public int sumDecoded(long[] nums) {
        long sum = 0;
        long mod = 1000000007;
        for(int i =0 ; i<nums.length;i++){
            long width = nums[i]%10;
            long d = (long)Math.floor(nums[i]/10);
            long len = String.valueOf(d).length();
            long x = d/(long)(Math.pow(10,len-width));
            long y = d%(long)(Math.pow(10,len-width));
            long temp = binExp(x, y, mod);
            sum += temp;
        }
        return (int)(sum%mod);    
    }
    private long binExp(long x, long y, long mod){
        long ans = 1;
        while(y>0){
            if((y&1)==1) ans = (ans*x)%mod;//4
            x = (x*x)%mod;
            y >>= 1;
        }
        return ans;
    }
     // x^21 => x^20 => x^20 = (x^2)^10
        // 7
        // 6
        // 3
        // 4^5
        // 5 = 10    
}