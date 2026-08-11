class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //total o=m*n numbers hai, aur ye sorted order me merge hone pe m*n
        // sbse chhote o/2 numbers ka highest, aur bache hue t/2 numbers ka lowest
        //m=3 n=4 => 7 => o/2 = 3. 
        if(m>n) return findMedianSortedArrays(nums2,nums1); // because we assumed m is smaller. (1st array is smaller)
        int o = m+n;
  
        //cut2 lgaya n/2 pe
        //cut1 lgega o - cut2;
        int s=0, e=m;
  
        //cut mid pe lga lo, aur fir check krte rho cut ko kahan move krna hai
        while(s<=e){
            int cut1 = (s+e)/2, cut2 = o/2-cut1;
            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1-1]; //cut ke left wala element in nums1. //cut1 agr 0 tk aa gya, means l1 ignore krna hia, and since we use max of l1,l2. we can put l1 as int minvalue
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2-1];
            int r1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];
            //agr ye cuts correct hai, nums1[cut1] > nums2[cut2-1] => it means cut1 further right nhi ja skta. 
            // agr cuts valid hai, mtlb left ke elements right se chhote hoge, we know that l1<r1 & l2<r2 kyuki nums sorted hhai.
          // we need to just check
            if(l1<=r2 && l2<=r1){
                if(o%2==1) return Math.min(r1,r2);   //large part ka highest  //5 => 2,3 not 3,2
                else return (Math.max(l1,l2)+Math.min(r1,r2))/2.0;//(smalls ka h + bigs ka l)/2
            }
            if(l1 > r2) e=cut1-1; //nums1 ka element bda hai, usse aur chhota element chahiye
            else s = cut1+1;
          //cut 2 will be recalculated
        }
        return -1;
    }
}
