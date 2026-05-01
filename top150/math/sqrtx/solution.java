class Solution {
    public int mySqrt(int x) {
        if(x <= 1) return x;
        int l = 1;
        int r = x/2;
        int ans = 1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(mid <= x/mid){
                ans = mid; // if 2.8 is the answer, then 2 ko ans lena hai, 3 ko nhi lena hai.
                l = mid+1;
            }
            else r = mid-1;
        }
        return ans;
    }
}