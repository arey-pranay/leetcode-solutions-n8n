class Solution {
    public int splitArray(int[] nums, int k) {
        // low  = 10 (max)
        // high = 32 (total)
        int low = 0, high = 0; // humaara ans will be the max number in case we divide all of them separately aur humara ans will be max if we put all of them in one group. we need to pick the correct answer between them.
        for(int num : nums){low = Math.max(low,num); high += num;}
        int res = high;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(canBreak(nums,k,mid)){
                res = mid;
                high = mid - 1;
            } else {
                low = mid+1;
            }
        }
        return res;
    }
    public boolean canBreak(int[] nums, int k, int maxSum){
        // running sum lenge, aur jese hi sum maxSum se increase hua, sum reset and k--
        
        // kya hum nums ko k groups me tod skte hai, jisse har group ka sum maxSum se km ho
        
        // agr k extra bach gye to theek hai, we can obviously make more groups, but k khtm hogye to hum haar gye
         
        int sum = 0;
        for(int num : nums){
            sum += num;
            if(sum>maxSum){
                sum = num;
                k--;
                if(k==0) return false;
            }
        }
        return true;
    }
}
// 7 2,5,10,8 => 7,25
// 7,2 5,10,8 => 9,23
// 7,2,5 10,8 => 14,18
// 7,2,5,10,8 => 24,8


// 7     2     5,10,8
  