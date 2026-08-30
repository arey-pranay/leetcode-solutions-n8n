class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int[] pref = new int[n];
        int[] suff = new int[n];
        
        pref[0] = nums[0]; suff[n-1] = nums[n-1];
        for(int i =1 ; i<n ; i++) pref[i] = gcd(pref[i-1],nums[i]);
        for(int i = n-2 ; i>=0;i--) suff[i] = gcd(suff[i+1],nums[i]);
        
        int count = 0;
        for(int i =0 ; i<n-1;i++) if(pref[i]==suff[i+1]) count++;
        int max = count;
        // --------------------------------------------------------
        int m = n-1;
        for(int k=0; k<n;k++){
          int[] now = new int[m];
          int j=0;
          for(int i=0;i<n;i++)if(i!=k) now[j++] = nums[i];
          pref = new int[m];
          suff = new int[m];
          pref[0] = now[0]; suff[m-1] = now[m-1];
          for(int i =1 ; i<m ; i++) pref[i] = gcd(pref[i-1],now[i]);
          for(int i = m-2 ; i>=0;i--) suff[i] = gcd(suff[i+1],now[i]);
          
          count = 0;
          for(int i =0 ; i<m-1;i++) if(pref[i]==suff[i+1]) count++;
          max = Math.max(max,count);
        }
       
        return max;
    }
    public int gcd(int a, int b){
        if(a%b==0) return b;
        return gcd(b,a%b);
    }
}