class Solution {
    public int maxLength(int[] nums) {
        // a*b = lcm(a,b)*gcd(a,b)
        int ans = 0,n=nums.length;
        for(int i=0;i<n;i++){
            int product = 1;
            int lcm = 1;
            int gcd = 0;
            for(int j=i;j<n;j++){
                product *= nums[j];// 64
                gcd = getGcd(gcd,nums[j]); // 4
                lcm = (lcm/getGcd(lcm,nums[j])) * nums[j]; //2
                if(product == lcm*gcd) ans = Math.max(ans,j-i+1); //true always
            }
        }
        return ans;
    }
    public int getGcd(int a , int b){
        if(b==0) return a;
        return getGcd(b,a%b);
    }
}

  // for arr having 1, 5 and 7, gcd = 1. we would need lcm = prod
        // gcd can never grow, lcm can never shrink
        // gcd <= lowest, lcm >= highest, product != 1 (except all 1)
        